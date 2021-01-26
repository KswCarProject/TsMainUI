package com.T.T.Tr;

import com.T.T.T.Ty;
import com.T.T.Tn.T9;
import com.T.T.Tr;
import com.T.T.Tr.T.T0;
import com.T.T.Tr.T.T1;
import com.T.T.Tr.T.T2;
import com.T.T.Tr.T.T3;
import com.T.T.Tr.T.T5;
import com.T.T.Tr.T.T6;
import com.T.T.Tr.T.T7;
import com.T.T.Tr.T.T8;
import com.T.T.Tr.T.TA;
import com.T.T.Tr.T.TB;
import com.T.T.Tr.T.TC;
import com.T.T.Tr.T.TD;
import com.T.T.Tr.T.TE;
import com.T.T.Tr.T.TF;
import com.T.T.Tr.T.TG;
import com.T.T.Tr.T.TI;
import com.T.T.Tr.T.TM;
import com.T.T.Tr.T.TO;
import com.T.T.Tr.T.TP;
import com.T.T.Tr.T.TZ;
import com.T.T.Tr.T.Th;
import com.T.T.Tr.T.Tj;
import com.T.T.Tr.T.Tk;
import com.T.T.Tr.T.Tn;
import com.T.T.Tr.T.Tq;
import com.T.T.Tr.T.Tr5;
import com.T.T.Tr.T.Tr6;
import com.T.T.Tr.T.Tr9;
import com.T.T.Tr.T.TrE;
import com.T.T.Tr.T.TrZ;
import com.T.T.Tr.T.Trh;
import com.T.T.Tr.T.Trk;
import com.T.T.Tr.T.Trn;
import com.T.T.Tr.T.Trq;
import com.T.T.Tr.T.Trr;
import com.T.T.Tr.T.Trv;
import com.T.T.Tr.T.Try;
import com.T.T.Tr.T.Ts;
import com.T.T.Tr.T.Tt;
import com.T.T.Tr.T.Tu;
import com.T.T.Tr.T.Tw;
import com.T.T.Tr.T.Tx;
import java.io.Closeable;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

/* compiled from: Proguard */
public class Tv {
    private static Tv Ty = new Tv();

    /* renamed from: T  reason: collision with root package name */
    protected final Th f185T = new Th();
    private final T9<Type, T7> Tn = new T9<>();
    private final Set<Class<?>> Tr = new HashSet();

    public static Tv T() {
        return Ty;
    }

