package com.ts.can.bmw.withcd;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
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
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanBmwWithCdCarCvbsDevView extends CanRelativeCarInfoView {
    public static int mBtCnt = 0;
    public static int mOldBtSta = 0;
    public static int mOldMode = 0;
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private AutoFitTextureView mCameraView;
    private RelativeLayoutManager mManager;

    public CanBmwWithCdCarCvbsDevView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        getActivity().setContentView(R.layout.activity_can_bmw_withcd_base);
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
        if (CanFunc.getInstance().IsCore() == 1 && CanFunc.IsRviewDis() > 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mCameraView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.leftMargin = 0;
            this.mCameraView.setLayoutParams(layoutParams);
        }
        this.mManager = new RelativeLayoutManager(getActivity(), R.id.can_bmw_withcd_base_layout);
        this.mManager.GetLayout().setOnTouchListener(this);
        this.mManager.GetLayout().setClickable(true);
        Log.d("nyw", "BmwWithCDCarSet");
        CanJni.BmwWithCDCarSet(162, 0, CanBmwWithCDCarInfoActivity.IsBmwType(), 0);
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
        if (event.getAction() == 0 || event.getAction() == 2 || event.getAction() != 1) {
            return false;
        }
        Mcu.SetCkey(8);
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public static void Init() {
        if (FtSet.Getlgb5() != 165) {
            FtSet.Setlgb1(1);
            FtSet.Setlgb4(0);
            FtSet.Setlgb5(165);
        }
    }

    public static void DealDevEvent() {
    }
}
