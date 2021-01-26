package com.ts.can.cc.wc.h6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanWcH6CarInfoView extends CanScrollCarInfoView {
    public CanWcH6CarInfoView(Activity activity) {
        super(activity, 5);
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
        this.mItemTitleIds = new int[]{R.string.can_light_set, R.string.can_vehi_setup, R.string.can_ycpsz, R.string.can_amp_set, R.string.can_lang_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        if (CanJni.GetSubType() == 5 || CanJni.GetSubType() == 6) {
            this.mItemVisibles[2] = 1;
        } else {
            this.mItemVisibles[2] = 0;
        }
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
