package com.ts.can.blsu.od;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.can.CanBaseCarDeviceActivity;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;

public class CanBlsuOdDvrView extends CanRelativeCarInfoView {
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private AutoFitTextureView mCameraView;

    public CanBlsuOdDvrView(Activity activity) {
        super(activity);
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action != 0 && action != 2) {
            return false;
        }
        CanJni.BlsuOdAvmTouchCmd((int) ((event.getX() * 255.0f) / 1024.0f), (int) ((event.getY() * 127.0f) / 545.0f));
        return false;
    }

    public void doOnResume() {
        super.doOnResume();
        mfgShow = true;
        mfgFinish = false;
        MainSet.GetInstance().SetVideoChannel(2);
        getRelativeManager().GetLayout().setOnTouchListener(this);
        getRelativeManager().GetLayout().setClickable(true);
        getRelativeManager().AddViewWrapContent(View.inflate(getActivity().getApplicationContext(), R.layout.activity_can_base_textview, (ViewGroup) null), 0, 0);
        this.mCameraView = (AutoFitTextureView) getActivity().findViewById(R.id.DevtextureView);
        BackcarService.getInstance().StartCamera(this.mCameraView, true);
    }

    public void doOnPause() {
        super.doOnPause();
        mfgShow = false;
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        BackcarService.getInstance().StopCamera();
    }

    public static void showCanBlusOdView() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanBaseCarDeviceActivity.class);
        }
    }

    public static void finishCanBlusOdView() {
        if (mfgShow) {
            mfgFinish = true;
        }
    }

    public static void DealDevEvent() {
        if (mfgShow) {
            mfgFinish = true;
        } else {
            CanFunc.showCanActivity(CanBaseCarDeviceActivity.class);
        }
    }

    public void doOnFinish() {
        super.doOnFinish();
        if (mfgShow && mfgFinish) {
            mfgFinish = false;
            mfgShow = false;
            getActivity().finish();
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
