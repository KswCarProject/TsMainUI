package com.ts.can.cc.wc.h6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanWcH6LightSetView extends CanScrollCarInfoView {
    private CanDataInfo.CcH6WcLightSet mLightSet;

    public CanWcH6LightSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.CcH6WcCarLightSet(6, item + 1);
                return;
            case 1:
                CanJni.CcH6WcCarLightSet(5, item + 1);
                return;
            case 2:
                CanJni.CcH6WcCarLightSet(7, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_cch6_gensuihuijia_delaytime, R.string.can_cch6_dingdeng_delaytime, R.string.can_cch6_jiedian_setup};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.array.can_cch6_hj_delay_array};
        this.mPopValueIds[1] = new int[]{R.array.can_cch6_dd_delay_2017_array};
        this.mPopValueIds[2] = new int[]{R.array.can_cch6_jd_setup_array};
        this.mLightSet = new CanDataInfo.CcH6WcLightSet();
    }

    public void ResetData(boolean check) {
        CanJni.CcH6WcGetCarLightSet(this.mLightSet);
        if (!i2b(this.mLightSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLightSet.Update)) {
            this.mLightSet.Update = 0;
            updateItem(new int[]{this.mLightSet.Gshjyssj - 1, this.mLightSet.Ddyssjsd - 1, this.mLightSet.Jdsz - 1});
        }
    }

    public void QueryData() {
    }
}
