package com.ts.can.luxgen.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanLuxgenWCCarSetView extends CanScrollCarInfoView {
    public static final int ITEM_CCAQYXFZ = 0;
    public static final int ITEM_GXDBTZ = 1;
    public static final int ITEM_MAX = 2;
    private CanDataInfo.LuxgenWc_SetData mSetData;

    public CanLuxgenWCCarSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.LuxgenWcCarSet(10, item);
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
                CanJni.LuxgenWcCarSet(9, Neg(this.mSetData.Ccaqyxfzxt));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ccaqyxfz, R.string.can_gxdbtz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_noupdate, R.string.can_update};
        this.mSetData = new CanDataInfo.LuxgenWc_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.LuxgenWcGetCarSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Ccaqyxfzxt);
        }
    }

    public void QueryData() {
    }
}
