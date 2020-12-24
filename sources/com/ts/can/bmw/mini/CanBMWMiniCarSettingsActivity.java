package com.ts.can.bmw.mini;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanBMWMiniCarSettingsActivity extends CanBaseActivity implements View.OnClickListener {
    public static final int ITEM_CAR_DOOR = 4;
    public static final int ITEM_DATE_TIME = 1;
    public static final int ITEM_INFO_DISPLAY = 0;
    public static final int ITEM_LIGHT = 3;
    public static final int ITEM_SPEED = 5;
    public static final int ITEM_UNIT = 2;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        initUI();
    }

    private void initUI() {
        this.mManager = new CanScrollList(this);
        AddIcoItem(R.string.can_display, 0);
        AddIcoItem(R.string.can_time_date, 1);
        AddIcoItem(R.string.can_units, 2);
        AddIcoItem(R.string.can_light_setup, 3);
        AddIcoItem(R.string.can_door_lock_set, 4);
        AddIcoItem(R.string.can_speed_limit, 5);
    }

    /* access modifiers changed from: protected */
    public CanItemTextBtnList AddIcoItem(int text, int id) {
        CanItemTextBtnList item = new CanItemTextBtnList((Context) this, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanBMWMiniSetDisplayActivity.class);
                return;
            case 1:
                enterSubWin(CanBMWMiniSetDateActivity.class);
                return;
            case 2:
                enterSubWin(CanBMWMiniSetUnitsActivity.class);
                return;
            case 3:
                enterSubWin(CanBMWMiniSetLightActivity.class);
                return;
            case 4:
                enterSubWin(CanBMWMiniSetDoorActivity.class);
                return;
            case 5:
                enterSubWin(CanBMWMiniSetSpeedActivity.class);
                return;
            default:
                return;
        }
    }
}
