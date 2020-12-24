package com.ts.backcar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AutoFitTextureView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "CameraSurfaceView";
    private CameraPreview mCameraPreview = null;
    private int mPreviewHeight = 0;
    private int mPreviewWidth = 0;
    private SurfaceHolder surfaceHolder;

    public AutoFitTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        getHolder().setType(3);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated start");
        this.surfaceHolder = holder;
        try {
            this.mCameraPreview.setPreviewDisplay(this.surfaceHolder);
            this.mCameraPreview.startPreview();
        } catch (Exception e) {
            Log.d(TAG, "startPreview fail!");
        }
        Log.i(TAG, "surfaceCreated end");
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        Log.d(TAG, "surfaceChanged start, w is " + w + ", h is " + h);
        this.surfaceHolder = holder;
        try {
            this.mCameraPreview.setPreviewDisplay(this.surfaceHolder);
        } catch (Exception e) {
            Log.d(TAG, "open  camera setPreviewDisplay fail!");
        }
        Log.d(TAG, "surfaceChanged end");
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestoryed start");
        this.surfaceHolder = null;
        try {
            this.mCameraPreview.setPreviewDisplay(this.surfaceHolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "surfaceDestroyed end");
    }

    public void setCameraPreview(CameraPreview cameraPreview) {
        this.mCameraPreview = cameraPreview;
    }
}
