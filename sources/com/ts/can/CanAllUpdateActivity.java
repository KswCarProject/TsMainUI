package com.ts.can;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.yyw.ts70xhw.Mcu;

public class CanAllUpdateActivity extends Activity {
    public static final String TAG = "CanAllUpdateActivity";
    /* access modifiers changed from: private */
    public static Handler handler = null;
    private View.OnClickListener McuUpdateClick = new View.OnClickListener() {
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.mcu_update_start) {
                CanAllUpdateActivity.this.mUpdater.startUpdate();
                CanFunc.mIapInfo.Type = CanAllUpdateActivity.this.mSpinnerVal;
            } else if (id == R.id.mcu_update_reset && CanAllUpdateActivity.this.mUpdater.getIapSta() == 65535) {
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
    private Spinner mSpinner;
    private ArrayAdapter<String> mSpinnerAdapter;
    /* access modifiers changed from: private */
    public int mSpinnerVal;
    private Button mStart;
    private String[] mStrCanSta;
    private String[] mStrMsg;
    private final String[] mStrSpinner = {"XP", "FL"};
    /* access modifiers changed from: private */
    public AllMcuUpdater mUpdater;
    private CanDataInfo.CAN_VerInfo m_CanVer = new CanDataInfo.CAN_VerInfo();
    private Boolean mcheck;
    /* access modifiers changed from: private */
    public Runnable runnable = new Runnable() {
        public void run() {
            CanAllUpdateActivity.this.onTimer();
            CanAllUpdateActivity.handler.postDelayed(CanAllUpdateActivity.this.runnable, 30);
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_mcu_update);
        this.mUpdater = AllMcuUpdater.getInstance();
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
        this.mSpinner = new Spinner(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(200, 70);
        lp.leftMargin = 100;
        lp.topMargin = 100;
        addContentView(this.mSpinner, lp);
        this.mSpinnerAdapter = new ArrayAdapter<>(this, 17367048, this.mStrSpinner);
        this.mSpinnerAdapter.setDropDownViewResource(R.layout.spinner_item_drop);
        this.mSpinner.setAdapter(this.mSpinnerAdapter);
        this.mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                CanAllUpdateActivity.this.mSpinnerVal = position + 1;
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
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
        this.mcheck = false;
    }

    /* access modifiers changed from: protected */
    public void setIapInfo() {
    }
}
