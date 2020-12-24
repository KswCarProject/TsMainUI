package com.Tr.Tr.T9.T;

import java.lang.reflect.Array;

/* compiled from: Proguard */
public final class T {

    /* renamed from: T  reason: collision with root package name */
    private final Tr[] f251T;
    private final int Tn;
    private int Tr;
    private final int Ty;

    T(int height, int width) {
        this.f251T = new Tr[height];
        int matrixLength = this.f251T.length;
        for (int i = 0; i < matrixLength; i++) {
            this.f251T[i] = new Tr(((width + 4) * 17) + 1);
        }
        this.Tn = width * 17;
        this.Ty = height;
        this.Tr = -1;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        this.Tr++;
    }

    /* access modifiers changed from: package-private */
    public Tr Tr() {
        return this.f251T[this.Tr];
    }

    public byte[][] T(int xScale, int yScale) {
        byte[][] matrixOut = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.Ty * yScale, this.Tn * xScale});
        int yMax = this.Ty * yScale;
        for (int i = 0; i < yMax; i++) {
            matrixOut[(yMax - i) - 1] = this.f251T[i / yScale].T(xScale);
        }
        return matrixOut;
    }
}
