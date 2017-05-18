package com.chris.pss.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chris.pss.R;

public class ResetPwdActivity extends AppCompatActivity {

    private static final int TYPE_STU = 0;
    private static final int TYPE_TCH = 1;

    private int mType = TYPE_STU;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        //
        Intent intent = getIntent();
        if (intent != null) {
            mType = intent.getIntExtra("type", TYPE_STU);
        }
    }


    public static void resetStudentPwd(Context context) {
        Intent intent = new Intent(context, ResetPwdActivity.class);
        intent.putExtra("type", TYPE_STU);
        context.startActivity(intent);
    }

    public static void resetTeacherPwd(Context context) {
        Intent intent = new Intent(context, ResetPwdActivity.class);
        intent.putExtra("type", TYPE_TCH);
        context.startActivity(intent);
    }
}
