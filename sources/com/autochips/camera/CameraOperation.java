package com.autochips.camera;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.media.MediaCodec;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemProperties;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import com.autochips.camera.util.LogUtil;
import com.autochips.camera.util.StorageUtil;
import com.autochips.metazone.AtcMetazone;
import com.ts.backcar.BackcarService;
import com.ts.main.common.MainUI;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class CameraOperation {
    private static final int AHD_H_CUT_ADDR = 65604;
    private static final int AHD_MIR_ADDR = 65600;
    private static final int AHD_W_CUT_ADDR = 65603;
    private static final int AHD_X_CUT_ADDR = 65601;
    private static final int AHD_Y_CUT_ADDR = 65602;
    private static final String ATC_MIRROR_KEY_NAME = "com.atc.mirror";
    private static final String ATC_SHARPNESS_KEY_NAME = "com.atc.sharpness";
    private static final String ATC_SOURCEINFO_KEY_NAME = "com.atc.sourceinfo";
    private static final String ATC_WATERMARK_KEY_NAME = "com.atc.watermark.switch";
    private static final int AUDIO_CHANNEL_NUM = 2;
    private static final int AUDIO_ENCODE_BIT_RATE = 96000;
    private static final int AUDIO_SAMPLE_RATE = 44100;
    private static final int CVBS_H_CUT_ADDR = 65597;
    private static final int CVBS_MIR_ADDR = 65588;
    private static final int CVBS_W_CUT_ADDR = 65596;
    private static final int CVBS_X_CUT_ADDR = 65594;
    private static final int CVBS_Y_CUT_ADDR = 65595;
    private static final int DEFAULT_HEIGHT = 720;
    private static final long DEFAULT_NORMAL_RECORD_TIME = 300000;
    private static final long DEFAULT_URGENT_RECORD_TIME = 30000;
    private static final int DEFAULT_WIDTH = 1280;
    private static final int FRAMES_MAYBE_WRONG = 30;
    public static final String IMAGE_TPYE = "image";
    private static final int MEDIA_RECORDER_INFO_RECORDING_SIZE = 895;
    private static final int MEDIA_RECORDER_INFO_WRITE_SLOW = 899;
    private static final int MEDIA_RECORDER_INFO_WRITE_TOO_SLOW = 900;
    public static final String NORMAL_TYPE = "normal";
    private static final int SHARPNESS_ADDR = 65598;
    public static final String URGENT_TYPE = "urgent";
    private static final int VIDEO_ENCODE_BIT_RATE = 3145728;
    private static final int VIDEO_FRAME_RATE = 50;
    /* access modifiers changed from: private */
    public String TAG = "CameraOperation";
    private Rect mCamRect = new Rect();
    /* access modifiers changed from: private */
    public CameraDevice mCameraDevice;
    private final String mCameraId;
    private CameraManager mCameraManager;
    private CameraOperationCallback mCameraOperationCallback;
    private CameraCaptureSession.CaptureCallback mCaptureCallback = new CameraCaptureSession.CaptureCallback() {
        public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request, CaptureResult partialResult) {
        }

        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            int[] sourceInfo;
            if (CameraOperation.this.mSourceInfoKey != null && (sourceInfo = (int[]) result.get(CameraOperation.this.mSourceInfoKey)) != null && sourceInfo.length >= 2) {
                if (sourceInfo[0] == 0 && sourceInfo[1] == 0) {
                    if (CameraOperation.this.mFramesShouldSkipped == 0) {
                        CameraOperation.this.notifyOperationCallback(14, false, 15);
                        CameraOperation cameraOperation = CameraOperation.this;
                        cameraOperation.mFramesShouldSkipped = cameraOperation.mFramesShouldSkipped - 1;
                    } else if (CameraOperation.this.mFramesShouldSkipped >= 0) {
                        CameraOperation cameraOperation2 = CameraOperation.this;
                        cameraOperation2.mFramesShouldSkipped = cameraOperation2.mFramesShouldSkipped - 1;
                    }
                } else if (sourceInfo[0] == 720 && sourceInfo[1] == 480) {
                    LogUtil.d(CameraOperation.this.TAG, "width = " + sourceInfo[0] + ", height = " + sourceInfo[1], new Object[0]);
                    CameraOperation.this.notifyOperationCallback(14, false, 13);
                    CameraOperation.this.mFramesShouldSkipped = 30;
                } else if (sourceInfo[0] == 720 && sourceInfo[1] == 576) {
                    LogUtil.d(CameraOperation.this.TAG, "width = " + sourceInfo[0] + ", height = " + sourceInfo[1], new Object[0]);
                    CameraOperation.this.notifyOperationCallback(14, false, 14);
                    CameraOperation.this.mFramesShouldSkipped = 30;
                } else {
                    LogUtil.d(CameraOperation.this.TAG, "width = " + sourceInfo[0] + ", height = " + sourceInfo[1], new Object[0]);
                    CameraOperation.this.notifyOperationCallback(14, false, 18);
                    CameraOperation.this.mFramesShouldSkipped = 30;
                }
            }
        }
    };
    private CaptureRequest.Builder mCaptureRequestBuilder;
    /* access modifiers changed from: private */
    public CameraCaptureSession mCaptureSession;
    private Context mContext;
    private Runnable mDelayedUrgentRecordTask = new Runnable() {
        public void run() {
            LogUtil.d(CameraOperation.this.TAG, "mDelayedUrgentRecordTask, mIsRecording=" + CameraOperation.this.mIsRecording, new Object[0]);
            CameraOperation.this.stopRecycleTask();
            CameraOperation.this.mIsUrgentRecording = true;
            new MoveUrgentFileTask(CameraOperation.this.mStorageUtil).execute(new Long[]{Long.valueOf(CameraOperation.this.mUrgentRecordTriggeredTime)});
            CameraOperation.this.goOnRecording();
        }
    };
    /* access modifiers changed from: private */
    public int mFramesShouldSkipped = 30;
    private ImageReader.OnImageAvailableListener mImageListener = new ImageReader.OnImageAvailableListener() {
        /* JADX WARNING: Removed duplicated region for block: B:33:0x00d7 A[SYNTHETIC, Splitter:B:33:0x00d7] */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x00dc  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onImageAvailable(android.media.ImageReader r13) {
            /*
                r12 = this;
                r11 = 0
                com.autochips.camera.CameraOperation r7 = com.autochips.camera.CameraOperation.this
                java.lang.String r7 = r7.TAG
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                java.lang.String r9 = "onImageAvailable, width = "
                r8.<init>(r9)
                int r9 = r13.getWidth()
                java.lang.StringBuilder r8 = r8.append(r9)
                java.lang.String r9 = ", height = "
                java.lang.StringBuilder r8 = r8.append(r9)
                int r9 = r13.getHeight()
                java.lang.StringBuilder r8 = r8.append(r9)
                java.lang.String r8 = r8.toString()
                java.lang.Object[] r9 = new java.lang.Object[r11]
                com.autochips.camera.util.LogUtil.d(r7, r8, r9)
                r5 = 0
                r3 = 0
                android.media.Image r3 = r13.acquireNextImage()     // Catch:{ Exception -> 0x008f }
                com.autochips.camera.CameraOperation r7 = com.autochips.camera.CameraOperation.this     // Catch:{ Exception -> 0x008f }
                java.lang.String r8 = "image"
                java.io.File r4 = r7.getOutputFile(r8)     // Catch:{ Exception -> 0x008f }
                if (r4 != 0) goto L_0x005b
                if (r5 == 0) goto L_0x0045
                r5.close()     // Catch:{ Exception -> 0x004b }
            L_0x0045:
                if (r3 == 0) goto L_0x004a
                r3.close()
            L_0x004a:
                return
            L_0x004b:
                r2 = move-exception
                com.autochips.camera.CameraOperation r7 = com.autochips.camera.CameraOperation.this
                java.lang.String r7 = r7.TAG
                java.lang.String r8 = "image outputstream close fail"
                java.lang.Object[] r9 = new java.lang.Object[r11]
                com.autochips.camera.util.LogUtil.d(r7, r8, r9)
                goto L_0x0045
            L_0x005b:
                android.media.Image$Plane[] r7 = r3.getPlanes()     // Catch:{ Exception -> 0x008f }
                r8 = 0
                r7 = r7[r8]     // Catch:{ Exception -> 0x008f }
                java.nio.ByteBuffer r1 = r7.getBuffer()     // Catch:{ Exception -> 0x008f }
                int r7 = r1.remaining()     // Catch:{ Exception -> 0x008f }
                byte[] r0 = new byte[r7]     // Catch:{ Exception -> 0x008f }
                r1.get(r0)     // Catch:{ Exception -> 0x008f }
                java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x008f }
                r6.<init>(r4)     // Catch:{ Exception -> 0x008f }
                r6.write(r0)     // Catch:{ Exception -> 0x0103, all -> 0x0100 }
                r6.flush()     // Catch:{ Exception -> 0x0103, all -> 0x0100 }
                com.autochips.camera.CameraOperation r7 = com.autochips.camera.CameraOperation.this     // Catch:{ Exception -> 0x0103, all -> 0x0100 }
                r8 = 9
                r9 = 1
                r10 = 0
                r7.notifyOperationCallback(r8, r9, r10)     // Catch:{ Exception -> 0x0103, all -> 0x0100 }
                if (r6 == 0) goto L_0x0088
                r6.close()     // Catch:{ Exception -> 0x00f0 }
            L_0x0088:
                if (r3 == 0) goto L_0x008d
                r3.close()
            L_0x008d:
                r5 = r6
                goto L_0x004a
            L_0x008f:
                r2 = move-exception
            L_0x0090:
                com.autochips.camera.CameraOperation r7 = com.autochips.camera.CameraOperation.this     // Catch:{ all -> 0x00d4 }
                java.lang.String r7 = r7.TAG     // Catch:{ all -> 0x00d4 }
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d4 }
                java.lang.String r9 = "write image file fail: "
                r8.<init>(r9)     // Catch:{ all -> 0x00d4 }
                java.lang.String r9 = r2.toString()     // Catch:{ all -> 0x00d4 }
                java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ all -> 0x00d4 }
                java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x00d4 }
                r9 = 0
                java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ all -> 0x00d4 }
                com.autochips.camera.util.LogUtil.d(r7, r8, r9)     // Catch:{ all -> 0x00d4 }
                com.autochips.camera.CameraOperation r7 = com.autochips.camera.CameraOperation.this     // Catch:{ all -> 0x00d4 }
                r8 = 9
                r9 = 0
                r10 = -1
                r7.notifyOperationCallback(r8, r9, r10)     // Catch:{ all -> 0x00d4 }
                if (r5 == 0) goto L_0x00be
                r5.close()     // Catch:{ Exception -> 0x00c4 }
            L_0x00be:
                if (r3 == 0) goto L_0x004a
                r3.close()
                goto L_0x004a
            L_0x00c4:
                r2 = move-exception
                com.autochips.camera.CameraOperation r7 = com.autochips.camera.CameraOperation.this
                java.lang.String r7 = r7.TAG
                java.lang.String r8 = "image outputstream close fail"
                java.lang.Object[] r9 = new java.lang.Object[r11]
                com.autochips.camera.util.LogUtil.d(r7, r8, r9)
                goto L_0x00be
            L_0x00d4:
                r7 = move-exception
            L_0x00d5:
                if (r5 == 0) goto L_0x00da
                r5.close()     // Catch:{ Exception -> 0x00e0 }
            L_0x00da:
                if (r3 == 0) goto L_0x00df
                r3.close()
            L_0x00df:
                throw r7
            L_0x00e0:
                r2 = move-exception
                com.autochips.camera.CameraOperation r8 = com.autochips.camera.CameraOperation.this
                java.lang.String r8 = r8.TAG
                java.lang.String r9 = "image outputstream close fail"
                java.lang.Object[] r10 = new java.lang.Object[r11]
                com.autochips.camera.util.LogUtil.d(r8, r9, r10)
                goto L_0x00da
            L_0x00f0:
                r2 = move-exception
                com.autochips.camera.CameraOperation r7 = com.autochips.camera.CameraOperation.this
                java.lang.String r7 = r7.TAG
                java.lang.String r8 = "image outputstream close fail"
                java.lang.Object[] r9 = new java.lang.Object[r11]
                com.autochips.camera.util.LogUtil.d(r7, r8, r9)
                goto L_0x0088
            L_0x0100:
                r7 = move-exception
                r5 = r6
                goto L_0x00d5
            L_0x0103:
                r2 = move-exception
                r5 = r6
                goto L_0x0090
            */
            throw new UnsupportedOperationException("Method not decompiled: com.autochips.camera.CameraOperation.AnonymousClass6.onImageAvailable(android.media.ImageReader):void");
        }
    };
    private ImageReader mImageReader;
    private final boolean mIsBackcarCamera;
    /* access modifiers changed from: private */
    public boolean mIsChangingResolution;
    private boolean mIsMicOn = true;
    /* access modifiers changed from: private */
    public volatile boolean mIsPreviewing;
    /* access modifiers changed from: private */
    public volatile boolean mIsRecording;
    /* access modifiers changed from: private */
    public volatile boolean mIsUrgentRecording;
    private boolean mIsWatermarkOn = true;
    private CaptureRequest.Key<int[]> mMirrorKey;
    private int mMirrorVal;
    private Surface mNextPreviewSurface;
    private long mNormalRecordStartTime;
    private Runnable mNormalRecordTask = new Runnable() {
        public void run() {
            LogUtil.d(CameraOperation.this.TAG, "mNormalRecordTask, mIsRecording=" + CameraOperation.this.mIsRecording, new Object[0]);
            CameraOperation.this.stopRecycleTask();
            if (CameraOperation.this.mRecordRequested && CameraOperation.this.mIsRecording) {
                CameraOperation.this.goOnRecording();
            }
        }
    };
    private Surface mPendingPreviewSurface;
    private volatile boolean mPreviewRequested;
    private Surface mPreviewSurface;
    /* access modifiers changed from: private */
    public volatile boolean mRecordRequested;
    /* access modifiers changed from: private */
    public Handler mRecordThreadHandler = new Handler();
    /* access modifiers changed from: private */
    public MediaRecorder mRecorder;
    private MediaRecorder.OnErrorListener mRecorderErrorListener = new MediaRecorder.OnErrorListener() {
        public void onError(MediaRecorder mr, int what, int extra) {
            LogUtil.d(CameraOperation.this.TAG, "onError, what = " + what + ", extra = " + extra, new Object[0]);
        }
    };
    private MediaRecorder.OnInfoListener mRecorderInfoListener = new MediaRecorder.OnInfoListener() {
        public void onInfo(MediaRecorder mr, int what, int extra) {
            LogUtil.d(CameraOperation.this.TAG, "onInfo, what = " + what + ", extra = " + extra, new Object[0]);
            if (what == CameraOperation.MEDIA_RECORDER_INFO_WRITE_SLOW) {
                CameraOperation.this.notifyOperationCallback(14, false, 16);
            } else if (what == 900) {
                CameraOperation.this.notifyOperationCallback(14, false, 17);
            }
        }
    };
    private Surface mRecorderSurface;
    private File mRecordingFile;
    private CaptureRequest.Key<int[]> mSharpnessKey;
    private int mSharpnessVal;
    /* access modifiers changed from: private */
    public CaptureResult.Key<int[]> mSourceInfoKey;
    private CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {
        public void onClosed(CameraDevice camera) {
            super.onClosed(camera);
            CameraOperation.this.mCameraDevice = null;
            CameraOperation.this.mIsPreviewing = false;
            CameraOperation.this.mIsRecording = false;
            CameraOperation.this.notifyOperationCallback(2, true, 0);
        }

        public void onOpened(CameraDevice camera) {
            CameraOperation.this.mCameraDevice = camera;
            CameraOperation.this.notifyOperationCallback(1, true, 0);
        }

        public void onDisconnected(CameraDevice camera) {
            LogUtil.d(CameraOperation.this.TAG, "camera disconnected", new Object[0]);
            CameraOperation.this.close();
            CameraOperation.this.notifyOperationCallback(1, false, 6);
        }

        public void onError(CameraDevice camera, int error) {
            LogUtil.d(CameraOperation.this.TAG, "camera open error: " + error, new Object[0]);
            CameraOperation.this.close();
            CameraOperation.this.notifyOperationCallback(1, false, 7);
        }
    };
    private Timer mStorageCheckerThread;
    /* access modifiers changed from: private */
    public StorageUtil mStorageUtil;
    private Runnable mUrgentRecordTask = new Runnable() {
        public void run() {
            LogUtil.d(CameraOperation.this.TAG, "mUrgentRecordTask, mIsRecording=" + CameraOperation.this.mIsRecording, new Object[0]);
            CameraOperation.this.stopRecycleTask();
            if (CameraOperation.this.mRecordRequested && CameraOperation.this.mIsRecording) {
                CameraOperation.this.mIsUrgentRecording = false;
                CameraOperation.this.notifyOperationCallback(8, true, 0);
                CameraOperation.this.goOnRecording();
            }
        }
    };
    /* access modifiers changed from: private */
    public long mUrgentRecordTriggeredTime;
    private long mVideoDuration = DEFAULT_NORMAL_RECORD_TIME;
    private Size mVideoSize = new Size(1280, 720);
    private CaptureRequest.Key<int[]> mWatermarkKey;

    public interface CameraOperationCallback {
        void onOperationFailed(int i, String str, int i2);

        void onOperationSuccess(int i, String str);
    }

    public CameraOperation(Context context, String cameraId) {
        this.mContext = context;
        this.mStorageUtil = new StorageUtil(context, cameraId);
        this.mCameraManager = (CameraManager) context.getSystemService("camera");
        this.mCameraId = cameraId;
        this.mIsBackcarCamera = isBackcarCamera(cameraId);
        getAtcCaptureKeys();
        this.mRecorder = new MediaRecorder();
        setupNewImageReader(1280, 720);
        prepareRecorderSurface();
        this.TAG = String.valueOf(this.TAG) + cameraId;
    }

    public boolean isBackcarCamera(String cameraId) {
        if ("3".equals(cameraId) || "2".equals(cameraId) || "11".equals(cameraId) || "9".equals(cameraId)) {
            return true;
        }
        return false;
    }

    public String getCameraId() {
        return this.mCameraId;
    }

    /* access modifiers changed from: private */
    public void notifyOperationCallback(int operation, boolean success, int error) {
        if (this.mCameraOperationCallback == null) {
            return;
        }
        if (success) {
            this.mCameraOperationCallback.onOperationSuccess(operation, this.mCameraId);
        } else {
            this.mCameraOperationCallback.onOperationFailed(operation, this.mCameraId, error);
        }
    }

    public void open() {
        int count = 3;
        boolean success = false;
        int errorCode = 0;
        while (!success) {
            try {
                this.mCameraManager.openCamera(this.mCameraId, this.mStateCallback, (Handler) null);
                success = true;
            } catch (CameraAccessException e) {
                LogUtil.d(this.TAG, "openCamera: " + e.toString(), new Object[0]);
                errorCode = 7;
            } catch (IllegalArgumentException e2) {
                LogUtil.d(this.TAG, "openCamera: " + e2.toString(), new Object[0]);
                errorCode = 6;
            } catch (SecurityException e3) {
                LogUtil.d(this.TAG, "do not have permission to open camera", new Object[0]);
                errorCode = 8;
            }
            count--;
            if (success || count <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (Exception e4) {
            }
        }
        if (!success) {
            notifyOperationCallback(1, false, errorCode);
        }
    }

    public void close() {
        LogUtil.d(this.TAG, "close camera " + this.mCameraId, new Object[0]);
        if (this.mCaptureSession != null) {
            stopRecord();
            closePreviewSession();
            this.mCaptureSession = null;
        }
        if (this.mCameraDevice != null) {
            LogUtil.d(this.TAG, "close start", new Object[0]);
            this.mCameraDevice.close();
            LogUtil.d(this.TAG, "close end", new Object[0]);
            this.mCameraDevice = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0091 A[Catch:{ Exception -> 0x00d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0096 A[Catch:{ Exception -> 0x00d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a5 A[Catch:{ Exception -> 0x00d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c8 A[SYNTHETIC, Splitter:B:39:0x00c8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean createCaptureSession() {
        /*
            r10 = this;
            r6 = 0
            r4 = 0
            android.view.Surface r7 = r10.mPreviewSurface     // Catch:{ Exception -> 0x00d1 }
            if (r7 == 0) goto L_0x00f1
            android.view.Surface r7 = r10.mPreviewSurface     // Catch:{ Exception -> 0x00d1 }
            boolean r7 = r7.isValid()     // Catch:{ Exception -> 0x00d1 }
            if (r7 == 0) goto L_0x00f1
            android.hardware.camera2.params.OutputConfiguration r5 = new android.hardware.camera2.params.OutputConfiguration     // Catch:{ Exception -> 0x00d1 }
            android.view.Surface r7 = r10.mPreviewSurface     // Catch:{ Exception -> 0x00d1 }
            r5.<init>(r7)     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r7 = r10.mCameraId     // Catch:{ Exception -> 0x00ee }
            java.lang.String r8 = "3"
            boolean r7 = r7.equals(r8)     // Catch:{ Exception -> 0x00ee }
            if (r7 != 0) goto L_0x002e
            r5.enableSurfaceSharing()     // Catch:{ Exception -> 0x00ee }
            java.lang.String r7 = r10.TAG     // Catch:{ Exception -> 0x00ee }
            java.lang.String r8 = "enableSurfaceSharing"
            r9 = 0
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ Exception -> 0x00ee }
            com.autochips.camera.util.LogUtil.d(r7, r8, r9)     // Catch:{ Exception -> 0x00ee }
        L_0x002e:
            android.view.Surface r7 = r10.mRecorderSurface     // Catch:{ Exception -> 0x00ee }
            if (r7 != 0) goto L_0x0038
            boolean r7 = r10.prepareRecorderSurface()     // Catch:{ Exception -> 0x00ee }
            if (r7 == 0) goto L_0x00c6
        L_0x0038:
            java.lang.String r7 = r10.mCameraId     // Catch:{ Exception -> 0x00ee }
            java.lang.String r8 = "3"
            boolean r7 = r7.equals(r8)     // Catch:{ Exception -> 0x00ee }
            if (r7 != 0) goto L_0x00c6
            if (r5 != 0) goto L_0x00c1
            android.hardware.camera2.params.OutputConfiguration r4 = new android.hardware.camera2.params.OutputConfiguration     // Catch:{ Exception -> 0x00ee }
            android.view.Surface r7 = r10.mRecorderSurface     // Catch:{ Exception -> 0x00ee }
            r4.<init>(r7)     // Catch:{ Exception -> 0x00ee }
            r4.enableSurfaceSharing()     // Catch:{ Exception -> 0x00d1 }
        L_0x004f:
            android.media.ImageReader r7 = r10.mImageReader     // Catch:{ Exception -> 0x00d1 }
            android.view.Surface r3 = r7.getSurface()     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r7 = r10.TAG     // Catch:{ Exception -> 0x00d1 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r9 = "imageSurface = "
            r8.<init>(r9)     // Catch:{ Exception -> 0x00d1 }
            java.lang.StringBuilder r8 = r8.append(r3)     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r9 = ", isValid= "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x00d1 }
            boolean r9 = r3.isValid()     // Catch:{ Exception -> 0x00d1 }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x00d1 }
            r9 = 0
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ Exception -> 0x00d1 }
            com.autochips.camera.util.LogUtil.d(r7, r8, r9)     // Catch:{ Exception -> 0x00d1 }
            r2 = 0
            if (r3 == 0) goto L_0x008a
            boolean r7 = r3.isValid()     // Catch:{ Exception -> 0x00d1 }
            if (r7 == 0) goto L_0x008a
            android.hardware.camera2.params.OutputConfiguration r2 = new android.hardware.camera2.params.OutputConfiguration     // Catch:{ Exception -> 0x00d1 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x00d1 }
        L_0x008a:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Exception -> 0x00d1 }
            r0.<init>()     // Catch:{ Exception -> 0x00d1 }
            if (r4 == 0) goto L_0x0094
            r0.add(r4)     // Catch:{ Exception -> 0x00d1 }
        L_0x0094:
            if (r2 == 0) goto L_0x0099
            r0.add(r2)     // Catch:{ Exception -> 0x00d1 }
        L_0x0099:
            java.lang.String r8 = r10.TAG     // Catch:{ Exception -> 0x00d1 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r7 = "createCaptureSession, surface sharing size = "
            r9.<init>(r7)     // Catch:{ Exception -> 0x00d1 }
            if (r4 != 0) goto L_0x00c8
            r7 = r6
        L_0x00a6:
            java.lang.StringBuilder r7 = r9.append(r7)     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x00d1 }
            r9 = 0
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ Exception -> 0x00d1 }
            com.autochips.camera.util.LogUtil.d(r8, r7, r9)     // Catch:{ Exception -> 0x00d1 }
            android.hardware.camera2.CameraDevice r7 = r10.mCameraDevice     // Catch:{ Exception -> 0x00d1 }
            com.autochips.camera.CameraOperation$9 r8 = new com.autochips.camera.CameraOperation$9     // Catch:{ Exception -> 0x00d1 }
            r8.<init>()     // Catch:{ Exception -> 0x00d1 }
            r9 = 0
            r7.createCaptureSessionByOutputConfigurations(r0, r8, r9)     // Catch:{ Exception -> 0x00d1 }
            r6 = 1
        L_0x00c0:
            return r6
        L_0x00c1:
            android.view.Surface r7 = r10.mRecorderSurface     // Catch:{ Exception -> 0x00ee }
            r5.addSurface(r7)     // Catch:{ Exception -> 0x00ee }
        L_0x00c6:
            r4 = r5
            goto L_0x004f
        L_0x00c8:
            java.util.List r7 = r4.getSurfaces()     // Catch:{ Exception -> 0x00d1 }
            int r7 = r7.size()     // Catch:{ Exception -> 0x00d1 }
            goto L_0x00a6
        L_0x00d1:
            r1 = move-exception
        L_0x00d2:
            java.lang.String r7 = r10.TAG
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "createCaptureSession: "
            r8.<init>(r9)
            java.lang.String r9 = r1.toString()
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            java.lang.Object[] r9 = new java.lang.Object[r6]
            com.autochips.camera.util.LogUtil.d(r7, r8, r9)
            goto L_0x00c0
        L_0x00ee:
            r1 = move-exception
            r4 = r5
            goto L_0x00d2
        L_0x00f1:
            r5 = r4
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autochips.camera.CameraOperation.createCaptureSession():boolean");
    }

    public void startPreview() {
        startPreview(this.mNextPreviewSurface);
    }

    public void startPreview(Surface surface) {
        LogUtil.d(this.TAG, "startPreview, surface=" + surface + ", isValid=" + surface.isValid(), new Object[0]);
        if (surface == null || !surface.isValid()) {
            notifyOperationCallback(3, false, -1);
            return;
        }
        LogUtil.d(this.TAG, "mPreviewSurface=" + this.mPreviewSurface + ", mPreviewRequested=" + this.mPreviewRequested, new Object[0]);
        if (this.mPendingPreviewSurface == null && surface != this.mPreviewSurface && this.mPreviewRequested) {
            this.mPendingPreviewSurface = this.mPreviewSurface;
        }
        this.mPreviewRequested = true;
        this.mPreviewSurface = surface;
        this.mNextPreviewSurface = surface;
        if (!this.mRecordRequested || !this.mIsRecording) {
            closePreviewSession();
            if (this.mCaptureSession != null) {
                updatePreviewAndRecord();
            } else if (!createCaptureSession()) {
                notifyOperationCallback(3, false, 7);
            }
        } else {
            restartRecord();
        }
    }

    public boolean hasPendingPreviewSurface() {
        return this.mPendingPreviewSurface != null;
    }

    public void stopPreviewWhileQbOff() {
        LogUtil.d(this.TAG, "stopPreviewWhileQbOff, mPendingPreviewSurface=" + this.mPendingPreviewSurface, new Object[0]);
        if (this.mPendingPreviewSurface != null) {
            this.mNextPreviewSurface = this.mPendingPreviewSurface;
        } else {
            this.mNextPreviewSurface = null;
        }
    }

    public void stopPreview() {
        LogUtil.d(this.TAG, "stopPreview, mPreviewRequested=" + this.mPreviewRequested + ", mPendingPreviewSurface=" + this.mPendingPreviewSurface, new Object[0]);
        if (!this.mPreviewRequested) {
            notifyOperationCallback(6, true, 0);
            return;
        }
        try {
            if (this.mPendingPreviewSurface != null && this.mPendingPreviewSurface != this.mPreviewSurface) {
                Surface surface = this.mPendingPreviewSurface;
                this.mPendingPreviewSurface = null;
                this.mPreviewSurface = surface;
                if (!this.mRecordRequested || !this.mIsRecording) {
                    startPreview(surface);
                } else {
                    restartRecord();
                }
            } else if (this.mPendingPreviewSurface != null || this.mPreviewSurface == this.mNextPreviewSurface) {
                if (this.mRecordRequested) {
                    if (this.mIsRecording) {
                        this.mCaptureRequestBuilder.removeTarget(this.mPreviewSurface);
                        this.mCaptureSession.setRepeatingRequest(this.mCaptureRequestBuilder.build(), (CameraCaptureSession.CaptureCallback) null, (Handler) null);
                    }
                } else if (this.mCaptureSession != null) {
                    this.mCaptureSession.stopRepeating();
                    this.mCaptureSession.close();
                    this.mCaptureSession = null;
                }
                this.mNextPreviewSurface = null;
                this.mPendingPreviewSurface = null;
                this.mPreviewSurface = null;
                this.mPreviewRequested = false;
                this.mIsPreviewing = false;
                notifyOperationCallback(6, true, 0);
            } else {
                LogUtil.d(this.TAG, "invalid stopPreview", new Object[0]);
            }
        } catch (Exception e) {
            LogUtil.d(this.TAG, "stopPreview fail: " + e.toString(), new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void updatePreviewAndRecord() {
        int i;
        int i2 = 5;
        LogUtil.d(this.TAG, "updatePreviewAndRecord, mRecordRequested=" + this.mRecordRequested + ", mPreviewRequested=" + this.mPreviewRequested, new Object[0]);
        if (this.mRecordRequested || this.mPreviewRequested) {
            boolean setRequestSuccess = false;
            try {
                setUpCaptureRequestBuilder();
                this.mCaptureSession.setRepeatingRequest(this.mCaptureRequestBuilder.build(), this.mCaptureCallback, (Handler) null);
                setRequestSuccess = true;
                this.mFramesShouldSkipped = 30;
                if (this.mIsChangingResolution) {
                    notifyOperationCallback(13, true, 0);
                    this.mIsChangingResolution = false;
                }
                if (this.mPreviewRequested) {
                    this.mIsPreviewing = true;
                    notifyOperationCallback(3, true, 0);
                }
            } catch (IllegalStateException e) {
                LogUtil.d(this.TAG, "updatePreviewAndRecord fail: " + e.toString(), new Object[0]);
            } catch (Exception e2) {
                LogUtil.d(this.TAG, "updatePreviewAndRecord fail: " + e2.toString(), new Object[0]);
                if (this.mCameraOperationCallback != null) {
                    if (this.mRecordRequested) {
                        if (this.mIsUrgentRecording) {
                            i = 5;
                        } else {
                            i = 4;
                        }
                        notifyOperationCallback(i, false, -1);
                    }
                    if (this.mPreviewRequested && !this.mIsPreviewing) {
                        notifyOperationCallback(3, false, -1);
                    }
                }
            }
            if (setRequestSuccess && this.mRecordRequested && !this.mIsRecording) {
                try {
                    this.mRecorder.start();
                    LogUtil.d(this.TAG, "MediaRecorder start", new Object[0]);
                    this.mIsRecording = true;
                    if (!this.mIsUrgentRecording) {
                        notifyOperationCallback(4, true, 0);
                        this.mRecordThreadHandler.postDelayed(this.mNormalRecordTask, this.mVideoDuration);
                        this.mNormalRecordStartTime = System.currentTimeMillis();
                    } else {
                        notifyOperationCallback(5, true, 0);
                        new MoveUrgentFileTask(this.mStorageUtil).execute(new Long[]{Long.valueOf(System.currentTimeMillis())});
                        this.mRecordThreadHandler.postDelayed(this.mUrgentRecordTask, DEFAULT_URGENT_RECORD_TIME);
                    }
                    startNewRecycleTask();
                } catch (Exception e3) {
                    LogUtil.d(this.TAG, "MeidaPlayer start fail: " + e3.toString(), new Object[0]);
                    if (!this.mIsUrgentRecording) {
                        i2 = 4;
                    }
                    notifyOperationCallback(i2, false, -1);
                }
            }
        }
    }

    private void setUpCaptureRequestBuilder() throws CameraAccessException {
        if (this.mRecordRequested) {
            this.mCaptureRequestBuilder = this.mCameraDevice.createCaptureRequest(3);
            this.mCaptureRequestBuilder.addTarget(this.mRecorderSurface);
        } else {
            this.mCaptureRequestBuilder = this.mCameraDevice.createCaptureRequest(1);
        }
        if (this.mPreviewRequested) {
            this.mCaptureRequestBuilder.addTarget(this.mPreviewSurface);
        }
        if (this.mPreviewRequested && this.mIsBackcarCamera) {
            getMetazoneInfo();
            this.mCaptureRequestBuilder.set(CaptureRequest.SCALER_CROP_REGION, this.mCamRect);
            if (this.mMirrorKey != null) {
                CaptureRequest.Builder builder = this.mCaptureRequestBuilder;
                CaptureRequest.Key<int[]> key = this.mMirrorKey;
                int[] iArr = new int[4];
                iArr[0] = this.mMirrorVal;
                builder.set(key, iArr);
            }
            if (this.mSharpnessKey != null) {
                CaptureRequest.Builder builder2 = this.mCaptureRequestBuilder;
                CaptureRequest.Key<int[]> key2 = this.mSharpnessKey;
                int[] iArr2 = new int[4];
                iArr2[0] = this.mSharpnessVal;
                builder2.set(key2, iArr2);
            }
        }
        if (!this.mIsWatermarkOn || this.mWatermarkKey == null) {
            this.mIsWatermarkOn = false;
        } else {
            this.mCaptureRequestBuilder.set(this.mWatermarkKey, new int[]{1});
        }
        this.mCaptureRequestBuilder.set(CaptureRequest.CONTROL_MODE, 1);
    }

    public void setScalerCropRegion(Rect rect) {
        this.mCamRect = rect;
        this.mCaptureRequestBuilder.set(CaptureRequest.SCALER_CROP_REGION, this.mCamRect);
        try {
            this.mCaptureSession.setRepeatingRequest(this.mCaptureRequestBuilder.build(), (CameraCaptureSession.CaptureCallback) null, (Handler) null);
        } catch (Exception e) {
            LogUtil.d(this.TAG, "[yu.mo]: setRepeatingRequest error ->" + e.toString(), new Object[0]);
        }
    }

    private void getMetazoneInfo() {
        int mirror_addr = AHD_MIR_ADDR;
        int x_cut_addr = AHD_X_CUT_ADDR;
        int y_cut_addr = AHD_Y_CUT_ADDR;
        int w_cut_addr = AHD_W_CUT_ADDR;
        int h_cut_addr = AHD_H_CUT_ADDR;
        if ("3".equals(this.mCameraId)) {
            mirror_addr = CVBS_MIR_ADDR;
            x_cut_addr = CVBS_X_CUT_ADDR;
            y_cut_addr = CVBS_Y_CUT_ADDR;
            w_cut_addr = CVBS_W_CUT_ADDR;
            h_cut_addr = CVBS_H_CUT_ADDR;
        }
        int[] tmp = new int[4];
        if (MainUI.IsCameraMode() == 0 && "3".equals(this.mCameraId)) {
            if (BackcarService.GetForceMir()) {
                this.mMirrorVal = 1;
            }
            this.mMirrorVal = 0;
        } else if (MainUI.IsCameraMode() != 0 || !"2".equals(this.mCameraId)) {
            AtcMetazone.readval(mirror_addr, tmp);
            this.mMirrorVal = tmp[0];
        } else {
            this.mMirrorVal = 0;
        }
        AtcMetazone.readval(SHARPNESS_ADDR, tmp);
        this.mSharpnessVal = tmp[0];
        AtcMetazone.readval(x_cut_addr, tmp);
        int x = tmp[0];
        AtcMetazone.readval(y_cut_addr, tmp);
        int y = tmp[0];
        AtcMetazone.readval(w_cut_addr, tmp);
        int w = tmp[0];
        AtcMetazone.readval(h_cut_addr, tmp);
        this.mCamRect = new Rect(x, y, x + w, y + tmp[0]);
    }

    private void closePreviewSession() {
        LogUtil.d(this.TAG, "closePreviewSession", new Object[0]);
        if (this.mCaptureSession != null) {
            this.mCaptureSession.close();
            this.mCaptureSession = null;
        }
        this.mIsPreviewing = false;
    }

    public synchronized void restartRecord() {
        startRecord(this.mIsUrgentRecording);
    }

    public synchronized void startRecord(boolean isUrgent) {
        int i = 4;
        synchronized (this) {
            Log.d(this.TAG, "startRecord, type = " + (isUrgent ? URGENT_TYPE : NORMAL_TYPE));
            if (this.mIsRecording) {
                if (!isUrgent) {
                    stopRecordAndRecycle();
                } else {
                    this.mUrgentRecordTriggeredTime = System.currentTimeMillis();
                    long timeDiffer = this.mUrgentRecordTriggeredTime - this.mNormalRecordStartTime;
                    LogUtil.d(this.TAG, "mUrgentRecordTriggeredTime=" + this.mUrgentRecordTriggeredTime + ", timeDiffer=" + timeDiffer, new Object[0]);
                    if (timeDiffer < DEFAULT_URGENT_RECORD_TIME) {
                        this.mIsUrgentRecording = true;
                        this.mRecordThreadHandler.postDelayed(this.mDelayedUrgentRecordTask, DEFAULT_URGENT_RECORD_TIME - timeDiffer);
                        notifyOperationCallback(5, true, 0);
                        if (this.mFramesShouldSkipped <= 0) {
                            notifyOperationCallback(14, false, 15);
                        }
                    } else {
                        stopRecord();
                    }
                }
            }
            this.mRecordRequested = true;
            this.mIsUrgentRecording = isUrgent;
            if (!prepareMediaRecorder()) {
                LogUtil.d(this.TAG, "prepareMediaRecorder fail", new Object[0]);
                this.mRecordRequested = false;
                if (isUrgent) {
                    i = 5;
                }
                notifyOperationCallback(i, false, -1);
            } else {
                if (this.mFramesShouldSkipped <= 0) {
                    notifyOperationCallback(14, false, 15);
                }
                closePreviewSession();
                if (!createCaptureSession()) {
                    if (isUrgent) {
                        i = 5;
                    }
                    notifyOperationCallback(i, false, 7);
                }
            }
        }
    }

    private void stopRecordAndRecycle() {
        LogUtil.d(this.TAG, "stopRecordAndRecycle", new Object[0]);
        if (this.mIsRecording) {
            if (this.mRecordThreadHandler.hasCallbacks(this.mDelayedUrgentRecordTask)) {
                new MoveUrgentFileTask(this.mStorageUtil).execute(new Long[]{Long.valueOf(this.mUrgentRecordTriggeredTime)});
            }
            this.mRecordThreadHandler.removeCallbacksAndMessages((Object) null);
            stopRecycleTask();
            try {
                this.mRecorder.reset();
            } catch (Exception e) {
                LogUtil.d(this.TAG, "stopRecordAndRecycle fail:" + e.toString(), new Object[0]);
            }
            this.mIsRecording = false;
        }
    }

    private boolean prepareRecorderSurface() {
        try {
            MediaRecorder mr = new MediaRecorder();
            mr.setVideoSource(2);
            this.mRecorderSurface = MediaCodec.createPersistentInputSurface();
            mr.setInputSurface(this.mRecorderSurface);
            mr.setOutputFormat(8);
            mr.setVideoEncodingBitRate(VIDEO_ENCODE_BIT_RATE);
            mr.setVideoFrameRate(50);
            mr.setVideoSize(this.mVideoSize.getWidth(), this.mVideoSize.getHeight());
            mr.setOutputFile("/dev/null");
            mr.setVideoEncoder(2);
            mr.prepare();
            mr.reset();
            mr.release();
            return true;
        } catch (Exception e) {
            LogUtil.d(this.TAG, "prepareRecordSurface fail: " + e.toString(), new Object[0]);
            this.mRecorderSurface = null;
            return false;
        }
    }

    private boolean prepareMediaRecorder() {
        try {
            this.mRecorder.reset();
            this.mRecorder.setOnErrorListener(this.mRecorderErrorListener);
            this.mRecorder.setOnInfoListener(this.mRecorderInfoListener);
            this.mRecorder.setVideoSource(2);
            if (this.mRecorderSurface == null && !prepareRecorderSurface()) {
                return false;
            }
            this.mRecorder.setInputSurface(this.mRecorderSurface);
            if (this.mIsMicOn) {
                this.mRecorder.setAudioSource(1);
            }
            this.mRecorder.setOutputFormat(8);
            File file = getOutputFile(this.mIsUrgentRecording ? URGENT_TYPE : NORMAL_TYPE);
            if (file == null) {
                return false;
            }
            this.mRecordingFile = file;
            this.mRecorder.setOutputFile(file);
            this.mRecorder.setVideoEncodingBitRate(VIDEO_ENCODE_BIT_RATE);
            this.mRecorder.setVideoFrameRate(50);
            this.mRecorder.setVideoSize(this.mVideoSize.getWidth(), this.mVideoSize.getHeight());
            this.mRecorder.setVideoEncoder(2);
            if (this.mIsMicOn) {
                this.mRecorder.setAudioEncodingBitRate(AUDIO_ENCODE_BIT_RATE);
                this.mRecorder.setAudioChannels(2);
                this.mRecorder.setAudioSamplingRate(AUDIO_SAMPLE_RATE);
                this.mRecorder.setAudioEncoder(3);
            }
            this.mRecorder.prepare();
            return true;
        } catch (Exception e) {
            LogUtil.e(this.TAG, e.toString(), new Object[0]);
            try {
                this.mRecorder.reset();
                return false;
            } catch (Exception e2) {
                return false;
            }
        }
    }

    public synchronized void stopRecord() {
        LogUtil.d(this.TAG, "stopRecord", new Object[0]);
        stopRecordAndRecycle();
        this.mRecordRequested = false;
        notifyOperationCallback(!this.mIsUrgentRecording ? 7 : 8, true, 0);
    }

    public void snapshot() {
        if (this.mCaptureSession == null || this.mCameraDevice == null) {
            notifyOperationCallback(9, false, 7);
            return;
        }
        try {
            CaptureRequest.Builder builder = this.mCameraDevice.createCaptureRequest(2);
            builder.addTarget(this.mImageReader.getSurface());
            builder.set(CaptureRequest.CONTROL_AF_MODE, 4);
            if (this.mWatermarkKey == null || !this.mIsWatermarkOn) {
                this.mIsWatermarkOn = false;
            } else {
                builder.set(this.mWatermarkKey, new int[]{1});
            }
            if (this.mFramesShouldSkipped <= 0) {
                notifyOperationCallback(14, false, 15);
            }
            this.mCaptureSession.capture(builder.build(), new CameraCaptureSession.CaptureCallback() {
                public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                    LogUtil.d(CameraOperation.this.TAG, "onCaptureCompleted", new Object[0]);
                }

                public void onCaptureFailed(CameraCaptureSession session, CaptureRequest request, CaptureFailure failure) {
                    LogUtil.d(CameraOperation.this.TAG, "onCaptureFailed", new Object[0]);
                    CameraOperation.this.notifyOperationCallback(9, false, -1);
                }
            }, (Handler) null);
        } catch (Exception e) {
            notifyOperationCallback(9, false, 7);
        }
    }

    private void getAtcCaptureKeys() {
        try {
            for (CaptureRequest.Key<?> key : this.mCameraManager.getCameraCharacteristics(this.mCameraId).getAvailableCaptureRequestKeys()) {
                if (ATC_WATERMARK_KEY_NAME.equals(key.getName())) {
                    this.mWatermarkKey = key;
                    LogUtil.d(this.TAG, "Camera " + this.mCameraId + " support watermark", new Object[0]);
                } else if (ATC_MIRROR_KEY_NAME.equals(key.getName())) {
                    this.mMirrorKey = key;
                    LogUtil.d(this.TAG, "Camera " + this.mCameraId + " support mirror", new Object[0]);
                } else if (ATC_SHARPNESS_KEY_NAME.equals(key.getName())) {
                    this.mSharpnessKey = key;
                    LogUtil.d(this.TAG, "Camera " + this.mCameraId + " support sharpness", new Object[0]);
                }
            }
            for (CaptureResult.Key<?> key2 : this.mCameraManager.getCameraCharacteristics(this.mCameraId).getAvailableCaptureResultKeys()) {
                if (ATC_SOURCEINFO_KEY_NAME.equals(key2.getName())) {
                    this.mSourceInfoKey = key2;
                    LogUtil.d(this.TAG, "Camera " + this.mCameraId + " support sourceinfo", new Object[0]);
                }
            }
        } catch (Exception e) {
            LogUtil.d(this.TAG, "getAtcCaptureKeys fail: " + e.toString(), new Object[0]);
        }
    }

    public Size[] getSupportResolutions() {
        try {
            StreamConfigurationMap map = (StreamConfigurationMap) this.mCameraManager.getCameraCharacteristics(this.mCameraId).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            if (map == null) {
                return null;
            }
            return map.getOutputSizes(SurfaceTexture.class);
        } catch (Exception e) {
            return null;
        }
    }

    private boolean checkIfResolutionSupported(Size res) {
        Size[] resolutionGroup = getSupportResolutions();
        if (resolutionGroup == null) {
            return false;
        }
        for (Size size : resolutionGroup) {
            if (size.equals(res)) {
                return true;
            }
        }
        return false;
    }

    private void setMediaRecorderSyncFlag(boolean flag) {
        if (flag) {
            try {
                SystemProperties.set("persist.sys.media.recorder.setsync.storage", String.valueOf(1));
            } catch (Exception e) {
                LogUtil.e(this.TAG, "can not set property, please check selinux permission", new Object[0]);
            }
        } else {
            SystemProperties.set("persist.sys.media.recorder.setsync.storage", String.valueOf(0));
        }
    }

    /* access modifiers changed from: private */
    public File getOutputFile(String fileType) {
        File file = null;
        int operation = IMAGE_TPYE.equals(fileType) ? 9 : 4;
        try {
            file = this.mStorageUtil.getOutputFile(fileType);
        } catch (StorageUtil.NoSDCardMountedException e) {
            LogUtil.d(this.TAG, "NoSDCardMountedException", new Object[0]);
            notifyOperationCallback(operation, false, 2);
        } catch (StorageUtil.NoUdiskMountedException e2) {
            LogUtil.d(this.TAG, "NoUdiskMountedException", new Object[0]);
            notifyOperationCallback(operation, false, 3);
        } catch (StorageUtil.SpaceNotEnoughException e3) {
            LogUtil.d(this.TAG, "SpaceNotEnoughException", new Object[0]);
            notifyOperationCallback(operation, false, 1);
        } catch (StorageUtil.FileAlreadyExistException e4) {
            LogUtil.d(this.TAG, "FileAlreadyExistException", new Object[0]);
            notifyOperationCallback(operation, false, 11);
        }
        if (this.mRecorder != null) {
            if (URGENT_TYPE.equals(fileType)) {
                setMediaRecorderSyncFlag(true);
            } else if (NORMAL_TYPE.equals(fileType)) {
                setMediaRecorderSyncFlag(false);
            }
        }
        return file;
    }

    private void startNewRecycleTask() {
        stopRecycleTask();
        this.mStorageCheckerThread = new Timer();
        this.mStorageCheckerThread.schedule(new RecycleTask(this.mRecordingFile), 0, 1000);
    }

    /* access modifiers changed from: private */
    public void stopRecycleTask() {
        if (this.mStorageCheckerThread != null) {
            this.mStorageCheckerThread.cancel();
            this.mStorageCheckerThread = null;
        }
    }

    private void setupNewImageReader(int width, int height) {
        if (this.mImageReader != null) {
            this.mImageReader.close();
        }
        this.mImageReader = ImageReader.newInstance(width, height, 256, 2);
        this.mImageReader.setOnImageAvailableListener(this.mImageListener, (Handler) null);
    }

    public void setPreviewSurface(Surface surface) {
        this.mNextPreviewSurface = surface;
    }

    public boolean getBackcarState() {
        return this.mIsBackcarCamera;
    }

    public void setResolution(int width, int height) {
        LogUtil.d(this.TAG, "setResolution, width = " + width + ", height = " + height, new Object[0]);
        if (this.mVideoSize.getWidth() != width || this.mVideoSize.getHeight() != height) {
            if (this.mIsChangingResolution || (this.mCaptureSession == null && (this.mPreviewRequested || this.mRecordRequested))) {
                LogUtil.d(this.TAG, "resolution is changing , return", new Object[0]);
                return;
            }
            Size resolution = new Size(width, height);
            if (checkIfResolutionSupported(resolution)) {
                this.mVideoSize = resolution;
                setupNewImageReader(width, height);
                prepareRecorderSurface();
                if (this.mIsRecording) {
                    this.mIsChangingResolution = true;
                    restartRecord();
                } else if (this.mIsPreviewing) {
                    this.mIsChangingResolution = true;
                    startPreview(this.mPreviewSurface);
                } else {
                    notifyOperationCallback(13, true, 0);
                }
            } else {
                notifyOperationCallback(13, false, 10);
            }
        }
    }

    public void setVideoDuration(long duration) {
        LogUtil.d(this.TAG, "setVideoDurtion, duration = " + duration, new Object[0]);
        if (this.mVideoDuration != duration) {
            this.mVideoDuration = duration;
            if (this.mIsRecording) {
                restartRecord();
            } else {
                notifyOperationCallback(12, true, 0);
            }
        }
    }

    public void setMicOnOff(boolean micOnOff) {
        if (this.mIsMicOn != micOnOff) {
            this.mIsMicOn = micOnOff;
            if (this.mIsRecording) {
                restartRecord();
            } else {
                notifyOperationCallback(10, true, 0);
            }
        }
    }

    public void setWatermarkOnOff(boolean watermarkOnOff) {
        if (this.mWatermarkKey == null) {
            notifyOperationCallback(11, false, 12);
            return;
        }
        this.mIsWatermarkOn = watermarkOnOff;
        if (this.mIsRecording) {
            restartRecord();
        } else if (this.mIsPreviewing) {
            startPreview(this.mPreviewSurface);
        }
    }

    public boolean isPreviewing() {
        return this.mPreviewRequested && this.mIsPreviewing;
    }

    public boolean isRecording(boolean urgent) {
        if (!this.mIsRecording || !this.mRecordRequested) {
            return false;
        }
        if (urgent) {
            return this.mIsUrgentRecording;
        }
        return true;
    }

    public boolean isPreviewRequest() {
        return this.mPreviewRequested;
    }

    public boolean isRecordRequest() {
        return this.mRecordRequested;
    }

    public Size getResolution() {
        return this.mVideoSize;
    }

    public long getDuration() {
        return this.mVideoDuration;
    }

    public boolean getMicState() {
        return this.mIsMicOn;
    }

    public boolean getWatermarkState() {
        return this.mIsWatermarkOn;
    }

    public void registerCameraOperationCallback(CameraOperationCallback callback) {
        this.mCameraOperationCallback = callback;
    }

    public void unregisterCameraOperationCallback() {
        this.mCameraOperationCallback = null;
    }

    /* access modifiers changed from: private */
    public void goOnRecording() {
        int i = 5;
        boolean success = false;
        File newFile = getOutputFile(this.mIsUrgentRecording ? URGENT_TYPE : NORMAL_TYPE);
        if (newFile != null) {
            try {
                this.mRecorder.setNextOutputFile(newFile);
                this.mRecordingFile = newFile;
                success = true;
            } catch (Exception e) {
                LogUtil.d(this.TAG, "setNextOutputFile fail " + e.toString(), new Object[0]);
                notifyOperationCallback(!this.mIsUrgentRecording ? 4 : 5, false, -1);
            }
        }
        if (success) {
            if (!this.mIsUrgentRecording) {
                this.mNormalRecordStartTime = System.currentTimeMillis();
            }
            if (!this.mIsUrgentRecording) {
                i = 4;
            }
            notifyOperationCallback(i, true, 0);
            this.mRecordThreadHandler.postDelayed(!this.mIsUrgentRecording ? this.mNormalRecordTask : this.mUrgentRecordTask, !this.mIsUrgentRecording ? this.mVideoDuration : DEFAULT_URGENT_RECORD_TIME);
            startNewRecycleTask();
            return;
        }
        stopRecord();
        this.mRecordRequested = false;
        this.mIsRecording = false;
    }

    class RecycleTask extends TimerTask {
        private int mCounter = 0;
        private File mExceptFile;

        public RecycleTask(File file) {
            this.mExceptFile = file;
        }

        public void run() {
            LogUtil.d(CameraOperation.this.TAG, "RecycleTask begin, mCounter = " + this.mCounter, new Object[0]);
            if (this.mCounter >= 4) {
                this.mCounter = 0;
                if (CameraOperation.this.mIsRecording && !CameraOperation.this.mIsUrgentRecording) {
                    File subDir = this.mExceptFile.getParentFile();
                    if (subDir.exists() && !CameraOperation.this.mStorageUtil.reuseStorageSpace(subDir, this.mExceptFile) && subDir.exists()) {
                        CameraOperation.this.stopRecord();
                        CameraOperation.this.mRecordThreadHandler.post(new Runnable() {
                            public void run() {
                                CameraOperation.this.notifyOperationCallback(4, false, 1);
                            }
                        });
                        return;
                    }
                    return;
                }
                return;
            }
            this.mCounter++;
            if (this.mExceptFile.getParentFile().getParentFile().getParentFile().exists() && !this.mExceptFile.exists() && CameraOperation.this.mRecorder != null) {
                CameraOperation.this.mRecordThreadHandler.removeCallbacksAndMessages((Object) null);
                CameraOperation.this.mRecordThreadHandler.post(new Runnable() {
                    public void run() {
                        CameraOperation.this.goOnRecording();
                    }
                });
            }
        }
    }

    static class MoveUrgentFileTask extends AsyncTask<Long, Void, Void> {
        private StorageUtil mSp;

        public MoveUrgentFileTask(StorageUtil sp) {
            this.mSp = sp;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Long... longs) {
            this.mSp.moveUrgentFileFromNormal(longs[0].longValue());
            return null;
        }
    }
}
