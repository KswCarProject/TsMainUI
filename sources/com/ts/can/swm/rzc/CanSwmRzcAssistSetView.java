package com.ts.can.swm.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSwmRzcAssistSetView extends CanScrollCarInfoView {
    public static final int ITEM_CDPLYJ = 2;
    public static final int ITEM_FWDKG = 4;
    public static final int ITEM_MAX = 6;
    public static final int ITEM_QFPZYJ = 1;
    public static final int ITEM_YYSZ = 3;
    public static final int ITEM_ZCZYTFJR = 0;
    public static final int ITEM_ZXLDTJ = 5;
    private CanDataInfo.SwmRzc_Set mSetData;

    public CanSwmRzcAssistSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.SwmRzcCarSet(1, item);
                return;
            case 1:
                CanJni.SwmRzcCarSet(6, item);
                return;
            case 2:
                CanJni.SwmRzcCarSet(7, item);
                return;
            case 3:
                CanJni.SwmRzcCarSet(9, item);
                return;
            case 5:
                CanJni.SwmRzcCarSet(13, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 4:
                CanJni.SwmRzcCarSet(11, Neg(this.mSetData.Fwdzt));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_seat_lhotwind, R.string.can_jp_qfpzjg, R.string.can_jp_cdpljg, R.string.can_lang_set, R.string.can_hant_fwd, R.string.can_zxldtj};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.array.can_lhotwind_array};
        this.mPopValueIds[1] = new int[]{R.string.can_off, R.string.can_ac_lo_sens, R.string.can_mid_sens, R.string.can_ac_hi_sens};
        this.mPopValueIds[2] = new int[]{R.string.can_off, R.string.can_ac_lo_sens, R.string.can_mid_sens, R.string.can_ac_hi_sens};
        this.mPopValueIds[3] = new int[]{R.string.can_lang_cn, R.string.can_lang_en};
        this.mPopValueIds[5] = new int[]{R.string.can_sport, R.string.can_comfort};
        this.mSetData = new CanDataInfo.SwmRzc_Set();
    }

    public void ResetData(boolean check) {
        CanJni.SwmRzcGetSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(1, this.mSetData.Qfpzyj);
            updateItem(2, this.mSetData.Cdplyj);
            updateItem(3, this.mSetData.Lang);
            updateItem(4, this.mSetData.Fwdzt);
            updateItem(5, Neg(this.mSetData.Zxld));
        }
    }

    public void QueryData() {
        CanJni.SwmRzcQuery(65, 0);
    }
}
