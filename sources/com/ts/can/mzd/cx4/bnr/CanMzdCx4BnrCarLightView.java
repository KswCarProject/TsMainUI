package com.ts.can.mzd.cx4.bnr;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMzdCx4BnrCarLightView extends CanScrollCarInfoView {
    private static final int ITEM_COMING_HOME_LIGHT = 7;
    private static final int ITEM_HIGH_BEAM_CONTROL = 6;
    private static final int ITEM_LEAVING_HOME_LIGHT = 8;
    private CanDataInfo.Cx4_CarSet_Data mSetData;
    private CanDataInfo.Mzd_Bnr_Set mSetData2;

    public CanMzdCx4BnrCarLightView(Activity activity) {
        super(activity, 9);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.MzdCx4CarSet(8, item);
        } else if (id == 1) {
            CanJni.MzdCx4CarSet(9, item);
        } else if (id == 3) {
            CanJni.MzdCx4CarSet(11, item);
        } else if (id == 4) {
            CanJni.MzdCx4CarSet(12, item);
        } else if (id == 5) {
            CanJni.MzdCx4CarSet(13, item);
        } else if (id == 7) {
            CanJni.MzdCx4CarSet(33, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 2) {
            CanJni.MzdCx4CarSet(10, Neg(this.mSetData.zsyzxqzdxt));
        } else if (id == 6) {
            CanJni.MzdCx4CarSet(32, Neg(this.mSetData2.HighBeamControl));
        } else if (id == 8) {
            CanJni.MzdCx4CarSet(34, Neg(this.mSetData2.LeavingHomeLight));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_cx4_light_open_auto_off, R.string.can_mzd_cx4_light_close_auto_off, R.string.can_mzd_cx4_light_turn_system, R.string.can_mzd_cx4_light_off_noticer, R.string.can_mzd_cx4_light_off_timer, R.string.can_mzd_cx4_light_auto_open, R.string.can_mzd_cx4_hbctrl, R.string.can_mzd_cx4_chl, R.string.can_mzd_cx4_lhl};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_mzd_cx4_time_10min, R.string.can_mzd_cx4_time_30min, R.string.can_mzd_cx4_time_60min};
        this.mPopValueIds[1] = new int[]{R.string.can_mzd_cx4_time_7_5s, R.string.can_mzd_cx4_time_15s, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s};
        this.mPopValueIds[3] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_high};
        this.mPopValueIds[4] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s, R.string.can_mzd_cx4_time_120s};
        this.mPopValueIds[5] = new int[]{R.string.can_mzd_cx4_light_dark, R.string.can_mzd_cx4_light_middle_dark, R.string.can_mzd_cx4_light_middle, R.string.can_mzd_cx4_light_middle_lighter, R.string.can_mzd_cx4_light_lighter};
        this.mPopValueIds[7] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s, R.string.can_mzd_cx4_time_120s};
        this.mSetData = new CanDataInfo.Cx4_CarSet_Data();
        this.mSetData2 = new CanDataInfo.Mzd_Bnr_Set();
    }

    public void ResetData(boolean check) {
        CanJni.MzdCx4GetCarSetInfo(this.mSetData);
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.cmdkscndzdxm, this.mSetData.cmgbscndzdxm, this.mSetData.zsyzxqzdxt, this.mSetData.cdwgtsq, this.mSetData.ddgbdsq, this.mSetData.zdddkq});
        }
        CanJni.MzdBnrGetCarSet2(this.mSetData2);
        if (!i2b(this.mSetData2.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData2.Update)) {
            this.mSetData2.Update = 0;
            updateItem(6, this.mSetData2.HighBeamControl);
            updateItem(7, this.mSetData2.ComingHomeLight);
            updateItem(8, this.mSetData2.LeavingHomeLight);
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(9, 0);
        Sleep(5);
        CanJni.MzdCx4Query(13, 0);
    }
}
