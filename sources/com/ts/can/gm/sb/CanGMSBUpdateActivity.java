package com.ts.can.gm.sb;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanIF;
import com.txznet.sdk.TXZPoiSearchManager;
import com.yyw.ts70xhw.Mcu;

public class CanGMSBUpdateActivity extends Activity {
    public static final String TAG = "CanGMSBUpdateActivity";
    /* access modifiers changed from: private */
    public static Handler handler = null;
    private View.OnClickListener McuUpdateClick = new View.OnClickListener() {
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.xt5_update_start) {
                char[] mcuVer = new char[32];
                Mcu.GetMcuVer(mcuVer);
                if ((Character.digit(mcuVer[6], 10) * 100000) + (Character.digit(mcuVer[7], 10) * TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT) + (Character.digit(mcuVer[8], 10) * 1000) + (Character.digit(mcuVer[9], 10) * 100) + (Character.digit(mcuVer[10], 10) * 10) + Character.digit(mcuVer[11], 10) >= 180130) {
                    CanGMSBUpdateActivity.this.mUpdater.startUpdate();
                } else {
                    Toast.makeText(CanGMSBUpdateActivity.this.getApplicationContext(), "请更新MCU", 0).show();
                }
            } else if (id == R.id.xt5_update_reset && CanGMSBUpdateActivity.this.m_IapSta.Sta == 4) {
                Mcu.SendXKey(19);
            }
        }
    };
    private TextView mCanMsg1;
    private TextView mCanMsg2;
    private int mCanMsgVal;
    private TextView mCanVer;
    private TextView mMsg;
    private int mMsgVal;
    private ProgressBar mProgress;
    private int mProgressVal;
    private Button mReset;
    private Button mStart;
    private final String[] mStrCanErr = {" ", "无应答", "文件过大", "擦除失败", "校验出错", "写入出错", "数据超时", "数据传输出错", "文件信息错误", "其他出错"};
    private final String[] mStrCanSta = {"协议盒状态：不允许升级", "协议盒状态：准备中", "协议盒状态：擦除中", "协议盒状态：开始升级", "协议盒状态：完成", "协议盒状态：错误", "协议盒状态：ID错误", "协议盒状态：文件大小错误", "协议盒状态：长时间没有收到第一帧数据", "协议盒状态：CAN请求进入CAN升级APP"};
    private final String[] mStrMsg = {"未进入升级流程", "升级文件打开失败!", "升级中，升级完成之前请不要断电!"};
    /* access modifiers changed from: private */
    public McuUpdater mUpdater;
    private CanDataInfo.CAN_VerInfo m_CanVer = new CanDataInfo.CAN_VerInfo();
    private CanDataInfo.GmSb_IapPro m_IapPro = new CanDataInfo.GmSb_IapPro();
    /* access modifiers changed from: private */
    public CanDataInfo.GmSb_IapSta m_IapSta = new CanDataInfo.GmSb_IapSta();
    private Boolean mcheck;
    /* access modifiers changed from: private */
    public Runnable runnable = new Runnable() {
        public void run() {
            CanGMSBUpdateActivity.this.onTimer();
            CanGMSBUpdateActivity.handler.postDelayed(CanGMSBUpdateActivity.this.runnable, 30);
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_xt5_update);
        this.mUpdater = McuUpdater.getInstance();
        this.mMsg = (TextView) findViewById(R.id.xt5_update_msg);
        this.mCanMsg1 = (TextView) findViewById(R.id.xt5_can_update_msg);
        this.mCanMsg2 = (TextView) findViewById(R.id.xt5_can_update_err);
        this.mProgress = (ProgressBar) findViewById(R.id.xt5_update_progress);
        this.mStart = (Button) findViewById(R.id.xt5_update_start);
        this.mReset = (Button) findViewById(R.id.xt5_update_reset);
        this.mStart.setOnClickListener(this.McuUpdateClick);
        this.mReset.setOnClickListener(this.McuUpdateClick);
        this.mProgress.setMax(100);
        this.mCanVer = (TextView) findViewById(R.id.xt5_can_ver);
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
        this.mCanMsgVal = -1;
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
        CanJni.GmSbIapGetSta(this.m_IapSta);
        if (i2b(this.m_IapSta.UpdateOnce) && (i2b(this.m_IapSta.Update) || this.mcheck.booleanValue())) {
            this.m_IapSta.Update = 0;
            if (this.m_IapSta.Sta < this.mStrCanSta.length) {
                this.mCanMsg1.setText(this.mStrCanSta[this.m_IapSta.Sta]);
            }
            if (this.m_IapSta.Sta == 3) {
                this.mUpdater.sendFileData(0);
            }
        }
        CanJni.GmSbIapGetPro(this.m_IapPro);
        if (i2b(this.m_IapPro.UpdateOnce) && (i2b(this.m_IapPro.Update) || this.mcheck.booleanValue())) {
            this.m_IapPro.Update = 0;
            if (this.m_IapPro.Pro < this.mStrCanErr.length) {
                this.mCanMsg2.setText(this.mStrCanErr[this.m_IapPro.Pro]);
            }
        }
        CanJni.GetVersion(this.m_CanVer);
        if (i2b(this.m_CanVer.UpdateOnce) && (i2b(this.m_CanVer.Update) || this.mcheck.booleanValue())) {
            this.m_CanVer.Update = 0;
            this.mCanVer.setText(CanIF.byte2String(this.m_CanVer.VerData));
        }
        this.mcheck = false;
    }
}
