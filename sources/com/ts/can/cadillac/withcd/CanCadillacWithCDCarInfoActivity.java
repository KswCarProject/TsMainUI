package com.ts.can.cadillac.withcd;

import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanScrollList;

public class CanCadillacWithCDCarInfoActivity extends CanCommonActivity {
    public static final int ITEM_AIR = 5;
    public static final int ITEM_CAR_TYPE = 0;
    public static final int ITEM_FUNC = 4;
    public static final int ITEM_IAP = 2;
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 0;
    public static final int ITEM_STATUS = 1;
    public static final int ITEM_TPMS = 3;
    private CanItemIcoList mItemAir;
    private CanItemIcoList mItemCarType;
    private CanItemIcoList mItemFunc;
    private CanItemIcoList mItemIap;
    private CanItemIcoList mItemStatus;
    private CanItemIcoList mItemTpms;
    private CanScrollList mManager;
    private CanDataInfo.CadillacTpms mTpms = new CanDataInfo.CadillacTpms();

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanCadillacWithCDCarTypeActivity.class);
                return;
            case 1:
                enterSubWin(CanCadillacWithCDCarStatusActivity.class);
                return;
            case 2:
                enterSubWin(CanCadillacWithCDUpdateActivity.class);
                return;
            case 3:
                enterSubWin(CanCadillacWithCDTpmsActivity.class);
                return;
            case 4:
                enterSubWin(CanCadillacWithCDCarFuncActivity.class);
                return;
            case 5:
                enterSubWin(CanCadillacWithCDCarAirSetActivity.class);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemCarType = this.mManager.addItemIconList(R.drawable.can_icon_esc, R.string.can_car_type_select, 0, this);
        this.mItemStatus = this.mManager.addItemIconList(R.drawable.can_icon_driver_assist, R.string.can_vehi_status, 1, this);
        this.mItemTpms = this.mManager.addItemIconList(R.drawable.can_icon_tpms, R.string.can_pressure, 3, this);
        this.mItemIap = this.mManager.addItemIconList(R.drawable.can_golf_icon03, R.string.can_can_iap, 2, this);
        this.mItemFunc = this.mManager.addItemIconList(R.drawable.can_icon_light2, R.string.can_func_chos, 4, this);
        this.mItemAir = this.mManager.addItemIconList(R.drawable.can_icon_ac, R.string.can_ac_set, 5, this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.CadillacWithCDGetTpms(this.mTpms);
        if (!i2b(this.mTpms.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTpms.Update)) {
            this.mTpms.Update = 0;
            LayoutUI();
        }
    }

    private void LayoutUI() {
        for (int i = 0; i <= 5; i++) {
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
                ret = 1;
                break;
            case 1:
                ret = 0;
                break;
            case 2:
                ret = 1;
                break;
            case 3:
                ret = this.mTpms.Vaild;
                break;
            case 4:
                ret = 1;
                break;
            case 5:
                if (CanJni.GetSubType() == 5 || CanJni.GetSubType() == 6) {
                    ret = 1;
                    break;
                }
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.CadillacWithCDQuery(37);
    }
}
