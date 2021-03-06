package com.chris.pss.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.chris.pss.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashActivity extends BaseActivity {

    private static final int DURATION_ANIM = 1200;
    private static final int DURATION_JUMP = DURATION_ANIM + 200;

    @BindView(R.id.tv_app_name)
    TextView mTvAppName;
    @BindView(R.id.tv_code_by)
    TextView mTvCodeBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViews();
    }

    private void initViews() {
        ObjectAnimator.ofFloat(mTvAppName, "alpha", 0.3f, 1f)
                .setDuration(DURATION_ANIM)
                .start();
        ObjectAnimator.ofFloat(mTvCodeBy, "alpha", 0.3f, 1f)
                .setDuration(DURATION_ANIM)
                .start();
        mTvAppName.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
                overridePendingTransition(R.anim.anim_alpha_show, R.anim.anim_alpha_stay);
            }
        }, DURATION_JUMP);
    }


}
