package com.ts.can.vw.golf.wc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanGolfWcSetOCActivity extends CanBaseActivity implements View.OnClickListener, CanItemPopupList.onPopItemClick, UserCallBack {
    public static final int ITEM_AUTO_LOCK = 5;
    public static final int ITEM_CONV_OPEN = 2;
    public static final int ITEM_DOOR_UNLOCK = 4;
    public static final int ITEM_GYSHCXG = 7;
    public static final int ITEM_LOCK_TITLE = 3;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_MSKZSYQR = 8;
    public static final int ITEM_OPEN_TITLE = 1;
    public static final int ITEM_YKZYJYPP = 6;
    private static final int[] mMenuConvOpen = {R.string.can_all_windows, R.string.can_driver_window, R.string.can_off};
    private static final int[] mMenuDoorUnlock = {R.string.can_sym, R.string.can_single_door, R.string.can_veh_side};
    private CanItemCheckList mItemAutoLockSW;
    private CanItemPopupList mItemConvOpen;
    private CanItemBlankTextList mItemDoorLockTitle;
    private CanItemPopupList mItemDoorUnlock;
    private CanItemCheckList mItemGysSW;
    private CanItemCheckList mItemMskzsyqr;
    private CanItemBlankTextList mItemWinOptTitle;
    private CanItemCheckList mItemYkysjypp;
    private CanScrollList mManager;
    private CanDataInfo.GolfWcOpeningAndClosing mOCAdt = new CanDataInfo.GolfWcOpeningAndClosing();
    private CanDataInfo.GolfWcOpeningAndClosing mOCData = new CanDataInfo.GolfWcOpeningAndClosing();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        boolean z = true;
        CanJni.GolfWcGetOpeningAndClosingData(1, this.mOCAdt);
        CanJni.GolfWcGetOpeningAndClosingData(0, this.mOCData);
        if (i2b(this.mOCAdt.UpdateOnce) && (!check || i2b(this.mOCAdt.Update))) {
            this.mOCAdt.Update = 0;
            this.mItemWinOptTitle.ShowGone(this.mOCAdt.ConvenienceOpening);
            this.mItemConvOpen.ShowGone(this.mOCAdt.ConvenienceOpening);
            this.mItemDoorUnlock.ShowGone(this.mOCAdt.DoorUnlocking);
            this.mItemAutoLockSW.ShowGone(this.mOCAdt.AutomaticLocking);
            this.mItemYkysjypp.ShowGone(this.mOCAdt.Zyykysjypp);
            this.mItemGysSW.ShowGone(this.mOCAdt.Gysxlxg);
            this.mItemMskzsyqr.ShowGone(this.mOCAdt.Mskzsyqr);
            CanItemBlankTextList canItemBlankTextList = this.mItemDoorLockTitle;
            if (this.mOCData.DoorUnlocking == 0 && this.mOCAdt.AutomaticLocking == 0 && this.mOCAdt.Zyykysjypp == 0 && this.mOCAdt.Gysxlxg == 0 && this.mOCAdt.Mskzsyqr == 0) {
                z = false;
            }
            canItemBlankTextList.ShowGone(z);
        }
        if (!i2b(this.mOCData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mOCData.Update)) {
            this.mOCData.Update = 0;
            this.mItemConvOpen.SetSel(this.mOCData.ConvenienceOpening);
            this.mItemDoorUnlock.SetSel(this.mOCData.DoorUnlocking);
            this.mItemAutoLockSW.SetCheck(this.mOCData.AutomaticLocking);
            this.mItemYkysjypp.SetCheck(this.mOCData.Zyykysjypp);
            this.mItemGysSW.SetCheck(this.mOCData.Gysxlxg);
            this.mItemMskzsyqr.SetCheck(this.mOCData.Mskzsyqr);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfWcQueryData(1, 100);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        for (int i = 1; i <= 8; i++) {
            InitItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void InitItem(int item) {
        switch (item) {
            case 1:
                this.mItemWinOptTitle = new CanItemBlankTextList((Context) this, R.string.can_win_opt);
                this.mItemWinOptTitle.ShowGone(false);
                this.mManager.AddView(this.mItemWinOptTitle.GetView());
                return;
            case 2:
                this.mItemConvOpen = new CanItemPopupList((Context) this, R.string.can_conv_opening, mMenuConvOpen, (CanItemPopupList.onPopItemClick) this);
                this.mItemConvOpen.SetId(2);
                this.mItemConvOpen.ShowGone(false);
                this.mManager.AddView(this.mItemConvOpen.GetView());
                return;
            case 3:
                this.mItemDoorLockTitle = new CanItemBlankTextList((Context) this, R.string.can_central_lock);
                this.mItemDoorLockTitle.ShowGone(false);
                this.mManager.AddView(this.mItemDoorLockTitle.GetView());
                return;
            case 4:
                this.mItemDoorUnlock = new CanItemPopupList((Context) this, R.string.can_door_unlock, mMenuDoorUnlock, (CanItemPopupList.onPopItemClick) this);
                this.mItemDoorUnlock.SetId(4);
                this.mItemDoorUnlock.ShowGone(false);
                this.mManager.AddView(this.mItemDoorUnlock.GetView());
                return;
            case 5:
                this.mItemAutoLockSW = new CanItemCheckList(this, R.string.can_auto_lock);
                this.mItemAutoLockSW.SetIdClickListener(this, 5);
                this.mItemAutoLockSW.ShowGone(false);
                this.mManager.AddView(this.mItemAutoLockSW.GetView());
                return;
            case 6:
                this.mItemYkysjypp = new CanItemCheckList(this, R.string.can_zyykysjypp);
                this.mItemYkysjypp.SetIdClickListener(this, 6);
                this.mItemYkysjypp.ShowGone(false);
                this.mManager.AddView(this.mItemYkysjypp.GetView());
                return;
            case 7:
                this.mItemGysSW = new CanItemCheckList(this, R.string.can_magoten_gyswxg);
                this.mItemGysSW.SetIdClickListener(this, 7);
                this.mItemGysSW.ShowGone(false);
                this.mManager.AddView(this.mItemGysSW.GetView());
                return;
            case 8:
                this.mItemMskzsyqr = new CanItemCheckList(this, R.string.can_mskzysqr);
                this.mItemMskzsyqr.SetIdClickListener(this, 8);
                this.mItemMskzsyqr.ShowGone(false);
                this.mManager.AddView(this.mItemMskzsyqr.GetView());
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 5:
                CanJni.GolfWcOpenClosSet(3, Neg(this.mOCData.AutomaticLocking));
                return;
            case 6:
                CanJni.GolfWcOpenClosSet(4, Neg(this.mOCData.Zyykysjypp));
                return;
            case 7:
                CanJni.GolfWcOpenClosSet(5, Neg(this.mOCData.Gysxlxg));
                return;
            case 8:
                CanJni.GolfWcOpenClosSet(6, Neg(this.mOCData.Mskzsyqr));
                return;
            default:
                return;
        }
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 2:
                CanJni.GolfWcOpenClosSet(1, item);
                return;
            case 4:
                CanJni.GolfWcOpenClosSet(2, item);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
