package com.T.T.Tr.T;

import com.T.T.Tn;
import com.T.T.Tn.Ty;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: Proguard */
public abstract class Ts {

    /* renamed from: T  reason: collision with root package name */
    protected final Ty f170T;
    protected final Class<?> Tr;

    public abstract void T(com.T.T.Tr.Ty ty, Object obj, Type type, Map<String, Object> map);

    public Ts(Class<?> clazz, Ty fieldInfo) {
        this.Tr = clazz;
        this.f170T = fieldInfo;
    }

    public Method Tr() {
        return this.f170T.Tn();
    }

    public Field Ty() {
        return this.f170T.T9();
    }

    public Class<?> Tn() {
        return this.f170T.T();
    }

    public Type T9() {
        return this.f170T.Tr();
    }

    public int T() {
        return 0;
    }

    public void T(Object object, boolean value) {
        T(object, (Object) Boolean.valueOf(value));
    }

    public void T(Object object, int value) {
        T(object, (Object) Integer.valueOf(value));
    }

    public void T(Object object, long value) {
        T(object, (Object) Long.valueOf(value));
    }

    public void T(Object object, String value) {
        T(object, (Object) value);
    }

    public void T(Object object, Object value) {
        Method method = this.f170T.Tn();
        if (method != null) {
            try {
                if (!this.f170T.Tk()) {
                    method.invoke(object, new Object[]{value});
                } else if (this.f170T.T() == AtomicInteger.class) {
                    AtomicInteger atomic = (AtomicInteger) method.invoke(object, new Object[0]);
                    if (atomic != null) {
                        atomic.set(((AtomicInteger) value).get());
                    }
                } else if (this.f170T.T() == AtomicLong.class) {
                    AtomicLong atomic2 = (AtomicLong) method.invoke(object, new Object[0]);
                    if (atomic2 != null) {
                        atomic2.set(((AtomicLong) value).get());
                    }
                } else if (this.f170T.T() == AtomicBoolean.class) {
                    AtomicBoolean atomic3 = (AtomicBoolean) method.invoke(object, new Object[0]);
                    if (atomic3 != null) {
                        atomic3.set(((AtomicBoolean) value).get());
                    }
                } else if (Map.class.isAssignableFrom(method.getReturnType())) {
                    Map map = (Map) method.invoke(object, new Object[0]);
                    if (map != null) {
                        map.putAll((Map) value);
                    }
                } else {
                    Collection collection = (Collection) method.invoke(object, new Object[0]);
                    if (collection != null) {
                        collection.addAll((Collection) value);
                    }
                }
            } catch (Exception e) {
                throw new Tn("set property error, " + this.f170T.Ty(), e);
            }
        } else {
            Field field = this.f170T.T9();
            if (field != null) {
                try {
                    field.set(object, value);
                } catch (Exception e2) {
                    throw new Tn("set property error, " + this.f170T.Ty(), e2);
                }
            }
        }
    }
}
