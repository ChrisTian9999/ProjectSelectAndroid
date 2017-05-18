package com.chris.pss.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chris.pss.R;
import com.chris.pss.data.entity.DepartEntity;

import java.util.List;

/**
 * Created by zht on 2017/5/19.
 */

public class RvMajorListAdapter extends RecyclerView.Adapter<RvMajorListAdapter.ItemHolder{

    private Context mContext;
    private List<DepartEntity> mList;

    public RvMajorListAdapter(Context context, List<DepartEntity> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<DepartEntity> list) {
        mList = list;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater.from(mContext).inflate(R.layout.layout_depart_item)
        return new ItemHolder();
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private static class ItemHolder extends RecyclerView.ViewHolder{
        public ItemHolder(View itemView) {
            super(itemView);
        }
    }
}
