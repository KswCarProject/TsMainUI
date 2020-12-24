package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/* compiled from: Proguard */
public final class Try implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final Try f229T = new Try();

    /* JADX INFO: finally extract failed */
    public final void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        boolean writeClassName = serializer.T(TrG.WriteClassName);
        Trs out = serializer.Tv();
        Type elementType = null;
        if (writeClassName && (fieldType instanceof ParameterizedType)) {
            elementType = ((ParameterizedType) fieldType).getActualTypeArguments()[0];
        }
        if (object != null) {
            List<?> list = (List) object;
            int size = list.size();
            int end = size - 1;
            if (end == -1) {
                out.append((CharSequence) "[]");
                return;
            }
            TrB context = serializer.Tr();
            serializer.T(context, object, fieldName);
            if (size > 1) {
                try {
                    if (out.T(TrG.PrettyFormat)) {
                        out.append('[');
                        serializer.Tn();
                        for (int i = 0; i < size; i++) {
                            if (i != 0) {
                                out.append(',');
                            }
                            serializer.Tk();
                            Object item = list.get(i);
                            if (item == null) {
                                serializer.Tv().T();
                            } else if (serializer.Tr(item)) {
                                serializer.Ty(item);
                            } else {
                                Trh itemSerializer = serializer.T(item.getClass());
                                serializer.T(new TrB(context, object, fieldName));
                                itemSerializer.T(serializer, item, Integer.valueOf(i), elementType);
                            }
                        }
                        serializer.T9();
                        serializer.Tk();
                        out.append(']');
                        serializer.T(context);
                        return;
                    }
                } catch (Throwable th) {
                    serializer.T(context);
                    throw th;
                }
            }
            out.append('[');
            for (int i2 = 0; i2 < end; i2++) {
                Object item2 = list.get(i2);
                if (item2 == null) {
                    out.append((CharSequence) "null,");
                } else {
                    Class<?> clazz = item2.getClass();
                    if (clazz == Integer.class) {
                        out.T(((Integer) item2).intValue(), ',');
                    } else if (clazz == Long.class) {
                        long val = ((Long) item2).longValue();
                        if (writeClassName) {
                            out.T(val, 'L');
                            out.T(',');
                        } else {
                            out.T(val, ',');
                        }
                    } else {
                        serializer.T(new TrB(context, object, fieldName));
                        if (serializer.Tr(item2)) {
                            serializer.Ty(item2);
                        } else {
                            serializer.T(item2.getClass()).T(serializer, item2, Integer.valueOf(i2), elementType);
                        }
                        out.append(',');
                    }
                }
            }
            Object item3 = list.get(end);
            if (item3 == null) {
                out.append((CharSequence) "null]");
            } else {
                Class<?> clazz2 = item3.getClass();
                if (clazz2 == Integer.class) {
                    out.T(((Integer) item3).intValue(), ']');
                } else if (clazz2 != Long.class) {
                    serializer.T(new TrB(context, object, fieldName));
                    if (serializer.Tr(item3)) {
                        serializer.Ty(item3);
                    } else {
                        serializer.T(item3.getClass()).T(serializer, item3, Integer.valueOf(end), elementType);
                    }
                    out.append(']');
                } else if (writeClassName) {
                    out.T(((Long) item3).longValue(), 'L');
                    out.T(']');
                } else {
                    out.T(((Long) item3).longValue(), ']');
                }
            }
            serializer.T(context);
        } else if (out.T(TrG.WriteNullListAsEmpty)) {
            out.write("[]");
        } else {
            out.T();
        }
    }
}
