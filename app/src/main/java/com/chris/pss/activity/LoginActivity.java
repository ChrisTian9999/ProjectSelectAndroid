package com.chris.pss.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.chris.pss.R;
import com.chris.pss.app.IApp;
import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.StudentEntity;
import com.chris.pss.data.entity.TeacherLoginResult;
import com.chris.pss.data.service.StudentDataHttpRequest;
import com.chris.pss.data.service.TeacherDataHttpRequest;
import com.chris.pss.utils.LogUtils;
import com.chris.pss.utils.ToastUtils;
import com.chris.pss.widgets.subscribers.GeneralSubscriber;
import com.chris.pss.widgets.subscribers.ProgressSubscriber;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity {


    @BindView(R.id.et_no)
    EditText mEtNo;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.cb_eye)
    CheckBox mCbEye;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mCbEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mEtPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mEtPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    @OnClick({R.id.rl_eye, R.id.btn_login_tch, R.id.btn_login_stu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_eye:
                mCbEye.setChecked(!mCbEye.isChecked());
                break;
            case R.id.btn_login_tch:
                mEtNo.setText("t002");
                mEtPwd.setText("123456");
                loginTeacher();
                break;
            case R.id.btn_login_stu:
                mEtNo.setText("201301");
                mEtPwd.setText("123456");
                loginStudent();
                break;
        }
    }

    public void loginTeacher() {
        String tno = mEtNo.getText().toString().trim();
        String pwd = mEtPwd.getText().toString().trim();
        if (!checkIfOk(tno, pwd)) {
            return;
        }
        TeacherDataHttpRequest.newInstance(IApp.context)
                .postLogin(new ProgressSubscriber<>(new GeneralSubscriber<BaseResponse<TeacherLoginResult>>() {
                    @Override
                    public void onNext(BaseResponse<TeacherLoginResult> response) {
                        LogUtils.e(response);
                        //
                        String teacherName = response.getData().getTeacher().getName();
                        ToastUtils.showToast("欢迎您，" + teacherName);
                        loginSuc(response.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                    }
                }, LoginActivity.this), tno, pwd);
    }

    /**
     * 教师登录成功
     */
    private void loginSuc(TeacherLoginResult result) {
        IApp.isStudent = false;
        IApp.teacher = result.getTeacher();//当前教师的信息
        IApp.majors = result.getMajors();//所在学院的所有专业
        //跳转到教师页面
        Intent intent = new Intent(this, TeacherActivity.class);
        startActivity(intent);
        finish();
    }

    public void loginStudent() {
        String sno = mEtNo.getText().toString().trim();
        String pwd = mEtPwd.getText().toString().trim();
        if (!checkIfOk(sno, pwd)) {
            return;
        }
        StudentDataHttpRequest.newInstance(IApp.context)
                .postLogin(new ProgressSubscriber<>(new GeneralSubscriber<BaseResponse<StudentEntity>>() {
                    @Override
                    public void onNext(BaseResponse<StudentEntity> response) {
                        LogUtils.e(response);

                        StudentEntity studentEntity = response.getData();
                        if (studentEntity != null) {
                            String studentName = studentEntity.getName();
                            ToastUtils.showToast("欢迎您，" + studentName);
                            loginSuc(studentEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                    }
                }, LoginActivity.this), sno, pwd);
    }

    private void loginSuc(StudentEntity entity) {
        IApp.isStudent = true;
        IApp.student = entity;
        //跳转到学生页面
        Intent intent = new Intent(this, StudentActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * 检验账号密码是否符合规则
     */
    public boolean checkIfOk(String account, String pwd) {
        if (account.length() <= 0) {
            ToastUtils.showToast(R.string.login_hint_no);
            return false;
        }
        if (pwd.length() <= 0) {
            ToastUtils.showToast(R.string.login_hint_pwd);
            return false;
        }
        return true;
    }
}

