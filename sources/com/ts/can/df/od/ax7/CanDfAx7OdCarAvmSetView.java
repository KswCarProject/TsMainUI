package com.ts.can.df.od.ax7;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanDfAx7OdCarAvmSetView extends CanScrollCarInfoView {
    private CanDataInfo.FengshenAx7OdCarSet mSetData;

    public CanDfAx7OdCarAvmSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.DffengsOdAvmSet(Neg(this.mSetData.Zxkgzdkqcshm), this.mSetData.Mqjc);
        } else if (id == 1) {
            CanJni.DffengsOdAvmSet(this.mSetData.Zxkgzdkqcshm, Neg(this.mSetData.Mqjc));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_zxkgzdkqcshm, R.string.can_blind_spot_monitoring};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mSetData = new CanDataInfo.FengshenAx7OdCarSet();
    }

    public void ResetData(boolean check) {
        CanJni.DffengsOdGetAvmData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Zxkgzdkqcshm);
            updateItem(1, this.mSetData.Mqjc);
        }
    }

    public void QueryData() {
    }
}
