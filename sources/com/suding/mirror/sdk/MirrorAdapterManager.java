package com.suding.mirror.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.suding.mirror.util.LogUtils;
import com.ts.main.txz.AmapAuto;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.texustek.mirror.aidl.IMirrorAdapterBluetoothServer;
import org.texustek.mirror.aidl.IMirrorMainAidlInterface;

public class MirrorAdapterManager {
    public static final int MSG_CONNECT_STATUS_CONNECTED = 1002;
    public static final int MSG_CONNECT_STATUS_CONNECTING = 1001;
    public static final int MSG_CONNECT_STATUS_DISCONNECTED = 1000;
    public static final String PHONE_MODE_AIRPLAY_WIRED = "airplay_wired";
    public static final String PHONE_MODE_AIRPLAY_WIRELESS = "airplay_wireless";
    public static final String PHONE_MODE_AUTO_WIRED = "auto_wired";
    public static final String PHONE_MODE_AUTO_WIRELESS = "auto_wireless";
    public static final String PHONE_MODE_CARPLAY_WIRED = "carplay_wired";
    public static final String PHONE_MODE_CARPLAY_WIRELESS = "carplay_wireless";
    private static final String TAG = "MirrorAdapterManager";
    private static MirrorAdapterManager instance;
    private static final Object mLock = new Object();
    private CopyOnWriteArrayList<IBluetoothTool> mBluetoothToolList = new CopyOnWriteArrayList<>();
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public CopyOnWriteArrayList<IMirrorTool> mMirrorToolList = new CopyOnWriteArrayList<>();
    /* access modifiers changed from: private */
    public ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.tagLog(MirrorAdapterManager.TAG, "onServiceDisconnected");
            MirrorAdapterManager.this.mContext.unbindService(MirrorAdapterManager.this.mServiceConnection);
            MirrorSDKBinder.getInstance().setIMirrorMainAidlInterface((IMirrorMainAidlInterface) null, false);
            Iterator it = MirrorAdapterManager.this.mMirrorToolList.iterator();
            while (it.hasNext()) {
                ((IMirrorTool) it.next()).onMirrorSDKCoreDisconnected();
            }
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.tagLog(MirrorAdapterManager.TAG, "onServiceConnected");
            IMirrorMainAidlInterface tempAidlInterface = IMirrorMainAidlInterface.Stub.asInterface(service);
            if (tempAidlInterface != null) {
                MirrorSDKBinder.getInstance().setIMirrorMainAidlInterface(tempAidlInterface, true);
                Iterator it = MirrorAdapterManager.this.mMirrorToolList.iterator();
                while (it.hasNext()) {
                    ((IMirrorTool) it.next()).onMirrorSDKCoreConnected();
                }
            }
        }
    };

    private MirrorAdapterManager() {
    }

    public static MirrorAdapterManager getInstance() {
        if (instance == null) {
            synchronized (mLock) {
                if (instance == null) {
                    instance = new MirrorAdapterManager();
                }
            }
        }
        return instance;
    }

    /* access modifiers changed from: protected */
    public void initializeMirror(Context context) {
        this.mContext = context;
        try {
            LogUtils.jdsLog("Bind MirrorSDK MirrorService action : " + "com.suding.speedplay.MirrorService");
            Intent serviceIntent = new Intent("com.suding.speedplay.MirrorService");
            serviceIntent.setPackage(context.getPackageName());
            LogUtils.jdsLog("Bind MirrorSDK MirrorService pkgName : " + context.getPackageName());
            serviceIntent.setComponent(new ComponentName(AmapAuto.BROADCAST_SUDING_SPEEDPLAY, "org.texustek.mirror.core.MirrorService"));
            this.mContext.startService(serviceIntent);
            this.mContext.bindService(serviceIntent, this.mServiceConnection, 1);
        } catch (Exception e) {
            LogUtils.jdsLog("Bind MirrorSDK MirrorService Failed : " + e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public CopyOnWriteArrayList<IBluetoothTool> getBluetoothToolList() {
        return this.mBluetoothToolList;
    }

    public boolean setMirrorTool(IMirrorTool mirrorTool) {
        if (mirrorTool == null || this.mMirrorToolList.contains(mirrorTool)) {
            return false;
        }
        this.mMirrorToolList.add(mirrorTool);
        return true;
    }

    public boolean removeMirrorTool(IMirrorTool mirrorTool) {
        if (mirrorTool == null || !this.mMirrorToolList.contains(mirrorTool)) {
            return false;
        }
        this.mMirrorToolList.remove(mirrorTool);
        return true;
    }

    public boolean setBluetoothTool(IBluetoothTool bluetoothTool) {
        if (bluetoothTool == null || this.mBluetoothToolList.contains(bluetoothTool)) {
            return false;
        }
        this.mBluetoothToolList.add(bluetoothTool);
        return true;
    }

    public boolean removeBluetoothTool(IBluetoothTool bluetoothTool) {
        if (bluetoothTool == null || !this.mBluetoothToolList.contains(bluetoothTool)) {
            return false;
        }
        this.mBluetoothToolList.remove(bluetoothTool);
        return true;
    }

    public void reportBluetoothConnectState(int state) {
        IMirrorAdapterBluetoothServer btServer = MirrorSDKBinder.getInstance().getIMirrorBluetoothServer();
        if (btServer != null) {
            try {
                btServer.reportBluetoothConnectState(state);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void reportRfcommReadData(byte[] data) {
        IMirrorAdapterBluetoothServer btServer = MirrorSDKBinder.getInstance().getIMirrorBluetoothServer();
        if (btServer != null) {
            try {
                btServer.reportRfcommReadData(data);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void reportLocalBluetoothMacAddress(byte[] address) {
        IMirrorAdapterBluetoothServer btServer = MirrorSDKBinder.getInstance().getIMirrorBluetoothServer();
        if (btServer != null) {
            try {
                btServer.reportLocalBluetoothMacAddress(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void reportPhoneBluetoothMacAddress(byte[] address, int firstTimePaired) {
        IMirrorAdapterBluetoothServer btServer = MirrorSDKBinder.getInstance().getIMirrorBluetoothServer();
        if (btServer != null) {
            try {
                btServer.reportPhoneBluetoothMacAddress(address, firstTimePaired);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void reportPairedBluetoothUuids(List<String> uuids) {
        IMirrorAdapterBluetoothServer btServer = MirrorSDKBinder.getInstance().getIMirrorBluetoothServer();
        if (btServer != null) {
            try {
                btServer.reportPairedBluetoothUuids(uuids);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void reportRfcommConnectState(int state) {
        IMirrorAdapterBluetoothServer btServer = MirrorSDKBinder.getInstance().getIMirrorBluetoothServer();
        if (btServer != null) {
            try {
                btServer.reportRfcommConnectState(state);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void reportMpuBackCarStatus(int status) {
        IMirrorAdapterBluetoothServer btServer = MirrorSDKBinder.getInstance().getIMirrorBluetoothServer();
        if (btServer != null) {
            try {
                btServer.reportMpuBackCarStatus(status);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getSpeedPlayConnectState() {
        IMirrorAdapterBluetoothServer btServer = MirrorSDKBinder.getInstance().getIMirrorBluetoothServer();
        if (btServer != null) {
            try {
                return btServer.getSpeedPlayConnectState();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 1000;
    }

    public boolean isConnected(String phoneMode) {
        IMirrorAdapterBluetoothServer btServer = MirrorSDKBinder.getInstance().getIMirrorBluetoothServer();
        if (btServer != null) {
            try {
                return btServer.isConnected(phoneMode);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setNightMode(boolean isNightMode) {
        IMirrorAdapterBluetoothServer btServer = MirrorSDKBinder.getInstance().getIMirrorBluetoothServer();
        if (btServer != null) {
            try {
                btServer.setNightMode(isNightMode);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendKeyEventRequest(int specFuncCode) {
        IMirrorAdapterBluetoothServer btServer = MirrorSDKBinder.getInstance().getIMirrorBluetoothServer();
        if (btServer != null) {
            try {
                btServer.sendKeyEventRequest(specFuncCode);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
