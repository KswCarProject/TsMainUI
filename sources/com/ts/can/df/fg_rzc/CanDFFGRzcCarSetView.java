package com.ts.can.df.fg_rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanDFFGRzcCarSetView extends CanScrollCarInfoView {
    private static final int CDGLDJKZ = 3;
    private static final int CDGLDJSDML = 4;
    private static final int FWD = 6;
    private static final int NLHSDJ = 0;
    private static final int TTXSXTKG = 1;
    private static final int VPS = 2;
    private static final int YBD = 7;
    private static final int YYDD = 5;
    private CanDataInfo.Dffg_RzcSet mSetData;

    public CanDFFGRzcCarSetView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.DffgRzcCarSet(1, item);
                return;
            case 3:
                CanJni.DffgRzcCarSet(4, item);
                return;
            case 5:
                CanJni.DffgRzcCarSet(7, item);
                return;
            case 6:
                CanJni.DffgRzcCarSet(8, item);
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
                CanJni.DffgRzcCarSet(2, Neg(this.mSetData.Ttxsxtkg));
                return;
            case 2:
                CanJni.DffgRzcCarSet(3, Neg(this.mSetData.VSP));
                return;
            case 4:
                CanJni.DffgRzcCarSet(5, Neg(this.mSetData.Cdgldjsdml));
                return;
            case 7:
                CanJni.DffgRzcCarSet(9, Neg(this.mSetData.Ybd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_gc_nlhsdj, R.string.can_ttxsxtkg, R.string.can_vsp_sw, R.string.can_cdgldjkz, R.string.can_cdgldjsdml, R.string.can_car_ddycxm, R.string.can_fwdkzfs, R.string.can_yb_light};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_headlightsens_0, R.string.can_headlightsens_1, R.string.can_headlightsens_2, R.string.can_headlightsens_3};
        this.mPopValueIds[3] = new int[]{R.string.can_headlightsens_0, R.string.can_headlightsens_1, R.string.can_headlightsens_2, R.string.can_headlightsens_3};
        this.mPopValueIds[5] = new int[]{R.string.can_0s, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s};
        this.mPopValueIds[6] = new int[]{R.string.can_off, R.string.can_on, R.string.can_sxdgb};
        if (CanJni.GetSubType() == 2) {
            this.mItemVisibles[5] = 0;
            this.mItemVisibles[6] = 0;
            this.mItemVisibles[7] = 0;
        } else {
            this.mItemVisibles[0] = 0;
            this.mItemVisibles[1] = 0;
            this.mItemVisibles[2] = 0;
            this.mItemVisibles[3] = 0;
            this.mItemVisibles[4] = 0;
        }
        this.mSetData = new CanDataInfo.Dffg_RzcSet();
    }

    public void ResetData(boolean check) {
        CanJni.DffgRzcGetSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Nlhsdj, this.mSetData.Ttxsxtkg, this.mSetData.VSP, this.mSetData.Cdgldjkz, this.mSetData.Cdgldjsdml, this.mSetData.Ysdd, this.mSetData.Fwd, this.mSetData.Ybd});
        }
    }

    public void QueryData() {
        CanJni.DffgRzcCarQuery(41);
    }
}
