package com.Tr.Tr.Tk.T;

/* compiled from: Proguard */
public enum T {
    L(1),
    M(0),
    Q(3),
    H(2);
    
    private static final T[] T9 = null;
    private final int Tk;

    static {
        T9 = new T[]{M, L, H, Q};
    }

    private T(int bits) {
        this.Tk = bits;
    }

    public int T() {
        return this.Tk;
    }
}
