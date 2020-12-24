package com.ts.can.df.fg_rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanDFFGRzcDjxxSetView extends CanScrollCarInfoView {
    private static final int QDDJKZQWD = 0;
    private static final int QDDJWD = 1;
    private CanDataInfo.Dffg_RzcCarInfo mSetData;

    public CanDFFGRzcDjxxSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_qddjkzwd, R.string.can_qddjwd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mSetData = new CanDataInfo.Dffg_RzcCarInfo();
    }

    public void ResetData(boolean check) {
        CanJni.DffgRzcGetCarInfo(this.mSetData);
        if (!i2b(this.mSetData.DjUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.DjUpdate)) {
            this.mSetData.DjUpdate = 0;
            updateItem(0, this.mSetData.Qddjkzqwd, String.format("%d ℃", new Object[]{Integer.valueOf(this.mSetData.Qddjkzqwd - 40)}));
            updateItem(1, this.mSetData.Qddjwd, String.format("%d ℃", new Object[]{Integer.valueOf(this.mSetData.Qddjwd - 40)}));
        }
    }

    public void QueryData() {
    }
}
