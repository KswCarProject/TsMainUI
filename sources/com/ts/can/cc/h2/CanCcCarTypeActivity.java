package com.ts.can.cc.h2;

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
import com.ts.can.cc.h6_rzc.CanCCH6RzcCarInfoActivity;
import com.ts.can.cc.h6_rzc.CanCCH9RzcAmpSetActivity;
import com.ts.can.cc.h6_rzc.CanCCH9RzcCarInfoActivity;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanCcCarTypeActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_AMP_SET = 4;
    public static final int ITEM_CARCHAIR_SET = 5;
    public static final int ITEM_CARSTATUS_SET = 6;
    public static final int ITEM_CAR_INFO = 3;
    public static final int ITEM_CAR_SET = 2;
    private static final int ITEM_MAX = 6;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_TYPE = 1;
    public static final String TAG = "CanCcCarTypeActivity";
    private String[] mCarTypeArray;
    private CanItemTextBtnList mItemAmpSet;
    private CanItemTextBtnList mItemCarChairSet;
    private CanItemTextBtnList mItemCarInfo;
    private CanItemTextBtnList mItemCarSet;
    private CanItemTextBtnList mItemCarStatusSet;
    private CanItemCarType mItemCarType;
    private CanScrollList mManager;
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
        this.mCarTypeArray = getResources().getStringArray(R.array.can_fs_declare_78);
        this.mItemCarType = new CanItemCarType((Context) this, R.string.can_car_type_select, this.mCarTypeArray, 1, (CanItemPopupList.onPopItemClick) this);
        this.mManager.AddView(this.mItemCarType.GetView());
        this.mItemCarSet = new CanItemTextBtnList((Context) this, R.string.can_mzd_cx4_setup);
        this.mItemCarSet.SetIdClickListener(this, 2);
        this.mManager.AddView(this.mItemCarSet.GetView());
        this.mItemCarInfo = new CanItemTextBtnList((Context) this, R.string.can_car_info);
        this.mItemCarInfo.SetIdClickListener(this, 3);
        this.mManager.AddView(this.mItemCarInfo.GetView());
        this.mItemAmpSet = new CanItemTextBtnList((Context) this, R.string.can_amp_set);
        this.mItemAmpSet.SetIdClickListener(this, 4);
        this.mManager.AddView(this.mItemAmpSet.GetView());
        this.mItemCarChairSet = new CanItemTextBtnList((Context) this, R.string.can_chair_set);
        this.mItemCarChairSet.SetIdClickListener(this, 5);
        this.mManager.AddView(this.mItemCarChairSet.GetView());
        this.mItemCarStatusSet = new CanItemTextBtnList((Context) this, R.string.can_seat_status);
        this.mItemCarStatusSet.SetIdClickListener(this, 6);
        this.mManager.AddView(this.mItemCarStatusSet.GetView());
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 6; i++) {
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
                if (CanJni.GetSubType() == 4 || CanJni.GetSubType() == 6 || CanJni.GetSubType() == 5 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9 || CanJni.GetSubType() == 10 || CanJni.GetSubType() == 11) {
                    ret = 1;
                    break;
                }
            case 3:
                if (CanJni.GetSubType() == 8 || CanJni.GetSubType() == 3 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 10 || CanJni.GetSubType() == 11) {
                    ret = 1;
                    break;
                }
            case 4:
                if (CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9) {
                    ret = 1;
                    break;
                }
            case 5:
                if (CanJni.GetSubType() == 10 || CanJni.GetSubType() == 8) {
                    ret = 1;
                    break;
                }
            case 6:
                if (CanJni.GetSubType() == 10 || CanJni.GetSubType() == 8) {
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
                this.mItemCarSet.ShowGone(show);
                return;
            case 3:
                this.mItemCarInfo.ShowGone(show);
                return;
            case 4:
                this.mItemAmpSet.ShowGone(show);
                return;
            case 5:
                this.mItemCarChairSet.ShowGone(show);
                return;
            case 6:
                this.mItemCarStatusSet.ShowGone(show);
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
                enterSubWin(CanCCH6RzcCarInfoActivity.class);
                return;
            case 3:
                enterSubWin(CanCCH9RzcCarInfoActivity.class);
                return;
            case 4:
                enterSubWin(CanCCH9RzcAmpSetActivity.class);
                return;
            case 5:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, -1);
                return;
            case 6:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, -2);
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
