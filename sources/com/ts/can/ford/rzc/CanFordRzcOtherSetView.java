package com.ts.can.ford.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordRzcOtherSetView extends CanScrollCarInfoView {
    protected static final int ITEM_CFYGYC = 6;
    protected static final int ITEM_DDHBX = 1;
    protected static final int ITEM_DDHBX_TITLE = 0;
    protected static final int ITEM_DLDW = 10;
    protected static final int ITEM_HSJ_TITLE = 2;
    protected static final int ITEM_HYGQ = 7;
    protected static final int ITEM_TYDW = 9;
    protected static final int ITEM_UNIT_TITLE = 8;
    protected static final int ITEM_WDDW = 11;
    protected static final int ITEM_YGQ_TITLE = 4;
    protected static final int ITEM_YLGYSYG = 5;
    protected static final int ITEM_ZDZD = 3;
    private CanDataInfo.FordRzcSetInfo mSetData;

    public CanFordRzcOtherSetView(Activity activity) {
        super(activity, 12);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 9:
                CanJni.FordRzcCarSet2(112, item);
                return;
            case 10:
                if (item == 1) {
                    CanJni.FordRzcCarSet2(113, 2);
                    return;
                } else if (item == 2) {
                    CanJni.FordRzcCarSet2(113, 3);
                    return;
                } else {
                    CanJni.FordRzcCarSet2(113, item);
                    return;
                }
            case 11:
                CanJni.FordRzcCarSet2(114, item);
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
                CanJni.FordRzcCarSet2(48, Neg(this.mSetData.Ddhbx));
                return;
            case 3:
                CanJni.FordRzcCarSet2(64, Neg(this.mSetData.Zdzd));
                return;
            case 5:
                CanJni.FordRzcCarSet2(96, Neg(this.mSetData.Ylgysyg));
                return;
            case 6:
                CanJni.FordRzcCarSet2(97, Neg(this.mSetData.Cfygyc));
                return;
            case 7:
                CanJni.FordRzcCarSet2(98, Neg(this.mSetData.Hygq));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_trunk, R.string.can_ddhbxg, R.string.can_rearview_mirror_settings, R.string.can_zdzdwhsj, R.string.can_ygq_set, R.string.can_jp_ylgysyg, R.string.can_cfygyc, R.string.can_rear_wiper, R.string.can_base_setup, R.string.can_TPMS_DW, R.string.can_dlunits, R.string.can_temp_dw};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mSetData = new CanDataInfo.FordRzcSetInfo();
        this.mPopValueIds[9] = new int[]{R.string.can_pressure_psi, R.string.can_pressure_kpa, R.string.can_pressure_bar};
        this.mPopValueIds[10] = new int[]{R.string.can_dldw_mi_mpg, R.string.can_dldw_km_lkm, R.string.can_dldw_km_kml};
        this.mPopValueIds[11] = new int[]{R.string.can_temperature_c, R.string.can_temperature_f};
    }

    public void ResetData(boolean check) {
        CanJni.FordRzcGetSetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(1, this.mSetData.Ddhbx);
            updateItem(3, this.mSetData.Zdzd);
            updateItem(5, this.mSetData.Ylgysyg);
            updateItem(6, this.mSetData.Cfygyc);
            updateItem(7, this.mSetData.Hygq);
            updateItem(9, this.mSetData.Tydw);
            int temp = this.mSetData.Dldw;
            if (this.mSetData.Dldw >= 2) {
                temp = this.mSetData.Dldw - 1;
            }
            updateItem(10, temp);
            updateItem(11, this.mSetData.Wddw);
        }
    }

    public void QueryData() {
        CanJni.FordQuery(40, 0);
    }
}
