package com.ts.can.df.wc.ax7;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanDfFsWcCarSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    protected static final int ITEM_BWHJ = 2;
    protected static final int ITEM_BWHJSJ = 3;
    protected static final int ITEM_CSZDBS = 0;
    protected static final int ITEM_DPHJQD = 5;
    protected static final int ITEM_HHBB = 9;
    protected static final int ITEM_KSJW = 8;
    protected static final int ITEM_LANG = 11;
    protected static final int ITEM_LCHZDLS = 4;
    private static final int ITEM_MAX = 13;
    private static final int ITEM_MIN = 0;
    protected static final int ITEM_TYCZ = 12;
    protected static final int ITEM_XHZDJS = 1;
    protected static final int ITEM_YBBGLD = 7;
    protected static final int ITEM_YXMS = 10;
    protected static final int ITEM_YYKZTC = 6;
    private CanDataInfo.DfWc_SetData m_SetData;

    public CanDfFsWcCarSetView(Activity activity) {
        super(activity, 13);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CanJni.DfWcCarSet(4, item + 1, 0, 0);
                return;
            case 11:
                CanJni.DfWcCarLangSet(1, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 7:
                CanJni.DfWcCarSet(9, pos, 0, 0);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.DfWcCarSet(1, Neg(this.m_SetData.Cszdgb), 0, 0);
                return;
            case 1:
                CanJni.DfWcCarSet(2, Neg(this.m_SetData.Xhzdjs), 0, 0);
                return;
            case 2:
                CanJni.DfWcCarSet(3, Neg(this.m_SetData.Bwhj), 0, 0);
                return;
            case 4:
                CanJni.DfWcCarSet(6, Neg(this.m_SetData.Lchzdls), 0, 0);
                return;
            case 5:
                CanJni.DfWcCarSet(5, Neg(this.m_SetData.Dphjqd), 0, 0);
                return;
            case 6:
                CanJni.DfWcCarSet(7, Neg(this.m_SetData.Yykztc), 0, 0);
                return;
            case 8:
                CanJni.DfWcCarSet(16, Neg(this.m_SetData.Ksjw), 0, 0);
                return;
            case 9:
                CanJni.DfWcCarSet(17, Neg(this.m_SetData.Hhbb), 0, 0);
                return;
            case 10:
                CanJni.DfWcCarSet(19, Neg(this.m_SetData.Yxms), 0, 0);
                return;
            case 12:
                new CanItemMsgBox(12, getActivity(), R.string.can_sure_tybd, this);
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
        switch (param) {
            case 12:
                CanJni.DfWcCarSet(8, 1, 0, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_auto_speed_lock, R.string.can_xhzdjs, R.string.can_dgkz_bwhj, R.string.can_dgsjkz_bwhj, R.string.can_lczdls, R.string.can_teramont_dphj_system, R.string.can_sktc, R.string.can_tigger7_behind_light, R.string.can_ksjw, R.string.can_hhbb, R.string.can_yxms, R.string.can_car_lang, R.string.can_tpms_reset_notice};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[3] = new int[]{R.string.can_15s, R.string.can_30s, R.string.can_time_45s, R.string.can_60s};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = 1;
        iArr2[1] = 6;
        iArr2[2] = 1;
        iArr[7] = iArr2;
        this.mPopValueIds[11] = new int[]{R.string.lang_en_uk, R.string.lang_cn};
        this.m_SetData = new CanDataInfo.DfWc_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.DfWcGetCarSetData(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(0, this.m_SetData.Cszdgb);
            updateItem(1, this.m_SetData.Xhzdjs);
            updateItem(2, this.m_SetData.Bwhj);
            updateItem(3, this.m_SetData.Bwhjsj - 1);
            updateItem(4, this.m_SetData.Lchzdls);
            updateItem(5, this.m_SetData.Dphjqd);
            updateItem(6, this.m_SetData.Yykztc);
            updateItem(7, this.m_SetData.Ybbgld);
            updateItem(8, this.m_SetData.Ksjw);
            updateItem(9, this.m_SetData.Hhbb);
            updateItem(10, this.m_SetData.Yxms);
        }
    }

    public void QueryData() {
        CanJni.DfWcCarQuery(5, 1, 97);
    }
}
