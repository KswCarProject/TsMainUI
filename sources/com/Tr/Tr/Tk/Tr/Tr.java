package com.Tr.Tr.Tk.Tr;

import java.lang.reflect.Array;

/* compiled from: Proguard */
public final class Tr {

    /* renamed from: T  reason: collision with root package name */
    private final byte[][] f267T;
    private final int Tr;
    private final int Ty;

    public Tr(int width, int height) {
        this.f267T = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{height, width});
        this.Tr = width;
        this.Ty = height;
    }

    public int T() {
        return this.Ty;
    }

    public int Tr() {
        return this.Tr;
    }

    public byte T(int x, int y) {
        return this.f267T[y][x];
    }

    public byte[][] Ty() {
        return this.f267T;
    }

    public void T(int x, int y, int value) {
        this.f267T[y][x] = (byte) value;
    }

    public void T(int x, int y, boolean value) {
        this.f267T[y][x] = (byte) (value ? 1 : 0);
    }

    public void T(byte value) {
        for (int y = 0; y < this.Ty; y++) {
            for (int x = 0; x < this.Tr; x++) {
                this.f267T[y][x] = value;
            }
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder((this.Tr * 2 * this.Ty) + 2);
        for (int y = 0; y < this.Ty; y++) {
            for (int x = 0; x < this.Tr; x++) {
                switch (this.f267T[y][x]) {
                    case 0:
                        result.append(" 0");
                        break;
                    case 1:
                        result.append(" 1");
                        break;
                    default:
                        result.append("  ");
                        break;
                }
            }
            result.append(10);
        }
        return result.toString();
    }
}
