package com.ts.can;

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

public class CanPradoCarInfoActivity extends CanToyotaBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_CONSUMPTION = 0;
    public static final int ITEM_DSP = 2;
    public static final int ITEM_PARK_ASSIST = 1;
    public static final String TAG = "CanPradoCarInfoActivity";
    protected static final int TOTAL_ITEM = 3;
    public static final int[] mOptId;
    protected CanItemTextBtnList[] mOpt;
    private CanDataInfo.ToyotaSysInfo mSysData = new CanDataInfo.ToyotaSysInfo();

    static {
        int[] iArr = new int[3];
        iArr[1] = 1;
        iArr[2] = 2;
        mOptId = iArr;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_list);
        CanScrollList sl = new CanScrollList(this);
        this.mOpt = new CanItemTextBtnList[3];
        for (int i = 0; i < 3; i++) {
            this.mOpt[i] = new CanItemTextBtnList((Context) this, 0);
            this.mOpt[i].SetIdClickListener(this, mOptId[i]);
            sl.AddView(this.mOpt[i].GetView());
        }
        this.mOpt[0].SetVal(R.string.can_consumption);
        this.mOpt[1].SetVal("Park Assist");
        this.mOpt[2].SetVal("DSP");
        this.mOpt[2].ShowGone(false);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanToyotaTripPerMinActivity.class);
                return;
            case 1:
                enterSubWin(CanPradoParkAssistActivity.class);
                return;
            case 2:
                enterSubWin(CanPradoDspActivity.class);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    private void ResetData(boolean check) {
        CanJni.ToyotaGetSysInfo(this.mSysData);
        if (this.mSysData.UpdateOnce != 0) {
            if (!check || this.mSysData.Update != 0) {
                this.mSysData.Update = 0;
                this.mOpt[2].ShowGone(this.mSysData.fgAmp);
            }
        } else if (!check) {
            CanJni.ToyotaQuery(50, 0);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
