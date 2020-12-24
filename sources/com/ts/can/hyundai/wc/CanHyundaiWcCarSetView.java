package com.ts.can.hyundai.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHyundaiWcCarSetView extends CanScrollCarInfoView {
    private static final int DSPZYKBZD_L = 1;
    private static final int DSPZYKBZD_R = 2;
    private static final int FXPJR = 3;
    private static final int KQNXH = 0;
    private static final int ZYJRTF = 4;
    private CanDataInfo.HyundaiWcSetData mSetData;

    public CanHyundaiWcCarSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.HyundaiWcCarSet(2, item, 255, 255);
                return;
            case 2:
                CanJni.HyundaiWcCarSet(3, item, 255, 255);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.HyundaiWcCarSet(1, Neg(this.mSetData.Kqnxh), 255, 255);
                return;
            case 3:
                CanJni.HyundaiWcCarSet(4, Neg(this.mSetData.Swhot), 255, 255);
                return;
            case 4:
                CanJni.HyundaiWcCarSet(5, Neg(this.mSetData.Zyjrtf), 255, 255);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_kqnxh, R.string.can_dspzykbzd_l, R.string.can_dspzykbzd_r, R.string.can_fxpjr, R.string.can_seat_hotwind};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[1] = new int[]{R.string.can_expand, R.string.can_fold};
        this.mPopValueIds[2] = new int[]{R.string.can_expand, R.string.can_fold};
        this.mSetData = new CanDataInfo.HyundaiWcSetData();
    }

    public void ResetData(boolean check) {
        CanJni.HyundaiWcGetSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Kqnxh, this.mSetData.Dspzykbzd_l, this.mSetData.Dspzykbzd_r, this.mSetData.Swhot, this.mSetData.Zyjrtf});
        }
    }

    public void QueryData() {
    }
}
