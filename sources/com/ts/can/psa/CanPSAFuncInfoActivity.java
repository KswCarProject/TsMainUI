package com.ts.can.psa;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanPSAFuncInfoActivity extends CanPSABaseActivity implements View.OnClickListener, UserCallBack {
    public static final String TAG = "CanPSAFuncInfoActivity";
    private int FUNC_MAX = 0;
    private String[] mFuncArr;
    private CanDataInfo.PeugFuncInfo mFuncData = new CanDataInfo.PeugFuncInfo();
    private CanItemTextBtnList[] mItemFunc;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.PSAGetFunc(this.mFuncData);
        if (!i2b(this.mFuncData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mFuncData.Update)) {
            this.mFuncData.Update = 0;
            LayoutUI();
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.PSAQuery(57, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mFuncArr = getResources().getStringArray(R.array.can_psa_func);
        this.FUNC_MAX = this.mFuncArr.length;
        this.mItemFunc = new CanItemTextBtnList[this.FUNC_MAX];
        this.mItemFunc[0] = new CanItemTextBtnList((Context) this, 0);
        this.mItemFunc[0].ShowArrow(false);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        this.mManager.RemoveAllViews();
        int num = this.mFuncData.Num;
        if (num > this.FUNC_MAX) {
            num = this.FUNC_MAX;
        }
        if (num == 0) {
            this.mItemFunc[0].SetVal(R.string.can_check_end);
            this.mManager.AddView(this.mItemFunc[0].GetView());
            return;
        }
        for (int i = 0; i < num; i++) {
            if (this.mItemFunc[i] == null) {
                this.mItemFunc[i] = new CanItemTextBtnList((Context) this, 0);
                this.mItemFunc[i].ShowArrow(false);
            }
            this.mItemFunc[i].SetVal(this.mFuncArr[this.mFuncData.Info[i]]);
            this.mManager.AddView(this.mItemFunc[i].GetView());
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }
}
