package com.ts.can.hant.rzc;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanHantElectCarInfoView extends CanScrollCarInfoView {
    public CanHantElectCarInfoView(Activity activity) {
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
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        this.mItemTitleIds = new int[]{R.string.can_car_drive_info, R.string.can_warn_msg, R.string.can_car_lock_set, R.string.can_base_setup};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
