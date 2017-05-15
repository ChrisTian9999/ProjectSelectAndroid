package com.chris.pss.data.entity;

/**
 * Created by noonecode on 2017/5/15.
 */

public class TchEntity {
    /**
     * id : 1
     * departmentId : 1
     * tno : t001
     * name : 赵老师
     * pwd : null
     * gender : 1
     * zhicheng : 老师
     * tel : 18100000001
     * email : zhao@163.com
     * isAdmin : 0
     */

    private int id;
    private int departmentId;
    private String tno;
    private String name;
    private String pwd;
    private int gender;
    private String zhicheng;
    private String tel;
    private String email;
    private int isAdmin;

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

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getPwd() {
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

    public String getZhicheng() {
        return zhicheng;
    }

    public void setZhicheng(String zhicheng) {
        this.zhicheng = zhicheng;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "TchEntity{" +
                "id=" + id +
                ", departmentId=" + departmentId +
                ", tno='" + tno + '\'' +
                ", name='" + name + '\'' +
                ", pwd=" + pwd +
                ", gender=" + gender +
                ", zhicheng='" + zhicheng + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
