package com.Tr.Tr.Tr;

import java.util.Arrays;

/* compiled from: Proguard */
public final class T implements Cloneable {

    /* renamed from: T  reason: collision with root package name */
    private int[] f285T;
    private int Tr;

    public T() {
        this.Tr = 0;
        this.f285T = new int[1];
    }

    T(int[] bits, int size) {
        this.f285T = bits;
        this.Tr = size;
    }

    public int T() {
        return this.Tr;
    }

    public int Tr() {
        return (this.Tr + 7) / 8;
    }

    private void Tr(int size) {
        if (size > (this.f285T.length << 5)) {
            int[] newBits = Ty(size);
            System.arraycopy(this.f285T, 0, newBits, 0, this.f285T.length);
            this.f285T = newBits;
        }
    }

    public boolean T(int i) {
        return (this.f285T[i / 32] & (1 << (i & 31))) != 0;
    }

    public void T(boolean bit) {
        Tr(this.Tr + 1);
        if (bit) {
            int[] iArr = this.f285T;
            int i = this.Tr / 32;
            iArr[i] = iArr[i] | (1 << (this.Tr & 31));
        }
        this.Tr++;
    }

    public void T(int value, int numBits) {
        if (numBits < 0 || numBits > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        Tr(this.Tr + numBits);
        for (int numBitsLeft = numBits; numBitsLeft > 0; numBitsLeft--) {
            T(((value >> (numBitsLeft + -1)) & 1) == 1);
        }
    }

    public void T(T other) {
        int otherSize = other.Tr;
        Tr(this.Tr + otherSize);
        for (int i = 0; i < otherSize; i++) {
            T(other.T(i));
        }
    }

    public void Tr(T other) {
        if (this.Tr != other.Tr) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        for (int i = 0; i < this.f285T.length; i++) {
            int[] iArr = this.f285T;
            iArr[i] = iArr[i] ^ other.f285T[i];
        }
    }

    public void T(int bitOffset, byte[] array, int offset, int numBytes) {
        for (int i = 0; i < numBytes; i++) {
            int theByte = 0;
            for (int j = 0; j < 8; j++) {
                if (T(bitOffset)) {
                    theByte |= 1 << (7 - j);
                }
                bitOffset++;
            }
            array[offset + i] = (byte) theByte;
        }
    }

    private static int[] Ty(int size) {
        return new int[((size + 31) / 32)];
    }

    public boolean equals(Object o) {
        if (!(o instanceof T)) {
            return false;
        }
        T other = (T) o;
        if (this.Tr != other.Tr || !Arrays.equals(this.f285T, other.f285T)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.Tr * 31) + Arrays.hashCode(this.f285T);
    }

    public String toString() {
        StringBuilder result = new StringBuilder(this.Tr);
        for (int i = 0; i < this.Tr; i++) {
            if ((i & 7) == 0) {
                result.append(' ');
            }
            result.append(T(i) ? 'X' : '.');
        }
        return result.toString();
    }

    /* renamed from: Ty */
    public T clone() {
        return new T((int[]) this.f285T.clone(), this.Tr);
    }
}
