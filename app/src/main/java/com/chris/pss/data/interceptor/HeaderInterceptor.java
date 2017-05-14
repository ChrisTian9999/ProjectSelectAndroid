/*
 * Copyright (C) 2016. Tripint Information Technology Co., Ltd. All rights reserved. *
 * Authors:lirichen
 * File:HeaderInterceptor.java
 * This software, including documentation, is protected by copyright controlled
 * by Tripint Information Technology Co., Ltd. All rights are reserved.
 */

package com.chris.pss.data.interceptor;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

//import cn.joyea.common.cache.ACache;
//import cn.joyea.common.enumkey.EnumKey;
//import cn.joyea.common.utils.StringUtils;


public class HeaderInterceptor implements Interceptor {

    private Context context;

    public HeaderInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
//        String token = ACache.get(context).getAsString(EnumKey.User.USER_TOKEN);
//        if (TextUtils.isEmpty(token)) {
//            token = "";
//        }
        Request request = original.newBuilder()
//                .header("Authorization", "Bearer " + token)
                .header("Client", "App/V1")
                .header("Terminal", "1")
                .method(original.method(), original.body())
                .build();

        return chain.proceed(request);

    }
}
