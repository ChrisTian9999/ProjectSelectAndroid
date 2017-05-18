package com.chris.pss.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chris.pss.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherAdminFragment extends Fragment {


    public TeacherAdminFragment() {
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
        return inflater.inflate(R.layout.fragment_teacher_admin, container, false);
    }

}
