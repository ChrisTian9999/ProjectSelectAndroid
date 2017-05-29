package com.chris.pss.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.chris.pss.R;
import com.chris.pss.adapter.MajorChooseAdapter;
import com.chris.pss.app.IApp;
import com.chris.pss.app.TeacherUtils;
import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.DepartEntity;
import com.chris.pss.data.entity.SimpleFlagEntity;
import com.chris.pss.data.service.ProjectDataHttpRequest;
import com.chris.pss.utils.EmptyUtils;
import com.chris.pss.utils.ToastUtils;
import com.chris.pss.widgets.subscribers.GeneralSubscriber;
import com.chris.pss.widgets.subscribers.ProgressSubscriber;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeacherCreateProjectActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_detail)
    EditText mEtDetail;
    @BindView(R.id.sp_major)
    Spinner mSpMajor;
    @BindView(R.id.rb_rank)
    RatingBar mRbRank;
    @BindView(R.id.btn_create)
    Button mBtnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_project);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initToolBar(mToolbar);
        //
        mSpMajor.setAdapter(new MajorChooseAdapter(this, TeacherUtils.getMajorList()));
        //

    }

    @OnClick({R.id.btn_create})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_create:
                createProject();
                break;
        }
    }

    private void createProject() {
        String title = mEtName.getText().toString().trim();//标题
        String detail = mEtDetail.getText().toString().trim();//详情
        DepartEntity majorEntity = (DepartEntity) mSpMajor.getSelectedItem();//所选的专业
        int starsNum = (int) mRbRank.getRating();//难度1-5
        int departId = TeacherUtils.getMyDepartId();//学院
        //开始检测
        if (EmptyUtils.isEmpty(title)) {
            ToastUtils.showToast("标题不能为空");
            return;
        }
        if (EmptyUtils.isEmpty(detail)) {
            ToastUtils.showToast("详情不能为空");
            return;
        }
        if (EmptyUtils.isEmpty(majorEntity)) {
            ToastUtils.showToast("请选择目标专业");
            return;
        }
//        postCreateProject(departId, majorEntity.getId(), IApp.tch.getId(), title, detail, starsNum);
    }

    private void postCreateProject(int departmentId, int majorId, int teacherId, String title, String detail, int ranking) {
        ProjectDataHttpRequest.newInstance(IApp.context)
                .createProject(new ProgressSubscriber<>(new GeneralSubscriber<BaseResponse<SimpleFlagEntity>>() {
                    @Override
                    public void onNext(BaseResponse<SimpleFlagEntity> response) {
                        ToastUtils.showToast("创建成功");
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                    }
                }, TeacherCreateProjectActivity.this), departmentId, majorId, teacherId, title, detail, ranking);

    }
}
