package com.ts.can.saic.wc.mg_zs;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMGZSWCCarSetView extends CanScrollCarInfoView {
    public static final int ITEM_BWHJ = 0;
    public static final int ITEM_BWHJ_BCLDSJ = 5;
    public static final int ITEM_CLSSFK = 8;
    public static final int ITEM_HSCLQB = 10;
    public static final int ITEM_HSDSDZXD = 9;
    public static final int ITEM_WYSJS = 7;
    public static final int ITEM_XCZDLS = 3;
    public static final int ITEM_XCZS = 1;
    public static final int ITEM_XHZDJS = 4;
    public static final int ITEM_YSJS = 6;
    public static final int ITEM_ZXSG = 2;
    private CanDataInfo.MgZsWc_Set mAdtData;
    private CanDataInfo.MgZsWc_Set mSetData;

    public CanMGZSWCCarSetView(Activity activity) {
        super(activity, 11);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.MgZsWcCarSet(2, item);
                return;
            case 2:
                CanJni.MgZsWcCarSet(3, item);
                return;
            case 5:
                CanJni.MgZsWcCarSet(7, item);
                return;
            case 6:
                CanJni.MgZsWcCarSet(9, item);
                return;
            case 7:
                CanJni.MgZsWcCarSet(10, item);
                return;
            case 8:
                CanJni.MgZsWcCarSet(8, item);
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
                CanJni.MgZsWcCarSet(1, Neg(this.mSetData.Bwhj));
                return;
            case 3:
                CanJni.MgZsWcCarSet(5, Neg(this.mSetData.Xczdls));
                return;
            case 4:
                CanJni.MgZsWcCarSet(6, Neg(this.mSetData.Xhzdjs));
                return;
            case 9:
                CanJni.MgZsWcCarSet(12, Neg(this.mSetData.Hsdsdzxd));
                return;
            case 10:
                CanJni.MgZsWcCarSet(11, Neg(this.mSetData.Hsclqb));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_bwhj, R.string.can_xunche, R.string.can_mg_turnfell, R.string.can_xczdls, R.string.can_xhzdjs, R.string.can_dgsjkz_bwhj, R.string.can_key_unlock, R.string.can_keyless_unlock, R.string.can_clssfk, R.string.can_hsdsdzxd, R.string.can_hsclqb};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[1] = new int[]{R.string.can_only_light, R.string.can_dghlb};
        this.mPopValueIds[2] = new int[]{R.string.can_mode_normal, R.string.can_comfort, R.string.can_sport};
        this.mPopValueIds[5] = new int[]{R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s, R.string.can_headlightautoofftime_90s};
        this.mPopValueIds[6] = new int[]{R.string.can_door_unlock_key2, R.string.can_door_unlock_key1};
        this.mPopValueIds[7] = new int[]{R.string.can_door_unlock_key2, R.string.can_door_unlock_key1};
        this.mPopValueIds[8] = new int[]{R.string.can_mingd, R.string.can_shand, R.string.can_sdmd};
        this.mAdtData = new CanDataInfo.MgZsWc_Set();
        this.mSetData = new CanDataInfo.MgZsWc_Set();
    }

    public void ResetData(boolean check) {
        CanJni.MgZsWcGetSetData(this.mAdtData, 0);
        if (i2b(this.mAdtData.UpdateOnce) && (!check || i2b(this.mAdtData.Update))) {
            this.mAdtData.Update = 0;
            showItem(new int[]{this.mAdtData.Bwhj, this.mAdtData.Xczs, this.mAdtData.Zxsg, this.mAdtData.Xczdls, this.mAdtData.Xhzdjs, this.mAdtData.Bwhjbcldsj, this.mAdtData.Ysjs, this.mAdtData.Wsjjs, this.mAdtData.Clssfk, this.mAdtData.Hsdsdzxd, this.mAdtData.Hsclqb});
        }
        CanJni.MgZsWcGetSetData(this.mSetData, 1);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Bwhj, this.mSetData.Xczs, this.mSetData.Zxsg, this.mSetData.Xczdls, this.mSetData.Xhzdjs, this.mSetData.Bwhjbcldsj, this.mSetData.Ysjs, this.mSetData.Wsjjs, this.mSetData.Clssfk, this.mSetData.Hsdsdzxd, this.mSetData.Hsclqb});
        }
    }

    public void QueryData() {
        CanJni.MgZsWcQueryData(5, 1, 120);
    }
}
