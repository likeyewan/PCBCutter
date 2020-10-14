package com.hih.pcbcutter.pic.bean;

import android.graphics.Paint;
import android.graphics.PointF;


/**
 * Created by likeye on 2020/7/28 11:42.
 **/
//圆
public class Circle {
    private PointF point;
    private float r;
    private Paint paint;


    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
    public PointF getPoint() {
        return point;
    }

    public void setPoint(PointF point) {
        this.point = point;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }
}
