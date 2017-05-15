package com.chris.pss.app;

import com.chris.pss.utils.EncryptUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by noonecode on 2017/5/15.
 */

public class MyConsts {

    public static final String KEY_AES = "f75cc2f59de266bd1fb51d9e9591cc297834e6d496da07ea504bb44712f5a574";

    /**
     * 12s内在线
     */
    public static final long DURATION_ONLINE = 12000;


    public static String getEncryptString(String from) {
        try {
            return EncryptUtils.encryptAES2HexString(from.getBytes("utf-8"), KEY_AES.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
