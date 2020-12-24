package com.ts.can.vw.golf.wc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollList;

public class CanGolfWcSetTyresActivity extends CanBaseActivity implements View.OnClickListener, CanItemMsgBox.onMsgBoxClick, CanItemProgressList.onPosChange, UserCallBack {
    public static final int ITEM_SET_BTN = 1;
    public static final int ITEM_SPEED_WARN = 2;
    public static final int ITEM_WARN_AT = 3;
    private CanItemBlankTextList mItemSetTitle;
    private CanItemCheckList mItemSpeedWarn;
    private CanItemIcoList mItemTyresSet;
    private CanItemProgressList mItemWarnAt;
    private CanItemBlankTextList mItemWarnTitle;
    private CanDataInfo.GolfWcTyres mTyresAdt = new CanDataInfo.GolfWcTyres();
    private CanDataInfo.GolfWcTyres mTyresInfos = new CanDataInfo.GolfWcTyres();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        boolean z = true;
        CanJni.GolfWcGetTyresData(1, this.mTyresAdt);
        CanJni.GolfWcGetTyresData(0, this.mTyresInfos);
        if (i2b(this.mTyresAdt.UpdateOnce) && (!check || i2b(this.mTyresAdt.Update))) {
            this.mTyresAdt.Update = 0;
            this.mItemSetTitle.ShowGone(true);
            this.mItemTyresSet.ShowGone(true);
            this.mItemWarnTitle.ShowGone(this.mTyresAdt.WinterTyprsSpeedWarning);
            this.mItemSpeedWarn.ShowGone(this.mTyresAdt.WinterTyprsSpeedWarning);
        }
        if (!i2b(this.mTyresInfos.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTyresInfos.Update)) {
            this.mTyresInfos.Update = 0;
            this.mItemSpeedWarn.SetCheck(this.mTyresInfos.WinterTyprsSpeedWarning);
            this.mItemWarnAt.SetCurVal(this.mTyresInfos.SpeedWarningAt);
            this.mItemWarnAt.SetValText(String.valueOf(this.mTyresInfos.SpeedWarningAt) + " km/h");
            CanItemProgressList canItemProgressList = this.mItemWarnAt;
            if (!(this.mTyresAdt.WinterTyprsSpeedWarning == 1 && this.mTyresInfos.WinterTyprsSpeedWarning == 1)) {
                z = false;
            }
            canItemProgressList.ShowGone(z);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfWcQueryData(1, 70);
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
        this.mItemSetTitle = new CanItemBlankTextList((Context) this, R.string.can_tyres_tpms);
        this.mItemTyresSet = new CanItemIcoList(this, R.drawable.can_golf_icon10, 0);
        this.mItemTyresSet.GetBtn().setText("SET");
        this.mItemTyresSet.SetIdClickListener(this, 1);
        this.mItemWarnTitle = new CanItemBlankTextList((Context) this, R.string.can_winter_tyres);
        this.mItemSpeedWarn = new CanItemCheckList(this, R.string.can_warn_at);
        this.mItemSpeedWarn.SetIdClickListener(this, 2);
        this.mItemWarnAt = new CanItemProgressList((Context) this, R.string.can_speed_warn);
        this.mItemWarnAt.SetIdCallBack(3, this);
        this.mItemWarnAt.SetMinMax(30, 240);
        this.mItemWarnAt.SetStep(10);
        this.mItemWarnAt.SetUserValText();
        this.mItemWarnAt.SetCurVal(30);
        this.mItemWarnAt.SetValText("30 km/h");
        this.mItemSetTitle.ShowGone(true);
        this.mItemTyresSet.ShowGone(true);
        this.mItemWarnTitle.ShowGone(false);
        this.mItemSpeedWarn.ShowGone(false);
        this.mItemWarnAt.ShowGone(false);
        mManager.AddView(this.mItemSetTitle.GetView());
        mManager.AddView(this.mItemTyresSet.GetView());
        mManager.AddView(this.mItemWarnTitle.GetView());
        mManager.AddView(this.mItemSpeedWarn.GetView());
        mManager.AddView(this.mItemWarnAt.GetView());
    }

    public void onClick(View v) {
        int Id = ((Integer) v.getTag()).intValue();
        switch (Id) {
            case 1:
                new CanItemMsgBox(Id, this, R.string.can_tpms_box, this);
                return;
            case 2:
                CanJni.GolfWcTyresSet(2, Neg(this.mTyresInfos.WinterTyprsSpeedWarning));
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
        if (1 == param) {
            CanJni.GolfWcTyresSet(1, 1);
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 3) {
            CanJni.GolfWcTyresSet(3, pos);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
