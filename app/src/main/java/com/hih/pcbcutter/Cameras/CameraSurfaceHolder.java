package com.hih.pcbcutter.Cameras;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 相机界面SurfaceView的Holder类
 */
public class CameraSurfaceHolder {
    private Context context;
    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private final SurfaceViewCallback callback = new SurfaceViewCallback();

    /**
    * 设置相机界面SurfaceView的Holder
     * @param context 从相机所在的Activity传入的context
     * @param surfaceView Holder所绑定的响应的SurfaceView
    * */
    public void setCameraSurfaceHolder(Context context, SurfaceView surfaceView) {
        this.context = context;
        this.surfaceView = surfaceView;
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(callback);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        callback.setContext(context);
    }

}


