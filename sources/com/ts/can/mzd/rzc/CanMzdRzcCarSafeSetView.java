package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanMzdRzcCarSafeSetView extends CanScrollCarInfoView {
    private static final int BLIND_SPOT_MONITOR = 16;
    private static final int BLIND_SPOT_MONITOR_JGYL = 18;
    private static final int BLIND_SPOT_MONITOR_XTSD = 17;
    private static final int DIS_REC_SUP_SYS = 7;
    private static final int DIS_REC_SUP_SYS_JGJL = 9;
    private static final int DIS_REC_SUP_SYS_RESET = 10;
    private static final int DIS_REC_SUP_SYS_XSMS = 8;
    private static final int ITEM_360QJSZ = 47;
    private static final int ITEM_AQJB = 42;
    private static final int ITEM_AQJB_RESET = 46;
    private static final int ITEM_BLIND_SPOT_MONITORJB = 44;
    private static final int ITEM_DTLX = 49;
    private static final int ITEM_HFJHJTJB = 43;
    private static final int ITEM_JBDS = 45;
    private static final int ITEM_JSYFZXT_RESET = 35;
    private static final int ITEM_QSXTST = 50;
    private static final int ITEM_TBWYBJ_SW = 41;
    private static final int ITEM_XHCZTSY = 34;
    private static final int ITEM_ZCCGQBJYL = 38;
    private static final int ITEM_ZCCGQXSYD = 37;
    private static final int ITEM_ZCCGQ_RESET = 39;
    private static final int ITEM_ZDXS360_VIEW_MONITOR = 48;
    private static final int LANE_DEP_WARN_SYS = 24;
    private static final int LANE_DEP_WARN_SYS_EQ = 27;
    private static final int LANE_DEP_WARN_SYS_EQ_CX5 = 31;
    private static final int LANE_DEP_WARN_SYS_GY = 29;
    private static final int LANE_DEP_WARN_SYS_JG = 26;
    private static final int LANE_DEP_WARN_SYS_JS = 25;
    private static final int LANE_DEP_WARN_SYS_LLS = 28;
    private static final int LANE_DEP_WARN_SYS_RESET = 32;
    private static final int LANE_DEP_WARN_SYS_XT = 30;
    private static final int REAR_VEHICLE_MONITO = 5;
    private static final int REAR_VEHICLE_MONITOR_VOL = 6;
    private static final int SBS_SCBS = 11;
    private static final int SBS_SCBS_JGJL = 13;
    private static final int SBS_SCBS_JGYL = 14;
    private static final int SBS_SCBS_RESET = 15;
    private static final int SBS_SCBS_XSMS = 12;
    private static final int SMART_BRAKE_SUP = 0;
    private static final int SMART_BRAKE_SUP_CX5 = 3;
    private static final int SMART_BRAKE_SUP_JGJL = 2;
    private static final int SMART_BRAKE_SUP_M3 = 1;
    private static final int SMART_BRAKE_SUP_RESET = 4;
    private static final int SXJG = 19;
    private static final int SXJG_JSFZ = 22;
    private static final int SXJG_RESET = 23;
    private static final int SXJG_SXJS = 21;
    private static final int SXJG_SXXS = 20;
    private static final int TITLE_JSYFZXT = 33;
    private static final int TITLE_TBWYBJ = 40;
    private static final int TITLE_ZCCGQ = 36;
    private CanDataInfo.Mzd_Rzc_SafeSet mSafeSetData;

    public CanMzdRzcCarSafeSetView(Activity activity) {
        super(activity, 51);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.MzdRzcCarSafeSet(1, item + 1, 0);
                return;
            case 2:
                CanJni.MzdRzcCarSafeSet(1, item + 17, 0);
                return;
            case 3:
                CanJni.MzdRzcCarSafeSet(1, item + 33, 0);
                return;
            case 6:
                CanJni.MzdRzcCarSafeSet(2, item + 1, 0);
                return;
            case 9:
                CanJni.MzdRzcCarSafeSet(3, 2, item + 1);
                return;
            case 13:
                CanJni.MzdRzcCarSafeSet(4, 2, item + 1);
                return;
            case 14:
                CanJni.MzdRzcCarSafeSet(4, 3, item + 1);
                return;
            case 18:
                CanJni.MzdRzcCarSafeSet(5, 2, item + 1);
                return;
            case 21:
                CanJni.MzdRzcCarSafeSet(7, 2, item + 1);
                return;
            case 22:
                CanJni.MzdRzcCarSafeSet(7, 3, item + 1);
                return;
            case 25:
                CanJni.MzdRzcCarSafeSet(6, 1, item + 1);
                return;
            case 26:
                CanJni.MzdRzcCarSafeSet(6, 2, item + 1);
                return;
            case 27:
                CanJni.MzdRzcCarSafeSet(6, 3, item + 1);
                return;
            case 28:
                CanJni.MzdRzcCarSafeSet(6, 4, item + 1);
                return;
            case 31:
                CanJni.MzdRzcCarSafeSet(6, 3, item + 2);
                return;
            case 38:
                CanJni.MzdRzcCarSafeSet(9, 2, item + 1);
                return;
            case 44:
                CanJni.MzdRzcCarSafeSet(11, 2, item + 1);
                return;
            case 45:
                CanJni.MzdRzcCarSafeSet(11, 3, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 4:
                setOnReset(id, 1, 48);
                return;
            case 8:
                CanJni.MzdRzcCarSafeSet(3, 1, Neg(this.mSafeSetData.DisRecSupportSys_Xsms) + 1);
                return;
            case 10:
                setOnReset(id, 3, 3);
                return;
            case 12:
                CanJni.MzdRzcCarSafeSet(4, 1, Neg(this.mSafeSetData.SBS_SCBS_Xsms) + 1);
                return;
            case 15:
                setOnReset(id, 4, 4);
                return;
            case 17:
                CanJni.MzdRzcCarSafeSet(5, 1, Neg(this.mSafeSetData.BlindSpMon_Xtsd) + 1);
                return;
            case 20:
                CanJni.MzdRzcCarSafeSet(7, 1, Neg(this.mSafeSetData.SpeedWarm_Sxxs) + 1);
                return;
            case 23:
                setOnReset(id, 7, 4);
                return;
            case 29:
                CanJni.MzdRzcCarSafeSet(6, 6, Neg(this.mSafeSetData.LaneDepWarnSys_Gy) + 1);
                return;
            case 30:
                CanJni.MzdRzcCarSafeSet(6, 7, Neg(this.mSafeSetData.LaneDepWarnSys_Xt) + 1);
                return;
            case 32:
                setOnReset(id, 6, 5);
                return;
            case 34:
                CanJni.MzdRzcCarSafeSet(8, 1, Neg(this.mSafeSetData.Xhcztsy) + 1);
                return;
            case 35:
                setOnReset(id, 8, 4);
                return;
            case 37:
                CanJni.MzdRzcCarSafeSet(9, 1, Neg(this.mSafeSetData.Zccgqxsyd) + 1);
                return;
            case 39:
                setOnReset(id, 9, 4);
                return;
            case 41:
                if (this.mSafeSetData.Tbwybj > 0) {
                    CanJni.MzdRzcCarSafeSet(10, 2, 0);
                    return;
                } else {
                    CanJni.MzdRzcCarSafeSet(10, 1, 0);
                    return;
                }
            case 43:
                CanJni.MzdRzcCarSafeSet(11, 1, Neg(this.mSafeSetData.Hfjhjtjb) + 1);
                return;
            case 46:
                setOnReset(id, 11, 4);
                return;
            case 48:
                CanJni.MzdRzcCarSafeSet(12, 1, Neg(this.mSafeSetData.Zdxs360ViewMonitor) + 1);
                return;
            case 49:
                CanJni.MzdRzcCarSafeSet(12, 2, Neg(this.mSafeSetData.Dtlx) + 1);
                return;
            case 50:
                CanJni.MzdRzcCarSafeSet(12, 3, Neg(this.mSafeSetData.Qsxtst) + 1);
                return;
            default:
                return;
        }
    }

    private void setOnReset(int id, final int cmd, final int para) {
        new CanItemMsgBox(id, getActivity(), R.string.can_sure_reset, new CanItemMsgBox.onMsgBoxClick() {
            public void onOK(int param) {
                CanJni.MzdRzcCarSafeSet(cmd, para, 0);
            }
        }, (CanItemMsgBox.onMsgBoxClick2) null);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_smart_city_brake_sup, R.string.can_jgyl_m3, R.string.can_jgjl, R.string.can_jgyl_cx5, R.string.can_reset, R.string.can_rear_vehicle_monitor, R.string.can_vol, R.string.can_dis_r_sup_sys, R.string.can_xsms, R.string.can_jgjl, R.string.can_reset, R.string.can_sbs_scbs, R.string.can_xsms, R.string.can_jgjl, R.string.can_jgyl, R.string.can_reset, R.string.can_blind_spod_mointor, R.string.can_xtsd, R.string.can_jgyl, R.string.can_sxjg, R.string.can_sxxs, R.string.can_sxjs, R.string.can_jsfaz, R.string.can_reset, R.string.can_lane_departure_warn_sys, R.string.can_js, R.string.can_jg, R.string.can_yxsd, R.string.can_llsbbs, R.string.can_gy, R.string.can_lane_departure_xt, R.string.can_yxsd_cx5, R.string.can_reset, R.string.can_drivet_assist, R.string.can_xhcztsy, R.string.can_rw_rx5_hfccsz, R.string.can_teramont_zcfz, R.string.can_zccgqxsyd, R.string.can_hzccgqbjyl, R.string.can_rw_rx5_hfccsz, R.string.can_tbwybj, R.string.can_car_active, R.string.can_mzd_wc_aqjb, R.string.can_mzd_wc_hfjhjtjb, R.string.can_blind_spod_mointor, R.string.can_mzd_wc_jbds, R.string.can_rw_rx5_hfccsz, R.string.can_honda_qjyxxtsz, R.string.can_zdxs360_view_monitor, R.string.can_dtlx, R.string.can_front_camera_view};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[1] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_teramont_open};
        this.mPopValueIds[6] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_high};
        this.mPopValueIds[2] = new int[]{R.string.can_sdqfwxjgjl_4, R.string.can_medium_z_d, R.string.can_sdqfwxjgjl_5};
        this.mPopValueIds[3] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_high};
        this.mPopValueIds[9] = new int[]{R.string.can_sdqfwxjgjl_4, R.string.can_medium_z_d, R.string.can_sdqfwxjgjl_5};
        this.mPopValueIds[13] = new int[]{R.string.can_sdqfwxjgjl_4, R.string.can_medium_z_d, R.string.can_sdqfwxjgjl_5};
        this.mPopValueIds[14] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_high};
        this.mPopValueIds[18] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_high};
        this.mPopValueIds[25] = new int[]{R.string.can_zdtz, R.string.can_early, R.string.can_air_medium, R.string.can_late};
        this.mPopValueIds[26] = new int[]{R.string.can_usually, R.string.can_air_medium, R.string.can_rarely};
        this.mPopValueIds[27] = new int[]{R.string.can_bbs, R.string.can_lls};
        this.mPopValueIds[28] = new int[]{R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
        this.mPopValueIds[21] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_vision, R.string.can_yxysj};
        this.mPopValueIds[22] = new int[]{R.array.can_mzd_cx4_jsfz};
        this.mPopValueIds[31] = new int[]{R.string.can_fzd, R.string.can_zd};
        this.mPopValueIds[31] = new int[]{R.string.can_fzd, R.string.can_zd};
        this.mPopValueIds[38] = new int[]{R.string.can_mzd_cx4_voice_high, R.string.can_mzd_cx4_voice_middle, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_mode_off};
        this.mPopValueIds[44] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_vision, R.string.can_mzd_wc_sjjtj};
        this.mPopValueIds[45] = new int[]{R.string.can_cdpyyzxt_4, R.string.can_cdpyyzxt_1, R.string.can_late};
        if (CanJni.GetSubType() != 5) {
            this.mItemVisibles[47] = 0;
            this.mItemVisibles[48] = 0;
            this.mItemVisibles[49] = 0;
            this.mItemVisibles[50] = 0;
        }
        this.mSafeSetData = new CanDataInfo.Mzd_Rzc_SafeSet();
    }

    public void ResetData(boolean check) {
        CanJni.MzdRzcGetCarSafeSet(this.mSafeSetData);
        if (!i2b(this.mSafeSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSafeSetData.Update)) {
            this.mSafeSetData.Update = 0;
            updateItem(1, this.mSafeSetData.SmartCityBrakeSupport);
            updateItem(2, this.mSafeSetData.SmartCityBrakeSupport2 - 1);
            updateItem(3, this.mSafeSetData.SmartCityBrakeSupport3 - 1);
            updateItem(6, this.mSafeSetData.RearVehMon - 1);
            updateItem(8, this.mSafeSetData.DisRecSupportSys_Xsms);
            updateItem(9, this.mSafeSetData.DisRecSupportSys_Jgjl - 1);
            updateItem(12, this.mSafeSetData.SBS_SCBS_Xsms);
            updateItem(13, this.mSafeSetData.SBS_SCBS2_Jgjl - 1);
            updateItem(14, this.mSafeSetData.SBS_SCBS2_Jgyl - 1);
            updateItem(17, this.mSafeSetData.BlindSpMon_Xtsd);
            updateItem(18, this.mSafeSetData.BlindSpMon_Jgyl - 1);
            updateItem(20, this.mSafeSetData.SpeedWarm_Sxxs);
            updateItem(21, this.mSafeSetData.SpeedWarm_Sxjs - 1);
            updateItem(22, this.mSafeSetData.SpeedWarm_Jsqz - 1);
            updateItem(25, this.mSafeSetData.LaneDepWarnSys_Js - 1);
            updateItem(26, this.mSafeSetData.LaneDepWarnSys_Jg - 1);
            updateItem(27, this.mSafeSetData.LaneDepWarnSys_EQ);
            updateItem(28, this.mSafeSetData.LaneDepWarnSys_Lls - 1);
            updateItem(29, this.mSafeSetData.LaneDepWarnSys_Gy);
            updateItem(30, this.mSafeSetData.LaneDepWarnSys_Xt);
            updateItem(31, this.mSafeSetData.LaneDepWarnSys_Yxsd);
            updateItem(34, this.mSafeSetData.Xhcztsy);
            updateItem(37, this.mSafeSetData.Zccgqxsyd);
            updateItem(38, this.mSafeSetData.Hzccgqbjyl - 1);
            updateItem(41, this.mSafeSetData.Tbwybj);
            updateItem(43, this.mSafeSetData.Hfjhjtjb);
            updateItem(44, this.mSafeSetData.BindSpotMonitor - 1);
            updateItem(45, this.mSafeSetData.Jbds - 1);
            updateItem(48, this.mSafeSetData.Zdxs360ViewMonitor);
            updateItem(49, this.mSafeSetData.Dtlx);
            updateItem(50, this.mSafeSetData.Qsxtst);
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(116, 0);
    }
}
