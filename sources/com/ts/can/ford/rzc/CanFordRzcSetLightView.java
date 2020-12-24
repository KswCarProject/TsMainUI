package com.ts.can.ford.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordRzcSetLightView extends CanScrollCarInfoView {
    private CanDataInfo.FordRzcFwd mFwdData;

    public CanFordRzcSetLightView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        if (id != 0) {
            return;
        }
        if (CanJni.GetSubType() == 5) {
            CanJni.FordRzcLightSet(item, this.mFwdData.Val);
        } else {
            CanJni.FordRzcLightSet(item + 1, this.mFwdData.Val);
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 1) {
            CanJni.FordRzcLightSet(this.mFwdData.Color, pos);
        }
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_fwd_color, R.string.can_fwd_value};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS};
        this.mFwdData = new CanDataInfo.FordRzcFwd();
        CanJni.FordRzcGetLightSet(this.mFwdData);
        if (CanJni.GetSubType() == 5) {
            this.mPopValueIds[0] = new int[]{R.string.can_magoten_light_color_0, R.string.can_color_tyellow, R.string.can_color_red, R.string.can_color_pink, R.string.can_purple, R.string.can_magoten_light_color_3, R.string.can_magoten_light_color_2, R.string.can_color_skyblue};
        } else {
            this.mPopValueIds[0] = new int[]{R.string.can_cold_blue, R.string.can_orange_color, R.string.can_soft_blue, R.string.can_color_red, R.string.can_magoten_light_color_2, R.string.can_magoten_light_color_3, R.string.can_purple};
        }
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 100;
        iArr2[2] = 5;
        iArr[1] = iArr2;
    }

    public void ResetData(boolean check) {
        CanJni.FordRzcGetLightSet(this.mFwdData);
        if (!i2b(this.mFwdData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mFwdData.Update)) {
            this.mFwdData.Update = 0;
            if (CanJni.GetSubType() == 5) {
                updateItem(0, this.mFwdData.Color);
            } else {
                updateItem(0, this.mFwdData.Color - 1);
            }
            updateItem(1, this.mFwdData.Val);
        }
    }

    public void QueryData() {
        CanJni.FordQuery(97, 0);
    }
}
