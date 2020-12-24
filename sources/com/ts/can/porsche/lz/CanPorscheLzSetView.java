package com.ts.can.porsche.lz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanPorscheLzSetView extends CanScrollCarInfoView {
    private static final int ITEM_AIR_CON_AUTO_AIR_CIRC = 11;
    private static final int ITEM_AIR_CON_CLIMATE_STYLE = 12;
    private static final int ITEM_AIR_CON_VENT_PANEL = 10;
    private static final int ITEM_COMFORT_ENTRY = 9;
    private static final int ITEM_DOOR_LOCK = 8;
    private static final int ITEM_DOOR_UNLOCK = 7;
    private static final int ITEM_EXT_LIGHTS_DAYTIME_LIGHT = 3;
    private static final int ITEM_EXT_LIGHTS_FADE_OUT_TIME = 1;
    private static final int ITEM_INT_LIGHTS_AMBIENT_LIGHT = 2;
    private static final int ITEM_INT_LIGHTS_FADE_OUT_TIME = 0;
    private static final int ITEM_MAX = 13;
    private static final int ITEM_REAR_WIPER = 5;
    private static final int ITEM_REVERSING_OPTIONS = 6;
    private static final int ITEM_WIPER_RAIN_SENSOR = 4;
    private CanDataInfo.CanPorCheLz_SetData mData;

    public CanPorscheLzSetView(Activity activity) {
        super(activity, 13);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 4:
                CanJni.PorscheLzCarSet(4, item);
                return;
            case 5:
                CanJni.PorscheLzCarSet(5, item);
                return;
            case 7:
                CanJni.PorscheLzCarSet(7, item);
                return;
            case 8:
                CanJni.PorscheLzCarSet(8, item);
                return;
            case 12:
                CanJni.PorscheLzCarSet(17, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.PorscheLzCarSet(2, pos);
                return;
            case 1:
                CanJni.PorscheLzCarSet(1, pos);
                return;
            case 2:
                CanJni.PorscheLzCarSet(3, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                CanJni.PorscheLzCarSet(0, Neg(this.mData.ExtLightDaytimeLight));
                return;
            case 6:
                CanJni.PorscheLzCarSet(6, Neg(this.mData.ReverOpitons));
                return;
            case 9:
                CanJni.PorscheLzCarSet(10, Neg(this.mData.ComfortEntry));
                return;
            case 10:
                CanJni.PorscheLzCarSet(9, Neg(this.mData.AirConVentPanel));
                return;
            case 11:
                CanJni.PorscheLzCarSet(11, Neg(this.mData.AirConAutoAirCirc));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_int_lights_fade_out_time, R.string.can_ext_lights_fade_out_time, R.string.can_environment_light, R.string.can_tigger7_day_light, R.string.can_zdys, R.string.can_rear_wiper, R.string.can_mirrors, R.string.can_cmjsfs, R.string.can_tigger7_auto_lock, R.string.can_zybljc, R.string.can_tfq, R.string.can_auto_recirculate, R.string.can_ac_mode};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 30;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[0] = 1;
        iArr4[1] = 12;
        iArr4[2] = 1;
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 10;
        iArr6[2] = 1;
        iArr5[2] = iArr6;
        this.mPopValueIds[4] = new int[]{R.string.can_mzd_cx4_drive_auto, R.string.can_mzd_cx4_drive_owner};
        this.mPopValueIds[5] = new int[]{R.string.can_mzd_cx4_drive_auto, R.string.can_mzd_cx4_drive_owner};
        this.mPopValueIds[7] = new int[]{R.string.can_sym, R.string.can_jsym};
        this.mPopValueIds[8] = new int[]{R.string.can_trunk_close, R.string.can_after_ignition_on, R.string.can_xczdls};
        this.mPopValueIds[12] = new int[]{R.string.can_air_light, R.string.can_air_medium, R.string.can_air_strong};
        this.mData = new CanDataInfo.CanPorCheLz_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.PorscheLzGetCarSetData(this.mData);
        if (!i2b(this.mData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mData.Update)) {
            this.mData.Update = 0;
            updateItem(0, this.mData.IntLightFadeOutTime, String.valueOf(this.mData.IntLightFadeOutTime * 10) + " S");
            updateItem(1, this.mData.ExtLightsFadeOutTime, String.valueOf(this.mData.ExtLightsFadeOutTime * 10) + " S");
            updateItem(2, this.mData.IntLightAmbLight, String.valueOf(this.mData.IntLightAmbLight * 10) + " %");
            updateItem(3, this.mData.ExtLightDaytimeLight);
            updateItem(4, this.mData.WiperRainSensor);
            updateItem(5, this.mData.RearWiper);
            updateItem(6, this.mData.ReverOpitons);
            updateItem(7, this.mData.DoorUnlock);
            updateItem(8, this.mData.DoorLock);
            updateItem(9, this.mData.ComfortEntry);
            updateItem(10, this.mData.AirConVentPanel);
            updateItem(11, this.mData.AirConAutoAirCirc);
            updateItem(12, this.mData.AirConClimateStyle);
        }
    }

    public void QueryData() {
        CanJni.PorscheLzQuery(56);
        Sleep(5);
    }
}
