package com.ts.backcar;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import com.autochips.camera.ICameraServiceCallback;
import com.autochips.camera.MIPIDVRActivity;
import com.autochips.camera.Resolution;
import com.autochips.camera.util.LogUtil;
import com.autochips.camera.util.ToastUtil;
import com.ts.MainUI.R;

public class TsDvrService {
    private static final int CAMERA_STATUS_MSG = 1;
    private static final int ERROR_STATUS_MSG = 2;
    private static final String KEY_BACK_RECORD_STARTED = "key_back_record_started";
    private static final String KEY_FRONT_RECORD_STARTED = "key_front_record_started";
    private static final int NOTIFY_ID = 1;
    private static final int RECORD_NORMAL_STARTED = 1;
    private static final int RECORD_NOT_STARTED = 0;
    private static final int RECORD_URGENT_STARTED = 2;
    private static final String TAG = "TsDvrService";
    private static TsDvrService gInst = null;
    private CameraServiceCallback mBackCallback;
    /* access modifiers changed from: private */
    public String mBackMIPICameraId = "11";
    /* access modifiers changed from: private */
    public Resolution mBackResolution;
    /* access modifiers changed from: private */
    public AutoFitTextureView mBackTextureView;
    private Notification.Builder mBuilder;
    /* access modifiers changed from: private */
    public Context mContext;
    private CameraServiceCallback mFrontCallback;
    /* access modifiers changed from: private */
    public String mFrontMIPICameraId = "9";
    /* access modifiers changed from: private */
    public Resolution mFrontResolution;
    /* access modifiers changed from: private */
    public AutoFitTextureView mFrontTextureView;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TsDvrService.TAG, "Handler - handleMessage:" + msg.what + " " + msg.arg1 + "   " + msg.arg2);
            String id = (String) msg.obj;
            if (msg.what == 1) {
                switch (msg.arg1) {
                    case 2:
                        Log.d(TsDvrService.TAG, "OPERATION_CLOSE_CAMERA: id = " + id);
                        TsDvrService.this.setTextureViewVisible(id, false);
                        return;
                    case 3:
                        Log.d(TsDvrService.TAG, "OPERATION_START_PREVIEW: id = " + id);
                        TsDvrService.this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                TsDvrService.this.setTextureViewVisible(TsDvrService.this.mFrontMIPICameraId, true);
                                TsDvrService.this.setTextureViewVisible(TsDvrService.this.mBackMIPICameraId, true);
                            }
                        }, 200);
                        return;
                    case 4:
                        Log.d(TsDvrService.TAG, "OPERATION_START_RECORD_NORMAL: id = " + id);
                        TsDvrService.this.showNotification();
                        TsDvrService.this.saveRecordStates(id, 1);
                        return;
                    case 5:
                        Log.d(TsDvrService.TAG, "OPERATION_START_RECORD_URGENT: id = " + id);
                        TsDvrService.this.showNotification();
                        TsDvrService.this.saveRecordStates(id, 2);
                        return;
                    case 6:
                        Log.d(TsDvrService.TAG, "OPERATION_STOP_PREVIEW: id = " + id);
                        TsDvrService.this.setTextureViewVisible(id, false);
                        return;
                    case 7:
                        Log.d(TsDvrService.TAG, "OPERATION_STOP_RECORD_NORMAL: id = " + id);
                        TsDvrService.this.dismissNotification();
                        TsDvrService.this.saveRecordStates(id, 0);
                        return;
                    case 8:
                        Log.d(TsDvrService.TAG, "OPERATION_STOP_RECORD_URGENT: id = " + id);
                        TsDvrService.this.dismissNotification();
                        TsDvrService.this.saveRecordStates(id, 0);
                        return;
                    default:
                        return;
                }
            } else if (msg.what == 2) {
                switch (msg.arg1) {
                    case 1:
                        Log.d(TsDvrService.TAG, "ERROR -> OPERATION_OPEN_CAMERA: id = " + id);
                        TsDvrService.this.setTextureViewVisible(id, false);
                        break;
                    case 3:
                        Log.d(TsDvrService.TAG, "ERROR -> OPERATION_START_PREVIEW: id = " + id);
                        TsDvrService.this.setTextureViewVisible(id, false);
                        break;
                    case 6:
                        Log.d(TsDvrService.TAG, "ERROR -> OPERATION_STOP_PREVIEW: id = " + id);
                        TsDvrService.this.setTextureViewVisible(id, false);
                        break;
                }
                switch (msg.arg2) {
                    case 2:
                        ToastUtil.showToast(TsDvrService.this.mContext, TsDvrService.this.mContext.getResources().getString(R.string.TIP_NO_MEMCARD));
                        return;
                    case 3:
                        ToastUtil.showToast(TsDvrService.this.mContext, TsDvrService.this.mContext.getResources().getString(R.string.TIP_NO_MEMCARD));
                        return;
                    case 6:
                        if (id.equals(TsDvrService.this.mFrontMIPICameraId)) {
                            ToastUtil.showToast(TsDvrService.this.mContext, TsDvrService.this.mContext.getResources().getString(R.string.TIP_NO_CAMERA));
                            return;
                        } else {
                            ToastUtil.showToast(TsDvrService.this.mContext, TsDvrService.this.mContext.getResources().getString(R.string.STR_NO_SIGNAL));
                            return;
                        }
                    case 7:
                        if (id.equals(TsDvrService.this.mFrontMIPICameraId)) {
                            ToastUtil.showToast(TsDvrService.this.mContext, TsDvrService.this.mContext.getResources().getString(R.string.TIP_NO_CAMERA));
                            return;
                        } else {
                            ToastUtil.showToast(TsDvrService.this.mContext, TsDvrService.this.mContext.getResources().getString(R.string.STR_NO_SIGNAL));
                            return;
                        }
                    case 15:
                        ToastUtil.showToast(TsDvrService.this.mContext, TsDvrService.this.mContext.getResources().getString(R.string.STR_NO_SIGNAL));
                        return;
                    default:
                        return;
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mIsBackTextureViewAvailable;
    /* access modifiers changed from: private */
    public boolean mIsFrontTextureViewAvailable;
    private NotificationManager mNotificationManager;
    private SharedPreferences mSharedPreferences;

    public static TsDvrService getInstance() {
        if (gInst == null) {
            gInst = new TsDvrService();
        }
        return gInst;
    }

    public void Init(Context context) {
        boolean z = true;
        InitFR();
        this.mContext = context;
        this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int frontRecordStarted = this.mSharedPreferences.getInt(KEY_FRONT_RECORD_STARTED, 0);
        int backRecordStarted = this.mSharedPreferences.getInt(KEY_BACK_RECORD_STARTED, 0);
        if (frontRecordStarted != 0) {
            StartRecord("9", frontRecordStarted == 2);
        }
        if (backRecordStarted != 0) {
            if (backRecordStarted != 2) {
                z = false;
            }
            StartRecord("11", z);
        }
    }

    /* access modifiers changed from: package-private */
    public void InitFR() {
        this.mFrontResolution = new Resolution(1280, 720);
        this.mBackResolution = new Resolution(1280, 720);
        this.mFrontCallback = new CameraServiceCallback(this.mFrontMIPICameraId);
        this.mBackCallback = new CameraServiceCallback(this.mBackMIPICameraId);
        BackcarService.getInstance().GetService().registerCameraServiceCallback(this.mFrontMIPICameraId, this.mFrontCallback);
        BackcarService.getInstance().GetService().registerCameraServiceCallback(this.mBackMIPICameraId, this.mBackCallback);
    }

    private boolean IsFront(String id) {
        return id.equals("9");
    }

    private boolean IsRear(String id) {
        return id.equals("11");
    }

    public void StartCam(String id, AutoFitTextureView tx) {
        if (IsFront(id)) {
            this.mFrontTextureView = tx;
            if (!this.mFrontTextureView.isAvailable()) {
                this.mFrontTextureView.setSurfaceTextureListener(new SurfaceTextureListener(this.mFrontMIPICameraId));
                return;
            }
            this.mIsFrontTextureViewAvailable = true;
            SurfaceTexture st = this.mFrontTextureView.getSurfaceTexture();
            if (st != null) {
                st.setDefaultBufferSize(this.mFrontResolution.getWidth(), this.mFrontResolution.getHeight());
                BackcarService.getInstance().GetService().startPreview(this.mFrontMIPICameraId, new Surface(st));
            }
        } else if (IsRear(id)) {
            this.mBackTextureView = tx;
            if (!this.mBackTextureView.isAvailable()) {
                this.mBackTextureView.setSurfaceTextureListener(new SurfaceTextureListener(this.mBackMIPICameraId));
                return;
            }
            this.mIsBackTextureViewAvailable = true;
            SurfaceTexture st2 = this.mBackTextureView.getSurfaceTexture();
            if (st2 != null) {
                st2.setDefaultBufferSize(this.mBackResolution.getWidth(), this.mBackResolution.getHeight());
                BackcarService.getInstance().GetService().startPreview(this.mBackMIPICameraId, new Surface(st2));
            }
        }
    }

    public void StopCam(String id) {
        setTextureViewVisible(id, false);
        BackcarService.getInstance().GetService().stopPreview(id);
    }

    public void StartRecord(String id, boolean bIsUrgent) {
        if (IsFront(id) || IsRear(id)) {
            Log.d(TAG, "StartRecord: id = " + id + " -> bIsUrgent = " + bIsUrgent);
            BackcarService.getInstance().GetService().startRecord(id, bIsUrgent);
            return;
        }
        Log.d(TAG, "StartRecord id error");
    }

    public void StopRecord(String id) {
        if (IsFront(id) || IsRear(id)) {
            Log.d(TAG, "StopRecord: id = " + id);
            BackcarService.getInstance().GetService().stopRecord(id);
            return;
        }
        Log.d(TAG, "StopRecord id error");
    }

    public boolean IsRecordStarted(String id) {
        return BackcarService.getInstance().GetService().isNormalRecordStarted(id) || BackcarService.getInstance().GetService().isUrgentRecordStarted(id);
    }

    public void SnapShot(String id) {
        if (IsFront(id) || IsRear(id)) {
            BackcarService.getInstance().GetService().snapShot(id);
        }
    }

    /* access modifiers changed from: private */
    public void showNotification() {
        if (this.mNotificationManager == null) {
            this.mNotificationManager = (NotificationManager) this.mContext.getSystemService("notification");
            if (Build.VERSION.SDK_INT == 23) {
                this.mBuilder = new Notification.Builder(this.mContext);
            } else {
                this.mBuilder = new Notification.Builder(this.mContext, this.mContext.getPackageName());
                this.mNotificationManager.createNotificationChannel(new NotificationChannel(this.mContext.getPackageName(), this.mContext.getPackageName(), 3));
            }
            this.mBuilder.setSmallIcon(R.drawable.ic_launcher).setContentIntent(PendingIntent.getActivity(this.mContext, 0, new Intent(this.mContext, MIPIDVRActivity.class), 0)).setOngoing(true);
        }
        boolean isFrontRecordStarted = IsRecordStarted(this.mFrontMIPICameraId);
        boolean isBackRecordStarted = IsRecordStarted(this.mBackMIPICameraId);
        if (isFrontRecordStarted && isBackRecordStarted) {
            this.mBuilder.setContentText("前后双摄像头正在录制...");
        } else if (isFrontRecordStarted) {
            this.mBuilder.setContentText("前摄像头正在录制...");
        } else if (isBackRecordStarted) {
            this.mBuilder.setContentText("后摄像头正在录制...");
        }
        this.mNotificationManager.notify(1, this.mBuilder.build());
    }

    /* access modifiers changed from: private */
    public void dismissNotification() {
        boolean isFrontRecordStarted = IsRecordStarted(this.mFrontMIPICameraId);
        boolean isBackRecordStarted = IsRecordStarted(this.mBackMIPICameraId);
        if (isFrontRecordStarted || isBackRecordStarted) {
            showNotification();
        } else if (this.mNotificationManager != null) {
            this.mNotificationManager.cancel(1);
        }
    }

    private class SurfaceTextureListener implements TextureView.SurfaceTextureListener {
        private String mCameraId;
        private boolean mIsFrontCamera;

        public SurfaceTextureListener(String id) {
            this.mCameraId = id;
            this.mIsFrontCamera = id.equals(TsDvrService.this.mFrontMIPICameraId);
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
            Resolution resolution;
            TextureView tv;
            Log.d(TsDvrService.TAG, "SurfaceTextureListener - onSurfaceTextureAvailable");
            if (this.mIsFrontCamera) {
                TsDvrService.this.mIsFrontTextureViewAvailable = true;
                resolution = TsDvrService.this.mFrontResolution;
                tv = TsDvrService.this.mFrontTextureView;
            } else {
                TsDvrService.this.mIsBackTextureViewAvailable = true;
                resolution = TsDvrService.this.mBackResolution;
                tv = TsDvrService.this.mBackTextureView;
            }
            SurfaceTexture st = tv.getSurfaceTexture();
            st.setDefaultBufferSize(resolution.getWidth(), resolution.getHeight());
            BackcarService.getInstance().GetService().startPreview(this.mCameraId, new Surface(st));
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
            Log.d(TsDvrService.TAG, "onSurfaceTextureSizeChanged, camera + " + this.mCameraId + ", width = " + width + ", height = " + height);
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            Log.d(TsDvrService.TAG, "onSurfaceTextureDestroyed, camera " + this.mCameraId);
            if (this.mIsFrontCamera) {
                TsDvrService.this.mIsFrontTextureViewAvailable = false;
            } else {
                TsDvrService.this.mIsBackTextureViewAvailable = false;
            }
            return false;
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    private class CameraServiceCallback extends ICameraServiceCallback.Stub {
        private String mCameraId;

        public CameraServiceCallback(String id) {
            this.mCameraId = id;
        }

        public String getCameraId() {
            return this.mCameraId;
        }

        public void onStatusChanged(String cameraId, int message) {
            LogUtil.i(TsDvrService.TAG, "ICameraServiceCallback.onStatusChanged - cameraId : " + cameraId + " message : " + message, new Object[0]);
            if (cameraId.equals(this.mCameraId)) {
                Message msg = TsDvrService.this.mHandler.obtainMessage();
                msg.what = 1;
                msg.arg1 = message;
                msg.obj = cameraId;
                TsDvrService.this.mHandler.sendMessage(msg);
                return;
            }
            LogUtil.w(TsDvrService.TAG, "ICameraServiceCallback.onStatusChanged - unkown camera id callback: " + cameraId, new Object[0]);
        }

        public void onErrorOccurred(String cameraId, int message, int error) throws RemoteException {
            LogUtil.i(TsDvrService.TAG, "ICameraServiceCallback.onErrorOccurred - cameraId : " + cameraId + " message : " + message + " error:" + error, new Object[0]);
            if (cameraId.equals(this.mCameraId)) {
                Message msg = TsDvrService.this.mHandler.obtainMessage();
                msg.what = 2;
                msg.arg1 = message;
                msg.arg2 = error;
                msg.obj = cameraId;
                TsDvrService.this.mHandler.sendMessage(msg);
                return;
            }
            LogUtil.w(TsDvrService.TAG, "ICameraServiceCallback.onErrorOccurred - unkown camera id callback: " + cameraId, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void setTextureViewVisible(String id, boolean visible) {
        int i = 0;
        if (id.equals(this.mFrontMIPICameraId)) {
            if (this.mFrontTextureView != null) {
                AutoFitTextureView autoFitTextureView = this.mFrontTextureView;
                if (!visible) {
                    i = 4;
                }
                autoFitTextureView.setVisibility(i);
            }
        } else if (this.mBackTextureView != null) {
            AutoFitTextureView autoFitTextureView2 = this.mBackTextureView;
            if (!visible) {
                i = 4;
            }
            autoFitTextureView2.setVisibility(i);
        }
    }

    /* access modifiers changed from: private */
    public void saveRecordStates(String id, int state) {
        if (IsFront(id)) {
            this.mSharedPreferences.edit().putInt(KEY_FRONT_RECORD_STARTED, state).apply();
        } else if (IsRear(id)) {
            this.mSharedPreferences.edit().putInt(KEY_BACK_RECORD_STARTED, state).apply();
        }
    }
}
