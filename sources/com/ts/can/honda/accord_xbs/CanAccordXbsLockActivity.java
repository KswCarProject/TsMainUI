package com.ts.can.honda.accord_xbs;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanAccordXbsLockActivity extends CanAccordXbsBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_ACCESS_BEEP = 7;
    public static final int ITEM_AUTO_DOOR_LOCK = 5;
    public static final int ITEM_AUTO_DOOR_UNLOCK = 4;
    public static final int ITEM_DOOR_UNLOCK_MODE = 8;
    public static final int ITEM_FDJJNZDQTXS = 12;
    public static final int ITEM_JNMSDBJZM = 10;
    public static final int ITEM_KEYLESS_BEEP_VOL = 6;
    public static final int ITEM_KEYLESS_LOCK = 1;
    public static final int ITEM_KEY_LIGHT_FLASH = 9;
    public static final int ITEM_KEY_RMT_UNLOCK = 2;
    public static final int ITEM_LANG = 13;
    private static final int ITEM_MAX = 14;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SDQFWXJGJL = 14;
    public static final int ITEM_SECURITY_RELOCK = 3;
    public static final int ITEM_ZNYSQDZNXS = 11;
    public static final String TAG = "CanAccordLockActivity";
    private static final int[] mAllDoorsArr = {R.string.can_qkdjsymk, R.string.can_qkdtzs, R.string.can_qkdth, R.string.can_off};
    private static final int[] mAutoLockArr = {R.string.can_qcsd, R.string.can_pdax, R.string.can_off};
    private static final int[] mBeepVolArr = {R.string.can_cdjd, R.string.can_cdjg};
    private static final int[] mDangFarArr = {R.string.can_sdqfwxjgjl_3, R.string.can_sdqfwxjgjl_2, R.string.can_sdqfwxjgjl_1};
    private static final int[] mKeyRmtArr = {R.string.can_jsym, R.string.can_sym};
    private static final int[] mKeylessLockArr = {R.string.can_off, R.string.can_on};
    private static final int[] mLangArr = {R.string.can_lang_cn, R.string.can_language_english};
    private static final int[] mSecurityArr = {R.string.can_30s, R.string.can_60s, R.string.can_90s};
    private static final int[] mUnlockModeArr = {R.string.can_jsym, R.string.can_sym};
    private CanItemSwitchList mItemAccessBeep;
    private CanItemPopupList mItemAutoDoorLock;
    private CanItemPopupList mItemAutoDoorUnlock;
    private CanItemPopupList mItemDoorUnlockMode;
    private CanItemSwitchList mItemFdjjnzdqtxs;
    private CanItemSwitchList mItemJnmsdmjzm;
    private CanItemSwitchList mItemKeyFlash;
    private CanItemPopupList mItemKeyRmtUnlock;
    private CanItemPopupList mItemKeylessBeepVol;
    private CanItemPopupList mItemKeylessLock;
    private CanItemPopupList mItemLang;
    private CanItemPopupList mItemSdqfwxjgjl;
    private CanItemPopupList mItemSecuRelock;
    private CanItemSwitchList mItemZnysqdznxs;
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
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemKeylessLock.SetSel(this.mSetData.KeyAnswerBackSW);
            this.mItemKeyRmtUnlock.SetSel(this.mSetData.KeyAndRemoteUnlockMode);
            this.mItemSecuRelock.SetSel(this.mSetData.SecurityTimer);
            this.mItemAutoDoorUnlock.SetSel(this.mSetData.AutoDoorUnlockWith);
            this.mItemAutoDoorLock.SetSel(this.mSetData.AutoDoorLockWith);
            this.mItemAccessBeep.SetCheck(this.mSetData.KeyAccessBeep);
            this.mItemKeylessBeepVol.SetSel(this.mSetData.KeyBeepVol);
            this.mItemDoorUnlockMode.SetSel(this.mSetData.UnlockMode);
            this.mItemKeyFlash.SetCheck(this.mSetData.KeylessFlash);
            this.mItemJnmsdmjzm.SetCheck(this.mSetData.Jnmsbjzm);
            this.mItemZnysqdznxs.SetCheck(this.mSetData.Znystsznxs);
            this.mItemFdjjnzdqtxs.SetCheck(this.mSetData.Fdjjnzdqtxs);
            this.mItemLang.SetSel(this.mSetData.Lang);
            this.mItemSdqfwxjgjl.SetSel(this.mSetData.Sdqfwxjgjl);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(10, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemKeylessLock = AddPopupItem(R.string.can_yklsts, mKeylessLockArr, 1);
        this.mItemKeyRmtUnlock = AddPopupItem(R.string.can_key_rmt_unlock, mKeyRmtArr, 2);
        this.mItemSecuRelock = AddPopupItem(R.string.can_zdcssj, mSecurityArr, 3);
        this.mItemAutoDoorUnlock = AddPopupItem(R.string.can_auto_door_unlock, mAllDoorsArr, 4);
        this.mItemAutoDoorLock = AddPopupItem(R.string.can_auto_lock_with, mAutoLockArr, 5);
        this.mItemAccessBeep = AddCheckItem(R.string.can_access_beep, 7);
        this.mItemKeylessBeepVol = AddPopupItem(R.string.can_keyless_vol, mBeepVolArr, 6);
        this.mItemDoorUnlockMode = AddPopupItem(R.string.can_door_unlock_mode, mUnlockModeArr, 8);
        this.mItemKeyFlash = AddCheckItem(R.string.can_keyless_flash, 9);
        this.mItemJnmsdmjzm = AddCheckItem(R.string.can_fuelefficiencybacklight, 10);
        this.mItemZnysqdznxs = AddCheckItem(R.string.can_znysqdznxs, 11);
        this.mItemFdjjnzdqtxs = AddCheckItem(R.string.can_fdjjnzdqtxs, 12);
        this.mItemLang = AddPopupItem(R.string.can_lang_set, mLangArr, 13);
        this.mItemSdqfwxjgjl = AddPopupItem(R.string.can_sdqfwxjgjl, mDangFarArr, 14);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 14; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.KeyAnswerBackSW;
                break;
            case 2:
                ret = this.mAdtData.KeyAndRemoteUnlockMode;
                break;
            case 3:
                ret = this.mAdtData.SecurityTimer;
                break;
            case 4:
                ret = this.mAdtData.AutoDoorUnlockWith;
                break;
            case 5:
                ret = this.mAdtData.AutoDoorLockWith;
                break;
            case 6:
                ret = this.mAdtData.KeyBeepVol;
                break;
            case 7:
                ret = this.mAdtData.KeyAccessBeep;
                break;
            case 8:
                ret = this.mAdtData.UnlockMode;
                break;
            case 9:
                ret = this.mAdtData.KeylessFlash;
                break;
            case 10:
                ret = this.mAdtData.Jnmsbjzm;
                break;
            case 11:
                ret = this.mAdtData.Znystsznxs;
                break;
            case 12:
                ret = this.mAdtData.Fdjjnzdqtxs;
                break;
            case 13:
                ret = this.mAdtData.Lang;
                break;
            case 14:
                ret = this.mAdtData.Sdqfwxjgjl;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemKeylessLock.ShowGone(show);
                return;
            case 2:
                this.mItemKeyRmtUnlock.ShowGone(show);
                return;
            case 3:
                this.mItemSecuRelock.ShowGone(show);
                return;
            case 4:
                this.mItemAutoDoorUnlock.ShowGone(show);
                return;
            case 5:
                this.mItemAutoDoorLock.ShowGone(show);
                return;
            case 6:
                this.mItemKeylessBeepVol.ShowGone(show);
                return;
            case 7:
                this.mItemAccessBeep.ShowGone(show);
                return;
            case 8:
                this.mItemDoorUnlockMode.ShowGone(show);
                return;
            case 9:
                this.mItemKeyFlash.ShowGone(show);
                return;
            case 10:
                this.mItemJnmsdmjzm.ShowGone(show);
                return;
            case 11:
                this.mItemZnysqdznxs.ShowGone(show);
                return;
            case 12:
                this.mItemFdjjnzdqtxs.ShowGone(show);
                return;
            case 13:
                this.mItemLang.ShowGone(show);
                return;
            case 14:
                this.mItemSdqfwxjgjl.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 7:
                CarSet(13, Neg(this.mSetData.KeyAccessBeep));
                return;
            case 9:
                CarSet(19, Neg(this.mSetData.KeylessFlash));
                return;
            case 10:
                CarSet(22, Neg(this.mSetData.Jnmsbjzm));
                return;
            case 11:
                CarSet(24, Neg(this.mSetData.Znystsznxs));
                return;
            case 12:
                CarSet(25, Neg(this.mSetData.Fdjjnzdqtxs));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CarSet(10, item);
                return;
            case 2:
                CarSet(9, item);
                return;
            case 3:
                CarSet(11, item);
                return;
            case 4:
                CarSet(8, item);
                return;
            case 5:
                CarSet(7, item);
                return;
            case 6:
                CarSet(12, item);
                return;
            case 8:
                CarSet(18, item);
                return;
            case 13:
                CarSet(23, item);
                return;
            case 14:
                CarSet(27, item);
                return;
            default:
                return;
        }
    }
}
