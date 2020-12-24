package com.ts.can;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGolfCameraSetView extends CanScrollCarInfoView {
    protected static final int ITEM_BRI = 0;
    protected static final int ITEM_CON = 1;
    protected static final int ITEM_SAT = 2;
    private CanDataInfo.GolfCarRvsCameraMode mSetData;

    public CanGolfCameraSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.GolfSendCmd(71, pos);
                return;
            case 1:
                CanJni.GolfSendCmd(72, pos);
                return;
            case 2:
                CanJni.GolfSendCmd(73, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_cx4_drive_light, R.string.can_con, R.string.can_sat};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = 30;
        iArr2[1] = 70;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[0] = 30;
        iArr4[1] = 70;
        iArr4[2] = 1;
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[0] = 30;
        iArr6[1] = 70;
        iArr6[2] = 1;
        iArr5[2] = iArr6;
        this.mSetData = new CanDataInfo.GolfCarRvsCameraMode();
    }

    public void ResetData(boolean check) {
        CanJni.MagotenGetRvsCameraMode(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.RvsBright, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.RvsBright)}));
            updateItem(1, this.mSetData.RvsContrast, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.RvsContrast)}));
            updateItem(2, this.mSetData.RvsColor, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.RvsColor)}));
        }
    }

    public void QueryData() {
        CanJni.GolfQuery(64, 176);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }
}
