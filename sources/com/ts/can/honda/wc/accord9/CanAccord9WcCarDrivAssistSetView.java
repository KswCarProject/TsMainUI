package com.ts.can.honda.wc.accord9;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanAccord9WcCarDrivAssistSetView extends CanScrollCarInfoView {
    private CanDataInfo.HondaAccord9WcOutTemp mTempData;

    public CanAccord9WcCarDrivAssistSetView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        if (id == 0) {
            CanJni.HondaWcAccord9OutTempSet(1, pos);
        }
    }

    public void onClick(View v) {
        ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_tjww};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 10;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        this.mTempData = new CanDataInfo.HondaAccord9WcOutTemp();
    }

    public void ResetData(boolean check) {
        CanJni.HondaWcAccord9GetOutTempData(this.mTempData);
        if (!i2b(this.mTempData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTempData.Update)) {
            this.mTempData.Update = 0;
            updateItem(0, this.mTempData.Tjwbqwxs, new StringBuilder(String.valueOf(this.mTempData.Tjwbqwxs - 5)).toString());
        }
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, 104);
    }
}
