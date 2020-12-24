package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanHondDACarAMPSetActivity extends CanBaseActivity implements CanItemProgressList.onPosChange, CanItemMsgBox.onMsgBoxClick, CanItemPopupList.onPopItemClick, View.OnClickListener, UserCallBack {
    public static final int ITEM_AMP_BAL = 2;
    public static final int ITEM_AMP_BAS = 5;
    public static final int ITEM_AMP_CSLD = 7;
    public static final int ITEM_AMP_DEFAULT = 10;
    public static final int ITEM_AMP_DTS = 8;
    public static final int ITEM_AMP_FAD = 1;
    public static final int ITEM_AMP_MID = 4;
    public static final int ITEM_AMP_SUBWOF = 6;
    public static final int ITEM_AMP_TRE = 3;
    public static final int ITEM_AMP_VOL = 9;
    public static final String TAG = "CanHondDACarAMPSetActivity";
    private static final int[] mAmpCsldArr = {R.string.can_Scsfctsy_3, R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
    protected CanDataInfo.HondaAmpData mAmpData = new CanDataInfo.HondaAmpData();
    protected CanItemProgressList mItemAmpBal;
    protected CanItemProgressList mItemAmpBas;
    protected CanItemPopupList mItemAmpCsld;
    protected CanItemTextBtnList mItemAmpDefalut;
    protected CanItemSwitchList mItemAmpDts;
    protected CanItemProgressList mItemAmpFad;
    protected CanItemProgressList mItemAmpMid;
    protected CanItemProgressList mItemAmpSubwof;
    protected CanItemProgressList mItemAmpTre;
    protected CanItemProgressList mItemAmpVol;
    protected CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemAmpFad = new CanItemProgressList((Context) this, R.string.can_balance_front_rear);
        this.mItemAmpFad.SetMinMax(0, 18);
        this.mItemAmpFad.SetIdCallBack(1, this);
        this.mManager.AddView(this.mItemAmpFad.GetView());
        this.mItemAmpBal = new CanItemProgressList((Context) this, R.string.can_balance_left_right);
        this.mItemAmpBal.SetMinMax(0, 18);
        this.mItemAmpBal.SetIdCallBack(2, this);
        this.mManager.AddView(this.mItemAmpBal.GetView());
        this.mItemAmpTre = new CanItemProgressList((Context) this, R.string.can_vol_high);
        this.mItemAmpTre.SetMinMax(0, 12);
        this.mItemAmpTre.SetIdCallBack(3, this);
        this.mManager.AddView(this.mItemAmpTre.GetView());
        this.mItemAmpMid = new CanItemProgressList((Context) this, R.string.can_vol_middle);
        this.mItemAmpMid.SetMinMax(0, 12);
        this.mItemAmpMid.SetIdCallBack(4, this);
        this.mManager.AddView(this.mItemAmpMid.GetView());
        this.mItemAmpBas = new CanItemProgressList((Context) this, R.string.can_vol_low);
        this.mItemAmpBas.SetMinMax(0, 12);
        this.mItemAmpBas.SetIdCallBack(5, this);
        this.mManager.AddView(this.mItemAmpBas.GetView());
        this.mItemAmpSubwof = new CanItemProgressList((Context) this, R.string.can_subwof);
        this.mItemAmpSubwof.SetMinMax(0, 12);
        this.mItemAmpSubwof.SetIdCallBack(6, this);
        this.mManager.AddView(this.mItemAmpSubwof.GetView());
        this.mItemAmpCsld = AddPopupItem(R.string.can_apply_speed, mAmpCsldArr, 7);
        this.mItemAmpDts = AddCheckItem(R.string.can_dts, 8);
        this.mItemAmpVol = new CanItemProgressList((Context) this, R.string.can_vol);
        this.mItemAmpVol.SetMinMax(0, 40);
        this.mItemAmpVol.SetIdCallBack(9, this);
        this.mManager.AddView(this.mItemAmpVol.GetView());
        this.mItemAmpDefalut = AddTextBtn(R.string.can_rpa_reset, 10);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        btn.ShowGone(true);
        return btn;
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.HondaDAQuery(49, 0);
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                CanJni.HondaDaSetAmp(1, pos);
                return;
            case 2:
                CanJni.HondaDaSetAmp(2, pos);
                return;
            case 3:
                CanJni.HondaDaSetAmp(3, pos);
                return;
            case 4:
                CanJni.HondaDaSetAmp(4, pos);
                return;
            case 5:
                CanJni.HondaDaSetAmp(5, pos);
                return;
            case 6:
                CanJni.HondaDaSetAmp(6, pos);
                return;
            case 9:
                CanJni.HondaDaSetAmp(9, pos);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 7:
                CanJni.HondaDaSetAmp(7, item);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 8:
                CanJni.HondaDaSetAmp(8, Neg(this.mAmpData.Dts));
                return;
            case 10:
                new CanItemMsgBox(10, this, R.string.can_cmp_reset_notice, this);
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
        switch (param) {
            case 10:
                CanJni.HondaDaSetAmp(10, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.HondaDaGetAmp(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            this.mItemAmpFad.SetCurVal(this.mAmpData.Fad);
            this.mItemAmpFad.SetValText(new StringBuilder(String.valueOf(this.mAmpData.Fad - 9)).toString());
            this.mItemAmpBal.SetCurVal(this.mAmpData.Bal);
            this.mItemAmpBal.SetValText(new StringBuilder(String.valueOf(this.mAmpData.Bal - 9)).toString());
            this.mItemAmpTre.SetCurVal(this.mAmpData.Tre);
            this.mItemAmpTre.SetValText(new StringBuilder(String.valueOf(this.mAmpData.Tre - 6)).toString());
            this.mItemAmpMid.SetCurVal(this.mAmpData.Mid);
            this.mItemAmpMid.SetValText(new StringBuilder(String.valueOf(this.mAmpData.Mid - 6)).toString());
            this.mItemAmpBas.SetCurVal(this.mAmpData.Bas);
            this.mItemAmpBas.SetValText(new StringBuilder(String.valueOf(this.mAmpData.Bas - 6)).toString());
            this.mItemAmpSubwof.SetCurVal(this.mAmpData.Subwof);
            this.mItemAmpSubwof.SetValText(new StringBuilder(String.valueOf(this.mAmpData.Subwof - 6)).toString());
            this.mItemAmpCsld.SetSel(this.mAmpData.Csld);
            this.mItemAmpDts.SetCheck(this.mAmpData.Dts);
            this.mItemAmpVol.SetCurVal(this.mAmpData.Vol);
            this.mItemAmpVol.SetValText(new StringBuilder(String.valueOf(this.mAmpData.Vol)).toString());
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
