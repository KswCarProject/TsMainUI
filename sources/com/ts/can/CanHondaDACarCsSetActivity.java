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

public class CanHondaDACarCsSetActivity extends CanBaseActivity implements View.OnClickListener, CanItemPopupList.onPopItemClick, UserCallBack {
    public static final int ITEM_AUTODOORLOCKWITH = 5;
    public static final int ITEM_AUTODOORUNLOCKWITH = 4;
    public static final int ITEM_KEYREMOTEUNLOCKMODE = 2;
    public static final int ITEM_YKLSTS = 1;
    public static final int ITEM_ZDCSSJ = 3;
    public static final String TAG = "CanHondaDACarCsSetActivity";
    private static final int[] mAutoDoorLockWithArr = {R.string.can_autodoorlockwith1, R.string.can_autodoorlockwith2, R.string.can_autodooroff};
    private static final int[] mAutoDoorUnlockWithArr = {R.string.can_alldoorswhendriverdooropens, R.string.can_alldoorswhenshiftedtopark, R.string.can_alldoorswhenignitionswitchedoff, R.string.can_autodooroff};
    private static final int[] mKeyRemoteUnlockModeArr = {R.string.can_keyandremoteunlockmode_1, R.string.can_keyandremoteunlockmode_2};
    private static final int[] mZdcssjArr = {R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s, R.string.can_headlightautoofftime_90s};
    protected CanItemPopupList mItemAutoDoorLockWith;
    protected CanItemPopupList mItemAutoDoorUnlockWith;
    protected CanItemPopupList mItemKeyRemoteUnlockMode;
    protected CanItemSwitchList mItemYklsts;
    protected CanItemPopupList mItemZdcssj;
    protected CanScrollList mManager;
    protected CanDataInfo.HondaSetData mSetData = new CanDataInfo.HondaSetData();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemYklsts = AddCheckItem(R.string.can_keylesslockanswerback, 1);
        this.mItemKeyRemoteUnlockMode = AddPopupItem(R.string.can_keyandremoteunlockmode, mKeyRemoteUnlockModeArr, 2);
        this.mItemZdcssj = AddPopupItem(R.string.can_securityrelocktimer, mZdcssjArr, 3);
        this.mItemAutoDoorUnlockWith = AddPopupItem(R.string.can_autodoorunlockwith, mAutoDoorUnlockWithArr, 4);
        this.mItemAutoDoorLockWith = AddPopupItem(R.string.can_autodoorlockwith, mAutoDoorLockWithArr, 5);
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
                CanJni.HondaDACarSet(10, Neg(this.mSetData.yklsts));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.HondaDACarSet(9, item);
                return;
            case 3:
                CanJni.HondaDACarSet(11, item);
                return;
            case 4:
                CanJni.HondaDACarSet(8, item);
                return;
            case 5:
                CanJni.HondaDACarSet(7, item);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.CsUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.CsUpdate)) {
            this.mSetData.CsUpdate = 0;
            this.mItemYklsts.SetCheck(this.mSetData.yklsts);
            this.mItemKeyRemoteUnlockMode.SetSel(this.mSetData.keylockmode);
            this.mItemZdcssj.SetSel(this.mSetData.zdcssj);
            this.mItemAutoDoorUnlockWith.SetSel(this.mSetData.autodoorunlock);
            this.mItemAutoDoorLockWith.SetSel(this.mSetData.autodoorlock);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
