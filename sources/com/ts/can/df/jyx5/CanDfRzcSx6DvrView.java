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

public class CanDfRzcSx6DvrView extends CanRelativeCarInfoView {
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private AutoFitTextureView mCameraView;

    public CanDfRzcSx6DvrView(Activity activity) {
        super(activity);
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int x = (int) ((event.getX() * 800.0f) / ((float) this.mCameraView.getWidth()));
        int y = (int) ((event.getY() * 480.0f) / ((float) this.mCameraView.getHeight()));
        if (action == 0 || action == 2) {
            CanJni.DfJyRzcRecordCmd(3, 1, x, y);
        } else if (action == 1) {
            CanJni.DfJyRzcRecordCmd(3, 0, x, y);
        }
        return false;
    }

    public void doOnResume() {
        super.doOnResume();
        CanJni.DfJyRzcRecordCmd(1, 0, 0, 0);
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
        CanJni.DfJyRzcRecordCmd(2, 0, 0, 0);
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
