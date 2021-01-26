package com.ts.can.psa.rzc;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemTextBtnList;

public class CanPSARzcFuncInfoView extends CanScrollCarInfoView {
    public static final int ITEM_TYPE = 0;
    private int FUNC_MAX = 0;
    private String[] mFuncArr;
    private CanDataInfo.PeugFuncInfo mFuncData;
    private CanItemTextBtnList[] mItemFunc;

    public CanPSARzcFuncInfoView(Activity activity) {
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
        this.mFuncArr = getActivity().getResources().getStringArray(R.array.can_psa_func);
        this.mItemFunc = new CanItemTextBtnList[this.mFuncArr.length];
        this.mItemFunc[0] = new CanItemTextBtnList((Context) getActivity(), 0);
        this.mItemFunc[0].ShowArrow(false);
        this.mFuncData = new CanDataInfo.PeugFuncInfo();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        getScrollManager().RemoveAllViews();
    }

    public void ResetData(boolean check) {
        CanJni.PSAGetFunc(this.mFuncData);
        if (!i2b(this.mFuncData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mFuncData.Update)) {
            this.mFuncData.Update = 0;
            this.FUNC_MAX = this.mFuncArr.length;
            LayoutUI();
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        getScrollManager().RemoveAllViews();
        int num = this.mFuncData.Num;
        if (num > this.FUNC_MAX) {
            num = this.FUNC_MAX;
        }
        if (num == 0) {
            this.mItemFunc[0].SetVal(R.string.can_check_end);
            getScrollManager().AddView(this.mItemFunc[0].GetView());
            return;
        }
        for (int i = 0; i < num; i++) {
            if (this.mItemFunc[i] == null) {
                this.mItemFunc[i] = new CanItemTextBtnList((Context) getActivity(), 0);
                this.mItemFunc[i].ShowArrow(false);
            }
            this.mItemFunc[i].SetVal(this.mFuncArr[this.mFuncData.Info[i]]);
            getScrollManager().AddView(this.mItemFunc[i].GetView());
        }
    }

    public void QueryData() {
        CanJni.PSAQuery(57, 0);
    }
}
