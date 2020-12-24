package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMzdRzcOtherSetupView extends CanScrollCarInfoView {
    private static final int COME_HOME_L = 5;
    private static final int GYSYS = 0;
    private static final int HI_BEAM_CTRL = 8;
    private static final int JLDW = 6;
    private static final int LEAVE_HOME_L = 4;
    private static final int MQTSYL = 2;
    private static final int RXD = 9;
    private static final int TBPJHLCA = 3;
    private static final int WDDW = 7;
    private static final int YYZT = 1;
    private CanDataInfo.Mzd_Rzc_SetData mSetData;

    public CanMzdRzcOtherSetupView(Activity activity) {
        super(activity, 10);
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
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_cx4_door_yushua, R.string.can_language, R.string.can_mzd_cx4_other_control_voice, R.string.can_mzd_cx4_other_sync_tripa, R.string.can_mzd_cx4_lhl, R.string.can_mzd_cx4_chl, R.string.can_mzd_cx4_jldw, R.string.can_temp_dw, R.string.can_mzd_cx4_hbctrl, R.string.can_light_rxd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_lang_en, R.string.can_lang_cn};
        this.mPopValueIds[2] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_high};
        this.mPopValueIds[5] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s, R.string.can_mzd_cx4_time_120s};
        this.mPopValueIds[6] = new int[]{R.array.can_fist_l_c};
        this.mPopValueIds[7] = new int[]{R.string.can_temperature_c, R.string.can_temperature_f};
        this.mPopValueIds[8] = new int[]{R.string.can_trunk_close, R.string.can_trunk_open};
        this.mPopValueIds[9] = new int[]{R.string.can_trunk_close, R.string.can_trunk_open};
        this.mSetData = new CanDataInfo.Mzd_Rzc_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.MzdRzcGetCarSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.gysys, this.mSetData.Lang, this.mSetData.mdjkyl, this.mSetData.TbpjhlcA, this.mSetData.LeavingHomeLight, this.mSetData.ComingHomeLight, this.mSetData.DwDistance, this.mSetData.DwTemp, Neg(this.mSetData.HighBeamControl), Neg(this.mSetData.Rxd)});
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(65, 0);
    }
}
