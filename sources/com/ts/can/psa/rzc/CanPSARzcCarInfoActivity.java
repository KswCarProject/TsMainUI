package com.ts.can.psa.rzc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanPSARzcCarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_AIR = 11;
    public static final int ITEM_AMP = 10;
    public static final int ITEM_CAR_SET = 1;
    public static final int ITEM_CHECK_INFO = 3;
    public static final int ITEM_CRUISE_SPEED = 6;
    public static final int ITEM_DRIVE_INFO = 2;
    public static final int ITEM_FUNC_INFO = 5;
    public static final int ITEM_LANG = 9;
    private static final int ITEM_MAX = 11;
    public static final int ITEM_MEM_TAB = 8;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SPEED_LIMIT = 7;
    public static final int ITEM_WARN_INFO = 4;
    public static final String TAG = "CanPSARzcCarInfoActivity";
    private CanItemTextBtnList mItemAirInfo;
    private CanItemTextBtnList mItemAmpInfo;
    private CanItemTextBtnList mItemCarSet;
    private CanItemTextBtnList mItemCheckInfo;
    private CanItemTextBtnList mItemCruiseSpeed;
    private CanItemTextBtnList mItemDriveInfo;
    private CanItemTextBtnList mItemFuncInfo;
    private CanItemTextBtnList mItemLang;
    private CanItemTextBtnList mItemMemTab;
    private CanItemTextBtnList mItemSpeedLimit;
    private CanItemTextBtnList mItemWarnInfo;
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
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemCarSet = AddTextBtn(R.string.can_car_set, 1);
        this.mItemDriveInfo = AddTextBtn(R.string.can_xc_info, 2);
        this.mItemCheckInfo = AddTextBtn(R.string.can_check_info, 3);
        this.mItemWarnInfo = AddTextBtn(R.string.can_warn_info, 4);
        this.mItemFuncInfo = AddTextBtn(R.string.can_func_info, 5);
        this.mItemCruiseSpeed = AddTextBtn(R.string.can_xh_speed, 6);
        this.mItemSpeedLimit = AddTextBtn(R.string.can_speed_limit, 7);
        this.mItemMemTab = AddTextBtn(R.string.can_c4_l_mem_tab, 8);
        this.mItemLang = AddTextBtn(R.string.can_psa_wc_lang_settings_info, 9);
        this.mItemAmpInfo = AddTextBtn(R.string.can_amp_set, 10);
        this.mItemAirInfo = AddTextBtn(R.string.can_ac_set, 11);
        LayoutUI();
    }

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        btn.ShowGone(false);
        return btn;
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 11; i++) {
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
            case 8:
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
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemCarSet.ShowGone(show);
                return;
            case 2:
                this.mItemDriveInfo.ShowGone(show);
                return;
            case 3:
                this.mItemCheckInfo.ShowGone(show);
                return;
            case 4:
                this.mItemWarnInfo.ShowGone(show);
                return;
            case 5:
                this.mItemFuncInfo.ShowGone(show);
                return;
            case 6:
                this.mItemCruiseSpeed.ShowGone(show);
                return;
            case 7:
                this.mItemSpeedLimit.ShowGone(show);
                return;
            case 8:
                this.mItemMemTab.ShowGone(show);
                return;
            case 9:
                this.mItemLang.ShowGone(show);
                return;
            case 10:
                this.mItemAmpInfo.ShowGone(show);
                return;
            case 11:
                this.mItemAirInfo.ShowGone(show);
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

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                enterSubWin(CanPSARzcCarSetActivity.class);
                return;
            case 2:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 1);
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
            case 6:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 5);
                return;
            case 7:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 6);
                return;
            case 8:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 7);
                return;
            case 9:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 8);
                return;
            case 10:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 9);
                return;
            case 11:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 10);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
