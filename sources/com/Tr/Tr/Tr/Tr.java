package com.Tr.Tr.Tr;

import com.ts.main.common.ShellUtils;
import java.util.Arrays;

/* compiled from: Proguard */
public final class Tr implements Cloneable {

    /* renamed from: T  reason: collision with root package name */
    private final int f289T;
    private final int[] Tn;
    private final int Tr;
    private final int Ty;

    public Tr(int dimension) {
        this(dimension, dimension);
    }

    public Tr(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.f289T = width;
        this.Tr = height;
        this.Ty = (width + 31) / 32;
        this.Tn = new int[(this.Ty * height)];
    }

    private Tr(int width, int height, int rowSize, int[] bits) {
        this.f289T = width;
        this.Tr = height;
        this.Ty = rowSize;
        this.Tn = bits;
    }

    public boolean T(int x, int y) {
        return ((this.Tn[(this.Ty * y) + (x / 32)] >>> (x & 31)) & 1) != 0;
    }

    public void Tr(int x, int y) {
        int offset = (this.Ty * y) + (x / 32);
        int[] iArr = this.Tn;
        iArr[offset] = iArr[offset] | (1 << (x & 31));
    }

    public void T() {
        int max = this.Tn.length;
        for (int i = 0; i < max; i++) {
            this.Tn[i] = 0;
        }
    }

    public void T(int left, int top, int width, int height) {
        if (top < 0 || left < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        } else if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        } else {
            int right = left + width;
            int bottom = top + height;
            if (bottom > this.Tr || right > this.f289T) {
                throw new IllegalArgumentException("The region must fit inside the matrix");
            }
            for (int y = top; y < bottom; y++) {
                int offset = y * this.Ty;
                for (int x = left; x < right; x++) {
                    int[] iArr = this.Tn;
                    int i = (x / 32) + offset;
                    iArr[i] = iArr[i] | (1 << (x & 31));
                }
            }
        }
    }

    public int Tr() {
        return this.f289T;
    }

    public int Ty() {
        return this.Tr;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Tr)) {
            return false;
        }
        Tr other = (Tr) o;
        if (this.f289T == other.f289T && this.Tr == other.Tr && this.Ty == other.Ty && Arrays.equals(this.Tn, other.Tn)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.f289T * 31) + this.f289T) * 31) + this.Tr) * 31) + this.Ty) * 31) + Arrays.hashCode(this.Tn);
    }

    public String toString() {
        return T("X ", "  ");
    }

    public String T(String setString, String unsetString) {
        return T(setString, unsetString, ShellUtils.COMMAND_LINE_END);
    }

    private String T(String setString, String unsetString, String lineSeparator) {
        String str;
        StringBuilder result = new StringBuilder(this.Tr * (this.f289T + 1));
        for (int y = 0; y < this.Tr; y++) {
            for (int x = 0; x < this.f289T; x++) {
                if (T(x, y)) {
                    str = setString;
                } else {
                    str = unsetString;
                }
                result.append(str);
            }
            result.append(lineSeparator);
        }
        return result.toString();
    }

    /* renamed from: Tn */
    public Tr clone() {
        return new Tr(this.f289T, this.Tr, this.Ty, (int[]) this.Tn.clone());
    }
}
