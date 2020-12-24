package com.ts.can.gm.mkc;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
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

public class CanLincsMkcExdActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener, View.OnClickListener {
    public static final int BTN_DISPLAY_SET = 1;
    public static final String TAG = "CanLincsMkcExdActivity";
    public static int mOldCamPort = 255;
    public static int mVedioTick = 0;
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private AutoFitTextureView mAutoFitTextView;
    private Button mBtnDisplaySet;
    private RelativeLayout mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_lincs_mkc_base);
        this.mManager = (RelativeLayout) findViewById(R.id.can_lincs_mkc_layout);
        this.mManager.setOnTouchListener(this);
        this.mManager.setClickable(true);
        this.mBtnDisplaySet = (Button) findViewById(R.id.btn_display);
        this.mBtnDisplaySet.setVisibility(4);
        this.mBtnDisplaySet.setOnClickListener(this);
        this.mBtnDisplaySet.setTag(1);
        this.mAutoFitTextView = (AutoFitTextureView) findViewById(R.id.DevtextureView);
        this.mAutoFitTextView.setVisibility(4);
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
        TsDisplay.GetInstance().SetDispParat(-1);
        MainSet.GetInstance().SetVideoChannel(0);
        BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.DevtextureView), false);
        mOldCamPort = 255;
        mVedioTick = 8;
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
        this.mAutoFitTextView.setVisibility(4);
    }

    public static void showLincsMkc5Win() {
        if (!mfgShow) {
            mfgAutoEnt = true;
            CanFunc.showCanActivity(CanLincsMkcExdActivity.class);
        }
    }

    public static void entLincsMkcMode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanLincsMkcExdActivity.class);
        }
    }

    public static void finishLincsMkcWin() {
        if (mfgShow && mfgAutoEnt) {
            mfgFinish = true;
        }
    }

    public static boolean IsLincsMkcWin() {
        return mfgShow;
    }

    private void ResetData(boolean check) {
    }

    public void UserAll() {
        ResetData(true);
        if (mVedioTick > 0) {
            mVedioTick--;
            if (mVedioTick == 1) {
                mVedioTick = 0;
                this.mAutoFitTextView.setVisibility(0);
            }
        }
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
        int x = (int) ((event.getX() * 800.0f) / 768.0f);
        int y = (int) ((event.getY() * 480.0f) / 432.0f);
        Log.d(TAG, "onTouch ACTION_MOVE event.getX() = " + x);
        Log.d(TAG, "onTouch ACTION_MOVE event.getY() = " + y);
        if (event.getAction() == 0) {
            CanJni.GmSbCarTouchCtl(x, y, 1);
        } else if (event.getAction() == 2) {
            CanJni.GmSbCarTouchCtl(x, y, 2);
        } else if (event.getAction() == 1) {
            CanJni.GmSbCarTouchCtl(x, y, 0);
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
