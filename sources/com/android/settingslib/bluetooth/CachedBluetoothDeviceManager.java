package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CachedBluetoothDeviceManager {
    private static final boolean DEBUG = true;
    private static final String TAG = "CachedBluetoothDeviceManager";
    private final LocalBluetoothManager mBtManager;
    @VisibleForTesting
    final List<CachedBluetoothDevice> mCachedDevices = new ArrayList();
    @VisibleForTesting
    final Map<Long, CachedBluetoothDevice> mCachedDevicesMapForHearingAids = new HashMap();
    private Context mContext;
    @VisibleForTesting
    final List<CachedBluetoothDevice> mHearingAidDevicesNotAddedInCache = new ArrayList();

    CachedBluetoothDeviceManager(Context context, LocalBluetoothManager localBtManager) {
        this.mContext = context;
        this.mBtManager = localBtManager;
    }

    public synchronized Collection<CachedBluetoothDevice> getCachedDevicesCopy() {
        return new ArrayList(this.mCachedDevices);
    }

    public static boolean onDeviceDisappeared(CachedBluetoothDevice cachedDevice) {
        cachedDevice.setJustDiscovered(false);
        if (cachedDevice.getBondState() == 10) {
            return true;
        }
        return false;
    }

    public void onDeviceNameUpdated(BluetoothDevice device) {
        CachedBluetoothDevice cachedDevice = findDevice(device);
        if (cachedDevice != null) {
            cachedDevice.refreshName();
        }
    }

    public synchronized CachedBluetoothDevice findDevice(BluetoothDevice device) {
        CachedBluetoothDevice cachedDevice;
        Iterator<CachedBluetoothDevice> it = this.mCachedDevices.iterator();
        while (true) {
            if (it.hasNext()) {
                cachedDevice = it.next();
                if (cachedDevice.getDevice().equals(device)) {
                    break;
                }
            } else {
                Iterator<CachedBluetoothDevice> it2 = this.mHearingAidDevicesNotAddedInCache.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        CachedBluetoothDevice notCachedDevice = it2.next();
                        if (notCachedDevice.getDevice().equals(device)) {
                            cachedDevice = notCachedDevice;
                            break;
                        }
                    } else {
                        cachedDevice = null;
                        break;
                    }
                }
            }
        }
        return cachedDevice;
    }

    public CachedBluetoothDevice addDevice(LocalBluetoothAdapter adapter, LocalBluetoothProfileManager profileManager, BluetoothDevice device) {
        CachedBluetoothDevice newDevice = new CachedBluetoothDevice(this.mContext, adapter, profileManager, device);
        if (!(profileManager.getHearingAidProfile() == null || profileManager.getHearingAidProfile().getHiSyncId(newDevice.getDevice()) == 0)) {
            newDevice.setHiSyncId(profileManager.getHearingAidProfile().getHiSyncId(newDevice.getDevice()));
        }
        if (isPairAddedInCache(newDevice.getHiSyncId())) {
            synchronized (this) {
                this.mHearingAidDevicesNotAddedInCache.add(newDevice);
            }
        } else {
            synchronized (this) {
                this.mCachedDevices.add(newDevice);
                if (newDevice.getHiSyncId() != 0 && !this.mCachedDevicesMapForHearingAids.containsKey(Long.valueOf(newDevice.getHiSyncId()))) {
                    this.mCachedDevicesMapForHearingAids.put(Long.valueOf(newDevice.getHiSyncId()), newDevice);
                }
                this.mBtManager.getEventManager().dispatchDeviceAdded(newDevice);
            }
        }
        return newDevice;
    }

    private synchronized boolean isPairAddedInCache(long hiSyncId) {
        boolean z = false;
        synchronized (this) {
            if (hiSyncId != 0) {
                if (this.mCachedDevicesMapForHearingAids.containsKey(Long.valueOf(hiSyncId))) {
                    z = true;
                }
            }
        }
        return z;
    }

    public synchronized String getHearingAidPairDeviceSummary(CachedBluetoothDevice device) {
        String pairDeviceSummary;
        pairDeviceSummary = null;
        if (device.getHiSyncId() != 0) {
            for (CachedBluetoothDevice hearingAidDevice : this.mHearingAidDevicesNotAddedInCache) {
                if (hearingAidDevice.getHiSyncId() != 0 && hearingAidDevice.getHiSyncId() == device.getHiSyncId()) {
                    pairDeviceSummary = hearingAidDevice.getConnectionSummary();
                }
            }
        }
        return pairDeviceSummary;
    }

    public synchronized void addDeviceNotaddedInMap(CachedBluetoothDevice device) {
        this.mHearingAidDevicesNotAddedInCache.add(device);
    }

    public synchronized void updateHearingAidsDevices(LocalBluetoothProfileManager profileManager) {
        HearingAidProfile profileProxy = profileManager.getHearingAidProfile();
        if (profileProxy == null) {
            log("updateHearingAidsDevices: getHearingAidProfile() is null");
        } else {
            Set<Long> syncIdChangedSet = new HashSet<>();
            for (CachedBluetoothDevice cachedDevice : this.mCachedDevices) {
                if (cachedDevice.getHiSyncId() == 0) {
                    long newHiSyncId = profileProxy.getHiSyncId(cachedDevice.getDevice());
                    if (newHiSyncId != 0) {
                        cachedDevice.setHiSyncId(newHiSyncId);
                        syncIdChangedSet.add(Long.valueOf(newHiSyncId));
                    }
                }
            }
            for (Long syncId : syncIdChangedSet) {
                onHiSyncIdChanged(syncId.longValue());
            }
        }
    }

    public String getName(BluetoothDevice device) {
        CachedBluetoothDevice cachedDevice = findDevice(device);
        if (cachedDevice != null && cachedDevice.getName() != null) {
            return cachedDevice.getName();
        }
        String name = device.getAliasName();
        return name == null ? device.getAddress() : name;
    }

    public synchronized void clearNonBondedDevices() {
    }

    public synchronized void onScanningStateChanged(boolean started) {
        if (started) {
            for (int i = this.mCachedDevices.size() - 1; i >= 0; i--) {
                this.mCachedDevices.get(i).setJustDiscovered(false);
            }
            for (int i2 = this.mHearingAidDevicesNotAddedInCache.size() - 1; i2 >= 0; i2--) {
                this.mHearingAidDevicesNotAddedInCache.get(i2).setJustDiscovered(false);
            }
        }
    }

    public synchronized void onBtClassChanged(BluetoothDevice device) {
        CachedBluetoothDevice cachedDevice = findDevice(device);
        if (cachedDevice != null) {
            cachedDevice.refreshBtClass();
        }
    }

    public synchronized void onUuidChanged(BluetoothDevice device) {
        CachedBluetoothDevice cachedDevice = findDevice(device);
        if (cachedDevice != null) {
            cachedDevice.onUuidChanged();
        }
    }

    public synchronized void onBluetoothStateChanged(int bluetoothState) {
        if (bluetoothState == 13) {
            for (int i = this.mCachedDevices.size() - 1; i >= 0; i--) {
                CachedBluetoothDevice cachedDevice = this.mCachedDevices.get(i);
                if (cachedDevice.getBondState() != 12) {
                    Log.d(TAG, "Remove device for bond state : " + cachedDevice.getBondState() + "     and device name is : " + cachedDevice.getName());
                    cachedDevice.setJustDiscovered(false);
                    this.mCachedDevices.remove(i);
                    if (cachedDevice.getHiSyncId() != 0 && this.mCachedDevicesMapForHearingAids.containsKey(Long.valueOf(cachedDevice.getHiSyncId()))) {
                        this.mCachedDevicesMapForHearingAids.remove(Long.valueOf(cachedDevice.getHiSyncId()));
                    }
                } else {
                    cachedDevice.clearProfileConnectionState();
                }
            }
            for (int i2 = this.mHearingAidDevicesNotAddedInCache.size() - 1; i2 >= 0; i2--) {
                CachedBluetoothDevice notCachedDevice = this.mHearingAidDevicesNotAddedInCache.get(i2);
                if (notCachedDevice.getBondState() != 12) {
                    notCachedDevice.setJustDiscovered(false);
                    this.mHearingAidDevicesNotAddedInCache.remove(i2);
                } else {
                    notCachedDevice.clearProfileConnectionState();
                }
            }
        }
    }

    public synchronized void onActiveDeviceChanged(CachedBluetoothDevice activeDevice, int bluetoothProfile) {
        for (CachedBluetoothDevice cachedDevice : this.mCachedDevices) {
            cachedDevice.onActiveDeviceChanged(Objects.equals(cachedDevice, activeDevice), bluetoothProfile);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r0.isConnected() == false) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        r4 = r2;
        r1 = r8.mCachedDevices.get(r2);
        r8.mCachedDevicesMapForHearingAids.put(java.lang.Long.valueOf(r9), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
        r8.mCachedDevices.remove(r4);
        r8.mHearingAidDevicesNotAddedInCache.add(r1);
        log("onHiSyncIdChanged: removed from UI device=" + r1 + ", with hiSyncId=" + r9);
        r8.mBtManager.getEventManager().dispatchDeviceRemoved(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006e, code lost:
        r4 = r3;
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r8.mCachedDevicesMapForHearingAids.put(java.lang.Long.valueOf(r9), r8.mCachedDevices.get(r2));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onHiSyncIdChanged(long r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            r2 = -1
            java.util.List<com.android.settingslib.bluetooth.CachedBluetoothDevice> r5 = r8.mCachedDevices     // Catch:{ all -> 0x006b }
            int r5 = r5.size()     // Catch:{ all -> 0x006b }
            int r3 = r5 + -1
        L_0x000a:
            if (r3 >= 0) goto L_0x000e
        L_0x000c:
            monitor-exit(r8)
            return
        L_0x000e:
            java.util.List<com.android.settingslib.bluetooth.CachedBluetoothDevice> r5 = r8.mCachedDevices     // Catch:{ all -> 0x006b }
            java.lang.Object r0 = r5.get(r3)     // Catch:{ all -> 0x006b }
            com.android.settingslib.bluetooth.CachedBluetoothDevice r0 = (com.android.settingslib.bluetooth.CachedBluetoothDevice) r0     // Catch:{ all -> 0x006b }
            long r5 = r0.getHiSyncId()     // Catch:{ all -> 0x006b }
            int r5 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r5 != 0) goto L_0x008c
            r5 = -1
            if (r2 == r5) goto L_0x0082
            boolean r5 = r0.isConnected()     // Catch:{ all -> 0x006b }
            if (r5 == 0) goto L_0x006e
            r4 = r2
            java.util.List<com.android.settingslib.bluetooth.CachedBluetoothDevice> r5 = r8.mCachedDevices     // Catch:{ all -> 0x006b }
            java.lang.Object r1 = r5.get(r2)     // Catch:{ all -> 0x006b }
            com.android.settingslib.bluetooth.CachedBluetoothDevice r1 = (com.android.settingslib.bluetooth.CachedBluetoothDevice) r1     // Catch:{ all -> 0x006b }
            java.util.Map<java.lang.Long, com.android.settingslib.bluetooth.CachedBluetoothDevice> r5 = r8.mCachedDevicesMapForHearingAids     // Catch:{ all -> 0x006b }
            java.lang.Long r6 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x006b }
            r5.put(r6, r0)     // Catch:{ all -> 0x006b }
        L_0x0039:
            java.util.List<com.android.settingslib.bluetooth.CachedBluetoothDevice> r5 = r8.mCachedDevices     // Catch:{ all -> 0x006b }
            r5.remove(r4)     // Catch:{ all -> 0x006b }
            java.util.List<com.android.settingslib.bluetooth.CachedBluetoothDevice> r5 = r8.mHearingAidDevicesNotAddedInCache     // Catch:{ all -> 0x006b }
            r5.add(r1)     // Catch:{ all -> 0x006b }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x006b }
            java.lang.String r6 = "onHiSyncIdChanged: removed from UI device="
            r5.<init>(r6)     // Catch:{ all -> 0x006b }
            java.lang.StringBuilder r5 = r5.append(r1)     // Catch:{ all -> 0x006b }
            java.lang.String r6 = ", with hiSyncId="
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x006b }
            java.lang.StringBuilder r5 = r5.append(r9)     // Catch:{ all -> 0x006b }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x006b }
            r8.log(r5)     // Catch:{ all -> 0x006b }
            com.android.settingslib.bluetooth.LocalBluetoothManager r5 = r8.mBtManager     // Catch:{ all -> 0x006b }
            com.android.settingslib.bluetooth.BluetoothEventManager r5 = r5.getEventManager()     // Catch:{ all -> 0x006b }
            r5.dispatchDeviceRemoved(r1)     // Catch:{ all -> 0x006b }
            goto L_0x000c
        L_0x006b:
            r5 = move-exception
            monitor-exit(r8)
            throw r5
        L_0x006e:
            r4 = r3
            r1 = r0
            java.util.Map<java.lang.Long, com.android.settingslib.bluetooth.CachedBluetoothDevice> r6 = r8.mCachedDevicesMapForHearingAids     // Catch:{ all -> 0x006b }
            java.lang.Long r7 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x006b }
            java.util.List<com.android.settingslib.bluetooth.CachedBluetoothDevice> r5 = r8.mCachedDevices     // Catch:{ all -> 0x006b }
            java.lang.Object r5 = r5.get(r2)     // Catch:{ all -> 0x006b }
            com.android.settingslib.bluetooth.CachedBluetoothDevice r5 = (com.android.settingslib.bluetooth.CachedBluetoothDevice) r5     // Catch:{ all -> 0x006b }
            r6.put(r7, r5)     // Catch:{ all -> 0x006b }
            goto L_0x0039
        L_0x0082:
            java.util.Map<java.lang.Long, com.android.settingslib.bluetooth.CachedBluetoothDevice> r5 = r8.mCachedDevicesMapForHearingAids     // Catch:{ all -> 0x006b }
            java.lang.Long r6 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x006b }
            r5.put(r6, r0)     // Catch:{ all -> 0x006b }
            r2 = r3
        L_0x008c:
            int r3 = r3 + -1
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.bluetooth.CachedBluetoothDeviceManager.onHiSyncIdChanged(long):void");
    }

    private CachedBluetoothDevice getHearingAidOtherDevice(CachedBluetoothDevice thisDevice, long hiSyncId) {
        if (hiSyncId == 0) {
            return null;
        }
        for (CachedBluetoothDevice notCachedDevice : this.mHearingAidDevicesNotAddedInCache) {
            if (hiSyncId == notCachedDevice.getHiSyncId() && !Objects.equals(notCachedDevice, thisDevice)) {
                return notCachedDevice;
            }
        }
        CachedBluetoothDevice cachedDevice = this.mCachedDevicesMapForHearingAids.get(Long.valueOf(hiSyncId));
        if (!Objects.equals(cachedDevice, thisDevice)) {
            return cachedDevice;
        }
        return null;
    }

    private void hearingAidSwitchDisplayDevice(CachedBluetoothDevice toDisplayDevice, CachedBluetoothDevice toHideDevice, long hiSyncId) {
        log("hearingAidSwitchDisplayDevice: toDisplayDevice=" + toDisplayDevice + ", toHideDevice=" + toHideDevice);
        this.mHearingAidDevicesNotAddedInCache.add(toHideDevice);
        this.mCachedDevices.remove(toHideDevice);
        this.mBtManager.getEventManager().dispatchDeviceRemoved(toHideDevice);
        this.mHearingAidDevicesNotAddedInCache.remove(toDisplayDevice);
        this.mCachedDevices.add(toDisplayDevice);
        this.mCachedDevicesMapForHearingAids.put(Long.valueOf(hiSyncId), toDisplayDevice);
        this.mBtManager.getEventManager().dispatchDeviceAdded(toDisplayDevice);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r0 = r9.getHiSyncId();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onProfileConnectionStateChanged(com.android.settingslib.bluetooth.CachedBluetoothDevice r9, int r10, int r11) {
        /*
            r8 = this;
            monitor-enter(r8)
            r4 = 21
            if (r11 != r4) goto L_0x0021
            long r4 = r9.getHiSyncId()     // Catch:{ all -> 0x0032 }
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x0021
            int r4 = r9.getBondState()     // Catch:{ all -> 0x0032 }
            r5 = 12
            if (r4 != r5) goto L_0x0021
            long r0 = r9.getHiSyncId()     // Catch:{ all -> 0x0032 }
            com.android.settingslib.bluetooth.CachedBluetoothDevice r3 = r8.getHearingAidOtherDevice(r9, r0)     // Catch:{ all -> 0x0032 }
            if (r3 != 0) goto L_0x0023
        L_0x0021:
            monitor-exit(r8)
            return
        L_0x0023:
            r4 = 2
            if (r10 != r4) goto L_0x0035
            java.util.List<com.android.settingslib.bluetooth.CachedBluetoothDevice> r4 = r8.mHearingAidDevicesNotAddedInCache     // Catch:{ all -> 0x0032 }
            boolean r4 = r4.contains(r9)     // Catch:{ all -> 0x0032 }
            if (r4 == 0) goto L_0x0035
            r8.hearingAidSwitchDisplayDevice(r9, r3, r0)     // Catch:{ all -> 0x0032 }
            goto L_0x0021
        L_0x0032:
            r4 = move-exception
            monitor-exit(r8)
            throw r4
        L_0x0035:
            if (r10 != 0) goto L_0x0021
            boolean r4 = r3.isConnected()     // Catch:{ all -> 0x0032 }
            if (r4 == 0) goto L_0x0021
            java.util.Map<java.lang.Long, com.android.settingslib.bluetooth.CachedBluetoothDevice> r4 = r8.mCachedDevicesMapForHearingAids     // Catch:{ all -> 0x0032 }
            java.lang.Long r5 = java.lang.Long.valueOf(r0)     // Catch:{ all -> 0x0032 }
            java.lang.Object r2 = r4.get(r5)     // Catch:{ all -> 0x0032 }
            com.android.settingslib.bluetooth.CachedBluetoothDevice r2 = (com.android.settingslib.bluetooth.CachedBluetoothDevice) r2     // Catch:{ all -> 0x0032 }
            if (r2 == 0) goto L_0x0021
            boolean r4 = java.util.Objects.equals(r9, r2)     // Catch:{ all -> 0x0032 }
            if (r4 == 0) goto L_0x0021
            r8.hearingAidSwitchDisplayDevice(r3, r9, r0)     // Catch:{ all -> 0x0032 }
            goto L_0x0021
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.bluetooth.CachedBluetoothDeviceManager.onProfileConnectionStateChanged(com.android.settingslib.bluetooth.CachedBluetoothDevice, int, int):void");
    }

    public synchronized void onDeviceUnpaired(CachedBluetoothDevice device) {
        long hiSyncId = device.getHiSyncId();
        if (hiSyncId != 0) {
            for (int i = this.mHearingAidDevicesNotAddedInCache.size() - 1; i >= 0; i--) {
                CachedBluetoothDevice cachedDevice = this.mHearingAidDevicesNotAddedInCache.get(i);
                if (cachedDevice.getHiSyncId() == hiSyncId) {
                    this.mHearingAidDevicesNotAddedInCache.remove(i);
                    if (device != cachedDevice) {
                        log("onDeviceUnpaired: Unpair device=" + cachedDevice);
                        cachedDevice.unpair();
                    }
                }
            }
            CachedBluetoothDevice mappedDevice = this.mCachedDevicesMapForHearingAids.get(Long.valueOf(hiSyncId));
            if (mappedDevice != null && !Objects.equals(device, mappedDevice)) {
                log("onDeviceUnpaired: Unpair mapped device=" + mappedDevice);
                mappedDevice.unpair();
            }
        }
    }

    public synchronized void dispatchAudioModeChanged() {
        for (CachedBluetoothDevice cachedDevice : this.mCachedDevices) {
            cachedDevice.onAudioModeChanged();
        }
    }

    private void log(String msg) {
        Log.d(TAG, msg);
    }
}
