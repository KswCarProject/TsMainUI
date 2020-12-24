package com.ts.can.geely.boy;

import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanScrollList;

public class CanGeelyBoyCarInfoActivity extends CanCommonActivity {
    private static final int ITEM_ALL_VIEW = 1;
    private static final int ITEM_LOCK = 0;
    private static final int ITEM_OTHER = 2;

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanGeelyBoySetLockActivity.class);
                return;
            case 1:
                enterSubWin(CanGeelyBoySetViewActivity.class);
                return;
            case 2:
                enterSubWin(CanGeelyBoySetOtherActivity.class);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        CanScrollList mManager = new CanScrollList(this);
        mManager.addItemIconList(R.drawable.can_icon_lock, R.string.can_car_lock_set, 0, this);
        mManager.addItemIconList(R.drawable.can_icon_service, R.string.can_geely_boy_all_view, 1, this);
        mManager.addItemIconList(R.drawable.can_icon_setup, R.string.can_other_set, 2, this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
