package com.chris.pss.data.interceptor;

import java.util.Locale;

/**
 * Created by noonecode on 2017/5/15.
 */

public class ApiException extends Exception {

    public ApiException(String message, int code) {
        super(String.format(Locale.getDefault(), "error=%d msg=%s", code, message));
    }
}
