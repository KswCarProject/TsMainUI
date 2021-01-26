package com.ts.can.hyundai.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHyundaiRzcLightSetView extends CanScrollCarInfoView {
    private static final int ITEM_DSD = 2;
    private static final int ITEM_FWD = 0;
    private static final int ITEM_LD = 4;
    private static final int ITEM_YYJ = 3;
    private static final int ITEM_ZTS = 1;
    private CanDataInfo.HyCarSet mSetData;
    private int[] nFwdZts = {3, 6, 9, 12, 15, 18};

    public CanHyundaiRzcLightSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.HyundaiRzcCarSet(11, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                CanJni.HyundaiRzcCarSet(12, this.nFwdZts[pos]);
                return;
            case 2:
                CanJni.HyundaiRzcCarSet(13, pos);
                return;
            case 4:
                CanJni.HyundaiRzcCarSet(15, pos);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public int NegSwSet(int val) {
        if (1 == val) {
            return 2;
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public int SwSet(int val) {
        if (1 == val) {
            return 0;
        }
        return 1;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                CanJni.HyundaiRzcCarSet(14, NegSwSet(this.mSetData.Yyj));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_fwdkzfs, R.string.can_ztsz, R.string.can_fwd_dsd, R.string.can_fwd_music_i, R.string.can_mzd_cx4_drive_light};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS};
        this.mPopValueIds[0] = new int[]{R.string.can_fwd_zts, R.string.can_fwd_dsd};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 5;
        iArr2[2] = 1;
        iArr[1] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[0] = 1;
        iArr4[1] = 8;
        iArr4[2] = 1;
        iArr3[2] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[0] = 2;
        iArr6[1] = 5;
        iArr6[2] = 1;
        iArr5[4] = iArr6;
        this.mSetData = new CanDataInfo.HyCarSet();
    }

    public void ResetData(boolean check) {
        CanJni.HyundaiRzcGetCarSet(this.mSetData);
        if (i2b(this.mSetData.FwdUpdateOnce) && (!check || i2b(this.mSetData.FwdUpdate))) {
            this.mSetData.FwdUpdate = 0;
            updateItem(0, SwSet(this.mSetData.Fwd));
        }
        if (i2b(this.mSetData.ZtsUpdateOnce) && (!check || i2b(this.mSetData.ZtsUpdate))) {
            this.mSetData.ZtsUpdate = 0;
            int i = 0;
            while (true) {
                if (i >= this.nFwdZts.length) {
                    break;
                } else if (this.mSetData.Zts == this.nFwdZts[i]) {
                    updateItem(1, i);
                    break;
                } else {
                    i++;
                }
            }
        }
        if (i2b(this.mSetData.DsdUpdateOnce) && (!check || i2b(this.mSetData.DsdUpdate))) {
            this.mSetData.DsdUpdate = 0;
            updateItem(2, this.mSetData.Dsd);
        }
        if (i2b(this.mSetData.YyjUpdateOnce) && (!check || i2b(this.mSetData.YyjUpdate))) {
            this.mSetData.YyjUpdate = 0;
            updateItem(3, SwSet(this.mSetData.Yyj));
        }
        if (!i2b(this.mSetData.LdUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.LdUpdate)) {
            this.mSetData.LdUpdate = 0;
            updateItem(4, this.mSetData.Ld);
        }
    }

    public void QueryData() {
        CanJni.HyundaiRzcQuery(82, 11);
        Sleep(5);
        CanJni.HyundaiRzcQuery(82, 12);
        Sleep(5);
        CanJni.HyundaiRzcQuery(82, 13);
        Sleep(5);
        CanJni.HyundaiRzcQuery(82, 14);
        Sleep(5);
        CanJni.HyundaiRzcQuery(82, 15);
    }
}
