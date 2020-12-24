package com.ts.can.honda.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaDaRzcCarCsSetView extends CanScrollCarInfoView {
    public static final int ITEM_AUTODOORLOCKWITH = 4;
    public static final int ITEM_AUTODOORUNLOCKWITH = 3;
    public static final int ITEM_KEYREMOTEUNLOCKMODE = 1;
    public static final int ITEM_YKLSTS = 0;
    public static final int ITEM_ZDCSSJ = 2;
    protected CanDataInfo.HondaSetData mSetData;

    public CanHondaDaRzcCarCsSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.HondaDACarSet(9, item);
                return;
            case 2:
                CanJni.HondaDACarSet(11, item);
                return;
            case 3:
                CanJni.HondaDACarSet(8, item);
                return;
            case 4:
                CanJni.HondaDACarSet(7, item);
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
                CanJni.HondaDACarSet(10, Neg(this.mSetData.yklsts));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_keylesslockanswerback, R.string.can_keyandremoteunlockmode, R.string.can_securityrelocktimer, R.string.can_autodoorunlockwith, R.string.can_autodoorlockwith};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_keyandremoteunlockmode_1, R.string.can_keyandremoteunlockmode_2};
        this.mPopValueIds[2] = new int[]{R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s, R.string.can_headlightautoofftime_90s};
        this.mPopValueIds[3] = new int[]{R.string.can_alldoorswhendriverdooropens, R.string.can_alldoorswhenshiftedtopark, R.string.can_alldoorswhenignitionswitchedoff, R.string.can_autodooroff};
        this.mPopValueIds[4] = new int[]{R.string.can_autodoorlockwith1, R.string.can_autodoorlockwith2, R.string.can_autodooroff};
        this.mSetData = new CanDataInfo.HondaSetData();
    }

    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.CsUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.CsUpdate)) {
            this.mSetData.CsUpdate = 0;
            updateItem(0, this.mSetData.yklsts);
            updateItem(1, this.mSetData.keylockmode);
            updateItem(2, this.mSetData.zdcssj);
            updateItem(3, this.mSetData.autodoorunlock);
            updateItem(4, this.mSetData.autodoorlock);
        }
    }

    public void QueryData() {
    }
}
