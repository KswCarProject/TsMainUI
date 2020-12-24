package com.qiner.appinfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.MainUI.R;
import java.util.List;

public class AppListAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    List<AppInfo> mList;
    SharedPreferences mSharedPreferences;

    public AppListAdapter(Context context, List<AppInfo> list) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mList = list;
    }

    public int getCount() {
        return this.mList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder(this, (ViewHolder) null);
            convertView = this.mLayoutInflater.inflate(R.layout.applist_item, (ViewGroup) null);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.imgApp);
            holder.mTextView = (TextView) convertView.findViewById(R.id.tvAppName);
            holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.btn_check);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mImageView.setBackground(this.mList.get(position).getAppIcon());
        holder.mTextView.setText(this.mList.get(position).getPkgName());
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int ischecked;
                if (isChecked) {
                    ischecked = 1;
                } else {
                    ischecked = 0;
                }
                AppListUtil.getInstance().updateAppList(AppListAdapter.this.mList.get(position).getPkgName(), ischecked);
            }
        });
        if (AppListUtil.getInstance().isChecked(this.mList.get(position).getPkgName()) == 0) {
            holder.mCheckBox.setChecked(false);
        } else {
            holder.mCheckBox.setChecked(true);
        }
        return convertView;
    }

    private class ViewHolder {
        CheckBox mCheckBox;
        ImageView mImageView;
        TextView mTextView;

        private ViewHolder() {
        }

        /* synthetic */ ViewHolder(AppListAdapter appListAdapter, ViewHolder viewHolder) {
            this();
        }
    }
}
