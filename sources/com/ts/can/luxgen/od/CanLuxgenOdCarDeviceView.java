package com.ts.can.luxgen.od;

import android.app.Activity;
import android.util.Log;
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
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;
import com.yyw.ts70xhw.Mcu;

public class CanLuxgenOdCarDeviceView extends CanRelativeCarInfoView {
    public static final String TAG = "CanLuxgenOdCarDeviceView";
    private AutoFitTextureView mCameraView;

    public CanLuxgenOdCarDeviceView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int x = (int) ((event.getX() * 800.0f) / 1024.0f);
        int y = (int) ((event.getY() * 480.0f) / 600.0f);
        Log.d(TAG, "x = " + x);
        if (action != 0 && action != 2) {
            return false;
        }
        CanJni.LuxgenOdRecordCmd(x, y);
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
    }

    public void doOnResume() {
        boolean z;
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
        MainSet.GetInstance().SetVideoChannel(2);
        getRelativeManager().GetLayout().setOnTouchListener(this);
        getRelativeManager().GetLayout().setClickable(true);
        getRelativeManager().AddViewWrapContent(View.inflate(getActivity().getApplicationContext(), R.layout.activity_can_withcd_base, (ViewGroup) null), 0, 0);
        this.mCameraView = (AutoFitTextureView) getActivity().findViewById(R.id.DevtextureView);
        if ((Mcu.GetPowState() & 4) > 0) {
            z = false;
        } else {
            z = true;
        }
        BackcarService.getInstance().StartCamera(this.mCameraView, Boolean.valueOf(z).booleanValue());
        Log.d(TAG, "doOnResume ");
    }

    public void ResetData(boolean check) {
    }

    public void doOnPause() {
        super.doOnPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        BackcarService.getInstance().StopCamera();
        Log.d(TAG, "doOnPause ");
    }

    public void QueryData() {
    }
}
