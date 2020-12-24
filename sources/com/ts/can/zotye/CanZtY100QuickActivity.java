package com.ts.can.zotye;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;

public class CanZtY100QuickActivity extends CanZtY100BaseActivity implements UserCallBack {
    public static final String TAG = "CanZtY100QuickActivity";
    protected CanZtY100Item mItemCdzt;
    protected CanZtY100Item mItemDjwd;
    protected CanZtY100Item mItemScdl;
    protected CanZtY100Item mItemScdy;
    protected CanDataInfo.ZT_CAR_QUICK mQuickData = new CanDataInfo.ZT_CAR_QUICK();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBtnStatus.setDrawable(R.drawable.can_zt_xx_icon_up, R.drawable.can_zt_xx_icon_dn);
        this.mBtnStatus.setText("快冲状态");
        this.mBtnStatus.setOnClickListener(this);
        this.mBtnWarn.setOnClickListener(this);
        this.mItemCdzt = AddStaLine("充电状态:");
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
        CanJni.ZtY100GetQuickCharge(this.mQuickData);
        if (!i2b(this.mQuickData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mQuickData.Update)) {
            this.mQuickData.Update = 0;
            if (this.mQuickData.Off == 0) {
                this.mItemCdzt.SetVal("开启充电");
            } else {
                this.mItemCdzt.SetVal("关闭充电");
            }
            this.mItemScdy.SetVal(String.format("%.1f V", new Object[]{Double.valueOf(((double) this.mQuickData.Scdy) * 0.1d)}));
            this.mItemScdl.SetVal(String.format("%.1f A", new Object[]{Double.valueOf(((double) this.mQuickData.Scdl) * 0.1d)}));
            CanJni.ZtY100GetWarn(this.mWarnData);
            UpdateWarnNum(this.mWarnData.QuickWarn);
            RemoveAllWarn();
            AddWarn(this.mQuickData.fgTx, "通讯超时");
            AddWarn(this.mQuickData.fgDy, "输入电压错误，充电机停止工作");
            AddWarn(this.mQuickData.fgTemp, "充电机过温");
            AddWarn(this.mQuickData.fgYjgz, "硬件故障");
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
