package com.Tr.Tr.Ty.T;

/* compiled from: Proguard */
class Ty implements TZ {
    Ty() {
    }

    public int T() {
        return 1;
    }

    public void T(TE context) {
        int newMode;
        StringBuilder buffer = new StringBuilder();
        while (true) {
            if (!context.TZ()) {
                break;
            }
            char c = context.Tr();
            context.f295T++;
            int lastCharSize = T(c, buffer);
            int curCodewordCount = context.Tn() + ((buffer.length() / 3) << 1);
            context.Ty(curCodewordCount);
            int available = context.T5().Tk() - curCodewordCount;
            if (context.TZ()) {
                if (buffer.length() % 3 == 0 && (newMode = Tv.T(context.T(), context.f295T, T())) != T()) {
                    context.Tr(newMode);
                    break;
                }
            } else {
                StringBuilder removed = new StringBuilder();
                if (buffer.length() % 3 == 2 && (available < 2 || available > 2)) {
                    lastCharSize = T(context, buffer, removed, lastCharSize);
                }
                while (buffer.length() % 3 == 1 && ((lastCharSize <= 3 && available != 1) || lastCharSize > 3)) {
                    lastCharSize = T(context, buffer, removed, lastCharSize);
                }
            }
        }
        Tr(context, buffer);
    }

    private int T(TE context, StringBuilder buffer, StringBuilder removed, int lastCharSize) {
        int count = buffer.length();
        buffer.delete(count - lastCharSize, count);
        context.f295T--;
        int lastCharSize2 = T(context.Tr(), removed);
        context.Th();
        return lastCharSize2;
    }

    static void T(TE context, StringBuilder buffer) {
        context.T(T((CharSequence) buffer, 0));
        buffer.delete(0, 3);
    }

    /* access modifiers changed from: package-private */
    public void Tr(TE context, StringBuilder buffer) {
        int unwritten = (buffer.length() / 3) << 1;
        int rest = buffer.length() % 3;
        int curCodewordCount = context.Tn() + unwritten;
        context.Ty(curCodewordCount);
        int available = context.T5().Tk() - curCodewordCount;
        if (rest == 2) {
            buffer.append(0);
            while (buffer.length() >= 3) {
                T(context, buffer);
            }
            if (context.TZ()) {
                context.T(254);
            }
        } else if (available == 1 && rest == 1) {
            while (buffer.length() >= 3) {
                T(context, buffer);
            }
            if (context.TZ()) {
                context.T(254);
            }
            context.f295T--;
        } else if (rest == 0) {
            while (buffer.length() >= 3) {
                T(context, buffer);
            }
            if (available > 0 || context.TZ()) {
                context.T(254);
            }
        } else {
            throw new IllegalStateException("Unexpected case. Please report!");
        }
        context.Tr(0);
    }

    /* access modifiers changed from: package-private */
    public int T(char c, StringBuilder sb) {
        if (c == ' ') {
            sb.append(3);
            return 1;
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
            return 1;
        } else if (c >= 'A' && c <= 'Z') {
            sb.append((char) ((c - 'A') + 14));
            return 1;
        } else if (c >= 0 && c <= 31) {
            sb.append(0);
            sb.append(c);
            return 2;
        } else if (c >= '!' && c <= '/') {
            sb.append(1);
            sb.append((char) (c - '!'));
            return 2;
        } else if (c >= ':' && c <= '@') {
            sb.append(1);
            sb.append((char) ((c - ':') + 15));
            return 2;
        } else if (c >= '[' && c <= '_') {
            sb.append(1);
            sb.append((char) ((c - '[') + 22));
            return 2;
        } else if (c >= '`' && c <= 127) {
            sb.append(2);
            sb.append((char) (c - '`'));
            return 2;
        } else if (c >= 128) {
            sb.append("\u0001\u001e");
            return T((char) (c - 128), sb) + 2;
        } else {
            throw new IllegalArgumentException("Illegal character: " + c);
        }
    }

    private static String T(CharSequence sb, int startPos) {
        char c1 = sb.charAt(startPos);
        char c2 = sb.charAt(startPos + 1);
        int v = (c1 * 1600) + (c2 * '(') + sb.charAt(startPos + 2) + 1;
        return new String(new char[]{(char) (v / 256), (char) (v % 256)});
    }
}
