package com.chris.pss.data.entity;

import java.util.List;

/**
 * Created by noonecode on 2017/5/13.
 */

public class StuLoginResult {

    private StuEntity stud;
    private DepartEntity major;
    private Object proj;// TODO: 2017/5/13
    private List<DepartEntity> extras;

    public StuLoginResult() {
    }

    public StuLoginResult(StuEntity stud, DepartEntity major, Object proj, List<DepartEntity> extras) {
        this.stud = stud;
        this.major = major;
        this.proj = proj;
        this.extras = extras;
    }

    public StuEntity getStud() {
        return stud;
    }

    public void setStud(StuEntity stud) {
        this.stud = stud;
    }

    public DepartEntity getMajor() {
        return major;
    }

    public void setMajor(DepartEntity major) {
        this.major = major;
    }

    public Object getProj() {
        return proj;
    }

    public void setProj(Object proj) {
        this.proj = proj;
    }

    public List<DepartEntity> getExtras() {
        return extras;
    }

    public void setExtras(List<DepartEntity> extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        return "StuLoginResult{" +
                "stud=" + stud +
                ", major=" + major +
                ", proj=" + proj +
                ", extras=" + extras +
                '}';
    }
}
