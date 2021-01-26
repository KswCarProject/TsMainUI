package com.Ty.T.Tr.T;

/* compiled from: Proguard */
public class T9 {

    /* renamed from: T  reason: collision with root package name */
    private final int f315T;
    private final int Tr;

    public T9(int width, int height) {
        this.f315T = width;
        this.Tr = height;
    }

    public T9(int width, int height, int rotation) {
        if (rotation % 180 == 0) {
            this.f315T = width;
            this.Tr = height;
            return;
        }
        this.f315T = height;
        this.Tr = width;
    }

    public int T() {
        return this.f315T;
    }

    public int Tr() {
        return this.Tr;
    }

    public T9 T(int sampleSize) {
        return new T9(this.f315T / sampleSize, this.Tr / sampleSize);
    }

    public T9 T(float scale) {
        return new T9((int) (((float) this.f315T) * scale), (int) (((float) this.Tr) * scale));
    }

    public String toString() {
        return new StringBuilder(9).append(this.f315T).append("x").append(this.Tr).toString();
    }
}
