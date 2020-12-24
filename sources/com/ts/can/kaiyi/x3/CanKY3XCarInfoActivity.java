package com.ts.can.kaiyi.x3;

import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanScrollList;

public class CanKY3XCarInfoActivity extends CanCommonActivity {
    private static final int ITEM_LOCK = 0;
    private static final int ITEM_OIL = 1;
    private static final int ITEM_OTHER = 2;
    private CanScrollList mManager;

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanKY3XSetLockActivity.class);
                return;
            case 1:
                enterSubWin(CanKY3XSetOilActivity.class);
                return;
            case 2:
                enterSubWin(CanKY3XSetOtherActivity.class);
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
        this.mManager = new CanScrollList(this);
        this.mManager.addItemIconList(R.drawable.can_icon_lock, R.string.can_car_lock_set, 0, this);
        this.mManager.addItemIconList(R.drawable.can_icon_consumption, R.string.can_consumption, 1, this);
        this.mManager.addItemIconList(R.drawable.can_icon_setup, R.string.can_mzd_cx4_other, 2, this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
