package com.Ty.T.T.Tr.T;

import android.graphics.Bitmap;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/* compiled from: Proguard */
public class T implements com.Ty.T.T.Tr.T {

    /* renamed from: T  reason: collision with root package name */
    private final com.Ty.T.T.Tr.T f308T;
    private final Comparator<String> Tr;

    public T(com.Ty.T.T.Tr.T cache, Comparator<String> keyComparator) {
        this.f308T = cache;
        this.Tr = keyComparator;
    }

    public boolean T(String key, Bitmap value) {
        synchronized (this.f308T) {
            String keyToRemove = null;
            Iterator i$ = this.f308T.T().iterator();
            while (true) {
                if (!i$.hasNext()) {
                    break;
                }
                String cacheKey = i$.next();
                if (this.Tr.compare(key, cacheKey) == 0) {
                    keyToRemove = cacheKey;
                    break;
                }
            }
            if (keyToRemove != null) {
                this.f308T.Tr(keyToRemove);
            }
        }
        return this.f308T.T(key, value);
    }

    public Bitmap T(String key) {
        return this.f308T.T(key);
    }

    public Bitmap Tr(String key) {
        return this.f308T.Tr(key);
    }

    public void Tr() {
        this.f308T.Tr();
    }

    public Collection<String> T() {
        return this.f308T.T();
    }
}
