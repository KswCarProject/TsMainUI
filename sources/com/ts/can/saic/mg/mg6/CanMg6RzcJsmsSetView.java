package com.ts.can.saic.mg.mg6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMg6RzcJsmsSetView extends CanScrollCarInfoView {
    public static final int ITEM_JSMS = 0;
    public static final int ITEM_JSMS_ZDY_DLXY = 1;
    public static final int ITEM_JSMS_ZDY_SSKT = 3;
    public static final int ITEM_JSMS_ZDY_ZXSG = 2;
    private CanDataInfo.MG_GS_JSMS m_SetData;

    public CanMg6RzcJsmsSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.MGGSCarSet(11, item + 1, 0);
                return;
            case 1:
                CanJni.MGGSCarSet(11, 4, (this.m_SetData.Zxsg << 4) | (this.m_SetData.Sskt << 3) | (item << 6));
                return;
            case 2:
                CanJni.MGGSCarSet(11, 4, (this.m_SetData.Dlxy << 6) | (this.m_SetData.Sskt << 3) | (item << 4));
                return;
            case 3:
                CanJni.MGGSCarSet(11, 4, (this.m_SetData.Zxsg << 4) | (this.m_SetData.Dlxy << 6) | (item << 3));
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
        this.mItemTitleIds = new int[]{R.string.can_psa_wc_jsms, R.string.can_dlxy, R.string.can_mg_turnfell, R.string.can_sskt};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_eco, R.string.can_mode_normal, R.string.can_sport, R.string.can_gxh};
        this.mPopValueIds[1] = new int[]{R.string.can_eco, R.string.can_mode_normal, R.string.can_sport};
        this.mItemVisibles[1] = 0;
        this.mPopValueIds[2] = new int[]{R.string.can_lightness, R.string.can_mode_normal, R.string.can_calm};
        this.mItemVisibles[2] = 0;
        this.mPopValueIds[3] = new int[]{R.string.can_eco, R.string.can_mode_normal};
        this.mItemVisibles[3] = 0;
        this.m_SetData = new CanDataInfo.MG_GS_JSMS();
    }

    public void doOnResume() {
    }

    public void ResetData(boolean check) {
        CanJni.MGGSGetJsmsData(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(0, this.m_SetData.Mode);
            if (this.m_SetData.Mode == 3) {
                showItem(1, 1);
                showItem(2, 1);
                showItem(3, 1);
            } else {
                showItem(1, 0);
                showItem(2, 0);
                showItem(3, 0);
            }
            updateItem(1, this.m_SetData.Dlxy);
            updateItem(2, this.m_SetData.Zxsg);
            updateItem(3, this.m_SetData.Sskt);
        }
    }

    public void QueryData() {
        CanJni.MGGSQuery(83);
    }
}
