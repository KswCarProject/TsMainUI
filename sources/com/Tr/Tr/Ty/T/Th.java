package com.Tr.Tr.Ty.T;

import com.Tr.Tr.Tr;
import com.lgb.canmodule.Can;
import com.yyw.ts70xhw.KeyDef;

/* compiled from: Proguard */
public class Th {

    /* renamed from: T  reason: collision with root package name */
    static final Th[] f296T;
    private static Th[] Tn;
    private final int T5;
    private final boolean T9;
    private final int TE;
    private final int TZ;
    private final int Tk;
    public final int Tr;
    private final int Tv;
    public final int Ty;

    static {
        Th[] thArr = {new Th(false, 3, 5, 8, 8, 1), new Th(false, 5, 7, 10, 10, 1), new Th(true, 5, 7, 16, 6, 1), new Th(false, 8, 10, 12, 12, 1), new Th(true, 10, 11, 14, 6, 2), new Th(false, 12, 12, 14, 14, 1), new Th(true, 16, 14, 24, 10, 1), new Th(false, 18, 14, 16, 16, 1), new Th(false, 22, 18, 18, 18, 1), new Th(true, 22, 18, 16, 10, 2), new Th(false, 30, 20, 20, 20, 1), new Th(true, 32, 24, 16, 14, 2), new Th(false, 36, 24, 22, 22, 1), new Th(false, 44, 28, 24, 24, 1), new Th(true, 49, 28, 22, 14, 2), new Th(false, 62, 36, 14, 14, 4), new Th(false, 86, 42, 16, 16, 4), new Th(false, 114, 48, 18, 18, 4), new Th(false, 144, 56, 20, 20, 4), new Th(false, 174, 68, 22, 22, 4), new Th(false, 204, 84, 24, 24, 4, 102, 42), new Th(false, 280, 112, 14, 14, 16, Can.CAN_BENC_ZMYT, 56), new Th(false, 368, 144, 16, 16, 16, 92, 36), new Th(false, 456, 192, 18, 18, 16, 114, 48), new Th(false, 576, Can.CAN_ZH_H530, 20, 20, 16, 144, 56), new Th(false, 696, 272, 22, 22, 16, 174, 68), new Th(false, KeyDef.SKEY_CALLUP_3, KeyDef.RKEY_AVIN, 24, 24, 16, 136, 56), new Th(false, 1050, 408, 18, 18, 36, 175, 68), new Th(false, 1304, 496, 20, 20, 36, 163, 62), new Tn()};
        f296T = thArr;
        Tn = thArr;
    }

    public Th(boolean rectangular, int dataCapacity, int errorCodewords, int matrixWidth, int matrixHeight, int dataRegions) {
        this(rectangular, dataCapacity, errorCodewords, matrixWidth, matrixHeight, dataRegions, dataCapacity, errorCodewords);
    }

    Th(boolean rectangular, int dataCapacity, int errorCodewords, int matrixWidth, int matrixHeight, int dataRegions, int rsBlockData, int rsBlockError) {
        this.T9 = rectangular;
        this.Tk = dataCapacity;
        this.TZ = errorCodewords;
        this.Tr = matrixWidth;
        this.Ty = matrixHeight;
        this.TE = dataRegions;
        this.T5 = rsBlockData;
        this.Tv = rsBlockError;
    }

    public static Th T(int dataCodewords, T6 shape, Tr minSize, Tr maxSize, boolean fail) {
        for (Th symbol : Tn) {
            if ((shape != T6.FORCE_SQUARE || !symbol.T9) && ((shape != T6.FORCE_RECTANGLE || symbol.T9) && ((minSize == null || (symbol.Tn() >= minSize.T() && symbol.T9() >= minSize.Tr())) && ((maxSize == null || (symbol.Tn() <= maxSize.T() && symbol.T9() <= maxSize.Tr())) && dataCodewords <= symbol.Tk)))) {
                return symbol;
            }
        }
        if (!fail) {
            return null;
        }
        throw new IllegalArgumentException("Can't find a symbol arrangement that matches the message. Data codewords: " + dataCodewords);
    }

    private int TE() {
        switch (this.TE) {
            case 1:
                return 1;
            case 2:
            case 4:
                return 2;
            case 16:
                return 4;
            case 36:
                return 6;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    private int T5() {
        switch (this.TE) {
            case 1:
            case 2:
                return 1;
            case 4:
                return 2;
            case 16:
                return 4;
            case 36:
                return 6;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    public final int Tr() {
        return TE() * this.Tr;
    }

    public final int Ty() {
        return T5() * this.Ty;
    }

    public final int Tn() {
        return Tr() + (TE() << 1);
    }

    public final int T9() {
        return Ty() + (T5() << 1);
    }

    public int T() {
        return this.Tk / this.T5;
    }

    public final int Tk() {
        return this.Tk;
    }

    public final int TZ() {
        return this.TZ;
    }

    public int T(int index) {
        return this.T5;
    }

    public final int Tr(int index) {
        return this.Tv;
    }

    public final String toString() {
        return (this.T9 ? "Rectangular Symbol:" : "Square Symbol:") + " data region " + this.Tr + 'x' + this.Ty + ", symbol size " + Tn() + 'x' + T9() + ", symbol data size " + Tr() + 'x' + Ty() + ", codewords " + this.Tk + '+' + this.TZ;
    }
}
