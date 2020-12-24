package com.ts.can.psa;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanPSAAmpSetView extends CanScrollCarInfoView {
    public static final int ITEM_BAL = 3;
    public static final int ITEM_BAS = 4;
    public static final int ITEM_DEFAULT = 8;
    public static final int ITEM_EQ = 6;
    public static final int ITEM_FAD = 2;
    public static final int ITEM_LOUD = 7;
    public static final int ITEM_MAX = 9;
    public static final int ITEM_SYFB = 1;
    public static final int ITEM_TRE = 5;
    public static final int ITEM_VAILD = 0;
    private CanDataInfo.PeugAmpInfo mAmpData;

    public CanPSAAmpSetView(Activity activity) {
        super(activity, 9);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.PSAAmpSet(2, item);
                return;
            case 6:
                CanJni.PSAAmpSet(9, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 2:
                CanJni.PSAAmpSet(3, pos);
                return;
            case 3:
                CanJni.PSAAmpSet(4, pos);
                return;
            case 4:
                CanJni.PSAAmpSet(5, pos);
                return;
            case 5:
                CanJni.PSAAmpSet(6, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.PSAAmpSet(1, Neg(this.mAmpData.Vaild));
                return;
            case 7:
                CanJni.PSAAmpSet(10, Neg(this.mAmpData.Loud));
                return;
            case 8:
                new CanItemMsgBox(8, getActivity(), R.string.can_cmp_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.PSAAmpSet(170, 0);
                    }
                });
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_gf_exits, R.string.can_psa_wc_syfb, R.string.can_balance_front_rear, R.string.can_balance_left_right, R.string.can_vol_low, R.string.can_vol_high, R.string.can_eq, R.string.can_psa_wc_xd, R.string.can_factory_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.TITLE};
        int[] attr = new int[4];
        attr[0] = 3;
        attr[1] = 17;
        attr[2] = 1;
        this.mProgressAttrs[2] = attr;
        this.mProgressAttrs[3] = attr;
        this.mProgressAttrs[4] = attr;
        this.mProgressAttrs[5] = attr;
        this.mPopValueIds[1] = new int[]{R.string.can_all_passengers, R.string.can_driving_position};
        this.mPopValueIds[6] = new int[]{R.string.can_individual, R.string.can_eq_rock_pop, R.string.can_psa_eq_classic, R.string.can_psa_eq_ele, R.string.can_psa_eq_Jazz, R.string.can_psa_eq_vocal_music};
        this.mAmpData = new CanDataInfo.PeugAmpInfo();
    }

    public void ResetData(boolean check) {
        CanJni.PSAGetAmpInfo(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Vaild);
            updateItem(1, this.mAmpData.Syfb);
            updateItem(2, this.mAmpData.Fad, setValText(this.mAmpData.Fad, 2));
            updateItem(3, this.mAmpData.Bal, setValText(this.mAmpData.Bal, 3));
            updateItem(4, this.mAmpData.Bas, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Bas - 10)}));
            updateItem(5, this.mAmpData.Tre, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Tre - 10)}));
            updateItem(6, this.mAmpData.Eq);
            updateItem(7, this.mAmpData.Loud);
        }
    }

    private String setValText(int val, int sta) {
        switch (sta) {
            case 2:
                if (val == 10) {
                    return "0";
                }
                if (val < 10) {
                    return "F" + String.valueOf(10 - val);
                }
                if (val > 10) {
                    return "R" + String.valueOf(val - 10);
                }
                break;
            case 3:
                if (val == 10) {
                    return "0";
                }
                if (val < 10) {
                    return "L" + String.valueOf(10 - val);
                }
                if (val > 10) {
                    return "R" + String.valueOf(val - 10);
                }
                break;
        }
        return "0";
    }

    public void QueryData() {
        CanJni.PSAQuery(23, 0);
    }
}
