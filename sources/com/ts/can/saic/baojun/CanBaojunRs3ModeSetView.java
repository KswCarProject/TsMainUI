package com.ts.can.saic.baojun;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBaojunRs3ModeSetView extends CanScrollCarInfoView {
    private CanDataInfo.Baojun_Info mInfo;

    public CanBaojunRs3ModeSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        CanJni.BaojunCarSet(id + 3, swValue(new int[]{this.mInfo.QjmsYxms, this.mInfo.QjmsCyms, this.mInfo.QjmsQlms, this.mInfo.QjmsWnms}[id]));
    }

    private int swValue(int val) {
        return val == 1 ? 2 : 1;
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_yxms, R.string.can_smoking_mode, R.string.can_cool_mode, R.string.can_warm_mode};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mInfo = new CanDataInfo.Baojun_Info();
    }

    public void ResetData(boolean check) {
        CanJni.BaojunGetCarSet(this.mInfo);
        if (!i2b(this.mInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mInfo.Update)) {
            this.mInfo.Update = 0;
            updateItem(new int[]{this.mInfo.QjmsYxms - 1, this.mInfo.QjmsCyms - 1, this.mInfo.QjmsQlms - 1, this.mInfo.QjmsWnms - 1});
        }
    }

    public void QueryData() {
    }
}
