package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.ParcelUuid;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.txznet.sdk.TXZResourceManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CachedBluetoothDevice implements Comparable<CachedBluetoothDevice> {
    public static final int ACCESS_ALLOWED = 1;
    public static final int ACCESS_REJECTED = 2;
    public static final int ACCESS_UNKNOWN = 0;
    private static final boolean DEBUG = false;
    private static final long MAX_HOGP_DELAY_FOR_AUTO_CONNECT = 30000;
    private static final long MAX_UUID_DELAY_FOR_AUTO_CONNECT = 10000;
    private static final int MESSAGE_REJECTION_COUNT_LIMIT_TO_PERSIST = 2;
    private static final String MESSAGE_REJECTION_COUNT_PREFS_NAME = "bluetooth_message_reject";
    private static final String TAG = "CachedBluetoothDevice";
    private final AudioManager mAudioManager;
    private BluetoothClass mBtClass;
    private final Collection<Callback> mCallbacks = new ArrayList();
    private long mConnectAttempted;
    private final Context mContext;
    private final BluetoothDevice mDevice;
    private long mHiSyncId;
    private boolean mIsActiveDeviceA2dp = false;
    private boolean mIsActiveDeviceHeadset = false;
    private boolean mIsActiveDeviceHearingAid = false;
    private boolean mIsConnectingErrorPossible;
    private boolean mJustDiscovered;
    private final LocalBluetoothAdapter mLocalAdapter;
    private boolean mLocalNapRoleConnected;
    private int mMessageRejectionCount;
    private String mName;
    private HashMap<LocalBluetoothProfile, Integer> mProfileConnectionState;
    private final LocalBluetoothProfileManager mProfileManager;
    private final List<LocalBluetoothProfile> mProfiles = new ArrayList();
    private final List<LocalBluetoothProfile> mRemovedProfiles = new ArrayList();
    private short mRssi;

    public interface Callback {
        void onDeviceAttributesChanged();
    }

    public long getHiSyncId() {
        return this.mHiSyncId;
    }

    public void setHiSyncId(long id) {
        Log.d(TAG, "setHiSyncId: mDevice " + this.mDevice + ", id " + id);
        this.mHiSyncId = id;
    }

    private String describe(LocalBluetoothProfile profile) {
        StringBuilder sb = new StringBuilder();
        sb.append("Address:").append(this.mDevice);
        if (profile != null) {
            sb.append(" Profile:").append(profile);
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void onProfileStateChanged(LocalBluetoothProfile profile, int newProfileState) {
        Log.d(TAG, "onProfileStateChanged: profile " + profile + " newProfileState " + newProfileState);
        if (this.mLocalAdapter.getBluetoothState() == 13) {
            Log.d(TAG, " BT Turninig Off...Profile conn state change ignored...");
            return;
        }
        this.mProfileConnectionState.put(profile, Integer.valueOf(newProfileState));
        if (newProfileState == 2) {
            if (profile instanceof MapProfile) {
                profile.setPreferred(this.mDevice, true);
            }
            if (!this.mProfiles.contains(profile)) {
                this.mRemovedProfiles.remove(profile);
                this.mProfiles.add(profile);
                if ((profile instanceof PanProfile) && ((PanProfile) profile).isLocalRoleNap(this.mDevice)) {
                    this.mLocalNapRoleConnected = true;
                }
            }
        } else if ((profile instanceof MapProfile) && newProfileState == 0) {
            profile.setPreferred(this.mDevice, false);
            refresh();
        } else if (this.mLocalNapRoleConnected && (profile instanceof PanProfile) && ((PanProfile) profile).isLocalRoleNap(this.mDevice) && newProfileState == 0) {
            Log.d(TAG, "Removing PanProfile from device after NAP disconnect");
            this.mProfiles.remove(profile);
            this.mRemovedProfiles.add(profile);
            this.mLocalNapRoleConnected = false;
        }
        fetchActiveDevices();
    }

    CachedBluetoothDevice(Context context, LocalBluetoothAdapter adapter, LocalBluetoothProfileManager profileManager, BluetoothDevice device) {
        this.mContext = context;
        this.mLocalAdapter = adapter;
        this.mProfileManager = profileManager;
        this.mAudioManager = (AudioManager) context.getSystemService(AudioManager.class);
        this.mDevice = device;
        this.mProfileConnectionState = new HashMap<>();
        fillData();
        this.mHiSyncId = 0;
    }

    public void disconnect() {
        for (LocalBluetoothProfile profile : this.mProfiles) {
            if (profile != null) {
                disconnect(profile);
            } else {
                Log.d(TAG, "disconnect profile is null");
            }
        }
        PbapServerProfile PbapProfile = this.mProfileManager.getPbapProfile();
        if (PbapProfile != null && PbapProfile.getConnectionStatus(this.mDevice) == 2) {
            PbapProfile.disconnect(this.mDevice);
        }
    }

    public void disconnect(LocalBluetoothProfile profile) {
        if (profile.disconnect(this.mDevice)) {
            Log.d(TAG, "Command sent successfully:DISCONNECT " + describe(profile));
        }
    }

    public void connect(boolean connectAllProfiles) {
        if (ensurePaired()) {
            this.mConnectAttempted = SystemClock.elapsedRealtime();
            connectWithoutResettingTimer(connectAllProfiles);
        }
    }

    /* access modifiers changed from: package-private */
    public void onBondingDockConnect() {
        connect(false);
    }

    private void connectWithoutResettingTimer(boolean connectAllProfiles) {
        if (this.mProfiles.isEmpty()) {
            Log.d(TAG, "No profiles. Maybe we will connect later");
            return;
        }
        this.mIsConnectingErrorPossible = true;
        int preferredProfiles = 0;
        for (LocalBluetoothProfile profile : this.mProfiles) {
            if (profile != null) {
                if (connectAllProfiles) {
                    if (!profile.isConnectable()) {
                    }
                } else if (!profile.isAutoConnectable()) {
                }
                Log.d(TAG, String.valueOf(describe(profile)) + " isPreferred : " + profile.isPreferred(this.mDevice));
                if (profile.isPreferred(this.mDevice)) {
                    preferredProfiles++;
                    connectInt(profile);
                }
            } else {
                Log.d(TAG, "connectWithoutResettingTimer profile is null");
            }
        }
        if (preferredProfiles == 0) {
            connectAutoConnectableProfiles();
        }
    }

    private void connectAutoConnectableProfiles() {
        if (ensurePaired()) {
            this.mIsConnectingErrorPossible = true;
            for (LocalBluetoothProfile profile : this.mProfiles) {
                if (profile == null) {
                    Log.d(TAG, "profile is null");
                } else if (profile.isAutoConnectable()) {
                    profile.setPreferred(this.mDevice, true);
                    Log.d(TAG, String.valueOf(describe(profile)) + " setPreferred true and connect");
                    connectInt(profile);
                }
            }
        }
    }

    public void connectProfile(LocalBluetoothProfile profile) {
        this.mConnectAttempted = SystemClock.elapsedRealtime();
        this.mIsConnectingErrorPossible = true;
        connectInt(profile);
        refresh();
    }

    /* access modifiers changed from: package-private */
    public synchronized void connectInt(LocalBluetoothProfile profile) {
        if (ensurePaired()) {
            if (profile.connect(this.mDevice)) {
                Log.d(TAG, "Command sent successfully:CONNECT " + describe(profile));
            } else {
                Log.i(TAG, "Failed to connect " + profile.toString() + " to " + this.mName);
            }
        }
    }

    private boolean ensurePaired() {
        if (getBondState() != 10) {
            return true;
        }
        startPairing();
        return false;
    }

    public boolean startPairing() {
        if (this.mLocalAdapter.isDiscovering()) {
            this.mLocalAdapter.cancelDiscovery();
        }
        if (!this.mDevice.createBond()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isUserInitiatedPairing() {
        return this.mDevice.isBondingInitiatedLocally();
    }

    public void unpair() {
        BluetoothDevice dev;
        int state = getBondState();
        if (state == 11) {
            this.mDevice.cancelBondProcess();
        }
        if (state != 10 && (dev = this.mDevice) != null && dev.removeBond()) {
            Log.d(TAG, "Command sent successfully:REMOVE_BOND " + describe((LocalBluetoothProfile) null));
        }
    }

    public int getProfileConnectionState(LocalBluetoothProfile profile) {
        if (this.mProfileConnectionState.get(profile) == null) {
            if (profile == null) {
                return 0;
            }
            int state = profile.getConnectionStatus(this.mDevice);
            Log.d(TAG, String.valueOf(describe(profile)) + " state : " + state);
            this.mProfileConnectionState.put(profile, Integer.valueOf(state));
        }
        return this.mProfileConnectionState.get(profile).intValue();
    }

    public void clearProfileConnectionState() {
        Log.d(TAG, " Clearing all connection state for dev:" + this.mDevice.getName());
        for (LocalBluetoothProfile profile : getProfiles()) {
            this.mProfileConnectionState.put(profile, 0);
        }
    }

    private void fillData() {
        fetchName();
        fetchBtClass();
        updateProfiles();
        fetchActiveDevices();
        migratePhonebookPermissionChoice();
        migrateMessagePermissionChoice();
        fetchMessageRejectionCount();
        dispatchAttributesChanged();
    }

    public BluetoothDevice getDevice() {
        return this.mDevice;
    }

    public String getAddress() {
        return this.mDevice.getAddress();
    }

    public String getName() {
        return this.mName;
    }

    /* access modifiers changed from: package-private */
    public void setNewName(String name) {
        if (this.mName == null) {
            this.mName = name;
            if (this.mName == null || TextUtils.isEmpty(this.mName)) {
                this.mName = this.mDevice.getAddress();
            }
            dispatchAttributesChanged();
        }
    }

    public void setName(String name) {
        if (name != null && !TextUtils.equals(name, this.mName)) {
            this.mName = name;
            this.mDevice.setAlias(name);
            dispatchAttributesChanged();
        }
    }

    public boolean setActive() {
        boolean result = false;
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        if (a2dpProfile != null && isConnectedProfile(a2dpProfile) && a2dpProfile.setActiveDevice(getDevice())) {
            Log.i(TAG, "OnPreferenceClickListener: A2DP active device=" + this);
            result = true;
        }
        HeadsetProfile headsetProfile = this.mProfileManager.getHeadsetProfile();
        if (headsetProfile != null && isConnectedProfile(headsetProfile) && headsetProfile.setActiveDevice(getDevice())) {
            Log.i(TAG, "OnPreferenceClickListener: Headset active device=" + this);
            result = true;
        }
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        if (hearingAidProfile == null || !isConnectedProfile(hearingAidProfile) || !hearingAidProfile.setActiveDevice(getDevice())) {
            return result;
        }
        Log.i(TAG, "OnPreferenceClickListener: Hearing Aid active device=" + this);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void refreshName() {
        fetchName();
        dispatchAttributesChanged();
    }

    private void fetchName() {
        this.mName = this.mDevice.getAliasName();
        Log.d(TAG, "fetchName, AlaisName is " + this.mName);
        if (TextUtils.isEmpty(this.mName)) {
            this.mName = this.mDevice.getAddress();
        }
        Log.d(TAG, "fetchName, Return Name " + this.mName);
    }

    public boolean hasHumanReadableName() {
        return !TextUtils.isEmpty(this.mDevice.getAliasName());
    }

    public int getBatteryLevel() {
        return this.mDevice.getBatteryLevel();
    }

    /* access modifiers changed from: package-private */
    public void refresh() {
        dispatchAttributesChanged();
    }

    public void setJustDiscovered(boolean justDiscovered) {
        if (this.mJustDiscovered != justDiscovered) {
            this.mJustDiscovered = justDiscovered;
            dispatchAttributesChanged();
        }
    }

    public int getBondState() {
        return this.mDevice.getBondState();
    }

    public void onActiveDeviceChanged(boolean isActive, int bluetoothProfile) {
        boolean changed = false;
        switch (bluetoothProfile) {
            case 1:
                changed = this.mIsActiveDeviceHeadset ^ isActive;
                this.mIsActiveDeviceHeadset = isActive;
                break;
            case 2:
                changed = this.mIsActiveDeviceA2dp ^ isActive;
                this.mIsActiveDeviceA2dp = isActive;
                break;
            case 21:
                changed = this.mIsActiveDeviceHearingAid ^ isActive;
                this.mIsActiveDeviceHearingAid = isActive;
                break;
            default:
                Log.w(TAG, "onActiveDeviceChanged: unknown profile " + bluetoothProfile + " isActive " + isActive);
                break;
        }
        if (changed) {
            dispatchAttributesChanged();
        }
    }

    /* access modifiers changed from: package-private */
    public void onAudioModeChanged() {
        dispatchAttributesChanged();
    }

    /* access modifiers changed from: package-private */
    public void setRssi(short rssi) {
        if (this.mRssi != rssi) {
            this.mRssi = rssi;
            dispatchAttributesChanged();
        }
    }

    public boolean isConnected() {
        for (LocalBluetoothProfile profile : this.mProfiles) {
            if (profile == null) {
                Log.d(TAG, "isConnected profile is null");
            } else if (getProfileConnectionState(profile) == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isConnectedProfile(LocalBluetoothProfile profile) {
        return getProfileConnectionState(profile) == 2;
    }

    public boolean isBusy() {
        for (LocalBluetoothProfile profile : this.mProfiles) {
            if (profile != null) {
                int status = getProfileConnectionState(profile);
                if (status == 1 || status == 3) {
                    return true;
                }
            } else {
                Log.d(TAG, "isBusy profile is null");
            }
        }
        Log.d(TAG, String.valueOf(this.mName) + " bond state is " + getBondState());
        if (getBondState() == 11) {
            return true;
        }
        return false;
    }

    private void fetchBtClass() {
        this.mBtClass = this.mDevice.getBluetoothClass();
        if (this.mBtClass == null) {
            Log.d(TAG, "fetchClass, mBtClass is null");
            return;
        }
        Log.d(TAG, "fetchClass, mBtClass is " + this.mBtClass.getMajorDeviceClass());
    }

    private boolean updateProfiles() {
        ParcelUuid[] uuids = this.mDevice.getUuids();
        if (uuids == null) {
            Log.d(TAG, "Bluetooth device get uuid is null");
            return false;
        }
        ParcelUuid[] localUuids = this.mLocalAdapter.getUuids();
        if (localUuids == null) {
            Log.d(TAG, "Bluetooth Adapter get uuid is null");
            return false;
        }
        processPhonebookAccess();
        Log.d(TAG, String.valueOf(this.mName) + " update profiles");
        this.mProfileManager.updateProfiles(uuids, localUuids, this.mProfiles, this.mRemovedProfiles, this.mLocalNapRoleConnected, this.mDevice);
        return true;
    }

    private void fetchActiveDevices() {
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        if (a2dpProfile != null) {
            this.mIsActiveDeviceA2dp = this.mDevice.equals(a2dpProfile.getActiveDevice());
        }
        HeadsetProfile headsetProfile = this.mProfileManager.getHeadsetProfile();
        if (headsetProfile != null) {
            this.mIsActiveDeviceHeadset = this.mDevice.equals(headsetProfile.getActiveDevice());
        }
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        if (hearingAidProfile != null) {
            this.mIsActiveDeviceHearingAid = hearingAidProfile.getActiveDevices().contains(this.mDevice);
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshBtClass() {
        fetchBtClass();
        dispatchAttributesChanged();
    }

    /* access modifiers changed from: package-private */
    public void onUuidChanged() {
        updateProfiles();
        ParcelUuid[] uuids = this.mDevice.getUuids();
        long timeout = MAX_UUID_DELAY_FOR_AUTO_CONNECT;
        if (BluetoothUuid.isUuidPresent(uuids, BluetoothUuid.Hogp)) {
            timeout = MAX_HOGP_DELAY_FOR_AUTO_CONNECT;
        }
        if (!this.mProfiles.isEmpty() && this.mConnectAttempted + timeout > SystemClock.elapsedRealtime()) {
            connectWithoutResettingTimer(false);
        }
        dispatchAttributesChanged();
    }

    /* access modifiers changed from: package-private */
    public void onBondingStateChanged(int bondState) {
        Log.d(TAG, "onBondingStateChanged to " + bondState);
        if (bondState == 10) {
            this.mProfiles.clear();
            setPhonebookPermissionChoice(0);
            setMessagePermissionChoice(0);
            setSimPermissionChoice(0);
            this.mMessageRejectionCount = 0;
            saveMessageRejectionCount();
        }
        refresh();
        if (bondState == 12) {
            Log.d(TAG, "Bond state changed to bonded, mDevice.isBondingInitiatedLocally() is " + this.mDevice.isBondingInitiatedLocally());
            if (this.mDevice.isBluetoothDock()) {
                onBondingDockConnect();
            } else if (this.mDevice.isBondingInitiatedLocally()) {
                connect(false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setBtClass(BluetoothClass btClass) {
        if (btClass != null && this.mBtClass != btClass) {
            this.mBtClass = btClass;
            dispatchAttributesChanged();
        }
    }

    public BluetoothClass getBtClass() {
        return this.mBtClass;
    }

    public List<LocalBluetoothProfile> getProfiles() {
        return Collections.unmodifiableList(this.mProfiles);
    }

    public List<LocalBluetoothProfile> getConnectableProfiles() {
        Log.d(TAG, String.valueOf(this.mName) + " mprofile size is " + this.mProfiles.size());
        List<LocalBluetoothProfile> connectableProfiles = new ArrayList<>();
        for (LocalBluetoothProfile profile : this.mProfiles) {
            if (profile == null) {
                Log.d(TAG, "getConnectableProfiles profile is null");
            } else if (profile.isConnectable()) {
                connectableProfiles.add(profile);
            }
        }
        Log.d(TAG, String.valueOf(this.mName) + " conectable profile size is " + connectableProfiles.size());
        return connectableProfiles;
    }

    public List<LocalBluetoothProfile> getRemovedProfiles() {
        return this.mRemovedProfiles;
    }

    public void registerCallback(Callback callback) {
        synchronized (this.mCallbacks) {
            this.mCallbacks.add(callback);
        }
    }

    public void unregisterCallback(Callback callback) {
        synchronized (this.mCallbacks) {
            this.mCallbacks.remove(callback);
        }
    }

    private void dispatchAttributesChanged() {
        synchronized (this.mCallbacks) {
            for (Callback callback : this.mCallbacks) {
                callback.onDeviceAttributesChanged();
            }
        }
    }

    public String toString() {
        return this.mDevice.toString();
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof CachedBluetoothDevice)) {
            return false;
        }
        return this.mDevice.equals(((CachedBluetoothDevice) o).mDevice);
    }

    public int hashCode() {
        return this.mDevice.getAddress().hashCode();
    }

    public int compareTo(CachedBluetoothDevice another) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = 1;
        if (another.isConnected()) {
            i = 1;
        } else {
            i = 0;
        }
        int comparison = i - (isConnected() ? 1 : 0);
        if (comparison != 0) {
            return comparison;
        }
        if (another.getBondState() == 12) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (getBondState() == 12) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int comparison2 = i2 - i3;
        if (comparison2 != 0) {
            return comparison2;
        }
        if (another.mJustDiscovered) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        if (!this.mJustDiscovered) {
            i5 = 0;
        }
        int comparison3 = i4 - i5;
        if (comparison3 != 0) {
            return comparison3;
        }
        int comparison4 = another.mRssi - this.mRssi;
        if (comparison4 != 0) {
            return comparison4;
        }
        return this.mName.compareTo(another.mName);
    }

    public int getPhonebookPermissionChoice() {
        int permission = this.mDevice.getPhonebookAccessPermission();
        if (permission == 1) {
            return 1;
        }
        if (permission == 2) {
            return 2;
        }
        return 0;
    }

    public void setPhonebookPermissionChoice(int permissionChoice) {
        int permission = 0;
        if (permissionChoice == 1) {
            permission = 1;
        } else if (permissionChoice == 2) {
            permission = 2;
        }
        this.mDevice.setPhonebookAccessPermission(permission);
    }

    private void migratePhonebookPermissionChoice() {
        SharedPreferences preferences = this.mContext.getSharedPreferences("bluetooth_phonebook_permission", 0);
        if (preferences.contains(this.mDevice.getAddress())) {
            if (this.mDevice.getPhonebookAccessPermission() == 0) {
                int oldPermission = preferences.getInt(this.mDevice.getAddress(), 0);
                if (oldPermission == 1) {
                    this.mDevice.setPhonebookAccessPermission(1);
                } else if (oldPermission == 2) {
                    this.mDevice.setPhonebookAccessPermission(2);
                }
            }
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(this.mDevice.getAddress());
            editor.commit();
        }
    }

    public int getMessagePermissionChoice() {
        int permission = this.mDevice.getMessageAccessPermission();
        if (permission == 1) {
            return 1;
        }
        if (permission == 2) {
            return 2;
        }
        return 0;
    }

    public void setMessagePermissionChoice(int permissionChoice) {
        int permission = 0;
        if (permissionChoice == 1) {
            permission = 1;
        } else if (permissionChoice == 2) {
            permission = 2;
        }
        this.mDevice.setMessageAccessPermission(permission);
    }

    public int getSimPermissionChoice() {
        int permission = this.mDevice.getSimAccessPermission();
        if (permission == 1) {
            return 1;
        }
        if (permission == 2) {
            return 2;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void setSimPermissionChoice(int permissionChoice) {
        int permission = 0;
        if (permissionChoice == 1) {
            permission = 1;
        } else if (permissionChoice == 2) {
            permission = 2;
        }
        this.mDevice.setSimAccessPermission(permission);
    }

    private void migrateMessagePermissionChoice() {
        SharedPreferences preferences = this.mContext.getSharedPreferences("bluetooth_message_permission", 0);
        if (preferences.contains(this.mDevice.getAddress())) {
            if (this.mDevice.getMessageAccessPermission() == 0) {
                int oldPermission = preferences.getInt(this.mDevice.getAddress(), 0);
                if (oldPermission == 1) {
                    this.mDevice.setMessageAccessPermission(1);
                } else if (oldPermission == 2) {
                    this.mDevice.setMessageAccessPermission(2);
                }
            }
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(this.mDevice.getAddress());
            editor.commit();
        }
    }

    public boolean checkAndIncreaseMessageRejectionCount() {
        if (this.mMessageRejectionCount < 2) {
            this.mMessageRejectionCount++;
            saveMessageRejectionCount();
        }
        return this.mMessageRejectionCount >= 2;
    }

    private void fetchMessageRejectionCount() {
        this.mMessageRejectionCount = this.mContext.getSharedPreferences(MESSAGE_REJECTION_COUNT_PREFS_NAME, 0).getInt(this.mDevice.getAddress(), 0);
    }

    private void saveMessageRejectionCount() {
        SharedPreferences.Editor editor = this.mContext.getSharedPreferences(MESSAGE_REJECTION_COUNT_PREFS_NAME, 0).edit();
        if (this.mMessageRejectionCount == 0) {
            editor.remove(this.mDevice.getAddress());
        } else {
            editor.putInt(this.mDevice.getAddress(), this.mMessageRejectionCount);
        }
        editor.commit();
    }

    private void processPhonebookAccess() {
        if (this.mDevice.getBondState() != 12 || !BluetoothUuid.containsAnyUuid(this.mDevice.getUuids(), PbapServerProfile.PBAB_CLIENT_UUIDS) || getPhonebookPermissionChoice() != 0) {
            return;
        }
        if (this.mDevice.getBluetoothClass().getDeviceClass() == 1032 || this.mDevice.getBluetoothClass().getDeviceClass() == 1028) {
            setPhonebookPermissionChoice(1);
        } else {
            setPhonebookPermissionChoice(2);
        }
    }

    public int getMaxConnectionState() {
        int maxState = 0;
        for (LocalBluetoothProfile profile : getProfiles()) {
            int connectionStatus = getProfileConnectionState(profile);
            if (connectionStatus > maxState) {
                maxState = connectionStatus;
            }
        }
        return maxState;
    }

    public String getConnectionSummary() {
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public String getCarConnectionSummary() {
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public boolean isA2dpDevice() {
        return this.mProfileManager.getA2dpProfile().getConnectionStatus(this.mDevice) == 2;
    }

    public boolean isHfpDevice() {
        return this.mProfileManager.getHeadsetProfile().getConnectionStatus(this.mDevice) == 2;
    }
}
