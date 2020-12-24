package com.ts.can.honda;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaYiBiaoSetView extends CanScrollCarInfoView {
    private CanDataInfo.HondaTftData mTftData;

    public CanHondaYiBiaoSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.HondaDACarSet(66, item);
        } else if (id == 1) {
            CanJni.HondaDACarSet(65, item);
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 2:
                CanJni.HondaDACarSet(67, pos);
                return;
            case 3:
                CanJni.HondaDACarSet(68, pos);
                return;
            case 4:
                CanJni.HondaDACarSet(69, pos);
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
        this.mItemTitleIds = new int[]{R.string.can_honda_bgms, R.string.can_honda_bjstj, R.string.can_mzd_cx4_drive_light, R.string.can_con, R.string.can_sat};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS};
        this.mPopValueIds[0] = new int[]{R.string.can_scr_close, R.string.can_scr_day, R.string.can_scr_night};
        this.mPopValueIds[1] = new int[]{R.string.can_color_blue, R.string.can_color_amber, R.string.can_color_red, R.string.can_magoten_light_color_2, R.string.can_color_violet};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 10;
        iArr2[2] = 1;
        iArr[2] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 10;
        iArr4[2] = 1;
        iArr3[3] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 10;
        iArr6[2] = 1;
        iArr5[4] = iArr6;
        this.mTftData = new CanDataInfo.HondaTftData();
    }

    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarTftSet(this.mTftData);
        if (!i2b(this.mTftData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTftData.Update)) {
            this.mTftData.Update = 0;
            updateItem(0, this.mTftData.Bgms);
            updateItem(1, this.mTftData.Bjstj);
            updateItem(2, this.mTftData.Ldtj, new StringBuilder(String.valueOf(this.mTftData.Ldtj - 5)).toString());
            updateItem(3, this.mTftData.Dbdtj, new StringBuilder(String.valueOf(this.mTftData.Dbdtj - 5)).toString());
            updateItem(4, this.mTftData.Bhdtj, new StringBuilder(String.valueOf(this.mTftData.Bhdtj - 5)).toString());
        }
    }

    public void QueryData() {
        CanJni.HondaDAQuery(20, 0);
    }
}
