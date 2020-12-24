package com.ts.can.vw.touareg;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;

public class CanTouaregSetServiceActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemMsgBox.onMsgBoxClick {
    private static final int ITEM_RESET = 3;
    private static final int ITEM_SERVICE_UNIT = 1;
    private static final int ITEM_YSZWHWZ = 2;
    private static final String[] mUnitArray = {"KM", "Mile"};
    private static int[] mYsqArray = {R.string.can_not_exist, R.string.can_exist};
    private CanItemBlankTextList mItemByjcTitle;
    private CanItemTextBtnList mItemByjcValue;
    private CanItemBlankTextList mItemByjcZqTitle;
    private CanItemTextBtnList mItemByjcZqValue;
    private CanItemProgressList mItemOil;
    private CanItemTextBtnList mItemReset;
    private CanItemTitleValList mItemUnit;
    private CanItemPopupList mItemYsq;
    private CanScrollList mManager;
    private CanDataInfo.TouaregWcService mServiceData = new CanDataInfo.TouaregWcService();
    private String mStrDays;
    private String mStrFm1;
    private String mStrFm2;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
        this.mStrDays = getString(R.string.can_days);
        this.mStrFm1 = getString(R.string.can_gf_service_fm1);
        this.mStrFm2 = getString(R.string.can_gf_service_fm2);
    }

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemOil = this.mManager.addItemProgressList(R.string.can_jyyl, 0, (CanItemProgressList.onPosChange) null);
        this.mItemOil.SetMinMax(0, 8);
        this.mItemOil.SetStep(1);
        this.mItemOil.GetBtnAdd().setVisibility(8);
        this.mItemOil.GetBtnSub().setVisibility(8);
        this.mItemUnit = this.mManager.addItemTitleValList(R.string.can_bylcdw, 1, false, this);
        this.mItemYsq = this.mManager.addItemPopupList(R.string.can_ysqzwhwz, mYsqArray, 2, (CanItemPopupList.onPopItemClick) this);
        this.mItemByjcTitle = new CanItemBlankTextList((Context) this, R.string.can_byjc);
        this.mManager.AddView(this.mItemByjcTitle.GetView());
        this.mItemByjcValue = new CanItemTextBtnList((Context) this, 0);
        this.mItemByjcValue.ShowArrow(false);
        this.mManager.AddView(this.mItemByjcValue.GetView());
        this.mItemByjcZqTitle = new CanItemBlankTextList((Context) this, R.string.can_byzq);
        this.mManager.AddView(this.mItemByjcZqTitle.GetView());
        this.mItemByjcZqValue = new CanItemTextBtnList((Context) this, 0);
        this.mItemByjcZqValue.ShowArrow(false);
        this.mManager.AddView(this.mItemByjcZqValue.GetView());
        this.mItemReset = this.mManager.addItemFsSetList(R.string.can_service_reset, 3, this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        CanJni.TouaregQuery(5, 1, 195);
    }

    private void ResetData(boolean check) {
        CanJni.TouaregGetServiceData(this.mServiceData);
        if (!i2b(this.mServiceData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mServiceData.Update)) {
            this.mServiceData.Update = 0;
            this.mItemOil.SetCurVal(this.mServiceData.Jyyl);
            if (this.mServiceData.BylcDw < mUnitArray.length) {
                this.mItemUnit.SetVal(mUnitArray[this.mServiceData.BylcDw]);
            }
            this.mItemYsq.SetSel(this.mServiceData.Ysqzwhwz);
            int mile = this.mServiceData.ByjcKmMile * 100;
            this.mItemByjcValue.SetVal(String.format("%s %s %s %s", new Object[]{this.mStrFm1, i2b(this.mServiceData.BylcDw) ? String.valueOf(mile) + " Mile" : String.valueOf(mile) + " Km", this.mStrFm2, String.valueOf(this.mServiceData.ByjcDay) + " " + this.mStrDays}));
            int mile2 = this.mServiceData.ByzqKmMile * 100;
            this.mItemByjcZqValue.SetVal(String.format("%s %s %s %s", new Object[]{this.mStrFm1, i2b(this.mServiceData.BylcDw) ? String.valueOf(mile2) + " Mile" : String.valueOf(mile2) + " Km", this.mStrFm2, String.valueOf(this.mServiceData.ByzqDay) + " " + this.mStrDays}));
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void onClick(final View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 3:
                CanItemMsgBox msgbox = new CanItemMsgBox(id, this, R.string.can_sure_setup, this);
                v.setSelected(true);
                msgbox.getDlg().setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface arg0) {
                        if (v != null) {
                            v.setSelected(false);
                        }
                    }
                });
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
        CanJni.TouaregServiceSet(1, 1);
    }

    public void onItem(int id, int item) {
        if (id == 2) {
            CanJni.TouaregServiceSet(2, item);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
