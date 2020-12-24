package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanMzdRzcCarInfoView extends CanScrollCarInfoView {
    public CanMzdRzcCarInfoView(Activity activity) {
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
        this.mItemTitleIds = new int[]{R.string.can_car_info, R.string.can_mzd_cx4_setup, R.string.can_mzd_cx4_oil, R.string.can_istop_info};
        this.mItemIcons = new int[]{R.drawable.can_icon_car, R.drawable.can_icon_setup, R.drawable.can_icon_youhao, R.drawable.can_icon_driver_assist};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}