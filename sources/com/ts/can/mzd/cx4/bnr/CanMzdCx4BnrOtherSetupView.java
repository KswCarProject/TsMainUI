package com.ts.can.mzd.cx4.bnr;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanMzdCx4BnrOtherSetupView extends CanScrollCarInfoView {
    private static final int ITEM_MDJKXT = 4;
    private CanDataInfo.Cx4_CarSet_Data mSetData;
    private CanDataInfo.Mzd_Bnr_Set mSetData2;

    public CanMzdCx4BnrOtherSetupView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.MzdCx4CarSet(21, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.MzdCx4CarSet(20, Neg(this.mSetData.zncsscxt));
        } else if (id == 2) {
            CanJni.MzdCx4CarSet(22, Neg(this.mSetData.tbpjhlcA));
        } else if (id == 3) {
            new CanItemMsgBox(id, getActivity(), R.string.can_teramont_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanJni.MzdCx4CarSet(23, 0);
                }
            }, (CanItemMsgBox.onMsgBoxClick2) null);
        } else if (id == 4) {
            CanJni.MzdCx4CarSet(30, Neg(this.mSetData2.MdjcxtXtsd));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_cx4_other_sc_system, R.string.can_mzd_cx4_other_control_voice, R.string.can_mzd_cx4_other_sync_tripa, R.string.can_mzd_cx4_reset_avg, R.string.can_mdfzxt};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[1] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_voice_low, R.string.can_mzd_cx4_voice_high};
        this.mSetData = new CanDataInfo.Cx4_CarSet_Data();
        this.mSetData2 = new CanDataInfo.Mzd_Bnr_Set();
    }

    public void ResetData(boolean check) {
        CanJni.MzdCx4GetCarSetInfo(this.mSetData);
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.zncsscxt, this.mSetData.mdjkyl, this.mSetData.tbpjhlcA});
        }
        CanJni.MzdBnrGetCarSet2(this.mSetData2);
        if (!i2b(this.mSetData2.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData2.Update)) {
            this.mSetData2.Update = 0;
            updateItem(4, this.mSetData2.MdjcxtXtsd);
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(9, 0);
        Sleep(5);
        CanJni.MzdCx4Query(13, 0);
    }
}
