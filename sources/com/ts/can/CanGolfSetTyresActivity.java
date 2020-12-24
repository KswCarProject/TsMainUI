package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollList;

public class CanGolfSetTyresActivity extends CanGolfBaseActivity implements View.OnClickListener, CanItemMsgBox.onMsgBoxClick, CanItemProgressList.onPosChange, UserCallBack {
    private static final int ITEM_MAX = 3;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SET_BTN = 1;
    public static final int ITEM_SPEED_WARN = 2;
    public static final int ITEM_WARN_AT = 3;
    public static final String TAG = "CanGolfSetTyresActivity";
    private CanDataInfo.GolfAdtTyres mAdtTyres = new CanDataInfo.GolfAdtTyres();
    private CanItemBlankTextList mItemSetTitle;
    private CanItemCheckList mItemSpeedWarn;
    private CanItemIcoList mItemTyresSet;
    private CanItemProgressList mItemWarnAt;
    private CanItemBlankTextList mItemWarnTitle;
    private CanScrollList mManager;
    private CanDataInfo.GolfTyres mTyresData = new CanDataInfo.GolfTyres();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        if (!i2b(this.mAdtTyres.UpdateOnce)) {
            CanJni.GolfGetAdtTyres(this.mAdtTyres);
            if (i2b(this.mAdtTyres.UpdateOnce)) {
                this.mManager.RemoveAllViews();
                if (IsHaveItem(1)) {
                    this.mManager.AddView(this.mItemSetTitle.GetView());
                }
                AddItem(1);
                if (IsHaveItem(2) || IsHaveItem(3)) {
                    this.mManager.AddView(this.mItemWarnTitle.GetView());
                }
                AddItem(2);
                AddItem(3);
                this.mItemWarnAt.ShowGone(this.mTyresData.fgSpeedWarning);
            } else {
                return;
            }
        }
        CanJni.GolfGetTyres(this.mTyresData);
        if (!i2b(this.mTyresData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTyresData.Update)) {
            this.mTyresData.Update = 0;
            this.mItemSpeedWarn.SetCheck(this.mTyresData.fgSpeedWarning);
            if (this.mTyresData.SpeedDW == 0) {
                this.mItemWarnAt.SetMinMax(30, 240);
                this.mItemWarnAt.SetStep(10);
                this.mItemWarnAt.SetValText(this.mTyresData.Data + " km/h");
            } else {
                this.mItemWarnAt.SetMinMax(20, Can.CAN_JAC_REFINE_OD);
                this.mItemWarnAt.SetStep(5);
                this.mItemWarnAt.SetValText(this.mTyresData.Data + " mph");
            }
            this.mItemWarnAt.SetCurVal(this.mTyresData.Data);
            if (IsHaveItem(3)) {
                this.mItemWarnAt.ShowGone(this.mTyresData.fgSpeedWarning);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        if (!i2b(this.mAdtTyres.UpdateOnce)) {
            CanJni.GolfQuery(65, 32);
            Sleep(5);
        }
        if (!i2b(this.mTyresData.UpdateOnce)) {
            CanJni.GolfQuery(64, 32);
        }
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
        this.mItemSetTitle = new CanItemBlankTextList((Context) this, R.string.can_tyres_tpms);
        this.mItemTyresSet = new CanItemIcoList(this, R.drawable.can_golf_icon10, 0);
        this.mItemTyresSet.GetBtn().setText("SET");
        this.mItemTyresSet.SetIdClickListener(this, 1);
        this.mItemWarnTitle = new CanItemBlankTextList((Context) this, R.string.can_winter_tyres);
        this.mItemSpeedWarn = new CanItemCheckList(this, R.string.can_warn_at);
        this.mItemSpeedWarn.SetIdClickListener(this, 2);
        this.mItemWarnAt = new CanItemProgressList((Context) this, R.string.can_speed_warn);
        this.mItemWarnAt.SetIdCallBack(3, this);
        this.mItemWarnAt.SetUserValText();
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtTyres.SetBtn;
                break;
            case 2:
                ret = this.mAdtTyres.SpeedWarning;
                break;
            case 3:
                ret = this.mAdtTyres.WarnningAt;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void AddItem(int item) {
        if (IsHaveItem(item)) {
            switch (item) {
                case 1:
                    this.mManager.AddView(this.mItemTyresSet.GetView());
                    return;
                case 2:
                    this.mManager.AddView(this.mItemSpeedWarn.GetView());
                    return;
                case 3:
                    this.mManager.AddView(this.mItemWarnAt.GetView());
                    return;
                default:
                    return;
            }
        }
    }

    public void onClick(View v) {
        int Id = ((Integer) v.getTag()).intValue();
        switch (Id) {
            case 1:
                new CanItemMsgBox(Id, this, R.string.can_tpms_box, this);
                return;
            case 2:
                CanJni.GolfSendCmd(32, 1 - (this.mTyresData.fgSpeedWarning & 1));
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
        if (1 == param) {
            CanJni.GolfSendCmd(34, 1);
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 3) {
            CanJni.GolfSendCmd(33, pos);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
