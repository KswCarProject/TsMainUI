package com.ts.can.cc.wc.h2;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;

public class CanCCH2WcCameraSetView extends CanScrollCarInfoView {
    public CanCCH2WcCameraSetView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                FtSet.Setlgb4(Neg(FtSet.Getlgb4()));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_has_right_camera};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH};
    }

    public void ResetData(boolean check) {
        updateItem(0, FtSet.Getlgb4());
    }

    public void QueryData() {
    }
}
