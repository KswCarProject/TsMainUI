package com.ts.can.vw.touareg;

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

public class CanTouaregSetWindowActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    private static final int ITEM_CMJS = 6;
    private static final int ITEM_DDWXLXJS = 7;
    private static final int ITEM_FZHSJ = 5;
    private static final int ITEM_HBCC = 2;
    private static final int ITEM_HDTC = 3;
    private static final int ITEM_QBCC = 1;
    private static final int ITEM_ZDSZ = 4;
    private int[] mArrays = {R.string.can_jsym, R.string.can_sym};
    private CanItemPopupList mItemCmjs;
    private CanItemSwitchList mItemDdwxlxjs;
    private CanItemSwitchList mItemFzhsj;
    private CanItemSwitchList mItemHbcc;
    private CanItemSwitchList mItemHdtc;
    private CanItemSwitchList mItemQbcc;
    private CanItemSwitchList mItemZdsz;
    private CanScrollList mManager;
    private CanDataInfo.TouaregWcWindow mWindowData = new CanDataInfo.TouaregWcWindow();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemQbcc = this.mManager.addItemCheckBox(R.string.can_qbcc, 1, this);
        this.mItemHbcc = this.mManager.addItemCheckBox(R.string.can_hbcc, 2, this);
        this.mItemHdtc = this.mManager.addItemCheckBox(R.string.can_hdtc, 3, this);
        this.mItemZdsz = this.mManager.addItemCheckBox(R.string.can_auto_lock, 4, this);
        this.mItemFzhsj = this.mManager.addItemCheckBox(R.string.can_fzhsj, 5, this);
        this.mItemCmjs = this.mManager.addItemPopupList(R.string.can_door_unlock, this.mArrays, 6, (CanItemPopupList.onPopItemClick) this);
        this.mItemDdwxlxjs = this.mManager.addItemCheckBox(R.string.can_ddwxlxjs, 7, this);
        this.mItemQbcc.ShowGone(false);
        this.mItemHbcc.ShowGone(false);
        this.mItemHdtc.ShowGone(false);
        this.mItemZdsz.ShowGone(false);
        this.mItemFzhsj.ShowGone(false);
        this.mItemCmjs.ShowGone(false);
        this.mItemDdwxlxjs.ShowGone(false);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        CanJni.TouaregQuery(5, 1, 100);
    }

    private void ResetData(boolean check) {
        CanJni.TouaregGetWindowData(this.mWindowData);
        if (!i2b(this.mWindowData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWindowData.Update)) {
            this.mWindowData.Update = 0;
            this.mItemQbcc.ShowGone(this.mWindowData.QbccAvalid);
            this.mItemHbcc.ShowGone(this.mWindowData.HbccAvalid);
            this.mItemHdtc.ShowGone(this.mWindowData.HdtcAvalid);
            this.mItemZdsz.ShowGone(this.mWindowData.ZdszAvalid);
            this.mItemFzhsj.ShowGone(this.mWindowData.FzhsjAvalid);
            this.mItemCmjs.ShowGone(this.mWindowData.CmjsAvalid);
            this.mItemDdwxlxjs.ShowGone(this.mWindowData.DdwxlxjsAvalid);
            this.mItemQbcc.SetCheck(this.mWindowData.Qbcc);
            this.mItemHbcc.SetCheck(this.mWindowData.Hbcc);
            this.mItemHdtc.SetCheck(this.mWindowData.Hdtc);
            this.mItemZdsz.SetCheck(this.mWindowData.Zdsz);
            this.mItemFzhsj.SetCheck(this.mWindowData.Fzhsj);
            this.mItemCmjs.SetSel(this.mWindowData.Cmjs);
            this.mItemDdwxlxjs.SetCheck(this.mWindowData.Ddwxlxjs);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.TouaregWindowSet(1, Neg(this.mWindowData.Qbcc));
                return;
            case 2:
                CanJni.TouaregWindowSet(2, Neg(this.mWindowData.Hbcc));
                return;
            case 3:
                CanJni.TouaregWindowSet(3, Neg(this.mWindowData.Hdtc));
                return;
            case 4:
                CanJni.TouaregWindowSet(5, Neg(this.mWindowData.Zdsz));
                return;
            case 5:
                CanJni.TouaregWindowSet(6, Neg(this.mWindowData.Fzhsj));
                return;
            case 7:
                CanJni.TouaregWindowSet(8, Neg(this.mWindowData.Ddwxlxjs));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        if (id == 6) {
            CanJni.TouaregWindowSet(7, item);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
