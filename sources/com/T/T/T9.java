package com.T.T;

import com.T.T.T.Tr;
import com.T.T.Tn.TZ;
import com.T.T.Tr.Tv;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: Proguard */
public class T9 extends T implements Ty, Serializable, Cloneable, InvocationHandler, Map<String, Object> {
    private final Map<String, Object> T9;

    public T9() {
        this(16, false);
    }

    public T9(Map<String, Object> map) {
        this.T9 = map;
    }

    public T9(int initialCapacity, boolean ordered) {
        if (ordered) {
            this.T9 = new LinkedHashMap(initialCapacity);
        } else {
            this.T9 = new HashMap(initialCapacity);
        }
    }

    public int size() {
        return this.T9.size();
    }

    public boolean isEmpty() {
        return this.T9.isEmpty();
    }

    public boolean containsKey(Object key) {
        return this.T9.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return this.T9.containsValue(value);
    }

    public Object get(Object key) {
        return this.T9.get(key);
    }

    /* renamed from: T */
    public Object put(String key, Object value) {
        return this.T9.put(key, value);
    }

    public void putAll(Map<? extends String, ? extends Object> m) {
        this.T9.putAll(m);
    }

    public void clear() {
        this.T9.clear();
    }

    public Object remove(Object key) {
        return this.T9.remove(key);
    }

    public Set<String> keySet() {
        return this.T9.keySet();
    }

    public Collection<Object> values() {
        return this.T9.values();
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return this.T9.entrySet();
    }

    public Object clone() {
        return new T9(new HashMap(this.T9));
    }

    public boolean equals(Object obj) {
        return this.T9.equals(obj);
    }

    public int hashCode() {
        return this.T9.hashCode();
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length == 1) {
            if (method.getReturnType() != Void.TYPE) {
                throw new Tn("illegal setter");
            }
            String name = null;
            Tr annotation = (Tr) method.getAnnotation(Tr.class);
            if (!(annotation == null || annotation.T().length() == 0)) {
                name = annotation.T();
            }
            if (name == null) {
                String name2 = method.getName();
                if (!name2.startsWith("set")) {
                    throw new Tn("illegal setter");
                }
                String name3 = name2.substring(3);
                if (name3.length() == 0) {
                    throw new Tn("illegal setter");
                }
                name = Character.toLowerCase(name3.charAt(0)) + name3.substring(1);
            }
            this.T9.put(name, args[0]);
            return null;
        } else if (parameterTypes.length != 0) {
            throw new UnsupportedOperationException(method.toGenericString());
        } else if (method.getReturnType() == Void.TYPE) {
            throw new Tn("illegal getter");
        } else {
            String name4 = null;
            Tr annotation2 = (Tr) method.getAnnotation(Tr.class);
            if (!(annotation2 == null || annotation2.T().length() == 0)) {
                name4 = annotation2.T();
            }
            if (name4 == null) {
                String name5 = method.getName();
                if (name5.startsWith("get")) {
                    String name6 = name5.substring(3);
                    if (name6.length() == 0) {
                        throw new Tn("illegal getter");
                    }
                    name4 = Character.toLowerCase(name6.charAt(0)) + name6.substring(1);
                } else if (name5.startsWith("is")) {
                    String name7 = name5.substring(2);
                    if (name7.length() == 0) {
                        throw new Tn("illegal getter");
                    }
                    name4 = Character.toLowerCase(name7.charAt(0)) + name7.substring(1);
                } else {
                    throw new Tn("illegal getter");
                }
            }
            return TZ.T(this.T9.get(name4), method.getGenericReturnType(), Tv.T());
        }
    }
}
