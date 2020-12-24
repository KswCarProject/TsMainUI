package com.ts.can.fiat.doblo;

import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanFlatDobloSetTimeActivity extends CanFlatDobloBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_SHOW_TIME_STATUS = 1;
    public static final int ITEM_SYNC_TIME = 2;
    protected CanItemSwitchList mItemShowTimeStatus;
    protected CanItemSwitchList mItemSyncTime;
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
        this.mItemShowTimeStatus = AddCheckItem(R.string.can_show_time_status, 1);
        this.mItemSyncTime = AddCheckItem(R.string.can_sync_time, 2);
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
        Query(64, 112);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetSetData();
        if (!i2b(this.mSetData.ClockUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.ClockUpdate)) {
            this.mSetData.ClockUpdate = 0;
            this.mItemShowTimeStatus.SetCheck(this.mSetData.ShowTimeSta);
            this.mItemSyncTime.SetCheck(this.mSetData.SyncTime);
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
                CarSWSet(112, this.mSetData.ShowTimeSta);
                return;
            case 2:
                CarSWSet(113, this.mSetData.SyncTime);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
