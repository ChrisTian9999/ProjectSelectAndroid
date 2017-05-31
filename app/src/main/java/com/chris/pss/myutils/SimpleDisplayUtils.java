package com.chris.pss.myutils;

import com.chris.pss.utils.EmptyUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zht on 2017/5/26.
 */

public class SimpleDisplayUtils {

    private static final String DEF_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final int MILLI_SECOND = 1;
    private static final int SECOND = 1000 * MILLI_SECOND;// 1000ms
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;


    /**
     * 获得专业的显示的时间
     */
    public static String getDisplayMajorTime(String time) {
        if (time != null && time.length() == 19) {
            try {
                Date date = new SimpleDateFormat(DEF_TIME_FORMAT, Locale.getDefault()).parse(time);
                return getSimpleString(date);
            } catch (ParseException e) {
            }
        }
        return "未设置";
    }


    /**
     * 短时间
     */
    public static String getSimpleString(Date date) {
        if (date == null) {
            return null;
        }
        //date的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //当前的时间
        Calendar calendarCur = Calendar.getInstance();
        //非同一年
        if (calendar.get(Calendar.YEAR) != calendarCur.get(Calendar.YEAR)) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(date);
        }
        //非同一天
        if (calendar.get(Calendar.DAY_OF_YEAR) != calendarCur.get(Calendar.DAY_OF_YEAR)) {
            return new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault()).format(date);
        }
        return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date);
    }

    /**
     * 获得专业的状态，1，2，3分别对应着未开始，进行中，已结束
     * @return
     */
    public static int getMajorStateByTime(String startTime, String endTime) {
        long millis = System.currentTimeMillis();
        long start = getDateLongFromString(startTime);
        long end = getDateLongFromString(endTime);
        if (start <= 0 || end <= 0 || start > end
                || millis < start) {
            return 1;
        } else if (millis >= start && millis < end) {
            return 2;
        } else if (millis >= end) {
            return 3;
        }
        return 0;
    }


    /**
     * 时间字符串转换成为long
     */
    private static long getDateLongFromString(String date) {
        try {
            if (!EmptyUtils.isEmpty(date)) {
                return new SimpleDateFormat(DEF_TIME_FORMAT, Locale.getDefault()).parse(date).getTime();
            }
        } catch (ParseException e) {
        }
        return 0;
    }
}
