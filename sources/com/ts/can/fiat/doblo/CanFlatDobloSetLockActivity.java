package com.ts.can.fiat.doblo;

import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanFlatDobloSetLockActivity extends CanFlatDobloBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_ZDCMSD = 1;
    protected CanItemSwitchList mItemZdcmsd;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemZdcmsd = AddCheckItem(R.string.can_zmzdsd, 1);
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
    public void QueryData() {
        Query(64, 48);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetSetData();
        if (!i2b(this.mSetData.LockUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.LockUpdate)) {
            this.mSetData.LockUpdate = 0;
            this.mItemZdcmsd.SetCheck(this.mSetData.AutoClose);
        }
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
                CarSWSet(53, this.mSetData.AutoClose);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
