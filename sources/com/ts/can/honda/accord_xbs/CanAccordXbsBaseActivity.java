package com.ts.can.honda.accord_xbs;

import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.can.CanBaseActivity;

public class CanAccordXbsBaseActivity extends CanBaseActivity {
    protected CanDataInfo.AccordAdtAll mAdtData = new CanDataInfo.AccordAdtAll();
    protected CanDataInfo.AccordSetData mSetData = new CanDataInfo.AccordSetData();

    public void CarSet(int cmd) {
        CarSet(cmd, 255);
    }

    public void CarSet(int cmd, int para) {
        CanJni.Yg9XbsCarSet(cmd, para);
    }

    public void GetSetData() {
        CanJni.AccordGetSetData(this.mSetData);
    }

    public void GetAdtData() {
        CanJni.AccordGetAdtData(this.mAdtData);
    }

    public void Query(int cmd, int data) {
        CanJni.Yg9XbsQuery(cmd, data);
    }

    public void CarRvsSet(int cmd) {
        CanJni.Yg9XbsCarRvsSet(cmd);
    }

    public void CarBlkSet(int cmd, int data) {
        CanJni.Yg9XbsCarBlkSet(cmd, data);
    }

    public void CarRadioCtrl(int cmd, int data) {
        CanJni.Yg9XbsRadioCtrl(cmd, data);
    }
}
