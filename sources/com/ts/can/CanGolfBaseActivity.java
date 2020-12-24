package com.ts.can;

import com.lgb.canmodule.CanJni;

public class CanGolfBaseActivity extends CanBaseActivity {
    public static final String TAG = "CanGolfBaseActivity";

    /* access modifiers changed from: protected */
    public boolean isRzcMix() {
        if (CanJni.GetCanFsTp() == 2 && CanJni.GetSubType() == 4) {
            return true;
        }
        return false;
    }
}
