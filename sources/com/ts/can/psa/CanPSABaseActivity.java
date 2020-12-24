package com.ts.can.psa;

import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.can.CanBaseActivity;

public class CanPSABaseActivity extends CanBaseActivity {
    protected CanDataInfo.AccordAdtAll mAdtData = new CanDataInfo.AccordAdtAll();
    protected CanDataInfo.AccordSetData mSetData = new CanDataInfo.AccordSetData();

    public void CarSet(int cmd) {
        CarSet(cmd, 255);
    }

    public void CarSet(int cmd, int para) {
        CanJni.AccordCarCtrl(cmd, para);
    }

    public void GetSetData() {
        CanJni.AccordGetSetData(this.mSetData);
    }

    public void GetAdtData() {
        CanJni.AccordGetAdtData(this.mAdtData);
    }

    public void Query(int cmd, int data) {
        CanJni.AccordQuery(cmd, data);
    }
}
