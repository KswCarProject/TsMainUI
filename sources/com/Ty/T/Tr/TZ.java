package com.Ty.T.Tr;

import com.Ty.T.Tr.T.T9;
import com.Ty.T.Tr.Tk.T;
import com.Ty.T.Tr.Tk.Tr;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: Proguard */
final class TZ {

    /* renamed from: T  reason: collision with root package name */
    final String f332T;
    final Ty T9;
    final ReentrantLock TE;
    final Tr TZ;
    final T Tk;
    final T9 Tn;
    final String Tr;
    final com.Ty.T.Tr.T9.T Ty;

    public TZ(String uri, com.Ty.T.Tr.T9.T imageAware, T9 targetSize, String memoryCacheKey, Ty options, T listener, Tr progressListener, ReentrantLock loadFromUriLock) {
        this.f332T = uri;
        this.Ty = imageAware;
        this.Tn = targetSize;
        this.T9 = options;
        this.Tk = listener;
        this.TZ = progressListener;
        this.TE = loadFromUriLock;
        this.Tr = memoryCacheKey;
    }
}
