package com.chris.pss.data.entity;

import java.util.List;

/**
 * Created by zht on 2017/5/15.
 */

public class TeacherLoginResult {
    private TeacherEntity teacher;
    private List<DepartEntity> majors;

    public TeacherLoginResult() {
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public List<DepartEntity> getMajors() {
        return majors;
    }

    public void setMajors(List<DepartEntity> majors) {
        this.majors = majors;
    }

    @Override
    public String toString() {
        return "TeacherLoginResult{" +
                "teacher=" + teacher +
                ", majors=" + majors +
                '}';
    }
}
