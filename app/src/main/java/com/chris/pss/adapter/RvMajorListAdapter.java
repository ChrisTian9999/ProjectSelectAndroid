package com.chris.pss.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chris.pss.R;
import com.chris.pss.app.SimpleUtils;
import com.chris.pss.data.entity.DepartEntity;
import com.chris.pss.utils.EmptyUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zht on 2017/5/19.
 */

public class RvMajorListAdapter extends BaseRvAdapter<DepartEntity> {

    public RvMajorListAdapter(Context context, List<DepartEntity> list, OnItemClickListener<DepartEntity> listener) {
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

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void setData(int position, final DepartEntity data) {
            mTvMajorName.setText(data.getName());
            //
            mTvMajorStart.setText(EmptyUtils.isEmpty(data.getTimeBegin()) ? "null" : data.getTimeBegin());
            mTvMajorEnd.setText(EmptyUtils.isEmpty(data.getTimeEnd()) ? "null" : data.getTimeEnd());
            //显示状态
            long start = SimpleUtils.getLongDateFromString(data.getTimeBegin());
            long end = SimpleUtils.getLongDateFromString(data.getTimeEnd());
            // 未开始，进行中,已结束
            long millis = System.currentTimeMillis();
            if (start <= 0 || end <= 0 || start < end
                    || millis < start) {//未开始
                mTvState.setText(R.string.state_before);
                mTvState.setBackgroundResource(R.drawable.bg_state_black);
            } else if (millis >= start && millis < end) {
                mTvState.setText(R.string.state_start);
                mTvState.setBackgroundResource(R.drawable.bg_state_green);
            } else if (millis >= end) {
                mTvState.setText(R.string.state_end);
                mTvState.setBackgroundResource(R.drawable.bg_state_red);
            }
            //监听
            setListener(itemView, position, data, mListener);
        }
    }
}
