package com.ts.bt;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.media.AudioManager;
import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.provider.ContactsContract;
import android.support.v4.widget.ExploreByTouchHelper;
import android.telecom.Call;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.android.SdkConstants;
import com.android.settingslib.bluetooth.A2dpSinkProfile;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.BluetoothEventManager;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.CachedBluetoothDeviceManager;
import com.android.settingslib.bluetooth.HeadsetProfile;
import com.android.settingslib.bluetooth.LocalBluetoothAdapter;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothProfile;
import com.android.settingslib.bluetooth.LocalBluetoothProfileManager;
import com.autochips.camera.util.DVRConst;
import com.incall.proxy.binder.service.AppBinder;
import com.lgb.pymatch.PyMatch;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.bt.ContactInfo;
import com.ts.bt.lib.NameInfo;
import com.ts.bt.lib.PhoneInfo;
import com.ts.can.bmw.mini.CanBMWMiniServiceDetailActivity;
import com.ts.can.btobd.CanBtOBDActivity;
import com.ts.main.common.MainUI;
import com.ts.main.txz.AmapAuto;
import com.ts.main.txz.TxzReg;
import com.ts.t3.T3WeakJoinUtils;
import com.ts.tsspeechlib.bt.ITsSpeechBtPbInfo;
import com.ts.tsspeechlib.bt.PhonebookData;
import com.ts.tsspeechlib.bt.TsBtCallback;
import com.txznet.sdk.TXZCallManager;
import com.txznet.sdk.TXZResourceManager;
import com.txznet.sdk.bean.Poi;
import com.txznet.sdk.media.InvokeConstants;
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
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

@SuppressLint({"NewApi"})
public class BtExe {
    public static final String ACTION_BLUETOOTH_CALL_LOG = "com.autochips.bluetooth.BtUtils.action.ACTION_BLUETOOTH_CALL_LOG";
    public static final String ACTION_CALL_CHANGED = "android.bluetooth.headsetclient.profile.action.CALL_CHANGED";
    public static final int AUTO_ANSWER_CHK_TIME = 5000;
    private static final String BROADCAST_REQUEST_HFPSTATUS = "com.globalconstant.BROADCAST_REQUEST_HFPSTATUS";
    private static final String BT_BROWSED_PACKAGE = "com.android.bluetooth";
    private static final String BT_BROWSED_SERVICE = "com.android.bluetooth.avrcpcontroller.BluetoothMediaBrowserService";
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
    public static final int CMD_NEXT = 4;
    public static final int CMD_PAUSE = 2;
    public static final int CMD_PLAY = 1;
    public static final int CMD_PRV = 3;
    public static final int CMD_REPEAT = 5;
    public static final int CMD_SHUFFLE = 6;
    private static final String CONTACT_SELECT_BY_ID = "contacts_id=?";
    private static final String CONTACT_SELECT_BY_NUMBER = "data1=?";
    private static final String CUSTOM_EVENT_REMOTE_RC_FEATURES = "autochips.bluetooth.avrcp.CUSTOM_EVENT_REMOTE_RC_FEATURES";
    private static final String CUSTOM_MSG_REPEAT_MODE_CHANGED = "android.bluetooth.avrcp-controller.profile.MSG.REPEAT_MODE_CHANGED";
    private static final String CUSTOM_MSG_SUFFLE_MODE_CHNAGED = "android.bluetooth.avrcp-controller.profile.MSG.SHUFFLE_MODE_CHANGED";
    /* access modifiers changed from: private */
    public static boolean D = true;
    private static final int DEFAULT_DISCOVERABLE_TIMEOUT = -1;
    public static final String EXTRA_CALL_NAME = "com.autochips.bluetooth.BtUtils.extra.EXTRA_CALL_NAME";
    public static final String EXTRA_CALL_NUMBER = "com.autochips.bluetooth.BtUtils.extra.EXTRA_CALL_NUMBER";
    public static final String EXTRA_CALL_TIME = "com.autochips.bluetooth.BtUtils.extra.EXTRA_CALL_TIME";
    public static final String EXTRA_CALL_TYPE = "com.autochips.bluetooth.BtUtils.extra.EXTRA_CALL_TYPE";
    public static final int FILTER_INCOMING_TYPE = 2;
    public static final int FILTER_MISSED_TYPE = 4;
    public static final int FILTER_OUTGOING_TYPE = 1;
    private static final String HFP_CLIENT_CONNECTION_SERVICE_CLASS_NAME = "com.android.bluetooth.hfpclient.connserv.HfpClientConnectionService";
    public static final String INCOMING_TYPE = "incoming";
    public static final String ITEM_HISTORY_CALLTIME = "item_history_calltime";
    public static final String ITEM_HISTORY_NAME = "item_history_name";
    public static final String ITEM_HISTORY_NUMBER = "item_history_number";
    public static final String ITEM_HISTORY_TIME = "item_history_time";
    public static final String ITEM_HISTORY_TYPE = "item_history_type";
    public static final int LOG_MAX = 500;
    private static final int MAX_TRY_CONNECT_CNT = 3;
    public static final String MISSED_TYPE = "missed";
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
    public static final String OUTGOING_TYPE = "outgoing";
    private static final String REMOTE_RC_FEATURES_EXTR_VALUE = "autochips.bluetooth.avrcp.extra.CUSTOM_RC_FEATURES";
    private static final String TAG = "BtExe";
    private static final String TS_AUTHOR_INFO = "TS_AUTHOR_INFO";
    private static final String TS_GET_AUTHOR_INFO = "TS_GET_AUTHOR_INFO";
    public static final String UNKOWN_PHONE_NUMBER = "unkown";
    private static String VER_ID = TXZResourceManager.STYLE_DEFAULT;
    private static final String VER_STR = "BT.20.09.18.1900";
    private static long currentMusicTime = 0;
    public static BtDatabaseHelper dbHelper;
    public static boolean isAddCall = false;
    public static boolean isAutoAnswer = false;
    public static boolean isAutoConncted = true;
    public static boolean isAutoDownloadPb = false;
    public static boolean isCallback = false;
    public static boolean isDownLoading = false;
    public static boolean isDownloadPb = false;
    public static boolean isFirstCallLog = false;
    public static boolean isHideDialog = false;
    public static boolean isObd = false;
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
    public static int mAvrcpctstate = 0;
    /* access modifiers changed from: private */
    public static int mBatteryLevel = 0;
    /* access modifiers changed from: private */
    public static BluetoothHeadsetClient mBluetoothHeadsetClient;
    public static Bitmap mBmpAlbum;
    public static SQLiteDatabase mBtDb;
    public static boolean mBtIsEnabled = false;
    public static long mBtMusicLen = 0;
    public static long mBtMusicPos = 0;
    public static List<Call> mCallLists = new ArrayList();
    public static int mCallPath = 0;
    public static int mCallSta = 0;
    public static Time mCallTime;
    static Context mContext;
    public static long mCount = 0;
    public static BluetoothDevice mDevice;
    public static boolean mDeviceAdd = false;
    public static List<BtDevice> mDeviceLists = new ArrayList();
    public static CachedBluetoothDeviceManager mDeviceManager;
    public static BluetoothEventManager mEventManager;
    private static ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    private static List<Handler> mHandlerLists = new ArrayList();
    public static int mHeadSetState = 0;
    /* access modifiers changed from: private */
    public static HeadsetProfile mHeadsetClientProfile;
    public static String mHfConnectedDeviceAddr = TXZResourceManager.STYLE_DEFAULT;
    public static boolean mHideUnBonedListDialog = false;
    public static ArrayList<HashMap<String, Object>> mHistoryList = new ArrayList<>();
    public static int mIndex = 0;
    public static BtExe mInstance = new BtExe();
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
    public static String mLastLogTime = TXZResourceManager.STYLE_DEFAULT;
    public static String mLastOBDAddr = TXZResourceManager.STYLE_DEFAULT;
    public static String mLastPhoneNo = TXZResourceManager.STYLE_DEFAULT;
    public static ArrayList<PbItem> mListPb = new ArrayList<>();
    public static ArrayList<SearchItem> mListSearch = new ArrayList<>();
    public static LocalBluetoothAdapter mLocalAdapter;
    public static LocalBluetoothManager mLocalBtManager;
    public static LocalBluetoothProfileManager mLocalProfileManager;
    public static long mNum = 0;
    public static String mObdPin = TXZResourceManager.STYLE_DEFAULT;
    private static int mOldMcuSta = 3;
    public static String mOtherAddr = TXZResourceManager.STYLE_DEFAULT;
    public static long mPbStartTick;
    public static int mPbStatus = 0;
    public static String mPhoneName = null;
    public static String mPin = "0000";
    public static long mQueryNoCount = 0;
    public static long mQueryNoLastTick;
    public static boolean mRefreshBondedList = false;
    public static boolean mScaningDevice = false;
    public static long mSecondActiveTick = 0;
    private static String mStrCallName = TXZResourceManager.STYLE_DEFAULT;
    public static String mStrId3Album = TXZResourceManager.STYLE_DEFAULT;
    public static String mStrId3Artist = TXZResourceManager.STYLE_DEFAULT;
    public static String mStrId3Name = TXZResourceManager.STYLE_DEFAULT;
    private static String mStrOldCallNo = TXZResourceManager.STYLE_DEFAULT;
    public static String mStrPhoneName = TXZResourceManager.STYLE_DEFAULT;
    public static String mStrPhoneNo = UNKOWN_PHONE_NUMBER;
    public static int mSyncNum = 0;
    private static Toast mToast;
    public static long mTwoCallActiveSecond;
    public static List<BonedDevice> mUnBonedLists = new ArrayList();
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
    public static int mlistFilter = 1;
    public static byte musicState = 0;
    private final String HFP_CLINET_CONNECTION_SERVICE_CLASS_NAME = HFP_CLIENT_CONNECTION_SERVICE_CLASS_NAME;
    boolean bForceEnable = true;
    TsBtCallback btCallback;
    private boolean isAutoPause = true;
    private boolean isBackCar = false;
    private BluetoothAdapter mAdapter;
    private long mAutoAnswerStart = 0;
    private int mBatteryCount = 100;
    BluetoothProfile.ServiceListener mBluetoothProfileServiceListener = new BluetoothProfile.ServiceListener() {
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            Log.d(BtExe.TAG, "onServiceConnected, profile:" + profile);
            BtExe.mBluetoothHeadsetClient = (BluetoothHeadsetClient) proxy;
            BtExe.getBtInstance().setCbTimer();
            BtExe.this.isBtConnected();
        }

