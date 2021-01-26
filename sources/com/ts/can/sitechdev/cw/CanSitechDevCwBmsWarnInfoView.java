package com.ts.can.sitechdev.cw;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.txznet.sdk.TXZResourceManager;

public class CanSitechDevCwBmsWarnInfoView extends CanScrollCarInfoView {
    public static final String TAG = "CanSitechDevCwDtInfoView";
    private String[] mBmszjztbjStrs;
    private String[] mCdczwdbjStrs;
    private String[] mDcxklStrs;
    private CanDataInfo.SitechDev_BmsWarn mDev_BmsWarn;
    private String[] mSoctbbjStrs;
    private String[] mXdcgcbjStrs;

    public CanSitechDevCwBmsWarnInfoView(Activity activity) {
        super(activity, 37);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mDev_BmsWarn = new CanDataInfo.SitechDev_BmsWarn();
        Resources resources = getActivity().getResources();
        this.mSoctbbjStrs = resources.getStringArray(R.array.can_soctbbj_arrays);
        this.mXdcgcbjStrs = resources.getStringArray(R.array.can_xdcgcbj_arrays);
        this.mBmszjztbjStrs = resources.getStringArray(R.array.can_bmszjztbj_arrays);
        this.mDcxklStrs = resources.getStringArray(R.array.can_dcxkl_arrays);
        this.mCdczwdbjStrs = resources.getStringArray(R.array.can_cdczwdbj_arrays);
        this.mItemTitleIds = new int[]{R.string.can_soctbbj, R.string.can_dcdwbj, R.string.can_xdcgcbj, R.string.can_dtyzxcbj, R.string.can_dcsmbj, R.string.can_bmszjztbj, R.string.can_bmsnwtxgzbj, R.string.can_dcjrbj, R.string.can_dclqbj, R.string.can_yczcdjtxbj, R.string.can_ykctxbj, R.string.can_gyhsbj, R.string.can_xdcxtbppbj, R.string.can_dcxkl_1, R.string.can_dcxkl_2, R.string.can_dcxkl_3, R.string.can_dcxkl_4, R.string.can_dcxkl_5, R.string.can_dcxkl_6, R.string.can_dcxkl_7, R.string.can_dcxkl_8, R.string.can_dcxkl_9, R.string.can_dcxkl_10, R.string.can_dcxkl_11, R.string.can_dcxkl_12, R.string.can_dcxkl_13, R.string.can_dcxkl_14, R.string.can_dcxkl_15, R.string.can_cdczwdbj, R.string.can_dcqybj, R.string.can_dcgybj, R.string.can_dcgwbj, R.string.can_dcwcdbj, R.string.can_fdglgz, R.string.can_cdglgz, R.string.can_gyldbj, R.string.can_jzgwbj};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        for (int i = 0; i < 37; i++) {
            this.mItemVisibles[i] = 0;
        }
    }

