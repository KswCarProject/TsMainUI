package com.ts.can.honda;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaQJYXSystemSetView extends CanScrollCarInfoView {
    private CanDataInfo.HondaSetData mSetData;

    public CanHondaQJYXSystemSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        if (id == 4) {
            CanJni.HondaDACarSet(46, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.HondaDACarSet(42, Neg(this.mSetData.Dzyjsaqdydms));
                return;
            case 1:
                CanJni.HondaDACarSet(43, Neg(this.mSetData.Jtydx));
                return;
            case 2:
                CanJni.HondaDACarSet(44, Neg(this.mSetData.Dtydx));
                return;
            case 3:
                CanJni.HondaDACarSet(45, Neg(this.mSetData.Zdchxssxt));
                return;
            case 5:
                CanJni.HondaDACarSet(47, Neg(this.mSetData.Hsdttxxt));
                return;
            case 6:
                CanJni.HondaDACarSet(49, Neg(this.mSetData.Hpyldgnxt));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_dzyjsaqdydms, R.string.can_jtydx, R.string.can_dtydx, R.string.can_dchsxt, R.string.can_dcrkcwkd, R.string.can_hsdttxxtsz, R.string.can_hpyldgnxt};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[4] = new int[]{R.string.can_dcrkcwkd_z, R.string.can_dcrkcwkd_k};
        this.mSetData = new CanDataInfo.HondaSetData();
    }

    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.QjyxxtUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.QjyxxtUpdate)) {
            this.mSetData.QjyxxtUpdate = 0;
            updateItem(new int[]{this.mSetData.Dzyjsaqdydms, this.mSetData.Jtydx, this.mSetData.Dtydx, this.mSetData.Zdchxssxt, this.mSetData.Dcrkdcwkd, this.mSetData.Hsdttxxt, this.mSetData.Hpyldgnxt});
        }
    }

    public void QueryData() {
        CanJni.HondaDAQuery(50, 0);
    }
}
