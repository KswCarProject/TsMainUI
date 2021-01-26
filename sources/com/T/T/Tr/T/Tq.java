package com.T.T.Tr.T;

import com.T.T.Tn;
import com.T.T.Tr.Ty;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/* compiled from: Proguard */
public class Tq implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Tq f161T = new Tq();

    public <T> T T(Ty parser, Type type, Object fieldName) {
        Collection list;
        Type itemType;
        if (parser.Th().Tn() == 8) {
            parser.Th().T(16);
            return null;
        }
        Class<?> rawClass = T(type);
        if (rawClass == AbstractCollection.class) {
            list = new ArrayList();
        } else if (rawClass.isAssignableFrom(HashSet.class)) {
            list = new HashSet();
        } else if (rawClass.isAssignableFrom(LinkedHashSet.class)) {
            list = new LinkedHashSet();
        } else if (rawClass.isAssignableFrom(TreeSet.class)) {
            list = new TreeSet();
        } else if (rawClass.isAssignableFrom(ArrayList.class)) {
            list = new ArrayList();
        } else {
            try {
                list = (Collection) rawClass.newInstance();
            } catch (Exception e) {
                throw new Tn("create instane error, class " + rawClass.getName());
            }
        }
        if (type instanceof ParameterizedType) {
            itemType = ((ParameterizedType) type).getActualTypeArguments()[0];
        } else {
            itemType = Object.class;
        }
        parser.T(itemType, list, fieldName);
        return list;
    }

    public Class<?> T(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return T(((ParameterizedType) type).getRawType());
        }
        throw new Tn("TODO");
    }

    public int T() {
        return 14;
    }
}
