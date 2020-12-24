package com.ts.can.psa.wc;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanPSAWCCarInfoView extends CanScrollCarInfoView {
    public CanPSAWCCarInfoView(Activity activity) {
        super(activity, 11);
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
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_car_set, R.string.can_xc_info, R.string.can_psa_wc_warning_info, R.string.can_xh_speed, R.string.can_c4_l_mem_tab, R.string.can_psa_wc_sport_mode_settings_info, R.string.can_psa_wc_lang_settings_info, R.string.can_psa_wc_unit_setup_info, R.string.can_key_set, R.string.can_amp_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}