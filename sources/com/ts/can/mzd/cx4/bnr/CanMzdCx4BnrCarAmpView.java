package com.ts.can.mzd.cx4.bnr;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMzdCx4BnrCarAmpView extends CanScrollCarInfoView {
    private static final int ITEM_BOSE_AUDIO_PILOT = 1;
    private static final int ITEM_BOSE_CENTER_POINT = 0;
    private CanDataInfo.Mzd_Bnr_Set mSetData;

    public CanMzdCx4BnrCarAmpView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.MzdCx4CarSet(80, Neg(this.mSetData.BoseCen));
                return;
            case 1:
                CanJni.MzdCx4CarSet(81, Neg(this.mSetData.BoseAudioPilot));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_bose_centerpoint, R.string.can_bose_audiopilot};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mSetData = new CanDataInfo.Mzd_Bnr_Set();
    }

    public void ResetData(boolean check) {
        CanJni.MzdBnrGetCarSet2(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.BoseCen);
            updateItem(1, this.mSetData.BoseAudioPilot);
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(13, 0);
    }
}
