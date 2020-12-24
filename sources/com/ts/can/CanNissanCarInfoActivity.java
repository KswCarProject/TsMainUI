package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.nissan.CanNissanCarLangSetActivity;
import com.ts.can.nissan.juke.CanNissanJukeCarSetActivity;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanNissanCarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CARMERA = 1;
    public static final int ITEM_CAR_LANG = 4;
    public static final int ITEM_CAR_SET = 3;
    private static final int ITEM_MAX = 4;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_TYPE = 2;
    public static final String TAG = "CanNissanCarInfoActivity";
    private CanItemSwitchList mItemCamera;
    private CanItemTextBtnList mItemCarLang;
    private CanItemTextBtnList mItemCarSet;
    private CanItemCarType mItemCarType;
    private CanScrollList mManager;
    protected String[] mTypeArr;
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
        Log.d("CanNissanCarInfoActivity", "subtype = " + CanJni.GetSubType());
        this.mItemCarType.GetPopItem().SetSel(CanJni.GetSubType());
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mTypeArr = getResources().getStringArray(R.array.can_fs_declare_6);
        this.mItemCarType = new CanItemCarType((Context) this, R.string.can_car_type_select, this.mTypeArr, 2, (CanItemPopupList.onPopItemClick) this);
        this.mManager.AddView(this.mItemCarType.GetView());
        this.mItemCarSet = new CanItemTextBtnList((Context) this, R.string.can_mzd_cx4_setup);
        this.mItemCarSet.SetIdClickListener(this, 3);
        this.mManager.AddView(this.mItemCarSet.GetView());
        this.mItemCarLang = new CanItemTextBtnList((Context) this, R.string.can_car_lang);
        this.mItemCarLang.SetIdClickListener(this, 4);
        this.mManager.AddView(this.mItemCarLang.GetView());
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 4; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 3:
                if (CanJni.GetSubType() == 4) {
                    ret = 1;
                    break;
                }
                break;
            case 4:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 3:
                this.mItemCarSet.ShowGone(show);
                return;
            case 4:
                this.mItemCarLang.ShowGone(show);
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
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                FtSet.SetCanSubT(Neg(FtSet.GetCanSubT()));
                ResetData(false);
                return;
            case 3:
                enterSubWin(CanNissanJukeCarSetActivity.class);
                return;
            case 4:
                enterSubWin(CanNissanCarLangSetActivity.class);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        if (id == 2) {
            Log.d("CanNissanCarInfoActivity", "Select = " + item);
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }
}
