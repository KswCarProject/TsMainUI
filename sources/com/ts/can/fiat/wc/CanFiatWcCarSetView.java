package com.ts.can.fiat.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFiatWcCarSetView extends CanScrollCarInfoView {
    private static final int BCFZ = 3;
    private static final int BCFZYL = 4;
    private static final int CMZDSD = 0;
    private static final int COURTESY_LIGHTS_DELAY = 11;
    private static final int DDYC = 9;
    private static final int HSCKX = 8;
    private static final int HSYC = 10;
    private static final int RJXCD = 1;
    private static final int SCSD = 7;
    private static final int TRIPB = 2;
    private static final int YBL = 5;
    private static final int ZXFZD = 6;
    CanDataInfo.FiatAllWcSet mAdtData;
    CanDataInfo.FiatAllWcSet mSetData;

    public CanFiatWcCarSetView(Activity activity) {
        super(activity, 12);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CanJni.FiatAllWcCarSet(17, item);
                return;
            case 4:
                CanJni.FiatAllWcCarSet(18, item);
                return;
            case 9:
                CanJni.FiatAllWcCarSet(23, item);
                return;
            case 11:
                CanJni.FiatAllWcCarSet(25, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.FiatAllWcCarSet(12, Neg(this.mSetData.Cmzdsd));
                return;
            case 1:
                CanJni.FiatAllWcCarSet(5, Neg(this.mSetData.Rjxcd));
                return;
            case 2:
                CanJni.FiatAllWcCarSet(16, Neg(this.mSetData.Tripb));
                return;
            case 5:
                CanJni.FiatAllWcCarSet(19, Neg(this.mSetData.CourtesyLigts));
                return;
            case 6:
                CanJni.FiatAllWcCarSet(20, Neg(this.mSetData.CorningLights));
                return;
            case 7:
                CanJni.FiatAllWcCarSet(21, Neg(this.mSetData.FlashLightsWithLock));
                return;
            case 8:
                CanJni.FiatAllWcCarSet(22, Neg(this.mSetData.RearViewCameraGuidelines));
                return;
            case 10:
                CanJni.FiatAllWcCarSet(24, Neg(this.mSetData.RearViewCameraDelay));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_zmzdsd, R.string.can_rjxcd, R.string.can_tripb_disp, R.string.can_bcfz, R.string.can_bcfzyl, R.string.can_yb_light, R.string.can_zxfzd, R.string.can_scsd, R.string.can_hsckx, R.string.can_cch6_dingdeng_delaytime, R.string.can_hsyc, R.string.can_courtesylightdelay};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[3] = new int[]{R.string.can_type_vol, R.string.can_vol_img};
        this.mPopValueIds[4] = new int[]{R.string.can_sensitivity_low, R.string.can_sensitivity_mid, R.string.can_sensitivity_high};
        this.mPopValueIds[9] = new int[]{R.string.can_headlightautoofftime_0s, R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s, R.string.can_headlightautoofftime_90s};
        this.mPopValueIds[11] = new int[]{R.string.can_headlightautoofftime_0s, R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s, R.string.can_headlightautoofftime_90s};
        this.mSetData = new CanDataInfo.FiatAllWcSet();
        this.mAdtData = new CanDataInfo.FiatAllWcSet();
    }

    public void ResetData(boolean check) {
        CanJni.FiatAllWcGetSetData(this.mAdtData, 0);
        if (i2b(this.mAdtData.UpdateOnce) && (!check || i2b(this.mAdtData.Update))) {
            this.mAdtData.Update = 0;
            showItem(new int[]{this.mAdtData.Cmzdsd, this.mAdtData.Rjxcd, this.mAdtData.Tripb, this.mAdtData.ParkSense, this.mAdtData.RearParkSenseVol, this.mAdtData.CourtesyLigts, this.mAdtData.CorningLights, this.mAdtData.FlashLightsWithLock, this.mAdtData.RearViewCameraGuidelines, this.mAdtData.HeadlightOffDelay, this.mAdtData.RearViewCameraDelay, this.mAdtData.CourtesyLightDelay});
        }
        CanJni.FiatAllWcGetSetData(this.mSetData, 1);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Cmzdsd, this.mSetData.Rjxcd, this.mSetData.Tripb, this.mSetData.ParkSense, this.mSetData.RearParkSenseVol, this.mSetData.CourtesyLigts, this.mSetData.CorningLights, this.mSetData.FlashLightsWithLock, this.mSetData.RearViewCameraGuidelines, this.mSetData.HeadlightOffDelay, this.mSetData.RearViewCameraDelay, this.mSetData.CourtesyLightDelay});
        }
    }

    public void QueryData() {
    }
}
