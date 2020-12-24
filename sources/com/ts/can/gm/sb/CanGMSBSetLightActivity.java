package com.ts.can.gm.sb;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGMSBSetLightActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    public static final int ITEM_DDYS = 2;
    private static final int ITEM_MAX = 2;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_XCD = 1;
    private static final int[] mDdysArr = {R.string.can_off, R.string.can_30s, R.string.can_1min, R.string.can_2min};
    private CanDataInfo.GM_AdtLight mAdtLightData = new CanDataInfo.GM_AdtLight();
    private CanItemPopupList mItemDdys;
    private CanItemSwitchList mItemXcd;
    private CanScrollList mManager;
    private CanDataInfo.GM_CarSet mSetData = new CanDataInfo.GM_CarSet();

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GMCarCtrl(0, Neg(this.mSetData.XCD));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        if (id == 2) {
            CanJni.GMCarCtrl(1, item);
        }
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemXcd = this.mManager.addItemCheckBox(R.string.can_xcd, 1, this);
        this.mItemDdys = this.mManager.addItemPopupList(R.string.can_lsddys, mDdysArr, 2, (CanItemPopupList.onPopItemClick) this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GMGetCarSet(this.mSetData);
        CanJni.GMGetAdtLight(this.mAdtLightData);
        if (i2b(this.mAdtLightData.UpdateOnce) && (!check || i2b(this.mAdtLightData.Update))) {
            this.mAdtLightData.Update = 0;
            LayoutUI();
            check = false;
        }
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemXcd.SetCheck(this.mSetData.XCD);
            this.mItemDdys.SetSel(this.mSetData.LSDDYS);
        }
    }

    private void LayoutUI() {
        for (int i = 1; i <= 2; i++) {
            ShowItem(i);
        }
    }

    private void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemXcd.ShowGone(show);
                return;
            case 2:
                this.mItemDdys.ShowGone(show);
                return;
            default:
                return;
        }
    }

    private boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtLightData.XCD;
                break;
            case 2:
                ret = this.mAdtLightData.LSDDYS;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
