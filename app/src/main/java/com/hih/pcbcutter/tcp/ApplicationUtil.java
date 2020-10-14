package com.hih.pcbcutter.tcp;

/**
 * Created by likeye on 2020/7/23 9:59.
 **/
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;


public class ApplicationUtil extends Application {

    private static  String ADDRESS = "192.168.3.240";
    private static  int PORT = 5000;
    private ConnectListener mListener;
    private CListener listener;
    private TopListener topListener;
    private Socket socket;
    private IOListener ioListener;
    private TestListener testListener;
    private Top3Listener top3Listener;

    public void setOnConnectListener(ConnectListener linstener) {
        this.mListener = linstener;
    }
    public void setOnCListener(CListener listener) {
        this.listener = listener;
    }
    public void setOnTopCListener(TopListener listener) {
        this.topListener= listener;
    }
    public void setOnIOListener(IOListener listener) {
        this.ioListener= listener;
    }
    public void setOnTestListener(TestListener listener){this.testListener=listener;}
    public void setOnTop3Listener(Top3Listener listener){this.top3Listener=listener;}
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100://socket线程接收到数据交给Handler处理
                    if (mListener != null) {
                        mListener.onReceiveData(msg.what,msg.getData().getString("data"));
                    }
                    if(listener!=null){
                        listener.onReceiveData(msg.what,msg.getData().getString("data"));
                    }
                    if(topListener!=null){
                        topListener.onReceiveData(msg.what,msg.getData().getString("data"));
                    }
                    if(ioListener!=null){
                        ioListener.onReceiveData(msg.what,msg.getData().getString("data"));
                    }
                    if(testListener!=null){
                        testListener.onReceiveData(msg.what,msg.getData().getString("data"));
                    }
                    if(top3Listener!=null){
                        top3Listener.onReceiveData(msg.what,msg.getData().getString("data"));
                    }
                    break;
                case 0:
                    if(listener!=null){
                        listener.onBrokenPipe();
                    }
                    break;
            }
        }
    };
    public void setSocket(String address,int port){
        ADDRESS=address;
        PORT=port;
    }
    public void init() {
        new Thread(() -> {
            //与服务器建立连接
            try {
                socket = new Socket();
                SocketAddress socAddress = new InetSocketAddress(ADDRESS, PORT);
                socket.connect(socAddress, 2000);
                listener.onConnectionSucceeded();
            } catch (Exception e) {
                listener.onConnectError();
                e.printStackTrace();
            }
            try {
                if(socket!=null) {
                    //接收数据
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                    //读取服务器传来的信息
                    String content;
                    while ((content = bufferedReader.readLine()) != null) {
                        LogUtil.d("ss", "co=" + content);
                        Message message = new Message();
                        message.what = 100;
                        message.obj=content;
                        Bundle bundle = new Bundle();
                        bundle.putString("data", content);
                        message.setData(bundle);
                        mHandler.sendMessage(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                socket = null;
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("data", "断开连接。。。");
                message.setData(bundle);
                mHandler.sendMessage(message);
            }
        }).start();

    }
    //发送数据到服务端
    public void sendData(final String str) {
        new Thread(){
            @Override
            public void run() {
                try {
                   /* BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
                    bos.write(str.getBytes());
                    //一定不能忘记这步操作
                    bos.flush();
                    PrintStream pStream=new PrintStream(socket.getOutputStream());
                    //发送数据
                    pStream.println(str);
                    pStream.close();*/
                    LogUtil.d("ss","send="+str);
                    PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8),true);
                    output.println(str);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public Socket getSocket() {
        return socket;
    }



    /**
     * 对外暴露监听回调
     */
    public interface CListener {

        /**
         * 连接成功
         */
        void onConnectionSucceeded();


        /**
         * 接收数据
         *
         * @param data 值为接收到的数据
         */
        void onReceiveData(int flag,String data);

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
    public interface ConnectListener {
        void onReceiveData(int flag,String data);
    }
    public interface TopListener {
        void onReceiveData(int flag,String data);
    }
    public interface IOListener {
        void onReceiveData(int flag,String data);
    }
    public interface TestListener {
        void onReceiveData(int flag,String data);
    }
    public interface Top3Listener {
        void onReceiveData(int flag,String data);
    }
}
