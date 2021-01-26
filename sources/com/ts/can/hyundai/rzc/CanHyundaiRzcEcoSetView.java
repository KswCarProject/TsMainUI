package com.ts.can.hyundai.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHyundaiRzcEcoSetView extends CanScrollCarInfoView {
    private static final int ITEM_DDJNH = 3;
    private static final int ITEM_ECODJ = 0;
    private static final int ITEM_HHDLYH = 2;
    private static final int ITEM_PJNH = 1;
    private static final int ITEM_SSCS = 4;
    private static final int ITEM_ZXSLC = 5;
    private CanDataInfo.HyRzcXnySet2 mSetData;

    public CanHyundaiRzcEcoSetView(Activity activity) {
        super(activity, 6);
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
        this.mItemTitleIds = new int[]{R.string.can_ecodj, R.string.can_jac_pjnh, R.string.can_hhdlyh, R.string.can_ddjnh, R.string.can_sscs, R.string.can_zxslc};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mSetData = new CanDataInfo.HyRzcXnySet2();
    }

    public void ResetData(boolean check) {
        CanJni.HyundaiRzcGetXnySet2(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            if (this.mSetData.Eco <= -1 || this.mSetData.Eco > 8) {
                updateItem(0, this.mSetData.Eco, "--");
            } else {
                updateItem(0, this.mSetData.Eco, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.Eco)}));
            }
            if (this.mSetData.Pjnh < 999) {
                updateItem(1, this.mSetData.Pjnh, String.format("%.1fL/100KM", new Object[]{Double.valueOf(((double) this.mSetData.Pjnh) * 0.1d)}));
            } else {
                updateItem(1, this.mSetData.Pjnh, "--");
            }
            if (this.mSetData.Hhdlyh < 999) {
                updateItem(2, this.mSetData.Hhdlyh, String.format("%.1fL/KM", new Object[]{Double.valueOf(((double) this.mSetData.Hhdlyh) * 0.1d)}));
            } else {
                updateItem(1, this.mSetData.Pjnh, "--");
            }
            updateItem(3, this.mSetData.Ddjnh, String.format("%dKW", new Object[]{Integer.valueOf(this.mSetData.Ddjnh)}));
            updateItem(4, this.mSetData.Sscs, String.format("%dKM/H", new Object[]{Integer.valueOf(this.mSetData.Sscs)}));
            updateItem(5, this.mSetData.Zxslc, String.format("%.1fKM", new Object[]{Double.valueOf(((double) this.mSetData.Zxslc) * 0.1d)}));
        }
    }

    public void QueryData() {
        CanJni.HyundaiRzcXnySet(255, 1, 0, 0);
        Sleep(10);
        CanJni.HyundaiRzcQuery(84, 0);
    }
}
