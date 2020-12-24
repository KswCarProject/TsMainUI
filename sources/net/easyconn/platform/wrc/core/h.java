package net.easyconn.platform.wrc.core;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import net.easyconn.platform.wrc.core.b.a;

@SuppressLint({"NewApi"})
/* compiled from: WrcConnectHelper */
public class h implements a {
    private static final String g = h.class.getSimpleName();
    private static h h;
    /* access modifiers changed from: private */
    public BluetoothGatt i;
    /* access modifiers changed from: private */
    public net.easyconn.platform.wrc.core.a.a j;
    /* access modifiers changed from: private */
    public Handler k = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public LinkedList<BluetoothGattCharacteristic> l = new LinkedList<>();
    /* access modifiers changed from: private */
    public BluetoothGattCallback m = new BluetoothGattCallback() {
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            if (h.this.j != null && h.this.i != null && bluetoothGatt != null) {
                if (i == 0) {
                    WrcDevice wrcDevice = new WrcDevice();
                    wrcDevice.setName(bluetoothGatt.getDevice().getName());
                    wrcDevice.setAddress(bluetoothGatt.getDevice().getAddress());
                    if (i2 == 2) {
                        h.this.k.postDelayed(new Runnable() {
                            public void run() {
                                if (h.this.i == null) {
                                    if (h.this.j != null) {
                                        h.this.j.b(5);
                                    }
                                } else if (h.this.i != null) {
                                    h.this.i.discoverServices();
                                }
                            }
                        }, 600);
                    } else if (i2 == 0 && h.this.j != null) {
                        h.this.j.b(wrcDevice);
                    }
                } else if (h.this.j != null) {
                    h.this.j.b(i);
                }
            }
        }

        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            if (h.this.j != null && h.this.i != null && bluetoothGatt != null) {
                if (i == 0) {
                    h.this.a(bluetoothGatt.getServices());
                    if (h.this.n != null && h.this.a(true)) {
                        WrcDevice wrcDevice = new WrcDevice();
                        wrcDevice.setName(bluetoothGatt.getDevice().getName());
                        wrcDevice.setAddress(bluetoothGatt.getDevice().getAddress());
                        if (h.this.j != null) {
                            h.this.j.a(wrcDevice);
                        }
                        h.this.d();
                    }
                } else if (h.this.j != null) {
                    h.this.j.b(i);
                }
            }
        }

        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            if (h.this.j != null && a.b.contains(bluetoothGattCharacteristic.getUuid())) {
                byte[] value = bluetoothGattCharacteristic.getValue();
                if (h.this.j != null) {
                    h.this.j.a(value[1], value[0]);
                }
            }
        }

        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (i == 0) {
                if (h.this.j != null) {
                    h.this.j.a(bluetoothGattCharacteristic.getUuid(), bluetoothGattCharacteristic.getValue());
                }
                if (h.this.l.isEmpty()) {
                    h.this.j.a();
                } else if (h.this.i != null) {
                    h.this.i.readCharacteristic((BluetoothGattCharacteristic) h.this.l.remove());
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public BluetoothGattCharacteristic n;

    private h() {
    }

    static h a() {
        if (h == null) {
            synchronized (h.class) {
                if (h == null) {
                    h = new h();
                }
            }
        }
        return h;
    }

    public synchronized void a(final Context context, WrcDevice wrcDevice, net.easyconn.platform.wrc.core.a.a aVar) {
        if (l.a()) {
            this.j = aVar;
            final BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(wrcDevice.getAddress());
            if (remoteDevice != null) {
                this.k.post(new Runnable() {
                    public void run() {
                        if (h.this.i != null) {
                            h.this.i.close();
                        }
                        BluetoothGatt unused = h.this.i = remoteDevice.connectGatt(context, false, h.this.m);
                    }
                });
            } else if (aVar != null) {
                aVar.a(3);
            }
        } else if (aVar != null) {
            aVar.b(2);
        }
    }

    public void b() {
        if (this.i != null && l.a()) {
            this.i.disconnect();
            e();
        }
    }

    public void c() {
        b();
        if (this.i != null) {
            this.i.close();
        }
        this.j = null;
        this.i = null;
        this.m = null;
        if (this.k != null) {
            this.k.removeCallbacksAndMessages((Object) null);
        }
        h = null;
    }

    /* access modifiers changed from: private */
    public void a(List<BluetoothGattService> list) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic;
        BluetoothGattCharacteristic bluetoothGattCharacteristic2;
        BluetoothGattCharacteristic bluetoothGattCharacteristic3 = null;
        BluetoothGattCharacteristic bluetoothGattCharacteristic4 = null;
        BluetoothGattCharacteristic bluetoothGattCharacteristic5 = null;
        for (BluetoothGattService next : list) {
            UUID uuid = next.getUuid();
            List<BluetoothGattCharacteristic> characteristics = next.getCharacteristics();
            if (a.contains(uuid)) {
                for (BluetoothGattCharacteristic next2 : characteristics) {
                    if (b.contains(next2.getUuid())) {
                        this.n = next2;
                    }
                }
            } else if (c.contains(uuid)) {
                for (BluetoothGattCharacteristic next3 : characteristics) {
                    if (d.contains(next3.getUuid())) {
                        BluetoothGattCharacteristic bluetoothGattCharacteristic6 = bluetoothGattCharacteristic3;
                        bluetoothGattCharacteristic = bluetoothGattCharacteristic4;
                        bluetoothGattCharacteristic2 = next3;
                        next3 = bluetoothGattCharacteristic6;
                    } else if (e.contains(next3.getUuid())) {
                        bluetoothGattCharacteristic2 = bluetoothGattCharacteristic5;
                        BluetoothGattCharacteristic bluetoothGattCharacteristic7 = next3;
                        next3 = bluetoothGattCharacteristic3;
                        bluetoothGattCharacteristic = bluetoothGattCharacteristic7;
                    } else if (f.contains(next3.getUuid())) {
                        bluetoothGattCharacteristic = bluetoothGattCharacteristic4;
                        bluetoothGattCharacteristic2 = bluetoothGattCharacteristic5;
                    } else {
                        next3 = bluetoothGattCharacteristic3;
                        bluetoothGattCharacteristic = bluetoothGattCharacteristic4;
                        bluetoothGattCharacteristic2 = bluetoothGattCharacteristic5;
                    }
                    bluetoothGattCharacteristic5 = bluetoothGattCharacteristic2;
                    bluetoothGattCharacteristic4 = bluetoothGattCharacteristic;
                    bluetoothGattCharacteristic3 = next3;
                }
            }
        }
        this.l.clear();
        if (bluetoothGattCharacteristic5 != null) {
            this.l.add(bluetoothGattCharacteristic5);
        }
        if (bluetoothGattCharacteristic4 != null) {
            this.l.add(bluetoothGattCharacteristic4);
        }
        if (bluetoothGattCharacteristic3 != null) {
            this.l.add(bluetoothGattCharacteristic3);
        }
    }

    public boolean a(boolean z) {
        try {
            boolean characteristicNotification = this.i.setCharacteristicNotification(this.n, z);
            BluetoothGattDescriptor descriptor = this.n.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
            boolean value = descriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
            if (!this.i.writeDescriptor(descriptor) || !value || !characteristicNotification) {
                return false;
            }
            return true;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (!this.l.isEmpty()) {
            this.k.postDelayed(new Runnable() {
                public void run() {
                    if (h.this.i != null) {
                        h.this.i.readCharacteristic((BluetoothGattCharacteristic) h.this.l.remove());
                    }
                }
            }, 200);
        }
    }

    private void e() {
        try {
            Method method = this.i.getClass().getMethod("refresh", new Class[0]);
            if (method != null) {
                method.setAccessible(true);
                method.invoke(this.i, (Object[]) null);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }
}
