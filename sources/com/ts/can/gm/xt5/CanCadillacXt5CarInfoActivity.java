package com.ts.can.gm.xt5;

import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCommonActivity;
import com.ts.can.gm.ats.CanCadillacAtsTpmsActivity;
import com.ts.can.gm.sb.CanGMSBCarTypeActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanScrollList;

public class CanCadillacXt5CarInfoActivity extends CanCommonActivity {
    public static final int ITEM_CAR_TYPE = 0;
    public static final int ITEM_FUNC = 3;
    public static final int ITEM_IAP = 2;
    private static final int ITEM_MAX = 4;
    private static final int ITEM_MIN = 0;
    public static final int ITEM_STATUS = 1;
    public static final int ITEM_TPMS = 4;
    protected CanDataInfo.CAN_Msg mData = new CanDataInfo.CAN_Msg();
    private CanItemIcoList mItemCarType;
    private CanItemIcoList mItemFunc;
    private CanItemIcoList mItemIap;
    private CanItemIcoList mItemStatus;
    private CanItemIcoList mItemTpms;
    private CanScrollList mManager;

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanGMSBCarTypeActivity.class);
                return;
            case 1:
                enterSubWin(CanCadillacXt5CarStatusActivity.class);
                return;
            case 2:
                enterSubWin(CanCadillacXt5UpdateActivity.class);
                return;
            case 3:
                enterSubWin(CanCadillacXt5CarFuncActivity.class);
                return;
            case 4:
                enterSubWin(CanCadillacAtsTpmsActivity.class);
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
        this.mItemFunc = this.mManager.addItemIconList(R.drawable.can_icon_light2, R.string.can_func_chos, 3, this);
        this.mItemIap = this.mManager.addItemIconList(R.drawable.can_golf_icon03, R.string.can_can_iap, 2, this);
        this.mItemTpms = this.mManager.addItemIconList(R.drawable.can_icon_tpms, R.string.can_pressure, 4, this);
        this.mItemCarType.ShowGone(true);
        this.mItemTpms.ShowGone(false);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GetCarInfo(this.mData);
        if (!i2b(this.mData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mData.Update)) {
            this.mData.Update = 0;
            if (this.mData.Tpms[0] == 0 && this.mData.Tpms[1] == 0 && this.mData.Tpms[2] == 0 && this.mData.Tpms[3] == 0) {
                this.mItemTpms.ShowGone(false);
            } else {
                this.mItemTpms.ShowGone(true);
            }
        }
    }

    private void LayoutUI() {
        for (int i = 0; i <= 4; i++) {
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
                this.mItemFunc.ShowGone(show);
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
                if (!(CanJni.GetSubType() == 4 || CanJni.GetSubType() == 5 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 6 || CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9 || CanJni.GetSubType() == 11)) {
                    ret = 1;
                    break;
                }
            case 2:
                ret = 1;
                break;
            case 3:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
