package com.ts.can.mitsubishi.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanMitsubshiRzcCarInfoView extends CanScrollCarInfoView {
    public CanMitsubshiRzcCarInfoView(Activity activity) {
        super(activity, 3);
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
        this.mItemTitleIds = new int[]{R.string.can_amp_set, R.string.can_car_drive_info, R.string.can_car_ctr_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        if (CanJni.GetSubType() != 1) {
            this.mItemVisibles[1] = 0;
            this.mItemVisibles[2] = 0;
        }
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
