package com.Ty.T.Tr;

import com.Ty.T.Tr.T9.T;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: Proguard */
class Tk {

    /* renamed from: T  reason: collision with root package name */
    final T9 f333T;
    private final AtomicBoolean T5 = new AtomicBoolean(false);
    private final Map<Integer, String> T9 = Collections.synchronizedMap(new HashMap());
    private final AtomicBoolean TE = new AtomicBoolean(false);
    private final AtomicBoolean TZ = new AtomicBoolean(false);
    private final Map<String, ReentrantLock> Tk = new WeakHashMap();
    private Executor Tn;
    /* access modifiers changed from: private */
    public Executor Tr;
    private final Object Tv = new Object();
    /* access modifiers changed from: private */
    public Executor Ty;

    Tk(T9 configuration) {
        this.f333T = configuration;
        this.Tr = configuration.TZ;
        this.Ty = configuration.TE;
        this.Tn = T.T();
    }

    /* access modifiers changed from: package-private */
    public void T(final TE task) {
        this.Tn.execute(new Runnable() {
            public void run() {
                File image = Tk.this.f333T.TF.T(task.T());
                boolean isImageCachedOnDisk = image != null && image.exists();
                Tk.this.T9();
                if (isImageCachedOnDisk) {
                    Tk.this.Ty.execute(task);
                } else {
                    Tk.this.Tr.execute(task);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void T(T5 task) {
        T9();
        this.Ty.execute(task);
    }

    /* access modifiers changed from: private */
    public void T9() {
        if (!this.f333T.T5 && ((ExecutorService) this.Tr).isShutdown()) {
            this.Tr = Tk();
        }
        if (!this.f333T.Tv && ((ExecutorService) this.Ty).isShutdown()) {
            this.Ty = Tk();
        }
    }

    private Executor Tk() {
        return T.T(this.f333T.Th, this.f333T.T6, this.f333T.Te);
    }

    /* access modifiers changed from: package-private */
    public String T(T imageAware) {
        return this.T9.get(Integer.valueOf(imageAware.Tk()));
    }

    /* access modifiers changed from: package-private */
    public void T(T imageAware, String memoryCacheKey) {
        this.T9.put(Integer.valueOf(imageAware.Tk()), memoryCacheKey);
    }

    /* access modifiers changed from: package-private */
    public void Tr(T imageAware) {
        this.T9.remove(Integer.valueOf(imageAware.Tk()));
    }

    /* access modifiers changed from: package-private */
    public void T(Runnable r) {
        this.Tn.execute(r);
    }

    /* access modifiers changed from: package-private */
    public ReentrantLock T(String uri) {
        ReentrantLock lock = this.Tk.get(uri);
        if (lock != null) {
            return lock;
        }
        ReentrantLock lock2 = new ReentrantLock();
        this.Tk.put(uri, lock2);
        return lock2;
    }

    /* access modifiers changed from: package-private */
    public AtomicBoolean T() {
        return this.TZ;
    }

    /* access modifiers changed from: package-private */
    public Object Tr() {
        return this.Tv;
    }

    /* access modifiers changed from: package-private */
    public boolean Ty() {
        return this.TE.get();
    }

    /* access modifiers changed from: package-private */
    public boolean Tn() {
        return this.T5.get();
    }
}
