package com.ts.bt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;

@SuppressLint({"NewApi"})
public class BtConnectActivity extends BtBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int BT_ACTIVITY_ID = 1;
    private static final int MAXPINCODELENGTH = 16;
    private static final int MINPINCODELENGTH = 4;
    private static final String TAG = "BtConnectActivity";
    public static String mStrPinCode = "0000";
    int isBtState = -1;
    private ImageButton mBtnConnect;
    private ImageButton mBtnDisconnect;
    private ImageView mIcoPhone;
    boolean mIsConnected = false;
    private String mStrDevName;
    Switch mSwitchEnabled;
    private TextView mTvDevice;
    private TextView mTvPhone;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_connect);
        initView();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outstate) {
    }

    public void send() {
        Intent intent = new Intent("android.intent.action.BT_BOOT_BROADCAST");
        intent.putExtra("msg", 1);
        sendBroadcast(intent);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (this.bt.powerAbnomarlCheck()) {
            Toast.makeText(this, R.string.str_bt_reset, 0).show();
        }
        SubItemsInit(this, 1);
        EnterSubFocus();
        updatePhoneInfo();
        updateDevInfo();
        MainTask.GetInstance().SetUserCallBack(this);
        super.onResume();
    }

    /* access modifiers changed from: package-private */
    public void updateDevInfo() {
        this.bt.readDeviceNamePin();
        this.mStrDevName = this.bt.getDevName();
        this.mTvDevice.setText(this.bt.getDevName());
    }

    /* access modifiers changed from: package-private */
    public void updatePhoneInfo() {
        boolean bIsConnectd = this.bt.isConnected();
        Log.d("lh", "bIsConnectd: " + bIsConnectd);
        if (bIsConnectd) {
            this.mTvPhone.setText(this.bt.getPhoneName());
            this.mIcoPhone.setBackgroundResource(R.drawable.bt_bluetooth_dn);
        } else {
            this.mTvPhone.setText("");
            this.mIcoPhone.setBackgroundResource(R.drawable.bt_bluetooth_up);
        }
        this.mIsConnected = bIsConnectd;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btBtnConnect) {
            this.bt.connect();
        } else if (id == R.id.btBtnDisconnect) {
            this.bt.disconnect();
        }
        this.mbSubFocus = 2;
        updateFocus(v);
    }

    private void initView() {
        BtExe.getBtInstance().setBluetoothDiscoverability(true);
        this.mIcoPhone = (ImageView) findViewById(R.id.btIvIcon);
        this.mTvDevice = (TextView) findViewById(R.id.btTvDev);
        this.mTvPhone = (TextView) findViewById(R.id.btTvPhone);
        this.mBtnConnect = (ImageButton) findViewById(R.id.btBtnConnect);
        this.mBtnDisconnect = (ImageButton) findViewById(R.id.btBtnDisconnect);
        this.mSwitchEnabled = (Switch) findViewById(R.id.btSwitchEnabled);
        this.mSwitchEnabled.setChecked(this.bt.getBtEnabled());
        this.mSwitchEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                BtConnectActivity.this.bt.setBluetoothEnabled(checked);
            }
        });
        this.mBtnConnect.setOnClickListener(this);
        this.mBtnDisconnect.setOnClickListener(this);
        this.mBtnConnect.setOnClickListener(this);
        this.mBtnDisconnect.setOnClickListener(this);
        this.mFocusView = new View[2];
        this.mFocusView[0] = this.mBtnConnect;
        this.mFocusView[1] = this.mBtnDisconnect;
        if (this.mbSubFocus == 2) {
            updateFocus(this.mFocusView[0]);
        }
    }

    public void onTimer() {
        String strName;
        if (!this.bt.isConnected()) {
            mBaseStrDialNo = "";
        }
        if (this.bt.isConnected() != this.mIsConnected || this.bt.IsNeedUpdatePhoneName()) {
            BtExe.getBtInstance().saveLastAddr();
            updatePhoneInfo();
            Log.d("lh", "PhoneName+" + this.bt.getPhoneName());
            if (this.bt.getPhoneName() != null && !this.bt.getPhoneName().isEmpty()) {
                Log.d("lh", "phoneName setFalse" + this.bt.getPhoneName());
                this.bt.ResetUpdatePhoneName();
            }
        }
        if (!(this.mStrDevName == null || (strName = this.bt.getDevName()) == null || this.mStrDevName.equals(strName))) {
            Toast.makeText(getApplicationContext(), strName, 0).show();
            this.mStrDevName = strName;
            this.mTvDevice.setText(this.mStrDevName);
            Log.d(TAG, "device name changed!");
        }
        updateBtState();
    }

    /* access modifiers changed from: package-private */
    public void updateBtState() {
        int state;
        if (BtExe.mLocalAdapter != null && this.isBtState != (state = this.bt.getBluetoothAdapterState())) {
            this.isBtState = state;
            if (state == 12 || state == 10) {
                this.mSwitchEnabled.setEnabled(true);
                this.mSwitchEnabled.setClickable(true);
                if (state == 10) {
                    this.mSwitchEnabled.setChecked(false);
                } else {
                    this.mSwitchEnabled.setChecked(true);
                }
            } else {
                this.mSwitchEnabled.setEnabled(false);
                this.mSwitchEnabled.setClickable(false);
            }
        }
    }

    public void UserAll() {
        onTimer();
    }

    /* access modifiers changed from: protected */
    public int GetConnectFocusIndex() {
        if (this.mOldFocusView == null) {
            return 0;
        }
        for (int j = 0; j < this.mFocusView.length; j++) {
            if (this.mOldFocusView == this.mFocusView[j]) {
                return j;
            }
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void EnterSubFocus() {
        this.mbSubFocus = 2;
        updateFocus(this.mFocusView[0]);
    }

    /* access modifiers changed from: protected */
    public void ConnectPrev() {
        int index;
        int index2 = GetConnectFocusIndex();
        if (index2 > 0) {
            index = index2 - 1;
        } else {
            index = this.mFocusView.length - 1;
        }
        Log.d(TAG, "GetConnectFocusIndex LtPrev = " + index);
        updateFocus(this.mFocusView[index]);
    }

    /* access modifiers changed from: protected */
    public void ConnectNext() {
        int index;
        int index2 = GetConnectFocusIndex();
        if (index2 < this.mFocusView.length - 1) {
            index = index2 + 1;
        } else {
            index = 0;
        }
        Log.d(TAG, "GetConnectFocusIndex LtNext = " + index);
        updateFocus(this.mFocusView[index]);
    }

    /* access modifiers changed from: protected */
    public void ConnectCenter() {
        this.mFocusView[GetConnectFocusIndex()].performClick();
    }

    /* access modifiers changed from: protected */
    public boolean DealSubKey(int key) {
        switch (key) {
            case 19:
                ConnectPrev();
                return true;
            case 20:
                ConnectNext();
                return true;
            case 21:
                if (this.mbSubFocus != 2) {
                    return true;
                }
                updateFocus(this.mFocusView[0]);
                return true;
            case 23:
                ConnectCenter();
                return true;
            default:
                return true;
        }
    }
}
