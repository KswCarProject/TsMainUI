package com.ts.main.auth;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import com.ts.MainUI.AuthServer;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsBuf;
import com.ts.MainUI.UserCallBack;
import com.ts.activate.ITsAuthor;
import com.ts.activate.ITsAuthorCallBack;
import com.ts.bt.BtExe;
import com.ts.main.common.KeyTouch;
import com.ts.main.common.MainAlterwin;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.ShellUtils;
import com.txznet.sdk.TXZPoiSearchManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AuthActivity extends Activity implements UserCallBack {
    public static final int AUTH_CKFILE = 1;
    public static final int AUTH_CKNET = 2;
    public static final int AUTH_CKNETAG = 3;
    public static final int AUTH_IDLE = 153;
    public static final int AUTH_INIT = 0;
    public static final int AUTH_START = 4;
    public static final int AUTH_USBAUTHOK = 6;
    public static final int AUTH_USBCHECK = 5;
    private static final String TAG = "AuthActivity";
    private static final String USB_ACTION = "com.ts.main.auth.AuthActivity";
    byte[] Cmd = new byte[512];
    private ServiceConnection authorServiceConn = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName arg0) {
            try {
                AuthActivity.this.mAuthorServer.unRegisterCallback(AuthActivity.this.mAuthorCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            AuthActivity.this.mAuthorServer = null;
        }

        public void onServiceConnected(ComponentName arg0, IBinder binder) {
            AuthActivity.this.mAuthorServer = ITsAuthor.Stub.asInterface(binder);
            try {
                AuthActivity.this.mAuthorServer.registerCallback(AuthActivity.this.mAuthorCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
    boolean bCmdOk = false;
    /* access modifiers changed from: private */
    public boolean bIsHaveUsbAuth = false;
    boolean bNetCheckOK = false;
    /* access modifiers changed from: private */
    public boolean isReceiverMessage = true;
    /* access modifiers changed from: private */
    public boolean isSendMessage = true;
    /* access modifiers changed from: private */
    public ITsAuthorCallBack mAuthorCallback = new ITsAuthorCallBack.Stub() {
        public void onCmdResult(byte[] cmd) throws RemoteException {
            for (int i = 0; i < cmd.length; i++) {
                Log.d(AuthActivity.TAG, String.format("%02x\n", new Object[]{Byte.valueOf(cmd[i])}));
                AuthActivity.this.usbBuf.Push(cmd[i]);
            }
            AuthActivity.this.bCmdOk = true;
        }
    };
    public ITsAuthor mAuthorServer = null;
    /* access modifiers changed from: private */
    public byte[] mBytes = new byte[1024];
    private BroadcastReceiver mOpenDevicesReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
            if (intent.getBooleanExtra("permission", false) && usbDevice != null && AuthActivity.this.checkAOASupport(usbDevice)) {
                boolean unused = AuthActivity.this.initAccessory(usbDevice);
            }
        }
    };
    private ExecutorService mThreadPool;
    List<String> mUsbAuthCommond = new ArrayList();
    private BroadcastReceiver mUsbDetachedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Log.d(AuthActivity.TAG, intent.toString());
            String action = intent.getAction();
            UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
            if (action == null) {
                return;
            }
            if (action.equals("android.hardware.usb.action.USB_DEVICE_ATTACHED")) {
                Log.d(AuthActivity.TAG, "attch productId==" + usbDevice.getProductId());
                Log.d(AuthActivity.TAG, "attch VendorId==" + usbDevice.getVendorId());
                if (usbDevice.getProductId() == 11520 || usbDevice.getProductId() == 11521) {
                    boolean unused = AuthActivity.this.openUsbAccessory(usbDevice);
                    if (AuthActivity.this.m_dialgo != null) {
                        AuthActivity.this.m_dialgo.dismiss();
                    }
                    AuthActivity.this.nStep = 0;
                    return;
                }
                AuthActivity.this.listUsbDevice();
                return;
            }
            Log.d(AuthActivity.TAG, "detecth productId==" + usbDevice.getProductId());
            Log.d(AuthActivity.TAG, "detecth VendorId==" + usbDevice.getVendorId());
            if (usbDevice.getProductId() == 11520 || usbDevice.getProductId() == 11521) {
                AuthActivity.this.mUsbDevice = null;
                if (AuthActivity.this.mUsbDeviceConnection != null) {
                    AuthActivity.this.mUsbDeviceConnection.releaseInterface(AuthActivity.this.mUsbInterface);
                    AuthActivity.this.mUsbDeviceConnection.close();
                    AuthActivity.this.mUsbDeviceConnection = null;
                }
                AuthActivity.this.mUsbEndpointIn = null;
                AuthActivity.this.mUsbEndpointOut = null;
                AuthActivity.this.bIsHaveUsbAuth = false;
            }
        }
    };
    /* access modifiers changed from: private */
    public UsbDevice mUsbDevice;
    /* access modifiers changed from: private */
    public UsbDeviceConnection mUsbDeviceConnection;
    /* access modifiers changed from: private */
    public UsbEndpoint mUsbEndpointIn;
    /* access modifiers changed from: private */
    public UsbEndpoint mUsbEndpointOut;
    /* access modifiers changed from: private */
    public UsbInterface mUsbInterface;
    UsbManager mUsbManager;
    AuthServer m_AuthServer = AuthServer.GetInstance();
    AlertDialog m_dialgo;
    int nDellayTime = 0;
    int nErrorNum = 0;
    int nRenum = 0;
    int nStep = 0;
    int nUpdateOnce = 1;
    String reciveString;
    byte[] ubnum = new byte[1];
    TsBuf usbBuf = new TsBuf(512);

    private void initData() {
        this.isReceiverMessage = true;
        this.isSendMessage = true;
        IntentFilter intentFilter = new IntentFilter("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        registerReceiver(this.mUsbDetachedReceiver, intentFilter);
        this.mThreadPool = Executors.newFixedThreadPool(5);
        this.mUsbManager = (UsbManager) getSystemService("usb");
        registerReceiver(this.mOpenDevicesReceiver, new IntentFilter(USB_ACTION));
        loopReceiverMessage();
        loopSendMessage();
    }

    private void deinitData() {
        this.bIsHaveUsbAuth = false;
        this.isReceiverMessage = false;
        this.isSendMessage = false;
        this.mThreadPool.shutdownNow();
        unregisterReceiver(this.mUsbDetachedReceiver);
        unregisterReceiver(this.mOpenDevicesReceiver);
    }

    /* access modifiers changed from: private */
    public void listUsbDevice() {
        HashMap<String, UsbDevice> deviceList = this.mUsbManager.getDeviceList();
        if (deviceList != null && deviceList.size() > 0) {
            for (UsbDevice usbDevice : deviceList.values()) {
                boolean bSupportAOA = checkAOASupport(usbDevice);
                if (bSupportAOA) {
                    initAccessory(usbDevice);
                    Log.d(TAG, "listUsbDevice== initAccessory");
                    return;
                }
                Log.d(TAG, "bSupportAOA== " + bSupportAOA);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean checkAOASupport(UsbDevice usbDevice) {
        UsbDeviceConnection usbDeviceConnection = this.mUsbManager.openDevice(usbDevice);
        if (usbDeviceConnection == null) {
            return false;
        }
        byte[] data = new byte[2];
        if (usbDeviceConnection.controlTransfer(192, 51, 0, 0, data, data.length, 100) > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean initAccessory(UsbDevice usbDevice) {
        if (this.mUsbDevice != null) {
            Log.d(TAG, "mUsbDevice.getDeviceName()==" + this.mUsbDevice.getDeviceName());
            Log.d(TAG, "mUsbDevice.getProductId()==" + this.mUsbDevice.getProductId());
            Log.d(TAG, "mUsbDevice.getVendorId()==" + this.mUsbDevice.getVendorId());
            Log.d(TAG, "mUsbDevice.getDeviceId()==" + this.mUsbDevice.getDeviceId());
            Log.d(TAG, "mUsbDevice.getSerialNumber()==" + this.mUsbDevice.getSerialNumber());
            Log.d(TAG, "mUsbDevice.getManufacturerName()==" + this.mUsbDevice.getManufacturerName());
            return false;
        }
        UsbDeviceConnection usbDeviceConnection = this.mUsbManager.openDevice(usbDevice);
        if (usbDeviceConnection == null) {
            return false;
        }
        initStringControlTransfer(usbDeviceConnection, 0, "Forfan, Inc.");
        initStringControlTransfer(usbDeviceConnection, 1, "ForfanActivation");
        initStringControlTransfer(usbDeviceConnection, 2, "Forfan Activation");
        initStringControlTransfer(usbDeviceConnection, 3, "1.0");
        initStringControlTransfer(usbDeviceConnection, 4, "http://www.forfan.com");
        initStringControlTransfer(usbDeviceConnection, 5, "0123456789");
        usbDeviceConnection.controlTransfer(64, 53, 0, 0, new byte[0], 0, 100);
        usbDeviceConnection.close();
        return true;
    }

    private void initStringControlTransfer(UsbDeviceConnection deviceConnection, int index, String string) {
        deviceConnection.controlTransfer(64, 52, 0, index, string.getBytes(), string.length(), 100);
    }

    /* access modifiers changed from: private */
    public boolean openUsbAccessory(UsbDevice usbDevice) {
        if (this.mUsbDeviceConnection != null) {
            this.mUsbDeviceConnection.releaseInterface(this.mUsbInterface);
            this.mUsbDeviceConnection.close();
            this.mUsbDeviceConnection = null;
            this.mUsbEndpointIn = null;
            this.mUsbEndpointOut = null;
        }
        this.mUsbDeviceConnection = this.mUsbManager.openDevice(usbDevice);
        if (this.mUsbDeviceConnection != null) {
            this.mUsbInterface = usbDevice.getInterface(0);
            int endpointCount = this.mUsbInterface.getEndpointCount();
            for (int i = 0; i < endpointCount; i++) {
                UsbEndpoint usbEndpoint = this.mUsbInterface.getEndpoint(i);
                if (usbEndpoint.getType() == 2) {
                    if (usbEndpoint.getDirection() == 0) {
                        this.mUsbEndpointOut = usbEndpoint;
                    } else if (usbEndpoint.getDirection() == 128) {
                        this.mUsbEndpointIn = usbEndpoint;
                    }
                }
            }
            if (!(this.mUsbEndpointOut == null || this.mUsbEndpointIn == null)) {
                this.bIsHaveUsbAuth = true;
                this.mUsbDevice = usbDevice;
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void SendCmd() {
        synchronized (this.mUsbAuthCommond) {
            this.mUsbAuthCommond.add("activate===" + AuthServer.GetInstance().GetLicense() + "===" + BtExe.getBtInstance().getLocalBtAddress() + "=== ");
        }
    }

    /* access modifiers changed from: package-private */
    public String GetRevCmd(int len) {
        Arrays.fill(this.Cmd, (byte) 0);
        StringBuilder builder = new StringBuilder();
        byte[] tmp = new byte[1];
        for (int i = 0; i < len - 1; i++) {
            this.usbBuf.GetData(i + 4, tmp, 0);
            builder.append((char) tmp[0]);
        }
        String reciveString2 = builder.toString();
        Log.d(TAG, " " + reciveString2);
        Log.d(TAG, "Cmd length " + reciveString2.length());
        for (int j = 0; j < len + 4; j++) {
            this.usbBuf.Pop(this.ubnum);
        }
        return reciveString2;
    }

    /* access modifiers changed from: package-private */
    public boolean bIsAuthData() {
        if (this.usbBuf.GetLen() < 6 || !this.bCmdOk) {
            this.bCmdOk = false;
            return false;
        }
        Log.d(TAG, "usbBuf.GetLen()==" + this.usbBuf.GetLen());
        Arrays.fill(this.Cmd, (byte) 0);
        for (int i = 0; i < 4; i++) {
            this.usbBuf.GetData(i, this.Cmd, i);
            Log.d(TAG, "usbBuf[i]== " + this.Cmd[i]);
        }
        if (this.Cmd[0] == 85 && (this.Cmd[1] & 255) == 170) {
            Log.d(TAG, "get the head==");
            byte len = this.Cmd[2];
            byte cmdtype = this.Cmd[3];
            if (len + 4 <= this.usbBuf.GetLen()) {
                switch (cmdtype) {
                    case -1:
                        this.reciveString = GetRevCmd(len);
                        Toast.makeText(this, this.reciveString, 0).show();
                        this.m_AuthServer.SetnID(Integer.parseInt(this.reciveString));
                        return false;
                    case 0:
                        this.reciveString = GetRevCmd(len);
                        String[] data = this.reciveString.split("##\\*");
                        if (!AuthServer.GetInstance().bCheckAuthID(data[0])) {
                            this.m_AuthServer.SetnID(4);
                            break;
                        } else {
                            this.nRenum = Integer.parseInt(data[1]);
                            Log.d(TAG, "AUTH OK");
                            return true;
                        }
                }
            }
        } else {
            this.usbBuf.Pop(this.ubnum);
        }
        return false;
    }

    private void loopReceiverMessage() {
        this.mThreadPool.execute(new Runnable() {
            public void run() {
                int result;
                while (AuthActivity.this.isReceiverMessage) {
                    if (!(AuthActivity.this.mUsbDeviceConnection == null || AuthActivity.this.mUsbEndpointIn == null || (result = AuthActivity.this.mUsbDeviceConnection.bulkTransfer(AuthActivity.this.mUsbEndpointIn, AuthActivity.this.mBytes, AuthActivity.this.mBytes.length, TXZPoiSearchManager.DEFAULT_NEARBY_RADIUS)) <= 0)) {
                        Log.d(AuthActivity.TAG, "getDatas:" + result + ShellUtils.COMMAND_LINE_END);
                        for (int i = 0; i < result; i++) {
                            AuthActivity.this.usbBuf.Push(AuthActivity.this.mBytes[i]);
                        }
                        AuthActivity.this.bCmdOk = true;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void loopSendMessage() {
        this.mThreadPool.execute(new Runnable() {
            public void run() {
                while (AuthActivity.this.isSendMessage) {
                    if (!(AuthActivity.this.mUsbDeviceConnection == null || AuthActivity.this.mUsbEndpointOut == null)) {
                        String commond = null;
                        synchronized (AuthActivity.this.mUsbAuthCommond) {
                            if (AuthActivity.this.mUsbAuthCommond.size() > 0) {
                                commond = AuthActivity.this.mUsbAuthCommond.get(0);
                                AuthActivity.this.mUsbAuthCommond.remove(0);
                            }
                        }
                        if (commond != null) {
                            if (AuthActivity.this.mUsbDeviceConnection.bulkTransfer(AuthActivity.this.mUsbEndpointOut, commond.getBytes(), commond.getBytes().length, TXZPoiSearchManager.DEFAULT_NEARBY_RADIUS) > 0) {
                                Log.d(AuthActivity.TAG, "send message success");
                            } else {
                                Log.d(AuthActivity.TAG, "send message fail");
                            }
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authactivity_main);
        bindAuthorService();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        deinitData();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.nStep = 0;
        this.nErrorNum = 0;
        this.bNetCheckOK = false;
        MainTask.GetInstance().SetUserCallBack(this);
        MainUI.UpdateCheckTIme = 0;
        initData();
        super.onResume();
    }

    private void ShowOneMessage(String str, final int nFinish) {
        this.m_dialgo = new AlertDialog.Builder(this).setTitle("系统提示").setMessage(str).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (nFinish == 1) {
                    AuthActivity.this.finish();
                    return;
                }
                AuthActivity.this.m_dialgo.dismiss();
                AuthActivity.this.nStep = 0;
                AuthActivity.this.nUpdateOnce = 1;
            }
        }).show();
        MainSet.GetInstance().RefreshDialog(this, this.m_dialgo);
    }

    private void ShowNetIsNotOK() {
        if (this.m_dialgo != null) {
            this.m_dialgo.dismiss();
        }
        this.m_dialgo = new AlertDialog.Builder(this).setTitle("系统提示").setMessage("设备未激活,请联网").setPositiveButton("去连接Wifi", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                AuthActivity.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                AuthActivity.this.finish();
            }
        }).setNegativeButton("重试", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                AuthActivity.this.m_dialgo.dismiss();
                AuthActivity.this.nStep = 0;
            }
        }).show();
        MainSet.GetInstance().RefreshDialog(this, this.m_dialgo);
    }

    /* access modifiers changed from: package-private */
    public boolean IsHaveUsbAuth() {
        return this.bIsHaveUsbAuth;
    }

    public void UserAll() {
        if (MainUI.UpdateCheckTIme > 0) {
            MainUI.UpdateCheckTIme = 0;
        }
        switch (this.nStep) {
            case 0:
                if (!this.m_AuthServer.IsAuthOk()) {
                    this.nStep = 1;
                    this.nErrorNum = 0;
                    MainAlterwin.GetInstance().ShowUnRegWin(0);
                    break;
                } else {
                    ShowOneMessage("设备已激活", 1);
                    this.nStep = 153;
                    break;
                }
            case 1:
                if (!MainSet.GetInstance().bAutoAuth() && !this.m_AuthServer.GettheLicense()) {
                    this.m_dialgo = new AlertDialog.Builder(this).setTitle("系统提示").setMessage("未发现授权文件").setPositiveButton("重试", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            AuthActivity.this.m_dialgo.dismiss();
                            AuthActivity.this.nStep = 0;
                        }
                    }).setNegativeButton("返回", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            KeyTouch.GetInstance().sendKeyClick(3);
                        }
                    }).show();
                    MainSet.GetInstance().RefreshDialog(this, this.m_dialgo);
                    this.nStep = 153;
                    break;
                } else {
                    this.nStep = 2;
                    listUsbDevice();
                    break;
                }
            case 2:
                if (!AuthServer.GetInstance().IsNetOk(this)) {
                    if (!IsHaveUsbAuth()) {
                        ShowNetIsNotOK();
                        this.nStep = 153;
                        break;
                    } else {
                        Toast.makeText(this, "开始USB激活" + BtExe.getBtInstance().getLocalBtAddress(), 0).show();
                        SendCmd();
                        this.nStep = 5;
                        break;
                    }
                } else {
                    this.nStep = 3;
                    MainSet.GetInstance().NetCheck();
                    if (MainSet.GetInstance().bAutoAuth() && MainUI.nHasGetTime == 0) {
                        GetNetTime();
                        break;
                    }
                }
            case 3:
                if (!MainSet.GetInstance().bAutoAuth()) {
                    if (!MainSet.GetInstance().bIsValid()) {
                        this.nErrorNum++;
                        if (this.nErrorNum > 100) {
                            Toast.makeText(this, "网络延迟", 0).show();
                            this.nStep = 4;
                            break;
                        }
                    } else {
                        Toast.makeText(this, "网络正常", 0).show();
                        this.nStep = 4;
                        break;
                    }
                } else {
                    if (MainUI.nHasGetTime == 1) {
                        Toast.makeText(this, "自动激活网络正常", 0).show();
                        this.nStep = 4;
                    }
                    this.nErrorNum++;
                    if (this.nErrorNum > 100) {
                        Toast.makeText(this, "自动激活网络延迟", 0).show();
                        this.nStep = 4;
                        break;
                    }
                }
                break;
            case 4:
                if (this.nUpdateOnce == 1) {
                    Toast.makeText(this, "开始激活" + BtExe.getBtInstance().getLocalBtAddress(), 0).show();
                    if (this.mAuthorServer != null) {
                        if (!MainSet.GetInstance().bAutoAuth()) {
                            try {
                                this.mAuthorServer.sendCmd("activate===" + AuthServer.GetInstance().GetLicense() + "===" + BtExe.getBtInstance().getLocalBtAddress() + "=== ");
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                this.mAuthorServer.sendCmd("activateA===" + getResources().getString(R.string.custom_num));
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    this.nUpdateOnce = 0;
                }
                this.nStep = 5;
                break;
            case 5:
                if (!bIsAuthData()) {
                    this.nErrorNum++;
                    if (this.nErrorNum >= 250) {
                        this.nStep = 153;
                        if (this.m_AuthServer.GetnID() != 3) {
                            ShowOneMessage("激活超时,请重试", 0);
                            break;
                        } else {
                            ShowOneMessage("文件无效", 0);
                            break;
                        }
                    }
                } else {
                    this.nStep = 6;
                    break;
                }
                break;
            case 6:
                ShowOneMessage("激活中..." + this.nRenum, 1);
                MainSet.GetInstance().ResetTheFirstFlag();
                this.nStep = 153;
                this.nDellayTime = 150;
                break;
        }
        if (this.nDellayTime > 0) {
            this.nDellayTime--;
            if (this.nDellayTime == 0) {
                this.m_AuthServer.UpdateMcu();
            }
        }
    }

    private void GetNetTime() {
        if (this.mAuthorServer != null) {
            try {
                this.mAuthorServer.sendCmd("getnettime===888");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void bindAuthorService() {
        Intent intent = new Intent("com.ts.AuthorService");
        intent.setPackage("com.ts.activate");
        bindService(intent, this.authorServiceConn, 1);
    }
}
