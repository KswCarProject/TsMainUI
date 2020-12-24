package com.ts.can.qoros.bnr;

import android.provider.Settings;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanQorosBnrCarSetActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    private static final int ITEM_DDWM = 9;
    private static final int ITEM_DGKZ_BWHJ = 7;
    private static final int ITEM_DGSJKZ_BWHJ = 8;
    private static final int ITEM_DIS_UNIT = 4;
    private static final int ITEM_LANGUAGE = 1;
    private static final int ITEM_MAX = 12;
    private static final int ITEM_MIN = 1;
    private static final int ITEM_OIL_UNIT = 11;
    private static final int ITEM_TAILDOOR_ANGLE = 12;
    private static final int ITEM_TEMP_UNIT = 5;
    private static final int ITEM_TIME_UNIT = 6;
    private static final int ITEM_YBD = 2;
    private static final int ITEM_YBPBG = 10;
    private static final int ITEM_ZDHSJ = 3;
    private static String[] mDgkzArray;
    private static String[] mDisArray;
    private static int[] mLanguageArray = {R.string.can_lang_cn, R.string.can_lang_en};
    private static int[] mOilArray = {R.string.can_fuels_lkm, R.string.can_fuels_mpg, R.string.can_fuels_kml};
    private static String[] mTailDoorAngleArray = {"70%", "85%", "100%"};
    private static String[] mTempArray = {"°C", "°F"};
    private static int[] mTimeArray = {R.string.can12_hours, R.string.can24_hours};
    private CanItemProgressList mItemDdwm;
    private CanItemPopupList mItemDgkzBwhj;
    private CanItemProgressList mItemDgsjkzBwhj;
    private CanItemPopupList mItemDisUnit;
    private CanItemPopupList mItemLanguage;
    private CanItemPopupList mItemOilUnit;
    private CanItemPopupList mItemTailDoorAngle;
    private CanItemPopupList mItemTempUnit;
    private CanItemPopupList mItemTimeUnit;
    private CanItemSwitchList mItemYbd;
    private CanItemProgressList mItemYbpbg;
    private CanItemSwitchList mItemZdhsj;
    private CanDataInfo.QorosBnrSet mSetData = new CanDataInfo.QorosBnrSet();

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        mDisArray = getResources().getStringArray(R.array.can_fist_l_c);
        mDgkzArray = getResources().getStringArray(R.array.can_dgkz);
        CanScrollList manager = new CanScrollList(this);
        this.mItemLanguage = manager.addItemPopupList(R.string.can_language, mLanguageArray, 1, (CanItemPopupList.onPopItemClick) this);
        this.mItemYbd = manager.addItemCheckBox(R.string.can_yb_light, 2, this);
        this.mItemZdhsj = manager.addItemCheckBox(R.string.can_zdhsjzd, 3, this);
        this.mItemDisUnit = manager.addItemPopupList(R.string.can_lcdw, mDisArray, 4, (CanItemPopupList.onPopItemClick) this);
        this.mItemTempUnit = manager.addItemPopupList(R.string.can_temperature, mTempArray, 5, (CanItemPopupList.onPopItemClick) this);
        this.mItemTimeUnit = manager.addItemPopupList(R.string.can_time, mTimeArray, 6, (CanItemPopupList.onPopItemClick) this);
        this.mItemDgsjkzBwhj = manager.addItemProgressList(R.string.can_dgsjkz_bwhj, 8, (CanItemProgressList.onPosChange) this);
        this.mItemDgsjkzBwhj.SetStep(1);
        this.mItemDgsjkzBwhj.SetMinMax(0, 12);
        this.mItemDgsjkzBwhj.SetUserValText();
        this.mItemYbpbg = manager.addItemProgressList(R.string.can_ybpbg, 10, (CanItemProgressList.onPosChange) this);
        this.mItemYbpbg.SetStep(1);
        this.mItemYbpbg.SetMinMax(0, 10);
        this.mItemOilUnit = manager.addItemPopupList(R.string.can_fuel_d_w, mOilArray, 11, (CanItemPopupList.onPopItemClick) this);
        this.mItemTailDoorAngle = manager.addItemPopupList(R.string.can_ddwm_zdkqjd, mTailDoorAngleArray, 12, (CanItemPopupList.onPopItemClick) this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.QorosBnrGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemZdhsj.SetCheck(this.mSetData.Zdhsjzd);
            this.mItemYbd.SetCheck(this.mSetData.Ybd);
            this.mItemDgsjkzBwhj.SetCurVal(this.mSetData.Bwhjdcxsj);
            this.mItemDgsjkzBwhj.SetValText(String.valueOf(this.mSetData.Bwhjdcxsj * 5) + "s");
            this.mItemYbpbg.SetCurVal(this.mSetData.Ybbg);
            this.mItemLanguage.SetSel(this.mSetData.Lang);
            this.mItemDisUnit.SetSel(this.mSetData.Jldw);
            this.mItemTimeUnit.SetSel(this.mSetData.Sjzs);
            setSystemTime(this.mSetData.Sjzs);
            this.mItemTempUnit.SetSel(this.mSetData.Wddw);
            this.mItemOilUnit.SetSel(this.mSetData.Yhdw);
        }
    }

    private void setSystemTime(int sjzs) {
        String time = "24";
        if (sjzs == 0) {
            time = "12";
        }
        Settings.System.putString(getContentResolver(), "time_12_24", time);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 12; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = 1;
                break;
            case 2:
                ret = 1;
                break;
            case 3:
                ret = 1;
                break;
            case 4:
                ret = 1;
                break;
            case 5:
                ret = 1;
                break;
            case 6:
                ret = 1;
                break;
            case 7:
                ret = 1;
                break;
            case 9:
                ret = 1;
                break;
            case 10:
                ret = 1;
                break;
            case 11:
                ret = 1;
                break;
            case 12:
                if (CanJni.GetSubType() != 0) {
                    ret = 1;
                    break;
                }
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemLanguage.ShowGone(show);
                return;
            case 2:
                this.mItemYbd.ShowGone(show);
                return;
            case 3:
                this.mItemZdhsj.ShowGone(show);
                return;
            case 4:
                this.mItemDisUnit.ShowGone(show);
                return;
            case 5:
                this.mItemTempUnit.ShowGone(show);
                return;
            case 6:
                this.mItemTimeUnit.ShowGone(show);
                return;
            case 10:
                this.mItemYbpbg.ShowGone(show);
                return;
            case 11:
                this.mItemOilUnit.ShowGone(show);
                return;
            case 12:
                this.mItemTailDoorAngle.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.QorosBnrQuery(50, 0);
        LayoutUI();
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 2) {
            CanJni.QorosBnrCarSet(2, Neg(this.mSetData.Ybd));
        } else if (id == 3) {
            CanJni.QorosBnrCarSet(0, Neg(this.mSetData.Zdhsjzd));
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.QorosBnrCarSet(4, item);
                return;
            case 4:
                CanJni.QorosBnrCarSet(5, item);
                return;
            case 5:
                CanJni.QorosBnrCarSet(7, item);
                return;
            case 6:
                CanJni.QorosBnrCarSet(6, item);
                return;
            case 11:
                CanJni.QorosBnrCarSet(8, item);
                return;
            case 12:
                if (item == 0) {
                    CanJni.QorosBnrCarSet(10, 182);
                    return;
                } else if (item == 1) {
                    CanJni.QorosBnrCarSet(10, 215);
                    return;
                } else if (item == 2) {
                    CanJni.QorosBnrCarSet(10, Can.CAN_FORD_ESCORT_LY);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 8:
                CanJni.QorosBnrCarSet(1, pos);
                return;
            case 10:
                CanJni.QorosBnrCarSet(3, pos);
                return;
            default:
                return;
        }
    }
}
