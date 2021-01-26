package com.ts.can.bmw.e90;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;

public class CanE90SetUnitsActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CONSUMP = 4;
    public static final int ITEM_LANG = 5;
    public static final int ITEM_LR_HOT = 6;
    private static final int ITEM_MAX = 6;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_RANGE = 3;
    public static final int ITEM_TEMP = 2;
    public static final int ITEM_TIME_FMT = 1;
    public static final String TAG = "CanE90SetUnitsActivity";
    private static int nOldLrHot = 255;
    protected String[] mConsumpArr = {"l/100km", "mpg(US)", "mpg(UK)", "km/l"};
    protected CanItemPopupList mItemConsump;
    protected CanItemPopupList mItemLang;
    protected CanItemSwitchList mItemLrHot;
    protected CanItemPopupList mItemRange;
    protected CanItemPopupList mItemTemp;
    protected CanItemPopupList mItemTimeFmt;
    protected int[] mLangArr = {R.string.lang_deutsch, R.string.lang_en_uk, R.string.lang_en_us, R.string.lang_espanol, R.string.lang_itanliano, R.string.lang_francais};
    private CanScrollList mManager;
    protected String[] mRangeArr = {"km", "mls"};
    protected CanDataInfo.BMW_Settings mSetData = new CanDataInfo.BMW_Settings();
    protected String[] mTempArr = {"℃", "℉"};
    protected int[] mTimeFmtArr = {R.string.can12_hours, R.string.can24_hours};
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.E90GetSetData(this.mSetData);
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            this.mItemTimeFmt.SetSel(this.mSetData.TimeFormat);
            this.mItemTemp.SetSel(this.mSetData.DWTemp);
            this.mItemRange.SetSel(this.mSetData.DWJl);
            this.mItemConsump.SetSel(this.mSetData.DWConsumption);
            this.mItemLang.SetSel(this.mSetData.Lang);
        }
        if (nOldLrHot != FtSet.GetCanS(1) || !check) {
            nOldLrHot = FtSet.GetCanS(1);
            this.mItemLrHot.SetCheck(nOldLrHot);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemTimeFmt = AddPopupItem(R.string.can_time_format, this.mTimeFmtArr, 1);
        this.mItemTemp = AddPopupItem(R.string.can_temperature, this.mTempArr, 2);
        this.mItemRange = AddPopupItem(R.string.can_distance_l_c, this.mRangeArr, 3);
        this.mItemConsump = AddPopupItem(R.string.can_consumption, this.mConsumpArr, 4);
        this.mItemLang = AddPopupItem(R.string.can_language, this.mLangArr, 5);
        this.mItemLrHot = AddCheckItem(R.string.can_zyjr_hx, 6);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 6; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        return i2b(0);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean IsHaveItem = IsHaveItem(item);
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, String[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 6:
                if (FtSet.GetCanS(1) > 0) {
                    FtSet.SetCanS((byte) 0, 1);
                    return;
                } else {
                    FtSet.SetCanS((byte) 1, 1);
                    return;
                }
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.E90CarSet(4, item);
                return;
            case 2:
                CanJni.E90CarSet(3, item);
                return;
            case 3:
                CanJni.E90CarSet(0, item);
                return;
            case 4:
                CanJni.E90CarSet(2, item);
                return;
            case 5:
                CanJni.E90CarSet(1, item);
                return;
            default:
                return;
        }
    }
}
