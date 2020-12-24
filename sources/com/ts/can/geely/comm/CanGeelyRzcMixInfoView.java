package com.ts.can.geely.comm;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGeelyRzcMixInfoView extends CanScrollCarInfoView {
    protected static final int ITEM_BCCDMS = 4;
    protected static final int ITEM_CCFJBJ = 9;
    protected static final int ITEM_DSJGY = 3;
    protected static final int ITEM_HBXWYSJS = 13;
    protected static final int ITEM_HBXZDJSJL = 12;
    protected static final int ITEM_HBXZDKQ = 11;
    protected static final int ITEM_KJJSPZ = 6;
    protected static final int ITEM_LKSSPZ = 7;
    protected static final int ITEM_MAX = 14;
    protected static final int ITEM_MIN = 0;
    protected static final int ITEM_RYMKQSCBJ = 5;
    protected static final int ITEM_SFTSLX = 2;
    protected static final int ITEM_SOC = 0;
    protected static final int ITEM_ZCHSJZD = 8;
    protected static final int ITEM_ZJXCD = 10;
    protected static final int ITEM_ZTYS = 1;
    private CanDataInfo.Geely_Set m_SetData;

    public CanGeelyRzcMixInfoView(Activity activity) {
        super(activity, 14);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.GeelyCarSet(28, item);
                return;
            case 2:
                CanJni.GeelyCarSet(17, item);
                return;
            case 3:
                CanJni.GeelyCarSet(27, item);
                return;
            case 12:
                CanJni.GeelyCarSet(35, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.GeelyCarSet(37, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 4:
                CanJni.GeelyCarSet(29, Neg(this.m_SetData.Bccdms));
                return;
            case 5:
                CanJni.GeelyCarSet(30, Neg(this.m_SetData.Rymkqscbjpz));
                return;
            case 6:
                CanJni.GeelyCarSet(31, Neg(this.m_SetData.Kjjspz));
                return;
            case 7:
                CanJni.GeelyCarSet(32, Neg(this.m_SetData.Lksspz));
                return;
            case 8:
                CanJni.GeelyCarSet(20, Neg(this.m_SetData.Zchsjzd));
                return;
            case 9:
                CanJni.GeelyCarSet(33, Neg(this.m_SetData.Ccfjbj));
                return;
            case 10:
                CanJni.GeelyCarSet(10, Neg(this.m_SetData.Zjxcd));
                return;
            case 11:
                CanJni.GeelyCarSet(34, Neg(this.m_SetData.Hbxzdkq));
                return;
            case 13:
                CanJni.GeelyCarSet(36, Neg(this.m_SetData.Hbxwysjs));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_socphd, R.string.can_psa_wc_ztyssz, R.string.can_set_tip, R.string.can_dsjgy, R.string.can_bccdms, R.string.can_rymkqscbjpz, R.string.can_kjjs, R.string.can_lczdls, R.string.can_zchsjzd, R.string.can_ccfjbj, R.string.can_rjxcd, R.string.can_hbxzdkq, R.string.can_hbxzdjsjl, R.string.can_hbxwysjs};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.m_SetData = new CanDataInfo.Geely_Set();
        this.mPopValueIds[1] = new int[]{R.string.can_color_blue, R.string.can_color_red, R.string.can_cmzdls_1, R.string.can_color_yellow};
        this.mPopValueIds[2] = new int[]{R.string.can_dghlb, R.string.can_only_light};
        this.mPopValueIds[3] = new int[]{R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
        this.mPopValueIds[12] = new int[]{R.string.can_sdqfwxjgjl_3, R.string.can_sdqfwxjgjl_1};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = 20;
        iArr2[1] = 80;
        iArr2[2] = 1;
        iArr[0] = iArr2;
    }

    public void ResetData(boolean check) {
        CanJni.GeelyRzcGetSet(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(1, this.m_SetData.Ztys);
            updateItem(2, this.m_SetData.Sftslx);
            updateItem(3, this.m_SetData.Dsjgy);
            updateItem(4, this.m_SetData.Bccdms);
            updateItem(5, this.m_SetData.Rymkqscbjpz);
            updateItem(6, this.m_SetData.Kjjspz);
            updateItem(7, this.m_SetData.Lksspz);
            updateItem(8, this.m_SetData.Zchsjzd);
            updateItem(9, this.m_SetData.Ccfjbj);
            updateItem(10, this.m_SetData.Zjxcd);
            updateItem(11, this.m_SetData.Hbxzdkq);
            updateItem(12, this.m_SetData.Hbxzdjsjl);
            updateItem(13, this.m_SetData.Hbxwysjs);
            updateItem(0, this.m_SetData.Soc, String.format("%d %%", new Object[]{Integer.valueOf(this.m_SetData.Soc)}));
        }
    }

    public void QueryData() {
        CanJni.GeelyCarQuery(79, 0);
    }
}
