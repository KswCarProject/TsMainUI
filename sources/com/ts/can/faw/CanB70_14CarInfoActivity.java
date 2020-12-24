package com.ts.can.faw;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.ts.other.ParamButton;

public class CanB70_14CarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemProgressList.onPosChange, CanItemPopupList.onPopItemClick {
    public static final int ID_HOUR_DEC = 17;
    public static final int ID_HOUR_INC = 16;
    public static final int ID_MIN_DEC = 19;
    public static final int ID_MIN_INC = 18;
    public static final int ITEM_FMT = 3;
    public static final int ITEM_HOUR = 1;
    private static final int ITEM_MAX = 3;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_MINUTE = 2;
    public static final String TAG = "CanB70_14CarInfoActivity";
    private static final int[] mFmtArr = {R.string.can24_hours, R.string.can12_hours};
    private CanItemTextBtnList mItemHour;
    private CanItemTextBtnList mItemMinute;
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
        this.mItemHour = AddTextBtn(R.string.can_hour, 1);
        this.mItemMinute = AddTextBtn(R.string.can_min, 2);
        AddPopupItem(R.string.can_time_format, mFmtArr, 3);
        AddIncButton((ViewGroup) this.mItemHour.GetView(), 16);
        AddIncButton((ViewGroup) this.mItemMinute.GetView(), 18);
        AddDecButton((ViewGroup) this.mItemHour.GetView(), 17);
        AddDecButton((ViewGroup) this.mItemMinute.GetView(), 19);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 3; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        return i2b(0);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean IsHaveItem = IsHaveItem(item);
    }

    public ParamButton AddIncButton(ViewGroup p, int id) {
        ParamButton btn = new ParamButton(this);
        btn.setStateUpDn(R.drawable.can_seekbar_inc_up, R.drawable.can_seekbar_inc_dn);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        AddViewWrapContent(p, btn, 925, 5);
        return btn;
    }

    public ParamButton AddDecButton(ViewGroup p, int id) {
        ParamButton btn = new ParamButton(this);
        btn.setStateUpDn(R.drawable.can_seekbar_dec_up, R.drawable.can_seekbar_dec_dn);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        AddViewWrapContent(p, btn, 755, 5);
        return btn;
    }

    public void AddViewWrapContent(ViewGroup p, View view, int x, int y) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(-2, -2);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        p.addView(view);
    }

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        btn.ShowArrow(false);
        return btn;
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
    public CanItemProgressList AddProgressItem(int resId, int Id) {
        CanItemProgressList item = new CanItemProgressList((Context) this, resId);
        item.SetIdCallBack(Id, this);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 16:
                CanJni.B70TimeSet(1);
                return;
            case 17:
                CanJni.B70TimeSet(2);
                return;
            case 18:
                CanJni.B70TimeSet(3);
                return;
            case 19:
                CanJni.B70TimeSet(4);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    public void onItem(int id, int item) {
        if (3 == id) {
            CanJni.B70TimeFormat(item);
        }
    }
}
