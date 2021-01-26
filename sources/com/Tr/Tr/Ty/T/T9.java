package com.Tr.Tr.Ty.T;

import java.util.Arrays;

/* compiled from: Proguard */
public class T9 {

    /* renamed from: T  reason: collision with root package name */
    private final CharSequence f294T;
    private final byte[] Tn;
    private final int Tr;
    private final int Ty;

    public T9(CharSequence codewords, int numcols, int numrows) {
        this.f294T = codewords;
        this.Ty = numcols;
        this.Tr = numrows;
        this.Tn = new byte[(numcols * numrows)];
        Arrays.fill(this.Tn, (byte) -1);
    }

    public final boolean T(int col, int row) {
        return this.Tn[(this.Ty * row) + col] == 1;
    }

    private void T(int col, int row, boolean bit) {
        this.Tn[(this.Ty * row) + col] = (byte) (bit ? 1 : 0);
    }

    private boolean Tr(int col, int row) {
        return this.Tn[(this.Ty * row) + col] >= 0;
    }

    public final void T() {
        int pos = 0;
        int row = 4;
        int col = 0;
        while (true) {
            if (row == this.Tr && col == 0) {
                T(pos);
                pos++;
            }
            if (row == this.Tr - 2 && col == 0 && this.Ty % 4 != 0) {
                Tr(pos);
                pos++;
            }
            if (row == this.Tr - 2 && col == 0 && this.Ty % 8 == 4) {
                Ty(pos);
                pos++;
            }
            if (row == this.Tr + 4 && col == 2 && this.Ty % 8 == 0) {
                Tn(pos);
                pos++;
            }
            do {
                if (row < this.Tr && col >= 0 && !Tr(col, row)) {
                    T(row, col, pos);
                    pos++;
                }
                row -= 2;
                col += 2;
                if (row < 0 || col >= this.Ty) {
                    int row2 = row + 1;
                    int col2 = col + 3;
                    int pos2 = pos;
                }
                T(row, col, pos);
                pos++;
                row -= 2;
                col += 2;
                break;
            } while (col >= this.Ty);
            int row22 = row + 1;
            int col22 = col + 3;
            int pos22 = pos;
            while (true) {
                if (row22 < 0 || col22 >= this.Ty || Tr(col22, row22)) {
                    pos = pos22;
                } else {
                    pos = pos22 + 1;
                    T(row22, col22, pos22);
                }
                row22 += 2;
                col22 -= 2;
                if (row22 >= this.Tr || col22 < 0) {
                    row = row22 + 3;
                    col = col22 + 1;
                } else {
                    pos22 = pos;
                }
            }
            row = row22 + 3;
            col = col22 + 1;
            if (row >= this.Tr && col >= this.Ty) {
                break;
            }
        }
        if (!Tr(this.Ty - 1, this.Tr - 1)) {
            T(this.Ty - 1, this.Tr - 1, true);
            T(this.Ty - 2, this.Tr - 2, true);
        }
    }

    private void T(int row, int col, int pos, int bit) {
        boolean z = true;
        if (row < 0) {
            row += this.Tr;
            col += 4 - ((this.Tr + 4) % 8);
        }
        if (col < 0) {
            col += this.Ty;
            row += 4 - ((this.Ty + 4) % 8);
        }
        if ((this.f294T.charAt(pos) & (1 << (8 - bit))) == 0) {
            z = false;
        }
        T(col, row, z);
    }

    private void T(int row, int col, int pos) {
        T(row - 2, col - 2, pos, 1);
        T(row - 2, col - 1, pos, 2);
        T(row - 1, col - 2, pos, 3);
        T(row - 1, col - 1, pos, 4);
        T(row - 1, col, pos, 5);
        T(row, col - 2, pos, 6);
        T(row, col - 1, pos, 7);
        T(row, col, pos, 8);
    }

    private void T(int pos) {
        T(this.Tr - 1, 0, pos, 1);
        T(this.Tr - 1, 1, pos, 2);
        T(this.Tr - 1, 2, pos, 3);
        T(0, this.Ty - 2, pos, 4);
        T(0, this.Ty - 1, pos, 5);
        T(1, this.Ty - 1, pos, 6);
        T(2, this.Ty - 1, pos, 7);
        T(3, this.Ty - 1, pos, 8);
    }

    private void Tr(int pos) {
        T(this.Tr - 3, 0, pos, 1);
        T(this.Tr - 2, 0, pos, 2);
        T(this.Tr - 1, 0, pos, 3);
        T(0, this.Ty - 4, pos, 4);
        T(0, this.Ty - 3, pos, 5);
        T(0, this.Ty - 2, pos, 6);
        T(0, this.Ty - 1, pos, 7);
        T(1, this.Ty - 1, pos, 8);
    }

    private void Ty(int pos) {
        T(this.Tr - 3, 0, pos, 1);
        T(this.Tr - 2, 0, pos, 2);
        T(this.Tr - 1, 0, pos, 3);
        T(0, this.Ty - 2, pos, 4);
        T(0, this.Ty - 1, pos, 5);
        T(1, this.Ty - 1, pos, 6);
        T(2, this.Ty - 1, pos, 7);
        T(3, this.Ty - 1, pos, 8);
    }

    private void Tn(int pos) {
        T(this.Tr - 1, 0, pos, 1);
        T(this.Tr - 1, this.Ty - 1, pos, 2);
        T(0, this.Ty - 3, pos, 3);
        T(0, this.Ty - 2, pos, 4);
        T(0, this.Ty - 1, pos, 5);
        T(1, this.Ty - 3, pos, 6);
        T(1, this.Ty - 2, pos, 7);
        T(1, this.Ty - 1, pos, 8);
    }
}
