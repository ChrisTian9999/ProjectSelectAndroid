package com.chris.pss.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chris.pss.R;
import com.chris.pss.adapter.BaseRvAdapter;
import com.chris.pss.adapter.RvProjectAdapter;
import com.chris.pss.app.IApp;
import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.DepartEntity;
import com.chris.pss.data.entity.ProjectEntity;
import com.chris.pss.data.service.ProjectDataHttpRequest;
import com.chris.pss.utils.ToastUtils;
import com.chris.pss.widgets.recyclerview.dividers.HorizontalDividerItemDecoration;
import com.chris.pss.widgets.subscribers.GeneralSubscriber;
import com.chris.pss.widgets.subscribers.ProgressSubscriber;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeacherAdminMajorProjectListActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;

    private DepartEntity major;
    private RvProjectAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_admin_major_project_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            major = (DepartEntity) intent.getSerializableExtra("major");
        }
        initViews();
    }

    private void initViews() {
        initToolBar(mToolbar);
        setTitle(major.getName());
        //
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .colorResId(R.color.colorDividerGray)
                .sizeResId(R.dimen.divider_height)
                .showLastDivider()
                .build());
        mAdapter = getAdapter();
        mRecycler.setAdapter(mAdapter);
        //
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchMajorCheckedList();
            }
        });
        //
        refresh();
    }


    public RvProjectAdapter getAdapter(){
        return new RvProjectAdapter(this, null, new BaseRvAdapter.OnItemClickListener<ProjectEntity>() {
            @Override
            public void OnItemClick(View view, int position, ProjectEntity data) {

            }
        }, false);
    }

    public void fetchMajorCheckedList() {
        ProjectDataHttpRequest.newInstance(IApp.context)
                .getMajorCheckedProjectList(new ProgressSubscriber<>(new GeneralSubscriber<BaseResponse<List<ProjectEntity>>>() {
                    @Override
                    public void onNext(BaseResponse<List<ProjectEntity>> response) {
                        List<ProjectEntity> projectList = response.getData();
                        mAdapter.setList(projectList);
                        finishRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                        finishRefresh();
                    }
                }, this), major.getId());
    }


    private void refresh() {
        mSrlRefresh.post(new Runnable() {
            @Override
            public void run() {
                mSrlRefresh.setRefreshing(true);
                fetchMajorCheckedList();
            }
        });
    }

    private void finishRefresh() {
        mSrlRefresh.post(new Runnable() {
            @Override
            public void run() {
                mSrlRefresh.setRefreshing(false);
            }
        });
    }

    public static void JumpHere(Context c, DepartEntity entity) {
        Intent intent = new Intent(c, TeacherAdminMajorProjectListActivity.class);
        intent.putExtra("major", entity);
        c.startActivity(intent);
    }
}
