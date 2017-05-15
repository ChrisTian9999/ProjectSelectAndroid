package com.chris.pss.data.entity;

import java.util.List;

/**
 * Created by noonecode on 2017/5/15.
 */

public class TchLoginResult {
    private TchEntity tch;
    private DepartEntity depart;
    private List<DepartEntity> extras;

    public TchLoginResult() {
    }

    public TchLoginResult(TchEntity tch, DepartEntity depart, List<DepartEntity> extras) {
        this.tch = tch;
        this.depart = depart;
        this.extras = extras;
    }

    public TchEntity getTch() {
        return tch;
    }

    public void setTch(TchEntity tch) {
        this.tch = tch;
    }

    public DepartEntity getDepart() {
        return depart;
    }

    public void setDepart(DepartEntity depart) {
        this.depart = depart;
    }

    public List<DepartEntity> getExtras() {
        return extras;
    }

    public void setExtras(List<DepartEntity> extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        return "TchLoginResult{" +
                "tch=" + tch +
                ", depart=" + depart +
                ", extras=" + extras +
                '}';
    }
}
