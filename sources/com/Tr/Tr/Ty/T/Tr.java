package com.Tr.Tr.Ty.T;

import com.lgb.canmodule.Can;

/* compiled from: Proguard */
final class Tr implements TZ {
    Tr() {
    }

    public int T() {
        return 5;
    }

    public void T(TE context) {
        boolean mustPad;
        StringBuilder buffer = new StringBuilder();
        buffer.append(0);
        while (true) {
            if (!context.TZ()) {
                break;
            }
            buffer.append(context.Tr());
            context.f295T++;
            int newMode = Tv.T(context.T(), context.f295T, T());
            if (newMode != T()) {
                context.Tr(newMode);
                break;
            }
        }
        int dataCount = buffer.length() - 1;
        int currentSize = context.Tn() + dataCount + 1;
        context.Ty(currentSize);
        if (context.T5().Tk() - currentSize > 0) {
            mustPad = true;
        } else {
            mustPad = false;
        }
        if (context.TZ() || mustPad) {
            if (dataCount <= 249) {
                buffer.setCharAt(0, (char) dataCount);
            } else if (dataCount <= 1555) {
                buffer.setCharAt(0, (char) ((dataCount / Can.CAN_NISSAN_XFY) + Can.CAN_LUXGEN_WC));
                buffer.insert(1, (char) (dataCount % Can.CAN_NISSAN_XFY));
            } else {
                throw new IllegalStateException("Message length not in valid ranges: " + dataCount);
            }
        }
        int c = buffer.length();
        for (int i = 0; i < c; i++) {
            context.T(T(buffer.charAt(i), context.Tn() + 1));
        }
    }

    private static char T(char ch, int codewordPosition) {
        int tempVariable = ch + ((codewordPosition * 149) % 255) + 1;
        if (tempVariable <= 255) {
            return (char) tempVariable;
        }
        return (char) (tempVariable - 256);
    }
}
