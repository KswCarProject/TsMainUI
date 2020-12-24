package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollList;

public class CanGolfSetChairActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    private static final int ITEM_DRIVE_CHAIR = 1;
    private static final int ITEM_KEY_ACTIVE = 0;
    private static final int ITEM_LT_CHAIR_WIND = 2;
    private static final int ITEM_RT_CHAIR_WIND = 3;
    private CanDataInfo.GolfACData mACData = new CanDataInfo.GolfACData();
    private CanDataInfo.GolfChair mGolfChair = new CanDataInfo.GolfChair();
    private CanItemCheckList mItemDriveChair;
    private CanItemCheckList mItemKeyActive;
    private CanItemProgressList mItemLtChairWind;
    private CanItemProgressList mItemRtChairWind;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemKeyActive = new CanItemCheckList(this, R.string.can_magoten_key_active);
        this.mItemDriveChair = new CanItemCheckList(this, R.string.can_jsyzy);
        this.mItemLtChairWind = new CanItemProgressList((Context) this, R.string.can_lt_wind);
        this.mItemLtChairWind.SetIdCallBack(2, this);
        this.mItemLtChairWind.SetMinMax(0, 3);
        this.mItemRtChairWind = new CanItemProgressList((Context) this, R.string.can_rt_wind);
        this.mItemRtChairWind.SetIdCallBack(3, this);
        this.mItemRtChairWind.SetMinMax(0, 3);
        this.mItemKeyActive.SetIdClickListener(this, 0);
        this.mItemDriveChair.SetIdClickListener(this, 1);
        this.mManager.AddView(this.mItemKeyActive.GetView());
        if (2 == CanJni.GetCanFsTp()) {
            this.mManager.AddView(this.mItemDriveChair.GetView());
            this.mManager.AddView(this.mItemLtChairWind.GetView());
            this.mManager.AddView(this.mItemRtChairWind.GetView());
        }
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int resId, int Id, int[] arry) {
        CanItemPopupList item = new CanItemPopupList((Context) this, resId, arry, (CanItemPopupList.onPopItemClick) this);
        item.SetId(Id);
        return item;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Query();
    }

    private void ResetData(boolean check) {
        CanJni.MagotenGetSeat(this.mGolfChair);
        if (i2b(this.mGolfChair.UpdateOnce) && (!check || i2b(this.mGolfChair.Update))) {
            this.mGolfChair.Update = 0;
            this.mItemKeyActive.SetCheck(this.mGolfChair.KeyActive);
            this.mItemDriveChair.SetCheck(this.mGolfChair.Jsyzy);
        }
        CanJni.GolfGetACData(this.mACData);
        if (!i2b(this.mACData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mACData.Update)) {
            this.mACData.Update = 0;
            this.mItemLtChairWind.SetCurVal(this.mACData.nLtChairWind);
            this.mItemRtChairWind.SetCurVal(this.mACData.nRtChairWind);
        }
    }

    private void Query() {
        CanJni.GolfQuery(64, 176);
        Sleep(10);
        CanJni.GolfQuery(33, 0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.GolfSendCmd(202, Neg(this.mGolfChair.KeyActive));
                return;
            case 1:
                CanJni.GolfSendCmd(201, Neg(this.mGolfChair.Jsyzy));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 2:
                CanJni.GolfSendCmd(191, pos);
                return;
            case 3:
                CanJni.GolfSendCmd(192, pos);
                return;
            default:
                return;
        }
    }
}
