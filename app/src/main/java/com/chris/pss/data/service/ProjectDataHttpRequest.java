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
import com.chris.pss.data.entity.ProjectEntity;
import com.chris.pss.data.entity.SimpleFlagEntity;

import java.util.List;

import rx.Observable;
import rx.Subscriber;


public class ProjectDataHttpRequest extends HttpRequest {
    private static ProjectDataHttpRequest instants;
    private ProjectDataService mProjectDataService;

    private ProjectDataHttpRequest(Context context) {
        super(context);
        mProjectDataService = retrofit.create(ProjectDataService.class);
    }

    public static ProjectDataHttpRequest newInstance(Context context) {
        if (instants == null) {
            instants = new ProjectDataHttpRequest(context);
        }
        return instants;
    }

    /**
     * 创建课题
     */
    public void createProject(Subscriber<BaseResponse<SimpleFlagEntity>> subscriber, int majorId, int teacherId, String title, String detail, int ranking) {
        Observable<BaseResponse<SimpleFlagEntity>> observable =
                mProjectDataService.createProject(majorId, teacherId, title, detail, ranking);
        toSubscribe(observable, subscriber);
    }

    /**
     * 获得教师的所有课题
     * @param tno 教师号
     */
    public void getProjectListByTno(Subscriber<BaseResponse<List<ProjectEntity>>> subscriber, String tno) {
        Observable<BaseResponse<List<ProjectEntity>>> observable = mProjectDataService.getProjectListByTno(tno);
        toSubscribe(observable, subscriber);
    }

    /**
     * 某个学院departId，审核状态为isChecked课题列表
     */
    public void getDepartProjectListByCheckState(Subscriber<BaseResponse<List<ProjectEntity>>> subscriber, int departId, boolean isChecked) {
        Observable<BaseResponse<List<ProjectEntity>>> observable = mProjectDataService.getDepartProjectListByCheckState(departId, isChecked);
        toSubscribe(observable, subscriber);
    }


    /**
     * 审核项目的状态
     */
    public void postResetCheckState(Subscriber<BaseResponse<SimpleFlagEntity>> subscriber, int projectId, boolean isChecked) {
        Observable<BaseResponse<SimpleFlagEntity>> observable = mProjectDataService.postResetCheckState(projectId, isChecked);
        toSubscribe(observable, subscriber);
    }
}
