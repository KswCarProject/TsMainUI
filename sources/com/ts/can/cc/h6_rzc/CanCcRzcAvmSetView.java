package com.ts.can.cc.h6_rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemPopupList;

public class CanCcRzcAvmSetView extends CanScrollCarInfoView {
    private static final int ITEM_AVMZDJH = 4;
    private static final int ITEM_CFZXSZ = 5;
    private static final int ITEM_CMTMD = 0;
    private static final int ITEM_CMYSSZ = 3;
    private static final int ITEM_LDXSMSSZ = 1;
    private static final int ITEM_ZNSJQH = 2;
    private int[] mCmysszArr;
    private CanDataInfo.CcRzcAvmSet mSetData;
    private int[] nCmtmdArr = {R.string.can_cmtmd_1, R.string.can_cmtmd_2, R.string.can_cmtmd_3};
    private int[] nLdxsmsszArr = {R.string.can_ldxsmssz_1, R.string.can_ldxsmssz_2, R.string.can_ldxsmssz_3, R.string.can_mzd_cx4_mode_off};

    public CanCcRzcAvmSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CanJni.CCH6CarSetRzc(32, item, 0);
                return;
            case 4:
                CanJni.CCH6CarSetRzc(33, item, 0);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.CCH6CarSetRzc(29, 1, 0);
                Sleep(10);
                CanJni.CCH6CarSetRzc(29, 0, 0);
                return;
            case 1:
                CanJni.CCH6CarSetRzc(30, 1, 0);
                Sleep(10);
                CanJni.CCH6CarSetRzc(30, 0, 0);
                return;
            case 2:
                CanJni.CCH6CarSetRzc(31, 1, 0);
                Sleep(10);
                CanJni.CCH6CarSetRzc(31, 0, 0);
                return;
            case 5:
                CanJni.CCH6CarSetRzc(34, 1, 0);
                Sleep(10);
                CanJni.CCH6CarSetRzc(34, 0, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_cmtmd, R.string.can_ldxsmssz, R.string.can_znsjqh, R.string.can_cmyssz, R.string.can_avmzdjh, R.string.can_cfzxsz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.VALUE};
        this.mPopValueIds[3] = new int[]{R.string.can_color_silver, R.string.can_color_red, R.string.can_gray};
        this.mPopValueIds[4] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_driving_ds, R.string.can_low_mid_speed};
        this.mSetData = new CanDataInfo.CcRzcAvmSet();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        if (CanJni.GetSubType() == 8) {
            this.mCmysszArr = new int[]{R.string.can_color_silver, R.string.can_color_red, R.string.can_gray, R.string.can_magoten_light_color_4, R.string.can_magoten_light_color_2};
        } else {
            this.mCmysszArr = new int[]{R.string.can_color_silver, R.string.can_color_red, R.string.can_gray};
        }
        getScrollManager().RemoveView(3);
        this.mItemObjects[3] = getScrollManager().addItemPopupList(this.mItemTitleIds[3], this.mCmysszArr, 3, (CanItemPopupList.onPopItemClick) this);
    }

    public void ResetData(boolean check) {
        CanJni.CcHfH9GetAvmInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, 0, getString(this.nCmtmdArr[this.mSetData.Cmtmd]));
            updateItem(1, 0, getString(this.nLdxsmsszArr[this.mSetData.Ldxsmssz]));
            updateItem(2, this.mSetData.Znsjqh);
            updateItem(3, this.mSetData.Cmyssz);
            updateItem(4, this.mSetData.AvmZdjh);
            updateItem(5, 0, getString(this.nLdxsmsszArr[this.mSetData.Cfzxsz]));
        }
    }

    public void QueryData() {
        CanJni.CCCarQueryRzc(66, 0);
    }
}
