package com.chris.pss.app;

/**
 * Created by zht on 2017/5/30.
 */

public class StudentUtils {

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
}
