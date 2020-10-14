package com.hih.pcbcutter.ui.bean;

/**
 * Created by likeye on 2020/8/18 10:48.
 **/
class PicParameter {
    private String tc;
    private String tk;
    private String[] txcs;
    private String x1;
    private String y1;
    private String x2;
    private String y2;

    public void setParameter(String[] a){
        this.tc = a[0];
        this.tk = a[1];
        String[]b=new String[9];
        for(int i=0;i<9;i++){
            b[i]=a[i+2];
        }
        this.txcs = b;
        this.x1 = a[11];
        this.y1 = a[12];
        this.x2 = a[13];
        this.y2 = a[14];
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String[] getTxcs() {
        return txcs;
    }

    public void setTxcs(String[] txcs) {
        this.txcs = txcs;
    }

    public String getX1() {
        return x1;
    }

    public void setX1(String x1) {
        this.x1 = x1;
    }

    public String getY1() {
        return y1;
    }

    public void setY1(String y1) {
        this.y1 = y1;
    }

    public String getX2() {
        return x2;
    }

    public void setX2(String x2) {
        this.x2 = x2;
    }

    public String getY2() {
        return y2;
    }

    public void setY2(String y2) {
        this.y2 = y2;
    }
}
