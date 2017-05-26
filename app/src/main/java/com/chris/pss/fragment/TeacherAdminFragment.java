package com.chris.pss.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chris.pss.R;
import com.chris.pss.adapter.BaseRvAdapter;
import com.chris.pss.adapter.RvMajorListAdapter;
import com.chris.pss.app.IApp;
import com.chris.pss.app.SimpleUtils;
import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.DepartEntity;
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
public class TeacherAdminFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    private RvMajorListAdapter mAdapter;

    public TeacherAdminFragment() {
    }

    public static TeacherAdminFragment newInstance() {

        Bundle args = new Bundle();

        TeacherAdminFragment fragment = new TeacherAdminFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_admin, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                .colorResId(R.color.colorDividerGray)
                .showLastDivider()
                .build());

        mAdapter = new RvMajorListAdapter(getContext(), null, new BaseRvAdapter.OnItemClickListener<DepartEntity>() {
            @Override
            public void OnItemClick(View view, int position, DepartEntity data) {
                ToastUtils.showToast("" + data.getName());
            }
        });
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
                .getDepartList(new ProgressSubscriber<>(new GeneralSubscriber<BaseResponse<List<DepartEntity>>>() {
                    @Override
                    public void onNext(BaseResponse<List<DepartEntity>> response) {
                        List<DepartEntity> departList = response.getData();
                        if (departList != null) {
                            //更新全局数据
                            IApp.extras = departList;
                            //
                            List<DepartEntity> majorList = SimpleUtils.getChiledDepartList(IApp.tch.getDepartmentId());
                            mAdapter.setList(majorList);
                        }
                        finishRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                        finishRefresh();
                    }
                }, getContext()));
    }


    public void refresh() {
        mSrlRefresh.post(new Runnable() {
            @Override
            public void run() {
                mSrlRefresh.setRefreshing(true);
                fetchDeparts();
            }
        });
    }

    public void finishRefresh() {
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
