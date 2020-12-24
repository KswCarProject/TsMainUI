package com.ts.canview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.ts.MainUI.R;
import com.ts.other.CustomImgView;

public class CanItemSwitchList {
    private Button mBtn;
    private CustomImgView mIco;
    private ImageView mIcoR;
    private View mView;

    public CanItemSwitchList() {
    }

    public CanItemSwitchList(Context context, int text) {
        Create(context, text);
    }

    public void Create(Context context, int text) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.can_item_switch_list, (ViewGroup) null);
        this.mBtn = (Button) this.mView.findViewById(R.id.btn);
        if (text != 0) {
            this.mBtn.setText(text);
        }
        this.mIco = (CustomImgView) this.mView.findViewById(R.id.ico);
        this.mIco.setStateDrawable(R.drawable.can_golf_check_up, R.drawable.can_golf_check_dn);
    }

    public CanItemSwitchList(Context context, int ico, int text) {
        Create(context, ico, text);
    }

    public void Create(Context context, int ico, int text) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.can_item_switch_list, (ViewGroup) null);
        this.mBtn = (Button) this.mView.findViewById(R.id.btn);
        if (text != 0) {
            this.mBtn.setText(text);
            this.mBtn.setPadding(90, 0, 0, 0);
        }
        this.mIco = (CustomImgView) this.mView.findViewById(R.id.ico);
        this.mIco.setStateDrawable(R.drawable.can_golf_check_up, R.drawable.can_golf_check_dn);
        this.mIcoR = (ImageView) this.mView.findViewById(R.id.r_ico);
        this.mIcoR.setImageResource(ico);
    }

    public View GetView() {
        return this.mView;
    }

    public Button GetBtn() {
        return this.mBtn;
    }

    public void SetCheck(boolean check) {
        this.mIco.setSelected(check);
    }

    public void SetCheck(int check) {
        if (check == 0) {
            this.mIco.setSelected(false);
        } else {
            this.mIco.setSelected(true);
        }
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
}
