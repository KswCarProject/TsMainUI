package com.ts.can.ford.wc.mondeo;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordMondeoWcSystemSetView extends CanScrollCarInfoView {
    private CanDataInfo.FordWcMondeo2013TsMsg mMsgData;

    public CanFordMondeoWcSystemSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.FordWcMondeo2013TsSet(5, item);
                return;
            case 1:
                CanJni.FordWcMondeo2013TsSet(1, item);
                return;
            case 2:
                CanJni.FordWcMondeo2013TsSet(4, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_lang_set, R.string.can_dlunits, R.string.can_temp_dw};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_lang_cn, R.string.can_lang_en};
        this.mPopValueIds[1] = new int[]{R.array.can_fist_l_c};
        this.mPopValueIds[2] = new int[]{R.string.can_temperature_c, R.string.can_temperature_f};
        this.mMsgData = new CanDataInfo.FordWcMondeo2013TsMsg();
    }

    public void ResetData(boolean check) {
        CanJni.FordWcMondeo2013GetTsData(this.mMsgData);
        if (!i2b(this.mMsgData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mMsgData.Update)) {
            this.mMsgData.Update = 0;
            updateItem(new int[]{this.mMsgData.Lang, this.mMsgData.Dldw, this.mMsgData.Wddw});
        }
    }

    public void QueryData() {
        CanJni.FordWcMondeo2013Query(1, 104);
    }
}
