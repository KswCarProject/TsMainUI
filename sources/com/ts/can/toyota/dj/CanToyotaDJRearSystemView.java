package com.ts.can.toyota.dj;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanToyotaDJRearSystemView extends CanScrollCarInfoView {
    private CanDataInfo.ToyotaDj_RearSys mRearSys;

    public CanToyotaDJRearSystemView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.ToyotaDjRearSysCmd(1, Neg(this.mRearSys.Hpxts));
        } else if (id == 1) {
            CanJni.ToyotaDjRearSysCmd(2, Neg(this.mRearSys.Hphm));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_rear_sys_lock, R.string.can_rear_surface};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mRearSys = new CanDataInfo.ToyotaDj_RearSys();
    }

    public void ResetData(boolean check) {
        CanJni.ToyotaDjGetRearSys(this.mRearSys);
        if (!i2b(this.mRearSys.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mRearSys.Update)) {
            this.mRearSys.Update = 0;
            updateItem(0, this.mRearSys.Hpxts);
            updateItem(1, this.mRearSys.Hphm);
        }
    }

    public void QueryData() {
        CanJni.ToyotaDjQuery(106, 0);
    }
}
