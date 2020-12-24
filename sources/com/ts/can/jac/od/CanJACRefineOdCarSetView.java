package com.ts.can.jac.od;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanJACRefineOdCarSetView extends CanScrollCarInfoView {
    private CanDataInfo.JAC_CARDATA mCarData;

    public CanJACRefineOdCarSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.JACRefineOdCarSet(1, item);
                return;
            case 1:
                CanJni.JACRefineOdCarSet(2, item);
                return;
            case 2:
                CanJni.JACRefineOdCarSet(3, item);
                return;
            case 3:
                CanJni.JACRefineOdCarSet(4, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 4:
                CanJni.JACRefineOdCarSet(5, Neg(this.mCarData.Sftsy));
                return;
            case 5:
                CanJni.JACRefineOdCarSet(6, Neg(this.mCarData.Dwzm));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_tsysy, R.string.can_outcarlightoftime, R.string.can_light_off_time, R.string.can_auto_speed_lock, R.string.can_set_tip_vol, R.string.can_gps_light};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mPopValueIds[1] = new int[]{R.string.can_0s, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s, R.string.can_mzd_cx4_time_120s};
        this.mPopValueIds[2] = new int[]{R.string.can_0s, R.string.can_mzd_cx4_time_15s, R.string.can_mzd_cx4_time_30s, R.string.can_time_45s, R.string.can_mzd_cx4_time_60s};
        this.mPopValueIds[3] = new int[]{R.array.can_auto_speed_lock_array};
        this.mCarData = new CanDataInfo.JAC_CARDATA();
    }

    public void ResetData(boolean check) {
        CanJni.JACRefineOdGetCarSet(this.mCarData);
        if (!i2b(this.mCarData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarData.Update)) {
            this.mCarData.Update = 0;
            updateItem(0, this.mCarData.Tsyldx);
            updateItem(1, this.mCarData.Cwzmycgbsj);
            updateItem(2, this.mCarData.Cnzmycgbsj);
            updateItem(3, this.mCarData.Zdcss);
            updateItem(4, this.mCarData.Sftsy);
            updateItem(5, this.mCarData.Dwzm);
        }
    }

    public void QueryData() {
    }
}
