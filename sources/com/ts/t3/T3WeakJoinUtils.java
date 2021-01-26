package com.ts.t3;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.EthernetManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.ShellUtils;
import com.ts.main.common.tool;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.FtSet;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class T3WeakJoinUtils {
    private static final String ACTION_T3_GET_DEVICE_ID = "com.forfan.tsmqtt.GET_DEVICEID";
    private static final String ACTION_T3_SEND_DEVICE_ID = "com.forfan.tsmqtt.SEND_DEVICEID";
    private static final String BROADCAST_NET_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    private static final int G4_ETH_USB_PORT = 1;
    static NetworkInfo Net4GInfo = null;
    private static final String TAG = "T3Prj";
    public static boolean bIsT3WeakPrj = false;
    public static boolean binTestMode = false;
    /* access modifiers changed from: private */
    public static ConnectivityManager connectivityManager;
    /* access modifiers changed from: private */
    public static EthernetManager ethmanager;
    /* access modifiers changed from: private */
    public static TextView ivIccid;
    /* access modifiers changed from: private */
    public static TextView ivImei;
    private static Runnable mCarSpeedDetector = new Runnable() {
        public void run() {
            CanJni.T3FlGetCarSpeed(T3WeakJoinUtils.mT3FlSpeed);
            sendQXSpeedBroadcast((T3WeakJoinUtils.mT3FlSpeed.Val[0] * 255) + T3WeakJoinUtils.mT3FlSpeed.Val[1]);
            T3WeakJoinUtils.mHandler.postDelayed(this, 50);
        }

        private void sendQXSpeedBroadcast(int speed) {
            Intent intent = new Intent("com.android.internal.location.QX_SPD_MODE");
            intent.putExtra("qxtime", SystemClock.uptimeMillis());
            intent.putExtra("qxspeed", ((float) speed) / 3.6f);
            T3WeakJoinUtils.mContext.sendBroadcast(intent);
        }
    };
    public static Runnable mCmmni0Route = new Runnable() {
        public void run() {
            String cmnet = "ccmni0";
            try {
                Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
                while (true) {
                    if (!nis.hasMoreElements()) {
                        break;
                    }
                    NetworkInterface networkInterface = nis.nextElement();
                    if (networkInterface.getName().startsWith("ccmni")) {
                        Log.d(T3WeakJoinUtils.TAG, "interface = " + networkInterface.getName() + " DisplayName = " + networkInterface.getDisplayName());
                        Enumeration<InetAddress> ias = networkInterface.getInetAddresses();
                        if (ias.hasMoreElements()) {
                            Log.d(T3WeakJoinUtils.TAG, "ip addr == " + ias.nextElement().getHostAddress());
                            cmnet = networkInterface.getName();
                            break;
                        }
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
            Log.d(T3WeakJoinUtils.TAG, "config " + cmnet + " route");
            List<String> commands = new ArrayList<>();
            commands.add("iptables -t filter -F");
            commands.add("iptables -t nat -F");
            commands.add("iptables --flush");
            commands.add("iptables -t nat -A POSTROUTING -o " + cmnet + " -j MASQUERADE");
            commands.add("ip route del default dev " + cmnet + " table local");
            commands.add("ip route add default dev " + cmnet + " table local");
            ShellUtils.CommandResult result = ShellUtils.execCommand(commands, true);
            Log.d(T3WeakJoinUtils.TAG, "config " + cmnet + " commonds = " + commands.toString());
            if (result.result != 0) {
                Log.d(T3WeakJoinUtils.TAG, "T3 config " + cmnet + " commond result = error " + result.errorMsg);
            } else {
                Log.d(T3WeakJoinUtils.TAG, "T3 config " + cmnet + " commond result = success " + result.successMsg);
            }
        }
    };
    /* access modifiers changed from: private */
    public static Context mContext;
    private static BroadcastReceiver mDeviceMountedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.MEDIA_MOUNTED".equals(intent.getAction())) {
                final String diskPath = intent.getData().getPath();
                if (diskPath.contains("udisk")) {
                    MainUI.mBackgroundExecutor.execute(new Runnable() {
                        public void run() {
                            boolean unused = T3WeakJoinUtils.checkTestModeFile(diskPath);
                        }
                    });
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public static Handler mHandler;
    public static CanDataInfo.T3Fl_CanSpeed mT3FlSpeed = new CanDataInfo.T3Fl_CanSpeed();
    private static BroadcastReceiver mT3Receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            NetworkInfo info;
            String action = intent.getAction();
            if (T3WeakJoinUtils.ACTION_T3_GET_DEVICE_ID.equals(action)) {
                T3WeakJoinUtils.sendProductId(context);
            } else if ("android.net.conn.CONNECTIVITY_CHANGE".equalsIgnoreCase(action) && T3WeakJoinUtils.connectivityManager != null && (info = T3WeakJoinUtils.connectivityManager.getActiveNetworkInfo()) != null) {
                if (info.isConnected()) {
                    if (info.getType() != 0) {
                        return;
                    }
                    if (T3WeakJoinUtils.Net4GInfo == null || T3WeakJoinUtils.Net4GInfo.getType() != info.getType()) {
                        T3WeakJoinUtils.Net4GInfo = info;
                        MainUI.mBackgroundExecutor.execute(T3WeakJoinUtils.mCmmni0Route);
                    }
                } else if (T3WeakJoinUtils.Net4GInfo != null && T3WeakJoinUtils.Net4GInfo.getType() != info.getType()) {
                    T3WeakJoinUtils.Net4GInfo = null;
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public static AlertDialog productIdIputDlg;

    public static void sendProductId(Context context) {
        Intent deviceIntent = new Intent(ACTION_T3_SEND_DEVICE_ID);
        deviceIntent.putExtra("deviceId", MainSet.GetInstance().GetProid());
        context.sendBroadcast(deviceIntent);
    }

    private static void configT3Hardware() {
        MainUI.mBackgroundExecutor.execute(new Runnable() {
            public void run() {
                if (T3WeakJoinUtils.ethmanager != null) {
                    for (String eth : T3WeakJoinUtils.ethmanager.getAvailableInterfaces()) {
                        MainUI.mBackgroundExecutor.execute(new EthConfigRunnable(eth));
                    }
                }
                List<String> commands = new ArrayList<>();
                commands.add("echo \"1\" > /proc/sys/net/ipv4/ip_forward");
                NetworkInfo networkInfo = T3WeakJoinUtils.connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.getSubtype() == 0) {
                    commands.add("iptables -t filter -F");
                    commands.add("iptables -t nat -F");
                    commands.add("iptables --flush");
                    commands.add("iptables -t nat -A POSTROUTING -o ccmni0 -j MASQUERADE");
                    commands.add("ip route del default dev ccmni0 table local");
                    commands.add("ip route add default dev ccmni0 table local");
                }
                ShellUtils.CommandResult result = ShellUtils.execCommand(commands, true);
                if (result.result != 0) {
                    Log.d(T3WeakJoinUtils.TAG, "configT3Hardware commond result = error " + result.errorMsg);
                } else {
                    Log.d(T3WeakJoinUtils.TAG, "configT3Hardware commond result = success " + result.successMsg);
                }
            }
        });
    }

    public static void startT3IVIApp(Context context) {
        if (!checkProductId(MainSet.GetInstance().GetProid())) {
            inputProductId(context);
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(MainSet.TS_T3_PACKAGENAME, "com.forfan.tsmqtt.TsService"));
        context.startService(intent);
        registerEthListener();
    }

    /* access modifiers changed from: private */
    public static void startTestActivity(String disk) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.ts.MainUI", "com.ts.t3.T3TestActivity"));
        intent.addFlags(268435456);
        intent.putExtra("disk", disk);
        mContext.startActivity(intent);
    }

    private static int getEthUsbPort(String eth) {
        ShellUtils.CommandResult result = ShellUtils.execCommand("ls -l /sys/class/net|grep " + eth, false);
        if (result.result == 0) {
            String msg = result.successMsg.substring(0, result.successMsg.indexOf("/net/" + eth));
            String lastDeviceInfo = msg.substring(msg.lastIndexOf(47) + 1);
            String lastPortInfo = lastDeviceInfo.substring(0, lastDeviceInfo.indexOf(58));
            String lastPort = lastPortInfo.substring(lastPortInfo.lastIndexOf(46) + 1);
            Log.d(TAG, "last port == " + lastPort);
            try {
                return Integer.parseInt(lastPort);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    private static class EthConfigRunnable implements Runnable {
        private String ethport = null;

        public EthConfigRunnable(String port) {
            this.ethport = port;
        }

        public void run() {
            if (this.ethport != null && T3WeakJoinUtils.ethmanager != null && T3WeakJoinUtils.ethmanager.isAvailable(this.ethport)) {
                List<String> commands = new ArrayList<>();
                commands.add("ifconfig br0 down");
                commands.add("busybox brctl delbr br0");
                commands.add("busybox brctl addbr br0");
                commands.add("ifconfig br1 down");
                commands.add("busybox brctl delbr br1");
                commands.add("busybox brctl addbr br1");
                commands.add("busybox brctl addif br0 eth0");
                commands.add("ifconfig br0 192.168.100.2/24");
                commands.add("ifconfig br1 192.168.100.10/24");
                commands.add("ip route del 192.168.100.0/24 via 192.168.100.2 table local");
                commands.add("ip route add 192.168.100.0/24 via 192.168.100.2 table local");
                Log.d(T3WeakJoinUtils.TAG, "config G4 cmd = " + commands.toString());
                ShellUtils.CommandResult result = ShellUtils.execCommand(commands, true);
                Log.d(T3WeakJoinUtils.TAG, "execCommand = " + commands.toString());
                if (result.result != 0) {
                    Log.d(T3WeakJoinUtils.TAG, "T3 config " + this.ethport + " commond result = error " + result.errorMsg);
                } else {
                    Log.d(T3WeakJoinUtils.TAG, "T3 config " + this.ethport + " commond result = success " + result.successMsg);
                }
            }
        }
    }

    private static void registerEthListener() {
        ethmanager.addListener(new EthernetManager.Listener() {
            public void onAvailabilityChanged(String iface, boolean up) {
                Log.d(T3WeakJoinUtils.TAG, "interface == " + iface + "  state ==== " + up);
                if (up) {
                    MainUI.mBackgroundExecutor.execute(new EthConfigRunnable(iface));
                }
            }
        });
        connectivityManager.addDefaultNetworkActiveListener(new ConnectivityManager.OnNetworkActiveListener() {
            public void onNetworkActive() {
                int nType;
                NetworkInfo networkInfo = T3WeakJoinUtils.connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && (nType = networkInfo.getType()) != 1 && nType == 0) {
                    Log.d(T3WeakJoinUtils.TAG, "4G net connect " + networkInfo.getSubtype());
                    MainUI.mBackgroundExecutor.execute(T3WeakJoinUtils.mCmmni0Route);
                }
            }
        });
    }

    public static void inputProductId(final Context context) {
        ivImei = new TextView(context);
        ivImei.setGravity(17);
        ivIccid = new TextView(context);
        ivIccid.setGravity(17);
        LinearLayout mlayout = new LinearLayout(context);
        mlayout.setOrientation(1);
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(0);
        TextView tv = new TextView(context);
        tv.setText("IMEI:");
        tv.setGravity(17);
        tv.setTextSize(18.0f);
        layout.addView(tv, -2, 96);
        layout.addView(ivImei, 512, 96);
        mlayout.addView(layout, -2, 96);
        ImageView line = new ImageView(context);
        line.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        mlayout.addView(line, 512, 1);
        LinearLayout layout2 = new LinearLayout(context);
        TextView tv2 = new TextView(context);
        tv2.setText("ICCID:");
        tv2.setGravity(17);
        tv2.setTextSize(18.0f);
        layout2.addView(tv2, -2, 96);
        layout2.addView(ivIccid, 512, 96);
        mlayout.addView(layout2, -2, 96);
        ImageView line2 = new ImageView(context);
        line2.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        mlayout.addView(line2, 512, 1);
        final EditText edit = new EditText(context);
        edit.setHint("请输入设备Id");
        edit.requestFocus();
        edit.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString().contains(ShellUtils.COMMAND_LINE_END)) {
                    T3WeakJoinUtils.dealTextChange(context, edit);
                }
            }
        });
        mlayout.addView(edit, 512, 96);
        updateImei(context);
        updateIccid(context);
        productIdIputDlg = new AlertDialog.Builder(context).setTitle("生产设置").setView(mlayout).setPositiveButton(17039370, (DialogInterface.OnClickListener) null).create();
        productIdIputDlg.getWindow().setType(2003);
        productIdIputDlg.setCanceledOnTouchOutside(false);
        productIdIputDlg.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dlg) {
                Button btnPositive = T3WeakJoinUtils.productIdIputDlg.getButton(-1);
                final Context context = context;
                final EditText editText = edit;
                btnPositive.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        T3WeakJoinUtils.dealTextChange(context, editText);
                    }
                });
            }
        });
        productIdIputDlg.show();
    }

    /* access modifiers changed from: private */
    public static void dealTextChange(Context context, EditText edit) {
        if (productIdIputDlg != null) {
            String productId = edit.getText().toString().replace(ShellUtils.COMMAND_LINE_END, TXZResourceManager.STYLE_DEFAULT);
            if (checkProductId(productId)) {
                FtSet.SetProId(productId.getBytes(), productId.length());
                FtSet.Save(0);
                sendProductId(context);
                productIdIputDlg.dismiss();
                return;
            }
            edit.setHint("id错误,请重新输入");
            edit.setText(TXZResourceManager.STYLE_DEFAULT);
        }
    }

    /* access modifiers changed from: private */
    public static boolean checkTestModeFile(final String diskPath) {
        Log.d("wcb", "checkTestModeFile " + diskPath);
        File flagfile = new File(String.valueOf(diskPath) + "/t3_test_mode.txt");
        File mp3file = new File(String.valueOf(diskPath) + "/t3_test_sine.mp3");
        if (!flagfile.exists() || !mp3file.exists()) {
            if (flagfile.exists() && !mp3file.exists()) {
                mHandler.post(new Runnable() {
                    public void run() {
                        if (T3WeakJoinUtils.mContext != null) {
                            Toast.makeText(T3WeakJoinUtils.mContext, "测试音频丢失", 1).show();
                        }
                    }
                });
            }
            return false;
        }
        Log.d("wcb", "enter test mode !!!");
        binTestMode = true;
        mHandler.post(new Runnable() {
            public void run() {
                T3WeakJoinUtils.startTestActivity(diskPath);
            }
        });
        return true;
    }

    private static boolean checkProductId(String productId) {
        if (productId == null || productId.length() != 18) {
            return false;
        }
        return true;
    }

    public static void init(final Context context) {
        mHandler = new Handler(context.getMainLooper());
        ethmanager = (EthernetManager) context.getSystemService("ethernet");
        connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_T3_GET_DEVICE_ID);
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(mT3Receiver, filter);
        IntentFilter filter2 = new IntentFilter("android.intent.action.MEDIA_MOUNTED");
        filter2.addDataScheme("file");
        context.registerReceiver(mDeviceMountedReceiver, filter2);
        Log.d("wcb", "T3 init !!!");
        for (File file : new File("/storage").listFiles()) {
            Log.d("wcb", "list file name = " + file.getName());
            if (file.isDirectory() && file.getName().contains("udisk") && checkTestModeFile(file.getAbsolutePath())) {
                break;
            }
        }
        configT3Hardware();
        MainUI.mBackgroundExecutor.execute(new Runnable() {
            public void run() {
                String imei = tool.getIMEI(context);
                String iccid = tool.getICCID(context);
                while (true) {
                    if (imei == null || iccid == null) {
                        imei = T3WeakJoinUtils.updateImei(context);
                        iccid = T3WeakJoinUtils.updateIccid(context);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        return;
                    }
                }
            }
        });
        mContext = context;
        mHandler.postDelayed(mCarSpeedDetector, 50);
    }

    /* access modifiers changed from: private */
    public static String updateImei(Context context) {
        final String imei = tool.getIMEI(context);
        if (!(mHandler == null || imei == null || ivImei == null)) {
            final Bitmap bmp = tool.createBarCode(imei, 512, 76);
            mHandler.post(new Runnable() {
                public void run() {
                    T3WeakJoinUtils.ivImei.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, new BitmapDrawable(bmp), (Drawable) null, (Drawable) null);
                    T3WeakJoinUtils.ivImei.setText(imei);
                }
            });
        }
        return imei;
    }

    /* access modifiers changed from: private */
    public static String updateIccid(Context context) {
        final String iccid = tool.getICCID(context);
        if (!(iccid == null || ivIccid == null || mHandler == null)) {
            final Bitmap bmp = tool.createBarCode(iccid, 512, 76);
            mHandler.post(new Runnable() {
                public void run() {
                    T3WeakJoinUtils.ivIccid.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, new BitmapDrawable(bmp), (Drawable) null, (Drawable) null);
                    T3WeakJoinUtils.ivIccid.setText(iccid);
                }
            });
        }
        return iccid;
    }
}
