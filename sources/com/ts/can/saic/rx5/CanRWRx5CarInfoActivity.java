package com.ts.can.saic.rx5;

import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanScrollList;

public class CanRWRx5CarInfoActivity extends CanCommonActivity {
    private static final int ITEM_LIGHT = 0;
    private static final int ITEM_LOCK = 1;
    private static final int ITEM_OTHER = 2;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mManager.addItemIconList(R.drawable.can_icon_light, R.string.can_c4_l_light, 0, this);
        this.mManager.addItemIconList(R.drawable.can_icon_lock, R.string.can_car_lock_set, 1, this);
        this.mManager.addItemIconList(R.drawable.can_icon_setup, R.string.can_other_set, 2, this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanRWRx5SetLightActivity.class);
                return;
            case 1:
                enterSubWin(CanRWRx5SetLockActivity.class);
                return;
            case 2:
                enterSubWin(CanRWRx5SetOtherActivity.class);
                return;
            default:
                return;
        }
    }
}
