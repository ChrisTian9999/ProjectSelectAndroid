package com.chris.pss.app;

import com.chris.pss.data.entity.StuEntity;
import com.chris.pss.data.entity.TchEntity;

import java.util.Locale;

/**
 * Created by noonecode on 2017/5/16.
 */

public class SimpleUtils {
    private static final String DEF_WELCOME_FORMAT = "欢迎您，%s%s";
    private static final String DEF_WELCOME_STRING = "登录成功";

    public static String getWelcomeString(TchEntity entity) {
        return String.format(Locale.getDefault(), DEF_WELCOME_FORMAT, entity.getName(), entity.getZhicheng());
    }

    public static String getWelcomeString(StuEntity entity) {
        return String.format(Locale.getDefault(), DEF_WELCOME_FORMAT, entity.getName(), "同学");
    }
}
