package com.chris.pss.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chris.pss.R;
import com.chris.pss.data.entity.ProjectEntity;
import com.chris.pss.data.entity.StudentEntity;
import com.chris.pss.data.entity.TeacherEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by zht on 2017/5/30.
 */

public class RvProjectAdapter extends BaseRvAdapter<ProjectEntity> {

    private boolean isShowCheck;

    public RvProjectAdapter(Context context, List<ProjectEntity> list, OnItemClickListener<ProjectEntity> listener, boolean isShowCheck) {
        super(context, list, listener);
        this.isShowCheck = isShowCheck;
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.layout_project_item;
    }

    @Override
    protected ItemHolder<ProjectEntity> getItemHolder(View view, int viewType) {
        return new Holder(view);
    }

    class Holder extends ItemHolder<ProjectEntity> {
        @BindView(R.id.tv_project_name)
        TextView mTvProjectName;
        @BindView(R.id.tv_project_major)
        TextView mTvProjectMajor;
        @BindView(R.id.mrb_project_star)
        MaterialRatingBar mMrbProjectStar;
        @BindView(R.id.tv_project_detail)
        TextView mTvProjectDetail;
        @BindView(R.id.tv_project_teacher)
        TextView mTvProjectTeacher;
        @BindView(R.id.ll_project_teacher)
        LinearLayout mLlProjectTeacher;
        @BindView(R.id.tv_project_student)
        TextView mTvProjectStudent;
        @BindView(R.id.ll_project_student)
        LinearLayout mLlProjectStudent;
        @BindView(R.id.tv_project_checking)
        TextView mTvProjectChecking;
        @BindView(R.id.iv_project_check)
        ImageView mIvProjectCheck;
        @BindView(R.id.rl_project_root)
        RelativeLayout mRlProjectRoot;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(int position, ProjectEntity data) {
            //课题名
            mTvProjectName.setText(data.getTitle());
            //课题专业
            mTvProjectMajor.setText(data.getMajor().getName());
            //难度
            mMrbProjectStar.setProgress(data.getRanking());
            //详情
            mTvProjectDetail.setText(data.getDetail());
            //
            TeacherEntity teacher = data.getTeacher();
            mTvProjectTeacher.setText(teacher.getName() + "/" + teacher.getZhicheng());
            //
            StudentEntity student = data.getStudent();
            mTvProjectStudent.setText(student == null ? "无" : (student.getName() + "/" + student.getSno()));
            //审核状态
            boolean isChecking = 1 != data.getIsChecked();
            mTvProjectChecking.setVisibility(isChecking ? View.VISIBLE : View.GONE);
            //显示按钮
            mIvProjectCheck.setVisibility(isShowCheck ? View.VISIBLE : View.GONE);
            //
            //监听
            setListener(mRlProjectRoot, position, data, mListener);
            setListener(mLlProjectTeacher, position, data, mListener);
            setListener(mLlProjectStudent, position, data, mListener);
            setListener(mIvProjectCheck, position, data, mListener);
        }
    }
}
