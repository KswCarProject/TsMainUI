package com.ts.can.gac.trumpchi;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGqcqSetDrvAssActivity extends CanGqcqBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    public static final int ITEM_CSBJ = 1;
    private static final int ITEM_MAX = 7;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_YCBXFZ = 6;
    public static final int ITEM_YCQDSJ = 4;
    public static final int ITEM_YCSDSJ = 3;
    public static final int ITEM_ZCBXFZ = 7;
    public static final int ITEM_ZHYBYL = 2;
    public static final int ITEM_ZXMS = 5;
    public static final String TAG = "CanGqcqSetDrvAssActivity";
    private CanItemProgressList mItemCsbj;
    private CanItemPopupList mItemYcbxfz;
    private CanItemProgressList mItemYcqdsj;
    private CanItemProgressList mItemYcsdsj;
    private CanItemPopupList mItemZcbxfz;
    private CanItemPopupList mItemZhybyl;
    private CanItemPopupList mItemZxms;
    private CanScrollList mManager;
    protected int[] mZhybylArr = {R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
    protected int[] mZxmsArr = {R.string.can_sport, R.string.can_mode_normal, R.string.can_mode_ss};
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
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemCsbj.SetCurVal(this.mSetData.SpeedWarn);
            this.mItemYcsdsj.SetCurVal(this.mSetData.Ycsdsj);
            this.mItemYcqdsj.SetCurVal(this.mSetData.Ycqdsj);
            this.mItemCsbj.SetValText(new StringBuilder(String.valueOf(this.mSetData.SpeedWarn * 10)).toString());
            this.mItemZhybyl.SetSel(this.mSetData.Zhybyl - 1);
            this.mItemZxms.SetSel(this.mSetData.ZxMode - 1);
            this.mItemYcbxfz.SetSel(this.mSetData.Ycbxfz - 1);
            this.mItemZcbxfz.SetSel(this.mSetData.Zcbxfz - 1);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemCsbj = AddProgressItem(R.string.can_csbj, 1);
        this.mItemYcsdsj = AddProgressItem(R.string.can_ycsdsj, 3);
        this.mItemYcqdsj = AddProgressItem(R.string.can_ycqdsj, 4);
        this.mItemZhybyl = AddPopupItem(R.string.can_zhbjyl, this.mZhybylArr, 2);
        this.mItemZxms = AddPopupItem(R.string.can_zxms, this.mZxmsArr, 5);
        this.mItemCsbj.SetUserValText();
        this.mItemCsbj.SetMinMax(0, 20);
        this.mItemYcsdsj.SetMinMax(0, 30);
        this.mItemYcqdsj.SetMinMax(0, 30);
        this.mItemYcbxfz = AddPopupItem(R.string.can_trumpchi_ycbxfz, this.mSWArr, 6);
        this.mItemZcbxfz = AddPopupItem(R.string.can_trumpchi_zcbxfz, this.mSWArr, 7);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 7; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        return i2b(1);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemCsbj.ShowGone(show);
                return;
            case 2:
                this.mItemZhybyl.ShowGone(show);
                return;
            case 3:
                this.mItemYcsdsj.ShowGone(show);
                return;
            case 4:
                this.mItemYcqdsj.ShowGone(show);
                return;
            case 5:
                this.mItemZxms.ShowGone(show);
                return;
            case 6:
                this.mItemYcbxfz.ShowGone(show);
                return;
            case 7:
                this.mItemZcbxfz.ShowGone(show);
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

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemProgressList AddProgressItem(int resId, int Id) {
        CanItemProgressList item = new CanItemProgressList((Context) this, resId);
        item.SetIdCallBack(Id, this);
        item.ShowGone(false);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CarSet(8, item);
                return;
            case 5:
                CarSet(11, item);
                return;
            case 6:
                CarSet(34, item);
                return;
            case 7:
                CarSet(35, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                CanJni.GqcqCarSet(7, pos);
                return;
            case 3:
                CanJni.GqcqCarSet(9, pos);
                return;
            case 4:
                CanJni.GqcqCarSet(10, pos);
                return;
            default:
                return;
        }
    }
}
