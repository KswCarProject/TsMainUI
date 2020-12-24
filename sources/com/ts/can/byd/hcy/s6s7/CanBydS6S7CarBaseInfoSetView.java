package com.ts.can.byd.hcy.s6s7;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanBydS6S7CarBaseInfoSetView extends CanScrollCarInfoView {
    private static final int BYDS6S7_CARWIN = 0;
    private static final int BYDS6S7_EQ = 2;
    private static final int BYDS6S7_PM25 = 1;

    public CanBydS6S7CarBaseInfoSetView(Activity activity) {
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
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        this.mItemTitleIds = new int[]{R.string.can_ykcckz, R.string.can_pm_25, R.string.can_amp_set};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
