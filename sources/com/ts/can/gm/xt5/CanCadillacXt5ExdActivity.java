package com.ts.can.gm.xt5;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanFunc;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.ScreenSet;
import com.yyw.ts70xhw.FtSet;

public class CanCadillacXt5ExdActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener, View.OnClickListener {
    public static final int BTN_DISPLAY_SET = 1;
    public static final String TAG = "CanCadillacXt5ExdActivity";
    public static int mOldCamPort = 255;
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private Button mBtnDisplaySet;
    private RelativeLayout mManager;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_cadillax_xt5_base);
        this.mManager = (RelativeLayout) findViewById(R.id.can_cadillax_xt5_layout);
        this.mManager.setBackgroundResource(R.drawable.can_aode_bg);
        this.mManager.setOnTouchListener(this);
        this.mManager.setClickable(true);
        this.mBtnDisplaySet = (Button) findViewById(R.id.btn_display);
        this.mBtnDisplaySet.setVisibility(4);
        this.mBtnDisplaySet.setOnClickListener(this);
        this.mBtnDisplaySet.setTag(1);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        if (!mfgAutoEnt) {
            Evc.GetInstance().evol_workmode_set(12);
            Log.d(TAG, "WORKMODE_EXD");
        }
        mfgShow = true;
        mfgFinish = false;
        Log.d(TAG, "onResume");
        getWindow().getDecorView().setSystemUiVisibility(4612);
        TsDisplay.GetInstance().SetDispParat(-1);
        if (10 == CanJni.GetSubType()) {
            MainSet.GetInstance().SetVideoChannel(2);
        } else {
            MainSet.GetInstance().SetVideoChannel(0);
        }
        BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.DevtextureView), false);
        mOldCamPort = 255;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        mfgShow = false;
        mfgAutoEnt = false;
        Log.d(TAG, "onPause");
        BackcarService.getInstance().StopCamera();
        this.mBtnDisplaySet.setVisibility(4);
        ScreenSet.GetInstance().Hide();
    }

    public static void showCadillacXt5Win() {
        if (!mfgShow) {
            mfgAutoEnt = true;
            CanFunc.showCanActivity(CanCadillacXt5ExdActivity.class);
        }
    }

    public static void entCadillacXt5Mode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanCadillacXt5ExdActivity.class);
        }
    }

    public static void finishCadillacXt5Win() {
        if (mfgShow && mfgAutoEnt) {
            mfgFinish = true;
        }
    }

    public static boolean IsCadillacXt5Win() {
        return mfgShow;
    }

    private void ResetData(boolean check) {
    }

    public void UserAll() {
        ResetData(true);
        if (mfgShow && mfgFinish) {
            mfgFinish = false;
            mfgShow = false;
            finish();
        }
        int curMode = MainUI.IsCameraMode();
        if (mOldCamPort != curMode) {
            mOldCamPort = curMode;
            Log.d(TAG, "curMode =" + curMode);
            if (curMode == 0) {
                this.mBtnDisplaySet.setVisibility(4);
                ScreenSet.GetInstance().Hide();
            } else if (FtSet.GetTconAdj() != 0) {
                this.mBtnDisplaySet.setVisibility(0);
            }
        }
    }

    public boolean onTouch(View arg0, MotionEvent event) {
        if (event.getAction() == 0) {
            CanJni.GmSbCarTouchCtl((int) event.getX(), (int) event.getY(), 1);
        } else if (event.getAction() == 2) {
            Log.d(TAG, "onTouch ACTION_MOVE event.getY() = " + event.getY());
            Log.d(TAG, "onTouch ACTION_MOVE event.getY() = " + ((event.getY() / 425.0f) * 480.0f));
            CanJni.GmSbCarTouchCtl((int) event.getX(), (int) event.getY(), 2);
        } else if (event.getAction() == 1) {
            CanJni.GmSbCarTouchCtl((int) event.getX(), (int) event.getY(), 0);
        }
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                ScreenSet.GetInstance().Show(0);
                return;
            default:
                return;
        }
    }
}
