package com.hih.pcbcutter.ui.bean;

/**
 * Created by likeye on 2020/8/3 16:33.
 **/
class Machine {
    private String language;//语言
    private String control;//控制卡
    private String type;//机器类型
    private String norm;//机器规格

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }
}
