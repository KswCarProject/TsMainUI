package com.autochips.camera;

import android.content.Context;
import android.graphics.Rect;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.WindowManager;
import com.autochips.camera.CameraStateMachine;
import com.autochips.camera.ICameraService;
import com.autochips.camera.util.DVRConst;
import com.autochips.camera.util.LogUtil;
import com.autochips.metazone.AtcMetazone;
import com.ts.backcar.BackcarService;
import java.util.HashMap;
import java.util.Map;

public class CameraServiceImpl extends ICameraService.Stub implements CameraStateMachine.StateMachineCallback {
    private static final int H_ADDR = 65592;
    private static final String ROTATION_OF_SCREEN = "persist.sf.hwrotation";
    private static final String TAG = "CameraServiceImpl";
    private static final int W_ADDR = 65591;
    private static final int X_ADDR = 65589;
    private static final int Y_ADDR = 65590;
    private Rect mBackcarViewRect;
    private RemoteCallbackList<ICameraServiceCallback> mCallbacks = new RemoteCallbackList<>();
    private Map<String, CameraStateMachine> mCameraStateMachineMap;
    private Context mContext;
    private Handler mHandler = new Handler();
    private int mRotation;
    private boolean mSignalOn;
    private BackcarSurfaceCallback mSurfaceCallback;
    private int mWindowHeight;
    private int mWindowWidth;

    public CameraServiceImpl(Context context) {
        this.mContext = context;
        this.mCameraStateMachineMap = new HashMap();
        getWindowInfo(context);
        updateDisplayInfoFromMetazone();
        initCameraStateMachine();
    }

    private void initCameraStateMachine() {
        getCameraStateMachine("2");
        getCameraStateMachine("0");
        getCameraStateMachine("3");
        getCameraStateMachine("11");
        getCameraStateMachine("9");
    }

    private CameraStateMachine getCameraStateMachine(String id) {
        CameraStateMachine csm = this.mCameraStateMachineMap.get(id);
        if (csm != null) {
            return csm;
        }
        CameraStateMachine csm2 = new CameraStateMachine(this.mContext, id);
        this.mCameraStateMachineMap.put(id, csm2);
        csm2.registerCallback(this);
        csm2.start();
        return csm2;
    }

    private void getWindowInfo(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService("window");
        wm.getDefaultDisplay().getMetrics(dm);
        this.mWindowWidth = dm.widthPixels;
        this.mWindowHeight = dm.heightPixels;
        this.mRotation = wm.getDefaultDisplay().getRotation();
        Log.d(TAG, "mWindowWidth=" + this.mWindowWidth + ", mWindowHeight=" + this.mWindowHeight + ", mRotation=" + this.mRotation);
    }

    private void updateDisplayInfoFromMetazone() {
        int[] tmp = new int[4];
        AtcMetazone.readval(X_ADDR, tmp);
        int x = tmp[0];
        AtcMetazone.readval(Y_ADDR, tmp);
        int y = tmp[0];
        AtcMetazone.readval(W_ADDR, tmp);
        int w = tmp[0];
        AtcMetazone.readval(H_ADDR, tmp);
        int h = tmp[0];
        Log.d(TAG, "x=" + x + ", y=" + y + ", w=" + w + ", h=" + h);
        this.mBackcarViewRect = new Rect(x, y, x + w, y + h);
    }

    public void sendMediaMountedAndEjectedMessage(boolean isMounted) {
        for (Map.Entry<String, CameraStateMachine> entry : this.mCameraStateMachineMap.entrySet()) {
            entry.getValue().sendMessage(isMounted ? -17 : 17);
        }
    }

    public void sendQBMessage(boolean isQbOn) {
        Log.d(TAG, "sendQBMessage, isQbOn=" + isQbOn);
        for (Map.Entry<String, CameraStateMachine> entry : this.mCameraStateMachineMap.entrySet()) {
            entry.getValue().sendMessage(isQbOn ? 16 : -16);
        }
    }

    public void startPreview(String cameraId, Surface surface) {
        Log.d(TAG, "startPreview - cameraId:" + cameraId);
        getCameraStateMachine(cameraId).startPreview(cameraId, surface);
    }

    public void stopPreview(String cameraId) {
        Log.d(TAG, "stopPreview - cameraId:" + cameraId);
        getCameraStateMachine(cameraId).stopPreview(cameraId);
    }

