package com.Tr.Tr.Ty.T;

/* compiled from: Proguard */
final class TF extends Ty {
    TF() {
    }

    public int T() {
        return 3;
    }

    public void T(TE context) {
        StringBuilder buffer = new StringBuilder();
        while (true) {
            if (!context.TZ()) {
                break;
            }
            char c = context.Tr();
            context.f292T++;
            T(c, buffer);
            if (buffer.length() % 3 == 0) {
                T(context, buffer);
                int newMode = Tv.T(context.T(), context.f292T, T());
                if (newMode != T()) {
                    context.Tr(newMode);
                    break;
                }
            }
        }
        Tr(context, buffer);
    }

    /* access modifiers changed from: package-private */
    public int T(char c, StringBuilder sb) {
        if (c == 13) {
            sb.append(0);
        } else if (c == '*') {
            sb.append(1);
        } else if (c == '>') {
            sb.append(2);
        } else if (c == ' ') {
            sb.append(3);
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
        } else if (c < 'A' || c > 'Z') {
            Tv.Ty(c);
        } else {
            sb.append((char) ((c - 'A') + 14));
        }
        return 1;
    }

    /* access modifiers changed from: package-private */
    public void Tr(TE context, StringBuilder buffer) {
        context.Tv();
        int available = context.T5().Tk() - context.Tn();
        context.f292T -= buffer.length();
        if (context.TE() > 1 || available > 1 || context.TE() != available) {
            context.T(254);
        }
        if (context.T9() < 0) {
            context.Tr(0);
        }
    }
}
