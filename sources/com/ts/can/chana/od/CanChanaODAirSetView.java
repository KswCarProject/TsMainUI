package com.ts.can.chana.od;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanChanaODAirSetView extends CanScrollCarInfoView {
    private CanDataInfo.ChanAOd_SetData mSetData;

    public CanChanaODAirSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.ChanAOdCarSet(7, Neg(this.mSetData.Jszdhq - 1) + 1);
        } else if (id == 1) {
            CanJni.ChanAOdCarSet(8, Neg(this.mSetData.Jsjctf - 1) + 1);
        } else if (id == 2) {
            CanJni.ChanAOdCarSet(9, Neg(this.mSetData.Ktzgz - 1) + 1);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_jszdhq, R.string.can_jsjctf, R.string.can_ktzgz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mSetData = new CanDataInfo.ChanAOd_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.ChanAOdGetCarSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, Neg(this.mSetData.Jszdhq - 1));
            updateItem(1, Neg(this.mSetData.Jsjctf - 1));
            updateItem(2, Neg(this.mSetData.Ktzgz - 1));
        }
    }

    public void QueryData() {
        CanJni.ChanAOdCarQuery(131, 0);
    }
}
