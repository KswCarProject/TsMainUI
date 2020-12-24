package com.ts.can.chrysler.xbs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;

public class CanXbsygSetCompassActivity extends CanXbsygBaseActivity implements View.OnClickListener, UserCallBack, CanItemProgressList.onPosChange {
    public static final int ITEM_LPCZ = 2;
    public static final int ITEM_LPFX = 1;
    public static final int ITEM_LPJZ = 3;
    private static final int ITEM_MAX = 3;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanRZygSetCompassActivity";
    private static final String[] mStrDirect = {"北", "东北", "东", "东南", "南", "西南", "西", "西北"};
    private CanItemProgressList mItemLpcz;
    private CanItemTitleValList mItemLpfx;
    private CanItemTitleValList mItemLpjz;
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
        if (!i2b(this.mSetData.CompassUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.CompassUpdate)) {
            this.mSetData.CompassUpdate = 0;
            this.mItemLpcz.SetCurVal(this.mSetData.CpsWucha);
            this.mItemLpcz.SetValText(new StringBuilder().append(this.mSetData.CpsWucha).toString());
            if (this.mSetData.CpsDirect < 0 || this.mSetData.CpsDirect >= mStrDirect.length) {
                this.mItemLpfx.SetVal("无信号");
            } else {
                this.mItemLpfx.SetVal(mStrDirect[this.mSetData.CpsDirect]);
            }
            switch (this.mSetData.CpsShowSta) {
                case 0:
                    this.mItemLpjz.SetVal("校准完成");
                    return;
                case 1:
                    this.mItemLpjz.SetVal("正在校准");
                    return;
                case 2:
                    this.mItemLpjz.SetVal("校准失败");
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query2(16);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        Log.d("CanRZygSetCompassActivity", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanRZygSetCompassActivity", "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemLpfx = new CanItemTitleValList(this, R.string.can_lpzx);
        this.mItemLpjz = new CanItemTitleValList(this, R.string.can_znzjz);
        this.mItemLpcz = new CanItemProgressList((Context) this, R.string.can_lppcz);
        this.mItemLpcz.SetMinMax(1, 15);
        this.mItemLpcz.SetIdCallBack(2, this);
        this.mItemLpcz.SetUserValText();
        this.mManager.AddView(this.mItemLpcz.GetView());
        this.mManager.AddView(this.mItemLpfx.GetView());
        this.mManager.AddView(this.mItemLpjz.GetView());
        this.mItemLpjz.GetView().setOnClickListener(this);
        this.mItemLpjz.GetView().setTag(3);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 3; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        switch (item) {
        }
        return i2b(0);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean IsHaveItem = IsHaveItem(item);
        switch (item) {
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

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                if (this.mSetData.CpsShowSta == 1) {
                    CarCompassSet(this.mSetData.CpsWucha, 0);
                    return;
                } else {
                    CarCompassSet(this.mSetData.CpsWucha, 128);
                    return;
                }
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
        if (2 == id) {
            CarCompassSet(pos, this.mSetData.CpsShowSta == 0 ? 0 : 128);
        }
    }
}
