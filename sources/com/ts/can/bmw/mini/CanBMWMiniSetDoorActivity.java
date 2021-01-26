package com.ts.can.bmw.mini;

import android.view.View;
import com.ts.MainUI.R;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.yyw.ts70xhw.FtSet;

public class CanBMWMiniSetDoorActivity extends CanBMWMiniBaseActivity {
    public static final int ITEM_AQD_SET = 7;
    public static final int ITEM_AUTO_LOCK = 2;
    public static final int ITEM_DOOR_UNLOCK = 0;
    public static final int ITEM_DRIVE_LOCK = 3;
    public static final int ITEM_LOCK_FLICKER = 4;
    public static final int ITEM_REAR_UNLOCK = 1;
    public static final int ITEM_SW_KEY = 6;
    public static final int ITEM_XCJSHJS = 5;
    private static int nAqdb = 255;
    private static int nSwKeyb = 255;
    private int[] mAqdArrays = {R.string.can_normal, R.string.can_swap, R.string.can_hide};
    private int[] mDoorUnlockArrays = {R.string.can_door_unlock_key1, R.string.can_door_unlock_key2};
    private CanItemPopupList mItemAqd;
    private CanItemSwitchList mItemAutoLock;
    private CanItemPopupList mItemDoorUnlock;
    private CanItemSwitchList mItemDriveLock;
    private CanItemSwitchList mItemLockFlicker;
    private CanItemPopupList mItemRearUnLock;
    private CanItemSwitchList mItemSwKey;
    private CanItemSwitchList mItemXcjshjs;
    private int[] mRearUnlockArrays = {R.string.can_rear_unlock_key1, R.string.can_rear_unlock_key2, R.string.can_rear_unlock_key3, R.string.can_rear_unlock_key4};

    /* access modifiers changed from: protected */
    public void AddItemView() {
        this.mItemDoorUnlock = AddItemPopup(R.string.can_door_unlock_key, this.mDoorUnlockArrays, 0);
        this.mItemRearUnLock = AddItemPopup(R.string.can_rear_unlock_key, this.mRearUnlockArrays, 1);
        this.mItemAutoLock = AddItemCheck(R.string.can_auto_lock_key, 2);
        this.mItemDriveLock = AddItemCheck(R.string.can_drive_lock_key, 3);
        this.mItemLockFlicker = AddItemCheck(R.string.can_lock_flicker, 4);
        this.mItemXcjshjs = AddItemCheck(R.string.can_xcjszhjs, 5);
        this.mItemSwKey = AddItemCheck(R.string.can_sw_speech_mode, 6);
        this.mItemAqd = AddItemPopup(R.string.can_auto_break_aqd, this.mAqdArrays, 7);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetMiniSetData();
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            this.mItemDoorUnlock.SetSel(this.mSetData.DoorLock);
            this.mItemRearUnLock.SetSel(this.mSetData.TrunkLock);
            this.mItemAutoLock.SetCheck(this.mSetData.fgAutoLock);
            this.mItemDriveLock.SetCheck(this.mSetData.fgDriveLock);
            this.mItemLockFlicker.SetCheck(this.mSetData.fgLockFlash);
            this.mItemXcjshjs.SetCheck(this.mSetData.fgXcjshjs);
        }
        if (nSwKeyb != (FtSet.Getlgb5() & 1) || !check) {
            nSwKeyb = FtSet.Getlgb5() & 1;
            this.mItemSwKey.SetCheck(nSwKeyb);
        }
        if (nAqdb != FtSet.GetCanS(3) || !check) {
            nAqdb = FtSet.GetCanS(3);
            this.mItemAqd.SetSel(nAqdb);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        Query(49);
        Sleep(1);
        Query(50);
        Sleep(1);
        Query(51);
        Sleep(1);
        Query(52);
        Sleep(1);
        Query(53);
    }

    public void onClick(View v) {
        int param;
        int param2;
        int param3;
        int id = ((Integer) v.getTag()).intValue();
        int param4 = getCarSetParam();
        switch (id) {
            case 2:
                if (this.mSetData.fgAutoLock == 1) {
                    param3 = param4 - 32;
                } else {
                    param3 = param4 + 32;
                }
                CarSet(51, param3);
                return;
            case 3:
                if (this.mSetData.fgDriveLock == 1) {
                    param2 = param4 - 16;
                } else {
                    param2 = param4 + 16;
                }
                CarSet(52, param2);
                return;
            case 4:
                if (this.mSetData.fgLockFlash == 1) {
                    param = param4 - 8;
                } else {
                    param = param4 + 8;
                }
                CarSet(53, param);
                return;
            case 5:
                if (this.mSetData.fgXcjshjs == 1) {
                    CarSet(54, 0);
                    return;
                } else {
                    CarSet(54, 16);
                    return;
                }
            case 6:
                if ((FtSet.Getlgb5() & 1) > 0) {
                    FtSet.Setlgb5(0);
                    return;
                } else {
                    FtSet.Setlgb5(1);
                    return;
                }
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CarSet(49, item * 128);
                return;
            case 1:
                CarSet(50, item * 64);
                return;
            case 7:
                FtSet.SetCanS((byte) item, 3);
                return;
            default:
                return;
        }
    }

    private int getCarSetParam() {
        int param = 64;
        if (this.mSetData.fgAutoLock == 1) {
            param = 64 + 32;
        }
        if (this.mSetData.fgDriveLock == 1) {
            param += 16;
        }
        if (this.mSetData.fgLockFlash == 1) {
            return param + 8;
        }
        return param;
    }
}
