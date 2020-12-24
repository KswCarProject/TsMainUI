package com.ts.can.gm.comm;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGMSetConvActivity extends CanGMBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CLZDBC = 11;
    public static final int ITEM_DCHSJZDFZ = 12;
    public static final int ITEM_DCHYSZDQD = 1;
    public static final int ITEM_DDHSJQX = 5;
    public static final int ITEM_DYJYWZ = 3;
    public static final int ITEM_HSJZDZD = 6;
    public static final int ITEM_JSYBLXC = 4;
    public static final int ITEM_JSYGXSZ = 2;
    public static final int ITEM_JSYYSZDSB = 14;
    public static final int ITEM_JSYZYZDHW = 13;
    private static final int ITEM_MAX = 16;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_MRB_DDHSJQX = 8;
    public static final int ITEM_PDQBFZ = 9;
    public static final int ITEM_REARTX = 10;
    public static final int ITEM_YDZX = 15;
    public static final int ITEM_ZDYS = 7;
    public static final int ITEM_ZSYQZD = 16;
    public static final String TAG = "CanGMCarInfoActivity";
    private static final int[] mClzdbcArr = {R.string.can_cxzdzczd, R.string.can_dxzdzczd};
    private static final int[] mMrbDchsjzdqxArr = {R.string.can_off, R.string.can_ckhjsy, R.string.can_jiashiyuan, R.string.can_chengk};
    private static final int[] mPdqbfzArr = {R.string.can_bzzd, R.string.can_gjzd};
    private static final int[] mZsyqzdArr = {R.string.can_mzd_cx4_mode_off, R.string.can_jlhwdzm, R.string.can_zndgfp};
    private CanDataInfo.GM_AdtConv mAdtConvData = new CanDataInfo.GM_AdtConv();
    private CanItemPopupList mItemClzdbc;
    private CanItemSwitchList mItemDchsjzdfz;
    private CanItemSwitchList mItemDchyszdqd;
    private CanItemSwitchList mItemDdhsjqx;
    private CanItemSwitchList mItemDyjywz;
    private CanItemSwitchList mItemHsjzdzd;
    private CanItemSwitchList mItemJsyblxc;
    private CanItemSwitchList mItemJsygxhsz;
    private CanItemSwitchList mItemJsyyszdsb;
    private CanItemSwitchList mItemJsyzyzdhw;
    private CanItemPopupList mItemMrbDchsjzdqx;
    private CanItemPopupList mItemPdqbfz;
    private CanItemSwitchList mItemRearTx;
    private CanItemSwitchList mItemYdzx;
    private CanItemSwitchList mItemZdys;
    private CanItemPopupList mItemZsyqzd;
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
        CanJni.GMGetAdtConv(this.mAdtConvData);
        if (i2b(this.mAdtConvData.UpdateOnce)) {
            if (!check || i2b(this.mAdtConvData.Update)) {
                this.mAdtConvData.Update = 0;
                LayoutUI();
                check = false;
                this.mbLayout = true;
            }
            if (!i2b(this.mSetData.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mSetData.Update)) {
                this.mSetData.Update = 0;
                this.mItemDchyszdqd.SetCheck(this.mSetData.DCYHSZDQD);
                this.mItemJsygxhsz.SetCheck(this.mSetData.PersonByDriver);
                this.mItemDyjywz.SetCheck(this.mSetData.AutoMemRecall);
                this.mItemJsyblxc.SetCheck(this.mSetData.EasyExitSeat);
                if (CanJni.GetSubType() == 7 || CanJni.GetSubType() == 8) {
                    this.mItemMrbDchsjzdqx.SetSel(this.mSetData.RevTiltMirror);
                } else {
                    this.mItemDdhsjqx.SetCheck(this.mSetData.RevTiltMirror);
                }
                this.mItemHsjzdzd.SetCheck(this.mSetData.AutoMirrorFold);
                this.mItemZdys.SetCheck(this.mSetData.ZDYX);
                this.mItemPdqbfz.SetSel(this.mSetData.Pdqbfz);
                this.mItemRearTx.SetCheck(this.mSetData.RearTx);
                this.mItemClzdbc.SetSel(this.mSetData.Clzdbc);
                this.mItemDchsjzdfz.SetCheck(this.mSetData.Dchsjzdfz);
                this.mItemJsyzyzdhw.SetCheck(this.mSetData.Jsyzyzdhw);
                this.mItemJsyyszdsb.SetCheck(this.mSetData.Zsjyszdsb);
                this.mItemYdzx.SetCheck(this.mSetData.Ydzx);
                this.mItemZsyqzd.SetSel(this.mSetData.Zsyqzd);
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
        this.mItemDchyszdqd = AddCheckItem(R.string.can_dcyhszdqd, 1);
        this.mItemJsygxhsz = AddCheckItem(R.string.can_personalization, 2);
        this.mItemDyjywz = AddCheckItem(R.string.can_dyjywz, 3);
        this.mItemJsyblxc = AddCheckItem(R.string.can_jsyblxc, 4);
        this.mItemDdhsjqx = AddCheckItem(R.string.can_dchsjzdqx, 5);
        this.mItemHsjzdzd = AddCheckItem(R.string.can_zdhsjzd, 6);
        this.mItemZdys = AddCheckItem(R.string.can_zdys, 7);
        this.mItemRearTx = AddCheckItem(R.string.can_rear_tx, 10);
        this.mItemDchsjzdfz = AddCheckItem(R.string.can_dchsjzdfz, 12);
        this.mItemJsyzyzdhw = AddCheckItem(R.string.can_jsyzyzdhw, 13);
        this.mItemJsyyszdsb = AddCheckItem(R.string.can_jsyyszdsb, 14);
        this.mItemYdzx = AddCheckItem(R.string.can_sport_turn, 15);
        this.mItemMrbDchsjzdqx = AddPopupItem(R.string.can_dchsjzdqx, mMrbDchsjzdqxArr, 8);
        this.mItemPdqbfz = AddPopupItem(R.string.can_teramont_pdqbfz, mPdqbfzArr, 9);
        this.mItemClzdbc = AddPopupItem(R.string.can_clzdbc, mClzdbcArr, 11);
        this.mItemZsyqzd = AddPopupItem(R.string.can_adt_front_light, mZsyqzdArr, 16);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 16; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtConvData.DCYHSZDQD;
                break;
            case 2:
                ret = this.mAdtConvData.PersonByDriver;
                break;
            case 3:
                ret = this.mAdtConvData.AutoMemRecall;
                break;
            case 4:
                ret = this.mAdtConvData.EasyExitSeat;
                break;
            case 5:
                ret = this.mAdtConvData.RevTiltMirror;
                break;
            case 6:
                ret = this.mAdtConvData.AutoMirrorFold;
                break;
            case 7:
                ret = this.mAdtConvData.ZDYX;
                break;
            case 9:
                ret = this.mAdtConvData.Pdqbfz;
                break;
            case 10:
                ret = this.mAdtConvData.RearTx;
                break;
            case 11:
                ret = this.mAdtConvData.Clzdbc;
                break;
            case 12:
                ret = this.mAdtConvData.Dchsjzdfz;
                break;
            case 13:
                ret = this.mAdtConvData.Jsyzyzdhw;
                break;
            case 14:
                ret = this.mAdtConvData.Zsjyszdsb;
                break;
            case 15:
                ret = this.mAdtConvData.Ydzx;
                break;
            case 16:
                ret = this.mAdtConvData.Zsyqzd;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemDchyszdqd.ShowGone(show);
                return;
            case 2:
                this.mItemJsygxhsz.ShowGone(show);
                return;
            case 3:
                this.mItemDyjywz.ShowGone(show);
                return;
            case 4:
                this.mItemJsyblxc.ShowGone(show);
                return;
            case 5:
                if (CanJni.GetSubType() == 7 || CanJni.GetSubType() == 8 || CanJni.GetSubType() == 11) {
                    this.mItemMrbDchsjzdqx.ShowGone(show);
                    return;
                } else {
                    this.mItemDdhsjqx.ShowGone(show);
                    return;
                }
            case 6:
                this.mItemHsjzdzd.ShowGone(show);
                return;
            case 7:
                this.mItemZdys.ShowGone(show);
                return;
            case 9:
                this.mItemPdqbfz.ShowGone(show);
                return;
            case 10:
                this.mItemRearTx.ShowGone(show);
                return;
            case 11:
                this.mItemClzdbc.ShowGone(show);
                return;
            case 12:
                this.mItemDchsjzdfz.ShowGone(show);
                return;
            case 13:
                this.mItemJsyzyzdhw.ShowGone(show);
                return;
            case 14:
                this.mItemJsyyszdsb.ShowGone(show);
                return;
            case 15:
                this.mItemYdzx.ShowGone(show);
                return;
            case 16:
                this.mItemZsyqzd.ShowGone(show);
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
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GMCarCtrl(9, Neg(this.mSetData.DCYHSZDQD));
                return;
            case 2:
                CanJni.GMCarCtrl(14, Neg(this.mSetData.PersonByDriver));
                return;
            case 3:
                if (CanJni.GetSubType() == 11) {
                    CanJni.GMCarCtrl(88, Neg(this.mSetData.AutoMemRecall));
                    return;
                } else {
                    CanJni.GMCarCtrl(21, Neg(this.mSetData.AutoMemRecall));
                    return;
                }
            case 4:
                if (CanJni.GetSubType() == 11) {
                    CanJni.GMCarCtrl(89, Neg(this.mSetData.EasyExitSeat));
                    return;
                } else {
                    CanJni.GMCarCtrl(20, Neg(this.mSetData.EasyExitSeat));
                    return;
                }
            case 5:
                CanJni.GMCarCtrl(19, Neg(this.mSetData.RevTiltMirror));
                return;
            case 6:
                if (CanJni.GetSubType() == 11) {
                    CanJni.GMCarCtrl(91, Neg(this.mSetData.AutoMirrorFold));
                    return;
                } else {
                    CanJni.GMCarCtrl(18, Neg(this.mSetData.AutoMirrorFold));
                    return;
                }
            case 7:
                if (CanJni.GetSubType() == 9 || CanJni.GetSubType() == 11) {
                    CanJni.GMCarCtrl(26, Neg(this.mSetData.ZDYX));
                    return;
                } else {
                    CanJni.GMCarCtrl(24, Neg(this.mSetData.ZDYX));
                    return;
                }
            case 10:
                CanJni.GMCarCtrl(83, Neg(this.mSetData.RearTx));
                return;
            case 12:
                CanJni.GMCarCtrl(19, Neg(this.mSetData.Dchsjzdfz));
                return;
            case 13:
                CanJni.GMCarCtrl(20, Neg(this.mSetData.Jsyzyzdhw));
                return;
            case 14:
                CanJni.GMCarCtrl(21, Neg(this.mSetData.Zsjyszdsb));
                return;
            case 15:
                CanJni.GMCarCtrl(93, Neg(this.mSetData.Ydzx));
                return;
            default:
                return;
        }
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 8:
                if (CanJni.GetSubType() == 11) {
                    CanJni.GMCarCtrl(90, item);
                    return;
                } else {
                    CanJni.GMCarCtrl(19, item);
                    return;
                }
            case 9:
                CanJni.GMCarCtrl(81, item);
                return;
            case 11:
                CanJni.GMCarCtrl(92, item);
                return;
            case 16:
                CanJni.GMCarCtrl(94, item);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
