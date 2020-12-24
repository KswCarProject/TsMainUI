package com.ts.can.psa.hc;

import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.can.CanBaseActivity;

public class CanBZBaseActivity extends CanBaseActivity {
    protected CanDataInfo.PeugAdt mAdtData = new CanDataInfo.PeugAdt();
    protected CanDataInfo.PeugSet mSetData = new CanDataInfo.PeugSet();

    public void CarSet(int cmd) {
        CarSet(cmd, 255);
    }

    public void CarSet(int cmd, int para) {
        CanJni.BZCarSet(cmd, para);
    }

    public void GetSetData() {
        CanJni.BZGetSetup(this.mSetData);
    }

    public void GetAdtData() {
        CanJni.BZGetAdt(this.mAdtData);
    }

    public void Query(int cmd, int data) {
        CanJni.BZQuery(cmd);
    }

    public void Query(int cmd) {
        CanJni.BZQuery(cmd);
    }
}
