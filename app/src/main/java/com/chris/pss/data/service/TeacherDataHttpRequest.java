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
import com.chris.pss.data.entity.TeacherLoginResult;

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
     */
    public void postLogin(Subscriber<BaseResponse<TeacherLoginResult>> subscriber, String tno, String pwd) {
        Observable<BaseResponse<TeacherLoginResult>> observable = mTeacherDataService.postLogin(tno, pwd);
        toSubscribe(observable, subscriber);
    }

    /**
     * 获得教师的信息，包括学院
     * @param tno 教师号
     */
    public void getTchInfo(Subscriber<BaseResponse<TeacherLoginResult>> subscriber, String tno) {
        Observable<BaseResponse<TeacherLoginResult>> observable = mTeacherDataService.getTchInfo(tno);
        toSubscribe(observable, subscriber);
    }

}
