package com.Tr.Tr.T9.T;

/* compiled from: Proguard */
final class Tr {

    /* renamed from: T  reason: collision with root package name */
    private final byte[] f259T;
    private int Tr = 0;

    Tr(int width) {
        this.f259T = new byte[width];
    }

    private void T(int x, boolean black) {
        this.f259T[x] = (byte) (black ? 1 : 0);
    }

    /* access modifiers changed from: package-private */
    public void T(boolean black, int width) {
        for (int ii = 0; ii < width; ii++) {
            int i = this.Tr;
            this.Tr = i + 1;
            T(i, black);
        }
    }

    /* access modifiers changed from: package-private */
    public byte[] T(int scale) {
        byte[] output = new byte[(this.f259T.length * scale)];
        for (int i = 0; i < output.length; i++) {
            output[i] = this.f259T[i / scale];
        }
        return output;
    }
}
