package com.T.T.Tr;

/* compiled from: Proguard */
public enum Tn {
    AutoCloseSource,
    AllowComment,
    AllowUnQuotedFieldNames,
    AllowSingleQuotes,
    InternFieldNames,
    AllowISO8601DateFormat,
    AllowArbitraryCommas,
    UseBigDecimal,
    IgnoreNotMatch,
    SortFeidFastMatch,
    DisableASM,
    DisableCircularReferenceDetect,
    InitStringFieldAsEmpty;
    
    private final int Tq;

    public final int T() {
        return this.Tq;
    }

    public static boolean T(int features, Tn feature) {
        return (feature.T() & features) != 0;
    }

    public static int T(int features, Tn feature, boolean state) {
        if (state) {
            return features | feature.T();
        }
        return features & (feature.T() ^ -1);
    }
}
