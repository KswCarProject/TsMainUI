package com.ts.can.ford;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanFordWarnInfoActivity extends CanFordBaseActivity implements View.OnClickListener, UserCallBack {
    public static final String TAG = "CanFordWarnInfoActivity";
    private int WARN_MAX = 0;
    private CanItemTextBtnList[] mItemWarn;
    private CanScrollList mManager;
    private String[] mWarnArr;
    private CanDataInfo.FordWarn mWarnData = new CanDataInfo.FordWarn();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.FordGetWarn(this.mWarnData);
        if (!i2b(this.mWarnData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWarnData.Update)) {
            this.mWarnData.Update = 0;
            LayoutUI();
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        if (this.mWarnData.UpdateOnce == 0) {
            CanJni.FordQuery(42, 0);
        }
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
        this.mWarnArr = getResources().getStringArray(R.array.can_ford_warn_text);
        this.WARN_MAX = this.mWarnArr.length;
        this.mItemWarn = new CanItemTextBtnList[this.WARN_MAX];
        this.mItemWarn[0] = new CanItemTextBtnList((Context) this, 0);
        this.mItemWarn[0].ShowArrow(false);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        this.mManager.RemoveAllViews();
        int num = this.mWarnData.Num;
        if (num > this.WARN_MAX) {
            num = this.WARN_MAX;
        }
        if (num == 0) {
            this.mItemWarn[0].SetVal(R.string.can_check_end);
            this.mItemWarn[0].SetColor(-1);
            this.mManager.AddView(this.mItemWarn[0].GetView());
            return;
        }
        for (int i = 0; i < num; i++) {
            if (this.mItemWarn[i] == null) {
                this.mItemWarn[i] = new CanItemTextBtnList((Context) this, 0);
                this.mItemWarn[i].ShowArrow(false);
            }
            switch (this.mWarnData.Color[i]) {
                case 1:
                    this.mItemWarn[i].SetColor(-256);
                    break;
                case 2:
                    this.mItemWarn[i].SetColor(SupportMenu.CATEGORY_MASK);
                    break;
                default:
                    this.mItemWarn[i].SetColor(-1);
                    break;
            }
            if (this.mWarnData.Value[i] < this.mWarnArr.length && this.mWarnData.Value[i] >= 0) {
                this.mItemWarn[i].SetVal(this.mWarnArr[this.mWarnData.Value[i]]);
                this.mManager.AddView(this.mItemWarn[i].GetView());
            }
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }
}
