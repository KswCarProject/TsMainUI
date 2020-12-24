package com.Tr.T.T;

import java.io.IOException;

/* compiled from: Proguard */
public abstract class T9 {

    /* renamed from: T  reason: collision with root package name */
    protected volatile int f238T = -1;

    public abstract T9 T(T t) throws IOException;

    public int T() {
        if (this.f238T < 0) {
            Tr();
        }
        return this.f238T;
    }

    public int Tr() {
        int Ty = Ty();
        this.f238T = Ty;
        return Ty;
    }

    /* access modifiers changed from: protected */
    public int Ty() {
        return 0;
    }

    public void T(Tr tr) throws IOException {
    }

    public static final byte[] T(T9 t9) {
        byte[] bArr = new byte[t9.Tr()];
        T(t9, bArr, 0, bArr.length);
        return bArr;
    }

    public static final void T(T9 t9, byte[] bArr, int i, int i2) {
        try {
            Tr T2 = Tr.T(bArr, i, i2);
            t9.T(T2);
            T2.Tr();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final <T extends T9> T T(T t, byte[] bArr) throws Tn {
        return Tr(t, bArr, 0, bArr.length);
    }

    public static final <T extends T9> T Tr(T t, byte[] bArr, int i, int i2) throws Tn {
        try {
            T T2 = T.T(bArr, i, i2);
            t.T(T2);
            T2.T(0);
            return t;
        } catch (Tn e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public String toString() {
        return Tk.T(this);
    }
}
