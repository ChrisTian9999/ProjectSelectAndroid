package com.chris.pss.data.entity;

public class ProjectEntity {
    private Integer id;
    private DepartEntity major;//专业
    private TeacherEntity teacher;
    private StudentEntity student;
    private String title;
    private String detail;
    private Integer ranking;
    private Integer isChecked;
    private Integer isFinish;


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

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "id=" + id +
                ", major=" + major +
                ", teacher=" + teacher +
                ", student=" + student +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", ranking=" + ranking +
                ", isChecked=" + isChecked +
                ", isFinish=" + isFinish +
                '}';
    }
}