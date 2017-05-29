package com.chris.pss.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chris.pss.R;
import com.chris.pss.app.SimpleDisplayUtils;
import com.chris.pss.data.entity.DepartEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zht on 2017/5/19.
 */

public class RvTeacherAdminMajorListAdapter extends BaseRvAdapter<DepartEntity> {

    public RvTeacherAdminMajorListAdapter(Context context, List<DepartEntity> list, OnItemClickListener<DepartEntity> listener) {
        super(context, list, listener);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.layout_depart_item;
    }

    @Override
    protected ItemHolder<DepartEntity> getItemHolder(View view, int viewType) {
        return new ViewHolder(view);
    }

    class ViewHolder extends ItemHolder<DepartEntity> {
        @BindView(R.id.tv_major_name)
        TextView mTvMajorName;
        @BindView(R.id.tv_major_start)
        TextView mTvMajorStart;
        @BindView(R.id.tv_major_end)
        TextView mTvMajorEnd;
        @BindView(R.id.tv_major_state)
        TextView mTvState;
        @BindView(R.id.iv_reset_time)
        ImageView mIvResetTime;
        @BindView(R.id.iv_cha_kan)
        ImageView mIvChaKan;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void setData(int position, final DepartEntity data) {
            mTvMajorName.setText(data.getName());
            //
            mTvMajorStart.setText(SimpleDisplayUtils.getDisplayMajorTime(data.getTimeBegin()));
            mTvMajorEnd.setText(SimpleDisplayUtils.getDisplayMajorTime(data.getTimeEnd()));

            int state = SimpleDisplayUtils.getMajorStateByTime(data.getTimeBegin(), data.getTimeEnd());
            switch (state) {
                case 1:
                    mTvState.setText(R.string.state_before);
                    mTvState.setBackgroundResource(R.drawable.bg_state_black);
                    break;
                case 2:
                    mTvState.setText(R.string.state_start);
                    mTvState.setBackgroundResource(R.drawable.bg_state_green);
                    break;
                case 3:
                    mTvState.setText(R.string.state_end);
                    mTvState.setBackgroundResource(R.drawable.bg_state_red);
                    break;
                default:
                    break;
            }
            //监听
            setListener(itemView, position, data, mListener);
            setListener(mIvResetTime, position, data, mListener);
            setListener(mIvChaKan, position, data, mListener);
        }
    }
}
