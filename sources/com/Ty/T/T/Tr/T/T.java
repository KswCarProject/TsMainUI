package com.Ty.T.T.Tr.T;

import android.graphics.Bitmap;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/* compiled from: Proguard */
public class T implements com.Ty.T.T.Tr.T {

    /* renamed from: T  reason: collision with root package name */
    private final com.Ty.T.T.Tr.T f305T;
    private final Comparator<String> Tr;

    public T(com.Ty.T.T.Tr.T cache, Comparator<String> keyComparator) {
        this.f305T = cache;
        this.Tr = keyComparator;
    }

    public boolean T(String key, Bitmap value) {
        synchronized (this.f305T) {
            String keyToRemove = null;
            Iterator i$ = this.f305T.T().iterator();
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
                this.f305T.Tr(keyToRemove);
            }
        }
        return this.f305T.T(key, value);
    }

    public Bitmap T(String key) {
        return this.f305T.T(key);
    }

    public Bitmap Tr(String key) {
        return this.f305T.Tr(key);
    }

    public void Tr() {
        this.f305T.Tr();
    }

    public Collection<String> T() {
        return this.f305T.T();
    }
}
