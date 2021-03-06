package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanMzdRzcCarTurnView extends CanScrollCarInfoView {
    private CanDataInfo.Mzd_Rzc_SetData mSetData;

    public CanMzdRzcCarTurnView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                if (item == 1) {
                    CanJni.MzdCx4CarSet(8, 2);
                    return;
                } else if (item == 2) {
                    CanJni.MzdCx4CarSet(8, 1);
                    return;
                } else {
                    CanJni.MzdCx4CarSet(8, item);
                    return;
                }
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.MzdCx4CarSet(7, Neg(this.mSetData.scsdzxxh));
        } else if (id == 2) {
            new CanItemMsgBox(id, getActivity(), R.string.can_teramont_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanJni.MzdCx4CarSet(9, 0);
                }
            }, (CanItemMsgBox.onMsgBoxClick2) null);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_cx4_turn_three_light, R.string.can_mzd_cx4_turn_voice, R.string.can_teramont_model_reset};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[1] = new int[]{R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_middle, R.string.can_mzd_cx4_voice_high};
        this.mSetData = new CanDataInfo.Mzd_Rzc_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.MzdRzcGetCarSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.scsdzxxh, this.mSetData.zxxhyl});
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(65, 0);
    }
}
