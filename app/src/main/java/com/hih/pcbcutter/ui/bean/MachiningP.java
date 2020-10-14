package com.hih.pcbcutter.ui.bean;

/**
 * Created by likeye on 2020/8/18 9:50.
 **/
public class MachiningP {
    private String pcb0;//PCB厚度0
    private String qgsdtj0;//切割速度调节0
    private String pcb1;//PCB厚度1
    private String qgsdtj1;//切割速度调节1
    private String djzj;//刀具直径
    private String ddsyjl;//单段使用距离
    private String xdcs;//下刀次数
    private String djsm;//刀具寿命
    private String ddjc;//断刀检测
    private String smms;//寿命模式
    private String meslj;//MES连接
    private String tmsm;//条码扫描
    private String tmlxxz;//条码类型选择
    private String lxxs;//类型显示
    private String kdsdtj;//空刀速度调节
    private String aqgd0;//安全高度0
    private String qygd0;//取样高度0
    private String qggd0;//切割高度0
    private String vqgd0;//V切高度0
    private String aqgd1;//安全高度1
    private String qygd1;//取样高度1
    private String qggd1;//切割高度1
    private String ddjcgd;//断刀检测高度
    private String vqgd1;//V切高度1
    private String djyy;//刀具已用
    private String gdyy;//滚刀已用
    private String cl;//产量
    private String zzxg;//Z轴限高
    private String hdwzx;//换刀位置X
    private String hdwzy;//换刀位置Y
    private String pcbkd;//PCB宽度
    private String xflw;//X放料位
    private String xqlw;//X取料位
    private String yflw;//Y放料位
    private String zjsx;//治具上下

    public void setParameter(String[] a){
        this.pcb0 = a[0];
        this.qgsdtj0 = a[1];
        this.pcb1 = a[2];
        this.qgsdtj1 = a[3];
        this.djzj = a[4];
        this.ddsyjl = a[5];
        this.xdcs = a[6];
        this.djsm = a[7];
        this.ddjc = a[8];
        this.smms = a[9];
        this.meslj = a[10];
        this.tmsm = a[11];
        this.tmlxxz = a[12];
        this.lxxs = a[13];
        this.kdsdtj = a[14];
        this.aqgd0 = a[15];
        this.qygd0 = a[16];
        this.qggd0 = a[17];
        this.vqgd0 = a[18];
        this.aqgd1 = a[19];
        this.qygd1 = a[20];
        this.qggd1 = a[21];
        this.ddjcgd = a[22];
        this.vqgd1 = a[23];
        this.djyy = a[24];
        this.gdyy = a[25];
        this.cl = a[26];
        this.zzxg = a[27];
        this.hdwzx = a[28];
        this.hdwzy = a[29];
        this.pcbkd = a[30];
        this.xflw = a[31];
        this.xqlw = a[32];
        this.yflw = a[33];
        this.zjsx = a[34];
    }

    public String getPcb0() {
        return pcb0;
    }

    public void setPcb0(String pcb0) {
        this.pcb0 = pcb0;
    }

    public String getQgsdtj0() {
        return qgsdtj0;
    }

    public void setQgsdtj0(String qgsdtj0) {
        this.qgsdtj0 = qgsdtj0;
    }

    public String getPcb1() {
        return pcb1;
    }

    public void setPcb1(String pcb1) {
        this.pcb1 = pcb1;
    }

    public String getQgsdtj1() {
        return qgsdtj1;
    }

    public void setQgsdtj1(String qgsdtj1) {
        this.qgsdtj1 = qgsdtj1;
    }

    public String getDjzj() {
        return djzj;
    }

    public void setDjzj(String djzj) {
        this.djzj = djzj;
    }

    public String getDdsyjl() {
        return ddsyjl;
    }

    public void setDdsyjl(String ddsyjl) {
        this.ddsyjl = ddsyjl;
    }

    public String getXdcs() {
        return xdcs;
    }

    public void setXdcs(String xdcs) {
        this.xdcs = xdcs;
    }

