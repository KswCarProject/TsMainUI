package com.ts.can.saic.wc.rw550;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;

public class CanRW550MG6WcYBSetView extends CanScrollCarInfoView {
    int preLgb5 = -1;

    public CanRW550MG6WcYBSetView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            FtSet.Setlgb5(item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_psa_2017_4008_ybbjys};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_nothave, R.string.can_color_blue, R.string.can_color_yellow, R.string.can_color_red};
    }

    public void ResetData(boolean check) {
        int lgb5 = FtSet.Getlgb5();
        if (lgb5 != this.preLgb5) {
            this.preLgb5 = lgb5;
            updateItem(0, lgb5);
        }
    }

    public void QueryData() {
    }
}