    public Tv() {
        this.Tr.add(Boolean.TYPE);
        this.Tr.add(Boolean.class);
        this.Tr.add(Character.TYPE);
        this.Tr.add(Character.class);
        this.Tr.add(Byte.TYPE);
        this.Tr.add(Byte.class);
        this.Tr.add(Short.TYPE);
        this.Tr.add(Short.class);
        this.Tr.add(Integer.TYPE);
        this.Tr.add(Integer.class);
        this.Tr.add(Long.TYPE);
        this.Tr.add(Long.class);
        this.Tr.add(Float.TYPE);
        this.Tr.add(Float.class);
        this.Tr.add(Double.TYPE);
        this.Tr.add(Double.class);
        this.Tr.add(BigInteger.class);
        this.Tr.add(BigDecimal.class);
        this.Tr.add(String.class);
        this.Tr.add(Date.class);
        this.Tr.add(java.sql.Date.class);
        this.Tr.add(Time.class);
        this.Tr.add(Timestamp.class);
        this.Tn.T(SimpleDateFormat.class, Tj.f158T);
        this.Tn.T(Timestamp.class, Trv.f171T);
        this.Tn.T(java.sql.Date.class, Try.f172T);
        this.Tn.T(Time.class, TrE.f166T);
        this.Tn.T(Date.class, TF.f151T);
        this.Tn.T(Calendar.class, TE.f150T);
        this.Tn.T(com.T.T.T9.class, TM.f154T);
        this.Tn.T(Tr.class, T0.f140T);
        this.Tn.T(Map.class, T2.f142T);
        this.Tn.T(HashMap.class, T2.f142T);
        this.Tn.T(LinkedHashMap.class, T2.f142T);
        this.Tn.T(TreeMap.class, T2.f142T);
        this.Tn.T(ConcurrentMap.class, T2.f142T);
        this.Tn.T(ConcurrentHashMap.class, T2.f142T);
        this.Tn.T(Collection.class, Tq.f161T);
        this.Tn.T(List.class, Tq.f161T);
        this.Tn.T(ArrayList.class, Tq.f161T);
        this.Tn.T(Object.class, TI.f153T);
        this.Tn.T(String.class, Tr9.f165T);
        this.Tn.T(Character.TYPE, com.T.T.Tr.T.Tv.f176T);
        this.Tn.T(Character.class, com.T.T.Tr.T.Tv.f176T);
        this.Tn.T(Byte.TYPE, T3.f143T);
        this.Tn.T(Byte.class, T3.f143T);
        this.Tn.T(Short.TYPE, T3.f143T);
        this.Tn.T(Short.class, T3.f143T);
        this.Tn.T(Integer.TYPE, TA.f147T);
        this.Tn.T(Integer.class, TA.f147T);
        this.Tn.T(Long.TYPE, TC.f148T);
        this.Tn.T(Long.class, TC.f148T);
        this.Tn.T(BigInteger.class, com.T.T.Tr.T.T9.f146T);
        this.Tn.T(BigDecimal.class, Tn.f160T);
        this.Tn.T(Float.TYPE, Tu.f175T);
        this.Tn.T(Float.class, Tu.f175T);
        this.Tn.T(Double.TYPE, T3.f143T);
        this.Tn.T(Double.class, T3.f143T);
        this.Tn.T(Boolean.TYPE, Tk.f159T);
        this.Tn.T(Boolean.class, Tk.f159T);
        this.Tn.T(Class.class, T6.f145T);
        this.Tn.T(char[].class, T5.f144T);
        this.Tn.T(AtomicBoolean.class, Tk.f159T);
        this.Tn.T(AtomicInteger.class, TA.f147T);
        this.Tn.T(AtomicLong.class, TC.f148T);
        this.Tn.T(AtomicReference.class, Trr.f170T);
        this.Tn.T(WeakReference.class, Trr.f170T);
        this.Tn.T(SoftReference.class, Trr.f170T);
        this.Tn.T(UUID.class, Trq.f169T);
        this.Tn.T(TimeZone.class, Tr5.f163T);
        this.Tn.T(Locale.class, Tw.f177T);
        this.Tn.T(InetAddress.class, Tt.f174T);
        this.Tn.T(Inet4Address.class, Tt.f174T);
        this.Tn.T(Inet6Address.class, Tt.f174T);
        this.Tn.T(InetSocketAddress.class, TD.f149T);
        this.Tn.T(File.class, TG.f152T);
        this.Tn.T(URI.class, Trh.f167T);
        this.Tn.T(URL.class, Tr6.f164T);
        this.Tn.T(Pattern.class, T1.f141T);
        this.Tn.T(Charset.class, Th.f157T);
        this.Tn.T(Number.class, T3.f143T);
        this.Tn.T(AtomicIntegerArray.class, com.T.T.Tr.T.Tr.f162T);
        this.Tn.T(AtomicLongArray.class, com.T.T.Tr.T.Tr.f162T);
        this.Tn.T(StackTraceElement.class, Trn.f168T);
        this.Tn.T(Serializable.class, TI.f153T);
        this.Tn.T(Cloneable.class, TI.f153T);
        this.Tn.T(Comparable.class, TI.f153T);
        this.Tn.T(Closeable.class, TI.f153T);
    }

    public Th Tr() {
        return this.f185T;
    }

    public T7 T(Type type) {
        T7 derializer = this.Tn.T(type);
        if (derializer != null) {
            return derializer;
        }
        if (type instanceof Class) {
            return T((Class<?>) (Class) type, type);
        }
        if (!(type instanceof ParameterizedType)) {
            return TI.f153T;
        }
        Type rawType = ((ParameterizedType) type).getRawType();
        if (rawType instanceof Class) {
            return T((Class<?>) (Class) rawType, type);
        }
        return T(rawType);
    }

