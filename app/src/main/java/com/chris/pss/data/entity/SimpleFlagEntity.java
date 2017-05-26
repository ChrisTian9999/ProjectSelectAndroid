package com.chris.pss.data.entity;

/**
 * Created by zht on 2017/5/26.
 */

public class SimpleFlagEntity {
    private boolean flag;

    public SimpleFlagEntity(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "SimpleFlagEntity{" +
                "flag=" + flag +
                '}';
    }
}
