package com.txznet.sdk.tongting;

/* compiled from: Proguard */
public class TongTingUtils {

    /* renamed from: T  reason: collision with root package name */
    private static int f890T = 0;
    private static int Tn = 3;
    private static int Tr = 1;
    private static int Ty = 2;

    public static int getFavourState(int support_fav, int favour, int support_sub, int subscribe) {
        int a = support_fav << f890T;
        int b = favour << Tr;
        int c = support_sub << Ty;
        return a + b + c + (subscribe << Tn);
    }

    private static int T(int num, int bit) {
        return (num >> bit) & 1;
    }

    public static boolean supportFavour(int favourState) {
        return T(favourState, f890T) == 1;
    }

    public static boolean supportSubscribe(int favourState) {
        return T(favourState, Ty) == 1;
    }

    public static boolean isFavour(int favourState) {
        return T(favourState, Tr) == 1;
    }

    public static boolean isSubscribe(int favourState) {
        return T(favourState, Tn) == 1;
    }
}
