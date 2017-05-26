package com.chris.pss.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chris.pss.R;
import com.chris.pss.data.entity.DepartEntity;

public class TeacherResetTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_reset_time);
    }


    public static void JumpHere(Context c, DepartEntity entity) {
        Intent intent = new Intent(c, TeacherResetTimeActivity.class);
        intent.putExtra("depart", entity);
        c.startActivity(intent);
    }
}
