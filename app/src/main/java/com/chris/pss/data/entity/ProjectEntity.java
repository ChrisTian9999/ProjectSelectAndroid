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
    public ProjectEntity(Integer departmentId, Integer majorId, Integer teacherId, String title, String detail, Integer ranking) {
//        this.departmentId = departmentId;
//        this.teacherId = teacherId;
//        this.majorId = majorId;
        this.title = title;
        this.detail = detail;
        this.ranking = ranking;
        this.isChecked = 0;
        this.isFinish = 0;
    }

}