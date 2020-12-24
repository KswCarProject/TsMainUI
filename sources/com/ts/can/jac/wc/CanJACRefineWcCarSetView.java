package com.ts.can.jac.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanJACRefineWcCarSetView extends CanScrollCarInfoView {
    private String[] mHldtjValues;
    private CanDataInfo.JACWC_SETDATA mSetData;

    public CanJACRefineWcCarSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.JACRefineWcCarSet(3, item + 1);
                return;
            case 1:
                CanJni.JACRefineWcCarSet(4, item + 1);
                return;
            case 2:
                CanJni.JACRefineWcCarSet(5, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ldwlmd, R.string.can_hldyltjxy, R.string.can_hldyptjxy};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_ac_low, R.string.can_ac_high};
        this.mPopValueIds[1] = new int[]{R.string.app_name};
        this.mPopValueIds[2] = new int[]{R.string.app_name};
        this.mSetData = new CanDataInfo.JACWC_SETDATA();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        this.mHldtjValues = new String[]{"Level 1", "Level 2", "Level 3", "Level 4", "Level 5"};
        getScrollManager().RemoveView(1);
        this.mItemObjects[1] = getScrollManager().addItemPopupList(this.mItemTitleIds[1], this.mHldtjValues, 1, this, 1);
        getScrollManager().RemoveView(2);
        this.mItemObjects[2] = getScrollManager().addItemPopupList(this.mItemTitleIds[2], this.mHldtjValues, 2, this, 2);
    }

    public void ResetData(boolean check) {
        CanJni.JACRefineWcGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.LDW - 1);
            updateItem(1, this.mSetData.R_radarYltj - 1);
            updateItem(2, this.mSetData.R_radarYptj - 1);
        }
    }

    public void QueryData() {
    }
}
