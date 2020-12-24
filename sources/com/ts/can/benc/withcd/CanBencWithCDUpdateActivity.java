package com.ts.can.benc.withcd;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanIF;
import com.yyw.ts70xhw.Mcu;

public class CanBencWithCDUpdateActivity extends Activity {
    public static final String TAG = "CanBencWithCDUpdateActivity";
    /* access modifiers changed from: private */
    public static Handler handler = null;
    private View.OnClickListener McuUpdateClick = new View.OnClickListener() {
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.mcu_update_start) {
                CanBencWithCDUpdateActivity.this.mUpdater.startUpdate();
            } else if (id == R.id.mcu_update_reset && CanBencWithCDUpdateActivity.this.mUpdater.getIapSta() == 65535) {
                Mcu.SendXKey(19);
            }
        }
    };
    private TextView mCanMsg1;
    private TextView mCanOpera;
    private TextView mCanVer;
    private TextView mMsg;
    private int mMsgVal;
    private ProgressBar mProgress;
    private int mProgressVal;
    private Button mReset;
    private Button mStart;
    private String[] mStrCanSta;
    private String[] mStrMsg;
    /* access modifiers changed from: private */
    public McuUpdater mUpdater;
    private CanDataInfo.CAN_VerInfo m_CanVer = new CanDataInfo.CAN_VerInfo();
    private CanDataInfo.CanOperationMsg m_Opera = new CanDataInfo.CanOperationMsg();
    private Boolean mcheck;
    /* access modifiers changed from: private */
    public Runnable runnable = new Runnable() {
        public void run() {
            CanBencWithCDUpdateActivity.this.onTimer();
            CanBencWithCDUpdateActivity.handler.postDelayed(CanBencWithCDUpdateActivity.this.runnable, 30);
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_mcu_update);
        this.mUpdater = McuUpdater.getInstance();
        this.mMsg = (TextView) findViewById(R.id.mcu_update_msg);
        this.mCanMsg1 = (TextView) findViewById(R.id.can_update_msg);
        this.mProgress = (ProgressBar) findViewById(R.id.mcu_update_progress);
        this.mStart = (Button) findViewById(R.id.mcu_update_start);
        this.mReset = (Button) findViewById(R.id.mcu_update_reset);
        this.mStart.setOnClickListener(this.McuUpdateClick);
        this.mReset.setOnClickListener(this.McuUpdateClick);
        this.mProgress.setMax(100);
        this.mCanVer = (TextView) findViewById(R.id.can_ver);
        this.mCanOpera = (TextView) findViewById(R.id.can_update_err);
        this.mStrMsg = getResources().getStringArray(R.array.mcu_update_msg);
        this.mStrCanSta = getResources().getStringArray(R.array.can_iap_updata_sta);
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
        if (handler == null) {
            handler = new Handler();
        }
        handler.postDelayed(this.runnable, 30);
        this.mcheck = true;
    }

    protected static boolean i2b(int val) {
        if (val == 0) {
            return false;
        }
        return true;
    }

    public void onTimer() {
        this.mUpdater.main();
        int pos = this.mUpdater.getProgress();
        if (pos != this.mProgressVal) {
            this.mProgressVal = pos;
            this.mProgress.setProgress(pos);
        }
        int msg = this.mUpdater.getSta();
        if (this.mMsgVal != msg) {
            this.mMsgVal = msg;
            if (msg < this.mStrMsg.length) {
                this.mMsg.setText(this.mStrMsg[msg]);
            }
        }
        if (this.mUpdater.getIapSta() == 65535) {
            this.mCanMsg1.setText(this.mStrCanSta[1]);
        } else if (this.mUpdater.getIapSta() == 65534) {
            this.mCanMsg1.setText(this.mStrCanSta[2]);
        } else if (this.mUpdater.getIapSta() == 65533) {
            this.mCanMsg1.setText(this.mStrCanSta[3]);
        } else {
            this.mCanMsg1.setText(this.mStrCanSta[0]);
        }
        CanJni.GetVersion(this.m_CanVer);
        if (i2b(this.m_CanVer.UpdateOnce) && (i2b(this.m_CanVer.Update) || this.mcheck.booleanValue())) {
            this.m_CanVer.Update = 0;
            this.mCanVer.setText(CanIF.byte2String(this.m_CanVer.VerData));
        }
        CanJni.BencZmytWithCDGetOperationMsg(this.m_Opera);
        if (i2b(this.m_Opera.UpdateOnce) && (i2b(this.m_Opera.Update) || this.mcheck.booleanValue())) {
            this.m_Opera.Update = 0;
            this.mCanOpera.setText(CanIF.byte2String(this.m_Opera.Data));
        }
        this.mcheck = false;
    }
}
