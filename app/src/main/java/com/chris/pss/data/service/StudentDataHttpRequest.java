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
import com.chris.pss.data.entity.ProjectEntity;
import com.chris.pss.data.entity.SimpleCountEntity;
import com.chris.pss.data.entity.SimpleFlagEntity;
import com.chris.pss.data.entity.StudentEntity;

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
    public void postLogin(Subscriber<BaseResponse<StudentEntity>> subscriber, String sno, String pwd) {
        Observable<BaseResponse<StudentEntity>> observable = mStudentDataService.postLogin(sno, pwd);
        toSubscribe(observable, subscriber);
    }

    /**
     * 获得学生的信息
     * @param sno 学号
     */
    public void getStuInfo(Subscriber<BaseResponse<StudentEntity>> subscriber, String sno) {
        Observable<BaseResponse<StudentEntity>> observable = mStudentDataService.getStuInfo(sno);
        toSubscribe(observable, subscriber);
    }

    /**
     * 通知在线，并返回当前在线学生数
     */
    public void postOnline(Subscriber<BaseResponse<SimpleCountEntity>> subscriber, String sno) {
        Observable<BaseResponse<SimpleCountEntity>> observable = mStudentDataService.postOnline(sno, 12000L);
        toSubscribe(observable, subscriber);
    }

    /**
     * 获得当前在线学生数目
     */
    public void getOnlineCount(Subscriber<BaseResponse<SimpleCountEntity>> subscriber) {
        Observable<BaseResponse<SimpleCountEntity>> observable = mStudentDataService.getOnlineCount(12000L);
        toSubscribe(observable, subscriber);
    }


    /**
     * 返回学生选择的课题
     */
    public void getProjectBySno(Subscriber<BaseResponse<ProjectEntity>> subscriber, String sno) {
        Observable<BaseResponse<ProjectEntity>> observable = mStudentDataService.getProjectBySno(sno);
        toSubscribe(observable, subscriber);
    }

    /**
     * 重置密码
     */
    public void resetPwd(Subscriber<BaseResponse<SimpleFlagEntity>> subscriber, String tno, String pwd, String newPwd) {
        Observable<BaseResponse<SimpleFlagEntity>> observable = mStudentDataService.resetPwd(tno, pwd, newPwd);
        toSubscribe(observable, subscriber);
    }

}
