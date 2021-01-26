package com.ts.wireless.carplay;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.os.ParcelUuid;
import android.os.SystemProperties;
import android.util.Log;
import com.autochips.carplay.CarplayAppState;
import com.autochips.carplay.CarplayAudioInfo;
import com.autochips.carplay.CarplayCall;
import com.autochips.carplay.CarplayDevice;
import com.autochips.carplay.CarplayManager;
import com.autochips.carplay.CarplayResourceState;
import com.suding.mirror.sdk.IBluetoothTool;
import com.suding.mirror.sdk.IMirrorTool;
import com.suding.mirror.sdk.MirrorAdapterInitializer;
import com.suding.mirror.sdk.MirrorAdapterManager;
import com.suding.mirror.sdk.MirrorInitParams;
import com.ts.MainUI.Evc;
import com.ts.bt.BtExe;
import com.ts.main.common.CarplayControl;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.txznet.sdk.bean.Poi;
import com.txznet.sdk.media.InvokeConstants;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Mcu;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WirelessCarplay implements BtExe.IBtStatusChangeListener {
    private static final int CONNECTION_STATE_CONNECTED = 2;
    private static final int CONNECTION_STATE_CONNECTING = 1;
    private static final int CONNECTION_STATE_DISCONNECTED = 0;
    private static final int CONNECTION_STATE_DISCONNECTING = 3;
    private static final int KEYCODE_LEFT_TURN = 1501;
    private static final int KEYCODE_REQUESTUI_MAP = 1504;
    private static final int KEYCODE_REQUESTUI_MUSIC = 1506;
    private static final int KEYCODE_REQUESTUI_PHONECALL = 1505;
    private static final int KEYCODE_RIGHT_TURN = 1502;
    private static final int KEYCODE_SIRI = 1500;
    private static final String SUDING_AUTO_PLAY_ACTION = "com.suding.speedplay";
    private static final String SUDING_AUTO_PLAY_RECEIVER_ACTION = "com.suding.speedplay.receive";
    private static final String TAG = "AirCarplay";
    /* access modifiers changed from: private */
    public static BluetoothSocket bs = null;
    /* access modifiers changed from: private */
    public static byte[] buffer = new byte[512];
    static InputStream is = null;
    /* access modifiers changed from: private */
    public static boolean isAutoAudioFocus = false;
    /* access modifiers changed from: private */
    public static boolean isAutoForeground = false;
    /* access modifiers changed from: private */
    public static boolean isAutoPhoneCallOn = false;
    /* access modifiers changed from: private */
    public static boolean isAutoSDKConnected = false;
    /* access modifiers changed from: private */
    public static BtExe mBt = null;
    private static final WirelessCarplay mCarplay = new WirelessCarplay();
    private static Context mContext = null;
    /* access modifiers changed from: private */
    public static Handler mHandler = null;
    private static Runnable mRfCommReadRunnable = new Runnable() {
        public void run() {
            Log.d(WirelessCarplay.TAG, "mRfCommReadThread run!!");
            if (WirelessCarplay.bs != null && WirelessCarplay.is == null) {
                synchronized (WirelessCarplay.bs) {
                    try {
                        WirelessCarplay.is = WirelessCarplay.bs.getInputStream();
                        Log.d(WirelessCarplay.TAG, "mRfCommReadThread get InputStream success!");
                    } catch (IOException e) {
                        Log.d(WirelessCarplay.TAG, "mRfCommReadThread IOException " + e);
                        synchronized (WirelessCarplay.bs) {
                            WirelessCarplay.bs = null;
                            MirrorAdapterManager.getInstance().reportRfcommConnectState(0);
                            return;
                        }
                    }
                }
            }
            while (WirelessCarplay.is != null) {
                try {
                    Log.d(WirelessCarplay.TAG, "mRfCommReadThread is.read(buffer)");
                    int cnt = WirelessCarplay.is.read(WirelessCarplay.buffer);
                    StringBuilder sb = new StringBuilder("mRfCommReadThread read = " + cnt);
                    for (int i = 0; i < cnt; i++) {
                        sb.append(String.format(" 0x%02x ", new Object[]{Byte.valueOf(WirelessCarplay.buffer[i])}));
                    }
                    Log.d(WirelessCarplay.TAG, sb.toString());
                    if (cnt > 0) {
                        byte[] data = new byte[cnt];
                        System.arraycopy(WirelessCarplay.buffer, 0, data, 0, cnt);
                        MirrorAdapterManager.getInstance().reportRfcommReadData(data);
                    }
                } catch (IOException e2) {
                    Log.d(WirelessCarplay.TAG, "mRfCommReadThread is read exception!!!");
                    WirelessCarplay.is = null;
                    MirrorAdapterManager.getInstance().reportRfcommConnectState(0);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public static int wiredAutoState = 1000;
    /* access modifiers changed from: private */
    public static int wiredCarplayState = 0;
    /* access modifiers changed from: private */
    public static int wirelessCarplayState = 1000;
    CarplayManager carplayManager = CarplayManager.getDefaultManager();
    private BroadcastReceiver mACCStatusReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(MainUI.ACTION_MAINUI_ACCOFF)) {
                Intent sendIntent = new Intent(WirelessCarplay.SUDING_AUTO_PLAY_RECEIVER_ACTION);
                sendIntent.setComponent(new ComponentName("com.suding.speedplay", "com.texustek.speedplay.broadcast.VendorBroadcastReceiver"));
                sendIntent.putExtra("command", "ACTION_EXIT");
                context.sendBroadcast(sendIntent);
                Log.d(WirelessCarplay.TAG, "MainUI ACC OFF sendbroadcast to com.suding.speedplay");
                Log.d(WirelessCarplay.TAG, sendIntent.toString());
            } else if (action.equals(MainUI.ACTION_MAINUI_ACCON)) {
                Intent sendIntent2 = new Intent(WirelessCarplay.SUDING_AUTO_PLAY_RECEIVER_ACTION);
                sendIntent2.setComponent(new ComponentName("com.suding.speedplay", "com.texustek.speedplay.broadcast.VendorBroadcastReceiver"));
                sendIntent2.putExtra("command", "ACTION_ENTER");
                context.sendBroadcast(sendIntent2);
                Log.d(WirelessCarplay.TAG, "MainUI ACC ON sendbroadcast to com.suding.speedplay");
                Log.d(WirelessCarplay.TAG, sendIntent2.toString());
            }
        }
    };
    private BroadcastReceiver mAutoPlayReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.suding.speedplay")) {
                Log.d(WirelessCarplay.TAG, "Receiver autoplay broadcast !!!");
                String status = intent.getStringExtra("status");
                if (status != null) {
                    Log.d(WirelessCarplay.TAG, "Receiver autoplay broadcast !!! status == " + status);
                    String phoneType = intent.getStringExtra("phoneType");
                    switch (status.hashCode()) {
                        case -1987407015:
                            if (status.equals("SERVICE_READY")) {
                                WirelessCarplay.this.bindAutoSDK(context);
                                break;
                            }
                            break;
                        case -1843701849:
                            if (status.equals("MAIN_PAGE_SHOW")) {
                                WirelessCarplay.isAutoForeground = true;
                                break;
                            }
                            break;
                        case -1478680495:
                            if (!status.equals("SIRI_ON")) {
                            }
                            break;
                        case -497207953:
                            if (status.equals("PHONE_CALL_ON") && phoneType != null && phoneType.equals("ios_carplay")) {
                                WirelessCarplay.isAutoPhoneCallOn = true;
                                Evc.GetInstance().evol_blue_set(1);
                                WirelessCarplay.setMicGain(1);
                                break;
                            }
                        case 1405544733:
                            if (!status.equals("SIRI_OFF")) {
                            }
                            break;
                        case 1709675476:
                            if (status.equals("MAIN_PAGE_HIDDEN")) {
                                WirelessCarplay.isAutoForeground = false;
                                break;
                            }
                            break;
                        case 1766422463:
                            if (status.equals("PHONE_CALL_OFF") && WirelessCarplay.isAutoPhoneCallOn) {
                                WirelessCarplay.isAutoPhoneCallOn = false;
                                Evc.GetInstance().evol_blue_set(0);
                                break;
                            }
                        case 2008068401:
                            if (!status.equals("MAIN_AUDIO_STOP")) {
                            }
                            break;
                        case 2120564979:
                            if (!status.equals("MAIN_AUDIO_START")) {
                            }
                            break;
                    }
                }
                String command = intent.getStringExtra("command");
                if (command != null) {
                    Log.d(WirelessCarplay.TAG, "Receiver autoplay broadcast !!! command == " + command);
                    switch (command.hashCode()) {
                        case -2126047073:
                            if (!command.equals("CMD_MIC_STOP")) {
                            }
                            return;
                        case -1895081633:
                            if (command.equals("UNREGISTER_HEADSET")) {
                                WirelessCarplay.isAutoAudioFocus = false;
                                return;
                            }
                            return;
                        case -1482963131:
                            if (!command.equals("CMD_MIC_START")) {
                            }
                            return;
                        case -1227251514:
                            if (command.equals("REGISTER_HEADSET")) {
                                WirelessCarplay.isAutoAudioFocus = true;
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    };
    IBluetoothTool mBluetoothTools = new IBluetoothTool() {
        private Runnable mOpenRfCommRunnable = null;
        OutputStream mOutStream = null;

        public void onRequestWriteRfcomm(byte[] data) {
            if (WirelessCarplay.bs != null) {
                Log.d(WirelessCarplay.TAG, "onRequestWriteRfcomm bs.isConnected() == " + WirelessCarplay.bs.isConnected());
            } else {
                Log.d(WirelessCarplay.TAG, "onRequestWriteRfcomm bs == null");
                MirrorAdapterManager.getInstance().reportRfcommConnectState(0);
            }
            if (WirelessCarplay.bs != null && WirelessCarplay.bs.isConnected()) {
                try {
                    this.mOutStream = WirelessCarplay.bs.getOutputStream();
                    if (this.mOutStream != null) {
                        this.mOutStream.write(data);
                        StringBuilder sb = new StringBuilder("onRequestWriteRfcomm write data length == " + data.length + " data == ");
                        for (int i = 0; i < data.length; i++) {
                            sb.append(String.format("0x%02x ", new Object[]{Byte.valueOf(data[i])}));
                            if (i % 20 == 0) {
                                sb.append("\r\n");
                                if (i % 100 == 0) {
                                    Log.d(WirelessCarplay.TAG, sb.toString());
                                    sb.delete(0, sb.length());
                                }
                            }
                        }
                        Log.d(WirelessCarplay.TAG, sb.toString());
                    }
                } catch (IOException e) {
                    synchronized (WirelessCarplay.bs) {
                        WirelessCarplay.bs = null;
                        Log.d(WirelessCarplay.TAG, "bs get OutStream IOException" + e);
                        MirrorAdapterManager.getInstance().reportRfcommConnectState(0);
                    }
                }
            }
        }

        public int onRequestRfcommConnectState() {
            if (WirelessCarplay.bs != null) {
                Log.d(WirelessCarplay.TAG, "onRequestRfcommConnectState bConnected " + WirelessCarplay.bs.isConnected());
                if (WirelessCarplay.bs.isConnected()) {
                    return 1;
                }
                return 0;
            }
            Log.d(WirelessCarplay.TAG, "onRequestRfcommConnectState bs == null");
            return 0;
        }

        public byte[] onRequestPairedPhoneBluetoothMacAddress() {
            Log.d(WirelessCarplay.TAG, "onRequestPairedPhoneBluetoothMacAddress");
            if (WirelessCarplay.mBt.isConnected()) {
                return WirelessCarplay.mBt.getRemoteAddr().getBytes();
            }
            return null;
        }

        public List<String> onRequestPairedBluetoothUuids() {
            Log.d(WirelessCarplay.TAG, "onRequestPairedBluetoothUuids");
            return WirelessCarplay.this.getPairedUUIDs();
        }

        public void onRequestOpenRfcomm(final byte[] mac, final String uuid) {
            Log.d(WirelessCarplay.TAG, "onRequestOpenRfcomm mac == " + WirelessCarplay.byteArray2addr(mac) + "  uuid === " + uuid);
            if (this.mOpenRfCommRunnable != null) {
                WirelessCarplay.mHandler.removeCallbacks(this.mOpenRfCommRunnable);
            }
            this.mOpenRfCommRunnable = new Runnable() {
                private int retryCnt = 3;

                public void run() {
                    if (this.retryCnt > 0 && !WirelessCarplay.openRfComm(mac, uuid)) {
                        this.retryCnt--;
                        Log.d(WirelessCarplay.TAG, "retryCnt == " + this.retryCnt);
                        WirelessCarplay.mHandler.postDelayed(this, 1000);
                    }
                }
            };
            WirelessCarplay.mHandler.post(this.mOpenRfCommRunnable);
        }

        public byte[] onRequestLocalBluetoothMacAddress() {
            Log.d(WirelessCarplay.TAG, "onRequestLocalBluetoothMacAddress");
            return WirelessCarplay.this.addr2byteArray(BluetoothAdapter.getDefaultAdapter().getAddress());
        }

        public void onRequestDisconnectBluetooth() {
            Log.d(WirelessCarplay.TAG, "onRequestDisconnectBluetooth");
            if (WirelessCarplay.mBt.isConnected()) {
                WirelessCarplay.mBt.disconnect();
                Log.d(WirelessCarplay.TAG, "mBt.disconnect() ###");
            }
        }

        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onRequestCloseRfcomm() {
            /*
                r3 = this;
                java.lang.String r1 = "AirCarplay"
                java.lang.String r2 = "onRequestCloseRfcomm"
                android.util.Log.d(r1, r2)
                android.bluetooth.BluetoothSocket r1 = com.ts.wireless.carplay.WirelessCarplay.bs
                if (r1 == 0) goto L_0x0020
                android.bluetooth.BluetoothSocket r2 = com.ts.wireless.carplay.WirelessCarplay.bs
                monitor-enter(r2)
                android.bluetooth.BluetoothSocket r1 = com.ts.wireless.carplay.WirelessCarplay.bs     // Catch:{ IOException -> 0x0021 }
                r1.close()     // Catch:{ IOException -> 0x0021 }
            L_0x001b:
                r1 = 0
                com.ts.wireless.carplay.WirelessCarplay.bs = r1     // Catch:{ all -> 0x0026 }
                monitor-exit(r2)     // Catch:{ all -> 0x0026 }
            L_0x0020:
                return
            L_0x0021:
                r0 = move-exception
                r0.printStackTrace()     // Catch:{ all -> 0x0026 }
                goto L_0x001b
            L_0x0026:
                r1 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0026 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ts.wireless.carplay.WirelessCarplay.AnonymousClass3.onRequestCloseRfcomm():void");
        }

        public int onRequestBluetoothConnectState() {
            Log.d(WirelessCarplay.TAG, "onRequestBluetoothConnectState");
            return WirelessCarplay.mBt.isConnected() ? 1 : 0;
        }

        public int onRequestMpuBackCarStatus() {
            return MainUI.IsCameraMode();
        }

        public void onSpeedPlayConnectState(int state, String phoneMode) {
            WirelessCarplay.this.updateSpeedPlayState(state, phoneMode);
        }
    };
    private BluetoothDevice mBondDevice = null;
    /* access modifiers changed from: private */
    public Runnable mGetUUIDRunnable = new Runnable() {
        public void run() {
            if (WirelessCarplay.this.getPairedUUIDs() != null) {
                MirrorAdapterManager.getInstance().reportPairedBluetoothUuids(WirelessCarplay.this.getPairedUUIDs());
            } else {
                WirelessCarplay.mHandler.postDelayed(WirelessCarplay.this.mGetUUIDRunnable, 1000);
            }
        }
    };
    IMirrorTool mMirrorTools = new IMirrorTool() {
        public void onMirrorSDKCoreDisconnected() {
            Log.d(WirelessCarplay.TAG, "onMirrorSDKCoreDisconnected");
            WirelessCarplay.isAutoSDKConnected = false;
            WirelessCarplay.isAutoPhoneCallOn = false;
            WirelessCarplay.isAutoAudioFocus = false;
            WirelessCarplay.wirelessCarplayState = 1000;
            WirelessCarplay.wiredAutoState = 1000;
            if (MainUI.GetInstance().GetMcuState() == 0) {
                CarplayControl.SetCarplayState();
            }
            if (WirelessCarplay.bs != null) {
                try {
                    Log.d(WirelessCarplay.TAG, "onMirrorSDKCoreDisconnected bs.close()");
                    WirelessCarplay.bs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                WirelessCarplay.bs = null;
            }
        }

        public void onMirrorSDKCoreConnected() {
            String strBtAddr = BluetoothAdapter.getDefaultAdapter().getAddress();
            if (strBtAddr != null) {
                byte[] btaddr = WirelessCarplay.this.addr2byteArray(strBtAddr);
                Log.d(WirelessCarplay.TAG, "onMirrorSDKCoreConnected report BT Addr = " + strBtAddr);
                MirrorAdapterManager.getInstance().reportLocalBluetoothMacAddress(btaddr);
            }
            WirelessCarplay.isAutoSDKConnected = true;
            if (WirelessCarplay.mBt.isConnected()) {
                WirelessCarplay.this.onBtConnected(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(WirelessCarplay.mBt.getRemoteAddr()));
            }
        }

        public void onSpeedPlayConnectState(int state, String phoneMode) {
            WirelessCarplay.this.updateSpeedPlayState(state, phoneMode);
        }
    };

    public static WirelessCarplay getInstance() {
        return mCarplay;
    }

    public static boolean isWirelessCarplayConnected() {
        return wirelessCarplayState == 1002;
    }

    /* access modifiers changed from: private */
    public static boolean openRfComm(byte[] mac, String uuid) {
        Log.d(TAG, "openRfComm mac == " + byteArray2addr(mac) + "  uuid === " + uuid);
        if (mBt.isConnected()) {
            if (bs == null) {
                Log.d(TAG, "bs == null start openRfComm");
                BluetoothDevice mdevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(byteArray2addr(mac));
                if (mdevice != null) {
                    try {
                        bs = mdevice.createRfcommSocketToServiceRecord(UUID.fromString(uuid));
                        bs.connect();
                        if (bs.isConnected()) {
                            Log.d(TAG, "onRequestOpenRfcomm bs Connected bs == null");
                            MirrorAdapterManager.getInstance().reportRfcommConnectState(1);
                            MainUI.mBackgroundExecutor.submit(mRfCommReadRunnable);
                            return true;
                        }
                        bs.close();
                        bs = null;
                        Log.d(TAG, "onRequestOpenRfcomm bs DisConnected");
                        MirrorAdapterManager.getInstance().reportRfcommConnectState(0);
                    } catch (IOException e) {
                        bs = null;
                        Log.d(TAG, "IOException onRequestOpenRfcomm bs DisConnected");
                        MirrorAdapterManager.getInstance().reportRfcommConnectState(0);
                    }
                }
            } else {
                try {
                    bs.connect();
                    if (bs.isConnected()) {
                        Log.d(TAG, "onRequestOpenRfcomm bs isConnected");
                        MirrorAdapterManager.getInstance().reportRfcommConnectState(1);
                        MainUI.mBackgroundExecutor.submit(mRfCommReadRunnable);
                        return true;
                    }
                    Log.d(TAG, "onRequestOpenRfcomm bs DisConnected");
                    MirrorAdapterManager.getInstance().reportRfcommConnectState(0);
                } catch (IOException e2) {
                    bs = null;
                    Log.d(TAG, "IOException onRequestOpenRfcomm bs DisConnected");
                    MirrorAdapterManager.getInstance().reportRfcommConnectState(0);
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void updateSpeedPlayState(int state, String phoneMode) {
        Log.d(TAG, "SpeedPlay State Change state = " + state + " phoneMode == " + phoneMode);
        if (phoneMode != null) {
            if (phoneMode.equals(MirrorAdapterManager.PHONE_MODE_CARPLAY_WIRELESS)) {
                wirelessCarplayState = state;
            } else if (phoneMode.equals(MirrorAdapterManager.PHONE_MODE_AUTO_WIRED)) {
                wiredAutoState = state;
            }
            if (state == 1001) {
                if (this.carplayManager.isEnabled()) {
                    this.carplayManager.disable();
                }
            } else if (state == 1002) {
                if (this.carplayManager.isEnabled()) {
                    this.carplayManager.disable();
                }
                if (phoneMode.equals(MirrorAdapterManager.PHONE_MODE_CARPLAY_WIRELESS)) {
                    mBt.disconnect();
                    mBt.setAutoConnect(false);
                    Log.d(TAG, "mBt.disconnect() ###");
                    if (Mcu.GetIll() == 1) {
                        setNightMode(true);
                    } else {
                        setNightMode(false);
                    }
                }
            } else if (state == 1000) {
                if (MainUI.GetInstance().GetMcuState() == 0) {
                    CarplayControl.SetCarplayState();
                }
                isAutoPhoneCallOn = false;
                if (phoneMode.equals(MirrorAdapterManager.PHONE_MODE_CARPLAY_WIRELESS)) {
                    mBt.initData();
                }
            }
            Log.d(TAG, "WiredCarplay enable state == " + this.carplayManager.isEnabled());
        }
    }

    private void initWiredCarplayState() {
        this.carplayManager.registerCallback(new CarplayManager.CarplayCallback() {
            public void onStreamStateChanged(int arg0, int arg1, int arg2) {
            }

            public void onResourceStateChanged(CarplayResourceState arg0) {
            }

            public void onRequestAccessoryUI(int arg0) {
            }

            public void onEnableStateChanged(int arg0, int arg1) {
                Log.d(WirelessCarplay.TAG, "onEnableStateChanged wirelessCarplayState == " + WirelessCarplay.wirelessCarplayState);
                Log.d(WirelessCarplay.TAG, "onEnableStateChanged wiredAutoState == " + WirelessCarplay.wiredAutoState);
                if (WirelessCarplay.wirelessCarplayState == 1001 || WirelessCarplay.wirelessCarplayState == 1002 || WirelessCarplay.wiredAutoState == 1001 || WirelessCarplay.wiredAutoState == 1002) {
                    WirelessCarplay.this.carplayManager.disable();
                }
                Log.d(WirelessCarplay.TAG, "WiredCarplay onEnableStateChanged state == " + WirelessCarplay.this.carplayManager.isEnabled());
            }

            public void onConnectionStateChanged(CarplayDevice carplayDevice, int state) {
                WirelessCarplay.wiredCarplayState = state;
                Log.d(WirelessCarplay.TAG, "WiredCarplay State Change === " + state);
                if (WirelessCarplay.wiredCarplayState == 1 || WirelessCarplay.wiredCarplayState == 2) {
                    SystemProperties.set("persist.sys.speedplay.cp.disa", "1");
                    SystemProperties.set("persist.sys.speedplay.auto.disa", "1");
                    if (WirelessCarplay.wiredCarplayState == 2) {
                        if (Mcu.GetIll() == 1) {
                            CarplayControl.SetNightMode(true);
                        } else {
                            CarplayControl.SetNightMode(false);
                        }
                    }
                } else if (WirelessCarplay.wiredCarplayState == 0) {
                    if (!MainSet.GetInstance().IsHaveApk("net.easyconn")) {
                        SystemProperties.set("persist.sys.speedplay.auto.disa", "0");
                    }
                    SystemProperties.set("persist.sys.speedplay.cp.disa", "0");
                }
                Log.d(WirelessCarplay.TAG, "SystemProperty persist.sys.speedplay.cp.disa === " + SystemProperties.get("persist.sys.speedplay.cp.disa"));
                Log.d(WirelessCarplay.TAG, "SystemProperty persist.sys.speedplay.auto.disa === " + SystemProperties.get("persist.sys.speedplay.auto.disa"));
            }

            public void onCallStateChanged(CarplayCall arg0) {
            }

            public void onAudioInfo(int arg0, int arg1, CarplayAudioInfo arg2) {
            }

            public void onAppStateChanged(CarplayAppState arg0) {
            }
        }, mHandler);
    }

    public static void setMicGain(int value) {
        if (mContext != null) {
            AudioManager am = (AudioManager) mContext.getSystemService(Poi.PoiAction.ACTION_AUDIO);
            am.setParameters("MIC1_HW_GAIN=" + value);
            am.setParameters("MIC2_HW_GAIN=" + value);
        }
    }

    public static void setAutoPlayBackStatus(boolean isBackCar) {
        if (isAutoSDKConnected) {
            MirrorAdapterManager.getInstance().reportMpuBackCarStatus(isBackCar ? 1 : 0);
        }
    }

    public static boolean AutoPlayDealKey(int nKeyCode) {
        if (wirelessCarplayState == 1002 || wiredAutoState == 1002) {
            if (isAutoPhoneCallOn) {
                switch (nKeyCode) {
                    case 29:
                    case 273:
                    case KeyDef.SKEY_CALLUP_1 /*814*/:
                        carplayControl("call");
                        return true;
                    case 30:
                    case 274:
                    case KeyDef.SKEY_CALLDN_1 /*819*/:
                        carplayControl("hang");
                        return true;
                }
            }
            if (isAutoAudioFocus && Evc.GetInstance().GetWorkMode() == 0) {
                switch (nKeyCode) {
                    case 44:
                    case 291:
                    case KeyDef.SKEY_SEEKUP_1 /*784*/:
                    case KeyDef.SKEY_CHUP_1 /*794*/:
                        carplayControl(InvokeConstants.INVOKE_NEXT);
                        return true;
                    case 45:
                    case 292:
                    case KeyDef.SKEY_SEEKDN_1 /*789*/:
                    case KeyDef.SKEY_CHDN_1 /*799*/:
                        carplayControl(InvokeConstants.INVOKE_PREV);
                        return true;
                    case 60:
                    case 91:
                    case KeyDef.SKEY_PP_1 /*824*/:
                        carplayControl("pp");
                        return true;
                }
            }
            if (isAutoForeground) {
                switch (nKeyCode) {
                    case 11:
                    case 262:
                        carplayControl("map");
                        return true;
                    case 21:
                    case 304:
                        carplayControl("enter");
                        return true;
                    case 22:
                    case 272:
                        carplayControl("back");
                        return true;
                    case 56:
                    case 516:
                        carplayControl("turn_right");
                        return true;
                    case 57:
                    case 515:
                        carplayControl("turn_left");
                        return true;
                    case 101:
                        carplayControl("music");
                        return true;
                    case 287:
                        carplayControl("right");
                        return true;
                    case 288:
                        carplayControl("left");
                        return true;
                    case 289:
                        carplayControl("up");
                        return true;
                    case 290:
                        carplayControl("dn");
                        return true;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void carplayControl(java.lang.String r4) {
        /*
            android.content.Context r2 = mContext
            if (r2 == 0) goto L_0x0030
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r2 = "com.suding.speedplay.receive"
            r0.<init>(r2)
            int r2 = r4.hashCode()
            switch(r2) {
                case -965787319: goto L_0x0031;
                case -124477815: goto L_0x0044;
                case 3584: goto L_0x005f;
                case 3739: goto L_0x007a;
                case 107868: goto L_0x0095;
                case 3015911: goto L_0x00b1;
                case 3045982: goto L_0x00cc;
                case 3089570: goto L_0x00e7;
                case 3194994: goto L_0x0103;
                case 3208415: goto L_0x011e;
                case 3317767: goto L_0x0139;
                case 3377907: goto L_0x0154;
                case 3443508: goto L_0x0170;
                case 3449395: goto L_0x018c;
                case 3530509: goto L_0x01a8;
                case 96667352: goto L_0x01c4;
                case 104263205: goto L_0x01e0;
                case 106440182: goto L_0x01fc;
                case 106642798: goto L_0x0218;
                case 108511772: goto L_0x0234;
                case 131025178: goto L_0x024f;
                case 441816026: goto L_0x0263;
                default: goto L_0x0013;
            }
        L_0x0013:
            java.lang.String r2 = "command"
            r0.putExtra(r2, r4)
        L_0x0019:
            java.lang.String r2 = "com.suding.speedplay"
            r0.setPackage(r2)
            java.lang.String r2 = "specFuncCode"
            r3 = 0
            int r1 = r0.getIntExtra(r2, r3)
            if (r1 == 0) goto L_0x027f
            com.suding.mirror.sdk.MirrorAdapterManager r2 = com.suding.mirror.sdk.MirrorAdapterManager.getInstance()
            r2.sendKeyEventRequest(r1)
        L_0x0030:
            return
        L_0x0031:
            java.lang.String r2 = "driver_pos_left"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "SET_DRIVER_POS_LEFT"
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x0044:
            java.lang.String r2 = "turn_left"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 1501(0x5dd, float:2.103E-42)
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x005f:
            java.lang.String r2 = "pp"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 85
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x007a:
            java.lang.String r2 = "up"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 19
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x0095:
            java.lang.String r2 = "map"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 1504(0x5e0, float:2.108E-42)
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x00b1:
            java.lang.String r2 = "back"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 4
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x00cc:
            java.lang.String r2 = "call"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 5
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x00e7:
            java.lang.String r2 = "down"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 20
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x0103:
            java.lang.String r2 = "hang"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 6
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x011e:
            java.lang.String r2 = "home"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 3
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x0139:
            java.lang.String r2 = "left"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 1
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x0154:
            java.lang.String r2 = "next"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 87
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x0170:
            java.lang.String r2 = "play"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 126(0x7e, float:1.77E-43)
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x018c:
            java.lang.String r2 = "prev"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 88
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x01a8:
            java.lang.String r2 = "siri"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 1500(0x5dc, float:2.102E-42)
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x01c4:
            java.lang.String r2 = "enter"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 66
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x01e0:
            java.lang.String r2 = "music"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 1506(0x5e2, float:2.11E-42)
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x01fc:
            java.lang.String r2 = "pause"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 127(0x7f, float:1.78E-43)
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x0218:
            java.lang.String r2 = "phone"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 1505(0x5e1, float:2.109E-42)
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x0234:
            java.lang.String r2 = "right"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 2
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x024f:
            java.lang.String r2 = "driver_pos_right"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "SET_DRIVER_POS_RIGHT"
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x0263:
            java.lang.String r2 = "turn_right"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = "command"
            java.lang.String r3 = "REQ_SPEC_FUNC_CMD"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "specFuncCode"
            r3 = 1502(0x5de, float:2.105E-42)
            r0.putExtra(r2, r3)
            goto L_0x0019
        L_0x027f:
            android.content.Context r2 = mContext
            r2.sendBroadcast(r0)
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.wireless.carplay.WirelessCarplay.carplayControl(java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public List<String> getPairedUUIDs() {
        ParcelUuid[] uuids;
        if (!mBt.isConnected() || (uuids = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(mBt.getRemoteAddr()).getUuids()) == null) {
            return null;
        }
        List<String> lUUIDs = new ArrayList<>();
        for (ParcelUuid uuid : uuids) {
            lUUIDs.add(uuid.toString());
        }
        return lUUIDs;
    }

    public void onBtStateChange(BluetoothDevice device, int state) {
        if (MainSet.GetInstance().IsHaveApk("com.suding.speedplay") && device != null) {
            if (state == 0) {
                this.mBondDevice = null;
                if (bs != null) {
                    synchronized (bs) {
                        try {
                            bs.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bs = null;
                    }
                }
                MirrorAdapterManager.getInstance().reportBluetoothConnectState(0);
                MirrorAdapterManager.getInstance().reportRfcommConnectState(0);
            } else if (state != 2) {
            } else {
                if (wirelessCarplayState == 1002) {
                    mBt.disconnect();
                    Log.d(TAG, "mBt.disconnect() ###");
                } else if (wirelessCarplayState == 1000 && wiredCarplayState != 1 && wiredCarplayState != 2) {
                    onBtConnected(device);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void onBtConnected(BluetoothDevice device) {
        Log.d(TAG, "onBtConnected !!!");
        MirrorAdapterManager.getInstance().reportBluetoothConnectState(1);
        if (this.mBondDevice == null || !this.mBondDevice.equals(device)) {
            MirrorAdapterManager.getInstance().reportPhoneBluetoothMacAddress(addr2byteArray(device.getAddress()), 0);
        } else {
            MirrorAdapterManager.getInstance().reportPhoneBluetoothMacAddress(addr2byteArray(device.getAddress()), 1);
            Log.d(TAG, "first boned device report bt addr = " + device.getAddress());
        }
        if (getPairedUUIDs() != null) {
            MirrorAdapterManager.getInstance().reportPairedBluetoothUuids(getPairedUUIDs());
        } else {
            mHandler.postDelayed(this.mGetUUIDRunnable, 1000);
        }
    }

    /* access modifiers changed from: private */
    public byte[] addr2byteArray(String addr) {
        if (addr == null || addr.isEmpty()) {
            return null;
        }
        String[] splits = addr.split(":");
        byte[] byteAddr = new byte[splits.length];
        for (int i = 0; i < splits.length; i++) {
            byteAddr[i] = Integer.valueOf(splits[i], 16).byteValue();
        }
        return byteAddr;
    }

    /* access modifiers changed from: private */
    public static String byteArray2addr(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(array[i])}));
            if (i < array.length - 1) {
                sb.append(":");
            }
        }
        return sb.toString().toUpperCase();
    }

    public void onApplicationCreate(Context context) {
        if (MainSet.GetInstance().IsHaveApk("com.suding.speedplay")) {
            mContext = context;
            mHandler = new Handler(mContext.getMainLooper());
            Log.d(TAG, "supprot speedpaly");
            context.registerReceiver(this.mAutoPlayReceiver, new IntentFilter("com.suding.speedplay"));
            mBt = BtExe.getBtInstance();
            mBt.registerStatusChangeListener(this);
            bindAutoSDK(context);
            initWiredCarplayState();
            IntentFilter filter = new IntentFilter(MainUI.ACTION_MAINUI_ACCON);
            filter.addAction(MainUI.ACTION_MAINUI_ACCOFF);
            mContext.registerReceiver(this.mACCStatusReceiver, filter);
        }
    }

    /* access modifiers changed from: private */
    public void bindAutoSDK(Context context) {
        Log.d(TAG, "bindAutoSDK !!!");
        SystemProperties.set("persist.sys.speedplay.cp.disa", "0");
        if (MainSet.GetInstance().IsHaveApk("net.easyconn")) {
            SystemProperties.set("persist.sys.speedplay.auto.disa", "1");
        } else {
            SystemProperties.set("persist.sys.speedplay.auto.disa", "0");
        }
        MirrorAdapterManager.getInstance().setMirrorTool(this.mMirrorTools);
        MirrorAdapterManager.getInstance().setBluetoothTool(this.mBluetoothTools);
        MirrorAdapterInitializer.getInstance().setDebug(true);
        MirrorAdapterInitializer.getInstance().initMirrorSDK(context, new MirrorInitParams());
    }

    public void onBtConnectStatusChange(BluetoothDevice device, int state) {
        mHandler.removeCallbacks((Runnable) null);
        if (!CarplayControl.bConnect) {
            onBtStateChange(device, state);
        }
    }

    public void onBtBonedStatusChange(BluetoothDevice device, int bondState) {
        Log.d(TAG, "onBtBonedStatusChange device mac == " + device.getAddress() + " state == " + bondState);
        if (bondState == 11) {
            this.mBondDevice = device;
        }
    }

    public static void setNightMode(boolean bNightMode) {
        if (wirelessCarplayState != 1002 && wiredAutoState != 1002) {
            return;
        }
        if (bNightMode) {
            MirrorAdapterManager.getInstance().setNightMode(true);
        } else {
            MirrorAdapterManager.getInstance().setNightMode(false);
        }
    }
}
