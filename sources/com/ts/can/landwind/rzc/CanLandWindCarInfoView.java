package com.ts.can.landwind.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanLandWindCarInfoView extends CanScrollCarInfoView {
    public CanLandWindCarInfoView(Activity activity) {
        super(activity, 2);
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
        this.mItemTitleIds = new int[]{R.string.can_car_gxh, R.string.can_pressure};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON};
        this.mItemIcons = new int[]{R.drawable.can_icon_setup, R.drawable.can_icon_tpms};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
        CanJni.LoadWindRzcQuery(39);
    }
}
