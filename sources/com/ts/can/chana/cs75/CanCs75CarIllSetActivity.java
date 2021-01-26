package com.ts.can.chana.cs75;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanCs75CarIllSetActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    public static final int ITEM_FWDGN = 10;
    public static final int ITEM_FWDLD = 4;
    public static final int ITEM_FWDLDDJ = 6;
    public static final int ITEM_FWDYS = 7;
    public static final int ITEM_FWDYYSDGN = 11;
    public static final int ITEM_FWDZDTJ = 8;
    public static final int ITEM_FWDZDTJFS = 9;
    private static final int ITEM_MAX = 11;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_RJXCD = 3;
    public static final int ITEM_YJZX = 2;
    public static final int ITEM_YZDYS = 1;
    public static final int ITEM_ZDYGD = 5;
    public static final String TAG = "CanCs75CarIllSetActivity";
    private static final int[] mFWDYSArr = {R.string.can_color_red, R.string.can_purple, R.string.can_color_blue, R.string.can_magoten_light_color_4, R.string.can_orange_color, R.string.can_bao_blue, R.string.can_magoten_light_color_2};
    private static final int[] mFwdZdtjfs = {R.string.can_driving_dark, R.string.can_driving_off};
    private static final int[] mQzdysArr = {R.string.can_mzd_cx4_mode_off, R.string.can_10s, R.string.can_30s, R.string.can_60s, R.string.can_mzd_cx4_time_120s};
    private static final int[] mYjzxArr = {R.string.can_mzd_cx4_mode_off, R.string.can_3c, R.string.can_5c, R.string.can_7c};
    protected CanItemPopupList mItemFwdYs;
    protected CanItemSwitchList mItemFwdYysdgn;
    protected CanItemSwitchList mItemFwdgn;
    protected CanItemSwitchList mItemFwdld;
    private CanItemProgressList mItemFwdlddj;
    protected CanItemSwitchList mItemFwdzdtj;
    protected CanItemPopupList mItemFwdzdtjfs;
    protected CanItemPopupList mItemQzdys;
    protected CanItemSwitchList mItemRjxcd;
    protected CanItemPopupList mItemYjzx;
    protected CanItemSwitchList mItemZdygd;
    private CanScrollList mManager;
    protected CanDataInfo.CS75CarInfo mSetData = new CanDataInfo.CS75CarInfo();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
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

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.Cs75GetCarInfo(this.mSetData);
        if (i2b(this.mSetData.QzdysUpdateOnce) && (!check || i2b(this.mSetData.QzdysUpdate))) {
            this.mSetData.QzdysUpdate = 0;
            this.mItemQzdys.SetSel(this.mSetData.Qzdys);
        }
        if (i2b(this.mSetData.YjzxUpdateOnce) && (!check || i2b(this.mSetData.YjzxUpdate))) {
            this.mSetData.YjzxUpdate = 0;
            this.mItemYjzx.SetSel(this.mSetData.Yjzx);
        }
        if (i2b(this.mSetData.RjxcdUpdateOnce) && (!check || i2b(this.mSetData.RjxcdUpdate))) {
            this.mSetData.RjxcdUpdate = 0;
            this.mItemRjxcd.SetCheck(SwSet(this.mSetData.Rjxcd));
        }
        if (i2b(this.mSetData.FwdldUpdateOnce) && (!check || i2b(this.mSetData.FwdldUpdate))) {
            this.mSetData.FwdldUpdate = 0;
            this.mItemFwdld.SetCheck(SwSet(this.mSetData.Fwdld));
        }
        if (i2b(this.mSetData.ZdygdUpdateOnce) && (!check || i2b(this.mSetData.ZdygdUpdate))) {
            this.mSetData.ZdygdUpdate = 0;
            this.mItemZdygd.SetCheck(SwSet(this.mSetData.Zdygd));
        }
        if (i2b(this.mSetData.FwdlddjUpdateOnce) && (!check || i2b(this.mSetData.FwdlddjUpdate))) {
            this.mSetData.FwdlddjUpdate = 0;
            this.mItemFwdlddj.SetCurVal(this.mSetData.Fwdlddj);
        }
        if (i2b(this.mSetData.FwdysUpdateOnce) && (!check || i2b(this.mSetData.FwdysUpdate))) {
            this.mSetData.FwdysUpdate = 0;
            this.mItemFwdYs.SetSel(this.mSetData.Fwdys - 1);
        }
        if (i2b(this.mSetData.FwdzdtjUpdateOnce) && (!check || i2b(this.mSetData.FwdzdtjUpdate))) {
            this.mSetData.FwdzdtjUpdate = 0;
            this.mItemFwdzdtj.SetCheck(SwSet(this.mSetData.Fwdzdtj));
        }
        if (i2b(this.mSetData.FwdzdtjfsUpdateOnce) && (!check || i2b(this.mSetData.FwdzdtjfsUpdate))) {
            this.mSetData.FwdzdtjfsUpdate = 0;
            this.mItemFwdzdtjfs.SetSel(this.mSetData.Fwdzdtjfs - 1);
        }
        if (i2b(this.mSetData.FwdgnUpdateOnce) && (!check || i2b(this.mSetData.FwdgnUpdate))) {
            this.mSetData.FwdgnUpdate = 0;
            this.mItemFwdgn.SetCheck(this.mSetData.Fwdgn - 1);
        }
        if (!i2b(this.mSetData.FwdyysdgnUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.FwdyysdgnUpdate)) {
            this.mSetData.FwdyysdgnUpdate = 0;
            this.mItemFwdYysdgn.SetCheck(this.mSetData.Fwdyysdgn - 1);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.Cs75CarQuery(82, 10);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 11);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 24);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 25);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 26);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 50);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 54);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 55);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 71);
        Sleep(10);
        CanJni.Cs75CarQuery(82, 72);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
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
        this.mItemQzdys = AddPopupItem(R.string.can_tigger7_light_delay, mQzdysArr, 1);
        this.mItemYjzx = AddPopupItem(R.string.can_yjzx, mYjzxArr, 2);
        this.mItemRjxcd = AddCheckItem(R.string.can_tigger7_day_light, 3);
        this.mItemFwdld = AddCheckItem(R.string.can_env_light, 4);
        this.mItemZdygd = AddCheckItem(R.string.can_light_zdygd, 5);
        this.mItemFwdlddj = this.mManager.addItemProgressList(R.string.can_fwdlddj, 6, (CanItemProgressList.onPosChange) this);
        this.mItemFwdlddj.SetStep(1);
        this.mItemFwdlddj.SetMinMax(0, 6);
        this.mItemFwdYs = AddPopupItem(R.string.can_fwd_color, mFWDYSArr, 7);
        this.mItemFwdzdtj = AddCheckItem(R.string.can_fwd_auto_adjust, 8);
        this.mItemFwdzdtjfs = AddPopupItem(R.string.can_fwd_auto_adjust_method, mFwdZdtjfs, 9);
        this.mItemFwdgn = AddCheckItem(R.string.can_fwd_kg, 10);
        this.mItemFwdYysdgn = AddCheckItem(R.string.can_fwdyyld, 11);
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
                ret = 1;
                break;
            case 2:
                ret = 1;
                break;
            case 3:
                if (CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 4:
                if (CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 5:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 6:
                if (CanJni.GetSubType() != 11) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 7:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 18) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
                break;
            case 8:
                if (CanJni.GetSubType() != 12) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 9:
                if (CanJni.GetSubType() != 12) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 10:
            case 11:
                if (CanJni.GetSubType() != 18) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemQzdys.ShowGone(show);
                return;
            case 2:
                this.mItemYjzx.ShowGone(show);
                return;
            case 3:
                this.mItemRjxcd.ShowGone(show);
                return;
            case 4:
                this.mItemFwdld.ShowGone(show);
                return;
            case 5:
                this.mItemZdygd.ShowGone(show);
                return;
            case 6:
                this.mItemFwdlddj.ShowGone(show);
                return;
            case 7:
                this.mItemFwdYs.ShowGone(show);
                return;
            case 8:
                this.mItemFwdzdtj.ShowGone(show);
                return;
            case 9:
                this.mItemFwdzdtjfs.ShowGone(show);
                return;
            case 10:
                this.mItemFwdgn.ShowGone(show);
                return;
            case 11:
                this.mItemFwdYysdgn.ShowGone(show);
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
    public CanItemIcoList AddIcoItem(int ico, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                CanJni.Cs75CarSet(24, NegSwSet(this.mSetData.Rjxcd));
                return;
            case 4:
                CanJni.Cs75CarSet(25, NegSwSet(this.mSetData.Fwdld));
                return;
            case 5:
                CanJni.Cs75CarSet(26, NegSwSet(this.mSetData.Zdygd));
                return;
            case 8:
                CanJni.Cs75CarSet(54, NegSwSet(this.mSetData.Fwdzdtj));
                return;
            case 10:
                CanJni.Cs75CarSet(71, NegSwSet(this.mSetData.Fwdgn));
                return;
            case 11:
                CanJni.Cs75CarSet(72, NegSwSet(this.mSetData.Fwdyysdgn));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.Cs75CarSet(10, item);
                return;
            case 2:
                CanJni.Cs75CarSet(11, item);
                return;
            case 7:
                CanJni.Cs75CarSet(50, item + 1);
                return;
            case 9:
                CanJni.Cs75CarSet(55, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 6:
                CanJni.Cs75CarSet(39, pos);
                return;
            default:
                return;
        }
    }
}
