package com.hih.pcbcutter.ui.bean;

/**
 * Created by likeye on 2020/8/12 11:19.
 **/
public class PointMove {
    private int axleNum;
    private int wz;
    private int sd;
    private int jxd;

    public int getAxleNum() {
        return axleNum;
    }

    public void setAxleNum(int axleNum) {
        this.axleNum = axleNum;
    }

    public int getWz() {
        return wz;
    }

    public void setWz(int wz) {
        this.wz = wz;
    }

    public int getSd() {
        return sd;
    }

    public void setSd(int sd) {
        this.sd = sd;
    }

    public int getJxd() {
        return jxd;
    }

    public void setJxd(int jxd) {
        this.jxd = jxd;
    }

    @Override
    public String toString() {
        return  axleNum +
                "," + wz +
                "," + sd +
                "," + jxd;
    }
}
