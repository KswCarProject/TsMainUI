package net.easyconn.platform.wrc.core;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;
import com.ts.main.common.MainUI;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WrcManager {
    private static final int APP_KEY_DEFAULT_MSG = 0;
    private static final boolean APP_KEY_DEFAULT_VALUE = true;
    public static final int CONNECT_MESSAGE_DEVICE_IS_CONNECTED = 4;
    public static final int ERROR_BLUETOOTH_TOGGLE_OFF = 2;
    public static final int ERROR_CONNECT_DEVICE_IS_NULL = 3;
    public static final int ERROR_WRC_NOT_SUPPORT = 1;
    public static final int GATT_NULL = 5;
    private static final String GET_MSG_CODE = "code";
    private static final int MSG_CHECKING_KEY = 1;
    private static final int MSG_KEY_CORRECT = 0;
    private static final int MSG_KEY_INCORRECT = 9002;
    private static final int MSG_KEY_NULL = 9004;
    private static final int MSG_NO_NETWORK = 2;
    private static final int MSG_PACKAGE_INCORRECT = 9001;
    private static final int MSG_PACKAGE_NULL = 9003;
    private static final String WRC_KEY_DEVICE = "wrc.key.device";
    private static final String WRC_KEY_VALUE = "wrc.key.value";
    private static final String WRC_NULL_DEVICE_MAC = "";
    private static final String WRC_SHOW_TEXT_CHECK_KEY = "正在验证方控key。。。";
    private static final String WRC_SHOW_TEXT_CHECK_KEY_SUS = "验证成功";
    private static final String WRC_SHOW_TEXT_ILLEGAL_KEY = "非法key，请联系客服";
    private static final String WRC_SHOW_TEXT_NO_NETWORK = "请联网验证方控key！";
    static final String WRC_STORE_FILE = "net.easyconn.platform.wrc";
    /* access modifiers changed from: private */
    public static Context mContext;
    private static AlertDialog mErrorAlertDialog;
    private static WrcManager sInstance;
    private NetWorkChangeReceiver a = null;
    private MyHandle b = null;
    /* access modifiers changed from: private */
    public boolean c = true;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public String f;
    /* access modifiers changed from: private */
    public String g;
    /* access modifiers changed from: private */
    public j h = null;

    public interface OtaCallback {
        void onOtaError(int i);

        void onOtaProgressUpdate(int i, int i2, int i3);

        void onOtaStateUpdate(int i);
    }

    public interface ScanCallback extends d {
        List<UUID> getUuidFilter();

        void onScanError(int i);

        void onWrcScan(WrcDevice wrcDevice);
    }

    public interface WrcCallback {
        public static final byte ACTION_LONG_PRESSED = -93;
        public static final byte ACTION_SINGLE_CLICK = -95;
        public static final byte KEY_CENTER = 16;
        public static final byte KEY_LEFT_DOWN = 8;
        public static final byte KEY_LEFT_UP = 4;
        public static final byte KEY_RIGHT_DOWN = 2;
        public static final byte KEY_RIGHT_UP = 1;
        public static final byte MINI_KEY_1 = 33;
        public static final byte MINI_KEY_2 = 34;
        public static final byte MINI_KEY_3 = 36;

        void onCharacteristicRead(WrcDevice wrcDevice);

        void onConnected(WrcDevice wrcDevice);

        void onDisconnected(WrcDevice wrcDevice);

        void onError(int i);

        void onWrcKeyEvent(byte b, byte b2);
    }

    private WrcManager() {
    }

    public static WrcManager getInstance() {
        if (sInstance == null) {
            synchronized (WrcManager.class) {
                if (sInstance == null) {
                    sInstance = new WrcManager();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context, String str, String str2) {
        if (context != null && this.b == null) {
            if (this.b == null) {
                synchronized (WrcManager.class) {
                    if (this.b == null) {
                        this.b = new MyHandle(context);
                    }
                }
            }
            if (TextUtils.isEmpty(str)) {
                a(9001);
                return;
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences(WRC_STORE_FILE, 0);
            mContext = context;
            if (!a(sharedPreferences)) {
                this.d = a(context);
                this.c = sharedPreferences.getBoolean(WRC_KEY_VALUE, true);
                this.e = sharedPreferences.getString(WRC_KEY_DEVICE, "");
                this.f = str;
                this.g = str2;
                this.a = new NetWorkChangeReceiver();
                mContext.registerReceiver(this.a, new IntentFilter(MainUI.BROADCAST_NET_CHANGE));
                if (!this.c) {
                    if (b(mContext)) {
                        a(1);
                        return;
                    } else {
                        a(2);
                        return;
                    }
                }
            }
            this.h = j.a();
            this.h.a(context, str, str2);
        }
    }

    public boolean isWrcSupport(Context context) {
        if (this.h != null) {
            return this.h.a(context);
        }
        return false;
    }

    public boolean isBluetoothEnabled() {
        if (this.h != null) {
            return this.h.b();
        }
        return false;
    }

    public void openBluetooth() {
        if (this.h != null) {
            this.h.c();
        }
    }

    public void closeBluetooth() {
        if (this.h != null) {
            this.h.d();
        }
    }

    public boolean isConnectWrc() {
        if (this.h != null) {
            return this.h.f();
        }
        return false;
    }

    public boolean isOnConnecting() {
        if (this.h != null) {
            return this.h.g();
        }
        return false;
    }

    public void disconnect() {
        if (this.h != null) {
            this.h.h();
        }
    }

    public void destroy() {
        if (this.a != null) {
            mContext.unregisterReceiver(this.a);
            this.a = null;
        }
        if (this.b != null) {
            this.b.removeCallbacksAndMessages((Object) null);
            this.b = null;
        }
        if (this.h != null) {
            this.h.i();
            this.h = null;
        }
        mContext = null;
        sInstance = null;
    }

    public void startWrcScan(final ScanCallback scanCallback) {
        if (this.b != null) {
            this.b.postDelayed(new Runnable() {
                public void run() {
                    if (WrcManager.this.h != null && scanCallback != null && WrcManager.this.c) {
                        WrcManager.this.h.a(scanCallback);
                    }
                }
            }, 500);
        }
    }

    public void stopWrcScan() {
        if (this.h != null) {
            this.h.e();
        }
    }

    public void connectWrc(final WrcDevice wrcDevice, final WrcCallback wrcCallback) {
        if (this.b != null) {
            this.b.postDelayed(new Runnable() {
                public void run() {
                    if (wrcDevice == null) {
                        wrcCallback.onError(3);
                    } else if (WrcManager.this.h != null && wrcCallback != null && WrcManager.this.c) {
                        WrcManager.this.h.a(wrcDevice, wrcCallback);
                    }
                }
            }, 500);
        }
    }

    public void startWrcOta(String str, OtaCallback otaCallback) {
        if (!TextUtils.isEmpty(str) && otaCallback != null && this.c && this.h != null) {
            this.h.a(str, otaCallback);
        }
    }

    private String a(Context context) {
        try {
            String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (TextUtils.isEmpty(deviceId)) {
                return Settings.Secure.getString(context.getContentResolver(), "android_id");
            }
            return deviceId;
        } catch (Throwable th) {
            return "";
        }
    }

    private boolean b(Context context) {
        boolean z;
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return false;
                }
                z = activeNetworkInfo.isAvailable();
            } catch (Throwable th) {
                z = false;
            }
        } else {
            z = false;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, String str3, String str4) {
        if (b(mContext)) {
            if (this.a != null) {
                mContext.unregisterReceiver(this.a);
                this.a = null;
            }
            final String str5 = str;
            final String str6 = str2;
            final String str7 = str3;
            final String str8 = str4;
            new Thread() {
                /* JADX WARNING: Can't fix incorrect switch cases order */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r6 = this;
                        r2 = 1
                        r1 = 0
                        java.lang.String r0 = r2
                        java.lang.String r3 = r3
                        java.lang.String r4 = r4
                        java.lang.String r5 = r5
                        java.lang.String r0 = net.easyconn.platform.wrc.core.c.a(r0, r3, r4, r5)
                        boolean r3 = android.text.TextUtils.isEmpty(r0)
                        if (r3 != 0) goto L_0x00be
                        org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00a8 }
                        r3.<init>(r0)     // Catch:{ JSONException -> 0x00a8 }
                        java.lang.String r0 = "code"
                        java.lang.String r0 = r3.getString(r0)     // Catch:{ JSONException -> 0x00a8 }
                        if (r0 == 0) goto L_0x00bb
                        int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ JSONException -> 0x00a8 }
                        switch(r1) {
                            case 0: goto L_0x00a2;
                            case 9001: goto L_0x00b2;
                            case 9002: goto L_0x00af;
                            case 9003: goto L_0x00b8;
                            case 9004: goto L_0x00b5;
                            default: goto L_0x0028;
                        }
                    L_0x0028:
                        r0 = r1
                    L_0x0029:
                        net.easyconn.platform.wrc.core.WrcManager r3 = net.easyconn.platform.wrc.core.WrcManager.this
                        boolean r3 = r3.c
                        if (r2 == r3) goto L_0x0084
                        android.content.Context r3 = net.easyconn.platform.wrc.core.WrcManager.mContext
                        if (r3 == 0) goto L_0x0084
                        net.easyconn.platform.wrc.core.WrcManager r3 = net.easyconn.platform.wrc.core.WrcManager.this
                        boolean unused = r3.c = r2
                        android.content.Context r3 = net.easyconn.platform.wrc.core.WrcManager.mContext
                        java.lang.String r4 = "net.easyconn.platform.wrc"
                        android.content.SharedPreferences r1 = r3.getSharedPreferences(r4, r1)
                        android.content.SharedPreferences$Editor r1 = r1.edit()
                        java.lang.String r3 = "wrc.key.value"
                        r1.putBoolean(r3, r2)
                        r1.commit()
                        r1.apply()
                        net.easyconn.platform.wrc.core.WrcManager r1 = net.easyconn.platform.wrc.core.WrcManager.this
                        boolean r1 = r1.c
                        if (r1 == 0) goto L_0x0084
                        net.easyconn.platform.wrc.core.WrcManager r1 = net.easyconn.platform.wrc.core.WrcManager.this
                        net.easyconn.platform.wrc.core.j r2 = net.easyconn.platform.wrc.core.j.a()
                        net.easyconn.platform.wrc.core.j unused = r1.h = r2
                        net.easyconn.platform.wrc.core.WrcManager r1 = net.easyconn.platform.wrc.core.WrcManager.this
                        net.easyconn.platform.wrc.core.j r1 = r1.h
                        android.content.Context r2 = net.easyconn.platform.wrc.core.WrcManager.mContext
                        net.easyconn.platform.wrc.core.WrcManager r3 = net.easyconn.platform.wrc.core.WrcManager.this
                        java.lang.String r3 = r3.f
                        net.easyconn.platform.wrc.core.WrcManager r4 = net.easyconn.platform.wrc.core.WrcManager.this
                        java.lang.String r4 = r4.g
                        r1.a(r2, r3, r4)
                        net.easyconn.platform.wrc.core.WrcManager r1 = net.easyconn.platform.wrc.core.WrcManager.this
                        r1.a((int) r0)
                    L_0x0084:
                        net.easyconn.platform.wrc.core.WrcManager r1 = net.easyconn.platform.wrc.core.WrcManager.this
                        boolean r1 = r1.c
                        if (r1 != 0) goto L_0x00a1
                        android.content.Context r1 = net.easyconn.platform.wrc.core.WrcManager.mContext
                        if (r1 == 0) goto L_0x00a1
                        net.easyconn.platform.wrc.core.WrcManager r1 = net.easyconn.platform.wrc.core.WrcManager.this
                        r1.disconnect()
                        net.easyconn.platform.wrc.core.WrcManager r1 = net.easyconn.platform.wrc.core.WrcManager.this
                        r1.stopWrcScan()
                        net.easyconn.platform.wrc.core.WrcManager r1 = net.easyconn.platform.wrc.core.WrcManager.this
                        r1.a((int) r0)
                    L_0x00a1:
                        return
                    L_0x00a2:
                        net.easyconn.platform.wrc.core.WrcManager r3 = net.easyconn.platform.wrc.core.WrcManager.this     // Catch:{ JSONException -> 0x00a8 }
                        r3.b()     // Catch:{ JSONException -> 0x00a8 }
                        goto L_0x0029
                    L_0x00a8:
                        r0 = move-exception
                        r0.printStackTrace()
                        r0 = r1
                        goto L_0x0029
                    L_0x00af:
                        r2 = r1
                        goto L_0x0029
                    L_0x00b2:
                        r2 = r1
                        goto L_0x0029
                    L_0x00b5:
                        r2 = r1
                        goto L_0x0029
                    L_0x00b8:
                        r2 = r1
                        goto L_0x0029
                    L_0x00bb:
                        r0 = r1
                        goto L_0x0029
                    L_0x00be:
                        r0 = r1
                        goto L_0x0029
                    */
                    throw new UnsupportedOperationException("Method not decompiled: net.easyconn.platform.wrc.core.WrcManager.AnonymousClass3.run():void");
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        if (this.b != null) {
            this.b.sendEmptyMessage(i);
        }
    }

    private static class MyHandle extends Handler {
        WeakReference<Context> a;

        MyHandle(Context context) {
            super(Looper.getMainLooper());
            this.a = new WeakReference<>(context);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            Context context = (Context) this.a.get();
            if (context != null) {
                switch (message.what) {
                    case 0:
                        Toast.makeText(context, WrcManager.WRC_SHOW_TEXT_CHECK_KEY_SUS, 1).show();
                        return;
                    case 1:
                        Toast.makeText(context, WrcManager.WRC_SHOW_TEXT_CHECK_KEY, 1).show();
                        return;
                    case 2:
                        Toast.makeText(context, WrcManager.WRC_SHOW_TEXT_NO_NETWORK, 1).show();
                        return;
                    case 9001:
                        Toast.makeText(context, WrcManager.WRC_SHOW_TEXT_ILLEGAL_KEY, 1).show();
                        return;
                    case 9002:
                        Toast.makeText(context, WrcManager.WRC_SHOW_TEXT_ILLEGAL_KEY, 1).show();
                        return;
                    case 9003:
                        Toast.makeText(context, WrcManager.WRC_SHOW_TEXT_ILLEGAL_KEY, 1).show();
                        return;
                    case 9004:
                        Toast.makeText(context, WrcManager.WRC_SHOW_TEXT_ILLEGAL_KEY, 1).show();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private class NetWorkChangeReceiver extends BroadcastReceiver {
        private NetWorkChangeReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MainUI.BROADCAST_NET_CHANGE)) {
                try {
                    if (((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() != null) {
                        WrcManager.this.a(WrcManager.mContext.getPackageName(), WrcManager.this.f, WrcManager.this.e, WrcManager.this.d);
                    }
                } catch (Throwable th) {
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        Date date = new Date();
        Date date2 = new Date(date.getYear(), date.getMonth(), date.getDate());
        SharedPreferences.Editor edit = mContext.getSharedPreferences(WRC_STORE_FILE, 0).edit();
        edit.putString("checkdate", date2.toString());
        edit.commit();
        edit.apply();
    }

    private boolean a(SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString("checkdate", "");
        Date date = new Date();
        return new Date(date.getYear(), date.getMonth(), date.getDate()).toString().equals(string);
    }
}
