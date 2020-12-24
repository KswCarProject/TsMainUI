package com.ts.can.kaiyi.x3;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanKY3XSetOtherActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_TDJD = 0;
    private static String[] mTdjdArray;
    private CanItemPopupList mItemTdjd;
    private CanScrollList mManager;
    private CanDataInfo.KaiYi3X_CarSet mSetData = new CanDataInfo.KaiYi3X_CarSet();

    public void onClick(View arg0) {
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.KaiYi3xCarSet(14, item);
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
        mTdjdArray = getResources().getStringArray(R.array.can_kaiyi_3x_tdjd);
        this.mManager = new CanScrollList(this);
        this.mItemTdjd = this.mManager.addItemPopupList(R.string.can_kaiyi_3x_tdjd, mTdjdArray, 0, (CanItemPopupList.onPopItemClick) this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.KaiYi3xGetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemTdjd.SetSel(this.mSetData.Tdjd);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
