package com.ts.can.renault.wc;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanRenaultWcCarInfoView extends CanScrollCarInfoView {
    public CanRenaultWcCarInfoView(Activity activity) {
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
        this.mItemTitleIds = new int[]{R.string.can_czdn};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.ICON};
        this.mItemIcons = new int[]{R.drawable.can_icon_units};
    }

    public void doOnResume() {
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
