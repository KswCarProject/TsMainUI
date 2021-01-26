package com.ts.backcar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;

public class RightCameraActivity extends Activity {
    /* access modifiers changed from: private */
    public Runnable CheckBackCarState = new Runnable() {
        public void run() {
            if (MainUI.IsRightCamMode() == 0) {
                RightCameraActivity.this.finish();
            } else {
                RightCameraActivity.this.mHandler.postDelayed(RightCameraActivity.this.CheckBackCarState, 30);
            }
        }
    };
    private ImageView Imgforbiden;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    ViewGroup mlayout;
    private ProgressBar pProgressLoading;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_avin_main);
        this.mlayout = (RelativeLayout) findViewById(R.id.activity_avin_mainid);
        this.pProgressLoading = (ProgressBar) this.mlayout.findViewById(R.id.loading_progress);
        this.pProgressLoading.setVisibility(4);
        this.Imgforbiden = (ImageView) this.mlayout.findViewById(R.id.image_forbiden);
        this.Imgforbiden.setVisibility(4);
        this.mHandler.postDelayed(this.CheckBackCarState, 300);
        super.onCreate(savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        BackcarService.getInstance().StopCamera();
        TsDisplay.GetInstance().SetDispParat(-1);
        if (isFinishing()) {
            MainUI.bIsInRight = false;
        }
        if (BackcarService.getInstance().bIsAvm360()) {
            MainSet.GetInstance().SetVideoChannel(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainSet.GetInstance().SetVideoChannel(2);
        BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.textureView), false);
        TsDisplay.GetInstance().SetDispParat(0);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }
}
