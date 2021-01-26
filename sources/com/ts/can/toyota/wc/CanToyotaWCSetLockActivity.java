package com.ts.can.toyota.wc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanToyotaWCSetLockActivity extends CanToyotaWCBaseActivity implements View.OnClickListener, UserCallBack, CanItemProgressList.onPosChange, CanItemPopupList.onPopItemClick {
    public static final int ITEM_AUTO_BY_FROM_P = 2;
    public static final int ITEM_AUTO_BY_SPEED = 1;
    public static final int ITEM_AUTO_BY_TO_P = 3;
    public static final int ITEM_KEY2_UNLOCK = 5;
    public static final int ITEM_LOCK_FLASH = 7;
    private static final int ITEM_MAX = 10;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_ONE_KEY_START = 8;
    public static final int ITEM_OPEN_LINK_UNLOCK = 6;
    public static final int ITEM_REMOTE2_UNLOCK = 4;
    public static final int ITEM_SMART_UNLOCK = 9;
    public static final int ITEM_XCSZXZYDTJ = 10;
    public static final String TAG = "CanToyotaSetLockActivity";
    private static int[] mStrAutoDoorLockArr = {R.string.can_off, R.string.can_shifttoP, R.string.can_byspeed};
    private static int[] mStrAutoDoorUnlockArr = {R.string.can_off, R.string.can_door_unlock_key1, R.string.can_shifttoP};
    private static int[] mStrConServicesArr = {R.string.can_off, R.string.can_onwhilestopped, R.string.can_on};
    private static int[] mStrConServicesRzcArr = {R.string.can_off, R.string.can_on, R.string.can_onwhilestopped};
    private static int[] mStrRelockTimer = {R.string.can_off, R.string.can_30s, R.string.can_60s, R.string.can_90s};
    private static int[] mStrSmartUnlockArr = {R.string.can_unlock_all_seats, R.string.can_unlock_cab};
    private static int[] mStrWirelessLockArr = {R.string.can_off, R.string.can_door_unlock_key1};
    private static int[] mStrXcszxzydltjArr = {R.string.can_off, R.string.can_tilt_only, R.string.can_stretch_only, R.string.can_tilt_stretch};
    private CanItemCheckList mItemAutoByFromP;
    private CanItemCheckList mItemAutoBySpeed;
    private CanItemCheckList mItemAutoByToP;
    private CanItemCheckList mItemKey2Unlock;
    private CanItemCheckList mItemLockFlash;
    private CanItemCheckList mItemOneKeyStart;
    private CanItemCheckList mItemOpenLinkUnlock;
    private CanItemCheckList mItemRemote2Unlock;
    private CanItemPopupList mItemSmartUnlock;
    private CanItemPopupList mItemXcszxzydltj;
    private CanScrollList mManager;
    private boolean mbLayout = false;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetSetData();
        if (!this.mbLayout) {
            LayoutUI();
            check = false;
            this.mbLayout = true;
        }
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemAutoBySpeed.SetCheck(this.mSetData.fgAutoLockBySpeed);
            this.mItemAutoByFromP.SetCheck(this.mSetData.fgAutoLockByShiftFromP);
            this.mItemAutoByToP.SetCheck(this.mSetData.fgAutoLockByShitToP);
            this.mItemRemote2Unlock.SetCheck(this.mSetData.fgRemote2PressUnlock);
            this.mItemKey2Unlock.SetCheck(this.mSetData.fgKey2TimesUnlock);
            this.mItemOpenLinkUnlock.SetCheck(this.mSetData.fgDoorUnlock);
            this.mItemLockFlash.SetCheck(this.mSetData.fgLightResponse);
            this.mItemOneKeyStart.SetCheck(this.mSetData.fgSmartLockAnd1KeyStart);
            this.mItemSmartUnlock.SetSel(this.mSetData.fgSmartUnlock);
            this.mItemXcszxzydltj.SetSel(this.mSetData.Xcszxzydltj);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
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
        for (int i = 1; i <= 10; i++) {
            InitItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        this.mManager.RemoveAllViews();
        for (int i = 1; i <= 10; i++) {
            AddItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mSetData.fgAutoLockBySpeed;
                break;
            case 2:
                ret = this.mSetData.fgAutoLockByShiftFromP;
                break;
            case 3:
                ret = this.mSetData.fgAutoLockByShitToP;
                break;
            case 4:
                ret = this.mSetData.fgRemote2PressUnlock;
                break;
            case 5:
                ret = this.mSetData.fgKey2TimesUnlock;
                break;
            case 6:
                ret = this.mSetData.fgDoorUnlock;
                break;
            case 7:
                ret = this.mSetData.fgLightResponse;
                break;
            case 8:
                ret = this.mSetData.fgSmartLockAnd1KeyStart;
                break;
            case 9:
                ret = this.mSetData.fgSmartUnlock;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void InitItem(int item) {
        switch (item) {
            case 1:
                this.mItemAutoBySpeed = AddCheckItem(R.string.can_auto_lock_by_speed, item);
                return;
            case 2:
                this.mItemAutoByFromP = AddCheckItem(R.string.can_auto_lock_by_from_p, item);
                return;
            case 3:
                this.mItemAutoByToP = AddCheckItem(R.string.can_auto_lock_by_to_p, item);
                return;
            case 4:
                this.mItemRemote2Unlock = AddCheckItem(R.string.can_remote2_unlock, item);
                return;
            case 5:
                this.mItemKey2Unlock = AddCheckItem(R.string.can_key2_unlock, item);
                return;
            case 6:
                this.mItemOpenLinkUnlock = AddCheckItem(R.string.can_door_link_unlock, item);
                return;
            case 7:
                this.mItemLockFlash = AddCheckItem(R.string.can_lock_flash, item);
                return;
            case 8:
                this.mItemOneKeyStart = AddCheckItem(R.string.can_one_key_start, item);
                return;
            case 9:
                this.mItemSmartUnlock = new CanItemPopupList((Context) this, R.string.can_smart_unlock, mStrSmartUnlockArr, (CanItemPopupList.onPopItemClick) this);
                this.mItemSmartUnlock.SetId(item);
                return;
            case 10:
                this.mItemXcszxzydltj = new CanItemPopupList((Context) this, R.string.can_xcszxzydltj, mStrXcszxzydltjArr, (CanItemPopupList.onPopItemClick) this);
                this.mItemXcszxzydltj.SetId(item);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void AddItem(int item) {
        switch (item) {
            case 1:
                this.mManager.AddView(this.mItemAutoBySpeed.GetView());
                return;
            case 2:
                this.mManager.AddView(this.mItemAutoByFromP.GetView());
                return;
            case 3:
                this.mManager.AddView(this.mItemAutoByToP.GetView());
                return;
            case 4:
                this.mManager.AddView(this.mItemRemote2Unlock.GetView());
                return;
            case 5:
                this.mManager.AddView(this.mItemKey2Unlock.GetView());
                return;
            case 6:
                this.mManager.AddView(this.mItemOpenLinkUnlock.GetView());
                return;
            case 7:
                this.mManager.AddView(this.mItemLockFlash.GetView());
                return;
            case 8:
                this.mManager.AddView(this.mItemOneKeyStart.GetView());
                return;
            case 9:
                this.mManager.AddView(this.mItemSmartUnlock.GetView());
                return;
            case 10:
                this.mManager.AddView(this.mItemXcszxzydltj.GetView());
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemCheckList AddCheckItem(int resId, int Id) {
        CanItemCheckList item = new CanItemCheckList(this, resId);
        item.SetIdClickListener(this, Id);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemTextBtnList AddTextItem(int resId, int Id) {
        CanItemTextBtnList item = new CanItemTextBtnList((Context) this, resId);
        item.SetIdClickListener(this, Id);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CarSet(1, 1, Neg(this.mSetData.fgAutoLockBySpeed));
                return;
            case 2:
                CarSet(1, 5, Neg(this.mSetData.fgAutoLockByShiftFromP));
                return;
            case 3:
                CarSet(1, 4, Neg(this.mSetData.fgAutoLockByShitToP));
                return;
            case 4:
                CarSet(2, 4, Neg(this.mSetData.fgRemote2PressUnlock));
                return;
            case 5:
                CarSet(2, 3, Neg(this.mSetData.fgKey2TimesUnlock));
                return;
            case 6:
                CarSet(1, 3, Neg(this.mSetData.fgDoorUnlock));
                return;
            case 7:
                CarSet(2, 1, Neg(this.mSetData.fgLightResponse));
                return;
            case 8:
                CarSet(2, 2, Neg(this.mSetData.fgSmartLockAnd1KeyStart));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 9:
                CarSet(1, 2, item);
                return;
            case 10:
                CarSet(1, 15, item);
                return;
            default:
                return;
        }
    }
}
