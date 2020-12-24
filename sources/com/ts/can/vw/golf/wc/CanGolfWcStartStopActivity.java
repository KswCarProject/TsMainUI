package com.ts.can.vw.golf.wc;

import android.content.Context;
import android.os.Bundle;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanGolfWcStartStopActivity extends CanBaseActivity implements UserCallBack {
    private CanItemTextBtnList[] mItemSS = new CanItemTextBtnList[7];
    private CanDataInfo.GolfWcStartStopWarn mSSWarn = new CanDataInfo.GolfWcStartStopWarn();
    private String[] mSSWarnTips;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        CanScrollList mManager = new CanScrollList(this);
        for (int i = 0; i < this.mItemSS.length; i++) {
            this.mItemSS[i] = new CanItemTextBtnList((Context) this, 0);
            this.mItemSS[i].ShowArrow(false);
            this.mItemSS[i].ShowGone(false);
            mManager.AddView(this.mItemSS[i].GetView());
        }
        this.mSSWarnTips = getResources().getStringArray(R.array.can_golf_wc_start_stop_msg);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GolfWcGetStartStopWarn(this.mSSWarn);
        if (!i2b(this.mSSWarn.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSSWarn.Update)) {
            this.mSSWarn.Update = 0;
            int num = this.mSSWarn.Num;
            int[] indexs = this.mSSWarn.Warn;
            if (num >= 0 && num <= 7) {
                for (int i = 0; i < this.mItemSS.length; i++) {
                    if (i < num) {
                        this.mItemSS[i].ShowGone(true);
                        if (indexs[i] > this.mSSWarnTips.length - 1) {
                            indexs[i] = 0;
                        }
                        this.mItemSS[i].SetVal(this.mSSWarnTips[indexs[i]]);
                    } else {
                        this.mItemSS[i].ShowGone(false);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfWcQueryData(1, 117);
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

    public void UserAll() {
        ResetData(true);
    }
}
