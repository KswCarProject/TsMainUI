package com.ts.can.obd;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemIcoVal;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanObdCarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_DISTANCE = 3;
    public static final int ITEM_FULE = 4;
    public static final int ITEM_FUNCTION = 2;
    private static final int ITEM_MAX = 7;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_OBD_STA = 1;
    public static final int ITEM_OTHER = 7;
    public static final int ITEM_SPEED = 5;
    public static final int ITEM_TEMP = 6;
    public static final String TAG = "CanObdCarInfoActivity";
    private String[] ObdStaArr = {"正常", "OBD总线不通", "主机通信异常"};
    private CanItemIcoList mItemDistance;
    private CanItemIcoList mItemFule;
    private CanItemIcoList mItemFunction;
    private CanItemIcoVal mItemObdSta;
    private CanItemIcoList mItemOther;
    private CanItemIcoList mItemSpeed;
    private CanItemIcoList mItemTemp;
    private CanScrollList mManager;
    private CanDataInfo.Obd_Adt m_AdtDate = new CanDataInfo.Obd_Adt();
    private CanDataInfo.Obd_Sta m_SetDate = new CanDataInfo.Obd_Sta();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.CanObdGetSta(this.m_SetDate);
        if (!i2b(this.m_SetDate.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetDate.Update)) {
            this.m_SetDate.Update = 0;
            this.mItemObdSta.SetVal(this.ObdStaArr[this.m_SetDate.Sta]);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.CanObdQuery(12);
        Sleep(10);
        CanJni.CanObdQuery(126);
        Sleep(10);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
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
        this.mItemObdSta = AddIcoValItem(R.drawable.can_icon_lock3, R.string.can_obd_sta, 1);
        this.mItemFunction = AddIcoItem(R.drawable.can_icon_light2, R.string.can_func_info, 2);
        this.mItemDistance = AddIcoItem(R.drawable.can_icon_units, R.string.can_range, 3);
        this.mItemFule = AddIcoItem(R.drawable.can_icon_mfd, R.string.can_consumption, 4);
        this.mItemSpeed = AddIcoItem(R.drawable.can_icon_sudu, R.string.can_speed_info, 5);
        this.mItemTemp = AddIcoItem(R.drawable.can_icon_light, R.string.can_temperature, 6);
        this.mItemOther = AddIcoItem(R.drawable.can_icon_setup, R.string.can_other_info, 7);
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
        CanJni.CanObdGetAdt(this.m_AdtDate);
        switch (item) {
            case 1:
                ret = 1;
                break;
            case 2:
                ret = this.m_AdtDate.Bjxx;
                break;
            case 3:
                ret = this.m_AdtDate.Distance;
                break;
            case 4:
                ret = this.m_AdtDate.Fule;
                break;
            case 5:
                ret = this.m_AdtDate.Sdxx;
                break;
            case 6:
                ret = this.m_AdtDate.Temp;
                break;
            case 7:
                ret = this.m_AdtDate.Other;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemObdSta.ShowGone(show);
                return;
            case 2:
                this.mItemFunction.ShowGone(show);
                return;
            case 3:
                this.mItemDistance.ShowGone(show);
                return;
            case 4:
                this.mItemFule.ShowGone(show);
                return;
            case 5:
                this.mItemSpeed.ShowGone(show);
                return;
            case 6:
                this.mItemTemp.ShowGone(show);
                return;
            case 7:
                this.mItemOther.ShowGone(show);
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

    /* access modifiers changed from: protected */
    public CanItemIcoList AddIcoItem(int ico, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemIcoVal AddIcoValItem(int ico, int text, int id) {
        CanItemIcoVal item = new CanItemIcoVal(this, ico, text);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                enterSubWin(CanObdCarFuncInfoActivity.class);
                return;
            case 3:
                enterSubWin(CanObdCarDistanceActivity.class);
                return;
            case 4:
                enterSubWin(CanObdCarFuleInfoActivity.class);
                return;
            case 5:
                enterSubWin(CanObdCarSpeedInfoActivity.class);
                return;
            case 6:
                enterSubWin(CanObdCarTempInfoActivity.class);
                return;
            case 7:
                enterSubWin(CanObdCarOtherInfoActivity.class);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
