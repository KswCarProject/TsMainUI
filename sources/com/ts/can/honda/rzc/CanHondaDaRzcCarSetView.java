package com.ts.can.honda.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaDaRzcCarSetView extends CanScrollCarInfoView {
    public static final int ITEM_ADJUSTALARMVOL = 0;
    public static final int ITEM_AUTOHEADLIGHT = 6;
    public static final int ITEM_FUELEFFICIENCYL = 1;
    public static final int ITEM_NEWMESSAGE = 2;
    public static final int ITEM_SPEEDDISTANCE = 3;
    public static final int ITEM_TACHOMETER = 4;
    public static final int ITEM_WALKAWAY = 5;
    protected CanDataInfo.HondaSetData mSetData;

    public CanHondaDaRzcCarSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.HondaDACarSet(18, item);
                return;
            case 3:
                CanJni.HondaDACarSet(21, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.HondaDACarSet(19, Neg(this.mSetData.jnmsdbjzm));
                return;
            case 2:
                CanJni.HondaDACarSet(20, Neg(this.mSetData.xxxtx));
                return;
            case 4:
                CanJni.HondaDACarSet(22, Neg(this.mSetData.tachometer));
                return;
            case 5:
                CanJni.HondaDACarSet(23, Neg(this.mSetData.lkszgxhsz));
                return;
            case 6:
                CanJni.HondaDACarSet(28, Neg(this.mSetData.yshzdddldgxhsd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_adjustalarmvolume, R.string.can_fuelefficiencybacklight, R.string.can_newmessage, R.string.can_speeddistanceunits, R.string.can_tachometer, R.string.can_walkawayautolock, R.string.can_autoheadlinghtonwitchwiper};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_sensitivity_high, R.string.can_sensitivity_mid, R.string.can_sensitivity_low};
        this.mPopValueIds[3] = new int[]{R.string.can_speeddistanceunits_1, R.string.can_speeddistanceunits_2};
        this.mSetData = new CanDataInfo.HondaSetData();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }

    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.CarSetUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.CarSetUpdate)) {
            this.mSetData.CarSetUpdate = 0;
            updateItem(0, this.mSetData.tzbjyl);
            updateItem(1, this.mSetData.jnmsdbjzm);
            updateItem(2, this.mSetData.xxxtx);
            updateItem(3, this.mSetData.speeddisunit);
            updateItem(4, this.mSetData.tachometer);
            updateItem(5, this.mSetData.lkszgxhsz);
            updateItem(6, this.mSetData.yshzdddldgxhsd);
        }
    }

    public void QueryData() {
    }
}
