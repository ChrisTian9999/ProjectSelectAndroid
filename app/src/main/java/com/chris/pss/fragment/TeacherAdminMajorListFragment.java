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
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chris.pss.R;
import com.chris.pss.adapter.BaseRvAdapter;
import com.chris.pss.adapter.RvTeacherAdminMajorListAdapter;
import com.chris.pss.app.IApp;
import com.chris.pss.app.TeacherUtils;
import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.DepartEntity;
import com.chris.pss.data.entity.SimpleFlagEntity;
import com.chris.pss.data.service.DepartDataHttpRequest;
import com.chris.pss.utils.ToastUtils;
import com.chris.pss.widgets.recyclerview.dividers.HorizontalDividerItemDecoration;
import com.chris.pss.widgets.subscribers.GeneralSubscriber;
import com.chris.pss.widgets.subscribers.ProgressSubscriber;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherAdminMajorListFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    private RvTeacherAdminMajorListAdapter mAdapter;

    public TeacherAdminMajorListFragment() {
    }

    public static TeacherAdminMajorListFragment newInstance() {

        Bundle args = new Bundle();

        TeacherAdminMajorListFragment fragment = new TeacherAdminMajorListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_admin_major_list, container, false);
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

        mAdapter = getRvMajorListAdapter();
        mRecycler.setAdapter(mAdapter);
        //
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchDeparts();
            }
        });
        //
        refresh();
    }


    public void fetchDeparts() {
        DepartDataHttpRequest.newInstance(IApp.context)
                .getMajorList(new ProgressSubscriber<>(new GeneralSubscriber<BaseResponse<List<DepartEntity>>>() {
                    @Override
                    public void onNext(BaseResponse<List<DepartEntity>> response) {
                        List<DepartEntity> majorList = response.getData();
                        //更新全局数据
                        IApp.majors = majorList;

                        mAdapter.setList(majorList);
                        finishRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                        finishRefresh();
                    }
                }, getContext()), TeacherUtils.getMyDepartId());
    }

    @NonNull
    private RvTeacherAdminMajorListAdapter getRvMajorListAdapter() {
        return new RvTeacherAdminMajorListAdapter(getContext(), null, new BaseRvAdapter.OnItemClickListener<DepartEntity>() {
            @Override
            public void OnItemClick(View view, int position, DepartEntity data) {
                switch (view.getId()) {
                    case R.id.rl_root://点击item
                        break;
                    case R.id.iv_reset_time:
                        resetTime(data);
                        break;
                }
            }
        });
    }


    private void resetTime(final DepartEntity entity) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_admin_reset_time, null);
        final EditText EtStart = (EditText) view.findViewById(R.id.et_start);
        final EditText EtEnd = (EditText) view.findViewById(R.id.et_end);
        EtStart.setText(entity.getTimeBegin());
        EtEnd.setText(entity.getTimeEnd());

        new MaterialDialog.Builder(getContext())
                .customView(view, false)
                .title(R.string.title_dialog_reset_time)
                .positiveText(R.string.ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        String start = EtStart.getText().toString().trim();
                        String end = EtEnd.getText().toString().trim();
                        postModifyMajor(entity.getId(), start, end);
                    }
                })
                .negativeText(R.string.cancel)
                .show();
    }

    private void postModifyMajor(int id, String start, String end) {
        DepartDataHttpRequest.newInstance(IApp.context)
                .postModifyMajor(new ProgressSubscriber<>(new GeneralSubscriber<BaseResponse<SimpleFlagEntity>>() {
                    @Override
                    public void onNext(BaseResponse<SimpleFlagEntity> response) {
                        ToastUtils.showToast("修改成功");
                        refresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                    }
                }, getContext()), id, start, end);
    }


    private void refresh() {
        mSrlRefresh.post(new Runnable() {
            @Override
            public void run() {
                mSrlRefresh.setRefreshing(true);
                fetchDeparts();
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
