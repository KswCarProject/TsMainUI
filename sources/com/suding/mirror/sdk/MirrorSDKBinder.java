package com.suding.mirror.sdk;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.texustek.mirror.aidl.BinderName;
import org.texustek.mirror.aidl.IMirrorAdapterBluetoothCallback;
import org.texustek.mirror.aidl.IMirrorAdapterBluetoothServer;
import org.texustek.mirror.aidl.IMirrorMainAidlInterface;

public class MirrorSDKBinder {
    private static final String TAG = "MirrorSDKBinder";
    private static MirrorSDKBinder instance;
    private static final Object mLock = new Object();
    private IMirrorAdapterBluetoothCallback.Stub mBluetoothCallback = new IMirrorAdapterBluetoothCallback.Stub() {
        public void onRequestOpenRfcomm(byte[] mac, String uuid) throws RemoteException {
            CopyOnWriteArrayList<IBluetoothTool> toolList = MirrorAdapterManager.getInstance().getBluetoothToolList();
            if (toolList != null && toolList.size() > 0) {
                Iterator<IBluetoothTool> it = toolList.iterator();
                while (it.hasNext()) {
                    it.next().onRequestOpenRfcomm(mac, uuid);
                }
            }
        }

        public void onRequestCloseRfcomm() throws RemoteException {
            CopyOnWriteArrayList<IBluetoothTool> toolList = MirrorAdapterManager.getInstance().getBluetoothToolList();
            if (toolList != null && toolList.size() > 0) {
                Iterator<IBluetoothTool> it = toolList.iterator();
                while (it.hasNext()) {
                    it.next().onRequestCloseRfcomm();
                }
            }
        }

        public void onRequestDisconnectBluetooth() throws RemoteException {
            CopyOnWriteArrayList<IBluetoothTool> toolList = MirrorAdapterManager.getInstance().getBluetoothToolList();
            if (toolList != null && toolList.size() > 0) {
                Iterator<IBluetoothTool> it = toolList.iterator();
                while (it.hasNext()) {
                    it.next().onRequestDisconnectBluetooth();
                }
            }
        }

        public void onRequestWriteRfcomm(byte[] data) throws RemoteException {
            CopyOnWriteArrayList<IBluetoothTool> toolList = MirrorAdapterManager.getInstance().getBluetoothToolList();
            if (toolList != null && toolList.size() > 0) {
                Iterator<IBluetoothTool> it = toolList.iterator();
                while (it.hasNext()) {
                    it.next().onRequestWriteRfcomm(data);
                }
            }
        }

        public int onRequestBluetoothConnectState() throws RemoteException {
            int state = 0;
            CopyOnWriteArrayList<IBluetoothTool> toolList = MirrorAdapterManager.getInstance().getBluetoothToolList();
            if (toolList != null && toolList.size() > 0) {
                Iterator<IBluetoothTool> it = toolList.iterator();
                while (it.hasNext()) {
                    state = it.next().onRequestBluetoothConnectState();
                }
            }
            return state;
        }

        public byte[] onRequestLocalBluetoothMacAddress() throws RemoteException {
            byte[] mac = null;
            CopyOnWriteArrayList<IBluetoothTool> toolList = MirrorAdapterManager.getInstance().getBluetoothToolList();
            if (toolList != null && toolList.size() > 0) {
                Iterator<IBluetoothTool> it = toolList.iterator();
                while (it.hasNext()) {
                    mac = it.next().onRequestLocalBluetoothMacAddress();
                }
            }
            return mac;
        }

        public byte[] onRequestPairedPhoneBluetoothMacAddress() throws RemoteException {
            byte[] mac = null;
            CopyOnWriteArrayList<IBluetoothTool> toolList = MirrorAdapterManager.getInstance().getBluetoothToolList();
            if (toolList != null && toolList.size() > 0) {
                Iterator<IBluetoothTool> it = toolList.iterator();
                while (it.hasNext()) {
                    mac = it.next().onRequestPairedPhoneBluetoothMacAddress();
                }
            }
            return mac;
        }

        public List<String> onRequestPairedBluetoothUuids() throws RemoteException {
            List<String> uuids = null;
            CopyOnWriteArrayList<IBluetoothTool> toolList = MirrorAdapterManager.getInstance().getBluetoothToolList();
            if (toolList != null && toolList.size() > 0) {
                Iterator<IBluetoothTool> it = toolList.iterator();
                while (it.hasNext()) {
                    uuids = it.next().onRequestPairedBluetoothUuids();
                }
            }
            return uuids;
        }

        public int onRequestRfcommConnectState() throws RemoteException {
            int state = 0;
            CopyOnWriteArrayList<IBluetoothTool> toolList = MirrorAdapterManager.getInstance().getBluetoothToolList();
            if (toolList != null && toolList.size() > 0) {
                Iterator<IBluetoothTool> it = toolList.iterator();
                while (it.hasNext()) {
                    state = it.next().onRequestRfcommConnectState();
                }
            }
            return state;
        }

        public int onRequestMpuBackCarStatus() throws RemoteException {
            int state = 0;
            CopyOnWriteArrayList<IBluetoothTool> toolList = MirrorAdapterManager.getInstance().getBluetoothToolList();
            if (toolList != null && toolList.size() > 0) {
                Iterator<IBluetoothTool> it = toolList.iterator();
                while (it.hasNext()) {
                    state = it.next().onRequestMpuBackCarStatus();
                }
            }
            return state;
        }

        public void onSpeedPlayConnectState(int state, String phoneMode) throws RemoteException {
            CopyOnWriteArrayList<IBluetoothTool> toolList = MirrorAdapterManager.getInstance().getBluetoothToolList();
            if (toolList != null && toolList.size() > 0) {
                Iterator<IBluetoothTool> it = toolList.iterator();
                while (it.hasNext()) {
                    it.next().onSpeedPlayConnectState(state, phoneMode);
                }
            }
        }
    };
    private IMirrorAdapterBluetoothServer mIMirrorBluetoothServer;
    private IMirrorMainAidlInterface mIMirrorMainAidlInterface;

