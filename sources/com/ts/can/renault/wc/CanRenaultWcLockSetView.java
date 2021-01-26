package com.ts.can.renault.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanRenaultWcLockSetView extends CanScrollCarInfoView {
    protected static final int ITEM_JYMS = 2;
    protected static final int ITEM_KJJS_LKSC = 1;
    protected static final int ITEM_LKHZDSC = 3;
    protected static final int ITEM_WYSSC_JS = 0;
    private CanDataInfo.RenaulWcCarSetData mSetData;

    public CanRenaultWcLockSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.RenaultWcCarSet(37, Neg(this.mSetData.Wysscjs), 0, 0);
                return;
            case 1:
                CanJni.RenaultWcCarSet(38, Neg(this.mSetData.KjjsLksc), 0, 0);
                return;
            case 2:
                CanJni.RenaultWcCarSet(39, Neg(this.mSetData.Jyms), 0, 0);
                return;
            case 3:
                CanJni.RenaultWcCarSet(40, Neg(this.mSetData.Lkhzdsc), 0, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_wyssc_js, R.string.can_kjjs_lksc, R.string.can_mute_mode, R.string.can_lkhzdsc};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mSetData = new CanDataInfo.RenaulWcCarSetData();
    }

    public void ResetData(boolean check) {
        CanJni.RenaultWcGetCarSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Wysscjs);
            updateItem(1, this.mSetData.KjjsLksc);
            updateItem(2, this.mSetData.Jyms);
            updateItem(3, this.mSetData.Lkhzdsc);
        }
    }

    public void QueryData() {
    }
}
