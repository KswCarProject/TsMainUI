package com.ts.backcar;

import android.hardware.Camera;
import android.util.Log;
import android.util.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CameraPreview {
    private static final boolean AUTOFOUCUS = true;
    private static final String TAG = "CameraPreview";
    private Camera camera = null;
    private boolean isPreviewed = false;
    private List<Camera.Size> mPreviewSizes = null;
    private Camera.Parameters parameters = null;
    private int previewHeight = 480;
    private int previewWidth = 720;
    private SurfaceHolder surfaceHolder = null;
    private SurfaceView surfaceView = null;

    public CameraPreview() {
        Log.d(TAG, "CameraPreview construct entry");
        Log.d(TAG, "CameraPreview construct end");
    }

    public boolean initCamera() {
        Log.d(TAG, "initCamera() entry");
        try {
            int cameraCount = Camera.getNumberOfCameras();
            Log.d(TAG, "[initCamera]The facing of the camera is same to the screen cameraCount" + cameraCount);
            if (cameraCount == 0) {
                this.camera = Camera.open(1);
            } else {
                this.camera = Camera.open(1);
            }
            this.parameters = this.camera.getParameters();
            this.camera.setParameters(getCusParameters());
            this.camera.setErrorCallback(new CameraErrorCallback());
            if (this.surfaceHolder != null) {
                setPreviewDisplay(this.surfaceHolder);
            }
        } catch (Exception e) {
            Log.i(TAG, "InitCamera fail!");
        }
        return true;
    }

    public void deInitCamera() {
        Log.d(TAG, "deInitCamera entry");
        if (this.camera != null) {
            Log.d(TAG, "camera not null, deinit camera");
            this.camera.setErrorCallback((Camera.ErrorCallback) null);
            this.camera.setPreviewCallback((Camera.PreviewCallback) null);
            this.camera.release();
            this.camera = null;
        }
        this.isPreviewed = false;
        Log.d(TAG, "deInitCamera end");
    }

    public void setPreviewDisplay(SurfaceHolder surfaceHolder2) {
        Log.d(TAG, "setPreviewDisplay()");
        this.surfaceHolder = surfaceHolder2;
        if (this.camera != null) {
            Log.d(TAG, "camera not null, set previewDisplay to camera");
            try {
                this.camera.setPreviewDisplay(surfaceHolder2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void startPreview() {
        Log.d(TAG, "startPreview() entry");
        if (this.camera != null) {
            try {
                this.camera.setPreviewDisplay(this.surfaceHolder);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!this.isPreviewed) {
                this.camera.startPreview();
                this.isPreviewed = true;
                this.camera.cancelAutoFocus();
            } else {
                Log.d(TAG, "Camera is previewing");
            }
        }
        Log.d(TAG, "startPreview() end");
    }

    public void stopPreview() {
        Log.d(TAG, "stopPreview() entry");
        if (this.camera != null) {
            if (this.isPreviewed) {
                this.camera.stopPreview();
                this.isPreviewed = false;
            } else {
                Log.d(TAG, "Camera is not preview");
            }
        }
        Log.d(TAG, "stopPreview() end");
    }

    public Camera.Parameters getCusParameters() {
        Log.d(TAG, "getCusParameters() entry, get custom parameters");
        Camera.Parameters cp = this.parameters;
        if (cp == null) {
            return null;
        }
        this.mPreviewSizes = cp.getSupportedPreviewSizes();
        Collections.sort(this.mPreviewSizes, new CameraSizeComparator());
        for (int i = 0; i < this.mPreviewSizes.size(); i++) {
            Log.d(TAG, String.valueOf(i + 1) + ". Camera.Preview size width = " + this.mPreviewSizes.get(i).width + ", height = " + this.mPreviewSizes.get(i).height);
        }
        if (!checkPreviewSizeValid(this.previewWidth, this.previewHeight)) {
            this.previewWidth = this.mPreviewSizes.get(0).width;
            this.previewHeight = this.mPreviewSizes.get(0).height;
        }
        cp.setPreviewSize(this.previewWidth, this.previewHeight);
        cp.setFocusMode("continuous-picture");
        return cp;
    }

    public class CameraSizeComparator implements Comparator<Camera.Size> {
        public CameraSizeComparator() {
        }

        public int compare(Camera.Size lhs, Camera.Size rhs) {
            if (lhs.width == rhs.width) {
                return 0;
            }
            if (lhs.width < rhs.width) {
                return 1;
            }
            return -1;
        }
    }

    public boolean checkPreviewSizeValid(int x, int y) {
        Log.d(TAG, "checkPreviewSizeValid() entry");
        if (this.camera == null) {
            return false;
        }
        if (this.mPreviewSizes == null || this.mPreviewSizes.size() == 0) {
            return false;
        }
        int n = this.mPreviewSizes.size();
        for (int i = 0; i < n; i++) {
            if (this.mPreviewSizes.get(i).width == x && this.mPreviewSizes.get(i).height == y) {
                return true;
            }
        }
        return false;
    }

    public Size getPreviewSize() {
        Log.d(TAG, "getPreviewSize() entry");
        if (this.previewWidth == 0 || this.previewHeight == 0) {
            return null;
        }
        return new Size(this.previewWidth, this.previewHeight);
    }

    public Camera.Size getPreviewSuitableSize() {
        Log.d(TAG, "getPreviewSuitableSize() entry");
        if (this.camera == null || this.mPreviewSizes == null || this.mPreviewSizes.size() == 0) {
            return null;
        }
        Collections.sort(this.mPreviewSizes, new CameraSizeComparator());
        return this.mPreviewSizes.get(0);
    }

    public class CameraErrorCallback implements Camera.ErrorCallback {
        private static final String TAG = "CameraErrorCallback";

        public CameraErrorCallback() {
        }

        public void onError(int error, Camera camera) {
            Log.e(TAG, "onError got camera error callback. error = " + error);
            CameraPreview.this.stopPreview();
        }
    }
}
