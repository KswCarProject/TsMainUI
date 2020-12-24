package net.easyconn.platform.wrc.core;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

/* compiled from: WrcUtil */
public class l {
    static boolean a(Context context) {
        BluetoothManager bluetoothManager;
        if (Build.VERSION.SDK_INT < 18 || !context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le") || (bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth")) == null || bluetoothManager.getAdapter() == null) {
            return false;
        }
        return true;
    }

    static boolean a() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    static void b() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && !defaultAdapter.isEnabled()) {
            defaultAdapter.enable();
        }
    }

    static void c() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.isEnabled()) {
            defaultAdapter.disable();
        }
    }

    static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    static String c(Context context) {
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

    static UUID a(byte[] bArr) {
        byte b;
        try {
            ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            while (order.remaining() > 2 && (b = order.get()) != 0) {
                int i = (byte) (b - 1);
                switch (order.get()) {
                    case -1:
                        i = (byte) (i - 2);
                        order.getShort();
                        break;
                    case 1:
                        i = (byte) (i - 1);
                        order.get();
                        break;
                    case 2:
                    case 3:
                    case 20:
                        if (i >= 2) {
                            return UUID.fromString(String.format("%08x-0000-1000-8000-00805f9b34fb", new Object[]{Short.valueOf(order.getShort())}));
                        }
                        break;
                    case 4:
                    case 5:
                        if (i >= 4) {
                            return UUID.fromString(String.format("%08x-0000-1000-8000-00805f9b34fb", new Object[]{Integer.valueOf(order.getInt())}));
                        }
                        break;
                    case 6:
                    case 7:
                    case 21:
                        if (i >= 16) {
                            return new UUID(order.getLong(), order.getLong());
                        }
                        break;
                    case 8:
                    case 9:
                        order.get(new byte[i], 0, i);
                        i = 0;
                        break;
                    case 10:
                        i = (byte) (i - 1);
                        break;
                }
                if (i > 0) {
                    order.position(i + order.position());
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }
}
