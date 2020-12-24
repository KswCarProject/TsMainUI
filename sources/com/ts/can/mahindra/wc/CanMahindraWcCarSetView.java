package com.ts.can.mahindra.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemSwitchList;

public class CanMahindraWcCarSetView extends CanScrollCarInfoView {
    protected static final int ITEM_AUTO_LAMP = 1;
    protected static final int ITEM_AUTO_WIPE = 0;
    private CanDataInfo.MahindraCarInfo mSetData;

    public CanMahindraWcCarSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.MahindraWcCarSet(2, Neg(this.mSetData.Auto_Wipe));
                return;
            case 1:
                CanJni.MahindraWcCarSet(1, Neg(this.mSetData.Auto_Lamp));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.app_name, R.string.app_name};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mSetData = new CanDataInfo.MahindraCarInfo();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        ((CanItemSwitchList) this.mItemObjects[0]).GetBtn().setText("Auto Wipe");
        ((CanItemSwitchList) this.mItemObjects[1]).GetBtn().setText("Auto Lamp");
    }

    public void ResetData(boolean check) {
        CanJni.MahindraWcGetSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(1, this.mSetData.Auto_Lamp);
            updateItem(0, this.mSetData.Auto_Wipe);
        }
    }

    public void QueryData() {
        CanJni.MahindraWcQuery(97);
    }
}
