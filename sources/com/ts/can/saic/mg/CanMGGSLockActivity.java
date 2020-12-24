package com.ts.can.saic.mg;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanMGGSLockActivity extends CanMGGSBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_AUTO_UNLOCK = 2;
    private static final int ITEM_MAX = 4;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_NEAR_UNLOCK = 4;
    public static final int ITEM_SPEED_LOCK = 1;
    public static final int ITEM_UNLOCK_MODE = 3;
    public static final String TAG = "CanMGGSLockActivity";
    private static final int[] mLocksArr = {R.string.can_sym, R.string.can_jsym};
    private CanItemSwitchList mItemAutoUnlock;
    private CanItemPopupList mItemNearUnlock;
    private CanItemSwitchList mItemSpeedLock;
    private CanItemPopupList mItemUnlockMode;
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
            this.mItemSpeedLock.SetCheck(this.mSetData.fgSpeedLock);
            this.mItemAutoUnlock.SetCheck(this.mSetData.fgAutoUnlock);
            this.mItemUnlockMode.SetSel(this.mSetData.UnlockMode);
            this.mItemNearUnlock.SetSel(this.mSetData.SmartUnlock);
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
        this.mItemSpeedLock = AddCheckItem(R.string.can_csss, 1);
        this.mItemAutoUnlock = AddCheckItem(R.string.can_zdjs, 2);
        this.mItemUnlockMode = AddPopupItem(R.string.can_unlock_mode, mLocksArr, 3);
        this.mItemNearUnlock = AddPopupItem(R.string.can_smart_near_lock, mLocksArr, 4);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 4; i++) {
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
            case 1:
                CarSet(1, 1, Neg(this.mSetData.fgSpeedLock));
                return;
            case 2:
                CarSet(1, 2, Neg(this.mSetData.fgAutoUnlock));
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
            case 3:
                CarSet(1, 3, item);
                return;
            case 4:
                CarSet(1, 4, item);
                return;
            default:
                return;
        }
    }
}
