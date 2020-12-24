package com.ts.can.hyundai;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHyundaiBocheNaviSetView extends CanScrollCarInfoView {
    private CanDataInfo.HyundaiCamera360 mSetData;

    public CanHyundaiBocheNaviSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.HyundaiXpCameraSet(5, item);
                return;
            case 3:
                CanJni.HyundaiXpCameraSet(4, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View arg0) {
        int id = ((Integer) arg0.getTag()).intValue();
        if (id == 0) {
            CanJni.HyundaiXpCameraSet(2, Neg(this.mSetData.Zyxxs));
        } else if (id == 1) {
            CanJni.HyundaiXpCameraSet(3, Neg(this.mSetData.Qhjjjgxs));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_zyxxs, R.string.can_qhjjjgxs, R.string.can_hfcsstms, R.string.can_qfcsstms};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[2] = new int[]{R.string.can_shitu_all, R.string.can_rear_only, R.string.can_rear_left, R.string.can_rear_right};
        this.mPopValueIds[3] = new int[]{R.string.can_shitu_all, R.string.can_front_only, R.string.can_front_left, R.string.can_front_right};
        this.mSetData = new CanDataInfo.HyundaiCamera360();
    }

    public void ResetData(boolean check) {
        CanJni.HyundaiXpGetCameraInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Zyxxs, this.mSetData.Qhjjjgxs, this.mSetData.Hfcsstms, this.mSetData.Qfcsstms});
        }
    }

    public void QueryData() {
    }
}
