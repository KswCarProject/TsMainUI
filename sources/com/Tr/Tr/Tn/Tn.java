package com.Tr.Tr.Tn;

import com.Tr.Tr.TE;
import com.Tr.Tr.Tr.Tr;
import com.Tr.Tr.Ty;
import com.lgb.canmodule.Can;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* compiled from: Proguard */
public final class Tn extends Tj {

    /* compiled from: Proguard */
    private enum T {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }

    public Tr T(String contents, com.Tr.Tr.T format, int width, int height, Map<Ty, ?> hints) throws TE {
        if (format == com.Tr.Tr.T.CODE_128) {
            return super.T(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode CODE_128, but got " + format);
    }

    public boolean[] T(String contents) {
        int patternIndex;
        int patternIndex2;
        int length = contents.length();
        if (length <= 0 || length > 80) {
            throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got " + length);
        }
        for (int i = 0; i < length; i++) {
            char c = contents.charAt(i);
            if (c < ' ' || c > '~') {
                switch (c) {
                    case Can.CAN_SITECHDEV_CW /*241*/:
                    case Can.CAN_MZD_LUOMU /*242*/:
                    case Can.CAN_MZD_TXB /*243*/:
                    case Can.CAN_BYD_M6_DJ /*244*/:
                        break;
                    default:
                        throw new IllegalArgumentException("Bad character in input: " + c);
                }
            }
        }
        Collection<int[]> patterns = new ArrayList<>();
        int checkSum = 0;
        int checkWeight = 1;
        int codeSet = 0;
        int position = 0;
        while (position < length) {
            int newCodeSet = T(contents, position, codeSet);
            if (newCodeSet == codeSet) {
                switch (contents.charAt(position)) {
                    case Can.CAN_SITECHDEV_CW /*241*/:
                        patternIndex2 = 102;
                        break;
                    case Can.CAN_MZD_LUOMU /*242*/:
                        patternIndex2 = 97;
                        break;
                    case Can.CAN_MZD_TXB /*243*/:
                        patternIndex2 = 96;
                        break;
                    case Can.CAN_BYD_M6_DJ /*244*/:
                        patternIndex2 = 100;
                        break;
                    default:
                        if (codeSet != 100) {
                            patternIndex2 = Integer.parseInt(contents.substring(position, position + 2));
                            position++;
                            break;
                        } else {
                            patternIndex2 = contents.charAt(position) - ' ';
                            break;
                        }
                }
                position++;
            } else {
                if (codeSet != 0) {
                    patternIndex = newCodeSet;
                } else if (newCodeSet == 100) {
                    patternIndex = 104;
                } else {
                    patternIndex = 105;
                }
                codeSet = newCodeSet;
            }
            patterns.add(Ty.f283T[patternIndex2]);
            checkSum += patternIndex2 * checkWeight;
            if (position != 0) {
                checkWeight++;
            }
        }
        patterns.add(Ty.f283T[checkSum % 103]);
        patterns.add(Ty.f283T[106]);
        int codeWidth = 0;
        for (int[] next : patterns) {
            int length2 = next.length;
            for (int i2 = 0; i2 < length2; i2++) {
                codeWidth += next[i2];
            }
        }
        boolean[] result = new boolean[codeWidth];
        int pos = 0;
        for (int[] pattern : patterns) {
            pos += Tr(result, pos, pattern, true);
        }
        return result;
    }

    private static T T(CharSequence value, int start) {
        int last = value.length();
        if (start >= last) {
            return T.UNCODABLE;
        }
        char c = value.charAt(start);
        if (c == 241) {
            return T.FNC_1;
        }
        if (c < '0' || c > '9') {
            return T.UNCODABLE;
        }
        if (start + 1 >= last) {
            return T.ONE_DIGIT;
        }
        char c2 = value.charAt(start + 1);
        if (c2 < '0' || c2 > '9') {
            return T.ONE_DIGIT;
        }
        return T.TWO_DIGITS;
    }

    private static int T(CharSequence value, int start, int oldCode) {
        T lookahead;
        T lookahead2;
        T lookahead3 = T(value, start);
        if (lookahead3 == T.UNCODABLE || lookahead3 == T.ONE_DIGIT) {
            return 100;
        }
        if (oldCode == 99) {
            return oldCode;
        }
        if (oldCode != 100) {
            if (lookahead3 == T.FNC_1) {
                lookahead3 = T(value, start + 1);
            }
            if (lookahead3 == T.TWO_DIGITS) {
                return 99;
            }
            return 100;
        } else if (lookahead3 == T.FNC_1 || (lookahead = T(value, start + 2)) == T.UNCODABLE || lookahead == T.ONE_DIGIT) {
            return oldCode;
        } else {
            if (lookahead != T.FNC_1) {
                int index = start + 4;
                while (true) {
                    lookahead2 = T(value, index);
                    if (lookahead2 != T.TWO_DIGITS) {
                        break;
                    }
                    index += 2;
                }
                if (lookahead2 == T.ONE_DIGIT) {
                    return 100;
                }
                return 99;
            } else if (T(value, start + 3) == T.TWO_DIGITS) {
                return 99;
            } else {
                return 100;
            }
        }
    }
}
