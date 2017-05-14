package com.chris.pss.utils;

import java.math.BigDecimal;

/**
 * Created by zhuyingming on 2017/1/11 0011.
 */

public class NumberUtils {

    ////表明四舍五入，保留两位小数
    public static double getRound(double math) {
        BigDecimal decimal = new BigDecimal(math);
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
