package com.ts.can.bmw.mini;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.ts.canview.CanNumInuptDlg;
import com.txznet.sdk.TXZResourceManager;

public class CanBMWMiniServiceDetailActivity extends CanBMWMiniBaseActivity {
    private static final int ITEM_MONTH = 1;
    private static final int ITEM_SETUP = 2;
    private static final int ITEM_YEAR = 0;
    public static final String KEY_INDEX = "index";
    public static final String KEY_NUM = "num";
    public static final String KEY_STATUS = "status";
    private LinearLayout mDateLayout;
    private int[] mIconArrays = {R.drawable.can_mini_icon_oil, R.drawable.can_mini_icon_02, R.drawable.can_mini_icon_03, R.drawable.can_mini_icon_04, R.drawable.can_mini_icon_05, R.drawable.can_mini_icon_06, R.drawable.can_mini_icon_07, R.drawable.can_mini_icon_08, R.drawable.can_mini_icon_09, R.drawable.can_mini_icon_10};
    private int mItemIndex;
    private int mItemNum;
    private int mItemStatus;
    private RelativeLayout mManager;
    private String[] mNoticeArrays;
    private String[] mServiceArrays;
    private TextView mTvDistance;

    /* access modifiers changed from: protected */
    public void SetLayoutContainer() {
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = (RelativeLayout) findViewById(R.id.can_comm_layout);
        getInitData();
    }

    private void getInitData() {
        this.mNoticeArrays = getResources().getStringArray(R.array.can_service_notice_array);
        this.mServiceArrays = getResources().getStringArray(R.array.can_by_service_array);
        Bundle data = getIntent().getExtras();
        if (data != null) {
            this.mItemIndex = data.getInt(KEY_INDEX);
            this.mItemStatus = data.getInt("status");
            this.mItemNum = data.getInt(KEY_NUM);
        }
    }

