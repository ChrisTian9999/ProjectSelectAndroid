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
import com.chris.pss.data.entity.StuLoginResult;

import rx.Observable;
import rx.Subscriber;


public class StudentDataHttpRequest extends HttpRequest {
    private static StudentDataHttpRequest instants;
    private StudentDataService mStudentDataService;

    private StudentDataHttpRequest(Context context) {
        super(context);
        mStudentDataService = retrofit.create(StudentDataService.class);
    }

    public static StudentDataHttpRequest newInstance(Context context) {
        if (instants == null) {
            instants = new StudentDataHttpRequest(context);
        }
        return instants;
    }

    /**
     * 登录
     *
     * @param sno 学号
     * @param pwd 密码
     */
    public void postLogin(Subscriber<BaseResponse<StuLoginResult>> subscriber, String sno, String pwd) {
        Observable<BaseResponse<StuLoginResult>> observable = mStudentDataService.postLogin(sno, pwd);
        toSubscribe(observable, subscriber);
    }

}
