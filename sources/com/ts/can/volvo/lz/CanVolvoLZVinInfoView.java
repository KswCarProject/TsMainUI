package com.ts.can.volvo.lz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanVolvoLZVinInfoView extends CanScrollCarInfoView {
    private CanDataInfo.VolvoXc60_Vin mVinData;

    public CanVolvoLZVinInfoView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_volvo_vin};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE};
        this.mVinData = new CanDataInfo.VolvoXc60_Vin();
    }

    public void ResetData(boolean check) {
        CanJni.VolvoLzCx60GetCarVin(this.mVinData);
        if (!i2b(this.mVinData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mVinData.UpdateOnce)) {
            StringBuilder sb = new StringBuilder();
            for (int i : this.mVinData.Data) {
                sb.append(String.valueOf((char) i));
            }
            updateItem(0, 0, sb.toString());
        }
    }

    public void QueryData() {
        CanJni.VolvoLzCx60Query(123, 0);
    }
}
