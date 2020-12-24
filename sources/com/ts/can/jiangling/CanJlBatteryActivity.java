package com.ts.can.jiangling;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;

public class CanJlBatteryActivity extends CanJlBaseActivity implements UserCallBack {
    public static final String TAG = "CanJlBatteryActivity";
    private String[] mBatteryArrays;
    protected CanDataInfo.JL_BAT_DATA1 mData1 = new CanDataInfo.JL_BAT_DATA1();
    protected CanDataInfo.JL_BAT_DATA2 mData2 = new CanDataInfo.JL_BAT_DATA2();
    protected CanDataInfo.JL_CAR_FAULT_DATA mData3 = new CanDataInfo.JL_CAR_FAULT_DATA();
    protected CanDataInfo.JL_BAT_BMS6 mData6 = new CanDataInfo.JL_BAT_BMS6();
    private String[] mErrorArrays;
    protected CanJianglingItem mItemDczzdwd;
    protected CanJianglingItem mItemDczzdwdzh;
    protected CanJianglingItem mItemDczzgwd;
    protected CanJianglingItem mItemDczzgwdzh;
    protected CanJianglingItem mItemDtzddy;
    protected CanJianglingItem mItemDtzdyzh;
    protected CanJianglingItem mItemDtzgdy;
    protected CanJianglingItem mItemDtzgdyzh;
    protected CanJianglingItem mItemKxslc;
    protected CanJianglingItem mItemLjlc;
    protected CanJianglingItem mItemSoc;
    protected CanJianglingItem mItemSpeed;
    protected CanDataInfo.JL_CAR_DATA mStaData = new CanDataInfo.JL_CAR_DATA();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBtnStatus.setDrawable(R.drawable.can_jl_xx_icon_up, R.drawable.can_jl_xx_icon_dn);
        this.mBtnStatus.setText(R.string.can_battery_status);
        this.mBtnStatus.setOnClickListener(this);
        this.mBtnWarn.setOnClickListener(this);
        this.mBatteryArrays = getResources().getStringArray(R.array.can_battery_arrays);
        this.mErrorArrays = getResources().getStringArray(R.array.can_error_arrays);
        this.mItemDtzgdy = AddStaLine(this.mBatteryArrays[0]);
        this.mItemDtzgdyzh = AddStaLine(this.mBatteryArrays[1]);
        this.mItemDtzddy = AddStaLine(this.mBatteryArrays[2]);
        this.mItemDtzdyzh = AddStaLine(this.mBatteryArrays[3]);
        this.mItemDczzgwd = AddStaLine(this.mBatteryArrays[4]);
        this.mItemDczzdwd = AddStaLine(this.mBatteryArrays[5]);
        this.mItemDczzgwdzh = AddStaLine(this.mBatteryArrays[6]);
        this.mItemDczzdwdzh = AddStaLine(this.mBatteryArrays[7]);
        this.mItemSoc = AddStaLine(this.mBatteryArrays[8]);
        if (CanJni.GetCanFsTp() == 79 && CanJni.GetSubType() == 1) {
            this.mItemSpeed = AddStaLine(getString(R.string.can_speed));
            this.mItemLjlc = AddStaLine(getString(R.string.can_yslc));
            this.mItemKxslc = AddStaLine(getString(R.string.can_range_xhlc));
        }
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
        CanJni.JLGetCarFault(this.mData3);
        if (i2b(this.mData3.UpdateOnce) && (!check || i2b(this.mData3.Update))) {
            this.mData3.Update = 0;
            CanJni.JLGetWarn(this.mWarnData);
            UpdateWarnNum(this.mWarnData.BatteryWarn);
            RemoveAllWarn();
            AddWarn(this.mData3.fgYzgy, this.mErrorArrays[0]);
            AddWarn(this.mData3.fgYzgw, this.mErrorArrays[1]);
            AddWarn(this.mData3.fgGl, this.mErrorArrays[2]);
            AddWarn(this.mData3.fgIgbtgz, this.mErrorArrays[3]);
            AddWarn(this.mData3.fgZjgz, this.mErrorArrays[4]);
            AddWarn(this.mData3.fgYcgz, this.mErrorArrays[5]);
            AddWarn(this.mData3.fgCangz, this.mErrorArrays[6]);
            AddWarn(this.mData3.fgCs, this.mErrorArrays[7]);
            AddWarn(this.mData3.fgYzdz, this.mErrorArrays[8]);
            AddWarn(this.mData3.fgYzqy, this.mErrorArrays[9]);
            AddWarn(this.mData3.fgIgbtgw, this.mErrorArrays[10]);
            AddWarn(this.mData3.fgXbyj, this.mErrorArrays[11]);
            AddWarn(this.mData3.fgXbjd, this.mErrorArrays[12]);
            AddWarn(this.mData3.fgZddy, this.mErrorArrays[13]);
            AddWarn(this.mData3.fgYmgz, this.mErrorArrays[14]);
            AddWarn(this.mData3.fgDwgz, this.mErrorArrays[15]);
            AddWarn(this.mData3.fgTxyc, this.mErrorArrays[16]);
            AddWarn(this.mData3.fgJygd, this.mErrorArrays[17]);
            AddWarn(this.mData3.fgFdgl, this.mErrorArrays[18]);
            AddWarn(this.mData3.fgDcwdgg, this.mErrorArrays[19]);
            AddWarn(this.mData3.fgDtgb, this.mErrorArrays[20]);
            AddWarn(this.mData3.fgDlbh, this.mErrorArrays[21]);
            AddWarn(this.mData3.fgWsgk, this.mErrorArrays[22]);
            AddWarn(this.mData3.fgYcgd, this.mErrorArrays[23]);
            AddWarn(this.mData3.fgWcgd, this.mErrorArrays[24]);
            AddWarn(this.mData3.fgWdgd, this.mErrorArrays[25]);
            AddWarn(this.mData3.fgCdgl, this.mErrorArrays[26]);
            AddWarn(this.mData3.fgZygd, this.mErrorArrays[27]);
            AddWarn(this.mData3.fgZygg, this.mErrorArrays[28]);
        }
        CanJni.JLGetBatData(this.mData1, this.mData2);
        if (i2b(this.mData1.UpdateOnce) && (!check || i2b(this.mData1.Update))) {
            this.mData1.Update = 0;
            this.mItemDtzgdy.SetVal(String.format("%.3f V", new Object[]{Double.valueOf(((double) this.mData1.Dtzgdy) * 0.001d)}));
            this.mItemDtzgdyzh.SetVal(new StringBuilder(String.valueOf(this.mData1.Dtzgdzzh)).toString());
            this.mItemDtzddy.SetVal(String.format("%.3f V", new Object[]{Double.valueOf(((double) this.mData1.Dtzddy) * 0.001d)}));
            this.mItemDtzdyzh.SetVal(new StringBuilder(String.valueOf(this.mData1.Dtzddyzh)).toString());
        }
        if (i2b(this.mData2.UpdateOnce) && (!check || i2b(this.mData2.Update))) {
            this.mData2.Update = 0;
            this.mItemDczzgwd.SetVal(String.format("%d  ℃", new Object[]{Integer.valueOf(this.mData2.Dczzgwd - 40)}));
            this.mItemDczzdwd.SetVal(String.format("%d  ℃", new Object[]{Integer.valueOf(this.mData2.Dczzdwd - 40)}));
            this.mItemDczzgwdzh.SetVal(String.format("%d   ", new Object[]{Integer.valueOf(this.mData2.Dczzgwdzh)}));
            this.mItemDczzdwdzh.SetVal(String.format("%d   ", new Object[]{Integer.valueOf(this.mData2.Dczzdwdzh)}));
        }
        CanJni.JLGetCarMsg(this.mStaData);
        if (i2b(this.mStaData.UpdateOnce) && (!check || i2b(this.mStaData.Update))) {
            this.mItemSoc.SetVal(String.format("%.1f %%", new Object[]{Double.valueOf(((double) this.mStaData.SOC) * 0.1d)}));
        }
        if (CanJni.GetCanFsTp() == 79 && CanJni.GetSubType() == 1) {
            CanJni.JLGetCarBms6(this.mData6);
            if (!i2b(this.mData6.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mData6.Update)) {
                this.mData6.Update = 0;
                if (this.mData6.Speed == 65535) {
                    this.mItemSpeed.SetVal("--");
                } else {
                    this.mItemSpeed.SetVal(String.format("%.2f Kph", new Object[]{Double.valueOf(((double) this.mData6.Speed) * 0.05d)}));
                }
                if (this.mData6.Ljlc == 16777215) {
                    this.mItemLjlc.SetVal("--");
                } else {
                    this.mItemLjlc.SetVal(String.format("%d Km", new Object[]{Integer.valueOf(this.mData6.Ljlc)}));
                }
                if (this.mData6.Kxslc == 65535) {
                    this.mItemKxslc.SetVal("--");
                    return;
                }
                this.mItemKxslc.SetVal(String.format("%.1f Km", new Object[]{Double.valueOf(((double) this.mData6.Kxslc) * 0.5d)}));
            }
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                ShowStatus(true);
                return;
            case 2:
                if (this.mWarnNum > 0) {
                    ShowStatus(false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
