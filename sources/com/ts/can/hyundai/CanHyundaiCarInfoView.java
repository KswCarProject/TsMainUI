package com.ts.can.hyundai;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.can.CanScrollCarInfoView;

public class CanHyundaiCarInfoView extends CanScrollCarInfoView {
    static int nOldLangTick = 0;

    public CanHyundaiCarInfoView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View arg0) {
        int id = ((Integer) arg0.getTag()).intValue();
        if (id == 0) {
            enterSubWin(CanHyunDaiCarTypeActivity.class);
        } else if (id == 1) {
            enterSubWin(CanCarInfoSub1Activity.class, 1);
        } else if (id == 2) {
            enterSubWin(CanCarInfoSub1Activity.class, 2);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_bcdhsz, R.string.can_mzd_cx4_other};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }

    public static void HyundaiSystemnLangSet() {
        if (nOldLangTick == 0) {
            nOldLangTick = 100;
            String language = CanFunc.mContext.getResources().getConfiguration().locale.getLanguage();
            if (language.endsWith("zh")) {
                CanJni.HyundaiEuropaCmd(Can.CAN_FLAT_WC, 1);
            } else if (language.endsWith("en")) {
                CanJni.HyundaiEuropaCmd(Can.CAN_FLAT_WC, 2);
            } else if (language.endsWith("ru")) {
                CanJni.HyundaiEuropaCmd(Can.CAN_FLAT_WC, 3);
            } else {
                CanJni.HyundaiEuropaCmd(Can.CAN_FLAT_WC, 0);
            }
        }
        if (nOldLangTick > 0) {
            nOldLangTick--;
        }
    }
}
