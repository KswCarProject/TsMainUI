package com.ts.can.honda.wc.accord9;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanAccord9WcDistanceSetView extends CanScrollCarInfoView {
    private CanDataInfo.HondaAccord9WcDistance mDisData;

    public CanAccord9WcDistanceSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.HondaWcAccord9DistanceSet(1, item);
                return;
            case 2:
                CanJni.HondaWcAccord9DistanceSet(2, item);
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
                CanJni.HondaWcAccord9DistanceSet(3, Neg(this.mDisData.FuleEffBackLight));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_rybjtm, R.string.can_lcacs, R.string.can_lcbcs};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_tripbresettiming_wc_1, R.string.can_yjyld, R.string.can_gbsld, R.string.can_shoudong};
        this.mPopValueIds[2] = this.mPopValueIds[1];
        this.mDisData = new CanDataInfo.HondaAccord9WcDistance();
    }

    public void ResetData(boolean check) {
        CanJni.HondaWcAccord9GetDistanceData(this.mDisData);
        if (!i2b(this.mDisData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDisData.Update)) {
            this.mDisData.Update = 0;
            updateItem(0, this.mDisData.FuleEffBackLight);
            updateItem(1, this.mDisData.TripaResetTiming);
            updateItem(2, this.mDisData.TripbResetTiming);
        }
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, 117);
    }
}
