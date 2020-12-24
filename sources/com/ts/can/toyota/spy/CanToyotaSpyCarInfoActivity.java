package com.ts.can.toyota.spy;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanScrollList;

public class CanToyotaSpyCarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_CAR_INFO = 0;
    public static final int ITEM_CONSUMPTION = 1;
    public static final int ITEM_HYBRID = 2;
    public static final String TAG = "CanToyotaSpyCarInfoActivity";
    protected static final int TOTAL_ITEM = 3;
    public static final int[] mOptIco = {R.drawable.can_icon_service, R.drawable.can_icon_consumption, R.drawable.can_icon_hybrid};
    public static final int[] mOptId;
    public static final int[] mOptTitle = {R.string.can_car_info, R.string.can_consumption, R.string.can_hybrid_image};
    private CanDataInfo.ToyotaHybrid mHybridData = new CanDataInfo.ToyotaHybrid();
    protected CanItemIcoList[] mOpt;

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
        this.mOpt = new CanItemIcoList[3];
        for (int i = 0; i < 3; i++) {
            this.mOpt[i] = new CanItemIcoList(this, mOptIco[i], mOptTitle[i]);
            this.mOpt[i].GetView().setOnClickListener(this);
            this.mOpt[i].GetView().setTag(Integer.valueOf(mOptId[i]));
            sl.AddView(this.mOpt[i].GetView());
        }
        this.mOpt[2].ShowGone(false);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanToyotaSpyBaseInfoActivity.class);
                return;
            case 1:
                enterSubWin(CanToyotaSpyTripPerMinActivity.class);
                return;
            case 2:
                enterSubWin(CanToyotaSpyHybridActivity.class);
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
        CanJni.ToyotaGetHybrid(this.mHybridData);
        if (this.mHybridData.UpdateOnce == 0) {
            return;
        }
        if (!check || this.mHybridData.Update != 0) {
            this.mHybridData.Update = 0;
            this.mOpt[2].ShowGone(this.mHybridData.fgHybrid);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
