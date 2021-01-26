package com.ts.can.nissan.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanNissanRzcCarInfoView extends CanScrollCarInfoView {
    public CanNissanRzcCarInfoView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        } else if (id == 2) {
            CanJni.NissanCarSet(49, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        enterSubWin(CanCarInfoSub1Activity.class, ((Integer) v.getTag()).intValue());
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_amp_set, R.string.can_lang_set, R.string.can_mzd_cx4_setup};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CAR_TYPE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[0] = new int[]{R.array.can_fs_declare_199};
        this.mPopValueIds[2] = new int[]{R.string.can_language_chinese, R.string.can_language_english};
        if (CanJni.GetSubType() == 2 || CanJni.GetSubType() == 8 || CanJni.GetSubType() == 4 || CanJni.GetSubType() == 7) {
            this.mItemVisibles[3] = 1;
        } else {
            this.mItemVisibles[3] = 0;
        }
    }

    public void ResetData(boolean check) {
        updateItem(0, CanJni.GetSubType());
    }

    public void QueryData() {
    }

    public void doOnResume() {
        updateItem(0, CanJni.GetSubType());
    }
}
