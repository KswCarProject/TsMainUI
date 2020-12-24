package com.ts.can.dj.qoros;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanQorosCarInfoActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    private static final int ITEM_DDWM = 9;
    private static final int ITEM_DGKZ_BWHJ = 7;
    private static final int ITEM_DGSJKZ_BWHJ = 8;
    private static final int ITEM_DIS_UNIT = 4;
    private static final int ITEM_LANGUAGE = 1;
    private static final int ITEM_OIL_UNIT = 11;
    private static final int ITEM_TEMP_UNIT = 5;
    private static final int ITEM_TIME_UNIT = 6;
    private static final int ITEM_YBD = 2;
    private static final int ITEM_YBPBG = 10;
    private static final int ITEM_ZDHSJ = 3;
    private static String[] mDgkzArray;
    private static String[] mDisArray;
    private static int[] mLanguageArray = {R.string.can_lang_cn, R.string.can_lang_en};
    private static int[] mOilArray = {R.string.can_fuels_lkm, R.string.can_fuels_mpg, R.string.can_fuels_kml};
    private static String[] mTempArray = {"°C", "°F"};
    private static int[] mTimeArray = {R.string.can24_hours, R.string.can12_hours};
    private CanItemProgressList mItemDdwm;
    private CanItemPopupList mItemDgkzBwhj;
    private CanItemProgressList mItemDgsjkzBwhj;
    private CanItemPopupList mItemDisUnit;
    private CanItemPopupList mItemLanguage;
    private CanItemPopupList mItemOilUnit;
    private CanItemPopupList mItemTempUnit;
    private CanItemPopupList mItemTimeUnit;
    private CanItemSwitchList mItemYbd;
    private CanItemProgressList mItemYbpbg;
    private CanItemSwitchList mItemZdhsj;
    private CanDataInfo.QorosSet mSetData = new CanDataInfo.QorosSet();

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
        this.mItemDgkzBwhj = manager.addItemPopupList(R.string.can_dgkz_bwhj, mDgkzArray, 7, (CanItemPopupList.onPopItemClick) this);
        this.mItemDgsjkzBwhj = manager.addItemProgressList(R.string.can_dgsjkz_bwhj, 8, (CanItemProgressList.onPosChange) this);
        this.mItemDgsjkzBwhj.SetStep(1);
        this.mItemDgsjkzBwhj.SetMinMax(1, 13);
        this.mItemDgsjkzBwhj.SetUserValText();
        this.mItemDdwm = manager.addItemProgressList(R.string.can_ddwm_zdkqjd, 9, (CanItemProgressList.onPosChange) this);
        this.mItemDdwm.SetStep(1);
        this.mItemDdwm.SetMinMax(1, 12);
        this.mItemDdwm.SetUserValText();
        this.mItemDdwm.SetValText("");
        this.mItemYbpbg = manager.addItemProgressList(R.string.can_ybpbg, 10, (CanItemProgressList.onPosChange) this);
        this.mItemYbpbg.SetStep(1);
        this.mItemYbpbg.SetMinMax(1, 11);
        this.mItemOilUnit = manager.addItemPopupList(R.string.can_fuel_d_w, mOilArray, 11, (CanItemPopupList.onPopItemClick) this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.QorosDjGetCarSet(this.mSetData);
        if (i2b(this.mSetData.LangUpdateOnce) && (!check || i2b(this.mSetData.LangUpdate))) {
            this.mSetData.LangUpdate = 0;
            this.mItemLanguage.SetSel(this.mSetData.Lang - 1);
        }
        if (i2b(this.mSetData.YbdUpdateOnce) && (!check || i2b(this.mSetData.YbdUpdate))) {
            this.mSetData.YbdUpdate = 0;
            if (this.mSetData.Ybd == 1) {
                this.mItemYbd.SetCheck(true);
            } else if (this.mSetData.Ybd == 2) {
                this.mItemYbd.SetCheck(false);
            }
        }
        if (i2b(this.mSetData.ZdhsjzdUpdateOnce) && (!check || i2b(this.mSetData.ZdhsjzdUpdate))) {
            this.mSetData.ZdhsjzdUpdate = 0;
            if (this.mSetData.Zdhsjzd == 1) {
                this.mItemZdhsj.SetCheck(true);
            } else if (this.mSetData.Zdhsjzd == 2) {
                this.mItemZdhsj.SetCheck(false);
            }
        }
        if (i2b(this.mSetData.LcdwUpdateOnce) && (!check || i2b(this.mSetData.LcdwUpdate))) {
            this.mSetData.LcdwUpdate = 0;
            this.mItemDisUnit.SetSel(this.mSetData.Lcdw - 1);
        }
        if (i2b(this.mSetData.WdzsUpdateOnce) && (!check || i2b(this.mSetData.WdzsUpdate))) {
            this.mSetData.WdzsUpdate = 0;
            this.mItemTempUnit.SetSel(this.mSetData.Wdzs - 1);
        }
        if (i2b(this.mSetData.SjzsUpdateOnce) && (!check || i2b(this.mSetData.SjzsUpdate))) {
            this.mSetData.SjzsUpdate = 0;
            this.mItemTimeUnit.SetSel(this.mSetData.Sjzs - 1);
        }
        if (i2b(this.mSetData.BwhjUpdateOnce) && (!check || i2b(this.mSetData.BwhjUpdate))) {
            this.mSetData.BwhjUpdate = 0;
            this.mItemDgkzBwhj.SetSel(this.mSetData.Bwhj - 1);
        }
        if (i2b(this.mSetData.BwhjsjUpdateOnce) && (!check || i2b(this.mSetData.BwhjsjUpdate))) {
            this.mSetData.BwhjsjUpdate = 0;
            this.mItemDgsjkzBwhj.SetCurVal(this.mSetData.Bwhjsj);
            if (this.mSetData.Bwhjsj > 0) {
                this.mItemDgsjkzBwhj.SetValText(String.valueOf((this.mSetData.Bwhjsj - 1) * 5) + "s");
            } else {
                this.mItemDgsjkzBwhj.SetValText("0s");
            }
        }
        if (i2b(this.mSetData.ZdkqjdUpdateOnce) && (!check || i2b(this.mSetData.ZdkqjdUpdate))) {
            this.mSetData.ZdkqjdUpdate = 0;
            this.mItemDdwm.SetCurVal(this.mSetData.Zdkqjd);
        }
        if (i2b(this.mSetData.YbbgUpdateOnce) && (!check || i2b(this.mSetData.YbbgUpdate))) {
            this.mSetData.YbbgUpdate = 0;
            this.mItemYbpbg.SetCurVal(this.mSetData.Ybbg);
        }
        if (!i2b(this.mSetData.YhdwUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.YhdwUpdate)) {
            this.mSetData.YhdwUpdate = 0;
            this.mItemOilUnit.SetSel(this.mSetData.Yhdw - 1);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.QorosDjQuery(82);
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 2) {
            CanJni.QorosDjCarSet(2, switchValue(this.mSetData.Ybd));
        } else if (id == 3) {
            CanJni.QorosDjCarSet(3, switchValue(this.mSetData.Zdhsjzd));
        }
    }

    private int switchValue(int val) {
        if (val == 1) {
            return 2;
        }
        if (val == 2) {
            return 1;
        }
        return 0;
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.QorosDjCarSet(1, item + 1);
                return;
            case 4:
                CanJni.QorosDjCarSet(4, item + 1);
                return;
            case 5:
                CanJni.QorosDjCarSet(5, item + 1);
                return;
            case 6:
                CanJni.QorosDjCarSet(6, item + 1);
                return;
            case 7:
                CanJni.QorosDjCarSet(7, item + 1);
                return;
            case 11:
                CanJni.QorosDjCarSet(11, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 8:
                CanJni.QorosDjCarSet(8, pos);
                return;
            case 9:
                CanJni.QorosDjCarSet(9, pos);
                return;
            case 10:
                CanJni.QorosDjCarSet(10, pos);
                return;
            default:
                return;
        }
    }
}
