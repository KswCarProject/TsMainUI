package com.Tr.Tr.Tk.T;

/* compiled from: Proguard */
public enum Tr {
    TERMINATOR(new int[]{0, 0, 0}, 0),
    NUMERIC(new int[]{10, 12, 14}, 1),
    ALPHANUMERIC(new int[]{9, 11, 13}, 2),
    STRUCTURED_APPEND(new int[]{0, 0, 0}, 3),
    BYTE(new int[]{8, 16, 16}, 4),
    ECI(new int[]{0, 0, 0}, 7),
    KANJI(new int[]{8, 10, 12}, 8),
    FNC1_FIRST_POSITION(new int[]{0, 0, 0}, 5),
    FNC1_SECOND_POSITION(new int[]{0, 0, 0}, 9),
    HANZI(new int[]{8, 10, 12}, 13);
    
    private final int T6;
    private final int[] Th;

    private Tr(int[] characterCountBitsForVersions, int bits) {
        this.Th = characterCountBitsForVersions;
        this.T6 = bits;
    }

    public int T(Ty version) {
        int offset;
        int number = version.T();
        if (number <= 9) {
            offset = 0;
        } else if (number <= 26) {
            offset = 1;
        } else {
            offset = 2;
        }
        return this.Th[offset];
    }

    public int T() {
        return this.T6;
    }
}
