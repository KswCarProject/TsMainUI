package com.ts.can.mzd.cx4.bnr;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub2Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanMzdCx4BnrCarSetupView extends CanScrollCarInfoView {
    public CanMzdCx4BnrCarSetupView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        enterSubWin(CanCarInfoSub2Activity.class, ((Integer) v.getTag()).intValue());
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_cx4_door, R.string.can_mzd_cx4_turn, R.string.can_mzd_cx4_light, R.string.can_mzd_cx4_drive, R.string.can_mzd_cx4_safe_set, R.string.can_mzd_cx4_other, R.string.can_amp_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
