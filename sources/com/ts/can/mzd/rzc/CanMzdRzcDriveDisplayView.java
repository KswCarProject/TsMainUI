package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMzdRzcDriveDisplayView extends CanScrollCarInfoView {
    private CanDataInfo.Mzd_Rzc_Jsxs mSetData;

    public CanMzdRzcDriveDisplayView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        if (id == 3) {
            CanJni.MzdRzcGetCarJsxsSet(2, item);
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 0) {
            CanJni.MzdRzcGetCarJsxsSet(1, pos);
        } else if (id == 1) {
            CanJni.MzdRzcGetCarJsxsSet(4, pos);
        } else if (id == 2) {
            CanJni.MzdRzcGetCarJsxsSet(3, pos);
        }
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_cx4_drive_height, R.string.can_mzd_cx4_drive_light, R.string.can_mzd_cx4_drive_jiaozhun, R.string.can_mzd_cx4_drive_light_control};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 26;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 40;
        iArr4[2] = 1;
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 4;
        iArr6[2] = 1;
        iArr5[2] = iArr6;
        this.mPopValueIds[3] = new int[]{R.string.can_mzd_cx4_drive_auto, R.string.can_mzd_cx4_drive_owner};
        this.mSetData = new CanDataInfo.Mzd_Rzc_Jsxs();
    }

    public void ResetData(boolean check) {
        CanJni.MzdRzcGetCarJsxsData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.jsxsgd, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.jsxsgd - 13)}));
            updateItem(1, this.mSetData.jsxsld, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.jsxsld - 20)}));
            updateItem(2, this.mSetData.jsxsjz, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.jsxsjz - 2)}));
            updateItem(3, this.mSetData.ldkz);
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(64, 0);
    }
}
