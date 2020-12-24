package com.ts.can.gac.trumpchi;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGqcqSetACActivity extends CanGqcqBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_FLZMS = 4;
    public static final int ITEM_KQZLCGQ = 5;
    public static final int ITEM_LOOP = 2;
    private static final int ITEM_MAX = 6;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SSQX = 3;
    public static final int ITEM_YSJ = 1;
    public static final int ITEM_ZDMSFLSZ = 6;
    public static final String TAG = "CanGqcqSetACActivity";
    private CanItemPopupList mItemFlzms;
    private CanItemPopupList mItemKqzlcgq;
    private CanItemPopupList mItemLoop;
    private CanItemPopupList mItemSsqx;
    private CanItemPopupList mItemYsj;
    private CanItemPopupList mItemZdmsflsz;
    protected int[] mKqzlcgqArr = {R.string.can_ac_lo_sens, R.string.can_mid_sens, R.string.can_ac_hi_sens};
    protected int[] mLoopArr = {R.string.can_type_mode_auto, R.string.can_shoudong};
    private CanScrollList mManager;
    protected int[] mSsqxArr = {R.string.can_sdhm, R.string.can_normal, R.string.can_sdks};
    protected int[] mZdmsflszArr = {R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
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
            this.mItemYsj.SetSel(this.mSetData.YsjSta - 1);
            this.mItemLoop.SetSel(this.mSetData.LoopStyle - 1);
            this.mItemSsqx.SetSel(this.mSetData.SsxQx - 1);
            this.mItemFlzms.SetSel(this.mSetData.Flzms - 1);
            this.mItemKqzlcgq.SetSel(this.mSetData.Kqzlcgq - 1);
            this.mItemZdmsflsz.SetSel(this.mSetData.Zdmsflsz - 1);
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
        this.mItemYsj = AddPopupItem(R.string.can_ac_ysj, this.mSWArr, 1);
        this.mItemLoop = AddPopupItem(R.string.can_ac_loop, this.mLoopArr, 2);
        this.mItemSsqx = AddPopupItem(R.string.can_ac_ssqx, this.mSsqxArr, 3);
        this.mItemFlzms = AddPopupItem(R.string.can_ac_flzms, this.mSWArr, 4);
        this.mItemKqzlcgq = AddPopupItem(R.string.can_ac_cgq, this.mKqzlcgqArr, 5);
        this.mItemZdmsflsz = AddPopupItem(R.string.can_ac_zdfl, this.mZdmsflszArr, 6);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 6; i++) {
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
                this.mItemYsj.ShowGone(show);
                return;
            case 2:
                this.mItemLoop.ShowGone(show);
                return;
            case 3:
                this.mItemSsqx.ShowGone(show);
                return;
            case 4:
                this.mItemFlzms.ShowGone(show);
                return;
            case 5:
                this.mItemKqzlcgq.ShowGone(show);
                return;
            case 6:
                this.mItemZdmsflsz.ShowGone(show);
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

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CarSet(2, item);
                return;
            case 2:
                CarSet(3, item);
                return;
            case 3:
                CarSet(4, item);
                return;
            case 4:
                CarSet(24, item);
                return;
            case 5:
                CarSet(37, item);
                return;
            case 6:
                CarSet(38, item);
                return;
            default:
                return;
        }
    }
}
