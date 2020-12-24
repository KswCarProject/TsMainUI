package com.ts.can.df.jyx5;

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

public class CanJYX5RzcDvrView extends CanRelativeCarInfoView {
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private AutoFitTextureView mCameraView;

    public CanJYX5RzcDvrView(Activity activity) {
        super(activity);
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() != 0) {
        }
        return false;
    }

    public void doOnResume() {
        super.doOnResume();
        CanJni.SenovaRzcDvrCmd(2);
        mfgShow = true;
        mfgFinish = false;
        MainSet.GetInstance().SetVideoChannel(2);
        getRelativeManager().AddViewWrapContent(View.inflate(getActivity().getApplicationContext(), R.layout.activity_can_base_textview, (ViewGroup) null), 0, 0);
        this.mCameraView = (AutoFitTextureView) getActivity().findViewById(R.id.DevtextureView);
        BackcarService.getInstance().StartCamera(this.mCameraView, true);
    }

    public void doOnPause() {
        super.doOnPause();
        mfgShow = false;
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        BackcarService.getInstance().StopCamera();
        CanJni.SenovaRzcDvrCmd(0);
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
