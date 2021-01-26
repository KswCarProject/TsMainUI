package com.ts.can.hyundai.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHyundaiRzcAirSetView extends CanScrollCarInfoView {
    private static final int ITEM_AIR = 0;
    private CanDataInfo.HyCarSet mSetData;

    public CanHyundaiRzcAirSetView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    /* access modifiers changed from: protected */
    public int NegSwSet(int val) {
        if (1 == val) {
            return 2;
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public int SwSet(int val) {
        if (1 == val) {
            return 0;
        }
        return 1;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.HyundaiRzcCarSet(10, NegSwSet(this.mSetData.Kqnxh));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_kqnxh};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH};
        this.mSetData = new CanDataInfo.HyCarSet();
    }

    public void ResetData(boolean check) {
        CanJni.HyundaiRzcGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.KqnxhUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.KqnxhUpdate)) {
            this.mSetData.KqnxhUpdate = 0;
            updateItem(0, SwSet(this.mSetData.Kqnxh));
        }
    }

    public void QueryData() {
        CanJni.HyundaiRzcQuery(82, 10);
    }
}
