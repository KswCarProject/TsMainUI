package com.ts.bt;

import android.annotation.SuppressLint;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.content.BroadcastReceiver;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.OperationApplicationException;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;
import com.autochips.bluetooth.A2dpSinkProfile;
import com.autochips.bluetooth.AvrcpControllerProfile;
import com.autochips.bluetooth.BluetoothCallback;
import com.autochips.bluetooth.BluetoothContacts;
import com.autochips.bluetooth.BluetoothContactsData;
import com.autochips.bluetooth.BluetoothEventManager;
import com.autochips.bluetooth.BluetoothPBManager;
import com.autochips.bluetooth.CachedBluetoothDevice;
import com.autochips.bluetooth.CachedBluetoothDeviceManager;
import com.autochips.bluetooth.HeadsetClientProfile;
import com.autochips.bluetooth.LocalBluetoothAdapter;
import com.autochips.bluetooth.LocalBluetoothManager;
import com.autochips.bluetooth.LocalBluetoothProfile;
import com.autochips.bluetooth.LocalBluetoothProfileManager;
import com.lgb.pymatch.PyMatch;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.bt.ContactInfo;
import com.ts.can.bmw.mini.CanBMWMiniServiceDetailActivity;
import com.ts.main.common.MainSet;
import com.ts.main.txz.TxzReg;
import com.ts.tsspeechlib.bt.ITsSpeechBtPbInfo;
import com.ts.tsspeechlib.bt.TsBtCallback;
import com.txznet.sdk.TXZCallManager;
import com.txznet.sdk.bean.Poi;
import com.txznet.sdk.media.InvokeConstants;
import com.txznet.sdk.tongting.IConstantData;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import java.lang.reflect.Field;
import java.text.Collator;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressLint({"NewApi"})
public class BtExe {
    public static final String ACTION_BLUETOOTH_CALL_LOG = "com.autochips.bluetooth.BtUtils.action.ACTION_BLUETOOTH_CALL_LOG";
    public static final int AUTO_ANSWER_CHK_TIME = 5000;
    private static final String BROADCAST_REQUEST_HFPSTATUS = "com.globalconstant.BROADCAST_REQUEST_HFPSTATUS";
    public static final String BT_CONNECTION_CHANGE = "com.bt.ACTION_BT_CONNECTION_CHANGE";
    public static final String BT_CONNECTION_REQUEST = "com.bt.ACTION_BT_CONNECTION_REQUEST";
    public static final String BT_NAME_AND_PINCODE = "com.bt.ACTION_BT_NAME_AND_PINCODE";
    public static final String BT_NAME_AND_PINCODE_REQUEST = "com.bt.ACTION_BT_NAME_AND_PINCODE_REQUEST";
    public static final String BT_SYNC_CONTACT_REQUEST = "com.bt.ACTION_BT_SYNC_CONTACT_REQUEST";
    public static final int BthPbStaDel = 3;
    public static final int BthPbStaIdle = 0;
    public static final int BthPbStaReadOK = 2;
    public static final int BthPbStaReading = 1;
    public static final int BthStaCallActive = 4;
    public static final int BthStaCallHeld = 5;
    public static final int BthStaCallIn = 3;
    public static final int BthStaCallOut = 2;
    public static final int BthStaCallWaiting = 6;
    public static final int BthStaConnected = 1;
    public static final int BthStaOffLine = 0;
    private static final String CONTACT_SELECT_BY_ID = "_id=?";
    private static final String CONTACT_SELECT_BY_NUMBER = "data1=?";
    /* access modifiers changed from: private */
    public static boolean D = true;
    public static final String DDHU_SYNC_CONTACT_FINISH = "com.nwd.bt.ddhu.ACTION_DDHU_SYNC_CONTACT_FINISH";
    private static final int DEFAULT_DISCOVERABLE_TIMEOUT = -1;
    public static final String EXTRA_CALL_NAME = "com.autochips.bluetooth.BtUtils.extra.EXTRA_CALL_NAME";
    public static final String EXTRA_CALL_NUMBER = "com.autochips.bluetooth.BtUtils.extra.EXTRA_CALL_NUMBER";
    public static final String EXTRA_CALL_TIME = "com.autochips.bluetooth.BtUtils.extra.EXTRA_CALL_TIME";
    public static final String EXTRA_CALL_TYPE = "com.autochips.bluetooth.BtUtils.extra.EXTRA_CALL_TYPE";
    public static final int FILTER_INCOMING_TYPE = 2;
    public static final int FILTER_MISSED_TYPE = 4;
    public static final int FILTER_OUTGOING_TYPE = 1;
    public static final String ITEM_HISTORY_NAME = "item_history_name";
    public static final String ITEM_HISTORY_NUMBER = "item_history_number";
    public static final String ITEM_HISTORY_TIME = "item_history_time";
    public static final String ITEM_HISTORY_TYPE = "item_history_type";
    public static final int LOG_MAX = 500;
    public static final int MSG_ADD_CALL_LOG = 84;
    public static final int MSG_ADD_CONTACT = 71;
    public static final int MSG_ADD_HISTORY = 91;
    public static final int MSG_ADD_HISTORYLIST = 81;
    public static final int MSG_ADD_PBLIST = 61;
    public static final int MSG_ATCMD_NO_RESPONSE = 122;
    public static final int MSG_AUTO_CONNECT = 120;
    public static final int MSG_BOND_STATE_CHANGED = 14;
    public static final int MSG_BT_STATE_CHANGED = 10;
    public static final int MSG_CLEAR_HISTORYLIST = 83;
    public static final int MSG_CLEAR_PBLIST = 62;
    public static final int MSG_CONNECT_STATE_CHANGED = 15;
    public static final int MSG_DEVICE_ACL_DISCONNECTED = 18;
    public static final int MSG_DEVICE_ADD = 12;
    public static final int MSG_DEVICE_ATTR_CHANGED = 16;
    public static final int MSG_DEVICE_DELETE = 13;
    public static final int MSG_FLUSH_FILTER_LIST = 82;
    public static final int MSG_LOAD_CONTACT = 70;
    public static final int MSG_LOAD_HISTORY = 90;
    public static final int MSG_META_DATA_CHANGED = 31;
    public static final int MSG_PLAY_STATE_CHANGED = 30;
    public static final int MSG_PROFILE_STATE_CHANGED = 17;
    public static final int MSG_RECONNECT_DEVICE = 123;
    public static final int MSG_RECONNECT_HFP = 121;
    public static final int MSG_SCAN_STATE_CHANGED = 11;
    public static final int MSG_SERVICE_ATTACH = 0;
    public static final int MSG_SERVICE_DETACH = 1;
    public static final int MSG_UPDATE_HISTORYLIST = 80;
    public static final int MSG_UPDATE_PBLIST = 60;
    private static final String PARING_REQUEST_INTENT = "android.bluetooth.device.action.PAIRING_REQUEST";
    private static final String TAG = "BtExe";
    public static final String UNKOWN_PHONE_NUMBER = "unkown";
    private static String VER_ID = "";
    private static final String VER_STR = "BT.19.09.10.1830";
    private static long currentMusicTime = 0;
    public static BtDatabaseHelper dbHelper;
    public static boolean isAddCall = false;
    public static boolean isAutoAnswer = false;
    public static boolean isCallback = false;
    public static boolean isDownLoading = false;
    public static boolean isFirstCallLog = false;
    public static boolean isHideDialog = false;
    public static boolean isRefreshLog = false;
    public static boolean isSaveLastAddr = true;
    public static boolean isShowBox = false;
    private static boolean isWrcConnected = false;
    private static long lastClickTime;
    /* access modifiers changed from: private */
    public static A2dpSinkProfile mA2dpSinkProfile;
    public static int mA2dpsinkstate = 0;
    public static long mActiveSecond = 0;
    public static long mActiveTick = 0;
    public static ArrayList<HashMap<String, Object>> mAllHistoryList = new ArrayList<>();
    public static AudioManager mAudioManager;
    /* access modifiers changed from: private */
    public static AvrcpControllerProfile mAvrcpCtProfile;
    public static int mAvrcpctstate = 0;
    public static SQLiteDatabase mBtDb;
    public static boolean mBtIsEnabled = false;
    public static int mBtMusicLen = 0;
    public static int mBtMusicPos = 0;
    private static ExecutorService mBtService = Executors.newSingleThreadExecutor();
    public static int mCallPath = 0;
    public static int mCallSta = 0;
    public static Time mCallTime;
    static Context mContext;
    public static long mCount = 0;
    public static List<BluetoothDevice> mDeviceLists = new ArrayList();
    public static CachedBluetoothDeviceManager mDeviceManager;
    public static BluetoothEventManager mEventManager;
    private static List<Handler> mHandlerLists = new ArrayList();
    public static int mHeadSetState = 0;
    /* access modifiers changed from: private */
    public static HeadsetClientProfile.HeadsetClientCallCallback mHeadsetClientCallCallback = new HeadsetClientProfile.HeadsetClientCallCallback() {
        public void onCallStateChanged(Intent intent) {
            BtExe.getBtInstance().onActionCallChanged(BtExe.mContext, intent);
            BtExe.getBtInstance().updateCallSta();
            BtExe.getBtInstance().UpdateHfpSta();
            BtExe.getBtInstance().addIgnoreHistory();
        }

        public void onAudioStatusChanged(Intent intent) {
            Log.d(BtExe.TAG, "onAudioStatusChanged");
        }
    };
    /* access modifiers changed from: private */
    public static HeadsetClientProfile mHeadsetClientProfile;
    public static String mHfConnectedDeviceAddr = "";
    public static ArrayList<HashMap<String, Object>> mHistoryList = new ArrayList<>();
    public static int mIndex = 0;
    public static BtExe mInstance = null;
    /* access modifiers changed from: private */
    public static boolean mIsAutoConnect = false;
    public static boolean mIsAutoDisconnect = false;
    public static boolean mIsConnectOther = false;
    public static boolean mIsId3Update = false;
    public static final String[] mLanguages = {"zh", "ko", "mn", "ja", "vi", "hu"};
    private static long mLastAccOnTick = 0;
    public static int mLastCallSta = 0;
    private static long mLastConnectTick = -59000;
    public static BluetoothDevice mLastDevice = null;
    public static String mLastDeviceAddr = null;
    public static String mLastLogTime = "";
    public static String mLastOBDAddr = "";
    public static String mLastPhoneNo = "";
    public static ArrayList<PbItem> mListPb = new ArrayList<>();
    public static ArrayList<SearchItem> mListSearch = new ArrayList<>();
    public static LocalBluetoothAdapter mLocalAdapter;
    public static LocalBluetoothManager mLocalBtManager;
    public static LocalBluetoothProfileManager mLocalProfileManager;
    public static long mNum = 0;
    private static int mOldMcuSta = 3;
    public static String mOtherAddr = "";
    /* access modifiers changed from: private */
    public static BluetoothPBManager mPBManager;
    public static long mPbStartTick;
    public static int mPbStatus = 0;
    public static String mPhoneName = null;
    public static String mPin = "0000";
    private static LocalBluetoothProfileManager.ProfileCallback mProfileCallback = new LocalBluetoothProfileManager.ProfileCallback() {
        public void onProfileStateChanged(BluetoothDevice device, int profile, int state) {
            BtExe.dispatchMessage(17, device, profile, state);
        }
    };
    public static long mQueryNoCount = 0;
    public static long mQueryNoLastTick;
    public static long mSecondActiveTick = 0;
    private static final LocalBluetoothProfileManager.ServiceListener mServiceListener = new LocalBluetoothProfileManager.ServiceListener() {
        public void onServiceConnected() {
            if (BtExe.D) {
                Log.d(BtExe.TAG, "onServiceConnected ");
            }
            synchronized (BtExe.mContext) {
                BtExe.mBtIsEnabled = false;
                BtExe.mHeadsetClientProfile = BtExe.mLocalProfileManager.getHeadsetClientProfile();
                BtExe.mA2dpSinkProfile = BtExe.mLocalProfileManager.getA2dpSinkProfile();
                BtExe.mAvrcpCtProfile = BtExe.mLocalProfileManager.getAvrcpCtProfile();
                BtExe.mPBManager = BluetoothPBManager.getInstance(BtExe.mContext);
                BtExe.getBtInstance().regPBCallback();
                BtExe.getBtInstance().regMetadataCallback();
                if (BtExe.isCallback) {
                    if (BtExe.mHeadsetClientProfile != null) {
                        BtExe.mHeadsetClientProfile.registerHeadsetClientCallCallback(BtExe.mHeadsetClientCallCallback);
                    }
                    BtExe.getBtInstance().setCbTimer();
                }
                BtExe.getBtInstance().regPlayerUtility();
                if (BtExe.mIsAutoConnect && BtExe.mbNeedAutoConnect) {
                    BtExe.getBtInstance().connect();
                }
            }
            BtExe.dispatchMessage(0, (Object) null, 0, 0);
        }

        public void onServiceDisconnected() {
            if (BtExe.D) {
                Log.d(BtExe.TAG, "onServiceDisconnected ");
            }
            synchronized (BtExe.mContext) {
                BtExe.mHeadsetClientProfile = null;
                BtExe.mA2dpSinkProfile = null;
                BtExe.mAvrcpCtProfile = null;
                BtExe.mBtIsEnabled = true;
                BtExe.getBtInstance().unregPBCallback();
                BtExe.getBtInstance().unregMetadataCallback();
                BtExe.getBtInstance().clearCbTimer();
            }
            BtExe.dispatchMessage(1, (Object) null, 0, 0);
        }
    };
    private static String mStrCallName = "";
    public static String mStrId3Album = "";
    public static String mStrId3Artist = "";
    public static String mStrId3Name = "";
    private static String mStrOldCallNo = "";
    public static String mStrPhoneNo = UNKOWN_PHONE_NUMBER;
    public static int mSyncNum = 0;
    private static Toast mToast;
    public static long mTwoCallActiveSecond;
    public static boolean mbAbNomarl = false;
    private static boolean mbConnectUI = false;
    public static boolean mbConnecting = false;
    public static boolean mbFirstConnect = true;
    private static boolean mbFsInit = false;
    public static boolean mbHfConnected = false;
    public static boolean mbMicmute;
    private static boolean mbModuleInit = false;
    /* access modifiers changed from: private */
    public static boolean mbNeedAutoConnect = true;
    public static boolean mbNeedPWROn = false;
    public static boolean mbNeedSaveData = false;
    private static boolean mbNeedSetName = true;
    public static boolean mbNeedUpdatePhoneName = false;
    static BluetoothCallback mbtCallback = new BluetoothCallback() {
        public void onBluetoothStateChanged(int bluetoothState) {
            BtExe.dispatchMessage(10, (Object) null, bluetoothState, 0);
            BtExe.handleBtStateChanged(bluetoothState);
        }

        public void onScanningStateChanged(boolean started) {
            BtExe.dispatchMessage(11, (Object) null, started ? 0 : 1, 0);
        }

        public void onDeviceAdded(CachedBluetoothDevice cachedDevice) {
            BtExe.dispatchMessage(12, cachedDevice, 0, 0);
        }

        public void onDeviceDeleted(CachedBluetoothDevice cachedDevice) {
            BtExe.dispatchMessage(13, cachedDevice, 0, 0);
        }

        public void onDeviceBondStateChanged(CachedBluetoothDevice cachedDevice, int bondState) {
            BtExe.dispatchMessage(14, cachedDevice, bondState, 0);
        }

        public void onConnectionStateChanged(CachedBluetoothDevice cachedDevice, int state) {
            BtExe.dispatchMessage(15, cachedDevice, state, 0);
            if (cachedDevice != null) {
                BtExe.handleBtConnectStateChanged(cachedDevice.getDevice(), state);
                if (BtExe.getBtInstance().btCallback != null) {
                    BtExe.getBtInstance().btCallback.onBtConnectStateChange(state);
                }
            }
        }
    };
    public static int mlistFilter = 1;
    public static byte musicState = 0;
    TsBtCallback btCallback;
    private boolean isAutoPause = true;
    private boolean isBackCar = false;
    private long mAutoAnswerStart = 0;
    BroadcastReceiver mBtReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(BtExe.TAG, "action = " + action);
            if (action.equals("android.bluetooth.headsetclient.profile.action.RESULT")) {
                BtExe.this.onATCmdResult(context, intent);
            } else if (action.equals(BtExe.PARING_REQUEST_INTENT)) {
                BtExe.this.onBluetoothPairingRequest(context, intent);
            } else if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                BtExe.this.onBluetoothAclDisconnected(context, intent);
            } else if (action.equals(BtExe.BROADCAST_REQUEST_HFPSTATUS)) {
                BtExe.getBtInstance().UpdateHfpSta();
            } else if (action.equals(BtExe.BT_CONNECTION_REQUEST)) {
                BtExe.this.sendConnectionChange();
            } else if (action.equals(BtExe.BT_NAME_AND_PINCODE_REQUEST)) {
                BtExe.this.sendBtNameAndPincode();
            } else if (action.equals(BtExe.BT_SYNC_CONTACT_REQUEST)) {
                BtExe.this.sendBtSyncContact();
            }
        }
    };
    public LinkedHashMap<String, PhoneCall> mCallMap = new LinkedHashMap<>();
    public HashMap<String, String> mCallTypes = new HashMap<>();
    private BtUITimer mCbTimer = null;
    private boolean mChkAutoAnswer = false;
    protected Comparator<Object> mCmp;
    private String mCurName = null;
    private Evc mEvc = Evc.GetInstance();
    public IReceiver mIReceiver = null;
    private boolean mIsA2dpMode = true;
    private boolean mIsAutoAnswer = false;
    AvrcpControllerProfile.MetadataCallback mMetadataCallback = new AvrcpControllerProfile.MetadataCallback() {
        public void onMetadataChanged(String title, String artist, String album) {
            Log.d(BtExe.TAG, "onMetadataChanged title=" + title + ",artist=" + artist + ",album=" + album);
            BtExe.mIsId3Update = true;
            BtExe.mStrId3Name = title;
            BtExe.mStrId3Album = album;
            BtExe.mStrId3Artist = artist;
        }

        public void onPlayStatusChanged(byte play_status, int song_len, int song_pos) {
            if (BtExe.D) {
                Log.d(BtExe.TAG, "onPlayStatusChanged play_status=" + play_status + ",song_len=" + song_len + ",song_pos=" + song_pos);
            }
            BtExe.this.updatePlaybackStatus(play_status, song_len, song_pos);
            BtExe.mBtMusicLen = song_len;
            BtExe.mBtMusicPos = song_pos;
        }
    };
    public BluetoothPBManager.BluetoothPBCallback mPBCallback = new BluetoothPBManager.BluetoothPBCallback() {
        public void onPBDownloadStateChanged(Intent intent) {
            int state = intent.getIntExtra("com.autochips.bluetooth.phonebookdownload.extra.state", 0);
            if ((intent.getIntExtra("com.autochips.bluetooth.phonebookdownload.extra.path", 0) & 6) == 0) {
                Log.d(BtExe.TAG, "not care this intent");
                return;
            }
            Log.i(BtExe.TAG, "download ind state:" + state);
            switch (state) {
                case 1:
                    BtExe.mPbStatus = 1;
                    BtExe.mPbStartTick = BtExe.getTickCount();
                    BtExe.mSyncNum = 0;
                    BtExe.mListPb.clear();
                    BtExe.isDownLoading = true;
                    return;
                case 2:
                case 3:
                case 4:
                    BtExe.mSyncNum = intent.getIntExtra("com.autochips.bluetooth.phonebookdownload.extra.index", 0);
                    BtExe.isDownLoading = true;
                    new UpdateAsyncTask().execute(new Void[0]);
                    return;
                default:
                    return;
            }
        }

        public void onPBDownloadOnestep(Intent intent) {
            int path = intent.getIntExtra("com.autochips.bluetooth.phonebookdownload.extra.path", 0);
            int index = intent.getIntExtra("com.autochips.bluetooth.phonebookdownload.extra.index", 0);
            if ((path & 6) == 0) {
                Log.d(BtExe.TAG, "not care this intent");
                return;
            }
            BtExe.mSyncNum = index;
            if (BtExe.D) {
                Log.i(BtExe.TAG, "PBSyncManagerService.ACTION_DOWNLOAD_ONESTEP) iCount =  " + index);
            }
            BtExe.mPbStatus = 1;
            BtExe.mPbStartTick = BtExe.getTickCount();
        }
    };
    public String mStrSta = "";

    public static class BonedDevice {
        public String addr;
        public String name;
    }

    public interface IReceiver {
        void onReceive(Intent intent);
    }

    public static class PbItem {
        public int collect;
        public String first_name;
        public String given_name;
        public String middle_name;
        public String name;
        public String num;
        public String pinyin;
    }

    public static class SearchItem {
        public int MatchPos;
        public PbItem pb;
    }

    public static BtExe getBtInstance() {
        if (mInstance == null) {
            mInstance = new BtExe();
        }
        return mInstance;
    }

    BtExe() {
        if (D) {
            Log.d(TAG, "BtUtils ");
        }
    }

    public static Context getContext() {
        return mContext;
    }

    public static void close() {
        if (mEventManager != null) {
            mEventManager.unregisterCallback(mbtCallback);
        }
        if (mPBManager != null) {
            mPBManager.close();
            mPBManager = null;
        }
        deinitLocalBluetooth();
        mContext = null;
        mInstance = null;
    }

    public void Init() {
        getBtInstance();
        mInstance.initData();
        mbFsInit = true;
        mbModuleInit = true;
        mBtIsEnabled = false;
        mCount = 0;
        mNum = 0;
        if (mContext.getResources().getString(R.string.support_bt_phonemeeting).equals(MainSet.SP_XPH5)) {
            isCallback = true;
        } else {
            isCallback = false;
        }
        initLocalBluetooth(mContext);
        getLocalAdapter();
        getBtInstance().checkIfAbnormal();
        if (mLocalAdapter.isEnabled()) {
            setBluetoothDiscoverability(true);
            Log.d(TAG, "isEnabled+++++++++");
        }
        dbHelper = new BtDatabaseHelper(mContext, "BtPhone", (SQLiteDatabase.CursorFactory) null, 1);
        if (dbHelper != null) {
            mBtDb = dbHelper.getWritableDatabase();
        }
        if (mContext != null) {
            mAudioManager = (AudioManager) mContext.getSystemService(Poi.PoiAction.ACTION_AUDIO);
        }
        registerBtReceiver();
        ((Application) mContext).registerActivityLifecycleCallbacks(BtFunc.mLifecyleCallbacks);
    }

    public void CheckSetDevName() {
        if (mbFsInit && mbNeedSetName) {
            Log.d(TAG, String.valueOf(mbFsInit) + "+mbFsInit");
            Log.d(TAG, String.valueOf(mbModuleInit) + "+mbModuleInit");
            Log.d(TAG, String.valueOf(mbNeedSetName) + "+mbNeedSetName");
            fsSetDevName();
            if (this.mCurName != null) {
                Log.d(TAG, "CheckName+" + this.mCurName);
                Log.d(TAG, "CheckName+" + mLocalAdapter.getName());
                if (this.mCurName.equals(mLocalAdapter.getName())) {
                    mbNeedSetName = false;
                }
            }
        }
    }

    public void fsSetDevName() {
        char d;
        char[] mBtName = new char[32];
        FtSet.GetBtName(mBtName);
        String strName = new String();
        int i = 0;
        while (i < mBtName.length && (d = mBtName[i]) != 0) {
            strName = String.valueOf(strName) + mBtName[i];
            Log.i(TAG, "BtName[" + i + "] = " + d);
            i++;
        }
        int id = FtSet.GetBtId();
        if (strName == null || strName.length() < 3) {
            setDevByNameId("BT", id);
            return;
        }
        Log.i(TAG, "fsSetDevName = " + strName + ", len = " + strName.length());
        if (strName.charAt(0) == '0') {
            setDevByNameId(strName.substring(1), id);
        } else {
            setDevByNameId(strName, 0);
        }
    }

    public void setDevByNameId(String name, int id) {
        if (mPin == null || mPin.isEmpty()) {
            readDeviceNamePin();
        }
        String strDev = name;
        Log.i(TAG, "fsSetDevName = " + strDev + ", len = " + strDev.length());
        if (id != 0) {
            strDev = String.valueOf(strDev) + String.format("%04d", new Object[]{Integer.valueOf(id)});
        }
        setDevName(strDev);
    }

    public int getSta() {
        return mCallSta;
    }

    public static LocalBluetoothProfile getProfile(int profile) {
        switch (profile) {
            case 11:
                if (mA2dpSinkProfile != null && mA2dpSinkProfile.isProfileReady()) {
                    return mA2dpSinkProfile;
                }
            case 12:
                if (mAvrcpCtProfile != null && mAvrcpCtProfile.isProfileReady()) {
                    return mAvrcpCtProfile;
                }
            case 16:
                if (mHeadsetClientProfile != null && mHeadsetClientProfile.isProfileReady()) {
                    return mHeadsetClientProfile;
                }
        }
        return null;
    }

    public static BluetoothPBManager getPBManager() {
        return mPBManager;
    }

    public static synchronized void initLocalBluetooth(Context context) {
        synchronized (BtExe.class) {
            synchronized (mContext) {
                if (mLocalBtManager == null) {
                    mLocalBtManager = LocalBluetoothManager.getInstance(context);
                    if (mLocalBtManager != null) {
                        mEventManager = mLocalBtManager.getEventManager();
                        mDeviceManager = mLocalBtManager.getCachedDeviceManager();
                        mLocalProfileManager = mLocalBtManager.getProfileManager();
                    }
                    if (mLocalProfileManager != null) {
                        mLocalProfileManager.addServiceListener(mServiceListener);
                        mLocalProfileManager.addProfileCallback(mProfileCallback);
                    }
                    if (mEventManager != null) {
                        mEventManager.registerCallback(mbtCallback);
                    }
                }
            }
        }
    }

    public static synchronized void deinitLocalBluetooth() {
        synchronized (BtExe.class) {
            synchronized (mContext) {
                if (mLocalProfileManager != null) {
                    mLocalProfileManager.removeServiceListener(mServiceListener);
                    mLocalProfileManager.removeProfileCallback(mProfileCallback);
                }
                mLocalBtManager = null;
                mEventManager = null;
                mDeviceManager = null;
                mLocalProfileManager = null;
                mA2dpSinkProfile = null;
                mHeadsetClientProfile = null;
            }
        }
    }

    public static synchronized boolean isBluetoothReady() {
        boolean z;
        synchronized (BtExe.class) {
            z = mLocalBtManager != null;
        }
        return z;
    }

    public void setBluetoothDiscoverability(boolean isVisible) {
        Log.d(TAG, "setBluetoothDiscoverability = " + isVisible);
        if (isVisible) {
            if (mLocalAdapter != null) {
                mLocalAdapter.setScanMode(23, -1);
            }
        } else if (mLocalAdapter != null) {
            mLocalAdapter.setScanMode(21);
        }
    }

    public static void handleBtStateChanged(int btState) {
        switch (btState) {
            case 10:
                if (isBluetoothReady()) {
                    deinitLocalBluetooth();
                }
                mbModuleInit = false;
                mbHfConnected = false;
                mBtIsEnabled = true;
                if (mbAbNomarl) {
                    mbNeedPWROn = true;
                    mbAbNomarl = false;
                    return;
                }
                return;
            case 12:
                if (!isBluetoothReady()) {
                    initLocalBluetooth(mContext);
                    getBtInstance().setBluetoothDiscoverability(true);
                    Log.d(TAG, "Bluetooth open");
                }
                mbModuleInit = true;
                mBtIsEnabled = false;
                return;
            default:
                return;
        }
    }

    public static void handleBtConnectStateChanged(BluetoothDevice device, int state) {
        Log.d(TAG, "handleBtConnectStateChanged = " + state);
        switch (state) {
            case 0:
                Log.d(TAG, "BluetoothProfile.STATE_DISCONNECTED");
                mbNeedSaveData = false;
                mbNeedUpdatePhoneName = false;
                mbHfConnected = false;
                mCallSta = 0;
                isShowBox = false;
                isHideDialog = false;
                isAddCall = false;
                getBtInstance().pbClear();
                getBtInstance().sendConnectStateChange();
                getBtInstance().unregMetadataCallback();
                getBtInstance().mCallMap.clear();
                if (mDeviceLists.contains(device)) {
                    mDeviceLists.remove(device);
                }
                if (mIsConnectOther) {
                    mIsConnectOther = false;
                    getBtInstance().connect(mOtherAddr);
                    return;
                }
                return;
            case 2:
                Log.d(TAG, "BluetoothProfile.STATE_CONNECTED");
                mbNeedUpdatePhoneName = true;
                mbHfConnected = true;
                mbNeedSaveData = true;
                getBtInstance().updateCallSta();
                for (int i = 0; i < mDeviceLists.size(); i++) {
                    BluetoothDevice btdevice = mDeviceLists.get(i);
                    if (!btdevice.isConnected()) {
                        mDeviceLists.remove(btdevice);
                    }
                }
                if (!mDeviceLists.contains(device)) {
                    mDeviceLists.add(device);
                }
                String addr = device.getAddress();
                if (isExistAddr(addr)) {
                    deleteBondedDevice(addr);
                }
                insertBonedDevice(addr, device.getName());
                getBtInstance().saveLastAddr();
                getBtInstance().UpdateHfpSta();
                getBtInstance().updatePbList();
                mBtService.execute(new Runnable() {
                    public void run() {
                        BtExe.getBtInstance().UpdatePbMap();
                        BtExe.getBtInstance().sendConnectStateChange();
                    }
                });
                mPbStatus = 2;
                getBtInstance().regMetadataCallback();
                if (mIsAutoDisconnect) {
                    getBtInstance().disconnect();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static void handleDeviceSelected(BluetoothDevice device, boolean isSelected) {
        if (isSelected) {
            synchronized (mDeviceLists) {
                if (!mDeviceLists.contains(device)) {
                    mDeviceLists.add(device);
                }
            }
            return;
        }
        synchronized (mDeviceLists) {
            if (mDeviceLists.contains(device)) {
                mDeviceLists.remove(device);
            }
        }
    }

    public static synchronized void addHandler(Handler handler) {
        synchronized (BtExe.class) {
            if (D) {
                Log.d(TAG, "addHandler ");
            }
            if (!mHandlerLists.contains(handler)) {
                mHandlerLists.add(handler);
            }
        }
    }

    public static synchronized void removeHandler(Handler handler) {
        synchronized (BtExe.class) {
            if (D) {
                Log.d(TAG, "removeHandler ");
            }
            if (mHandlerLists.contains(handler)) {
                mHandlerLists.remove(handler);
            }
        }
    }

    public static void dispatchMessage(int what, Object arg, int arg1, int arg2) {
        if (D) {
            Log.d(TAG, "dispatchMessage(" + what + ")" + "arg1 = " + arg1 + " " + "arg2 = " + arg2);
        }
        for (Handler handler : mHandlerLists) {
            if (handler != null) {
                Message msg = handler.obtainMessage();
                msg.what = what;
                msg.obj = arg;
                msg.arg1 = arg1;
                msg.arg2 = arg2;
                handler.sendMessage(msg);
            }
        }
    }

    public static void showToast(int resid) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", 0);
        }
        mToast.setText(resid);
        mToast.show();
    }

    public static class SharedPreferencesCommitor implements Runnable {
        private SharedPreferences.Editor mSharedata;

        public SharedPreferencesCommitor(SharedPreferences.Editor sharedata) {
            this.mSharedata = sharedata;
        }

        public void run() {
            if (this.mSharedata != null) {
                this.mSharedata.commit();
            }
        }
    }

    public boolean isConnected() {
        return mbHfConnected;
    }

    public void getCurrentAgEvents() {
        Set<String> set;
        Log.d(TAG, "getCurrentAgEvents");
        checkHfp();
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
            return;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "device is not connected!");
            return;
        }
        Bundle bundle = hfProfile.getCurrentAgEvents(deviceList.get(0));
        if (bundle != null && (set = bundle.keySet()) != null) {
            for (String key : set) {
                Log.d("BTServicesss", "key:" + key + " value:" + bundle.get(key));
            }
        }
    }

    public void dial(String callNumber) {
        Log.d(TAG, "dial");
        checkHfp();
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
            return;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "device is not connected!");
        } else if (!isHaveCall()) {
            hfProfile.dial(deviceList.get(0), callNumber);
        } else if (!isDialFastDoubleClick()) {
            hfProfile.dial(deviceList.get(0), callNumber);
        }
    }

    public void reDial() {
        Log.d(TAG, "reDial");
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
            return;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "device is not connected!");
        } else {
            hfProfile.redial(deviceList.get(0));
        }
    }

    public void addWithReg(Handler mHandler, BluetoothPBManager.BluetoothPBCallback mPBCallback2) {
        addHandler(mHandler);
        BluetoothPBManager pbManager = getPBManager();
        if (pbManager != null) {
            pbManager.regPBCallback(mPBCallback2);
        }
    }

    public void removeWithUnreg(Handler mHandler, BluetoothPBManager.BluetoothPBCallback mPBCallback2) {
        removeHandler(mHandler);
        BluetoothPBManager pbManager = getPBManager();
        if (pbManager != null) {
            pbManager.unregPBCallback(mPBCallback2);
        }
    }

    public void regPBCallback(BluetoothPBManager.BluetoothPBCallback mPBCallback2) {
        BluetoothPBManager pbManager = getPBManager();
        if (pbManager != null) {
            pbManager.regPBCallback(mPBCallback2);
        }
    }

    public void stopDownloadCall() {
        BluetoothPBManager pbManager = getPBManager();
        if (pbManager != null) {
            pbManager.stopDownload((BluetoothDevice) null);
        }
    }

    public static String getAddr() {
        if (!mDeviceLists.isEmpty()) {
            return mDeviceLists.get(0).getAddress();
        }
        Log.d(TAG, "getAddr mDeviceLists == null");
        return "";
    }

    public boolean startDownload(BluetoothPBManager pbManager, int path) {
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf dapter fail!");
            return true;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList != null && deviceList.size() != 0) {
            return pbManager.startDownload(deviceList.get(0), path);
        }
        Log.e(TAG, "33 hf client is not connected!");
        return true;
    }

    public void stopDownload(BluetoothPBManager pbManager) {
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf dapter fail!");
            return;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "44 hf client is not connected!");
        } else {
            pbManager.stopDownload(deviceList.get(0));
        }
    }

    public void checkHfp() {
        Log.d(TAG, "checkHfp");
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
            return;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "device is not connected!");
            if (!mDeviceLists.isEmpty()) {
                hfProfile.connect(mDeviceLists.get(0));
            }
        }
    }

    public List<BluetoothDevice> getConnectedDevices(AvrcpControllerProfile avrcpProfile) {
        return avrcpProfile.getConnectedDevices();
    }

    public void checkBtAvState(boolean isFirst) {
        Log.d("BtMusicActivity", "checkBtAvState");
        A2dpSinkProfile a2dpProfile = getProfile(11);
        AvrcpControllerProfile avrcpProfile = getProfile(12);
        if (a2dpProfile != null) {
            List<BluetoothDevice> deviceList = a2dpProfile.getConnectedDevices();
            if (deviceList == null || deviceList.size() == 0) {
                mA2dpsinkstate = 0;
                if (!mDeviceLists.isEmpty()) {
                    a2dpProfile.connect(mDeviceLists.get(0));
                }
            } else if (isFirst) {
                mA2dpsinkstate = 2;
            }
        }
        if (avrcpProfile != null) {
            List<BluetoothDevice> deviceList2 = avrcpProfile.getConnectedDevices();
            if (deviceList2 == null || deviceList2.size() == 0) {
                mAvrcpctstate = 0;
                if (mA2dpsinkstate == 2 && !mDeviceLists.isEmpty()) {
                    avrcpProfile.connect(mDeviceLists.get(0));
                }
            } else if (isFirst) {
                mAvrcpctstate = 2;
                byte play_status = avrcpProfile.getPlaybackStatus();
                if (play_status != musicState) {
                    updatePlayPauseButton(play_status);
                }
            }
        }
    }

    public void sendAvrcpCommand(int cmd) {
        Log.d("BtMusicActivity", "sendAvrcpCommand");
        AvrcpControllerProfile avrcpProfile = getProfile(12);
        if (avrcpProfile != null) {
            checkBtAvState(false);
            if (mAvrcpctstate == 2) {
                List<BluetoothDevice> deviceList = avrcpProfile.getConnectedDevices();
                if (deviceList.size() > 0) {
                    avrcpProfile.sendAvrcpCommand(deviceList.get(0), cmd);
                }
            }
        }
    }

    public void updatePlayPauseButton(byte playState) {
        musicState = playState;
    }

    public void updatePlaybackStatus(byte play_status, int song_len, int song_pos) {
        if (D) {
            Log.d(TAG, "updatePlaybackStatus playState=" + play_status + ",bt.musicState=" + musicState);
        }
        if (play_status != musicState) {
            updatePlayPauseButton(play_status);
        }
    }

    public byte getMusicState() {
        byte state = 0;
        AvrcpControllerProfile avrcpProfile = getProfile(12);
        if (avrcpProfile != null) {
            state = avrcpProfile.getPlaybackStatus();
        }
        Log.d(TAG, "state = " + state);
        return state;
    }

    public void musicPP() {
        checkBtAvState(true);
        if (getMusicState() == 1) {
            sendAvrcpCommand(2);
        } else {
            sendAvrcpCommand(1);
        }
    }

    public void musicPrev() {
        Log.d(TAG, "musicPrev");
        if (!isFastDoubleClick()) {
            checkBtAvState(true);
            sendAvrcpCommand(3);
        }
    }

    public void musicNext() {
        Log.d(TAG, "musicNext");
        if (!isFastDoubleClick()) {
            checkBtAvState(true);
            sendAvrcpCommand(4);
        }
    }

    public void musicPause() {
        Log.d(TAG, "musicPause");
        checkBtAvState(true);
        sendAvrcpCommand(2);
    }

    public void musicPlay() {
        checkBtAvState(true);
        Log.d(TAG, "musicPlay");
        sendAvrcpCommand(1);
    }

    public void hangup() {
        Log.i(TAG, "on click hangup!");
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
            return;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "55 hf client is not connected!");
            return;
        }
        List<BluetoothHeadsetClientCall> callList = hfProfile.getCurrentCalls(deviceList.get(0));
        if (callList == null || callList.size() == 0) {
            Log.e(TAG, "no call!");
        } else if (isCallback) {
            BluetoothHeadsetClientCall c = getCall(callList, 4, 5);
            if (c != null) {
                Log.d(TAG, "rejectCall: " + c.getState());
                hfProfile.rejectCall(deviceList.get(0));
                return;
            }
            BluetoothHeadsetClientCall c2 = getCall(callList, 2, 3, 0);
            if (c2 != null) {
                Log.d(TAG, "terminateCall: " + c2.getState());
                hfProfile.terminateCall(deviceList.get(0), 0);
                return;
            }
            for (int i = 0; i < callList.size(); i++) {
                Log.d(TAG, "rejectCall(" + i + "), state: " + callList.get(i).getState());
            }
            hfProfile.rejectCall(deviceList.get(0));
        } else if (callList.get(0).getState() == 4) {
            hfProfile.rejectCall(deviceList.get(0));
        } else {
            hfProfile.terminateCall(deviceList.get(0), 0);
        }
    }

    public void answer() {
        Log.i(TAG, "on click answer!");
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
            return;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "66 hf client is not connected!");
            return;
        }
        SystemProperties.set("forfan.tsbt.mute", MainSet.SP_XPH5);
        hfProfile.acceptCall(deviceList.get(0), 0);
    }

    public void answerHold() {
        Log.i(TAG, "on click answer!");
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
            return;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "66 hf client is not connected!");
            return;
        }
        SystemProperties.set("forfan.tsbt.mute", MainSet.SP_XPH5);
        hfProfile.acceptCall(deviceList.get(0), 1);
    }

    public void answer(int flag) {
        Log.i(TAG, "on click answerfalg!");
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
            return;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "66 hf client is not connected!");
            return;
        }
        SystemProperties.set("forfan.tsbt.mute", MainSet.SP_XPH5);
        hfProfile.acceptCall(deviceList.get(0), flag);
    }

    public void hangup1() {
        Log.i(TAG, "on click hangup1!");
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
            return;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "55 hf client is not connected!");
            return;
        }
        List<BluetoothHeadsetClientCall> callList = hfProfile.getCurrentCalls(deviceList.get(0));
        if (callList == null || callList.size() == 0) {
            Log.e(TAG, "no call!");
            return;
        }
        int j = 0;
        int i = 0;
        while (true) {
            if (i >= callList.size()) {
                break;
            } else if (callList.get(i).getNumber().equals(mStrPhoneNo)) {
                j = i;
                break;
            } else {
                i++;
            }
        }
        if (j < callList.size()) {
            BluetoothDevice bluetoothDevice = callList.get(j).getDevice();
            int state = callList.get(j).getState();
            Log.d("__lh__", "state = " + state);
            if (state == 5) {
                hfProfile.rejectCall(bluetoothDevice);
            } else if (state == 0) {
                hfProfile.terminateCall(bluetoothDevice, 0);
            } else {
                hfProfile.terminateCall(bluetoothDevice, 0);
            }
        }
    }

    public void audioSwitch() {
        Log.i(TAG, "on click audioSwitch!");
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
            return;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "77 hf client is not connected!");
            return;
        }
        int scoState = hfProfile.getAudioState(deviceList.get(0));
        Log.d("__lh__", "scoState = " + scoState);
        if (scoState == 0) {
            hfProfile.connectAudio();
        } else if (scoState == 2) {
            hfProfile.disconnectAudio();
        } else {
            Log.d(TAG, "hf sco audio state:!" + scoState + ", can not switch!");
        }
    }

    public int getAudioState() {
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
            return 2;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "77 hf client is not connected!");
            return 2;
        }
        int scoState = hfProfile.getAudioState(deviceList.get(0));
        Log.d("__lh__", "scoState = " + scoState);
        return scoState;
    }

    public int getBluetoothAdapterState() {
        return mLocalAdapter.getState();
    }

    public void getLocalAdapter() {
        mLocalAdapter = LocalBluetoothAdapter.getInstance();
        if (mLocalAdapter != null && getBtEnabled() && !mLocalAdapter.isEnabled()) {
            Log.d(TAG, "setBluetoothEnabled");
            mLocalAdapter.setBluetoothEnabled(true);
        }
    }

    public void writeDeviceName(String name) {
        if (mLocalAdapter != null) {
            mLocalAdapter.setName(name);
            Log.d(TAG, "WriteName+" + name);
            Log.d(TAG, "mLocalAdapter.getName()" + mLocalAdapter.getName());
        }
    }

    public String getDevName() {
        return this.mCurName;
    }

    public void writeDevicePin(String pin) {
        SharedPreferences.Editor sharedata = mContext.getSharedPreferences("device_name_pin_data", 0).edit();
        sharedata.putString("PIN", pin);
        sharedata.commit();
    }

    public String getDevPin() {
        return mContext.getSharedPreferences("device_name_pin_data", 0).getString("PIN", "0000");
    }

    private void writeDeviceNamePin(String devicename, String pin) {
        SharedPreferences.Editor sharedata = mContext.getSharedPreferences("device_name_pin_data", 0).edit();
        sharedata.putString("DEVICENAME", devicename);
        sharedata.putString("PIN", pin);
        sharedata.commit();
    }

    public void setDevName(String devicename) {
        Log.i(TAG, "**********setDevName " + devicename + " **********");
        getLocalAdapter();
        if (devicename != null && !devicename.isEmpty()) {
            writeDeviceNamePin(devicename, mPin);
            readDeviceNamePin();
            Log.d(TAG, "SetDevName+" + this.mCurName);
            Log.d(TAG, "SetDevName+" + mLocalAdapter.getName());
            if (mLocalAdapter != null) {
                Log.d(TAG, "mLocalAdapter is not null");
                if (!devicename.equals(mLocalAdapter.getName())) {
                    writeDeviceName(this.mCurName);
                }
            }
        }
    }

    public void setDevPin(String pin) {
        if (pin != null && !pin.isEmpty()) {
            writeDeviceNamePin(this.mCurName, pin);
            readDeviceNamePin();
        }
    }

    public void readDeviceNamePin() {
        SharedPreferences sharedata = mContext.getSharedPreferences("device_name_pin_data", 0);
        getLocalAdapter();
        this.mCurName = sharedata.getString("DEVICENAME", mLocalAdapter.getName());
        mPin = sharedata.getString("PIN", "0000");
    }

    public void startDiscovery() {
        if (D) {
            Log.d(TAG, "onClick scan!");
        }
        if (mLocalAdapter == null) {
            return;
        }
        if (mLocalAdapter.isDiscovering()) {
            mLocalAdapter.cancelDiscovery();
        } else {
            mLocalAdapter.startScanning(true);
        }
    }

    public void connect() {
        Log.d(TAG, "bt connect");
        getLocalAdapter();
        if (mLocalAdapter != null && mDeviceManager != null && !mbHfConnected) {
            getLastAddr();
            if (!TextUtils.isEmpty(mLastDeviceAddr) && checkBluetoothAddress(mLastDeviceAddr)) {
                Log.d(TAG, mLastDeviceAddr);
                CachedBluetoothDevice cachedDevice = mDeviceManager.findDevice(mLocalAdapter.getRemoteDevice(mLastDeviceAddr));
                if (cachedDevice == null) {
                    Log.d(TAG, "cahedDevice == null");
                }
                if (cachedDevice != null && !cachedDevice.isConnected()) {
                    if (D) {
                        Log.d(TAG, "cnnect device:" + cachedDevice);
                    }
                    cachedDevice.connect();
                }
            }
        }
    }

    public void connect(String addr) {
        Log.d(TAG, "bt connect");
        getLocalAdapter();
        if (mLocalAdapter != null) {
            if (mbHfConnected) {
                mIsConnectOther = true;
                mOtherAddr = addr;
                disconnect();
            } else if (!TextUtils.isEmpty(addr) && checkBluetoothAddress(addr)) {
                Log.d(TAG, addr);
                CachedBluetoothDevice cachedDevice = mDeviceManager.findDevice(mLocalAdapter.getRemoteDevice(addr));
                if (cachedDevice == null) {
                    Log.d(TAG, "cahedDevice == null");
                }
                if (cachedDevice != null && !cachedDevice.isConnected()) {
                    if (D) {
                        Log.d(TAG, "cnnect device:" + cachedDevice);
                    }
                    cachedDevice.connect();
                }
            }
        }
    }

    public void connect(BonedDevice bonedDevice) {
        Log.d(TAG, "bt connect");
        getLocalAdapter();
        if (mLocalAdapter != null) {
            String addr = bonedDevice.addr;
            if (!"OBDII".equals(bonedDevice.name) && mbHfConnected) {
                mIsConnectOther = true;
                mOtherAddr = addr;
                disconnect();
            } else if (!TextUtils.isEmpty(addr) && checkBluetoothAddress(addr)) {
                Log.d(TAG, addr);
                CachedBluetoothDevice cachedDevice = mDeviceManager.findDevice(mLocalAdapter.getRemoteDevice(addr));
                if (cachedDevice == null) {
                    Log.d(TAG, "cahedDevice == null");
                }
                if (cachedDevice != null && !cachedDevice.isConnected()) {
                    if (D) {
                        Log.d(TAG, "cnnect device:" + cachedDevice);
                    }
                    cachedDevice.connect();
                }
            }
        }
    }

    public void disconnect() {
        CachedBluetoothDevice cachedDevice;
        if (D) {
            Log.d(TAG, "onClickdisConnect");
        }
        if (isBluetoothReady() && mbHfConnected) {
            if (isSaveLastAddr) {
                saveLastAddr();
            } else {
                isSaveLastAddr = true;
            }
            getLocalAdapter();
            if (mLocalAdapter != null && !TextUtils.isEmpty(mLastDeviceAddr) && checkBluetoothAddress(mLastDeviceAddr) && (cachedDevice = mDeviceManager.findDevice(mLocalAdapter.getRemoteDevice(mLastDeviceAddr))) != null && cachedDevice.isConnected()) {
                cachedDevice.disconnect();
                mbHfConnected = false;
                mbNeedUpdatePhoneName = false;
                mbNeedSaveData = false;
                mCallSta = 0;
                getBtInstance().sendConnectStateChange();
            }
        }
    }

    public void saveLastAddr() {
        if (isConnected()) {
            mLastDeviceAddr = getAddr();
            if (mLastDeviceAddr != null) {
                storeLastAddr();
            }
        }
    }

    public void getLastAddr() {
        if (mLastDeviceAddr == null || mLastDeviceAddr.isEmpty()) {
            mLastDeviceAddr = mContext.getSharedPreferences("last_dev_info", 0).getString("LAST_DEV_ADDR", (String) null);
        }
    }

    public void storeLastAddr() {
        SharedPreferences.Editor sharedata = mContext.getSharedPreferences("last_dev_info", 0).edit();
        sharedata.putString("LAST_DEV_ADDR", mLastDeviceAddr);
        sharedata.commit();
    }

    public void clearLastAddr() {
        SharedPreferences.Editor sharedata = mContext.getSharedPreferences("last_dev_info", 0).edit();
        sharedata.putString("LAST_DEV_ADDR", (String) null);
        sharedata.commit();
        mLastDeviceAddr = null;
    }

    public void saveOBDAddr() {
        SharedPreferences.Editor sharedata = mContext.getSharedPreferences("last_dev_info", 0).edit();
        sharedata.putString("LAST_OBD_ADDR", mLastOBDAddr);
        sharedata.commit();
    }

    public void getOBDAddr() {
        if (mLastOBDAddr == null || mLastOBDAddr.isEmpty()) {
            mLastOBDAddr = mContext.getSharedPreferences("last_dev_info", 0).getString("LAST_OBD_ADDR", "");
        }
    }

    public String GetOBDAddr() {
        if (mLastOBDAddr == null || mLastOBDAddr.isEmpty()) {
            return "";
        }
        return mLastOBDAddr;
    }

    public void micMute() {
        micMuteSW(!mbMicmute);
    }

    public void micMuteSW(boolean mute) {
        mbMicmute = mute;
        Log.i(TAG, "SetMyContext mAudioManager " + mbMicmute);
        mAudioManager.setMicrophoneMute(mute);
    }

    public boolean getMicMuteSta() {
        return mbMicmute;
    }

    public void insertPhonebook(String name, String num) {
        insertPhonebook(getAddr(), name, num, "", "", "", "");
    }

    public void insertPhonebook(String name, String num, String pinyin) {
        insertPhonebook(getAddr(), name, num, pinyin, "", "", "");
    }

    public void insertPhonebook(String name, String num, String pinyin, String first_name, String middle_name, String given_name) {
        insertPhonebook(getAddr(), name, num, pinyin, first_name, middle_name, given_name);
    }

    public void insertPhonebook(String addr, String name, String num, String pinyin, String first_name, String middle_name, String given_name) {
        if (dbHelper != null) {
            if (mBtDb == null) {
                mBtDb = dbHelper.getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put("addr", addr);
            values.put(IConstantData.KEY_NAME, name);
            values.put(CanBMWMiniServiceDetailActivity.KEY_NUM, num);
            values.put(InvokeConstants.INVOKE_COLLECT, 0);
            values.put("pinyin", pinyin);
            values.put("first_name", first_name);
            values.put("middle_name", middle_name);
            values.put("given_name", given_name);
            if (num != null && !num.isEmpty() && num.matches("[\\d\\+\\-\\.\\,\\(\\)\\*\\#\\/\\s]*") && name != null && !name.isEmpty() && !name.startsWith("@@@")) {
                mBtDb.insert("phonebook", (String) null, values);
            }
            values.clear();
        }
    }

    public boolean insertPhonebookList(List<PbItem> list) {
        boolean result = true;
        if (list == null || list.size() <= 0) {
            return true;
        }
        if (dbHelper != null) {
            if (mBtDb == null) {
                mBtDb = dbHelper.getWritableDatabase();
            }
            try {
                mBtDb.beginTransaction();
                String addr = getAddr();
                Iterator<PbItem> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    PbItem pbItem = it.next();
                    if (pbItem.num != null && !pbItem.num.isEmpty() && pbItem.num.matches("[\\d\\+\\-\\.\\,\\(\\)\\*\\#\\/\\s]*") && pbItem.name != null && !pbItem.name.isEmpty() && !pbItem.name.startsWith("@@@")) {
                        ContentValues values = new ContentValues();
                        values.put("addr", addr);
                        values.put(IConstantData.KEY_NAME, pbItem.name);
                        values.put(CanBMWMiniServiceDetailActivity.KEY_NUM, pbItem.num);
                        values.put(InvokeConstants.INVOKE_COLLECT, 0);
                        values.put("pinyin", pbItem.pinyin);
                        values.put("first_name", pbItem.first_name);
                        values.put("middle_name", pbItem.middle_name);
                        values.put("given_name", pbItem.given_name);
                        if (mBtDb.insert("phonebook", (String) null, values) < 0) {
                            result = false;
                            break;
                        }
                    }
                }
                if (result) {
                    Log.d("lh3", "insertPbBook success");
                    mBtDb.setTransactionSuccessful();
                }
                try {
                    if (mBtDb != null) {
                        mBtDb.endTransaction();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                try {
                    if (mBtDb == null) {
                        return false;
                    }
                    mBtDb.endTransaction();
                    return false;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return false;
                }
            } catch (Throwable th) {
                try {
                    if (mBtDb != null) {
                        mBtDb.endTransaction();
                    }
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
                throw th;
            }
        }
        return true;
    }

    public void updatePhonebook(String name, String num, int collect) {
        if (dbHelper != null) {
            if (mBtDb == null) {
                mBtDb = dbHelper.getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(InvokeConstants.INVOKE_COLLECT, Integer.valueOf(collect));
            Log.d("lh", "isSuccess=" + mBtDb.update("phonebook", values, "addr=? and name=? and num=?", new String[]{getAddr(), name, num}));
            values.clear();
        }
    }

    public void insertDiallog(String name, String num, String time, String type) {
        insertDiallog(name, num, time, type, 0);
    }

    public void insertDiallog(String name, String num, String time, String type, long calltime) {
        if (dbHelper != null) {
            if (mBtDb == null) {
                mBtDb = dbHelper.getWritableDatabase();
            }
            if (getAddr() != null && !getAddr().isEmpty()) {
                Cursor dbCursor = mBtDb.query("diallog", (String[]) null, "addr=?", new String[]{getAddr()}, (String) null, (String) null, (String) null);
                if (dbCursor.getCount() >= 500) {
                    if (dbCursor.moveToFirst()) {
                        String name1 = dbCursor.getString(dbCursor.getColumnIndex(IConstantData.KEY_NAME));
                        String num1 = dbCursor.getString(dbCursor.getColumnIndex(CanBMWMiniServiceDetailActivity.KEY_NUM));
                        String time1 = dbCursor.getString(dbCursor.getColumnIndex("time"));
                        String type1 = dbCursor.getString(dbCursor.getColumnIndex(IConstantData.KEY_TYPE));
                        long j = dbCursor.getLong(dbCursor.getColumnIndex("calltime"));
                        mBtDb.delete("diallog", "addr=? and name=? and num=? and time=? and type=?", new String[]{getAddr(), name1, num1, time1, type1});
                    }
                    dbCursor.close();
                }
            }
            ContentValues values = new ContentValues();
            values.put("addr", getAddr());
            values.put(IConstantData.KEY_NAME, name);
            values.put(CanBMWMiniServiceDetailActivity.KEY_NUM, num);
            values.put("time", time);
            values.put(IConstantData.KEY_TYPE, type);
            values.put("calltime", Long.valueOf(calltime));
            mBtDb.insert("diallog", (String) null, values);
            values.clear();
        }
    }

    public void delete(String table) {
        if (dbHelper != null) {
            if (mBtDb == null) {
                mBtDb = dbHelper.getWritableDatabase();
            }
            mBtDb.delete(table, (String) null, (String[]) null);
        }
    }

    public void delete(String table, String selection, String[] selctions) {
        if (dbHelper != null) {
            if (mBtDb == null) {
                mBtDb = dbHelper.getWritableDatabase();
            }
            mBtDb.delete(table, selection, selctions);
        }
    }

    public String DateToStr(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public String getCurrentTime(long date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(date));
    }

    public Date StrToDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getStringDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    private void writeAutoConnectData(boolean isAutoConnect) {
        SharedPreferences.Editor sharedata = mContext.getSharedPreferences("bt.setting.autoconnect", 0).edit();
        sharedata.putBoolean("IS_BT_AUTO_CONNECT", isAutoConnect);
        sharedata.commit();
    }

    private void readAutoConnectData() {
        mIsAutoConnect = mContext.getSharedPreferences("bt.setting.autoconnect", 0).getBoolean("IS_BT_AUTO_CONNECT", false);
    }

    private void writeAutoAnswerData(boolean isAutoAnswer2) {
        SharedPreferences.Editor sharedata = mContext.getSharedPreferences("bt.setting.autoanswer", 0).edit();
        sharedata.putBoolean("IS_BT_AUTO_ANSWER", isAutoAnswer2);
        sharedata.commit();
    }

    private void readAutoAnswerData() {
        this.mIsAutoAnswer = mContext.getSharedPreferences("bt.setting.autoanswer", 0).getBoolean("IS_BT_AUTO_ANSWER", false);
    }

    public boolean isAutoAnswer() {
        return this.mIsAutoAnswer;
    }

    public boolean isAutoConnect() {
        return mIsAutoConnect;
    }

    public void autoAnswerSw() {
        this.mIsAutoAnswer = !this.mIsAutoAnswer;
        writeAutoAnswerData(this.mIsAutoAnswer);
    }

    public void autoConnectSw() {
        mIsAutoConnect = !mIsAutoConnect;
        writeAutoConnectData(mIsAutoConnect);
    }

    public void resetAutoConnect() {
        mbNeedAutoConnect = false;
    }

    public void setAutoConnect() {
        mbNeedAutoConnect = true;
    }

    public void sendDTMFCode(byte code) {
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
            return;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "88 hf client is not connected!");
        } else {
            hfProfile.sendDTMF(deviceList.get(0), code);
        }
    }

    public static void setContext(Context context) {
        if (mContext == null) {
            mContext = context;
        }
    }

    /* access modifiers changed from: package-private */
    public void initData() {
        getLastAddr();
        readAutoConnectData();
        readAutoAnswerData();
    }

    public String getContactByNumber(String number) {
        String contactName = "";
        int contactID = -1;
        String recordAddr = "";
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf dapter fail!");
            return contactName;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "99 hf client is not connected!");
            return contactName;
        }
        String remoteAddr = deviceList.get(0).getAddress();
        ContentResolver resolver = mContext.getContentResolver();
        Cursor cursor = resolver.query(BluetoothContactsData.CONTENT_URI, new String[]{"contacts_id", "display_name"}, CONTACT_SELECT_BY_NUMBER, new String[]{number}, (String) null);
        if (cursor.moveToNext()) {
            contactName = cursor.getString(cursor.getColumnIndex("display_name"));
            contactID = cursor.getInt(cursor.getColumnIndex("contacts_id"));
        }
        cursor.close();
        Cursor cursor2 = resolver.query(BluetoothContacts.CONTENT_URI, new String[]{"remote_addr"}, CONTACT_SELECT_BY_ID, new String[]{String.valueOf(contactID)}, (String) null);
        if (cursor2.moveToNext()) {
            recordAddr = cursor2.getString(cursor2.getColumnIndex("remote_addr"));
        }
        cursor2.close();
        if (!recordAddr.equals(remoteAddr)) {
            contactName = "";
        }
        return contactName;
    }

    public int getCallValue() {
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
            return -1;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            return -1;
        }
        List<BluetoothHeadsetClientCall> callList = hfProfile.getCurrentCalls(deviceList.get(0));
        if (callList == null || callList.size() == 0) {
            Log.e(TAG, "Call had been ended before this activity create!");
            return -1;
        }
        BluetoothHeadsetClientCall callStatus = callList.get(0);
        for (int i = 0; i < callList.size(); i++) {
            callStatus = callList.get(i);
            if (callStatus.getState() == 0) {
                break;
            }
        }
        return callStatus.getState();
    }

    public String getLastPhoneNo() {
        return mLastPhoneNo;
    }

    public void updateLastPhoneNum() {
        if (dbHelper != null && isConnected() && getAddr() != null && !getAddr().isEmpty()) {
            if (mBtDb == null) {
                mBtDb = dbHelper.getWritableDatabase();
            }
            Cursor dbCursor = mBtDb.query("diallog", (String[]) null, "addr=?", new String[]{getAddr()}, (String) null, (String) null, (String) null);
            if (dbCursor.getCount() != 0 && dbCursor.moveToLast()) {
                mLastPhoneNo = dbCursor.getString(dbCursor.getColumnIndex(CanBMWMiniServiceDetailActivity.KEY_NUM));
            }
        }
    }

    public String GetCallName() {
        return mStrCallName;
    }

    public void UpdateCallName() {
        String strPbName;
        String strPbName2;
        String strPbName3;
        String strPbName4;
        String strPbName5;
        String strPbName6;
        Log.d(TAG, "UpdateCallName");
        mStrCallName = "";
        if (mListPb != null) {
            mStrPhoneNo = mStrPhoneNo.replace("-", "");
            mStrPhoneNo = mStrPhoneNo.replace(" ", "");
            mStrPhoneNo = mStrPhoneNo.replace("(", "");
            mStrPhoneNo = mStrPhoneNo.replace(")", "");
            int iNoLen = mStrPhoneNo.length();
            int i = 0;
            while (i < mListPb.size()) {
                String strPbNo = mListPb.get(i).num.replace("-", "").replace(" ", "").replace("(", "").replace(")", "");
                if (iNoLen != strPbNo.length() || !mStrPhoneNo.equals(strPbNo)) {
                    i++;
                } else {
                    if (isBtCountry()) {
                        strPbName6 = mListPb.get(i).name;
                    } else {
                        String first_name = mListPb.get(i).first_name;
                        String middle_name = mListPb.get(i).middle_name;
                        String strPbName7 = String.valueOf("") + mListPb.get(i).given_name;
                        if (!strPbName7.isEmpty()) {
                            strPbName5 = String.valueOf(strPbName7) + " " + middle_name;
                        } else {
                            strPbName5 = String.valueOf(strPbName7) + middle_name;
                        }
                        if (!strPbName5.isEmpty()) {
                            strPbName6 = String.valueOf(strPbName5) + " " + first_name;
                        } else {
                            strPbName6 = String.valueOf(strPbName5) + first_name;
                        }
                    }
                    Log.d("lh", "num =" + mStrPhoneNo);
                    Log.d("lh", "name = " + strPbName6);
                    mStrCallName = strPbName6;
                    return;
                }
            }
            if (iNoLen >= 7 && mStrCallName.length() == 0) {
                int recentPatch = Integer.MAX_VALUE;
                for (int i2 = 0; i2 < mListPb.size(); i2++) {
                    String strPbNo2 = mListPb.get(i2).num.replace("-", "").replace(" ", "").replace("(", "").replace(")", "");
                    int iPbLen = strPbNo2.length();
                    if (iNoLen >= iPbLen) {
                        if (mStrPhoneNo.substring(iNoLen - iPbLen).equals(strPbNo2) && iPbLen != 0) {
                            if (isBtCountry()) {
                                strPbName4 = mListPb.get(i2).name;
                            } else {
                                String first_name2 = mListPb.get(i2).first_name;
                                String middle_name2 = mListPb.get(i2).middle_name;
                                String strPbName8 = String.valueOf("") + mListPb.get(i2).given_name;
                                if (!strPbName8.isEmpty()) {
                                    strPbName3 = String.valueOf(strPbName8) + " " + middle_name2;
                                } else {
                                    strPbName3 = String.valueOf(strPbName8) + middle_name2;
                                }
                                if (!strPbName3.isEmpty()) {
                                    strPbName4 = String.valueOf(strPbName3) + " " + first_name2;
                                } else {
                                    strPbName4 = String.valueOf(strPbName3) + first_name2;
                                }
                            }
                            int index = iNoLen - iPbLen;
                            if (index < recentPatch) {
                                mStrCallName = strPbName4;
                                recentPatch = index;
                            }
                        }
                    } else if (iNoLen < iPbLen && strPbNo2.substring(iPbLen - iNoLen).equals(mStrPhoneNo)) {
                        if (isBtCountry()) {
                            strPbName2 = mListPb.get(i2).name;
                        } else {
                            String first_name3 = mListPb.get(i2).first_name;
                            String middle_name3 = mListPb.get(i2).middle_name;
                            String strPbName9 = String.valueOf("") + mListPb.get(i2).given_name;
                            if (!strPbName9.isEmpty()) {
                                strPbName = String.valueOf(strPbName9) + " " + middle_name3;
                            } else {
                                strPbName = String.valueOf(strPbName9) + middle_name3;
                            }
                            if (!strPbName.isEmpty()) {
                                strPbName2 = String.valueOf(strPbName) + " " + first_name3;
                            } else {
                                strPbName2 = String.valueOf(strPbName) + first_name3;
                            }
                        }
                        int index2 = iPbLen - iNoLen;
                        if (index2 < recentPatch) {
                            mStrCallName = strPbName2;
                            recentPatch = index2;
                        }
                    }
                }
            }
        }
    }

    private void checkNeedPWROn() {
        if (mbNeedPWROn) {
            Log.e(TAG, "checkNeedPWROn");
            powerOn();
            mbNeedPWROn = false;
        }
    }

    public int timerCall(int mcusSta) {
        if (mOldMcuSta != mcusSta) {
            if ((mOldMcuSta == 3 || mOldMcuSta == 2) && mcusSta == 0) {
                mLastConnectTick = 0;
                mLastAccOnTick = getTickCount();
                Log.e(TAG, "Fake on ,clear mLastConnectTick");
            } else if ((mcusSta == 3 || mcusSta == 2) && mbHfConnected && mbNeedSaveData) {
                storeLastAddr();
                mbNeedSaveData = false;
            }
            mOldMcuSta = mcusSta;
        }
        if (!mBtIsEnabled) {
            long j = mCount;
            mCount = 1 + j;
            if (j > 150) {
                if (mLocalAdapter.isEnabled()) {
                    if (mLocalAdapter.getScanMode() != 23) {
                        mCount = 0;
                        setBluetoothDiscoverability(true);
                    } else {
                        Log.d(TAG, "bt is discoverable");
                        mBtIsEnabled = true;
                    }
                    Log.d(TAG, "mCount+++++++");
                }
                mCount = 0;
            }
        }
        checkNeedPWROn();
        if (mbModuleInit && getTickCount() > mLastAccOnTick + 20000) {
            checkAutoConnect();
        }
        CheckSetDevName();
        if (mCallSta != mLastCallSta) {
            UpdateHfpSta();
            if (mCallSta == 3 && this.mIsAutoAnswer) {
                this.mChkAutoAnswer = true;
                this.mAutoAnswerStart = getTickCount();
            }
            if (mCallSta >= 1 && mLastCallSta < 1) {
                setAutoConnect();
            }
            if (mCallSta <= 1 && mLastCallSta > 1) {
                mStrCallName = "";
                mStrOldCallNo = "";
                this.mEvc.evol_blue_set(0);
                Log.d(TAG, "mEvc.evol_blue_set(0)");
                mQueryNoLastTick = getTickCount();
                mQueryNoCount = 0;
            }
            if (mCallSta > 1 && mLastCallSta <= 1) {
                micMuteSW(false);
                this.mEvc.evol_blue_set(1);
                Log.d(TAG, "mEvc.evol_blue_set(1)");
                if (mCallSta != 2) {
                    mQueryNoLastTick = getTickCount();
                }
            }
            if (mCallSta == 4 && mLastCallSta < 1) {
                micMuteSW(false);
                this.mEvc.evol_blue_set(1);
                Log.d(TAG, "mEvc.evol_blue_set(1)");
            }
            if (mCallSta == 4 && mLastCallSta < 4) {
                mActiveTick = getTickCount();
            } else if (mCallSta < 4 && mLastCallSta == 4) {
                mActiveSecond = 0;
                mActiveTick = 0;
            }
            if (mLastCallSta > 1) {
            }
            updateLastPhoneNum();
        }
        if (mCallSta > 1) {
            if (mStrPhoneNo.equals(UNKOWN_PHONE_NUMBER)) {
                long curTick = getTickCount();
                if (curTick > mQueryNoLastTick + 2000) {
                    mQueryNoLastTick = curTick;
                    mQueryNoCount++;
                    String strPhoneNum = getCallingNum();
                    if (strPhoneNum != null && !strPhoneNum.isEmpty() && !strPhoneNum.equals(UNKOWN_PHONE_NUMBER) && D) {
                        Log.i(TAG, "********************getCallingNum = " + mStrPhoneNo + "********************");
                    }
                }
            }
            if (!mStrOldCallNo.equals(mStrPhoneNo)) {
                mStrOldCallNo = new String(mStrPhoneNo);
                if (mStrCallName.length() == 0 && mStrPhoneNo != null && mStrPhoneNo.length() > 0) {
                    UpdateCallName();
                    UpdateHfpSta();
                }
            }
        }
        if (mCallSta == 4) {
            mActiveSecond = (getTickCount() - mActiveTick) / 1000;
        }
        mLastCallSta = mCallSta;
        if (this.isAutoPause) {
            long currentTime = getTickCount();
            if (currentTime - currentMusicTime > 3000) {
                currentMusicTime = currentTime;
                int mode = Iop.GetWorkMode();
                Log.d(TAG, "workMode = " + mode);
                if (mode != 5 && getMusicState() == 1) {
                    musicPause();
                }
            }
        }
        if (this.mChkAutoAnswer) {
            if (mCallSta != 3) {
                this.mChkAutoAnswer = false;
            } else if (SystemClock.uptimeMillis() > this.mAutoAnswerStart + 5000) {
                answer();
                this.mChkAutoAnswer = false;
                this.mIsAutoAnswer = true;
                Log.d(TAG, "NewSta == BthStaCallIn, auto answer");
            }
        }
        if (this.mCbTimer == null) {
            return 255;
        }
        updateActiveSecond();
        this.mCbTimer.onBtTimer();
        return 255;
    }

    /* access modifiers changed from: package-private */
    public void updateActiveSecond() {
        for (Map.Entry<String, PhoneCall> element : this.mCallMap.entrySet()) {
            String strKey = element.getKey();
            PhoneCall strValue = element.getValue();
            int state = strValue.getCallState();
            if (state == 0 || state == 1) {
                long activeTick = strValue.getCallActiveTick();
                if (activeTick == 0) {
                    activeTick = getTickCount();
                    strValue.setCallActiveTick(activeTick);
                }
                strValue.setCallActiveSecond((getTickCount() - activeTick) / 1000);
                this.mCallMap.put(strKey, strValue);
            }
        }
    }

    public void UpdateHfpSta() {
        if (mContext != null) {
            Intent intent = new Intent("com.globalconstant.BROADCAST_RESPONSE_HFPSTATUS");
            Log.d(TAG, "UpdateHfpSta  mCallSta== " + mCallSta + ", name = " + GetCallName());
            Log.d(TAG, "UpdateHfpSta  mCallSta== " + mCallSta + ", num = " + getCallingNum());
            switch (mCallSta) {
                case 0:
                    intent.putExtra("hfpStatus", 0);
                    TxzReg.GetInstance().SetBtState(3, (TXZCallManager.Contact) null);
                    break;
                case 1:
                    intent.putExtra("hfpStatus", 1);
                    TxzReg.GetInstance().SetBtState(3, (TXZCallManager.Contact) null);
                    break;
                case 2:
                    intent.putExtra("hfpStatus", 5);
                    TXZCallManager.Contact con = new TXZCallManager.Contact();
                    con.setName(GetCallName());
                    con.setNumber(getCallingNum());
                    TxzReg.GetInstance().SetBtState(2, con);
                    Evc.GetInstance().evol_mut_set(0);
                    break;
                case 3:
                    if (getCallingNum() != null && getCallingNum().length() > 0) {
                        intent.putExtra("hfpStatus", 2);
                        TXZCallManager.Contact con2 = new TXZCallManager.Contact();
                        con2.setName(GetCallName());
                        con2.setNumber(getCallingNum());
                        TxzReg.GetInstance().SetBtState(0, con2);
                        Evc.GetInstance().evol_mut_set(0);
                        break;
                    }
                case 4:
                    if (mLastCallSta == 2) {
                        intent.putExtra("hfpStatus", 6);
                    } else {
                        intent.putExtra("hfpStatus", 3);
                    }
                    TxzReg.GetInstance().SetBtState(1, (TXZCallManager.Contact) null);
                    break;
                default:
                    TxzReg.GetInstance().SetBtState(3, (TXZCallManager.Contact) null);
                    break;
            }
            intent.putExtra(IConstantData.KEY_NAME, GetCallName());
            intent.putExtra("number", getCallingNum());
            Log.d(TAG, "UpdateHfpSta " + intent.getIntExtra("hfpStatus", 0) + ", name = " + mStrCallName);
            mContext.sendBroadcast(intent);
        }
    }

    public Map<String, String> GetPbMap() {
        Map<String, String> pbMap = new HashMap<>();
        if (mListPb != null && isConnected()) {
            Log.d(TAG, "*****GetPbMap***** size = " + mListPb.size());
            for (int i = 0; i < mListPb.size(); i++) {
                PbItem item = mListPb.get(i);
                if (item.name != null && !item.name.isEmpty() && item.num != null && !item.num.isEmpty()) {
                    pbMap.put(item.num, item.name);
                }
            }
        }
        return pbMap;
    }

    public List<PbInfo> getPbInfo() {
        List<PbInfo> pbInfos = new ArrayList<>();
        if (mListPb != null && isConnected()) {
            Log.d(TAG, "*****GetPbMap***** size = " + mListPb.size());
            for (int i = 0; i < mListPb.size(); i++) {
                PbInfo pbInfo = new PbInfo();
                PbItem item = mListPb.get(i);
                if (item.name != null && !item.name.isEmpty() && item.num != null && !item.num.isEmpty()) {
                    pbInfo.setName(item.name);
                    pbInfo.setNum(item.num);
                    pbInfo.setPinyin(item.pinyin);
                    pbInfos.add(pbInfo);
                }
            }
        }
        return pbInfos;
    }

    public void pbClear() {
        int size = mListPb.size();
        mListPb.clear();
        mPbStatus = 0;
        mSyncNum = 0;
        UpdatePbMap();
    }

    public void UpdatePbMap() {
        Log.d(TAG, "UpdatePbMap");
        if (mContext != null) {
            long startTime = System.currentTimeMillis();
            if (MainSet.GetInstance().IsLocal()) {
                deletePhonebookProvider();
            } else if (MainSet.IsGLSXVer().booleanValue()) {
                deletePhonebookProvider1();
            }
            List<ContactInfo> list = new ArrayList<>();
            List<TXZCallManager.Contact> lst = new ArrayList<>();
            if (mListPb != null && isConnected()) {
                Log.d(TAG, "*****GetPbMap***** size = " + mListPb.size());
                String addr = getAddr();
                if (MainSet.GetInstance().IsLocal()) {
                    insertPhonebookProviderList(mListPb, addr);
                } else if (MainSet.IsGLSXVer().booleanValue()) {
                    insertPhonebookProviderList1(mListPb, addr);
                }
                for (int i = 0; i < mListPb.size(); i++) {
                    PbItem item = mListPb.get(i);
                    if (!(item == null || item.name == null || item.name.isEmpty() || item.num == null || item.num.isEmpty())) {
                        ContactInfo contactInfo = new ContactInfo();
                        ContactInfo.PhoneInfo phoneInfo = new ContactInfo.PhoneInfo();
                        TXZCallManager.Contact con = new TXZCallManager.Contact();
                        contactInfo.setName(item.name);
                        phoneInfo.setNumber(item.num);
                        contactInfo.addPhoneInfo(phoneInfo);
                        list.add(contactInfo);
                        con.setName(item.name);
                        con.setNumber(item.num);
                        lst.add(con);
                    }
                }
            }
            sendUpdatePhonebookComplete();
            TxzReg.GetInstance().SyncBlueToothContacts(lst);
            if (this.btCallback != null) {
                this.btCallback.onBtPbListChange(getTsSpeechBtPbInfo());
            }
            Log.d(TAG, "updatePbMap:endTime = " + (System.currentTimeMillis() - startTime));
        }
    }

    public void updatePbList() {
        if (dbHelper != null) {
            ArrayList<PbItem> pbList = new ArrayList<>();
            if (mBtDb == null) {
                mBtDb = dbHelper.getWritableDatabase();
            }
            if (getAddr() != null && !getAddr().isEmpty()) {
                Cursor cursor1 = mBtDb.query("phonebook", (String[]) null, "addr=?", new String[]{getAddr()}, (String) null, (String) null, (String) null);
                boolean isZhCountry = isZhCountry();
                boolean isBtCountry = isBtCountry();
                if (cursor1.moveToLast()) {
                    do {
                        String name = cursor1.getString(cursor1.getColumnIndex(IConstantData.KEY_NAME));
                        String num = cursor1.getString(cursor1.getColumnIndex(CanBMWMiniServiceDetailActivity.KEY_NUM));
                        int collect = cursor1.getInt(cursor1.getColumnIndex(InvokeConstants.INVOKE_COLLECT));
                        String pinyin = cursor1.getString(cursor1.getColumnIndex("pinyin"));
                        String first_name = cursor1.getString(cursor1.getColumnIndex("first_name"));
                        String middle_name = cursor1.getString(cursor1.getColumnIndex("middle_name"));
                        String given_name = cursor1.getString(cursor1.getColumnIndex("given_name"));
                        PbItem map = new PbItem();
                        map.name = name;
                        map.num = num;
                        map.collect = collect;
                        map.pinyin = pinyin;
                        map.first_name = first_name;
                        map.middle_name = middle_name;
                        map.given_name = given_name;
                        map.pinyin = updateSortRow(isZhCountry, isBtCountry, map);
                        pbList.add(map);
                    } while (cursor1.moveToPrevious());
                    cursor1.close();
                }
                mListPb.clear();
                mListPb.addAll(pbList);
                PbSort(mListPb);
            }
            sendPbUpdate();
        }
    }

    /* access modifiers changed from: package-private */
    public String updateSortRow(boolean isZhCountry, boolean isBtCountry, PbItem map) {
        if (!isZhCountry) {
            char c = map.name.charAt(0);
            if (c >= '0' && c <= '9') {
                map.pinyin = "zzzzzz" + map.name;
            } else if (isBtCountry) {
                map.pinyin = map.name;
            } else {
                map.pinyin = String.valueOf(map.given_name) + map.middle_name + map.first_name;
            }
        } else if (TextUtils.isEmpty(map.pinyin)) {
            map.pinyin = "zzzzzz999999999";
        } else {
            char c2 = map.pinyin.charAt(0);
            if (c2 >= '0' && c2 <= '9') {
                map.pinyin = "zzzzzz999" + map.pinyin;
            } else if (c2 < 'A' || c2 > 'Z') {
                map.pinyin = "zzzzzz999999999" + map.pinyin;
            }
        }
        return map.pinyin;
    }

    /* access modifiers changed from: package-private */
    public void sendPbUpdate() {
        mContext.sendBroadcast(new Intent("com.ts.bt.PHONEBOOK_UPDATE"));
    }

    /* access modifiers changed from: package-private */
    public void sendConnectStateChange() {
        mContext.sendBroadcast(new Intent("com.ts.bt.CONNECT_STATE_CHANGE"));
    }

    public void checkAutoConnect() {
        if (mIsAutoConnect && mbNeedAutoConnect) {
            long tick = getTickCount();
            if (mbConnectUI || isConnected()) {
                mLastConnectTick = tick;
            } else if (tick > mLastConnectTick + 59000) {
                Log.d(TAG, String.valueOf(mbConnectUI) + "+mbConnectUI");
                Log.d(TAG, String.valueOf(mIsAutoConnect) + "+mIsAutoConnect");
                Log.d(TAG, String.valueOf(mbNeedAutoConnect) + "+mbNeedAutoConnect");
                getLastAddr();
                connect();
                mLastConnectTick = tick;
            }
        }
    }

    public void setUI(boolean isBtUI) {
        mbConnectUI = isBtUI;
    }

    public static long getTickCount() {
        return SystemClock.uptimeMillis();
    }

    public void updateCallSta() {
        int CallSta = 0;
        int phoneCallState = getCallValue();
        Log.d(TAG, "phoneCallState = " + phoneCallState);
        if (phoneCallState == 0) {
            CallSta = 4;
        } else if (phoneCallState == -1 || phoneCallState == 7) {
            if (mbHfConnected) {
                CallSta = 1;
            } else {
                CallSta = 0;
            }
        } else if (phoneCallState == 4) {
            CallSta = 3;
        } else if (phoneCallState == 2) {
            CallSta = 2;
        } else if (phoneCallState == 3) {
            CallSta = 2;
        } else if (phoneCallState == 1) {
            CallSta = 5;
        } else if (phoneCallState == 5) {
            CallSta = 6;
        }
        mCallSta = CallSta;
    }

    public boolean isHaveCall() {
        if (mCallSta > 1) {
            return true;
        }
        return false;
    }

    public String getPhoneName() {
        String strName = "";
        if (isConnected()) {
            if (mLocalAdapter == null) {
                System.out.println("------------->mLocalAdapter");
                return strName;
            } else if (mDeviceLists.isEmpty()) {
                Log.d(TAG, "getPhoneName mDeviceLists is null!");
                return null;
            } else {
                CachedBluetoothDevice cachedDevice = mDeviceManager.findDevice(mDeviceLists.get(0));
                if (cachedDevice != null) {
                    strName = cachedDevice.getName();
                }
            }
        }
        return strName;
    }

    public String getVersion() {
        BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mAdapter == null) {
            return VER_STR;
        }
        String strAddr = mAdapter.getAddress();
        if (strAddr == null) {
            strAddr = "null";
        }
        VER_ID = String.format("%s(%s)", new Object[]{VER_STR, strAddr});
        return VER_ID;
    }

    public void update(String title, String artist, String album) {
        mStrId3Name = title;
        mStrId3Artist = artist;
        mStrId3Album = album;
    }

    public String getCallingNum() {
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
            return "";
        } else if (mDeviceLists.isEmpty()) {
            Log.d(TAG, "mDeviceLists is null!");
            return "";
        } else {
            List<BluetoothHeadsetClientCall> callList = hfProfile.getCurrentCalls(mDeviceLists.get(0));
            if (callList != null && callList.size() != 0) {
                return callList.get(0).getNumber();
            }
            Log.e(TAG, "Call had been ended before this activity create!");
            return "";
        }
    }

    public void regPlayerUtility() {
        if (mAvrcpCtProfile != null) {
            mAvrcpCtProfile.regPlayerUtility();
        }
    }

    public boolean IsNeedUpdatePhoneName() {
        return mbNeedUpdatePhoneName;
    }

    public void ResetUpdatePhoneName() {
        mbNeedUpdatePhoneName = false;
    }

    public boolean powerAbnomarlCheck() {
        getLocalAdapter();
        if (checkIfAbnormal()) {
            Log.d(TAG, "Bluetooth reseting");
        } else {
            powerOn();
        }
        return false;
    }

    public boolean isBtEnabled() {
        if (mLocalAdapter != null) {
            return mLocalAdapter.isEnabled();
        }
        return false;
    }

    private boolean checkIfAbnormal() {
        boolean IsHfConnected;
        List<BluetoothDevice> deviceList;
        if (mLocalAdapter == null || !mLocalAdapter.isEnabled() || (IsHfConnected = bluetoothIsConnected()) == mbHfConnected) {
            return false;
        }
        Log.e(TAG, "checkIfAbnormal IsHfConnected != mbHfConnected");
        if (IsHfConnected) {
            HeadsetClientProfile hfProfile = getProfile(16);
            if (!(hfProfile == null || (deviceList = hfProfile.getConnectedDevices()) == null || deviceList.size() <= 0)) {
                Log.d(TAG, "bt is connected");
                mbNeedUpdatePhoneName = true;
                mbHfConnected = true;
                mCallSta = 1;
                mbNeedSaveData = true;
                synchronized (mDeviceLists) {
                    for (int i = 0; i < mDeviceLists.size(); i++) {
                        BluetoothDevice btdevice = mDeviceLists.get(i);
                        if (!btdevice.isConnected()) {
                            mDeviceLists.remove(btdevice);
                        }
                    }
                    if (!mDeviceLists.contains(deviceList.get(0))) {
                        mDeviceLists.add(deviceList.get(0));
                    }
                }
                getBtInstance().saveLastAddr();
                getBtInstance().UpdateHfpSta();
                getBtInstance().updatePbList();
                getBtInstance().UpdatePbMap();
                mPbStatus = 2;
                Log.e(TAG, "hfp connected!");
                return true;
            }
        } else {
            Log.d(TAG, "bt is disconnected");
            checkHfp();
        }
        return true;
    }

    public void powerOn() {
        getLocalAdapter();
        if (getBtEnabled() && !mLocalAdapter.isEnabled()) {
            Log.d(TAG, "powerOn");
            mLocalAdapter.setBluetoothEnabled(true);
        }
    }

    public boolean bluetoothIsConnected() {
        List<BluetoothDevice> deviceList;
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null || (deviceList = hfProfile.getConnectedDevices()) == null || deviceList.size() <= 0) {
            return false;
        }
        if (D) {
            Log.e(TAG, "hfp connected!");
        }
        return true;
    }

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1200) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean isDialFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 5000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public void PbSort(ArrayList<PbItem> list) {
        Log.d("lh", "PbSort");
        if (list.size() > 0 && mContext != null) {
            Locale curLocal = mContext.getResources().getConfiguration().locale;
            Log.d(TAG, "lgb CurLocal = " + curLocal);
            this.mCmp = Collator.getInstance(curLocal);
            Collections.sort(list, new Comparator() {
                public int compare(Object o1, Object o2) {
                    return BtExe.this.mCmp.compare(((PbItem) o1).pinyin, ((PbItem) o2).pinyin);
                }
            });
        }
    }

    public static void PbSearch(String strKey) {
        Log.d(TAG, "PbSearch begin");
        mListSearch.clear();
        if (strKey != null && !strKey.isEmpty() && mListPb != null && mListPb.size() != 0) {
            PyMatch.SetSubstr(strKey);
            for (int i = 0; i < mListPb.size(); i++) {
                PbItem pi = mListPb.get(i);
                int matchPos = PyMatch.GetMatchVal(pi.name);
                if (-1 != matchPos) {
                    SearchItem si = new SearchItem();
                    si.pb = pi;
                    si.MatchPos = matchPos;
                    mListSearch.add(si);
                }
            }
            Log.d(TAG, "PbSearch list = " + mListSearch.size());
            if (mListSearch.size() > 0) {
                Collections.sort(mListSearch, new Comparator() {
                    public int compare(Object o1, Object o2) {
                        return ((SearchItem) o1).MatchPos - ((SearchItem) o2).MatchPos;
                    }
                });
            }
        }
    }

    public boolean isBtCountry() {
        String language = mContext.getResources().getConfiguration().locale.getLanguage();
        Log.d("lh", "isBtCountry = " + language);
        for (String equals : mLanguages) {
            if (language.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    public boolean isZhCountry() {
        String language = mContext.getResources().getConfiguration().locale.getLanguage();
        Log.d("lh3", "isBtCountry = " + language);
        if (language.equals("zh")) {
            return true;
        }
        return false;
    }

    class UpdateAsyncTask extends AsyncTask<Void, Void, Void> {
        UpdateAsyncTask() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... arg0) {
            try {
                BtExe.this.addContact();
            } catch (Exception e) {
                e.printStackTrace();
            }
            BtExe.this.PbSort(BtExe.mListPb);
            if (1 == BtExe.mPbStatus) {
                BtExe.mPbStatus = 2;
            }
            BtExe.getBtInstance().UpdatePbMap();
            BtExe.this.SaveDatabase();
            return null;
        }
    }

    public void addContact() throws Exception {
        Log.d(TAG, "addContact");
        Cursor dataCursor = mContext.getContentResolver().query(BluetoothContactsData.CONTENT_URI, (String[]) null, (String) null, (String[]) null, (String) null);
        if (dataCursor.moveToLast()) {
            if (dataCursor.getColumnIndex("given_name") != -1) {
                boolean isZhCountry = isZhCountry();
                boolean isBtCountry = isBtCountry();
                Log.d(TAG, "exist givenname1");
                do {
                    PbItem map = new PbItem();
                    map.name = dataCursor.getString(dataCursor.getColumnIndex("display_name"));
                    map.num = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                    map.pinyin = Cn2Spell.getPinYin(map.name).toUpperCase().trim();
                    map.first_name = dataCursor.getString(dataCursor.getColumnIndex("first_name"));
                    map.middle_name = dataCursor.getString(dataCursor.getColumnIndex("middle_name"));
                    map.given_name = dataCursor.getString(dataCursor.getColumnIndex("given_name"));
                    if (map.num != null && !map.num.isEmpty() && map.num.matches("[\\d\\+\\-\\.\\,\\(\\)\\*\\#\\/\\s]*") && map.name != null && !map.name.isEmpty() && !map.name.startsWith("@@@")) {
                        map.pinyin = updateSortRow(isZhCountry, isBtCountry, map);
                        mListPb.add(map);
                    }
                } while (dataCursor.moveToPrevious());
            } else {
                Log.d(TAG, "not exist givenname");
                do {
                    PbItem map2 = new PbItem();
                    map2.name = dataCursor.getString(dataCursor.getColumnIndex("display_name"));
                    map2.num = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                    map2.pinyin = Cn2Spell.getPinYin(map2.name).toUpperCase();
                    map2.first_name = "";
                    map2.middle_name = "";
                    map2.given_name = map2.name;
                    if (map2.num != null && !map2.num.isEmpty() && map2.num.matches("[\\d\\+\\-\\.\\,\\(\\)\\*\\#\\/\\s]*") && map2.name != null && !map2.name.isEmpty() && !map2.name.startsWith("@@@")) {
                        mListPb.add(map2);
                    }
                } while (dataCursor.moveToPrevious());
            }
            sendPbUpdate();
        }
        dataCursor.close();
    }

    /* access modifiers changed from: package-private */
    public void downLoad() {
        checkHfp();
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf dapter fail!");
            return;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "hf client is not connected!");
            return;
        }
        BluetoothPBManager pbManager = getPBManager();
        if (pbManager == null) {
            Log.e(TAG, "get pbManager fail!");
        } else {
            startDownload(pbManager, 6);
        }
    }

    public void regPBCallback() {
        BluetoothPBManager pbManager = getPBManager();
        if (pbManager != null) {
            pbManager.regPBCallback(this.mPBCallback);
        }
    }

    public void unregPBCallback() {
        BluetoothPBManager pbManager = getPBManager();
        if (pbManager != null) {
            pbManager.unregPBCallback(this.mPBCallback);
        }
    }

    public void SaveDatabase() {
        Log.d(TAG, "SaveDatabase = ");
        long startTime = System.currentTimeMillis();
        String addr = getAddr();
        if (addr != null && !addr.isEmpty()) {
            delete("phonebook", "addr=?", new String[]{addr});
            Cursor dataCursor = mContext.getContentResolver().query(BluetoothContactsData.CONTENT_URI, (String[]) null, (String) null, (String[]) null, (String) null);
            List<PbItem> list = new ArrayList<>();
            if (!dataCursor.moveToLast()) {
                insertPhonebookList(list);
                dataCursor.close();
                Log.d("lh", "finished");
            } else if (dataCursor.getColumnIndex("given_name") != -1) {
                Log.d(TAG, "exist givenname");
                do {
                    String name = dataCursor.getString(dataCursor.getColumnIndex("display_name"));
                    list.add(updatePbItem(name, dataCursor.getString(dataCursor.getColumnIndex("data1")), Cn2Spell.getPinYin(name).toUpperCase().trim(), dataCursor.getString(dataCursor.getColumnIndex("first_name")), dataCursor.getString(dataCursor.getColumnIndex("middle_name")), dataCursor.getString(dataCursor.getColumnIndex("given_name"))));
                } while (dataCursor.moveToPrevious());
            } else {
                Log.d(TAG, "not exist givenname");
                do {
                    String name2 = dataCursor.getString(dataCursor.getColumnIndex("display_name"));
                    list.add(updatePbItem(name2, dataCursor.getString(dataCursor.getColumnIndex("data1")), Cn2Spell.getPinYin(name2).toUpperCase(), "", "", name2));
                } while (dataCursor.moveToPrevious());
            }
            insertPhonebookList(list);
            dataCursor.close();
            Log.d("lh", "finished");
        }
        Log.d("lh3", new StringBuilder(String.valueOf(System.currentTimeMillis() - startTime)).toString());
    }

    private PbItem updatePbItem(String name, String num, String pinyin, String first_name, String middle_name, String given_name) {
        PbItem pbItem = new PbItem();
        pbItem.name = name;
        pbItem.num = num;
        pbItem.pinyin = pinyin;
        pbItem.first_name = first_name;
        pbItem.middle_name = middle_name;
        pbItem.given_name = given_name;
        pbItem.collect = 0;
        return pbItem;
    }

    /* access modifiers changed from: package-private */
    public void regMetadataCallback() {
        Log.d(TAG, "regMetadataCallback");
        AvrcpControllerProfile avrcpProfile = getProfile(12);
        if (avrcpProfile != null) {
            avrcpProfile.regMetaCallback(this.mMetadataCallback);
            avrcpProfile.setPlayerState(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void unregMetadataCallback() {
        Log.d(TAG, "unregMetadataCallback");
        AvrcpControllerProfile avrcpProfile = getProfile(12);
        if (avrcpProfile != null) {
            avrcpProfile.unregMetaCallback(this.mMetadataCallback);
            avrcpProfile.setPlayerState(false);
        }
    }

    public void setAutoDisconnect(boolean isAutoDisconnect) {
        mIsAutoDisconnect = isAutoDisconnect;
    }

    public boolean getAutoDisconnect() {
        return mIsAutoDisconnect;
    }

    public void insertPhonebookProvider(String name, String num) {
        insertPhonebookProvider(name, num, "");
    }

    public void insertPhonebookProvider(String name, String num, String addr) {
        Uri uri = Uri.parse("content://com.nwd.bt.provider/phonebook");
        ContentValues values = new ContentValues();
        values.put("phonebook_name", name);
        values.put("phonebook_number", num);
        values.put("phonebook_mac", addr);
        mContext.getContentResolver().insert(uri, values);
    }

    public void insertPhonebookProviderList(List<PbItem> lists, String addr) {
        Uri uri = Uri.parse("content://com.nwd.bt.provider/phonebook");
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        int size = lists.size();
        for (int i = 0; i < size; i++) {
            PbItem pbItem = lists.get(i);
            ops.add(ContentProviderOperation.newInsert(uri).withValue("phonebook_name", pbItem.name).withValue("phonebook_number", pbItem.num).withValue("phonebook_mac", addr).withYieldAllowed(true).build());
        }
        try {
            mContext.getContentResolver().applyBatch(PhoneBookProvider.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e2) {
            e2.printStackTrace();
        }
    }

    public void deletePhonebookProvider() {
        mContext.getContentResolver().delete(Uri.parse("content://com.nwd.bt.provider/phonebook"), (String) null, (String[]) null);
    }

    public void insertPhonebookProvider1(String name, String num) {
        insertPhonebookProvider1(name, num, "");
    }

    public void insertPhonebookProvider1(String name, String num, String addr) {
        Uri uri = Uri.parse("content://com.glsx.bt.provider/phonebook");
        ContentValues values = new ContentValues();
        values.put("phonebook_name", name);
        values.put("phonebook_number", num);
        values.put("phonebook_mac", addr);
        mContext.getContentResolver().insert(uri, values);
    }

    public void insertPhonebookProviderList1(List<PbItem> lists, String addr) {
        Uri uri = Uri.parse("content://com.glsx.bt.provider/phonebook");
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        int size = lists.size();
        for (int i = 0; i < size; i++) {
            PbItem pbItem = lists.get(i);
            ops.add(ContentProviderOperation.newInsert(uri).withValue("phonebook_name", pbItem.name).withValue("phonebook_number", pbItem.num).withValue("phonebook_mac", addr).withYieldAllowed(true).build());
        }
        try {
            mContext.getContentResolver().applyBatch(GlsxDdBoxProvider.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e2) {
            e2.printStackTrace();
        }
    }

    public void deletePhonebookProvider1() {
        mContext.getContentResolver().delete(Uri.parse("content://com.glsx.bt.provider/phonebook"), (String) null, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public void sendUpdatePhonebookComplete() {
        mContext.sendBroadcast(new Intent("com.ts.bt.SAVE_PHONEBOOK_COMPLETE"));
    }

    public boolean isAutoPause() {
        return this.isAutoPause;
    }

    public void setAutoPause(boolean isAutoPause2) {
        this.isAutoPause = isAutoPause2;
    }

    public boolean isBackCar() {
        return this.isBackCar;
    }

    public void setBackCar(boolean isBackCar2) {
        this.isBackCar = isBackCar2;
    }

    public void clearBtData() {
        removePairDevice();
        delete("phonebook");
        delete("diallog");
        delete("boned_device");
        pbClear();
        clearLastAddr();
        isSaveLastAddr = false;
    }

    public void removePairDevice() {
        Log.d(TAG, "removePairDevice");
        if (mLocalAdapter != null) {
            for (BluetoothDevice device : mLocalAdapter.getBondedDevices()) {
                unpairDevice(device);
            }
        }
    }

    public void unpairDevice(String addr) {
        if (mLocalAdapter != null && !TextUtils.isEmpty(addr) && checkBluetoothAddress(addr)) {
            unpairDevice(mLocalAdapter.getRemoteDevice(addr));
        }
    }

    private void unpairDevice(BluetoothDevice device) {
        try {
            device.getClass().getMethod("removeBond", (Class[]) null).invoke(device, (Object[]) null);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public static boolean isExistAddr(String addr) {
        if (dbHelper != null) {
            if (mBtDb == null) {
                mBtDb = dbHelper.getWritableDatabase();
            }
            Cursor cursor1 = mBtDb.query("boned_device", (String[]) null, "addr=?", new String[]{addr}, (String) null, (String) null, (String) null);
            Log.d(TAG, "size = " + cursor1.getCount());
            if (cursor1.moveToNext()) {
                cursor1.close();
                return true;
            }
            cursor1.close();
        }
        return false;
    }

    public static void deleteBondedDevice(String addr) {
        if (dbHelper != null) {
            if (mBtDb == null) {
                mBtDb = dbHelper.getWritableDatabase();
            }
            mBtDb.delete("boned_device", "addr=?", new String[]{addr});
        }
    }

    public static List<BonedDevice> updateBonedDeviceList() {
        List<BonedDevice> pbList = new ArrayList<>();
        if (dbHelper != null) {
            if (mBtDb == null) {
                mBtDb = dbHelper.getWritableDatabase();
            }
            Cursor cursor1 = mBtDb.query("boned_device", (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            if (cursor1.moveToLast()) {
                do {
                    String addr = cursor1.getString(cursor1.getColumnIndex("addr"));
                    String name = cursor1.getString(cursor1.getColumnIndex(IConstantData.KEY_NAME));
                    BonedDevice bonedDevice = new BonedDevice();
                    bonedDevice.addr = addr;
                    bonedDevice.name = name;
                    pbList.add(bonedDevice);
                    if (pbList.size() >= 5 || !cursor1.moveToPrevious()) {
                        cursor1.close();
                    }
                    String addr2 = cursor1.getString(cursor1.getColumnIndex("addr"));
                    String name2 = cursor1.getString(cursor1.getColumnIndex(IConstantData.KEY_NAME));
                    BonedDevice bonedDevice2 = new BonedDevice();
                    bonedDevice2.addr = addr2;
                    bonedDevice2.name = name2;
                    pbList.add(bonedDevice2);
                    break;
                } while (!cursor1.moveToPrevious());
                cursor1.close();
            }
        }
        return pbList;
    }

    public static void insertBonedDevice(String addr, String name) {
        if (dbHelper != null) {
            if (mBtDb == null) {
                mBtDb = dbHelper.getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put("addr", addr);
            values.put(IConstantData.KEY_NAME, name);
            mBtDb.insert("boned_device", (String) null, values);
            values.clear();
        }
    }

    public void registerBtReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PARING_REQUEST_INTENT);
        intentFilter.addAction(BT_CONNECTION_REQUEST);
        intentFilter.addAction(BT_NAME_AND_PINCODE_REQUEST);
        intentFilter.addAction(BT_SYNC_CONTACT_REQUEST);
        if (isCallback) {
            intentFilter.addAction("android.bluetooth.headsetclient.profile.action.RESULT");
            intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
            intentFilter.addAction(BROADCAST_REQUEST_HFPSTATUS);
        }
        mContext.registerReceiver(this.mBtReceiver, intentFilter);
    }

    public void unregisterBtReceiver() {
        mContext.unregisterReceiver(this.mBtReceiver);
    }

    /* access modifiers changed from: private */
    public void sendConnectionChange() {
        Intent intent = new Intent();
        intent.setAction(BT_CONNECTION_CHANGE);
        int value = 0;
        if (isConnected()) {
            value = 1;
        }
        intent.putExtra("extra_bt_connection_event", value);
        mContext.sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void sendBtNameAndPincode() {
        readDeviceNamePin();
        Intent intent = new Intent();
        intent.setAction(BT_NAME_AND_PINCODE);
        intent.putExtra("extra_bt_name", this.mCurName);
        intent.putExtra("extra_bt_pincode", mPin);
        mContext.sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void sendBtSyncContact() {
        Intent intent = new Intent();
        String addr = getAddr();
        intent.setAction(DDHU_SYNC_CONTACT_FINISH);
        intent.putExtra("extra_bt_ddhu_sync_contact_state", 1);
        intent.putExtra("extra_bt_connected_mac", addr);
        mContext.sendBroadcast(intent);
    }

    public String getLocalBtAddress() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        String strAddr = null;
        if (bluetoothAdapter != null) {
            strAddr = bluetoothAdapter.getAddress();
        }
        if (strAddr == null) {
            return "null";
        }
        return strAddr;
    }

    public BluetoothHeadsetClientCall getCall(List<BluetoothHeadsetClientCall> callList, int... states) {
        for (BluetoothHeadsetClientCall c : callList) {
            int length = states.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    if (c.getState() == states[i]) {
                        return c;
                    }
                    i++;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void addIgnoreHistory() {
        List<BluetoothHeadsetClientCall> callList = getCurrentCalls();
        if (callList != null) {
            Log.d("lh6", "size6: " + callList.size());
        }
        if (callList == null || callList.size() == 0) {
            isShowBox = false;
            if (getBtInstance().mCallMap.size() > 0) {
                for (Map.Entry<String, PhoneCall> element : getBtInstance().mCallMap.entrySet()) {
                    String callNumber = element.getKey();
                    if (getBtInstance().mCallMap.containsKey(callNumber)) {
                        PhoneCall phonecall = getBtInstance().mCallMap.get(callNumber);
                        long callActiveSecond = phonecall.getCallActiveSecond();
                        String callName = phonecall.getCallName();
                        String callType = phonecall.getCallType();
                        isHideDialog = false;
                        isAddCall = false;
                        Log.d(TAG, "callType = " + callType);
                        mStrPhoneNo = callNumber;
                        getBtInstance().UpdateCallName();
                        Time t = new Time();
                        t.setToNow();
                        String time1 = t.toString();
                        String time = String.valueOf(time1.substring(0, 4)) + "-" + time1.substring(4, 6) + "-" + time1.substring(6, 8) + " " + time1.substring(9, 11) + ":" + time1.substring(11, 13) + ":" + time1.substring(13, 15);
                        Log.d(TAG, "currentTime: " + time);
                        if (mStrPhoneNo != null && !mStrPhoneNo.isEmpty()) {
                            mLastPhoneNo = mStrPhoneNo;
                        }
                        getBtInstance().insertDiallog(callName, callNumber, time, callType, callActiveSecond);
                        updateHistory();
                        isRefreshLog = true;
                        getBtInstance().mCallMap.remove(callNumber);
                    }
                }
                getBtInstance().mCallMap.clear();
            }
        }
    }

    public static void updateHistory() {
        if (dbHelper != null) {
            ArrayList<HashMap<String, Object>> historyList = new ArrayList<>();
            Cursor dbCursor = dbHelper.getWritableDatabase().query("diallog", (String[]) null, "addr=?", new String[]{getAddr()}, (String) null, (String) null, (String) null);
            if (dbCursor.getCount() != 0) {
                if (dbCursor.moveToLast()) {
                    do {
                        HashMap<String, Object> map = new HashMap<>();
                        String name = dbCursor.getString(dbCursor.getColumnIndex(IConstantData.KEY_NAME));
                        String num = dbCursor.getString(dbCursor.getColumnIndex(CanBMWMiniServiceDetailActivity.KEY_NUM));
                        String time = dbCursor.getString(dbCursor.getColumnIndex("time"));
                        String type = dbCursor.getString(dbCursor.getColumnIndex(IConstantData.KEY_TYPE));
                        map.put(ITEM_HISTORY_NAME, name);
                        map.put(ITEM_HISTORY_NUMBER, num);
                        map.put(ITEM_HISTORY_TIME, time);
                        map.put(ITEM_HISTORY_TYPE, type);
                        historyList.add(map);
                    } while (dbCursor.moveToPrevious());
                }
                dbCursor.close();
            }
            mAllHistoryList.clear();
            mAllHistoryList.addAll(historyList);
        }
    }

    public static void flushFilterList() {
        int length = mAllHistoryList.size();
        mHistoryList.clear();
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = mAllHistoryList.get(i);
            String type = (String) map.get(ITEM_HISTORY_TYPE);
            if (!TextUtils.isEmpty(type)) {
                if (mlistFilter == 1 && type.equals("outgoing")) {
                    mHistoryList.add(map);
                }
                if (mlistFilter == 2 && type.equals("incoming")) {
                    mHistoryList.add(map);
                }
                if (mlistFilter == 4 && type.equals("missed")) {
                    mHistoryList.add(map);
                }
            }
        }
        isRefreshLog = true;
    }

    public void setIReceiver(IReceiver iReceiver) {
        this.mIReceiver = iReceiver;
    }

    public void SetTimerCallBack(BtUITimer pfn) {
        if (this.mCbTimer == null) {
            this.mCbTimer = pfn;
        }
    }

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "action = " + intent.getAction());
    }

    /* access modifiers changed from: private */
    public void onBluetoothPairingRequest(Context context, Intent intent) {
        BluetoothDevice remoteDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int type = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", ExploreByTouchHelper.INVALID_ID);
        if (D) {
            Log.d(TAG, "[Pair] Device : " + remoteDevice.getName() + ", type = " + type);
        }
        switch (type) {
            case 0:
            case 5:
            case 7:
                remoteDevice.setPin(BluetoothDevice.convertPinToBytes(mPin));
                return;
            case 1:
            case 3:
            case 6:
                return;
            case 2:
            case 4:
                if (intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", ExploreByTouchHelper.INVALID_ID) == Integer.MIN_VALUE) {
                    Log.e(TAG, "Invalid Confirmation Passkey received, not showing any dialog");
                    return;
                } else {
                    remoteDevice.setPairingConfirmation(true);
                    return;
                }
            default:
                Log.e(TAG, "Incorrect pairing type received, not showing any dialog");
                return;
        }
    }

    /* access modifiers changed from: private */
    public void onBluetoothAclDisconnected(Context context, Intent intent) {
        isShowBox = false;
        isHideDialog = false;
        isAddCall = false;
        getBtInstance().updateCallSta();
        getBtInstance().mCallMap.clear();
        dispatchMessage(18, (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), 0, 0);
    }

    public void onActionCallChanged(Context context, Intent intent) {
        BluetoothHeadsetClientCall callStatus = intent.getParcelableExtra("android.bluetooth.headsetclient.extra.CALL");
        if (callStatus == null) {
            Log.e(TAG, "get callStatus fall!");
            return;
        }
        String callNumber = callStatus.getNumber();
        if (!callNumber.startsWith("+86") && callNumber.indexOf("+") == 0) {
            callNumber = callNumber.replace("+", "");
        }
        mStrPhoneNo = callNumber;
        UpdateCallName(callNumber);
        int state = callStatus.getState();
        Log.d("lh6", "state: " + state);
        if (this.btCallback != null) {
            this.btCallback.onBtStateChange(state, callNumber);
        }
        String callName = new String(mStrCallName);
        if (state == 7) {
            updateCallSta();
            if (this.mCallMap.containsKey(callNumber)) {
                PhoneCall phonecall = this.mCallMap.get(callNumber);
                long callActiveSecond = phonecall.getCallActiveSecond();
                String callName2 = phonecall.getCallName();
                String callType = phonecall.getCallType();
                isHideDialog = false;
                isAddCall = false;
                Log.d(TAG, "callType = " + callType);
                mStrPhoneNo = callNumber;
                UpdateCallName();
                Time t = new Time();
                t.setToNow();
                String time1 = t.toString();
                String time = String.valueOf(time1.substring(0, 4)) + "-" + time1.substring(4, 6) + "-" + time1.substring(6, 8) + " " + time1.substring(9, 11) + ":" + time1.substring(11, 13) + ":" + time1.substring(13, 15);
                Log.d(TAG, "currentTime: " + time);
                if (mStrPhoneNo != null && !mStrPhoneNo.isEmpty()) {
                    mLastPhoneNo = mStrPhoneNo;
                }
                getBtInstance().insertDiallog(callName2, callNumber, time, callType, callActiveSecond);
                updateHistory();
                isRefreshLog = true;
                this.mCallMap.remove(callNumber);
                return;
            }
        }
        if (this.mCallMap.containsKey(callNumber)) {
            Log.d("lh6", "second");
            PhoneCall phoneCall = this.mCallMap.get(callNumber);
            int lastState = phoneCall.getCallState();
            if (state == 2 || state == 3) {
                isHideDialog = false;
                phoneCall.setCallType("outgoing");
            } else if (state == 4 || state == 5) {
                isHideDialog = false;
                phoneCall.setCallType("missed");
            } else if (state == 0) {
                if (lastState == 4 || lastState == 5) {
                    phoneCall.setCallType("incoming");
                }
                if (lastState == 2 || lastState == 3 || lastState == 4 || lastState == 5) {
                    long callActiveTick = getTickCount();
                    phoneCall.setCallActiveSecond(0);
                    phoneCall.setCallActiveTick(callActiveTick);
                }
            }
            phoneCall.setCallName(callName);
            phoneCall.setCallState(state);
            this.mCallMap.put(callNumber, phoneCall);
        } else {
            Log.d("lh6", "first");
            PhoneCall phoneCall2 = new PhoneCall();
            if (state == 2 || state == 3) {
                phoneCall2.setCallType("outgoing");
            } else if (state == 4 || state == 5) {
                phoneCall2.setCallType("missed");
            } else if (state == 0 && (-1 == 4 || -1 == 5)) {
                phoneCall2.setCallType("incoming");
            }
            int lastState2 = state;
            phoneCall2.setCallName(callName);
            phoneCall2.setCallState(state);
            this.mCallMap.put(callNumber, phoneCall2);
        }
        if (state == 4 || state == 2 || state == 0 || state == 3 || state == 5) {
            isShowBox = true;
        } else {
            Log.d(TAG, "ignore callState !");
        }
    }

    public void UpdateCallName(String phoneNumber) {
        String strPbName;
        String strPbName2;
        String strPbName3;
        String strPbName4;
        String strPbName5;
        String strPbName6;
        Log.d(TAG, "UpdateCallName");
        mStrCallName = "";
        if (mListPb != null) {
            String phoneNumber2 = phoneNumber.replace("-", "").replace(" ", "").replace("(", "").replace(")", "");
            int iNoLen = phoneNumber2.length();
            int i = 0;
            while (i < mListPb.size()) {
                String strPbNo = mListPb.get(i).num.replace("-", "").replace(" ", "").replace("(", "").replace(")", "");
                if (iNoLen != strPbNo.length() || !phoneNumber2.equals(strPbNo)) {
                    i++;
                } else {
                    if (isBtCountry()) {
                        strPbName6 = mListPb.get(i).name;
                    } else {
                        String first_name = mListPb.get(i).first_name;
                        String middle_name = mListPb.get(i).middle_name;
                        String strPbName7 = String.valueOf("") + mListPb.get(i).given_name;
                        if (!strPbName7.isEmpty()) {
                            strPbName5 = String.valueOf(strPbName7) + " " + middle_name;
                        } else {
                            strPbName5 = String.valueOf(strPbName7) + middle_name;
                        }
                        if (!strPbName5.isEmpty()) {
                            strPbName6 = String.valueOf(strPbName5) + " " + first_name;
                        } else {
                            strPbName6 = String.valueOf(strPbName5) + first_name;
                        }
                    }
                    Log.d("lh", "num =" + phoneNumber2);
                    Log.d("lh", "name = " + strPbName6);
                    mStrCallName = strPbName6;
                    return;
                }
            }
            if (iNoLen >= 7 && mStrCallName.length() == 0) {
                int recentPatch = Integer.MAX_VALUE;
                for (int i2 = 0; i2 < mListPb.size(); i2++) {
                    String strPbNo2 = mListPb.get(i2).num.replace("-", "").replace(" ", "").replace("(", "").replace(")", "");
                    int iPbLen = strPbNo2.length();
                    if (iNoLen >= iPbLen) {
                        if (phoneNumber2.substring(iNoLen - iPbLen).equals(strPbNo2) && iPbLen != 0) {
                            if (isBtCountry()) {
                                strPbName4 = mListPb.get(i2).name;
                            } else {
                                String first_name2 = mListPb.get(i2).first_name;
                                String middle_name2 = mListPb.get(i2).middle_name;
                                String strPbName8 = String.valueOf("") + mListPb.get(i2).given_name;
                                if (!strPbName8.isEmpty()) {
                                    strPbName3 = String.valueOf(strPbName8) + " " + middle_name2;
                                } else {
                                    strPbName3 = String.valueOf(strPbName8) + middle_name2;
                                }
                                if (!strPbName3.isEmpty()) {
                                    strPbName4 = String.valueOf(strPbName3) + " " + first_name2;
                                } else {
                                    strPbName4 = String.valueOf(strPbName3) + first_name2;
                                }
                            }
                            int index = iNoLen - iPbLen;
                            if (index < recentPatch) {
                                mStrCallName = strPbName4;
                                recentPatch = index;
                            }
                        }
                    } else if (iNoLen < iPbLen && strPbNo2.substring(iPbLen - iNoLen).equals(phoneNumber2)) {
                        if (isBtCountry()) {
                            strPbName2 = mListPb.get(i2).name;
                        } else {
                            String first_name3 = mListPb.get(i2).first_name;
                            String middle_name3 = mListPb.get(i2).middle_name;
                            String strPbName9 = String.valueOf("") + mListPb.get(i2).given_name;
                            if (!strPbName9.isEmpty()) {
                                strPbName = String.valueOf(strPbName9) + " " + middle_name3;
                            } else {
                                strPbName = String.valueOf(strPbName9) + middle_name3;
                            }
                            if (!strPbName.isEmpty()) {
                                strPbName2 = String.valueOf(strPbName) + " " + first_name3;
                            } else {
                                strPbName2 = String.valueOf(strPbName) + first_name3;
                            }
                        }
                        int index2 = iPbLen - iNoLen;
                        if (index2 < recentPatch) {
                            mStrCallName = strPbName2;
                            recentPatch = index2;
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void onATCmdResult(Context context, Intent intent) {
        BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int resultCode = intent.getIntExtra("android.bluetooth.headsetclient.extra.RESULT_CODE", 0);
        if (resultCode == 4) {
            dispatchMessage(122, device, 0, 0);
            Log.d(TAG, "onATCmdResult: MSG_ATCMD_NO_RESPONSE " + device);
        } else if (resultCode == 7) {
            handleATCmdCME(device, intent.getIntExtra("android.bluetooth.headsetclient.extra.CME_CODE", 0));
        }
    }

    public static void handleATCmdCME(BluetoothDevice device, int cmeCode) {
        if (D) {
            Log.d(TAG, "handleATCmdCME. " + device + ", cmeCode = " + cmeCode);
        }
        if (cmeCode == 0) {
            Log.e(TAG, "phone failure!");
        } else if (cmeCode == 31) {
            Log.e(TAG, "network timeout!");
        } else if (cmeCode == 32) {
            Log.e(TAG, "emergency service only!");
        } else if (cmeCode == 27) {
            Log.e(TAG, "invalid character in dial string!");
        }
    }

    private void startBtIntentService() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.BT_INTENT_SERVICE");
        intent.setPackage("com.ts.MainUI");
        mContext.startService(intent);
    }

    public static synchronized List<BluetoothHeadsetClientCall> getCurrentCalls() {
        List<BluetoothHeadsetClientCall> callList = null;
        synchronized (BtExe.class) {
            HeadsetClientProfile hfProfile = getProfile(16);
            if (hfProfile == null) {
                Log.e(TAG, "get hf profile fail!");
            } else {
                List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
                if (deviceList == null || deviceList.size() == 0) {
                    Log.e(TAG, "hf client is not connected!");
                } else {
                    callList = hfProfile.getCurrentCalls(deviceList.get(0));
                    if (callList == null) {
                        Log.e(TAG, "callList is null!");
                    }
                }
            }
        }
        return callList;
    }

    /* access modifiers changed from: package-private */
    public void setCbTimer() {
        try {
            Class<?> clazz = Class.forName("com.ts.bt.BtReceiver");
            Field field = clazz.getDeclaredField("timeCallBack");
            field.setAccessible(true);
            if (this.mCbTimer == null) {
                this.mCbTimer = (BtUITimer) field.get(clazz);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.d("__lh__", "exception: " + e.toString());
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
            Log.d("__lh__", "exception: " + e2.toString());
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            Log.d("__lh__", "exception: " + e3.toString());
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
            Log.d("__lh__", "exception: " + e4.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void clearCbTimer() {
        if (this.mCbTimer != null) {
            this.mCbTimer = null;
        }
    }

    public boolean isPhoneMeeting() {
        boolean isPhoneMeeting = false;
        if (getBtInstance().mCallMap.size() > 1) {
            int i = 0;
            int firstState = -1;
            for (Map.Entry<String, PhoneCall> element : getBtInstance().mCallMap.entrySet()) {
                int state = element.getValue().getCallState();
                if (i == 0) {
                    firstState = state;
                } else if (i == 1 && state == firstState) {
                    if (state == 0) {
                        isPhoneMeeting = true;
                    } else if (state == 1) {
                        isPhoneMeeting = true;
                    }
                }
                i++;
            }
        }
        return isPhoneMeeting;
    }

    public boolean isMultiCall() {
        boolean isMultiCall = false;
        int size = getBtInstance().mCallMap.size();
        if (size == 2) {
            int i = 0;
            int firstState = -1;
            for (Map.Entry<String, PhoneCall> element : getBtInstance().mCallMap.entrySet()) {
                int state = element.getValue().getCallState();
                if (i == 0) {
                    firstState = state;
                } else if (i == 1) {
                    if ((state == 0 && (firstState == 0 || firstState == 1)) || (firstState == 0 && (state == 0 || state == 1))) {
                        isMultiCall = true;
                    } else {
                        isMultiCall = false;
                    }
                }
                i++;
            }
            return isMultiCall;
        } else if (size > 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isActive() {
        int size = getBtInstance().mCallMap.size();
        if (size == 1) {
            int i = 0;
            for (Map.Entry<String, PhoneCall> element : getBtInstance().mCallMap.entrySet()) {
                if (element.getValue().getCallState() == 0) {
                    return true;
                }
                i++;
            }
            return false;
        } else if (size > 1) {
            return true;
        } else {
            return false;
        }
    }

    public void setBluetoothEnabled(boolean checked) {
        if (mLocalAdapter == null) {
            mLocalAdapter = LocalBluetoothAdapter.getInstance();
        }
        if (mLocalAdapter != null) {
            int state = getBluetoothAdapterState();
            if (state == 12) {
                if (!checked) {
                    Log.d(TAG, "off");
                    mLocalAdapter.setBluetoothEnabled(false);
                    saveBtEnabled(false);
                }
            } else if (state == 10 && checked) {
                Log.d(TAG, "on");
                mLocalAdapter.setBluetoothEnabled(true);
                saveBtEnabled(true);
            }
        }
    }

    public boolean getBtEnabled() {
        return mContext.getSharedPreferences("bt_info", 0).getBoolean("bt_enabled", true);
    }

    public void saveBtEnabled(boolean enabled) {
        SharedPreferences.Editor sharedata = mContext.getSharedPreferences("bt_info", 0).edit();
        sharedata.putBoolean("bt_enabled", enabled);
        sharedata.commit();
    }

    public int getBatteryLevel() {
        Log.d("lh3", "getBatteryLevel");
        checkHfp();
        HeadsetClientProfile hfProfile = getProfile(16);
        if (hfProfile == null) {
            Log.e("lh3", "get hf profile fail!");
            return 0;
        }
        List<BluetoothDevice> deviceList = hfProfile.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e("lh3", "device is not connected!");
            return 0;
        }
        Bundle bundle = hfProfile.getCurrentAgEvents(deviceList.get(0));
        if (bundle != null) {
            return bundle.getInt("android.bluetooth.headsetclient.extra.BATTERY_LEVEL");
        }
        return 0;
    }

    public void setBtCallback(TsBtCallback callback) {
        this.btCallback = callback;
    }

    public List<ITsSpeechBtPbInfo> getTsSpeechBtPbInfo() {
        List<ITsSpeechBtPbInfo> pbInfos = new ArrayList<>();
        if (mListPb != null && isConnected()) {
            Log.d(TAG, "*****GetPbMap***** size = " + mListPb.size());
            for (int i = 0; i < mListPb.size(); i++) {
                ITsSpeechBtPbInfo pbInfo = new ITsSpeechBtPbInfo();
                PbItem item = mListPb.get(i);
                if (item.name != null && !item.name.isEmpty() && item.num != null && !item.num.isEmpty()) {
                    pbInfo.setName(item.name);
                    pbInfo.setNum(item.num);
                    pbInfos.add(pbInfo);
                }
            }
        }
        return pbInfos;
    }

    /* access modifiers changed from: package-private */
    public boolean checkBluetoothAddress(String remoteAddr) {
        return BluetoothAdapter.checkBluetoothAddress(remoteAddr);
    }

    public void startPairing(String deviceAddr) {
        BluetoothDevice device = mLocalAdapter.getRemoteDevice(deviceAddr);
        CachedBluetoothDevice cachedDevice = mDeviceManager.findDevice(device);
        if (cachedDevice != null) {
            Log.d(TAG, "cnnect device:" + device);
            mLastOBDAddr = deviceAddr;
            saveOBDAddr();
            cachedDevice.startPairing();
        }
    }
}
