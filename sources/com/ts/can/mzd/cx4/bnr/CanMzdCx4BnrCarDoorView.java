package com.ts.can.mzd.cx4.bnr;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMzdCx4BnrCarDoorView extends CanScrollCarInfoView {
    private CanDataInfo.Cx4_CarSet_Data mSetData;

    public CanMzdCx4BnrCarDoorView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.MzdCx4CarSet(1, item);
                return;
            case 2:
                CanJni.MzdCx4CarSet(2, item);
                return;
            case 3:
                CanJni.MzdCx4CarSet(3, item);
                return;
            case 4:
                CanJni.MzdCx4CarSet(4, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.MzdCx4CarSet(0, Neg(this.mSetData.gysys));
        } else if (id == 5) {
            CanJni.MzdCx4CarSet(5, Neg(this.mSetData.lccs));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_cx4_door_yushua, R.string.can_mzd_cx4_door_lock_voice, R.string.can_mzd_cx4_door_lock_mode, R.string.can_mzd_cx4_door_lock_time, R.string.can_mzd_cx4_door_unlock_mode, R.string.can_mzd_cx4_door_leave_lock};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[1] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_middle, R.string.can_mzd_cx4_voice_high};
        this.mPopValueIds[2] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_lock_mode1, R.string.can_mzd_cx4_lock_mode2, R.string.can_mzd_cx4_lock_mode3, R.string.can_mzd_cx4_lock_mode4};
        this.mPopValueIds[3] = new int[]{R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s};
        this.mPopValueIds[4] = new int[]{R.string.can_mzd_cx4_unlock_all, R.string.can_mzd_cx4_unlock_driver};
        this.mSetData = new CanDataInfo.Cx4_CarSet_Data();
    }

    public void ResetData(boolean check) {
        CanJni.MzdCx4GetCarSetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.gysys, this.mSetData.wyssctsy, this.mSetData.zdssms, this.mSetData.zdcssj, this.mSetData.jsms, this.mSetData.lccs});
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(9, 0);
    }
}
