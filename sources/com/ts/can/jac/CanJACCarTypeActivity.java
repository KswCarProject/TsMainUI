package com.ts.can.jac;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanJACCarTypeActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CARINFO = 4;
    public static final int ITEM_DSCDSZ = 5;
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SETTINGS = 3;
    public static final int ITEM_TPMS = 2;
    public static final int ITEM_TYPE = 1;
    public static final String TAG = "CanJACCarTypeActivity";
    private CanItemTextBtnList mItemCarInfo;
    private CanItemCarType mItemCarType;
    private CanItemTextBtnList mItemDSCDSZ;
    private CanItemTextBtnList mItemSettings;
    private CanItemTextBtnList mItemTpms;
    private CanScrollList mManager;
    private String[] mTypeArr;
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
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
        LayoutUI();
        Log.d(TAG, "subtype = " + CanJni.GetSubType());
        this.mItemCarType.GetPopItem().SetSel(CanJni.GetSubType());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mTypeArr = getResources().getStringArray(R.array.can_fs_declare_27);
        this.mItemCarType = new CanItemCarType((Context) this, R.string.can_car_type_select, this.mTypeArr, 1, (CanItemPopupList.onPopItemClick) this);
        this.mManager.AddView(this.mItemCarType.GetView());
        this.mItemTpms = new CanItemTextBtnList((Context) this, R.string.can_tyres_tpms);
        this.mItemTpms.SetIdClickListener(this, 2);
        this.mManager.AddView(this.mItemTpms.GetView());
        this.mItemSettings = new CanItemTextBtnList((Context) this, R.string.can_car_set);
        this.mItemSettings.SetIdClickListener(this, 3);
        this.mManager.AddView(this.mItemSettings.GetView());
        this.mItemCarInfo = new CanItemTextBtnList((Context) this, R.string.can_info_title);
        this.mItemCarInfo.SetIdClickListener(this, 4);
        this.mManager.AddView(this.mItemCarInfo.GetView());
        this.mItemDSCDSZ = new CanItemTextBtnList((Context) this, R.string.can_dscdsz);
        this.mItemDSCDSZ.SetIdClickListener(this, 5);
        this.mManager.AddView(this.mItemDSCDSZ.GetView());
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 5; i++) {
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
                if (CanJni.GetSubType() == 3 || CanJni.GetSubType() == 5) {
                    ret = 1;
                    break;
                }
            case 5:
                if (CanJni.GetSubType() == 3 || CanJni.GetSubType() == 5) {
                    ret = 1;
                    break;
                }
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 2:
                this.mItemTpms.ShowGone(show);
                return;
            case 3:
                this.mItemSettings.ShowGone(show);
                return;
            case 4:
                this.mItemCarInfo.ShowGone(show);
                return;
            case 5:
                this.mItemDSCDSZ.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemTitleValList AddItemTitleVal(int text, int id) {
        CanItemTitleValList item = new CanItemTitleValList(this, text);
        item.SetIdClickListener(this, id);
        item.SetIconVisibility(0);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        item.SetIconVisibility(8);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                enterSubWin(CanJACRefineTpmsActivity.class);
                return;
            case 3:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 2);
                return;
            case 4:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 3);
                return;
            case 5:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 4);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            Log.d(TAG, "Select = " + item);
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }
}
