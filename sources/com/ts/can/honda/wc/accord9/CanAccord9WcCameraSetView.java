package com.ts.can.honda.wc.accord9;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;

public class CanAccord9WcCameraSetView extends CanScrollCarInfoView {
    private CanDataInfo.HondaAccord9WcDispInfo mDispData;

    public CanAccord9WcCameraSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                CanJni.HondaWcCameraSet(1, pos);
                return;
            case 2:
                CanJni.HondaWcCameraSet(2, checkPos(pos));
                return;
            case 3:
                CanJni.HondaWcCameraSet(3, checkPos(pos));
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                FtSet.Setlgb4(Neg(FtSet.Getlgb4()));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_has_right_camera, R.string.can_mzd_cx4_drive_light, R.string.can_con, R.string.can_sat};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 255;
        iArr2[2] = 1;
        iArr[1] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 10;
        iArr4[2] = 1;
        iArr3[2] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 10;
        iArr6[2] = 1;
        iArr5[3] = iArr6;
        this.mDispData = new CanDataInfo.HondaAccord9WcDispInfo();
    }

    public void ResetData(boolean check) {
        CanJni.HondaWcAccord9GetDisplayData(this.mDispData);
        if (i2b(this.mDispData.UpdateOnce) && (!check || i2b(this.mDispData.Update))) {
            this.mDispData.Update = 0;
            updateItem(1, this.mDispData.Bri);
            updateItem(2, changeVal(this.mDispData.Con), new StringBuilder(String.valueOf(changeVal(this.mDispData.Con) - 5)).toString());
            updateItem(3, changeVal(this.mDispData.Sat), new StringBuilder(String.valueOf(changeVal(this.mDispData.Sat) - 5)).toString());
        }
        updateItem(0, FtSet.Getlgb4());
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, Can.CAN_FLAT_WC);
    }

    private int changeVal(int val) {
        switch (val) {
            case 0:
                return 5;
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 8;
            case 4:
                return 9;
            case 5:
                return 10;
            case Can.CAN_MG_ZS_WC:
                return 0;
            case Can.CAN_TOYOTA_SP_XP:
                return 1;
            case Can.CAN_FORD_ESCORT_LY:
                return 2;
            case Can.CAN_FLAT_RZC:
                return 3;
            case 255:
                return 4;
            default:
                return 0;
        }
    }

    private int checkPos(int val) {
        switch (val) {
            case 0:
                return Can.CAN_MG_ZS_WC;
            case 1:
                return Can.CAN_TOYOTA_SP_XP;
            case 2:
                return Can.CAN_FORD_ESCORT_LY;
            case 3:
                return Can.CAN_FLAT_RZC;
            case 4:
                return 255;
            case 5:
                return 0;
            case 6:
                return 1;
            case 7:
                return 2;
            case 8:
                return 3;
            case 9:
                return 4;
            case 10:
                return 5;
            default:
                return 0;
        }
    }
}
