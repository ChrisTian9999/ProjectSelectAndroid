package com.chris.pss.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.chris.pss.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_no)
    EditText mEtNo;
    @BindView(R.id.et_pwd)
    EditText mEtpwd;
    @BindView(R.id.cb_eye)
    CheckBox mCbEye;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mCbEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mEtpwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mEtpwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
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
                break;
            case R.id.btn_login_stu:
                break;
        }
    }
}

