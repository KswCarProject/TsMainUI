package com.ts.can.df.wc.jy;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanJYX5WcLightSetView extends CanScrollCarInfoView {
    private CanDataInfo.DfJyX5_Wc_LightSet mLightSet;

    public CanJYX5WcLightSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.DfJyWcGetCarLightSet(2, item);
        } else if (id == 1) {
            CanJni.DfJyWcGetCarLightSet(1, item + 1);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_df_jyx5_ddys, R.string.can_df_jyx5_jdsj};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.array.can_df_jyx5_ddys};
        this.mPopValueIds[1] = new int[]{R.array.can_df_jyx5_jdsj};
        this.mLightSet = new CanDataInfo.DfJyX5_Wc_LightSet();
    }

    public void ResetData(boolean check) {
        CanJni.DfJyWcGetCarLightInfo(this.mLightSet);
        if (!i2b(this.mLightSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLightSet.Update)) {
            this.mLightSet.Update = 0;
            showItem(new int[]{this.mLightSet.AdtDdys, this.mLightSet.AdtJdsj});
            updateItem(new int[]{this.mLightSet.Ddys, this.mLightSet.Jdsj});
        }
    }

    public void QueryData() {
        CanJni.DfJyWcGetCarQuery(5, 1, 103);
    }
}
