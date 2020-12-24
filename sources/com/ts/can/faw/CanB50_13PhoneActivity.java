package com.ts.can.faw;

import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemProgressList;
import com.ts.other.RelativeLayoutManager;

public class CanB50_13PhoneActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_VOL = 0;
    public static final String TAG = "CanB50_13PhoneActivity";
    protected static boolean mIsActive;
    protected RelativeLayoutManager mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.AddImage(428, 108, R.drawable.original_car_phone);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void ResetData(boolean check) {
        if (!mIsActive) {
            finish();
        }
    }

    public static void DealStatus(boolean isCamMode, boolean on) {
        if (mIsActive != on) {
            mIsActive = on;
            if (on) {
                if (!isCamMode) {
                    CanFunc.showCanActivity(CanB50_13PhoneActivity.class);
                }
                Evc.GetInstance().evol_aux_hold();
                return;
            }
            Evc.GetInstance().evol_aux_release();
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }
}