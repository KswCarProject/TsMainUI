package com.ts.can.nissan.xc.teana;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanTeanaOldXcCarSetView extends CanScrollCarInfoView {
    private static final int ITEM_CSLDJXSYSQ = 2;
    private static final int ITEM_MSJCCNDZDDK = 0;
    private static final int ITEM_RESET = 5;
    private static final int ITEM_ZDDGDSJSZ = 4;
    private static final int ITEM_ZDDLMD = 1;
    private static final int ITEM_ZNYSKSGN = 3;
    private CanDataInfo.TeanaOldXc_Set mSetData;

    public CanTeanaOldXcCarSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.TeanOldXcCarSet(3, item);
                return;
            case 4:
                CanJni.TeanOldXcCarSet(4, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 0:
                CanJni.TeanOldXcCarSet(2, Neg(this.mSetData.AutoIntIllum));
                return;
            case 2:
                CanJni.TeanOldXcCarSet(5, Neg(this.mSetData.SpeedSenWiperInt));
                return;
            case 3:
                CanJni.TeanOldXcCarSet(7, Neg(this.mSetData.IntKeyLockUnlock));
                return;
            case 5:
                new CanItemMsgBox(id, getActivity(), R.string.can_sure_reset, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.TeanOldXcCarSet(8, 1);
                    }
                }, (CanItemMsgBox.onMsgBoxClick2) null);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_msjccndzddk, R.string.can_zddlmd, R.string.can_csldjxsysq, R.string.can_znysksgn, R.string.can_zdgdsjsz, R.string.can_reset_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[1] = new int[]{R.string.can_headlightsens_0, R.string.can_headlightsens_1, R.string.can_headlightsens_2, R.string.can_headlightsens_3};
        this.mPopValueIds[4] = new int[]{R.string.can_0s, R.string.can_mzd_cx4_time_30s, R.string.can_time_45s, R.string.can_headlightautoofftime_60s, R.string.can_headlightautoofftime_90s, R.string.can_mzd_cx4_time_120s, R.string.can_mzd_cx4_time_150s, R.string.can_mzd_cx4_time_180s};
        this.mSetData = new CanDataInfo.TeanaOldXc_Set();
    }

    public void ResetData(boolean check) {
        CanJni.TeanOldXcGetSetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.AutoIntIllum);
            updateItem(1, this.mSetData.AutoHeadSens);
            updateItem(2, this.mSetData.SpeedSenWiperInt);
            updateItem(3, this.mSetData.IntKeyLockUnlock);
            updateItem(4, this.mSetData.AutoHeadOffDelay);
        }
    }

    public void QueryData() {
        CanJni.TeanOldXcQuery(48);
    }
}
