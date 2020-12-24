package com.ts.can.chery.airuize;

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
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;

public class CanCheryAiRuizeDvrView extends CanRelativeCarInfoView {
    private AutoFitTextureView mCameraView;

    public CanCheryAiRuizeDvrView(Activity activity) {
        super(activity);
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == 0 || action == 2) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (x <= 1024 && x >= 0 && y >= 0 && y <= 600) {
                int x2 = (x * 1920) / 1024;
                int y2 = (y * 720) / CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
                CanJni.CheryRzcJtX70DvrCmd(0, x2 >> 8, x2 & 255, y2 >> 8, y2 & 255);
            }
        }
        return false;
    }

    public void doOnResume() {
        CanJni.CheryRzcJtX70DvrCmd(1, 1, 0, 0, 0);
        super.doOnResume();
        MainSet.GetInstance().SetVideoChannel(0);
        getRelativeManager().GetLayout().setOnTouchListener(this);
        getRelativeManager().GetLayout().setClickable(true);
        getRelativeManager().AddViewWrapContent(View.inflate(getActivity().getApplicationContext(), R.layout.activity_can_withcd_base, (ViewGroup) null), 0, 0);
        this.mCameraView = (AutoFitTextureView) getActivity().findViewById(R.id.DevtextureView);
        BackcarService.getInstance().StartCamera(this.mCameraView, true);
    }

    public void doOnPause() {
        CanJni.CheryRzcJtX70DvrCmd(1, 0, 0, 0, 0);
        super.doOnPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        BackcarService.getInstance().StopCamera();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
