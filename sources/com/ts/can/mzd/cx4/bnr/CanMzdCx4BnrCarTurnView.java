package com.ts.can.mzd.cx4.bnr;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMzdCx4BnrCarTurnView extends CanScrollCarInfoView {
    private CanDataInfo.Cx4_CarSet_Data mSetData;

    public CanMzdCx4BnrCarTurnView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.MzdCx4CarSet(7, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        if (((Integer) v.getTag()).intValue() == 0) {
            CanJni.MzdCx4CarSet(6, Neg(this.mSetData.scsdzxxh));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_cx4_turn_three_light, R.string.can_mzd_cx4_turn_voice};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_high};
        this.mSetData = new CanDataInfo.Cx4_CarSet_Data();
    }

    public void ResetData(boolean check) {
        CanJni.MzdCx4GetCarSetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.scsdzxxh, this.mSetData.zxxhyl});
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(9, 0);
    }
}
