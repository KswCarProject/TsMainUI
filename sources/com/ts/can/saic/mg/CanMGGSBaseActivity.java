package com.ts.can.saic.mg;

import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.can.CanBaseActivity;

public class CanMGGSBaseActivity extends CanBaseActivity {
    protected CanDataInfo.MG_GS_DATA mSetData = new CanDataInfo.MG_GS_DATA();
    protected CanDataInfo.MG_GS_DATA1 mSetData1 = new CanDataInfo.MG_GS_DATA1();
    protected CanDataInfo.MG_RX5_DATA2 mSetData2 = new CanDataInfo.MG_RX5_DATA2();

    public void GetSetData() {
        CanJni.MGGSGetSetData(this.mSetData);
    }

    public void GetSetData1() {
        CanJni.MGZSGetSetData(this.mSetData1);
    }

    public void GetSetData2() {
        CanJni.MgRx5GetCarSet(this.mSetData2);
    }

    public void CarSet(int item, int index, int val) {
        CanJni.MGGSCarSet(item, index, val);
    }

    public void ACSet(int key, int sta) {
        CanJni.MGGSACSet(key, sta);
    }

    public void Query(int index) {
        CanJni.MGGSQuery(index);
    }
}
