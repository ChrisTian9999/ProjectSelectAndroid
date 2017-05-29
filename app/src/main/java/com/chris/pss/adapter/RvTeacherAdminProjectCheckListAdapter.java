package com.chris.pss.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chris.pss.R;
import com.chris.pss.data.entity.DepartEntity;
import com.chris.pss.data.entity.ProjectEntity;
import com.chris.pss.data.entity.TeacherEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by zht on 2017/5/30.
 */

public class RvTeacherAdminProjectCheckListAdapter extends BaseRvAdapter<ProjectEntity> {

    public RvTeacherAdminProjectCheckListAdapter(Context context, List<ProjectEntity> list, OnItemClickListener<ProjectEntity> listener) {
        super(context, list, listener);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.layout_teacher_admin_project_check_item;
    }

    @Override
    protected ItemHolder<ProjectEntity> getItemHolder(View view, int viewType) {
        return new ViewHolder(view);
    }

    class ViewHolder extends ItemHolder<ProjectEntity> {
        @BindView(R.id.rl_project_root)
        RelativeLayout mLlProjectRoot;
        @BindView(R.id.tv_project_name)
        TextView mTvProjectName;
        @BindView(R.id.tv_project_major)
        TextView mTvProjectMajor;
        @BindView(R.id.mrb_project_star)
        MaterialRatingBar mMrbProjectStar;
        @BindView(R.id.tv_project_detail)
        TextView mTvProjectDetail;
        @BindView(R.id.tv_project_teacher)
        TextView mTvProjectTch;
        @BindView(R.id.iv_project_check)
        ImageView mIvProjectCheck;

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
            mTvProjectDetail.setText(data.getDetail());
            //checking (默认全部是审核未通过的)
            if (1 != data.getIsChecked()) {//审核暂未通过
                TeacherEntity teacher = data.getTeacher();
                mTvProjectTch.setText(teacher.getName() + "/" + teacher.getZhicheng());
            }
            setListener(mIvProjectCheck, position, data, mListener);
            setListener(mLlProjectRoot, position, data, mListener);
        }
    }
}
