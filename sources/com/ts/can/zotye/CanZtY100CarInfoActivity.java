package com.ts.can.zotye;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemProgressList;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanZtY100CarInfoActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_BATTERY = 1;
    public static final int ITEM_BMS = 0;
    public static final int ITEM_QUICK = 3;
    public static final int ITEM_SLOW = 2;
    public static final String TAG = "CanZtY100CarInfoActivity";
    protected ParamButton mBtnBattery;
    protected ParamButton mBtnBms;
    protected ParamButton mBtnQuick;
    protected ParamButton mBtnSlow;
    protected RelativeLayoutManager mManager;
    protected CustomTextView mTvBattery;
    protected CustomTextView mTvBms;
    protected CustomTextView mTvQuick;
    protected CustomTextView mTvSlow;
    protected CanDataInfo.ZT_WARN_ENTER mWarnData = new CanDataInfo.ZT_WARN_ENTER();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mBtnBms = AddBtn(0, Can.CAN_DFFG_S560, 132, R.drawable.can_zt_jdxx_up, R.drawable.can_zt_jdxx_dn, R.drawable.can_zt_jdxx_warning);
        this.mBtnBattery = AddBtn(1, 345, 132, R.drawable.can_zt_dcxx_up, R.drawable.can_zt_dcxx_dn, R.drawable.can_zt_dcxx_warning);
        this.mBtnSlow = AddBtn(2, 534, 132, R.drawable.can_zt_mc_up, R.drawable.can_zt_mc_dn, R.drawable.can_zt_mc_warning);
        this.mBtnQuick = AddBtn(3, 724, 132, R.drawable.can_zt_kc_up, R.drawable.can_zt_kc_dn, R.drawable.can_zt_kc_warning);
        this.mTvBms = AddText(135, 287, 188, 40);
        this.mTvBattery = AddText(KeyDef.RKEY_RADIO_3S, 287, 188, 40);
        this.mTvSlow = AddText(514, 287, 188, 40);
        this.mTvQuick = AddText(704, 287, 188, 40);
        this.mTvBms.setText("电机信息");
        this.mTvBattery.setText("电池信息");
        this.mTvSlow.setText("慢充");
        this.mTvQuick.setText("快充");
        this.mBtnSlow.setEnabled(false);
        this.mBtnQuick.setEnabled(false);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d("CanZtY100CarInfoActivity", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanZtY100CarInfoActivity", "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn, int sel) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setStateDrawable(up, dn, sel);
        return btn;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddText(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public void SetWarn(ParamButton btn, int warn, int upImg, int dnImg, int warnImg) {
        if (warn != 0) {
            btn.setStateUpDn(warnImg, dnImg);
        } else {
            btn.setStateUpDn(upImg, dnImg);
        }
    }

    private void ResetData(boolean check) {
        boolean z = true;
        CanJni.ZtY100GetWarn(this.mWarnData);
        if (!i2b(this.mWarnData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWarnData.Update)) {
            this.mWarnData.Update = 0;
            SetWarn(this.mBtnBms, this.mWarnData.MachineWarn, R.drawable.can_zt_jdxx_up, R.drawable.can_zt_jdxx_dn, R.drawable.can_zt_jdxx_warning);
            SetWarn(this.mBtnBattery, this.mWarnData.BatteryWarn, R.drawable.can_zt_dcxx_up, R.drawable.can_zt_dcxx_dn, R.drawable.can_zt_dcxx_warning);
            SetWarn(this.mBtnSlow, this.mWarnData.SlowWarn, R.drawable.can_zt_mc_up, R.drawable.can_zt_mc_dn, R.drawable.can_zt_mc_warning);
            SetWarn(this.mBtnQuick, this.mWarnData.QuickWarn, R.drawable.can_zt_kc_up, R.drawable.can_zt_kc_dn, R.drawable.can_zt_kc_warning);
            this.mBtnSlow.setEnabled(this.mWarnData.SlowEnter != 0);
            ParamButton paramButton = this.mBtnQuick;
            if (this.mWarnData.QuickEnter == 0) {
                z = false;
            }
            paramButton.setEnabled(z);
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanZtY100BmsActivity.class);
                return;
            case 1:
                enterSubWin(CanZtY100BatteryActivity.class);
                return;
            case 2:
                enterSubWin(CanZtY100SlowActivity.class);
                return;
            case 3:
                enterSubWin(CanZtY100QuickActivity.class);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }
}
