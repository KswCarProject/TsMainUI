package com.ts.backcar;

import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.SystemProperties;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import com.autochips.android.backcar.Backcar_GPIO;
import com.mediatek.perfservice.IPerfServiceWrapper;
import com.mediatek.perfservice.PerfServiceWrapper;
import com.mediatek.serviceMonitor.CameraServiceDetector;
import com.ts.main.common.MainSet;
import java.lang.reflect.Method;

public class BackcarService {
    public static final String ACTION_BACKCAR_FINISH = "android.backcar.action.FINISH";
    public static final String ACTION_BACKCAR_PREPARE_START = "android.backcar.action.PREPARE_START";
    public static final String ACTION_BACKCAR_STARTED = "android.backcar.action.STARTED";
    public static final String BACK_CAR_INIT = "forfan.ps.backinint";
    public static final int BC_AHD_1080P = 2;
    public static final int BC_AHD_720P = 1;
    public static final int BC_AVM_1080P = 4;
    public static final int BC_AVM_720P = 3;
    public static final int BC_CVBS = 0;
    public static final int BC_MIPI_USER = 5;
    public static final int BC_SOURCE_AHD = 1;
    public static final int BC_SOURCE_AVM = 2;
    public static final int BC_SOURCE_CVBS = 0;
    private static final String TAG = "BackcarService";
    private static BackcarService gInst = null;
    private static volatile Method mGetMethod = null;
    private static volatile Method mSetMethod = null;
    public static int nSourceType = 255;
    public static int nStep = 0;
    private boolean bHasQuickBc = true;
    public boolean bIninOK = false;
    private Activity mActivity = null;
    /* access modifiers changed from: private */
    public CameraManager mCM = null;
    private CameraPreview mCameraPreview = null;
    CameraServiceDetector mCameraServiceDetector = new CameraServiceDetector();
    public Context mContext = null;
    private int mPerfHandle = -1;
    private IPerfServiceWrapper mPerfService = null;
    private AutoFitTextureView mTextureView = null;
    public ImageView mView;
    private WindowManager mWM = null;
    private String preWatermarkStatus;

    public void setActivity(Activity activity) {
        Log.i(TAG, "setActivity mActivity= " + this.mActivity + "activity=" + activity);
        if (!(this.mActivity == null || this.mActivity == activity)) {
            Log.i(TAG, "setActivity mActivity= " + this.mActivity + "activity=" + activity);
            this.mActivity.finish();
        }
        this.mActivity = activity;
    }

    public void StartCamera(AutoFitTextureView tex, boolean bEnableDI) {
        if (!this.bIninOK) {
            Log.e(TAG, "StartCamera arm2 is run =" + tex);
        } else if (tex == null) {
            Log.e(TAG, "StartCamera tex=" + tex);
        } else {
            if (bEnableDI) {
                SystemProperties.set("runtime.backcar.di.en", MainSet.SP_XPH5);
                SystemProperties.set("runtime.backcar.sf.en", "0");
            } else {
                SystemProperties.set("runtime.backcar.di.en", "0");
                SystemProperties.set("runtime.backcar.sf.en", "0");
            }
            this.mTextureView = tex;
            Log.i(TAG, "StartCamera 1 tex=" + tex);
            if (this.mCameraPreview == null) {
                this.mCameraPreview = new CameraPreview();
            } else {
                Log.i(TAG, "BackCarRunnable BACKCAR_START mCamera2Preview is not null L_FAILED");
            }
            this.mTextureView.setCameraPreview(this.mCameraPreview);
            this.mCameraPreview.initCamera();
            this.mTextureView.setVisibility(0);
            this.preWatermarkStatus = getString("runtime.watermark.config", "0");
            setString("runtime.watermark.config", "0");
            if (MainSet.getNumCores() == 4 && this.mPerfService != null && this.mPerfHandle == -1) {
                this.mPerfHandle = this.mPerfService.userReg(MainSet.getNumCores(), 1300000);
                if (this.mPerfHandle != -1) {
                    Log.i(TAG, "enable perfservice");
                    this.mPerfService.userEnable(this.mPerfHandle);
                }
            }
        }
    }

    public void StopActivity() {
        if (this.mActivity != null) {
            Log.i(TAG, "mActivity finish" + this.mActivity);
            this.mActivity.finish();
            this.mActivity = null;
        }
    }

    public void StopCamera() {
        if (this.mTextureView != null) {
            this.mTextureView.setVisibility(4);
        }
        if (this.mCameraPreview != null) {
            Log.i(TAG, "StopCamera");
            setString("runtime.watermark.config", this.preWatermarkStatus);
            this.mCameraPreview.deInitCamera();
        } else {
            Log.i(TAG, "mCamera2Preview== null");
        }
        if (MainSet.getNumCores() == 4 && this.mPerfService != null && this.mPerfHandle != -1) {
            Log.i(TAG, "disable perfservice");
            this.mPerfService.userDisable(this.mPerfHandle);
            this.mPerfService.userUnreg(this.mPerfHandle);
            this.mPerfHandle = -1;
        }
    }

