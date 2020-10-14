package com.hih.pcbcutter.ui.bean;

/**
 * Created by likeye on 2020/8/21 9:36.
 **/
public class ErrorMsg {
    private String num;
    private String time;
    private String msg;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ErrorMsg{" +
                "num='" + num + '\'' +
                ", time='" + time + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
