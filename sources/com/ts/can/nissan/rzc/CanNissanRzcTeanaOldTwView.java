package com.ts.can.nissan.rzc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.can.CanBaseCarDeviceActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;

public class CanNissanRzcTeanaOldTwView extends CanRelativeCarInfoView {
    public static boolean mfgShow = false;
    private AutoFitTextureView mCameraView;

    public CanNissanRzcTeanaOldTwView(Activity activity) {
        super(activity);
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (((int) event.getY()) * 1024) / CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
        if (action == 0) {
            CanJni.NissanRzcTouchCmd(1, x, y);
        } else if (action == 1) {
            CanJni.NissanRzcTouchCmd(0, x, y);
        }
        return false;
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
        MainSet.GetInstance().SetVideoChannel(0);
        getRelativeManager().GetLayout().setOnTouchListener(this);
        getRelativeManager().GetLayout().setClickable(true);
        getRelativeManager().AddViewWrapContent(View.inflate(getActivity().getApplicationContext(), R.layout.activity_can_withcd_base, (ViewGroup) null), 0, 0);
        this.mCameraView = (AutoFitTextureView) getActivity().findViewById(R.id.DevtextureView);
        BackcarService.getInstance().StartCamera(this.mCameraView, true);
        mfgShow = true;
    }

    public void doOnPause() {
        super.doOnPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        BackcarService.getInstance().StopCamera();
        mfgShow = false;
    }

    public static void entNissanRzcTeanaOldTwWithCDMode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanBaseCarDeviceActivity.class);
        }
    }

    public static boolean IsNissanRzcTeanaOldTwWithCD() {
        return mfgShow;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
