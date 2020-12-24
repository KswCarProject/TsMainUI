package net.easyconn.platform.wrc.core;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.easyconn.platform.wrc.core.WrcManager;
import net.easyconn.platform.wrc.core.a.c;
import net.easyconn.platform.wrc.core.b.a;
import net.easyconn.platform.wrc.core.b.b;
import net.easyconn.platform.wrc.core.g;

/* compiled from: WrcOperator */
class j {
    private static final boolean a = (Build.VERSION.SDK_INT >= 21);
    private static j d;
    /* access modifiers changed from: private */
    public Map<String, UUID> b;
    /* access modifiers changed from: private */
    public Handler c;
    /* access modifiers changed from: private */
    public Context e;
    private b f;
    /* access modifiers changed from: private */
    public boolean g;
    /* access modifiers changed from: private */
    public WrcDevice h;
    /* access modifiers changed from: private */
    public b i;
    /* access modifiers changed from: private */
    public a j;
    /* access modifiers changed from: private */
    public g k;
    /* access modifiers changed from: private */
    public WrcManager.ScanCallback l;
    /* access modifiers changed from: private */
    public WrcManager.WrcCallback m;
    private WrcManager.OtaCallback n;
    private c o = new c() {
        public List<UUID> getUuidFilter() {
            if (j.this.l != null) {
                return j.this.l.getUuidFilter();
            }
            return null;
        }

        public void a(final WrcDevice wrcDevice) {
            if (j.this.c != null) {
                j.this.c.post(new Runnable() {
                    public void run() {
                        if (j.this.l != null) {
                            j.this.l.onWrcScan(wrcDevice);
                        }
                    }
                });
            }
            if (j.this.b != null && wrcDevice != null) {
                j.this.b.put(wrcDevice.getAddress(), wrcDevice.getUuid());
            }
        }

        public void a(final int i) {
            if (j.this.c != null) {
                j.this.c.post(new Runnable() {
                    public void run() {
                        if (j.this.l != null) {
                            j.this.l.onScanError(i);
                        }
                    }
                });
            }
        }
    };
    private net.easyconn.platform.wrc.core.a.a p = new net.easyconn.platform.wrc.core.a.a() {
        public void a(final WrcDevice wrcDevice) {
            WrcDevice unused = j.this.h = wrcDevice;
            if (j.this.e != null) {
                SharedPreferences.Editor edit = j.this.e.getSharedPreferences("net.easyconn.platform.wrc", 0).edit();
                edit.putString("wrc.key.device", wrcDevice.getAddress());
                edit.commit();
                edit.apply();
            }
            if (j.this.i != null) {
                j.this.i.b();
            }
            if (j.this.c != null) {
                j.this.c.post(new Runnable() {
                    public void run() {
                        if (j.this.m != null) {
                            j.this.m.onConnected(wrcDevice);
                        }
                    }
                });
            }
            boolean unused2 = j.this.g = false;
            if (j.this.k != null && wrcDevice != null) {
                String address = wrcDevice.getAddress();
                if (!TextUtils.isEmpty(address) && j.this.b != null) {
                    j.this.k.a(address, (UUID) j.this.b.get(address));
                }
            }
        }

        public void b(final WrcDevice wrcDevice) {
            WrcDevice unused = j.this.h = null;
            if (j.this.c != null) {
                j.this.c.post(new Runnable() {
                    public void run() {
                        if (j.this.m != null) {
                            j.this.m.onDisconnected(wrcDevice);
                        }
                    }
                });
            }
            boolean unused2 = j.this.g = false;
            if (j.this.k != null) {
                j.this.k.d();
            }
        }

        public void a(final byte b, final byte b2) {
            if (j.this.c != null) {
                j.this.c.post(new Runnable() {
                    public void run() {
                        if (j.this.m != null) {
                            j.this.m.onWrcKeyEvent(b, b2);
                        }
                    }
                });
            }
        }

        public void a(UUID uuid, byte[] bArr) {
            if (j.this.h == null) {
                return;
            }
            if (a.d.contains(uuid)) {
                j.this.h.setFirmware(new String(bArr));
            } else if (a.e.contains(uuid)) {
                j.this.h.setHardware(new String(bArr));
            } else if (a.f.contains(uuid)) {
                j.this.h.setSoftware(new String(bArr));
            }
        }

        public void a() {
            if (j.this.c != null) {
                j.this.c.post(new Runnable() {
                    public void run() {
                        if (j.this.m != null && j.this.h != null) {
                            j.this.m.onCharacteristicRead(j.this.h);
                        }
                    }
                });
            }
        }

        public void a(final int i) {
            if (j.this.c != null) {
                j.this.c.post(new Runnable() {
                    public void run() {
                        if (j.this.m != null) {
                            j.this.m.onError(i);
                        }
                    }
                });
            }
            boolean unused = j.this.g = false;
        }

        public void b(int i) {
            WrcDevice unused = j.this.h = null;
            if (j.this.j != null) {
                j.this.j.b();
            }
            boolean unused2 = j.this.g = false;
        }
    };
    private net.easyconn.platform.wrc.core.a.b q = new net.easyconn.platform.wrc.core.a.b() {
    };
    private g.b r = new g.b() {
        public void a(final int i) {
            if (j.this.c != null) {
                j.this.c.post(new Runnable() {
                    public void run() {
                        if (j.this.f()) {
                            if (j.this.m != null) {
                                j.this.m.onError(i);
                            }
                            if (j.this.j != null) {
                                j.this.j.b();
                            }
                        }
                    }
                });
            }
        }
    };

