/*******************************************************************************
 * Copyright (C) 2016. Tripint Information Technology Co., Ltd. All rights reserved. *
 * Authors:lirichen
 * File:StudentDataService.java
 * This software, including documentation, is protected by copyright controlled
 * by Tripint Information Technology Co., Ltd. All rights are reserved.
 ******************************************************************************/

package com.chris.pss.data.service;

import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.StuLoginResult;

import retrofit2.http.Field;
import retrofit2.http.POST;
import rx.Observable;

public interface StudentDataService {

    /**
     * 登录
     */
    @POST("student/login")
    Observable<BaseResponse<StuLoginResult>> postLogin(@Field("sno") String sno, @Field("pwd") String pwd);


}
