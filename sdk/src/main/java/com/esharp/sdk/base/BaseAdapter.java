package com.esharp.sdk.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 功能描述：RecyclerView 适配器基类
 *
 * @author (作者) someone
 * 创建时间： 2021/7/13
 */
public abstract class BaseAdapter<D, I> extends RecyclerView.Adapter<BaseAdapter.ViewHolder<D, I>> {
    protected final List<D> mList;
    protected final I onItemOperate;

    public BaseAdapter(List<D> list, I onItemOperate) {
        mList = list;
        this.onItemOperate = onItemOperate;
    }

    public BaseAdapter(I onItemOperate) {
        mList = new ArrayList<>();
        this.onItemOperate = onItemOperate;
    }

    public List<D> getData() {
        return mList;
    }

    @NonNull
    @Override
    public abstract ViewHolder<D, I> onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull ViewHolder<D, I> holder, int position) {
        holder.bindData(mList.get(position), onItemOperate);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public abstract static class ViewHolder<D, I> extends RecyclerView.ViewHolder {
        protected final Context mContext;

        public ViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
        }

        protected abstract void bindData(D item, I onItemOperate);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshAllItems(List<D> list) {
        this.mList.clear();
        this.mList.addAll(list);
        this.notifyDataSetChanged();
    }

    public void removeItem(D item) {
        int index = mList.indexOf(item);
        if (index == -1) return;
        mList.remove(index);
        this.notifyItemRemoved(index);
        this.notifyItemRangeChanged(index, getItemCount() - index);
    }

    public void refreshItem(D item) {
        int index = mList.indexOf(item);
        if (index == -1) return;
        this.notifyItemChanged(index);
    }

    public void refreshItem(D item, Object payLoad) {
        int index = mList.indexOf(item);
        if (index == -1) return;
        this.notifyItemChanged(index, payLoad);
    }

    public void insertLastItem(D item) {
        mList.add(item);
        this.notifyItemInserted(mList.size());
    }

    public void insertItem(D item, int position) {
        mList.add(position, item);
        this.notifyItemInserted(position);
    }

    public void insertItem(List<D> items, int position) {
        mList.addAll(position, items);
        this.notifyItemRangeInserted(position, items.size());
    }

    public void insertLastItems(List<D> items) {
        mList.addAll(items);
        this.notifyItemInserted(mList.size());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void clearItems() {
        mList.clear();
        this.notifyDataSetChanged();
    }
}
