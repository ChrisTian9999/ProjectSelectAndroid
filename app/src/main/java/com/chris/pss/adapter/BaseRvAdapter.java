package com.chris.pss.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zht on 2017/5/22.
 */

public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter<BaseRvAdapter.ItemHolder<T>> {

    protected Context mContext;
    protected List<T> mList;
    protected OnItemClickListener<T> mListener;

    public BaseRvAdapter(Context context, List<T> list, OnItemClickListener<T> listener) {
        mContext = context;
        mList = list;
        mListener = listener;
    }

    public void setList(List<T> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public ItemHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(getItemLayoutId(viewType), parent, false);
        return getItemHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ItemHolder<T> holder, int position) {
        holder.setData(position, mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    protected void setListener(View view, final int position, final T data, final OnItemClickListener<T> listener) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClick(position, data);
            }
        });
    }


    protected abstract int getItemLayoutId(int viewType);

    protected abstract ItemHolder<T> getItemHolder(View view, int viewType);




    protected static class ItemHolder<T> extends RecyclerView.ViewHolder {
        public ItemHolder(View itemView) {
            super(itemView);
        }

        public void setData(int position, T data){}
    }

    public interface OnItemClickListener<T> {
        void OnItemClick(int position, T data);
    }
}
