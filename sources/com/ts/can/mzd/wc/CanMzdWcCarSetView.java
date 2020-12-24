package com.ts.can.mzd.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMzdWcCarSetView extends CanScrollCarInfoView {
    private CanDataInfo.MzdWcCarData mCarAdt;
    private CanDataInfo.MzdWcCarData mCarData;

    public CanMzdWcCarSetView(Activity activity) {
        super(activity, 25);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.MzdWcCarSet(10, 0, 1, item);
                return;
            case 1:
                CanJni.MzdWcCarSet(10, 0, 2, item);
                return;
            case 3:
                CanJni.MzdWcCarSet(10, 0, 4, item);
                return;
            case 4:
                CanJni.MzdWcCarSet(10, 0, 5, item);
                return;
            case 5:
                CanJni.MzdWcCarSet(10, 0, 6, item);
                return;
            case 7:
                CanJni.MzdWcCarSet(10, 0, 8, item);
                return;
            case 9:
                CanJni.MzdWcCarSet(10, 0, 10, item);
                return;
            case 10:
                CanJni.MzdWcCarSet(10, 0, 11, item);
                return;
            case 11:
                CanJni.MzdWcCarSet(10, 0, 12, item);
                return;
            case 13:
                CanJni.MzdWcCarSet(10, 0, 14, item);
                return;
            case 14:
                CanJni.MzdWcCarSet(10, 0, 15, item);
                return;
            case 15:
                CanJni.MzdWcCarSet(10, 0, 16, item);
                return;
            case 18:
                CanJni.MzdWcCarSet(10, 0, 19, item);
                return;
            case 19:
                CanJni.MzdWcCarSet(10, 0, 20, item);
                return;
            case 21:
                CanJni.MzdWcCarSet(10, 0, 22, item);
                return;
            case 22:
                CanJni.MzdWcCarSet(10, 0, 23, item);
                return;
            case 24:
                CanJni.MzdWcCarSet(10, 0, 25, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.MzdWcCarSet(10, 0, 3, Neg(this.mCarData.Lccs));
                return;
            case 6:
                CanJni.MzdWcCarSet(10, 0, 7, Neg(this.mCarData.Scsdzxxh));
                return;
            case 8:
                CanJni.MzdWcCarSet(10, 0, 9, Neg(this.mCarData.Gysys));
                return;
            case 12:
                CanJni.MzdWcCarSet(10, 0, 13, Neg(this.mCarData.Zsyzxqzdxt));
                return;
            case 16:
                CanJni.MzdWcCarSet(10, 0, 17, Neg(this.mCarData.Zncsscxt));
                return;
            case 17:
                CanJni.MzdWcCarSet(10, 0, 18, Neg(this.mCarData.Ryjjx));
                return;
            case 20:
                CanJni.MzdWcCarSet(10, 0, 21, Neg(this.mCarData.SBS_SCBS));
                return;
            case 23:
                CanJni.MzdWcCarSet(10, 0, 24, Neg(this.mCarData.DRSS_Ms));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_cx4_door_lock_mode, R.string.can_mzd_cx4_light_open_auto_off, R.string.can_mzd_cx4_door_leave_lock, R.string.can_mzd_cx4_door_lock_voice, R.string.can_securityrelocktimer, R.string.can_unlock_mode, R.string.can_mzd_cx4_turn_three_light, R.string.can_mzd_cx4_turn_voice, R.string.can_mzd_cx4_door_yushua, R.string.can_gscmsnsdcs, R.string.can_mzd_cx4_light_auto_open, R.string.can_mzd_cx4_light_off_noticer, R.string.can_mzd_cx4_light_turn_system, R.string.can_mzd_cx4_drive_light_control, R.string.can_mzd_cx4_drive_display, R.string.can_mzd_cx4_drive_navigation, R.string.can_mzd_cx4_other_sc_system, R.string.can_mzd_wc_ryjjx_tbpjlc, R.string.can_mzd_cx4_other_control_voice, R.string.can_mzd_cx4_light_off_timer, R.string.can_mzd_wc_sbs_mode, R.string.can_mzd_wc_sbs_jgjl, R.string.can_mzd_wc_sbs_jgyl, R.string.can_mzd_wc_drss_mode, R.string.can_mzd_wc_drss_jgjl};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_off, R.string.can_mzd_cx4_lock_mode1, R.string.can_mzd_cx4_lock_mode2, R.string.can_mzd_cx4_lock_mode4, R.string.can_mzd_cx4_lock_mode3};
        this.mPopValueIds[1] = new int[]{R.string.can_mzd_cx4_time_10min, R.string.can_mzd_cx4_time_30min, R.string.can_mzd_cx4_time_60min};
        this.mPopValueIds[3] = new int[]{R.string.can_off, R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mPopValueIds[4] = new int[]{R.string.can_30s, R.string.can_60s, R.string.can_90s};
        this.mPopValueIds[5] = new int[]{R.string.can_mzd_cx4_unlock_all, R.string.can_mzd_cx4_unlock_driver};
        this.mPopValueIds[7] = new int[]{R.string.can_ac_low, R.string.can_ac_high};
        this.mPopValueIds[9] = new int[]{R.string.can_7dot5s, R.string.can_15s, R.string.can_30s, R.string.can_60s};
        this.mPopValueIds[10] = new int[]{R.string.can_mzd_cx4_light_dark, R.string.can_mzd_cx4_light_middle_dark, R.string.can_mzd_cx4_light_middle, R.string.can_mzd_cx4_light_middle_lighter, R.string.can_mzd_cx4_light_lighter};
        this.mPopValueIds[11] = new int[]{R.string.can_off, R.string.can_ac_low, R.string.can_ac_high};
        this.mPopValueIds[13] = new int[]{R.string.can_mzd_cx4_drive_auto, R.string.can_shoudong};
        this.mPopValueIds[14] = new int[]{R.string.can_trunk_close, R.string.can_display};
        this.mPopValueIds[15] = new int[]{R.string.can_trunk_close, R.string.can_display};
        this.mPopValueIds[18] = new int[]{R.string.can_trunk_close, R.string.can_cdjd, R.string.can_cdjg};
        this.mPopValueIds[19] = new int[]{R.string.can_off, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s, R.string.can_mzd_cx4_time_120s};
        this.mPopValueIds[21] = new int[]{R.string.can_sdqfwxjgjl_1, R.string.can_sdqfwxjgjl_3};
        this.mPopValueIds[22] = new int[]{R.string.can_trunk_close, R.string.can_cdjd, R.string.can_cdjg};
        this.mPopValueIds[24] = new int[]{R.string.can_trunk_close, R.string.can_sdqfwxjgjl_2, R.string.can_sdqfwxjgjl_1};
        for (int i = 0; i < this.mItemVisibles.length; i++) {
            this.mItemVisibles[i] = 0;
        }
        this.mCarAdt = new CanDataInfo.MzdWcCarData();
        this.mCarData = new CanDataInfo.MzdWcCarData();
    }

    public void ResetData(boolean check) {
        CanJni.MzdWcGetCarSet(this.mCarAdt, 0);
        if (i2b(this.mCarAdt.UpdateOnce) && (!check || i2b(this.mCarAdt.Update))) {
            this.mCarAdt.Update = 0;
            showItem(new int[]{this.mCarAdt.Zdssms, this.mCarAdt.Dkcmscndzdxm, this.mCarAdt.Lccs, this.mCarAdt.Wyssctsyl, this.mCarAdt.Zdcsss, this.mCarAdt.Jsms, this.mCarAdt.Scsdzxxh, this.mCarAdt.Zxxhyl, this.mCarAdt.Gysys, this.mCarAdt.Gscmsnsdcs, this.mCarAdt.Zdddkq, this.mCarAdt.Cdwgtsq, this.mCarAdt.Zsyzxqzdxt, this.mCarAdt.Jsxs_Ldkz, this.mCarAdt.Jsxs_Zdjsxs, this.mCarAdt.Jsxs_Dh, this.mCarAdt.Zncsscxt, this.mCarAdt.Ryjjx, this.mCarAdt.Mdjkyl, this.mCarAdt.Ddgbdsq, this.mCarAdt.SBS_SCBS, this.mCarAdt.SBS_Jgjl, this.mCarAdt.SBS_Jgyl, this.mCarAdt.DRSS_Ms, this.mCarAdt.DRSS_Jgjl});
        }
        CanJni.MzdWcGetCarSet(this.mCarData, 1);
        if (!i2b(this.mCarData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarData.Update)) {
            this.mCarData.Update = 0;
            updateItem(new int[]{this.mCarData.Zdssms, this.mCarData.Dkcmscndzdxm, this.mCarData.Lccs, this.mCarData.Wyssctsyl, this.mCarData.Zdcsss, this.mCarData.Jsms, this.mCarData.Scsdzxxh, this.mCarData.Zxxhyl, this.mCarData.Gysys, this.mCarData.Gscmsnsdcs, this.mCarData.Zdddkq, this.mCarData.Cdwgtsq, this.mCarData.Zsyzxqzdxt, this.mCarData.Jsxs_Ldkz, this.mCarData.Jsxs_Zdjsxs, this.mCarData.Jsxs_Dh, this.mCarData.Zncsscxt, this.mCarData.Ryjjx, this.mCarData.Mdjkyl, this.mCarData.Ddgbdsq, this.mCarData.SBS_SCBS, this.mCarData.SBS_Jgjl, this.mCarData.SBS_Jgyl, this.mCarData.DRSS_Ms, this.mCarData.DRSS_Jgjl});
        }
    }

    public void QueryData() {
        CanJni.MzdWcQuery(5, 1, 120);
    }
}
