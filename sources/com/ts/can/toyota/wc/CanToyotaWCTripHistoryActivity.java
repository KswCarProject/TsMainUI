package com.ts.can.toyota.wc;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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
import com.yyw.ts70xhw.KeyDef;

public class CanToyotaWCTripHistoryActivity extends CanToyotaWCBaseActivity implements UserCallBack, View.OnClickListener {
    private static final int ID_CLEAR = 1281;
    private static final int ID_PERMIN = 1280;
    private static final int ID_UPDATE = 1282;
    public static final String TAG = "CanToyotaTripHistoryActivity";
    private ParamButton mBtnClear;
    private ParamButton mBtnPerMin;
    private ParamButton mBtnUpdate;
    private TextView mDW;
    protected RelativeLayoutManager mManager;
    private TextView[] mProgText;
    private CanVerticalBar[] mTrip;
    private CanDataInfo.ToyotaWcYhPage1 mTripData = new CanDataInfo.ToyotaWcYhPage1();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.AddImage(45, 67, R.drawable.fuel_consumption_line02);
        this.mTrip = new CanVerticalBar[6];
        for (int i = 0; i < this.mTrip.length; i++) {
            this.mTrip[i] = new CanVerticalBar((Context) this, R.drawable.fuel_consumption_pillars03);
            this.mTrip[i].setMinMax(0.0f, 100.0f);
            if (i == 0) {
                this.mManager.AddViewWrapContent(this.mTrip[i], 819 - (i * 152), 71);
            } else {
                this.mManager.AddViewWrapContent(this.mTrip[i], 819 - ((6 - i) * 152), 71);
            }
        }
        TextView tv = this.mManager.AddText(760, 395, 100, 30);
        SetProgText(tv);
        tv.setText(R.string.can_begin);
        TextView tv2 = this.mManager.AddText(912, 395, 100, 30);
        SetProgText(tv2);
        tv2.setText(R.string.can_current);
        this.mProgText = new TextView[4];
        for (int i2 = 0; i2 < this.mProgText.length; i2++) {
            this.mProgText[i2] = this.mManager.AddText(966, 364 - (i2 * 104), 58, 30);
            SetProgText(this.mProgText[i2]);
            this.mProgText[i2].setText(new StringBuilder(String.valueOf(i2 * 10)).toString());
        }
        this.mDW = this.mManager.AddText(KeyDef.SKEY_CALLDN_2, 20, 133, 30);
        SetProgText(this.mDW);
        this.mDW.setText("MPG");
        if (MainSet.GetScreenType() == 5) {
            this.mBtnPerMin = this.mManager.AddButton(1044, 54);
            this.mBtnClear = this.mManager.AddButton(1044, 174);
            this.mBtnUpdate = this.mManager.AddButton(1044, 293);
        } else {
            this.mBtnPerMin = this.mManager.AddButton(44, 455);
            this.mBtnClear = this.mManager.AddButton(344, 455);
            this.mBtnUpdate = this.mManager.AddButton(CanCameraUI.BTN_LANDWIND_2D_RIGHT, 455);
        }
        SetCommBtn(this.mBtnPerMin, R.string.can_yh_per_min);
        this.mBtnPerMin.setTag(1280);
        this.mBtnPerMin.setOnClickListener(this);
        SetCommBtn(this.mBtnClear, R.string.can_clear);
        this.mBtnClear.setTag(1281);
        this.mBtnClear.setOnClickListener(this);
        SetCommBtn(this.mBtnUpdate, R.string.can_update);
        this.mBtnUpdate.setTag(1282);
        this.mBtnUpdate.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d("CanToyotaTripHistoryActivity", "onResume");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanToyotaTripHistoryActivity", "onPause");
    }

    private void SetProgText(TextView tv) {
        tv.setTextSize(0, 25.0f);
        tv.setTextColor(-1);
        tv.setGravity(17);
    }

    private void ResetData(boolean check) {
        int max;
        CanJni.ToyotaWcGetYhPage1(this.mTripData);
        if (!i2b(this.mTripData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTripData.Update)) {
            this.mTripData.Update = 0;
            this.mDW.setText(GetDWStr(this.mTripData.Yhdw));
            int base = 10;
            if (this.mTripData.Yhdw == 0) {
                max = 600;
                base = 20;
            } else {
                max = 300;
            }
            for (int i = 1; i < this.mProgText.length; i++) {
                this.mProgText[i].setText(new StringBuilder(String.valueOf(base * i)).toString());
            }
            for (int i2 = 0; i2 < 6; i2++) {
                this.mTrip[i2].setMinMax(0.0f, (float) max);
                switch (i2) {
                    case 0:
                        this.mTrip[i2].setCurPos(this.mTripData.Dqxcyh > max ? max : this.mTripData.Dqxcyh);
                        break;
                    case 1:
                        this.mTrip[i2].setCurPos(this.mTripData.YhTrip1 > max ? max : this.mTripData.YhTrip1);
                        break;
                    case 2:
                        this.mTrip[i2].setCurPos(this.mTripData.YhTrip2 > max ? max : this.mTripData.YhTrip2);
                        break;
                    case 3:
                        this.mTrip[i2].setCurPos(this.mTripData.YhTrip3 > max ? max : this.mTripData.YhTrip3);
                        break;
                    case 4:
                        this.mTrip[i2].setCurPos(this.mTripData.YhTrip4 > max ? max : this.mTripData.YhTrip4);
                        break;
                    case 5:
                        this.mTrip[i2].setCurPos(this.mTripData.YhTrip5 > max ? max : this.mTripData.YhTrip5);
                        break;
                }
            }
        }
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

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1280:
                enterSubWin(CanToyotaWCTripPerMinActivity.class);
                return;
            case 1281:
                CarSet(4, 1, 1);
                return;
            case 1282:
                CarSet(4, 2, 2);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
