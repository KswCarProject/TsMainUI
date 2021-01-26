package com.ts.bt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    private int layoutId;
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    int mSelect = -1;

    public abstract void convert(ViewHolder viewHolder, T t, int i);

    public int getSelect() {
        return this.mSelect;
    }

    public void setSelect(int mSelect2) {
        this.mSelect = mSelect2;
    }

    public CommonAdapter(Context context, List<T> datas, int layoutId2) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.layoutId = layoutId2;
    }

    public int getCount() {
        if (this.mDatas != null) {
            return this.mDatas.size();
        }
        return 0;
    }

    public T getItem(int position) {
        return this.mDatas.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(this.mContext, convertView, parent, this.layoutId, position);
        convert(holder, getItem(position), position);
        return holder.getConvertView();
    }

    public void setDatas(List<T> list) {
        if (this.mDatas == null) {
            this.mDatas = list;
        } else if (list != null) {
            ArrayList temp = new ArrayList();
            temp.addAll(list);
            this.mDatas.clear();
            this.mDatas.addAll(temp);
        } else {
            this.mDatas.clear();
        }
        notifyDataSetChanged();
    }

    public void remove(int i) {
        if (this.mDatas != null && this.mDatas.size() > i && i > -1) {
            this.mDatas.remove(i);
            notifyDataSetChanged();
        }
    }

    public void addDatas(List<T> list) {
        if (list != null) {
            ArrayList temp = new ArrayList();
            temp.addAll(list);
            if (this.mDatas != null) {
                this.mDatas.addAll(temp);
            } else {
                this.mDatas = temp;
            }
            notifyDataSetChanged();
        }
    }

    public List<T> getDatas() {
        return this.mDatas;
    }
}
