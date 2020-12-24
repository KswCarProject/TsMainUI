package com.ts.can.bmw.lz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanBMWLzCarInfoView extends CanScrollCarInfoView {
    public CanBMWLzCarInfoView(Activity activity) {
        super(activity, 5);
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
        enterSubWin(CanCarInfoSub1Activity.class, ((Integer) v.getTag()).intValue());
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_amp_set, R.string.can_vehi_setup, R.string.can_fuelt_set, R.string.can_car_drive_info};
        this.mPopValueIds[0] = new int[]{R.array.can_fs_declare_274};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CAR_TYPE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
    }

    public void ResetData(boolean check) {
        updateItem(0, CanJni.GetSubType());
    }

    public void QueryData() {
    }

    public void doOnResume() {
        updateItem(0, CanJni.GetSubType());
    }
}
