package com.txznet.comm.Tr.Tr;

/* compiled from: Proguard */
public class Ty {

    /* renamed from: T  reason: collision with root package name */
    private static double f405T = 52.35987755982988d;

    public static double[] T(double lat, double lng) {
        double x = lng - 0.0065d;
        double y = lat - 0.006d;
        double z = Math.sqrt((x * x) + (y * y)) - (2.0E-5d * Math.sin(f405T * y));
        double theta = Math.atan2(y, x) - (3.0E-6d * Math.cos(f405T * x));
        return new double[]{z * Math.sin(theta), z * Math.cos(theta)};
    }
}
