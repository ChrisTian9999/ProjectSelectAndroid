/*******************************************************************************
 * Copyright (C) 2016. Tripint Information Technology Co., Ltd. All rights reserved. *
 * Authors:lirichen
 * File:StudentDataService.java
 * This software, including documentation, is protected by copyright controlled
 * by Tripint Information Technology Co., Ltd. All rights are reserved.
 ******************************************************************************/

package com.chris.pss.data.service;

import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.DepartEntity;
import com.chris.pss.data.entity.SimpleFlagEntity;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface DepartDataService {


    /**
     * 获得所有的专业/学院信息
     */
    @Deprecated
    @GET("depart/list")
    Observable<BaseResponse<List<DepartEntity>>> getDepartList();

    /**
     * 根据学院id，获得所属所有专业的信息
     */
    @GET("depart/{departId}/list")
    Observable<BaseResponse<List<DepartEntity>>> getMajorList(
            @Path("departId") int departId
    );

    /**
     * @param start yyyy-MM-dd HH:mm:ss
     * @param end yyyy-MM-dd HH:mm:ss
     * @return
     */
    @FormUrlEncoded
    @POST("depart/major/{id}/modify")
    Observable<BaseResponse<SimpleFlagEntity>> postModifyMajor(
            @Path("id") int id,
            @Field("start") String start,
            @Field("end") String end
    );
}