    public boolean isPreviewStarted(String cameraId) {
        Log.d(TAG, "isPreviewStarted - cameraId:" + cameraId);
        return getCameraStateMachine(cameraId).getPreviewState(cameraId);
    }

    public void startRecord(String cameraId, boolean isUrgent) {
        Log.d(TAG, "startRecord - cameraId:" + cameraId);
        getCameraStateMachine(cameraId).startRecord(cameraId, isUrgent);
    }

    public void stopRecord(String cameraId) {
        Log.d(TAG, "stopRecord - cameraId:" + cameraId);
        getCameraStateMachine(cameraId).stopRecord(cameraId);
    }

    public boolean isNormalRecordStarted(String cameraId) {
        Log.d(TAG, "isNormalRecordStarted - cameraId:" + cameraId);
        return getCameraStateMachine(cameraId).getRecordingState(cameraId, false);
    }

    public boolean isUrgentRecordStarted(String cameraId) {
        Log.d(TAG, "isUrgentRecordStarted - cameraId:" + cameraId);
        return getCameraStateMachine(cameraId).getRecordingState(cameraId, true);
    }

    public void snapShot(String cameraId) {
        Log.d(TAG, "snapShot - cameraId:" + cameraId);
        getCameraStateMachine(cameraId).snapshot(cameraId);
    }

    public void setMicState(String cameraId, boolean micOn) {
        Log.d(TAG, "setMicState - cameraId:" + cameraId + " micOn:" + micOn);
        getCameraStateMachine(cameraId).setMic(cameraId, micOn);
    }

    public boolean getMicState(String cameraId) {
        Log.d(TAG, "getMicState - cameraId:" + cameraId);
        return getCameraStateMachine(cameraId).getMicState(cameraId);
    }

    public void setResolution(String cameraId, int width, int height) {
        Log.d(TAG, "setResolution - cameraId:" + cameraId + " width:" + width + " height:" + height);
        getCameraStateMachine(cameraId).setResolution(cameraId, width, height);
    }

    public Resolution getResolution(String cameraId) {
        Log.d(TAG, "getResolution - cameraId:" + cameraId);
        Size size = getCameraStateMachine(cameraId).getResolution(cameraId);
        return new Resolution(size.getWidth(), size.getHeight());
    }

    public void setDuration(String cameraId, int duration) {
        Log.d(TAG, "setDuration - cameraId:" + cameraId + " duration:" + duration + "(s)");
        getCameraStateMachine(cameraId).setDuration(cameraId, (long) (duration * 1000));
    }

    public int getDuration(String cameraId) {
        Log.d(TAG, "getDuration - cameraId:" + cameraId);
        return (int) (getCameraStateMachine(cameraId).getDuration(cameraId) / 1000);
    }

    public void setWatermarkState(String cameraId, boolean watermarkOn) {
        Log.d(TAG, "setWatermarkState - cameraId:" + cameraId + " watermarkOn:" + watermarkOn);
        getCameraStateMachine(cameraId).setWaterMark(cameraId, watermarkOn);
    }

    public boolean getWatermarkState(String cameraId) {
        Log.d(TAG, "getWatermarkState - cameraId:" + cameraId);
        return getCameraStateMachine(cameraId).getWatermarkState(cameraId);
    }

    public void registerCameraServiceCallback(String cameraId, ICameraServiceCallback cb) {
        Log.d(TAG, "registerCameraServiceCallback - cameraId:" + cameraId);
        if (cb != null) {
            this.mCallbacks.register(cb);
            if ((cameraId.equals("2") || cameraId.equals("11") || cameraId.equals("3")) && !this.mSignalOn) {
                updateStatus(cameraId, 101);
            }
        }
    }

    public void unregisterCameraServiceCallback(String cameraId, ICameraServiceCallback cb) {
        LogUtil.d(TAG, "unregisterCameraServiceCallback - cameraId:" + cameraId, new Object[0]);
        if (cb != null) {
            this.mCallbacks.unregister(cb);
        }
    }

    public void startBackcarTheFirstTime(int backcarType) {
        String backcarCameraId = getCameraId(backcarType);
        getCameraStateMachine(backcarCameraId).openCamera(backcarCameraId);
    }

