package com.ts.can.chrysler.wc.compass;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanCompassWcSafeAssSetView extends CanScrollCarInfoView {
    private CanDataInfo.ChrWcSafeAssist mSafeAssistAdt;
    private CanDataInfo.ChrWcSafeAssist mSafeAssistData;

    public CanCompassWcSafeAssSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.ChryslerWcAssistSet(5, item);
                return;
            case 6:
                CanJni.ChryslerWcAssistSet(14, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.ChryslerWcAssistSet(18, Neg(this.mSafeAssistData.RadarVol));
                return;
            case 2:
                CanJni.ChryslerWcAssistSet(12, Neg(this.mSafeAssistData.Zdzczd));
                return;
            case 3:
                CanJni.ChryslerWcAssistSet(9, Neg(this.mSafeAssistData.Yxbcys));
                return;
            case 4:
                CanJni.ChryslerWcAssistSet(7, Neg(this.mSafeAssistData.Yxbcdtydx));
                return;
            case 5:
                CanJni.ChryslerWcAssistSet(16, 1);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_psbcld, R.string.can_lddcsy, R.string.can_zdzczd, R.string.can_jeep_znz_yxbcys, R.string.can_pvyxdtydx, R.string.can_shss, R.string.can_hpsyl};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_type_vol, R.string.can_vol_img};
        this.mPopValueIds[6] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mSafeAssistAdt = new CanDataInfo.ChrWcSafeAssist();
        this.mSafeAssistData = new CanDataInfo.ChrWcSafeAssist();
    }

    public void ResetData(boolean check) {
        CanJni.ChryslerWcGetSafeAssist(this.mSafeAssistAdt, 0);
        CanJni.ChryslerWcGetSafeAssist(this.mSafeAssistData, 1);
        if (i2b(this.mSafeAssistAdt.UpdateOnce) && (!check || i2b(this.mSafeAssistAdt.Update))) {
            this.mSafeAssistAdt.Update = 0;
            showItem(new int[]{this.mSafeAssistAdt.ParkSense, this.mSafeAssistAdt.RadarVol, this.mSafeAssistAdt.Zdzczd, this.mSafeAssistAdt.Yxbcys, this.mSafeAssistAdt.Yxbcdtydx, this.mSafeAssistAdt.Shssyxzdxtfw, this.mSafeAssistAdt.RadarParkSenseVol});
        }
        if (!i2b(this.mSafeAssistData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSafeAssistData.Update)) {
            this.mSafeAssistData.Update = 0;
            updateItem(new int[]{this.mSafeAssistData.ParkSense, this.mSafeAssistData.RadarVol, this.mSafeAssistData.Zdzczd, this.mSafeAssistData.Yxbcys, this.mSafeAssistData.Yxbcdtydx, this.mSafeAssistData.Shssyxzdxtfw, this.mSafeAssistData.RadarParkSenseVol});
        }
    }

    public void QueryData() {
        CanJni.ChryslerWcQuery(1, 67);
    }
}
