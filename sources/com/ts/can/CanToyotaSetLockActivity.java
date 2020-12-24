package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanToyotaSetLockActivity extends CanToyotaBaseActivity implements View.OnClickListener, UserCallBack, CanItemProgressList.onPosChange, CanItemPopupList.onPopItemClick {
    public static final int ITEM_AUTODOOR_LOCK = 15;
    public static final int ITEM_AUTODOOR_UNLOCK = 14;
    public static final int ITEM_AUTO_BY_FROM_P = 2;
    public static final int ITEM_AUTO_BY_SPEED = 1;
    public static final int ITEM_AUTO_BY_TO_P = 3;
    public static final int ITEM_AUTO_RELOCK_TIMER = 10;
    public static final int ITEM_CON_SERVICES = 12;
    public static final int ITEM_FEEDBACK_TONE = 11;
    public static final int ITEM_KEY2_UNLOCK = 5;
    public static final int ITEM_LOCK_FLASH = 7;
    private static final int ITEM_MAX = 15;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_ONE_KEY_START = 8;
    public static final int ITEM_OPEN_LINK_UNLOCK = 6;
    public static final int ITEM_REMOTE2_UNLOCK = 4;
    public static final int ITEM_SMART_UNLOCK = 9;
    public static final int ITEM_WIRE_LESSLOCK = 13;
    public static final String TAG = "CanToyotaSetLockActivity";
    private static int[] mStrAutoDoorLockArr = {R.string.can_off, R.string.can_shifttoP, R.string.can_byspeed};
    private static int[] mStrAutoDoorUnlockArr = {R.string.can_off, R.string.can_door_unlock_key1, R.string.can_shifttoP};
    private static int[] mStrConServicesArr = {R.string.can_off, R.string.can_onwhilestopped, R.string.can_on};
    private static int[] mStrConServicesRzcArr = {R.string.can_off, R.string.can_on, R.string.can_onwhilestopped};
    private static int[] mStrRelockTimer = {R.string.can_off, R.string.can_30s, R.string.can_60s, R.string.can_90s};
    private static int[] mStrSmartUnlockArr = {R.string.can_unlock_all_seats, R.string.can_unlock_cab};
    private static int[] mStrWirelessLockArr = {R.string.can_off, R.string.can_door_unlock_key1};
    private CanItemCheckList mItemAutoByFromP;
    private CanItemCheckList mItemAutoBySpeed;
    private CanItemCheckList mItemAutoByToP;
    private CanItemPopupList mItemAutoDoorLock;
    private CanItemPopupList mItemAutoDoorUnlock;
    private CanItemPopupList mItemAutoRelockTimer;
    private CanItemPopupList mItemConServices;
    private CanItemProgressList mItemFeedBackTone;
    private CanItemCheckList mItemKey2Unlock;
    private CanItemCheckList mItemLockFlash;
    private CanItemCheckList mItemOneKeyStart;
    private CanItemCheckList mItemOpenLinkUnlock;
    private CanItemCheckList mItemRemote2Unlock;
    private CanItemPopupList mItemSmartUnlock;
    private CanItemPopupList mItemWirelessLock;
    private CanScrollList mManager;
    private boolean mbLayout;

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
            GetAdaptData();
            if (i2b(this.mAdtData.UpdateOnce)) {
                LayoutUI();
                check = false;
                this.mbLayout = true;
            } else {
                return;
            }
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
            this.mItemAutoRelockTimer.SetSel(this.mSetData.AutoRelockTimer);
            this.mItemFeedBackTone.SetCurVal(this.mSetData.FeedBackTone);
            this.mItemConServices.SetSel(this.mSetData.ConvenServices);
            this.mItemWirelessLock.SetSel(this.mSetData.WirelessLock);
            this.mItemAutoDoorUnlock.SetSel(this.mSetData.AutoDoorUnlock);
            this.mItemAutoDoorLock.SetSel(this.mSetData.AutoDoorLock);
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
        for (int i = 1; i <= 15; i++) {
            InitItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        this.mManager.RemoveAllViews();
        for (int i = 1; i <= 15; i++) {
            AddItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.AutoLockBySpeed;
                break;
            case 2:
                ret = this.mAdtData.ShiftFromP;
                break;
            case 3:
                ret = this.mAdtData.ShiftToP;
                break;
            case 4:
                ret = this.mAdtData.Remote2Unlock;
                break;
            case 5:
                ret = this.mAdtData.Key2Unlock;
                break;
            case 6:
                ret = this.mAdtData.DoorLinkUnlock;
                break;
            case 7:
                ret = this.mAdtData.LockFlash;
                break;
            case 8:
                ret = this.mAdtData.OneKeyStart;
                break;
            case 9:
                ret = this.mAdtData.SmartUnlock;
                break;
            case 10:
                ret = this.mAdtData.AutoRelockTimer;
                break;
            case 11:
                ret = this.mAdtData.FeedBackTone;
                break;
            case 12:
                ret = this.mAdtData.ConvenServices;
                break;
            case 13:
                ret = this.mAdtData.WirelessLock;
                break;
            case 14:
                ret = this.mAdtData.AutoDoorUnlock;
                break;
            case 15:
                ret = this.mAdtData.AutoDoorLock;
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
                this.mItemAutoRelockTimer = new CanItemPopupList((Context) this, R.string.can_auto_relock_timer, mStrRelockTimer, (CanItemPopupList.onPopItemClick) this);
                this.mItemAutoRelockTimer.SetId(item);
                return;
            case 11:
                this.mItemFeedBackTone = new CanItemProgressList((Context) this, R.string.can_feedback_tone);
                this.mItemFeedBackTone.SetIdCallBack(item, this);
                this.mItemFeedBackTone.SetMinMax(0, 6);
                return;
            case 12:
                if (CanJni.GetCanType() == 128) {
                    this.mItemConServices = new CanItemPopupList((Context) this, R.string.can_ssblx, mStrConServicesRzcArr, (CanItemPopupList.onPopItemClick) this);
                } else {
                    this.mItemConServices = new CanItemPopupList((Context) this, R.string.can_ssblx, mStrConServicesArr, (CanItemPopupList.onPopItemClick) this);
                }
                this.mItemConServices.SetId(item);
                return;
            case 13:
                this.mItemWirelessLock = new CanItemPopupList((Context) this, R.string.can_wirlesslock, mStrWirelessLockArr, (CanItemPopupList.onPopItemClick) this);
                this.mItemWirelessLock.SetId(item);
                return;
            case 14:
                this.mItemAutoDoorUnlock = new CanItemPopupList((Context) this, R.string.can_zdjs, mStrAutoDoorUnlockArr, (CanItemPopupList.onPopItemClick) this);
                this.mItemAutoDoorUnlock.SetId(item);
                return;
            case 15:
                this.mItemAutoDoorLock = new CanItemPopupList((Context) this, R.string.can_zmzdsd, mStrAutoDoorLockArr, (CanItemPopupList.onPopItemClick) this);
                this.mItemAutoDoorLock.SetId(item);
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
                    this.mManager.AddView(this.mItemAutoRelockTimer.GetView());
                    return;
                case 11:
                    this.mManager.AddView(this.mItemFeedBackTone.GetView());
                    return;
                case 12:
                    this.mManager.AddView(this.mItemConServices.GetView());
                    return;
                case 13:
                    this.mManager.AddView(this.mItemWirelessLock.GetView());
                    return;
                case 14:
                    this.mManager.AddView(this.mItemAutoDoorUnlock.GetView());
                    return;
                case 15:
                    this.mManager.AddView(this.mItemAutoDoorLock.GetView());
                    return;
                default:
                    return;
            }
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
                CarSet(0, Neg(this.mSetData.fgAutoLockBySpeed));
                return;
            case 2:
                CarSet(1, Neg(this.mSetData.fgAutoLockByShiftFromP));
                return;
            case 3:
                CarSet(2, Neg(this.mSetData.fgAutoLockByShitToP));
                return;
            case 4:
                CarSet(3, Neg(this.mSetData.fgRemote2PressUnlock));
                return;
            case 5:
                CarSet(13, Neg(this.mSetData.fgKey2TimesUnlock));
                return;
            case 6:
                CarSet(14, Neg(this.mSetData.fgDoorUnlock));
                return;
            case 7:
                CarSet(17, Neg(this.mSetData.fgLightResponse));
                return;
            case 8:
                CarSet(16, Neg(this.mSetData.fgSmartLockAnd1KeyStart));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
        if (11 == id) {
            CarSet(5, pos);
        }
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 9:
                CarSet(15, item);
                return;
            case 10:
                CarSet(20, item);
                return;
            case 12:
                if (CanJni.GetCanType() == 128) {
                    CarSet(37, item);
                    return;
                } else {
                    CarSet(40, item);
                    return;
                }
            case 13:
                CarSet(37, item);
                return;
            case 14:
                CarSet(39, item);
                return;
            case 15:
                CarSet(38, item);
                return;
            default:
                return;
        }
    }
}
