package com.Tr.Tr.T.T;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* compiled from: Proguard */
public final class Tn {

    /* renamed from: T  reason: collision with root package name */
    static final String[] f250T = {"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};
    private static final int[][] Tn;
    static final int[][] Tr = {new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};
    static final int[][] Ty;
    private final byte[] T9;

    static {
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{5, 256});
        Tn = iArr;
        iArr[0][32] = 1;
        for (int c = 65; c <= 90; c++) {
            Tn[0][c] = (c - 65) + 2;
        }
        Tn[1][32] = 1;
        for (int c2 = 97; c2 <= 122; c2++) {
            Tn[1][c2] = (c2 - 97) + 2;
        }
        Tn[2][32] = 1;
        for (int c3 = 48; c3 <= 57; c3++) {
            Tn[2][c3] = (c3 - 48) + 2;
        }
        Tn[2][44] = 12;
        Tn[2][46] = 13;
        int[] mixedTable = {0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, 126, 127};
        for (int i = 0; i < 28; i++) {
            Tn[3][mixedTable[i]] = i;
        }
        int[] punctTable = {0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125};
        for (int i2 = 0; i2 < 31; i2++) {
            if (punctTable[i2] > 0) {
                Tn[4][punctTable[i2]] = i2;
            }
        }
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{6, 6});
        Ty = iArr2;
        for (int[] fill : iArr2) {
            Arrays.fill(fill, -1);
        }
        Ty[0][4] = 0;
        Ty[1][4] = 0;
        Ty[1][0] = 28;
        Ty[3][4] = 0;
        Ty[2][4] = 0;
        Ty[2][0] = 15;
    }

    public Tn(byte[] text) {
        this.T9 = text;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r1v2, types: [byte] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.Tr.Tr.Tr.T T() {
        /*
            r8 = this;
            r7 = 32
            r4 = 0
            com.Tr.Tr.T.T.Tk r5 = com.Tr.Tr.T.T.Tk.f249T
            java.util.List r3 = java.util.Collections.singletonList(r5)
            r0 = 0
        L_0x000a:
            byte[] r5 = r8.T9
            int r5 = r5.length
            if (r0 >= r5) goto L_0x0050
            int r5 = r0 + 1
            byte[] r6 = r8.T9
            int r6 = r6.length
            if (r5 >= r6) goto L_0x002f
            byte[] r5 = r8.T9
            int r6 = r0 + 1
            byte r1 = r5[r6]
        L_0x001c:
            byte[] r5 = r8.T9
            byte r5 = r5[r0]
            switch(r5) {
                case 13: goto L_0x0031;
                case 44: goto L_0x003f;
                case 46: goto L_0x0039;
                case 58: goto L_0x0045;
                default: goto L_0x0023;
            }
        L_0x0023:
            r2 = 0
        L_0x0024:
            if (r2 <= 0) goto L_0x004b
            java.util.Collection r3 = T((java.lang.Iterable<com.Tr.Tr.T.T.Tk>) r3, (int) r0, (int) r2)
            int r0 = r0 + 1
        L_0x002c:
            int r0 = r0 + 1
            goto L_0x000a
        L_0x002f:
            r1 = r4
            goto L_0x001c
        L_0x0031:
            r5 = 10
            if (r1 != r5) goto L_0x0037
            r2 = 2
        L_0x0036:
            goto L_0x0024
        L_0x0037:
            r2 = r4
            goto L_0x0036
        L_0x0039:
            if (r1 != r7) goto L_0x003d
            r2 = 3
        L_0x003c:
            goto L_0x0024
        L_0x003d:
            r2 = r4
            goto L_0x003c
        L_0x003f:
            if (r1 != r7) goto L_0x0043
            r2 = 4
        L_0x0042:
            goto L_0x0024
        L_0x0043:
            r2 = r4
            goto L_0x0042
        L_0x0045:
            if (r1 != r7) goto L_0x0049
            r2 = 5
        L_0x0048:
            goto L_0x0024
        L_0x0049:
            r2 = r4
            goto L_0x0048
        L_0x004b:
            java.util.Collection r3 = r8.T(r3, r0)
            goto L_0x002c
        L_0x0050:
            com.Tr.Tr.T.T.Tn$1 r4 = new com.Tr.Tr.T.T.Tn$1
            r4.<init>()
            java.lang.Object r4 = java.util.Collections.min(r3, r4)
            com.Tr.Tr.T.T.Tk r4 = (com.Tr.Tr.T.T.Tk) r4
            byte[] r5 = r8.T9
            com.Tr.Tr.Tr.T r4 = r4.T((byte[]) r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Tr.Tr.T.T.Tn.T():com.Tr.Tr.Tr.T");
    }

    private Collection<Tk> T(Iterable<Tk> states, int index) {
        Collection<Tk> result = new LinkedList<>();
        for (Tk state : states) {
            T(state, index, result);
        }
        return T(result);
    }

    private void T(Tk state, int index, Collection<Tk> result) {
        char ch = (char) (this.T9[index] & 255);
        boolean charInCurrentTable = Tn[state.T()][ch] > 0;
        Tk stateNoBinary = null;
        for (int mode = 0; mode <= 4; mode++) {
            int charInMode = Tn[mode][ch];
            if (charInMode > 0) {
                if (stateNoBinary == null) {
                    stateNoBinary = state.Tr(index);
                }
                if (!charInCurrentTable || mode == state.T() || mode == 2) {
                    result.add(stateNoBinary.T(mode, charInMode));
                }
                if (!charInCurrentTable && Ty[state.T()][mode] >= 0) {
                    result.add(stateNoBinary.Tr(mode, charInMode));
                }
            }
        }
        if (state.Tr() > 0 || Tn[state.T()][ch] == 0) {
            result.add(state.T(index));
        }
    }

    private static Collection<Tk> T(Iterable<Tk> states, int index, int pairCode) {
        Collection<Tk> result = new LinkedList<>();
        for (Tk T2 : states) {
            T(T2, index, pairCode, result);
        }
        return T(result);
    }

    private static void T(Tk state, int index, int pairCode, Collection<Tk> result) {
        Tk stateNoBinary = state.Tr(index);
        result.add(stateNoBinary.T(4, pairCode));
        if (state.T() != 4) {
            result.add(stateNoBinary.Tr(4, pairCode));
        }
        if (pairCode == 3 || pairCode == 4) {
            result.add(stateNoBinary.T(2, 16 - pairCode).T(2, 1));
        }
        if (state.Tr() > 0) {
            result.add(state.T(index).T(index + 1));
        }
    }

    private static Collection<Tk> T(Iterable<Tk> states) {
        List<Tk> result = new LinkedList<>();
        for (Tk newState : states) {
            boolean add = true;
            Iterator<Tk> iterator = result.iterator();
            while (true) {
                if (!iterator.hasNext()) {
                    break;
                }
                Tk oldState = iterator.next();
                if (oldState.T(newState)) {
                    add = false;
                    break;
                } else if (newState.T(oldState)) {
                    iterator.remove();
                }
            }
            if (add) {
                result.add(newState);
            }
        }
        return result;
    }
}
