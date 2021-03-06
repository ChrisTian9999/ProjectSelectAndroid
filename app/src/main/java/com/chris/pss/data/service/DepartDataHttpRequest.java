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
import com.chris.pss.data.entity.DepartEntity;
import com.chris.pss.data.entity.SimpleFlagEntity;

import java.util.List;

import rx.Observable;
import rx.Subscriber;


public class DepartDataHttpRequest extends HttpRequest {
    private static DepartDataHttpRequest instants;
    private DepartDataService mDepartDataService;

    private DepartDataHttpRequest(Context context) {
        super(context);
        mDepartDataService = retrofit.create(DepartDataService.class);
    }

    public static DepartDataHttpRequest newInstance(Context context) {
        if (instants == null) {
            instants = new DepartDataHttpRequest(context);
        }
        return instants;
    }


    /**
     * 获得所有的专业/学院信息
     */
    @Deprecated
    public void getDepartList(Subscriber<BaseResponse<List<DepartEntity>>> subscriber) {
        Observable<BaseResponse<List<DepartEntity>>> observable = mDepartDataService.getDepartList();
        toSubscribe(observable, subscriber);
    }

    /**
     * 根据学院id，获得所属所有专业的信息
     */
    public void getMajorList(Subscriber<BaseResponse<List<DepartEntity>>> subscriber, int departId) {
        Observable<BaseResponse<List<DepartEntity>>> observable = mDepartDataService.getMajorList(departId);
        toSubscribe(observable, subscriber);
    }

    /**
     * 修改专业的时间
     */
    public void postModifyMajor(Subscriber<BaseResponse<SimpleFlagEntity>> subscriber, int id, String start, String end) {
        Observable<BaseResponse<SimpleFlagEntity>> observable = mDepartDataService.postModifyMajor(id, start, end);
        toSubscribe(observable, subscriber);
    }

}
