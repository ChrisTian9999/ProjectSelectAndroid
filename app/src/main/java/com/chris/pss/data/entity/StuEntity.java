package com.chris.pss.data.entity;

/**
 * Created by noonecode on 2017/5/13.
 */

public class StuEntity {
    private int id;
    private int departmentId;
    private String sno;
    private String name;
    private String pwd;
    private int gender;
    private String tel;
    private long heartBeat;

    public StuEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public long getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(long heartBeat) {
        this.heartBeat = heartBeat;
    }

    @Override
    public String toString() {
        return "StuEntity{" +
                "id=" + id +
                ", departmentId=" + departmentId +
                ", sno='" + sno + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", gender=" + gender +
                ", tel='" + tel + '\'' +
                ", heartBeat=" + heartBeat +
                '}';
    }
}
