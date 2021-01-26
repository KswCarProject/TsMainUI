package com.ts.can.chrysler.wc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanChryslerWcCarDrivAssistSetView extends CanScrollCarInfoView {
    public static final int ITEM_ACTIVE_PARKVIEW_BACKUP = 25;
    public static final int ITEM_FORWARD_COLLISION_SENSITIVITY = 22;
    public static final int ITEM_FORWARD_COLLISION_WARNING = 21;
    public static final int ITEM_GREETING_LIGHT = 27;
    private static final int ITEM_MAX = 27;
    public static final int ITEM_SIDE_DISTANCE_WARNING = 23;
    public static final int ITEM_SIDE_DISTANCE_WARNING_VOLUME = 24;
    public static final int ITEM_TRAFFIC_SIGN_ASSIST = 26;
    private CanDataInfo.ChrWcAirInfo mAirInfo;
    private CanDataInfo.ChrWcSafeAssist mAssistADT;
    private CanDataInfo.ChrWcSafeAssist2 mAssistADT2;
    private CanDataInfo.ChrWcCompassInfo mAssistCompassData;
    private CanDataInfo.ChrWcSafeAssist mAssistData;
    private CanDataInfo.ChrWcSafeAssist2 mAssistData2;

    public CanChryslerWcCarDrivAssistSetView(Activity activity) {
        super(activity, 28);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (item == 8) {
                    CanJni.ChryslerWcCompassSet(1, 15);
                    return;
                } else {
                    CanJni.ChryslerWcCompassSet(1, item);
                    return;
                }
            case 3:
                CanJni.ChryslerWcAssistSet(5, item);
                return;
            case 4:
                CanJni.ChryslerWcAssistSet(4, item);
                return;
            case 5:
                CanJni.ChryslerWcAssistSet(3, item);
                return;
            case 7:
                CanJni.ChryslerWcAssistSet(1, item);
                return;
            case 13:
                CanJni.ChryslerWcAssistSet(6, item);
                return;
            case 14:
                CanJni.ChryslerWcAssistSet(17, item);
                return;
            case 17:
                CanJni.ChryslerWcAssistSet(14, item);
                return;
            case 18:
                CanJni.ChryslerWcAssistSet(13, item);
                return;
            case 21:
                CanJni.ChryslerWcAssistSet(19, item);
                return;
            case 22:
                CanJni.ChryslerWcAssistSet(20, item);
                return;
            case 23:
                CanJni.ChryslerWcAssistSet(21, item);
                return;
            case 24:
                CanJni.ChryslerWcAssistSet(22, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 2) {
            CanJni.ChryslerWcCompassAdjust(pos);
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action != 0) {
            if (1 == action) {
                switch (Id) {
                    case 19:
                        CanJni.ChryslerWcAirKey(22, 0);
                        break;
                }
            }
        } else {
            switch (Id) {
                case 19:
                    CanJni.ChryslerWcAirKey(22, 1);
                    break;
            }
        }
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.ChryslerWcCompassSet(2, 1);
                return;
            case 6:
                CanJni.ChryslerWcAssistSet(2, Neg(this.mAssistData.Qfpzbjzdzd));
                return;
            case 8:
                CanJni.ChryslerWcAssistSet(12, Neg(this.mAssistData.Zdzczd));
                return;
            case 9:
                CanJni.ChryslerWcAssistSet(11, Neg(this.mAssistData.Pdqbfz));
                return;
            case 10:
                CanJni.ChryslerWcAssistSet(10, Neg(this.mAssistData.Ylgysys));
                return;
            case 11:
                CanJni.ChryslerWcAssistSet(7, Neg(this.mAssistData.Yxbcdtydx));
                return;
            case 12:
                CanJni.ChryslerWcAssistSet(8, Neg(this.mAssistData.Yxbcgdydx));
                return;
            case 15:
                CanJni.ChryslerWcAssistSet(16, 1);
                return;
            case 16:
                CanJni.ChryslerWcAssistSet(15, Neg(this.mAssistData.RadarParkSense));
                return;
            case 20:
                CanJni.ChryslerWcAssistSet(18, Neg(this.mAssistData.RadarVol));
                return;
            case 25:
                CanJni.ChryslerWcAssistSet(23, Neg(this.mAssistData2.ActiveParkviewBackup));
                return;
            case 26:
                CanJni.ChryslerWcAssistSet(24, Neg(this.mAssistData2.TrafficSignAssist));
                return;
            case 27:
                CanJni.ChryslerWcAssistSet(25, Neg(this.mAssistData2.GreetingLight));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_znzfxsz, R.string.can_znzjzsz, R.string.can_lppysd, R.string.can_psbcld, R.string.can_jp_cdpljzld, R.string.can_jp_cdpljg, R.string.can_jp_qfpzzdzd, R.string.can_jp_qfpzjg, R.string.can_zdzczd, R.string.can_teramont_pdqbfz, R.string.can_jp_ylgysys, R.string.can_yxdtydx, R.string.can_yxgdydx, R.string.can_jp_mdjb, R.string.can_wc_zdkqssxt, R.string.can_shss, R.string.can_hpsbcfz, R.string.can_hpsyl, R.string.can_qpsyl, R.string.can_jp_hsjtgj, R.string.can_lddcsy, R.string.can_forward_collision_warning, R.string.can_forward_collision_sensitivity, R.string.can_side_distance_warning, R.string.can_side_distance_warning_volume, R.string.can_active_parkview_backup, R.string.can_traffic_sign_assist, R.string.can_greeting_light};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH_TOUCH2, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.array.can_fs_declare_162_direction};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 15;
        iArr2[2] = 1;
        iArr[2] = iArr2;
        this.mPopValueIds[3] = new int[]{R.string.can_type_vol, R.string.can_vol_img, R.string.can_mzd_cx4_mode_off};
        this.mPopValueIds[4] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mPopValueIds[5] = new int[]{R.string.can_jp_early, R.string.can_ac_mid, R.string.can_jp_late};
        this.mPopValueIds[7] = new int[]{R.string.can_sdqfwxjgjl_3, R.string.can_sdqfwxjgjl_1};
        this.mPopValueIds[13] = new int[]{R.string.can_Scsfctsy_3, R.string.can_light, R.string.can_jp_cdjbj};
        this.mPopValueIds[14] = new int[]{R.string.can_Scsfctsy_3, R.string.can_jp_yc, R.string.can_jp_qb};
        this.mPopValueIds[17] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mPopValueIds[18] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mPopValueIds[21] = new int[]{R.string.can_Scsfctsy_3, R.string.can_only_active_braking, R.string.can_warn_active_braking};
        this.mPopValueIds[22] = new int[]{R.string.can_sdqfwxjgjl_3, R.string.can_sdqfwxjgjl_2, R.string.can_sdqfwxjgjl_1};
        this.mPopValueIds[23] = new int[]{R.string.can_Scsfctsy_3, R.string.can_type_vol, R.string.can_vol_img};
        this.mPopValueIds[24] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mAssistData = new CanDataInfo.ChrWcSafeAssist();
        this.mAssistADT = new CanDataInfo.ChrWcSafeAssist();
        this.mAssistCompassData = new CanDataInfo.ChrWcCompassInfo();
        this.mAirInfo = new CanDataInfo.ChrWcAirInfo();
        this.mAssistData2 = new CanDataInfo.ChrWcSafeAssist2();
        this.mAssistADT2 = new CanDataInfo.ChrWcSafeAssist2();
    }

    public void ResetData(boolean check) {
        CanJni.ChryslerWcGetSafeAssist(this.mAssistADT, 0);
        CanJni.ChryslerWcGetSafeAssist(this.mAssistData, 1);
        CanJni.ChryslerWcGetCompassInfo(this.mAssistCompassData);
        CanJni.ChryslerWcGetAirInfo(this.mAirInfo);
        CanJni.ChryslerWcGetSafeAssist2(this.mAssistADT2, 0);
        CanJni.ChryslerWcGetSafeAssist2(this.mAssistData2, 1);
        if (i2b(this.mAssistADT.UpdateOnce) && (!check || i2b(this.mAssistADT.Update))) {
            this.mAssistADT.Update = 0;
            showItem(new int[]{this.mAssistADT.Znzfxsz, this.mAssistADT.Znzjzsz, this.mAssistADT.Lppysd, this.mAssistADT.ParkSense, this.mAssistADT.Cdpljzld, this.mAssistADT.Cdpljg, this.mAssistADT.Qfpzbjzdzd, this.mAssistADT.Qfpzjg, this.mAssistADT.Zdzczd, this.mAssistADT.Pdqbfz, this.mAssistADT.Ylgysys, this.mAssistADT.Yxbcdtydx, this.mAssistADT.Yxbcgdydx, this.mAssistADT.Mdbj, this.mAssistADT.Zdkqssxt, this.mAssistADT.Shssyxzdxtfw, this.mAssistADT.RadarParkSense, this.mAssistADT.RadarParkSenseVol, this.mAssistADT.FrontParkSenseVol, 1, this.mAssistADT.RadarVol});
        }
        if (i2b(this.mAssistData.UpdateOnce) && (!check || i2b(this.mAssistData.Update))) {
            this.mAssistData.Update = 0;
            int[] values = new int[19];
            values[0] = this.mAssistData.Znzfxsz;
            values[1] = this.mAssistData.Znzjzsz;
            values[3] = this.mAssistData.ParkSense;
            values[4] = this.mAssistData.Cdpljzld;
            values[5] = this.mAssistData.Cdpljg;
            values[6] = this.mAssistData.Qfpzbjzdzd;
            values[7] = this.mAssistData.Qfpzjg;
            values[8] = this.mAssistData.Zdzczd;
            values[9] = this.mAssistData.Pdqbfz;
            values[10] = this.mAssistData.Ylgysys;
            values[11] = this.mAssistData.Yxbcdtydx;
            values[12] = this.mAssistData.Yxbcgdydx;
            values[13] = this.mAssistData.Mdbj;
            values[14] = this.mAssistData.Zdkqssxt;
            values[15] = this.mAssistData.Shssyxzdxtfw;
            values[16] = this.mAssistData.RadarParkSense;
            values[17] = this.mAssistData.RadarParkSenseVol;
            values[18] = this.mAssistData.FrontParkSenseVol;
            updateItem(values);
            updateItem(20, this.mAssistData.RadarVol);
        }
        if (i2b(this.mAssistCompassData.UpdateOnce) && (!check || i2b(this.mAssistCompassData.Update))) {
            this.mAssistCompassData.Update = 0;
            updateItem(2, this.mAssistCompassData.Lppyl);
        }
        if (i2b(this.mAirInfo.UpdateOnce) && (!check || i2b(this.mAirInfo.Update))) {
            this.mAirInfo.Update = 0;
            updateItem(19, this.mAirInfo.Hsjtgj);
        }
        if (i2b(this.mAssistADT2.UpdateOnce) && (!check || i2b(this.mAssistADT2.Update))) {
            this.mAssistADT2.Update = 0;
            showItem(21, this.mAssistADT2.ForwardCollisionWarning);
            showItem(22, this.mAssistADT2.ForwardCollisionSensitivity);
            showItem(23, this.mAssistADT2.SideDistanceWarning);
            showItem(24, this.mAssistADT2.SideDistanceWarningVolume);
            showItem(25, this.mAssistADT2.ActiveParkviewBackup);
            showItem(26, this.mAssistADT2.TrafficSignAssist);
            showItem(27, this.mAssistADT2.GreetingLight);
        }
        if (!i2b(this.mAssistData2.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAssistData2.Update)) {
            this.mAssistData2.Update = 0;
            updateItem(21, this.mAssistData2.ForwardCollisionWarning);
            updateItem(22, this.mAssistData2.ForwardCollisionSensitivity);
            updateItem(23, this.mAssistData2.SideDistanceWarning);
            updateItem(24, this.mAssistData2.SideDistanceWarningVolume);
            updateItem(25, this.mAssistData2.ActiveParkviewBackup);
            updateItem(26, this.mAssistData2.TrafficSignAssist);
            updateItem(27, this.mAssistData2.GreetingLight);
        }
    }

    public void QueryData() {
        CanJni.ChryslerWcQuery(1, 67);
        Sleep(10);
        CanJni.ChryslerWcQuery(1, 69);
    }
}
