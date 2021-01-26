package com.ts.can.renault.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanRenaultWcDriveAssistSetView extends CanScrollCarInfoView {
    protected static final int ITEM_JSFZ_QFJL = 1;
    protected static final int ITEM_JSFZ_ZDZD = 0;
    protected static final int ITEM_ZXDYL = 2;
    private CanDataInfo.RenaulWcCarSetData mSetData;

    public CanRenaultWcDriveAssistSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.RenaultWcCarSet(36, item, 0, 0);
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
                CanJni.RenaultWcCarSet(34, Neg(this.mSetData.JsfzZdzd), 0, 0);
                return;
            case 1:
                CanJni.RenaultWcCarSet(35, Neg(this.mSetData.JsfzQfjl), 0, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_zdzdfzxt, R.string.can_front_dis, R.string.can_zxdyl};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[2] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mSetData = new CanDataInfo.RenaulWcCarSetData();
    }

    public void ResetData(boolean check) {
        CanJni.RenaultWcGetCarSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.JsfzZdzd);
            updateItem(1, this.mSetData.JsfzQfjl);
            updateItem(2, this.mSetData.Zxdyl);
        }
    }

    public void QueryData() {
    }
}
