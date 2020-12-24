package com.ts.can.gm.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGMWcSetOtherView extends CanScrollCarInfoView {
    private CanDataInfo.GmWc_SportMode mSportAdt;
    private CanDataInfo.GmWc_SportMode mSportData;
    private CanDataInfo.GmWc_YbData mYbAdt;
    private CanDataInfo.GmWc_YbData mYbData;

    public CanGMWcSetOtherView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.GmWcCarYbSet(3, Neg(item));
                return;
            case 3:
                CanJni.GmWcCarSportModeSet(1, item);
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
                CanJni.GmWcCarYbSet(1, Neg(this.mYbData.HhdlECO));
                return;
            case 1:
                CanJni.GmWcCarYbSet(2, Neg(this.mYbData.Ybdhxxxs));
                return;
            case 4:
                CanJni.GmWcCarSportModeSet(2, Neg(this.mSportData.Ydmsbg));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_hhdl_eco, R.string.can_ybdhxxxs, R.string.can_sdfwtsms, R.string.can_ydmsfdjzt, R.string.can_ydmsbg};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[2] = new int[]{R.string.can_jb, R.string.can_jp_qb};
        this.mPopValueIds[3] = new int[]{R.string.can_normal_mode, R.string.can_sport_mode};
        for (int i = 0; i < this.mItemVisibles.length; i++) {
            this.mItemVisibles[i] = 0;
        }
        this.mYbAdt = new CanDataInfo.GmWc_YbData();
        this.mYbData = new CanDataInfo.GmWc_YbData();
        this.mSportAdt = new CanDataInfo.GmWc_SportMode();
        this.mSportData = new CanDataInfo.GmWc_SportMode();
    }

    public void ResetData(boolean check) {
        CanJni.GmWcGetCarYbSetAdt(this.mYbAdt);
        CanJni.GmWcGetCarYbSet(this.mYbData);
        if (i2b(this.mYbAdt.UpdateOnce) && (!check || i2b(this.mYbAdt.Update))) {
            this.mYbAdt.Update = 0;
            showItem(new int[]{this.mYbAdt.HhdlECO, this.mYbAdt.Ybdhxxxs, this.mYbAdt.Sdfwtsms});
        }
        if (i2b(this.mYbData.UpdateOnce) && (!check || i2b(this.mYbData.Update))) {
            this.mYbData.Update = 0;
            updateItem(new int[]{this.mYbData.HhdlECO, this.mYbData.Ybdhxxxs, this.mYbData.Sdfwtsms});
        }
        CanJni.GmWcGetCarSportModeAdt(this.mSportAdt);
        CanJni.GmWcGetCarSportMode(this.mSportData);
        if (i2b(this.mSportAdt.UpdateOnce) && (!check || i2b(this.mSportAdt.Update))) {
            this.mSportAdt.Update = 0;
            int[] values = {this.mSportAdt.Ydmsfdjzt, this.mSportAdt.Ydmsbg};
            showItem(3, values[0]);
            showItem(4, values[1]);
        }
        if (!i2b(this.mSportData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSportData.Update)) {
            this.mSportData.Update = 0;
            int[] values2 = {this.mSportData.Ydmsfdjzt, this.mSportData.Ydmsbg};
            updateItem(3, values2[0]);
            updateItem(4, values2[1]);
        }
    }

    public void QueryData() {
        CanJni.GmWcCarQuery(5, 1, 117);
        Sleep(5);
        CanJni.GmWcCarQuery(5, 1, 133);
    }
}
