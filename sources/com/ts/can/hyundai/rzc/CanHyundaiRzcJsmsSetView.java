package com.ts.can.hyundai.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHyundaiRzcJsmsSetView extends CanScrollCarInfoView {
    private static final int ITEM_COMFORT = 11;
    private static final int ITEM_COMFORT_HXNYZS = 13;
    private static final int ITEM_COMFORT_KTKZ = 14;
    private static final int ITEM_COMFORT_QN = 12;
    private static final int ITEM_ECO = 2;
    private static final int ITEM_ECOJN = 15;
    private static final int ITEM_ECOJN_CO2JPL = 17;
    private static final int ITEM_ECOJN_XSJL = 16;
    private static final int ITEM_ECO_HXNYZS = 5;
    private static final int ITEM_ECO_KTKZ = 6;
    private static final int ITEM_ECO_QN = 3;
    private static final int ITEM_ECO_ZGXS = 4;
    private static final int ITEM_INIT = 0;
    private static final int ITEM_JSMS = 1;
    private static final int ITEM_SPORT = 7;
    private static final int ITEM_SPORT_HXNYZS = 9;
    private static final int ITEM_SPORT_KTKZ = 10;
    private static final int ITEM_SPORT_QN = 8;
    private CanDataInfo.HyRzcXnySet5 mSetData;

    public CanHyundaiRzcJsmsSetView(Activity activity) {
        super(activity, 18);
    }

    public void onPositiveItem(int id, int[] item) {
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 4:
                CanJni.HyundaiRzcXnySet(10, (this.mSetData.EcoAir << 6) + (this.mSetData.SportAir << 4) + (this.mSetData.ComfortAir << 2), (this.mSetData.EcoHxnyzs << 4) + (this.mSetData.SoprtHxnyzs << 2) + this.mSetData.ComfortHxnyzs, setZgxs(item));
                return;
            case 5:
                CanJni.HyundaiRzcXnySet(10, (this.mSetData.EcoAir << 6) + (this.mSetData.SportAir << 4) + (this.mSetData.ComfortAir << 2), (item << 4) + (this.mSetData.SoprtHxnyzs << 2) + this.mSetData.ComfortHxnyzs, this.mSetData.Eco);
                return;
            case 6:
                CanJni.HyundaiRzcXnySet(10, (item << 6) + (this.mSetData.SportAir << 4) + (this.mSetData.ComfortAir << 2), (this.mSetData.EcoHxnyzs << 4) + (this.mSetData.SoprtHxnyzs << 2) + this.mSetData.ComfortHxnyzs, this.mSetData.Eco);
                return;
            case 9:
                CanJni.HyundaiRzcXnySet(10, (this.mSetData.EcoAir << 6) + (this.mSetData.SportAir << 4) + (this.mSetData.ComfortAir << 2), (this.mSetData.EcoHxnyzs << 4) + (item << 2) + this.mSetData.ComfortHxnyzs, this.mSetData.Eco);
                return;
            case 10:
                CanJni.HyundaiRzcXnySet(10, (this.mSetData.EcoAir << 6) + (item << 4) + (this.mSetData.ComfortAir << 2), (this.mSetData.EcoHxnyzs << 4) + (this.mSetData.SoprtHxnyzs << 2) + this.mSetData.ComfortHxnyzs, this.mSetData.Eco);
                return;
            case 13:
                CanJni.HyundaiRzcXnySet(10, (this.mSetData.EcoAir << 6) + (this.mSetData.SportAir << 4) + (this.mSetData.ComfortAir << 2), (this.mSetData.EcoHxnyzs << 4) + (this.mSetData.SoprtHxnyzs << 2) + item, this.mSetData.Eco);
                return;
            case 14:
                CanJni.HyundaiRzcXnySet(10, (this.mSetData.EcoAir << 6) + (this.mSetData.SportAir << 4) + (item << 2), (this.mSetData.EcoHxnyzs << 4) + (this.mSetData.SoprtHxnyzs << 2) + this.mSetData.ComfortHxnyzs, this.mSetData.Eco);
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
                CanJni.HyundaiRzcXnySet(11, 1, 0, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_jsms_init, R.string.can_psa_wc_jsms, R.string.can_eco, R.string.can_qn, R.string.can_zgxs, R.string.can_hxnyzs, R.string.can_ktkz, R.string.can_sport, R.string.can_qn, R.string.can_hxnyzs, R.string.can_ktkz, R.string.can_comfort, R.string.can_qn, R.string.can_hxnyzs, R.string.can_ktkz, R.string.can_ecojn, R.string.can_xsjl, R.string.can_co2jpl};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mPopValueIds[4] = new int[]{R.array.can_hyundai_zgxs_arrays};
        this.mPopValueIds[5] = new int[]{R.array.can_hyundai_hxnyzs_arrays};
        this.mPopValueIds[9] = new int[]{R.array.can_hyundai_hxnyzs_arrays};
        this.mPopValueIds[13] = new int[]{R.array.can_hyundai_hxnyzs_arrays};
        this.mPopValueIds[6] = new int[]{R.string.can_mode_normal, R.string.can_eco};
        this.mPopValueIds[10] = new int[]{R.string.can_mode_normal, R.string.can_eco};
        this.mPopValueIds[14] = new int[]{R.string.can_mode_normal, R.string.can_eco};
        this.mPopValueIds[1] = new int[]{R.string.can_comfort, R.string.can_sport, R.string.can_eco};
        this.mSetData = new CanDataInfo.HyRzcXnySet5();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }

    public void ResetData(boolean check) {
        CanJni.HyundaiRzcGetXnySet5(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(1, this.mSetData.Jsms);
            updateItem(3, this.mSetData.EcoQn, String.format("%d%%", new Object[]{Integer.valueOf(this.mSetData.EcoQn)}));
            updateItem(4, getZgxs(this.mSetData.Eco));
            updateItem(5, this.mSetData.EcoHxnyzs);
            updateItem(6, this.mSetData.EcoAir);
            updateItem(8, this.mSetData.SportQn, String.format("%d%%", new Object[]{Integer.valueOf(this.mSetData.SportQn)}));
            updateItem(9, this.mSetData.SoprtHxnyzs);
            updateItem(10, this.mSetData.SportAir);
            updateItem(12, this.mSetData.ComfortQn, String.format("%d%%", new Object[]{Integer.valueOf(this.mSetData.ComfortQn)}));
            updateItem(13, this.mSetData.ComfortHxnyzs);
            updateItem(14, this.mSetData.ComfortAir);
            updateItem(16, this.mSetData.Co2Dis, String.format("%d KM", new Object[]{Integer.valueOf(this.mSetData.Co2Dis)}));
            updateItem(17, this.mSetData.Co2Val, String.format("%.1f Kg", new Object[]{Double.valueOf(((double) this.mSetData.Co2Val) * 0.1d)}));
        }
    }

    private int setZgxs(int item) {
        switch (item) {
            case 0:
                return Can.CAN_FLAT_RZC;
            case 1:
                return 90;
            case 2:
                return 100;
            case 3:
                return 110;
            case 4:
                return 120;
            case 5:
                return 130;
            default:
                return Can.CAN_FLAT_RZC;
        }
    }

    private int getZgxs(int zgxs) {
        switch (zgxs) {
            case 90:
                return 1;
            case 100:
                return 2;
            case 110:
                return 3;
            case 120:
                return 4;
            case 130:
                return 5;
            case Can.CAN_FLAT_RZC:
                return 0;
            default:
                return 0;
        }
    }

    public void QueryData() {
        CanJni.HyundaiRzcXnySet(255, 1, 0, 0);
        Sleep(10);
        CanJni.HyundaiRzcQuery(87, 0);
    }
}
