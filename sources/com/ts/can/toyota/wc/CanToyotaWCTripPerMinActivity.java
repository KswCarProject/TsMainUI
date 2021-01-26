package com.ts.can.toyota.wc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCameraUI;
import com.ts.canview.CanVerticalBar;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;

public class CanToyotaWCTripPerMinActivity extends CanToyotaWCBaseActivity implements UserCallBack, View.OnClickListener {
    private static final int ID_CLEAR = 1281;
    private static final int ID_HISTORY = 1280;
    public static final String TAG = "CanToyotaTripPerMinActivity";
    private CanVerticalBar[] m30Min = new CanVerticalBar[30];
    private ParamButton mBtnClear;
    private ParamButton mBtnHistory;
    private CanVerticalBar mCurrent;
    private TextView mDW;
    private TextView mDriveTime;
    protected RelativeLayoutManager mManager;
    private TextView mPastDW;
    private TextView mPjyh;
    private TextView[] mProgText;
    private TextView mRange;
    private TextView mSpeed;
    private CanDataInfo.ToyotaWcYhPage0 mYhPage0 = new CanDataInfo.ToyotaWcYhPage0();
    private CanDataInfo.ToyotaWcYhPage1 mYhPage1 = new CanDataInfo.ToyotaWcYhPage1();
    private CanDataInfo.ToyotaWcYhPage2 mYhPage2 = new CanDataInfo.ToyotaWcYhPage2();
    private TextView mZjyh;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        String[] strLtArray = getResources().getStringArray(R.array.can_cam_yh_title);
        TextView[] tvLtVal = new TextView[5];
        for (int i = 0; i < 5; i++) {
            TextView tv = this.mManager.AddText(15, (i * 80) + 30);
            SetLeftText(tv);
            tv.setText(strLtArray[i]);
            tvLtVal[i] = this.mManager.AddText(15, (i * 81) + 67, 200, 35);
            SetLeftText(tvLtVal[i]);
        }
        this.mSpeed = tvLtVal[0];
        this.mDriveTime = tvLtVal[1];
        this.mRange = tvLtVal[2];
        this.mPjyh = tvLtVal[3];
        this.mZjyh = tvLtVal[4];
        this.mDW = this.mManager.AddText(860, 20, 130, 30);
        SetProgText(this.mDW);
        this.mDW.setText("L/100km");
        this.mPastDW = this.mManager.AddText(CanCameraUI.BTN_CCH9_MODE1, 20, 130, 30);
        SetProgText(this.mPastDW);
        this.mPastDW.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mProgText = new TextView[4];
        TextView[] tvBot = new TextView[4];
        for (int i2 = 0; i2 < this.mProgText.length; i2++) {
            tvBot[i2] = this.mManager.AddText((i2 * 176) + 177, 388, 100, 30);
            SetProgText(tvBot[i2]);
            tvBot[i2].setText(new StringBuilder(String.valueOf((3 - i2) * 10)).toString());
            this.mProgText[i2] = this.mManager.AddText(760, 357 - (i2 * 104), 100, 30);
            SetProgText(this.mProgText[i2]);
            this.mProgText[i2].setText(new StringBuilder(String.valueOf(i2 * 10)).toString());
        }
        tvBot[0].setText(R.string.can_30min);
        TextView tv2 = this.mManager.AddText(860, 388, 130, 30);
        SetProgText(tv2);
        tv2.setText(R.string.can_current);
        this.mManager.AddImage(Can.CAN_BENZ_SMART_OD, 61, R.drawable.fuel_consumption_line01);
        this.mCurrent = new CanVerticalBar((Context) this, R.drawable.fuel_consumption_pillars02);
        this.mManager.AddViewWrapContent(this.mCurrent, 879, 65);
        this.mCurrent.setMinMax(0.0f, 100.0f);
        for (int i3 = 0; i3 < this.m30Min.length; i3++) {
            this.m30Min[i3] = new CanVerticalBar((Context) this, R.drawable.fuel_consumption_pillars04);
            this.m30Min[i3].setMinMax(0.0f, 100.0f);
            this.mManager.AddView(this.m30Min[i3], ((i3 % 10) * 16) + Can.CAN_LIEBAO_WC + ((i3 / 10) * 177), 65, 15, 308);
        }
        if (MainSet.GetScreenType() == 5) {
            this.mBtnHistory = this.mManager.AddButton(1044, 54);
            this.mBtnClear = this.mManager.AddButton(1044, 174);
        } else {
            this.mBtnHistory = this.mManager.AddButton(44, 455);
            this.mBtnClear = this.mManager.AddButton(344, 455);
        }
        SetCommBtn(this.mBtnHistory, R.string.can_yh_history);
        this.mBtnHistory.setTag(1280);
        this.mBtnHistory.setOnClickListener(this);
        SetCommBtn(this.mBtnClear, R.string.can_clear);
        this.mBtnClear.setTag(1281);
        this.mBtnClear.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void SetLeftText(TextView tv) {
        tv.setTextSize(0, 27.0f);
        tv.setTextColor(-1);
        tv.setGravity(3);
    }

