/*******************************************************************************
 * Copyright (C) 2016. Tripint Information Technology Co., Ltd. All rights reserved. *
 * Authors:lirichen
 * File:StudentDataService.java
 * This software, including documentation, is protected by copyright controlled
 * by Tripint Information Technology Co., Ltd. All rights are reserved.
 ******************************************************************************/

package com.chris.pss.data.service;

import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.ProjectEntity;
import com.chris.pss.data.entity.SimpleCountEntity;
import com.chris.pss.data.entity.StudentEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface StudentDataService {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("student/login")
    Observable<BaseResponse<StudentEntity>> postLogin(
            @Field("sno") String sno,
            @Field("pwd") String pwd
    );

    /**
     * 通知服务器在线，并返回当前在线学生数
     */
    @FormUrlEncoded
    @POST("student/online")
    Observable<BaseResponse<SimpleCountEntity>> postOnline(
            @Field("sno") String sno,
            @Field("duration") Long duration
    );

    /**
     * 返回当前在线的学生数
     */
    @GET("student/online/count")
    Observable<BaseResponse<SimpleCountEntity>> getOnlineCount(
            @Query("duration") Long duration
    );


    /**
     * 返回学生选择的课题
     */
    @GET("student/{sno}/project")
    Observable<BaseResponse<ProjectEntity>> getProjectBySno(
            @Path("sno") String sno
    );

}
