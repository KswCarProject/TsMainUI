package com.ts.can.chrysler;

import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.can.CanBaseActivity;

public class CanChrOthBaseActivity extends CanBaseActivity {
    protected CanDataInfo.ChrOthAdt mAdtData = new CanDataInfo.ChrOthAdt();
    protected CanDataInfo.ChrOthSetData mSetData = new CanDataInfo.ChrOthSetData();

    /* access modifiers changed from: protected */
    public void GetAdtData() {
        CanJni.ChrOthGetAdt(this.mAdtData);
    }

    /* access modifiers changed from: protected */
    public void GetSetData() {
        CanJni.ChrOthGetCarSet(this.mSetData);
    }

    /* access modifiers changed from: protected */
    public void Query(int cmd, int para) {
        CanJni.ChrOthQuery(cmd, para, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
        CanJni.ChrOthCarSet(cmd, para);
    }

    /* access modifiers changed from: protected */
    public void CarSWSet(int cmd, int para) {
        CanJni.ChrOthCarSet(cmd, Neg(para));
    }

    /* access modifiers changed from: protected */
    public void CarTypeSet(int cmd) {
        CanJni.ChrOthCarTypeSet(cmd);
    }
}
