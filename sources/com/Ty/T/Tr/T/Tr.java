package com.Ty.T.Tr.T;

/* compiled from: Proguard */
public class Tr {

    /* renamed from: T  reason: collision with root package name */
    private final T f318T;
    private final Throwable Tr;

    /* compiled from: Proguard */
    public enum T {
        IO_ERROR,
        DECODING_ERROR,
        NETWORK_DENIED,
        OUT_OF_MEMORY,
        UNKNOWN
    }

    public Tr(T type, Throwable cause) {
        this.f318T = type;
        this.Tr = cause;
    }
}
