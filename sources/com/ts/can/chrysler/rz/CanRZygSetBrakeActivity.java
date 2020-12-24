package com.ts.can.chrysler.rz;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanRZygSetBrakeActivity extends CanRZygBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_FWMS = 1;
    public static final int ITEM_FWMS_2 = 3;
    private static final int ITEM_MAX = 4;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_ZDBCZD = 2;
    public static final int ITEM_ZDZCZD_2 = 4;
    public static final String TAG = "CanChrOthSetBrakeActivity";
    protected CanItemTextBtnList mItemFwms;
    protected CanItemSwitchList mItemFwms2;
    protected CanItemSwitchList mItemZdbczd;
    protected CanItemSwitchList mItemZdzczd2;
    private CanScrollList mManager;
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetSetData();
        if (!i2b(this.mSetData.BrkUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.BrkUpdate)) {
            this.mSetData.BrkUpdate = 0;
            this.mItemZdbczd.SetCheck(this.mSetData.Zdzczd);
            this.mItemFwms2.SetCheck(this.mSetData.Fwms);
            this.mItemZdzczd2.SetCheck(this.mSetData.AutoPark);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(64, 192);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
        ResetData(false);
        QueryData();
        Log.d("CanChrOthSetBrakeActivity", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanChrOthSetBrakeActivity", "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemFwms = AddTextBtn(R.string.can_service_mode, 1);
        this.mItemZdbczd = AddCheckItem(R.string.can_zdbczd, 2);
        this.mItemFwms2 = AddCheckItem(R.string.can_service_mode, 3);
        this.mItemZdzczd2 = AddCheckItem(R.string.can_zdzczd, 4);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 4; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.BrkSta;
                break;
            case 2:
                ret = this.mAdtData.Zdzczd;
                break;
            case 3:
                ret = this.mAdtData.Fwms;
                break;
            case 4:
                ret = this.mAdtData.AutoPark;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemFwms.ShowGone(show);
                return;
            case 2:
                this.mItemZdbczd.ShowGone(show);
                return;
            case 3:
                this.mItemFwms2.ShowGone(show);
                return;
            case 4:
                this.mItemZdzczd2.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        return btn;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                new AlertDialog.Builder(this).setTitle(R.string.can_service_mode).setMessage(R.string.can_sevice_question).setNegativeButton(R.string.can_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setPositiveButton(R.string.can_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CanRZygSetBrakeActivity.this.CarSet(10, 1);
                    }
                }).show();
                return;
            case 2:
                CarSWSet(11, this.mSetData.Zdzczd);
                return;
            case 3:
                CarSet(99, Neg(this.mSetData.Fwms));
                return;
            case 4:
                CarSet(98, Neg(this.mSetData.AutoPark));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
