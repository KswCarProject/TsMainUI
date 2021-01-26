package com.Tr.Tr.T.T;

import com.Tr.Tr.Tr.T;

/* compiled from: Proguard */
abstract class TZ {

    /* renamed from: T  reason: collision with root package name */
    static final TZ f248T = new T9((TZ) null, 0, 0);
    private final TZ Tr;

    /* access modifiers changed from: package-private */
    public abstract void T(T t, byte[] bArr);

    TZ(TZ previous) {
        this.Tr = previous;
    }

    /* access modifiers changed from: package-private */
    public final TZ T() {
        return this.Tr;
    }

    /* access modifiers changed from: package-private */
    public final TZ T(int value, int bitCount) {
        return new T9(this, value, bitCount);
    }

    /* access modifiers changed from: package-private */
    public final TZ Tr(int start, int byteCount) {
        return new Tr(this, start, byteCount);
    }
}
