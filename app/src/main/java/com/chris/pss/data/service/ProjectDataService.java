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
import com.chris.pss.data.entity.SimpleFlagEntity;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
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

    /**
     * 获得教师的所有的项目
     */
    @GET("project/teacher/{tno}/list")
    Observable<BaseResponse<List<ProjectEntity>>> getProjectListByTno(
            @Path("tno") String tno
    );

    /**
     * 某个学院departId，审核状态为isChecked课题列表
     */
    @GET("project/depart/{departId}/list")
    Observable<BaseResponse<List<ProjectEntity>>> getDepartProjectListByCheckState(
            @Path("departId") int departId,
            @Query("isChecked") boolean isChecked
    );

    /**
     * 某个专业majorId，的通过审核的课题的列表
     */
    @GET("project/major/{majorId}/list/checked")
    Observable<BaseResponse<List<ProjectEntity>>> getMajorCheckedProjectList(
            @Path("majorId") int majorId
    );

    /**
     * 审核项目的状态
     */
    @FormUrlEncoded
    @POST("project/{projectId}/reset_state")
    Observable<BaseResponse<SimpleFlagEntity>> postResetCheckState(
            @Path("projectId") int projectId,
            @Field("isChecked") Boolean isChecked
    );
}
