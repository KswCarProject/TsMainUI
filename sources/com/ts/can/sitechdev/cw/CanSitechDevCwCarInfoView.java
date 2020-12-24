package com.ts.can.sitechdev.cw;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanSitechDevCwCarInfoView extends CanScrollCarInfoView {
    public static final String TAG = "CanSitechdevCwCarInfoView";

    public CanSitechDevCwCarInfoView(Activity activity) {
        super(activity, 10);
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
        this.mItemTitleIds = new int[]{R.string.can_vehi_status, R.string.can_csxx, R.string.can_car_drive_info, R.string.can_tyres_tpms, R.string.can_car_set, R.string.can_battery_infos, R.string.can_dtxx, R.string.can_dczxx, R.string.can_bmsgzxx, R.string.can_can_iap};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON};
        this.mItemIcons = new int[]{255, R.drawable.can_icon_light, R.drawable.can_icon_tpms, R.drawable.can_golf_icon14, R.drawable.can_icon_setup, 255, R.drawable.can_icon_units, R.drawable.can_icon_hybrid, R.drawable.can_icon_tpms_set, R.drawable.can_icon_factory};
        if (CanJni.GetSubType() != 2) {
            this.mItemVisibles[4] = 0;
        }
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
