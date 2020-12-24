package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanHondDACarSystemSetActivity extends CanBaseActivity implements View.OnClickListener, CanItemPopupList.onPopItemClick, UserCallBack, CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_DEFAULTALL = 4;
    public static final int ITEM_JTBZSBXTXTB = 6;
    public static final int ITEM_JYWZLD = 8;
    public static final int ITEM_RESETMAINTENANCE = 3;
    public static final int ITEM_SXCWZYD = 9;
    public static final int ITEM_TPMS_RST = 5;
    public static final int ITEM_TTJG = 7;
    public static final int ITEM_ZSBSZ = 1;
    public static final int ITEM_lANGSET = 2;
    public static final String TAG = "CanHondDACarSystemSetActivity";
    private CanItemTextBtnList mItemDefaultAll;
    protected CanItemSwitchList mItemJtbzSbxtXtb;
    protected CanItemSwitchList mItemJywzld;
    protected CanItemPopupList mItemLangSet;
    private CanItemTextBtnList mItemResetMain;
    protected CanItemSwitchList mItemSxcwzyd;
    private CanItemTextBtnList mItemTpmsRst;
    protected CanItemSwitchList mItemTtjg;
    protected CanItemSwitchList mItemZsbsz;
    protected String[] mLangSetArr = {"中文", "English"};
    protected CanScrollList mManager;
    protected CanDataInfo.HondaSetData mSetData = new CanDataInfo.HondaSetData();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemZsbsz = AddCheckItem(R.string.can_system_zsb, 1);
        this.mItemLangSet = AddPopupItem(R.string.can_lang_set, this.mLangSetArr, 2);
        this.mItemResetMain = AddTextBtn(R.string.can_xlbyxxcz, 3);
        this.mItemTpmsRst = AddTextBtn(R.string.can_tpms_set, 5);
        this.mItemDefaultAll = AddTextBtn(R.string.can_hfcsz, 4);
        this.mItemJtbzSbxtXtb = AddCheckItem(R.string.can_traffice_sign_rec, 6);
        this.mItemTtjg = AddCheckItem(R.string.can_ttjg, 7);
        this.mItemJywzld = AddCheckItem(R.string.can_jywzzyld, 8);
        this.mItemSxcwzyd = AddCheckItem(R.string.can_sxcwzyd, 9);
        if (!(CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 12 || CanJni.GetSubType() == 11)) {
            this.mItemJtbzSbxtXtb.ShowGone(false);
            this.mItemTtjg.ShowGone(false);
            this.mItemJywzld.ShowGone(false);
        }
        if (CanJni.GetSubType() != 11) {
            this.mItemSxcwzyd.ShowGone(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, String[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        btn.ShowGone(true);
        return btn;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.HondaDACarSet(35, Neg(this.mSetData.zsbsz));
                return;
            case 3:
                new CanItemMsgBox(3, this, R.string.can_sure_setup, this);
                return;
            case 4:
                new CanItemMsgBox(4, this, R.string.can_hfcsz, this);
                return;
            case 5:
                new CanItemMsgBox(5, this, R.string.can_tpms_set, this);
                return;
            case 6:
                CanJni.HondaDACarSet(39, Neg(this.mSetData.Jtbzsbxtxtb));
                return;
            case 7:
                CanJni.HondaDACarSet(40, Neg(this.mSetData.Ttjg));
                return;
            case 8:
                CanJni.HondaDACarSet(41, Neg(this.mSetData.Jywzld));
                return;
            case 9:
                CanJni.HondaDACarSet(52, Neg(this.mSetData.Sxcszywzyd));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.HondaDACarSet(85, item);
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
        switch (param) {
            case 3:
                CanJni.HondaDACarSet(14, 0);
                return;
            case 4:
                CanJni.HondaDACarSet(15, 0);
                return;
            case 5:
                CanJni.HondaDACarSet(17, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.XtszUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.XtszUpdate)) {
            this.mSetData.XtszUpdate = 0;
            this.mItemZsbsz.SetCheck(this.mSetData.zsbsz);
            this.mItemJtbzSbxtXtb.SetCheck(this.mSetData.Jtbzsbxtxtb);
            this.mItemTtjg.SetCheck(this.mSetData.Ttjg);
            this.mItemJywzld.SetCheck(this.mSetData.Jywzld);
            this.mItemSxcwzyd.SetCheck(this.mSetData.Sxcszywzyd);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
