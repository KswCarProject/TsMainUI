package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanMzdRzcCarDoorView extends CanScrollCarInfoView {
    private CanDataInfo.Mzd_Rzc_SetData mSetData;

    public CanMzdRzcCarDoorView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.MzdCx4CarSet(4, item);
                return;
            case 1:
                CanJni.MzdCx4CarSet(1, item);
                return;
            case 2:
                CanJni.MzdCx4CarSet(2, item);
                return;
            case 3:
                CanJni.MzdCx4CarSet(3, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 4) {
            CanJni.MzdCx4CarSet(5, Neg(this.mSetData.lccs));
        } else if (id == 5) {
            new CanItemMsgBox(id, getActivity(), R.string.can_teramont_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanJni.MzdCx4CarSet(6, 0);
                }
            }, (CanItemMsgBox.onMsgBoxClick2) null);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_cx4_door_lock_voice, R.string.can_mzd_cx4_door_lock_mode, R.string.can_mzd_cx4_door_lock_time, R.string.can_mzd_cx4_door_unlock_mode, R.string.can_mzd_cx4_door_leave_lock, R.string.can_teramont_model_reset};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[0] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_middle, R.string.can_mzd_cx4_voice_high};
        this.mPopValueIds[1] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_lock_mode1, R.string.can_mzd_cx4_lock_mode2, R.string.can_mzd_cx4_lock_mode4, R.string.can_mzd_cx4_lock_mode3, R.string.can_mzd_cx4_lock_mode5};
        this.mPopValueIds[2] = new int[]{R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s};
        this.mPopValueIds[3] = new int[]{R.string.can_mzd_cx4_unlock_all, R.string.can_mzd_cx4_unlock_driver};
        this.mSetData = new CanDataInfo.Mzd_Rzc_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.MzdRzcGetCarSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.wyssctsy, this.mSetData.zdssms, this.mSetData.zdcssj, this.mSetData.jsms, this.mSetData.lccs});
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(65, 0);
    }
}
