package com.ts.can.gm.comm;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGMSetCDSActivity extends CanGMBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CDBHTS = 8;
    public static final int ITEM_CYMQBJ = 1;
    public static final int ITEM_HFCLTGJS = 7;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_QCZTTZ = 2;
    public static final int ITEM_QZXRJC = 6;
    public static final int ITEM_TSLX = 5;
    public static final int ITEM_ZDFZZB = 3;
    public static final int ITEM_ZSYXHZDTX = 4;
    public static final String TAG = "CanGMCarInfoActivity";
    private static final int[] mTslxArr = {R.string.can_bjying, R.string.can_aqtszy};
    private static final int[] mZdfzzbArr = {R.string.can_off, R.string.can_baojing, R.string.can_bjhzd};
    private CanDataInfo.GM_AdtPzjc mAdtPzjcData = new CanDataInfo.GM_AdtPzjc();
    private CanItemSwitchList mItemCdbhts;
    private CanItemSwitchList mItemCymqbj;
    private CanItemSwitchList mItemHfcltgjs;
    private CanItemSwitchList mItemQczttz;
    private CanItemPopupList mItemQzxrjc;
    private CanItemPopupList mItemTslx;
    private CanItemPopupList mItemZdfzzb;
    private CanItemSwitchList mItemZsyxhzdtx;
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
        CanJni.GMGetAdtPzjc(this.mAdtPzjcData);
        if (i2b(this.mAdtPzjcData.UpdateOnce)) {
            if (!check || i2b(this.mAdtPzjcData.Update)) {
                this.mAdtPzjcData.Update = 0;
                LayoutUI();
                check = false;
                this.mbLayout = true;
            }
            if (!i2b(this.mSetData.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mSetData.Update)) {
                Log.d("CanGMCarInfoActivity", "mSetData.UpdateOnce");
                this.mSetData.Update = 0;
                this.mItemCymqbj.SetCheck(this.mSetData.CYMQJB);
                this.mItemQczttz.SetCheck(this.mSetData.QCZTTZ);
                this.mItemZsyxhzdtx.SetCheck(this.mSetData.Zsyxhqdtx);
                if (3 == this.mSetData.ZDFFZB) {
                    this.mItemZdfzzb.SetSel(2);
                } else {
                    this.mItemZdfzzb.SetSel(this.mSetData.ZDFFZB);
                }
                this.mItemTslx.SetSel(this.mSetData.Tslx);
                this.mItemQzxrjc.SetSel(this.mSetData.Qzxrjc);
                this.mItemHfcltgjs.SetCheck(this.mSetData.Hfcltgjs);
                this.mItemCdbhts.SetCheck(this.mSetData.Cdbhts);
            }
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
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemCymqbj = AddCheckItem(R.string.can_cymqbj, 1);
        this.mItemQczttz = AddCheckItem(R.string.can_qczttz, 2);
        this.mItemZdfzzb = AddPopupItem(R.string.can_zdfzzb, mZdfzzbArr, 3);
        this.mItemZsyxhzdtx = AddCheckItem(R.string.can_gl8_2017_zsyxhzdtx, 4);
        this.mItemTslx = AddPopupItem(R.string.can_tslx, mTslxArr, 5);
        this.mItemQzxrjc = AddPopupItem(R.string.can_qzxrjc, mZdfzzbArr, 6);
        this.mItemHfcltgjs = AddCheckItem(R.string.can_hfcltgjs, 7);
        this.mItemCdbhts = AddCheckItem(R.string.can_cdbhjs, 8);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 8; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtPzjcData.CYMQJB;
                break;
            case 2:
                ret = this.mAdtPzjcData.QCZTTZ;
                break;
            case 3:
                ret = this.mAdtPzjcData.ZDFFZB;
                break;
            case 4:
                ret = this.mAdtPzjcData.Zsyxhqdtx;
                break;
            case 5:
                ret = this.mAdtPzjcData.Tslx;
                break;
            case 6:
                ret = this.mAdtPzjcData.Qzxrjc;
                break;
            case 7:
                ret = this.mAdtPzjcData.Hfcltgjs;
                break;
            case 8:
                ret = this.mAdtPzjcData.Cdbhts;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemCymqbj.ShowGone(show);
                return;
            case 2:
                this.mItemQczttz.ShowGone(show);
                return;
            case 3:
                this.mItemZdfzzb.ShowGone(show);
                return;
            case 4:
                this.mItemZsyxhzdtx.ShowGone(show);
                return;
            case 5:
                this.mItemTslx.ShowGone(show);
                return;
            case 6:
                this.mItemQzxrjc.ShowGone(show);
                return;
            case 7:
                this.mItemHfcltgjs.ShowGone(show);
                return;
            case 8:
                this.mItemCdbhts.ShowGone(show);
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

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GMCarCtrl(22, Neg(this.mSetData.CYMQJB));
                return;
            case 2:
                if (CanJni.GetSubType() == 11) {
                    CanJni.GMCarCtrl(25, Neg(this.mSetData.QCZTTZ));
                    return;
                } else {
                    CanJni.GMCarCtrl(26, Neg(this.mSetData.QCZTTZ));
                    return;
                }
            case 4:
                CanJni.GMCarCtrl(82, Neg(this.mSetData.Zsyxhqdtx));
                return;
            case 7:
                CanJni.GMCarCtrl(86, Neg(this.mSetData.Hfcltgjs));
                return;
            case 8:
                CanJni.GMCarCtrl(87, Neg(this.mSetData.Cdbhts));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 3:
                if (CanJni.GetSubType() == 9 || CanJni.GetSubType() == 11) {
                    CanJni.GMCarCtrl(24, item);
                    return;
                } else {
                    CanJni.GMCarCtrl(25, item);
                    return;
                }
            case 5:
                CanJni.GMCarCtrl(84, item);
                return;
            case 6:
                CanJni.GMCarCtrl(85, item);
                return;
            default:
                return;
        }
    }
}
