package com.ts.can.bmw.mini;

import android.content.res.ColorStateList;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;

public class CanBMWMiniStatusRPAActivity extends CanBMWMiniBaseActivity {
    private ImageView[] mErrorIcons = new ImageView[4];
    private ImageView[] mErrorSans = new ImageView[4];
    private RelativeLayout mManager;
    private TextView mTvResetBtn;
    private TextView mTvRpaStatus;

    /* access modifiers changed from: protected */
    public void SetLayoutContainer() {
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = (RelativeLayout) findViewById(R.id.can_comm_layout);
        this.mManager.setBackgroundResource(R.drawable.can_mini_bg);
    }

    /* access modifiers changed from: protected */
    public void AddItemView() {
        addTextView(30, 300, R.string.can_rpa_reset);
        addTextView(30, 350, R.string.can_rpa_reset_notice);
        addImageView(Can.CAN_JAC_REFINE_OD, 100, R.drawable.can_mini_car_normal);
        for (int i = 0; i < this.mErrorIcons.length; i++) {
            this.mErrorIcons[i] = addImageView(((i % 2) * 210) + 80, ((i / 2) * 55) + 110, R.drawable.can_mini_hook);
            this.mErrorIcons[i].setVisibility(8);
            this.mErrorSans[i] = addImageView(((i % 2) * 103) + Can.CAN_JAC_REFINE_OD, ((i / 2) * 75) + 112, i % 2 == 0 ? R.drawable.can_mini_car_left : R.drawable.can_mini_car_right);
            this.mErrorSans[i].setVisibility(8);
        }
        this.mTvRpaStatus = addTextView(500, Can.CAN_JAC_REFINE_OD, 0);
        this.mTvResetBtn = addTextView(800, 450, R.string.can_rpa_start_reset);
        this.mTvResetBtn.setTextColor(getColorStateList());
        this.mTvResetBtn.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetMiniRpa();
        if (!i2b(this.mRpa.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mRpa.Update)) {
            this.mRpa.Update = 0;
            setRpaStatus(this.mRpa.Avalid, this.mRpa.Status);
            setResetStatus(this.mRpa.ResetAvalid);
            setCarStatus(this.mRpa.Avalid, this.mRpa.LFSta, this.mRpa.LRSta, this.mRpa.RFSta, this.mRpa.RRSta);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        Query(73);
    }

    private void setCarStatus(int valid, int lf, int lr, int rf, int rr) {
        int[] iArr = new int[4];
        int[] status = i2b(valid) ? new int[]{lf, rf, lr, rr} : new int[4];
        for (int i = 0; i < this.mErrorIcons.length; i++) {
            if (status[i] == 1) {
                this.mErrorIcons[i].setImageResource(R.drawable.can_mini_hook);
                this.mErrorIcons[i].setVisibility(0);
                this.mErrorSans[i].setVisibility(8);
            } else {
                this.mErrorIcons[i].setImageResource(R.drawable.can_mini_warning);
                this.mErrorIcons[i].setVisibility(0);
                this.mErrorSans[i].setVisibility(0);
            }
        }
    }

    private void setRpaStatus(int valid, int status) {
        String str;
        String str2 = getString(R.string.can_rpa);
        if (!i2b(valid)) {
            str = String.valueOf(str2) + getString(R.string.can_rpa_invalide);
        } else if (status == 0) {
            str = String.valueOf(str2) + getString(R.string.can_rpa_not_start);
        } else if (status == 1) {
            str = String.valueOf(str2) + getString(R.string.can_rpa_starting);
        } else if (status == 2) {
            str = String.valueOf(str2) + getString(R.string.can_rpa_started);
        } else if (status == 3) {
            str = String.valueOf(str2) + getString(R.string.can_rpa_resetting);
        } else {
            str = String.valueOf(str2) + getString(R.string.can_rpa_tybz);
        }
        this.mTvRpaStatus.setText(str);
    }

    private void setResetStatus(int reset) {
        if (i2b(reset)) {
            this.mTvResetBtn.setClickable(true);
            this.mTvResetBtn.setEnabled(true);
            return;
        }
        this.mTvResetBtn.setClickable(false);
        this.mTvResetBtn.setEnabled(false);
    }

    private ColorStateList getColorStateList() {
        return new ColorStateList(new int[][]{new int[]{16842910, 16842919}, new int[]{16842910}, new int[0]}, new int[]{SupportMenu.CATEGORY_MASK, -1, -7829368});
    }

    private TextView addTextView(int x, int y, int resId) {
        TextView view = new TextView(this);
        if (resId != 0) {
            view.setText(resId);
        }
        view.setTextColor(-1);
        view.setTextSize(0, 30.0f);
        view.setPadding(0, 0, 30, 0);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(-2, -2);
        param.leftMargin = x;
        param.topMargin = y;
        this.mManager.addView(view, param);
        return view;
    }

    private ImageView addImageView(int x, int y, int resId) {
        ImageView view = new ImageView(this);
        view.setImageResource(resId);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(-2, -2);
        param.leftMargin = x;
        param.topMargin = y;
        this.mManager.addView(view, param);
        return view;
    }

    public void onClick(View v) {
        CarSet(145, 1);
    }
}
