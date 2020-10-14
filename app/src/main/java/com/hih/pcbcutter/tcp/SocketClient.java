package com.hih.pcbcutter.tcp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by likeye on 2020/7/25 9:59.
 **/
public class SocketClient {
    private static SocketClient socketClient;
    private Socket mClient;
    private ConnectListener mListener;
    private Thread mConnectThread;
    private String mSerIP = null;
    private int mSerPort = 0;


    public void setOnConnectListener(ConnectListener linstener) {
        this.mListener = linstener;
    }

    public static SocketClient getInstance() {
        if (socketClient == null)
            socketClient = new SocketClient();
        return socketClient;
    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100://socket线程接收到数据交给Handler处理
                    if (mListener != null) {
                        Log.d("ms","data="+msg.getData().getString("data"));
                        mListener.onReceiveData(msg.getData().getString("data"));
                    }
                    break;
            }
        }
    };

    /**
     * 创建连接
     *
     * @param mSerIP
     * @param mSerPort
     */
    private void creatConnect(final String mSerIP, final int mSerPort) {
        this.mSerIP = mSerIP;
        this.mSerPort = mSerPort;
        if (mConnectThread == null) {
            mConnectThread = new Thread(() -> connect(mSerIP, mSerPort));
            mConnectThread.start();
        }
    }

    /**
     * 与服务端进行连接
     */
    private void connect(String mSerIP, int mSerPort) {
        int connect_count = 0;
        while (mClient == null) {//检测是否实例化，如果没有实例化则进行实例化
            connect_count++;
            if (mListener != null) {
                mListener.onConnectionLoading(connect_count);
            }

            try {
                mClient = new Socket(mSerIP, mSerPort);
                if (mListener != null) {
                    mListener.onConnectionSucceeded();//连接成功，回调通知
                }

            } catch (IOException e) {
                if (connect_count == 3) {//连接3次
                    if (mListener != null) {
                        mListener.onConnectError();
                    }
                    return;
                }
                SystemClock.sleep(3000);


            }
        }

        try {
            //接收数据
            InputStream inputStream = mClient.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            //读取服务器传来的信息
            String content;
            while ((content = bufferedReader.readLine()) != null) {
                Log.d("ss","co="+content);
                Message message = new Message();
                message.what = 100;
                Bundle bundle = new Bundle();
                bundle.putString("data", content);
                message.setData(bundle);
                mHandler.sendMessage(message);
            }
            /*byte[] buffer = new byte[1024];
            int len = -1;
            //读取数据
            while ((len = inputStream.read(buffer)) != -1) {
                String data = new String(buffer, 0, len);
                Message message = new Message();
                message.what = 100;
                Bundle bundle = new Bundle();
                bundle.putString("data", data);
                message.setData(bundle);
                mHandler.sendMessage(message);
            }*/
        } catch (IOException e) {
        }

    }

    /**
     * 发送数据
     *
     * @param data
     */
    public void send(final String data) {
        new Thread(() -> {
            try {
                PrintStream pStream=new PrintStream(mClient.getOutputStream());
                //发送数据
                pStream.println(data);
                //OutputStream outputStream = mClient.getOutputStream();
                //outputStream.write(data.getBytes());
            } catch (IOException e) {
                if (mListener != null) {
                    mListener.onBrokenPipe();
                }
                //释放连接资源
                mClient = null;
                mConnectThread = null;
                if (mSerIP != null
                        && mSerPort != 0) {
                    creatConnect(mSerIP, mSerPort);//重新连接
                }
            }
        }).start();
    }

    /**
     * 断开连接
     *
     * @throws IOException
     */
    public void disconnect() {
        if (mClient != null) {
            try {
                mClient.shutdownInput();
                mClient.shutdownOutput();
                mClient.close();
                mClient = null;
                mConnectThread = null;
                mListener.onDisconnect();
            } catch (IOException e) {
                Log.d("断开连接", e.getMessage());
            }
        }
    }

    /**
     * 对外暴露监听回调
     */
    interface ConnectListener {

        /**
         * 连接成功
         */
        void onConnectionSucceeded();

        /**
         * 连接等待
         *
         * @param i 值为连接次数
         */
        void onConnectionLoading(int i);

        /**
         * 接收数据
         *
         * @param data 值为接收到的数据
         */
        void onReceiveData(String data);

        /**
         * 连接错误，目前用于未连接到网络、连接超时...
         */
        void onConnectError();

        /**
         * 连接中断(连接断开后无法发送数据)
         */
        void onBrokenPipe();

        /**
         * 断开
         */
        void onDisconnect();
    }

}
