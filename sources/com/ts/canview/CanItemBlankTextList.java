package com.ts.canview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ts.MainUI.R;

public class CanItemBlankTextList {
    private TextView mVal;
    private View mView;

    public CanItemBlankTextList(Context context, int resId) {
        Create(context, resId);
        if (resId != 0) {
            this.mVal.setText(resId);
        }
    }

    public CanItemBlankTextList(Context context, String strVal) {
        Create(context, strVal);
        this.mVal.setText(strVal);
    }

    public void Create(Context context, String strVal) {
        Init(context);
    }

    public void SetBold(boolean bold) {
        this.mVal.getPaint().setFakeBoldText(bold);
    }

    public void Create(Context context, int resId) {
        Init(context);
    }

    public View GetView() {
        return this.mView;
    }

    private void Init(Context context) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.can_item_blanktext_list, (ViewGroup) null);
        this.mVal = (TextView) this.mView.findViewById(R.id.text);
        SetBold(true);
    }

    public void SetVal(int resId) {
        this.mVal.setText(resId);
    }

    public void SetVal(String val) {
        this.mVal.setText(val);
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
}
