package com.ts.can.cc.wc.h6;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanWcH6AVMSetView extends CanScrollCarInfoView {
    private CanDataInfo.CcH6WcCamerSta mSetData;

    public CanWcH6AVMSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.CcH6WcCarCameraSet(5, item);
                return;
            case 1:
                CanJni.CcH6WcCarCameraSet(6, item);
                return;
            case 2:
                CanJni.CcH6WcCarCameraSet(7, item);
                return;
            case 3:
                CanJni.CcH6WcCarCameraSet(8, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_avmzdjh, R.string.can_cmyssz, R.string.can_ldxsmssz, R.string.can_avm_gzms};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_trunk_close, R.string.can_driving_ds, R.string.can_low_mid_speed};
        this.mPopValueIds[1] = new int[]{R.string.can_color_silver, R.string.can_color_red, R.string.can_gray};
        this.mPopValueIds[2] = new int[]{R.string.can_trunk_close, R.string.can_ldxsmssz_2};
        this.mPopValueIds[3] = new int[]{R.string.can_ldxsmssz_2, R.string.can_ldxsmssz_3};
        this.mSetData = new CanDataInfo.CcH6WcCamerSta();
    }

    public void ResetData(boolean check) {
        CanJni.CcH6WcGetCarCameraSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            Log.d("lq", "mSetData.Avmzdjh:" + this.mSetData.Avmzdjh);
            updateItem(new int[]{this.mSetData.Avmzdjh, this.mSetData.Cmyssz, this.mSetData.RadarDis, this.mSetData.AvmMode});
        }
    }

    public void QueryData() {
    }
}
