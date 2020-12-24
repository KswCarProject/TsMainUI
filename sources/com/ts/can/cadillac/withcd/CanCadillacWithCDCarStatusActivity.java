package com.ts.can.cadillac.withcd;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;

public class CanCadillacWithCDCarStatusActivity extends CanCommonActivity implements View.OnClickListener, UserCallBack {
    private CanDataInfo.CadillacInfo1 mInfo1 = new CanDataInfo.CadillacInfo1();
    private CanDataInfo.CadillacInfo2 mInfo2 = new CanDataInfo.CadillacInfo2();
    private CanItemTitleValList mItemCs;
    private CanItemTitleValList mItemSyyl;
    private CanItemTitleValList mItemXhlc;
    private CanItemTitleValList mItemXszlc;
    private CanItemTitleValList mItemZs;
    private CanScrollList mManager;

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemZs = this.mManager.addItemTitleValList(R.string.can_rpm, 0, false, this);
        this.mItemCs = this.mManager.addItemTitleValList(R.string.can_speed, 0, false, this);
        this.mItemSyyl = this.mManager.addItemTitleValList(R.string.can_rest_oil, 0, false, this);
        this.mItemXszlc = this.mManager.addItemTitleValList(R.string.can_dis_trav, 0, false, this);
        this.mItemXhlc = this.mManager.addItemTitleValList(R.string.can_range_xhlc, 0, false, this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.CadillacWithCDGetInfo1(this.mInfo1);
        CanJni.CadillacWithCDGetInfo2(this.mInfo2);
        if (i2b(this.mInfo1.UpdateOnce) && (!check || i2b(this.mInfo1.Update))) {
            this.mInfo1.Update = 0;
            this.mItemZs.SetVal(String.format("%d Rpm", new Object[]{Integer.valueOf(this.mInfo1.Zs)}));
            this.mItemCs.SetVal(String.format("%d Km/h", new Object[]{Integer.valueOf(this.mInfo1.Cs)}));
        }
        if (!i2b(this.mInfo2.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mInfo2.Update)) {
            this.mInfo2.Update = 0;
            this.mItemSyyl.SetVal(String.format("%d L", new Object[]{Integer.valueOf(this.mInfo2.Syyl)}));
            this.mItemXszlc.SetVal(String.format("%d Km", new Object[]{Integer.valueOf(this.mInfo2.Xszlc)}));
            this.mItemXhlc.SetVal(String.format("%d KM", new Object[]{Integer.valueOf(this.mInfo2.Xhlc)}));
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.CadillacWithCDQuery(55);
        Sleep(20);
        CanJni.CadillacWithCDConfigSet(54, 1);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        CanJni.CadillacWithCDConfigSet(54, 0);
    }
}
