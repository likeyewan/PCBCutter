package com.hih.pcbcutter.ui.bean;

/**
 * Created by likeye on 2020/8/6 11:37.
 **/
public class DataSet {
    private String name;
    private String num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return  name + "," + num ;
    }
}
