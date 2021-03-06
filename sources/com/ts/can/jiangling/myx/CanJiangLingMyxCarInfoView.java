package com.ts.can.jiangling.myx;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanJiangLingMyxCarInfoView extends CanScrollCarInfoView {
    public static final int ITEM_CAR_SET = 0;
    public static final String TAG = "CanGMCarInfoActivity";

    public CanJiangLingMyxCarInfoView(Activity activity) {
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
        this.mItemTitleIds = new int[]{R.string.can_car_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
