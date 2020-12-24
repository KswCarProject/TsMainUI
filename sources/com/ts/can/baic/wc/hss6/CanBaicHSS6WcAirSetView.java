package com.ts.can.baic.wc.hss6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBaicHSS6WcAirSetView extends CanScrollCarInfoView {
    private CanDataInfo.BaicHsS6AirSet mAdtData;
    private CanDataInfo.BaicHsS6AirSet mSetData;

    public CanBaicHSS6WcAirSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        if (id == 1) {
            CanJni.BaicWcS6AirKey(5, pos);
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.BaicWcS6AirKey(6, Neg(this.mSetData.Yckqkt));
                return;
            case 2:
                CanJni.BaicWcS6AirKey(4, Neg(this.mSetData.Ktgwnxh));
                return;
            case 3:
                CanJni.BaicWcS6AirKey(3, Neg(this.mSetData.Zdfl));
                return;
            case 4:
                CanJni.BaicWcS6AirKey(2, Neg(this.mSetData.Aczdms));
                return;
            case 5:
                CanJni.BaicWcS6AirKey(1, Neg(this.mSetData.Zczdnxh));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_gl8_2017_ktycqd, R.string.can_lythjfs, R.string.can_hightemp_loop, R.string.can_auto_wind, R.string.can_auto_ac, R.string.can_auto_zhuche};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 7;
        iArr2[2] = 1;
        iArr[1] = iArr2;
        this.mAdtData = new CanDataInfo.BaicHsS6AirSet();
        this.mSetData = new CanDataInfo.BaicHsS6AirSet();
    }

    public void ResetData(boolean check) {
        CanJni.BaicWcS6GetAirSet(this.mAdtData, 0);
        if (i2b(this.mAdtData.UpdateOnce) && (!check || i2b(this.mAdtData.Update))) {
            this.mAdtData.Update = 0;
            showItem(new int[]{this.mAdtData.Yckqkt, this.mAdtData.Lythzdjfs, this.mAdtData.Ktgwnxh, this.mAdtData.Zdfl, this.mAdtData.Aczdms, this.mAdtData.Zczdnxh});
        }
        CanJni.BaicWcS6GetAirSet(this.mSetData, 1);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Yckqkt, this.mSetData.Lythzdjfs, this.mSetData.Ktgwnxh, this.mSetData.Zdfl, this.mSetData.Aczdms, this.mSetData.Zczdnxh});
        }
    }

    public void QueryData() {
        CanJni.BaicWcS6Query(5, 1, 102);
    }
}
