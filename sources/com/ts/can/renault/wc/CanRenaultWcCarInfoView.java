package com.ts.can.renault.wc;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanRenaultWcCarInfoView extends CanScrollCarInfoView {
    public CanRenaultWcCarInfoView(Activity activity) {
        super(activity, 8);
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
        this.mItemTitleIds = new int[]{R.string.can_czdn, R.string.can_vehi_setup, R.string.can_lang_set, R.string.can_lane_assist, R.string.can_jsfz, R.string.can_cssz, R.string.can_mzd_cx4_other, R.string.can_factory_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON};
        this.mItemIcons = new int[]{R.drawable.can_icon_units, R.drawable.can_icon_driver_assist, R.drawable.can_icon_setup, R.drawable.can_golf_icon01, R.drawable.can_golf_icon02, R.drawable.can_icon_lock2, R.drawable.can_icon_service, R.drawable.can_icon_carset};
    }

    public void doOnResume() {
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
