package com.ts.can.gm.onstar;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanScrollList;

public class CanOnStarMainActivity extends CanOnStarBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_GPS = 0;
    public static final int ITEM_SPK = 1;
    public static final int ITEM_WIFI = 2;
    public static final String TAG = "CanOnStarMainActivity";
    protected static final int TOTAL_ITEM = 3;
    public static final int[] mOptIco = {R.drawable.can_icon_gps, R.drawable.can_icon_onstar, R.drawable.can_golf_icon14};
    public static final int[] mOptId;
    public static final int[] mOptTitle = {R.string.can_gps, R.string.can_onstar, R.string.can_onstar_wifi};
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
        if (8 == CanJni.GetCanType() || 88 == CanJni.GetCanType() || 151 == CanJni.GetCanType() || 210 == CanJni.GetCanType() || 263 == CanJni.GetCanType()) {
            setContentView(R.layout.activity_can_comm_list);
            CanScrollList sl = new CanScrollList(this);
            this.mOpt = new CanItemIcoList[3];
            int startItem = 0;
            int total = 2;
            if (88 == CanJni.GetCanType() || 151 == CanJni.GetCanType()) {
                startItem = 1;
                total = 2;
            } else if (263 == CanJni.GetCanType()) {
                startItem = 0;
                total = 3;
            } else if (210 == CanJni.GetCanType()) {
                startItem = 1;
                total = 3;
            }
            for (int i = startItem; i < total; i++) {
                this.mOpt[i] = new CanItemIcoList(this, mOptIco[i], mOptTitle[i]);
                this.mOpt[i].SetIdClickListener(this, mOptId[i]);
                sl.AddView(this.mOpt[i].GetView());
            }
            return;
        }
        finish();
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanOnStarNaviActivity.class);
                return;
            case 1:
                if (151 == CanJni.GetCanType()) {
                    CanFunc.showCanActivity(CanCarInfoSub1Activity.class, -1);
                    return;
                } else {
                    enterSubWin(CanOnStarSpkActivity.class);
                    return;
                }
            case 2:
                if (210 == CanJni.GetCanType() || 263 == CanJni.GetCanType()) {
                    CanFunc.showCanActivity(CanCarInfoSub1Activity.class, -3);
                    return;
                }
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
    }

    public void UserAll() {
        ResetData(true);
    }
}
