package com.ts.can.saic.mg;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMgCarAvmSetView extends CanScrollCarInfoView {
    private CanDataInfo.MG_GS_AVMDATA mSetData;

    public CanMgCarAvmSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.MGGSAvmSet(item + 12);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        if (((Integer) v.getTag()).intValue() == 0) {
            CanJni.MGGSAvmSet(Neg(this.mSetData.Dsdzxdzdkq) + 16);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_avm_dszxdzdkq360, R.string.can_tigger7_car_line};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.array.can_mggs_avm_array};
        this.mSetData = new CanDataInfo.MG_GS_AVMDATA();
    }

    public void ResetData(boolean check) {
        CanJni.MGGSGetAvmData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Dsdzxdzdkq);
            updateItem(1, this.mSetData.Cfx);
        }
    }

    public void QueryData() {
        CanJni.MGGSQuery(80);
    }
}
