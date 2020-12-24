package com.Tr.Tr.T.T;

import com.Tr.Tr.Tr.T;

/* compiled from: Proguard */
final class T9 extends TZ {
    private final short Tr;
    private final short Ty;

    T9(TZ previous, int value, int bitCount) {
        super(previous);
        this.Tr = (short) value;
        this.Ty = (short) bitCount;
    }

    /* access modifiers changed from: package-private */
    public void T(T bitArray, byte[] text) {
        bitArray.T(this.Tr, this.Ty);
    }

    public String toString() {
        return "<" + Integer.toBinaryString((1 << this.Ty) | (this.Tr & ((1 << this.Ty) - 1)) | (1 << this.Ty)).substring(1) + '>';
    }
}
