package com.ts.can.bmw.mini;

import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanScrollList;

public class CanBMWMiniCarInfoActivity extends CanBaseActivity implements View.OnClickListener {
    public static final int ITEM_CAR_COMPUTER = 0;
    public static final int ITEM_CAR_SETTINGS = 2;
    public static final int ITEM_CAR_STATUS = 1;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        initUI();
    }

    private void initUI() {
        this.mManager = new CanScrollList(this);
        AddIcoItem(R.drawable.can_icon_pm, R.string.can_vehi_computer, 0);
        AddIcoItem(R.drawable.can_icon_driver_assist, R.string.can_vehi_status, 1);
        AddIcoItem(R.drawable.can_icon_setup, R.string.can_vehi_setup, 2);
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
            case 0:
                enterSubWin(CanBMWMiniCarCmpActivity.class);
                return;
            case 1:
                enterSubWin(CanBMWMiniCarStatusActivity.class);
                return;
            case 2:
                enterSubWin(CanBMWMiniCarSettingsActivity.class);
                return;
            default:
                return;
        }
    }
}
