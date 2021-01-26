package com.ts.can.jiangling.myx;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanJiangLingMyxCarSetView extends CanScrollCarInfoView {
    protected static final int ITEM_FWD = 1;
    protected static final int ITEM_YBD = 0;
    protected static final int ITEM_YSDD = 2;
    private CanDataInfo.JiangLingMyx_SetData mJiangLingMyx_SetData;

    public CanJiangLingMyxCarSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.JiangLingMyxCarSet(151, item);
        } else if (id == 2) {
            CanJni.JiangLingMyxCarSet(152, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        if (((Integer) v.getTag()).intValue() == 0) {
            CanJni.JiangLingMyxCarSet(150, Neg(this.mJiangLingMyx_SetData.Ybd));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_yb_light, R.string.can_hant_fwd, R.string.can_car_ddycxm};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_off, R.string.can_on, R.string.can_sxdkq};
        this.mPopValueIds[2] = new int[]{R.string.can_off, R.string.can_30s, R.string.can_60s, R.string.can_90s};
        this.mJiangLingMyx_SetData = new CanDataInfo.JiangLingMyx_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.JiangLingMyxGetSetData(this.mJiangLingMyx_SetData);
        if (!i2b(this.mJiangLingMyx_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mJiangLingMyx_SetData.Update)) {
            this.mJiangLingMyx_SetData.Update = 0;
            updateItem(0, this.mJiangLingMyx_SetData.Ybd);
            updateItem(1, this.mJiangLingMyx_SetData.Fwd);
            updateItem(2, this.mJiangLingMyx_SetData.Ysdd);
        }
    }

    public void QueryData() {
        CanJni.JiangLingMyxQuery(65);
    }
}
