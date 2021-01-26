package com.ts.can.saic.mg.mg6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMg6RzcSystemSetView extends CanScrollCarInfoView {
    public static final int ITEM_LANG = 0;
    private CanDataInfo.MG_GS_SET m_SetData;

    public CanMg6RzcSystemSetView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.MGGSCarSet(10, 1, item + 1);
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
        this.mItemTitleIds = new int[]{R.string.can_car_lang};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.lang_cn, R.string.lang_en_uk};
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
            updateItem(0, this.m_SetData.Lang - 1);
        }
    }

    public void QueryData() {
        CanJni.MGGSQuery(57);
    }
}
