package com.ts.can.saic.mg.mg6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMg6RzcConvSetView extends CanScrollCarInfoView {
    public static final int ITEM_BWHJ = 0;
    public static final int ITEM_XCZS = 1;
    public static final int ITEM_YBD = 2;
    public static final int ITEM_YBLD = 3;
    private CanDataInfo.MG_GS_SET m_SetData;

    public CanMg6RzcConvSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.MGGSCarSet(3, 1, item);
                return;
            case 1:
                CanJni.MGGSCarSet(3, 2, item);
                return;
            case 2:
                CanJni.MGGSCarSet(3, 4, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 3:
                CanJni.MGGSCarSet(7, 1, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_bwhj, R.string.can_xcd, R.string.can_yb_light, R.string.can_ybld};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS};
        this.mPopValueIds[0] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_mode_on, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s};
        this.mPopValueIds[1] = new int[]{R.string.can_only_light, R.string.can_dghlb};
        this.mPopValueIds[2] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_mode_on, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = 1;
        iArr2[1] = 10;
        iArr2[2] = 1;
        iArr[3] = iArr2;
        this.m_SetData = new CanDataInfo.MG_GS_SET();
    }

    public void doOnResume() {
    }

    public void ResetData(boolean check) {
        CanJni.MGGSGetSetData4(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(0, this.m_SetData.Bwhj2);
            updateItem(1, this.m_SetData.Xczs);
            updateItem(2, this.m_SetData.Ybd);
            updateItem(3, this.m_SetData.Ybld);
        }
    }

    public void QueryData() {
        CanJni.MGGSQuery(57);
    }
}
