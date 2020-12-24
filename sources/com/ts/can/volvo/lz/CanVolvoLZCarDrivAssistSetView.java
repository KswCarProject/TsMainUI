package com.ts.can.volvo.lz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanVolvoLZCarDrivAssistSetView extends CanScrollCarInfoView {
    private static final int CITY_SAFE = 8;
    private static final int CMJS = 2;
    private static final int CYSJYSZ = 0;
    private static final int DSTC = 7;
    private static final int FXPLSZ = 6;
    private static final int HFQCSZ = 9;
    private static final int QXYCHSJ = 5;
    private static final int QXZCHSJ = 4;
    private static final int ZDHSJ = 3;
    private static final int ZDSM = 1;
    private CanDataInfo.VolvoXc60_CarSet mSetData;

    public CanVolvoLZCarDrivAssistSetView(Activity activity) {
        super(activity, 10);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.VolvoLzCx60CarSet(2, item);
                return;
            case 6:
                CanJni.VolvoLzCx60CarSet(13, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.VolvoLzCx60CarSet(0, Neg(this.mSetData.Cysjywz));
                return;
            case 1:
                CanJni.VolvoLzCx60CarSet(1, Neg(this.mSetData.Zdsm));
                return;
            case 3:
                CanJni.VolvoLzCx60CarSet(3, Neg(this.mSetData.Zdhsj));
                return;
            case 4:
                CanJni.VolvoLzCx60CarSet(4, Neg(this.mSetData.Qxzchsj));
                return;
            case 5:
                CanJni.VolvoLzCx60CarSet(5, Neg(this.mSetData.Qxychsj));
                return;
            case 7:
                CanJni.VolvoLzCx60CarSet(14, Neg(this.mSetData.DSTC));
                return;
            case 8:
                CanJni.VolvoLzCx60CarSet(15, Neg(this.mSetData.CitySafety));
                return;
            case 9:
                new CanItemMsgBox(((Integer) v.getTag()).intValue(), getActivity(), R.string.can_sure_setup, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.VolvoLzCx60Restor(0);
                    }
                });
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_volvo_cysjysz, R.string.can_volvo_zdsm, R.string.can_door_unlock, R.string.can_volvo_zdhsj, R.string.can_volvo_qxzchsj, R.string.can_volvo_qxychsj, R.string.can_volvo_fxplsz, R.string.can_volvo_dstc, R.string.can_volvo_csaq, R.string.can_volvo_hfqcsz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[2] = new int[]{R.string.can_door_unlock_key2, R.string.can_door_unlock_key1};
        this.mPopValueIds[6] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mSetData = new CanDataInfo.VolvoXc60_CarSet();
    }

    public void ResetData(boolean check) {
        CanJni.VolvoLzCx60GetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Cysjywz, this.mSetData.Zdsm, this.mSetData.Cmjs, this.mSetData.Zdhsj, this.mSetData.Qxzchsj, this.mSetData.Qxychsj, this.mSetData.Fxplsz, this.mSetData.DSTC, this.mSetData.CitySafety});
        }
    }

    public void QueryData() {
        CanJni.VolvoLzCx60Query(124, 0);
    }
}
