package com.ts.can.geely.boy;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGeelyBoySetLockActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_BSCMZDGC = 0;
    private static final int ITEM_XHJS = 2;
    private static final int ITEM_YKLSFK = 3;
    private static final int ITEM_ZDXMWZDZT = 1;
    private static String[] mYklsfkArray;
    private CanItemSwitchList mItemBscmzdgc;
    private CanItemSwitchList mItemXhjs;
    private CanItemPopupList mItemYklsfk;
    private CanItemSwitchList mItemZdxmwzdzt;
    private CanDataInfo.GeelyBoy_Settings mSetData = new CanDataInfo.GeelyBoy_Settings();

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.GeelyBoyCarSet(1, Neg(this.mSetData.Bscmzdgc));
                return;
            case 1:
                CanJni.GeelyBoyCarSet(2, Neg(this.mSetData.Bshzdxmszdzt));
                return;
            case 2:
                CanJni.GeelyBoyCarSet(3, Neg(this.mSetData.Xhjs));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CanJni.GeelyBoyCarSet(4, item);
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
        mYklsfkArray = getResources().getStringArray(R.array.can_geely_boy_yklsfk);
        CanScrollList manager = new CanScrollList(this);
        this.mItemBscmzdgc = manager.addItemCheckBox(R.string.can_geely_boy_bscmzdgc, 0, this);
        this.mItemZdxmwzdzt = manager.addItemCheckBox(R.string.can_geely_boy_zdxmwzdzt, 1, this);
        this.mItemXhjs = manager.addItemCheckBox(R.string.can_kaiyi_3x_xhjs, 2, this);
        this.mItemYklsfk = manager.addItemPopupList(R.string.can_yklsts, mYklsfkArray, 3, (CanItemPopupList.onPopItemClick) this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GeelyBoyGetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemBscmzdgc.SetCheck(this.mSetData.Bscmzdgc);
            this.mItemZdxmwzdzt.SetCheck(this.mSetData.Bshzdxmszdzt);
            this.mItemXhjs.SetCheck(this.mSetData.Xhjs);
            this.mItemYklsfk.SetSel(this.mSetData.Yklsfk);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GeelyBoyCarQuery(64, 0);
    }
}
