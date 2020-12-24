package com.ts.can.qoros.bnr;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;

public class CanQorosBnrCarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemMsgBox.onMsgBoxClick, CanItemMsgBox.onMsgBoxClick2, View.OnTouchListener {
    public static final int ITEM_AMP = 6;
    public static final int ITEM_CAR_SET = 2;
    public static final int ITEM_CAR_TYPE = 1;
    public static final int ITEM_IAP = 5;
    private static final int ITEM_MAX = 6;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_TPMS = 3;
    public static final int ITEM_TYBD = 4;
    public static final String TAG = "CanQorosBnrCarInfoActivity";
    private CanItemIcoList mItemAmp;
    private CanItemIcoList mItemCarSet;
    private CanItemIcoList mItemCarType;
    private CanItemIcoList mItemIap;
    private CanItemIcoList mItemTpms;
    private CanItemIcoList mItemTybd;
    private CanScrollList mManager;
    private boolean mbLayout;
    private int nTouchTime = 0;

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
        CanJni.QorosBnrQuery(50, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
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
        this.mItemCarType = AddIcoItem(R.drawable.can_icon_light2, R.string.can_car_type_select, 1);
        this.mItemCarSet = AddIcoItem(R.drawable.can_icon_carset, R.string.can_car_set, 2);
        this.mItemTpms = AddIcoItem(R.drawable.can_icon_carchk, R.string.can_car_check, 3);
        this.mItemTybd = AddIcoItem(R.drawable.can_icon_units, R.string.can_tybd, 4);
        this.mItemIap = AddIcoItem(R.drawable.can_icon_light2, R.string.can_can_iap, 5);
        this.mItemAmp = AddIcoItem(R.drawable.can_icon_light2, R.string.can_amp_set, 6);
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
                ret = 0;
                break;
            case 2:
                ret = 1;
                break;
            case 3:
                ret = 1;
                break;
            case 4:
                ret = 0;
                break;
            case 5:
                ret = 0;
                break;
            case 6:
                if (CanJni.GetSubType() == 2) {
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
                this.mItemCarType.ShowGone(show);
                return;
            case 2:
                this.mItemCarSet.ShowGone(show);
                return;
            case 3:
                this.mItemTpms.ShowGone(show);
                return;
            case 4:
                this.mItemTybd.ShowGone(show);
                return;
            case 5:
                this.mItemIap.ShowGone(show);
                return;
            case 6:
                this.mItemAmp.ShowGone(show);
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

    /* access modifiers changed from: protected */
    public CanItemIcoList AddIcoItem(int ico, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, text);
        item.SetIdTouchListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public boolean onTouch(View arg0, MotionEvent event) {
        int id = ((Integer) arg0.getTag()).intValue();
        int action = event.getAction();
        if (action == 0) {
            Log.d(TAG, "MotionEvent.ACTION_DOWN  id= " + id);
            switch (id) {
                case 1:
                    enterSubWin(CanQorosBnrCarTypeActivity.class);
                    return false;
                case 2:
                    this.nTouchTime = 200;
                    return false;
                case 3:
                    enterSubWin(CanQorosBnrTpmsActivity.class);
                    return false;
                case 4:
                    new CanItemMsgBox(4, this, R.string.can_sure_tybd, this, this);
                    return false;
                case 5:
                    enterSubWin(CanQorosBnrUpdateActivity.class);
                    return false;
                case 6:
                    enterSubWin(CanQorosBnrCarAmpInfoActivity.class);
                    return false;
                default:
                    return false;
            }
        } else if (action != 1) {
            return false;
        } else {
            switch (id) {
                case 2:
                    if (this.nTouchTime == 0) {
                        enterSubWin(CanQorosBnrUpdateActivity.class);
                        return false;
                    }
                    enterSubWin(CanQorosBnrCarSetActivity.class);
                    return false;
                default:
                    return false;
            }
        }
    }

    public void onOK(int param) {
        if (param == 4) {
            CanJni.QorosBnrCarSet(9, 1);
        }
    }

    public void onCancel(int param) {
    }

    public void UserAll() {
        ResetData(true);
        if (this.nTouchTime > 0) {
            this.nTouchTime--;
        }
    }
}
