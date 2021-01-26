package com.txznet.txz.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: Proguard */
public class T5 {

    /* renamed from: T  reason: collision with root package name */
    private static T f894T = null;
    public static boolean T9 = true;
    protected static List<WeakReference<T5>> Tr = new ArrayList();
    protected static long Ty = SystemClock.elapsedRealtime();
    private long TE = 0;
    private Handler TZ;
    private Thread Tk;
    public AtomicInteger Tn = new AtomicInteger(0);

    /* compiled from: Proguard */
    public interface T {
    }

    public T5(Looper looper) {
        this.Tk = looper.getThread();
        this.TZ = new Handler(looper) {
            public void handleMessage(Message msg) {
                T5.this.T();
                T5.this.T(msg);
                T5.this.Tr();
            }
        };
        synchronized (Tr) {
            Tr.add(new WeakReference(this));
        }
    }

    public boolean T(Runnable r, long delayMillis) {
        this.Tn.getAndIncrement();
        this.TZ.postDelayed(new Runnable() {
            public void run() {
                T5.this.T();
            }
        }, delayMillis);
        boolean ret = this.TZ.postDelayed(r, delayMillis);
        this.TZ.postDelayed(new Runnable() {
            public void run() {
                T5.this.Tn.getAndDecrement();
                T5.this.Tr();
            }
        }, delayMillis);
        return ret;
    }

    public boolean T(Runnable r) {
        this.Tn.getAndIncrement();
        this.TZ.post(new Runnable() {
            public void run() {
                T5.this.T();
            }
        });
        boolean ret = this.TZ.post(r);
        this.TZ.post(new Runnable() {
            public void run() {
                T5.this.Tn.getAndDecrement();
                T5.this.Tr();
            }
        });
        return ret;
    }

    public void T(Message msg) {
    }

    public void Tr(Runnable r) {
        this.TZ.removeCallbacks(r);
    }

    public void T() {
        this.TE = SystemClock.elapsedRealtime();
    }

    public void Tr() {
        this.TE = 0;
    }
}
