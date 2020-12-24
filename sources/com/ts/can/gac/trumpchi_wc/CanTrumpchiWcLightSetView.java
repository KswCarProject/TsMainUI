package com.ts.can.gac.trumpchi_wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanTrumpchiWcLightSetView extends CanScrollCarInfoView {
    private CanDataInfo.GCWcCarSet mCarSet;

    public CanTrumpchiWcLightSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.TrumpchiWcCarSet(18, item);
        } else if (id == 3) {
            CanJni.TrumpchiWcCarSet(21, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.TrumpchiWcCarSet(19, Neg(this.mCarSet.Wdzxfz));
                return;
            case 2:
                CanJni.TrumpchiWcCarSet(20, Neg(this.mCarSet.Rjxcd));
                return;
            case 4:
                CanJni.TrumpchiWcCarSet(27, Neg(this.mCarSet.Fwdkz));
                return;
            case 5:
                CanJni.TrumpchiWcCarSet(26, Neg(this.mCarSet.Jsmdts));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_bwhj, R.string.can_wdzxfz, R.string.can_rjxcd, R.string.can_zddglmd, R.string.can_fwdkzfs, R.string.can_unlock_mingdi};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_off, R.string.can_only_jgd, R.string.can_jghwd};
        this.mPopValueIds[3] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mCarSet = new CanDataInfo.GCWcCarSet();
    }

    public void ResetData(boolean check) {
        CanJni.TrumpchiWcGetCarSet(this.mCarSet);
        if (!i2b(this.mCarSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSet.Update)) {
            this.mCarSet.Update = 0;
            updateItem(0, this.mCarSet.Bwhjzm);
            updateItem(1, this.mCarSet.Wdzxfz);
            updateItem(2, this.mCarSet.Rjxcd);
            updateItem(3, this.mCarSet.Zddglmd);
            updateItem(4, this.mCarSet.Fwdkz);
            updateItem(5, this.mCarSet.Jsmdts);
        }
    }

    public void QueryData() {
    }
}
