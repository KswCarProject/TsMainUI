package com.ts.can.fiat.doblo;

import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanScrollList;

public class CanFlatDobloCarInfoActivity extends CanFlatDobloBaseActivity implements View.OnClickListener {
    public static final int ITEM_DISPLAY = 1;
    public static final int ITEM_LIGHT = 2;
    public static final int ITEM_LOCK = 3;
    public static final int ITEM_TIME = 4;
    private CanItemIcoList mItemDisplay;
    private CanItemIcoList mItemLight;
    private CanItemIcoList mItemLock;
    private CanItemIcoList mItemTime;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        initUI();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    private void initUI() {
        this.mManager = new CanScrollList(this);
        this.mItemDisplay = AddIcoItem(R.drawable.can_icon_setup, R.string.can_display, 1);
        this.mItemLight = AddIcoItem(R.drawable.can_icon_light, R.string.can_light_setup, 2);
        this.mItemLock = AddIcoItem(R.drawable.can_icon_lock, R.string.can_door_lock_set, 3);
        this.mItemTime = AddIcoItem(R.drawable.can_icon_date_time, R.string.can_car_time_set, 4);
    }

    /* access modifiers changed from: protected */
    public CanItemIcoList AddIcoItem(int ico, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                enterSubWin(CanFlatDobloSetDisplayActivity.class);
                return;
            case 2:
                enterSubWin(CanFlatDobloSetLightActivity.class);
                return;
            case 3:
                enterSubWin(CanFlatDobloSetLockActivity.class);
                return;
            case 4:
                enterSubWin(CanFlatDobloSetTimeActivity.class);
                return;
            default:
                return;
        }
    }
}
