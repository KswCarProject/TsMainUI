package com.ts.can.saic.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSaicRWMGWcCarDrivAssistSetView extends CanScrollCarInfoView {
    private CanDataInfo.SailRwMg_SetData mSetData;

    public CanSaicRWMGWcCarDrivAssistSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CarSet(32, item);
                return;
            case 3:
                CarSet(18, item);
                return;
            case 4:
                CarSet(14, item);
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
                CarSet(1, Neg(this.mSetData.Zdls));
                return;
            case 1:
                CarSet(2, Neg(this.mSetData.Zdjs));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_tigger7_auto_lock, R.string.can_zdjs, R.string.can_smart_near_lock, R.string.can_ykjs, R.string.can_mg_turnfell};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[2] = new int[]{R.string.can_jsym, R.string.can_sym};
        this.mPopValueIds[3] = new int[]{R.string.can_jsym, R.string.can_sym};
        this.mPopValueIds[4] = new int[]{R.string.can_mode_normal, R.string.can_comfort, R.string.can_sport};
        this.mSetData = new CanDataInfo.SailRwMg_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.SaicRwMgGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Zdls);
            updateItem(1, this.mSetData.Zdjs);
            updateItem(2, this.mSetData.Znjcjssd);
            updateItem(3, this.mSetData.Ykjsmssd);
            updateItem(4, this.mSetData.Zxsg);
        }
    }

    private void CarSet(int cmd, int para1) {
        CanJni.SaicRwMgCarSet(cmd, para1, 255, 255);
    }

    public void QueryData() {
        CanJni.SaicRwMgQuery(5, 1, 102);
    }
}
