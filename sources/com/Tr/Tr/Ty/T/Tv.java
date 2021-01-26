package com.Tr.Tr.Ty.T;

import com.Tr.Tr.Tr;
import com.lgb.canmodule.Can;
import java.util.Arrays;

/* compiled from: Proguard */
public final class Tv {
    private static char T(char ch, int codewordPosition) {
        int tempVariable = ch + ((codewordPosition * 149) % Can.CAN_FORD_ESCORT_LY) + 1;
        if (tempVariable > 254) {
            tempVariable -= 254;
        }
        return (char) tempVariable;
    }

    public static String T(String msg, T6 shape, Tr minSize, Tr maxSize) {
        TZ[] encoders = {new T(), new Ty(), new Tq(), new TF(), new Tk(), new Tr()};
        TE context = new TE(msg);
        context.T(shape);
        context.T(minSize, maxSize);
        if (msg.startsWith("[)>\u001e05\u001d") && msg.endsWith("\u001e\u0004")) {
            context.T(236);
            context.T(2);
            context.f295T += 7;
        } else if (msg.startsWith("[)>\u001e06\u001d") && msg.endsWith("\u001e\u0004")) {
            context.T(237);
            context.T(2);
            context.f295T += 7;
        }
        int encodingMode = 0;
        while (context.TZ()) {
            encoders[encodingMode].T(context);
            if (context.T9() >= 0) {
                encodingMode = context.T9();
                context.Tk();
            }
        }
        int len = context.Tn();
        context.Tv();
        int capacity = context.T5().Tk();
        if (!(len >= capacity || encodingMode == 0 || encodingMode == 5)) {
            context.T(254);
        }
        StringBuilder codewords = context.Ty();
        if (codewords.length() < capacity) {
            codewords.append(129);
        }
        while (codewords.length() < capacity) {
            codewords.append(T(129, codewords.length() + 1));
        }
        return context.Ty().toString();
    }

