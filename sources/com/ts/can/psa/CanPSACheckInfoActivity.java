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

public class CanPSACheckInfoActivity extends CanPSABaseActivity implements View.OnClickListener, UserCallBack {
    public static final String TAG = "CanPSACheckInfoActivity";
    private int CHK_MAX = 0;
    private CanDataInfo.PeugCheckInfo mCheckData = new CanDataInfo.PeugCheckInfo();
    private String[] mChkArr;
    private CanItemTextBtnList[] mItemChk;
    private CanScrollList mManager;
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.PSAGetCheck(this.mCheckData);
        if (!i2b(this.mCheckData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCheckData.Update)) {
            this.mCheckData.Update = 0;
            LayoutUI();
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.PSAQueryCheck();
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
        if (CanJni.GetCanFsTp() == 127) {
            this.mChkArr = getResources().getStringArray(R.array.can_psa_rzc_check);
        } else {
            this.mChkArr = getResources().getStringArray(R.array.can_psa_check);
        }
        this.CHK_MAX = this.mChkArr.length;
        this.mItemChk = new CanItemTextBtnList[this.CHK_MAX];
        this.mItemChk[0] = new CanItemTextBtnList((Context) this, 0);
        this.mItemChk[0].ShowArrow(false);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        this.mManager.RemoveAllViews();
        int num = this.mCheckData.Num;
        if (num > this.CHK_MAX) {
            num = this.CHK_MAX;
        }
        if (num == 0) {
            this.mItemChk[0].SetVal(R.string.can_check_end);
            this.mManager.AddView(this.mItemChk[0].GetView());
            return;
        }
        for (int i = 0; i < num; i++) {
            if (this.mItemChk[i] == null) {
                this.mItemChk[i] = new CanItemTextBtnList((Context) this, 0);
                this.mItemChk[i].ShowArrow(false);
            }
            this.mItemChk[i].SetVal(this.mChkArr[this.mCheckData.Info[i]]);
            this.mManager.AddView(this.mItemChk[i].GetView());
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }
}
