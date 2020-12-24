package com.ts.can.subuar.xp;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSubuarDrivingAidsView extends CanScrollCarInfoView {
    private CanDataInfo.SubuarXp_DrivingAids mDrivingAids;

    public CanSubuarDrivingAidsView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.SubuarXpCarSet(1, Neg(this.mDrivingAids.Bcfzxtzt), 0);
        } else if (id == 1) {
            CanJni.SubuarXpCarSet(2, Neg(this.mDrivingAids.RAB), 0);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_bcfz, R.string.can_rabzt};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mDrivingAids = new CanDataInfo.SubuarXp_DrivingAids();
    }

    public void ResetData(boolean check) {
        CanJni.SubuarXpGetDrivingAids(this.mDrivingAids);
        if (!i2b(this.mDrivingAids.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDrivingAids.Update)) {
            this.mDrivingAids.Update = 0;
            updateItem(new int[]{this.mDrivingAids.Bcfzxtzt, this.mDrivingAids.RAB});
        }
    }

    public void QueryData() {
        CanJni.SubuarXpQuery(37, 0);
    }
}
