package com.chris.pss.data.entity;

/**
 * Created by noonecode on 2017/5/15.
 */

public class SimpleCountEntity {
    private int count;

    public SimpleCountEntity(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SimpleCountEntity{" +
                "count=" + count +
                '}';
    }
}
