package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMzdRzcOtherSetupView extends CanScrollCarInfoView {
    private static final int CHSJZDZD = 12;
    private static final int CLTZHJGYL = 13;
    private static final int COME_HOME_L = 5;
    private static final int GYSYS = 0;
    private static final int HCCWQ = 10;
    private static final int HI_BEAM_CTRL = 8;
    private static final int JLDW = 6;
    private static final int LEAVE_HOME_L = 4;
    private static final int MQTSYL = 2;
    private static final int RXD = 9;
    private static final int TBPJHLCA = 3;
    private static final int WDDW = 7;
    private static final int YBHFCCSZ = 15;
    private static final int YBZDYXS = 14;
    private static final int YXGZDSSSJ = 11;
    private static final int YYZT = 1;
    private CanDataInfo.Mzd_Rzc_SetData mSetData;

    public CanMzdRzcOtherSetupView(Activity activity) {
        super(activity, 16);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.MzdCx4CarSet(18, item);
        } else if (id == 2) {
            CanJni.MzdCx4CarSet(19, item);
        } else if (id == 5) {
            CanJni.MzdCx4CarSet(129, item);
        } else if (id == 6) {
            CanJni.MzdCx4CarSet(130, item);
        } else if (id == 7) {
            CanJni.MzdCx4CarSet(131, item);
        } else if (id == 8) {
            CanJni.MzdCx4CarSet(132, item);
        } else if (id == 9) {
            CanJni.MzdCx4CarSet(133, item);
        } else if (id == 10) {
            CanJni.MzdCx4CarSet(22, item);
        } else if (id == 11) {
            CanJni.MzdCx4CarSet(23, item);
        } else if (id == 13) {
            CanJni.MzdCx4CarSet(25, item);
        } else if (id == 14) {
            CanJni.MzdCx4CarSet(26, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.MzdCx4CarSet(17, Neg(this.mSetData.gysys));
                return;
            case 3:
                CanJni.MzdCx4CarSet(20, Neg(this.mSetData.TbpjhlcA));
                return;
            case 4:
                CanJni.MzdCx4CarSet(128, Neg(this.mSetData.LeavingHomeLight));
                return;
            case 12:
                CanJni.MzdCx4CarSet(24, Neg(this.mSetData.Chsjzdzd));
                return;
            case 15:
                CanJni.MzdCx4CarSet(27, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_cx4_door_yushua, R.string.can_language, R.string.can_mzd_cx4_other_control_voice, R.string.can_mzd_cx4_other_sync_tripa, R.string.can_mzd_cx4_lhl, R.string.can_mzd_cx4_chl, R.string.can_mzd_cx4_jldw, R.string.can_temp_dw, R.string.can_mzd_cx4_hbctrl, R.string.can_light_rxd, R.string.can_ac_hccw, R.string.can_yxgzdsssj, R.string.can_zdzdwhsj, R.string.can_cltzhjgyl, R.string.can_theme, R.string.can_ybhfccsz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[1] = new int[]{R.string.can_lang_en, R.string.can_lang_cn};
        this.mPopValueIds[2] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_high};
        this.mPopValueIds[5] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s, R.string.can_mzd_cx4_time_120s};
        this.mPopValueIds[6] = new int[]{R.array.can_fist_l_c};
        this.mPopValueIds[7] = new int[]{R.string.can_temperature_c, R.string.can_temperature_f};
        this.mPopValueIds[8] = new int[]{R.string.can_trunk_close, R.string.can_trunk_open};
        this.mPopValueIds[9] = new int[]{R.string.can_trunk_close, R.string.can_trunk_open};
        this.mPopValueIds[10] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_continuous};
        this.mPopValueIds[11] = new int[]{R.string.can_0s, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s, R.string.can_mzd_cx4_time_120s};
        this.mPopValueIds[13] = new int[]{R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_middle, R.string.can_mzd_cx4_voice_high};
        this.mPopValueIds[14] = new int[]{R.string.can_car_type1, R.string.can_car_type2, R.string.can_mzd_cx4_mode_off};
        this.mSetData = new CanDataInfo.Mzd_Rzc_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.MzdRzcGetCarSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.gysys, this.mSetData.Lang, this.mSetData.mdjkyl, this.mSetData.TbpjhlcA, this.mSetData.LeavingHomeLight, this.mSetData.ComingHomeLight, this.mSetData.DwDistance, this.mSetData.DwTemp, Neg(this.mSetData.HighBeamControl), Neg(this.mSetData.Rxd), this.mSetData.Hccwq, this.mSetData.Yxgzdsssj, this.mSetData.Chsjzdzd, this.mSetData.Cltzhjgyl, this.mSetData.Ybzdyxs, 1});
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(65, 0);
    }
}
