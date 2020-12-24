package com.txznet.sdk;

import com.txznet.comm.Tr.Tn;
import com.txznet.sdk.TXZService;
import org.json.JSONObject;

/* compiled from: Proguard */
public class TXZCameraManager {
    public static final int CAMERA_BACK = 2;
    public static final int CAMERA_FRONT = 1;
    public static final int CAPTURE_ERROR_IO_ERROR = 7104;
    public static final int CAPTURE_ERROR_NOT_FOUND = 7105;
    public static final int CAPTURE_ERROR_NO_CAMERA = 7102;
    public static final int CAPTURE_ERROR_NO_SUPPORT = 7103;
    public static final int CAPTURE_ERROR_UNKNOW = 7101;
    public static final String REMOTE_NAME_CAMERA_POSITION = "position";
    public static final String REMOTE_NAME_ERROR_CODE = "errCode";
    public static final String REMOTE_NAME_ERROR_MESSAGE = "errMessage";
    public static final String REMOTE_NAME_TASK_ID = "taskId";
    public static final String REMOTE_NAME_VIDEO_PATH = "path";
    public static final String REMOTE_NAME_VIDEO_THUMBNAIL_PATH = "thumbnail";
    private static TXZCameraManager Ty = new TXZCameraManager();

    /* renamed from: T  reason: collision with root package name */
    private Boolean f712T;
    /* access modifiers changed from: private */
    public CameraTool T9 = null;
    private boolean Tn = false;
    private Boolean Tr;
    public Long mCaptureVideoTimeout = null;
    public CameraType mSupportCameraType = null;
    public Long mTimeout = null;

    /* compiled from: Proguard */
    public interface CameraTool {
        boolean capturePicure(long j, CapturePictureListener capturePictureListener);

        boolean captureVideo(CaptureVideoListener captureVideoListener, CaptureVideoListener captureVideoListener2);
    }

    /* compiled from: Proguard */
    public enum CameraType {
        SINGLE_CAMERA,
        DUAL_CAMERA
    }

    /* compiled from: Proguard */
    public interface CapturePictureListener {
        void onBackPicture(String str);

        void onError(int i, String str);

        void onFrontPicture(String str);

        void onSave(String str);
    }

    /* compiled from: Proguard */
    public interface CaptureVideoListener {
        void onError(int i, String str);

        void onSave(String str, String str2);
    }

    private TXZCameraManager() {
    }

    public static TXZCameraManager getInstance() {
        return Ty;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (this.Tn) {
            setCameraTool(this.T9);
        }
        if (this.f712T != null) {
            useWakeupCapturePhoto(this.f712T.booleanValue());
        }
        if (this.Tr != null) {
            useWakeupCaptureVideo(this.Tr.booleanValue());
        }
        if (this.mTimeout != null) {
            setCapturePhotoTimeout(this.mTimeout.longValue());
        }
        if (this.mCaptureVideoTimeout != null) {
            setCaptureVideoTimeout(this.mCaptureVideoTimeout.longValue());
        }
        if (this.mSupportCameraType != null) {
            setSupportCameraType(this.mSupportCameraType);
        }
    }

