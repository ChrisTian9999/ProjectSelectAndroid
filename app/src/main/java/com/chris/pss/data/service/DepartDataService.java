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

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface DepartDataService {


    /**
     * 获得所有的专业/学院信息
     */
    @GET("department/list")
    Observable<BaseResponse<List<DepartEntity>>> getDepartList();

}
