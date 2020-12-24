package com.ts.can.swm.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSwmRzcAvmSetView extends CanScrollCarInfoView {
    public static final int ITEM_3DHR = 3;
    public static final int ITEM_3DZGSXS = 1;
    public static final int ITEM_CSGBGN = 4;
    public static final int ITEM_DLDCYX = 2;
    public static final int ITEM_FZXKG = 0;
    public static final int ITEM_MAX = 5;
    private CanDataInfo.SwmRzc_Set mSetData;

    public CanSwmRzcAvmSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.SwmRzcAvmSet(Neg(this.mSetData.Fzxkg) + 65);
                return;
            case 1:
                CanJni.SwmRzcAvmSet(Neg(this.mSetData.sDZgsxs) + 67);
                return;
            case 2:
                CanJni.SwmRzcAvmSet(Neg(this.mSetData.Dldcyx) + 69);
                return;
            case 3:
                CanJni.SwmRzcAvmSet(Neg(this.mSetData.sDHr) + 71);
                return;
            case 4:
                CanJni.SwmRzcAvmSet(Neg(this.mSetData.Csgbgn) + 73);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_fzxkg, R.string.can_3dzgsxs, R.string.can_geely_boy_dldcyx, R.string.can_3dhr, R.string.can_csgb};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mSetData = new CanDataInfo.SwmRzc_Set();
    }

    public void ResetData(boolean check) {
        CanJni.SwmRzcGetSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Fzxkg);
            updateItem(1, this.mSetData.sDZgsxs);
            updateItem(2, this.mSetData.Dldcyx);
            updateItem(3, this.mSetData.sDHr);
            updateItem(4, this.mSetData.Csgbgn);
        }
    }

    public void QueryData() {
        CanJni.SwmRzcQuery(65, 0);
    }
}
