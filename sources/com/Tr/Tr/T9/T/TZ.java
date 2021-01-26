package com.Tr.Tr.T9.T;

import com.Tr.Tr.TE;
import com.Tr.Tr.Tr.Ty;
import com.ts.can.CanCameraUI;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

/* compiled from: Proguard */
final class TZ {

    /* renamed from: T  reason: collision with root package name */
    private static final byte[] f256T = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, 32, 0, 0, 0};
    private static final Charset T9 = Charset.forName("ISO-8859-1");
    private static final byte[] Tn = new byte[128];
    private static final byte[] Tr = {59, 60, 62, 64, 91, 92, 93, 95, 96, 126, 33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, 125, 39, 0};
    private static final byte[] Ty = new byte[128];

    static {
        Arrays.fill(Ty, (byte) -1);
        for (int i = 0; i < f256T.length; i++) {
            byte b = f256T[i];
            if (b > 0) {
                Ty[b] = (byte) i;
            }
        }
        Arrays.fill(Tn, (byte) -1);
        for (int i2 = 0; i2 < Tr.length; i2++) {
            byte b2 = Tr[i2];
            if (b2 > 0) {
                Tn[b2] = (byte) i2;
            }
        }
    }

    static String T(String msg, Ty compaction, Charset encoding) throws TE {
        Ty eci;
        StringBuilder sb = new StringBuilder(msg.length());
        if (encoding == null) {
            encoding = T9;
        } else if (!T9.equals(encoding) && (eci = Ty.T(encoding.name())) != null) {
            T(eci.T(), sb);
        }
        int len = msg.length();
        int p = 0;
        int textSubMode = 0;
        if (compaction == Ty.TEXT) {
            T((CharSequence) msg, 0, len, sb, 0);
        } else if (compaction == Ty.BYTE) {
            byte[] bytes = msg.getBytes(encoding);
            T(bytes, 0, bytes.length, 1, sb);
        } else if (compaction == Ty.NUMERIC) {
            sb.append(902);
            T(msg, 0, len, sb);
        } else {
            int encodingMode = 0;
            while (p < len) {
                int n = T((CharSequence) msg, p);
                if (n >= 13) {
                    sb.append(902);
                    encodingMode = 2;
                    textSubMode = 0;
                    T(msg, p, n, sb);
                    p += n;
                } else {
                    int t = Tr(msg, p);
                    if (t >= 5 || n == len) {
                        if (encodingMode != 0) {
                            sb.append(900);
                            encodingMode = 0;
                            textSubMode = 0;
                        }
                        textSubMode = T((CharSequence) msg, p, t, sb, textSubMode);
                        p += t;
                    } else {
                        int b = T(msg, p, encoding);
                        if (b == 0) {
                            b = 1;
                        }
                        byte[] bytes2 = msg.substring(p, p + b).getBytes(encoding);
                        if (bytes2.length == 1 && encodingMode == 0) {
                            T(bytes2, 0, 1, 0, sb);
                        } else {
                            T(bytes2, 0, bytes2.length, encodingMode, sb);
                            encodingMode = 1;
                            textSubMode = 0;
                        }
                        p += b;
                    }
                }
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int T(java.lang.CharSequence r9, int r10, int r11, java.lang.StringBuilder r12, int r13) {
        /*
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r11)
            r5 = r13
            r3 = 0
        L_0x0007:
            int r7 = r10 + r3
            char r0 = r9.charAt(r7)
            switch(r5) {
                case 0: goto L_0x003f;
                case 1: goto L_0x007e;
                case 2: goto L_0x00c5;
                default: goto L_0x0010;
            }
        L_0x0010:
            boolean r7 = T9(r0)
            if (r7 == 0) goto L_0x011c
            byte[] r7 = Tn
            byte r7 = r7[r0]
            char r7 = (char) r7
            r6.append(r7)
        L_0x001e:
            int r3 = r3 + 1
            if (r3 < r11) goto L_0x0007
            r1 = 0
            int r4 = r6.length()
            r2 = 0
        L_0x0028:
            if (r2 >= r4) goto L_0x012d
            int r7 = r2 % 2
            if (r7 == 0) goto L_0x0124
            r7 = 1
        L_0x002f:
            if (r7 == 0) goto L_0x0127
            int r7 = r1 * 30
            char r8 = r6.charAt(r2)
            int r7 = r7 + r8
            char r1 = (char) r7
            r12.append(r1)
        L_0x003c:
            int r2 = r2 + 1
            goto L_0x0028
        L_0x003f:
            boolean r7 = Tr(r0)
            if (r7 == 0) goto L_0x0056
            r7 = 32
            if (r0 != r7) goto L_0x004f
            r7 = 26
            r6.append(r7)
            goto L_0x001e
        L_0x004f:
            int r7 = r0 + -65
            char r7 = (char) r7
            r6.append(r7)
            goto L_0x001e
        L_0x0056:
            boolean r7 = Ty(r0)
            if (r7 == 0) goto L_0x0063
            r5 = 1
            r7 = 27
            r6.append(r7)
            goto L_0x0007
        L_0x0063:
            boolean r7 = Tn(r0)
            if (r7 == 0) goto L_0x0070
            r5 = 2
            r7 = 28
            r6.append(r7)
            goto L_0x0007
        L_0x0070:
            r7 = 29
            r6.append(r7)
            byte[] r7 = Tn
            byte r7 = r7[r0]
            char r7 = (char) r7
            r6.append(r7)
            goto L_0x001e
        L_0x007e:
            boolean r7 = Ty(r0)
            if (r7 == 0) goto L_0x0095
            r7 = 32
            if (r0 != r7) goto L_0x008e
            r7 = 26
            r6.append(r7)
            goto L_0x001e
        L_0x008e:
            int r7 = r0 + -97
            char r7 = (char) r7
            r6.append(r7)
            goto L_0x001e
        L_0x0095:
            boolean r7 = Tr(r0)
            if (r7 == 0) goto L_0x00a8
            r7 = 27
            r6.append(r7)
            int r7 = r0 + -65
            char r7 = (char) r7
            r6.append(r7)
            goto L_0x001e
        L_0x00a8:
            boolean r7 = Tn(r0)
            if (r7 == 0) goto L_0x00b6
            r5 = 2
            r7 = 28
            r6.append(r7)
            goto L_0x0007
        L_0x00b6:
            r7 = 29
            r6.append(r7)
            byte[] r7 = Tn
            byte r7 = r7[r0]
            char r7 = (char) r7
            r6.append(r7)
            goto L_0x001e
        L_0x00c5:
            boolean r7 = Tn(r0)
            if (r7 == 0) goto L_0x00d5
            byte[] r7 = Ty
            byte r7 = r7[r0]
            char r7 = (char) r7
            r6.append(r7)
            goto L_0x001e
        L_0x00d5:
            boolean r7 = Tr(r0)
            if (r7 == 0) goto L_0x00e3
            r5 = 0
            r7 = 28
            r6.append(r7)
            goto L_0x0007
        L_0x00e3:
            boolean r7 = Ty(r0)
            if (r7 == 0) goto L_0x00f1
            r5 = 1
            r7 = 27
            r6.append(r7)
            goto L_0x0007
        L_0x00f1:
            int r7 = r10 + r3
            int r7 = r7 + 1
            if (r7 >= r11) goto L_0x010d
            int r7 = r10 + r3
            int r7 = r7 + 1
            char r7 = r9.charAt(r7)
            boolean r7 = T9(r7)
            if (r7 == 0) goto L_0x010d
            r5 = 3
            r7 = 25
            r6.append(r7)
            goto L_0x0007
        L_0x010d:
            r7 = 29
            r6.append(r7)
            byte[] r7 = Tn
            byte r7 = r7[r0]
            char r7 = (char) r7
            r6.append(r7)
            goto L_0x001e
        L_0x011c:
            r5 = 0
            r7 = 29
            r6.append(r7)
            goto L_0x0007
        L_0x0124:
            r7 = 0
            goto L_0x002f
        L_0x0127:
            char r1 = r6.charAt(r2)
            goto L_0x003c
        L_0x012d:
            int r7 = r4 % 2
            if (r7 == 0) goto L_0x0139
            int r7 = r1 * 30
            int r7 = r7 + 29
            char r7 = (char) r7
            r12.append(r7)
        L_0x0139:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Tr.Tr.T9.T.TZ.T(java.lang.CharSequence, int, int, java.lang.StringBuilder, int):int");
    }

    private static void T(byte[] bytes, int startpos, int count, int startmode, StringBuilder sb) {
        if (count == 1 && startmode == 0) {
            sb.append(913);
        } else if (count % 6 == 0) {
            sb.append(924);
        } else {
            sb.append(901);
        }
        int idx = startpos;
        if (count >= 6) {
            char[] chars = new char[5];
            while ((startpos + count) - idx >= 6) {
                long t = 0;
                for (int i = 0; i < 6; i++) {
                    t = (t << 8) + ((long) (bytes[idx + i] & 255));
                }
                for (int i2 = 0; i2 < 5; i2++) {
                    chars[i2] = (char) ((int) (t % 900));
                    t /= 900;
                }
                for (int i3 = 4; i3 >= 0; i3--) {
                    sb.append(chars[i3]);
                }
                idx += 6;
            }
        }
        for (int i4 = idx; i4 < startpos + count; i4++) {
            sb.append((char) (bytes[i4] & 255));
        }
    }

    private static void T(String msg, int startpos, int count, StringBuilder sb) {
        int idx = 0;
        StringBuilder tmp = new StringBuilder((count / 3) + 1);
        BigInteger num900 = BigInteger.valueOf(900);
        BigInteger num0 = BigInteger.valueOf(0);
        while (idx < count) {
            tmp.setLength(0);
            int len = Math.min(44, count - idx);
            BigInteger bigint = new BigInteger("1" + msg.substring(startpos + idx, startpos + idx + len));
            do {
                tmp.append((char) bigint.mod(num900).intValue());
                bigint = bigint.divide(num900);
            } while (!bigint.equals(num0));
            for (int i = tmp.length() - 1; i >= 0; i--) {
                sb.append(tmp.charAt(i));
            }
            idx += len;
        }
    }

    private static boolean T(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private static boolean Tr(char ch) {
        return ch == ' ' || (ch >= 'A' && ch <= 'Z');
    }

    private static boolean Ty(char ch) {
        return ch == ' ' || (ch >= 'a' && ch <= 'z');
    }

    private static boolean Tn(char ch) {
        return Ty[ch] != -1;
    }

    private static boolean T9(char ch) {
        return Tn[ch] != -1;
    }

    private static boolean Tk(char ch) {
        return ch == 9 || ch == 10 || ch == 13 || (ch >= ' ' && ch <= '~');
    }

    private static int T(CharSequence msg, int startpos) {
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

    private static int Tr(CharSequence msg, int startpos) {
        int len = msg.length();
        int idx = startpos;
        while (idx < len) {
            char ch = msg.charAt(idx);
            int numericCount = 0;
            while (numericCount < 13 && T(ch) && idx < len) {
                numericCount++;
                idx++;
                if (idx < len) {
                    ch = msg.charAt(idx);
                }
            }
            if (numericCount < 13) {
                if (numericCount <= 0) {
                    if (!Tk(msg.charAt(idx))) {
                        break;
                    }
                    idx++;
                }
            } else {
                return (idx - startpos) - numericCount;
            }
        }
        return idx - startpos;
    }

    private static int T(String msg, int startpos, Charset encoding) throws TE {
        CharsetEncoder encoder = encoding.newEncoder();
        int len = msg.length();
        int idx = startpos;
        while (idx < len) {
            char ch = msg.charAt(idx);
            int numericCount = 0;
            while (numericCount < 13 && T(ch)) {
                numericCount++;
                int i = idx + numericCount;
                if (i >= len) {
                    break;
                }
                ch = msg.charAt(i);
            }
            if (numericCount >= 13) {
                return idx - startpos;
            }
            char ch2 = msg.charAt(idx);
            if (!encoder.canEncode(ch2)) {
                throw new TE("Non-encodable character detected: " + ch2 + " (Unicode: " + ch2 + ')');
            }
            idx++;
        }
        return idx - startpos;
    }

    private static void T(int eci, StringBuilder sb) throws TE {
        if (eci >= 0 && eci < 900) {
            sb.append(927);
            sb.append((char) eci);
        } else if (eci < 810900) {
            sb.append(926);
            sb.append((char) ((eci / CanCameraUI.BTN_CS75_WC_MODE) - 1));
            sb.append((char) (eci % CanCameraUI.BTN_CS75_WC_MODE));
        } else if (eci < 811800) {
            sb.append(925);
            sb.append((char) (810900 - eci));
        } else {
            throw new TE("ECI number not in valid range from 0..811799, but was " + eci);
        }
    }
}
