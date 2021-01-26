package com.ts.can.ford.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordRzcSetLightView extends CanScrollCarInfoView {
    protected static final int ITEM_QZDYC = 2;
    protected static final int ITEM_ZDYGD = 3;
    private CanDataInfo.FordRzcFwd mFwdData;
    private CanDataInfo.FordRzcSetInfo mSetData;

    public CanFordRzcSetLightView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (CanJni.GetSubType() == 5) {
                    CanJni.FordRzcLightSet(item, this.mFwdData.Val);
                    return;
                } else {
                    CanJni.FordRzcLightSet(item + 1, this.mFwdData.Val);
                    return;
                }
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                CanJni.FordRzcLightSet(this.mFwdData.Color, pos);
                return;
            case 2:
                CanJni.FordRzcCarSet2(33, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                CanJni.FordRzcCarSet2(34, Neg(this.mSetData.Zdygd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_fwd_color, R.string.can_fwd_value, R.string.can_tigger7_light_delay, R.string.can_light_zdygd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH};
        this.mFwdData = new CanDataInfo.FordRzcFwd();
        this.mSetData = new CanDataInfo.FordRzcSetInfo();
        CanJni.FordRzcGetLightSet(this.mFwdData);
        if (CanJni.GetSubType() == 5) {
            this.mPopValueIds[0] = new int[]{R.string.can_magoten_light_color_0, R.string.can_color_tyellow, R.string.can_color_red, R.string.can_color_pink, R.string.can_purple, R.string.can_magoten_light_color_3, R.string.can_magoten_light_color_2, R.string.can_color_skyblue};
        } else {
            this.mPopValueIds[0] = new int[]{R.string.can_cold_blue, R.string.can_orange_color, R.string.can_soft_blue, R.string.can_color_red, R.string.can_magoten_light_color_2, R.string.can_magoten_light_color_3, R.string.can_purple};
        }
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 120;
        iArr2[2] = 5;
        iArr[2] = iArr2;
        if (CanJni.GetSubType() != 12) {
            this.mItemVisibles[2] = 0;
            this.mItemVisibles[3] = 0;
        }
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 100;
        iArr4[2] = 5;
        iArr3[1] = iArr4;
    }

    public void ResetData(boolean check) {
        CanJni.FordRzcGetLightSet(this.mFwdData);
        if (i2b(this.mFwdData.UpdateOnce) && (!check || i2b(this.mFwdData.Update))) {
            this.mFwdData.Update = 0;
            if (CanJni.GetSubType() == 5) {
                updateItem(0, this.mFwdData.Color);
            } else {
                updateItem(0, this.mFwdData.Color - 1);
            }
            updateItem(1, this.mFwdData.Val);
        }
        CanJni.FordRzcGetSetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(2, this.mSetData.Qzdyc, String.format("%d s", new Object[]{Integer.valueOf(this.mSetData.Qzdyc)}));
            updateItem(3, this.mSetData.Zdygd);
        }
    }

    public void QueryData() {
        CanJni.FordQuery(97, 0);
        Sleep(10);
        CanJni.FordQuery(40, 0);
    }
}
