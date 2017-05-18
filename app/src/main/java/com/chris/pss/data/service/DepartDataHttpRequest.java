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
    public void getDepartList(Subscriber<BaseResponse<List<DepartEntity>>> subscriber) {
        Observable<BaseResponse<List<DepartEntity>>> observable = mDepartDataService.getDepartList();
        toSubscribe(observable, subscriber);
    }

}
