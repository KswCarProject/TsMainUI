package com.ts.can.nissan.dj.teana;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemProgressList;

public class CanTeanaOldDjAmpSetView extends CanScrollCarInfoView {
    protected static final int ITEM_AUDIOSTATUS = 5;
    protected static final int ITEM_BALANCE = 1;
    protected static final int ITEM_BASS = 2;
    protected static final int ITEM_FADE = 0;
    protected static final int ITEM_TREBLE = 3;
    protected static final int ITEM_VOLUME = 4;
    private static String[] mBalStrings = {"L5", "L4", "L3", "L2", "L1", "0", "R1", "R2", "R3", "R4", "R5"};
    private static String[] mFadeStrings = {"F5", "F4", "F3", "F2", "F1", "0", "R1", "R2", "R3", "R4", "R5"};
    private CanDataInfo.TeanaOldDjAmpInfo mAmpInfo;

    public CanTeanaOldDjAmpSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_balance_front_rear, R.string.can_balance_left_right, R.string.can_vol_low, R.string.can_vol_high, R.string.can_vol, R.string.can_gf_toggle};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH};
        this.mAmpInfo = new CanDataInfo.TeanaOldDjAmpInfo();
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 10;
        iArr2[2] = 1;
        iArr2[3] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 10;
        iArr4[2] = 1;
        iArr4[3] = 1;
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[0] = -5;
        iArr6[1] = 5;
        iArr6[2] = 1;
        iArr5[2] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[0] = -5;
        iArr8[1] = 5;
        iArr8[2] = 1;
        iArr7[3] = iArr8;
        int[][] iArr9 = this.mProgressAttrs;
        int[] iArr10 = new int[4];
        iArr10[1] = 63;
        iArr10[2] = 1;
        iArr9[4] = iArr10;
    }

    public void ResetData(boolean check) {
        CanJni.TeanaOldDjGetAmpInfo(this.mAmpInfo);
        if (!i2b(this.mAmpInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpInfo.Update)) {
            this.mAmpInfo.Update = 0;
            if (this.mAmpInfo.Fad >= 2) {
                updateItem(0, this.mAmpInfo.Fad - 2, mFadeStrings[this.mAmpInfo.Fad - 2]);
            }
            if (this.mAmpInfo.Bal >= 2) {
                updateItem(1, this.mAmpInfo.Bal - 2, mBalStrings[this.mAmpInfo.Bal - 2]);
            }
            updateItem(2, this.mAmpInfo.Bass - 7);
            updateItem(3, this.mAmpInfo.Tre - 7);
            updateItem(4, this.mAmpInfo.Vol);
            updateItem(5, Neg(this.mAmpInfo.Mute));
        }
    }

    public void QueryData() {
        CanJni.TeanaOldDjQuery(49);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        for (int i = 0; i < 5; i++) {
            CanItemProgressList mCanItemProgressList = (CanItemProgressList) this.mItemObjects[i];
            mCanItemProgressList.GetBtnAdd().setVisibility(8);
            mCanItemProgressList.GetBtnSub().setVisibility(8);
            mCanItemProgressList.GetProgress().setOnTouchListener((View.OnTouchListener) null);
        }
    }
}
