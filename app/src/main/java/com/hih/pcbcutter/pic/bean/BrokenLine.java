package com.hih.pcbcutter.pic.bean;

import android.graphics.Paint;
import android.graphics.PointF;

import java.util.List;

/**
 * Created by likeye on 2020/9/17 14:12.
 **/
public class BrokenLine {
    private List<PointF> BList;
    private Paint paint;


    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public List<PointF> getBList() {
        return BList;
    }

    public void setBList(List<PointF> BList) {
        this.BList = BList;
    }
}
