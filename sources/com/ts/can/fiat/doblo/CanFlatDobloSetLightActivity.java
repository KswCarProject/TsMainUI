package com.ts.can.fiat.doblo;

import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanFlatDobloSetLightActivity extends CanFlatDobloBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_RXD = 1;
    private CanItemSwitchList mItemRxd;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        initUI();
    }

    /* access modifiers changed from: protected */
    public void initUI() {
        this.mManager = new CanScrollList(this);
        this.mItemRxd = AddCheckItem(R.string.can_rjxcd, 1);
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
        Query(64, 32);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetSetData();
        if (!i2b(this.mSetData.LightsUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.LightsUpdate)) {
            this.mSetData.LightsUpdate = 0;
            this.mItemRxd.SetCheck(this.mSetData.DaytimeLights);
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
                CarSWSet(36, this.mSetData.DaytimeLights);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
