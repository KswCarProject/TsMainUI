package com.Ty.T.Tr.T;

/* compiled from: Proguard */
public class T9 {

    /* renamed from: T  reason: collision with root package name */
    private final int f312T;
    private final int Tr;

    public T9(int width, int height) {
        this.f312T = width;
        this.Tr = height;
    }

    public T9(int width, int height, int rotation) {
        if (rotation % 180 == 0) {
            this.f312T = width;
            this.Tr = height;
            return;
        }
        this.f312T = height;
        this.Tr = width;
    }

    public int T() {
        return this.f312T;
    }

    public int Tr() {
        return this.Tr;
    }

    public T9 T(int sampleSize) {
        return new T9(this.f312T / sampleSize, this.Tr / sampleSize);
    }

    public T9 T(float scale) {
        return new T9((int) (((float) this.f312T) * scale), (int) (((float) this.Tr) * scale));
    }

    public String toString() {
        return new StringBuilder(9).append(this.f312T).append("x").append(this.Tr).toString();
    }
}
