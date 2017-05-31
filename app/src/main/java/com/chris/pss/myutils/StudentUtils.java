package com.chris.pss.myutils;

import com.chris.pss.app.IApp;

/**
 * Created by zht on 2017/5/30.
 */

public class StudentUtils {


    public static int getId() {
        return IApp.student.getId();
    }
    /**
     * 专业id
     */
    public static int getMajorId() {
        return IApp.student.getMajor().getId();
    }

    /**
     * 学院id
     */
    public static int getDepartId(){
        return IApp.student.getMajor().getParent().getId();
    }

    /**
     * 当前是学生登陆，而且学号相等
     */
    public static boolean isMe(String sno) {
        return IApp.isStudent && IApp.student.getSno().equals(sno);
    }
}
