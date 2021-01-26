package com.ts.can.saic.mg.mg6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMg6RzcLockSetView extends CanScrollCarInfoView {
    public static final int ITEM_CMS = 9;
    public static final int ITEM_JS = 1;
    public static final int ITEM_JSMS = 2;
    public static final int ITEM_RHCC = 8;
    public static final int ITEM_RQCC = 7;
    public static final int ITEM_TC = 4;
    public static final int ITEM_XCLS = 0;
    public static final int ITEM_ZHCC = 6;
    public static final int ITEM_ZNJCJS = 3;
    public static final int ITEM_ZQCC = 5;
    private CanDataInfo.MG_GS_SET m_SetData;

    public CanMg6RzcLockSetView(Activity activity) {
        super(activity, 10);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.MGGSCarSet(1, 3, item);
                return;
            case 3:
                CanJni.MGGSCarSet(1, 4, item);
                return;
            case 5:
                CanJni.MGGSCarSet(9, 2, item);
                return;
            case 6:
                CanJni.MGGSCarSet(9, 3, item);
                return;
            case 7:
                CanJni.MGGSCarSet(9, 4, item);
                return;
            case 8:
                CanJni.MGGSCarSet(9, 5, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 4:
                CanJni.MGGSCarSet(9, 1, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.MGGSCarSet(1, 1, Neg(this.m_SetData.Xcls));
                return;
            case 1:
                CanJni.MGGSCarSet(1, 2, Neg(this.m_SetData.Js));
                return;
            case 9:
                CanJni.MGGSCarSet(9, 6, Neg(this.m_SetData.Cms));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_csss, R.string.can_zdjs, R.string.can_unlock_mode, R.string.can_smart_near_lock, R.string.can_kztckg, R.string.can_lf_window, R.string.can_lr_window, R.string.can_rf_window, R.string.can_rr_window, R.string.can_mzd_cx4_door};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[2] = new int[]{R.string.can_sym, R.string.can_jsym};
        this.mPopValueIds[3] = new int[]{R.string.can_sym, R.string.can_jsym};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 10;
        iArr2[2] = 1;
        iArr[4] = iArr2;
        this.mPopValueIds[5] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_mode_on, R.string.can_all_close, R.string.can_all_open};
        this.mPopValueIds[6] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_mode_on, R.string.can_all_close, R.string.can_all_open};
        this.mPopValueIds[7] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_mode_on, R.string.can_all_close, R.string.can_all_open};
        this.mPopValueIds[8] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_mode_on, R.string.can_all_close, R.string.can_all_open};
        this.mItemVisibles[4] = 0;
        this.mItemVisibles[5] = 0;
        this.mItemVisibles[6] = 0;
        this.mItemVisibles[7] = 0;
        this.mItemVisibles[8] = 0;
        this.m_SetData = new CanDataInfo.MG_GS_SET();
    }

    public void doOnResume() {
    }

    public void ResetData(boolean check) {
        CanJni.MGGSGetSetData4(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(0, this.m_SetData.Xcls);
            updateItem(1, this.m_SetData.Js);
            updateItem(2, this.m_SetData.Jsms);
            updateItem(3, this.m_SetData.Znjcjs);
            updateItem(4, this.m_SetData.Tc);
            updateItem(5, this.m_SetData.Zqcc);
            updateItem(6, this.m_SetData.Zhcc);
            updateItem(7, this.m_SetData.Yqcc);
            updateItem(8, this.m_SetData.Yhcc);
            updateItem(9, this.m_SetData.Cms);
        }
    }

    public void QueryData() {
        CanJni.MGGSQuery(57);
    }
}
