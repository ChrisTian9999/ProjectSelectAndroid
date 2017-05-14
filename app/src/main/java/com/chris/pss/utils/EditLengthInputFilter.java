package com.chris.pss.utils;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by jonathan on 2017/4/25.
 */
public class EditLengthInputFilter implements InputFilter {


    //最大英文/数字长度 一个汉字算两个字母
    int maxLength;
    //中日韩文字
    String regEx = "[\\u3000-\\u9FFF]";

    public EditLengthInputFilter(int maxLength) {
        super();
        this.maxLength = maxLength;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        int destCount = dest.toString().length()
                + getChineseCount(dest.toString());
        int sourceCount = source.toString().length()
                + getChineseCount(source.toString());
        String name = "";
        int count = 0;
        int i = 0;
        if (destCount + sourceCount > maxLength) {
            if (destCount < maxLength) {
                while (!(destCount + count >= maxLength)) {
                    ++i;
                    name = source.subSequence(0, i).toString();
                    count = name.toString().length()
                            + getChineseCount(name.toString());
                    if (destCount + count > maxLength) {
                        --i;
                    }
                }
                return i == 0 ? "" : source.subSequence(0, i).toString();
            }
            return "";
        } else {
            return source;
        }
    }

    private int getChineseCount(String str) {
        int count = 0;
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            count++;
        }
        return count;
    }
}