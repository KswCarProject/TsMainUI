package com.ts.can.ht.x7;

import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanHtX7CarTypeActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_CAR_TYPE = 0;
    private String[] mCarTypeArray;
    private CanItemCarType mItemCarType;
    private CanScrollList mManager;

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mCarTypeArray = getResources().getStringArray(R.array.can_fs_declare_86);
        this.mManager = new CanScrollList(this);
        this.mItemCarType = this.mManager.addItemCarType(R.string.can_car_type_select, this.mCarTypeArray, 0, (CanItemPopupList.onPopItemClick) this);
        Log.d(CanBaseActivity.TAG, "subtype = " + CanJni.GetSubType());
        this.mItemCarType.GetPopItem().SetSel(CanJni.GetSubType());
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                FtSet.SetCanSubT(item);
                Mcu.SendXKey(20);
                return;
            default:
                return;
        }
    }
}
