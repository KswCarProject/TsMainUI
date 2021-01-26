package com.ts.can.psa.rzc;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemTextBtnList;

public class CanPSARzcWarnInfoView extends CanScrollCarInfoView {
    public static final int ITEM_TYPE = 0;
    public static final String TAG = "CanPSAWarnInfoActivity";
    private int WARN_MAX = 0;
    private CanItemTextBtnList[] mItemWarn;
    private String[] mWarnArr;
    private CanDataInfo.PeugWarnInfo mWarnData;

    public CanPSARzcWarnInfoView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_gf_exits};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE};
        this.mWarnData = new CanDataInfo.PeugWarnInfo();
        this.mWarnArr = getActivity().getResources().getStringArray(R.array.can_psa_warn);
        this.mItemWarn = new CanItemTextBtnList[this.mWarnArr.length];
        this.mItemWarn[0] = new CanItemTextBtnList((Context) getActivity(), 0);
        this.mItemWarn[0].ShowArrow(false);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        getScrollManager().RemoveAllViews();
    }

    public void ResetData(boolean check) {
        CanJni.PSAGetWarn(this.mWarnData);
        if (!i2b(this.mWarnData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWarnData.Update)) {
            this.mWarnData.Update = 0;
            this.WARN_MAX = this.mWarnArr.length;
            LayoutUI();
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        getScrollManager().RemoveAllViews();
        int num = this.mWarnData.Num;
        if (num > this.WARN_MAX) {
            num = this.WARN_MAX;
        }
        if (num == 0) {
            this.mItemWarn[0].SetVal(R.string.can_check_end);
            getScrollManager().AddView(this.mItemWarn[0].GetView());
            return;
        }
        for (int i = 0; i < num; i++) {
            if (this.mItemWarn[i] == null) {
                this.mItemWarn[i] = new CanItemTextBtnList((Context) getActivity(), 0);
                this.mItemWarn[i].ShowArrow(false);
            }
            this.mItemWarn[i].SetVal(this.mWarnArr[this.mWarnData.Info[i]]);
            getScrollManager().AddView(this.mItemWarn[i].GetView());
        }
    }

    public void QueryData() {
        CanJni.PSAQuery(55, 0);
    }
}
