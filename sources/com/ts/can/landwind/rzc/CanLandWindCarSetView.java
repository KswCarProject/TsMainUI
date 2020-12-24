package com.ts.can.landwind.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanLandWindCarSetView extends CanScrollCarInfoView {
    protected CanDataInfo.LandWind_CarSet mCarSetData;

    public CanLandWindCarSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CarSet(1, item);
                return;
            case 6:
                CarSet(5, item + 1);
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
                CarSet(2, Neg(this.mCarSetData.AvmSw));
                return;
            case 2:
                CarSet(3, Neg(this.mCarSetData.Ykzdsc));
                return;
            case 3:
                CarSet(4, Neg(this.mCarSetData.Rjxcd));
                return;
            case 4:
                CarSet(6, Neg(this.mCarSetData.Hsjzdzd));
                return;
            case 5:
                CarSet(7, Neg(this.mCarSetData.Ybd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mCarSetData = new CanDataInfo.LandWind_CarSet();
        this.mItemTitleIds = new int[]{R.string.can_ykjs, R.string.can_avmsw, R.string.can_ykzdsc, R.string.can_rjxcd, R.string.can_zdhsjzd, R.string.can_yb_light, R.string.can_bwhj};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_cch9_gatingsettings_key2, R.string.can_cch9_gatingsettings_key1};
        this.mPopValueIds[6] = new int[]{R.array.can_landwind_bnhj};
    }

    public void ResetData(boolean check) {
        GetCarSetData();
        if (!i2b(this.mCarSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSetData.Update)) {
            this.mCarSetData.Update = 0;
            updateItem(0, this.mCarSetData.Ykks);
            updateItem(1, this.mCarSetData.AvmSw);
            updateItem(2, this.mCarSetData.Ykzdsc);
            updateItem(3, this.mCarSetData.Rjxcd);
            updateItem(4, this.mCarSetData.Hsjzdzd);
            updateItem(5, this.mCarSetData.Ybd);
            updateItem(6, this.mCarSetData.Bwhj - 1);
        }
    }

    public void QueryData() {
        CanJni.LoadWindRzcQuery(39);
    }

    /* access modifiers changed from: protected */
    public void GetCarSetData() {
        CanJni.LoadWindRzcGetCarSet(this.mCarSetData);
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
        CanJni.LoadWindRzcCarSet(cmd, para, 0);
    }
}
