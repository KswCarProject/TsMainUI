package com.ts.can.gm.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGMSetLightView extends CanScrollCarInfoView {
    private static final int ITEM_CSFWDYSSZ = 4;
    public static final int ITEM_DDYS = 1;
    private static final int ITEM_MAX = 1;
    private static final int ITEM_MIN = 0;
    public static final int ITEM_XCD = 0;
    public static final String TAG = "CanGMCarInfoActivity";
    private static final int[] mAdaptiveForwardLightingArr = {R.string.can_corner_and_bend_lighting, R.string.can_intelligent_distribution};
    private static final int[] mDdysArr = {R.string.can_off, R.string.can_30s, R.string.can_1min, R.string.can_2min};
    private static final int[] mLeftOrRightHandTrafficArr = {R.string.can_left_hand_traffic, R.string.can_right_Hand_traffic};
    protected CanDataInfo.GM_CarSet mSetData = new CanDataInfo.GM_CarSet();
    private boolean mbLayout;

    public CanGMSetLightView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int Id, int item) {
        if (Id == 1) {
            CanJni.GMCarCtrl(1, item);
        } else if (Id == 2) {
            CanJni.GMCarCtrl(16, item);
        } else if (Id == 3) {
            CanJni.GMCarCtrl(17, item);
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 4:
                CanJni.GMCarCtrl(32, pos);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_xcd, R.string.can_lsddys, R.string.can_left_or_right_hand_traffic, R.string.can_adaptive_forward_lighting, R.string.can_fwd_color};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS};
        this.mPopValueIds[1] = mDdysArr;
        this.mPopValueIds[2] = mLeftOrRightHandTrafficArr;
        this.mPopValueIds[3] = mAdaptiveForwardLightingArr;
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 18;
        iArr2[2] = 1;
        iArr[4] = iArr2;
        if (CanJni.GetSubType() != 8) {
            this.mItemVisibles[4] = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void GetSetData() {
        CanJni.GMGetCarSet(this.mSetData);
    }

    /* access modifiers changed from: protected */
    public void QuerySetData() {
        if (this.mSetData.UpdateOnce == 0) {
            CanJni.GMQuery(6);
        }
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
        CanJni.GMCarCtrl(cmd, para);
    }

    public void ResetData(boolean check) {
        GetSetData();
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.XCD);
            updateItem(1, this.mSetData.LSDDYS);
            updateItem(2, this.mSetData.LtOrRtTraffic);
            updateItem(3, this.mSetData.AdtForwardLight);
            updateItem(4, this.mSetData.Fwdys);
        }
    }

    public void QueryData() {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.GMCarCtrl(0, Neg(this.mSetData.XCD));
                return;
            default:
                return;
        }
    }
}
