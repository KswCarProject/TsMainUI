package com.ts.can.renault.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanRenaultWcAdasSetView extends CanScrollCarInfoView {
    protected static final int ITEM_ADAS_JG = 1;
    protected static final int ITEM_ADAS_ZDQD = 0;
    private CanDataInfo.RenaulWcCarSetData mSetData;

    public CanRenaultWcAdasSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.RenaultWcCarSet(32, item, 0, 0);
                return;
            case 1:
                CanJni.RenaultWcCarSet(33, item, 0, 0);
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
        this.mItemTitleIds = new int[]{R.string.can_zdqd, R.string.can_warn};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
        this.mPopValueIds[1] = new int[]{R.string.can_jp_early, R.string.can_cdpyyzxt_1, R.string.can_cdpyyzxt_2};
        this.mSetData = new CanDataInfo.RenaulWcCarSetData();
    }

    public void ResetData(boolean check) {
        CanJni.RenaultWcGetCarSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.CdbcfzZdqd);
            updateItem(1, this.mSetData.CdbcfzJg);
        }
    }

    public void QueryData() {
    }
}
