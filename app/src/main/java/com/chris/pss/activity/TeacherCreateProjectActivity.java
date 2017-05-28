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
import com.chris.pss.app.TeacherUtils;

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
                break;
        }
    }
}
