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
public class TeacherNotAdminFragment extends Fragment {


    public TeacherNotAdminFragment() {
        // Required empty public constructor
    }

    public static TeacherNotAdminFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TeacherNotAdminFragment fragment = new TeacherNotAdminFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher_not_admin, container, false);
    }

}
