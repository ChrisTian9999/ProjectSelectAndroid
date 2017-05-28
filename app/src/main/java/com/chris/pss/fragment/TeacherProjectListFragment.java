package com.chris.pss.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chris.pss.R;
import com.chris.pss.activity.TeacherCreateProjectActivity;
import com.chris.pss.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherProjectListFragment extends Fragment {


    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.fab_add)
    FloatingActionButton mFabAdd;
    Unbinder unbinder;

    public TeacherProjectListFragment() {
        // Required empty public constructor
    }

    public static TeacherProjectListFragment newInstance() {
        Bundle args = new Bundle();
        TeacherProjectListFragment fragment = new TeacherProjectListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_project_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @OnClick({R.id.fab_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab_add://创建课题
                startActivity(new Intent(getContext(), TeacherCreateProjectActivity.class));
                break;
        }
    }

    @Subscribe
    public void onEvent(ProjectCreateSuccessEvent event) {
        ToastUtils.showToast("接受到创建成功");
    }

    /**
     * 创建项目成功的事件
     */
    public static class ProjectCreateSuccessEvent {
    }
}
