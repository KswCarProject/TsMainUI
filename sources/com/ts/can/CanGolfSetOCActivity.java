package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanGolfSetOCActivity extends CanGolfBaseActivity implements View.OnClickListener, CanItemPopupList.onPopItemClick, UserCallBack {
    public static final int ITEM_ACOUSTIC = 7;
    public static final int ITEM_AUTO_LOCK = 5;
    public static final int ITEM_CONV_OPEN = 2;
    public static final int ITEM_DOOR_UNLOCK = 4;
    public static final int ITEM_GYSHCXG = 6;
    public static final int ITEM_LOCK_TITLE = 3;
    private static final int ITEM_MAX = 7;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_OPEN_TITLE = 1;
    public static final String TAG = "CanGolfSetOCActivity";
    private static final int[] mMenuConvOpen = {R.string.can_off, R.string.can_driver_window, R.string.can_all_windows};
    private static final int[] mMenuDoorUnlock = {R.string.can_veh_side, R.string.can_single_door, R.string.can_sym};
    private CanDataInfo.GolfAdtOpenAndC mAdtOC = new CanDataInfo.GolfAdtOpenAndC();
    private CanItemCheckList mItemAcousticSW;
    private CanItemCheckList mItemAutoLockSW;
    private CanItemPopupList mItemConvOpen;
    private CanItemBlankTextList mItemDoorLockTitle;
    private CanItemPopupList mItemDoorUnlock;
    private CanItemCheckList mItemGysSW;
    private CanItemBlankTextList mItemWinOptTitle;
    private CanScrollList mManager;
    private CanDataInfo.GolfOpeningAndClosing mOCData = new CanDataInfo.GolfOpeningAndClosing();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GolfGetOpenClose(this.mOCData);
        if (!this.mbLayout) {
            CanJni.GolfGetAdtOC(this.mAdtOC);
            if (i2b(this.mAdtOC.UpdateOnce) && i2b(this.mOCData.UpdateOnce)) {
                LayoutUI();
                check = false;
                this.mbLayout = true;
            } else {
                return;
            }
        }
        if (!i2b(this.mOCData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mOCData.Update)) {
            this.mOCData.Update = 0;
            this.mItemConvOpen.SetSel(this.mOCData.ConvOpening);
            this.mItemDoorUnlock.SetSel(this.mOCData.DoorUnlocking);
            this.mItemAutoLockSW.SetCheck(this.mOCData.fgAutoLocking);
            this.mItemAcousticSW.SetCheck(this.mOCData.fgAcousticConfirm);
            this.mItemGysSW.SetCheck(this.mOCData.fgGyshwxg);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        if (!i2b(this.mAdtOC.UpdateOnce)) {
            CanJni.GolfQuery(65, 112);
            Sleep(5);
        }
        CanJni.GolfQuery(64, 112);
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
        for (int i = 1; i <= 7; i++) {
            InitItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        this.mManager.RemoveAllViews();
        for (int i = 1; i <= 7; i++) {
            AddItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtOC.ConvOpening;
                break;
            case 2:
                ret = this.mAdtOC.ConvOpening;
                break;
            case 3:
                ret = this.mAdtOC.DoorUnlocking + this.mAdtOC.AutomaticLocking;
                break;
            case 4:
                ret = this.mAdtOC.DoorUnlocking;
                break;
            case 5:
                ret = this.mAdtOC.AutomaticLocking;
                break;
            case 6:
                if (1 == CanJni.GetSubType()) {
                    ret = 1;
                    break;
                }
                break;
            case 7:
                ret = 0;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void InitItem(int item) {
        switch (item) {
            case 1:
                this.mItemWinOptTitle = new CanItemBlankTextList((Context) this, R.string.can_win_opt);
                return;
            case 2:
                this.mItemConvOpen = new CanItemPopupList((Context) this, R.string.can_conv_opening, mMenuConvOpen, (CanItemPopupList.onPopItemClick) this);
                this.mItemConvOpen.SetId(2);
                return;
            case 3:
                this.mItemDoorLockTitle = new CanItemBlankTextList((Context) this, R.string.can_central_lock);
                return;
            case 4:
                this.mItemDoorUnlock = new CanItemPopupList((Context) this, R.string.can_door_unlock, mMenuDoorUnlock, (CanItemPopupList.onPopItemClick) this);
                this.mItemDoorUnlock.SetId(4);
                return;
            case 5:
                this.mItemAutoLockSW = new CanItemCheckList(this, R.string.can_auto_lock);
                this.mItemAutoLockSW.SetIdClickListener(this, 5);
                return;
            case 6:
                this.mItemGysSW = new CanItemCheckList(this, R.string.can_magoten_gyswxg);
                this.mItemGysSW.SetIdClickListener(this, 6);
                return;
            case 7:
                this.mItemAcousticSW = new CanItemCheckList(this, R.string.can_acoustic_confirm);
                this.mItemAcousticSW.SetIdClickListener(this, 7);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void AddItem(int item) {
        if (IsHaveItem(item)) {
            switch (item) {
                case 1:
                    this.mManager.AddView(this.mItemWinOptTitle.GetView());
                    return;
                case 2:
                    this.mManager.AddView(this.mItemConvOpen.GetView());
                    return;
                case 3:
                    this.mManager.AddView(this.mItemDoorLockTitle.GetView());
                    return;
                case 4:
                    this.mManager.AddView(this.mItemDoorUnlock.GetView());
                    return;
                case 5:
                    this.mManager.AddView(this.mItemAutoLockSW.GetView());
                    return;
                case 6:
                    this.mManager.AddView(this.mItemGysSW.GetView());
                    return;
                case 7:
                    this.mManager.AddView(this.mItemAcousticSW.GetView());
                    return;
                default:
                    return;
            }
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 5:
                CanJni.GolfSendCmd(114, Neg(this.mOCData.fgAutoLocking));
                return;
            case 6:
                CanJni.GolfSendCmd(116, Neg(this.mOCData.fgGyshwxg));
                return;
            case 7:
                CanJni.GolfSendCmd(201, Neg(this.mOCData.fgAcousticConfirm));
                return;
            default:
                return;
        }
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 2:
                CanJni.GolfSendCmd(112, item);
                return;
            case 4:
                CanJni.GolfSendCmd(113, item);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
