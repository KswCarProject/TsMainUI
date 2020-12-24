package com.ts.canview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.MainUI.R;

public class CanItemIcoVal {
    private Button mBtn;
    private ImageView mIco;
    private TextView mVal;
    private View mView;

    public CanItemIcoVal() {
    }

    public CanItemIcoVal(Context context, int ico, int text) {
        Create(context, ico, text);
    }

    public void Create(Context context, int ico, int text) {
        Init(context, ico);
        if (text != 0) {
            this.mBtn.setText(text);
        }
    }

    private void Init(Context context, int ico) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.can_item_ico_val, (ViewGroup) null);
        this.mBtn = (Button) this.mView.findViewById(R.id.btn);
        this.mIco = (ImageView) this.mView.findViewById(R.id.ico);
        this.mIco.setImageResource(ico);
        this.mVal = (TextView) this.mView.findViewById(R.id.val);
    }

    public View GetView() {
        return this.mView;
    }

    public Button GetBtn() {
        return this.mBtn;
    }

    public void SetIdClickListener(View.OnClickListener l, int Id) {
        GetView().setTag(Integer.valueOf(Id));
        GetView().setOnClickListener(l);
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

    public void SetVal(int resId) {
        if (resId > 0) {
            this.mVal.setText(resId);
        }
    }

    public void SetVal(String str) {
        this.mVal.setText(str);
    }
}
