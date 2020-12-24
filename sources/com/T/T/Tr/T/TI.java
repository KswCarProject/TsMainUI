package com.T.T.Tr.T;

import com.T.T.Tr.Ty;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: Proguard */
public class TI implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final TI f150T = new TI();

    /* JADX WARNING: type inference failed for: r0v3, types: [T, java.lang.Object[]] */
    public <T> T T(Ty parser, Type type, Object fieldName) {
        if (!(type instanceof GenericArrayType)) {
            return parser.T(fieldName);
        }
        Type componentType = ((GenericArrayType) type).getGenericComponentType();
        if (componentType instanceof TypeVariable) {
            componentType = ((TypeVariable) componentType).getBounds()[0];
        }
        List<Object> list = new ArrayList<>();
        parser.T(componentType, (Collection) list);
        if (!(componentType instanceof Class)) {
            return list.toArray();
        }
        ? r0 = (Object[]) Array.newInstance((Class) componentType, list.size());
        list.toArray(r0);
        return r0;
    }

    public int T() {
        return 12;
    }
}
