package com.ts.can.honda.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanHondaWcCarInfoView extends CanScrollCarInfoView {
    public CanHondaWcCarInfoView(Activity activity) {
        super(activity, 11);
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
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_consumption, R.string.can_light_setup, R.string.can_remote_set, R.string.can_cssz, R.string.can_jsfzxysz, R.string.can_csshez, R.string.can_moto_rear_door, R.string.can_system_set, R.string.can_amp_set, R.string.can_ycpsz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CAR_TYPE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[0] = new int[]{R.array.can_fs_declare_156};
    }

    public void doOnResume() {
        updateItem(0, CanJni.GetSubType());
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
