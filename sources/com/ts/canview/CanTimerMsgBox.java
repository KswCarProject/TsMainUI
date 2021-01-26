package com.ts.canview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.ts.MainUI.R;
import java.util.Timer;
import java.util.TimerTask;

public class CanTimerMsgBox {
    private AlertDialog mDlg;
    private long mMSec;
    private TimerTask mTask = null;
    private Timer mTimer = null;

    public CanTimerMsgBox() {
    }

    public void Hide() {
        StopTimer();
    }

    public CanTimerMsgBox(Context context, long msec, int title) {
        this.mMSec = msec;
        AlertDialog.Builder build = new AlertDialog.Builder(context);
        build.setTitle(R.string.str_fs_tip);
        build.setMessage(title);
        build.setCancelable(false);
        build.setPositiveButton(R.string.can_confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                CanTimerMsgBox.this.StopTimer();
            }
        });
        this.mDlg = build.create();
        this.mDlg.getWindow().setType(2003);
        this.mDlg.show();
        StartTimer();
    }

    public CanTimerMsgBox(Context context, long msec, String title) {
        this.mMSec = msec;
        AlertDialog.Builder build = new AlertDialog.Builder(context);
        build.setTitle(R.string.str_fs_tip);
        build.setMessage(title);
        build.setCancelable(false);
        build.setPositiveButton(R.string.can_confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                CanTimerMsgBox.this.StopTimer();
            }
        });
        this.mDlg = build.create();
        this.mDlg.getWindow().setType(2003);
        this.mDlg.show();
        StartTimer();
    }

    private void StartTimer() {
        if (this.mTimer == null) {
            this.mTimer = new Timer();
            this.mTask = new TimerTask() {
                public void run() {
                    CanTimerMsgBox.this.StopTimer();
                }
            };
            this.mTimer.schedule(this.mTask, this.mMSec);
        }
    }

    /* access modifiers changed from: private */
    public void StopTimer() {
        if (this.mTimer != null) {
            this.mTimer.cancel();
            this.mTimer.purge();
            this.mTimer = null;
            this.mTask.cancel();
            this.mTask = null;
            this.mDlg.dismiss();
        }
    }
}
