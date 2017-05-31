package com.chris.pss.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chris.pss.R;
import com.chris.pss.activity.BaseActivity;
import com.chris.pss.activity.LoginActivity;
import com.chris.pss.activity.ResetPwdActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zht on 2017/5/18.
 */

public class SettingsFragment extends Fragment {

    Unbinder unbinder;

    public static SettingsFragment newInstance() {

        Bundle args = new Bundle();

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.tv_reset_pwd, R.id.tv_log_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_reset_pwd:
                startActivity(new Intent(getContext(), ResetPwdActivity.class));
                break;
            case R.id.tv_log_out:
                logout();
                break;
        }
    }

    private void logout() {
        new MaterialDialog.Builder(getContext())
                .title("确定退出？")
                .negativeText(R.string.cancel)
                .onNegative(null)
                .positiveText(R.string.ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        //退出登陆
                        EventBus.getDefault().post(new BaseActivity.FinishEvent());
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    }
                }).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
