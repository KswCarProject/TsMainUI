package com.ts.can.gm.wc.enclave;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanGmEnclaveWcCarInfoView extends CanScrollCarInfoView {
    public CanGmEnclaveWcCarInfoView(Activity activity) {
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
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_car_lock_set, R.string.can_ac_set, R.string.can_light_set, R.string.can_sshbl, R.string.can_cds, R.string.can_car_info, R.string.can_oil_mile_info, R.string.can_tmps, R.string.can_other_set, R.string.can_lang_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON};
        this.mItemIcons = new int[]{R.drawable.can_icon_esc, R.drawable.can_icon_lock, R.drawable.can_icon_ac, R.drawable.can_icon_light, R.drawable.can_icon_service, R.drawable.can_icon_cds, R.drawable.can_icon_sudu, R.drawable.can_icon_consumption, R.drawable.can_icon_tpms, R.drawable.can_icon_setup, R.drawable.can_icon_tyres};
        this.mItemVisibles[0] = 0;
        this.mItemVisibles[1] = 0;
        this.mItemVisibles[3] = 0;
        this.mItemVisibles[4] = 0;
        this.mItemVisibles[5] = 0;
        this.mItemVisibles[6] = 0;
        this.mItemVisibles[7] = 0;
        this.mItemVisibles[8] = 0;
        this.mItemVisibles[9] = 0;
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
