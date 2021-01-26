package com.autochips.camera;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import com.autochips.camera.CameraOperation;
import com.autochips.camera.util.DVRConst;
import com.autochips.camera.util.LogUtil;
import com.ts.can.CanCameraUI;
import com.ts.main.common.MainUI;
import java.util.ArrayList;
import java.util.Iterator;

public class CameraStateMachine extends StateMachine {
    public static final int MSG_CAMERA_CLOSED = 7;
    public static final int MSG_CAMERA_OPENED = 6;
    public static final int MSG_CLOSE_CAMERA = -1;
    public static final int MSG_MEDIA_EJECTED = 17;
    public static final int MSG_MEDIA_MOUNTED = -17;
    public static final int MSG_OPEN_CAMERA = 1;
    public static final int MSG_PREVIEWING = 12;
    public static final int MSG_PREVIEW_STOPPED = 14;
    public static final int MSG_QB_OFF = -16;
    public static final int MSG_QB_ON = 16;
    public static final int MSG_RECORDING = 13;
    public static final int MSG_RECORD_STOPPED = 15;
    public static final int MSG_SET_DURATION = 9;
    public static final int MSG_SET_MIC = 10;
    public static final int MSG_SET_RESOLUTION = 8;
    public static final int MSG_SET_WATERMARK = 11;
    public static final int MSG_SNAPSHOT = 5;
    public static final int MSG_START_PREVIEW = 2;
    public static final int MSG_START_RECORD = 3;
    public static final int MSG_START_URGENT = 4;
    public static final int MSG_STOP_PREVIEW = -2;
    public static final int MSG_STOP_RECORD = -3;
    public static final int MSG_STOP_URGENT = -4;
    private static final String TAG = "CameraStateMachine";
    /* access modifiers changed from: private */
    public CameraOperation mActiveCamera;
    /* access modifiers changed from: private */
    public String mActiveCameraId;
    /* access modifiers changed from: private */
    public StateMachineCallback mCallback;
    private ArrayList<CameraOperation> mCameraOperations = new ArrayList<>();
    /* access modifiers changed from: private */
    public State mClosedState;
    /* access modifiers changed from: private */
    public State mClosingState;
    private Context mContext;
    /* access modifiers changed from: private */
    public boolean mIsRecordingBeforeMediaEjected;
    /* access modifiers changed from: private */
    public State mOpenedState;
    /* access modifiers changed from: private */
    public State mOpeningState;
    private CameraOperation.CameraOperationCallback mOperationCallback = new CameraOperation.CameraOperationCallback() {
        public void onOperationSuccess(int op, String id) {
            LogUtil.d(CameraStateMachine.TAG, "onOperationSuccess: " + op + ", id = " + id, new Object[0]);
            switch (op) {
                case 1:
                    CameraStateMachine.this.sendMessage(6, (Object) CameraStateMachine.this.getCameraOperation(id));
                    break;
                case 2:
                    CameraStateMachine.this.sendMessage(7, (Object) CameraStateMachine.this.getCameraOperation(id));
                    break;
                case 3:
                    CameraStateMachine.this.sendMessage(12, (Object) CameraStateMachine.this.getCameraOperation(id));
                    break;
                case 4:
                    CameraStateMachine.this.sendMessage(13, (Object) CameraStateMachine.this.getCameraOperation(id));
                    break;
                case 5:
                    CameraStateMachine.this.sendMessage(13, (Object) CameraStateMachine.this.getCameraOperation(id));
                    break;
                case 6:
                    CameraStateMachine.this.sendMessage(14, (Object) CameraStateMachine.this.getCameraOperation(id));
                    break;
                case 7:
                    CameraStateMachine.this.sendMessage(15, (Object) CameraStateMachine.this.getCameraOperation(id));
                    break;
                case 8:
                    CameraStateMachine.this.sendMessage(15, (Object) CameraStateMachine.this.getCameraOperation(id));
                    break;
            }
            CameraStateMachine.this.mServiceThread.sendMessage(Message.obtain(CameraStateMachine.this.mServiceThread, op, 0, 0, id));
        }

        public void onOperationFailed(int op, String id, int extra) {
            LogUtil.d(CameraStateMachine.TAG, "onOperationFailed: " + op + ", id = " + id, new Object[0]);
            switch (op) {
                case 1:
                    CameraStateMachine.this.sendMessage(7, (Object) CameraStateMachine.this.getCameraOperation(id));
                    break;
            }
            CameraStateMachine.this.mServiceThread.sendMessage(Message.obtain(CameraStateMachine.this.mServiceThread, op, extra, 0, id));
        }
    };
    private Handler mOutsideHandler = new Handler();
    /* access modifiers changed from: private */
    public State mPreviewingAndRecordingState;
    /* access modifiers changed from: private */
    public State mPreviewingState;
    /* access modifiers changed from: private */
    public State mQBOffState;
    /* access modifiers changed from: private */
    public State mRecordingState;
    /* access modifiers changed from: private */
    public Handler mServiceThread = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String id = (String) msg.obj;
            MainUI.GetInstance();
            if (MainUI.IsCameraMode() == 0 && (!(msg.what == 14 && msg.arg1 == 17) && (("11".equals(id) || "2".equals(id) || "3".equals(id)) && msg.what == 14))) {
                if (msg.arg1 == 13) {
                    LogUtil.i(CameraStateMachine.TAG, "[yu.mo]setScalerCropRegion:NTSC", new Object[0]);
                    CameraStateMachine.this.mActiveCamera.setScalerCropRegion(new Rect(5, 5, 710, 470));
                } else if (msg.arg1 == 14) {
                    LogUtil.i(CameraStateMachine.TAG, "[yu.mo]setScalerCropRegion:PAL", new Object[0]);
                    CameraStateMachine.this.mActiveCamera.setScalerCropRegion(new Rect(5, 5, 710, CanCameraUI.BTN_TRUMPCHI_GS7_MODE7));
                }
            }
            if (CameraStateMachine.this.mCallback == null) {
                return;
            }
            if (msg.arg1 != 0) {
                CameraStateMachine.this.mCallback.onOperationFail(id, msg.what, msg.arg1);
            } else {
                CameraStateMachine.this.mCallback.onOperationSuccess(id, msg.what);
            }
        }
    };

    interface StateMachineCallback {
        void onOperationFail(String str, int i, int i2);

        void onOperationSuccess(String str, int i);
    }

    public CameraStateMachine(Context context, String cameraId) {
        super(TAG);
        this.mContext = context;
        this.mActiveCameraId = cameraId;
        initStates();
    }

    private void initStates() {
        this.mClosedState = new ClosedState(this, (ClosedState) null);
        this.mOpeningState = new OpeningState(this, (OpeningState) null);
        this.mOpenedState = new OpenedState(this, (OpenedState) null);
        this.mClosingState = new ClosingState(this, (ClosingState) null);
        this.mQBOffState = new QBOffState(this, (QBOffState) null);
        this.mPreviewingState = new PreviewingState(this, (PreviewingState) null);
        this.mRecordingState = new RecordingState(this, (RecordingState) null);
        this.mPreviewingAndRecordingState = new PreviewingAndRecordingState(this, (PreviewingAndRecordingState) null);
        addState(this.mClosedState);
        addState(this.mOpeningState);
        addState(this.mOpenedState);
        addState(this.mClosingState);
        addState(this.mQBOffState);
        addState(this.mPreviewingState, this.mOpenedState);
        addState(this.mRecordingState, this.mOpenedState);
        addState(this.mPreviewingAndRecordingState, this.mOpenedState);
        setInitialState(this.mClosedState);
    }

    /* access modifiers changed from: private */
    public void handleSetParamMessage(CameraOperation cameraOperation, Message msg) {
        boolean z = true;
        switch (msg.what) {
            case 8:
                cameraOperation.setResolution(msg.arg1, msg.arg2);
                return;
            case 9:
                cameraOperation.setVideoDuration((long) msg.arg1);
                return;
            case 10:
                if (msg.arg1 != 1) {
                    z = false;
                }
                cameraOperation.setMicOnOff(z);
                return;
            case 11:
                if (msg.arg1 != 1) {
                    z = false;
                }
                cameraOperation.setWatermarkOnOff(z);
                return;
            default:
                return;
        }
    }

    private class ClosedState extends State {
        private ClosedState() {
        }

        /* synthetic */ ClosedState(CameraStateMachine cameraStateMachine, ClosedState closedState) {
            this();
        }

        public void enter() {
            LogUtil.d(CameraStateMachine.TAG, "enter ClosedState", new Object[0]);
            CameraOperation unused = CameraStateMachine.this.getCameraOperation(CameraStateMachine.this.mActiveCameraId);
        }

        public boolean processMessage(Message msg) {
            Log.d(CameraStateMachine.TAG, String.valueOf(getClass().getSimpleName()) + " processMessage " + msg.what);
            CameraOperation cameraOperation = (CameraOperation) msg.obj;
            Log.d(CameraStateMachine.TAG, "camera id is " + (cameraOperation != null ? cameraOperation.getCameraId() : DVRConst.UNKOWN_CAMERA_ID) + ", mActiveCameraId = " + (CameraStateMachine.this.mActiveCamera != null ? CameraStateMachine.this.mActiveCamera.getCameraId() : DVRConst.UNKOWN_CAMERA_ID));
            if (!CameraStateMachine.this.hasTheOppositeMessage(msg.what, cameraOperation) && !CameraStateMachine.this.hasTheSameMessage(msg.what, cameraOperation)) {
                if (!CameraStateMachine.this.hasMessages(-16)) {
                    switch (msg.what) {
                        case -3:
                        case -2:
                            break;
                        case 1:
                            cameraOperation.open();
                            CameraStateMachine.this.transitionTo(CameraStateMachine.this.mOpeningState);
                            break;
                        case 2:
                        case 3:
                        case 5:
                            if ((CameraStateMachine.this.mActiveCamera == null || cameraOperation == CameraStateMachine.this.mActiveCamera) && (cameraOperation.getBackcarState() || !CameraStateMachine.this.hasMessages(msg.what))) {
                                CameraStateMachine.this.transitionTo(CameraStateMachine.this.mOpeningState);
                                CameraStateMachine.this.mActiveCamera = cameraOperation;
                                cameraOperation.open();
                            }
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                            CameraStateMachine.this.handleSetParamMessage(cameraOperation, msg);
                            break;
                        default:
                            LogUtil.d(CameraStateMachine.TAG, "illegal mesage " + msg.what + " appear in " + getClass().getSimpleName(), new Object[0]);
                            break;
                    }
                } else {
                    CameraStateMachine.this.transitionTo(CameraStateMachine.this.mQBOffState);
                    ((QBOffState) CameraStateMachine.this.mQBOffState).mIsCameraClosed = true;
                    CameraStateMachine.this.deferMessage(msg);
                }
            }
            return true;
        }
    }

    private class OpeningState extends State {
        private OpeningState() {
        }

        /* synthetic */ OpeningState(CameraStateMachine cameraStateMachine, OpeningState openingState) {
            this();
        }

        public void enter() {
            LogUtil.d(CameraStateMachine.TAG, "enter OpeningState", new Object[0]);
        }

        public boolean processMessage(Message msg) {
            LogUtil.d(CameraStateMachine.TAG, String.valueOf(getClass().getSimpleName()) + " processMessage " + msg.what, new Object[0]);
            CameraOperation cameraOperation = (CameraOperation) msg.obj;
            LogUtil.d(CameraStateMachine.TAG, "camera id is " + (cameraOperation != null ? cameraOperation.getCameraId() : DVRConst.UNKOWN_CAMERA_ID) + ", mActiveCameraId = " + (CameraStateMachine.this.mActiveCamera != null ? CameraStateMachine.this.mActiveCamera.getCameraId() : DVRConst.UNKOWN_CAMERA_ID), new Object[0]);
            if (!CameraStateMachine.this.hasTheOppositeMessage(msg.what, cameraOperation) && !CameraStateMachine.this.hasTheSameMessage(msg.what, cameraOperation)) {
                switch (msg.what) {
                    case CameraStateMachine.MSG_MEDIA_MOUNTED /*-17*/:
                    case CameraStateMachine.MSG_QB_OFF /*-16*/:
                    case 17:
                        CameraStateMachine.this.deferMessage(msg);
                        break;
                    case -3:
                    case -2:
                    case 2:
                    case 3:
                    case 5:
                        CameraStateMachine.this.deferMessage(msg);
                        break;
                    case 6:
                        CameraStateMachine.this.mActiveCamera = cameraOperation;
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mOpenedState);
                        break;
                    case 7:
                        CameraStateMachine.this.mActiveCamera = null;
                        CameraStateMachine.this.removeTheSameMessages(2, cameraOperation);
                        CameraStateMachine.this.removeTheSameMessages(3, cameraOperation);
                        CameraStateMachine.this.removeTheSameMessages(4, cameraOperation);
                        CameraStateMachine.this.removeTheSameMessages(5, cameraOperation);
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosedState);
                        break;
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                        CameraStateMachine.this.handleSetParamMessage(cameraOperation, msg);
                        break;
                    default:
                        LogUtil.d(CameraStateMachine.TAG, "illegal mesage " + msg.what + " appear in " + getClass().getSimpleName(), new Object[0]);
                        break;
                }
            }
            return true;
        }
    }

    private class OpenedState extends State {
        private OpenedState() {
        }

        /* synthetic */ OpenedState(CameraStateMachine cameraStateMachine, OpenedState openedState) {
            this();
        }

        public void enter() {
            LogUtil.d(CameraStateMachine.TAG, "enter OpenedState", new Object[0]);
        }

        public boolean processMessage(Message msg) {
            Log.d(CameraStateMachine.TAG, String.valueOf(getClass().getSimpleName()) + " processMessage " + msg.what);
            CameraOperation cameraOperation = (CameraOperation) msg.obj;
            Log.d(CameraStateMachine.TAG, "camera id is " + (cameraOperation != null ? cameraOperation.getCameraId() : DVRConst.UNKOWN_CAMERA_ID) + ", mActiveCameraId = " + (CameraStateMachine.this.mActiveCamera != null ? CameraStateMachine.this.mActiveCamera.getCameraId() : DVRConst.UNKOWN_CAMERA_ID));
            if (!CameraStateMachine.this.hasTheOppositeMessage(msg.what, cameraOperation) && !CameraStateMachine.this.hasTheSameMessage(msg.what, cameraOperation)) {
                switch (msg.what) {
                    case CameraStateMachine.MSG_MEDIA_MOUNTED /*-17*/:
                    case 17:
                        CameraStateMachine.this.deferMessage(msg);
                        break;
                    case CameraStateMachine.MSG_QB_OFF /*-16*/:
                        if (CameraStateMachine.this.mActiveCamera.isPreviewRequest() && !CameraStateMachine.this.hasTheOppositeMessage(2, CameraStateMachine.this.mActiveCamera)) {
                            CameraStateMachine.this.sendMessage(2, (Object) CameraStateMachine.this.mActiveCamera);
                        }
                        if (CameraStateMachine.this.mActiveCamera.isRecordRequest() && !CameraStateMachine.this.hasTheOppositeMessage(3, CameraStateMachine.this.mActiveCamera)) {
                            CameraStateMachine.this.sendMessage(3, (Object) CameraStateMachine.this.mActiveCamera);
                        }
                        CameraStateMachine.this.mActiveCamera.close();
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mQBOffState);
                        break;
                    case CameraStateMachine.MSG_STOP_URGENT /*-4*/:
                    case -3:
                        if (cameraOperation != CameraStateMachine.this.mActiveCamera) {
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        } else {
                            cameraOperation.stopRecord();
                            break;
                        }
                    case -2:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            if (!cameraOperation.getBackcarState()) {
                                cameraOperation.stopPreview();
                                break;
                            } else {
                                cameraOperation.close();
                                CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosingState);
                                break;
                            }
                        } else {
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                    case 2:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            if (!cameraOperation.getBackcarState() && CameraStateMachine.this.hasMessages(2)) {
                                CameraStateMachine.this.deferMessage(msg);
                                break;
                            } else {
                                cameraOperation.startPreview();
                                break;
                            }
                        } else {
                            if (cameraOperation.getBackcarState()) {
                                CameraStateMachine.this.mActiveCamera.close();
                                CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosingState);
                            }
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                        break;
                    case 3:
                    case 4:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            if (!cameraOperation.getBackcarState() && CameraStateMachine.this.hasMessages(2)) {
                                CameraStateMachine.this.deferMessage(msg);
                                break;
                            } else {
                                cameraOperation.startRecord(msg.what != 3);
                                break;
                            }
                        } else {
                            if (cameraOperation.getBackcarState()) {
                                CameraStateMachine.this.mActiveCamera.close();
                                CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosingState);
                            }
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                        break;
                    case 5:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            cameraOperation.snapshot();
                            break;
                        } else {
                            if (cameraOperation.getBackcarState()) {
                                CameraStateMachine.this.mActiveCamera.close();
                                CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosingState);
                            }
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                        CameraStateMachine.this.handleSetParamMessage(cameraOperation, msg);
                        break;
                    case 12:
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mPreviewingState);
                        break;
                    case 13:
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mRecordingState);
                        break;
                    default:
                        LogUtil.d(CameraStateMachine.TAG, "illegal mesage " + msg.what + " appear in " + getClass().getSimpleName(), new Object[0]);
                        break;
                }
            }
            return true;
        }
    }

    private class ClosingState extends State {
        private ClosingState() {
        }

        /* synthetic */ ClosingState(CameraStateMachine cameraStateMachine, ClosingState closingState) {
            this();
        }

        public void enter() {
            LogUtil.d(CameraStateMachine.TAG, "enter ClosingState", new Object[0]);
        }

        public boolean processMessage(Message msg) {
            Log.d(CameraStateMachine.TAG, String.valueOf(getClass().getSimpleName()) + " processMessage " + msg.what);
            CameraOperation cameraOperation = (CameraOperation) msg.obj;
            Log.d(CameraStateMachine.TAG, "camera id is " + (cameraOperation != null ? cameraOperation.getCameraId() : DVRConst.UNKOWN_CAMERA_ID) + ", mActiveCameraId = " + (CameraStateMachine.this.mActiveCamera != null ? CameraStateMachine.this.mActiveCamera.getCameraId() : DVRConst.UNKOWN_CAMERA_ID));
            if (!CameraStateMachine.this.hasTheOppositeMessage(msg.what, cameraOperation) && !CameraStateMachine.this.hasTheSameMessage(msg.what, cameraOperation)) {
                switch (msg.what) {
                    case CameraStateMachine.MSG_QB_OFF /*-16*/:
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mQBOffState);
                        break;
                    case 2:
                    case 3:
                    case 5:
                        CameraStateMachine.this.deferMessage(msg);
                        break;
                    case 7:
                        CameraStateMachine.this.mActiveCamera = null;
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosedState);
                        break;
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                        CameraStateMachine.this.handleSetParamMessage(cameraOperation, msg);
                        break;
                    default:
                        LogUtil.d(CameraStateMachine.TAG, "illegal mesage " + msg.what + " appear in " + getClass().getSimpleName(), new Object[0]);
                        break;
                }
            }
            return true;
        }
    }

    private class RecordingState extends State {
        private RecordingState() {
        }

        /* synthetic */ RecordingState(CameraStateMachine cameraStateMachine, RecordingState recordingState) {
            this();
        }

        public void enter() {
            LogUtil.d(CameraStateMachine.TAG, "enter RecordingState", new Object[0]);
        }

        public boolean processMessage(Message msg) {
            Log.d(CameraStateMachine.TAG, String.valueOf(getClass().getSimpleName()) + " processMessage " + msg.what);
            CameraOperation cameraOperation = (CameraOperation) msg.obj;
            Log.d(CameraStateMachine.TAG, "camera id is " + (cameraOperation != null ? cameraOperation.getCameraId() : DVRConst.UNKOWN_CAMERA_ID) + ", mActiveCameraId = " + (CameraStateMachine.this.mActiveCamera != null ? CameraStateMachine.this.mActiveCamera.getCameraId() : DVRConst.UNKOWN_CAMERA_ID));
            if (!CameraStateMachine.this.hasTheOppositeMessage(msg.what, cameraOperation) && !CameraStateMachine.this.hasTheSameMessage(msg.what, cameraOperation)) {
                switch (msg.what) {
                    case CameraStateMachine.MSG_MEDIA_MOUNTED /*-17*/:
                        break;
                    case CameraStateMachine.MSG_QB_OFF /*-16*/:
                        if (!CameraStateMachine.this.hasTheOppositeMessage(3, CameraStateMachine.this.mActiveCamera)) {
                            CameraStateMachine.this.sendMessage(3, (Object) CameraStateMachine.this.mActiveCamera);
                        }
                        if (CameraStateMachine.this.mActiveCamera.isPreviewRequest() && !CameraStateMachine.this.hasTheOppositeMessage(2, CameraStateMachine.this.mActiveCamera)) {
                            CameraStateMachine.this.sendMessage(2, (Object) CameraStateMachine.this.mActiveCamera);
                        }
                        CameraStateMachine.this.mActiveCamera.close();
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mQBOffState);
                        break;
                    case -3:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            cameraOperation.stopRecord();
                            break;
                        } else {
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                    case -2:
                        CameraStateMachine.this.deferMessage(msg);
                        break;
                    case 2:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            cameraOperation.startPreview();
                            break;
                        } else {
                            if (cameraOperation.getBackcarState()) {
                                CameraOperation activeCamera = CameraStateMachine.this.mActiveCamera;
                                activeCamera.stopRecord();
                                if (!CameraStateMachine.this.hasTheOppositeMessage(2, activeCamera)) {
                                    CameraStateMachine.this.sendMessage(CameraStateMachine.this.obtainMessage(2, (Object) activeCamera));
                                }
                                activeCamera.close();
                                CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosingState);
                            }
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                    case 3:
                        if (cameraOperation != CameraStateMachine.this.mActiveCamera) {
                            if (cameraOperation.getBackcarState()) {
                                CameraOperation activeCamera2 = CameraStateMachine.this.mActiveCamera;
                                activeCamera2.stopRecord();
                                if (!CameraStateMachine.this.hasTheOppositeMessage(3, activeCamera2)) {
                                    CameraStateMachine.this.sendMessage(CameraStateMachine.this.obtainMessage(3, (Object) activeCamera2));
                                }
                                activeCamera2.close();
                                CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosingState);
                            }
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                        break;
                    case 5:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            cameraOperation.snapshot();
                            break;
                        } else {
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                        CameraStateMachine.this.handleSetParamMessage(cameraOperation, msg);
                        break;
                    case 12:
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mPreviewingAndRecordingState);
                        break;
                    case 15:
                        cameraOperation.close();
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosingState);
                        break;
                    case 17:
                        if (!CameraStateMachine.this.mActiveCamera.isRecordRequest()) {
                            CameraStateMachine.this.mIsRecordingBeforeMediaEjected = false;
                            break;
                        } else {
                            CameraStateMachine.this.mIsRecordingBeforeMediaEjected = true;
                            CameraStateMachine.this.mActiveCamera.stopRecord();
                            break;
                        }
                    default:
                        LogUtil.d(CameraStateMachine.TAG, "illegal mesage " + msg.what + " appear in " + getClass().getSimpleName(), new Object[0]);
                        break;
                }
            }
            return true;
        }
    }

    private class PreviewingState extends State {
        private PreviewingState() {
        }

        /* synthetic */ PreviewingState(CameraStateMachine cameraStateMachine, PreviewingState previewingState) {
            this();
        }

        public void enter() {
            Log.d(CameraStateMachine.TAG, "enter PreviewingState");
        }

        public boolean processMessage(Message msg) {
            Log.d(CameraStateMachine.TAG, String.valueOf(getClass().getSimpleName()) + " processMessage " + msg.what);
            CameraOperation cameraOperation = (CameraOperation) msg.obj;
            Log.d(CameraStateMachine.TAG, "camera id is " + (cameraOperation != null ? cameraOperation.getCameraId() : DVRConst.UNKOWN_CAMERA_ID) + ", mActiveCameraId = " + (CameraStateMachine.this.mActiveCamera != null ? CameraStateMachine.this.mActiveCamera.getCameraId() : DVRConst.UNKOWN_CAMERA_ID));
            if (!CameraStateMachine.this.hasTheOppositeMessage(msg.what, cameraOperation) && !CameraStateMachine.this.hasTheSameMessage(msg.what, cameraOperation)) {
                switch (msg.what) {
                    case CameraStateMachine.MSG_MEDIA_MOUNTED /*-17*/:
                        if (CameraStateMachine.this.mIsRecordingBeforeMediaEjected) {
                            CameraStateMachine.this.mActiveCamera.startRecord(false);
                        }
                        CameraStateMachine.this.mIsRecordingBeforeMediaEjected = false;
                        break;
                    case CameraStateMachine.MSG_QB_OFF /*-16*/:
                        if (!CameraStateMachine.this.hasTheOppositeMessage(2, CameraStateMachine.this.mActiveCamera)) {
                            CameraStateMachine.this.sendMessage(2, (Object) CameraStateMachine.this.mActiveCamera);
                        }
                        if (CameraStateMachine.this.mActiveCamera.isRecordRequest() && !CameraStateMachine.this.hasTheOppositeMessage(3, CameraStateMachine.this.mActiveCamera)) {
                            CameraStateMachine.this.sendMessage(3, (Object) CameraStateMachine.this.mActiveCamera);
                        }
                        CameraStateMachine.this.mActiveCamera.close();
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mQBOffState);
                        break;
                    case -3:
                        CameraStateMachine.this.deferMessage(msg);
                        break;
                    case -2:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            cameraOperation.stopPreview();
                            break;
                        } else {
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                    case 2:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            cameraOperation.startPreview();
                            break;
                        } else {
                            if (cameraOperation.getBackcarState()) {
                                CameraStateMachine.this.mActiveCamera.stopPreview();
                                if (!CameraStateMachine.this.hasTheOppositeMessage(2, CameraStateMachine.this.mActiveCamera)) {
                                    CameraStateMachine.this.sendMessage(2, (Object) CameraStateMachine.this.mActiveCamera);
                                }
                                if (CameraStateMachine.this.mActiveCamera.isRecordRequest() && !CameraStateMachine.this.hasTheOppositeMessage(3, CameraStateMachine.this.mActiveCamera)) {
                                    CameraStateMachine.this.sendMessage(3, (Object) CameraStateMachine.this.mActiveCamera);
                                }
                                CameraStateMachine.this.mActiveCamera.close();
                                CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosingState);
                            }
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                    case 3:
                    case 4:
                        if (cameraOperation != CameraStateMachine.this.mActiveCamera) {
                            if (!cameraOperation.getBackcarState()) {
                                CameraStateMachine.this.deferMessage(msg);
                                break;
                            } else {
                                CameraStateMachine.this.mActiveCamera.stopPreview();
                                if (!CameraStateMachine.this.hasTheOppositeMessage(msg.what, CameraStateMachine.this.mActiveCamera)) {
                                    CameraStateMachine.this.sendMessage(CameraStateMachine.this.obtainMessage(msg.what, (Object) CameraStateMachine.this.mActiveCamera));
                                }
                                CameraStateMachine.this.mActiveCamera.close();
                                CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosingState);
                                CameraStateMachine.this.deferMessage(msg);
                                break;
                            }
                        } else {
                            cameraOperation.startRecord(msg.what != 3);
                            break;
                        }
                    case 5:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            cameraOperation.snapshot();
                            break;
                        } else {
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                    case 7:
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosedState);
                        break;
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                        CameraStateMachine.this.handleSetParamMessage(cameraOperation, msg);
                        break;
                    case 13:
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mPreviewingAndRecordingState);
                        break;
                    case 14:
                        cameraOperation.close();
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosingState);
                        break;
                    case 17:
                        if (!CameraStateMachine.this.mActiveCamera.isRecordRequest()) {
                            CameraStateMachine.this.mIsRecordingBeforeMediaEjected = false;
                            break;
                        } else {
                            CameraStateMachine.this.mIsRecordingBeforeMediaEjected = true;
                            break;
                        }
                    default:
                        LogUtil.d(CameraStateMachine.TAG, "illegal mesage " + msg.what + " appear in " + getClass().getSimpleName(), new Object[0]);
                        break;
                }
            }
            return true;
        }
    }

    private class PreviewingAndRecordingState extends State {
        private PreviewingAndRecordingState() {
        }

        /* synthetic */ PreviewingAndRecordingState(CameraStateMachine cameraStateMachine, PreviewingAndRecordingState previewingAndRecordingState) {
            this();
        }

        public void enter() {
            LogUtil.d(CameraStateMachine.TAG, "enter PreviewingAndRecordingState", new Object[0]);
        }

        public boolean processMessage(Message msg) {
            Log.d(CameraStateMachine.TAG, String.valueOf(getClass().getSimpleName()) + " processMessage " + msg.what);
            CameraOperation cameraOperation = (CameraOperation) msg.obj;
            Log.d(CameraStateMachine.TAG, "camera id is " + (cameraOperation != null ? cameraOperation.getCameraId() : DVRConst.UNKOWN_CAMERA_ID) + ", mActiveCameraId = " + (CameraStateMachine.this.mActiveCamera != null ? CameraStateMachine.this.mActiveCamera.getCameraId() : DVRConst.UNKOWN_CAMERA_ID));
            if (!CameraStateMachine.this.hasTheOppositeMessage(msg.what, cameraOperation) && !CameraStateMachine.this.hasTheSameMessage(msg.what, cameraOperation)) {
                switch (msg.what) {
                    case CameraStateMachine.MSG_MEDIA_MOUNTED /*-17*/:
                        CameraStateMachine.this.mIsRecordingBeforeMediaEjected = false;
                        break;
                    case CameraStateMachine.MSG_QB_OFF /*-16*/:
                        if (!CameraStateMachine.this.hasTheOppositeMessage(2, CameraStateMachine.this.mActiveCamera)) {
                            CameraStateMachine.this.sendMessage(2, (Object) CameraStateMachine.this.mActiveCamera);
                        }
                        if (!CameraStateMachine.this.hasTheOppositeMessage(3, CameraStateMachine.this.mActiveCamera)) {
                            CameraStateMachine.this.sendMessage(3, (Object) CameraStateMachine.this.mActiveCamera);
                        }
                        CameraStateMachine.this.mActiveCamera.close();
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mQBOffState);
                        break;
                    case -3:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            cameraOperation.stopRecord();
                            break;
                        } else {
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                    case -2:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            cameraOperation.stopPreview();
                            break;
                        } else {
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                    case 2:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            cameraOperation.startPreview();
                            break;
                        }
                    case 3:
                        if (cameraOperation != CameraStateMachine.this.mActiveCamera) {
                            if (cameraOperation.getBackcarState()) {
                                CameraStateMachine.this.mActiveCamera.stopPreview();
                                if (!CameraStateMachine.this.hasTheOppositeMessage(2, CameraStateMachine.this.mActiveCamera)) {
                                    CameraStateMachine.this.sendMessage(2, (Object) CameraStateMachine.this.mActiveCamera);
                                }
                                CameraStateMachine.this.mActiveCamera.stopRecord();
                                if (!CameraStateMachine.this.hasTheOppositeMessage(3, CameraStateMachine.this.mActiveCamera)) {
                                    CameraStateMachine.this.sendMessage(3, (Object) CameraStateMachine.this.mActiveCamera);
                                }
                                CameraStateMachine.this.mActiveCamera.close();
                                CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosingState);
                            }
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                        break;
                    case 4:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            cameraOperation.startRecord(true);
                            break;
                        } else {
                            if (cameraOperation.getBackcarState()) {
                                CameraStateMachine.this.mActiveCamera.stopPreview();
                                if (!CameraStateMachine.this.hasTheOppositeMessage(2, CameraStateMachine.this.mActiveCamera)) {
                                    CameraStateMachine.this.sendMessage(2, (Object) CameraStateMachine.this.mActiveCamera);
                                }
                                CameraStateMachine.this.mActiveCamera.stopRecord();
                                if (!CameraStateMachine.this.hasTheOppositeMessage(3, CameraStateMachine.this.mActiveCamera)) {
                                    CameraStateMachine.this.sendMessage(3, (Object) CameraStateMachine.this.mActiveCamera);
                                }
                                CameraStateMachine.this.mActiveCamera.close();
                                CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosingState);
                            }
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                    case 5:
                        if (cameraOperation == CameraStateMachine.this.mActiveCamera) {
                            cameraOperation.snapshot();
                            break;
                        } else {
                            CameraStateMachine.this.deferMessage(msg);
                            break;
                        }
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                        CameraStateMachine.this.handleSetParamMessage(cameraOperation, msg);
                        break;
                    case 14:
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mRecordingState);
                        break;
                    case 15:
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mPreviewingState);
                        break;
                    case 17:
                        if (!CameraStateMachine.this.mActiveCamera.isRecordRequest()) {
                            CameraStateMachine.this.mIsRecordingBeforeMediaEjected = false;
                            break;
                        } else {
                            CameraStateMachine.this.mIsRecordingBeforeMediaEjected = true;
                            CameraStateMachine.this.mActiveCamera.stopRecord();
                            break;
                        }
                    default:
                        LogUtil.d(CameraStateMachine.TAG, "illegal mesage " + msg.what + " appear in " + getClass().getSimpleName(), new Object[0]);
                        break;
                }
            }
            return true;
        }
    }

    private class QBOffState extends State {
        boolean mIsCameraClosed;
        boolean mIsQbOn;

        private QBOffState() {
        }

        /* synthetic */ QBOffState(CameraStateMachine cameraStateMachine, QBOffState qBOffState) {
            this();
        }

        public void enter() {
            LogUtil.d(CameraStateMachine.TAG, "enter QBOffState", new Object[0]);
        }

        public boolean processMessage(Message msg) {
            LogUtil.d(CameraStateMachine.TAG, String.valueOf(getClass().getSimpleName()) + " processMessage " + msg.what, new Object[0]);
            CameraOperation cameraOperation = (CameraOperation) msg.obj;
            LogUtil.d(CameraStateMachine.TAG, "camera id is " + (cameraOperation != null ? cameraOperation.getCameraId() : DVRConst.UNKOWN_CAMERA_ID) + ", mActiveCameraId = " + (CameraStateMachine.this.mActiveCamera != null ? CameraStateMachine.this.mActiveCamera.getCameraId() : DVRConst.UNKOWN_CAMERA_ID), new Object[0]);
            switch (msg.what) {
                case CameraStateMachine.MSG_MEDIA_MOUNTED /*-17*/:
                case 14:
                case 15:
                case 17:
                    break;
                case CameraStateMachine.MSG_QB_OFF /*-16*/:
                    this.mIsQbOn = false;
                    break;
                case -2:
                    if (!cameraOperation.hasPendingPreviewSurface()) {
                        CameraStateMachine.this.removeTheSameMessages(2, cameraOperation);
                    }
                    cameraOperation.stopPreviewWhileQbOff();
                    break;
                case 7:
                    if (!this.mIsQbOn) {
                        this.mIsCameraClosed = true;
                        break;
                    } else {
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosedState);
                        this.mIsCameraClosed = false;
                        this.mIsQbOn = false;
                        break;
                    }
                case 12:
                    CameraStateMachine.this.sendMessage(2, (Object) cameraOperation);
                    break;
                case 13:
                    CameraStateMachine.this.sendMessage(3, (Object) cameraOperation);
                    break;
                case 16:
                    if (!this.mIsCameraClosed) {
                        this.mIsQbOn = true;
                        break;
                    } else {
                        CameraStateMachine.this.transitionTo(CameraStateMachine.this.mClosedState);
                        this.mIsQbOn = false;
                        this.mIsCameraClosed = false;
                        break;
                    }
                default:
                    CameraStateMachine.this.deferMessage(msg);
                    break;
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public CameraOperation getCameraOperation(String id) {
        Iterator<CameraOperation> it = this.mCameraOperations.iterator();
        while (it.hasNext()) {
            CameraOperation operation = it.next();
            if (operation.getCameraId().equals(id)) {
                return operation;
            }
        }
        CameraOperation op = new CameraOperation(this.mContext, id);
        op.registerCameraOperationCallback(this.mOperationCallback);
        this.mCameraOperations.add(op);
        return op;
    }

    /* access modifiers changed from: private */
    public void removeTheSameMessages(int what, CameraOperation cameraOperation) {
        if (hasMessages(what, cameraOperation)) {
            removeMessages(what, cameraOperation);
        }
        if (hasDeferredMessages(what, cameraOperation)) {
            removeDeferredMessages(what, cameraOperation);
        }
    }

    private void removeTheOppositeMessages(int what, CameraOperation cameraOperation) {
        if (hasMessages(-what, cameraOperation)) {
            removeMessages(-what, cameraOperation);
        }
        if (hasDeferredMessages(-what, cameraOperation)) {
            removeDeferredMessages(-what, cameraOperation);
        }
    }

    /* access modifiers changed from: private */
    public boolean hasTheOppositeMessage(int what, CameraOperation cameraOperation) {
        return hasTheSameMessage(-what, cameraOperation);
    }

    /* access modifiers changed from: private */
    public boolean hasTheSameMessage(int what, CameraOperation cameraOperation) {
        if (hasMessages(what, cameraOperation) || hasDeferredMessages(what, cameraOperation)) {
            return true;
        }
        return false;
    }

    public void openCamera(String id) {
        LogUtil.d(TAG, "startPreview id:" + id, new Object[0]);
        sendMessage(1, (Object) getCameraOperation(id));
    }

    public void startPreview(String id, Surface surface) {
        LogUtil.d(TAG, "startPreview id:" + id, new Object[0]);
        CameraOperation cameraOperation = getCameraOperation(id);
        cameraOperation.setPreviewSurface(surface);
        sendMessage(2, (Object) cameraOperation);
    }

    public void stopPreview(String id) {
        LogUtil.d(TAG, "stopPreview id:" + id, new Object[0]);
        sendMessage(-2, (Object) getCameraOperation(id));
    }

    public void startRecord(String id, boolean isUrgent) {
        LogUtil.d(TAG, "startRecord id:" + id + " isUrgent:" + isUrgent, new Object[0]);
        sendMessage(isUrgent ? 4 : 3, (Object) getCameraOperation(id));
    }

    public void stopRecord(String id) {
        LogUtil.d(TAG, "stopRecord id:" + id, new Object[0]);
        sendMessage(-3, (Object) getCameraOperation(id));
    }

    public void snapshot(String id) {
        LogUtil.d(TAG, "snapshot id:" + id, new Object[0]);
        sendMessage(5, (Object) getCameraOperation(id));
    }

    public void setResolution(String id, int width, int height) {
        LogUtil.d(TAG, "setResolution id:" + id, new Object[0]);
        sendMessage(8, width, height, getCameraOperation(id));
    }

    public void setDuration(String id, long duration) {
        LogUtil.d(TAG, "setDuration id:" + id, new Object[0]);
        sendMessage(9, (int) duration, 0, getCameraOperation(id));
    }

    public void setMic(String id, boolean isOn) {
        int i;
        LogUtil.d(TAG, "setMic id:" + id, new Object[0]);
        CameraOperation cameraOperation = getCameraOperation(id);
        if (isOn) {
            i = 1;
        } else {
            i = 0;
        }
        sendMessage(10, i, 0, cameraOperation);
    }

    public void setWaterMark(String id, boolean isOn) {
        int i;
        LogUtil.d(TAG, "setWaterMark id:" + id, new Object[0]);
        CameraOperation cameraOperation = getCameraOperation(id);
        if (isOn) {
            i = 1;
        } else {
            i = 0;
        }
        sendMessage(11, i, 0, cameraOperation);
    }

    public boolean getPreviewState(String id) {
        return getCameraOperation(id).isPreviewing();
    }

    public boolean getRecordingState(String id, boolean isUrgent) {
        return getCameraOperation(id).isRecording(isUrgent);
    }

    public Size getResolution(String id) {
        return getCameraOperation(id).getResolution();
    }

    public boolean getMicState(String id) {
        return getCameraOperation(id).getMicState();
    }

    public boolean getWatermarkState(String id) {
        return getCameraOperation(id).getWatermarkState();
    }

    public long getDuration(String id) {
        return getCameraOperation(id).getDuration();
    }

    public void registerCallback(StateMachineCallback callback) {
        this.mCallback = callback;
    }

    public void unregisterCallback(StateMachineCallback callback) {
        this.mCallback = null;
    }
}
