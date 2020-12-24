package com.ts.MainUI;

import android.app.Activity;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import com.txznet.sdk.bean.Poi;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.StSet;

public class TsMode extends Activity {
    public static final String ACTION_QB_POWEROFF = "autochips.intent.action.QB_POWEROFF";
    public static final String ACTION_QB_POWERON = "autochips.intent.action.QB_POWERON";
    static final String TAG = "TsModeActivity";
    public static final int UI_ATV_APORT = 3;
    public static final int UI_ATV_VPORT = 3;
    public static final int UI_AVIN_APORT = 1;
    public static final int UI_AVIN_VPORT = 1;
    public static final int UI_BCAM_APORT = 6;
    public static final int UI_BCAM_VPORT = 6;
    public static final int UI_CMMB_APORT = 2;
    public static final int UI_CMMB_VPORT = 2;
    public static final int UI_DVR_APORT = 4;
    public static final int UI_DVR_VPORT = 4;
    public static final int UI_FCAM_APORT = 5;
    public static final int UI_FCAM_VPORT = 5;
    public static final int V_BACK_MODE = 2;
    public static final int V_FRONT_BACK_MODE = 3;
    public static final int V_FRONT_MODE = 1;
    public int FRmode = 0;
    public int aPort = 0;
    Display[] displays;
    private DisplayManager mDisplayManager;
    public int nHeight = 0;
    public int nIsRearShow = 0;
    public int nWidth = 0;
    int nWorkMode = 0;
    public int vPort = 0;

    public void ShowSurfaceView() {
    }

    public void HideSurfaceView() {
    }

    public Boolean IsHaveSignal() {
        return true;
    }

    public Boolean IsvideoForbiden() {
        if (StSet.GetDriveVideo() == 1 && Mcu.GetBrake() == 0) {
            return true;
        }
        return false;
    }

    public int EnterAv() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart!!!");
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart!!!");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume!!!");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d(TAG, "onPause!!!");
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop!!!");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Log.d(TAG, "onDestroy ");
        Log.d(TAG, "onDestroy end");
        try {
            if (this.displays.length >= 2) {
                Log.d(TAG, "Hide Presentation");
            } else {
                Log.e(TAG, "onDestroy Do not have rear display!!");
            }
        } catch (Exception e) {
            Log.e(TAG, "hide presentation exception = " + e);
        }
        ((AudioManager) getSystemService(Poi.PoiAction.ACTION_AUDIO)).abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
        super.onDestroy();
    }
}
