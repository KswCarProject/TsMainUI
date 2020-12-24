package com.ts.can.vw.golf.wc;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGolfWcSetACActivity extends CanBaseActivity implements CanItemPopupList.onPopItemClick, UserCallBack, View.OnClickListener {
    private static final int ITEM_AUTO_FRONT_WIN = 2;
    private static final int ITEM_AUTO_LOOPER = 3;
    private static final int ITEM_AUTO_WIND = 1;
    private static int[] mAutoWindArrays = {R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
    private CanDataInfo.GolfWcACData mAcAdt = new CanDataInfo.GolfWcACData();
    private CanDataInfo.GolfWcACData mAcData = new CanDataInfo.GolfWcACData();
    private CanItemSwitchList mItemAutoFrontWin;
    private CanItemSwitchList mItemAutoLooper;
    private CanItemPopupList mItemAutoWind;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GolfWcGetAcData(1, this.mAcAdt);
        CanJni.GolfWcGetAcData(0, this.mAcData);
        if (i2b(this.mAcAdt.UpdateOnce) && (!check || i2b(this.mAcAdt.Update))) {
            this.mAcAdt.Update = 0;
            this.mItemAutoWind.ShowGone(this.mAcAdt.AutoModeWind);
            this.mItemAutoFrontWin.ShowGone(this.mAcAdt.FrontWidAutoDefog);
            this.mItemAutoLooper.ShowGone(this.mAcAdt.AutoLoop);
        }
        if (!i2b(this.mAcData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAcData.Update)) {
            this.mAcData.Update = 0;
            this.mItemAutoWind.SetSel(this.mAcData.AutoModeWind);
            this.mItemAutoFrontWin.SetCheck(this.mAcData.FrontWidAutoDefog);
            this.mItemAutoLooper.SetCheck(this.mAcData.AutoLoop);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfWcQueryData(1, 53);
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
        CanScrollList mManager = new CanScrollList(this);
        this.mItemAutoWind = mManager.addItemPopupList(R.string.can_ac_zdfl, mAutoWindArrays, 1, (CanItemPopupList.onPopItemClick) this);
        this.mItemAutoFrontWin = mManager.addItemCheckBox(R.string.can_ac_qccw, 2, this);
        this.mItemAutoLooper = mManager.addItemCheckBox(R.string.can_auto_recirculate, 3, this);
        this.mItemAutoWind.ShowGone(false);
        this.mItemAutoFrontWin.ShowGone(false);
        this.mItemAutoLooper.ShowGone(false);
    }

    public void onItem(int Id, int item) {
        if (Id == 1) {
            CanJni.GolfWcAcSet(1, item);
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.GolfWcAcSet(10, Neg(this.mAcData.FrontWidAutoDefog));
                return;
            case 3:
                CanJni.GolfWcAcSet(14, Neg(this.mAcData.AutoLoop));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
