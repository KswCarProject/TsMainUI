package com.ts.can.zotye;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;

public class CanZtY100BatteryActivity extends CanZtY100BaseActivity implements UserCallBack {
    public static final String TAG = "CanZtY100BatteryActivity";
    protected CanDataInfo.ZT_BMS_DRVDATA1 mData1 = new CanDataInfo.ZT_BMS_DRVDATA1();
    protected CanDataInfo.ZT_BMS_DRVDATA2 mData2 = new CanDataInfo.ZT_BMS_DRVDATA2();
    protected CanDataInfo.ZT_BMS_DRVDATA3 mData3 = new CanDataInfo.ZT_BMS_DRVDATA3();
    protected CanZtY100Item mItemDczdwd;
    protected CanZtY100Item mItemDczgwd;
    protected CanZtY100Item mItemDczzdl;
    protected CanZtY100Item mItemDczzdy;
    protected CanZtY100Item mItemDtzddcdy;
    protected CanZtY100Item mItemDtzddch;
    protected CanZtY100Item mItemDtzgdcdy;
    protected CanZtY100Item mItemDtzgdch;
    protected CanZtY100Item mItemSoc;
    protected CanDataInfo.ZT_BMS_DRVSTA mStaData = new CanDataInfo.ZT_BMS_DRVSTA();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBtnStatus.setDrawable(R.drawable.can_zt_xx_icon_up, R.drawable.can_zt_xx_icon_dn);
        this.mBtnStatus.setText("电池状态");
        this.mBtnStatus.setOnClickListener(this);
        this.mBtnWarn.setOnClickListener(this);
        this.mItemDczzdy = AddStaLine("电池组总电压:");
        this.mItemDczzdl = AddStaLine("电池组总电流:");
        this.mItemSoc = AddStaLine("SOC:");
        this.mItemDtzgdcdy = AddStaLine("单体最高电池电压:");
        this.mItemDtzgdch = AddStaLine("单体最高电池号:");
        this.mItemDtzddcdy = AddStaLine("单体最低电池电压:");
        this.mItemDtzddch = AddStaLine("单体最低电池号:");
        this.mItemDczgwd = AddStaLine("电池最高温度:");
        this.mItemDczdwd = AddStaLine("电池最低温度:");
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
        CanJni.ZtY100GetBatteryInfo(this.mStaData, this.mData1, this.mData2, this.mData3);
        if (i2b(this.mStaData.UpdateOnce) && (!check || i2b(this.mStaData.Update))) {
            this.mStaData.Update = 0;
            CanJni.ZtY100GetWarn(this.mWarnData);
            UpdateWarnNum(this.mWarnData.BatteryWarn);
            RemoveAllWarn();
            switch (this.mStaData.BatTemp) {
                case 1:
                    AddWarn("电池温度过低");
                    break;
                case 2:
                    AddWarn("电池温度过高");
                    break;
                case 3:
                    AddWarn("电池温度不均匀");
                    break;
            }
            AddWarn2(this.mStaData.Jybj, "绝缘一级报警", "绝缘二级报警");
            AddWarn2(this.mStaData.Dtqybj, "单体欠压一级报警", "单体欠压二级报警");
            AddWarn(this.mStaData.Dtgybj, "单体过压报警");
            AddWarn(this.mStaData.fgDldckd, "动力电池馈电报警");
            AddWarn(this.mStaData.JdqSta, "主继电器粘连");
            AddWarn(this.mStaData.fgSOH, "SOH报警");
            AddWarn(this.mStaData.fgDybjh, "电池组电压不均衡报警");
            AddWarn(this.mStaData.fgGl, "过流报警");
            AddWarn(this.mStaData.fgMknbdcgz, "模块内部电池故障");
            AddWarn(this.mStaData.fgMknbtxgz, "模块内部通讯故障");
            AddWarn2(this.mStaData.Dczdyycbj, "电池组电压异常报警-欠压", "电池组电压异常报警-过压");
            AddWarn(this.mStaData.fgYjgz, "BMS硬件故障");
            AddWarn(this.mStaData.fgTxgz, "通讯故障");
        }
        if (i2b(this.mData1.UpdateOnce) && (!check || i2b(this.mData1.Update))) {
            this.mData1.Update = 0;
            this.mItemDczzdy.SetVal(String.format("%.1f V", new Object[]{Double.valueOf(((double) this.mData1.Zdy) * 0.1d)}));
            this.mItemDczzdl.SetVal(String.format("%.1f A", new Object[]{Double.valueOf(-320.0d + (((double) this.mData1.Zdl) * 0.1d))}));
            this.mItemSoc.SetVal(new StringBuilder(String.valueOf(this.mData1.Soc)).toString());
        }
        if (i2b(this.mData2.UpdateOnce) && (!check || i2b(this.mData2.Update))) {
            this.mData2.Update = 0;
            this.mItemDtzgdcdy.SetVal(String.format("%.3f V", new Object[]{Double.valueOf(((double) this.mData2.Dtzgdy) * 0.001d)}));
            this.mItemDtzgdch.SetVal(new StringBuilder(String.valueOf(this.mData2.Dtzgdch)).toString());
            this.mItemDtzddcdy.SetVal(String.format("%.3f V", new Object[]{Double.valueOf(((double) this.mData2.Dtzddy) * 0.001d)}));
            this.mItemDtzddch.SetVal(new StringBuilder(String.valueOf(this.mData2.Dtzddch)).toString());
        }
        if (!i2b(this.mData3.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mData3.Update)) {
            this.mData3.Update = 0;
            this.mItemDczgwd.SetVal(String.valueOf(this.mData3.Dczgwd - 40) + " ℃");
            this.mItemDczdwd.SetVal(String.valueOf(this.mData3.Dczdwd - 40) + " ℃");
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
