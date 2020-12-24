package com.ts.can.vw.touareg;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanTouaregSetParkActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemProgressList.onPosChange {
    private static final int ITEM_ACTIVE = 1;
    private static final int ITEM_HYD = 5;
    private static final int ITEM_HYL = 4;
    private static final int ITEM_QPYD = 3;
    private static final int ITEM_QPYL = 2;
    private CanItemSwitchList mItemActive;
    private CanItemProgressList mItemHyd;
    private CanItemProgressList mItemHyl;
    private CanItemProgressList mItemQpyd;
    private CanItemProgressList mItemQpyl;
    private CanScrollList mManager;
    private CanDataInfo.TouaregWcParkSet mParkData = new CanDataInfo.TouaregWcParkSet();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemActive = this.mManager.addItemCheckBox(R.string.can_active_auto, 1, this);
        this.mItemQpyl = this.mManager.addItemProgressList(R.string.can_front_vol, 2, (CanItemProgressList.onPosChange) this);
        this.mItemQpyl.SetMinMax(1, 9);
        this.mItemQpyd = this.mManager.addItemProgressList(R.string.can_front_tone, 3, (CanItemProgressList.onPosChange) this);
        this.mItemQpyd.SetMinMax(1, 9);
        this.mItemHyl = this.mManager.addItemProgressList(R.string.can_rear_vol, 4, (CanItemProgressList.onPosChange) this);
        this.mItemHyl.SetMinMax(1, 9);
        this.mItemHyd = this.mManager.addItemProgressList(R.string.can_rear_tone, 5, (CanItemProgressList.onPosChange) this);
        this.mItemHyd.SetMinMax(1, 9);
        this.mItemActive.ShowGone(false);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        CanJni.TouaregQuery(5, 1, 113);
    }

    private void ResetData(boolean check) {
        CanJni.TouaregGetParkSet(this.mParkData);
        if (!i2b(this.mParkData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mParkData.Update)) {
            this.mParkData.Update = 0;
            this.mItemActive.ShowGone(this.mParkData.ActiveAvalid);
            this.mItemActive.SetCheck(this.mParkData.Active);
            this.mItemQpyl.SetCurVal(this.mParkData.Qpyl);
            this.mItemQpyd.SetCurVal(this.mParkData.Qpyd);
            this.mItemHyl.SetCurVal(this.mParkData.Hyl);
            this.mItemHyd.SetCurVal(this.mParkData.Hyd);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void onClick(View v) {
        if (((Integer) v.getTag()).intValue() == 1) {
            CanJni.TouaregParkSet(5, Neg(this.mParkData.Active));
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 2:
                CanJni.TouaregParkSet(1, pos);
                return;
            case 3:
                CanJni.TouaregParkSet(2, pos);
                return;
            case 4:
                CanJni.TouaregParkSet(3, pos);
                return;
            case 5:
                CanJni.TouaregParkSet(4, pos);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
