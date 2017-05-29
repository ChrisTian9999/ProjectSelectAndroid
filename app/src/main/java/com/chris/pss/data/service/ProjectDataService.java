/*******************************************************************************
 * Copyright (C) 2016. Tripint Information Technology Co., Ltd. All rights reserved. *
 * Authors:lirichen
 * File:StudentDataService.java
 * This software, including documentation, is protected by copyright controlled
 * by Tripint Information Technology Co., Ltd. All rights are reserved.
 ******************************************************************************/

package com.chris.pss.data.service;

import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.SimpleFlagEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface ProjectDataService {


    /**
     * 创建项目
     */
    @FormUrlEncoded
    @POST("project/create")
    Observable<BaseResponse<SimpleFlagEntity>> createProject(
            @Field("majorId") Integer majorId,
            @Field("teacherId") Integer teacherId,
            @Field("title") String title,
            @Field("detail") String detail,
            @Field("ranking") Integer ranking
    );

}
