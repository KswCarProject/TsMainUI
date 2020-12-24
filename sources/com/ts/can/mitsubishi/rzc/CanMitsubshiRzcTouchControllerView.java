package com.ts.can.mitsubishi.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMitsubshiRzcTouchControllerView extends CanScrollCarInfoView {
    private static final int ITEM_TOUCH_SENSITIVITY = 0;
    private CanDataInfo.MitSubishiRzcSet m_SetData;

    public CanMitsubshiRzcTouchControllerView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.MitSubishiRzcCarSet(0, item);
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
        this.mItemTitleIds = new int[]{R.string.can_ckmblmd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_sensitivity_high, R.string.can_sensitivity_mid, R.string.can_sensitivity_low};
        this.m_SetData = new CanDataInfo.MitSubishiRzcSet();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }

    public void ResetData(boolean check) {
        CanJni.MitSubishiRzcGetSetData(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(0, this.m_SetData.TouchSensitivity);
        }
    }

    public void QueryData() {
        CanJni.MitSubishiRzcQuery(80, 0);
    }
}
