package com.ts.can.ford.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordRzcChairSetView extends CanScrollCarInfoView {
    private static final int CKC = 6;
    private static final int CKC_AM_KB = 10;
    private static final int CKC_AM_ZD = 11;
    private static final int CKC_TZ_D = 9;
    private static final int CKC_TZ_G = 7;
    private static final int CKC_TZ_Z = 8;
    private static final int JSC = 0;
    private static final int JSC_AM_KB = 4;
    private static final int JSC_AM_ZD = 5;
    private static final int JSC_TZ_D = 3;
    private static final int JSC_TZ_G = 1;
    private static final int JSC_TZ_Z = 2;
    private CanDataInfo.FordRzcChairMsj mSetData;

    public CanFordRzcChairSetView(Activity activity) {
        super(activity, 12);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.FordRzcCarSet(167, 10, item);
                return;
            case 4:
                CanJni.FordRzcCarSet(167, 6, item);
                return;
            case 5:
                CanJni.FordRzcCarSet(167, 7, item);
                return;
            case 6:
                CanJni.FordRzcCarSet(167, 11, item);
                return;
            case 10:
                CanJni.FordRzcCarSet(167, 8, item);
                return;
            case 11:
                CanJni.FordRzcCarSet(167, 9, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                CanJni.FordRzcCarSet(167, 0, checkVal(pos, this.mSetData.Jsctz[0]));
                return;
            case 2:
                CanJni.FordRzcCarSet(167, 1, checkVal(pos, this.mSetData.Jsctz[1]));
                return;
            case 3:
                CanJni.FordRzcCarSet(167, 2, checkVal(pos, this.mSetData.Jsctz[2]));
                return;
            case 7:
                CanJni.FordRzcCarSet(167, 3, checkVal(pos, this.mSetData.Ckctz[0]));
                return;
            case 8:
                CanJni.FordRzcCarSet(167, 4, checkVal(pos, this.mSetData.Ckctz[1]));
                return;
            case 9:
                CanJni.FordRzcCarSet(167, 5, checkVal(pos, this.mSetData.Ckctz[2]));
                return;
            default:
                return;
        }
    }

    private int checkVal(int pos, int val) {
        if (pos == val) {
            return 0;
        }
        if (pos - val > 0) {
            return 1;
        }
        return 2;
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_jscgn, R.string.can_jsczytj_sb, R.string.can_jsczytj_zb, R.string.can_jsczytj_xb, R.string.can_jscam_kb, R.string.can_jscam_zd, R.string.can_ckcgn, R.string.can_ckczytj_sb, R.string.can_ckczytj_zb, R.string.can_ckczytj_xb, R.string.can_ckcam_kb, R.string.can_ckcam_zd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        int[] progressArr = new int[4];
        progressArr[0] = 1;
        progressArr[1] = 10;
        progressArr[2] = 1;
        int[] popArr = {R.string.can_trunk_close, R.string.can_ac_low, R.string.can_ac_high};
        this.mPopValueIds[0] = new int[]{R.string.can_exit, R.string.can_tz, R.string.can_am};
        this.mProgressAttrs[1] = progressArr;
        this.mProgressAttrs[2] = progressArr;
        this.mProgressAttrs[3] = progressArr;
        this.mPopValueIds[4] = popArr;
        this.mPopValueIds[5] = popArr;
        this.mPopValueIds[6] = new int[]{R.string.can_exit, R.string.can_tz, R.string.can_am};
        this.mProgressAttrs[7] = progressArr;
        this.mProgressAttrs[8] = progressArr;
        this.mProgressAttrs[9] = progressArr;
        this.mPopValueIds[10] = popArr;
        this.mPopValueIds[11] = popArr;
        this.mSetData = new CanDataInfo.FordRzcChairMsj();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        for (int i = 0; i < 12; i++) {
            this.mItemVisibles[i] = 0;
        }
    }

    public void ResetData(boolean check) {
        CanJni.FordRzcGetChairMsjData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            if (i2b(this.mSetData.Adt)) {
                if (this.mSetData.Jscgn == 0) {
                    showItem(1, 0);
                    showItem(2, 0);
                    showItem(3, 0);
                    showItem(4, 0);
                    showItem(5, 0);
                } else if (this.mSetData.Jscgn == 1) {
                    showItem(1, 1);
                    showItem(2, 1);
                    showItem(3, 1);
                    showItem(4, 0);
                    showItem(5, 0);
                } else if (this.mSetData.Jscgn == 2) {
                    showItem(1, 0);
                    showItem(2, 0);
                    showItem(3, 0);
                    showItem(4, 1);
                    showItem(5, 1);
                }
                if (this.mSetData.Ckcgn == 0) {
                    showItem(7, 0);
                    showItem(8, 0);
                    showItem(9, 0);
                    showItem(10, 0);
                    showItem(11, 0);
                } else if (this.mSetData.Ckcgn == 1) {
                    showItem(7, 1);
                    showItem(8, 1);
                    showItem(9, 1);
                    showItem(10, 0);
                    showItem(11, 0);
                } else if (this.mSetData.Ckcgn == 2) {
                    showItem(7, 0);
                    showItem(8, 0);
                    showItem(9, 0);
                    showItem(10, 1);
                    showItem(11, 1);
                }
                updateItem(new int[]{this.mSetData.Jscgn, this.mSetData.Jsctz[0], this.mSetData.Jsctz[1], this.mSetData.Jsctz[2], this.mSetData.Jscam[0], this.mSetData.Jscam[1], this.mSetData.Ckcgn, this.mSetData.Ckctz[0], this.mSetData.Ckctz[1], this.mSetData.Ckctz[2], this.mSetData.Ckcam[0], this.mSetData.Ckcam[1]});
                return;
            }
            showItem(new int[12]);
        }
    }

    public void QueryData() {
        CanJni.FordQuery(100, 0);
    }
}
