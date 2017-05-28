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

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface ProjectDataService {


    /**
     * 创建项目
     */
    @POST("project/create")
    Observable<BaseResponse<SimpleFlagEntity>> createProject(
            @Body ProjectEntity entity
    );

}
