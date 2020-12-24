package com.ts.can.gm.rzc;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanGMCarTypeView extends CanScrollCarInfoView {
    public static final String TAG = "CanGMCarTypeActivity";

    public CanGMCarTypeView(Activity activity) {
        super(activity, 1);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CAR_TYPE};
        if (CanJni.GetCanFsTp() == 263) {
            this.mPopValueIds[0] = new int[]{R.array.can_fs_declare_263};
            return;
        }
        this.mPopValueIds[0] = new int[]{R.array.can_fs_declare_210};
    }

    public void doOnResume() {
        updateItem(0, CanJni.GetSubType());
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            Log.d("CanGMCarTypeActivity", "Select = " + item);
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }

    public void onClick(View arg0) {
    }
}
