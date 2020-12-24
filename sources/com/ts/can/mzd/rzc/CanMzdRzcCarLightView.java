package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanMzdRzcCarLightView extends CanScrollCarInfoView {
    private CanDataInfo.Mzd_Rzc_SetData mSetData;

    public CanMzdRzcCarLightView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.MzdCx4CarSet(10, item);
        } else if (id == 1) {
            CanJni.MzdCx4CarSet(11, item);
        } else if (id == 3) {
            CanJni.MzdCx4CarSet(12, item);
        } else if (id == 4) {
            CanJni.MzdCx4CarSet(13, item);
        } else if (id == 5) {
            CanJni.MzdCx4CarSet(15, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 2) {
            CanJni.MzdCx4CarSet(14, Neg(this.mSetData.zsyzxqzdxt));
        } else if (id == 6) {
            new CanItemMsgBox(id, getActivity(), R.string.can_teramont_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanJni.MzdCx4CarSet(16, 0);
                }
            }, (CanItemMsgBox.onMsgBoxClick2) null);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_cx4_light_open_auto_off, R.string.can_mzd_cx4_light_close_auto_off, R.string.can_mzd_cx4_light_turn_system, R.string.can_mzd_cx4_light_off_noticer, R.string.can_mzd_cx4_light_off_timer, R.string.can_mzd_cx4_light_auto_open, R.string.can_teramont_model_reset};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[0] = new int[]{R.string.can_mzd_cx4_time_10min, R.string.can_mzd_cx4_time_30min, R.string.can_mzd_cx4_time_60min};
        this.mPopValueIds[1] = new int[]{R.string.can_mzd_cx4_time_7_5s, R.string.can_mzd_cx4_time_15s, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s};
        this.mPopValueIds[3] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_high};
        this.mPopValueIds[4] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s, R.string.can_mzd_cx4_time_120s};
        this.mPopValueIds[5] = new int[]{R.string.can_mzd_cx4_light_dark, R.string.can_mzd_cx4_light_middle_dark, R.string.can_mzd_cx4_light_middle, R.string.can_mzd_cx4_light_middle_lighter, R.string.can_mzd_cx4_light_lighter};
        this.mSetData = new CanDataInfo.Mzd_Rzc_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.MzdRzcGetCarSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.cmdkscndzdxm, this.mSetData.cmgbscndzdxm, this.mSetData.zsyzxqzdxt, this.mSetData.cdwgtsq, this.mSetData.ddgbdsq, this.mSetData.zdddkq});
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(65, 0);
    }
}
