package com.ts.can.chrysler.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanChryslerWcLightLockSetView extends CanScrollCarInfoView {
    private CanDataInfo.ChrWcLightMoto mLightADT;
    private CanDataInfo.ChrWcLightMoto mLightData;

    public CanChryslerWcLightLockSetView(Activity activity) {
        super(activity, 11);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CanJni.ChryslerWcLightMotoSet(9, item);
                return;
            case 4:
                CanJni.ChryslerWcLightMotoSet(7, item);
                return;
            case 5:
                CanJni.ChryslerWcLightMotoSet(2, item);
                return;
            case 6:
                CanJni.ChryslerWcLightMotoSet(1, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 0) {
            CanJni.ChryslerWcLightMotoSet(11, checkPos(pos, this.mLightData.Cnfwd));
        } else if (id == 1) {
            CanJni.ChryslerWcLightMotoSet(8, checkHeadLightPos(pos, this.mLightData.Qzdlmd));
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.ChryslerWcLightMotoSet(10, Neg(this.mLightData.Zjfzd));
                return;
            case 7:
                CanJni.ChryslerWcLightMotoSet(6, Neg(this.mLightData.Scszxdss));
                return;
            case 8:
                CanJni.ChryslerWcLightMotoSet(5, Neg(this.mLightData.Rjxsd));
                return;
            case 9:
                CanJni.ChryslerWcLightMotoSet(4, Neg(this.mLightData.Zdfxgd));
                return;
            case 10:
                CanJni.ChryslerWcLightMotoSet(3, Neg(this.mLightData.Qdysszdqddd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_wc_car_inner_light, R.string.can_headlightsens, R.string.can_corneringLights, R.string.can_greetingLights, R.string.can_fdjgbdyyc, R.string.can_kjddlq, R.string.can_ddgbyc, R.string.can_scszxdss, R.string.can_rjxcd, R.string.can_jp_zdfxygd, R.string.can_jp_qdygszdqddd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 6;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[0] = 1;
        iArr4[1] = 3;
        iArr4[2] = 1;
        iArr3[1] = iArr4;
        this.mPopValueIds[3] = new int[]{R.string.can_amp_off, R.string.can_amp_on};
        this.mPopValueIds[4] = new int[]{R.string.can_0s, R.string.can_time_45s, R.string.can_time_5min, R.string.can_time_10min};
        this.mPopValueIds[5] = new int[]{R.string.can_0s, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s};
        this.mPopValueIds[6] = new int[]{R.string.can_0s, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s};
        this.mLightData = new CanDataInfo.ChrWcLightMoto();
        this.mLightADT = new CanDataInfo.ChrWcLightMoto();
    }

    public void ResetData(boolean check) {
        CanJni.ChryslerWcGetLightMoto(this.mLightADT, 0);
        CanJni.ChryslerWcGetLightMoto(this.mLightData, 1);
        if (i2b(this.mLightADT.UpdateOnce) && (!check || i2b(this.mLightADT.Update))) {
            this.mLightADT.Update = 0;
            showItem(new int[]{this.mLightADT.Cnfwd, this.mLightADT.Qzdlmd, this.mLightADT.Zjfzd, this.mLightADT.Jskqcd, this.mLightADT.Fdjgbdyyc, this.mLightADT.Kjsddlq, this.mLightADT.Ddgbyc, this.mLightADT.Scszxdss, this.mLightADT.Rjxsd, this.mLightADT.Zdfxgd, this.mLightADT.Qdysszdqddd});
        }
        if (!i2b(this.mLightData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLightData.Update)) {
            this.mLightData.Update = 0;
            updateItem(new int[]{this.mLightData.Cnfwd, this.mLightData.Qzdlmd, this.mLightData.Zjfzd, this.mLightData.Jskqcd, this.mLightData.Fdjgbdyyc, this.mLightData.Kjsddlq, this.mLightData.Ddgbyc, this.mLightData.Scszxdss, this.mLightData.Rjxsd, this.mLightData.Zdfxgd, this.mLightData.Qdysszdqddd});
        }
    }

    public void QueryData() {
        CanJni.ChryslerWcQuery(1, 98);
    }

    private int checkPos(int pos, int value) {
        int result = Math.abs(pos - value);
        if (result > 1) {
            result = 1;
        }
        if (pos - value > 0) {
            return value + result;
        }
        return value - result;
    }

    private int checkHeadLightPos(int pos, int value) {
        if (pos - value > 0) {
            return 1;
        }
        return 255;
    }
}
