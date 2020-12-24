package com.ts.can;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ts.MainUI.R;

public class CanMcuUpdateActivity extends Activity {
    public static final String TAG = "CanMcuUpdateActivity";
    /* access modifiers changed from: private */
    public static Handler handler = null;
    private View.OnClickListener McuUpdateClick = new View.OnClickListener() {
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.mcu_update_start) {
                CanMcuUpdateActivity.this.mUpdater.startUpdate();
            } else if (id == R.id.mcu_update_reset && 6 == CanMcuUpdateActivity.this.mUpdater.getSta()) {
                CanMcuUpdateActivity.this.mUpdater.sendReset();
            }
        }
    };
    private TextView mMsg;
    private int mMsgVal;
    private ProgressBar mProgress;
    private int mProgressVal;
    private Button mReset;
    private Button mStart;
    private String[] mStrMsg;
    /* access modifiers changed from: private */
    public McuUpdater mUpdater;
    /* access modifiers changed from: private */
    public Runnable runnable = new Runnable() {
        public void run() {
            CanMcuUpdateActivity.this.onTimer();
            CanMcuUpdateActivity.handler.postDelayed(CanMcuUpdateActivity.this.runnable, 30);
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_mcu_update);
        this.mUpdater = McuUpdater.getInstance();
        this.mMsg = (TextView) findViewById(R.id.mcu_update_msg);
        this.mProgress = (ProgressBar) findViewById(R.id.mcu_update_progress);
        this.mStart = (Button) findViewById(R.id.mcu_update_start);
        this.mReset = (Button) findViewById(R.id.mcu_update_reset);
        this.mStart.setOnClickListener(this.McuUpdateClick);
        this.mReset.setOnClickListener(this.McuUpdateClick);
        this.mProgress.setMax(100);
        this.mStrMsg = getResources().getStringArray(R.array.mcu_update_msg);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (!this.mUpdater.IsUpdating()) {
            handler.removeCallbacks(this.runnable);
            Log.d(TAG, "onPause !mUpdater.IsUpdating, removeCallbacks");
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mProgressVal = -1;
        this.mMsgVal = -1;
        if (handler == null) {
            handler = new Handler();
        }
        handler.postDelayed(this.runnable, 30);
    }

    public void onTimer() {
        this.mUpdater.main();
        int pos = this.mUpdater.getProgress();
        int msg = this.mUpdater.getSta();
        if (pos != this.mProgressVal) {
            this.mProgressVal = pos;
            this.mProgress.setProgress(pos);
        }
        if (this.mMsgVal != msg) {
            this.mMsgVal = msg;
            if (msg < this.mStrMsg.length) {
                this.mMsg.setText(this.mStrMsg[msg]);
            }
        }
    }
}