    public T7 T(Class<?> clazz, Type type) {
        T7 derializer;
        Class<?> mappingTo;
        T7 derializer2 = this.Tn.T(type);
        if (derializer2 != null) {
            return derializer2;
        }
        if (type == null) {
            type = clazz;
        }
        T7 derializer3 = this.Tn.T(type);
        if (derializer3 != null) {
            return derializer3;
        }
        Ty annotation = (Ty) clazz.getAnnotation(Ty.class);
        if (annotation != null && (mappingTo = annotation.Ty()) != Void.class) {
            return T(mappingTo, (Type) mappingTo);
        }
        if ((type instanceof WildcardType) || (type instanceof TypeVariable) || (type instanceof ParameterizedType)) {
            derializer3 = this.Tn.T(clazz);
        }
        if (derializer3 != null) {
            return derializer3;
        }
        T7 derializer4 = this.Tn.T(type);
        if (derializer4 != null) {
            return derializer4;
        }
        if (clazz.isEnum()) {
            derializer = new TO(clazz);
        } else if (clazz.isArray()) {
            return com.T.T.Tr.T.Tr.f162T;
        } else {
            if (clazz == Set.class || clazz == HashSet.class || clazz == Collection.class || clazz == List.class || clazz == ArrayList.class) {
                derializer = Tq.f161T;
            } else if (Collection.class.isAssignableFrom(clazz)) {
                derializer = Tq.f161T;
            } else if (Map.class.isAssignableFrom(clazz)) {
                derializer = T2.f142T;
            } else if (Throwable.class.isAssignableFrom(clazz)) {
                derializer = new TrZ(this, clazz);
            } else {
                derializer = Tr(clazz, type);
            }
        }
        T(type, derializer);
        return derializer;
    }

    public T7 Tr(Class<?> clazz, Type type) {
        return new TP(this, clazz, type);
    }

    public Ts T(Tv mapping, Class<?> clazz, com.T.T.Tn.Ty fieldInfo) {
        Class<?> fieldClass = fieldInfo.T();
        if (fieldClass == Boolean.TYPE || fieldClass == Boolean.class) {
            return new TZ(mapping, clazz, fieldInfo);
        }
        if (fieldClass == Integer.TYPE || fieldClass == Integer.class) {
            return new Tx(mapping, clazz, fieldInfo);
        }
        if (fieldClass == Long.TYPE || fieldClass == Long.class) {
            return new T8(mapping, clazz, fieldInfo);
        }
        if (fieldClass == String.class) {
            return new Trk(mapping, clazz, fieldInfo);
        }
        if (fieldClass == List.class || fieldClass == ArrayList.class) {
            return new com.T.T.Tr.T.Ty(mapping, clazz, fieldInfo);
        }
        return new TB(mapping, clazz, fieldInfo);
    }

    public void T(Type type, T7 deserializer) {
        this.Tn.T(type, deserializer);
    }

    public T7 T(com.T.T.Tn.Ty fieldInfo) {
        return T(fieldInfo.T(), fieldInfo.Tr());
    }

    public static Field T(Class<?> clazz, String fieldName) {
        Field field = Tr(clazz, fieldName);
        if (field == null) {
            field = Tr(clazz, "_" + fieldName);
        }
        if (field == null) {
            return Tr(clazz, "m_" + fieldName);
        }
        return field;
    }

    private static Field Tr(Class<?> clazz, String fieldName) {
        for (Field item : clazz.getDeclaredFields()) {
            if (fieldName.equals(item.getName())) {
                return item;
            }
        }
        if (clazz.getSuperclass() == null || clazz.getSuperclass() == Object.class) {
            return null;
        }
        return T((Class<?>) clazz.getSuperclass(), fieldName);
    }

    public Map<String, Ts> T(Class<?> clazz) {
        T7 deserizer = T((Type) clazz);
        if (deserizer instanceof TP) {
            return ((TP) deserizer).Tr();
        }
        return Collections.emptyMap();
    }
}