    static int T(CharSequence msg, int startpos, int currentMode) {
        float[] charCounts;
        if (startpos >= msg.length()) {
            return currentMode;
        }
        if (currentMode == 0) {
            charCounts = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.25f};
        } else {
            charCounts = new float[]{1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.25f};
            charCounts[currentMode] = 0.0f;
        }
        int charsProcessed = 0;
        while (startpos + charsProcessed != msg.length()) {
            char c = msg.charAt(startpos + charsProcessed);
            charsProcessed++;
            if (T(c)) {
                charCounts[0] = charCounts[0] + 0.5f;
            } else if (Tr(c)) {
                charCounts[0] = (float) Math.ceil((double) charCounts[0]);
                charCounts[0] = charCounts[0] + 2.0f;
            } else {
                charCounts[0] = (float) Math.ceil((double) charCounts[0]);
                charCounts[0] = charCounts[0] + 1.0f;
            }
            if (Tn(c)) {
                charCounts[1] = charCounts[1] + 0.6666667f;
            } else if (Tr(c)) {
                charCounts[1] = charCounts[1] + 2.6666667f;
            } else {
                charCounts[1] = charCounts[1] + 1.3333334f;
            }
            if (T9(c)) {
                charCounts[2] = charCounts[2] + 0.6666667f;
            } else if (Tr(c)) {
                charCounts[2] = charCounts[2] + 2.6666667f;
            } else {
                charCounts[2] = charCounts[2] + 1.3333334f;
            }
            if (Tk(c)) {
                charCounts[3] = charCounts[3] + 0.6666667f;
            } else if (Tr(c)) {
                charCounts[3] = charCounts[3] + 4.3333335f;
            } else {
                charCounts[3] = charCounts[3] + 3.3333333f;
            }
            if (TE(c)) {
                charCounts[4] = charCounts[4] + 0.75f;
            } else if (Tr(c)) {
                charCounts[4] = charCounts[4] + 4.25f;
            } else {
                charCounts[4] = charCounts[4] + 3.25f;
            }
            if (T5(c)) {
                charCounts[5] = charCounts[5] + 4.0f;
            } else {
                charCounts[5] = charCounts[5] + 1.0f;
            }
            if (charsProcessed >= 4) {
                int[] intCharCounts = new int[6];
                byte[] mins = new byte[6];
                T(charCounts, intCharCounts, Integer.MAX_VALUE, mins);
                int minCount = T(mins);
                if (intCharCounts[0] < intCharCounts[5] && intCharCounts[0] < intCharCounts[1] && intCharCounts[0] < intCharCounts[2] && intCharCounts[0] < intCharCounts[3] && intCharCounts[0] < intCharCounts[4]) {
                    return 0;
                }
                if (intCharCounts[5] < intCharCounts[0] || mins[1] + mins[2] + mins[3] + mins[4] == 0) {
                    return 5;
                }
                if (minCount == 1 && mins[4] > 0) {
                    return 4;
                }
                if (minCount == 1 && mins[2] > 0) {
                    return 2;
                }
                if (minCount == 1 && mins[3] > 0) {
                    return 3;
                }
                if (intCharCounts[1] + 1 < intCharCounts[0] && intCharCounts[1] + 1 < intCharCounts[5] && intCharCounts[1] + 1 < intCharCounts[4] && intCharCounts[1] + 1 < intCharCounts[2]) {
                    if (intCharCounts[1] < intCharCounts[3]) {
                        return 1;
                    }
                    if (intCharCounts[1] == intCharCounts[3]) {
                        int p = startpos + charsProcessed + 1;
                        while (p < msg.length()) {
                            char tc = msg.charAt(p);
                            if (!TZ(tc)) {
                                if (!Tk(tc)) {
                                    break;
                                }
                                p++;
                            } else {
                                return 3;
                            }
                        }
                        return 1;
                    }
                }
            }
        }
        byte[] mins2 = new byte[6];
        int[] intCharCounts2 = new int[6];
        int min = T(charCounts, intCharCounts2, Integer.MAX_VALUE, mins2);
        int minCount2 = T(mins2);
        if (intCharCounts2[0] == min) {
            return 0;
        }
        if (minCount2 == 1 && mins2[5] > 0) {
            return 5;
        }
        if (minCount2 == 1 && mins2[4] > 0) {
            return 4;
        }
        if (minCount2 == 1 && mins2[2] > 0) {
            return 2;
        }
        if (minCount2 != 1 || mins2[3] <= 0) {
            return 1;
        }
        return 3;
    }

    private static int T(float[] charCounts, int[] intCharCounts, int min, byte[] mins) {
        Arrays.fill(mins, (byte) 0);
        for (int i = 0; i < 6; i++) {
            intCharCounts[i] = (int) Math.ceil((double) charCounts[i]);
            int current = intCharCounts[i];
            if (min > current) {
                min = current;
                Arrays.fill(mins, (byte) 0);
            }
            if (min == current) {
                mins[i] = (byte) (mins[i] + 1);
            }
        }
        return min;
    }

    private static int T(byte[] mins) {
        int minCount = 0;
        for (int i = 0; i < 6; i++) {
            minCount += mins[i];
        }
        return minCount;
    }

    static boolean T(char ch) {
        return ch >= '0' && ch <= '9';
    }

    static boolean Tr(char ch) {
        return ch >= 128 && ch <= 255;
    }

    private static boolean Tn(char ch) {
        return ch == ' ' || (ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z');
    }

    private static boolean T9(char ch) {
        return ch == ' ' || (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z');
    }

    private static boolean Tk(char ch) {
        return TZ(ch) || ch == ' ' || (ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z');
    }

    private static boolean TZ(char ch) {
        return ch == 13 || ch == '*' || ch == '>';
    }

    private static boolean TE(char ch) {
        return ch >= ' ' && ch <= '^';
    }

    private static boolean T5(char ch) {
        return false;
    }

    public static int T(CharSequence msg, int startpos) {
        int count = 0;
        int len = msg.length();
        int idx = startpos;
        if (startpos < len) {
            char ch = msg.charAt(startpos);
            while (T(ch) && idx < len) {
                count++;
                idx++;
                if (idx < len) {
                    ch = msg.charAt(idx);
                }
            }
        }
        return count;
    }

    static void Ty(char c) {
        String hex = Integer.toHexString(c);
        throw new IllegalArgumentException("Illegal character: " + c + " (0x" + ("0000".substring(0, 4 - hex.length()) + hex) + ')');
    }
}
