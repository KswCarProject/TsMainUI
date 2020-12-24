package com.ts.can.cc.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanCCWcCarTypeView extends CanScrollCarInfoView {
    public CanCCWcCarTypeView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CAR_TYPE};
        this.mPopValueIds[0] = new int[]{R.array.can_fs_declare_154};
    }

    public void doOnResume() {
        updateItem(0, CanJni.GetSubType());
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