    private MirrorSDKBinder() {
    }

    protected static MirrorSDKBinder getInstance() {
        if (instance == null) {
            synchronized (mLock) {
                if (instance == null) {
                    instance = new MirrorSDKBinder();
                }
            }
        }
        return instance;
    }

    /* access modifiers changed from: protected */
    public IMirrorMainAidlInterface getIMirrorMainAidlInterface() {
        return this.mIMirrorMainAidlInterface;
    }

    /* access modifiers changed from: protected */
    public void setIMirrorMainAidlInterface(IMirrorMainAidlInterface iMirrorMainAidlInterface, boolean connected) {
        this.mIMirrorMainAidlInterface = iMirrorMainAidlInterface;
        synchronized (mLock) {
            if (connected) {
                try {
                    IBinder service = iMirrorMainAidlInterface.getBinder(BinderName.BLUETOOTH);
                    if (service != null) {
                        setIMirrorBluetoothServer(IMirrorAdapterBluetoothServer.Stub.asInterface(service));
                        Log.d(TAG, "success achieve bluetooth server remote");
                        getIMirrorBluetoothServer().registerCallBack(this.mBluetoothCallback);
                        Log.d(TAG, "success register bluetooth server callback");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                setIMirrorBluetoothServer((IMirrorAdapterBluetoothServer) null);
            }
        }
    }

    public IMirrorAdapterBluetoothServer getIMirrorBluetoothServer() {
        return this.mIMirrorBluetoothServer;
    }

    private void setIMirrorBluetoothServer(IMirrorAdapterBluetoothServer iMirrorSdkSystemServer) {
        if (iMirrorSdkSystemServer == null && this.mIMirrorBluetoothServer != null) {
            try {
                this.mIMirrorBluetoothServer.unregisterCallBack(this.mBluetoothCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        this.mIMirrorBluetoothServer = iMirrorSdkSystemServer;
    }
}
