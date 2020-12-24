package com.ts.can.chana.od;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanChanaODCarSetView extends CanScrollCarInfoView {
    private CanDataInfo.ChanAOd_SetData mSetData;

    public CanChanaODCarSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CanJni.ChanAOdCarSet(10, item);
                return;
            case 4:
                CanJni.ChanAOdCarSet(11, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.ChanAOdCarSet(4, Neg(this.mSetData.Xczdls - 1) + 1);
        } else if (id == 1) {
            CanJni.ChanAOdCarSet(16, Neg(this.mSetData.Zczdjs - 1) + 1);
        } else if (id == 2) {
            CanJni.ChanAOdCarSet(23, Neg(this.mSetData.Ecls - 1) + 1);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_xczdls, R.string.can_zczdjs, R.string.can_carset_ecls, R.string.can_tigger7_light_delay, R.string.can_yjzx};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[3] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_10s, R.string.can_30s, R.string.can_60s, R.string.can_mzd_cx4_time_120s};
        this.mPopValueIds[4] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_3c, R.string.can_5c, R.string.can_7c};
        this.mSetData = new CanDataInfo.ChanAOd_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.ChanAOdGetCarSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, Neg(this.mSetData.Xczdls - 1));
            updateItem(1, Neg(this.mSetData.Zczdjs - 1));
            updateItem(2, Neg(this.mSetData.Ecls - 1));
            updateItem(3, this.mSetData.Qzdys);
            updateItem(4, this.mSetData.Yjzx);
        }
    }

    public void QueryData() {
        CanJni.ChanAOdCarQuery(131, 0);
    }
}
