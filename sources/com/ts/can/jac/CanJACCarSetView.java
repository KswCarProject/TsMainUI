package com.ts.can.jac;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanJACCarSetView extends CanScrollCarInfoView {
    private static final int CKMBLMD = 2;
    private static final int CNZMYCGBSJ = 4;
    private static final int CWZMYCGBSJ = 3;
    private static final int DWZM = 7;
    private static final int SFTSY = 6;
    private static final int YKHSJZD = 1;
    private static final int YKZDJC = 0;
    private static final int ZCBGLDTJ = 9;
    private static final int ZDCSS = 5;
    private static final int ZXFZD = 8;
    private CanDataInfo.JAC_SET_DATA mSetData;

    public CanJACCarSetView(Activity activity) {
        super(activity, 10);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.JacCarSet(3, item);
                return;
            case 3:
                CanJni.JacCarSet(4, item);
                return;
            case 4:
                CanJni.JacCarSet(5, item);
                return;
            case 5:
                CanJni.JacCarSet(6, item);
                return;
            case 6:
                CanJni.JacCarSet(7, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 9) {
            CanJni.JacCarSet(10, pos);
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.JacCarSet(1, Neg(this.mSetData.Ykzdjc));
                return;
            case 1:
                CanJni.JacCarSet(2, Neg(this.mSetData.Ykhsjzd));
                return;
            case 7:
                CanJni.JacCarSet(8, Neg(this.mSetData.Dwzm));
                return;
            case 8:
                CanJni.JacCarSet(9, Neg(this.mSetData.Zxfzd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ykzdjc, R.string.can_ykhsjzd, R.string.can_ckmblmd, R.string.can_cwzmycgbsj, R.string.can_cnzmycgbsj, R.string.can_auto_speed_lock, R.string.can_set_tip_vol, R.string.can_gps_light, R.string.can_zxfzd, R.string.can_zcbgldtj};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS};
        this.mPopValueIds[2] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mPopValueIds[3] = new int[]{R.string.can_0s, R.string.can_mzd_cx4_time_30s, R.string.can_60s, R.string.can_90s, R.string.can_mzd_cx4_time_120s};
        this.mPopValueIds[4] = new int[]{R.string.can_0s, R.string.can_mzd_cx4_time_15s, R.string.can_mzd_cx4_time_30s, R.string.can_time_45s, R.string.can_60s};
        this.mPopValueIds[5] = new int[]{R.array.can_auto_speed_lock_array};
        this.mPopValueIds[6] = new int[]{R.string.can_trunk_close, R.string.can_ac_low, R.string.can_oil_status_normal, R.string.can_ac_high};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = 1;
        iArr2[1] = 21;
        iArr2[2] = 1;
        iArr[9] = iArr2;
        this.mSetData = new CanDataInfo.JAC_SET_DATA();
    }

    public void ResetData(boolean check) {
        CanJni.JacGetCarSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Ykzdjc, this.mSetData.Ykhsjzd, this.mSetData.Ckmblmd, this.mSetData.Cwzmycgbsj, this.mSetData.Cnzmycgbsj, this.mSetData.Zdcss, this.mSetData.Sftsy, this.mSetData.Dwzm, this.mSetData.Zxfzd, this.mSetData.Zcbgldtj});
        }
    }

    public void QueryData() {
    }
}
