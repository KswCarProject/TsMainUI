package com.ts.can.psa.rzc;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemTextBtnList;

public class CanPSARzcCheckInfoView extends CanScrollCarInfoView {
    public static final int ITEM_TYPE = 1;
    private int CHK_MAX = 0;
    private CanDataInfo.PeugCheckInfo mCheckData;
    private String[] mChkArr;
    private CanItemTextBtnList[] mItemChk;

    public CanPSARzcCheckInfoView(Activity activity) {
        super(activity, 2);
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
        this.mItemTitleIds = new int[]{R.string.can_gf_exits, R.string.can_gf_exits};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        this.mChkArr = getActivity().getResources().getStringArray(R.array.can_psa_rzc_check);
        this.mItemChk = new CanItemTextBtnList[this.mChkArr.length];
        this.mItemChk[0] = new CanItemTextBtnList((Context) getActivity(), 0);
        this.mItemChk[0].ShowArrow(false);
        this.mCheckData = new CanDataInfo.PeugCheckInfo();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        getScrollManager().RemoveAllViews();
    }

    public void ResetData(boolean check) {
        CanJni.PSAGetCheck(this.mCheckData);
        if (!i2b(this.mCheckData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCheckData.Update)) {
            this.mCheckData.Update = 0;
            this.CHK_MAX = this.mChkArr.length;
            LayoutUI();
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        getScrollManager().RemoveAllViews();
        int num = this.mCheckData.Num;
        if (num > this.CHK_MAX) {
            num = this.CHK_MAX;
        }
        Log.d("lq", "num:" + num);
        if (num == 0) {
            this.mItemChk[0].SetVal(R.string.can_check_end);
            getScrollManager().AddView(this.mItemChk[0].GetView());
            return;
        }
        for (int i = 0; i < num; i++) {
            if (this.mItemChk[i] == null) {
                this.mItemChk[i] = new CanItemTextBtnList((Context) getActivity(), 0);
                this.mItemChk[i].ShowArrow(false);
            }
            this.mItemChk[i].SetVal(this.mChkArr[this.mCheckData.Info[i]]);
            getScrollManager().AddView(this.mItemChk[i].GetView());
        }
    }

    public void QueryData() {
        CanJni.PSAQueryCheck();
    }
}
