package com.hih.pcbcutter.ui.bean;

import java.util.List;

/**
 * Created by likeye on 2020/8/3 8:46.
 **/
public class Axle {
    private int axlenum;//轴号
    private int pulse;//脉冲数
    private int screwPitch;//镙距
    private int qssd;//起始速度
    private int yxsd;//运行速度
    private int hlsd;//回零速度
    private int jsd;//加速度
    private int jiansd;//减速度
    private int rxwz;//软限位正
    private int rxwf;//软限位负
    private int yxzt;//运行状态
    private int dqwz;//当前位置
    public void set(List<String>list){
        this.axlenum = Integer.parseInt(list.get(0));
        this.pulse = Integer.parseInt(list.get(1));
        this.screwPitch = Integer.parseInt(list.get(2));
        this.qssd = Integer.parseInt(list.get(3));
        this.yxsd = Integer.parseInt(list.get(4));
        this.hlsd = Integer.parseInt(list.get(5));
        this.jsd = Integer.parseInt(list.get(6));
        this.jiansd = Integer.parseInt(list.get(7));
        this.rxwz = Integer.parseInt(list.get(8));
        this.rxwf = Integer.parseInt(list.get(9));
    }
    public int getAxlenum() {
        return axlenum;
    }

    public void setAxlenum(int axlenum) {
        this.axlenum = axlenum;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public int getScrewPitch() {
        return screwPitch;
    }

    public void setScrewPitch(int screwPitch) {
        this.screwPitch = screwPitch;
    }

    public int getQssd() {
        return qssd;
    }

    public void setQssd(int qssd) {
        this.qssd = qssd;
    }

    public int getYxsd() {
        return yxsd;
    }

    public void setYxsd(int yxsd) {
        this.yxsd = yxsd;
    }

    public int getHlsd() {
        return hlsd;
    }

    public void setHlsd(int hlsd) {
        this.hlsd = hlsd;
    }

    public int getJsd() {
        return jsd;
    }

    public void setJsd(int jsd) {
        this.jsd = jsd;
    }

    public int getJiansd() {
        return jiansd;
    }

    public void setJiansd(int jiansd) {
        this.jiansd = jiansd;
    }

    public int getRxwz() {
        return rxwz;
    }

    public void setRxwz(int rxwz) {
        this.rxwz = rxwz;
    }

    public int getRxwf() {
        return rxwf;
    }

    public void setRxwf(int rxwf) {
        this.rxwf = rxwf;
    }

    public int getYxzt() {
        return yxzt;
    }

    public void setYxzt(int yxzt) {
        this.yxzt = yxzt;
    }

    public int getDqwz() {
        return dqwz;
    }

    public void setDqwz(int dqwz) {
        this.dqwz = dqwz;
    }

    @Override
    public String toString() {
        return  axlenum +
                "," + pulse +
                "," + screwPitch +
                "," + qssd +
                "," + yxsd +
                "," + hlsd +
                "," + jsd +
                "," + jiansd +
                "," + rxwz +
                "," + rxwf;
    }

}
