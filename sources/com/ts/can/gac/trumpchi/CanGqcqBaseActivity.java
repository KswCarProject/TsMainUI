package com.ts.can.gac.trumpchi;

import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;

public class CanGqcqBaseActivity extends CanBaseActivity {
    protected int[] mSWArr = {R.string.can_on, R.string.can_off};
    protected CanDataInfo.GS4Set mSetData = new CanDataInfo.GS4Set();

    /* access modifiers changed from: protected */
    public void GetSetData() {
        CanJni.GqcqGetSet(this.mSetData);
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int itemval) {
        CanJni.GqcqCarSet(cmd, itemval + 1);
    }
}
