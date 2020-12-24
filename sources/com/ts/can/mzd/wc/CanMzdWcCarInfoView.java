package com.ts.can.mzd.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanMzdWcCarInfoView extends CanScrollCarInfoView {
    public CanMzdWcCarInfoView(Activity activity) {
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
        this.mItemTitleIds = new int[]{R.string.can_vehi_setup, R.string.can_amp_set, R.string.can_clocl_set, R.string.can_mzd_cx4_oil};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        int subType = CanJni.GetSubType();
        if (subType == 4 || subType == 5 || subType == 10 || subType == 3 || subType == 7 || subType == 13) {
            this.mItemVisibles[2] = 1;
        } else {
            this.mItemVisibles[2] = 0;
        }
        if (subType == 8) {
            this.mItemVisibles[3] = 1;
        } else {
            this.mItemVisibles[3] = 0;
        }
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
