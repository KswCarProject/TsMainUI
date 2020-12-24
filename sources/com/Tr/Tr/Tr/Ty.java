package com.Tr.Tr.Tr;

import com.google.zxing.common.StringUtils;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Proguard */
public enum Ty {
    Cp437((String) new int[]{0, 2}, (int) new String[0]),
    ISO8859_1((String) new int[]{1, 3}, (int) new String[]{"ISO-8859-1"}),
    ISO8859_2((String) 4, (int) new String[]{"ISO-8859-2"}),
    ISO8859_3((String) 5, (int) new String[]{"ISO-8859-3"}),
    ISO8859_4((String) 6, (int) new String[]{"ISO-8859-4"}),
    ISO8859_5((String) 7, (int) new String[]{"ISO-8859-5"}),
    ISO8859_6((String) 8, (int) new String[]{"ISO-8859-6"}),
    ISO8859_7((String) 9, (int) new String[]{"ISO-8859-7"}),
    ISO8859_8((String) 10, (int) new String[]{"ISO-8859-8"}),
    ISO8859_9((String) 11, (int) new String[]{"ISO-8859-9"}),
    ISO8859_10((String) 12, (int) new String[]{"ISO-8859-10"}),
    ISO8859_11((String) 13, (int) new String[]{"ISO-8859-11"}),
    ISO8859_13((String) 15, (int) new String[]{"ISO-8859-13"}),
    ISO8859_14((String) 16, (int) new String[]{"ISO-8859-14"}),
    ISO8859_15((String) 17, (int) new String[]{"ISO-8859-15"}),
    ISO8859_16((String) 18, (int) new String[]{"ISO-8859-16"}),
    SJIS((String) 20, (int) new String[]{"Shift_JIS"}),
    Cp1250((String) 21, (int) new String[]{"windows-1250"}),
    Cp1251((String) 22, (int) new String[]{"windows-1251"}),
    Cp1252((String) 23, (int) new String[]{"windows-1252"}),
    Cp1256((String) 24, (int) new String[]{"windows-1256"}),
    UnicodeBigUnmarked((String) 25, (int) new String[]{"UTF-16BE", "UnicodeBig"}),
    UTF8((String) 26, (int) new String[]{"UTF-8"}),
    ASCII((String) new int[]{27, 170}, (int) new String[]{"US-ASCII"}),
    Big5(28),
    GB18030((String) 29, (int) new String[]{StringUtils.GB2312, "EUC_CN", "GBK"}),
    EUC_KR((String) 30, (int) new String[]{"EUC-KR"});
    
    private static final Map<String, Ty> T0 = null;
    private static final Map<Integer, Ty> Tx = null;
    private final int[] TV;
    private final String[] Tb;

    static {
        Tx = new HashMap();
        T0 = new HashMap();
        for (Ty eci : values()) {
            for (int value : eci.TV) {
                Tx.put(Integer.valueOf(value), eci);
            }
            T0.put(eci.name(), eci);
            for (String name : eci.Tb) {
                T0.put(name, eci);
            }
        }
    }

    private Ty(int value) {
        this(r3, r4, new int[]{value}, new String[0]);
    }

    private Ty(int value, String... otherEncodingNames) {
        this.TV = new int[]{value};
        this.Tb = otherEncodingNames;
    }

    private Ty(int[] values, String... otherEncodingNames) {
        this.TV = values;
        this.Tb = otherEncodingNames;
    }

    public int T() {
        return this.TV[0];
    }

    public static Ty T(String name) {
        return T0.get(name);
    }
}