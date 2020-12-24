package com.ts.can.saic.rw950;

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

public class CanRW950ACSetActivity extends CanRW950BaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_DCHYS = 5;
    public static final int ITEM_FQWD = 3;
    public static final int ITEM_HCCW = 4;
    public static final int ITEM_KQZLCGQ = 2;
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_ZDMSFL = 1;
    public static final String TAG = "CanRW950ACSetActivity";
    private static final int[] mFqwdlArr = {R.string.can_ty_set, R.string.can_fq_set, R.string.can_sc_set};
    private static final int[] mKqzlcgqlArr = {R.string.can_off, R.string.can_ac_lo_sens, R.string.can_ac_hi_sens};
    private static final int[] mZdmsflArr = {R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
    private CanDataInfo.RoeweACSetData mACSetData = new CanDataInfo.RoeweACSetData();
    private CanItemSwitchList mItemDchys;
    private CanItemPopupList mItemFqwd;
    private CanItemSwitchList mItemHccw;
    private CanItemPopupList mItemKqzlcgq;
    private CanItemPopupList mItemZdmsfl;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.RW950GetACSetData(this.mACSetData);
        if (!i2b(this.mACSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mACSetData.Update)) {
            this.mACSetData.Update = 0;
            this.mItemZdmsfl.SetSel(this.mACSetData.ubAutoMode);
            this.mItemKqzlcgq.SetSel(this.mACSetData.ubKQZLLMD);
            this.mItemFqwd.SetSel(this.mACSetData.ubFQWD);
            this.mItemHccw.SetCheck(this.mACSetData.ubHCZDCW);
            this.mItemDchys.SetCheck(this.mACSetData.ubDcyhs);
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
        this.mItemZdmsfl = AddPopupItem(R.string.can_ac_zdfl, mZdmsflArr, 1);
        this.mItemKqzlcgq = AddPopupItem(R.string.can_ac_cgq, mKqzlcgqlArr, 2);
        this.mItemFqwd = AddPopupItem(R.string.can_ac_fqwd, mFqwdlArr, 3);
        this.mItemHccw = AddCheckItem(R.string.can_ac_hccw, 4);
        this.mItemDchys = AddCheckItem(R.string.can_dcyhszdqd, 5);
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
            case 4:
                CanJni.RW950ACSet(3, Neg(this.mACSetData.ubHCZDCW));
                return;
            case 5:
                CanJni.RW950ACSet(4, Neg(this.mACSetData.ubDcyhs));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 1:
                CanJni.RW950ACSet(0, item);
                return;
            case 2:
                CanJni.RW950ACSet(1, item);
                return;
            case 3:
                CanJni.RW950ACSet(2, item);
                return;
            default:
                return;
        }
    }
}
