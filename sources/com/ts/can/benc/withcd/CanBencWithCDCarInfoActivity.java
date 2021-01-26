package com.ts.can.benc.withcd;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanCommonActivity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanScrollList;

public class CanBencWithCDCarInfoActivity extends CanCommonActivity implements View.OnTouchListener {
    public static final int ITEM_AIR = 5;
    public static final int ITEM_AUTO_BREAK = 8;
    public static final int ITEM_CAR_INIT = 6;
    public static final int ITEM_CAR_SET = 7;
    public static final int ITEM_CAR_TYPE = 0;
    public static final int ITEM_FUNC = 4;
    public static final int ITEM_IAP = 2;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_MIN = 0;
    public static final int ITEM_STATUS = 1;
    public static final int ITEM_TPMS = 3;
    private static int nDisCar = 0;
    private CanItemIcoList mItemAir;
    private CanItemIcoList mItemAutoBreak;
    private CanItemIcoList mItemCarInit;
    private CanItemIcoList mItemCarSet;
    private CanItemIcoList mItemCarType;
    private CanItemIcoList mItemFunc;
    private CanItemIcoList mItemIap;
    private CanItemIcoList mItemStatus;
    private CanItemIcoList mItemTpms;
    private CanScrollList mManager;
    private CanDataInfo.CadillacTpms mTpms = new CanDataInfo.CadillacTpms();
    private int nTouchTime = 0;

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public boolean onTouch(View arg0, MotionEvent event) {
        int id = ((Integer) arg0.getTag()).intValue();
        int action = event.getAction();
        if (action != 0) {
            if (action == 1) {
                switch (id) {
                    case 4:
                        if (this.nTouchTime > 0) {
                            this.nTouchTime = 0;
                            enterSubWin(CanBencWithCDCarFuncActivity.class);
                            break;
                        }
                        break;
                }
            }
        } else {
            switch (id) {
                case 2:
                    enterSubWin(CanBencWithCDUpdateActivity.class);
                    break;
                case 4:
                    this.nTouchTime = 150;
                    break;
                case 6:
                    enterSubWin(CanBencWithCDCarInitActivity.class);
                    break;
                case 7:
                    CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 0);
                    break;
                case 8:
                    CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 1);
                    break;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemCarType = this.mManager.addTouchItemIconList(R.drawable.can_icon_esc, R.string.can_car_type_select, 0, this);
        this.mItemStatus = this.mManager.addTouchItemIconList(R.drawable.can_icon_driver_assist, R.string.can_vehi_status, 1, this);
        this.mItemTpms = this.mManager.addTouchItemIconList(R.drawable.can_icon_tpms, R.string.can_pressure, 3, this);
        this.mItemFunc = this.mManager.addTouchItemIconList(R.drawable.can_icon_light2, R.string.can_func_chos, 4, this);
        this.mItemCarInit = this.mManager.addTouchItemIconList(R.drawable.can_icon_service, R.string.can_host_init, 6, this);
        this.mItemIap = this.mManager.addTouchItemIconList(R.drawable.can_golf_icon03, R.string.can_can_iap, 2, this);
        this.mItemAir = this.mManager.addTouchItemIconList(R.drawable.can_icon_ac, R.string.can_ac_set, 5, this);
        this.mItemCarSet = this.mManager.addTouchItemIconList(R.drawable.can_icon_setup, R.string.can_car_set, 7, this);
        this.mItemAutoBreak = this.mManager.addTouchItemIconList(R.drawable.can_icon_esc, R.string.can_auto_break, 8, this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        if (this.nTouchTime > 0) {
            this.nTouchTime--;
            if (this.nTouchTime == 0) {
                nDisCar = 1;
                this.mItemCarInit.ShowGone(true);
                this.mItemIap.ShowGone(true);
            }
        }
    }

    private void LayoutUI() {
        for (int i = 0; i <= 8; i++) {
            ShowItem(i);
        }
    }

    private void ShowItem(int item) {
        boolean show = isHaveItem(item);
        switch (item) {
            case 0:
                this.mItemCarType.ShowGone(show);
                return;
            case 1:
                this.mItemStatus.ShowGone(show);
                return;
            case 2:
                this.mItemIap.ShowGone(show);
                return;
            case 3:
                this.mItemTpms.ShowGone(show);
                return;
            case 4:
                this.mItemFunc.ShowGone(show);
                return;
            case 5:
                this.mItemAir.ShowGone(show);
                return;
            case 6:
                this.mItemCarInit.ShowGone(show);
                return;
            case 7:
                this.mItemCarSet.ShowGone(show);
                return;
            case 8:
                this.mItemAutoBreak.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        QueryData();
        ResetData(false);
        Log.d(CanBaseActivity.TAG, "-----onResume-----");
        LayoutUI();
    }

    private boolean isHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 0:
                ret = 0;
                break;
            case 1:
                ret = 0;
                break;
            case 2:
                ret = nDisCar;
                break;
            case 3:
                ret = 0;
                break;
            case 4:
                ret = 1;
                break;
            case 5:
                ret = 0;
                break;
            case 6:
                ret = nDisCar;
                break;
            case 7:
                ret = 0;
                break;
            case 8:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
