package com.ts.can.toyota.wc;

import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.can.CanBaseActivity;

public class CanToyotaWCBaseActivity extends CanBaseActivity {
    public static final String TAG = "CanToyotaBaseActivity";
    private static int mPage;
    protected static int mQueryTime = 0;
    protected CanDataInfo.ToyotaWcAMPInfo mAmpData = new CanDataInfo.ToyotaWcAMPInfo();
    protected CanDataInfo.ToyotaWcHybrid mHybridData = new CanDataInfo.ToyotaWcHybrid();
    protected CanDataInfo.ToyotaWcSet mSetData = new CanDataInfo.ToyotaWcSet();

    /* access modifiers changed from: protected */
    public void TimerCall() {
    }

    /* access modifiers changed from: protected */
    public void GetSetData() {
        CanJni.ToyotaWcGetCarSet(this.mSetData);
    }

    /* access modifiers changed from: protected */
    public void GetAmpData() {
        CanJni.ToyotaWcGetAmpInfo(this.mAmpData);
    }

    /* access modifiers changed from: protected */
    public void AmpSet(int cmd, int para) {
        CanJni.ToyotaWcAmpSet(cmd, para);
    }

    /* access modifiers changed from: protected */
    public void GetHybridData() {
        CanJni.ToyotaWcGetHybrid(this.mHybridData);
    }

    /* access modifiers changed from: protected */
    public void CarSet(int type, int cmd, int para) {
        CanJni.ToyotaWcCarSet(type, cmd, para);
    }
}
