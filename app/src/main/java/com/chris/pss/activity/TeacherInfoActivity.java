package com.chris.pss.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chris.pss.R;
import com.chris.pss.app.IApp;
import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.DepartEntity;
import com.chris.pss.data.entity.TchEntity;
import com.chris.pss.data.entity.TchLoginResult;
import com.chris.pss.data.service.TeacherDataHttpRequest;
import com.chris.pss.utils.ToastUtils;
import com.chris.pss.widgets.subscribers.GeneralSubscriber;
import com.chris.pss.widgets.subscribers.ProgressSubscriber;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeacherInfoActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_info_tno)
    TextView mTvInfoTno;
    @BindView(R.id.tv_info_name)
    TextView mTvInfoName;
    @BindView(R.id.tv_info_gender)
    TextView mTvInfoGender;
    @BindView(R.id.tv_info_zhicheng)
    TextView mTvInfoZhicheng;
    @BindView(R.id.tv_info_depart)
    TextView mTvInfoDepart;
    @BindView(R.id.tv_info_tel)
    TextView mTvInfoTel;
    @BindView(R.id.tv_info_email)
    TextView mTvInfoEmail;
    @BindView(R.id.tv_info_is_admin)
    TextView mTvInfoIsAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mToolbar.setTitle(R.string.title_info_teacher_info);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String tno = intent.getStringExtra("tno");
            if (IApp.tch.getTno().equals(tno)) {//是当前用户
                initData(IApp.tch, IApp.depart);
            }else { //不是当前用户
                fetchTchInfo(tno);
            }
        }else{
            ToastUtils.showToast("未知错误");
        }

    }

    private void fetchTchInfo(String tno) {
        TeacherDataHttpRequest.newInstance(this)
                .getTchInfo(new ProgressSubscriber<>(new GeneralSubscriber<BaseResponse<TchLoginResult>>() {
                    @Override
                    public void onNext(BaseResponse<TchLoginResult> response) {
                        TchLoginResult data = response.getData();
                        initData(data.getTch(), data.getDepart());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                    }
                }, IApp.context), tno);
    }


    private void initData(TchEntity tch, DepartEntity depart) {
        if (tch == null) {
            ToastUtils.showToast("无数据");
            return;
        }
        mTvInfoTno.setText(tch.getTno());
        mTvInfoName.setText(tch.getName());
        mTvInfoGender.setText(tch.getGender() == 0 ? R.string.gender_girl : R.string.gender_boy);
        mTvInfoZhicheng.setText(tch.getZhicheng());
        //
        mTvInfoTel.setText(tch.getTel());
        mTvInfoEmail.setText(tch.getEmail());
        mTvInfoIsAdmin.setText(tch.getIsAdmin() == 0 ? R.string.yes : R.string.no);

        if (depart == null) {
            ToastUtils.showToast("无学院信息");
            return;
        }
        mTvInfoDepart.setText(depart.getName());
    }


    public static Intent getJumpIntent(String tno) {
        Intent intent = new Intent();
        intent.putExtra("tno", tno);
        return intent;
    }
}
