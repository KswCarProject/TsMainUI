package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanGolfStartStopActivity extends CanGolfBaseActivity implements UserCallBack {
    public static final String TAG = "CanGolfStartStopActivity";
    private CanItemTextBtnList[] mItemSS;
    private CanScrollList mManager;
    private CanDataInfo.GolfCarTip mSSData = new CanDataInfo.GolfCarTip();
    private String[] mStrMsgArray;
    private String[] mStrStaArray;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemSS = new CanItemTextBtnList[30];
        for (int i = 0; i < this.mItemSS.length; i++) {
            this.mItemSS[i] = new CanItemTextBtnList((Context) this, 0);
            this.mItemSS[i].ShowArrow(false);
        }
        this.mStrStaArray = getResources().getStringArray(R.array.can_start_stop_other_msg);
        this.mStrMsgArray = getResources().getStringArray(R.array.can_start_stop_msg);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GolfGetCarTip(this.mSSData);
        if (!i2b(this.mSSData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSSData.Update)) {
            this.mSSData.Update = 0;
            this.mManager.RemoveAllViews();
            if (this.mSSData.Num != 0) {
                int num = this.mSSData.Num;
                if (num > this.mItemSS.length) {
                    num = this.mItemSS.length;
                }
                int j = 0;
                for (int i = 0; i < num; i++) {
                    if (this.mSSData.TipData[i] < this.mStrMsgArray.length) {
                        this.mItemSS[j].SetVal(this.mStrMsgArray[this.mSSData.TipData[i]]);
                        this.mManager.AddView(this.mItemSS[j].GetView());
                        j++;
                    }
                }
            } else if (this.mSSData.StartStop < this.mStrStaArray.length) {
                this.mItemSS[0].SetVal(this.mStrStaArray[this.mSSData.StartStop]);
                this.mManager.AddView(this.mItemSS[0].GetView());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        if (!i2b(this.mSSData.UpdateOnce)) {
            CanJni.GolfQuery(96, 0);
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

    public void UserAll() {
        ResetData(true);
    }
}
