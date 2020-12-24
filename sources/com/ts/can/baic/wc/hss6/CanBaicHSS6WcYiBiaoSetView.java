package com.ts.can.baic.wc.hss6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBaicHSS6WcYiBiaoSetView extends CanScrollCarInfoView {
    private CanDataInfo.BaicHsS6YbSet mAdtData;
    private CanDataInfo.BaicHsS6YbSet mSetData;

    public CanBaicHSS6WcYiBiaoSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        if (id == 4) {
            CanJni.BaicWcS6YbSet(1, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.BaicWcS6YbSet(5, Neg(this.mSetData.LDWjsy));
                return;
            case 1:
                CanJni.BaicWcS6YbSet(4, Neg(this.mSetData.BSDjsy));
                return;
            case 2:
                CanJni.BaicWcS6YbSet(3, Neg(this.mSetData.Djxstx));
                return;
            case 3:
                CanJni.BaicWcS6YbSet(2, Neg(this.mSetData.Gscstx));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ybset_ldw, R.string.can_ybset_bsd, R.string.can_ybset_djxs, R.string.can_ybset_gscs, R.string.can_TPMS_DW};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[4] = new int[]{R.string.can_pressure_kpa, R.string.can_pressure_bar, R.string.can_pressure_psi};
        this.mAdtData = new CanDataInfo.BaicHsS6YbSet();
        this.mSetData = new CanDataInfo.BaicHsS6YbSet();
    }

    public void ResetData(boolean check) {
        CanJni.BaicWcS6GetCarYbSet(this.mAdtData, 0);
        if (i2b(this.mAdtData.UpdateOnce) && (!check || i2b(this.mAdtData.Update))) {
            this.mAdtData.Update = 0;
            showItem(new int[]{this.mAdtData.LDWjsy, this.mAdtData.BSDjsy, this.mAdtData.Djxstx, this.mAdtData.Gscstx, this.mAdtData.Tydw});
        }
        CanJni.BaicWcS6GetCarYbSet(this.mSetData, 1);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.LDWjsy, this.mSetData.BSDjsy, this.mSetData.Djxstx, this.mSetData.Gscstx, this.mSetData.Tydw});
        }
    }

    public void QueryData() {
        CanJni.BaicWcS6Query(5, 1, 104);
    }
}
