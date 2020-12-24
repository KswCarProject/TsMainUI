package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Enumeration;

/* compiled from: Proguard */
public class Tx implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static Tx f235T = new Tx();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            Type elementType = null;
            if (serializer.T(TrG.WriteClassName) && (fieldType instanceof ParameterizedType)) {
                elementType = ((ParameterizedType) fieldType).getActualTypeArguments()[0];
            }
            Enumeration<?> e = (Enumeration) object;
            TrB context = serializer.Tr();
            serializer.T(context, object, fieldName);
            try {
                out.append('[');
                int i = 0;
                while (e.hasMoreElements()) {
                    try {
                        Object item = e.nextElement();
                        int i2 = i + 1;
                        if (i != 0) {
                            out.append(',');
                        }
                        if (item == null) {
                            out.T();
                            i = i2;
                        } else {
                            serializer.T(item.getClass()).T(serializer, item, Integer.valueOf(i2 - 1), elementType);
                            i = i2;
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