    /* access modifiers changed from: protected */
    public void AddItemView() {
        addIcon(300, 120, this.mIconArrays[this.mItemNum]);
        addText(500, 160, this.mServiceArrays[this.mItemNum], -16711936);
        addText(300, Can.CAN_NISSAN_XFY, this.mNoticeArrays[this.mItemStatus], -256);
        this.mDateLayout = addDate(300, 300);
        this.mTvDistance = addText(300, 380, TXZResourceManager.STYLE_DEFAULT, -1);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetMiniService();
        if (!i2b(this.mCarService.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarService.Update)) {
            this.mCarService.Update = 0;
            setDateStatus(this.mCarService.fgDateAvalid[this.mItemIndex], this.mCarService.Year[this.mItemIndex], this.mCarService.Month[this.mItemIndex]);
            setDistanceStatus(this.mCarService.fgDistAvalid[this.mItemIndex], this.mCarService.Distance[this.mItemIndex], this.mCarService.DistanceDW[this.mItemIndex], this.mCarService.DistanceSta[this.mItemIndex]);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        Query(75);
    }

    private void setDateStatus(int valid, int year, int month) {
        if (i2b(valid)) {
            if (year < 2001 || year > 2099) {
                if (year == 2000) {
                    year = 2099;
                } else {
                    year = -1;
                }
            }
            if (month < 1 || month > 12) {
                month = -1;
            }
            setDate(year, month);
            this.mDateLayout.setVisibility(0);
            return;
        }
        this.mDateLayout.setVisibility(8);
    }

    private void setDistanceStatus(int valid, int distance, int unit, int status) {
        String text;
        if (i2b(valid)) {
            if (status == 0) {
                text = getString(R.string.can_service_distance_hs);
            } else {
                text = getString(R.string.can_service_distance_cg);
            }
            String text2 = String.valueOf(text) + " " + distance + " ";
            if (unit == 1) {
                text2 = String.valueOf(text2) + getString(R.string.can_service_distance_km);
            } else if (unit == 0) {
                text2 = String.valueOf(text2) + getString(R.string.can_service_distance_mi);
            }
            this.mTvDistance.setText(text2);
            this.mTvDistance.setVisibility(0);
            return;
        }
        this.mTvDistance.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void setDate(int year, int month) {
        TextView tvYear = (TextView) this.mDateLayout.getChildAt(1);
        if (!(tvYear == null || year == -1)) {
            tvYear.setText(String.valueOf(year));
        }
        TextView tvMonth = (TextView) this.mDateLayout.getChildAt(3);
        if (tvMonth != null && month != -1) {
            tvMonth.setText(String.valueOf(month));
        }
    }

    private int getYear() {
        TextView tvYear = (TextView) this.mDateLayout.getChildAt(1);
        if (tvYear != null) {
            return Integer.parseInt(tvYear.getText().toString().trim());
        }
        return -1;
    }

    private int getMonth() {
        TextView tvMonth = (TextView) this.mDateLayout.getChildAt(3);
        if (tvMonth != null) {
            return Integer.parseInt(tvMonth.getText().toString().trim());
        }
        return -1;
    }

    private LinearLayout addDate(int x, int y) {
        LinearLayout layout = new LinearLayout(this);
        layout.setVisibility(8);
        layout.setOrientation(0);
        layout.setPadding(5, 5, 5, 5);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        lp.leftMargin = x;
        lp.topMargin = y;
        this.mManager.addView(layout, lp);
        addWrapChild(layout, R.string.can_year);
        addChild(layout, Can.CAN_BENC_ZMYT, 60, R.drawable.btn_mini_date_bg, -256, 0);
        addWrapChild(layout, R.string.can_month);
        addChild(layout, 120, 60, R.drawable.btn_mini_date_bg, -256, 1);
        if (this.mItemNum == 3 || this.mItemNum == 4) {
            addChild(layout, Can.CAN_BENC_ZMYT, 60, R.drawable.btn_mini_service_item, -1, 2).setText(R.string.can_setup);
        }
        return layout;
    }

    private TextView addChild(LinearLayout layout, int w, int h, int bgId, int color, int tag) {
        TextView tv = addWrapChild(layout, 0);
        if (bgId != 0) {
            tv.setBackgroundResource(bgId);
        }
        tv.setTextColor(color);
        if (this.mItemNum == 3 || this.mItemNum == 4) {
            tv.setClickable(true);
            tv.setOnClickListener(this);
            tv.setTag(Integer.valueOf(tag));
        }
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tv.getLayoutParams();
        lp.width = w;
        lp.height = h;
        lp.rightMargin = 30;
        tv.setLayoutParams(lp);
        return tv;
    }

    private TextView addWrapChild(LinearLayout layout, int textId) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        lp.gravity = 16;
        lp.rightMargin = 20;
        TextView child = new TextView(this);
        child.setTextSize(0, 30.0f);
        child.setGravity(17);
        child.setTextColor(-1);
        if (textId != 0) {
            child.setText(textId);
        }
        layout.addView(child, lp);
        return child;
    }

    private TextView addText(int x, int y, String str, int color) {
        TextView tv = addText(x, y);
        tv.setText(str);
        tv.setTextColor(color);
        return tv;
    }

    private TextView addText(int x, int y) {
        TextView tv = new TextView(this);
        tv.setTextSize(0, 30.0f);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        lp.leftMargin = x;
        lp.topMargin = y;
        this.mManager.addView(tv, lp);
        return tv;
    }

    private void addIcon(int x, int y, int resId) {
        ImageView iv = new ImageView(this);
        iv.setImageResource(resId);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        lp.leftMargin = x;
        lp.topMargin = y;
        this.mManager.addView(iv, lp);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                new CanNumInuptDlg(this, new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 2001 && num <= 2099) {
                            CanBMWMiniServiceDetailActivity.this.setDate(num, -1);
                        }
                    }
                }, 4, 0);
                return;
            case 1:
                new CanNumInuptDlg(this, new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 1 && num <= 12) {
                            CanBMWMiniServiceDetailActivity.this.setDate(-1, num);
                        }
                    }
                }, 3, 1);
                return;
            case 2:
                CarSet(177, this.mItemNum + 1, getYear() / 255, (getYear() % 255) - 8, getMonth());
                return;
            default:
                return;
        }
    }
}
