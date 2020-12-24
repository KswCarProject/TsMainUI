package com.ts.can.honda.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaWcMotoRearDoorSetView extends CanScrollCarInfoView {
    private CanDataInfo.HondaWcDdwm mDdwmData;

    public CanHondaWcMotoRearDoorSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.HondaWcCarDdwmSet(1, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.HondaWcCarDdwmSet(2, Neg(this.mDdwmData.Sywsbdddk));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_sywsbdddk, R.string.can_ykkqtjsd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_renhsh, R.string.can_unlock};
        this.mDdwmData = new CanDataInfo.HondaWcDdwm();
    }

    public void ResetData(boolean check) {
        CanJni.HondaWcGetDdwm(this.mDdwmData);
        if (!i2b(this.mDdwmData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDdwmData.Update)) {
            this.mDdwmData.Update = 0;
            updateItem(0, this.mDdwmData.Sywsbdddk);
            updateItem(1, this.mDdwmData.Ykkqtjsd);
        }
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, 117);
    }
}
