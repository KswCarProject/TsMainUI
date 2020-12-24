package com.ts.can.mzd.cx4.bnr;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanMzdCx4BnrSafeSetupView extends CanScrollCarInfoView {
    private CanDataInfo.Cx4_CarSet_Data mSetData;

    public CanMzdCx4BnrSafeSetupView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.MzdCx4CarSet(26, item);
        } else if (id == 2) {
            CanJni.MzdCx4CarSet(27, item);
        } else if (id == 4) {
            CanJni.MzdCx4CarSet(29, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.MzdCx4CarSet(25, Neg(this.mSetData.SBS_SCBS));
        } else if (id == 3) {
            CanJni.MzdCx4CarSet(28, Neg(this.mSetData.DRSS_Ms));
        } else if (id == 5) {
            new CanItemMsgBox(id, getActivity(), R.string.can_teramont_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanJni.MzdCx4CarSet(24, 0);
                }
            }, (CanItemMsgBox.onMsgBoxClick2) null);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_wc_sbs_mode, R.string.can_mzd_wc_sbs_jgjl, R.string.can_mzd_wc_sbs_jgyl, R.string.can_mzd_wc_drss_mode, R.string.can_mzd_wc_drss_jgjl, R.string.can_reset_all};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[1] = new int[]{R.string.can_sdqfwxjgjl_3, R.string.can_sdqfwxjgjl_1};
        this.mPopValueIds[2] = new int[]{R.string.can_off, R.string.can_cdjd, R.string.can_cdjg};
        this.mPopValueIds[4] = new int[]{R.string.can_sdqfwxjgjl_3, R.string.can_sdqfwxjgjl_2, R.string.can_sdqfwxjgjl_1};
        this.mSetData = new CanDataInfo.Cx4_CarSet_Data();
    }

    public void ResetData(boolean check) {
        CanJni.MzdCx4GetCarSetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.SBS_SCBS, this.mSetData.SBS_Jgjl, this.mSetData.SBS_Jgyl, this.mSetData.DRSS_Ms, this.mSetData.DRSS_Jgjl});
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(9, 0);
    }
}
