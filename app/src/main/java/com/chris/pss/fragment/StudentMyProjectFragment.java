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
import com.chris.pss.data.entity.SimpleFlagEntity;
import com.chris.pss.data.entity.StudentEntity;
import com.chris.pss.data.entity.TeacherEntity;
import com.chris.pss.data.service.StudentDataHttpRequest;
import com.chris.pss.myutils.SimpleJumpUtils;
import com.chris.pss.myutils.StudentUtils;
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
public class StudentMyProjectFragment extends Fragment {


    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    Unbinder unbinder;

    private List<ProjectEntity> mProjectList = new ArrayList<>();
    private RvProjectAdapter mAdapter;

    public StudentMyProjectFragment() {
        // Required empty public constructor
    }

    public static StudentMyProjectFragment newInstance() {
        Bundle args = new Bundle();
        StudentMyProjectFragment fragment = new StudentMyProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_my_project, container, false);
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
                fetchMyProject();
            }
        });
        //
        refresh();
    }

    @NonNull
    private RvProjectAdapter getAdapter() {
        return new RvProjectAdapter(getContext(), mProjectList, new BaseRvAdapter.OnItemClickListener<ProjectEntity>() {
            @Override
            public void OnItemClick(View view, int position, ProjectEntity data) {
                switch (view.getId()) {
                    case R.id.rl_project_root:
                        break;
                    case R.id.ll_project_teacher:
                        TeacherEntity teacher = data.getTeacher();
                        if (teacher != null) {
                            SimpleJumpUtils.toTeacher(getContext(), teacher.getTno());
                        }
                        break;
                    case R.id.ll_project_student:
                        StudentEntity student = data.getStudent();
                        if (student != null) {
                            SimpleJumpUtils.toStudent(getContext(), student.getSno());
                        }
                        break;
                    case R.id.iv_project_check://remove
                        removeSelection(position, data);
                        break;
                    default:
                        break;
                }
            }
        }, true, R.drawable.ic_project_remove);
    }

    public void removeSelection(final int position, ProjectEntity entity) {
        StudentDataHttpRequest.newInstance(IApp.context)
                .removeMyProject(new ProgressSubscriber<>(new GeneralSubscriber<BaseResponse<SimpleFlagEntity>>() {
                    @Override
                    public void onNext(BaseResponse<SimpleFlagEntity> response) {
                        ToastUtils.showToast("取消选题成功");
                        mProjectList.remove(position);
                        mAdapter.notifyItemRemoved(position);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                    }
                }, getContext()), StudentUtils.getSno());
    }

    public void fetchMyProject() {
        StudentDataHttpRequest.newInstance(IApp.context)
                .getProjectBySno(new ProgressSubscriber<>(new GeneralSubscriber<BaseResponse<ProjectEntity>>() {
                    @Override
                    public void onNext(BaseResponse<ProjectEntity> response) {
                        mProjectList.clear();
                        if (response.getData() != null) {
                            mProjectList.add(response.getData());
                        }
                        mAdapter.notifyDataSetChanged();
                        finishRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                        finishRefresh();
                    }
                }, getContext()), StudentUtils.getSno());
    }

    private void refresh() {
        mSrlRefresh.post(new Runnable() {
            @Override
            public void run() {
                mSrlRefresh.setRefreshing(true);
                fetchMyProject();
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
