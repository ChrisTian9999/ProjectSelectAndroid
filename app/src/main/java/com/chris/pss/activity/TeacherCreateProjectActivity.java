package com.chris.pss.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.chris.pss.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeacherCreateProjectActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_project);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initToolBar(mToolbar);
    }
}
