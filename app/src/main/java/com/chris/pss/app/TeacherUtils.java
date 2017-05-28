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
        return IApp.tch == null ? -1 : IApp.tch.getDepartmentId();
    }

    /**
     * 获得所在学院的所有专业列表
     */
    public static List<DepartEntity> getMajorList() {
        ArrayList<DepartEntity> list = new ArrayList<>();
        int departId = getMyDepartId();
        if (departId > 0 && IApp.extras != null) {
            for (DepartEntity entity : IApp.extras) {
                if (entity.getParentId() == departId) {
                    list.add(entity);
                }
            }
        }
        return list;
    }

}
