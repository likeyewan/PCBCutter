package com.hih.pcbcutter.tcp;

import android.util.Log;

/**
 * Created by likeye on 2020/8/8 9:23.
 **/
public class LogUtil {
    private static final int level=1;
    public static void v(String tag,String msg){
        if(level<=1){
            Log.v(tag,msg);
        }
    }
    public static void d(String tag,String msg){
        if(level<=2){
            Log.d(tag,msg);
        }
    }
    public static void i(String tag,String msg){
        if(level<=3){
            Log.i(tag,msg);
        }
    }
    public static void w(String tag,String msg){
        if(level<=4){
            Log.w(tag,msg);
        }
    }
    public static void e(String tag,String msg){
        if(level<=5){
            Log.e(tag,msg);
        }
    }
}
