package com.ts.can.hyundai.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHyundaiWc360CameraSetView extends CanScrollCarInfoView {
    private static final int BCJLJG = 2;
    private static final int HFCSSTMS = 0;
    private static final int HSBCYD = 3;
    private static final int QFCSSTMS = 1;
    private CanDataInfo.HyundaiWcAvmData mAvmData;

    public CanHyundaiWc360CameraSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.HyundaiWcAvmSet(5, item);
                return;
            case 1:
                CanJni.HyundaiWcAvmSet(4, item);
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
                CanJni.HyundaiWcAvmSet(3, Neg(this.mAvmData.ParkDisWarn));
                return;
            case 3:
                CanJni.HyundaiWcAvmSet(2, Neg(this.mAvmData.RearParkLine));
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
        this.mAvmData = new CanDataInfo.HyundaiWcAvmData();
    }

    public void ResetData(boolean check) {
        CanJni.HyundaiWcGetAvmData(this.mAvmData);
        if (!i2b(this.mAvmData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAvmData.Update)) {
            this.mAvmData.Update = 0;
            updateItem(new int[]{this.mAvmData.RearInitMode, this.mAvmData.FrontInitMode, this.mAvmData.ParkDisWarn, this.mAvmData.RearParkLine});
        }
    }

    public void QueryData() {
    }
}
