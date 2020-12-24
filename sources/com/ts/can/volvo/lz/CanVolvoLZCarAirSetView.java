package com.ts.can.volvo.lz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanVolvoLZCarAirSetView extends CanScrollCarInfoView {
    private static final int CNXHJSSZ = 1;
    private static final int HFKTSZ = 3;
    private static final int ZDFL = 0;
    private static final int ZDHFCS = 2;
    private CanDataInfo.VolvoXc60_CarSet mSetData;

    public CanVolvoLZCarAirSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.VolvoLzCx60CarSet(19, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.VolvoLzCx60CarSet(20, Neg(this.mSetData.Cnxhjssz));
                return;
            case 2:
                CanJni.VolvoLzCx60CarSet(21, Neg(this.mSetData.Zdhfcs));
                return;
            case 3:
                new CanItemMsgBox(((Integer) v.getTag()).intValue(), getActivity(), R.string.can_sure_setup, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.VolvoLzCx60Restor(2);
                    }
                });
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_auto_wind, R.string.can_cnxhjssz, R.string.can_zdhfcs, R.string.can_volvo_hfktsz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[0] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mSetData = new CanDataInfo.VolvoXc60_CarSet();
    }

    public void ResetData(boolean check) {
        CanJni.VolvoLzCx60GetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Zdfltj, this.mSetData.Cnxhjssz, this.mSetData.Zdhfcs});
        }
    }

    public void QueryData() {
        CanJni.VolvoLzCx60Query(124, 0);
    }
}
