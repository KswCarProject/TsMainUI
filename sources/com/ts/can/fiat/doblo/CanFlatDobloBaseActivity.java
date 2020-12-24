package com.ts.can.fiat.doblo;

import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.can.CanBaseActivity;

public class CanFlatDobloBaseActivity extends CanBaseActivity {
    protected CanDataInfo.FlatSetData mSetData = new CanDataInfo.FlatSetData();

    /* access modifiers changed from: protected */
    public void GetSetData() {
        CanJni.FlatDobloGetCarSet(this.mSetData);
    }

    /* access modifiers changed from: protected */
    public void Query(int cmd, int para1, int para2, int para3) {
        CanJni.FlatDobloQuery(cmd, para1, para2, para3);
    }

    /* access modifiers changed from: protected */
    public void Query(int cmd, int para) {
        Query(cmd, para, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
        CanJni.FlatDobloCarSet(cmd, para);
    }

    /* access modifiers changed from: protected */
    public void CarSWSet(int cmd, int para) {
        CanJni.FlatDobloCarSet(cmd, Neg(para));
    }
}
