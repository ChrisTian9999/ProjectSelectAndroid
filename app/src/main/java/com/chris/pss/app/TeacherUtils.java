package com.chris.pss.app;

import com.chris.pss.data.entity.DepartEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zht on 2017/5/28.
 */

public class TeacherUtils {

    /**
     * 获得教师的所在学院id
     */
    public static int getMyDepartId() {
        return IApp.teacher.getDepart().getId();
    }

    /**
     * 获得所在学院的所有专业列表
     */
    public static List<DepartEntity> getMajorList() {
        return IApp.majors == null ? new ArrayList<DepartEntity>() : IApp.majors;
    }

    /**
     * 当前是否是教务员
     */
    public static boolean checkIsAdmin() {
        return IApp.teacher.getIsAdmin() == 1;
    }

}
