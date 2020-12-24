package com.ts.can.saic.wc;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemTextBtnList;

public class CanSaicRWMGWcSystemSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    private CanDataInfo.SailRwMg_SetData mSetData;

    public CanSaicRWMGWcSystemSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        if (id == 2) {
            CanJni.SaicRwMgLangSet(1, item + 1);
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
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_rw_rx5_tyfw, R.string.can_hfcssz, R.string.can_lang_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[2] = new int[]{R.string.can_lang_en, R.string.can_lang_cn};
        this.mSetData = new CanDataInfo.SailRwMg_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.SaicRwMgGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            if (this.mSetData.Tyfw == 0) {
                ((CanItemTextBtnList) this.mItemObjects[0]).SetColor(Color.rgb(255, 255, 255));
            }
        }
    }

    public void QueryData() {
    }

    public void onOK(int param) {
        switch (param) {
            case 0:
                CanJni.SaicRwMgCarSet(169, 1, 255, 255);
                ((CanItemTextBtnList) this.mItemObjects[0]).SetColor(Color.rgb(128, 128, 128));
                return;
            case 1:
                CanJni.SaicRwMgCarSet(170, 1, 255, 255);
                return;
            default:
                return;
        }
    }
}
