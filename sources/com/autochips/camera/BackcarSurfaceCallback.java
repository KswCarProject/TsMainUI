package com.autochips.camera;

import android.graphics.SurfaceTexture;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import com.autochips.camera.util.LogUtil;

public class BackcarSurfaceCallback implements TextureView.SurfaceTextureListener {
    private static final String TAG = "BackcarSurfaceCallback";
    private String mCameraId;
    private Size mFixedResolution;
    private CameraServiceImpl mService;

    public BackcarSurfaceCallback(String cameraID, CameraServiceImpl service, Size resolution) {
        this.mCameraId = cameraID;
        this.mService = service;
        this.mFixedResolution = resolution;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        LogUtil.d(TAG, "surfaceCreated, surface = , width = " + width + ", height = " + height, new Object[0]);
        surfaceTexture.setDefaultBufferSize(this.mFixedResolution.getWidth(), this.mFixedResolution.getHeight());
        this.mService.startPreview(this.mCameraId, new Surface(surfaceTexture));
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
        LogUtil.d(TAG, "surfaceChanged, width=" + width + ", height=" + height, new Object[0]);
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        LogUtil.d(TAG, "surfaceDestroyed", new Object[0]);
        return true;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof BackcarSurfaceCallback) {
            return this.mCameraId.equals(((BackcarSurfaceCallback) obj).mCameraId);
        }
        return false;
    }
}
