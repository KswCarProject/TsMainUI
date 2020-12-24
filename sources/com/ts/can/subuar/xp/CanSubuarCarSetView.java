package com.ts.can.subuar.xp;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSubuarCarSetView extends CanScrollCarInfoView {
    private CanDataInfo.SubuarXp_Set mSetData;

    public CanSubuarCarSetView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.SubuarXpCarSet(3, item, 0);
                return;
            case 1:
                CanJni.SubuarXpCarSet(4, item, 0);
                return;
            case 4:
                CanJni.SubuarXpCarSet(7, item, 0);
                return;
            case 5:
                CanJni.SubuarXpCarSet(8, item, 0);
                return;
            case 6:
                CanJni.SubuarXpCarSet(9, item, 0);
                return;
            case 7:
                CanJni.SubuarXpCarSet(10, item, 0);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 2) {
            CanJni.SubuarXpCarSet(5, Neg(this.mSetData.OneTouchLaneChanger), 0);
        } else if (id == 3) {
            CanJni.SubuarXpCarSet(6, Neg(this.mSetData.HazardWarningFlasher), 0);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_interior_light_1, R.string.can_defogger, R.string.can_one_touch_lane_changer, R.string.can_hazard_warning_flasher, R.string.can_security_relocking, R.string.can_auto_door_lock, R.string.can_auto_door_unlock, R.string.can_car_control_type};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.array.can_df_jyx5_ddys};
        this.mPopValueIds[1] = new int[]{R.string.can_15minutes, R.string.can_continuously};
        this.mPopValueIds[4] = new int[]{R.string.can_autodooroff, R.string.can_20s, R.string.can_30s, R.string.can_40s, R.string.can_50s, R.string.can_60s};
        this.mPopValueIds[5] = new int[]{R.string.can_autodooroff, R.string.can_vehicle_speed, R.string.can_shift_into_out_park};
        this.mPopValueIds[6] = new int[]{R.string.can_autodooroff, R.string.can_shift_into_out_park, R.string.can_ignition_off, R.string.can_driver_door_open};
        this.mPopValueIds[7] = new int[]{R.array.can_fs_declare_254};
        this.mSetData = new CanDataInfo.SubuarXp_Set();
    }

    public void ResetData(boolean check) {
        CanJni.SubuarXpGetSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.InteriorLight, this.mSetData.Defogger, this.mSetData.OneTouchLaneChanger, this.mSetData.HazardWarningFlasher, this.mSetData.SecurityReLocking, this.mSetData.AutoDoorLock, this.mSetData.AutoDoorUnlock, this.mSetData.SwType});
        }
    }

    public void QueryData() {
        CanJni.SubuarXpQuery(64, 0);
    }
}
