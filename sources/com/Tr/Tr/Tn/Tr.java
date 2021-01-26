package com.Tr.Tr.Tn;

/* compiled from: Proguard */
public final class Tr extends Tj {

    /* renamed from: T  reason: collision with root package name */
    private static final char[] f282T = {'A', 'B', 'C', 'D'};
    private static final char Tn = f282T[0];
    private static final char[] Tr = {'T', 'N', '*', 'E'};
    private static final char[] Ty = {'/', ':', '+', '.'};

    public boolean[] T(String contents) {
        if (contents.length() < 2) {
            contents = Tn + contents + Tn;
        } else {
            char firstChar = Character.toUpperCase(contents.charAt(0));
            char lastChar = Character.toUpperCase(contents.charAt(contents.length() - 1));
            boolean startsNormal = T.T(f282T, firstChar);
            boolean endsNormal = T.T(f282T, lastChar);
            boolean startsAlt = T.T(Tr, firstChar);
            boolean endsAlt = T.T(Tr, lastChar);
            if (startsNormal) {
                if (!endsNormal) {
                    throw new IllegalArgumentException("Invalid start/end guards: " + contents);
                }
            } else if (startsAlt) {
                if (!endsAlt) {
                    throw new IllegalArgumentException("Invalid start/end guards: " + contents);
                }
            } else if (endsNormal || endsAlt) {
                throw new IllegalArgumentException("Invalid start/end guards: " + contents);
            } else {
                contents = Tn + contents + Tn;
            }
        }
        int resultLength = 20;
        for (int i = 1; i < contents.length() - 1; i++) {
            if (Character.isDigit(contents.charAt(i)) || contents.charAt(i) == '-' || contents.charAt(i) == '$') {
                resultLength += 9;
            } else if (T.T(Ty, contents.charAt(i))) {
                resultLength += 10;
            } else {
                throw new IllegalArgumentException("Cannot encode : '" + contents.charAt(i) + '\'');
            }
        }
        boolean[] result = new boolean[((contents.length() - 1) + resultLength)];
        int position = 0;
        for (int index = 0; index < contents.length(); index++) {
            char c = Character.toUpperCase(contents.charAt(index));
            if (index == 0 || index == contents.length() - 1) {
                switch (c) {
                    case '*':
                        c = 'C';
                        break;
                    case 'E':
                        c = 'D';
                        break;
                    case 'N':
                        c = 'B';
                        break;
                    case 'T':
                        c = 'A';
                        break;
                }
            }
            int code = 0;
            int i2 = 0;
            while (true) {
                if (i2 < T.f273T.length) {
                    if (c == T.f273T[i2]) {
                        code = T.Tr[i2];
                    } else {
                        i2++;
                    }
                }
            }
            boolean color = true;
            int counter = 0;
            int bit = 0;
            while (bit < 7) {
                result[position] = color;
                position++;
                if (((code >> (6 - bit)) & 1) == 0 || counter == 1) {
                    color = !color;
                    bit++;
                    counter = 0;
                } else {
                    counter++;
                }
            }
            if (index < contents.length() - 1) {
                result[position] = false;
                position++;
            }
        }
        return result;
    }
}
