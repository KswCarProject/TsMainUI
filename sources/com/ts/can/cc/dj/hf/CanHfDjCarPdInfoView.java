package com.ts.can.cc.dj.hf;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHfDjCarPdInfoView extends CanScrollCarInfoView {
    private String[] mFxpzxjStrs;
    private CanDataInfo.CcHfDj_PdInfo mPdInfo;
    private String[] mSxqjStrs;
    private String[] mZyqjStrs;

    public CanHfDjCarPdInfoView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_zyqj, R.string.can_fxpzxj, R.string.can_sxqj};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mZyqjStrs = new String[2];
        this.mFxpzxjStrs = new String[2];
        this.mSxqjStrs = new String[2];
        Resources resources = getActivity().getResources();
        this.mZyqjStrs[0] = resources.getString(R.string.can_zyqj_zq);
        this.mZyqjStrs[1] = resources.getString(R.string.can_zyqj_yq);
        this.mFxpzxjStrs[0] = resources.getString(R.string.can_fxpzxj_zz);
        this.mFxpzxjStrs[1] = resources.getString(R.string.can_fxpzxj_yz);
        this.mSxqjStrs[0] = resources.getString(R.string.can_sxqj_sq);
        this.mSxqjStrs[1] = resources.getString(R.string.can_sxqj_xq);
        this.mPdInfo = new CanDataInfo.CcHfDj_PdInfo();
    }

    public void ResetData(boolean check) {
        CanJni.CcHfDjGetPdInfo(this.mPdInfo);
        if (!i2b(this.mPdInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPdInfo.Update)) {
            this.mPdInfo.Update = 0;
            if (this.mPdInfo.Lrqj < 2 && this.mPdInfo.Lrqj >= 0) {
                updateItem(0, 0, String.valueOf(this.mZyqjStrs[this.mPdInfo.Lrqj]) + this.mPdInfo.LrqjVal + "°");
            }
            if (this.mPdInfo.Sw < 2 && this.mPdInfo.Sw >= 0) {
                updateItem(1, 0, String.valueOf(this.mFxpzxjStrs[this.mPdInfo.Sw]) + this.mPdInfo.SwVal + "°");
            }
            if (this.mPdInfo.Upd < 2 && this.mPdInfo.Upd >= 0) {
                updateItem(2, 0, String.valueOf(this.mSxqjStrs[this.mPdInfo.Upd]) + this.mPdInfo.UpdVal + "°");
            }
        }
    }

    public void QueryData() {
        CanJni.CcHfDjQuery(17);
    }
}
