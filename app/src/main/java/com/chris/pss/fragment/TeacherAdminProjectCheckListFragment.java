package com.chris.pss.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chris.pss.R;
import com.chris.pss.adapter.BaseRvAdapter;
import com.chris.pss.adapter.RvTeacherAdminProjectCheckListAdapter;
import com.chris.pss.app.IApp;
import com.chris.pss.app.TeacherUtils;
import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.ProjectEntity;
import com.chris.pss.data.entity.SimpleFlagEntity;
import com.chris.pss.data.service.ProjectDataHttpRequest;
import com.chris.pss.utils.ToastUtils;
import com.chris.pss.widgets.recyclerview.dividers.HorizontalDividerItemDecoration;
import com.chris.pss.widgets.subscribers.GeneralSubscriber;
import com.chris.pss.widgets.subscribers.ProgressSubscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherAdminProjectCheckListFragment extends Fragment {


    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    Unbinder unbinder;

    private List<ProjectEntity> mProjectList = new ArrayList<>();
    private RvTeacherAdminProjectCheckListAdapter mAdapter;

    public TeacherAdminProjectCheckListFragment() {
        // Required empty public constructor
    }

    public static TeacherAdminProjectCheckListFragment newInstance() {
        Bundle args = new Bundle();
        TeacherAdminProjectCheckListFragment fragment = new TeacherAdminProjectCheckListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_admin_project_check_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
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
                getDepartProjectListByCheckState();
            }
        });
        //
        refresh();
    }

    @NonNull
    private RvTeacherAdminProjectCheckListAdapter getAdapter() {
        return new RvTeacherAdminProjectCheckListAdapter(getContext(), mProjectList, new BaseRvAdapter.OnItemClickListener<ProjectEntity>() {
            @Override
            public void OnItemClick(View view, int position, ProjectEntity data) {
                switch (view.getId()) {
                    case R.id.rl_project_root:
                        break;
                    case R.id.ll_project_teacher:
                        break;
                    case R.id.iv_project_check://通过审核
                        postCheckProject(position, data);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void postCheckProject(final int position, final ProjectEntity data) {
        ProjectDataHttpRequest.newInstance(IApp.context)
                .postResetCheckState(new ProgressSubscriber<>(new GeneralSubscriber<BaseResponse<SimpleFlagEntity>>() {
                    @Override
                    public void onNext(BaseResponse<SimpleFlagEntity> response) {
                        mProjectList.remove(data);
                        mAdapter.notifyItemRemoved(position);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                    }
                }, getContext()), data.getId(), true);
    }


    public void getDepartProjectListByCheckState() {
        ProjectDataHttpRequest.newInstance(IApp.context)
                .getDepartProjectListByCheckState(new ProgressSubscriber<>(new GeneralSubscriber<BaseResponse<List<ProjectEntity>>>() {
                    @Override
                    public void onNext(BaseResponse<List<ProjectEntity>> response) {
                        mProjectList.clear();
                        if (response.getData() != null) {
                            mProjectList.addAll(response.getData());
                        }
                        mAdapter.notifyDataSetChanged();
                        //
                        finishRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                        finishRefresh();
                    }
                }, getContext()), TeacherUtils.getMyDepartId(), false);
    }


    private void refresh() {
        mSrlRefresh.post(new Runnable() {
            @Override
            public void run() {
                mSrlRefresh.setRefreshing(true);
                getDepartProjectListByCheckState();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
