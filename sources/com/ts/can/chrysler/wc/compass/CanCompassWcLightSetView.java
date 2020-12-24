package com.ts.can.chrysler.wc.compass;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanCompassWcLightSetView extends CanScrollCarInfoView {
    private CanDataInfo.ChrWcLightMoto mLightAdt;
    private CanDataInfo.ChrWcLightMoto mLightData;

    public CanCompassWcLightSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.ChryslerWcLightMotoSet(1, item);
        } else if (id == 3) {
            CanJni.ChryslerWcLightMotoSet(8, item + 1);
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 6) {
            CanJni.ChryslerWcLightMotoSet(11, pos);
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.ChryslerWcLightMotoSet(5, Neg(this.mLightData.Rjxsd));
                return;
            case 2:
                CanJni.ChryslerWcLightMotoSet(6, Neg(this.mLightData.Scszxdss));
                return;
            case 4:
                CanJni.ChryslerWcLightMotoSet(9, Neg(this.mLightData.Jskqcd));
                return;
            case 5:
                CanJni.ChryslerWcLightMotoSet(10, Neg(this.mLightData.Zjfzd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ddgbyc, R.string.can_tigger7_day_light, R.string.can_scszxdss, R.string.can_headlightsens, R.string.can_zyx_jskqdg, R.string.can_corneringLights, R.string.can_wc_car_inner_light};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS};
        this.mPopValueIds[0] = new int[]{R.string.can_0s, R.string.can_30s, R.string.can_60s, R.string.can_90s};
        this.mPopValueIds[3] = new int[]{R.string.can_headlightsens_1, R.string.can_headlightsens_2, R.string.can_headlightsens_3};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 6;
        iArr2[2] = 1;
        iArr[6] = iArr2;
        this.mLightAdt = new CanDataInfo.ChrWcLightMoto();
        this.mLightData = new CanDataInfo.ChrWcLightMoto();
    }

    public void ResetData(boolean check) {
        CanJni.ChryslerWcGetLightMoto(this.mLightAdt, 0);
        CanJni.ChryslerWcGetLightMoto(this.mLightData, 1);
        if (i2b(this.mLightAdt.UpdateOnce) && (!check || i2b(this.mLightAdt.Update))) {
            this.mLightAdt.Update = 0;
            showItem(new int[]{this.mLightAdt.Ddgbyc, this.mLightAdt.Rjxsd, this.mLightAdt.Scszxdss, this.mLightAdt.Qzdlmd, this.mLightAdt.Jskqcd, this.mLightAdt.Zjfzd, this.mLightAdt.Cnfwd});
        }
        if (!i2b(this.mLightData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLightData.Update)) {
            this.mLightData.Update = 0;
            updateItem(new int[]{this.mLightData.Ddgbyc, this.mLightData.Rjxsd, this.mLightData.Scszxdss, this.mLightData.Qzdlmd - 1, this.mLightData.Jskqcd, this.mLightData.Zjfzd, this.mLightData.Cnfwd});
        }
    }

    public void QueryData() {
        CanJni.ChryslerWcQuery(1, 98);
    }
}
