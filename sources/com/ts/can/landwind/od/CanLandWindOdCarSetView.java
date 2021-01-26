package com.ts.can.landwind.od;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanLandWindOdCarSetView extends CanScrollCarInfoView {
    CanDataInfo.LandWindOd_CarSet mCarSet;

    public CanLandWindOdCarSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.LandWindOdCarSet(146, item);
                return;
            case 6:
                CanJni.LandWindOdCarSet(152, item);
                return;
            case 7:
                CanJni.LandWindOdCarSet(153, item);
                return;
            case 8:
                CanJni.LandWindOdCarSet(Can.CAN_CC_WC, item);
                return;
            case 9:
                CanJni.LandWindOdCarSet(Can.CAN_DFFG_S560, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 1) {
            CanJni.LandWindOdCarSet(147, Neg(this.mCarSet.AvmMrkg));
        } else if (id == 2) {
            CanJni.LandWindOdCarSet(148, Neg(this.mCarSet.Ykzdsc));
        } else if (id == 3) {
            CanJni.LandWindOdCarSet(149, Neg(this.mCarSet.Rjxcd));
        } else if (id == 4) {
            CanJni.LandWindOdCarSet(150, Neg(this.mCarSet.Ybd));
        } else if (id == 5) {
            CanJni.LandWindOdCarSet(151, Neg(this.mCarSet.Hsjzdzd));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ykjssz, R.string.can_avmsw, R.string.can_carset_ykss, R.string.can_tigger7_day_light, R.string.can_yb_light, R.string.can_hsjzdzd, R.string.can_bwhj_light};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_jsym, R.string.can_sym};
        this.mPopValueIds[6] = new int[]{R.string.can_trunk_close, R.string.can_30s, R.string.can_1min, R.string.can_3min, R.string.can_5min};
        this.mCarSet = new CanDataInfo.LandWindOd_CarSet();
    }

    public void ResetData(boolean check) {
        CanJni.LandWindOdGetCarSet(this.mCarSet);
        if (!i2b(this.mCarSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSet.Update)) {
            this.mCarSet.Update = 0;
            updateItem(0, this.mCarSet.Ykks);
            updateItem(1, this.mCarSet.AvmMrkg);
            updateItem(2, this.mCarSet.Ykzdsc);
            updateItem(3, this.mCarSet.Rjxcd);
            updateItem(4, this.mCarSet.Ybd);
            updateItem(5, this.mCarSet.Hsjzdzd);
            updateItem(6, this.mCarSet.Bnhj);
        }
    }

    public void QueryData() {
        CanJni.LandWindOdQuery(65);
    }
}
