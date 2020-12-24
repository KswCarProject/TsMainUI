package com.Tr.Tr.Ty.T;

/* compiled from: Proguard */
final class T implements TZ {
    T() {
    }

    public int T() {
        return 0;
    }

    public void T(TE context) {
        if (Tv.T((CharSequence) context.T(), context.f292T) >= 2) {
            context.T(T(context.T().charAt(context.f292T), context.T().charAt(context.f292T + 1)));
            context.f292T += 2;
            return;
        }
        char c = context.Tr();
        int newMode = Tv.T(context.T(), context.f292T, T());
        if (newMode != T()) {
            switch (newMode) {
                case 1:
                    context.T(230);
                    context.Tr(1);
                    return;
                case 2:
                    context.T(239);
                    context.Tr(2);
                    return;
                case 3:
                    context.T(238);
                    context.Tr(3);
                    return;
                case 4:
                    context.T(240);
                    context.Tr(4);
                    return;
                case 5:
                    context.T(231);
                    context.Tr(5);
                    return;
                default:
                    throw new IllegalStateException("Illegal mode: " + newMode);
            }
        } else if (Tv.Tr(c)) {
            context.T(235);
            context.T((char) ((c - 128) + 1));
            context.f292T++;
        } else {
            context.T((char) (c + 1));
            context.f292T++;
        }
    }

    private static char T(char digit1, char digit2) {
        if (Tv.T(digit1) && Tv.T(digit2)) {
            return (char) (((digit1 - '0') * 10) + (digit2 - '0') + 130);
        }
        throw new IllegalArgumentException("not digits: " + digit1 + digit2);
    }
}