    public void ResetData(boolean check) {
        CanJni.SitechDevCwGetBmsWarn(this.mDev_BmsWarn);
        if (!i2b(this.mDev_BmsWarn.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDev_BmsWarn.Update)) {
            Log.d("lh", "mDev_BmsWarn.SocTb = " + this.mDev_BmsWarn.SocTb);
            this.mDev_BmsWarn.Update = 0;
            updateItem(0, 0, GetWarnValue(0, this.mDev_BmsWarn.SocTb));
            updateItem(1, 0, GetWarnValue(1, this.mDev_BmsWarn.Dcdw));
            updateItem(2, 0, GetWarnValue(2, this.mDev_BmsWarn.Xdcgc));
            updateItem(3, 0, GetWarnValue(3, this.mDev_BmsWarn.Dtyzxc));
            updateItem(4, 0, GetWarnValue(4, this.mDev_BmsWarn.Dcsm));
            updateItem(5, 0, GetWarnValue(5, this.mDev_BmsWarn.BmsZjzt));
            updateItem(6, 0, GetWarnValue(6, this.mDev_BmsWarn.BmsNwtxgz));
            updateItem(7, 0, GetWarnValue(7, this.mDev_BmsWarn.Dcjrgz));
            updateItem(8, 0, GetWarnValue(8, this.mDev_BmsWarn.Dclqgz));
            updateItem(9, 0, GetWarnValue(9, this.mDev_BmsWarn.Yczcdjtx));
            updateItem(10, 0, GetWarnValue(10, this.mDev_BmsWarn.Ykctxbj));
            updateItem(11, 0, GetWarnValue(11, this.mDev_BmsWarn.Gyhs));
            updateItem(12, 0, GetWarnValue(12, this.mDev_BmsWarn.Xdcxtbpp));
            updateItem(13, 0, GetWarnValue(13, this.mDev_BmsWarn.Dcxkl_1));
            updateItem(14, 0, GetWarnValue(14, this.mDev_BmsWarn.Dcxkl_2));
            updateItem(15, 0, GetWarnValue(15, this.mDev_BmsWarn.Dcxkl_3));
            updateItem(16, 0, GetWarnValue(16, this.mDev_BmsWarn.Dcxkl_4));
            updateItem(17, 0, GetWarnValue(17, this.mDev_BmsWarn.Dcxkl_5));
            updateItem(18, 0, GetWarnValue(18, this.mDev_BmsWarn.Dcxkl_6));
            updateItem(19, 0, GetWarnValue(19, this.mDev_BmsWarn.Dcxkl_7));
            updateItem(20, 0, GetWarnValue(20, this.mDev_BmsWarn.Dcxkl_8));
            updateItem(21, 0, GetWarnValue(21, this.mDev_BmsWarn.Dcxkl_9));
            updateItem(22, 0, GetWarnValue(22, this.mDev_BmsWarn.Dcxkl_10));
            updateItem(23, 0, GetWarnValue(23, this.mDev_BmsWarn.Dcxkl_11));
            updateItem(24, 0, GetWarnValue(24, this.mDev_BmsWarn.Dcxkl_12));
            updateItem(25, 0, GetWarnValue(25, this.mDev_BmsWarn.Dcxkl_13));
            updateItem(26, 0, GetWarnValue(26, this.mDev_BmsWarn.Dcxkl_14));
            updateItem(27, 0, GetWarnValue(27, this.mDev_BmsWarn.Dcxkl_15));
            updateItem(28, 0, GetWarnValue(28, this.mDev_BmsWarn.Cdczwd));
            updateItem(29, 0, GetWarnValue(29, this.mDev_BmsWarn.Dcqy));
            updateItem(30, 0, GetWarnValue(30, this.mDev_BmsWarn.Dcgy));
            updateItem(31, 0, GetWarnValue(31, this.mDev_BmsWarn.Dcgw));
            updateItem(32, 0, GetWarnValue(32, this.mDev_BmsWarn.Dcwcd));
            updateItem(33, 0, GetWarnValue(33, this.mDev_BmsWarn.Fdgl));
            updateItem(34, 0, GetWarnValue(34, this.mDev_BmsWarn.Cdgl));
            updateItem(35, 0, GetWarnValue(35, this.mDev_BmsWarn.Gyld));
            updateItem(36, 0, GetWarnValue(36, this.mDev_BmsWarn.Jzgw));
        }
    }

    public String GetWarnValue(int id, int value) {
        if (value == 0) {
            showItem(id, 0);
        } else {
            showItem(id, 1);
        }
        switch (id) {
            case 0:
            case 1:
                if (value >= 0 && value < this.mSoctbbjStrs.length) {
                    return this.mSoctbbjStrs[value];
                }
            case 2:
            case 3:
            case 4:
                if (value >= 0 && value < this.mXdcgcbjStrs.length) {
                    return this.mXdcgcbjStrs[value];
                }
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                if (value >= 0 && value < this.mBmszjztbjStrs.length) {
                    return this.mBmszjztbjStrs[value];
                }
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
                if (value >= 0 && value < this.mDcxklStrs.length) {
                    return this.mDcxklStrs[value];
                }
            case 28:
                if (value >= 0 && value < this.mCdczwdbjStrs.length) {
                    return this.mCdczwdbjStrs[value];
                }
            case 29:
            case 30:
            case 31:
            case 32:
                if (value >= 0 && value < this.mXdcgcbjStrs.length) {
                    return this.mXdcgcbjStrs[value];
                }
            case 33:
            case 34:
            case 35:
            case 36:
                if (value >= 0 && value < this.mXdcgcbjStrs.length) {
                    return this.mXdcgcbjStrs[value];
                }
        }
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public void QueryData() {
    }
}
