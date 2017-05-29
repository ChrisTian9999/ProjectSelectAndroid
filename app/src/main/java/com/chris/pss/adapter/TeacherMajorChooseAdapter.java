package com.chris.pss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chris.pss.R;
import com.chris.pss.data.entity.DepartEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zht on 2017/5/28.
 */

public class TeacherMajorChooseAdapter extends BaseAdapter {

    private Context mContext;
    private List<DepartEntity> list;

    public TeacherMajorChooseAdapter(Context context, List<DepartEntity> list) {
        mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public DepartEntity getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_major_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.setData(getItem(position));
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView mTvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(DepartEntity entity) {
            mTvName.setText(entity.getName());
        }
    }
}