    private void SetProgText(TextView tv) {
        tv.setTextSize(0, 25.0f);
        tv.setTextColor(-1);
        tv.setGravity(17);
    }

    private String GetDWStr(int dw) {
        switch (dw) {
            case 0:
                return "MPG";
            case 1:
                return "KM/L";
            case 2:
                return "L/100KM";
            default:
                return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    private void ResetData(boolean check) {
        int max;
        int i;
        int max2;
        int i2;
        CanJni.ToyotaWcGetYhPage0(this.mYhPage0);
        CanJni.ToyotaWcGetYhPage1(this.mYhPage1);
        CanJni.ToyotaWcGetYhPage2(this.mYhPage2);
        if (i2b(this.mYhPage0.UpdateOnce) && (!check || i2b(this.mYhPage0.Update))) {
            this.mYhPage0.Update = 0;
            switch (this.mYhPage0.Lcdw) {
                case 0:
                    if (this.mYhPage0.Pjcs > 9999) {
                        this.mSpeed.setText("--");
                    } else {
                        this.mSpeed.setText(String.format("%.1fKm/h", new Object[]{Float.valueOf(1.0f * ((float) this.mYhPage0.Pjcs))}));
                    }
                    if (this.mYhPage0.Xhlc <= 9999) {
                        this.mRange.setText(String.format("%dKm", new Object[]{Integer.valueOf(this.mYhPage0.Xhlc)}));
                        break;
                    } else {
                        this.mRange.setText("--");
                        break;
                    }
                case 1:
                    if (this.mYhPage0.Pjcs > 9999) {
                        this.mSpeed.setText("--");
                    } else {
                        this.mSpeed.setText(String.format("%.1fKm/h", new Object[]{Float.valueOf(1.0f * ((float) this.mYhPage0.Pjcs))}));
                    }
                    if (this.mYhPage0.Xhlc <= 9999) {
                        this.mRange.setText(String.format("%dMile", new Object[]{Integer.valueOf(this.mYhPage0.Xhlc)}));
                        break;
                    } else {
                        this.mRange.setText("--");
                        break;
                    }
                default:
                    this.mSpeed.setText("--");
                    this.mRange.setText("--");
                    break;
            }
            if (this.mYhPage0.Xcsj > 5999) {
                this.mDriveTime.setText("--");
            } else {
                this.mDriveTime.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mYhPage0.Xcsj / 60), Integer.valueOf(this.mYhPage0.Xcsj % 60)}));
            }
            if (this.mYhPage0.Pjyh > 9999) {
                this.mPjyh.setText("--");
            } else {
                this.mPjyh.setText(String.format("%.1f%s", new Object[]{Double.valueOf(((double) this.mYhPage0.Pjyh) * 0.1d), GetDWStr(this.mYhPage0.Yhdw)}));
            }
            if (this.mYhPage0.Zjyh > 9999) {
                this.mZjyh.setText("--");
            } else {
                this.mZjyh.setText(String.format("%.1f%s", new Object[]{Double.valueOf(((double) this.mYhPage0.Zjyh) * 0.1d), GetDWStr(this.mYhPage0.Yhdw)}));
            }
        }
        if (i2b(this.mYhPage1.UpdateOnce) && (!check || i2b(this.mYhPage1.Update))) {
            this.mYhPage1.Update = 0;
            this.mDW.setText(GetDWStr(this.mYhPage1.Yhdw));
            int base = 10;
            if (this.mYhPage1.Yhdw == 0) {
                max2 = 600;
                base = 20;
            } else {
                max2 = 300;
            }
            for (int i3 = 1; i3 < this.mProgText.length; i3++) {
                this.mProgText[i3].setText(new StringBuilder(String.valueOf(base * i3)).toString());
            }
            this.mCurrent.setMinMax(0.0f, (float) max2);
            CanVerticalBar canVerticalBar = this.mCurrent;
            if (this.mYhPage1.Dqxcyh > max2) {
                i2 = max2;
            } else {
                i2 = this.mYhPage1.Dqxcyh;
            }
            canVerticalBar.setCurPos(i2);
        }
        if (!i2b(this.mYhPage2.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mYhPage2.Update)) {
            this.mYhPage2.Update = 0;
            this.mPastDW.setText(GetDWStr(this.mYhPage2.Yhdw));
            if (this.mYhPage2.Yhdw == 0) {
                max = 600;
            } else {
                max = 300;
            }
            for (int i4 = 0; i4 < 30; i4++) {
                this.m30Min[29 - i4].setMinMax(0.0f, (float) max);
                CanVerticalBar canVerticalBar2 = this.m30Min[29 - i4];
                if (this.mYhPage2.YhData[i4] > max) {
                    i = max;
                } else {
                    i = this.mYhPage2.YhData[i4];
                }
                canVerticalBar2.setCurPos(i);
            }
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1280:
                enterSubWin(CanToyotaWCTripHistoryActivity.class);
                return;
            case 1281:
                CarSet(4, 2, 1);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
