package com.hih.pcbcutter.ui.bean;

/**
 * Created by likeye on 2020/8/20 15:44.
 **/
class DemoBean {
    private String name;
    private int age;
    private boolean boy;

    public DemoBean(String name, int age, boolean boy) {
        this.name = name;
        this.age = age;
        this.boy = boy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isBoy() {
        return boy;
    }

    public void setBoy(boolean boy) {
        this.boy = boy;
    }
}