    public void setCameraTool(CameraTool tool) {
        this.Tn = true;
        this.T9 = tool;
        if (tool == null) {
            Tn.Tr().T("com.txznet.txz", "txz.camera.cleartool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        Tn.Tr().T("com.txznet.txz", "txz.camera.settool", (byte[]) null, (Tn.Tr) null);
        TXZService.T("tool.camera.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (command.equals("capturePicture")) {
                    try {
                        final JSONObject json = new JSONObject(new String(data));
                        TXZCameraManager.this.T9.capturePicure(json.getLong("time"), new CapturePictureListener() {
                            public void onSave(String path) {
                                try {
                                    json.put(TXZCameraManager.REMOTE_NAME_VIDEO_PATH, path);
                                    Tn.Tr().T("com.txznet.txz", "txz.camera.capturePicture.onSave", json.toString().getBytes(), (Tn.Tr) null);
                                } catch (Exception e) {
                                }
                            }

                            public void onError(int errCode, String errDesc) {
                                if (errCode < 7101 || errCode > 7105) {
                                    errCode = TXZCameraManager.CAPTURE_ERROR_UNKNOW;
                                }
                                try {
                                    json.put(TXZCameraManager.REMOTE_NAME_ERROR_CODE, errCode);
                                    json.put("errDesc", errDesc);
                                    Tn.Tr().T("com.txznet.txz", "txz.camera.capturePicture.onError", json.toString().getBytes(), (Tn.Tr) null);
                                } catch (Exception e) {
                                }
                            }

                            public void onFrontPicture(String path) {
                                try {
                                    json.put(TXZCameraManager.REMOTE_NAME_VIDEO_PATH, path);
                                    json.put(TXZCameraManager.REMOTE_NAME_CAMERA_POSITION, 1);
                                    Tn.Tr().T("com.txznet.txz", "txz.camera.capturePicture.onSave", json.toString().getBytes(), (Tn.Tr) null);
                                } catch (Exception e) {
                                }
                            }

                            public void onBackPicture(String path) {
                                try {
                                    json.put(TXZCameraManager.REMOTE_NAME_VIDEO_PATH, path);
                                    json.put(TXZCameraManager.REMOTE_NAME_CAMERA_POSITION, 2);
                                    Tn.Tr().T("com.txznet.txz", "txz.camera.capturePicture.onSave", json.toString().getBytes(), (Tn.Tr) null);
                                } catch (Exception e) {
                                }
                            }
                        });
                    } catch (Exception e) {
                    }
                }
                if (!command.equals("captureVideo")) {
                    return null;
                }
                try {
                    final long taskId = new JSONObject(new String(data)).getLong(TXZCameraManager.REMOTE_NAME_TASK_ID);
                    TXZCameraManager.this.T9.captureVideo(new CaptureVideoListener() {
                        public void onSave(String path, String thumbnailPath) {
                            TXZCameraManager.this.T(taskId, 1, path, thumbnailPath);
                        }

                        public void onError(int errCode, String errDesc) {
                            if (errCode < 7101 || errCode > 7105) {
                                errCode = TXZCameraManager.CAPTURE_ERROR_UNKNOW;
                            }
                            TXZCameraManager.this.T(taskId, 1, errCode, errDesc);
                        }
                    }, new CaptureVideoListener() {
                        public void onSave(String path, String thumbnailPath) {
                            TXZCameraManager.this.T(taskId, 2, path, thumbnailPath);
                        }

                        public void onError(int errCode, String errDesc) {
                            if (errCode < 7101 || errCode > 7105) {
                                errCode = TXZCameraManager.CAPTURE_ERROR_UNKNOW;
                            }
                            TXZCameraManager.this.T(taskId, 2, errCode, errDesc);
                        }
                    });
                    return null;
                } catch (Exception e2) {
                    return null;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void T(long taskId, int position, String videoPath, String thumbnailPath) {
        try {
            JSONObject json = new JSONObject();
            json.put(REMOTE_NAME_TASK_ID, taskId);
            json.put(REMOTE_NAME_CAMERA_POSITION, position);
            json.put(REMOTE_NAME_VIDEO_PATH, videoPath);
            json.put(REMOTE_NAME_VIDEO_THUMBNAIL_PATH, thumbnailPath);
            Tn.Tr().T("com.txznet.txz", "txz.camera.captureVideo.onSave", json.toString().getBytes(), (Tn.Tr) null);
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: private */
    public void T(long taskId, int position, int errCode, String errDesc) {
        try {
            JSONObject json = new JSONObject();
            json.put(REMOTE_NAME_TASK_ID, taskId);
            json.put(REMOTE_NAME_ERROR_CODE, errCode);
            json.put(REMOTE_NAME_ERROR_MESSAGE, errDesc);
            json.put(REMOTE_NAME_CAMERA_POSITION, position);
            Tn.Tr().T("com.txznet.txz", "txz.camera.captureVideo.onError", json.toString().getBytes(), (Tn.Tr) null);
        } catch (Exception e) {
        }
    }

    public void useWakeupCapturePhoto(boolean enable) {
        this.f712T = Boolean.valueOf(enable);
        Tn.Tr().T("com.txznet.txz", "txz.camera.useWakeupCapturePhoto", String.valueOf(enable).getBytes(), (Tn.Tr) null);
    }

    public void useWakeupCaptureVideo(boolean enable) {
        this.Tr = Boolean.valueOf(enable);
        Tn.Tr().T("com.txznet.txz", "txz.camera.useWakeupCaptureVideo", String.valueOf(enable).getBytes(), (Tn.Tr) null);
    }

    public void capturePhoto() {
        Tn.Tr().T("com.txznet.txz", "txz.camera.capturePhoto", (byte[]) null, (Tn.Tr) null);
    }

    public void setCapturePhotoTimeout(long timeout) {
        this.mTimeout = Long.valueOf(timeout);
        Tn.Tr().T("com.txznet.txz", "txz.camera.setCameraTimeout", ("" + this.mTimeout).getBytes(), (Tn.Tr) null);
    }

    public void setCaptureVideoTimeout(long timeout) {
        this.mCaptureVideoTimeout = Long.valueOf(timeout);
        Tn.Tr().T("com.txznet.txz", "txz.camera.setCaptureVideoTimeout", ("" + this.mCaptureVideoTimeout).getBytes(), (Tn.Tr) null);
    }

    public void setSupportCameraType(CameraType cameraType) {
        if (cameraType != null) {
            this.mSupportCameraType = cameraType;
            int camera = 0;
            switch (cameraType) {
                case SINGLE_CAMERA:
                    camera = 1;
                    break;
                case DUAL_CAMERA:
                    camera = 2;
                    break;
            }
            if (camera != 0) {
                Tn.Tr().T("com.txznet.txz", "txz.camera.setSupportCameraType", ("" + camera).getBytes(), (Tn.Tr) null);
            }
        }
    }
}
