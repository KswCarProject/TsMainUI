package com.Tr.T.T;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/* compiled from: Proguard */
public final class Tr {

    /* renamed from: T  reason: collision with root package name */
    private final byte[] f240T;
    private final int Tr;
    private int Ty;

    private Tr(byte[] bArr, int i, int i2) {
        this.f240T = bArr;
        this.Ty = i;
        this.Tr = i + i2;
    }

    public static Tr T(byte[] bArr, int i, int i2) {
        return new Tr(bArr, i, i2);
    }

    public void T(int i, double d) throws IOException {
        T9(i, 1);
        T(d);
    }

    public void T(int i, float f) throws IOException {
        T9(i, 5);
        T(f);
    }

    public void T(int i, long j) throws IOException {
        T9(i, 0);
        T(j);
    }

    public void T(int i, int i2) throws IOException {
        T9(i, 0);
        T(i2);
    }

    public void T(int i, boolean z) throws IOException {
        T9(i, 0);
        T(z);
    }

    public void T(int i, String str) throws IOException {
        T9(i, 2);
        T(str);
    }

    public void T(int i, T9 t9) throws IOException {
        T9(i, 2);
        T(t9);
    }

    public void T(int i, byte[] bArr) throws IOException {
        T9(i, 2);
        T(bArr);
    }

    public void Tr(int i, int i2) throws IOException {
        T9(i, 0);
        Tr(i2);
    }

    public void T(double d) throws IOException {
        T9(Double.doubleToLongBits(d));
    }

    public void T(float f) throws IOException {
        T5(Float.floatToIntBits(f));
    }

    public void T(long j) throws IOException {
        Ty(j);
    }

    public void T(int i) throws IOException {
        if (i >= 0) {
            TZ(i);
        } else {
            Ty((long) i);
        }
    }

    public void T(boolean z) throws IOException {
        T9(z ? 1 : 0);
    }

    public void T(String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        TZ(bytes.length);
        Ty(bytes);
    }

    public void T(T9 t9) throws IOException {
        TZ(t9.T());
        t9.T(this);
    }

    public void T(byte[] bArr) throws IOException {
        TZ(bArr.length);
        Ty(bArr);
    }

    public void Tr(int i) throws IOException {
        TZ(i);
    }

    public static int Tr(int i, double d) {
        return Tk(i) + Tr(d);
    }

    public static int Tr(int i, float f) {
        return Tk(i) + Tr(f);
    }

    public static int Tr(int i, long j) {
        return Tk(i) + Tr(j);
    }

    public static int Ty(int i, int i2) {
        return Tk(i) + Ty(i2);
    }

    public static int Tr(int i, boolean z) {
        return Tk(i) + Tr(z);
    }

    public static int Tr(int i, String str) {
        return Tk(i) + Tr(str);
    }

    public static int Tr(int i, T9 t9) {
        return Tk(i) + Tr(t9);
    }

    public static int Tr(int i, byte[] bArr) {
        return Tk(i) + Tr(bArr);
    }

    public static int Tn(int i, int i2) {
        return Tk(i) + Tn(i2);
    }

    public static int Tr(double d) {
        return 8;
    }

    public static int Tr(float f) {
        return 4;
    }

    public static int Tr(long j) {
        return Tn(j);
    }

    public static int Ty(int i) {
        if (i >= 0) {
            return TE(i);
        }
        return 10;
    }

    public static int Tr(boolean z) {
        return 1;
    }

    public static int Tr(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return bytes.length + TE(bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    public static int Tr(T9 t9) {
        int Tr2 = t9.Tr();
        return Tr2 + TE(Tr2);
    }

    public static int Tr(byte[] bArr) {
        return TE(bArr.length) + bArr.length;
    }

    public static int Tn(int i) {
        return TE(i);
    }

    public int T() {
        return this.Tr - this.Ty;
    }

    public void Tr() {
        if (T() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* compiled from: Proguard */
    public static class T extends IOException {
        T(int i, int i2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + i + " limit " + i2 + ").");
        }
    }

    public void T(byte b) throws IOException {
        if (this.Ty == this.Tr) {
            throw new T(this.Ty, this.Tr);
        }
        byte[] bArr = this.f240T;
        int i = this.Ty;
        this.Ty = i + 1;
        bArr[i] = b;
    }

    public void T9(int i) throws IOException {
        T((byte) i);
    }

    public void Ty(byte[] bArr) throws IOException {
        Tr(bArr, 0, bArr.length);
    }

    public void Tr(byte[] bArr, int i, int i2) throws IOException {
        if (this.Tr - this.Ty >= i2) {
            System.arraycopy(bArr, i, this.f240T, this.Ty, i2);
            this.Ty += i2;
            return;
        }
        throw new T(this.Ty, this.Tr);
    }

    public void T9(int i, int i2) throws IOException {
        TZ(TZ.T(i, i2));
    }

    public static int Tk(int i) {
        return TE(TZ.T(i, 0));
    }

    public void TZ(int i) throws IOException {
        while ((i & -128) != 0) {
            T9((i & 127) | 128);
            i >>>= 7;
        }
        T9(i);
    }

    public static int TE(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        if ((-268435456 & i) == 0) {
            return 4;
        }
        return 5;
    }

    public void Ty(long j) throws IOException {
        while ((-128 & j) != 0) {
            T9((((int) j) & 127) | 128);
            j >>>= 7;
        }
        T9((int) j);
    }

    public static int Tn(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        if ((Long.MIN_VALUE & j) == 0) {
            return 9;
        }
        return 10;
    }

    public void T5(int i) throws IOException {
        T9(i & 255);
        T9((i >> 8) & 255);
        T9((i >> 16) & 255);
        T9((i >> 24) & 255);
    }

    public void T9(long j) throws IOException {
        T9(((int) j) & 255);
        T9(((int) (j >> 8)) & 255);
        T9(((int) (j >> 16)) & 255);
        T9(((int) (j >> 24)) & 255);
        T9(((int) (j >> 32)) & 255);
        T9(((int) (j >> 40)) & 255);
        T9(((int) (j >> 48)) & 255);
        T9(((int) (j >> 56)) & 255);
    }
}
