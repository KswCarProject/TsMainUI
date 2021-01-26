package com.ts.backcar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.TsDisplay;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.WinShow;
import com.yyw.ts70xhw.FtSet;

public class BackcarMainActivity extends Activity {
    private static final String TAG = "BackcarMainActivity";
    /* access modifiers changed from: private */
    public Runnable CheckBackCarState = new Runnable() {
        public void run() {
            if (MainUI.IsCameraMode() == 0 || BackcarService.getInstance().bForceExit) {
                if (!BackcarMainActivity.this.bActivityOntop) {
                    Log.i("scj", "Runnable  DestroyCamera");
                    BackcarMainActivity.this.DestroyCamera();
                    BackcarMainActivity.this.CheckFcamMode();
                }
                BackcarMainActivity.this.mHandler.removeCallbacks(BackcarMainActivity.this.CheckBackCarState);
                BackcarMainActivity.this.finish();
                Log.i("scj", "finish");
                return;
            }
            BackcarMainActivity.this.mHandler.postDelayed(BackcarMainActivity.this.CheckBackCarState, 30);
        }
    };
    public boolean bActivityOntop = false;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();

    /* access modifiers changed from: package-private */
    public void CheckFcamMode() {
        if (!MainSet.GetInstance().IsHaveFcam()) {
            Log.i("scj", "not have fcam");
        } else if (FtSet.GetFcamTime() > 0 && MainUI.GetInstance().GetMcuState() == 0 && !BackcarService.getInstance().bForceExit) {
            switch (FtSet.GetFcamTime()) {
                case 1:
                    MainSet.nFcamTime = 75;
                    break;
                case 2:
                    MainSet.nFcamTime = 125;
                    break;
                case 3:
                    MainSet.nFcamTime = 200;
                    break;
            }
            if (MainSet.bIsFrontCam) {
                MainSet.nFcamTime = 0;
            }
            WinShow.GotoWin(14, 0);
            Log.i("scj", "goto fcam");
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getWindow().addFlags(256);
        getWindow().addFlags(512);
        setContentView(new View(this));
        this.mHandler.postDelayed(this.CheckBackCarState, 300);
        super.onCreate(savedInstanceState);
    }

    /* access modifiers changed from: package-private */
    public void DestroyCamera() {
        if (MainSet.GetScreenType() != 3) {
            TsDisplay.GetInstance().SetSrcMute(5);
        } else {
            TsDisplay.GetInstance().SetSrcMute(16);
        }
        TsDisplay.GetInstance().SetDispParat(-1);
        if (MainUI.mCanInterface != null) {
            MainUI.mCanInterface.EnterCamera(0);
        }
        MainUI.bIsInCamera = false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Log.i("scj", "back car onPause 1");
        if (this.bActivityOntop && MainUI.IsCameraMode() == 0) {
            Log.i("scj", "onPause  DestroyCamera");
            DestroyCamera();
            CheckFcamMode();
        }
        if (MainUI.IsRightCamMode() == 1 && MainUI.IsCameraMode() == 1 && MainUI.mCanInterface != null) {
            MainUI.mCanInterface.EnterCamera(0);
        }
        this.bActivityOntop = false;
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        Log.i("scj", "back car onStop ");
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Log.i("scj", "back car onDestroy ");
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.i("scj", "back car onResume ");
        MainSet.GetInstance().SetVideoChannel(0);
        if (MainUI.mCanInterface != null) {
            MainUI.mCanInterface.EnterCamera(1);
        }
        TsDisplay.GetInstance().SetDispParat(0);
        this.bActivityOntop = true;
        super.onResume();
    }
}
