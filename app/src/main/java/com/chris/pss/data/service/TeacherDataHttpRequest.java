/*******************************************************************************
 * Copyright (C) 2016. Tripint Information Technology Co., Ltd. All rights reserved. *
 * Authors:lirichen
 * File:StudentDataHttpRequest.java
 * This software, including documentation, is protected by copyright controlled
 * by Tripint Information Technology Co., Ltd. All rights are reserved.
 ******************************************************************************/

package com.chris.pss.data.service;

import android.content.Context;

import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.TchLoginEntity;

import rx.Observable;
import rx.Subscriber;


public class TeacherDataHttpRequest extends HttpRequest {
    private static TeacherDataHttpRequest instants;
    private TeacherDataService mTeacherDataService;

    private TeacherDataHttpRequest(Context context) {
        super(context);
        mTeacherDataService = retrofit.create(TeacherDataService.class);
    }

    public static TeacherDataHttpRequest newInstance(Context context) {
        if (instants == null) {
            instants = new TeacherDataHttpRequest(context);
        }
        return instants;
    }

    /**
     * 登录
     *
     * @param tno 教师号
     * @param pwd 密码
     */
    public void postLogin(Subscriber<BaseResponse<TchLoginEntity>> subscriber, String tno, String pwd) {
        Observable<BaseResponse<TchLoginEntity>> observable = mTeacherDataService.postLogin(tno, pwd);
        toSubscribe(observable, subscriber);
    }

}
