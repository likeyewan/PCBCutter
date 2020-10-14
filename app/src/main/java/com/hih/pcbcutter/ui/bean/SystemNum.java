package com.hih.pcbcutter.ui.bean;

/**
 * Created by likeye on 2020/8/17 18:00.
 **/
public class SystemNum {
    private String pyxa;//偏移Xa
    private String pyya;//偏移Ya
    private String pyxb;//偏移Xb
    private String pyyb;//偏移Yb
    private String azx;//A中X
    private String azy;//A中Y
    private String ddjd;//对刀角度
    private String fwx;//范围X
    private String fwy;//范围Y
    private String bdx;//标定X
    private String bdy;//标定Y
    private String mbys;//模板延时
    private String jgys;//加工延时
    private String jzfw;//校正范围
    private String jjs;//加减速
    private String ylsd;//预览速度
    private String xztj;//闲置停机
    private String ptj;//平台进
    private String ptc;//平台出
    private String xlh;//系列号
    private String aqm;//安全门
    private String sjjz;//视觉校正
    private String ljxz;//路径修正
    private String sdws;//手动微速
    private String sdds;//手动低速
    private String sdzs;//手动中速
    private String sdgs;//手动高速
    private String dlx;//多类型
    private String bdr;//标定R
    private String xtmm;//系统密码
    private String ydzds;//移动最大速
    private String qgzds;//切割最大速
    private String ydbcx;//圆刀补偿X
    private String ydbcy;//圆刀补偿Y

    public void setNum(String[]a){
        this.pyxa=a[0];
        this.pyya = a[1];
        this.pyxb = a[2];
        this.pyyb = a[3];
        this.azx = a[4];
        this.azy = a[5];
        this.ddjd = a[6];
        this.fwx = a[7];
        this.fwy = a[8];
        this.bdx = a[9];
        this.bdy = a[10];
        this.mbys = a[11];
        this.jgys = a[12];
        this.jzfw = a[13];
        this.jjs = a[14];
        this.ylsd = a[15];
        this.xztj = a[16];
        this.ptj = a[17];
        this.ptc = a[18];
        this.xlh = a[19];
        this.aqm = a[20];
        this.sjjz = a[21];
        this.ljxz = a[22];
        this.sdws = a[23];
        this.sdds = a[24];
        this.sdzs = a[25];
        this.sdgs = a[26];
        this.dlx = a[27];
        this.bdr = a[28];
        this.xtmm = a[29];
        this.ydzds = a[30];
        this.qgzds = a[31];
        this.ydbcx = a[32];
        this.ydbcy = a[33];
    }
    public String getPyxa() {
        return pyxa;
    }

    public void setPyxa(String pyxa) {
        this.pyxa = pyxa;
    }

    public String getPyya() {
        return pyya;
    }

    public void setPyya(String pyya) {
        this.pyya = pyya;
    }

    public String getPyxb() {
        return pyxb;
    }

    public void setPyxb(String pyxb) {
        this.pyxb = pyxb;
    }

    public String getPyyb() {
        return pyyb;
    }

    public void setPyyb(String pyyb) {
        this.pyyb = pyyb;
    }

    public String getAzx() {
        return azx;
    }

    public void setAzx(String azx) {
        this.azx = azx;
    }

    public String getAzy() {
        return azy;
    }

    public void setAzy(String azy) {
        this.azy = azy;
    }

    public String getDdjd() {
        return ddjd;
    }

    public void setDdjd(String ddjd) {
        this.ddjd = ddjd;
    }

    public String getFwx() {
        return fwx;
    }

    public void setFwx(String fwx) {
        this.fwx = fwx;
    }

    public String getFwy() {
        return fwy;
    }

    public void setFwy(String fwy) {
        this.fwy = fwy;
    }

    public String getBdx() {
        return bdx;
    }

    public void setBdx(String bdx) {
        this.bdx = bdx;
    }

    public String getBdy() {
        return bdy;
    }

    public void setBdy(String bdy) {
        this.bdy = bdy;
    }

    public String getMbys() {
        return mbys;
    }

    public void setMbys(String mbys) {
        this.mbys = mbys;
    }

    public String getJgys() {
        return jgys;
    }

    public void setJgys(String jgys) {
        this.jgys = jgys;
    }

    public String getJzfw() {
        return jzfw;
    }

    public void setJzfw(String jzfw) {
        this.jzfw = jzfw;
    }

    public String getJjs() {
        return jjs;
    }

    public void setJjs(String jjs) {
        this.jjs = jjs;
    }

    public String getYlsd() {
        return ylsd;
    }

    public void setYlsd(String ylsd) {
        this.ylsd = ylsd;
    }

    public String getXztj() {
        return xztj;
    }

    public void setXztj(String xztj) {
        this.xztj = xztj;
    }

    public String getPtj() {
        return ptj;
    }

    public void setPtj(String ptj) {
        this.ptj = ptj;
    }

    public String getPtc() {
        return ptc;
    }

    public void setPtc(String ptc) {
        this.ptc = ptc;
    }

    public String getXlh() {
        return xlh;
    }

    public void setXlh(String xlh) {
        this.xlh = xlh;
    }

    public String getAqm() {
        return aqm;
    }

    public void setAqm(String aqm) {
        this.aqm = aqm;
    }

    public String getSjjz() {
        return sjjz;
    }

    public void setSjjz(String sjjz) {
        this.sjjz = sjjz;
    }

    public String getLjxz() {
        return ljxz;
    }

    public void setLjxz(String ljxz) {
        this.ljxz = ljxz;
    }

    public String getSdws() {
        return sdws;
    }

    public void setSdws(String sdws) {
        this.sdws = sdws;

    }

    public String getSdds() {
        return sdds;
    }

    public void setSdds(String sdds) {
        this.sdds = sdds;
    }

    public String getSdzs() {
        return sdzs;
    }

    public void setSdzs(String sdzs) {
        this.sdzs = sdzs;
    }

    public String getSdgs() {
        return sdgs;
    }

    public void setSdgs(String sdgs) {
        this.sdgs = sdgs;
    }

    public String getDlx() {
        return dlx;
    }

    public void setDlx(String dlx) {
        this.dlx = dlx;
    }

    public String getBdr() {
        return bdr;
    }

    public void setBdr(String bdr) {
        this.bdr = bdr;
    }

    public String getXtmm() {
        return xtmm;
    }

    public void setXtmm(String xtmm) {
        this.xtmm = xtmm;
    }

    public String getYdzds() {
        return ydzds;
    }

    public void setYdzds(String ydzds) {
        this.ydzds = ydzds;
    }

    public String getQgzds() {
        return qgzds;
    }

    public void setQgzds(String qgzds) {
        this.qgzds = qgzds;
    }

    public String getYdbcx() {
        return ydbcx;
    }

    public void setYdbcx(String ydbcx) {
        this.ydbcx = ydbcx;
    }

    public String getYdbcy() {
        return ydbcy;
    }

    public void setYdbcy(String ydbcy) {
        this.ydbcy = ydbcy;
    }
}
