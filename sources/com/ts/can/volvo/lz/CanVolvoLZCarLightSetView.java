package com.ts.can.volvo.lz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanVolvoLZCarLightSetView extends CanScrollCarInfoView {
    private static final int JSQRD = 1;
    private static final int JTAQLDSJ = 3;
    private static final int LDSJ = 2;
    private static final int LSZSJTKG = 5;
    private static final int SCZXXHKG = 4;
    private static final int SMZSD = 0;
    private static final int ZNSXQD = 6;
    private CanDataInfo.VolvoXc60_CarSet mSetData;

    public CanVolvoLZCarLightSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.VolvoLzCx60CarSet(8, item);
                return;
            case 3:
                CanJni.VolvoLzCx60CarSet(9, item);
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
                CanJni.VolvoLzCx60CarSet(6, Neg(this.mSetData.Smtsd));
                return;
            case 1:
                CanJni.VolvoLzCx60CarSet(7, Neg(this.mSetData.Jsqrd));
                return;
            case 4:
                CanJni.VolvoLzCx60CarSet(10, Neg(this.mSetData.Sczxxhkg));
                return;
            case 5:
                CanJni.VolvoLzCx60CarSet(11, Neg(this.mSetData.Lszsjtkg));
                return;
            case 6:
                CanJni.VolvoLzCx60CarSet(12, Neg(this.mSetData.Znscqd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_volvo_smzsd, R.string.can_volvo_jsqrd, R.string.can_volvo_ldsj, R.string.can_volvo_jtaqldsj, R.string.can_volvo_sczxxhkg, R.string.can_volvo_lszsjtkg, R.string.can_volvo_znsxqd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[2] = new int[]{R.string.can_trunk_close, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s};
        this.mPopValueIds[3] = new int[]{R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_90s};
        this.mSetData = new CanDataInfo.VolvoXc60_CarSet();
    }

    public void ResetData(boolean check) {
        CanJni.VolvoLzCx60GetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Smtsd, this.mSetData.Jsqrd, this.mSetData.Ldsj, this.mSetData.Jtaqdsj, this.mSetData.Sczxxhkg, this.mSetData.Lszsjtkg, this.mSetData.Znscqd});
        }
    }

    public void QueryData() {
        CanJni.VolvoLzCx60Query(124, 0);
    }
}
