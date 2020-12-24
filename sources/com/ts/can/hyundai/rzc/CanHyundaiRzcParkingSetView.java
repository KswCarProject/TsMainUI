package com.ts.can.hyundai.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHyundaiRzcParkingSetView extends CanScrollCarInfoView {
    private static final int BCJLJG = 2;
    private static final int HFCSSTMS = 0;
    private static final int HSBCYD = 3;
    private static final int QFCSSTMS = 1;
    private CanDataInfo.HyCarParkSet mParkData;

    public CanHyundaiRzcParkingSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.HyundaiRzcParkSet(4, item + 1);
                return;
            case 1:
                CanJni.HyundaiRzcParkSet(3, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.HyundaiRzcParkSet(2, Neg(this.mParkData.WarnLine));
                return;
            case 3:
                CanJni.HyundaiRzcParkSet(1, Neg(this.mParkData.Line));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_hfcsstms, R.string.can_qfcsstms, R.string.can_bcjljg, R.string.can_hsbcyd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_shitu_all, R.string.can_rear_only, R.string.can_rear_left, R.string.can_rear_right};
        this.mPopValueIds[1] = new int[]{R.string.can_shitu_all, R.string.can_front_only, R.string.can_front_left, R.string.can_front_right};
        this.mParkData = new CanDataInfo.HyCarParkSet();
    }

    public void ResetData(boolean check) {
        CanJni.HyundaiRzcGetParkSet(this.mParkData);
        if (!i2b(this.mParkData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mParkData.Update)) {
            this.mParkData.Update = 0;
            updateItem(new int[]{this.mParkData.Hfstms - 1, this.mParkData.Qfstms - 1, this.mParkData.WarnLine, this.mParkData.Line});
        }
    }

    public void QueryData() {
    }
}
