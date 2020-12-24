package com.ts.can.mzd.cx5;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanMzdCx5LockActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_AUTO_DOOR_LOCK_MODE = 1;
    public static final int ITEM_AUTO_RELOCK_TIME = 3;
    public static final int ITEM_LOCK_BEEP_VOLUME = 4;
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_UNLOCK_MODE = 2;
    public static final int ITEM_WALK_AWAY_LOCK = 5;
    public static final String TAG = "CanMzdCx5LockActivity";
    private static final int[] mAutoDoorLockModeArr = {R.string.can_mzd_cx4_mode_off, R.string.can_lock_mode1, R.string.can_lock_mode2, R.string.can_lcok_mode3, R.string.can_lock_mode4};
    private static final int[] mAutoRelockTimeArr = {R.string.can_mzd_cx4_time_90s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_30s, R.string.can_tripbresettiming_4};
    private static final int[] mLockBeepVolArr = {R.string.can_sensitivity_high, R.string.can_sensitivity_mid, R.string.can_sensitivity_low, R.string.can_off};
    private static final int[] mUnlockModeArr = {R.string.can_touch_once, R.string.can_once_dr_twice_all};
    private CanItemPopupList mItemAutoDoorLockMode;
    private CanItemPopupList mItemAutoRelockTime;
    private CanItemPopupList mItemLockBeepVol;
    private CanItemPopupList mItemUnlockMode;
    private CanItemSwitchList mItemWalkAwayLock;
    private CanScrollList mManager;
    protected CanDataInfo.MZD_CX5_Info mSetData = new CanDataInfo.MZD_CX5_Info();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.MzdCx5GetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemAutoDoorLockMode.SetSel(this.mSetData.AutoDoorLockMode);
            this.mItemUnlockMode.SetSel(this.mSetData.UnlockMode);
            this.mItemAutoRelockTime.SetSel(this.mSetData.AutoRelockMode);
            this.mItemLockBeepVol.SetSel(this.mSetData.LockBeepVol);
            this.mItemWalkAwayLock.SetCheck(this.mSetData.WalkAwayLock);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.MzdCx5Query(50);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemAutoDoorLockMode = AddPopupItem(R.string.can_auto_door_lock_mode, mAutoDoorLockModeArr, 1);
        this.mItemUnlockMode = AddPopupItem(R.string.can_unlock_mode, mUnlockModeArr, 2);
        this.mItemAutoRelockTime = AddPopupItem(R.string.can_auto_relock_timer, mAutoRelockTimeArr, 3);
        this.mItemLockBeepVol = AddPopupItem(R.string.can_lock_beep_vol, mLockBeepVolArr, 4);
        this.mItemWalkAwayLock = AddCheckItem(R.string.can_walk_away_lock, 5);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 5; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        return i2b(0);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean IsHaveItem = IsHaveItem(item);
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 5:
                CanJni.MzdCx5CarSet(4, Neg(this.mSetData.WalkAwayLock));
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
                CanJni.MzdCx5CarSet(0, item);
                return;
            case 2:
                CanJni.MzdCx5CarSet(1, item);
                return;
            case 3:
                CanJni.MzdCx5CarSet(2, item);
                return;
            case 4:
                CanJni.MzdCx5CarSet(3, item);
                return;
            default:
                return;
        }
    }
}
