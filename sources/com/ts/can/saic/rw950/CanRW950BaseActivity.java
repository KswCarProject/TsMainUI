package com.ts.can.saic.rw950;

import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.can.CanBaseActivity;

public class CanRW950BaseActivity extends CanBaseActivity {
    public static final String TAG = "CanGMBaseActivity";
    protected CanDataInfo.RoeweCarSetData mSetData = new CanDataInfo.RoeweCarSetData();

    /* access modifiers changed from: protected */
    public void GetSetData() {
        CanJni.RW950GetCarSetData(this.mSetData);
    }

    /* access modifiers changed from: protected */
    public void QuerySetData() {
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
        CanJni.RW950CarSet(cmd, para);
    }
}
