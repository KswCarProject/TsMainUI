package com.ts.can.mitsubishi.rzc;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub2Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanMitsubshiRzcCarSetView extends CanScrollCarInfoView {
    private static final int ITEM_AIR_CONDITIONER = 6;
    private static final int ITEM_DOOR_UNLOCK_LOCK = 5;
    private static final int ITEM_KEYLESS_OPERATION_SYSTEM = 1;
    private static final int ITEM_LIGHTS = 3;
    private static final int ITEM_OTHER = 7;
    private static final int ITEM_TOUCH_CONTROLLER = 0;
    private static final int ITEM_TUM_SIGNAL = 4;
    private static final int ITEM_WIPERS = 2;

    public CanMitsubshiRzcCarSetView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        enterSubWin(CanCarInfoSub2Activity.class, ((Integer) v.getTag()).intValue());
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_touchpad_controller, R.string.can_car_lock_set, R.string.can_wipers, R.string.can_mzd_cx4_light, R.string.can_mzd_cx4_turn, R.string.can_mzd_cx4_door, R.string.can_ac_set, R.string.can_mzd_cx4_other};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON};
        this.mItemIcons = new int[]{R.drawable.can_golf_icon12, R.drawable.can_icon_lock2, R.drawable.can_golf_icon06, R.drawable.can_golf_icon05, R.drawable.can_golf_icon02, R.drawable.can_icon_lock, R.drawable.can_icon_ac, R.drawable.can_icon_service};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
