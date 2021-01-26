package com.ts.can.vw.touareg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollList;

public class CanTouaregSetTyresActivity extends CanBaseActivity implements View.OnClickListener, CanItemProgressList.onPosChange, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_FZZT = 4;
    public static final int ITEM_LTLX = 5;
    public static final int ITEM_SPEED_WARN = 2;
    public static final int ITEM_WARN_AT = 3;
    private static int[] mFzztArr = {R.string.can_full_load, R.string.can_partial_load};
    private CanItemPopupList mItemFzzt;
    private CanItemProgressList mItemLtlx;
    private CanItemBlankTextList mItemLtyljkTitle;
    private CanItemCheckList mItemSpeedWarn;
    private CanItemProgressList mItemWarnAt;
    private CanItemBlankTextList mItemWarnTitle;
    private CanDataInfo.TouaregWcTpmsInfo mLxyljk = new CanDataInfo.TouaregWcTpmsInfo();
    private CanScrollList mManager;
    private CanDataInfo.TouaregWcTyres mTyresData = new CanDataInfo.TouaregWcTyres();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemWarnTitle = new CanItemBlankTextList((Context) this, R.string.can_winter_tyres);
        this.mItemSpeedWarn = new CanItemCheckList(this, R.string.can_warn_at);
        this.mItemSpeedWarn.SetIdClickListener(this, 2);
        this.mItemWarnAt = new CanItemProgressList((Context) this, R.string.can_speed_warn);
        this.mItemWarnAt.SetIdCallBack(3, this);
        this.mItemWarnAt.SetMinMax(30, Can.CAN_VOLKS_XP);
        this.mItemWarnAt.SetStep(10);
        this.mItemWarnAt.SetUserValText();
        this.mManager.AddView(this.mItemWarnTitle.GetView());
        this.mManager.AddView(this.mItemSpeedWarn.GetView());
        this.mManager.AddView(this.mItemWarnAt.GetView());
        this.mItemLtyljkTitle = new CanItemBlankTextList((Context) this, R.string.can_tyres_tpms);
        this.mManager.AddView(this.mItemLtyljkTitle.GetView());
        this.mItemFzzt = this.mManager.addItemPopupList(R.string.can_load_status, mFzztArr, 4, (CanItemPopupList.onPopItemClick) this);
        this.mItemLtlx = new CanItemProgressList((Context) this, R.string.can_tyre_type);
        this.mItemLtlx.SetIdCallBack(5, this);
        this.mItemLtlx.SetMinMax(0, 8);
        this.mItemLtlx.SetStep(1);
        this.mItemLtlx.SetUserValText();
        this.mManager.AddView(this.mItemLtlx.GetView());
        showTyresViews(false);
        showTpmsViews(false);
    }

    private void showTyresViews(boolean show) {
        this.mItemWarnTitle.ShowGone(show);
        this.mItemSpeedWarn.ShowGone(show);
        this.mItemWarnAt.ShowGone(show);
    }

    private void showTpmsViews(boolean show) {
        this.mItemLtyljkTitle.ShowGone(show);
        this.mItemLtlx.ShowGone(show);
        this.mItemFzzt.ShowGone(show);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        CanJni.TouaregQuery(5, 1, 70);
        if (CanJni.GetCanFsTp() == 310) {
            CanJni.TouaregQuery(5, 1, 196);
        }
    }

    private void QueryData() {
    }

    private void ResetData(boolean check) {
        CanJni.TouaregGetTyresData(this.mTyresData);
        if (i2b(this.mTyresData.UpdateOnce) && (!check || i2b(this.mTyresData.Update))) {
            this.mTyresData.Update = 0;
            showTyresViews(i2b(this.mTyresData.TyersAvalid));
            this.mItemSpeedWarn.SetCheck(this.mTyresData.fgSpeedWarning);
            if (i2b(this.mTyresData.TyersAvalid)) {
                this.mItemWarnAt.ShowGone(this.mTyresData.fgSpeedWarning);
            }
            this.mItemWarnAt.SetValText(this.mTyresData.Data + " km/h");
            this.mItemWarnAt.SetCurVal(this.mTyresData.Data);
        }
        if (CanJni.GetCanFsTp() == 310) {
            CanJni.TouaregGetTpmsInfo(this.mLxyljk);
            if (!i2b(this.mLxyljk.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mLxyljk.Update)) {
                this.mLxyljk.Update = 0;
                if (this.mLxyljk.AdtLtlx > 0 || this.mLxyljk.AdtFzzt > 0) {
                    this.mItemLtyljkTitle.ShowGone(true);
                } else {
                    this.mItemLtyljkTitle.ShowGone(false);
                }
                this.mItemFzzt.ShowGone(this.mLxyljk.AdtFzzt);
                this.mItemLtlx.ShowGone(this.mLxyljk.AdtLtlx);
                if (this.mLxyljk.Fzzt == 1 || this.mLxyljk.Fzzt == 2) {
                    this.mItemFzzt.SetSel(this.mLxyljk.Fzzt - 1);
                }
                this.mItemLtlx.SetValText(new StringBuilder().append(this.mLxyljk.Ltlx).toString());
                this.mItemLtlx.SetCurVal(this.mLxyljk.Ltlx);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void onChanged(int id, int pos) {
        if (id == 3) {
            CanJni.TouaregTyresSet(3, pos);
        } else if (id == 5) {
            CanJni.TouaregTpmsCmd(2, pos);
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.TouaregTyresSet(2, Neg(this.mTyresData.fgSpeedWarning));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 4:
                CanJni.TouaregTpmsCmd(1, item + 1);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
