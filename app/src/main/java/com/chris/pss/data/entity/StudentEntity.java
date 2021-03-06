package com.chris.pss.data.entity;

/**
 * Created by noonecode on 2017/5/13.
 */

public class StudentEntity {
    private int id;
    private DepartEntity major;
    private String sno;
    private String name;
    private String pwd;
    private String classname;
    private int gender;
    private String tel;
    private long heartBeat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DepartEntity getMajor() {
        return major;
    }

    public void setMajor(DepartEntity major) {
        this.major = major;
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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
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
        return "StudentEntity{" +
                "id=" + id +
                ", major=" + major +
                ", sno='" + sno + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", classname='" + classname + '\'' +
                ", gender=" + gender +
                ", tel='" + tel + '\'' +
                ", heartBeat=" + heartBeat +
                '}';
    }
}
