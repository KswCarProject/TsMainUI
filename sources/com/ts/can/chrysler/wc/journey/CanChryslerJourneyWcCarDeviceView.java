package com.ts.can.chrysler.wc.journey;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.can.CanCarDeviceActivity;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;
import com.ts.other.RelativeLayoutManager;

public class CanChryslerJourneyWcCarDeviceView extends CanRelativeCarInfoView {
    public static int mBtCnt = 0;
    public static int mOldBtSta = 0;
    public static int mOldMode = 0;
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private AutoFitTextureView mCameraView;
    private RelativeLayoutManager mManager;

    public CanChryslerJourneyWcCarDeviceView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        getActivity().setContentView(R.layout.activity_can_base_textview);
    }

    public void doOnResume() {
        super.doOnResume();
        if (!mfgAutoEnt) {
            Evc.GetInstance().evol_workmode_set(12);
        }
        mfgShow = true;
        mfgFinish = false;
        TsDisplay.GetInstance().SetDispParat(-1);
        MainSet.GetInstance().SetVideoChannel(2);
        if (CanFunc.getInstance().IsCore() == 1) {
            BackcarService.getInstance().SetSource(1);
        }
        this.mCameraView = (AutoFitTextureView) getActivity().findViewById(R.id.DevtextureView);
        this.mCameraView.setOnTouchListener(this);
        BackcarService.getInstance().StartCamera(this.mCameraView, false);
        this.mManager = new RelativeLayoutManager(getActivity(), R.id.can_base_textview);
        this.mManager.GetLayout().setOnTouchListener(this);
        this.mManager.GetLayout().setClickable(true);
        CanJni.ChryslerWcTouchCmd(0, 0, 0, 1);
    }

    public void doOnPause() {
        super.doOnPause();
        mfgShow = false;
        mfgAutoEnt = false;
        BackcarService.getInstance().StopCamera();
    }

    public static void showWin() {
        if (!mfgShow) {
            mfgAutoEnt = true;
            CanFunc.showCanActivity(CanCarDeviceActivity.class);
        }
    }

    public static void entMode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanCarDeviceActivity.class);
        }
    }

    public static void finishWin() {
        if (mfgShow) {
            mfgFinish = true;
        }
    }

    public static boolean IsWin() {
        return mfgShow;
    }

    public void ResetData(boolean check) {
        if (mfgShow && mfgFinish) {
            mfgFinish = false;
            mfgShow = false;
            getActivity().finish();
        }
    }

    public void QueryData() {
    }

    public boolean onTouch(View arg0, MotionEvent event) {
        int x;
        int y;
        if (event.getX() >= 0.0f && event.getY() >= 0.0f) {
            if (MainSet.GetScreenType() == 9) {
                x = (int) ((event.getX() * 1560.0f) / 1920.0f);
                y = (int) ((event.getY() * 1900.0f) / 665.0f);
            } else if (MainSet.GetScreenType() == 10) {
                x = (int) ((event.getX() * 1560.0f) / 1280.0f);
                y = (int) ((event.getY() * 1900.0f) / 665.0f);
            } else {
                x = (int) ((event.getX() * 1560.0f) / 1024.0f);
                y = (int) ((event.getY() * 1900.0f) / 545.0f);
            }
            if (event.getAction() == 0 || event.getAction() == 2) {
                CanJni.ChryslerWcTouchCmd(1, x, y, 1);
            } else if (event.getAction() == 1) {
                CanJni.ChryslerWcTouchCmd(0, x, y, 1);
            }
        }
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public static void Init() {
    }

    public static void DealDevEvent() {
    }
}
