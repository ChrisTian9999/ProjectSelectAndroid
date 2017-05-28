package com.chris.pss.data.entity;

public class ProjectEntity {
    private int id;
    private int majorId;
    private int departmentId;
    private int teacherId;
    private int studentId;
    private String title;
    private String detail;
    private int ranking;
    private int isChecked;
    private int isFinish;


    public ProjectEntity() {
    }

    /**
     * 创建课题的默认的构造函数
     * @param departmentId 专业的id
     * @param teacherId 教师的id
     * @param title 标题
     * @param detail 详情
     * @param ranking 难度
     */
    public ProjectEntity(int departmentId, int majorId, int teacherId, String title, String detail, int ranking) {
        this.departmentId = departmentId;
        this.teacherId = teacherId;
        this.majorId = majorId;
        this.title = title;
        this.detail = detail;
        this.ranking = ranking;
        this.isChecked = 0;
        this.isFinish = 0;
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

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "id=" + id +
                ", majorId=" + majorId +
                ", departmentId=" + departmentId +
                ", teacherId=" + teacherId +
                ", studentId=" + studentId +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", ranking=" + ranking +
                ", isChecked=" + isChecked +
                ", isFinish=" + isFinish +
                '}';
    }
}