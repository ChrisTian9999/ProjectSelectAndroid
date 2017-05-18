package com.chris.pss.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chris.pss.R;
import com.chris.pss.app.IApp;
import com.chris.pss.fragment.BlankFragment;
import com.chris.pss.fragment.TeacherProjectListFragment;
import com.chris.pss.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeacherActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
        setContentView(R.layout.activity_teacher);
        ButterKnife.bind(this);
        //
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //
        mNavView.setNavigationItemSelectedListener(TeacherActivity.this);
        //初始化头部
        View mHeaderView = mNavView.getHeaderView(0);
        mHeaderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tno = IApp.tch.getTno();
                startActivity(TeacherInfoActivity.getJumpIntent(TeacherActivity.this, tno));
            }
        });
        TextView mHeaderName = (TextView) mHeaderView.findViewById(R.id.tv_header_name);
        mHeaderName.setText(IApp.tch.getName());

        //默认打开菜单
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_container, TeacherProjectListFragment.newInstance());
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.nav_project_all:
                ft.replace(R.id.fl_container, TeacherProjectListFragment.newInstance());
                break;
            case R.id.nav_admin:
                if (IApp.tch.getIsAdmin() == 1) {
                    startActivity(new Intent(this, TeacherAdminActivity.class));
                } else {
                    ToastUtils.showToast("无权限");
                }
                break;
            default:
                ft.replace(R.id.fl_container, BlankFragment.newInstance());
                break;
        }
        ft.commit();
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
