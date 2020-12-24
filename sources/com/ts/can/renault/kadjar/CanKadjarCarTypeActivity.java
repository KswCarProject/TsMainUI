package com.ts.can.renault.kadjar;

import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanKadjarCarTypeActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_CAR_TYPE = 1;
    private static String[] mCarTypeArrays;
    private CanItemCarType mItemCarType;

    public void onClick(View v) {
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        mCarTypeArrays = getResources().getStringArray(R.array.can_fs_declare_55);
        this.mItemCarType = new CanScrollList(this).addItemCarType(R.string.can_car_type_select, mCarTypeArrays, 1, (CanItemPopupList.onPopItemClick) this);
        this.mItemCarType.GetPopItem().SetSel(CanJni.GetSubType());
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
