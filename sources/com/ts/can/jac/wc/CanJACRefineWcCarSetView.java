package com.ts.can.jac.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanJACRefineWcCarSetView extends CanScrollCarInfoView {
    private CanDataInfo.JACWC_SETDATA mSetData;

    public CanJACRefineWcCarSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.JACRefineWcCarSet(3, item + 1);
                return;
            case 3:
                CanJni.JACRefineWcCarSet(6, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                CanJni.JACRefineWcCarSet(4, pos);
                return;
            case 2:
                CanJni.JACRefineWcCarSet(5, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ldwlmd, R.string.can_hldyltjxy, R.string.can_hldyptjxy, R.string.can_bcfs};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_ac_low, R.string.can_ac_high, R.string.can_default};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = 1;
        iArr2[1] = 5;
        iArr2[2] = 1;
        iArr[1] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[0] = 1;
        iArr4[1] = 5;
        iArr4[2] = 1;
        iArr3[2] = iArr4;
        this.mPopValueIds[3] = new int[]{R.string.can_czstc, R.string.can_pxstc};
        this.mSetData = new CanDataInfo.JACWC_SETDATA();
    }

    public void ResetData(boolean check) {
        CanJni.JACRefineWcGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.LDW - 1);
            updateItem(1, this.mSetData.R_radarYltj, "Lev " + this.mSetData.R_radarYltj);
            updateItem(2, this.mSetData.R_radarYptj, "Lev " + this.mSetData.R_radarYptj);
            updateItem(3, this.mSetData.ParkType - 1);
        }
    }

    public void QueryData() {
    }
}
