package com.ts.can.ford.dj;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanFordDjCarInfoView extends CanScrollCarInfoView {
    private static final int ITEM_CARSET = 0;
    private static final int ITEM_MAX = 1;

    public CanFordDjCarInfoView(Activity activity) {
        super(activity, 1);
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
        this.mItemTitleIds = new int[]{R.string.can_vehi_setup};
        this.mItemIcons = new int[]{R.drawable.can_icon_setup};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.ICON};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
        CanJni.FordDjQuery(3);
    }
}
