package com.ts.can.obd.dst;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanSciDstCarInfoView extends CanScrollCarInfoView {
    public CanSciDstCarInfoView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        if (id == 2) {
            CanJni.ObdDstTpmsSet(198, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        enterSubWin(CanCarInfoSub1Activity.class, ((Integer) v.getTag()).intValue());
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_info, R.string.can_tmps, R.string.can_rpa_level};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[2] = new int[]{R.string.can_mzd_cx4_voice_high, R.string.can_mzd_cx4_voice_middle, R.string.can_mzd_cx4_voice_low};
    }

    public void doOnResume() {
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
