package com.T.T.Ty;

import com.T.T.T;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/* compiled from: Proguard */
public class Trk implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static Trk f225T = new Trk();

    /* JADX INFO: finally extract failed */
    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object == null) {
            out.T();
            return;
        }
        Map<?, ?> map = (Map) object;
        if (out.T(TrG.SortField) && !(map instanceof SortedMap) && !(map instanceof LinkedHashMap)) {
            try {
                map = new TreeMap<>(map);
            } catch (Exception e) {
            }
        }
        if (serializer.Tr(object)) {
            serializer.Ty(object);
            return;
        }
        TrB parent = serializer.Tr();
        serializer.T(parent, object, fieldName);
        try {
            out.T('{');
            serializer.Tn();
            Class<?> preClazz = null;
            Trh preWriter = null;
            boolean first = true;
            if (out.T(TrG.WriteClassName)) {
                out.Tr(T.f131T);
                out.T(object.getClass().getName());
                first = false;
            }
            for (Map.Entry entry : map.entrySet()) {
                Object value = entry.getValue();
                Object entryKey = entry.getKey();
                if (entryKey == null || (entryKey instanceof String)) {
                    String key = (String) entryKey;
                    if (TI.T(serializer, object, key) && TI.Ty(serializer, object, key, value)) {
                        String key2 = TI.Tr(serializer, object, key, value);
                        value = TI.T(serializer, object, key2, value);
                        if (value != null || serializer.T(TrG.WriteMapNullValue)) {
                            if (!first) {
                                out.T(',');
                            }
                            if (out.T(TrG.PrettyFormat)) {
                                serializer.Tk();
                            }
                            out.T(key2, true);
                        }
                    }
                } else {
                    if (!first) {
                        out.T(',');
                    }
                    serializer.Tn(entryKey);
                    out.T(':');
                }
                first = false;
                if (value == null) {
                    out.T();
                } else {
                    Class<?> clazz = value.getClass();
                    if (clazz == preClazz) {
                        preWriter.T(serializer, value, entryKey, (Type) null);
                    } else {
                        preClazz = clazz;
                        preWriter = serializer.T(clazz);
                        preWriter.T(serializer, value, entryKey, (Type) null);
                    }
                }
            }
            serializer.T(parent);
            serializer.T9();
            if (out.T(TrG.PrettyFormat) && map.size() > 0) {
                serializer.Tk();
            }
            out.T('}');
        } catch (Throwable th) {
            serializer.T(parent);
            throw th;
        }
    }
}
