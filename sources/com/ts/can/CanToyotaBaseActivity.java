package com.ts.can;

import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.main.common.MainUI;

public class CanToyotaBaseActivity extends CanBaseActivity {
    public static final String TAG = "CanToyotaBaseActivity";
    private static int mPage;
    protected static int mQueryTime = 0;
    protected CanDataInfo.ToyotaSelfAdaptive mAdtData = new CanDataInfo.ToyotaSelfAdaptive();
    protected CanDataInfo.ToyotaAMPInfo mAmpData = new CanDataInfo.ToyotaAMPInfo();
    protected CanDataInfo.ToyotaHybrid mHybridData = new CanDataInfo.ToyotaHybrid();
    protected CanDataInfo.ToyotaSet mSetData = new CanDataInfo.ToyotaSet();

    /* access modifiers changed from: protected */
    public void TimerCall() {
    }

    /* access modifiers changed from: protected */
    public void GetSetData() {
        CanJni.ToyotaGetSetup(this.mSetData);
    }

    /* access modifiers changed from: protected */
    public void GetAmpData() {
        CanJni.ToyotaGetAmp(this.mAmpData);
    }

    /* access modifiers changed from: protected */
    public void AmpSet(int cmd, int para) {
        CanJni.ToyotaAmpSet(cmd, para);
    }

    /* access modifiers changed from: protected */
    public void GetAdaptData() {
        CanJni.ToyotaGetAdaptive(this.mAdtData);
    }

    /* access modifiers changed from: protected */
    public void GetHybridData() {
        CanJni.ToyotaGetHybrid(this.mHybridData);
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
        CanJni.ToyotaCarSet(cmd, para);
    }

    public static void SetCurrentPage(int page) {
        if (MainUI.IsCameraMode() == 0) {
            mPage = page;
        } else {
            mPage = 3;
        }
        CanJni.ToyotaSetCurPage(mPage);
    }
}
