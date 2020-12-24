package com.ts.can.sitechdev.cw;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanIF;
import com.ts.can.CanRelativeCarInfoView;
import com.yyw.ts70xhw.Mcu;

public class CanSitechDevCwUpdateView extends CanRelativeCarInfoView {
    public static final String TAG = "CanSitechDevCwUpdateView";
    /* access modifiers changed from: private */
    public static Handler handler = null;
    private View.OnClickListener McuUpdateClick = new View.OnClickListener() {
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.mcu_update_start) {
                CanSitechDevCwUpdateView.this.mUpdater.startUpdate();
            } else if (id == R.id.mcu_update_reset && CanSitechDevCwUpdateView.this.mUpdater.getIapSta() == 65535) {
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
    private CanDataInfo.CAN_VerInfo m_CanVer;
    private Boolean mcheck;
    /* access modifiers changed from: private */
    public Runnable runnable = new Runnable() {
        public void run() {
            CanSitechDevCwUpdateView.this.onTimer();
            CanSitechDevCwUpdateView.handler.postDelayed(CanSitechDevCwUpdateView.this.runnable, 30);
        }
    };

    public CanSitechDevCwUpdateView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    public void doOnPause() {
        super.doOnPause();
        if (!this.mUpdater.IsUpdating()) {
            handler.removeCallbacks(this.runnable);
            Log.d(TAG, "onPause !mUpdater.IsUpdating, removeCallbacks");
        }
    }

    public void doOnResume() {
        super.doOnResume();
        this.mProgressVal = -1;
        if (handler == null) {
            handler = new Handler();
        }
        handler.postDelayed(this.runnable, 30);
        this.mcheck = true;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        initScreen();
    }

    private void initScreen() {
        this.m_CanVer = new CanDataInfo.CAN_VerInfo();
        setBackgroundResource(R.drawable.can_comm_bg);
        View view1 = View.inflate(getActivity().getApplicationContext(), R.layout.activity_can_mcu_update, (ViewGroup) null);
        getRelativeManager().AddViewWrapContent(view1, 0, 0);
        this.mUpdater = McuUpdater.getInstance();
        this.mMsg = (TextView) view1.findViewById(R.id.mcu_update_msg);
        this.mCanMsg1 = (TextView) view1.findViewById(R.id.can_update_msg);
        this.mProgress = (ProgressBar) view1.findViewById(R.id.mcu_update_progress);
        this.mStart = (Button) view1.findViewById(R.id.mcu_update_start);
        this.mReset = (Button) view1.findViewById(R.id.mcu_update_reset);
        this.mStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                CanSitechDevCwUpdateView.this.mUpdater.startUpdate();
            }
        });
        this.mReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (CanSitechDevCwUpdateView.this.mUpdater.getIapSta() == 65535) {
                    Mcu.SendXKey(19);
                }
            }
        });
        this.mProgress.setMax(100);
        this.mCanVer = (TextView) view1.findViewById(R.id.can_ver);
        this.mCanOpera = (TextView) view1.findViewById(R.id.can_update_err);
        this.mStrMsg = getStringArray(R.array.mcu_update_msg);
        this.mStrCanSta = getStringArray(R.array.can_iap_updata_sta);
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public boolean i2b(int val) {
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
}