    private j() {
        if (this.c == null) {
            this.c = new Handler(Looper.getMainLooper());
        }
        if (this.b == null) {
            this.b = new HashMap();
        }
    }

    static j a() {
        if (d == null) {
            synchronized (j.class) {
                if (d == null) {
                    d = new j();
                }
            }
        }
        return d;
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, String str, String str2) {
        this.e = context;
        j();
        this.k = g.a();
        this.k.a(this.e, str, str2, this.r);
    }

    /* access modifiers changed from: package-private */
    public boolean a(Context context) {
        return l.a(context);
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return l.a();
    }

    /* access modifiers changed from: package-private */
    public void c() {
        l.b();
    }

    /* access modifiers changed from: package-private */
    public void d() {
        l.c();
    }

    /* access modifiers changed from: package-private */
    public void e() {
        if (this.i != null) {
            this.i.b();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return this.h != null;
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public void h() {
        if (this.j != null) {
            this.j.b();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(WrcManager.ScanCallback scanCallback) {
        this.l = scanCallback;
        if (scanCallback != null) {
            this.k.a(scanCallback.getUuidFilter());
        }
        if (this.b != null) {
            this.b.clear();
        }
        if (this.h != null) {
            return;
        }
        if (a(this.e)) {
            if (this.i == null) {
                if (a) {
                    this.i = new e();
                } else {
                    this.i = new f();
                }
            }
            this.i.a(this.o);
        } else if (scanCallback != null) {
            scanCallback.onScanError(1);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(WrcDevice wrcDevice, WrcManager.WrcCallback wrcCallback) {
        this.m = wrcCallback;
        if (this.h != null) {
            wrcCallback.onError(4);
        } else if (!this.g) {
            this.g = true;
            if (this.j == null) {
                this.j = h.a();
            }
            this.j.a(this.e, wrcDevice, this.p);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, WrcManager.OtaCallback otaCallback) {
        this.n = otaCallback;
    }

    /* access modifiers changed from: package-private */
    public synchronized void i() {
        if (this.c != null) {
            this.c.removeCallbacksAndMessages((Object) null);
        }
        if (this.e != null) {
            k();
        }
        if (this.i != null) {
            this.i.c();
            this.i = null;
        }
        if (this.j != null) {
            this.j.c();
            this.j = null;
        }
        if (this.k != null) {
            this.k.b();
        }
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.g = false;
        this.h = null;
        if (this.b != null) {
            this.b.clear();
            this.b = null;
        }
        d = null;
    }

    private synchronized void j() {
        if (this.f == null) {
            this.f = new b(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            this.e.registerReceiver(this.f, intentFilter);
        }
    }

    private void k() {
        if (this.f != null) {
            this.e.unregisterReceiver(this.f);
        }
    }
}
