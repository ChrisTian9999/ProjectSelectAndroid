/*******************************************************************************
 * Copyright (C) 2016. Tripint Information Technology Co., Ltd. All rights reserved. *
 * Authors:lirichen
 * File:StudentDataService.java
 * This software, including documentation, is protected by copyright controlled
 * by Tripint Information Technology Co., Ltd. All rights are reserved.
 ******************************************************************************/

package com.chris.pss.data.service;

import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.TeacherLoginResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface TeacherDataService {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("teacher/login")
    Observable<BaseResponse<TeacherLoginResult>> postLogin(
            @Field("tno") String tno,
            @Field("pwd") String pwd
    );

    /**
     * 获得教师的基本信息，包括学院
     */
    @GET("teacher/tno/{tno}")
    Observable<BaseResponse<TeacherLoginResult>> getTchInfo(@Path("tno") String tno);

}
