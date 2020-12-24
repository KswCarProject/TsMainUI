package com.ts.can.nissan.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanNissanRzcCarSetView extends CanScrollCarInfoView {
    private CanDataInfo.CanTeanaJukeData mSetData;

    public CanNissanRzcCarSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.NissanCarSet(82, 1);
        } else if (id == 1) {
            CanJni.NissanCarSet(83, 1);
        } else if (id == 2) {
            CanJni.NissanCarSet(81, 1);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_juke_Lanedeparture, R.string.can_juke_blindspotdetect, R.string.can_juke_movingobjdectect};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mSetData = new CanDataInfo.CanTeanaJukeData();
    }

    public void ResetData(boolean check) {
        CanJni.NissanGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.LaneDepartDetect);
            updateItem(1, this.mSetData.BlindSpotDetect);
            updateItem(2, this.mSetData.MovObjDetect);
        }
    }

    public void QueryData() {
    }
}
