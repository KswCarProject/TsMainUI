package com.Tr.Tr.Tr.T;

/* compiled from: Proguard */
public final class T {

    /* renamed from: T  reason: collision with root package name */
    public static final T f286T = new T(4201, 4096, 1);
    public static final T T9 = new T(285, 256, 0);
    public static final T TE = Ty;
    public static final T TZ;
    public static final T Tk;
    public static final T Tn = new T(19, 16, 1);
    public static final T Tr = new T(1033, 1024, 1);
    public static final T Ty = new T(67, 64, 1);
    private final int[] T5;
    private final Tr T6;
    private final int TF;
    private final int Te;
    private final Tr Th;
    private final int Tq;
    private final int[] Tv;

    static {
        T t = new T(301, 256, 1);
        Tk = t;
        TZ = t;
    }

    public T(int primitive, int size, int b) {
        this.Tq = primitive;
        this.Te = size;
        this.TF = b;
        this.T5 = new int[size];
        this.Tv = new int[size];
        int x = 1;
        for (int i = 0; i < size; i++) {
            this.T5[i] = x;
            x <<= 1;
            if (x >= size) {
                x = (x ^ primitive) & (size - 1);
            }
        }
        for (int i2 = 0; i2 < size - 1; i2++) {
            this.Tv[this.T5[i2]] = i2;
        }
        this.Th = new Tr(this, new int[]{0});
        this.T6 = new Tr(this, new int[]{1});
    }

    /* access modifiers changed from: package-private */
    public Tr T() {
        return this.Th;
    }

    /* access modifiers changed from: package-private */
    public Tr T(int degree, int coefficient) {
        if (degree < 0) {
            throw new IllegalArgumentException();
        } else if (coefficient == 0) {
            return this.Th;
        } else {
            int[] coefficients = new int[(degree + 1)];
            coefficients[0] = coefficient;
            return new Tr(this, coefficients);
        }
    }

    static int Tr(int a, int b) {
        return a ^ b;
    }

    /* access modifiers changed from: package-private */
    public int T(int a) {
        return this.T5[a];
    }

    /* access modifiers changed from: package-private */
    public int Tr(int a) {
        if (a != 0) {
            return this.Tv[a];
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public int Ty(int a) {
        if (a != 0) {
            return this.T5[(this.Te - this.Tv[a]) - 1];
        }
        throw new ArithmeticException();
    }

    /* access modifiers changed from: package-private */
    public int Ty(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return this.T5[(this.Tv[a] + this.Tv[b]) % (this.Te - 1)];
    }

    public int Tr() {
        return this.TF;
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.Tq) + ',' + this.Te + ')';
    }
}
