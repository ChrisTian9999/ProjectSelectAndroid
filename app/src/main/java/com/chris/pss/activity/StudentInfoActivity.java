package com.chris.pss.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.chris.pss.R;
import com.chris.pss.app.IApp;
import com.chris.pss.myutils.StudentUtils;
import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.entity.DepartEntity;
import com.chris.pss.data.entity.StudentEntity;
import com.chris.pss.data.service.StudentDataHttpRequest;
import com.chris.pss.utils.ToastUtils;
import com.chris.pss.widgets.subscribers.GeneralSubscriber;
import com.chris.pss.widgets.subscribers.ProgressSubscriber;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentInfoActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_info_sno)
    TextView mTvInfoSno;
    @BindView(R.id.tv_info_name)
    TextView mTvInfoName;
    @BindView(R.id.tv_info_gender)
    TextView mTvInfoGender;
    @BindView(R.id.tv_info_depart)
    TextView mTvInfoDepart;
    @BindView(R.id.tv_info_major)
    TextView mTvInfoMajor;
    @BindView(R.id.tv_info_tel)
    TextView mTvInfoTel;
    @BindView(R.id.tv_info_classname)
    TextView mTvInfoClassname;


    private String mSno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            mSno = intent.getStringExtra("sno");
        }
        initViews();
    }


    private void initViews() {
        initToolBar(mToolbar);

        if (StudentUtils.isMe(mSno)) {//是当前用户
            initData(IApp.student);
        } else { //不是当前用户
            fetchStuInfo(mSno);
        }
    }

    private void fetchStuInfo(String sno) {
        StudentDataHttpRequest.newInstance(IApp.context)
                .getStuInfo(new ProgressSubscriber<>(new GeneralSubscriber<BaseResponse<StudentEntity>>() {
                    @Override
                    public void onNext(BaseResponse<StudentEntity> response) {
                        initData(response.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                    }
                }, this), sno);
    }


    private void initData(StudentEntity stu) {
        if (stu == null) {
            ToastUtils.showToast("无数据");
            return;
        }
        mTvInfoSno.setText(stu.getSno());
        mTvInfoName.setText(stu.getName());
        mTvInfoGender.setText(stu.getGender() == 0 ? R.string.gender_girl : R.string.gender_boy);
        //
        mTvInfoTel.setText(stu.getTel());
        mTvInfoClassname.setText(stu.getClassname());
        //所在学院和专业的信息
        DepartEntity depart = stu.getMajor();
        if (depart != null) {
            mTvInfoDepart.setText(depart.getName());
            mTvInfoMajor.setText(depart.getParent().getName());
        }
    }


    public static void jumpHere(Context c, String sno) {
        Intent intent = new Intent(c, StudentInfoActivity.class);
        intent.putExtra("sno", sno);
        c.startActivity(intent);
    }
}
