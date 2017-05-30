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
import com.chris.pss.adapter.RvProjectAdapter;
import com.chris.pss.app.IApp;
import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.ProjectEntity;
import com.chris.pss.data.service.ProjectDataHttpRequest;
import com.chris.pss.utils.ToastUtils;
import com.chris.pss.widgets.recyclerview.dividers.HorizontalDividerItemDecoration;
import com.chris.pss.widgets.subscribers.GeneralSubscriber;
import com.chris.pss.widgets.subscribers.ProgressSubscriber;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentProjectListFragment extends Fragment {


    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    Unbinder unbinder;
    private RvProjectAdapter mAdapter;

    public StudentProjectListFragment() {
        // Required empty public constructor
    }

    public static StudentProjectListFragment newInstance() {
        Bundle args = new Bundle();
        StudentProjectListFragment fragment = new StudentProjectListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_project_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
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
                fetchMajorCheckedList();
            }
        });
        //
        refresh();
    }

    @NonNull
    private RvProjectAdapter getAdapter() {
        return new RvProjectAdapter(getContext(), null, new BaseRvAdapter.OnItemClickListener<ProjectEntity>() {
            @Override
            public void OnItemClick(View view, int position, ProjectEntity data) {
                switch (view.getId()) {
                    case R.id.rl_project_root:
                        break;
                    case R.id.ll_project_student:
                        break;
                    case R.id.iv_project_check:
                        break;
                    default:
                        break;
                }
            }
        }, true);
    }

    public void fetchMajorCheckedList() {
        ProjectDataHttpRequest.newInstance(IApp.context)
                .getProjectListByTno(new ProgressSubscriber<>(new GeneralSubscriber<BaseResponse<List<ProjectEntity>>>() {
                    @Override
                    public void onNext(BaseResponse<List<ProjectEntity>> response) {
                        mAdapter.setList(response.getData());
                        finishRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                        finishRefresh();
                    }
                }, getContext()), IApp.teacher.getTno());
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
