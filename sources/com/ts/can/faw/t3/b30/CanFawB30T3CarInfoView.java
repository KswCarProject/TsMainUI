package com.ts.can.faw.t3.b30;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanFawB30T3CarInfoView extends CanScrollCarInfoView {
    public CanFawB30T3CarInfoView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        enterSubWin(CanCarInfoSub1Activity.class, ((Integer) v.getTag()).intValue());
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_set, R.string.can_info_title, R.string.can_battery_status, R.string.can_machine_status};
        this.mItemIcons = new int[]{R.drawable.can_icon_setup, R.drawable.can_icon_esc, R.drawable.can_icon_wm, R.drawable.can_icon_sudu};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