    public void startBackcar(int backcarType, TextureView BackCarView) {
        this.mSignalOn = true;
        String backcarCameraId = getCameraId(backcarType);
        updateStatus(backcarCameraId, 100);
        LogUtil.d(TAG, "startBackcar, backType = " + backcarType, new Object[0]);
        BackCarView.setSurfaceTextureListener((TextureView.SurfaceTextureListener) null);
        if (BackCarView.isAvailable()) {
            startPreview(backcarCameraId, new Surface(BackCarView.getSurfaceTexture()));
            return;
        }
        this.mSurfaceCallback = new BackcarSurfaceCallback(backcarCameraId, this, getCameraStateMachine(backcarCameraId).getResolution(backcarCameraId));
        BackCarView.setSurfaceTextureListener(this.mSurfaceCallback);
    }

    public void stopBackcar(int backcarType, TextureView BackCarView) {
        this.mSignalOn = false;
        String backcarCameraId = getCameraId(backcarType);
        LogUtil.d(TAG, "stopBackcar, backcarType = " + backcarType, new Object[0]);
        this.mHandler.removeCallbacksAndMessages((Object) null);
        updateStatus(backcarCameraId, 101);
        if (BackCarView.isAvailable()) {
            stopPreview(backcarCameraId);
        }
        BackCarView.setSurfaceTextureListener((TextureView.SurfaceTextureListener) null);
    }

    private String getCameraId(int backcarType) {
        if (backcarType == 2) {
            return "2";
        }
        if (backcarType == 1) {
            return "3";
        }
        if (backcarType == 3) {
            return "11";
        }
        return DVRConst.UNKOWN_CAMERA_ID;
    }

    public void updateStatus(String cameraId, int message) {
        if (this.mCallbacks != null) {
            this.mCallbacks.beginBroadcast();
            int N = this.mCallbacks.getRegisteredCallbackCount();
            for (int i = 0; i < N; i++) {
                try {
                    ICameraServiceCallback callback = this.mCallbacks.getBroadcastItem(i);
                    if (callback != null && callback.getCameraId().equals(cameraId)) {
                        callback.onStatusChanged(cameraId, message);
                    }
                } catch (DeadObjectException e) {
                    if (0 != 0) {
                        this.mCallbacks.unregister((IInterface) null);
                    }
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
            this.mCallbacks.finishBroadcast();
            return;
        }
        Log.w(TAG, "updateStatus - Callbacks map no such camera id");
    }

    private void updateError(String cameraId, int message, int error) {
        if (this.mCallbacks != null) {
            this.mCallbacks.beginBroadcast();
            int N = this.mCallbacks.getRegisteredCallbackCount();
            for (int i = 0; i < N; i++) {
                try {
                    ICameraServiceCallback callback = this.mCallbacks.getBroadcastItem(i);
                    if (callback != null && callback.getCameraId().equals(cameraId)) {
                        callback.onErrorOccurred(cameraId, message, error);
                    }
                } catch (DeadObjectException e) {
                    if (0 != 0) {
                        this.mCallbacks.unregister((IInterface) null);
                    }
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
            this.mCallbacks.finishBroadcast();
            return;
        }
        Log.w(TAG, "updateError - Callbacks map no such camera id");
    }

    public void release() {
    }

    private void showBackcarInfo(int info) {
        switch (info) {
            case 13:
            case 14:
            case 18:
                BackcarService.SetSignal(true);
                return;
            case 15:
                BackcarService.SetSignal(false);
                return;
            default:
                return;
        }
    }

    private boolean filterRecordTooSlowMsg(String id, int operation, int error) {
        if (operation == 14 && error == 17) {
            return true;
        }
        return false;
    }

    public void onOperationSuccess(String id, int operation) {
        LogUtil.i(TAG, "onOperationSuccess -id:" + id + " operation:" + operation, new Object[0]);
        updateStatus(id, operation);
    }

    public void onOperationFail(String id, int operation, int error) {
        LogUtil.i(TAG, "onOperationFail -id:" + id + " operation:" + operation + " error:" + error, new Object[0]);
        if (!filterRecordTooSlowMsg(id, operation, error)) {
            if (("2".equals(id) || "11".equals(id) || "3".equals(id)) && operation == 14) {
                showBackcarInfo(error);
            }
            updateError(id, operation, error);
        }
    }

    public void linkToDeath(IBinder.DeathRecipient recipient, int flags) {
        super.linkToDeath(recipient, flags);
    }
}
