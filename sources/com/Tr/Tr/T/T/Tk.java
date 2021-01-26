package com.Tr.Tr.T.T;

import com.Tr.Tr.Tr.T;
import java.util.Deque;
import java.util.LinkedList;

/* compiled from: Proguard */
final class Tk {

    /* renamed from: T  reason: collision with root package name */
    static final Tk f249T = new Tk(TZ.f248T, 0, 0, 0);
    private final int T9;
    private final int Tn;
    private final int Tr;
    private final TZ Ty;

    private Tk(TZ token, int mode, int binaryBytes, int bitCount) {
        this.Ty = token;
        this.Tr = mode;
        this.Tn = binaryBytes;
        this.T9 = bitCount;
    }

    /* access modifiers changed from: package-private */
    public int T() {
        return this.Tr;
    }

    /* access modifiers changed from: package-private */
    public int Tr() {
        return this.Tn;
    }

    /* access modifiers changed from: package-private */
    public int Ty() {
        return this.T9;
    }

    /* access modifiers changed from: package-private */
    public Tk T(int mode, int value) {
        int bitCount = this.T9;
        TZ token = this.Ty;
        if (mode != this.Tr) {
            int latch = Tn.Tr[this.Tr][mode];
            token = token.T(65535 & latch, latch >> 16);
            bitCount += latch >> 16;
        }
        int latchModeBitCount = mode == 2 ? 4 : 5;
        return new Tk(token.T(value, latchModeBitCount), mode, 0, bitCount + latchModeBitCount);
    }

    /* access modifiers changed from: package-private */
    public Tk Tr(int mode, int value) {
        int thisModeBitCount;
        TZ token = this.Ty;
        if (this.Tr == 2) {
            thisModeBitCount = 4;
        } else {
            thisModeBitCount = 5;
        }
        return new Tk(token.T(Tn.Ty[this.Tr][mode], thisModeBitCount).T(value, 5), this.Tr, 0, this.T9 + thisModeBitCount + 5);
    }

    /* access modifiers changed from: package-private */
    public Tk T(int index) {
        TZ token = this.Ty;
        int mode = this.Tr;
        int bitCount = this.T9;
        if (this.Tr == 4 || this.Tr == 2) {
            int latch = Tn.Tr[mode][0];
            token = token.T(65535 & latch, latch >> 16);
            bitCount += latch >> 16;
            mode = 0;
        }
        Tk result = new Tk(token, mode, this.Tn + 1, bitCount + ((this.Tn == 0 || this.Tn == 31) ? 18 : this.Tn == 62 ? 9 : 8));
        if (result.Tn == 2078) {
            return result.Tr(index + 1);
        }
        return result;
    }

    /* Debug info: failed to restart local var, previous not found, register: 5 */
    /* access modifiers changed from: package-private */
    public Tk Tr(int index) {
        return this.Tn == 0 ? this : new Tk(this.Ty.Tr(index - this.Tn, this.Tn), this.Tr, 0, this.T9);
    }

    /* access modifiers changed from: package-private */
    public boolean T(Tk other) {
        int mySize = this.T9 + (Tn.Tr[this.Tr][other.Tr] >> 16);
        if (other.Tn > 0 && (this.Tn == 0 || this.Tn > other.Tn)) {
            mySize += 10;
        }
        return mySize <= other.T9;
    }

    /* access modifiers changed from: package-private */
    public T T(byte[] text) {
        Deque<TZ> symbols = new LinkedList<>();
        for (TZ token = Tr(text.length).Ty; token != null; token = token.T()) {
            symbols.addFirst(token);
        }
        T bitArray = new T();
        for (TZ T2 : symbols) {
            T2.T(bitArray, text);
        }
        return bitArray;
    }

    public String toString() {
        return String.format("%s bits=%d bytes=%d", new Object[]{Tn.f250T[this.Tr], Integer.valueOf(this.T9), Integer.valueOf(this.Tn)});
    }
}
