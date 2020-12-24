package com.ts.can.baic.wc.hss6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBaicHSS6WcCarInfoSetView extends CanScrollCarInfoView {
    private CanDataInfo.BaicHsS6Set mAdtData;
    private CanDataInfo.BaicHsS6Set mSetData;

    public CanBaicHSS6WcCarInfoSetView(Activity activity) {
        super(activity, 14);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.BaicWcS6CarSet(5, item);
                return;
            case 1:
                CanJni.BaicWcS6CarSet(4, item);
                return;
            case 3:
                CanJni.BaicWcS6CarSet(1, item);
                return;
            case 8:
                CanJni.BaicWcS6CarSet(7, item);
                return;
            case 11:
                CanJni.BaicWcS6CarSet(14, item);
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
                CanJni.BaicWcS6CarSet(2, Neg(this.mSetData.Zdls));
                return;
            case 4:
                CanJni.BaicWcS6CarSet(11, Neg(this.mSetData.Kmtzys));
                return;
            case 5:
                CanJni.BaicWcS6CarSet(10, Neg(this.mSetData.Jcfdsygdss));
                return;
            case 6:
                CanJni.BaicWcS6CarSet(9, Neg(this.mSetData.Ykjc));
                return;
            case 7:
                CanJni.BaicWcS6CarSet(8, Neg(this.mSetData.Yksc));
                return;
            case 9:
                CanJni.BaicWcS6CarSet(6, Neg(this.mSetData.WhsjLogo));
                return;
            case 10:
                CanJni.BaicWcS6CarSet(3, Neg(this.mSetData.Scwhsjzdzd));
                return;
            case 12:
                CanJni.BaicWcS6CarSet(13, Neg(this.mSetData.Jsyzyzdhy));
                return;
            case 13:
                CanJni.BaicWcS6CarSet(12, Neg(this.mSetData.Zxzdkqqwd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_carset_ljzmys, R.string.can_carset_hjzmys, R.string.can_carset_15zdls, R.string.can_carset_ecjs, R.string.can_carset_kmtzys, R.string.can_carset_jcygd, R.string.can_carset_ykjc, R.string.can_carset_ykss, R.string.can_carset_ddyczm, R.string.can_carset_logod, R.string.can_carset_hsjzdzd, R.string.can_carset_cbjdq, R.string.can_carset_jsyzdhw, R.string.can_carset_zxzdkqqwd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.array.can_baic_hss6_zmys};
        this.mPopValueIds[1] = this.mPopValueIds[0];
        this.mPopValueIds[3] = new int[]{R.string.can_sym, R.string.can_jsym};
        this.mPopValueIds[8] = new int[]{R.string.can_headlightautoofftime_0s, R.string.can_headlightautoofftime_8s, R.string.can_headlightautoofftime_15s, R.string.can_headlightautoofftime_30s};
        this.mPopValueIds[11] = new int[]{R.string.can_carset_gcms, R.string.can_carset_yhms};
        this.mAdtData = new CanDataInfo.BaicHsS6Set();
        this.mSetData = new CanDataInfo.BaicHsS6Set();
    }

    public void ResetData(boolean check) {
        CanJni.BaicWcS6GetCarSet(this.mAdtData, 0);
        if (i2b(this.mAdtData.UpdateOnce) && (!check || i2b(this.mAdtData.Update))) {
            this.mAdtData.Update = 0;
            showItem(new int[]{this.mAdtData.Ljzmys, this.mAdtData.Hjzmys, this.mAdtData.Zdls, this.mAdtData.Ecjs, this.mAdtData.Kmtzys, this.mAdtData.Jcfdsygdss, this.mAdtData.Ykjc, this.mAdtData.Yksc, this.mAdtData.Zdddyczmsj, this.mAdtData.WhsjLogo, this.mAdtData.Scwhsjzdzd, this.mAdtData.Cbjdqzt, this.mAdtData.Jsyzyzdhy, this.mAdtData.Zxzdkqqwd});
        }
        CanJni.BaicWcS6GetCarSet(this.mSetData, 1);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Ljzmys, this.mSetData.Hjzmys, this.mSetData.Zdls, this.mSetData.Ecjs, this.mSetData.Kmtzys, this.mSetData.Jcfdsygdss, this.mSetData.Ykjc, this.mSetData.Yksc, this.mSetData.Zdddyczmsj, this.mSetData.WhsjLogo, this.mSetData.Scwhsjzdzd, this.mSetData.Cbjdqzt, this.mSetData.Jsyzyzdhy, this.mSetData.Zxzdkqqwd});
        }
    }

    public void QueryData() {
        CanJni.BaicWcS6Query(5, 1, 103);
    }
}
