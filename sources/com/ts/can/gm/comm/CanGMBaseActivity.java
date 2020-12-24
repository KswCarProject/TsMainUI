package com.ts.can.gm.comm;

import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.can.CanBaseActivity;

public class CanGMBaseActivity extends CanBaseActivity {
    public static final String TAG = "CanGMBaseActivity";
    protected CanDataInfo.GM_CarSet mSetData = new CanDataInfo.GM_CarSet();

    /* access modifiers changed from: protected */
    public void GetSetData() {
        CanJni.GMGetCarSet(this.mSetData);
    }

    /* access modifiers changed from: protected */
    public void QuerySetData() {
        if (this.mSetData.UpdateOnce == 0) {
            CanJni.GMQuery(6);
        }
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
        CanJni.GMCarCtrl(cmd, para);
    }
}
