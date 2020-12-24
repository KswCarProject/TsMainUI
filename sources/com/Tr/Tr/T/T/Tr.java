package com.Tr.Tr.T.T;

import com.Tr.Tr.Tr.T;

/* compiled from: Proguard */
final class Tr extends TZ {
    private final short Tr;
    private final short Ty;

    Tr(TZ previous, int binaryShiftStart, int binaryShiftByteCount) {
        super(previous);
        this.Tr = (short) binaryShiftStart;
        this.Ty = (short) binaryShiftByteCount;
    }

    public void T(T bitArray, byte[] text) {
        for (int i = 0; i < this.Ty; i++) {
            if (i == 0 || (i == 31 && this.Ty <= 62)) {
                bitArray.T(31, 5);
                if (this.Ty > 62) {
                    bitArray.T(this.Ty - 31, 16);
                } else if (i == 0) {
                    bitArray.T(Math.min(this.Ty, 31), 5);
                } else {
                    bitArray.T(this.Ty - 31, 5);
                }
            }
            bitArray.T(text[this.Tr + i], 8);
        }
    }

    public String toString() {
        return "<" + this.Tr + "::" + ((this.Tr + this.Ty) - 1) + '>';
    }
}
