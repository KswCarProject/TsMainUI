package com.Tr.Tr.Tk.T;

import com.lgb.canmodule.Can;

/* compiled from: Proguard */
public final class Ty {

    /* renamed from: T  reason: collision with root package name */
    private static final int[] f264T = {31892, 34236, 39577, 42195, 48118, 51042, 55367, 58893, 63784, 68472, 70749, 76311, 79154, 84390, 87683, 92361, 96236, 102084, 102881, 110507, 110734, 117786, 119615, 126325, 127568, 133589, 136944, 141498, 145311, 150283, 152622, 158308, 161089, 167017};
    private static final Ty[] Tr = Tn();
    private final Tr[] T9;
    private final int Tk;
    private final int[] Tn;
    private final int Ty;

    private Ty(int versionNumber, int[] alignmentPatternCenters, Tr... ecBlocks) {
        this.Ty = versionNumber;
        this.Tn = alignmentPatternCenters;
        this.T9 = ecBlocks;
        int total = 0;
        int ecCodewords = ecBlocks[0].T();
        for (T ecBlock : ecBlocks[0].Tn()) {
            total += ecBlock.T() * (ecBlock.Tr() + ecCodewords);
        }
        this.Tk = total;
    }

    public int T() {
        return this.Ty;
    }

    public int Tr() {
        return this.Tk;
    }

    public int Ty() {
        return (this.Ty * 4) + 17;
    }

    public Tr T(T ecLevel) {
        return this.T9[ecLevel.ordinal()];
    }

    public static Ty T(int versionNumber) {
        if (versionNumber > 0 && versionNumber <= 40) {
            return Tr[versionNumber - 1];
        }
        throw new IllegalArgumentException();
    }

    /* compiled from: Proguard */
    public static final class Tr {

        /* renamed from: T  reason: collision with root package name */
        private final int f266T;
        private final T[] Tr;

        Tr(int ecCodewordsPerBlock, T... ecBlocks) {
            this.f266T = ecCodewordsPerBlock;
            this.Tr = ecBlocks;
        }

        public int T() {
            return this.f266T;
        }

        public int Tr() {
            int total = 0;
            for (T ecBlock : this.Tr) {
                total += ecBlock.T();
            }
            return total;
        }

        public int Ty() {
            return this.f266T * Tr();
        }

        public T[] Tn() {
            return this.Tr;
        }
    }

    /* compiled from: Proguard */
    public static final class T {

        /* renamed from: T  reason: collision with root package name */
        private final int f265T;
        private final int Tr;

        T(int count, int dataCodewords) {
            this.f265T = count;
            this.Tr = dataCodewords;
        }

        public int T() {
            return this.f265T;
        }

        public int Tr() {
            return this.Tr;
        }
    }

    public String toString() {
        return String.valueOf(this.Ty);
    }

