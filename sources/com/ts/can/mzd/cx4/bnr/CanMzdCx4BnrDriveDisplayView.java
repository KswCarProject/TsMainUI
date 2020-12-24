package com.ts.can.mzd.cx4.bnr;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMzdCx4BnrDriveDisplayView extends CanScrollCarInfoView {
    private CanDataInfo.Cx4_CarSet_Data mSetData;

    public CanMzdCx4BnrDriveDisplayView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.MzdCx4CarSet(15, item);
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 3) {
            CanJni.MzdCx4CarSet(14, pos + 13);
        } else if (id == 4) {
            CanJni.MzdCx4CarSet(16, pos + 20);
        } else if (id == 5) {
            CanJni.MzdCx4CarSet(19, pos + 2);
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 1) {
            CanJni.MzdCx4CarSet(17, Neg(this.mSetData.jsxszdjsxs));
        } else if (id == 2) {
            CanJni.MzdCx4CarSet(18, Neg(this.mSetData.jsxsdh));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_cx4_drive_light_control, R.string.can_mzd_cx4_drive_display, R.string.can_mzd_cx4_drive_navigation, R.string.can_mzd_cx4_drive_height, R.string.can_mzd_cx4_drive_light, R.string.can_mzd_cx4_drive_jiaozhun};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS};
        this.mPopValueIds[0] = new int[]{R.string.can_mzd_cx4_drive_auto, R.string.can_mzd_cx4_drive_owner};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = -13;
        iArr2[1] = 13;
        iArr2[2] = 1;
        iArr[3] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[0] = -20;
        iArr4[1] = 20;
        iArr4[2] = 1;
        iArr3[4] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[0] = -2;
        iArr6[1] = 2;
        iArr6[2] = 1;
        iArr5[5] = iArr6;
        this.mSetData = new CanDataInfo.Cx4_CarSet_Data();
    }

    public void ResetData(boolean check) {
        CanJni.MzdCx4GetCarSetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.jsxsldkz, this.mSetData.jsxszdjsxs, this.mSetData.jsxsdh, this.mSetData.jsxsgd - 13, this.mSetData.jsxsld - 20, this.mSetData.jsxsjz - 2});
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(9, 0);
    }
}
