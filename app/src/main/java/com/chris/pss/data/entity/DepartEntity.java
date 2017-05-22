package com.chris.pss.data.entity;

/**
 * Created by noonecode on 2017/5/13.
 */

public class DepartEntity {
    private int id;
    private int parentId;
    private String name;
    private String timeBegin;
    private String timeEnd;
    private long begin;
    private long end;

    public DepartEntity() {
    }

    public DepartEntity(int id, int parentId, String name, String timeBegin, String timeEnd) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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

    public long getBegin() {
        return begin;
    }

    public void setBegin(long begin) {
        this.begin = begin;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "DepartEntity{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", timeBegin='" + timeBegin + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                '}';
    }
}
