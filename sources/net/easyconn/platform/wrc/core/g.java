package net.easyconn.platform.wrc.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.ts.main.common.MainUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: WrcCheckManager */
public class g {
    private static g k;
    /* access modifiers changed from: private */
    public Context a;
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public String c;
    private BroadcastReceiver d;
    /* access modifiers changed from: private */
    public b e;
    /* access modifiers changed from: private */
    public List<UUID> f;
    private List<UUID> g;
    /* access modifiers changed from: private */
    public long h;
    private ExecutorService i = Executors.newSingleThreadExecutor(new ThreadFactory() {
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "check");
        }
    });
    /* access modifiers changed from: private */
    public Semaphore j = new Semaphore(1);

    /* compiled from: WrcCheckManager */
    public interface b {
        void a(int i);
    }

    private g() {
    }

    static g a() {
        if (k == null) {
            synchronized (g.class) {
                if (k == null) {
                    k = new g();
                }
            }
        }
        return k;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        g();
        this.a = null;
        this.e = null;
        if (this.i != null) {
            this.i.shutdown();
            this.i = null;
        }
    }

    /* access modifiers changed from: package-private */
    public List<UUID> c() {
        if ((this.f == null || this.f.isEmpty()) && this.g != null && !this.g.isEmpty()) {
            return this.g;
        }
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, String str, String str2, b bVar) {
        this.a = context;
        this.b = str;
        this.c = str2;
        this.e = bVar;
        this.f = i.a().a(context);
        this.h = this.a.getSharedPreferences("net.easyconn.platform.wrc", 0).getLong("time", -1);
    }

    /* access modifiers changed from: package-private */
    public void a(List<UUID> list) {
        this.g = list;
        e();
    }

    /* access modifiers changed from: package-private */
    public void d() {
    }

    /* access modifiers changed from: package-private */
    public void a(String str, UUID uuid) {
        if (this.a != null) {
            if (this.f != null && !this.f.isEmpty() && this.g != null && !this.g.isEmpty()) {
                for (UUID contains : this.g) {
                    if (!this.f.contains(contains)) {
                        this.e.a(ICode.ERROR_UUID_CHECK);
                    }
                }
            }
            a(str, uuid, 0);
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (l.b(this.a)) {
            g();
            if (this.i != null) {
                this.i.execute(new Runnable() {
                    public void run() {
                        int i = 0;
                        try {
                            g.this.j.acquire();
                            HashMap hashMap = new HashMap(5);
                            hashMap.put("0yO16=5iJnx8TwLL", g.this.c);
                            hashMap.put("0Y0i6/0y4vx8WMvn", g.this.a.getPackageName());
                            hashMap.put("WzWEe/CnuELL", g.this.b);
                            hashMap.put("TMxN6M3n", l.c(g.this.a));
                            hashMap.put("J/2d04LL", "");
                            String a2 = c.a("J/2d0TxM4zOc4uBCJz5c4N+b0/x8", hashMap);
                            if (!TextUtils.isEmpty(a2)) {
                                try {
                                    JSONObject jSONObject = new JSONObject(a2);
                                    int optInt = jSONObject.optInt("code", -1);
                                    if (optInt == 0) {
                                        JSONObject jSONObject2 = jSONObject.getJSONObject("context");
                                        if (jSONObject2 != null) {
                                            JSONArray jSONArray = jSONObject2.getJSONArray("uuids");
                                            if (jSONArray != null && jSONArray.length() > 0) {
                                                ArrayList arrayList = new ArrayList();
                                                while (true) {
                                                    int i2 = i;
                                                    if (i2 >= jSONArray.length()) {
                                                        break;
                                                    }
                                                    try {
                                                        arrayList.add(UUID.fromString(jSONArray.getString(i2)));
                                                    } catch (IllegalArgumentException e) {
                                                        e.printStackTrace();
                                                    } catch (NullPointerException e2) {
                                                        e2.printStackTrace();
                                                    }
                                                    i = i2 + 1;
                                                }
                                                i.a().a(g.this.a, arrayList);
                                                List unused = g.this.f = arrayList;
                                            }
                                            long j = jSONObject2.getLong("last_update_time");
                                            SharedPreferences sharedPreferences = g.this.a.getSharedPreferences("net.easyconn.platform.wrc", 0);
                                            if (g.this.h == -1) {
                                                sharedPreferences.edit().putLong("time", j).apply();
                                            } else if (j > g.this.h) {
                                                sharedPreferences.edit().putLong("time", j).apply();
                                                if (g.this.e != null) {
                                                    g.this.e.a(ICode.ERROR_UUID_UPDATE);
                                                }
                                            }
                                            long unused2 = g.this.h = j;
                                            if ((g.this.f == null || g.this.f.isEmpty()) && g.this.e != null) {
                                                g.this.e.a(ICode.ERROR_UUID_NO_BACKUP);
                                            }
                                        }
                                    } else if (g.this.e != null) {
                                        g.this.e.a(optInt);
                                    }
                                } catch (JSONException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        } catch (InterruptedException e4) {
                            e4.printStackTrace();
                        } finally {
                            g.this.j.release();
                        }
                    }
                });
                return;
            }
            return;
        }
        f();
    }

    private void a(final String str, final UUID uuid, final int i2) {
        if (l.b(this.a) && !TextUtils.isEmpty(str) && this.i != null) {
            this.i.execute(new Runnable() {
                public void run() {
                    try {
                        g.this.j.acquire();
                        HashMap hashMap = new HashMap(5);
                        hashMap.put("0yO16=5iJnx8TwLL", g.this.c);
                        hashMap.put("0Y0i6/0y4vx8WMvn", g.this.a.getPackageName());
                        hashMap.put("WzWEe/CnuELL", g.this.b);
                        hashMap.put("TMxN6M3n", l.c(g.this.a));
                        hashMap.put("J/2d04LL", str);
                        hashMap.put("WM2m6Mx8", Integer.valueOf(i2));
                        if (uuid != null) {
                            hashMap.put("Jz5c4RLL", uuid.toString());
                        }
                        c.a("J/2d0Txz0=rC0=5ETUOmF=cdT/pL", hashMap);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        g.this.j.release();
                    }
                }
            });
        }
    }

    private void f() {
        if (this.a != null && this.d == null) {
            this.d = new a();
            this.a.registerReceiver(this.d, new IntentFilter(MainUI.BROADCAST_NET_CHANGE));
        }
    }

    private void g() {
        if (this.a != null && this.d != null) {
            this.a.unregisterReceiver(this.d);
            this.d = null;
        }
    }

    /* compiled from: WrcCheckManager */
    private class a extends BroadcastReceiver {
        private a() {
        }

        public void onReceive(Context context, Intent intent) {
            NetworkInfo activeNetworkInfo;
            synchronized (a.class) {
                if (intent.getAction().equalsIgnoreCase(MainUI.BROADCAST_NET_CHANGE) && (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
                    g.this.e();
                }
            }
        }
    }
}
