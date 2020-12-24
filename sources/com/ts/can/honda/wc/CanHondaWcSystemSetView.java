package com.ts.can.honda.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanHondaWcSystemSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    public CanHondaWcSystemSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        if (id == 3) {
            CanJni.HondaWcCarLangSet(1, item + 1);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                new CanItemMsgBox(0, getActivity(), R.string.can_sure_tybd, this);
                return;
            case 1:
                new CanItemMsgBox(1, getActivity(), R.string.can_sure_setup, this);
                return;
            case 2:
                new CanItemMsgBox(2, getActivity(), R.string.can_cmp_reset_notice, this);
                return;
            case 4:
                new CanItemMsgBox(4, getActivity(), R.string.can_delete_notice, this);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_tpms_set, R.string.can_hfcssz, R.string.can_xlbyxxcz, R.string.can_lang_set, R.string.can_sclsyhjl};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[3] = new int[]{R.string.can_lang_en, R.string.can_lang_cn, R.string.can_lang_tw};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }

    public void onOK(int param) {
        switch (param) {
            case 0:
                CanJni.HondaWcTpmsSet(4, 1);
                return;
            case 1:
                CanJni.HondaWcCarAssistSet(5, 1);
                return;
            case 2:
                CanJni.HondaWcCarAssistSet(6, 1);
                return;
            case 4:
                CanJni.HondaWcCameraSet(6, 255);
                return;
            default:
                return;
        }
    }
}
