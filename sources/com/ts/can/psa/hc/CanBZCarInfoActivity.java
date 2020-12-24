package com.ts.can.psa.hc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanBZCarInfoActivity extends CanBZBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_CAR_SET = 2;
    public static final int ITEM_CAR_TYPE = 1;
    public static final int ITEM_CHECK_INFO = 4;
    public static final int ITEM_CRUISE_SPEED = 7;
    public static final int ITEM_DRIVE_INFO = 3;
    public static final int ITEM_FUNC_INFO = 6;
    public static final int ITEM_KEY_SET = 10;
    private static final int ITEM_MAX = 10;
    public static final int ITEM_MEM_TAB = 9;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SPEED_LIMIT = 8;
    public static final int ITEM_WARN_INFO = 5;
    public static final String TAG = "CanBZCarInfoActivity";
    private CanDataInfo.PeugAdt mAdtData = new CanDataInfo.PeugAdt();
    private CanItemTextBtnList mItemCarSet;
    private CanItemTextBtnList mItemCarType;
    private CanItemTextBtnList mItemCheckInfo;
    private CanItemTextBtnList mItemCruiseSpeed;
    private CanItemTextBtnList mItemDriveInfo;
    private CanItemTextBtnList mItemFuncInfo;
    private CanItemTextBtnList mItemKeySet;
    private CanItemTextBtnList mItemMemTab;
    private CanItemTextBtnList mItemSpeedLimit;
    private CanItemTextBtnList mItemWarnInfo;
    private CanDataInfo.PeugLogConfig mLogCfgData = new CanDataInfo.PeugLogConfig();
    private CanScrollList mManager;
    private boolean mbLayout;
    private boolean mbSaveScr;

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
        this.mbSaveScr = CanJni.GetSubType() == 5;
        CanJni.PSAGetLogCfg(this.mLogCfgData);
        this.mItemCarType = AddTextBtn(R.string.can_car_type_select, 1);
        this.mItemCarSet = AddTextBtn(R.string.can_car_set, 2);
        this.mItemDriveInfo = AddTextBtn(R.string.can_xc_info, 3);
        this.mItemCheckInfo = AddTextBtn(R.string.can_check_info, 4);
        this.mItemWarnInfo = AddTextBtn(R.string.can_warn_info, 5);
        this.mItemFuncInfo = AddTextBtn(R.string.can_func_info, 6);
        this.mItemCruiseSpeed = AddTextBtn(R.string.can_xh_speed, 7);
        this.mItemSpeedLimit = AddTextBtn(R.string.can_speed_limit, 8);
        this.mItemMemTab = AddTextBtn(R.string.can_c4_l_mem_tab, 9);
        this.mItemKeySet = AddTextBtn(R.string.can_key_set, 10);
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
        for (int i = 1; i <= 10; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        CanJni.BZGetLogCfg(this.mLogCfgData);
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
                ret = this.mLogCfgData.fgCheck;
                break;
            case 5:
                ret = this.mLogCfgData.fgWarn;
                break;
            case 6:
                ret = this.mLogCfgData.fgFunc;
                break;
            case 7:
                ret = this.mAdtData.Xhxz;
                break;
            case 8:
                ret = this.mAdtData.Xhxz;
                break;
            case 9:
                if (CanJni.GetSubType() != 4) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 10:
                ret = 0;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemCarType.ShowGone(show);
                return;
            case 2:
                this.mItemCarSet.ShowGone(show);
                return;
            case 3:
                this.mItemDriveInfo.ShowGone(show);
                return;
            case 4:
                this.mItemCheckInfo.ShowGone(show);
                return;
            case 5:
                this.mItemWarnInfo.ShowGone(show);
                return;
            case 6:
                this.mItemFuncInfo.ShowGone(show);
                return;
            case 7:
                this.mItemCruiseSpeed.ShowGone(show);
                return;
            case 8:
                this.mItemSpeedLimit.ShowGone(show);
                return;
            case 9:
                this.mItemMemTab.ShowGone(show);
                return;
            case 10:
                this.mItemKeySet.ShowGone(show);
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
                enterSubWin(CanBZCarTypeActivity.class);
                return;
            case 2:
                enterSubWin(CanBZCarSetActivity.class);
                return;
            case 3:
                enterSubWin(CanBZDriveInfoActivity.class);
                return;
            case 4:
                enterSubWin(CanBZCheckInfoActivity.class);
                return;
            case 5:
                enterSubWin(CanBZWarnInfoActivity.class);
                return;
            case 6:
                enterSubWin(CanBZFuncInfoActivity.class);
                return;
            case 9:
                enterSubWin(CanBZMemTabActivity.class);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
