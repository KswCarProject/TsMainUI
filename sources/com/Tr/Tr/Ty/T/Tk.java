package com.Tr.Tr.Ty.T;

/* compiled from: Proguard */
final class Tk implements TZ {
    Tk() {
    }

    public int T() {
        return 4;
    }

    public void T(TE context) {
        StringBuilder buffer = new StringBuilder();
        while (true) {
            if (!context.TZ()) {
                break;
            }
            T(context.Tr(), buffer);
            context.f295T++;
            if (buffer.length() >= 4) {
                context.T(T((CharSequence) buffer, 0));
                buffer.delete(0, 4);
                if (Tv.T(context.T(), context.f295T, T()) != T()) {
                    context.Tr(0);
                    break;
                }
            }
        }
        buffer.append(31);
        T(context, (CharSequence) buffer);
    }

    private static void T(TE context, CharSequence buffer) {
        boolean z;
        boolean restInAscii = true;
        try {
            int count = buffer.length();
            if (count != 0) {
                if (count == 1) {
                    context.Tv();
                    int available = context.T5().Tk() - context.Tn();
                    if (context.TE() == 0 && available <= 2) {
                        context.Tr(0);
                        return;
                    }
                }
                if (count > 4) {
                    throw new IllegalStateException("Count must not exceed 4");
                }
                int restChars = count - 1;
                String encoded = T(buffer, 0);
                if (!context.TZ()) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z || restChars > 2) {
                    restInAscii = false;
                }
                if (restChars <= 2) {
                    context.Ty(context.Tn() + restChars);
                    if (context.T5().Tk() - context.Tn() >= 3) {
                        restInAscii = false;
                        context.Ty(context.Tn() + encoded.length());
                    }
                }
                if (restInAscii) {
                    context.Th();
                    context.f295T -= restChars;
                } else {
                    context.T(encoded);
                }
                context.Tr(0);
            }
        } finally {
            context.Tr(0);
        }
    }

    private static void T(char c, StringBuilder sb) {
        if (c >= ' ' && c <= '?') {
            sb.append(c);
        } else if (c < '@' || c > '^') {
            Tv.Ty(c);
        } else {
            sb.append((char) (c - '@'));
        }
    }

    private static String T(CharSequence sb, int startPos) {
        char c2;
        char c3;
        char c4 = 0;
        int len = sb.length() - startPos;
        if (len == 0) {
            throw new IllegalStateException("StringBuilder must not be empty");
        }
        char c1 = sb.charAt(startPos);
        if (len >= 2) {
            c2 = sb.charAt(startPos + 1);
        } else {
            c2 = 0;
        }
        if (len >= 3) {
            c3 = sb.charAt(startPos + 2);
        } else {
            c3 = 0;
        }
        if (len >= 4) {
            c4 = sb.charAt(startPos + 3);
        }
        int v = (c1 << 18) + (c2 << 12) + (c3 << 6) + c4;
        char cw2 = (char) ((v >> 8) & 255);
        char cw3 = (char) (v & 255);
        StringBuilder res = new StringBuilder(3);
        res.append((char) ((v >> 16) & 255));
        if (len >= 2) {
            res.append(cw2);
        }
        if (len >= 3) {
            res.append(cw3);
        }
        return res.toString();
    }
}
