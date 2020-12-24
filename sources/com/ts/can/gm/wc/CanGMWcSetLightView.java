package com.ts.can.gm.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGMWcSetLightView extends CanScrollCarInfoView {
    private CanDataInfo.GmWc_LightData mLightAdt;
    private CanDataInfo.GmWc_LightData mLightData;

    public CanGMWcSetLightView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.GmWcCarLightSet(2, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.GmWcCarLightSet(1, Neg(this.mLightData.Xcdgn));
                return;
            case 4:
                CanJni.GmWcCarLightSet(3, Neg(this.mLightData.Cldwd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_xcd, R.string.can_lsddys, R.string.can_ill_zzxd, R.string.can_ill_yzxd, R.string.can_positioning_light};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[1] = new int[]{R.string.can_off, R.string.can_30s, R.string.can_60s, R.string.can_mzd_cx4_time_120s};
        this.mItemVisibles = new int[5];
        this.mLightAdt = new CanDataInfo.GmWc_LightData();
        this.mLightData = new CanDataInfo.GmWc_LightData();
    }

    public void ResetData(boolean check) {
        CanJni.GmWcGetCarLightSetAdt(this.mLightAdt);
        CanJni.GmWcGetCarLightSet(this.mLightData);
        if (i2b(this.mLightAdt.UpdateOnce) && (!check || i2b(this.mLightAdt.Update))) {
            this.mLightAdt.Update = 0;
            showItem(new int[]{this.mLightAdt.Xcdgn, this.mLightAdt.Lsddys, this.mLightAdt.Zzxd, this.mLightAdt.Rzxd, this.mLightAdt.Cldwd});
        }
        if (!i2b(this.mLightData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLightData.Update)) {
            this.mLightData.Update = 0;
            updateItem(new int[]{this.mLightData.Xcdgn, this.mLightData.Lsddys, this.mLightData.Zzxd, this.mLightData.Rzxd, this.mLightData.Cldwd});
        }
    }

    public void QueryData() {
        CanJni.GmWcCarQuery(5, 1, 103);
    }
}
