package com.chris.pss.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chris.pss.R;
import com.chris.pss.adapter.TeacherAdminPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherAdminFragment extends Fragment {


    @BindView(R.id.tl_indicator)
    TabLayout mTlIndicator;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    Unbinder unbinder;

    public TeacherAdminFragment() {
        // Required empty public constructor
    }

    public static TeacherAdminFragment newInstance() {

        Bundle args = new Bundle();

        TeacherAdminFragment fragment = new TeacherAdminFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teacher_admin, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        mVpContent.setOffscreenPageLimit(2);
        mVpContent.setAdapter(new TeacherAdminPagerAdapter(getChildFragmentManager()));
        mTlIndicator.setupWithViewPager(mVpContent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
