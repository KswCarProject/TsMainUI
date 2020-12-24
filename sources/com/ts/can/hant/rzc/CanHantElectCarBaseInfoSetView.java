package com.ts.can.hant.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHantElectCarBaseInfoSetView extends CanScrollCarInfoView {
    private static final int HANT_CDKZT = 0;
    private static final int HANT_CNWD = 6;
    private static final int HANT_FWD = 4;
    private static final int HANT_JGD = 3;
    private static final int HANT_TCZT = 1;
    private static final int HANT_YGD = 2;
    private static final int HANT_YGZT = 5;
    private CanDataInfo.HanTang_BaseInfo mBaseData;

    public CanHantElectCarBaseInfoSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        if (id == 5) {
            CanJni.HanTangElectCarLightYgSet(8, item + 1);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.HanTangElectCarLightYgSet(4, Neg(this.mBaseData.Cdkzt) + 1);
                return;
            case 1:
                CanJni.HanTangElectCarLightYgSet(5, Neg(this.mBaseData.Tczt) + 1);
                return;
            case 2:
                CanJni.HanTangElectCarLightYgSet(2, Neg(this.mBaseData.Ygd) + 1);
                return;
            case 3:
                CanJni.HanTangElectCarLightYgSet(1, Neg(this.mBaseData.Jgd) + 1);
                return;
            case 4:
                CanJni.HanTangElectCarLightYgSet(6, Neg(this.mBaseData.Fwd) + 1);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mBaseData = new CanDataInfo.HanTang_BaseInfo();
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.VALUE};
        this.mItemTitleIds = new int[]{R.string.can_hant_cdkzt, R.string.can_hant_tczt, R.string.can_ill_ygd, R.string.can_ill_jgd, R.string.can_hant_fwd, R.string.can_hant_ygzt, R.string.can_hant_cnwd};
        this.mPopValueIds[5] = new int[]{R.array.can_hant_ygzts};
    }

    public void ResetData(boolean check) {
        CanJni.HanTangElectCarGetBaseInfo(this.mBaseData);
        if (!i2b(this.mBaseData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mBaseData.Update)) {
            this.mBaseData.Update = 0;
            updateItem(0, this.mBaseData.Cdkzt);
            updateItem(1, this.mBaseData.Tczt);
            updateItem(2, this.mBaseData.Ygd);
            updateItem(3, this.mBaseData.Jgd);
            updateItem(4, this.mBaseData.Fwd);
            updateItem(5, this.mBaseData.Ygzt - 1);
            updateItem(6, this.mBaseData.Cnwd, String.format("%d  ℃", new Object[]{Integer.valueOf(this.mBaseData.Cnwd - 40)}));
        }
    }

    public void QueryData() {
        CanJni.HanTangElectCarQuery(38);
    }
}
