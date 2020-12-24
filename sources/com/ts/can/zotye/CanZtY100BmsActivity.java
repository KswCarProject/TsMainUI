package com.ts.can.zotye;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;

public class CanZtY100BmsActivity extends CanZtY100BaseActivity implements UserCallBack {
    public static final String TAG = "CanZtY100BmsActivity";
    protected CanDataInfo.ZT_BMS_DATA1 mData1 = new CanDataInfo.ZT_BMS_DATA1();
    protected CanDataInfo.ZT_BMS_DATA2 mData2 = new CanDataInfo.ZT_BMS_DATA2();
    protected CanZtY100Item mItemDjwd;
    protected CanZtY100Item mItemDjzs;
    protected CanZtY100Item mItemKzqwd;
    protected CanZtY100Item mItemMxdl;
    protected CanZtY100Item mItemMxdy;
    protected CanZtY100Item mItemRunMode;
    protected CanZtY100Item mItemRunSta;
    protected CanDataInfo.ZT_BMS_STA mStaData = new CanDataInfo.ZT_BMS_STA();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBtnStatus.setDrawable(R.drawable.can_zt_xx_icon_up, R.drawable.can_zt_xx_icon_dn);
        this.mBtnStatus.setText("电机状态");
        this.mItemRunSta = AddStaLine("运行状态:");
        this.mItemRunMode = AddStaLine("运行模式:");
        this.mItemMxdy = AddStaLine("母线电压:");
        this.mItemMxdl = AddStaLine("母线电流:");
        this.mItemDjzs = AddStaLine("电机转速:");
        this.mItemDjwd = AddStaLine("电机温度:");
        this.mItemKzqwd = AddStaLine("控制器温度:");
        this.mBtnStatus.setOnClickListener(this);
        this.mBtnWarn.setOnClickListener(this);
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
        CanJni.ZtY100GetBmsInfo(this.mStaData, this.mData1, this.mData2);
        if (i2b(this.mStaData.UpdateOnce) && (!check || i2b(this.mStaData.Update))) {
            this.mStaData.Update = 0;
            switch (this.mStaData.RunSta) {
                case 1:
                    this.mItemRunSta.SetVal("运行");
                    break;
                case 2:
                    this.mItemRunSta.SetVal("停止");
                    break;
            }
            switch (this.mStaData.RunMode) {
                case 1:
                    this.mItemRunMode.SetVal("牵引");
                    break;
                case 2:
                    this.mItemRunMode.SetVal("制动");
                    break;
            }
            CanJni.ZtY100GetWarn(this.mWarnData);
            UpdateWarnNum(this.mWarnData.MachineWarn);
            RemoveAllWarn();
            AddWarn2(this.mStaData.Mxdy, "母线欠压", "母线过压");
            AddWarn(this.mStaData.fgDjkzgl, "电流控制器过流报警");
            AddWarn(this.mStaData.fgUVW, "UVW故障");
            AddWarn(this.mStaData.fgQztj, "强制停机故障");
            AddWarn(this.mStaData.fgJstb, "加速踏板故障");
            AddWarn(this.mStaData.fgTx, "通讯故障");
            AddWarn(this.mStaData.fgDw, "档位故障");
            Log.d(TAG, "Toohot = " + this.mStaData.TooHot);
            AddWarn2(this.mStaData.TooHot, "控制器过热", "电机过热");
        }
        if (i2b(this.mData1.UpdateOnce) && (!check || i2b(this.mData1.Update))) {
            this.mData1.Update = 0;
            this.mItemMxdy.SetVal(String.format("%.1f V", new Object[]{Double.valueOf(((double) this.mData1.Mxdy) * 0.1d)}));
            this.mItemMxdl.SetVal(String.format("%.1f A", new Object[]{Double.valueOf(-320.0d + (((double) this.mData1.Mxdl) * 0.1d))}));
            this.mItemDjzs.SetVal(String.format("%d RPM", new Object[]{Integer.valueOf(this.mData1.Zs)}));
        }
        if (!i2b(this.mData2.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mData2.Update)) {
            this.mData2.Update = 0;
            this.mItemKzqwd.SetVal(String.format("%d  ℃", new Object[]{Integer.valueOf(this.mData2.CtlTemp - 40)}));
            this.mItemDjwd.SetVal(String.format("%d  ℃", new Object[]{Integer.valueOf(this.mData2.DjTemp - 40)}));
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
