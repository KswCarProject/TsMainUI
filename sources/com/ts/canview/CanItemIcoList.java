package com.ts.canview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.ts.MainUI.R;

public class CanItemIcoList {
    private ImageView mArrow;
    private Button mBtn;
    private ImageView mIco;
    private View mView;

    public CanItemIcoList() {
    }

    public CanItemIcoList(Context context, int ico, int text) {
        Create(context, ico, text);
    }

    public void Create(Context context, int ico, int text) {
        Init(context, ico);
        if (text != 0) {
            this.mBtn.setText(text);
        }
    }

    private void Init(Context context, int ico) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.can_item_ico_list, (ViewGroup) null);
        this.mBtn = (Button) this.mView.findViewById(R.id.btn);
        this.mIco = (ImageView) this.mView.findViewById(R.id.ico);
        this.mIco.setImageResource(ico);
        this.mArrow = (ImageView) this.mView.findViewById(R.id.arrow);
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

    public void SetIdTouchListener(View.OnTouchListener l, int Id) {
        GetView().setTag(Integer.valueOf(Id));
        GetView().setOnTouchListener(l);
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

    public void ShowArrow(int show) {
        ShowArrow(show != 0);
    }

    public void ShowArrow(boolean show) {
        if (show) {
            this.mArrow.setVisibility(0);
        } else {
            this.mArrow.setVisibility(4);
        }
    }
}
