package com.chris.pss.app;

import android.app.Application;
import android.content.Context;

import com.chris.pss.data.entity.DepartEntity;
import com.chris.pss.data.entity.StuEntity;
import com.chris.pss.data.entity.TeacherEntity;

import java.util.List;

/**
 * Created by noonecode on 2017/5/15.
 */

public class IApp extends Application {

    public static Context context;
    public static boolean isDebug = true;

    /**
     * 学生信息
     */
    public static StuEntity stu = null;

    /**
     * 当前登陆教师信息
     */
    public static TeacherEntity teacher = null;
    /**
     * 教师登陆时，本学院内所有的专业信息
     */
    public static List<DepartEntity> majors = null;

    /**
     * 学校的所有专业与学院信息
     * 学院无parentId信息和开始结束信息
     * 专业有所属学院的id（parentId）和开始选题结束选题信息
     */
    public static List<DepartEntity> extras = null;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

    }
}