    private static Ty[] Tn() {
        return new Ty[]{new Ty(1, new int[0], new Tr(7, new T(1, 19)), new Tr(10, new T(1, 16)), new Tr(13, new T(1, 13)), new Tr(17, new T(1, 9))), new Ty(2, new int[]{6, 18}, new Tr(10, new T(1, 34)), new Tr(16, new T(1, 28)), new Tr(22, new T(1, 22)), new Tr(28, new T(1, 16))), new Ty(3, new int[]{6, 22}, new Tr(15, new T(1, 55)), new Tr(26, new T(1, 44)), new Tr(18, new T(2, 17)), new Tr(22, new T(2, 13))), new Ty(4, new int[]{6, 26}, new Tr(20, new T(1, 80)), new Tr(18, new T(2, 32)), new Tr(26, new T(2, 24)), new Tr(16, new T(4, 9))), new Ty(5, new int[]{6, 30}, new Tr(26, new T(1, 108)), new Tr(24, new T(2, 43)), new Tr(18, new T(2, 15), new T(2, 16)), new Tr(22, new T(2, 11), new T(2, 12))), new Ty(6, new int[]{6, 34}, new Tr(18, new T(2, 68)), new Tr(16, new T(4, 27)), new Tr(24, new T(4, 19)), new Tr(28, new T(4, 15))), new Ty(7, new int[]{6, 22, 38}, new Tr(20, new T(2, 78)), new Tr(18, new T(4, 31)), new Tr(18, new T(2, 14), new T(4, 15)), new Tr(26, new T(4, 13), new T(1, 14))), new Ty(8, new int[]{6, 24, 42}, new Tr(24, new T(2, 97)), new Tr(22, new T(2, 38), new T(2, 39)), new Tr(22, new T(4, 18), new T(2, 19)), new Tr(26, new T(4, 14), new T(2, 15))), new Ty(9, new int[]{6, 26, 46}, new Tr(30, new T(2, 116)), new Tr(22, new T(3, 36), new T(2, 37)), new Tr(20, new T(4, 16), new T(4, 17)), new Tr(24, new T(4, 12), new T(4, 13))), new Ty(10, new int[]{6, 28, 50}, new Tr(18, new T(2, 68), new T(2, 69)), new Tr(26, new T(4, 43), new T(1, 44)), new Tr(24, new T(6, 19), new T(2, 20)), new Tr(28, new T(6, 15), new T(2, 16))), new Ty(11, new int[]{6, 30, 54}, new Tr(20, new T(4, 81)), new Tr(30, new T(1, 50), new T(4, 51)), new Tr(28, new T(4, 22), new T(4, 23)), new Tr(24, new T(3, 12), new T(8, 13))), new Ty(12, new int[]{6, 32, 58}, new Tr(24, new T(2, 92), new T(2, 93)), new Tr(22, new T(6, 36), new T(2, 37)), new Tr(26, new T(4, 20), new T(6, 21)), new Tr(28, new T(7, 14), new T(4, 15))), new Ty(13, new int[]{6, 34, 62}, new Tr(26, new T(4, 107)), new Tr(22, new T(8, 37), new T(1, 38)), new Tr(24, new T(8, 20), new T(4, 21)), new Tr(22, new T(12, 11), new T(4, 12))), new Ty(14, new int[]{6, 26, 46, 66}, new Tr(30, new T(3, 115), new T(1, 116)), new Tr(24, new T(4, 40), new T(5, 41)), new Tr(20, new T(11, 16), new T(5, 17)), new Tr(24, new T(11, 12), new T(5, 13))), new Ty(15, new int[]{6, 26, 48, 70}, new Tr(22, new T(5, 87), new T(1, 88)), new Tr(24, new T(5, 41), new T(5, 42)), new Tr(30, new T(5, 24), new T(7, 25)), new Tr(24, new T(11, 12), new T(7, 13))), new Ty(16, new int[]{6, 26, 50, 74}, new Tr(24, new T(5, 98), new T(1, 99)), new Tr(28, new T(7, 45), new T(3, 46)), new Tr(24, new T(15, 19), new T(2, 20)), new Tr(30, new T(3, 15), new T(13, 16))), new Ty(17, new int[]{6, 30, 54, 78}, new Tr(28, new T(1, 107), new T(5, 108)), new Tr(28, new T(10, 46), new T(1, 47)), new Tr(28, new T(1, 22), new T(15, 23)), new Tr(28, new T(2, 14), new T(17, 15))), new Ty(18, new int[]{6, 30, 56, 82}, new Tr(30, new T(5, 120), new T(1, 121)), new Tr(26, new T(9, 43), new T(4, 44)), new Tr(28, new T(17, 22), new T(1, 23)), new Tr(28, new T(2, 14), new T(19, 15))), new Ty(19, new int[]{6, 30, 58, 86}, new Tr(28, new T(3, 113), new T(4, 114)), new Tr(26, new T(3, 44), new T(11, 45)), new Tr(26, new T(17, 21), new T(4, 22)), new Tr(26, new T(9, 13), new T(16, 14))), new Ty(20, new int[]{6, 34, 62, 90}, new Tr(28, new T(3, 107), new T(5, 108)), new Tr(26, new T(3, 41), new T(13, 42)), new Tr(30, new T(15, 24), new T(5, 25)), new Tr(28, new T(15, 15), new T(10, 16))), new Ty(21, new int[]{6, 28, 50, 72, 94}, new Tr(28, new T(4, 116), new T(4, 117)), new Tr(26, new T(17, 42)), new Tr(28, new T(17, 22), new T(6, 23)), new Tr(30, new T(19, 16), new T(6, 17))), new Ty(22, new int[]{6, 26, 50, 74, 98}, new Tr(28, new T(2, 111), new T(7, 112)), new Tr(28, new T(17, 46)), new Tr(30, new T(7, 24), new T(16, 25)), new Tr(24, new T(34, 13))), new Ty(23, new int[]{6, 30, 54, 78, 102}, new Tr(30, new T(4, 121), new T(5, 122)), new Tr(28, new T(4, 47), new T(14, 48)), new Tr(30, new T(11, 24), new T(14, 25)), new Tr(30, new T(16, 15), new T(14, 16))), new Ty(24, new int[]{6, 28, 54, 80, 106}, new Tr(30, new T(6, 117), new T(4, 118)), new Tr(28, new T(6, 45), new T(14, 46)), new Tr(30, new T(11, 24), new T(16, 25)), new Tr(30, new T(30, 16), new T(2, 17))), new Ty(25, new int[]{6, 32, 58, 84, 110}, new Tr(26, new T(8, 106), new T(4, 107)), new Tr(28, new T(8, 47), new T(13, 48)), new Tr(30, new T(7, 24), new T(22, 25)), new Tr(30, new T(22, 15), new T(13, 16))), new Ty(26, new int[]{6, 30, 58, 86, 114}, new Tr(28, new T(10, 114), new T(2, 115)), new Tr(28, new T(19, 46), new T(4, 47)), new Tr(28, new T(28, 22), new T(6, 23)), new Tr(30, new T(33, 16), new T(4, 17))), new Ty(27, new int[]{6, 34, 62, 90, 118}, new Tr(30, new T(8, 122), new T(4, 123)), new Tr(28, new T(22, 45), new T(3, 46)), new Tr(30, new T(8, 23), new T(26, 24)), new Tr(30, new T(12, 15), new T(28, 16))), new Ty(28, new int[]{6, 26, 50, 74, 98, 122}, new Tr(30, new T(3, 117), new T(10, 118)), new Tr(28, new T(3, 45), new T(23, 46)), new Tr(30, new T(4, 24), new T(31, 25)), new Tr(30, new T(11, 15), new T(31, 16))), new Ty(29, new int[]{6, 30, 54, 78, 102, 126}, new Tr(30, new T(7, 116), new T(7, 117)), new Tr(28, new T(21, 45), new T(7, 46)), new Tr(30, new T(1, 23), new T(37, 24)), new Tr(30, new T(19, 15), new T(26, 16))), new Ty(30, new int[]{6, 26, 52, 78, 104, 130}, new Tr(30, new T(5, 115), new T(10, 116)), new Tr(28, new T(19, 47), new T(10, 48)), new Tr(30, new T(15, 24), new T(25, 25)), new Tr(30, new T(23, 15), new T(25, 16))), new Ty(31, new int[]{6, 30, 56, 82, 108, 134}, new Tr(30, new T(13, 115), new T(3, 116)), new Tr(28, new T(2, 46), new T(29, 47)), new Tr(30, new T(42, 24), new T(1, 25)), new Tr(30, new T(23, 15), new T(28, 16))), new Ty(32, new int[]{6, 34, 60, 86, 112, 138}, new Tr(30, new T(17, 115)), new Tr(28, new T(10, 46), new T(23, 47)), new Tr(30, new T(10, 24), new T(35, 25)), new Tr(30, new T(19, 15), new T(35, 16))), new Ty(33, new int[]{6, 30, 58, 86, 114, 142}, new Tr(30, new T(17, 115), new T(1, 116)), new Tr(28, new T(14, 46), new T(21, 47)), new Tr(30, new T(29, 24), new T(19, 25)), new Tr(30, new T(11, 15), new T(46, 16))), new Ty(34, new int[]{6, 34, 62, 90, 118, 146}, new Tr(30, new T(13, 115), new T(6, 116)), new Tr(28, new T(14, 46), new T(23, 47)), new Tr(30, new T(44, 24), new T(7, 25)), new Tr(30, new T(59, 16), new T(1, 17))), new Ty(35, new int[]{6, 30, 54, 78, 102, 126, 150}, new Tr(30, new T(12, 121), new T(7, 122)), new Tr(28, new T(12, 47), new T(26, 48)), new Tr(30, new T(39, 24), new T(14, 25)), new Tr(30, new T(22, 15), new T(41, 16))), new Ty(36, new int[]{6, 24, 50, 76, 102, 128, Can.CAN_CC_WC}, new Tr(30, new T(6, 121), new T(14, 122)), new Tr(28, new T(6, 47), new T(34, 48)), new Tr(30, new T(46, 24), new T(10, 25)), new Tr(30, new T(2, 15), new T(64, 16))), new Ty(37, new int[]{6, 28, 54, 80, 106, 132, Can.CAN_BJ20_WC}, new Tr(30, new T(17, 122), new T(4, 123)), new Tr(28, new T(29, 46), new T(14, 47)), new Tr(30, new T(49, 24), new T(10, 25)), new Tr(30, new T(24, 15), new T(46, 16))), new Ty(38, new int[]{6, 32, 58, 84, 110, 136, 162}, new Tr(30, new T(4, 122), new T(18, 123)), new Tr(28, new T(13, 46), new T(32, 47)), new Tr(30, new T(48, 24), new T(14, 25)), new Tr(30, new T(42, 15), new T(32, 16))), new Ty(39, new int[]{6, 26, 54, 82, 110, 138, 166}, new Tr(30, new T(20, 117), new T(4, 118)), new Tr(28, new T(40, 47), new T(7, 48)), new Tr(30, new T(43, 24), new T(22, 25)), new Tr(30, new T(10, 15), new T(67, 16))), new Ty(40, new int[]{6, 30, 58, 86, 114, 142, 170}, new Tr(30, new T(19, 118), new T(6, 119)), new Tr(28, new T(18, 47), new T(31, 48)), new Tr(30, new T(34, 24), new T(34, 25)), new Tr(30, new T(20, 15), new T(61, 16)))};
    }
}
