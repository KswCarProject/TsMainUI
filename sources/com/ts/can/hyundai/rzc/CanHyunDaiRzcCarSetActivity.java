package com.ts.can.hyundai.rzc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanHyunDaiRzcCarSetActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_BCKQHZSXT = 7;
    public static final int ITEM_DSPZYKBZD_L = 3;
    public static final int ITEM_DSPZYKBZD_R = 4;
    public static final int ITEM_FXPJR = 5;
    private static final int ITEM_MAX = 7;
    public static final int ITEM_MDJCQ = 1;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_ZDTJWD = 2;
    public static final int ITEM_ZYJRTF = 6;
    public static final String TAG = "CanHyunDaiRzcCarSetActivity";
    private CanItemSwitchList mItemBckhzsxt;
    private CanItemSwitchList mItemDspzykbzd_L;
    private CanItemSwitchList mItemDspzykbzd_R;
    private CanItemSwitchList mItemFxpjr;
    private CanItemSwitchList mItemMdjcq;
    private CanItemSwitchList mItemZdtjwd;
    private CanItemSwitchList mItemZyjrtf;
    private CanScrollList mManager;
    protected CanDataInfo.HyCarSet mSetData = new CanDataInfo.HyCarSet();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.HyundaiRzcGetCarSet(this.mSetData);
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            this.mItemMdjcq.SetCheck(SwSet(this.mSetData.Mdjcq));
        }
        if (i2b(this.mSetData.ZdtjwdUpdateOnce) && (!check || i2b(this.mSetData.ZdtjwdUpdate))) {
            this.mSetData.ZdtjwdUpdate = 0;
            this.mItemZdtjwd.SetCheck(SwSet(this.mSetData.Zdtjwd));
        }
        if (i2b(this.mSetData.DspzczykbzdUpdateOnce) && (!check || i2b(this.mSetData.DspzczykbzdUpdate))) {
            this.mSetData.DspzczykbzdUpdate = 0;
            this.mItemDspzykbzd_L.SetCheck(SwSet(this.mSetData.Dspzczykbzd));
        }
        if (i2b(this.mSetData.DspyczykbzdUpdateOnce) && (!check || i2b(this.mSetData.DspyczykbzdUpdate))) {
            this.mSetData.DspyczykbzdUpdate = 0;
            this.mItemDspzykbzd_R.SetCheck(SwSet(this.mSetData.Dspyczykbzd));
        }
        if (i2b(this.mSetData.FxpjrqUpdateOnce) && (!check || i2b(this.mSetData.FxpjrqUpdate))) {
            this.mSetData.FxpjrqUpdate = 0;
            this.mItemFxpjr.SetCheck(SwSet(this.mSetData.Fxpjrq));
        }
        if (i2b(this.mSetData.ZyjrtfUpdateOnce) && (!check || i2b(this.mSetData.ZyjrtfUpdate))) {
            this.mSetData.ZyjrtfUpdate = 0;
            this.mItemZyjrtf.SetCheck(SwSet(this.mSetData.Zyjrtf));
        }
        if (!i2b(this.mSetData.BckqhzsxtUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.BckqhzsxtUpdate)) {
            this.mSetData.BckqhzsxtUpdate = 0;
            this.mItemBckhzsxt.SetCheck(SwSet(this.mSetData.Bckqhzsxt));
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
        this.mItemMdjcq = AddCheckItem(R.string.can_car_mdjcq, 1);
        this.mItemZdtjwd = AddCheckItem(R.string.can_zdtjwd, 2);
        this.mItemDspzykbzd_L = AddCheckItem(R.string.can_dspzykbzd_l, 3);
        this.mItemDspzykbzd_R = AddCheckItem(R.string.can_dspzykbzd_r, 4);
        this.mItemFxpjr = AddCheckItem(R.string.can_fxpjr, 5);
        this.mItemZyjrtf = AddCheckItem(R.string.can_seat_hotwind, 6);
        this.mItemBckhzsxt = AddCheckItem(R.string.can_bckqhzsxt, 7);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 7; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        if (com.lgb.canmodule.CanJni.GetSubType() == 10) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        if (com.lgb.canmodule.CanJni.GetSubType() != 9) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
        r0 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0045, code lost:
        if (com.lgb.canmodule.CanJni.GetSubType() == 10) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004b, code lost:
        if (com.lgb.canmodule.CanJni.GetSubType() != 9) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004d, code lost:
        r0 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0052, code lost:
        if (com.lgb.canmodule.CanJni.GetSubType() == 10) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0058, code lost:
        if (com.lgb.canmodule.CanJni.GetSubType() != 9) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005a, code lost:
        r0 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005f, code lost:
        if (com.lgb.canmodule.CanJni.GetSubType() == 10) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0065, code lost:
        if (com.lgb.canmodule.CanJni.GetSubType() != 9) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0067, code lost:
        r0 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x006c, code lost:
        if (com.lgb.canmodule.CanJni.GetSubType() == 10) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0072, code lost:
        if (com.lgb.canmodule.CanJni.GetSubType() != 9) goto L_0x0008;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
        return i2b(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0074, code lost:
        r0 = 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean IsHaveItem(int r6) {
        /*
            r5 = this;
            r4 = 10
            r3 = 9
            r0 = 0
            switch(r6) {
                case 1: goto L_0x000d;
                case 2: goto L_0x000f;
                case 3: goto L_0x0034;
                case 4: goto L_0x0041;
                case 5: goto L_0x004e;
                case 6: goto L_0x005b;
                case 7: goto L_0x0068;
                default: goto L_0x0008;
            }
        L_0x0008:
            boolean r1 = i2b(r0)
            return r1
        L_0x000d:
            r0 = 1
            goto L_0x0008
        L_0x000f:
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            r2 = 8
            if (r1 == r2) goto L_0x0033
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            if (r1 == r4) goto L_0x0033
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            if (r1 == r3) goto L_0x0033
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            r2 = 11
            if (r1 == r2) goto L_0x0033
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            r2 = 13
            if (r1 != r2) goto L_0x0034
        L_0x0033:
            r0 = 1
        L_0x0034:
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            if (r1 == r4) goto L_0x0040
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            if (r1 != r3) goto L_0x0041
        L_0x0040:
            r0 = 1
        L_0x0041:
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            if (r1 == r4) goto L_0x004d
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            if (r1 != r3) goto L_0x004e
        L_0x004d:
            r0 = 1
        L_0x004e:
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            if (r1 == r4) goto L_0x005a
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            if (r1 != r3) goto L_0x005b
        L_0x005a:
            r0 = 1
        L_0x005b:
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            if (r1 == r4) goto L_0x0067
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            if (r1 != r3) goto L_0x0068
        L_0x0067:
            r0 = 1
        L_0x0068:
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            if (r1 == r4) goto L_0x0074
            int r1 = com.lgb.canmodule.CanJni.GetSubType()
            if (r1 != r3) goto L_0x0008
        L_0x0074:
            r0 = 1
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.can.hyundai.rzc.CanHyunDaiRzcCarSetActivity.IsHaveItem(int):boolean");
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemMdjcq.ShowGone(show);
                return;
            case 2:
                this.mItemZdtjwd.ShowGone(show);
                return;
            case 3:
                this.mItemDspzykbzd_L.ShowGone(show);
                return;
            case 4:
                this.mItemDspzykbzd_R.ShowGone(show);
                return;
            case 5:
                this.mItemFxpjr.ShowGone(show);
                return;
            case 6:
                this.mItemZyjrtf.ShowGone(show);
                return;
            case 7:
                this.mItemBckhzsxt.ShowGone(show);
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
        btn.ShowGone(true);
        return btn;
    }

    /* access modifiers changed from: protected */
    public int SwSet(int val) {
        if (1 == val) {
            return 1;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int NegSwSet(int val) {
        if (1 == val) {
            return 2;
        }
        return 1;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.HyundaiRzcCarSet(1, NegSwSet(this.mSetData.Mdjcq));
                return;
            case 2:
                CanJni.HyundaiRzcCarSet(2, NegSwSet(this.mSetData.Zdtjwd));
                return;
            case 3:
                CanJni.HyundaiRzcCarSet(3, NegSwSet(this.mSetData.Dspzczykbzd));
                return;
            case 4:
                CanJni.HyundaiRzcCarSet(4, NegSwSet(this.mSetData.Dspyczykbzd));
                return;
            case 5:
                CanJni.HyundaiRzcCarSet(5, NegSwSet(this.mSetData.Fxpjrq));
                return;
            case 6:
                CanJni.HyundaiRzcCarSet(6, NegSwSet(this.mSetData.Zyjrtf));
                return;
            case 7:
                CanJni.HyundaiRzcCarSet(8, NegSwSet(this.mSetData.Bckqhzsxt));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