        public void onServiceDisconnected(int profile) {
            Log.d(BtExe.TAG, "onServiceDisconnected, profile: " + profile);
            BtExe.mBluetoothHeadsetClient = null;
        }
    };
    BroadcastReceiver mBtReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("lh8", "action = " + action);
            if (action.equals("android.bluetooth.headsetclient.profile.action.RESULT")) {
                BtExe.this.onATCmdResult(context, intent);
            } else if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
                BtExe.this.onBluetoothPairingRequest(context, intent);
            } else if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                BtExe.this.onBluetoothAclDisconnected(context, intent);
            } else if (action.equals(BtExe.BROADCAST_REQUEST_HFPSTATUS)) {
                BtExe.getBtInstance().UpdateHfpSta();
            } else if (action.equals(BtExe.TS_GET_AUTHOR_INFO)) {
                BtExe.this.sendTsAuthorInfo(BtExe.mContext.getResources().getString(R.string.bt_author_info));
                BtExe.this.sendTsAuthorInfo(BtExe.mContext.getResources().getString(R.string.radio_author_info));
            } else if (!action.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED") && !action.equals(CanBtOBDActivity.ACTION_DISCOVERY_FINISHED)) {
                if (action.equals("android.bluetooth.device.action.FOUND")) {
                    BtExe.this.onDeviceAdded((BluetoothDevice) intent.getExtra("android.bluetooth.device.extra.DEVICE"));
                } else if (action.equals("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED")) {
                    Log.e("lh8", " action = BluetoothHeadsetClient.ACTION_CALL_CHANGED");
                    Object state = intent.getExtra("android.bluetooth.headsetclient.extra.CALL");
                    BluetoothHeadsetClientCall ss = (BluetoothHeadsetClientCall) state;
                    Log.e("lh8", " Object = " + String.valueOf(state));
                    Log.e("lh8", "ss.mId = " + String.valueOf(ss.getId()));
                    Log.e("lh8", "ss.mState = " + String.valueOf(ss.getState()));
                    Log.e("lh8", "ss.mNumber = " + String.valueOf(ss.getNumber()));
                    Log.e("lh8", " ss.mOutgoing = " + String.valueOf(ss.isOutgoing()));
                    Log.e("lh8", "ss.mMultiParty = " + String.valueOf(ss.isMultiParty()));
                } else if (action.equals("com.autochips.bluetooth.phonebookdownload.action.ACTION_PHONEBOOK_DOWNLOAD_STATE_IND")) {
                    Log.d("lh8", "isDownloadPb =" + BtExe.isDownloadPb);
                    if (BtExe.isDownloadPb) {
                        int state2 = intent.getIntExtra("com.autochips.bluetooth.phonebookdownload.extra.state", 0);
                        int path = intent.getIntExtra("com.autochips.bluetooth.phonebookdownload.extra.path", 0);
                        Log.d("lh8", "state = " + state2);
                        Log.d("lh8", "path = " + path);
                        if ((path & 6) == 0) {
                            Log.d(BtExe.TAG, "not care this intent");
                            return;
                        }
                        switch (state2) {
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
                                BtExe.isDownLoading = true;
                                BtExe.isDownloadPb = false;
                                new UpdateAsyncTask().execute(new Void[0]);
                                return;
                            default:
                                return;
                        }
                    }
                } else if (action.equals("com.autochips.bluetooth.phonebookdownload.action.ACTION_PHONEBOOK_DOWNLOAD_ONESTEP")) {
                    Log.d("lh8", "isDownloadPb =" + BtExe.isDownloadPb);
                    if (BtExe.isDownloadPb) {
                        int path2 = intent.getIntExtra("com.autochips.bluetooth.phonebookdownload.extra.path", 0);
                        int index = intent.getIntExtra("com.autochips.bluetooth.phonebookdownload.extra.index", 0);
                        Log.d("lh8", "path = " + path2);
                        Log.d("lh8", "index = " + index);
                        if ((path2 & 6) == 0) {
                            Log.d(BtExe.TAG, "not care this intent");
                            return;
                        }
                        BtExe.mSyncNum = index;
                        Log.i(BtExe.TAG, "PBSyncManagerService.ACTION_DOWNLOAD_ONESTEP) iCount =  " + index);
                        BtExe.mPbStatus = 1;
                        BtExe.mPbStartTick = BtExe.getTickCount();
                    }
                } else if (action.equals("android.bluetooth.headsetclient.profile.action.AG_EVENT")) {
                    BtExe.mBatteryLevel = BtExe.this.getBtBatteryLevel();
                }
            }
        }
    };
    public LinkedHashMap<String, PhoneCall> mCallMap = new LinkedHashMap<>();
    public HashMap<String, String> mCallTypes = new HashMap<>();
    private BtUITimer mCbTimer = null;
    private boolean mChkAutoAnswer = false;
    protected Comparator<Object> mCmp;
    private MediaBrowser.ConnectionCallback mConnectionCallback = new MediaBrowser.ConnectionCallback() {
        public void onConnected() {
            Log.d(BtExe.TAG, "onConnected: session token " + BtExe.this.mMediaBrowser.getSessionToken());
            if (BtExe.this.mMediaBrowser.getSessionToken() != null) {
                BtExe.this.mMediaController = new MediaController(BtExe.mContext, BtExe.this.mMediaBrowser.getSessionToken());
                BtExe.this.mMediaController.registerCallback(BtExe.this.mMediaControllerCallback);
                BtExe.this.mTryConnectMediaSessionCnt = 0;
            }
        }

        public void onConnectionFailed() {
            Log.d(BtExe.TAG, "onConnectionFailed");
            if (BtExe.this.mTryConnectMediaSessionCnt <= 3) {
                BtExe.this.connectMediaBrowser();
            }
        }

        public void onConnectionSuspended() {
            Log.d(BtExe.TAG, "onConnectionSuspended");
            BtExe.this.mTryConnectMediaSessionCnt = 0;
        }
    };
    private String mCurName = null;
    /* access modifiers changed from: private */
    public String mCurSubscribeId = null;
    private Evc mEvc = Evc.GetInstance();
    public IReceiver mIReceiver = null;
    private boolean mIsA2dpMode = true;
    private boolean mIsAutoAnswer = false;
    private List<IBtStatusChangeListener> mListener = new ArrayList();
    /* access modifiers changed from: private */
    public MediaBrowser mMediaBrowser = null;
    /* access modifiers changed from: private */
    public MediaController mMediaController = null;
    /* access modifiers changed from: private */
    public MediaController.Callback mMediaControllerCallback = new MediaController.Callback() {
        public void onSessionDestroyed() {
            Log.d(BtExe.TAG, "Session destroyed. Need to fetch a new Media Session");
        }

        public void onSessionEvent(String event, Bundle extras) {
            Log.d(BtExe.TAG, "onSessionEvent: " + event);
            if (!"android.bluetooth.avrcp-controller.profile.action.PLAYER_SETTING".equals(event) && BtExe.CUSTOM_EVENT_REMOTE_RC_FEATURES.equals(event) && extras != null) {
                extras.getInt(BtExe.REMOTE_RC_FEATURES_EXTR_VALUE);
            }
        }

        public void onPlaybackStateChanged(PlaybackState state) {
            if (state == null) {
                Log.d(BtExe.TAG, "state is null");
                return;
            }
            BtExe.musicState = (byte) state.getState();
            BtExe.mBtMusicPos = state.getPosition();
        }

        public void onMetadataChanged(MediaMetadata metadata) {
            if (metadata != null) {
                Log.d(BtExe.TAG, "Received updated metadata: " + metadata);
                String title = metadata.getString("android.media.metadata.TITLE");
                String artistName = metadata.getString("android.media.metadata.ARTIST");
                String albumName = metadata.getString("android.media.metadata.ALBUM");
                Log.d(BtExe.TAG, "title = " + title);
                Log.d(BtExe.TAG, "artistName = " + artistName);
                Log.d(BtExe.TAG, "albumName = " + albumName);
                Bitmap albumArt = metadata.getBitmap("android.media.metadata.ALBUM_ART");
                Log.d(BtExe.TAG, "albumArt = " + albumArt);
                BtExe.mBtMusicLen = metadata.getLong("android.media.metadata.DURATION");
                BtExe.mIsId3Update = true;
                BtExe.mStrId3Name = title;
                BtExe.mStrId3Artist = artistName;
                BtExe.mStrId3Album = albumName;
                BtExe.mBmpAlbum = albumArt;
            }
        }
    };
    private boolean mMute = false;
    private LocalBluetoothProfileManager.ServiceListener mServiceListener = new LocalBluetoothProfileManager.ServiceListener() {
        public void onServiceConnected() {
            if (BtExe.D) {
                Log.d(BtExe.TAG, "onServiceConnected ");
            }
            synchronized (BtExe.mContext) {
                BtExe.mHeadsetClientProfile = BtExe.mLocalProfileManager.getHeadsetProfile();
                BtExe.mA2dpSinkProfile = BtExe.mLocalProfileManager.getA2dpSinkProfile();
                BtExe.getBtInstance().regMetadataCallback();
                BtExe.getBtInstance().setCbTimer();
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
            }
            BtExe.getBtInstance().unregMetadataCallback();
            BtExe.dispatchMessage(1, (Object) null, 0, 0);
        }
    };
    public int mState = 7;
    public String mStrSta = TXZResourceManager.STYLE_DEFAULT;
    private MediaBrowser.SubscriptionCallback mSubscriptionCallback = new MediaBrowser.SubscriptionCallback() {
        public void onChildrenLoaded(String parentId, List<MediaBrowser.MediaItem> children) {
            Log.d(BtExe.TAG, "parentId: " + parentId + " list: " + children.size());
            BtExe.this.mCurSubscribeId = parentId;
        }

        public void onError(String id) {
            Log.d(BtExe.TAG, "load fail");
        }
    };
    TelecomManager mTelecomManager;
    /* access modifiers changed from: private */
    public int mTryConnectMediaSessionCnt = 0;
    BluetoothCallback mbtCallback = new BluetoothCallback() {
        public void onBluetoothStateChanged(int bluetoothState) {
            BtExe.dispatchMessage(10, (Object) null, bluetoothState, 0);
            BtExe.this.handleBtStateChanged(bluetoothState);
        }

        public void onScanningStateChanged(boolean started) {
            BtExe.dispatchMessage(11, (Object) null, started ? 0 : 1, 0);
            Log.d("lh8", "onScanningStateChanged: state = " + started);
            if (started) {
                BtExe.mScaningDevice = true;
            } else {
                BtExe.mScaningDevice = false;
            }
        }

        public void onDeviceAdded(CachedBluetoothDevice cachedDevice) {
            BtExe.dispatchMessage(12, cachedDevice, 0, 0);
        }

        public void onDeviceDeleted(CachedBluetoothDevice cachedDevice) {
            BtExe.dispatchMessage(13, cachedDevice, 0, 0);
            if (cachedDevice == null) {
                Log.d(BtExe.TAG, "onDeviceDeleted cachedDevice is null");
                return;
            }
            BluetoothDevice device = cachedDevice.getDevice();
            if (device == null) {
                Log.d(BtExe.TAG, "onDeviceDeleted device is null");
                return;
            }
            String name = cachedDevice.getName();
            String addr = device.getAddress();
            Log.d("lh8", "onDeviceDeleted: name = " + name);
            Log.d("lh8", "onDeviceDeleted: addr = " + addr);
            BonedDevice bonedDevice = new BonedDevice();
            bonedDevice.addr = addr;
            bonedDevice.name = name;
            int index = BtExe.deviceContains(bonedDevice);
            if (index != -1) {
                BtExe.mUnBonedLists.remove(index);
            }
        }

        public void onDeviceBondStateChanged(CachedBluetoothDevice cachedDevice, int bondState) {
            BtExe.dispatchMessage(14, cachedDevice, bondState, 0);
            Log.d("lh8", "onDeviceBondStateChanged: state = " + bondState);
            if (cachedDevice == null) {
                Log.d(BtExe.TAG, "onDeviceBondStateChanged cachedDevice is null");
                return;
            }
            BluetoothDevice device = cachedDevice.getDevice();
            if (device == null) {
                Log.d(BtExe.TAG, "onDeviceBondStateChanged device is null");
                return;
            }
            String name = cachedDevice.getName();
            String addr = device.getAddress();
            BonedDevice bonedDevice = new BonedDevice();
            bonedDevice.addr = addr;
            bonedDevice.name = name;
            int index = BtExe.deviceContains(bonedDevice);
            if (index != -1) {
                BtExe.mUnBonedLists.get(index).state = bondState;
                if (bondState == 12) {
                    BtExe.mUnBonedLists.remove(index);
                    BtExe.mHideUnBonedListDialog = true;
                }
                BtExe.mDeviceAdd = true;
            }
            if (bondState == 12 && name != null && name.startsWith("OBD") && !BtExe.isExistAddr(addr)) {
                BtExe.mRefreshBondedList = true;
                BtExe.insertBonedDevice(addr, device.getName());
            }
            if (bondState == 10 || bondState == 12) {
                BtExe.getBtInstance().readDeviceNamePin();
                BtExe.isObd = false;
            }
            BtExe.getBtInstance().notifyStatusChange(1, device, bondState);
        }

        public void onConnectionStateChanged(CachedBluetoothDevice cachedDevice, int state) {
            BtExe.dispatchMessage(15, cachedDevice, state, 0);
            if (cachedDevice != null) {
                if (state == 0) {
                    BtExe.mbHfConnected = false;
                    BtExe.mDevice = null;
                    Log.d(BtExe.TAG, "bt disconnected");
                    BtExe.handleBtConnectStateChanged(cachedDevice.getDevice(), 0);
                } else if (state == 2) {
                    BtExe.mbHfConnected = true;
                    BtExe.mDevice = cachedDevice.getDevice();
                    Log.d(BtExe.TAG, "bt connected");
                    BtExe.handleBtConnectStateChanged(cachedDevice.getDevice(), 2);
                }
                if (BtExe.getBtInstance().btCallback != null) {
                    BtExe.getBtInstance().btCallback.onBtConnectStateChange(state);
                }
            }
        }

        public void onActiveDeviceChanged(CachedBluetoothDevice cachedDevice, int changed) {
        }

        public void onAudioModeChanged() {
        }

        public void onProfileConnectionStateChanged(CachedBluetoothDevice cachedDevice, int profile, int state) {
            BtDevice btDevice;
            BtExe.dispatchMessage(17, cachedDevice, profile, state);
            Log.d(BtExe.TAG, "profile = " + state + ",state = " + profile);
            if (cachedDevice == null) {
                Log.d(BtExe.TAG, "CachedBluetoothDevice is null");
                return;
            }
            BluetoothDevice device = cachedDevice.getDevice();
            if (device == null) {
                Log.d(BtExe.TAG, "BluetoothDevice is null");
                return;
            }
            String addr = device.getAddress();
            if (TextUtils.isEmpty(addr)) {
                Log.d(BtExe.TAG, "addr is null");
                return;
            }
            Log.d(BtExe.TAG, "addr = " + addr);
            int size = BtExe.mDeviceLists.size();
            int flag = -1;
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                } else if (addr.equals(BtExe.mDeviceLists.get(i).device.getAddress())) {
                    flag = i;
                    break;
                } else {
                    i++;
                }
            }
            if (flag != -1) {
                btDevice = BtExe.mDeviceLists.get(flag);
            } else {
                btDevice = new BtDevice();
            }
            btDevice.device = device;
            if (state == 16) {
                btDevice.headset = profile;
            } else if (state == 11) {
                btDevice.a2dp = profile;
            } else if (state == 17 && !BtExe.isDownloadPb) {
                BtExe.this.stopDownload(device);
                return;
            } else {
                return;
            }
            if (btDevice.headset == 2 || btDevice.a2dp == 2) {
                if (flag != -1) {
                    BtExe.mDeviceLists.set(flag, btDevice);
                } else {
                    BtExe.mDeviceLists.add(btDevice);
                }
            }
            if (btDevice.headset == 2 && btDevice.a2dp == 2) {
                BtExe.mbHfConnected = true;
                BtExe.mDevice = device;
                Log.d(BtExe.TAG, "bt connected");
                BtExe.handleBtConnectStateChanged(cachedDevice.getDevice(), 2);
            }
            if (btDevice.headset == 0 && btDevice.a2dp == 0) {
                if (flag != -1) {
                    BtExe.mDeviceLists.remove(flag);
                }
                Log.d(BtExe.TAG, "disconnected");
                if (BtExe.mDevice != null && addr.equals(BtExe.mDevice.getAddress())) {
                    BtExe.mbHfConnected = false;
                    BtExe.mDevice = null;
                    Log.d(BtExe.TAG, "disconnected1");
                    BtExe.handleBtConnectStateChanged(BtExe.mDevice, 0);
                    BtExe.mbNeedUpdatePhoneName = true;
                }
            }
            int listSize = BtExe.mDeviceLists.size();
            Log.d(BtExe.TAG, "device size = " + BtExe.mDeviceLists.size());
            if (listSize > 0 && BtExe.mDevice == null) {
                BtExe.mbHfConnected = true;
                BtExe.mDevice = BtExe.mDeviceLists.get(0).device;
                Log.d(BtExe.TAG, "bt connected:");
                BtExe.handleBtConnectStateChanged(BtExe.mDevice, 2);
            }
        }
    };

    public static class BonedDevice {
        public String addr;
        public String name;
        public int state;
        public int type;
    }

    public interface IBtStatusChangeListener {
        void onBtBonedStatusChange(BluetoothDevice bluetoothDevice, int i);

        void onBtConnectStatusChange(BluetoothDevice bluetoothDevice, int i);
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

    public class BtDevice {
        public int a2dp;
        public BluetoothDevice device;
        public int headset;

        public BtDevice() {
        }
    }

    public static BtExe getBtInstance() {
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

    public void registerStatusChangeListener(IBtStatusChangeListener listener) {
        if (!this.mListener.contains(listener)) {
            this.mListener.add(listener);
        }
    }

    public void unregisterStatusChangeListener(IBtStatusChangeListener listener) {
        if (!this.mListener.contains(listener)) {
            this.mListener.remove(listener);
        }
    }

    public void notifyStatusChange(int type, BluetoothDevice device, int state) {
        if (type == 0) {
            for (IBtStatusChangeListener listener : this.mListener) {
                listener.onBtConnectStatusChange(device, state);
            }
            return;
        }
        for (IBtStatusChangeListener listener2 : this.mListener) {
            listener2.onBtBonedStatusChange(device, state);
        }
    }

    public void close() {
        if (mEventManager != null) {
            mEventManager.unregisterCallback(this.mbtCallback);
        }
        deinitLocalBluetooth();
        mContext = null;
        mInstance = null;
    }

    public void Init() {
    }

    public void InitBt() {
        long startTime = getTickCount();
        getBtInstance();
        mInstance.initData();
        mbFsInit = true;
        mbModuleInit = true;
        mBtIsEnabled = false;
        mCount = 0;
        mNum = 0;
        if (mContext.getResources().getString(R.string.support_bt_phonemeeting).equals("1")) {
            isCallback = true;
        } else {
            isCallback = false;
        }
        Log.d("lh8", "startTime5 = " + getTickCount());
        initLocalBluetooth(mContext);
        if (mEventManager != null) {
            mEventManager.registerCallback(this.mbtCallback);
        }
        Log.d("lh8", "startTime6 = " + getTickCount());
        getLocalAdapter();
        getBtInstance().checkIfAbnormal();
        if (mLocalAdapter.isEnabled()) {
            setBluetoothDiscoverability(true);
            Log.d(TAG, "isEnabled+++++++++");
        }
        dbHelper = new BtDatabaseHelper(mContext, "BtPhone", (SQLiteDatabase.CursorFactory) null, 1);
        readDeviceNamePin();
        BtPinDialog.initWindow(mContext);
        registerBtReceiver();
        Log.d("lh8", "endTime = " + (getTickCount() - startTime));
        this.mTelecomManager = (TelecomManager) mContext.getSystemService("telecom");
        mAudioManager = (AudioManager) mContext.getSystemService(Poi.PoiAction.ACTION_AUDIO);
    }

    /* access modifiers changed from: package-private */
    public void setDefaultDialer() {
        if (Build.VERSION.SDK_INT >= 23) {
            Intent intent = new Intent("android.telecom.action.CHANGE_DEFAULT_DIALER");
            intent.putExtra("android.telecom.extra.CHANGE_DEFAULT_DIALER_PACKAGE_NAME", mContext.getPackageName());
            mContext.startActivity(intent);
        }
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
            case 16:
                if (mHeadsetClientProfile != null && mHeadsetClientProfile.isProfileReady()) {
                    return mHeadsetClientProfile;
                }
        }
        return null;
    }

    public synchronized void initLocalBluetooth(Context context) {
        synchronized (mContext) {
            Log.d(TAG, "initLocalBluetooth");
            if (mLocalBtManager == null) {
                mLocalBtManager = LocalBluetoothManager.getInstance(context, new LocalBluetoothManager.BluetoothManagerCallback() {
                    public void onBluetoothManagerInitialized(Context context, LocalBluetoothManager localBluetoothManager) {
                        BtExe.mLocalBtManager = localBluetoothManager;
                    }
                });
                if (mLocalBtManager != null) {
                    mEventManager = mLocalBtManager.getEventManager();
                    mDeviceManager = mLocalBtManager.getCachedDeviceManager();
                    mLocalProfileManager = mLocalBtManager.getProfileManager();
                }
                if (mLocalProfileManager != null) {
                    mLocalProfileManager.addServiceListener(this.mServiceListener);
                }
                connectMediaBrowser();
                this.mAdapter = BluetoothAdapter.getDefaultAdapter();
                this.mAdapter.getProfileProxy(mContext, this.mBluetoothProfileServiceListener, 16);
            }
        }
    }

    public synchronized void deinitLocalBluetooth() {
        synchronized (mContext) {
            Log.d(TAG, "deinitLocalBluetooth");
            if (mLocalProfileManager != null) {
                mLocalProfileManager.removeServiceListener(this.mServiceListener);
            }
            disconnectMediaBrowser();
            mLocalBtManager = null;
            mEventManager = null;
            mDeviceManager = null;
            mLocalProfileManager = null;
            mA2dpSinkProfile = null;
            mHeadsetClientProfile = null;
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
        if (isVisible) {
            if (mLocalAdapter != null) {
                mLocalAdapter.setScanMode(23, -1);
            }
        } else if (mLocalAdapter != null) {
            mLocalAdapter.setScanMode(21);
        }
    }

    public void handleBtStateChanged(int btState) {
        switch (btState) {
            case 10:
                if (isBluetoothReady()) {
                    deinitLocalBluetooth();
                }
                mbModuleInit = false;
                mbHfConnected = false;
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
                return;
            default:
                return;
        }
    }

    public static void handleBtConnectStateChanged(BluetoothDevice device, int state) {
        switch (state) {
            case 0:
                Log.d(TAG, "BluetoothProfile.STATE_DISCONNECTED");
                mbNeedSaveData = false;
                mbNeedUpdatePhoneName = false;
                mbHfConnected = false;
                mCallSta = 0;
                isHideDialog = false;
                isAddCall = false;
                mStrPhoneName = TXZResourceManager.STYLE_DEFAULT;
                mBatteryLevel = 0;
                isDownloadPb = false;
                getBtInstance().pbClear();
                getBtInstance().sendConnectStateChange();
                getBtInstance().unregMetadataCallback();
                mDevice = null;
                if (mIsConnectOther) {
                    mIsConnectOther = false;
                    getBtInstance().connect(mOtherAddr);
                    break;
                }
                break;
            case 2:
                Log.d(TAG, "BluetoothProfile.STATE_CONNECTED");
                long startTime = System.currentTimeMillis();
                isAutoConncted = true;
                BtPinDialog.hideView();
                mbNeedUpdatePhoneName = true;
                mbHfConnected = true;
                mbNeedSaveData = true;
                getBtInstance().updateCallSta();
                mDevice = device;
                String addr = device.getAddress();
                if (isExistAddr(addr)) {
                    deleteBondedDevice(addr);
                }
                insertBonedDevice(addr, device.getName());
                mStrPhoneName = device.getName();
                mBatteryLevel = getBtInstance().getBtBatteryLevel();
                isDownloadPb = false;
                getBtInstance().saveLastAddr();
                getBtInstance().UpdateHfpSta();
                mExecutorService.submit(new Runnable() {
                    public void run() {
                        BtExe.getBtInstance().updatePbList();
                        BtExe.getBtInstance().UpdatePbMap();
                        BtExe.getBtInstance().sendConnectStateChange();
                        BtExe.mPbStatus = 2;
                    }
                });
                getBtInstance().regMetadataCallback();
                if (mIsAutoDisconnect) {
                    getBtInstance().disconnect();
                }
                Log.d("lh8", "connect totalTime = " + (System.currentTimeMillis() - startTime));
                break;
        }
        getBtInstance().notifyStatusChange(0, device, state);
    }

    public static void handleDeviceSelected(BluetoothDevice device, boolean isSelected) {
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

    protected static void connectHfp(BluetoothDevice device) {
        Log.d(TAG, "connectHfp");
        HeadsetProfile hfProfile = (HeadsetProfile) getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
        } else {
            hfProfile.connect(device);
        }
    }

    protected static void connectA2dp(BluetoothDevice device) {
        Log.d(TAG, "connectA2dp");
        A2dpSinkProfile a2dpProfile = (A2dpSinkProfile) getProfile(11);
        if (a2dpProfile == null) {
            Log.e(TAG, "get hf profile fail!");
        } else if (!a2dpProfile.isConnectable()) {
            Log.d(TAG, "isConnectable");
            a2dpProfile.connect(device);
        }
    }

    protected static void disconnectHfp(BluetoothDevice device) {
        Log.d(TAG, "disconnectHfp");
        HeadsetProfile hfProfile = (HeadsetProfile) getProfile(16);
        if (hfProfile == null) {
            Log.e(TAG, "get hf profile fail!");
        } else {
            hfProfile.disconnect(device);
        }
    }

    protected static void disconnectA2dp(BluetoothDevice device) {
        Log.d(TAG, "disconnectA2dp");
        A2dpSinkProfile a2dpProfile = (A2dpSinkProfile) getProfile(11);
        if (a2dpProfile == null) {
            Log.e(TAG, "get hf profile fail!");
        } else {
            a2dpProfile.disconnect(device);
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
            mToast = Toast.makeText(mContext, TXZResourceManager.STYLE_DEFAULT, 0);
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

    public void isBtConnected() {
        Log.d("lh8", "isBtConnected");
        if (mBluetoothHeadsetClient != null) {
            List<BluetoothDevice> deviceList = mBluetoothHeadsetClient.getConnectedDevices();
            Log.d("lh8", "deviceList:" + deviceList);
            if (deviceList == null || deviceList.size() <= 0) {
                mbHfConnected = false;
                return;
            }
            Log.d("lh8", "size = " + deviceList.size());
            BluetoothDevice bluetoothDevice = null;
            int size = deviceList.size();
            int i = 0;
            while (true) {
                if (i < size) {
                    BluetoothDevice device = deviceList.get(i);
                    if (device.isConnected() && !mbHfConnected) {
                        handleBtConnectStateChanged(device, 2);
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
            if (0 == 0) {
                bluetoothDevice = deviceList.get(0);
            }
            mbHfConnected = bluetoothDevice.isConnected();
        }
    }

    public void getCurrentAgEvents() {
        List<BluetoothDevice> deviceList;
        Bundle bundle;
        Set<String> set;
        if (mBluetoothHeadsetClient != null && (deviceList = mBluetoothHeadsetClient.getConnectedDevices()) != null && deviceList.size() != 0 && (bundle = mBluetoothHeadsetClient.getCurrentAgEvents(deviceList.get(0))) != null && (set = bundle.keySet()) != null) {
            for (String key : set) {
                bundle.get(key);
            }
        }
    }

    public void dial(String callNumber) {
        Log.d(TAG, "dial:" + callNumber);
        if (!isDialFastDoubleClick() && this.mTelecomManager != null) {
            this.mTelecomManager.placeCall(Uri.fromParts("tel", callNumber, (String) null), new Bundle());
        }
    }

    public void reDial() {
        Log.d(TAG, "reDial");
        if (mBluetoothHeadsetClient == null) {
            Log.e(TAG, "get hf profile fail!");
            return;
        }
        List<BluetoothDevice> deviceList = mBluetoothHeadsetClient.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "device is not connected!");
        }
    }

    public String getRemoteAddr() {
        if (mDevice != null) {
            return mDevice.getAddress();
        }
        Log.d(TAG, "getAddr mDeviceLists == null");
        return null;
    }

    public static String getAddr() {
        if (mDevice != null) {
            return mDevice.getAddress();
        }
        Log.d(TAG, "getAddr mDeviceLists == null");
        return null;
    }

    public void checkHfp() {
    }

    public void checkBtAvState(boolean isFirst) {
        Log.d("BtMusicActivity", "checkBtAvState");
    }

    public void sendAvrcpCommand(int cmd) {
        Log.d("BtMusicActivity", "sendAvrcpCommand");
        if (!isBrowserConnected() || this.mMediaController == null || this.mMediaController.getTransportControls() == null) {
            Log.d(TAG, "mediaSession is not ready");
            return;
        }
        MediaController.TransportControls mediaControllerCntrl = this.mMediaController.getTransportControls();
        switch (cmd) {
            case 1:
                mediaControllerCntrl.play();
                return;
            case 2:
                mediaControllerCntrl.pause();
                return;
            case 3:
                mediaControllerCntrl.skipToPrevious();
                return;
            case 4:
                mediaControllerCntrl.skipToNext();
                return;
            case 5:
                sendCustomAction(CUSTOM_MSG_REPEAT_MODE_CHANGED, (Bundle) null);
                return;
            case 6:
                sendCustomAction(CUSTOM_MSG_SUFFLE_MODE_CHNAGED, (Bundle) null);
                return;
            default:
                return;
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
        if (this.mMediaController != null) {
            PlaybackState playbackState = this.mMediaController.getPlaybackState();
            if (playbackState != null) {
                state = (byte) playbackState.getState();
            }
            Log.d("__lh__", "musicstate = " + state);
        }
        return state;
    }

    public int getPlayState() {
        PlaybackState playbackState;
        if (this.mMediaController == null || (playbackState = this.mMediaController.getPlaybackState()) == null) {
            return 0;
        }
        return playbackState.getState();
    }

    public void musicPP() {
        if (getPlayState() != 3) {
            musicPlay();
        } else {
            musicPause();
        }
    }

    public void musicPrev() {
        Log.d(TAG, "musicPrev");
        Log.d(SdkConstants.ATTR_TAG, "musicPrev");
        if (!isFastDoubleClick()) {
            sendAvrcpCommand(3);
        }
    }

    public void musicNext() {
        Log.d(TAG, "musicNext");
        Log.d(SdkConstants.ATTR_TAG, "musicNext");
        if (!isFastDoubleClick()) {
            sendAvrcpCommand(4);
        }
    }

    public void musicPause() {
        Log.d(TAG, "musicPause");
        Log.d(SdkConstants.ATTR_TAG, "musicPause");
        sendAvrcpCommand(2);
    }

    public void musicPlay() {
        Log.d(TAG, "musicPlay");
        Log.d(SdkConstants.ATTR_TAG, "musicPlay");
        sendAvrcpCommand(1);
    }

    public boolean isBtMusicPlaying() {
        boolean isBtMusicPlaying = false;
        if (getMusicState() == 3) {
            isBtMusicPlaying = true;
        }
        Log.d(TAG, "isBtMusicPlaying:" + isBtMusicPlaying);
        return isBtMusicPlaying;
    }

    public void hangup() {
        Log.i(TAG, "on click hangup!");
        if (mCallLists == null || mCallLists.size() == 0) {
            Log.e(TAG, "no call!");
            return;
        }
        Call c = getCall(mCallLists, 2);
        if (c != null) {
            Log.d(TAG, "rejectCall: " + c.getState());
            c.disconnect();
            return;
        }
        Call c2 = getCall(mCallLists, 1, 4);
        if (c2 != null) {
            Log.d(TAG, "terminateCall: " + c2.getState());
            c2.disconnect();
            return;
        }
        for (int i = 0; i < mCallLists.size(); i++) {
            Log.d(TAG, "rejectCall(" + i + "), state: " + mCallLists.get(i).getState());
        }
        mCallLists.get(0).disconnect();
    }

    public void answer() {
        Log.i(TAG, "on click answer!");
        if (mCallLists == null || mCallLists.size() == 0) {
            Log.e(TAG, "mCallList is null");
            return;
        }
        SystemProperties.set("forfan.tsbt.mute", "1");
        Call c = getCall(mCallLists, 2);
        if (c != null) {
            c.answer(c.getDetails().getVideoState());
        }
    }

    public void hold() {
        Log.d(TAG, "hold");
        if (mCallLists == null || mCallLists.size() == 0) {
            Log.e(TAG, "mCallList is null");
            return;
        }
        Call call = getCall(mCallLists, 3);
        if (call != null) {
            call.unhold();
        }
    }

    public void mergeCalls() {
        if (mCallLists == null || mCallLists.size() == 0) {
            Log.e(TAG, "mCallList is null");
            return;
        }
        int size = mCallLists.size();
        Log.d(TAG, "mergeConference:size = " + size);
        if (size > 1) {
            Call activeCall = getCall(mCallLists, 4);
            Call holdCall = getCall(mCallLists, 3);
            if (activeCall != null && holdCall != null) {
                Log.d(TAG, SdkConstants.VIEW_MERGE);
                activeCall.conference(holdCall);
            }
        }
    }

    public void audioSwitch() {
        Log.i(TAG, "on click audioSwitch!");
        if (mBluetoothHeadsetClient == null) {
            Log.e(TAG, "get hf profile fail!");
            return;
        }
        List<BluetoothDevice> deviceList = mBluetoothHeadsetClient.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "77 hf client is not connected!");
            return;
        }
        int scoState = mBluetoothHeadsetClient.getAudioState(deviceList.get(0));
        if (scoState == 0) {
            mBluetoothHeadsetClient.connectAudio(deviceList.get(0));
        } else if (scoState == 2) {
            mBluetoothHeadsetClient.disconnectAudio(deviceList.get(0));
        } else {
            Log.d(TAG, "hf sco audio state:!" + scoState + ", can not switch!");
        }
    }

    public int getAudioState() {
        if (mBluetoothHeadsetClient == null) {
            Log.e(TAG, "get hf profile fail!");
            return 2;
        }
        List<BluetoothDevice> deviceList = mBluetoothHeadsetClient.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "77 hf client is not connected!");
            return 2;
        }
        int scoState = mBluetoothHeadsetClient.getAudioState(deviceList.get(0));
        Log.d("__lh__", "scoState = " + scoState);
        return scoState;
    }

    public int getBluetoothAdapterState() {
        return mLocalAdapter.getState();
    }

    public void getLocalAdapter() {
        if (mLocalBtManager != null) {
            mLocalAdapter = mLocalBtManager.getBluetoothAdapter();
            if (mLocalAdapter != null && !mLocalAdapter.isEnabled()) {
                Log.d(TAG, "setBluetoothEnabled bForceEnable==" + this.bForceEnable);
                if (this.bForceEnable) {
                    mLocalAdapter.setBluetoothEnabled(true);
                }
            }
        }
    }

    public void setBluetoothEnabled(boolean enabled) {
        this.bForceEnable = enabled;
        if (mLocalAdapter == null) {
            mLocalAdapter = mLocalBtManager.getBluetoothAdapter();
        }
        if (mLocalAdapter != null && enabled != mLocalAdapter.isEnabled()) {
            mLocalAdapter.setBluetoothEnabled(enabled);
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
        if (mLocalAdapter != null && !mbHfConnected) {
            getLastAddr();
            if (mLastDeviceAddr != null) {
                Log.d(TAG, mLastDeviceAddr);
                if (mLocalAdapter != null) {
                    BluetoothDevice mDevice2 = mLocalAdapter.getRemoteDevice(mLastDeviceAddr);
                    if (mDeviceManager != null) {
                        CachedBluetoothDevice cachedDevice = mDeviceManager.findDevice(mDevice2);
                        if (cachedDevice == null) {
                            Log.d(TAG, "cahedDevice == null");
                        }
                        if (cachedDevice != null) {
                            Log.d(TAG, "cnnect device:" + cachedDevice);
                            cachedDevice.connect(true);
                            return;
                        }
                        return;
                    }
                    Log.d(TAG, "DeviceManager is null");
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
            } else if (addr != null) {
                Log.d(TAG, addr);
                if (mLocalAdapter != null) {
                    BluetoothDevice mDevice2 = mLocalAdapter.getRemoteDevice(addr);
                    if (mDeviceManager != null) {
                        CachedBluetoothDevice cachedDevice = mDeviceManager.findDevice(mDevice2);
                        if (cachedDevice == null) {
                            Log.d(TAG, "cahedDevice == null");
                        }
                        if (cachedDevice != null && !cachedDevice.isConnected()) {
                            Log.d(TAG, "cnnect device:" + cachedDevice);
                            cachedDevice.connect(true);
                            return;
                        }
                        return;
                    }
                    Log.d(TAG, "DeviceManager is null");
                }
            }
        }
    }

    public void connect(BonedDevice bonedDevice) {
        Log.d(TAG, "bt connect");
        getLocalAdapter();
        if (mLocalAdapter != null) {
            String addr = bonedDevice.addr;
            String name = bonedDevice.name;
            if (name != null && !name.startsWith("OBD") && mbHfConnected) {
                mIsConnectOther = true;
                mOtherAddr = addr;
                disconnect();
            } else if (addr != null) {
                Log.d(TAG, addr);
                if (mLocalAdapter != null) {
                    BluetoothDevice mDevice2 = mLocalAdapter.getRemoteDevice(addr);
                    if (mDeviceManager != null) {
                        CachedBluetoothDevice cachedDevice = mDeviceManager.findDevice(mDevice2);
                        if (cachedDevice == null) {
                            Log.d(TAG, "cahedDevice == null");
                        }
                        if (cachedDevice != null && !cachedDevice.isConnected()) {
                            Log.d(TAG, "cnnect device:" + cachedDevice);
                            cachedDevice.connect(true);
                            return;
                        }
                        return;
                    }
                    Log.d(TAG, "DeviceManager is null");
                }
            }
        }
    }

    public void disconnect() {
        String addr;
        if (D) {
            Log.d(TAG, "onClickdisConnect");
        }
        if (mbHfConnected) {
            if (isSaveLastAddr) {
                saveLastAddr();
            } else {
                isSaveLastAddr = true;
            }
            getLocalAdapter();
            if (mLocalAdapter != null && (addr = getAddr()) != null && !addr.isEmpty()) {
                Log.d(TAG, "disconnect");
                CachedBluetoothDevice cachedDevice = mDeviceManager.findDevice(mLocalAdapter.getRemoteDevice(addr));
                if (cachedDevice != null) {
                    cachedDevice.disconnect();
                }
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

    public void clearLastAddr() {
        SharedPreferences.Editor sharedata = mContext.getSharedPreferences("last_dev_info", 0).edit();
        sharedata.putString("LAST_DEV_ADDR", (String) null);
        sharedata.commit();
        mLastDeviceAddr = null;
    }

    public void storeLastAddr() {
        SharedPreferences.Editor sharedata = mContext.getSharedPreferences("last_dev_info", 0).edit();
        sharedata.putString("LAST_DEV_ADDR", mLastDeviceAddr);
        sharedata.commit();
    }

    public void saveOBDAddr() {
        SharedPreferences.Editor sharedata = mContext.getSharedPreferences("last_dev_info", 0).edit();
        sharedata.putString("LAST_OBD_ADDR", mLastOBDAddr);
        sharedata.commit();
        isObd = true;
    }

    public void getOBDAddr() {
        if (mLastOBDAddr == null || mLastOBDAddr.isEmpty()) {
            mLastOBDAddr = mContext.getSharedPreferences("last_dev_info", 0).getString("LAST_OBD_ADDR", TXZResourceManager.STYLE_DEFAULT);
        }
    }

    public String GetOBDAddr() {
        if (mLastOBDAddr == null || mLastOBDAddr.isEmpty()) {
            return TXZResourceManager.STYLE_DEFAULT;
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
        insertPhonebook(getAddr(), name, num, TXZResourceManager.STYLE_DEFAULT, TXZResourceManager.STYLE_DEFAULT, TXZResourceManager.STYLE_DEFAULT, TXZResourceManager.STYLE_DEFAULT);
    }

    public void insertPhonebook(String name, String num, String pinyin) {
        insertPhonebook(getAddr(), name, num, pinyin, TXZResourceManager.STYLE_DEFAULT, TXZResourceManager.STYLE_DEFAULT, TXZResourceManager.STYLE_DEFAULT);
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
            values.put("name", name);
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
                        values.put("name", pbItem.name);
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
                        String name1 = dbCursor.getString(dbCursor.getColumnIndex("name"));
                        String num1 = dbCursor.getString(dbCursor.getColumnIndex(CanBMWMiniServiceDetailActivity.KEY_NUM));
                        String time1 = dbCursor.getString(dbCursor.getColumnIndex(MainUI.NET_TIME_));
                        String type1 = dbCursor.getString(dbCursor.getColumnIndex("type"));
                        long j = dbCursor.getLong(dbCursor.getColumnIndex("calltime"));
                        mBtDb.delete("diallog", "addr=? and name=? and num=? and time=? and type=?", new String[]{getAddr(), name1, num1, time1, type1});
                    }
                    dbCursor.close();
                }
            }
            ContentValues values = new ContentValues();
            values.put("addr", getAddr());
            values.put("name", name);
            values.put(CanBMWMiniServiceDetailActivity.KEY_NUM, num);
            values.put(MainUI.NET_TIME_, time);
            values.put("type", type);
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
            stopDownload();
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
        mIsAutoConnect = mContext.getSharedPreferences("bt.setting.autoconnect", 0).getBoolean("IS_BT_AUTO_CONNECT", true);
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

    public void setAutoConnect(boolean isAutoConnect) {
        mIsAutoConnect = isAutoConnect;
    }

    public void resetAutoConnect() {
        mbNeedAutoConnect = false;
    }

    public void setAutoConnect() {
        mbNeedAutoConnect = true;
    }

    public void sendDTMFCode(byte code) {
        Log.d(TAG, "sendDTMFCode:" + code);
        if (mCallLists == null || mCallLists.size() == 0) {
            Log.e(TAG, "mCallLists is null");
            return;
        }
        Call call = getCall(mCallLists, 4);
        if (call != null) {
            call.playDtmfTone((char) code);
        }
    }

    public static void setContext(Context context) {
        if (mContext == null) {
            mContext = context;
            getBtInstance().InitBt();
        }
    }

    public void initData() {
        readAutoConnectData();
        readAutoAnswerData();
    }

    public String getContactByNumber(String number) {
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public int getCallValue() {
        if (mCallLists != null && mCallLists.size() != 0) {
            return mCallLists.get(0).getState();
        }
        Log.e(TAG, "Call had been ended before this activity create!");
        return -1;
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
        mStrCallName = TXZResourceManager.STYLE_DEFAULT;
        String phoneNumber = new String(mStrPhoneNo);
        if (mListPb != null) {
            String phoneNumber2 = phoneNumber.replace(SdkConstants.RES_QUALIFIER_SEP, TXZResourceManager.STYLE_DEFAULT).replace(" ", TXZResourceManager.STYLE_DEFAULT).replace("(", TXZResourceManager.STYLE_DEFAULT).replace(")", TXZResourceManager.STYLE_DEFAULT);
            int iNoLen = phoneNumber2.length();
            List<PbItem> lists = new ArrayList<>(mListPb);
            int size = lists.size();
            int i = 0;
            while (i < size) {
                PbItem pbItem = lists.get(i);
                String strPbNo = pbItem.num.replace(SdkConstants.RES_QUALIFIER_SEP, TXZResourceManager.STYLE_DEFAULT).replace(" ", TXZResourceManager.STYLE_DEFAULT).replace("(", TXZResourceManager.STYLE_DEFAULT).replace(")", TXZResourceManager.STYLE_DEFAULT);
                if (iNoLen != strPbNo.length() || !phoneNumber2.equals(strPbNo)) {
                    i++;
                } else {
                    if (isBtCountry()) {
                        strPbName6 = pbItem.name;
                    } else {
                        String first_name = pbItem.first_name;
                        String middle_name = pbItem.middle_name;
                        String strPbName7 = String.valueOf(TXZResourceManager.STYLE_DEFAULT) + pbItem.given_name;
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
                for (int i2 = 0; i2 < size; i2++) {
                    PbItem pbItem2 = lists.get(i2);
                    String strPbNo2 = pbItem2.num.replace(SdkConstants.RES_QUALIFIER_SEP, TXZResourceManager.STYLE_DEFAULT).replace(" ", TXZResourceManager.STYLE_DEFAULT).replace("(", TXZResourceManager.STYLE_DEFAULT).replace(")", TXZResourceManager.STYLE_DEFAULT);
                    int iPbLen = strPbNo2.length();
                    if (iNoLen >= iPbLen) {
                        String strNum = phoneNumber2.substring(iNoLen - iPbLen);
                        if ((strNum.equals(strPbNo2) && iPbLen != 0) || (iPbLen - 1 >= 7 && strNum.substring(1).equals(strPbNo2.substring(1)))) {
                            if (isBtCountry()) {
                                strPbName4 = pbItem2.name;
                            } else {
                                String first_name2 = pbItem2.first_name;
                                String middle_name2 = pbItem2.middle_name;
                                String strPbName8 = String.valueOf(TXZResourceManager.STYLE_DEFAULT) + pbItem2.given_name;
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
                            mStrCallName = strPbName4;
                        }
                    } else if (iNoLen < iPbLen) {
                        String strNum2 = strPbNo2.substring(iPbLen - iNoLen);
                        if (strNum2.equals(phoneNumber2) || (iNoLen - 1 >= 7 && strNum2.substring(1).equals(phoneNumber2.substring(1)))) {
                            if (isBtCountry()) {
                                strPbName2 = pbItem2.name;
                            } else {
                                String first_name3 = pbItem2.first_name;
                                String middle_name3 = pbItem2.middle_name;
                                String strPbName9 = String.valueOf(TXZResourceManager.STYLE_DEFAULT) + pbItem2.given_name;
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
                            mStrCallName = strPbName2;
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
                mbNeedAutoConnect = true;
                mLastConnectTick = 0;
                mLastAccOnTick = getTickCount();
                Log.e(TAG, "Fake on ,clear mLastConnectTick");
            } else if (mcusSta == 3 || mcusSta == 2) {
                mbNeedAutoConnect = false;
                Log.d("lh10", "mcusta:" + mcusSta);
                if (mcusSta == 2 && this.mCbTimer != null) {
                    Log.d("lh10", "sleep");
                    isShowBox = false;
                    isHideDialog = false;
                    isAddCall = false;
                    this.mCbTimer.onBtTimer();
                }
                if (mbHfConnected && mbNeedSaveData) {
                    storeLastAddr();
                    mbNeedSaveData = false;
                }
            }
            mOldMcuSta = mcusSta;
        }
        if (!mBtIsEnabled) {
            long j = mCount;
            mCount = 1 + j;
            if (j > 150) {
                if (mLocalAdapter.getScanMode() != 23) {
                    mCount = 0;
                    setBluetoothDiscoverability(true);
                } else {
                    Log.d(TAG, "bt is discoverable");
                    mBtIsEnabled = true;
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
                mStrCallName = TXZResourceManager.STYLE_DEFAULT;
                mStrOldCallNo = TXZResourceManager.STYLE_DEFAULT;
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
                if (!(mode == 5 || mode == 10 || !isBtMusicPlaying())) {
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
        if (this.mCbTimer != null) {
            updateActiveSecond();
            this.mCbTimer.onBtTimer();
        }
        int size = mCallLists.size();
        if (size > 0) {
            List<Call> mRemoveCallLists = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Call call = mCallLists.get(i);
                if (call.getState() == 7) {
                    mRemoveCallLists.add(call);
                }
                onActionCallChanged(call);
                updateCallSta();
                UpdateHfpSta();
            }
            int mRemoveSize = mRemoveCallLists.size();
            if (mRemoveSize <= 0) {
                return 255;
            }
            for (int i2 = 0; i2 < mRemoveSize; i2++) {
                mCallLists.remove(mRemoveCallLists.get(i2));
            }
            return 255;
        }
        isShowBox = false;
        return 255;
    }

    /* access modifiers changed from: package-private */
    public void updateActiveSecond() {
        for (Map.Entry<String, PhoneCall> element : this.mCallMap.entrySet()) {
            String strKey = element.getKey();
            PhoneCall strValue = element.getValue();
            int state = strValue.getCallState();
            if (state == 4 || state == 3) {
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
            intent.putExtra("name", GetCallName());
            intent.putExtra("number", getCallingNum());
            Log.d(TAG, "UpdateHfpSta " + intent.getIntExtra("hfpStatus", 0) + ", name = " + mStrCallName);
            mContext.sendBroadcast(intent);
        }
    }

    public Map<String, String> GetPbMap() {
        Map<String, String> pbMap = new HashMap<>();
        if (mListPb != null && isConnected()) {
            Log.d(TAG, "*****GetPbMap***** size = " + mListPb.size());
            List<PbItem> lists = new ArrayList<>(mListPb);
            int size = lists.size();
            for (int i = 0; i < size; i++) {
                PbItem item = lists.get(i);
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
            List<PbItem> lists = new ArrayList<>(mListPb);
            int size = lists.size();
            for (int i = 0; i < size; i++) {
                PbInfo pbInfo = new PbInfo();
                PbItem item = lists.get(i);
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
            List<ContactInfo> list = new ArrayList<>();
            List<TXZCallManager.Contact> lst = new ArrayList<>();
            if (mListPb != null && isConnected()) {
                Log.d(TAG, "*****GetPbMap***** size = " + mListPb.size());
                List<PbItem> lists = new ArrayList<>(mListPb);
                int size = lists.size();
                for (int i = 0; i < size; i++) {
                    PbItem item = lists.get(i);
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
            Log.d("lh8", "updatePbMap totalTime = " + (System.currentTimeMillis() - startTime));
            if (this.btCallback != null) {
                this.btCallback.onBtPbListChange(getTsSpeechBtPbInfo());
            }
        }
    }

    public void updatePbList() {
        if (dbHelper != null) {
            long startTime = System.currentTimeMillis();
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
                        String name = cursor1.getString(cursor1.getColumnIndex("name"));
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
            Log.d("lh8", "totalTime = " + (System.currentTimeMillis() - startTime));
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
                if (isAutoConncted) {
                    connect();
                }
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
        if (phoneCallState == 4) {
            CallSta = 4;
        } else if (phoneCallState == -1 || phoneCallState == 7) {
            if (mbHfConnected) {
                CallSta = 1;
            } else {
                CallSta = 0;
            }
        } else if (phoneCallState == 2) {
            CallSta = 3;
        } else if (phoneCallState == 1) {
            CallSta = 2;
        } else if (phoneCallState == 3) {
            CallSta = 5;
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
        return mStrPhoneName;
    }

    public String getVersion() {
        BluetoothAdapter mAdapter2 = BluetoothAdapter.getDefaultAdapter();
        if (mAdapter2 == null) {
            return VER_STR;
        }
        String strAddr = mAdapter2.getAddress();
        if (strAddr == null) {
            strAddr = DVRConst.UNKOWN_CAMERA_ID;
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
        if (mBluetoothHeadsetClient == null) {
            Log.e(TAG, "get hf profile fail!");
            return TXZResourceManager.STYLE_DEFAULT;
        }
        List<BluetoothDevice> deviceList = mBluetoothHeadsetClient.getConnectedDevices();
        if (deviceList == null || deviceList.size() == 0) {
            Log.e(TAG, "1212 hf client is not connected!");
            return TXZResourceManager.STYLE_DEFAULT;
        }
        List<BluetoothHeadsetClientCall> callList = mBluetoothHeadsetClient.getCurrentCalls(deviceList.get(0));
        if (callList != null && callList.size() != 0) {
            return callList.get(0).getNumber();
        }
        Log.e(TAG, "Call had been ended before this activity create!");
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public void regPlayerUtility() {
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
        if (mLocalAdapter == null || !mLocalAdapter.isEnabled() || (IsHfConnected = bluetoothIsConnected()) == mbHfConnected) {
            return false;
        }
        Log.e(TAG, "checkIfAbnormal IsHfConnected != mbHfConnected");
        if (!IsHfConnected) {
            Log.d(TAG, "bt is disconnected");
            return true;
        } else if (mDevice == null) {
            return true;
        } else {
            Log.d(TAG, "bt is connected");
            mbNeedUpdatePhoneName = true;
            mbHfConnected = true;
            mCallSta = 1;
            mbNeedSaveData = true;
            getBtInstance().saveLastAddr();
            getBtInstance().UpdateHfpSta();
            getBtInstance().updatePbList();
            getBtInstance().UpdatePbMap();
            mPbStatus = 2;
            Log.e(TAG, "hfp connected!");
            return true;
        }
    }

    public void powerOn() {
        getLocalAdapter();
        if (!mLocalAdapter.isEnabled()) {
            Log.d(TAG, "powerOn");
            mLocalAdapter.setBluetoothEnabled(true);
        }
    }

    public boolean bluetoothIsConnected() {
        if (mDevice != null) {
            return true;
        }
        return false;
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
        Log.d("lh3", "PbSort1");
        if (list.size() > 0 && mContext != null) {
            Locale curLocal = Locale.getDefault();
            Log.d(TAG, "lgb CurLocal = " + curLocal);
            this.mCmp = Collator.getInstance(curLocal);
            Collections.sort(list, new Comparator() {
                public int compare(Object o1, Object o2) {
                    return BtExe.this.mCmp.compare(((PbItem) o1).pinyin, ((PbItem) o2).pinyin);
                }

                public Comparator reversed() {
                    return null;
                }

                public Comparator thenComparing(Comparator other) {
                    return null;
                }

                public Comparator thenComparing(Function keyExtractor, Comparator keyComparator) {
                    return null;
                }

                public Comparator thenComparing(Function keyExtractor) {
                    return null;
                }

                public Comparator thenComparingInt(ToIntFunction keyExtractor) {
                    return null;
                }

                public Comparator thenComparingLong(ToLongFunction keyExtractor) {
                    return null;
                }

                public Comparator thenComparingDouble(ToDoubleFunction keyExtractor) {
                    return null;
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

                    public Comparator reversed() {
                        return null;
                    }

                    public Comparator thenComparing(Comparator other) {
                        return null;
                    }

                    public Comparator thenComparing(Function keyExtractor, Comparator keyComparator) {
                        return null;
                    }

                    public Comparator thenComparing(Function keyExtractor) {
                        return null;
                    }

                    public Comparator thenComparingInt(ToIntFunction keyExtractor) {
                        return null;
                    }

                    public Comparator thenComparingLong(ToLongFunction keyExtractor) {
                        return null;
                    }

                    public Comparator thenComparingDouble(ToDoubleFunction keyExtractor) {
                        return null;
                    }
                });
            }
        }
    }

    public boolean isBtCountry() {
        String language = mContext.getResources().getConfiguration().locale.getLanguage();
        Log.d("lh3", "isBtCountry = " + language);
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
            BtExe.this.updateListPb();
            BtExe.this.PbSort(BtExe.mListPb);
            BtExe.getBtInstance().UpdatePbMap();
            BtExe.this.SaveDatabase();
            if (1 != BtExe.mPbStatus) {
                return null;
            }
            BtExe.mPbStatus = 2;
            return null;
        }
    }

    public void downLoad() {
        startDownload();
    }

    public void updateListPb() {
        String addr = getAddr();
        if (addr != null && !addr.isEmpty()) {
            delete("phonebook", "addr=?", new String[]{addr});
            ContentResolver contentResolver = mContext.getContentResolver();
            Cursor nameCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, (String[]) null, "mimetype = ?", new String[]{"vnd.android.cursor.item/name"}, (String) null);
            Log.d(TAG, "size  = " + nameCur.getCount());
            HashMap hashMap = new HashMap();
            while (nameCur.moveToNext()) {
                NameInfo nameInfo = new NameInfo();
                String contactId = nameCur.getString(nameCur.getColumnIndex("contact_id"));
                String firstName = nameCur.getString(nameCur.getColumnIndex("data3"));
                String middleName = nameCur.getString(nameCur.getColumnIndex("data5"));
                String givenName = nameCur.getString(nameCur.getColumnIndex("data2"));
                String disPlayName = nameCur.getString(nameCur.getColumnIndex("data1"));
                if (TextUtils.isEmpty(contactId)) {
                    nameInfo.setContactId(TXZResourceManager.STYLE_DEFAULT);
                } else {
                    nameInfo.setContactId(contactId);
                    if (TextUtils.isEmpty(firstName)) {
                        nameInfo.setFirstName(TXZResourceManager.STYLE_DEFAULT);
                    } else {
                        nameInfo.setFirstName(firstName);
                    }
                    if (TextUtils.isEmpty(middleName)) {
                        nameInfo.setMiddleName(TXZResourceManager.STYLE_DEFAULT);
                    } else {
                        nameInfo.setMiddleName(middleName);
                    }
                    if (TextUtils.isEmpty(givenName)) {
                        nameInfo.setGivenName(TXZResourceManager.STYLE_DEFAULT);
                    } else {
                        nameInfo.setGivenName(givenName);
                    }
                    if (TextUtils.isEmpty(disPlayName)) {
                        nameInfo.setDisPlayName(TXZResourceManager.STYLE_DEFAULT);
                    } else {
                        nameInfo.setDisPlayName(disPlayName);
                    }
                    hashMap.put(contactId, nameInfo);
                }
            }
            nameCur.close();
            Cursor phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, (String[]) null, (String) null, (String[]) null, (String) null);
            Log.d(TAG, "size1 = " + phones.getCount());
            ArrayList arrayList = new ArrayList();
            while (phones.moveToNext()) {
                PhoneInfo phoneInfo = new PhoneInfo();
                String contactId2 = phones.getString(phones.getColumnIndex("contact_id"));
                String number = phones.getString(phones.getColumnIndex("data1"));
                String name = phones.getString(phones.getColumnIndex("display_name"));
                if (TextUtils.isEmpty(contactId2)) {
                    phoneInfo.setContactId(TXZResourceManager.STYLE_DEFAULT);
                } else {
                    phoneInfo.setContactId(contactId2);
                    if (TextUtils.isEmpty(number)) {
                        phoneInfo.setNumber(TXZResourceManager.STYLE_DEFAULT);
                    } else {
                        phoneInfo.setNumber(number);
                    }
                    if (TextUtils.isEmpty(name)) {
                        phoneInfo.setName(TXZResourceManager.STYLE_DEFAULT);
                    } else {
                        phoneInfo.setName(name);
                    }
                    arrayList.add(phoneInfo);
                }
            }
            phones.close();
            ArrayList arrayList2 = new ArrayList();
            boolean isZhCountry = isZhCountry();
            boolean isBtCountry = isBtCountry();
            for (int i = 0; i < arrayList.size(); i++) {
                PhoneInfo phoneInfo2 = (PhoneInfo) arrayList.get(i);
                NameInfo nameInfo2 = (NameInfo) hashMap.get(phoneInfo2.getContactId());
                if (nameInfo2 != null) {
                    PbItem pbItem = new PbItem();
                    pbItem.first_name = nameInfo2.getFirstName();
                    pbItem.middle_name = nameInfo2.getMiddleName();
                    pbItem.given_name = nameInfo2.getGivenName();
                    pbItem.name = nameInfo2.getDisPlayName();
                    pbItem.pinyin = Cn2Spell.getPinYin(pbItem.name).toUpperCase().trim();
                    pbItem.num = phoneInfo2.getNumber();
                    if (!TextUtils.isEmpty(pbItem.num) && pbItem.num.matches("[\\d\\+\\-\\.\\,\\(\\)\\*\\#\\/\\s]*") && !TextUtils.isEmpty(pbItem.name) && !pbItem.name.startsWith("@@@")) {
                        pbItem.pinyin = updateSortRow(isZhCountry, isBtCountry, pbItem);
                        arrayList2.add(pbItem);
                    }
                }
            }
            Log.d(TAG, "contactSize = " + arrayList2.size());
            mListPb.clear();
            mListPb.addAll(arrayList2);
        }
    }

    public void SaveDatabase() {
        Log.d(TAG, "SaveDatabase = ");
        long startTime = System.currentTimeMillis();
        insertPhonebookList(mListPb);
        Log.d(TAG, "SaveDatebase time = " + (System.currentTimeMillis() - startTime));
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
        if (mbHfConnected && mA2dpSinkProfile != null) {
            int size = mDeviceLists.size();
            String addr = getAddr();
            if (!TextUtils.isEmpty(addr)) {
                int i = 0;
                while (i < size) {
                    BtDevice btDevice = mDeviceLists.get(i);
                    if (!addr.equals(btDevice.device.getAddress()) || btDevice.a2dp == 2) {
                        i++;
                    } else {
                        mA2dpSinkProfile.connect(mDevice);
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void unregMetadataCallback() {
        Log.d(TAG, "unregMetadataCallback");
    }

    public void setAutoDisconnect(boolean isAutoDisconnect) {
        mIsAutoDisconnect = isAutoDisconnect;
    }

    public boolean getAutoDisconnect() {
        return mIsAutoDisconnect;
    }

    public void insertPhonebookProvider(String name, String num) {
        Uri uri = Uri.parse("content://com.nwd.bt.provider/phonebook");
        ContentValues values = new ContentValues();
        values.put("phonebook_name", name);
        values.put("phonebook_number", num);
        mContext.getContentResolver().insert(uri, values);
    }

    public void deletePhonebookProvider() {
        mContext.getContentResolver().delete(Uri.parse("content://com.nwd.bt.provider/phonebook"), (String) null, (String[]) null);
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
        if (mLocalAdapter != null) {
            unpairDevice(mLocalAdapter.getRemoteDevice(addr));
        }
    }

    public void unpairDevice(BluetoothDevice device) {
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

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005e, code lost:
        r9 = r12.get(0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.ts.bt.BtExe.BonedDevice> updateBonedDeviceList() {
        /*
            r15 = 1
            r14 = 0
            r2 = 0
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            com.ts.bt.BtDatabaseHelper r0 = dbHelper
            if (r0 == 0) goto L_0x0077
            android.database.sqlite.SQLiteDatabase r0 = mBtDb
            if (r0 != 0) goto L_0x0018
            com.ts.bt.BtDatabaseHelper r0 = dbHelper
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            mBtDb = r0
        L_0x0018:
            android.database.sqlite.SQLiteDatabase r0 = mBtDb
            java.lang.String r1 = "boned_device"
            r3 = r2
            r4 = r2
            r5 = r2
            r6 = r2
            r7 = r2
            android.database.Cursor r10 = r0.query(r1, r2, r3, r4, r5, r6, r7)
            boolean r0 = r10.moveToLast()
            if (r0 == 0) goto L_0x0077
            r9 = 0
        L_0x002d:
            java.lang.String r0 = "addr"
            int r0 = r10.getColumnIndex(r0)
            java.lang.String r8 = r10.getString(r0)
            java.lang.String r0 = "name"
            int r0 = r10.getColumnIndex(r0)
            java.lang.String r11 = r10.getString(r0)
            com.ts.bt.BtExe$BonedDevice r9 = new com.ts.bt.BtExe$BonedDevice
            r9.<init>()
            r9.addr = r8
            r9.name = r11
            r12.add(r9)
            boolean r0 = r10.moveToPrevious()
            if (r0 != 0) goto L_0x002d
            r10.close()
            int r13 = r12.size()
            if (r13 <= r15) goto L_0x0077
            java.lang.Object r9 = r12.get(r14)
            com.ts.bt.BtExe$BonedDevice r9 = (com.ts.bt.BtExe.BonedDevice) r9
            java.lang.String r11 = r9.name
            if (r11 == 0) goto L_0x0077
            java.lang.String r0 = "OBD"
            boolean r0 = r11.startsWith(r0)
            if (r0 == 0) goto L_0x0077
            r12.remove(r14)
            r12.add(r15, r9)
        L_0x0077:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.bt.BtExe.updateBonedDeviceList():java.util.List");
    }

    public static void insertBonedDevice(String addr, String name) {
        if (dbHelper != null) {
            if (mBtDb == null) {
                mBtDb = dbHelper.getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put("addr", addr);
            values.put("name", name);
            mBtDb.insert("boned_device", (String) null, values);
            values.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public void sendTsAuthorInfo(String msg) {
        Intent intent = new Intent(TS_AUTHOR_INFO);
        intent.putExtra("author", msg);
        mContext.sendBroadcast(intent);
    }

    public void registerBtReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.RESULT");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter.addAction(BROADCAST_REQUEST_HFPSTATUS);
        intentFilter.addAction(TS_GET_AUTHOR_INFO);
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction(CanBtOBDActivity.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("com.autochips.bluetooth.phonebookdownload.action.ACTION_PHONEBOOK_DOWNLOAD_STATE_IND");
        intentFilter.addAction("com.autochips.bluetooth.phonebookdownload.action.ACTION_PHONEBOOK_DOWNLOAD_ONESTEP");
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.AG_EVENT");
        mContext.registerReceiver(this.mBtReceiver, intentFilter);
    }

    public void unregisterBtReceiver() {
        mContext.unregisterReceiver(this.mBtReceiver);
    }

    public String getLocalBtAddress() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        String strAddr = null;
        if (bluetoothAdapter != null) {
            strAddr = bluetoothAdapter.getAddress();
        }
        if (strAddr == null) {
            return DVRConst.UNKOWN_CAMERA_ID;
        }
        return strAddr;
    }

    public Call getCall(List<Call> callList, int... states) {
        for (Call c : callList) {
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
        List<Call> callList = getCurrentCalls();
        if (callList != null) {
            Log.d("lh6", "size6: " + callList.size());
        }
        if ((callList == null || callList.size() == 0) && getBtInstance().mCallMap.size() > 0) {
            for (Map.Entry<String, PhoneCall> element : getBtInstance().mCallMap.entrySet()) {
                String uuid = element.getKey();
                if (getBtInstance().mCallMap.containsKey(uuid)) {
                    PhoneCall phonecall = getBtInstance().mCallMap.get(uuid);
                    long callActiveSecond = phonecall.getCallActiveSecond();
                    String callNumber = phonecall.getCallNumber();
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
                    String time = String.valueOf(time1.substring(0, 4)) + SdkConstants.RES_QUALIFIER_SEP + time1.substring(4, 6) + SdkConstants.RES_QUALIFIER_SEP + time1.substring(6, 8) + " " + time1.substring(9, 11) + ":" + time1.substring(11, 13) + ":" + time1.substring(13, 15);
                    Log.d(TAG, "currentTime: " + time);
                    if (mStrPhoneNo != null && !mStrPhoneNo.isEmpty()) {
                        mLastPhoneNo = mStrPhoneNo;
                    }
                    getBtInstance().insertDiallog(callName, callNumber, time, callType, callActiveSecond);
                    updateHistory();
                    isRefreshLog = true;
                    getBtInstance().mCallMap.remove(uuid);
                }
            }
            getBtInstance().mCallMap.clear();
        }
    }

    public static void updateHistory() {
        if (dbHelper != null) {
            ArrayList<HashMap<String, Object>> historyList = new ArrayList<>();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (!TextUtils.isEmpty(getAddr())) {
                Cursor dbCursor = db.query("diallog", (String[]) null, "addr=?", new String[]{getAddr()}, (String) null, (String) null, (String) null);
                if (dbCursor.getCount() != 0) {
                    if (dbCursor.moveToLast()) {
                        do {
                            HashMap<String, Object> map = new HashMap<>();
                            String name = dbCursor.getString(dbCursor.getColumnIndex("name"));
                            String num = dbCursor.getString(dbCursor.getColumnIndex(CanBMWMiniServiceDetailActivity.KEY_NUM));
                            String time = dbCursor.getString(dbCursor.getColumnIndex(MainUI.NET_TIME_));
                            String type = dbCursor.getString(dbCursor.getColumnIndex("type"));
                            long calltime = dbCursor.getLong(dbCursor.getColumnIndex("calltime"));
                            map.put(ITEM_HISTORY_NAME, name);
                            map.put(ITEM_HISTORY_NUMBER, num);
                            map.put(ITEM_HISTORY_TIME, time);
                            map.put(ITEM_HISTORY_TYPE, type);
                            map.put(ITEM_HISTORY_CALLTIME, Long.valueOf(calltime));
                            historyList.add(map);
                        } while (dbCursor.moveToPrevious());
                    }
                    dbCursor.close();
                }
                mAllHistoryList.clear();
                mAllHistoryList.addAll(historyList);
            }
        }
    }

    public static void flushFilterList() {
        int length = mAllHistoryList.size();
        mHistoryList.clear();
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = mAllHistoryList.get(i);
            String type = (String) map.get(ITEM_HISTORY_TYPE);
            if (!TextUtils.isEmpty(type)) {
                if (mlistFilter == 1 && type.equals(OUTGOING_TYPE)) {
                    mHistoryList.add(map);
                }
                if (mlistFilter == 2 && type.equals(INCOMING_TYPE)) {
                    mHistoryList.add(map);
                }
                if (mlistFilter == 4 && type.equals(MISSED_TYPE)) {
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
        String devName = remoteDevice.getName();
        if (D) {
            Log.d(TAG, "[Pair] Device : " + devName + ", type = " + type);
        }
        switch (type) {
            case 0:
            case 5:
            case 7:
                if (type == 0) {
                    if (devName != null && devName.startsWith("OBD")) {
                        remoteDevice.setPin(BluetoothDevice.convertPinToBytes(mObdPin));
                        return;
                    } else if (isObd) {
                        isObd = false;
                    } else if (!isConnected() && mContext != null) {
                        BtPinDialog.initWindow(mContext);
                        BtPinDialog.showView(mPin);
                    }
                }
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
        isHideDialog = false;
        isAddCall = false;
        getBtInstance().updateCallSta();
        dispatchMessage(18, (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), 0, 0);
    }

    public void onActionCallChanged(Call callStatus) {
        String callNumber;
        if (callStatus == null) {
            Log.e(TAG, "get callStatus fall!");
            return;
        }
        String callNumber2 = getNumber(callStatus);
        if (callNumber2 == null) {
            callNumber = TXZResourceManager.STYLE_DEFAULT;
        } else {
            callNumber = callNumber2.replace(" ", TXZResourceManager.STYLE_DEFAULT);
        }
        mStrPhoneNo = callNumber;
        UpdateCallName(callNumber);
        int state = callStatus.getState();
        if (!(AppBinder.mIOnActionCallback == null || this.mState == state)) {
            this.mState = state;
            try {
                Log.d(TAG, "onActionCallChanged");
                AppBinder.mIOnActionCallback.onActionCallChanged(state, callNumber);
            } catch (RemoteException e) {
                Log.d(TAG, "onActionCallChanged exception " + e.toString());
                e.printStackTrace();
            }
        }
        if (this.btCallback != null) {
            this.btCallback.onBtStateChange(state, callNumber);
        }
        Log.d("lh6", "state: " + state);
        Log.d("lh6", "callNumber: " + callNumber);
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
                String time = String.valueOf(time1.substring(0, 4)) + SdkConstants.RES_QUALIFIER_SEP + time1.substring(4, 6) + SdkConstants.RES_QUALIFIER_SEP + time1.substring(6, 8) + " " + time1.substring(9, 11) + ":" + time1.substring(11, 13) + ":" + time1.substring(13, 15);
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
            if (state == 1) {
                phoneCall.setCallType(OUTGOING_TYPE);
            } else if (state == 2) {
                phoneCall.setCallType(MISSED_TYPE);
            } else if (state == 4) {
                if (lastState == 2) {
                    phoneCall.setCallType(INCOMING_TYPE);
                }
                if (lastState == 1 || lastState == 2) {
                    long callActiveTick = getTickCount();
                    phoneCall.setCallActiveSecond(0);
                    phoneCall.setCallActiveTick(callActiveTick);
                }
            }
            phoneCall.setCallNumber(callNumber);
            phoneCall.setCallName(callName);
            phoneCall.setCallState(state);
            this.mCallMap.put(callNumber, phoneCall);
        } else {
            Log.d("lh6", "first");
            PhoneCall phoneCall2 = new PhoneCall();
            if (state == 1) {
                phoneCall2.setCallType(OUTGOING_TYPE);
            } else if (state == 2) {
                phoneCall2.setCallType(MISSED_TYPE);
            } else if (state == 4 && -1 == 2) {
                phoneCall2.setCallType(INCOMING_TYPE);
            }
            int lastState2 = state;
            phoneCall2.setCallNumber(callNumber);
            phoneCall2.setCallName(callName);
            phoneCall2.setCallState(state);
            phoneCall2.setBluetoothCall(isBluetoothCall(callStatus));
            this.mCallMap.put(callNumber, phoneCall2);
        }
        if (state != 2 && state != 1 && state != 4) {
            Log.d(TAG, "ignore callState !");
        } else if (!T3WeakJoinUtils.bIsT3WeakPrj) {
            isShowBox = true;
        }
    }

    public void removePhoneCall() {
    }

    public void UpdateCallName(String phoneNumber) {
        String strPbName;
        String strPbName2;
        String strPbName3;
        String strPbName4;
        String strPbName5;
        String strPbName6;
        Log.d(TAG, "UpdateCallName");
        mStrCallName = TXZResourceManager.STYLE_DEFAULT;
        if (mListPb != null) {
            String phoneNumber2 = phoneNumber.replace(SdkConstants.RES_QUALIFIER_SEP, TXZResourceManager.STYLE_DEFAULT).replace(" ", TXZResourceManager.STYLE_DEFAULT).replace("(", TXZResourceManager.STYLE_DEFAULT).replace(")", TXZResourceManager.STYLE_DEFAULT);
            int iNoLen = phoneNumber2.length();
            List<PbItem> lists = new ArrayList<>(mListPb);
            int size = lists.size();
            int i = 0;
            while (i < size) {
                PbItem pbItem = lists.get(i);
                String strPbNo = pbItem.num.replace(SdkConstants.RES_QUALIFIER_SEP, TXZResourceManager.STYLE_DEFAULT).replace(" ", TXZResourceManager.STYLE_DEFAULT).replace("(", TXZResourceManager.STYLE_DEFAULT).replace(")", TXZResourceManager.STYLE_DEFAULT);
                if (iNoLen != strPbNo.length() || !phoneNumber2.equals(strPbNo)) {
                    i++;
                } else {
                    if (isBtCountry()) {
                        strPbName6 = pbItem.name;
                    } else {
                        String first_name = pbItem.first_name;
                        String middle_name = pbItem.middle_name;
                        String strPbName7 = String.valueOf(TXZResourceManager.STYLE_DEFAULT) + pbItem.given_name;
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
                for (int i2 = 0; i2 < size; i2++) {
                    PbItem pbItem2 = lists.get(i2);
                    String strPbNo2 = pbItem2.num.replace(SdkConstants.RES_QUALIFIER_SEP, TXZResourceManager.STYLE_DEFAULT).replace(" ", TXZResourceManager.STYLE_DEFAULT).replace("(", TXZResourceManager.STYLE_DEFAULT).replace(")", TXZResourceManager.STYLE_DEFAULT);
                    int iPbLen = strPbNo2.length();
                    if (iNoLen >= iPbLen) {
                        String strNum = phoneNumber2.substring(iNoLen - iPbLen);
                        if ((strNum.equals(strPbNo2) && iPbLen != 0) || (iPbLen - 1 >= 7 && strNum.substring(1).equals(strPbNo2.substring(1)))) {
                            if (isBtCountry()) {
                                strPbName4 = pbItem2.name;
                            } else {
                                String first_name2 = pbItem2.first_name;
                                String middle_name2 = pbItem2.middle_name;
                                String strPbName8 = String.valueOf(TXZResourceManager.STYLE_DEFAULT) + pbItem2.given_name;
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
                            mStrCallName = strPbName4;
                        }
                    } else if (iNoLen < iPbLen) {
                        String strNum2 = strPbNo2.substring(iPbLen - iNoLen);
                        if (strNum2.equals(phoneNumber2) || (iNoLen - 1 >= 7 && strNum2.substring(1).equals(phoneNumber2.substring(1)))) {
                            if (isBtCountry()) {
                                strPbName2 = pbItem2.name;
                            } else {
                                String first_name3 = pbItem2.first_name;
                                String middle_name3 = pbItem2.middle_name;
                                String strPbName9 = String.valueOf(TXZResourceManager.STYLE_DEFAULT) + pbItem2.given_name;
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
                            mStrCallName = strPbName2;
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

    public static List<Call> getCurrentCalls() {
        return mCallLists;
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
                    if ((state == 4 && (firstState == 4 || firstState == 3)) || (firstState == 4 && (state == 4 || state == 3))) {
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
                if (element.getValue().getCallState() == 4) {
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

    public int getBatteryLevel() {
        int i = this.mBatteryCount;
        this.mBatteryCount = i + 1;
        if (i > 100) {
            this.mBatteryCount = 0;
            mBatteryLevel = getBtBatteryLevel();
            Log.d(TAG, "getBatteryLevel: " + mBatteryLevel);
        }
        return mBatteryLevel;
    }

    public int getBtBatteryLevel() {
        List<BluetoothDevice> deviceList;
        Bundle bundle;
        Log.d(TAG, "getBtBatteryLevel");
        if (mBluetoothHeadsetClient == null || (deviceList = mBluetoothHeadsetClient.getConnectedDevices()) == null || deviceList.size() == 0 || (bundle = mBluetoothHeadsetClient.getCurrentAgEvents(deviceList.get(0))) == null) {
            return 0;
        }
        return bundle.getInt("android.bluetooth.headsetclient.extra.BATTERY_LEVEL");
    }

    public boolean startScanning() {
        boolean isStartScanning = false;
        mUnBonedLists.clear();
        if (mLocalAdapter != null) {
            if (mLocalAdapter.isDiscovering()) {
                mLocalAdapter.cancelDiscovery();
            }
            mLocalAdapter.startScanning(true);
            isStartScanning = true;
        }
        Log.d(TAG, "startScanning:isStartScanning = " + isStartScanning);
        return isStartScanning;
    }

    public static int deviceContains(BonedDevice bonedDevice) {
        int size = mUnBonedLists.size();
        String addr = bonedDevice.addr;
        for (int i = 0; i < size; i++) {
            if (mUnBonedLists.get(i).addr.equals(addr)) {
                return i;
            }
        }
        return -1;
    }

    public void setPlayState(boolean playstate) {
        Log.d(TAG, "setPlayState");
    }

    /* access modifiers changed from: private */
    public void connectMediaBrowser() {
        if (this.mMediaBrowser != null) {
            disconnectMediaBrowser();
        }
        this.mMediaBrowser = new MediaBrowser(mContext, new ComponentName(BT_BROWSED_PACKAGE, BT_BROWSED_SERVICE), this.mConnectionCallback, (Bundle) null);
        this.mMediaBrowser.connect();
        this.mTryConnectMediaSessionCnt++;
    }

    private void disconnectMediaBrowser() {
        if (this.mMediaBrowser != null) {
            this.mMediaBrowser.disconnect();
            this.mMediaBrowser = null;
        }
    }

    private boolean isBrowserConnected() {
        if (this.mMediaBrowser != null) {
            return this.mMediaBrowser.isConnected();
        }
        return false;
    }

    private void sendCustomAction(String customAction, Bundle data) {
        if (!isBrowserConnected() || this.mMediaController == null) {
            Log.w(TAG, "browser is not connected, not send: " + customAction);
            return;
        }
        MediaController.TransportControls mediaControllerCntrl = this.mMediaController.getTransportControls();
        if (mediaControllerCntrl != null) {
            mediaControllerCntrl.sendCustomAction(customAction, data);
        } else {
            Log.d(TAG, "get TransportControls fail. " + customAction);
        }
    }

    private void initPhoneStateListener() {
        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                Log.d("lh7", "state = " + state + ",number = " + incomingNumber);
                switch (state) {
                }
            }
        };
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService("phone");
        if (telephonyManager != null) {
            telephonyManager.listen(phoneStateListener, 32);
        }
    }

    public void addCall(Call call) {
        mCallLists.add(call);
        Log.d(TAG, "addCall:size = " + mCallLists.size());
    }

    public void removeCall(Call call) {
        mCallLists.remove(call);
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

    /* access modifiers changed from: package-private */
    public void onDeviceAdded(BluetoothDevice device) {
        if (device == null) {
            Log.d(TAG, "onDeviceAdded device is null");
            return;
        }
        String name = device.getName();
        String addr = device.getAddress();
        int state = device.getBondState();
        int deviceType = device.getBluetoothClass().getMajorDeviceClass();
        Log.d("lh8", "onDeviceAdded: name = " + name);
        Log.d("lh8", "onDeviceAdded: addr = " + addr);
        Log.d("lh8", "onDeviceAdded: state = " + state);
        Log.d("lh8", "onDeviceAdded: deviceType = " + deviceType);
        if (state != 12 && name != null) {
            if ((!name.contains("BT") || name.length() != 6) && !name.equals(addr)) {
                BonedDevice bonedDevice = new BonedDevice();
                bonedDevice.name = name;
                bonedDevice.addr = addr;
                int index = deviceContains(bonedDevice);
                if (index == -1) {
                    Log.d("lh8", "add device");
                    bonedDevice.state = state;
                    bonedDevice.type = deviceType;
                    mDeviceAdd = true;
                    mUnBonedLists.add(bonedDevice);
                } else if (!name.equals(mUnBonedLists.get(index).name)) {
                    Log.d("lh8", "update boneddevice");
                    mUnBonedLists.get(index).name = name;
                    mDeviceAdd = true;
                }
            }
        }
    }

    public static String getNumber(Call call) {
        if (call == null) {
            return null;
        }
        if (call.getDetails().getGatewayInfo() != null) {
            return call.getDetails().getGatewayInfo().getOriginalAddress().getSchemeSpecificPart();
        }
        Uri handle = getHandle(call);
        if (handle != null) {
            return handle.getSchemeSpecificPart();
        }
        return null;
    }

    public static Uri getHandle(Call call) {
        if (call == null) {
            return null;
        }
        return call.getDetails().getHandle();
    }

    public void stopDownload() {
        Log.d("lh8", "stopDownload");
        if (mDeviceManager == null) {
            Log.d(TAG, "DeviceManager is null");
        } else if (mDevice != null) {
            CachedBluetoothDevice cachedDevice = mDeviceManager.findDevice(mDevice);
            if (cachedDevice == null) {
                Log.d(TAG, "cahedDevice == null");
            }
            if (cachedDevice != null) {
                cachedDevice.disconnect(mLocalBtManager.getProfileManager().getPbapClientProfile());
            }
        }
    }

    public void stopDownload(BluetoothDevice device) {
        Log.d("lh8", "stopDownload");
        isDownloadPb = false;
        if (mDeviceManager == null) {
            Log.d(TAG, "DeviceManager is null");
        } else if (device != null) {
            CachedBluetoothDevice cachedDevice = mDeviceManager.findDevice(device);
            if (cachedDevice == null) {
                Log.d(TAG, "cahedDevice == null");
            }
            if (cachedDevice != null) {
                Log.d("lh8", "stopDownload1");
                isDownloadPb = false;
                cachedDevice.disconnect(mLocalBtManager.getProfileManager().getPbapClientProfile());
            }
        }
    }

    public void startDownload() {
        Log.d("lh8", "startDownload");
        if (mDeviceManager == null) {
            Log.d(TAG, "DeviceManager is null");
        } else if (mDevice != null) {
            CachedBluetoothDevice cachedDevice = mDeviceManager.findDevice(mDevice);
            if (cachedDevice == null) {
                Log.d(TAG, "cahedDevice == null");
            }
            if (cachedDevice != null && !cachedDevice.isConnectedProfile(mLocalBtManager.getProfileManager().getPbapClientProfile())) {
                Log.d("lh8", "startDownload1");
                isDownloadPb = true;
                cachedDevice.connectProfile(mLocalBtManager.getProfileManager().getPbapClientProfile());
            }
        }
    }

    public List<PhonebookData> getPbData() {
        ArrayList<PhonebookData> pbList = new ArrayList<>();
        if (dbHelper != null) {
            long startTime = System.currentTimeMillis();
            if (mBtDb == null) {
                mBtDb = dbHelper.getWritableDatabase();
            }
            Cursor cursor1 = mBtDb.query("phonebook", (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            if (cursor1.moveToLast()) {
                do {
                    String addr = cursor1.getString(cursor1.getColumnIndex("addr"));
                    String name = cursor1.getString(cursor1.getColumnIndex("name"));
                    String num = cursor1.getString(cursor1.getColumnIndex(CanBMWMiniServiceDetailActivity.KEY_NUM));
                    int collect = cursor1.getInt(cursor1.getColumnIndex(InvokeConstants.INVOKE_COLLECT));
                    String pinyin = cursor1.getString(cursor1.getColumnIndex("pinyin"));
                    String first_name = cursor1.getString(cursor1.getColumnIndex("first_name"));
                    String middle_name = cursor1.getString(cursor1.getColumnIndex("middle_name"));
                    String given_name = cursor1.getString(cursor1.getColumnIndex("given_name"));
                    PhonebookData map = new PhonebookData();
                    map.addr = addr;
                    map.name = name;
                    map.num = num;
                    map.collect = collect;
                    map.pinyin = pinyin;
                    map.first_name = first_name;
                    map.middle_name = middle_name;
                    map.given_name = given_name;
                    pbList.add(map);
                } while (cursor1.moveToPrevious());
                cursor1.close();
            }
            Log.d("lh8", "getPbData totalTime = " + (System.currentTimeMillis() - startTime));
        }
        return pbList;
    }

    public void setBtCallback(TsBtCallback callback) {
        this.btCallback = callback;
    }

    public List<ITsSpeechBtPbInfo> getTsSpeechBtPbInfo() {
        List<ITsSpeechBtPbInfo> pbInfos = new ArrayList<>();
        if (mListPb != null && isConnected()) {
            Log.d(TAG, "*****GetPbMap***** size = " + mListPb.size());
            List<PbItem> lists = new ArrayList<>(mListPb);
            int size = lists.size();
            for (int i = 0; i < size; i++) {
                ITsSpeechBtPbInfo pbInfo = new ITsSpeechBtPbInfo();
                PbItem item = lists.get(i);
                if (item.name != null && !item.name.isEmpty() && item.num != null && !item.num.isEmpty()) {
                    pbInfo.setName(item.name);
                    pbInfo.setNum(item.num);
                    pbInfos.add(pbInfo);
                }
            }
        }
        return pbInfos;
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void sendSpeedPlayBroadcast(String command) {
        Log.d(TAG, "sendSpeedPlayBroadcast");
        if (mContext != null) {
            Intent intent = new Intent(AmapAuto.BROADCAST_SUDING_SPEEDPLAY);
            intent.putExtra("command", command);
            mContext.sendBroadcast(intent);
            return;
        }
        Log.d(TAG, "mContext is null");
    }

    public boolean isBluetoothCall(Call telecomCall) {
        PhoneAccountHandle phoneAccountHandle;
        if (telecomCall == null || (phoneAccountHandle = telecomCall.getDetails().getAccountHandle()) == null || phoneAccountHandle.getComponentName() == null) {
            return false;
        }
        return HFP_CLIENT_CONNECTION_SERVICE_CLASS_NAME.equals(phoneAccountHandle.getComponentName().getClassName());
    }
}
