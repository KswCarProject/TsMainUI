package com.ts.can.saic.wc.sgmw;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemBlankTextList;

public class CanSgmwWcWarnView extends CanScrollCarInfoView {
    private String[] mWarnArray;
    private CanDataInfo.SGMW_Wc_WarnData mWarnData;

    public CanSgmwWcWarnView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[7];
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.TEXT};
        this.mItemVisibles = new int[7];
        this.mWarnData = new CanDataInfo.SGMW_Wc_WarnData();
        this.mWarnArray = getStringArray(R.array.can_sgmw_warn_array);
    }

    public void ResetData(boolean check) {
        CanJni.SGMWWcGetWarnData(this.mWarnData);
        if (!i2b(this.mWarnData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWarnData.Update)) {
            this.mWarnData.Update = 0;
            int num = this.mWarnData.TotalNum;
            for (int i = 0; i < this.mItemObjects.length; i++) {
                if (i < num) {
                    int index = this.mWarnData.Data[i] - 1;
                    if (index < 0 || index >= this.mWarnArray.length) {
                        showItem(i, 0);
                    } else {
                        showItem(i, 1);
                        ((CanItemBlankTextList) this.mItemObjects[i]).SetVal(this.mWarnArray[index]);
                    }
                } else {
                    showItem(i, 0);
                }
            }
        }
    }

    public void QueryData() {
        CanJni.SGMWWcQuery(5, 1, 116);
    }
}
