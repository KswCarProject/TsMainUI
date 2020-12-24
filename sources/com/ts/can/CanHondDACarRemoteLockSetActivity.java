package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanHondDACarRemoteLockSetActivity extends CanBaseActivity implements View.OnClickListener, CanItemPopupList.onPopItemClick, UserCallBack {
    public static final int ITEM_DOORUNLOCKMODE = 3;
    public static final int ITEM_YKMSCBDTS = 4;
    public static final int ITEM_YKMSFMQTS = 1;
    public static final int ITEM_YKQDXT = 2;
    public static final int ITEM_ZDCNZMLMD = 5;
    public static final String TAG = "CanHondDACarRemoteLockSetActivity";
    private static final int[] mDoorUnlockModeArr = {R.string.can_keyandremoteunlockmode_2, R.string.can_keyandremoteunlockmode_1};
    private static final int[] mZdcnzmlmdArr = {R.string.can_sensitivity_min, R.string.can_sensitivity_low, R.string.can_sensitivity_mid, R.string.can_sensitivity_high, R.string.can_sensitivity_max};
    protected CanItemPopupList mItemDoorUnlockMode;
    protected CanItemSwitchList mItemYkmscbdts;
    protected CanItemSwitchList mItemYkmsfmqts;
    protected CanItemSwitchList mItemYkqdxtkq;
    protected CanItemPopupList mItemZdcnzmlmd;
    protected CanScrollList mManager;
    protected CanDataInfo.HondaSetData mSetData = new CanDataInfo.HondaSetData();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemYkmsfmqts = AddCheckItem(R.string.can_keylessaccessbeep, 1);
        this.mItemYkqdxtkq = AddCheckItem(R.string.can_remotestartsystem, 2);
        this.mItemDoorUnlockMode = AddPopupItem(R.string.can_doorunlockmode, mDoorUnlockModeArr, 3);
        this.mItemYkmscbdts = AddCheckItem(R.string.can_keylessaccesslightflash, 4);
        this.mItemZdcnzmlmd = AddPopupItem(R.string.can_autointeriorsensitivity, mZdcnzmlmdArr, 5);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.HondaDACarSet(13, Neg(this.mSetData.ykmsfmqts));
                return;
            case 2:
                CanJni.HondaDACarSet(24, Neg(this.mSetData.ykqdxt));
                return;
            case 4:
                CanJni.HondaDACarSet(26, Neg(this.mSetData.ykmscbdts));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CanJni.HondaDACarSet(25, item);
                return;
            case 5:
                CanJni.HondaDACarSet(27, item);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.RemCsUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.RemCsUpdate)) {
            this.mSetData.RemCsUpdate = 0;
            this.mItemYkmsfmqts.SetCheck(this.mSetData.ykmsfmqts);
            this.mItemYkqdxtkq.SetCheck(this.mSetData.ykqdxt);
            this.mItemDoorUnlockMode.SetSel(this.mSetData.doorunlockmode);
            this.mItemYkmscbdts.SetCheck(this.mSetData.ykmscbdts);
            this.mItemZdcnzmlmd.SetSel(this.mSetData.zdcnzmlmd);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
