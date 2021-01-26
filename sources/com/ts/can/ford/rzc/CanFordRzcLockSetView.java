package com.ts.can.ford.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordRzcLockSetView extends CanScrollCarInfoView {
    protected static final int ITEM_AIR_TITLE = 6;
    protected static final int ITEM_JHYKKQ = 10;
    protected static final int ITEM_KGJZ = 1;
    protected static final int ITEM_KTKZ = 11;
    protected static final int ITEM_LOCK_TITLE = 0;
    protected static final int ITEM_REMOTE_START_TITLE = 9;
    protected static final int ITEM_SYFK = 2;
    protected static final int ITEM_WSJG = 3;
    protected static final int ITEM_YKGB = 8;
    protected static final int ITEM_YKJS = 4;
    protected static final int ITEM_YKKQ = 7;
    protected static final int ITEM_ZDJS = 5;
    protected static final int ITEM_ZQ = 12;
    private CanDataInfo.FordRzcSetInfo mSetData;

    public CanFordRzcLockSetView(Activity activity) {
        super(activity, 13);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 4:
                CanJni.FordRzcCarSet2(83, item);
                return;
            case 11:
                CanJni.FordRzcCarSet2(88, item);
                return;
            case 12:
                CanJni.FordRzcCarSet2(90, item);
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
                CanJni.FordRzcCarSet2(80, Neg(this.mSetData.Kgjz));
                return;
            case 2:
                CanJni.FordRzcCarSet2(81, Neg(this.mSetData.Syfk));
                return;
            case 3:
                CanJni.FordRzcCarSet2(82, Neg(this.mSetData.Wsjg));
                return;
            case 5:
                CanJni.FordRzcCarSet2(84, Neg(this.mSetData.Zdjs));
                return;
            case 7:
                CanJni.FordRzcCarSet2(85, Neg(this.mSetData.Ykkq));
                return;
            case 8:
                CanJni.FordRzcCarSet2(86, Neg(this.mSetData.Ykgb));
                return;
            case 10:
                CanJni.FordRzcCarSet2(87, Neg(this.mSetData.Jhykqd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_cssz, R.string.can_lock_kgjz, R.string.can_syfk, R.string.can_lock_wsjg, R.string.can_ykjs, R.string.can_zdjs, R.string.can_ykcckz, R.string.can_remote_open, R.string.can_remote_close, R.string.can_remotesystem, R.string.can_remote_jhykqd, R.string.can_ktkz, R.string.can_zhouqi};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mSetData = new CanDataInfo.FordRzcSetInfo();
        this.mPopValueIds[4] = new int[]{R.string.can_door_unlock_key2, R.string.can_door_unlock_key1};
        this.mPopValueIds[11] = new int[]{R.string.can_mzd_cx4_drive_auto, R.string.can_gl8_2017_ktycqd_last};
        this.mPopValueIds[12] = new int[]{R.string.can_time_5min, R.string.can_time_10min, R.string.can_time_15min};
    }

    public void ResetData(boolean check) {
        CanJni.FordRzcGetSetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(1, this.mSetData.Kgjz);
            updateItem(2, this.mSetData.Syfk);
            updateItem(3, this.mSetData.Wsjg);
            updateItem(4, this.mSetData.Ykjs);
            updateItem(5, this.mSetData.Zdjs);
            updateItem(7, this.mSetData.Ykkq);
            updateItem(8, this.mSetData.Ykgb);
            updateItem(10, this.mSetData.Jhykqd);
            updateItem(11, this.mSetData.Ktkz);
            updateItem(12, this.mSetData.Zq);
        }
    }

    public void QueryData() {
        CanJni.FordQuery(40, 0);
    }
}
