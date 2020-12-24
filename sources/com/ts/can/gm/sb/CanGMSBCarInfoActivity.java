package com.ts.can.gm.sb;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanScrollList;

public class CanGMSBCarInfoActivity extends CanCommonActivity {
    public static final int ITEM_AC = 2;
    public static final int ITEM_CAR_TYPE = 0;
    public static final int ITEM_CDS = 5;
    public static final int ITEM_CONV = 4;
    public static final int ITEM_IAP = 9;
    public static final int ITEM_LIGHT = 3;
    public static final int ITEM_LOCK = 1;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_MIN = 0;
    public static final int ITEM_MODE_CUSTOM = 6;
    public static final int ITEM_OTHER = 7;
    public static final int ITEM_STATUS = 8;
    private CanDataInfo.GM_AdtAll mAdtAllData = new CanDataInfo.GM_AdtAll();
    private CanItemIcoList mItemAc;
    private CanItemIcoList mItemCarType;
    private CanItemIcoList mItemCds;
    private CanItemIcoList mItemConv;
    private CanItemIcoList mItemIap;
    private CanItemIcoList mItemLight;
    private CanItemIcoList mItemLock;
    private CanItemIcoList mItemModeCustom;
    private CanItemIcoList mItemOther;
    private CanItemIcoList mItemStatus;
    private CanScrollList mManager;

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanGMSBCarTypeActivity.class);
                return;
            case 1:
                enterSubWin(CanGMSBSetLockActivity.class);
                return;
            case 2:
                enterSubWin(CanGMSBSetACActivity.class);
                return;
            case 3:
                enterSubWin(CanGMSBSetLightActivity.class);
                return;
            case 4:
                enterSubWin(CanGMSBSetConvActivity.class);
                return;
            case 5:
                enterSubWin(CanGMSBSetCDSActivity.class);
                return;
            case 6:
                enterSubWin(CanGMSBSetModeActivity.class);
                return;
            case 7:
                enterSubWin(CanGMSBSetOtherActivity.class);
                return;
            case 8:
                enterSubWin(CanGMSBCarStatusActivity.class);
                return;
            case 9:
                enterSubWin(CanGMSBUpdateActivity.class);
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
        this.mManager = new CanScrollList(this);
        this.mItemCarType = this.mManager.addItemIconList(R.drawable.can_icon_esc, R.string.can_car_type_select, 0, this);
        this.mItemLock = this.mManager.addItemIconList(R.drawable.can_icon_lock, R.string.can_car_lock_set, 1, this);
        this.mItemAc = this.mManager.addItemIconList(R.drawable.can_icon_ac, R.string.can_ac_set, 2, this);
        this.mItemLight = this.mManager.addItemIconList(R.drawable.can_icon_light, R.string.can_c4_l_light, 3, this);
        this.mItemCds = this.mManager.addItemIconList(R.drawable.can_icon_cds, R.string.can_cds, 5, this);
        this.mItemConv = this.mManager.addItemIconList(R.drawable.can_icon_service, R.string.can_sshbl, 4, this);
        this.mItemModeCustom = this.mManager.addItemIconList(R.drawable.can_icon_tyres, R.string.can_mszdy, 6, this);
        this.mItemStatus = this.mManager.addItemIconList(R.drawable.can_icon_driver_assist, R.string.can_vehi_status, 8, this);
        this.mItemOther = this.mManager.addItemIconList(R.drawable.can_icon_setup, R.string.can_other_set, 7, this);
        this.mItemIap = this.mManager.addItemIconList(R.drawable.can_golf_icon03, R.string.can_can_iap, 9, this);
        this.mItemCarType.ShowGone(true);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GMGetAdtAll(this.mAdtAllData);
        if (!i2b(this.mAdtAllData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAdtAllData.Update)) {
            this.mAdtAllData.Update = 0;
            LayoutUI();
        }
    }

    private void LayoutUI() {
        for (int i = 0; i <= 8; i++) {
            ShowItem(i);
        }
    }

    private void ShowItem(int item) {
        boolean show = isHaveItem(item);
        switch (item) {
            case 0:
                this.mItemCarType.ShowGone(show);
                return;
            case 1:
                this.mItemLock.ShowGone(show);
                return;
            case 2:
                this.mItemAc.ShowGone(show);
                return;
            case 3:
                this.mItemLight.ShowGone(show);
                return;
            case 4:
                this.mItemConv.ShowGone(show);
                return;
            case 5:
                this.mItemCds.ShowGone(show);
                return;
            case 6:
                this.mItemModeCustom.ShowGone(show);
                return;
            case 7:
                this.mItemOther.ShowGone(show);
                return;
            case 8:
                this.mItemStatus.ShowArrow(show);
                return;
            case 9:
                this.mItemIap.ShowGone(show);
                return;
            default:
                return;
        }
    }

    private boolean isHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 0:
                ret = 1;
                break;
            case 1:
                ret = this.mAdtAllData.AutoLock + this.mAdtAllData.RmtLock;
                break;
            case 2:
                ret = this.mAdtAllData.AC;
                break;
            case 3:
                ret = this.mAdtAllData.Light;
                break;
            case 4:
                ret = this.mAdtAllData.Conv;
                break;
            case 5:
                ret = this.mAdtAllData.Pzjc;
                break;
            case 6:
                ret = 1;
                break;
            case 7:
                ret = this.mAdtAllData.Other;
                break;
            case 8:
                ret = 1;
                break;
            case 9:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