    public String getDjsm() {
        return djsm;
    }

    public void setDjsm(String djsm) {
        this.djsm = djsm;
    }

    public String getDdjc() {
        return ddjc;
    }

    public void setDdjc(String ddjc) {
        this.ddjc = ddjc;
    }

    public String getSmms() {
        return smms;
    }

    public void setSmms(String smms) {
        this.smms = smms;
    }

    public String getMeslj() {
        return meslj;
    }

    public void setMeslj(String meslj) {
        this.meslj = meslj;
    }

    public String getTmsm() {
        return tmsm;
    }

    public void setTmsm(String tmsm) {
        this.tmsm = tmsm;
    }

    public String getTmlxxz() {
        return tmlxxz;
    }

    public void setTmlxxz(String tmlxxz) {
        this.tmlxxz = tmlxxz;
    }

    public String getLxxs() {
        return lxxs;
    }

    public void setLxxs(String lxxs) {
        this.lxxs = lxxs;
    }

    public String getKdsdtj() {
        return kdsdtj;
    }

    public void setKdsdtj(String kdsdtj) {
        this.kdsdtj = kdsdtj;
    }

    public String getAqgd0() {
        return aqgd0;
    }

    public void setAqgd0(String aqgd0) {
        this.aqgd0 = aqgd0;
    }

    public String getQygd0() {
        return qygd0;
    }

    public void setQygd0(String qygd0) {
        this.qygd0 = qygd0;
    }

    public String getQggd0() {
        return qggd0;
    }

    public void setQggd0(String qggd0) {
        this.qggd0 = qggd0;
    }

    public String getVqgd0() {
        return vqgd0;
    }

    public void setVqgd0(String vqgd0) {
        this.vqgd0 = vqgd0;
    }

    public String getAqgd1() {
        return aqgd1;
    }

    public void setAqgd1(String aqgd1) {
        this.aqgd1 = aqgd1;
    }

    public String getQygd1() {
        return qygd1;
    }

    public void setQygd1(String qygd1) {
        this.qygd1 = qygd1;
    }

    public String getQggd1() {
        return qggd1;
    }

    public void setQggd1(String qggd1) {
        this.qggd1 = qggd1;
    }

    public String getDdjcgd() {
        return ddjcgd;
    }

    public void setDdjcgd(String ddjcgd) {
        this.ddjcgd = ddjcgd;
    }

    public String getVqgd1() {
        return vqgd1;
    }

    public void setVqgd1(String vqgd1) {
        this.vqgd1 = vqgd1;
    }

    public String getDjyy() {
        return djyy;
    }

    public void setDjyy(String djyy) {
        this.djyy = djyy;
    }

    public String getGdyy() {
        return gdyy;
    }

    public void setGdyy(String gdyy) {
        this.gdyy = gdyy;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public String getZzxg() {
        return zzxg;
    }

    public void setZzxg(String zzxg) {
        this.zzxg = zzxg;
    }

    public String getHdwzx() {
        return hdwzx;
    }

    public void setHdwzx(String hdwzx) {
        this.hdwzx = hdwzx;
    }

    public String getHdwzy() {
        return hdwzy;
    }

    public void setHdwzy(String hdwzy) {
        this.hdwzy = hdwzy;
    }

    public String getPcbkd() {
        return pcbkd;
    }

    public void setPcbkd(String pcbkd) {
        this.pcbkd = pcbkd;
    }

    public String getXflw() {
        return xflw;
    }

    public void setXflw(String xflw) {
        this.xflw = xflw;
    }

    public String getXqlw() {
        return xqlw;
    }

    public void setXqlw(String xqlw) {
        this.xqlw = xqlw;
    }

    public String getYflw() {
        return yflw;
    }

    public void setYflw(String yflw) {
        this.yflw = yflw;
    }

    public String getZjsx() {
        return zjsx;
    }

    public void setZjsx(String zjsx) {
        this.zjsx = zjsx;
    }
}
