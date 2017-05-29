package com.chris.pss.data.entity;

import java.io.Serializable;

/**
 * Created by zht on 2017/5/13.
 */

public class DepartEntity implements Serializable{
    private int id;
    private DepartEntity parent;
    private String name;
    private String timeBegin;
    private String timeEnd;

    public DepartEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DepartEntity getParent() {
        return parent;
    }

    public void setParent(DepartEntity parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "DepartEntity{" +
                "id=" + id +
                ", parent=" + parent +
                ", name='" + name + '\'' +
                ", timeBegin='" + timeBegin + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                '}';
    }
}
