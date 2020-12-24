package com.ts.can.honda;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHonda360PanoramaSetView extends CanScrollCarInfoView {
    private static final int ITEM_DSSSXTZDXS = 2;
    private static final int ITEM_FHTCMSSD = 4;
    private static final int ITEM_SXJQDHDCHM = 1;
    private static final int ITEM_SZLKJSQ = 5;
    private static final int ITEM_XSZMRSXTMS = 0;
    private static final int ITEM_YSTCMSSD = 3;
    private CanDataInfo.HondaSetData mSetData;

    public CanHonda360PanoramaSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.HondaDACarSet(53, item);
        } else if (id == 1) {
            CanJni.HondaDACarSet(54, item);
        } else if (id == 3) {
            CanJni.HondaDACarSet(56, item);
        } else if (id == 4) {
            CanJni.HondaDACarSet(57, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.HondaDACarSet(55, Neg(this.mSetData.Dsssxtzdxs));
                return;
            case 5:
                CanJni.HondaDACarSet(58, Neg(this.mSetData.Szlkjsq));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_xszmrdsxtms, R.string.can_sxjqdhddchm, R.string.can_dsssxtzdxs, R.string.can_ystcmssd, R.string.can_fhtcmssd, R.string.can_szlkjsq};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_qpm_qj, R.string.can_prepm, R.string.can_jqpm, R.string.can_zypm};
        this.mPopValueIds[1] = new int[]{R.string.can_hpm_qj, R.string.can_prepm, R.string.can_hxpm_gj, R.string.can_hxpm_bz};
        this.mPopValueIds[3] = new int[]{R.string.can_fhtc, R.string.can_pxtc};
        this.mPopValueIds[4] = new int[]{R.string.can_xzqyms, R.string.can_gyms};
        this.mSetData = new CanDataInfo.HondaSetData();
    }

    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.AvmUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.AvmUpdate)) {
            this.mSetData.AvmUpdate = 0;
            updateItem(new int[]{this.mSetData.Xszmrdsxtms, this.mSetData.Sxjqdhddchm, this.mSetData.Dsssxtzdxs, this.mSetData.Ystcmssd, this.mSetData.Fhtcmssd, this.mSetData.Szlkjsq});
        }
    }

    public void QueryData() {
        CanJni.HondaDAQuery(50, 0);
    }
}
