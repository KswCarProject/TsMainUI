package com.ts.can.df.er70;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.other.RelativeLayoutManager;

public abstract class CanDFQCBaseActivity extends CanBaseActivity implements UserCallBack {
    protected CanDataInfo.BMS_Version mBmsVersion = new CanDataInfo.BMS_Version();
    protected CanDataInfo.CHARGER_STA1 mChargerSta1 = new CanDataInfo.CHARGER_STA1();
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.MOTORC_PowerSpd mMotorSpd = new CanDataInfo.MOTORC_PowerSpd();
    protected CanDataInfo.MOTORC_STA3 mMotorSta3 = new CanDataInfo.MOTORC_STA3();
    protected CanDataInfo.VCU_life mVcuLife = new CanDataInfo.VCU_life();
    protected CanDataInfo.VCU_STA mVcuSta = new CanDataInfo.VCU_STA();
    protected CanDataInfo.VCU_STA1 mVcuSta1 = new CanDataInfo.VCU_STA1();
    protected CanDataInfo.VCU_STA2 mVcuSta2 = new CanDataInfo.VCU_STA2();
    protected CanDataInfo.VCU_STA3 mVcuSta3 = new CanDataInfo.VCU_STA3();
    protected CanDataInfo.VCU_STA4 mVcuSta4 = new CanDataInfo.VCU_STA4();
    protected CanDataInfo.VCU_STA5 mVcuSta5 = new CanDataInfo.VCU_STA5();
    protected CanDataInfo.VCU_VP mVcuVp = new CanDataInfo.VCU_VP();

    /* access modifiers changed from: protected */
    public abstract void InitLayout();

    /* access modifiers changed from: protected */
    public abstract void Query();

    /* access modifiers changed from: protected */
    public abstract void ResetData(boolean z);

    /* access modifiers changed from: protected */
    public void VenuciaGetBmsVersion() {
        CanJni.VenuciaGetBmsVersion(this.mBmsVersion);
    }

    /* access modifiers changed from: protected */
    public void VenuciaGetVcuVp() {
        CanJni.VenuciaGetVcuVp(this.mVcuVp);
    }

    /* access modifiers changed from: protected */
    public void VenuciaGetChargerSta1() {
        CanJni.VenuciaGetChargerSta1(this.mChargerSta1);
    }

    /* access modifiers changed from: protected */
    public void VenuciaGetMotorSpd() {
        CanJni.VenuciaGetPowerSpd(this.mMotorSpd);
    }

    /* access modifiers changed from: protected */
    public void VenuciaGetMotorSta3() {
        CanJni.VenuciaGetMotoRcSta3(this.mMotorSta3);
    }

    /* access modifiers changed from: protected */
    public void VenuciaGetVcuLife() {
        CanJni.VenuciaGetVcuLife(this.mVcuLife);
    }

    /* access modifiers changed from: protected */
    public void VenuciaGetVcuSta() {
        CanJni.VenuciaGetVcuSta(this.mVcuSta);
    }

    /* access modifiers changed from: protected */
    public void VenuciaGetVcuSta1() {
        CanJni.VenuciaGetVcuSta1(this.mVcuSta1);
    }

    /* access modifiers changed from: protected */
    public void VenuciaGetVcuSta2() {
        CanJni.VenuciaGetVcuSta2(this.mVcuSta2);
    }

    /* access modifiers changed from: protected */
    public void VenuciaGetVcuSta3() {
        CanJni.VenuciaGetVcuSta3(this.mVcuSta3);
    }

    /* access modifiers changed from: protected */
    public void VenuciaGetVcuSta4() {
        CanJni.VenuciaGetVcuSta4(this.mVcuSta4);
    }

    /* access modifiers changed from: protected */
    public void VenuciaGetVcuSta5() {
        CanJni.VenuciaGetVcuSta5(this.mVcuSta5);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        InitLayout();
    }

    /* access modifiers changed from: protected */
    public void SetBackground(int resId) {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.GetLayout().setBackgroundResource(resId);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Query();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void UserAll() {
        ResetData(true);
    }

    /* access modifiers changed from: protected */
    public TextView AddText(int x, int y, int resId) {
        TextView text = this.mManager.AddText(x, y);
        if (resId != 0) {
            text.setText(resId);
        }
        text.setTextColor(-1);
        text.setTextSize(0, 30.0f);
        return text;
    }

    /* access modifiers changed from: protected */
    public TextView AddText(int x, int y, int w, int h) {
        TextView text = this.mManager.AddText(x, y, w, h);
        text.setTextColor(Color.parseColor("#0099ff"));
        text.setPadding(5, 0, 0, 0);
        return text;
    }
}
