package com.hih.pcbcutter.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.hih.pcbcutter.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by ThinkPad on 2018/4/17.
 */

public class Main extends Activity {

    private EditText et_send;
    private TextView tv_receive;
    private ImageView img_receive;
    private ImageView img_send;
    private Bitmap bit_send;

    private Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main1);

        et_send = findViewById(R.id.et_send);
        tv_receive = findViewById(R.id.tv_receive);
        img_receive = findViewById(R.id.img_receive);
        img_send = findViewById(R.id.img_send);

        handler = new Handler() {
            public void handleMessage(Message msg) {
                if(msg.what == 0x001) {
                    tv_receive.setText(msg.obj.toString());
                } else if(msg.what == 0x002) {
                    img_receive.setImageBitmap((Bitmap)msg.obj);
                } else if(msg.what == 0x003) {
                    Toast.makeText(Main.this, "发送完成", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    /**
     * 发送接收文字
     * @param view
     */
    public void sendandreceive(View view) {
        final String sendMessage = et_send.getText().toString();
        if(sendMessage.equals("")) {
            Toast.makeText(Main.this, "发送信息不能为空", Toast.LENGTH_SHORT).show();
        } else {
            new Thread() {
                public void run() {
                    try {
                        Socket s = new Socket(Config.IP, 30001);
                        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        OutputStream os = s.getOutputStream();
                        os.write((sendMessage + "\r\n").getBytes());
                        String content = "";
                        while((content = br.readLine())!=null) {
                            Message msg = new Message();
                            msg.what = 0x001;
                            msg.obj = content;
                            handler.sendMessage(msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    /**
     * 接收图片
     * @param view
     */
    public void receiveImg(View view) {
        new Thread() {
            public void run() {
                try {
                    Socket socket = new Socket(Config.IP, 30002);
                    DataInputStream dataInput = new DataInputStream(socket.getInputStream());
                    int size = dataInput.readInt();
                    byte[] data = new byte[size];
                    int len = 0;
                    while (len < size) {
                        len += dataInput.read(data, len, size - len);
                    }
                    ByteArrayOutputStream outPut = new ByteArrayOutputStream();
                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outPut);
                    socket.close();
                    Message msg = new Message();
                    msg.what = 0x002;
                    msg.obj = bmp;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 选择图片
     * @param view
     */
    public void selectImg(View view) {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,111);
    }

    /**
     * 发送图片
     * @param view
     */
    public void sendImg(View view) {
        if(bit_send == null) {
            Toast.makeText(Main.this, "清闲选择要发送的图片", Toast.LENGTH_SHORT).show();
        } else {
            new Thread() {
                public void run() {
                    try {
                        Socket s = new Socket(Config.IP, 30003);
                        OutputStream os = s.getOutputStream();
                        //将图片bitmap转换成字节数组
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bit_send.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] data = baos.toByteArray();
                        os.write(data);
                        os.flush();//刷新缓冲区
                        os.close();

                        Message msg = new Message();
                        msg.what = 0x003;
                        handler.sendMessage(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    private Uri photoUri;
    private String photoPath;
    /**
     * 打开相册选择照片的回调函数
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 111:
                    //data中自带有返回的uri
                    photoUri=data.getData();
                    //获取照片路径
                    String[] filePathColumn={MediaStore.Audio.Media.DATA};
                    Cursor cursor=getContentResolver().query(photoUri,filePathColumn,null,null,null);
                    cursor.moveToFirst();
                    photoPath=cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
                    cursor.close();
                    System.out.println(photoPath);
                    try {
                        bit_send = MediaStore.Images.Media.getBitmap(getContentResolver(),photoUri);
                        img_send.setImageBitmap(bit_send);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
