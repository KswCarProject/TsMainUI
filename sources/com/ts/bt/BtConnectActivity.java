package com.ts.bt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.bt.BtExe;
import com.ts.main.common.MainSet;
import com.txznet.sdk.TXZResourceManager;

@SuppressLint({"NewApi", "Override"})
public class BtConnectActivity extends BtBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int BT_ACTIVITY_ID = 1;
    private static final int MAXPINCODELENGTH = 16;
    private static final int MINPINCODELENGTH = 4;
    private static final String TAG = "BtConnectActivity";
    public static String mStrPinCode = "0000";
    long lastBackPressed;
    private CommonAdapter<BtExe.BonedDevice> mAdapter;
    private ImageButton mBtnConnect;
    private ImageButton mBtnConnectSearch;
    private ImageButton mBtnDisconnect;
    private ImageView mIcoPhone;
    boolean mIsConnected = false;
    private ListView mListView;
    private String mStrDevName;
    private TextView mTvDevice;
    private TextView mTvPhone;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mIsInMultiWindowMode = isInMultiWindowMode();
        updateLayout(this.mIsInMultiWindowMode);
    }

    /* access modifiers changed from: package-private */
    public void setDefaultDialer() {
        if (Build.VERSION.SDK_INT >= 23) {
            Intent intent = new Intent("android.telecom.action.CHANGE_DEFAULT_DIALER");
            intent.putExtra("android.telecom.extra.CHANGE_DEFAULT_DIALER_PACKAGE_NAME", getPackageName());
            startActivity(intent);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateLayout(boolean isInMultiWindowMode) {
        if (isInMultiWindowMode) {
            setContentView(R.layout.activity_bt_connect_s);
            initView1();
            return;
        }
        setContentView(R.layout.activity_bt_connect);
        initView();
    }

    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        this.mIsInMultiWindowMode = isInMultiWindowMode;
        updateMultiChange(isInMultiWindowMode);
    }

    /* access modifiers changed from: package-private */
    public void updateMultiChange(boolean isInMultiWindowMode) {
        updateLayout(isInMultiWindowMode);
        SubItemsInit(this, 1);
        updatePhoneInfo();
        updateDevInfo();
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
        SubItemsInit(this, 1);
        if (!this.mIsInMultiWindowMode) {
            EnterSubFocus();
        }
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
            if (this.mIsInMultiWindowMode) {
                this.mIcoPhone.setBackgroundResource(R.drawable.bt_bluetooth_dn_s);
            } else {
                this.mIcoPhone.setBackgroundResource(R.drawable.bt_bluetooth_dn);
                if (this.mAdapter != null) {
                    this.mAdapter.setSelect(0);
                    this.mAdapter.setDatas(BtExe.updateBonedDeviceList());
                }
            }
        } else {
            this.mTvPhone.setText(TXZResourceManager.STYLE_DEFAULT);
            if (this.mIsInMultiWindowMode) {
                this.mIcoPhone.setBackgroundResource(R.drawable.bt_bluetooth_up_s);
            } else {
                this.mIcoPhone.setBackgroundResource(R.drawable.bt_bluetooth_up);
                if (this.mAdapter != null) {
                    this.mAdapter.setSelect(-1);
                    this.mAdapter.setDatas(BtExe.updateBonedDeviceList());
                }
            }
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
        } else if (id == R.id.btBtnConnectSearch && this.bt.startScanning()) {
            new BtUnBonedListDialog(this, true);
        }
        if (!this.mIsInMultiWindowMode) {
            this.mbSubFocus = 2;
            updateFocus(v);
        }
    }

    private void initView() {
        BtExe.getBtInstance().setBluetoothDiscoverability(true);
        this.mIcoPhone = (ImageView) findViewById(R.id.btIvIcon);
        this.mTvDevice = (TextView) findViewById(R.id.btTvDev);
        this.mTvPhone = (TextView) findViewById(R.id.btTvPhone);
        this.mBtnConnect = (ImageButton) findViewById(R.id.btBtnConnect);
        this.mBtnDisconnect = (ImageButton) findViewById(R.id.btBtnDisconnect);
        this.mBtnConnectSearch = (ImageButton) findViewById(R.id.btBtnConnectSearch);
        this.mBtnConnect.setOnClickListener(this);
        this.mBtnDisconnect.setOnClickListener(this);
        this.mBtnConnectSearch.setOnClickListener(this);
        this.mListView = (ListView) findViewById(R.id.btBonedList);
        this.mAdapter = new CommonAdapter<BtExe.BonedDevice>(this, BtExe.updateBonedDeviceList(), R.layout.bt_device_item) {
            public void convert(ViewHolder holder, BtExe.BonedDevice swipeBean, final int position) {
                holder.setOnClickListener(R.id.btDeviceName, new View.OnClickListener() {
                    public void onClick(View v) {
                        long currentTime = System.currentTimeMillis();
                        if (currentTime - BtConnectActivity.this.lastBackPressed >= 2000) {
                            String addr = ((BtExe.BonedDevice) AnonymousClass1.this.mDatas.get(position)).addr;
                            Log.d("lq", String.valueOf(BtExe.getAddr()) + "-----" + addr);
                            BtConnectActivity.this.lastBackPressed = currentTime;
                            if (BtExe.getAddr() == null || !BtExe.getAddr().equals(addr)) {
                                BtConnectActivity.this.bt.connect((BtExe.BonedDevice) AnonymousClass1.this.mDatas.get(position));
                            }
                        }
                    }
                });
                if (getSelect() == position) {
                    holder.setSelected(R.id.btDeviceName, true);
                } else {
                    holder.setSelected(R.id.btDeviceName, false);
                }
                holder.setText(R.id.btDeviceName, swipeBean.name);
                holder.setOnClickListener(R.id.btnDelete, new View.OnClickListener() {
                    public void onClick(View v) {
                        if (AnonymousClass1.this.getSelect() == position) {
                            AnonymousClass1.this.setSelect(-1);
                        }
                        String addr = ((BtExe.BonedDevice) AnonymousClass1.this.mDatas.get(position)).addr;
                        BtConnectActivity.this.bt.unpairDevice(addr);
                        BtExe.deleteBondedDevice(addr);
                        AnonymousClass1.this.mDatas.remove(position);
                        if (addr.equals(BtExe.mLastDeviceAddr)) {
                            BtConnectActivity.this.bt.clearLastAddr();
                            BtExe.isSaveLastAddr = false;
                        }
                        AnonymousClass1.this.notifyDataSetChanged();
                    }
                });
            }
        };
        this.mListView.setAdapter(this.mAdapter);
        if (!this.mIsInMultiWindowMode) {
            this.mFocusView = new View[3];
            this.mFocusView[0] = this.mBtnConnect;
            this.mFocusView[1] = this.mBtnDisconnect;
            this.mFocusView[2] = this.mBtnConnectSearch;
            if (this.mbSubFocus == 2) {
                updateFocus(this.mFocusView[0]);
            }
        }
    }

    private void initView1() {
        BtExe.getBtInstance().setBluetoothDiscoverability(true);
        this.mIcoPhone = (ImageView) findViewById(R.id.btIvIcon);
        this.mTvDevice = (TextView) findViewById(R.id.btTvDev);
        this.mTvPhone = (TextView) findViewById(R.id.btTvPhone);
        this.mBtnConnect = (ImageButton) findViewById(R.id.btBtnConnect);
        this.mBtnDisconnect = (ImageButton) findViewById(R.id.btBtnDisconnect);
        this.mBtnConnect.setOnClickListener(this);
        this.mBtnDisconnect.setOnClickListener(this);
        this.mBtnDisconnect.setOnTouchListener(new View.OnTouchListener() {
            Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    AnonymousClass2.this.isLongClick = true;
                }
            };
            boolean isLongClick = false;

            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                int id = v.getId();
                switch (action) {
                    case 0:
                        this.isLongClick = false;
                        this.handler.removeMessages(id);
                        this.handler.sendEmptyMessageDelayed(id, 3000);
                        break;
                    case 2:
                        if (this.isLongClick) {
                            this.isLongClick = false;
                            MainSet.GetInstance().StartRecord(3000);
                            break;
                        }
                        break;
                }
                return false;
            }
        });
    }

    public void onTimer() {
        String strName;
        if (!this.bt.isConnected()) {
            mBaseStrDialNo = TXZResourceManager.STYLE_DEFAULT;
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
        if (BtExe.mRefreshBondedList) {
            if (this.mAdapter != null) {
                if (this.bt.isConnected()) {
                    this.mAdapter.setSelect(0);
                } else {
                    this.mAdapter.setSelect(-1);
                }
                this.mAdapter.setDatas(BtExe.updateBonedDeviceList());
            }
            BtExe.mRefreshBondedList = false;
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
            case 22:
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
