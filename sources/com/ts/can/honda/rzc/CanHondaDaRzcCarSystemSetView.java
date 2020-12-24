package com.ts.can.honda.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanHondaDaRzcCarSystemSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_DEFAULTALL = 3;
    public static final int ITEM_JTBZSBXTXTB = 5;
    public static final int ITEM_JYWZLD = 7;
    public static final int ITEM_RESETMAINTENANCE = 2;
    public static final int ITEM_SXCWZYD = 8;
    public static final int ITEM_TPMS_RST = 4;
    public static final int ITEM_TTJG = 6;
    public static final int ITEM_ZSBSZ = 0;
    public static final int ITEM_lANGSET = 1;
    protected CanDataInfo.HondaSetData mSetData;

    public CanHondaDaRzcCarSystemSetView(Activity activity) {
        super(activity, 9);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.HondaDACarSet(85, item);
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
                CanJni.HondaDACarSet(35, Neg(this.mSetData.zsbsz));
                return;
            case 2:
                new CanItemMsgBox(2, getActivity(), R.string.can_sure_setup, this);
                return;
            case 3:
                new CanItemMsgBox(3, getActivity(), R.string.can_hfcsz, this);
                return;
            case 4:
                new CanItemMsgBox(4, getActivity(), R.string.can_tpms_set, this);
                return;
            case 5:
                CanJni.HondaDACarSet(39, Neg(this.mSetData.Jtbzsbxtxtb));
                return;
            case 6:
                CanJni.HondaDACarSet(40, Neg(this.mSetData.Ttjg));
                return;
            case 7:
                CanJni.HondaDACarSet(41, Neg(this.mSetData.Jywzld));
                return;
            case 8:
                CanJni.HondaDACarSet(52, Neg(this.mSetData.Sxcszywzyd));
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
        switch (param) {
            case 2:
                CanJni.HondaDACarSet(14, 0);
                return;
            case 3:
                CanJni.HondaDACarSet(15, 0);
                return;
            case 4:
                CanJni.HondaDACarSet(17, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_system_zsb, R.string.can_lang_set, R.string.can_xlbyxxcz, R.string.can_tpms_set, R.string.can_hfcsz, R.string.can_traffice_sign_rec, R.string.can_ttjg, R.string.can_jywzzyld, R.string.can_sxcwzyd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[1] = new int[]{R.string.can_lang_cn, R.string.can_lang_en};
        this.mSetData = new CanDataInfo.HondaSetData();
    }

    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.XtszUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.XtszUpdate)) {
            this.mSetData.XtszUpdate = 0;
            updateItem(0, this.mSetData.zsbsz);
            updateItem(5, this.mSetData.Jtbzsbxtxtb);
            updateItem(6, this.mSetData.Ttjg);
            updateItem(7, this.mSetData.Jywzld);
            updateItem(8, this.mSetData.Sxcszywzyd);
        }
    }

    public void QueryData() {
    }
}
