package com.T.T.Ty;

import com.T.T.Tn.T9;
import java.io.File;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

/* compiled from: Proguard */
public class TrO extends T9<Type, Trh> {

    /* renamed from: T  reason: collision with root package name */
    private static final TrO f220T = new TrO();

    public Trh T(Class<?> clazz) {
        return new Trr(clazz);
    }

    public static final TrO T() {
        return f220T;
    }

    public TrO() {
        this(1024);
    }

    public TrO(int tableSize) {
        super(tableSize);
        T(Boolean.class, Th.f207T);
        T(Character.class, TB.f196T);
        T(Byte.class, Tq.f211T);
        T(Short.class, Trt.f226T);
        T(Integer.class, T4.f189T);
        T(Long.class, Tr9.f215T);
        T(Float.class, Tw.f234T);
        T(Double.class, TD.f198T);
        T(BigDecimal.class, TE.f199T);
        T(BigInteger.class, T5.f190T);
        T(String.class, TrD.f218T);
        T(byte[].class, T6.f191T);
        T(short[].class, Tru.f227T);
        T(int[].class, T2.f187T);
        T(long[].class, Trn.f223T);
        T(float[].class, TL.f202T);
        T(double[].class, Tt.f231T);
        T(boolean[].class, Tv.f233T);
        T(char[].class, Tj.f208T);
        T(Object[].class, Tr5.f213T);
        T(Class.class, TO.f204T);
        T(SimpleDateFormat.class, TG.f201T);
        T(Locale.class, Trx.f228T);
        T(TimeZone.class, TrA.f216T);
        T(UUID.class, Trx.f228T);
        T(InetAddress.class, TC.f197T);
        T(Inet4Address.class, TC.f197T);
        T(Inet6Address.class, TC.f197T);
        T(InetSocketAddress.class, T8.f193T);
        T(File.class, TP.f205T);
        T(URI.class, Trx.f228T);
        T(URL.class, Trx.f228T);
        T(Appendable.class, T.f185T);
        T(StringBuffer.class, T.f185T);
        T(StringBuilder.class, T.f185T);
        T(StringWriter.class, T.f185T);
        T(Pattern.class, Tr6.f214T);
        T(Charset.class, Trx.f228T);
        T(AtomicBoolean.class, Ty.f236T);
        T(AtomicInteger.class, T9.f194T);
        T(AtomicLong.class, TZ.f206T);
        T(AtomicReference.class, Trj.f221T);
        T(AtomicIntegerArray.class, Tn.f210T);
        T(AtomicLongArray.class, Tk.f209T);
        T(WeakReference.class, Trj.f221T);
        T(SoftReference.class, Trj.f221T);
    }
}
