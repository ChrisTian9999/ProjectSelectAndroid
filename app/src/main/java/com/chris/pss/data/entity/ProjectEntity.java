package com.chris.pss.data.entity;

public class ProjectEntity {
    private Integer id;
    private DepartEntity major;//专业
    private TeacherEntity teacher;
    private String title;
    private String detail;
    private Integer ranking;
    private Integer isChecked;
    private Integer isFinish;
    //2 upload
    private Integer majorId;
    private Integer teacherId;


    public ProjectEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DepartEntity getMajor() {
        return major;
    }

    public void setMajor(DepartEntity major) {
        this.major = major;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
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

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "id=" + id +
                ", major=" + major +
                ", teacher=" + teacher +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", ranking=" + ranking +
                ", isChecked=" + isChecked +
                ", isFinish=" + isFinish +
                ", majorId=" + majorId +
                ", teacherId=" + teacherId +
                '}';
    }
}