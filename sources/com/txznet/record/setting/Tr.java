package com.txznet.record.setting;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.txznet.sdk.TXZConfigManager;
import com.txznet.txz.comm.R;

/* compiled from: Proguard */
public class Tr extends BaseAdapter {

    /* renamed from: T  reason: collision with root package name */
    Context f685T = null;
    public int Tr = -1;
    private boolean Ty = true;

    public Tr(Context context) {
        this.f685T = context;
    }

    public int getCount() {
        return ChangeCommandActivity.Tr.size();
    }

    public Object getItem(int position) {
        return ChangeCommandActivity.Tr.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public void T(boolean editable) {
        this.Ty = editable;
        notifyDataSetChanged();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        T holder;
        int i;
        Drawable drawable;
        int i2 = 0;
        if (convertView == null) {
            holder = new T();
            convertView = LayoutInflater.from(this.f685T).inflate(R.layout.layout_editcommand_item, parent, false);
            holder.f690T = (TextView) convertView.findViewById(R.id.txt_command);
            holder.Tr = (ImageView) convertView.findViewById(R.id.imgbnt_openOperate);
            holder.Ty = (LinearLayout) convertView.findViewById(R.id.layout_operate);
            holder.Tn = (FrameLayout) convertView.findViewById(R.id.bnt_edit);
            holder.T9 = (FrameLayout) convertView.findViewById(R.id.bnt_delete);
            convertView.setTag(holder);
        } else {
            holder = (T) convertView.getTag();
        }
        ImageView imageView = holder.Tr;
        if (this.Ty) {
            i = 0;
        } else {
            i = 8;
        }
        imageView.setVisibility(i);
        holder.Tr.setClickable(this.Ty);
        holder.f690T.setClickable(this.Ty);
        holder.f690T.setText(ChangeCommandActivity.Tr.get(position));
        LinearLayout linearLayout = holder.Ty;
        if (this.Tr != position) {
            i2 = 8;
        }
        linearLayout.setVisibility(i2);
        ImageView imageView2 = holder.Tr;
        if (this.Tr == position) {
            drawable = this.f685T.getResources().getDrawable(R.drawable.txz_item_up);
        } else {
            drawable = this.f685T.getResources().getDrawable(R.drawable.txz_item_down);
        }
        imageView2.setImageDrawable(drawable);
        holder.f690T.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Tr.this.T(position);
            }
        });
        holder.Tr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Tr.this.T(position);
            }
        });
        holder.Tn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ChangeCommandActivity.T(ChangeCommandActivity.Tr.get(position), Tr.this.f685T, "修改唤醒词", position);
            }
        });
        holder.T9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                synchronized (v) {
                    if (ChangeCommandActivity.Tr.size() <= 1) {
                        Toast.makeText(Tr.this.f685T, "亲，唤醒词全部删除，不能唤醒哦", 1).show();
                    } else {
                        ChangeCommandActivity.Tr.remove(position);
                        Tr.this.T(-1);
                    }
                }
                TXZConfigManager.getInstance().setWakeupKeywordsNew((String[]) ChangeCommandActivity.Tr.toArray(new String[ChangeCommandActivity.Tr.size()]));
            }
        });
        return convertView;
    }

    /* access modifiers changed from: protected */
    public void T(int position) {
        if (this.Ty) {
            if (this.Tr == position) {
                position = -1;
            }
            this.Tr = position;
            notifyDataSetChanged();
        }
    }

    /* compiled from: Proguard */
    class T {

        /* renamed from: T  reason: collision with root package name */
        TextView f690T;
        FrameLayout T9;
        FrameLayout Tn;
        ImageView Tr;
        LinearLayout Ty;

        T() {
        }
    }
}
