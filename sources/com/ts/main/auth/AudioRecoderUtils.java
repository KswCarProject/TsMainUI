package com.ts.main.auth;

import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import java.io.File;
import java.io.IOException;

public class AudioRecoderUtils {
    public static final int MAX_LENGTH = 600000;
    private int BASE;
    private String FolderPath;
    private int SPACE;
    private final String TAG;
    private OnAudioStatusUpdateListener audioStatusUpdateListener;
    private long endTime;
    private String filePath;
    private final Handler mHandler;
    private MediaRecorder mMediaRecorder;
    private Runnable mUpdateMicStatusTimer;
    private long startTime;

    public interface OnAudioStatusUpdateListener {
        void onStop(String str);

        void onUpdate(double d, long j);
    }

    public AudioRecoderUtils() {
        this(Environment.getExternalStorageDirectory() + "/record/");
    }

    public AudioRecoderUtils(String filePath2) {
        this.TAG = "fan";
        this.mHandler = new Handler();
        this.mUpdateMicStatusTimer = new Runnable() {
            public void run() {
                AudioRecoderUtils.this.updateMicStatus();
            }
        };
        this.BASE = 1;
        this.SPACE = 100;
        File path = new File(filePath2);
        if (!path.exists()) {
            path.mkdirs();
        }
        this.FolderPath = filePath2;
    }

    public void startRecord() {
        if (this.mMediaRecorder == null) {
            this.mMediaRecorder = new MediaRecorder();
        }
        try {
            this.mMediaRecorder.setAudioSource(1);
            this.mMediaRecorder.setOutputFormat(0);
            this.mMediaRecorder.setAudioEncoder(1);
            this.filePath = String.valueOf(this.FolderPath) + "record" + ".amr";
            this.mMediaRecorder.setOutputFile(this.filePath);
            this.mMediaRecorder.setMaxDuration(MAX_LENGTH);
            this.mMediaRecorder.prepare();
            this.mMediaRecorder.start();
            this.startTime = System.currentTimeMillis();
            updateMicStatus();
            Log.e("fan", "startTime" + this.startTime);
        } catch (IllegalStateException e) {
            Log.i("fan", "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
        } catch (IOException e2) {
            Log.i("fan", "call startAmr(File mRecAudioFile) failed!" + e2.getMessage());
        }
    }

    public long stopRecord() {
        if (this.mMediaRecorder == null) {
            return 0;
        }
        this.endTime = System.currentTimeMillis();
        this.mMediaRecorder.stop();
        this.mMediaRecorder.reset();
        this.mMediaRecorder.release();
        this.mMediaRecorder = null;
        this.audioStatusUpdateListener.onStop(this.filePath);
        this.filePath = "";
        return this.endTime - this.startTime;
    }

    public void cancelRecord() {
        this.mMediaRecorder.stop();
        this.mMediaRecorder.reset();
        this.mMediaRecorder.release();
        this.mMediaRecorder = null;
        File file = new File(this.filePath);
        if (file.exists()) {
            file.delete();
        }
        this.filePath = "";
    }

    public void setOnAudioStatusUpdateListener(OnAudioStatusUpdateListener audioStatusUpdateListener2) {
        this.audioStatusUpdateListener = audioStatusUpdateListener2;
    }

    /* access modifiers changed from: private */
    public void updateMicStatus() {
        if (this.mMediaRecorder != null) {
            double ratio = ((double) this.mMediaRecorder.getMaxAmplitude()) / ((double) this.BASE);
            if (ratio > 1.0d) {
                double db = 20.0d * Math.log10(ratio);
                if (this.audioStatusUpdateListener != null) {
                    this.audioStatusUpdateListener.onUpdate(db, System.currentTimeMillis() - this.startTime);
                }
            }
            this.mHandler.postDelayed(this.mUpdateMicStatusTimer, (long) this.SPACE);
        }
    }
}
