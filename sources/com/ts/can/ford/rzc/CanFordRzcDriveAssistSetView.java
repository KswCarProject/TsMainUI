package com.ts.can.ford.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordRzcDriveAssistSetView extends CanScrollCarInfoView {
    protected static final int ITEM_CDBCMS = 0;
    protected static final int ITEM_DDLCYJ = 2;
    protected static final int ITEM_JGQD = 1;
    protected static final int ITEM_LMD = 6;
    protected static final int ITEM_MQJC = 7;
    protected static final int ITEM_PLJSYJ = 8;
    protected static final int ITEM_TCS_QYLKZ = 3;
    protected static final int ITEM_ZDYQGB = 4;
    protected static final int ITEM_ZDZD = 5;
    private CanDataInfo.FordRzcSetInfo mSetData;

    public CanFordRzcDriveAssistSetView(Activity activity) {
        super(activity, 9);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.FordRzcCarSet2(0, item);
                return;
            case 1:
                CanJni.FordRzcCarSet2(1, item);
                return;
            case 6:
                CanJni.FordRzcCarSet2(18, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.FordRzcCarSet2(2, Neg(this.mSetData.Ddlcyj));
                return;
            case 3:
                CanJni.FordRzcCarSet2(3, Neg(this.mSetData.TcsQylkz));
                return;
            case 4:
                CanJni.FordRzcCarSet2(5, Neg(this.mSetData.Zdyqgb));
                return;
            case 5:
                CanJni.FordRzcCarSet2(17, Neg(this.mSetData.Zhudzd));
                return;
            case 7:
                CanJni.FordRzcCarSet2(19, Neg(this.mSetData.Mqjc));
                return;
            case 8:
                CanJni.FordRzcCarSet2(20, Neg(this.mSetData.Pljsyj));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_psa_wc_cdbcfz, R.string.can_jinggaoqingdu, R.string.can_daodanlaicheyujing, R.string.can_tcs_qylkz, R.string.can_zdyq_off, R.string.can_zdzdfzxt, R.string.can_cdfz_lmd, R.string.can_blind_spot_monitoring, R.string.can_fatigue_driving_tips};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mSetData = new CanDataInfo.FordRzcSetInfo();
        this.mPopValueIds[0] = new int[]{R.string.can_jg, R.string.can_fuzhu, R.string.can_jinggao_fuzhu};
        this.mPopValueIds[1] = new int[]{R.string.can_ac_low, R.string.can_cdpyyzxt_1, R.string.can_ac_high};
        this.mPopValueIds[6] = new int[]{R.string.can_ac_low, R.string.can_cdpyyzxt_1, R.string.can_ac_high};
    }

    public void ResetData(boolean check) {
        CanJni.FordRzcGetSetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Cdbcms);
            updateItem(1, this.mSetData.Jgqd);
            updateItem(2, this.mSetData.Ddlcyj);
            updateItem(3, this.mSetData.TcsQylkz);
            updateItem(4, this.mSetData.Zdyqgb);
            updateItem(5, this.mSetData.Zhudzd);
            updateItem(6, this.mSetData.Lmd);
            updateItem(7, this.mSetData.Mqjc);
            updateItem(8, this.mSetData.Pljsyj);
        }
    }

    public void QueryData() {
        CanJni.FordQuery(40, 0);
    }
}
