package com.chris.pss.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
    private String mTno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            mTno = intent.getStringExtra("tno");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViews();
    }

    private void initViews() {
        initToolBar(mToolbar);

        if (IApp.tch.getTno().equals(mTno)) {//是当前用户
            initData(IApp.tch, IApp.depart);
        }else { //不是当前用户
            fetchTchInfo(mTno);
        }
    }

    private void fetchTchInfo(String tno) {
        TeacherDataHttpRequest.newInstance(IApp.context)
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
                },this ), tno);
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
        mTvInfoIsAdmin.setText(tch.getIsAdmin() == 1 ? R.string.yes : R.string.no);

        if (depart == null) {
            ToastUtils.showToast("无学院信息");
            return;
        }
        mTvInfoDepart.setText(depart.getName());
    }


    public static Intent getJumpIntent(Context context, String tno) {
        Intent intent = new Intent(context, TeacherInfoActivity.class);
        intent.putExtra("tno", tno);
        return intent;
    }
}
