package com.ts.can.faw.t3.b30;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFawB30T3CarSetView extends CanScrollCarInfoView {
    private CanDataInfo.FawB30T3_SetInfo mSetInfo;

    public CanFawB30T3CarSetView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        if (((Integer) v.getTag()).intValue() == 0) {
            CanJni.FawB30T3CarSet(1, Neg(this.mSetInfo.Xrjby));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_xrjby};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH};
        this.mSetInfo = new CanDataInfo.FawB30T3_SetInfo();
    }

    public void ResetData(boolean check) {
        CanJni.FawB30T3GetSet(this.mSetInfo);
        if (!i2b(this.mSetInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetInfo.Update)) {
            this.mSetInfo.Update = 0;
            updateItem(0, this.mSetInfo.Xrjby);
        }
    }

    public void QueryData() {
        CanJni.FawB30T3Query(7);
    }
}
