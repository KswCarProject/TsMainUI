package com.ts.can.gac.trumpchi_wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanTrumpchiWcACSetView extends CanScrollCarInfoView {
    private CanDataInfo.GCWcCarSet mCarSet;

    public CanTrumpchiWcACSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.TrumpchiWcCarSet(3, item);
        } else if (id == 2) {
            CanJni.TrumpchiWcCarSet(4, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.TrumpchiWcCarSet(2, Neg(this.mCarSet.AutoYsjzt));
        } else if (id == 3) {
            CanJni.TrumpchiWcCarSet(22, Neg(this.mCarSet.Flzms));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ac_ysj, R.string.can_ac_loop, R.string.can_ac_ssqx, R.string.can_ac_flzms};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[1] = new int[]{R.string.can_mzd_cx4_drive_owner, R.string.can_mzd_cx4_drive_auto};
        this.mPopValueIds[2] = new int[]{R.string.can_sdhm, R.string.can_normal, R.string.can_sdks};
        this.mCarSet = new CanDataInfo.GCWcCarSet();
    }

    public void ResetData(boolean check) {
        CanJni.TrumpchiWcGetCarSet(this.mCarSet);
        if (!i2b(this.mCarSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSet.Update)) {
            this.mCarSet.Update = 0;
            updateItem(0, this.mCarSet.AutoYsjzt);
            updateItem(1, this.mCarSet.AutoNwxhkzfs);
            updateItem(2, this.mCarSet.Ktssqxsz);
            updateItem(3, this.mCarSet.Flzms);
        }
    }

    public void QueryData() {
    }
}
