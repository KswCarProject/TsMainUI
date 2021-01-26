package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.Log;
import com.ts.can.btobd.CanBtOBDActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class BluetoothEventManager {
    private static final String TAG = "BluetoothEventManager";
    private final IntentFilter mAdapterIntentFilter;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            Handler handler = (Handler) BluetoothEventManager.this.mHandlerMap.get(action);
            if (handler != null) {
                handler.onReceive(context, intent, device);
            }
        }
    };
    /* access modifiers changed from: private */
    public final Collection<BluetoothCallback> mCallbacks = new ArrayList();
    private Context mContext;
    /* access modifiers changed from: private */
    public final CachedBluetoothDeviceManager mDeviceManager;
    /* access modifiers changed from: private */
    public final Map<String, Handler> mHandlerMap;
    /* access modifiers changed from: private */
    public final LocalBluetoothAdapter mLocalAdapter;
    /* access modifiers changed from: private */
    public final BroadcastReceiver mProfileBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Log.v(BluetoothEventManager.TAG, "Received " + intent.getAction());
            String action = intent.getAction();
            BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            Handler handler = (Handler) BluetoothEventManager.this.mHandlerMap.get(action);
            if (handler != null) {
                handler.onReceive(context, intent, device);
            }
        }
    };
    private final IntentFilter mProfileIntentFilter;
    /* access modifiers changed from: private */
    public LocalBluetoothProfileManager mProfileManager;
    private android.os.Handler mReceiverHandler;

    interface Handler {
        void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice);
    }

    private void addHandler(String action, Handler handler) {
        this.mHandlerMap.put(action, handler);
        this.mAdapterIntentFilter.addAction(action);
    }

    /* access modifiers changed from: package-private */
    public void addProfileHandler(String action, Handler handler) {
        this.mHandlerMap.put(action, handler);
        this.mProfileIntentFilter.addAction(action);
    }

    /* access modifiers changed from: package-private */
    public void setProfileManager(LocalBluetoothProfileManager manager) {
        this.mProfileManager = manager;
    }

    BluetoothEventManager(LocalBluetoothAdapter adapter, CachedBluetoothDeviceManager deviceManager, Context context) {
        this.mLocalAdapter = adapter;
        this.mDeviceManager = deviceManager;
        this.mAdapterIntentFilter = new IntentFilter();
        this.mProfileIntentFilter = new IntentFilter();
        this.mHandlerMap = new HashMap();
        this.mContext = context;
        addHandler("android.bluetooth.adapter.action.STATE_CHANGED", new AdapterStateChangedHandler(this, (AdapterStateChangedHandler) null));
        addHandler("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED", new ConnectionStateChangedHandler(this, (ConnectionStateChangedHandler) null));
        addHandler("android.bluetooth.adapter.action.DISCOVERY_STARTED", new ScanningStateChangedHandler(true));
        addHandler(CanBtOBDActivity.ACTION_DISCOVERY_FINISHED, new ScanningStateChangedHandler(false));
        addHandler("android.bluetooth.device.action.FOUND", new DeviceFoundHandler(this, (DeviceFoundHandler) null));
        addHandler("android.bluetooth.device.action.DISAPPEARED", new DeviceDisappearedHandler(this, (DeviceDisappearedHandler) null));
        addHandler("android.bluetooth.device.action.NAME_CHANGED", new NameChangedHandler(this, (NameChangedHandler) null));
        addHandler("android.bluetooth.device.action.ALIAS_CHANGED", new NameChangedHandler(this, (NameChangedHandler) null));
        addHandler("android.bluetooth.device.action.BOND_STATE_CHANGED", new BondStateChangedHandler(this, (BondStateChangedHandler) null));
        addHandler("android.bluetooth.device.action.CLASS_CHANGED", new ClassChangedHandler(this, (ClassChangedHandler) null));
        addHandler("android.bluetooth.device.action.UUID", new UuidChangedHandler(this, (UuidChangedHandler) null));
        addHandler("android.bluetooth.device.action.BATTERY_LEVEL_CHANGED", new BatteryLevelChangedHandler(this, (BatteryLevelChangedHandler) null));
        addHandler("android.intent.action.DOCK_EVENT", new DockEventHandler(this, (DockEventHandler) null));
        addHandler("android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED", new ActiveDeviceChangedHandler(this, (ActiveDeviceChangedHandler) null));
        addHandler("android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED", new ActiveDeviceChangedHandler(this, (ActiveDeviceChangedHandler) null));
        addHandler("android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED", new ActiveDeviceChangedHandler(this, (ActiveDeviceChangedHandler) null));
        addHandler("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED", new AudioModeChangedHandler(this, (AudioModeChangedHandler) null));
        addHandler("android.intent.action.PHONE_STATE", new AudioModeChangedHandler(this, (AudioModeChangedHandler) null));
        this.mContext.registerReceiver(this.mBroadcastReceiver, this.mAdapterIntentFilter, (String) null, this.mReceiverHandler);
        this.mContext.registerReceiver(this.mProfileBroadcastReceiver, this.mProfileIntentFilter, (String) null, this.mReceiverHandler);
    }

    /* access modifiers changed from: package-private */
    public void registerProfileIntentReceiver() {
        this.mContext.registerReceiver(this.mProfileBroadcastReceiver, this.mProfileIntentFilter, (String) null, this.mReceiverHandler);
    }

    public void setReceiverHandler(android.os.Handler handler) {
        this.mContext.unregisterReceiver(this.mBroadcastReceiver);
        this.mContext.unregisterReceiver(this.mProfileBroadcastReceiver);
        this.mReceiverHandler = handler;
        this.mContext.registerReceiver(this.mBroadcastReceiver, this.mAdapterIntentFilter, (String) null, this.mReceiverHandler);
        registerProfileIntentReceiver();
    }

    public void registerCallback(BluetoothCallback callback) {
        synchronized (this.mCallbacks) {
            this.mCallbacks.add(callback);
        }
    }

    public void unregisterCallback(BluetoothCallback callback) {
        synchronized (this.mCallbacks) {
            this.mCallbacks.remove(callback);
        }
    }

    private class AdapterStateChangedHandler implements Handler {
        private AdapterStateChangedHandler() {
        }

        /* synthetic */ AdapterStateChangedHandler(BluetoothEventManager bluetoothEventManager, AdapterStateChangedHandler adapterStateChangedHandler) {
            this();
        }

        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            int state = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", ExploreByTouchHelper.INVALID_ID);
            if (state == 10) {
                context.unregisterReceiver(BluetoothEventManager.this.mProfileBroadcastReceiver);
                BluetoothEventManager.this.registerProfileIntentReceiver();
            }
            BluetoothEventManager.this.mLocalAdapter.setBluetoothStateInt(state);
            synchronized (BluetoothEventManager.this.mCallbacks) {
                for (BluetoothCallback callback : BluetoothEventManager.this.mCallbacks) {
                    callback.onBluetoothStateChanged(state);
                }
            }
            BluetoothEventManager.this.mDeviceManager.onBluetoothStateChanged(state);
        }
    }

    private class ScanningStateChangedHandler implements Handler {
        private final boolean mStarted;

        ScanningStateChangedHandler(boolean started) {
            this.mStarted = started;
        }

        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            synchronized (BluetoothEventManager.this.mCallbacks) {
                for (BluetoothCallback callback : BluetoothEventManager.this.mCallbacks) {
                    callback.onScanningStateChanged(this.mStarted);
                }
            }
            Log.d(BluetoothEventManager.TAG, "scanning state change to " + this.mStarted);
            BluetoothEventManager.this.mDeviceManager.onScanningStateChanged(this.mStarted);
        }
    }

    private class DeviceFoundHandler implements Handler {
        private DeviceFoundHandler() {
        }

        /* synthetic */ DeviceFoundHandler(BluetoothEventManager bluetoothEventManager, DeviceFoundHandler deviceFoundHandler) {
            this();
        }

        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            short rssi = intent.getShortExtra("android.bluetooth.device.extra.RSSI", Short.MIN_VALUE);
            BluetoothClass btClass = (BluetoothClass) intent.getParcelableExtra("android.bluetooth.device.extra.CLASS");
            String name = intent.getStringExtra("android.bluetooth.device.extra.NAME");
            Log.d(BluetoothEventManager.TAG, "Device " + name + " ,Class: " + (btClass != null ? Integer.valueOf(btClass.getMajorDeviceClass()) : null));
            CachedBluetoothDevice cachedDevice = BluetoothEventManager.this.mDeviceManager.findDevice(device);
            if (cachedDevice == null) {
                cachedDevice = BluetoothEventManager.this.mDeviceManager.addDevice(BluetoothEventManager.this.mLocalAdapter, BluetoothEventManager.this.mProfileManager, device);
                Log.d(BluetoothEventManager.TAG, "DeviceFoundHandler created new CachedBluetoothDevice: " + cachedDevice);
            }
            cachedDevice.setRssi(rssi);
            cachedDevice.setBtClass(btClass);
            cachedDevice.setNewName(name);
            cachedDevice.setJustDiscovered(true);
        }
    }

    private class ConnectionStateChangedHandler implements Handler {
        private ConnectionStateChangedHandler() {
        }

        /* synthetic */ ConnectionStateChangedHandler(BluetoothEventManager bluetoothEventManager, ConnectionStateChangedHandler connectionStateChangedHandler) {
            this();
        }

        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            BluetoothEventManager.this.dispatchConnectionStateChanged(BluetoothEventManager.this.mDeviceManager.findDevice(device), intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", ExploreByTouchHelper.INVALID_ID));
        }
    }

    /* access modifiers changed from: private */
    public void dispatchConnectionStateChanged(CachedBluetoothDevice cachedDevice, int state) {
        synchronized (this.mCallbacks) {
            for (BluetoothCallback callback : this.mCallbacks) {
                callback.onConnectionStateChanged(cachedDevice, state);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchDeviceAdded(CachedBluetoothDevice cachedDevice) {
        synchronized (this.mCallbacks) {
            for (BluetoothCallback callback : this.mCallbacks) {
                callback.onDeviceAdded(cachedDevice);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchDeviceRemoved(CachedBluetoothDevice cachedDevice) {
        synchronized (this.mCallbacks) {
            for (BluetoothCallback callback : this.mCallbacks) {
                callback.onDeviceDeleted(cachedDevice);
            }
        }
    }

    private class DeviceDisappearedHandler implements Handler {
        private DeviceDisappearedHandler() {
        }

        /* synthetic */ DeviceDisappearedHandler(BluetoothEventManager bluetoothEventManager, DeviceDisappearedHandler deviceDisappearedHandler) {
            this();
        }

        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            CachedBluetoothDevice cachedDevice = BluetoothEventManager.this.mDeviceManager.findDevice(device);
            if (cachedDevice == null) {
                Log.w(BluetoothEventManager.TAG, "received ACTION_DISAPPEARED for an unknown device: " + device);
            } else if (CachedBluetoothDeviceManager.onDeviceDisappeared(cachedDevice)) {
                synchronized (BluetoothEventManager.this.mCallbacks) {
                    for (BluetoothCallback callback : BluetoothEventManager.this.mCallbacks) {
                        callback.onDeviceDeleted(cachedDevice);
                    }
                }
            }
        }
    }

    private class NameChangedHandler implements Handler {
        private NameChangedHandler() {
        }

        /* synthetic */ NameChangedHandler(BluetoothEventManager bluetoothEventManager, NameChangedHandler nameChangedHandler) {
            this();
        }

        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            BluetoothEventManager.this.mDeviceManager.onDeviceNameUpdated(device);
        }
    }

    private class BondStateChangedHandler implements Handler {
        private BondStateChangedHandler() {
        }

        /* synthetic */ BondStateChangedHandler(BluetoothEventManager bluetoothEventManager, BondStateChangedHandler bondStateChangedHandler) {
            this();
        }

        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            if (device == null) {
                Log.e(BluetoothEventManager.TAG, "ACTION_BOND_STATE_CHANGED with no EXTRA_DEVICE");
                return;
            }
            int bondState = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", ExploreByTouchHelper.INVALID_ID);
            CachedBluetoothDevice cachedDevice = BluetoothEventManager.this.mDeviceManager.findDevice(device);
            if (cachedDevice == null) {
                Log.w(BluetoothEventManager.TAG, "CachedBluetoothDevice for device " + device + " not found, calling readPairedDevices().");
                if (BluetoothEventManager.this.readPairedDevices()) {
                    Log.e(BluetoothEventManager.TAG, "Got bonding state changed for " + device + ", and we have record of that device.");
                    cachedDevice = BluetoothEventManager.this.mDeviceManager.findDevice(device);
                }
                if (cachedDevice == null) {
                    Log.w(BluetoothEventManager.TAG, "Got bonding state changed for " + device + ", but we have no record of that device.");
                    cachedDevice = BluetoothEventManager.this.mDeviceManager.addDevice(BluetoothEventManager.this.mLocalAdapter, BluetoothEventManager.this.mProfileManager, device);
                    BluetoothEventManager.this.dispatchDeviceAdded(cachedDevice);
                }
            }
            synchronized (BluetoothEventManager.this.mCallbacks) {
                for (BluetoothCallback callback : BluetoothEventManager.this.mCallbacks) {
                    callback.onDeviceBondStateChanged(cachedDevice, bondState);
                }
            }
            cachedDevice.onBondingStateChanged(bondState);
            if (bondState == 10) {
                if (cachedDevice.getHiSyncId() != 0) {
                    BluetoothEventManager.this.mDeviceManager.onDeviceUnpaired(cachedDevice);
                }
                int reason = intent.getIntExtra("android.bluetooth.device.extra.REASON", ExploreByTouchHelper.INVALID_ID);
                Log.d(BluetoothEventManager.TAG, String.valueOf(cachedDevice.getName()) + " show unbond message for " + reason);
                showUnbondMessage(context, cachedDevice.getName(), reason);
            }
        }

        private void showUnbondMessage(Context context, String name, int reason) {
        }
    }

    private class ClassChangedHandler implements Handler {
        private ClassChangedHandler() {
        }

        /* synthetic */ ClassChangedHandler(BluetoothEventManager bluetoothEventManager, ClassChangedHandler classChangedHandler) {
            this();
        }

        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            BluetoothEventManager.this.mDeviceManager.onBtClassChanged(device);
        }
    }

    private class UuidChangedHandler implements Handler {
        private UuidChangedHandler() {
        }

        /* synthetic */ UuidChangedHandler(BluetoothEventManager bluetoothEventManager, UuidChangedHandler uuidChangedHandler) {
            this();
        }

        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            BluetoothEventManager.this.mDeviceManager.onUuidChanged(device);
        }
    }

    private class DockEventHandler implements Handler {
        private DockEventHandler() {
        }

        /* synthetic */ DockEventHandler(BluetoothEventManager bluetoothEventManager, DockEventHandler dockEventHandler) {
            this();
        }

        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            CachedBluetoothDevice cachedDevice;
            if (intent.getIntExtra("android.intent.extra.DOCK_STATE", 1) == 0 && device != null && device.getBondState() == 10 && (cachedDevice = BluetoothEventManager.this.mDeviceManager.findDevice(device)) != null) {
                cachedDevice.setJustDiscovered(false);
            }
        }
    }

    private class BatteryLevelChangedHandler implements Handler {
        private BatteryLevelChangedHandler() {
        }

        /* synthetic */ BatteryLevelChangedHandler(BluetoothEventManager bluetoothEventManager, BatteryLevelChangedHandler batteryLevelChangedHandler) {
            this();
        }

        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            CachedBluetoothDevice cachedDevice = BluetoothEventManager.this.mDeviceManager.findDevice(device);
            if (cachedDevice != null) {
                cachedDevice.refresh();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean readPairedDevices() {
        Set<BluetoothDevice> bondedDevices = this.mLocalAdapter.getBondedDevices();
        if (bondedDevices == null) {
            return false;
        }
        boolean deviceAdded = false;
        for (BluetoothDevice device : bondedDevices) {
            if (this.mDeviceManager.findDevice(device) == null) {
                dispatchDeviceAdded(this.mDeviceManager.addDevice(this.mLocalAdapter, this.mProfileManager, device));
                deviceAdded = true;
            }
        }
        return deviceAdded;
    }

    private class ActiveDeviceChangedHandler implements Handler {
        private ActiveDeviceChangedHandler() {
        }

        /* synthetic */ ActiveDeviceChangedHandler(BluetoothEventManager bluetoothEventManager, ActiveDeviceChangedHandler activeDeviceChangedHandler) {
            this();
        }

        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            int bluetoothProfile;
            String action = intent.getAction();
            if (action == null) {
                Log.w(BluetoothEventManager.TAG, "ActiveDeviceChangedHandler: action is null");
                return;
            }
            CachedBluetoothDevice activeDevice = BluetoothEventManager.this.mDeviceManager.findDevice(device);
            if (Objects.equals(action, "android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED")) {
                bluetoothProfile = 2;
            } else if (Objects.equals(action, "android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED")) {
                bluetoothProfile = 1;
            } else if (Objects.equals(action, "android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED")) {
                bluetoothProfile = 21;
            } else {
                Log.w(BluetoothEventManager.TAG, "ActiveDeviceChangedHandler: unknown action " + action);
                return;
            }
            BluetoothEventManager.this.dispatchActiveDeviceChanged(activeDevice, bluetoothProfile);
        }
    }

    /* access modifiers changed from: private */
    public void dispatchActiveDeviceChanged(CachedBluetoothDevice activeDevice, int bluetoothProfile) {
        this.mDeviceManager.onActiveDeviceChanged(activeDevice, bluetoothProfile);
        synchronized (this.mCallbacks) {
            for (BluetoothCallback callback : this.mCallbacks) {
                callback.onActiveDeviceChanged(activeDevice, bluetoothProfile);
            }
        }
    }

    private class AudioModeChangedHandler implements Handler {
        private AudioModeChangedHandler() {
        }

        /* synthetic */ AudioModeChangedHandler(BluetoothEventManager bluetoothEventManager, AudioModeChangedHandler audioModeChangedHandler) {
            this();
        }

        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            if (intent.getAction() == null) {
                Log.w(BluetoothEventManager.TAG, "AudioModeChangedHandler() action is null");
            } else {
                BluetoothEventManager.this.dispatchAudioModeChanged();
            }
        }
    }

    /* access modifiers changed from: private */
    public void dispatchAudioModeChanged() {
        this.mDeviceManager.dispatchAudioModeChanged();
        synchronized (this.mCallbacks) {
            for (BluetoothCallback callback : this.mCallbacks) {
                callback.onAudioModeChanged();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchProfileConnectionStateChanged(CachedBluetoothDevice device, int state, int bluetoothProfile) {
        synchronized (this.mCallbacks) {
            for (BluetoothCallback callback : this.mCallbacks) {
                callback.onProfileConnectionStateChanged(device, state, bluetoothProfile);
            }
        }
        this.mDeviceManager.onProfileConnectionStateChanged(device, state, bluetoothProfile);
    }
}
