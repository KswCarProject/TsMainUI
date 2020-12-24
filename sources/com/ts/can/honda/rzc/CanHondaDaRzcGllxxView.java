package com.ts.can.honda.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaDaRzcGllxxView extends CanScrollCarInfoView {
    public static final int ITEM_CLDC = 3;
    public static final int ITEM_DCCL = 2;
    public static final int ITEM_FDJCL = 1;
    public static final int ITEM_FDJDC = 0;
    protected CanDataInfo.HondaDARzc_Pw mSetData;

    public CanHondaDaRzcGllxxView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_fdjdc, R.string.can_fdjcl, R.string.can_dccl, R.string.can_cldc};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mSetData = new CanDataInfo.HondaDARzc_Pw();
    }

    public void ResetData(boolean check) {
        CanJni.HondDARzcGetPwInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.FdjDc);
            updateItem(1, this.mSetData.FdjCl);
            updateItem(2, this.mSetData.DcCl);
            updateItem(3, this.mSetData.ClDc);
        }
    }

    public void QueryData() {
    }
}
