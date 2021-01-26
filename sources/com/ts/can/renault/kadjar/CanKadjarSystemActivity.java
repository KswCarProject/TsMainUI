package com.ts.can.renault.kadjar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemFsSetList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollBaseActivity;
import com.ts.main.common.MainSet;

public class CanKadjarSystemActivity extends CanScrollBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange, CanItemFsSetList.onFsSetClick {
    protected static final int ITEM_LANG = 1;
    protected static final int ITEM_MAX = 6;
    protected static final int ITEM_MIN = 1;
    protected static final int ITEM_RESET = 6;
    protected static final int ITEM_YBPQY = 5;
    protected static final int ITEM_YBYS = 3;
    protected static final int ITEM_YB_COLOR = 2;
    protected static final int ITEM_YJLD = 4;
    public static final String TAG = "CanKadjarSystemActivity";
    protected static final int[] mLangArr = {R.string.lang_cn, R.string.lang_en_us, R.string.lang_Bulgarian, R.string.lang_arab, R.string.can_reserved, R.string.can_reserved, R.string.lang_Hrvatski, R.string.lang_Cesky, R.string.lang_dansk, R.string.lang_nederlands, R.string.lang_en_uk, R.string.lang_Eestlane, R.string.lang_suomi, R.string.lang_Belgian, R.string.lang_francais, R.string.lang_deutsch, R.string.lang_greek, R.string.lang_Israeli, R.string.lang_Magyar, R.string.lang_India, R.string.lang_Iran, R.string.lang_itanliano, R.string.lang_Japanese, R.string.can_reserved, R.string.lang_Austrian, R.string.lang_Lietuvis, R.string.lang_norsk, R.string.lang_polski, R.string.lang_portugues_br, R.string.lang_portugues, R.string.lang_Romagnio, R.string.lang_pyccknn, R.string.lang_Serbian, R.string.lang_Slovencina, R.string.lang_Slovensko, R.string.lang_espanol, R.string.lang_espanol_me, R.string.lang_svenska, R.string.can_reserved, R.string.lang_turkce, R.string.lang_Ykpaihcbka, R.string.can_yb_msg};
    protected static final String[] mYbColorArr = {"绿色", "红色", "蓝色", "紫色", "橙色"};
    protected static final String[] mYbysArr = {"1", "2", "3", MainSet.SP_KS_QOROS};
    protected CanItemPopupList mItemLang;
    protected CanItemFsSetList mItemReset;
    protected CanItemPopupList mItemYbColor;
    protected CanItemSwitchList mItemYbpqy;
    protected CanItemPopupList mItemYbys;
    protected CanItemProgressList mItemYjld;
    protected CanDataInfo.KadjarSet mSetData = new CanDataInfo.KadjarSet();
    protected boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mItemLang = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_language, mLangArr, 1);
        this.mItemYbColor = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_yb_color, mYbColorArr, 2);
        this.mItemYbys = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_ybxsys, mYbysArr, 3);
        this.mItemYjld = AddProgress(this, R.string.can_ybyjld, 4);
        this.mItemYjld.SetMinMax(0, 20);
        this.mItemYjld.SetUserValText();
        this.mItemYbpqy = AddSwitch((View.OnClickListener) this, R.string.can_ybfwd, 5);
        this.mItemReset = AddFsSetItem(this, R.string.can_tpms_reset_notice, 6);
        this.mItemReset.SetMsgText(R.string.can_sure_reset);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.KadjarGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            if (this.mSetData.Ybys >= 1 && this.mSetData.Ybys <= 4) {
                this.mItemYbys.SetSel(this.mSetData.Ybys - 1);
            }
            this.mItemYjld.SetCurVal(this.mSetData.Yjld / 5);
            this.mItemYjld.SetValText(new StringBuilder(String.valueOf(this.mSetData.Yjld)).toString());
            this.mItemYbpqy.SetCheck(this.mSetData.Ybpqy);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.KadjarCarQuery(113, 0);
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
        Log.d(TAG, "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 6; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        switch (item) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
        return i2b(1);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemLang.ShowGone(show);
                return;
            case 2:
                this.mItemYbColor.ShowGone(show);
                return;
            case 3:
                this.mItemYbys.ShowGone(show);
                return;
            case 4:
                this.mItemYjld.ShowGone(show);
                return;
            case 5:
                this.mItemYbpqy.ShowGone(show);
                return;
            case 6:
                this.mItemReset.ShowGone(show);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 5:
                CanJni.KadjarCarSet(33, Neg(this.mSetData.Ybpqy));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                if (item == 41) {
                    item = 129;
                }
                CanJni.KadjarCarSet(13, item);
                return;
            case 2:
                CanJni.KadjarCarSet(16, item + 1);
                return;
            case 3:
                CanJni.KadjarCarSet(14, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 4:
                CanJni.KadjarCarSet(15, pos * 5);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
    }

    public void onFsItem(int id, boolean sure) {
        if (sure) {
            CanJni.KadjarCarSet(128, 3);
        }
    }
}
