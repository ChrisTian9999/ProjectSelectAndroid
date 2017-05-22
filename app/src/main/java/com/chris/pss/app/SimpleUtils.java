package com.chris.pss.app;

import com.chris.pss.data.entity.DepartEntity;
import com.chris.pss.data.entity.StuEntity;
import com.chris.pss.data.entity.TchEntity;
import com.chris.pss.utils.EmptyUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by noonecode on 2017/5/16.
 */

public class SimpleUtils {

    public static final String DEF_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String DEF_WELCOME_FORMAT = "欢迎您，%s";

    public static String getWelcomeString(TchEntity entity) {
        return String.format(Locale.getDefault(), DEF_WELCOME_FORMAT, entity.getName());
    }

    public static String getWelcomeString(StuEntity entity) {
        return String.format(Locale.getDefault(), DEF_WELCOME_FORMAT, entity.getName());
    }


    /**
     * 由学院的id，获得所有的子专业
     */
    public static List<DepartEntity> getChiledDepartList(int parentId) {
        List<DepartEntity> list = new ArrayList<>();
        if (IApp.extras != null) {
            for (DepartEntity entity : IApp.extras) {
                if (entity.getParentId() == parentId) {
                    list.add(entity);
                }
            }
        }
        return list;
    }


    /**
     * 时间字符串转换成为long
     */
    public static long getLongDateFromString(String date) {
        try {
            if (!EmptyUtils.isEmpty(date)) {
                return new SimpleDateFormat(DEF_TIME_FORMAT, Locale.getDefault()).parse(date).getTime();
            }
        } catch (ParseException e) {
        }
        return 0;
    }
}
