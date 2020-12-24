package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

/* compiled from: Proguard */
public class Ts implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final Ts f230T = new Ts();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            Type elementType = null;
            if (serializer.T(TrG.WriteClassName) && (fieldType instanceof ParameterizedType)) {
                elementType = ((ParameterizedType) fieldType).getActualTypeArguments()[0];
            }
            Collection<?> collection = (Collection) object;
            TrB context = serializer.Tr();
            serializer.T(context, object, fieldName);
            if (serializer.T(TrG.WriteClassName)) {
                if (HashSet.class == collection.getClass()) {
                    out.append((CharSequence) "Set");
                } else if (TreeSet.class == collection.getClass()) {
                    out.append((CharSequence) "TreeSet");
                }
            }
            try {
                out.append('[');
                int i = 0;
                for (Object item : collection) {
                    try {
                        int i2 = i + 1;
                        if (i != 0) {
                            out.append(',');
                        }
                        if (item == null) {
                            out.T();
                            i = i2;
                        } else {
                            Class<?> clazz = item.getClass();
                            if (clazz == Integer.class) {
                                out.Tr(((Integer) item).intValue());
                                i = i2;
                            } else if (clazz == Long.class) {
                                out.T(((Long) item).longValue());
                                if (out.T(TrG.WriteClassName)) {
                                    out.T('L');
                                    i = i2;
                                } else {
                                    i = i2;
                                }
                            } else {
                                serializer.T(clazz).T(serializer, item, Integer.valueOf(i2 - 1), elementType);
                                i = i2;
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        int i3 = i;
                        serializer.T(context);
                        throw th;
                    }
                }
                out.append(']');
                serializer.T(context);
            } catch (Throwable th2) {
                th = th2;
                serializer.T(context);
                throw th;
            }
        } else if (out.T(TrG.WriteNullListAsEmpty)) {
            out.write("[]");
        } else {
            out.T();
        }
    }
}
