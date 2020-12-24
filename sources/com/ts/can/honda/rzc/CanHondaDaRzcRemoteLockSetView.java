package com.ts.can.honda.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaDaRzcRemoteLockSetView extends CanScrollCarInfoView {
    public static final int ITEM_DOORUNLOCKMODE = 2;
    public static final int ITEM_YKMSCBDTS = 3;
    public static final int ITEM_YKMSFMQTS = 0;
    public static final int ITEM_YKQDXT = 1;
    public static final int ITEM_ZDCNZMLMD = 4;
    protected CanDataInfo.HondaSetData mSetData;

    public CanHondaDaRzcRemoteLockSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.HondaDACarSet(25, item);
                return;
            case 4:
                CanJni.HondaDACarSet(27, item);
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
                CanJni.HondaDACarSet(13, Neg(this.mSetData.ykmsfmqts));
                return;
            case 1:
                CanJni.HondaDACarSet(24, Neg(this.mSetData.ykqdxt));
                return;
            case 3:
                CanJni.HondaDACarSet(26, Neg(this.mSetData.ykmscbdts));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_keylessaccessbeep, R.string.can_remotestartsystem, R.string.can_doorunlockmode, R.string.can_keylessaccesslightflash, R.string.can_autointeriorsensitivity};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[2] = new int[]{R.string.can_keyandremoteunlockmode_2, R.string.can_keyandremoteunlockmode_1};
        this.mPopValueIds[4] = new int[]{R.string.can_sensitivity_min, R.string.can_sensitivity_low, R.string.can_sensitivity_mid, R.string.can_sensitivity_high, R.string.can_sensitivity_max};
        this.mSetData = new CanDataInfo.HondaSetData();
    }

    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.RemCsUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.RemCsUpdate)) {
            this.mSetData.RemCsUpdate = 0;
            updateItem(0, this.mSetData.ykmsfmqts);
            updateItem(1, this.mSetData.ykqdxt);
            updateItem(2, this.mSetData.doorunlockmode);
            updateItem(3, this.mSetData.ykmscbdts);
            updateItem(4, this.mSetData.zdcnzmlmd);
        }
    }

    public void QueryData() {
    }
}
