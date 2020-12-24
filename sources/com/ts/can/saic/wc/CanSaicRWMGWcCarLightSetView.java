package com.ts.can.saic.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSaicRWMGWcCarLightSetView extends CanScrollCarInfoView {
    private CanDataInfo.SailRwMg_SetData mSetData;

    public CanSaicRWMGWcCarLightSetView(Activity activity) {
        super(activity, 10);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CarSet(59, item);
                return;
            case 5:
                CarSet(9, item);
                return;
            case 9:
                CarSet(10, item);
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
                CarSet(58, Neg(this.mSetData.Bwhjzt));
                return;
            case 2:
                CarSet(3, Neg(this.mSetData.Bwhjdcd));
                return;
            case 3:
                CarSet(4, Neg(this.mSetData.Bwhjjgd));
                return;
            case 4:
                CarSet(5, Neg(this.mSetData.Bwhjhwd));
                return;
            case 6:
                CarSet(6, Neg(this.mSetData.Xcdzsdcd));
                return;
            case 7:
                CarSet(7, Neg(this.mSetData.Xcdzsjgd));
                return;
            case 8:
                CarSet(8, Neg(this.mSetData.Xcdzshwd));
                return;
            default:
                return;
        }
    }

    private void CarSet(int cmd, int para1) {
        CanJni.SaicRwMgCarSet(cmd, para1, 255, 255);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_bwhj, R.string.can_xcd, R.string.can_bwhjdcd, R.string.can_bwhjjgd, R.string.can_bwhjhwd, R.string.can_dgsjkz_bwhj, R.string.can_xcdzsdcd, R.string.can_xcdzsjgd, R.string.can_xcdzshwd, R.string.can_cxsj_xcd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_only_light, R.string.can_dghlb};
        this.mPopValueIds[5] = new int[]{R.string.can_30s, R.string.can_1min, R.string.can_1min30s, R.string.can_2min, R.string.can_2min30s, R.string.can_3min, R.string.can_3min30s, R.string.can_4min, R.string.can_4min30s, R.string.can_5min};
        this.mPopValueIds[9] = new int[]{R.string.can_30s, R.string.can_1min, R.string.can_1min30s, R.string.can_2min, R.string.can_2min30s, R.string.can_3min, R.string.can_3min30s, R.string.can_4min, R.string.can_4min30s, R.string.can_5min};
        this.mSetData = new CanDataInfo.SailRwMg_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.SaicRwMgGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Bwhjzt, this.mSetData.Xczszt, this.mSetData.Bwhjdcd, this.mSetData.Bwhjjgd, this.mSetData.Bwhjhwd, this.mSetData.Bwhjcxsj, this.mSetData.Xcdzsdcd, this.mSetData.Xcdzsjgd, this.mSetData.Xcdzshwd, this.mSetData.Xcdzscxsj});
        }
    }

    public void QueryData() {
        CanJni.SaicRwMgQuery(5, 1, 102);
    }
}
