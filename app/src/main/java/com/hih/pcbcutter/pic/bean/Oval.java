package com.hih.pcbcutter.pic.bean;

import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;


/**
 * Created by likeye on 2020/7/29 17:33.
 **/
//椭圆
public class Oval  {
    private RectF rectF;
    private double angle;
    private Paint paint;


    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
    public RectF getRectF() {
        return rectF;
    }

    public void setRectF(RectF rectF) {
        this.rectF = rectF;
    }

    public PointF getTmp() {
        PointF tmp = new PointF();
        tmp.x=rectF.left+(rectF.right-rectF.left)/2;
        tmp.y=rectF.top;
        return tmp;
    }

    public PointF getBmp() {
        PointF bmp = new PointF();
        bmp.x=rectF.left+(rectF.right-rectF.left)/2;
        bmp.y=rectF.bottom;
        return bmp;
    }
    public PointF getLmp() {
        PointF lmp = new PointF();
        lmp.x=rectF.left;
        lmp.y=rectF.bottom+(rectF.top-rectF.bottom)/2;
        return lmp;
    }

    public PointF getRmp() {
        PointF rmp = new PointF();
        rmp.x=rectF.right;
        rmp.y=rectF.bottom+(rectF.top-rectF.bottom)/2;
        return rmp;
    }
    public PointF getPmid(){
        PointF pmid = new PointF();
        pmid.x=rectF.left+(rectF.right-rectF.left)/2;
        pmid.y=rectF.bottom+(rectF.top-rectF.bottom)/2;
        return pmid;
    }

    public PointF getZtmp() {
        double ang=Math.toRadians(getAngle());
        PointF ztmp = new PointF();
        ztmp.x= (float) (getPmid().x+getW()/2*Math.sin(ang));
        ztmp.y=(float)(getPmid().y-getW()/2*Math.cos(ang));
        return ztmp;
    }

    public PointF getZbmp() {
        double ang=Math.toRadians(getAngle());
        PointF zbmp = new PointF();
        zbmp.x= (float) (getPmid().x-getW()/2*Math.sin(ang));
        zbmp.y=(float)(getPmid().y+getW()/2*Math.cos(ang));
        return zbmp;
    }

    public PointF getZlmp() {
        double ang=Math.toRadians(getAngle());
        PointF zlmp = new PointF();
        zlmp.x=(float) (getPmid().x-getL()/2*Math.cos(ang));
        zlmp.y=(float)(getPmid().y-getL()/2*Math.sin(ang));
        return zlmp;
    }

    public PointF getZrmp() {
        double ang=Math.toRadians(getAngle());
        PointF zrmp = new PointF();
        zrmp.x=(float) (getPmid().x+getL()/2*Math.cos(ang));
        zrmp.y=(float)(getPmid().y+getL()/2*Math.sin(ang));
        return zrmp;
    }

    public double getL() {
        double l = Math.abs(rectF.left - rectF.right);
        return l;
    }

    public double getW() {
        double w = Math.abs(rectF.bottom - rectF.top);
        return w;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
