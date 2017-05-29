package com.chris.pss.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chris.pss.R;
import com.chris.pss.data.entity.DepartEntity;
import com.chris.pss.data.entity.ProjectEntity;
import com.chris.pss.data.entity.StuEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by zht on 2017/5/30.
 */

public class RvTeacherProjectListAdapter extends BaseRvAdapter<ProjectEntity> {

    public RvTeacherProjectListAdapter(Context context, List<ProjectEntity> list, OnItemClickListener<ProjectEntity> listener) {
        super(context, list, listener);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.layout_teacher_project_item;
    }

    @Override
    protected ItemHolder<ProjectEntity> getItemHolder(View view, int viewType) {
        return new ViewHolder(view);
    }

    class ViewHolder extends ItemHolder<ProjectEntity> {
        @BindView(R.id.ll_project_root)
        LinearLayout mLlProjectRoot;
        @BindView(R.id.tv_project_name)
        TextView mTvProjectName;
        @BindView(R.id.tv_project_major)
        TextView mTvProjectMajor;
        @BindView(R.id.mrb_project_star)
        MaterialRatingBar mMrbProjectStar;
        @BindView(R.id.expandable_text)
        TextView mExpandableText;
        @BindView(R.id.tv_project_checking)
        TextView mTvProjectChecking;
        @BindView(R.id.tv_project_stu)
        TextView mTvProjectStu;
        @BindView(R.id.ll_project_student)
        LinearLayout mLlProjectStudent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(int position, ProjectEntity data) {
            //课题名
            mTvProjectName.setText(data.getTitle());
            //课题专业
            DepartEntity major = data.getMajor();
            mTvProjectMajor.setText(major.getName());
            //难度
            mMrbProjectStar.setProgress(data.getRanking());
            //详情
            mExpandableText.setText(data.getDetail());
            //checking / student / null
            if (1 == data.getIsChecked()) {//审核已通过
                mTvProjectChecking.setVisibility(View.GONE);
                mLlProjectStudent.setVisibility(View.VISIBLE);
                //学生
                StuEntity stu = data.getStudent();
                mTvProjectStu.setText(stu == null ? "无" : stu.getName() + "/" + stu.getSno());
                setListener(mLlProjectStudent, position, data, mListener);
            } else {//审核暂未通过
                mTvProjectChecking.setVisibility(View.VISIBLE);
                mLlProjectStudent.setVisibility(View.GONE);
            }
            setListener(mLlProjectRoot, position, data, mListener);
        }
    }
}
