package com.ts.can.gm.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanGMWcCarInfoView extends CanScrollCarInfoView {
    private CanDataInfo.GmWc_TpmsData mTpmsData;

    public CanGMWcCarInfoView(Activity activity) {
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
        this.mItemVisibles[8] = 0;
        this.mTpmsData = new CanDataInfo.GmWc_TpmsData();
    }

    public void ResetData(boolean check) {
        CanJni.GmWcGetCarTpmsInfo(this.mTpmsData);
        if (!i2b(this.mTpmsData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTpmsData.Update)) {
            this.mTpmsData.Update = 0;
            if (i2b(this.mTpmsData.Vaild)) {
                showItem(8, 1);
            } else {
                showItem(8, 0);
            }
        }
    }

    public void QueryData() {
        CanJni.GmWcCarQuery(5, 1, 104);
    }
}
