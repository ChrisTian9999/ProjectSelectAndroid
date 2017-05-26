package com.chris.pss.utils;

import android.content.Context;

/**
 * Created by XNN on 2016/12/11.
 */

public class ViewUtils {
    public static int dp2px(Context mContent, float dp) {
        int v = (int) (mContent.getResources().getDisplayMetrics().density * dp);
        return v;
    }

}
