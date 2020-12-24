package com.ts.main.dsp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.txznet.sdk.TXZCameraManager;
import com.yyw.ts70xhw.Iop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class DspUpdateActivity extends Activity {
    public static final int DSP_STATE_DOWNLOAD_DATA = 0;
    public static final int DSP_STATE_UPDATE_FAIL = 4;
    public static final int DSP_STATE_UPDATE_SUCCESS = 3;
    public static final int DSP_STATE_WAITTING = 1;
    public static final int DSP_STATE_WRITTING_DATA = 2;
    public static final int MSG_UPDATE_UI = 256;
    public static final int UPDATE_VERSION_INFO = 257;
    /* access modifiers changed from: private */
    public boolean bQuitThread = false;
    /* access modifiers changed from: private */
    public int blockId;
    /* access modifiers changed from: private */
    public Button btnUpdate;
    /* access modifiers changed from: private */
    public byte[] buffer;
    protected Runnable downloadData = new Runnable() {
        private int oldState = -1;
        private int progress = -1;
        private byte[] sendBuffer;
        private int state = -1;

        public void run() {
            try {
                DspUpdateActivity.this.raf = new RandomAccessFile(new File(DspUpdateActivity.this.filePath), "r");
                if (DspUpdateActivity.this.raf != null) {
                    Arrays.fill(DspUpdateActivity.this.buffer, (byte) 0);
                    DspUpdateActivity.this.raf.read(DspUpdateActivity.this.buffer);
                    DspUpdateActivity.this.raf.close();
                }
                while (!DspUpdateActivity.this.bQuitThread) {
                    int update = Iop.DspGetUpdate();
                    this.state = (983040 & update) >> 16;
                    switch (this.state) {
                        case 0:
                            DspUpdateActivity.this.blockId = 65535 & update;
                            if (DspUpdateActivity.this.blockId > 0 && DspUpdateActivity.this.blockId * 128 <= DspUpdateActivity.this.buffer.length) {
                                this.sendBuffer = Arrays.copyOfRange(DspUpdateActivity.this.buffer, (DspUpdateActivity.this.blockId - 1) * 128, DspUpdateActivity.this.blockId * 128);
                                Iop.DspSndUpdate(DspUpdateActivity.this.blockId, this.sendBuffer);
                            }
                            DspUpdateActivity.this.mHandler.sendMessage(DspUpdateActivity.this.mHandler.obtainMessage(256, this.state, 0));
                            this.oldState = this.state;
                            break;
                        case 2:
                            if (!(this.oldState == this.state && this.progress == (update & 255))) {
                                this.progress = update & 255;
                                DspUpdateActivity.this.mHandler.sendMessage(DspUpdateActivity.this.mHandler.obtainMessage(256, this.state, update & 255));
                                this.oldState = this.state;
                                break;
                            }
                        case 3:
                        case 4:
                            if (this.oldState != this.state) {
                                DspUpdateActivity.this.mHandler.sendMessage(DspUpdateActivity.this.mHandler.obtainMessage(256, this.state, 0));
                                this.oldState = this.state;
                                break;
                            }
                            break;
                    }
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e2) {
                DspUpdateActivity.this.mHandler.sendMessage(DspUpdateActivity.this.mHandler.obtainMessage(256, -1));
                e2.printStackTrace();
            }
        }
    };
    /* access modifiers changed from: private */
    public String filePath = null;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        private static final int VERSION_UPDATE_COUNT = 30;
        private int updateCnt = 30;

        public void handleMessage(Message msg) {
            if (msg.what == 256) {
                switch (msg.arg1) {
                    case -1:
                        DspUpdateActivity.this.tvDownloadState.setText(R.string.read_file_error);
                        DspUpdateActivity.this.btnUpdate.setEnabled(true);
                        break;
                    case 0:
                        DspUpdateActivity.this.tvDownloadState.setText(String.valueOf(DspUpdateActivity.this.getResources().getString(R.string.downloading)) + " " + DspUpdateActivity.this.blockId + "/" + DspUpdateActivity.this.totalBlocks);
                        DspUpdateActivity.this.btnUpdate.setEnabled(false);
                        DspUpdateActivity.this.pbDownload.setProgress((int) (((long) (DspUpdateActivity.this.blockId * 100)) / DspUpdateActivity.this.totalBlocks));
                        break;
                    case 2:
                        DspUpdateActivity.this.tvDownloadState.setText(R.string.download_finish);
                        DspUpdateActivity.this.tvUpdateState.setText(R.string.writting_data);
                        DspUpdateActivity.this.pbUpdate.setProgress(msg.arg2);
                        break;
                    case 3:
                        DspUpdateActivity.this.tvUpdateState.setText(R.string.update_success);
                        new AlertDialog.Builder(DspUpdateActivity.this).setMessage(R.string.update_success).show();
                        DspUpdateActivity.this.pbUpdate.setProgress(100);
                        DspUpdateActivity.this.btnUpdate.setEnabled(true);
                        this.updateCnt = 30;
                        sendMessageDelayed(obtainMessage(257), 200);
                        break;
                    case 4:
                        DspUpdateActivity.this.tvUpdateState.setText(R.string.update_failed);
                        new AlertDialog.Builder(DspUpdateActivity.this).setMessage(R.string.update_failed).show();
                        DspUpdateActivity.this.btnUpdate.setEnabled(true);
                        break;
                }
            } else if (msg.what == 257) {
                DspUpdateActivity.this.updateVersionInfo();
                if (this.updateCnt > 0) {
                    this.updateCnt--;
                    sendMessageDelayed(obtainMessage(257), 200);
                }
            }
            super.handleMessage(msg);
        }
    };
    /* access modifiers changed from: private */
    public ProgressBar pbDownload;
    /* access modifiers changed from: private */
    public ProgressBar pbUpdate;
    /* access modifiers changed from: private */
    public RandomAccessFile raf;
    /* access modifiers changed from: private */
    public long totalBlocks = 0;
    /* access modifiers changed from: private */
    public TextView tvDownloadState;
    /* access modifiers changed from: private */
    public TextView tvUpdateState;
    private TextView tvVersion;
    private char[] version = new char[32];
    /* access modifiers changed from: private */
    public Thread workThread;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        this.filePath = getIntent().getStringExtra(TXZCameraManager.REMOTE_NAME_VIDEO_PATH);
        if (this.filePath == null) {
            finish();
            return;
        }
        this.workThread = new Thread(this.downloadData);
        setContentView(R.layout.activity_dsp_update);
        this.tvDownloadState = (TextView) findViewById(R.id.download_state);
        this.tvUpdateState = (TextView) findViewById(R.id.erase_state);
        this.tvVersion = (TextView) findViewById(R.id.version);
        updateVersionInfo();
        this.pbDownload = (ProgressBar) findViewById(R.id.download_progress);
        this.pbDownload.setMax(100);
        this.pbUpdate = (ProgressBar) findViewById(R.id.erase_progress);
        this.pbUpdate.setMax(100);
        this.btnUpdate = (Button) findViewById(R.id.btn_update);
        this.btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                File file = new File(DspUpdateActivity.this.filePath);
                if (DspUpdateActivity.this.filePath.endsWith("tsdsp.bin") && file.exists() && file.canRead()) {
                    try {
                        long totalSize = file.length();
                        if (totalSize == 0) {
                            new AlertDialog.Builder(DspUpdateActivity.this).setMessage(R.string.invaild_file).show();
                            return;
                        }
                        DspUpdateActivity.this.totalBlocks = (totalSize / 128) + ((long) (totalSize % 128 > 0 ? 1 : 0));
                        if (DspUpdateActivity.this.buffer == null || ((long) DspUpdateActivity.this.buffer.length) != DspUpdateActivity.this.totalBlocks * 128) {
                            DspUpdateActivity.this.buffer = new byte[((int) (DspUpdateActivity.this.totalBlocks * 128))];
                        }
                        Iop.DspStartUpdate((int) totalSize);
                        DspUpdateActivity.this.workThread.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateVersionInfo() {
        if (Iop.GetDspVer(this.version) == 1) {
            this.tvVersion.setText(String.valueOf(getResources().getString(R.string.version_code)) + " " + new String(this.version));
        }
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public void onDestroy() {
        this.mHandler.removeCallbacksAndMessages((Object) null);
        this.bQuitThread = true;
        this.workThread.interrupt();
        super.onDestroy();
    }
}
