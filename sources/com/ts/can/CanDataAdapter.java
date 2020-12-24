package com.ts.can;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ts.MainUI.R;
import java.util.ArrayList;

public class CanDataAdapter extends BaseAdapter {
    private ArrayList<String> datalist = new ArrayList<>();
    private Context mContext;
    ViewHolder vh;

    public CanDataAdapter(Context mContext2, ArrayList<String> dataList) {
        this.mContext = mContext2;
        this.datalist = dataList;
    }

    public int getCount() {
        return this.datalist.size();
    }

    public Object getItem(int position) {
        return this.datalist.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public void setData(ArrayList<String> addData) {
        for (int i = 0; i < addData.size(); i++) {
            this.datalist.add(addData.get(i));
        }
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            this.vh = new ViewHolder();
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.lv_item, (ViewGroup) null);
            this.vh.txt = (TextView) convertView.findViewById(R.id.lv_txt);
            this.vh.txt.setTextColor(-16777216);
            convertView.setTag(this.vh);
        } else {
            this.vh = (ViewHolder) convertView.getTag();
        }
        this.vh.txt.setText(this.datalist.get(position));
        return convertView;
    }

    class ViewHolder {
        /* access modifiers changed from: private */
        public TextView txt;

        ViewHolder() {
        }
    }
}
