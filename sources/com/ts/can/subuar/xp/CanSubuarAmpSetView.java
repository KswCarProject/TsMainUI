package com.ts.can.subuar.xp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemProgressList;

public class CanSubuarAmpSetView extends CanScrollCarInfoView {
    public static final int ITEM_BAL = 5;
    public static final int ITEM_BAS = 6;
    public static final int ITEM_FAD = 4;
    public static final int ITEM_MAX = 10;
    public static final int ITEM_MID = 7;
    public static final int ITEM_RESET = 9;
    public static final int ITEM_TOGGLE = 2;
    public static final int ITEM_TRE = 8;
    public static final int ITEM_TYPE = 0;
    public static final int ITEM_VAILD = 1;
    public static final int ITEM_VOL = 3;
    public static final String SUBUAR_XP_AMP_TYPE = "subuar_xp_amp_type";
    private CanDataInfo.SubuarXp_AmpInfo mAmpInfo;
    private int mLastType;
    private SharedPreferences mSp;

    public CanSubuarAmpSetView(Activity activity) {
        super(activity, 10);
    }

    public void onItem(int id, int item) {
        if (id == 0 && this.mLastType != item) {
            this.mLastType = item;
            updateProgress(item);
            updateItem(0, item);
            updateItem(4, 10, "0");
            updateItem(5, 10, "0");
            updateItem(6, 10, "0");
            updateItem(7, 10, "0");
            updateItem(8, 10, "0");
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 3:
                CanJni.SubuarXpAmpSet(2, pos);
                return;
            case 4:
                CanJni.SubuarXpAmpSet(3, pos);
                return;
            case 5:
                CanJni.SubuarXpAmpSet(4, pos);
                return;
            case 6:
                CanJni.SubuarXpAmpSet(5, pos);
                return;
            case 7:
                CanJni.SubuarXpAmpSet(7, pos);
                return;
            case 8:
                CanJni.SubuarXpAmpSet(6, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 9) {
            new CanItemMsgBox(id, getActivity(), R.string.can_cmp_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanJni.SubuarXpAmpSet(8, 170);
                }
            });
        } else if (id == 2) {
            CanJni.SubuarXpAmpSet(1, Neg(this.mAmpInfo.Sw));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_gf_exits, R.string.can_gf_toggle, R.string.can_vol, R.string.can_balance_front_rear, R.string.can_balance_left_right, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_vol_high, R.string.can_setup_reset};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[0] = new int[]{R.string.can_subuar_xp_car_type_1, R.string.can_subuar_xp_car_type_2};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 63;
        iArr2[2] = 1;
        iArr[3] = iArr2;
        this.mProgressAttrs[4] = new int[]{3, 17, 1, 1};
        this.mProgressAttrs[5] = new int[]{3, 17, 1, 1};
        this.mProgressAttrs[6] = new int[]{5, 15, 1, 1};
        this.mProgressAttrs[7] = new int[]{5, 15, 1, 1};
        this.mProgressAttrs[8] = new int[]{5, 15, 1, 1};
        this.mAmpInfo = new CanDataInfo.SubuarXp_AmpInfo();
    }

    public void doOnResume() {
        this.mSp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        this.mLastType = this.mSp.getInt(SUBUAR_XP_AMP_TYPE, 0);
        updateProgress(this.mLastType);
    }

    public void doOnPause() {
        this.mSp.edit().putInt(SUBUAR_XP_AMP_TYPE, this.mLastType).apply();
    }

    public void ResetData(boolean check) {
        CanJni.SubuarXpGetAmpInfo(this.mAmpInfo);
        if (!i2b(this.mAmpInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpInfo.Update)) {
            this.mAmpInfo.Update = 0;
            updateItem(0, this.mLastType);
            updateItem(1, this.mAmpInfo.Adt);
            updateItem(2, this.mAmpInfo.Sw);
            updateItem(3, this.mAmpInfo.Vol);
            updateItem(4, this.mAmpInfo.Fad, getFADText(this.mAmpInfo.Fad));
            updateItem(5, this.mAmpInfo.Bal, getBALText(this.mAmpInfo.Bal));
            updateItem(6, this.mAmpInfo.Bas, getText(this.mAmpInfo.Bas));
            updateItem(7, this.mAmpInfo.Mid, getText(this.mAmpInfo.Mid));
            updateItem(8, this.mAmpInfo.Tre, getText(this.mAmpInfo.Tre));
        }
    }

    public void QueryData() {
        CanJni.SubuarXpQuery(49, 0);
    }

    private void updateProgress(int type) {
        if (type == 0) {
            ((CanItemProgressList) this.mItemObjects[3]).SetMinMax(0, 63);
            ((CanItemProgressList) this.mItemObjects[4]).SetMinMax(3, 17);
            ((CanItemProgressList) this.mItemObjects[5]).SetMinMax(3, 17);
            ((CanItemProgressList) this.mItemObjects[6]).SetMinMax(5, 15);
            ((CanItemProgressList) this.mItemObjects[7]).SetMinMax(5, 15);
            ((CanItemProgressList) this.mItemObjects[8]).SetMinMax(5, 15);
            return;
        }
        ((CanItemProgressList) this.mItemObjects[3]).SetMinMax(0, 38);
        ((CanItemProgressList) this.mItemObjects[4]).SetMinMax(1, 19);
        ((CanItemProgressList) this.mItemObjects[5]).SetMinMax(1, 19);
        ((CanItemProgressList) this.mItemObjects[6]).SetMinMax(1, 19);
        ((CanItemProgressList) this.mItemObjects[7]).SetMinMax(1, 19);
        ((CanItemProgressList) this.mItemObjects[8]).SetMinMax(1, 19);
    }

    private String getFADText(int val) {
        int val2 = val - 10;
        if (val2 > 0) {
            return "R" + val2;
        }
        if (val2 < 0) {
            return "F" + Math.abs(val2);
        }
        return new StringBuilder().append(val2).toString();
    }

    private String getBALText(int val) {
        int val2 = val - 10;
        if (val2 > 0) {
            return "R" + val2;
        }
        if (val2 < 0) {
            return "L" + Math.abs(val2);
        }
        return new StringBuilder().append(val2).toString();
    }

    private String getText(int val) {
        int val2 = val - 10;
        if (val2 > 0) {
            return "+" + val2;
        }
        return new StringBuilder().append(val2).toString();
    }
}
