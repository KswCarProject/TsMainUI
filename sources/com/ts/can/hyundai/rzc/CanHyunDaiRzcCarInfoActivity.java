package com.ts.can.hyundai.rzc;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanHyunDaiRzcCarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_AIR = 4;
    public static final int ITEM_CAR_PARKING_SET = 3;
    public static final int ITEM_CAR_SET = 2;
    public static final int ITEM_CDSZ = 10;
    public static final int ITEM_ECO = 7;
    public static final int ITEM_JSMS = 11;
    private static final int ITEM_MAX = 11;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_NLLCT = 9;
    public static final int ITEM_NYXX = 8;
    public static final int ITEM_SYFWD = 5;
    public static final int ITEM_TYPE = 1;
    public static final int ITEM_YYSZ = 6;
    public static final String TAG = "CanHyunDaiRzcCarInfoActivity";
    private CanItemTextBtnList mItemAir;
    private CanItemTextBtnList mItemCarSet;
    private CanItemCarType mItemCarType;
    private CanItemTextBtnList mItemCdsz;
    private CanItemTextBtnList mItemEco;
    private CanItemTextBtnList mItemJsms;
    private CanItemTextBtnList mItemNllct;
    private CanItemTextBtnList mItemNyxx;
    private CanItemTextBtnList mItemParkingSet;
    private CanItemTextBtnList mItemSyfwd;
    private CanItemTextBtnList mItemYysz;
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
        LayoutUI();
        Log.d(TAG, "subtype = " + CanJni.GetSubType());
        this.mItemCarType.GetPopItem().SetSel(CanJni.GetSubType());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mTypeArr = getResources().getStringArray(R.array.can_fs_declare_102);
        this.mItemCarType = new CanItemCarType((Context) this, R.string.can_car_type_select, this.mTypeArr, 1, (CanItemPopupList.onPopItemClick) this);
        this.mManager.AddView(this.mItemCarType.GetView());
        this.mItemCarSet = AddTextBtn(R.string.can_car_set, 2);
        this.mItemParkingSet = AddTextBtn(R.string.can_bcdhsz, 3);
        this.mItemAir = AddTextBtn(R.string.can_ac_set, 4);
        this.mItemSyfwd = AddTextBtn(R.string.can_wc_car_inner_light, 5);
        this.mItemYysz = AddTextBtn(R.string.can_gc_nlsz, 6);
        this.mItemEco = AddTextBtn(R.string.can_golf_seat_drive_eco, 7);
        this.mItemNyxx = AddTextBtn(R.string.can_nyxx, 8);
        this.mItemCdsz = AddTextBtn(R.string.can_cdsz, 10);
        this.mItemJsms = AddTextBtn(R.string.can_psa_wc_jsms, 11);
        this.mItemNllct = AddTextBtn(R.string.can_nllct, 9);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 11; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = 0;
                break;
            case 2:
                ret = 1;
                break;
            case 3:
                if (CanJni.GetSubType() == 10 || CanJni.GetSubType() == 9 || 11 == CanJni.GetSubType() || 12 == CanJni.GetSubType() || 13 == CanJni.GetSubType() || CanJni.GetSubType() == 14) {
                    ret = 1;
                    break;
                }
            case 4:
                if (CanJni.GetSubType() == 14) {
                    ret = 1;
                    break;
                }
                break;
            case 5:
                if (CanJni.GetSubType() == 14) {
                    ret = 1;
                    break;
                }
                break;
            case 6:
                if (CanJni.GetSubType() == 13 || CanJni.GetSubType() == 15 || CanJni.GetSubType() == 5 || CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 16) {
                    ret = 1;
                    break;
                }
            case 7:
            case 8:
            case 9:
                if (CanJni.GetSubType() == 13 || CanJni.GetSubType() == 15 || CanJni.GetSubType() == 16) {
                    ret = 1;
                    break;
                }
            case 10:
            case 11:
                if (CanJni.GetSubType() == 16) {
                    ret = 1;
                    break;
                }
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 2:
                this.mItemCarSet.ShowGone(show);
                return;
            case 3:
                this.mItemParkingSet.ShowGone(show);
                return;
            case 4:
                this.mItemAir.ShowGone(show);
                return;
            case 5:
                this.mItemSyfwd.ShowGone(show);
                return;
            case 6:
                this.mItemYysz.ShowGone(show);
                return;
            case 7:
                this.mItemEco.ShowGone(show);
                return;
            case 8:
                this.mItemNyxx.ShowGone(show);
                return;
            case 9:
                this.mItemNllct.ShowGone(show);
                return;
            case 10:
                this.mItemCdsz.ShowGone(show);
                return;
            case 11:
                this.mItemJsms.ShowGone(show);
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
        btn.ShowGone(false);
        return btn;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                enterSubWin(CanHyunDaiRzcCarSetActivity.class);
                return;
            case 3:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 1);
                return;
            case 4:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 2);
                return;
            case 5:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 3);
                return;
            case 6:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 4);
                return;
            case 7:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 5);
                return;
            case 8:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 6);
                return;
            case 9:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 7);
                return;
            case 10:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 8);
                return;
            case 11:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 9);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            Log.d(TAG, "Select = " + item);
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }
}
