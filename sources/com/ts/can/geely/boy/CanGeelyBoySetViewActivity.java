package com.ts.can.geely.boy;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGeelyBoySetViewActivity extends CanCommonActivity {
    private static final int ITEM_DLDCYX = 4;
    private static final int ITEM_DTGJX = 1;
    private static final int ITEM_FZSDZD = 5;
    private static final int ITEM_JTGJX = 0;
    private static final int ITEM_RDTCYC = 3;
    private static final int ITEM_SETUP = 6;
    private static final int ITEM_YYJZ = 2;
    private CanItemSwitchList mItemDldcyx;
    private CanItemSwitchList mItemDtgjx;
    private CanItemSwitchList mItemFzsdzd;
    private CanItemSwitchList mItemJtgjx;
    private CanItemSwitchList mItemRdtcyc;
    private CanItemSwitchList mItemSetup;
    private CanItemSwitchList mItemYyjz;
    private CanDataInfo.GeelyBoy_Settings mSetData = new CanDataInfo.GeelyBoy_Settings();

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.GeelyBoyCarSet(6, Neg(this.mSetData.Jtgjx));
                return;
            case 1:
                CanJni.GeelyBoyCarSet(7, Neg(this.mSetData.Dtgjx));
                return;
            case 2:
                CanJni.GeelyBoyCarSet(8, Neg(this.mSetData.Yyjz));
                return;
            case 3:
                CanJni.GeelyBoyCarSet(9, Neg(this.mSetData.Rdtcys));
                return;
            case 4:
                CanJni.GeelyBoyCarSet(10, Neg(this.mSetData.Dldcyx));
                return;
            case 5:
                CanJni.GeelyBoyCarSet(11, Neg(this.mSetData.Fzsdzd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        CanScrollList manager = new CanScrollList(this);
        this.mItemJtgjx = manager.addItemCheckBox(R.string.can_geely_boy_jtgjx, 0, this);
        this.mItemDtgjx = manager.addItemCheckBox(R.string.can_geely_boy_dtgjx, 1, this);
        this.mItemYyjz = manager.addItemCheckBox(R.string.can_geely_boy_yyjz, 2, this);
        this.mItemRdtcyc = manager.addItemCheckBox(R.string.can_geely_boy_rdtcyc, 3, this);
        this.mItemDldcyx = manager.addItemCheckBox(R.string.can_geely_boy_dldcyx, 4, this);
        this.mItemFzsdzd = manager.addItemCheckBox(R.string.can_geely_boy_fzsdzx, 5, this);
        this.mItemSetup = manager.addItemCheckBox(R.string.can_geely_boy_setup, 6, this);
        this.mItemSetup.ShowGone(false);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GeelyBoyGetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemJtgjx.SetCheck(this.mSetData.Jtgjx);
            this.mItemDtgjx.SetCheck(this.mSetData.Dtgjx);
            this.mItemYyjz.SetCheck(this.mSetData.Yyjz);
            this.mItemRdtcyc.SetCheck(this.mSetData.Rdtcys);
            this.mItemDldcyx.SetCheck(this.mSetData.Dldcyx);
            this.mItemFzsdzd.SetCheck(this.mSetData.Fzsdzd);
            this.mItemSetup.SetCheck(this.mSetData.Szqk);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GeelyBoyCarQuery(64, 0);
    }
}
