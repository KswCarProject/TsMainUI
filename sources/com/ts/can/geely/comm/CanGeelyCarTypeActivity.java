package com.ts.can.geely.comm;

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
import com.ts.can.geely.yjx1.CanGeelyYjX1CarInfoActivity;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanGeelyCarTypeActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_AVM = 5;
    public static final int ITEM_CAR_INFO = 7;
    public static final int ITEM_CAR_SET = 2;
    private static final int ITEM_MAX = 7;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_MIX = 6;
    public static final int ITEM_PM25 = 3;
    public static final int ITEM_TPMS = 4;
    public static final int ITEM_TYPE = 1;
    public static final String TAG = "CanGeelyCarTypeActivity";
    private CanItemTextBtnList mItemAvm;
    private CanItemTextBtnList mItemCarInfo;
    private CanItemTextBtnList mItemCarSet;
    private CanItemCarType mItemCarType;
    private CanItemTextBtnList mItemMix;
    private CanItemTextBtnList mItemPM25;
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
        Log.d(TAG, "subtype = " + CanJni.GetSubType());
        this.mItemCarType.GetPopItem().SetSel(CanJni.GetSubType());
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mTypeArr = getResources().getStringArray(R.array.can_fs_declare_72);
        this.mItemCarType = new CanItemCarType((Context) this, R.string.can_car_type_select, this.mTypeArr, 1, (CanItemPopupList.onPopItemClick) this);
        this.mManager.AddView(this.mItemCarType.GetView());
        this.mItemCarSet = AddTextBtn(R.string.can_car_set, 2);
        this.mItemPM25 = AddTextBtn(R.string.can_pm_25, 3);
        this.mItemTpms = AddTextBtn(R.string.can_tmps, 4);
        this.mItemAvm = AddTextBtn(R.string.can_honda_qjyxxtsz, 5);
        this.mItemMix = AddTextBtn(R.string.can_car_set, 6);
        this.mItemCarInfo = AddTextBtn(R.string.can_car_info, 7);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 7; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
            case 7:
                ret = 1;
                break;
            case 2:
                if (2 == CanJni.GetSubType() || 5 == CanJni.GetSubType() || 1 == CanJni.GetSubType() || 3 == CanJni.GetSubType() || 4 == CanJni.GetSubType() || 9 == CanJni.GetSubType() || 10 == CanJni.GetSubType() || 11 == CanJni.GetSubType() || 12 == CanJni.GetSubType() || 14 == CanJni.GetSubType() || 15 == CanJni.GetSubType() || 16 == CanJni.GetSubType() || 17 == CanJni.GetSubType() || 18 == CanJni.GetSubType() || 19 == CanJni.GetSubType() || 20 == CanJni.GetSubType() || 22 == CanJni.GetSubType() || 23 == CanJni.GetSubType()) {
                    ret = 1;
                    break;
                }
            case 3:
                if (4 == CanJni.GetSubType() || 16 == CanJni.GetSubType()) {
                    ret = 1;
                    break;
                }
            case 4:
                if (6 == CanJni.GetSubType() || 17 == CanJni.GetSubType()) {
                    ret = 1;
                    break;
                }
            case 5:
                if (10 == CanJni.GetSubType()) {
                    ret = 1;
                    break;
                }
                break;
            case 6:
                if (13 == CanJni.GetSubType()) {
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
            case 2:
                this.mItemCarSet.ShowGone(show);
                return;
            case 3:
                this.mItemPM25.ShowGone(show);
                return;
            case 4:
                this.mItemTpms.ShowGone(show);
                return;
            case 5:
                this.mItemAvm.ShowGone(show);
                return;
            case 6:
                this.mItemMix.ShowGone(show);
                return;
            case 7:
                this.mItemCarInfo.ShowGone(show);
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
        item.ShowGone(false);
        return item;
    }

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        btn.ShowGone(false);
        return btn;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                enterSubWin(CanGeelyYjX1CarInfoActivity.class);
                return;
            case 3:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 0);
                return;
            case 4:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 1);
                return;
            case 5:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 2);
                return;
            case 6:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 3);
                return;
            case 7:
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
