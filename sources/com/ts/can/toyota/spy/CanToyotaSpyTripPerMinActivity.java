package com.ts.can.toyota.spy;

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
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.canview.CanVerticalBar;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanToyotaSpyTripPerMinActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener {
    private static final int ID_CLEAR = 1281;
    private static final int ID_HISTORY = 1280;
    public static final String TAG = "CanToyotaSpyTripPerMinActivity";
    private CanVerticalBar[] m15Min = new CanVerticalBar[15];
    private CanDataInfo.ToyotaConsump15Min m15MinData = new CanDataInfo.ToyotaConsump15Min();
    private ParamButton mBtnClear;
    private ParamButton mBtnHistory;
    private CanVerticalBar mCurrent;
    private CanDataInfo.ToyotaConsumpCurrent mCurrentData = new CanDataInfo.ToyotaConsumpCurrent();
    private TextView mDW;
    private TextView mDriveTime;
    protected RelativeLayoutManager mManager;
    private CanDataInfo.ToyotaConsumpPerMin mPerMinData = new CanDataInfo.ToyotaConsumpPerMin();
    private TextView[] mProgText;
    private TextView mRange;
    private TextView mSpeed;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        String[] strLtArray = getResources().getStringArray(R.array.can_cam_yh_title);
        TextView[] tvLtVal = new TextView[3];
        for (int i = 0; i < 3; i++) {
            TextView tv = this.mManager.AddText(15, (i * 103) + 61);
            SetLeftText(tv);
            tv.setText(strLtArray[i]);
            tvLtVal[i] = this.mManager.AddText(15, (i * 103) + 105, 200, 40);
            SetLeftText(tvLtVal[i]);
        }
        this.mSpeed = tvLtVal[0];
        this.mDriveTime = tvLtVal[1];
        this.mRange = tvLtVal[2];
        this.mDW = this.mManager.AddText(860, 20, 130, 30);
        SetProgText(this.mDW);
        this.mDW.setText("L/100km");
        this.mProgText = new TextView[4];
        TextView[] tvBot = new TextView[4];
        for (int i2 = 0; i2 < this.mProgText.length; i2++) {
            tvBot[i2] = this.mManager.AddText((i2 * 176) + 177, 388, 100, 30);
            SetProgText(tvBot[i2]);
            tvBot[i2].setText(new StringBuilder(String.valueOf((3 - i2) * 5)).toString());
            this.mProgText[i2] = this.mManager.AddText(760, 357 - (i2 * 104), 100, 30);
            SetProgText(this.mProgText[i2]);
            this.mProgText[i2].setText(new StringBuilder(String.valueOf(i2 * 10)).toString());
        }
        tvBot[0].setText(R.string.can_15min);
        TextView tv2 = this.mManager.AddText(860, 388, 130, 30);
        SetProgText(tv2);
        tv2.setText(R.string.can_current);
        this.mManager.AddImage(Can.CAN_BENZ_SMART_OD, 61, R.drawable.fuel_consumption_line01);
        this.mCurrent = new CanVerticalBar((Context) this, R.drawable.fuel_consumption_pillars02);
        this.mManager.AddViewWrapContent(this.mCurrent, 879, 65);
        this.mCurrent.setMinMax(0.0f, 100.0f);
        for (int i3 = 0; i3 < this.m15Min.length; i3++) {
            this.m15Min[i3] = new CanVerticalBar((Context) this, R.drawable.fuel_consumption_pillars01);
            this.m15Min[i3].setMinMax(0.0f, 100.0f);
            this.mManager.AddViewWrapContent(this.m15Min[i3], ((i3 % 5) * 33) + Can.CAN_LIEBAO_WC + ((i3 / 5) * 177), 65);
        }
        this.mBtnHistory = this.mManager.AddButton(44, 455);
        SetCommBtn(this.mBtnHistory, R.string.can_yh_history);
        this.mBtnHistory.setTag(1280);
        this.mBtnHistory.setOnClickListener(this);
        this.mBtnClear = this.mManager.AddButton(344, 455);
        SetCommBtn(this.mBtnClear, R.string.can_clear);
        this.mBtnClear.setTag(1281);
        this.mBtnClear.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        CanJni.ToyotaSpySetCurPage(1);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        CanJni.ToyotaSpySetCurPage(0);
    }

    private void SetLeftText(TextView tv) {
        tv.setTextSize(0, 30.0f);
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
                return "";
        }
    }

    private void ResetData(boolean check) {
        int max;
        int max2;
        CanJni.ToyotaGetTripPerMin(this.mPerMinData, this.mCurrentData, this.m15MinData);
        if (i2b(this.mPerMinData.UpdateOnce) && (!check || i2b(this.mPerMinData.Update))) {
            this.mPerMinData.Update = 0;
            if (this.mPerMinData.DW != 0) {
                switch (this.mPerMinData.DW) {
                    case 1:
                        if (this.mPerMinData.AverageSpeed > 9999) {
                            this.mSpeed.setText("--");
                        } else {
                            this.mSpeed.setText(String.format("%.1fMile/h", new Object[]{Double.valueOf(((double) this.mPerMinData.AverageSpeed) * 0.1d)}));
                        }
                        if (this.mPerMinData.CruisingRange <= 9999) {
                            this.mRange.setText(String.format("%dMile", new Object[]{Integer.valueOf(this.mPerMinData.CruisingRange)}));
                            break;
                        } else {
                            this.mRange.setText("--");
                            break;
                        }
                    case 2:
                        if (this.mPerMinData.AverageSpeed > 9999) {
                            this.mSpeed.setText("--");
                        } else {
                            this.mSpeed.setText(String.format("%.1fKm/h", new Object[]{Double.valueOf(((double) this.mPerMinData.AverageSpeed) * 0.1d)}));
                        }
                        if (this.mPerMinData.CruisingRange <= 9999) {
                            this.mRange.setText(String.format("%dKm", new Object[]{Integer.valueOf(this.mPerMinData.CruisingRange)}));
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
            } else {
                this.mSpeed.setText("--");
                this.mRange.setText("--");
            }
            if (this.mPerMinData.ElapsedTime > 5999) {
                this.mDriveTime.setText("--");
            } else {
                this.mDriveTime.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mPerMinData.ElapsedTime / 60), Integer.valueOf(this.mPerMinData.ElapsedTime % 60)}));
            }
        }
        if (i2b(this.mCurrentData.UpdateOnce) && (!check || i2b(this.mCurrentData.Update))) {
            this.mCurrentData.Update = 0;
            this.mDW.setText(GetDWStr(this.mCurrentData.DW));
            int base = 10;
            if (this.mCurrentData.DW == 0) {
                max2 = CanCameraUI.BTN_GOLF_WC_MODE1;
                base = 20;
            } else {
                max2 = 300;
            }
            for (int i = 1; i < this.mProgText.length; i++) {
                this.mProgText[i].setText(new StringBuilder(String.valueOf(base * i)).toString());
            }
            this.mCurrent.setMinMax(0.0f, (float) max2);
            this.mCurrent.setCurPos(this.mCurrentData.Current);
        }
        if (!i2b(this.m15MinData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m15MinData.Update)) {
            this.m15MinData.Update = 0;
            if (this.m15MinData.DW == 0) {
                max = CanCameraUI.BTN_GOLF_WC_MODE1;
            } else {
                max = 300;
            }
            for (int i2 = 0; i2 < 15; i2++) {
                this.m15Min[14 - i2].setMinMax(0.0f, (float) max);
                this.m15Min[14 - i2].setCurPos(this.m15MinData.Min[i2]);
            }
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1280:
                enterSubWin(CanToyotaSpyTripHistoryActivity.class);
                return;
            case 1281:
                CanJni.ToyotaSpyCarSet(10, 0);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
