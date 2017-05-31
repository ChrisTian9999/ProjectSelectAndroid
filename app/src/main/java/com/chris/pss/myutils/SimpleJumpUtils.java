package com.chris.pss.myutils;

import android.content.Context;

import com.chris.pss.activity.StudentInfoActivity;
import com.chris.pss.activity.TeacherInfoActivity;

/**
 * Created by zht on 2017/6/1.
 */

public class SimpleJumpUtils {
    public static void toTeacher(Context c, String tno) {
        TeacherInfoActivity.jumpHere(c, tno);
    }

    public static void toStudent(Context c, String sno) {
        StudentInfoActivity.jumpHere(c, sno);
    }
}
