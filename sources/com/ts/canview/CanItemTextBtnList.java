package com.ts.canview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.ts.MainUI.R;

public class CanItemTextBtnList {
    private Button mBtn;
    private ImageView mIco;
    private View mView;

    public CanItemTextBtnList() {
    }

    public CanItemTextBtnList(Context context, int text) {
        Create(context, text);
    }

    public CanItemTextBtnList(Context context, String text) {
        Create(context, text);
    }

    public void Create(Context context, int text) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.can_item_textbtn_list, (ViewGroup) null);
        this.mBtn = (Button) this.mView.findViewById(R.id.btn);
        this.mIco = (ImageView) this.mView.findViewById(R.id.arrow);
        if (text != 0) {
            this.mBtn.setText(text);
        }
    }

    public void Create(Context context, String text) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.can_item_textbtn_list, (ViewGroup) null);
        this.mBtn = (Button) this.mView.findViewById(R.id.btn);
        this.mIco = (ImageView) this.mView.findViewById(R.id.arrow);
        if (!TextUtils.isEmpty(text)) {
            this.mBtn.setText(text);
        }
    }

    public void ShowArrow(boolean show) {
        if (show) {
            this.mIco.setVisibility(0);
        } else {
            this.mIco.setVisibility(4);
        }
    }

    public View GetView() {
        return this.mView;
    }

    public void SetIdClickListener(View.OnClickListener l, int Id) {
        GetView().setTag(Integer.valueOf(Id));
        GetView().setOnClickListener(l);
    }

    public void SetVal(int resId) {
        this.mBtn.setText(resId);
    }

    public void SetVal(String val) {
        this.mBtn.setText(val);
    }

    public void ShowGone(int show) {
        ShowGone(show != 0);
    }

    public void ShowGone(boolean show) {
        if (show) {
            this.mView.setVisibility(0);
        } else {
            this.mView.setVisibility(8);
        }
    }

    public void SetColor(int color) {
        this.mBtn.setTextColor(color);
    }
}
