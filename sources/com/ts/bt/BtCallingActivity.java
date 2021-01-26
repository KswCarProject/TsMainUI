package com.ts.bt;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.txznet.sdk.TXZResourceManager;

public class BtCallingActivity extends BtBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int BT_ACTIVITY_ID = 3;
    private static final String TAG = "BtCallingActivity";
    public static boolean mbShow;
    private final long CALL_HOLD_TIME = 4000;
    private BtExe bt;
    private ImageButton mBtnAnswer;
    private ImageButton mBtnHangup;
    private ImageButton mBtnMicMute;
    private ImageButton mBtnSound;
    private ImageButton mBtnVoiceSW;
    private long mHoldBegin;
    private int mOldSta;
    private String mStrOldCallName;
    private String mStrOldNo;
    private String mStrSta;
    private TextView mTvName;
    private TextView mTvNo;
    private TextView mTvSta;
    private boolean mbHold;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_calling);
        this.bt = BtExe.getBtInstance();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        SubItemsInit(this, 3);
        ResetSta();
        UpdateCallingUI();
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        mbShow = true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        mbShow = false;
    }

    public void onClick(View v) {
        int id = v.getId();
    }

    public void onTimer() {
        UpdateCallingUI();
    }

    /* access modifiers changed from: package-private */
    public void ResetSta() {
        this.mOldSta = 0;
        this.mStrOldNo = BtExe.UNKOWN_PHONE_NUMBER;
        this.mbHold = false;
        this.mStrOldCallName = TXZResourceManager.STYLE_DEFAULT;
    }

    /* access modifiers changed from: package-private */
    public void UpdateCallingUI() {
        if (this.mOldSta != BtExe.mCallSta) {
            if (BtExe.mCallSta != 1 || this.mOldSta <= 1) {
                this.mbHold = false;
            } else {
                this.mbHold = true;
                this.mHoldBegin = BtExe.getTickCount();
            }
            this.mOldSta = BtExe.mCallSta;
            Resources res = getResources();
            switch (BtExe.mCallSta) {
                case 2:
                    this.mStrSta = res.getString(R.string.str_bt_call_out);
                    break;
                case 3:
                    this.mStrSta = res.getString(R.string.str_bt_call_in);
                    break;
                case 4:
                    this.mStrSta = String.format("%s, %02d:%02d:%02d", new Object[]{res.getString(R.string.str_bt_call_active), Long.valueOf(BtExe.mActiveSecond / 3600), Long.valueOf((BtExe.mActiveSecond / 60) % 60), Long.valueOf(BtExe.mActiveSecond % 60)});
                    break;
                default:
                    this.mStrSta = TXZResourceManager.STYLE_DEFAULT;
                    break;
            }
            if (!this.mbHold) {
                this.mTvSta.setText(this.mStrSta);
            } else {
                this.mTvSta.setText(R.string.str_bt_call_end);
            }
        } else if (4 == BtExe.mCallSta) {
            this.mStrSta = String.format("%s, %02d:%02d:%02d", new Object[]{this.res.getString(R.string.str_bt_call_active), Long.valueOf(BtExe.mActiveSecond / 3600), Long.valueOf((BtExe.mActiveSecond / 60) % 60), Long.valueOf(BtExe.mActiveSecond % 60)});
            this.mTvSta.setText(this.mStrSta);
        }
        if (this.bt.isHaveCall()) {
            if (!this.mStrOldNo.equals(BtExe.mStrPhoneNo)) {
                this.mStrOldNo = new String(BtExe.mStrPhoneNo);
                this.mTvNo.setText(this.mStrOldNo);
            }
            if (!this.mStrOldCallName.equals(this.bt.GetCallName())) {
                this.mStrOldCallName = new String(this.bt.GetCallName());
                this.mTvName.setText(this.bt.GetCallName());
            }
        } else if (!this.mbHold) {
            Log.e(TAG, "Hold不住啦，消灭窗口是也");
            mBaseStrDialNo = TXZResourceManager.STYLE_DEFAULT;
            finish();
            resetBaseActivity();
        } else if (BtExe.getTickCount() > this.mHoldBegin + 4000) {
            this.mbHold = false;
        }
    }

    public void UserAll() {
        onTimer();
    }
}
