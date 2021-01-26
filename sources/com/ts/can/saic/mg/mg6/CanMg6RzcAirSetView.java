package com.ts.can.saic.mg.mg6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMg6RzcAirSetView extends CanScrollCarInfoView {
    public static final int ITEM_HCCSCWLD = 0;
    public static final int ITEM_ZDMSFL = 1;
    private CanDataInfo.MG_GS_SET m_SetData;

    public CanMg6RzcAirSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.MGGSCarSet(5, 2, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.MGGSCarSet(5, 1, Neg(this.m_SetData.Hccscwld));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_hccscwld, R.string.can_ac_zdfl};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.m_SetData = new CanDataInfo.MG_GS_SET();
    }

    public void doOnResume() {
    }

    public void ResetData(boolean check) {
        CanJni.MGGSGetSetData4(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(0, this.m_SetData.Hccscwld);
            updateItem(1, this.m_SetData.Zdmsfl);
        }
    }

    public void QueryData() {
        CanJni.MGGSQuery(57);
    }
}
