package com.ts.can.honda.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaWcCarDistanceSetView extends CanScrollCarInfoView {
    private CanDataInfo.HondaWcPostGxh mPostGxhData;
    private CanDataInfo.HondaWcTftSta mTftStaData;

    public CanHondaWcCarDistanceSetView(Activity activity) {
        super(activity, 13);
    }

    public void onItem(int id, int item) {
        if (id == 6) {
            CanJni.HondaWcCarTftStaSet(4, item);
        } else if (id == 8) {
            CanJni.HondaWcCarTftStaSet(3, item);
        } else if (id == 9) {
            CanJni.HondaWcCarTftStaSet(2, item);
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 10) {
            CanJni.HondaWcCarTftStaSet(1, pos);
        }
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

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.HondaWcCarTftStaSet(10, Neg(this.mTftStaData.SwithLock));
                return;
            case 1:
                CanJni.HondaWcCarTftStaSet(9, Neg(this.mTftStaData.Ddtsy));
                return;
            case 2:
                CanJni.HondaWcCarTftStaSet(6, Neg(this.mTftStaData.Zsjts));
                return;
            case 3:
                CanJni.HondaWcCarTftStaSet(7, Neg(this.mTftStaData.Xxxtx));
                return;
            case 4:
                CanJni.HondaWcCarTftStaSet(8, Neg(this.mTftStaData.Fdjjnzdqtts));
                return;
            case 5:
                CanJni.HondaWcCarTftStaSet(5, Neg(this.mTftStaData.Jnmsbjzm));
                return;
            case 7:
                CanJni.HondaWcCarTftStaSet(13, Neg(this.mTftStaData.Jtbzsbxt));
                return;
            case 11:
                CanJni.HondaWcCarTftStaSet(11, Neg(this.mPostGxhData.Jywzzyld));
                return;
            case 12:
                CanJni.HondaWcCarTftStaSet(12, Neg(this.mPostGxhData.Dzyjsaqdydms));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_switch_lock, R.string.can_ddtsy, R.string.can_zsjts, R.string.can_newmessage, R.string.can_fdjjnzdqtxs, R.string.can_fuelefficiencybacklight, R.string.can_adjustalarmvolume, R.string.can_traffice_sign_rec, R.string.can_tripbresettiming, R.string.can_triparesettiming, R.string.can_adjustoutsidetemp, R.string.can_jywzzyld, R.string.can_dzyjsaqdydms};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[6] = new int[]{R.string.can_tripbresettiming_wc_1, R.string.can_tzbjyl_high, R.string.can_tzbjyl_mid, R.string.can_tzbjyl_low};
        this.mPopValueIds[8] = new int[]{R.string.can_tripbresettiming_wc_1, R.string.can_tripbresettiming_wc_2, R.string.can_tripbresettiming_wc_3, R.string.can_tripbresettiming_wc_4};
        this.mPopValueIds[9] = new int[]{R.string.can_tripbresettiming_wc_1, R.string.can_tripbresettiming_wc_2, R.string.can_tripbresettiming_wc_3, R.string.can_tripbresettiming_wc_4};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = 1;
        iArr2[1] = 7;
        iArr2[2] = 1;
        iArr[10] = iArr2;
        this.mTftStaData = new CanDataInfo.HondaWcTftSta();
        this.mPostGxhData = new CanDataInfo.HondaWcPostGxh();
        if (4 != CanJni.GetSubType() && 5 != CanJni.GetSubType()) {
            this.mItemVisibles[0] = 0;
            this.mItemVisibles[11] = 0;
            this.mItemVisibles[12] = 0;
        }
    }

    public void ResetData(boolean check) {
        CanJni.HondaWcGetTftSta(this.mTftStaData);
        CanJni.HondaWcGetPostGxh(this.mPostGxhData);
        if (i2b(this.mTftStaData.UpdateOnce) && (!check || i2b(this.mTftStaData.Update))) {
            this.mTftStaData.Update = 0;
            updateItem(0, this.mTftStaData.SwithLock);
            updateItem(1, this.mTftStaData.Ddtsy);
            updateItem(2, this.mTftStaData.Zsjts);
            updateItem(3, this.mTftStaData.Xxxtx);
            updateItem(4, this.mTftStaData.Fdjjnzdqtts);
            updateItem(5, this.mTftStaData.Jnmsbjzm);
            updateItem(6, this.mTftStaData.Tzbjyl);
            updateItem(7, this.mTftStaData.Jtbzsbxt);
            updateItem(8, this.mTftStaData.LcBcstjqh);
            updateItem(9, this.mTftStaData.LcAcstjqh);
            updateItem(10, this.mTftStaData.Tjwbqwxs, new StringBuilder(String.valueOf(this.mTftStaData.Tjwbqwxs - 4)).toString());
        }
        if (!i2b(this.mPostGxhData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPostGxhData.Update)) {
            this.mPostGxhData.Update = 0;
            updateItem(11, this.mPostGxhData.Jywzzyld);
            updateItem(12, this.mPostGxhData.Dzyjsaqdydms);
        }
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, 105);
        CanJni.HondaWcQuery(5, 1, 100);
    }
}
