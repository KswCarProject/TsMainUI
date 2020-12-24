package com.ts.can.saic.rw950;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanRW950DoorLockActivity extends CanRW950BaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_FZKMZDLS = 1;
    public static final int ITEM_LSYS = 2;
    public static final int ITEM_QBZDLS = 3;
    public static final int ITEM_ZCZDJS = 4;
    public static final String TAG = "CanRW950DoorLockActivity";
    private static final int[] mZczdjsArr = {R.string.can_off, R.string.can_jsym, R.string.can_sym};
    private CanItemSwitchList mItemFzkmzdls;
    private CanItemSwitchList mItemLsys;
    private CanItemSwitchList mItemQbzdls;
    private CanItemPopupList mItemZczdjs;
    private CanScrollList mManager;

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
            this.mItemFzkmzdls.SetCheck(this.mSetData.ubFZKMZDLS);
            this.mItemLsys.SetCheck(this.mSetData.ubYSLS);
            this.mItemQbzdls.SetCheck(this.mSetData.ubQBZDLS);
            this.mItemZczdjs.SetSel(this.mSetData.ubZCZDJS);
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
        this.mItemFzkmzdls = AddCheckItem(R.string.can_fzkmzdll, 1);
        this.mItemLsys = AddCheckItem(R.string.can_ysll, 2);
        this.mItemQbzdls = AddCheckItem(R.string.can_qbzzll, 3);
        this.mItemZczdjs = AddPopupItem(R.string.can_zczdjs, mZczdjsArr, 4);
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
                CarSet(2, Neg(this.mSetData.ubFZKMZDLS));
                return;
            case 2:
                CarSet(5, Neg(this.mSetData.ubYSLS));
                return;
            case 3:
                CarSet(3, Neg(this.mSetData.ubQBZDLS));
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
            case 4:
                CarSet(4, item);
                return;
            default:
                return;
        }
    }
}
