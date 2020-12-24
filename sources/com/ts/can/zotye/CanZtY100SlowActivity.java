package com.ts.can.zotye;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;

public class CanZtY100SlowActivity extends CanZtY100BaseActivity implements UserCallBack {
    public static final String TAG = "CanZtY100SlowActivity";
    protected CanZtY100Item mItemCdzt;
    protected CanZtY100Item mItemDjwd;
    protected CanZtY100Item mItemScdl;
    protected CanZtY100Item mItemScdy;
    protected CanDataInfo.ZT_CAR_CHARGE mSlowData = new CanDataInfo.ZT_CAR_CHARGE();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBtnStatus.setDrawable(R.drawable.can_zt_xx_icon_up, R.drawable.can_zt_xx_icon_dn);
        this.mBtnStatus.setText("慢充状态");
        this.mBtnStatus.setOnClickListener(this);
        this.mBtnWarn.setOnClickListener(this);
        this.mItemCdzt = AddStaLine("充电状态:");
        this.mItemDjwd = AddStaLine("充电机温度:");
        this.mItemScdy = AddStaLine("充电机实际输出电压:");
        this.mItemScdl = AddStaLine("充电机实际输出电流:");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    private void ResetData(boolean check) {
        CanJni.ZtY100GetSlowCharge(this.mSlowData);
        if (i2b(this.mSlowData.UpdateOnce) && (!check || i2b(this.mSlowData.Update))) {
            this.mSlowData.Update = 0;
            this.mItemScdy.SetVal(String.format("%.1f V", new Object[]{Double.valueOf(((double) this.mSlowData.Sjscdy) * 0.1d)}));
            this.mItemScdl.SetVal(String.format("%.1f A", new Object[]{Double.valueOf(-320.0d + (((double) this.mSlowData.Sjscdl) * 0.1d))}));
            this.mItemDjwd.SetVal(String.format("%d ℃", new Object[]{Integer.valueOf(this.mSlowData.FdjTemp - 40)}));
            RemoveAllWarn();
            AddWarn(this.mSlowData.fgTemp, "充电机过温");
            AddWarn(this.mSlowData.fgCddyl, "输入电压故障");
            AddWarn(this.mSlowData.fgYjgz, "硬件故障");
            AddWarn(this.mSlowData.fgTxgz, "通讯故障");
        }
        CanJni.ZtY100GetWarn(this.mWarnData);
        if (!i2b(this.mWarnData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWarnData.Update)) {
            UpdateWarnNum(this.mWarnData.SlowWarn);
            if (this.mWarnData.SlowEnter != 0) {
                this.mItemCdzt.SetVal("开启充电");
            } else {
                this.mItemCdzt.SetVal("关闭充电");
            }
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                ShowStatus(true);
                return;
            case 2:
                ShowStatus(false);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