    private static String getString(String key, String def) {
        try {
            if (mGetMethod == null) {
                mGetMethod = Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class});
            }
            return (String) mGetMethod.invoke((Object) null, new Object[]{key, def});
        } catch (Exception e) {
            Log.e(TAG, "Platform error: " + e.toString());
            return def;
        }
    }

    private static String setString(String key, String val) {
        try {
            if (mSetMethod == null) {
                mSetMethod = Class.forName("android.os.SystemProperties").getMethod("set", new Class[]{String.class, String.class});
            }
            return (String) mSetMethod.invoke((Object) null, new Object[]{key, val});
        } catch (Exception e) {
            Log.e(TAG, "Platform error: " + e.toString());
            return val;
        }
    }

    public class Arm2CommunicationRunnable implements Runnable {
        public Arm2CommunicationRunnable() {
        }

        public void run() {
            Log.i(BackcarService.TAG, "inform arm2 android system is ready");
            int nNUm = 0;
            String bCompelte = SystemProperties.get("sys.boot_completed");
            BackcarService.nStep = 1;
            while (!bCompelte.equals(MainSet.SP_XPH5)) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bCompelte = SystemProperties.get("sys.boot_completed");
                nNUm++;
                Log.i(BackcarService.TAG, "AutoBootUp.bBootComplete " + bCompelte + " " + nNUm);
            }
            BackcarService.nStep = 2;
            Backcar_GPIO.takeoverfromArm2();
            SystemProperties.set("runtime.backcar.isArm2Offline", MainSet.SP_XPH5);
            BackcarService.nStep = 3;
            Log.i(BackcarService.TAG, "arm2 backcar is exit, so arm1 start backcarservice and take over the backcar");
            BackcarService.nStep = 4;
            int nNUm2 = 0;
            int cameraNums = BackcarService.this.mCameraServiceDetector.atc_cameraServiceDetect();
            Log.i(BackcarService.TAG, "cameraNums====" + cameraNums);
            while (cameraNums <= 0 && nNUm2 <= 1000) {
                try {
                    Thread.sleep(10);
                    nNUm2++;
                    Log.i(BackcarService.TAG, "atc_cameraServiceDetect" + nNUm2);
                } catch (Exception e2) {
                    Log.e(BackcarService.TAG, "sleep Exception");
                }
                cameraNums = BackcarService.this.mCameraServiceDetector.atc_cameraServiceDetect();
            }
            Log.d(BackcarService.TAG, "found cameras: " + cameraNums);
            BackcarService.this.bIninOK = true;
            BackcarService.nStep = 5;
            Log.d(BackcarService.TAG, "SystemProperties.get(BACK_CAR_INIT) " + SystemProperties.get(BackcarService.BACK_CAR_INIT));
            if (cameraNums == 2) {
                try {
                    Log.i(BackcarService.TAG, "get parameters start");
                    CameraCharacteristics cameraCharacteristics = BackcarService.this.mCM.getCameraCharacteristics(MainSet.SP_XPH5);
                    CameraCharacteristics characteristics = BackcarService.this.mCM.getCameraCharacteristics("0");
                    Log.i(BackcarService.TAG, "get parameters end");
                } catch (CameraAccessException e3) {
                    Log.e(BackcarService.TAG, "get info  Exception");
                    e3.printStackTrace();
                }
            } else if (cameraNums == 1) {
                BackcarService.this.mCM.getCameraCharacteristics("0");
            }
        }
    }

    public void InintCamera(Context MyContext) {
        if (this.mWM == null) {
            this.mWM = (WindowManager) MyContext.getSystemService("window");
            this.mCM = (CameraManager) MyContext.getSystemService("camera");
            Log.i(TAG, "InintCamera mWM==" + this.mWM);
            Log.i(TAG, "InintCamera mCM==" + this.mCM);
            this.mContext = MyContext;
            this.mPerfService = new PerfServiceWrapper((Context) null);
            if (!this.bHasQuickBc) {
                this.bIninOK = true;
            } else if (!SystemProperties.get(BACK_CAR_INIT).equals("11")) {
                SystemProperties.set(BACK_CAR_INIT, "11");
                new Thread(new Arm2CommunicationRunnable()).start();
            } else {
                this.bIninOK = true;
            }
        }
    }

    public static BackcarService getInstance() {
        if (gInst == null) {
            gInst = new BackcarService();
        }
        return gInst;
    }

    public void SetSource(int src) {
    }

    public void SetCamType(int nType, int x, int y) {
    }
}
