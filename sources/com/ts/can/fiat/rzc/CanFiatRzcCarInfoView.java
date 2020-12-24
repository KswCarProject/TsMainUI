package com.ts.can.fiat.rzc;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanFiatRzcCarInfoView extends CanScrollCarInfoView {
    public CanFiatRzcCarInfoView(Activity activity) {
        super(activity, 7);
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
        this.mItemTitleIds = new int[]{R.string.can_vehi_setup, R.string.can_xc_info, R.string.can_psa_wc_lang_settings_info, R.string.can_psa_wc_unit_setup_info, R.string.can_light_setup, R.string.can_lock_center, R.string.can_bcfz};
        this.mItemIcons = new int[]{R.drawable.can_icon_setup, R.drawable.can_icon_sudu, R.drawable.can_golf_icon12, R.drawable.can_golf_icon11, R.drawable.can_icon_light, R.drawable.can_icon_lock2, R.drawable.can_icon_pm};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
