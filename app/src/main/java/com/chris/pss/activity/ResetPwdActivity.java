package com.chris.pss.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.chris.pss.R;
import com.chris.pss.app.IApp;
import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.SimpleFlagEntity;
import com.chris.pss.data.service.StudentDataHttpRequest;
import com.chris.pss.data.service.TeacherDataHttpRequest;
import com.chris.pss.myutils.StudentUtils;
import com.chris.pss.utils.EmptyUtils;
import com.chris.pss.utils.ToastUtils;
import com.chris.pss.widgets.subscribers.GeneralSubscriber;
import com.chris.pss.widgets.subscribers.ProgressSubscriber;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPwdActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_raw_pwd)
    EditText mEtRawPwd;
    @BindView(R.id.et_new_pwd)
    EditText mEtNewPwd;
    @BindView(R.id.et_new_pwd2)
    EditText mEtNewPwd2;
    private GeneralSubscriber<BaseResponse<SimpleFlagEntity>> mSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        ButterKnife.bind(this);
        initToolBar(mToolbar);

        mSubscriber = new GeneralSubscriber<BaseResponse<SimpleFlagEntity>>() {
            @Override
            public void onNext(BaseResponse<SimpleFlagEntity> response) {
                ToastUtils.showToast("修改成功");
                EventBus.getDefault().post(new BaseActivity.FinishEvent());
                startActivity(new Intent(ResetPwdActivity.this, LoginActivity.class));
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showToast(e.getMessage());
            }
        };

    }

    @OnClick({R.id.btn_reset_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_reset_pwd:
                resetPwd();
                break;
        }
    }

    private void resetPwd() {
        String rawPwd = mEtRawPwd.getText().toString().trim();
        String newPwd = mEtNewPwd.getText().toString().trim();
        String newPwd2 = mEtNewPwd2.getText().toString().trim();
        if (EmptyUtils.isEmpty(rawPwd)) {
            ToastUtils.showToast("请输入原密码");
            return;
        }
        if (EmptyUtils.isEmpty(newPwd)) {
            ToastUtils.showToast("请输入新密码");
            return;
        }

        if (newPwd.length() < 6) {
            ToastUtils.showToast("新密码过于简单");
            return;
        }
        if (EmptyUtils.isEmpty(newPwd2)) {
            ToastUtils.showToast("请再次输入新密码");
            return;
        }
        if (!newPwd.equals(newPwd2)) {
            ToastUtils.showToast("两次密码不一致");
            return;
        }
        //
        if (IApp.isStudent) {
            resetStudentPwd(StudentUtils.getSno(), rawPwd, newPwd);
        } else {
            resetTeacherPwd(IApp.teacher.getTno(), rawPwd, newPwd);
        }
    }

    public void resetTeacherPwd(String tno, String pwd, String newPwd) {
        TeacherDataHttpRequest.newInstance(IApp.context)
                .resetPwd(new ProgressSubscriber<>(mSubscriber, ResetPwdActivity.this), tno, pwd, newPwd);
    }

    public void resetStudentPwd(String sno, String pwd, String newPwd) {
        StudentDataHttpRequest.newInstance(IApp.context)
                .resetPwd(new ProgressSubscriber<>(mSubscriber, ResetPwdActivity.this), sno, pwd, newPwd);
    }
}
