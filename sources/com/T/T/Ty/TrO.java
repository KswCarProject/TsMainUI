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
    private static final TrO f223T = new TrO();

    public Trh T(Class<?> clazz) {
        return new Trr(clazz);
    }

    public static final TrO T() {
        return f223T;
    }

    public TrO() {
        this(1024);
    }

    public TrO(int tableSize) {
        super(tableSize);
        T(Boolean.class, Th.f210T);
        T(Character.class, TB.f199T);
        T(Byte.class, Tq.f214T);
        T(Short.class, Trt.f229T);
        T(Integer.class, T4.f192T);
        T(Long.class, Tr9.f218T);
        T(Float.class, Tw.f237T);
        T(Double.class, TD.f201T);
        T(BigDecimal.class, TE.f202T);
        T(BigInteger.class, T5.f193T);
        T(String.class, TrD.f221T);
        T(byte[].class, T6.f194T);
        T(short[].class, Tru.f230T);
        T(int[].class, T2.f190T);
        T(long[].class, Trn.f226T);
        T(float[].class, TL.f205T);
        T(double[].class, Tt.f234T);
        T(boolean[].class, Tv.f236T);
        T(char[].class, Tj.f211T);
        T(Object[].class, Tr5.f216T);
        T(Class.class, TO.f207T);
        T(SimpleDateFormat.class, TG.f204T);
        T(Locale.class, Trx.f231T);
        T(TimeZone.class, TrA.f219T);
        T(UUID.class, Trx.f231T);
        T(InetAddress.class, TC.f200T);
        T(Inet4Address.class, TC.f200T);
        T(Inet6Address.class, TC.f200T);
        T(InetSocketAddress.class, T8.f196T);
        T(File.class, TP.f208T);
        T(URI.class, Trx.f231T);
        T(URL.class, Trx.f231T);
        T(Appendable.class, T.f188T);
        T(StringBuffer.class, T.f188T);
        T(StringBuilder.class, T.f188T);
        T(StringWriter.class, T.f188T);
        T(Pattern.class, Tr6.f217T);
        T(Charset.class, Trx.f231T);
        T(AtomicBoolean.class, Ty.f239T);
        T(AtomicInteger.class, T9.f197T);
        T(AtomicLong.class, TZ.f209T);
        T(AtomicReference.class, Trj.f224T);
        T(AtomicIntegerArray.class, Tn.f213T);
        T(AtomicLongArray.class, Tk.f212T);
        T(WeakReference.class, Trj.f224T);
        T(SoftReference.class, Trj.f224T);
    }
}
