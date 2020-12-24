package com.T.T.Tn;

import com.T.T.T;
import com.T.T.T.Tr;
import com.T.T.T.Ty;
import com.T.T.T9;
import com.T.T.Tn;
import com.T.T.Tr.T.Ts;
import com.T.T.Tr.Tk;
import com.T.T.Tr.Tv;
import com.ts.main.common.MainSet;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: Proguard */
public class TZ {

    /* renamed from: T  reason: collision with root package name */
    private static ConcurrentMap<String, Class<?>> f132T = new ConcurrentHashMap();

    public static final String T(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    public static final Byte Tr(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Byte.valueOf(((Number) value).byteValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() != 0) {
                return Byte.valueOf(Byte.parseByte(strVal));
            }
            return null;
        }
        throw new Tn("can not cast to byte, value : " + value);
    }

    public static final Character Ty(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Character) {
            return (Character) value;
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }
            if (strVal.length() == 1) {
                return Character.valueOf(strVal.charAt(0));
            }
            throw new Tn("can not cast to byte, value : " + value);
        }
        throw new Tn("can not cast to byte, value : " + value);
    }

    public static final Short Tn(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Short.valueOf(((Number) value).shortValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() != 0) {
                return Short.valueOf(Short.parseShort(strVal));
            }
            return null;
        }
        throw new Tn("can not cast to short, value : " + value);
    }

    public static final BigDecimal T9(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        }
        String strVal = value.toString();
        if (strVal.length() == 0) {
            return null;
        }
        return new BigDecimal(strVal);
    }

    public static final BigInteger Tk(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigInteger) {
            return (BigInteger) value;
        }
        if ((value instanceof Float) || (value instanceof Double)) {
            return BigInteger.valueOf(((Number) value).longValue());
        }
        String strVal = value.toString();
        if (strVal.length() == 0) {
            return null;
        }
        return new BigInteger(strVal);
    }

    public static final Float TZ(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Float.valueOf(((Number) value).floatValue());
        }
        if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() != 0) {
                return Float.valueOf(Float.parseFloat(strVal));
            }
            return null;
        }
        throw new Tn("can not cast to float, value : " + value);
    }

    public static final Double TE(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Double.valueOf(((Number) value).doubleValue());
        }
        if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() != 0) {
                return Double.valueOf(Double.parseDouble(strVal));
            }
            return null;
        }
        throw new Tn("can not cast to double, value : " + value);
    }

    public static final Date T5(Object value) {
        String format;
        if (value == null) {
            return null;
        }
        if (value instanceof Calendar) {
            return ((Calendar) value).getTime();
        }
        if (value instanceof Date) {
            return (Date) value;
        }
        long longValue = -1;
        if (value instanceof Number) {
            longValue = ((Number) value).longValue();
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.indexOf(45) != -1) {
                if (strVal.length() == T.Ty.length()) {
                    format = T.Ty;
                } else if (strVal.length() == 10) {
                    format = "yyyy-MM-dd";
                } else if (strVal.length() == "yyyy-MM-dd HH:mm:ss".length()) {
                    format = "yyyy-MM-dd HH:mm:ss";
                } else {
                    format = "yyyy-MM-dd HH:mm:ss.SSS";
                }
                try {
                    return new SimpleDateFormat(format).parse(strVal);
                } catch (ParseException e) {
                    throw new Tn("can not cast to Date, value : " + strVal);
                }
            } else if (strVal.length() == 0) {
                return null;
            } else {
                longValue = Long.parseLong(strVal);
            }
        }
        if (longValue >= 0) {
            return new Date(longValue);
        }
        throw new Tn("can not cast to Date, value : " + value);
    }

    public static final java.sql.Date Tv(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Calendar) {
            return new java.sql.Date(((Calendar) value).getTimeInMillis());
        }
        if (value instanceof java.sql.Date) {
            return (java.sql.Date) value;
        }
        if (value instanceof Date) {
            return new java.sql.Date(((Date) value).getTime());
        }
        long longValue = 0;
        if (value instanceof Number) {
            longValue = ((Number) value).longValue();
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }
            longValue = Long.parseLong(strVal);
        }
        if (longValue > 0) {
            return new java.sql.Date(longValue);
        }
        throw new Tn("can not cast to Date, value : " + value);
    }

    public static final Timestamp Th(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Calendar) {
            return new Timestamp(((Calendar) value).getTimeInMillis());
        }
        if (value instanceof Timestamp) {
            return (Timestamp) value;
        }
        if (value instanceof Date) {
            return new Timestamp(((Date) value).getTime());
        }
        long longValue = 0;
        if (value instanceof Number) {
            longValue = ((Number) value).longValue();
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }
            longValue = Long.parseLong(strVal);
        }
        if (longValue > 0) {
            return new Timestamp(longValue);
        }
        throw new Tn("can not cast to Date, value : " + value);
    }

    public static final Long T6(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Long.valueOf(((Number) value).longValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }
            try {
                return Long.valueOf(Long.parseLong(strVal));
            } catch (NumberFormatException e) {
                Tk dateParser = new Tk(strVal);
                Calendar calendar = null;
                if (dateParser.Tr(false)) {
                    calendar = dateParser.TB();
                }
                dateParser.close();
                if (calendar != null) {
                    return Long.valueOf(calendar.getTimeInMillis());
                }
            }
        }
        throw new Tn("can not cast to long, value : " + value);
    }

    public static final Integer Te(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof Number) {
            return Integer.valueOf(((Number) value).intValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }
            return Integer.valueOf(Integer.parseInt(strVal));
        }
        throw new Tn("can not cast to int, value : " + value);
    }

    public static final Boolean Tq(Object value) {
        boolean z = true;
        if (value == null) {
            return null;
        }
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof Number) {
            if (((Number) value).intValue() != 1) {
                z = false;
            }
            return Boolean.valueOf(z);
        }
        if (value instanceof String) {
            String str = (String) value;
            if (str.length() == 0) {
                return null;
            }
            if ("true".equals(str)) {
                return Boolean.TRUE;
            }
            if ("false".equals(str)) {
                return Boolean.FALSE;
            }
            if (MainSet.SP_XPH5.equals(str)) {
                return Boolean.TRUE;
            }
        }
        throw new Tn("can not cast to int, value : " + value);
    }

    public static final <T> T T(Object obj, Class<T> clazz, Tv mapping) {
        Calendar calendar;
        if (obj == null) {
            return null;
        }
        if (clazz == null) {
            throw new IllegalArgumentException("clazz is null");
        } else if (clazz == obj.getClass()) {
            return obj;
        } else {
            if (obj instanceof Map) {
                if (clazz == Map.class) {
                    return obj;
                }
                Map map = (Map) obj;
                if (clazz != Object.class || map.containsKey(T.f128T)) {
                    return T((Map<String, Object>) (Map) obj, clazz, mapping);
                }
                return obj;
            } else if (!clazz.isArray() || !(obj instanceof Collection)) {
                if (clazz.isAssignableFrom(obj.getClass())) {
                    return obj;
                }
                if (clazz == Boolean.TYPE || clazz == Boolean.class) {
                    return Tq(obj);
                }
                if (clazz == Byte.TYPE || clazz == Byte.class) {
                    return Tr(obj);
                }
                if (clazz == Short.TYPE || clazz == Short.class) {
                    return Tn(obj);
                }
                if (clazz == Integer.TYPE || clazz == Integer.class) {
                    return Te(obj);
                }
                if (clazz == Long.TYPE || clazz == Long.class) {
                    return T6(obj);
                }
                if (clazz == Float.TYPE || clazz == Float.class) {
                    return TZ(obj);
                }
                if (clazz == Double.TYPE || clazz == Double.class) {
                    return TE(obj);
                }
                if (clazz == String.class) {
                    return T(obj);
                }
                if (clazz == BigDecimal.class) {
                    return T9(obj);
                }
                if (clazz == BigInteger.class) {
                    return Tk(obj);
                }
                if (clazz == Date.class) {
                    return T5(obj);
                }
                if (clazz == java.sql.Date.class) {
                    return Tv(obj);
                }
                if (clazz == Timestamp.class) {
                    return Th(obj);
                }
                if (clazz.isEnum()) {
                    return Tr(obj, clazz, mapping);
                }
                if (Calendar.class.isAssignableFrom(clazz)) {
                    Date date = T5(obj);
                    if (clazz == Calendar.class) {
                        calendar = Calendar.getInstance();
                    } else {
                        try {
                            calendar = clazz.newInstance();
                        } catch (Exception e) {
                            throw new Tn("can not cast to : " + clazz.getName(), e);
                        }
                    }
                    calendar.setTime(date);
                    return calendar;
                } else if ((obj instanceof String) && ((String) obj).length() == 0) {
                    return null;
                } else {
                    throw new Tn("can not cast to : " + clazz.getName());
                }
            } else {
                Collection<Object> collection = (Collection) obj;
                int index = 0;
                Object array = Array.newInstance(clazz.getComponentType(), collection.size());
                for (Object item : collection) {
                    Array.set(array, index, T(item, clazz.getComponentType(), mapping));
                    index++;
                }
                return array;
            }
        }
    }

    public static final <T> T Tr(Object obj, Class<T> clazz, Tv mapping) {
        try {
            if (obj instanceof String) {
                String name = (String) obj;
                if (name.length() == 0) {
                    return null;
                }
                return Enum.valueOf(clazz, name);
            }
            if (obj instanceof Number) {
                int ordinal = ((Number) obj).intValue();
                for (Object value : (Object[]) clazz.getMethod("values", new Class[0]).invoke((Object) null, new Object[0])) {
                    Enum e = (Enum) value;
                    if (e.ordinal() == ordinal) {
                        return e;
                    }
                }
            }
            throw new Tn("can not cast to : " + clazz.getName());
        } catch (Exception ex) {
            throw new Tn("can not cast to : " + clazz.getName(), ex);
        }
    }

    public static final <T> T T(Object obj, Type type, Tv mapping) {
        if (obj == null) {
            return null;
        }
        if (type instanceof Class) {
            return T(obj, (Class) type, mapping);
        }
        if (type instanceof ParameterizedType) {
            return T(obj, (ParameterizedType) type, mapping);
        }
        if ((obj instanceof String) && ((String) obj).length() == 0) {
            return null;
        }
        if (type instanceof TypeVariable) {
            return obj;
        }
        throw new Tn("can not cast to : " + type);
    }

    public static final <T> T T(Object obj, ParameterizedType type, Tv mapping) {
        Type rawTye = type.getRawType();
        if (rawTye == List.class || rawTye == ArrayList.class) {
            Type itemType = type.getActualTypeArguments()[0];
            if (obj instanceof Iterable) {
                List list = new ArrayList();
                for (Object item : (Iterable) obj) {
                    list.add(T(item, itemType, mapping));
                }
                return list;
            }
        }
        if (rawTye == Map.class || rawTye == HashMap.class) {
            Type keyType = type.getActualTypeArguments()[0];
            Type valueType = type.getActualTypeArguments()[1];
            if (obj instanceof Map) {
                Map map = new HashMap();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    map.put(T(entry.getKey(), keyType, mapping), T(entry.getValue(), valueType, mapping));
                }
                return map;
            }
        }
        if ((obj instanceof String) && ((String) obj).length() == 0) {
            return null;
        }
        if (type.getActualTypeArguments().length == 1 && (type.getActualTypeArguments()[0] instanceof WildcardType)) {
            return T(obj, rawTye, mapping);
        }
        throw new Tn("can not cast to : " + type);
    }

    public static final <T> T T(Map<String, Object> map, Class<T> clazz, Tv mapping) {
        T9 object;
        int lineNumber;
        if (clazz == StackTraceElement.class) {
            try {
                String declaringClass = (String) map.get("className");
                String methodName = (String) map.get("methodName");
                String fileName = (String) map.get("fileName");
                Number value = (Number) map.get("lineNumber");
                if (value == null) {
                    lineNumber = 0;
                } else {
                    lineNumber = value.intValue();
                }
                return new StackTraceElement(declaringClass, methodName, fileName, lineNumber);
            } catch (Exception e) {
                throw new Tn(e.getMessage(), e);
            }
        } else {
            Object iClassObject = map.get(T.f128T);
            if (iClassObject instanceof String) {
                String className = (String) iClassObject;
                Class<?> loadClazz = T(className);
                if (loadClazz == null) {
                    throw new ClassNotFoundException(className + " not found");
                } else if (!loadClazz.equals(clazz)) {
                    return T(map, loadClazz, mapping);
                }
            }
            if (clazz.isInterface()) {
                if (map instanceof T9) {
                    object = (T9) map;
                } else {
                    object = new T9(map);
                }
                return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{clazz}, object);
            }
            if (mapping == null) {
                mapping = Tv.T();
            }
            Map<String, Ts> setters = mapping.T((Class<?>) clazz);
            Constructor<T> constructor = clazz.getDeclaredConstructor(new Class[0]);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            T object2 = constructor.newInstance(new Object[0]);
            for (Map.Entry<String, Ts> entry : setters.entrySet()) {
                String key = entry.getKey();
                Ts fieldDeser = entry.getValue();
                if (map.containsKey(key)) {
                    Object value2 = map.get(key);
                    Method method = fieldDeser.Tr();
                    if (method != null) {
                        method.invoke(object2, new Object[]{T(value2, method.getGenericParameterTypes()[0], mapping)});
                    } else {
                        Field field = fieldDeser.Ty();
                        field.set(object2, T(value2, field.getGenericType(), mapping));
                    }
                }
            }
            return object2;
        }
    }

    static {
        T();
    }

    public static void T(String className, Class<?> clazz) {
        if (className == null) {
            className = clazz.getName();
        }
        f132T.put(className, clazz);
    }

    public static void T() {
        f132T.put("byte", Byte.TYPE);
        f132T.put("short", Short.TYPE);
        f132T.put("int", Integer.TYPE);
        f132T.put("long", Long.TYPE);
        f132T.put("float", Float.TYPE);
        f132T.put("double", Double.TYPE);
        f132T.put("boolean", Boolean.TYPE);
        f132T.put("char", Character.TYPE);
        f132T.put("[byte", byte[].class);
        f132T.put("[short", short[].class);
        f132T.put("[int", int[].class);
        f132T.put("[long", long[].class);
        f132T.put("[float", float[].class);
        f132T.put("[double", double[].class);
        f132T.put("[boolean", boolean[].class);
        f132T.put("[char", char[].class);
        f132T.put(HashMap.class.getName(), HashMap.class);
    }

    public static Class<?> T(String className) {
        if (className == null || className.length() == 0) {
            return null;
        }
        Class<?> clazz = (Class) f132T.get(className);
        if (clazz != null) {
            return clazz;
        }
        if (className.charAt(0) == '[') {
            return Array.newInstance(T(className.substring(1)), 0).getClass();
        }
        if (className.startsWith("L") && className.endsWith(";")) {
            return T(className.substring(1, className.length() - 1));
        }
        try {
            clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
            T(className, clazz);
            return clazz;
        } catch (Throwable th) {
            return clazz;
        }
    }

    public static List<Ty> T(Class<?> clazz, Map<String, String> aliasMap, boolean sorted) {
        String propertyName;
        Tr fieldAnnotation;
        String propertyName2;
        Tr fieldAnnotation2;
        Map<String, Ty> fieldInfoMap = new LinkedHashMap<>();
        for (Method method : clazz.getMethods()) {
            String methodName = method.getName();
            if (!Modifier.isStatic(method.getModifiers()) && !method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 0 && method.getReturnType() != ClassLoader.class && (!method.getName().equals("getMetaClass") || !method.getReturnType().getName().equals("groovy.lang.MetaClass"))) {
                Tr annotation = (Tr) method.getAnnotation(Tr.class);
                if (annotation == null) {
                    annotation = T(clazz, method);
                }
                if (annotation != null) {
                    if (annotation.Ty()) {
                        if (annotation.T().length() != 0) {
                            String propertyName3 = annotation.T();
                            if (aliasMap == null || (propertyName3 = aliasMap.get(propertyName3)) != null) {
                                fieldInfoMap.put(propertyName3, new Ty(propertyName3, method, (Field) null));
                            }
                        }
                    }
                }
                if (methodName.startsWith("get")) {
                    if (methodName.length() >= 4 && !methodName.equals("getClass")) {
                        char c3 = methodName.charAt(3);
                        if (Character.isUpperCase(c3)) {
                            propertyName2 = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                        } else if (c3 == '_') {
                            propertyName2 = methodName.substring(4);
                        } else if (c3 == 'f') {
                            propertyName2 = methodName.substring(3);
                        }
                        if (!T(clazz, propertyName2)) {
                            Field field = Tv.T(clazz, propertyName2);
                            if (field == null) {
                                field = Tv.T(clazz, propertyName2);
                            }
                            if (!(field == null || (fieldAnnotation2 = (Tr) field.getAnnotation(Tr.class)) == null)) {
                                if (fieldAnnotation2.Ty()) {
                                    if (fieldAnnotation2.T().length() != 0) {
                                        propertyName2 = fieldAnnotation2.T();
                                        if (aliasMap != null && (propertyName2 = aliasMap.get(propertyName2)) == null) {
                                        }
                                    }
                                }
                            }
                            if (aliasMap == null || (propertyName2 = aliasMap.get(propertyName2)) != null) {
                                fieldInfoMap.put(propertyName2, new Ty(propertyName2, method, field));
                            }
                        }
                    }
                }
                if (methodName.startsWith("is") && methodName.length() >= 3) {
                    char c2 = methodName.charAt(2);
                    if (Character.isUpperCase(c2)) {
                        propertyName = Character.toLowerCase(methodName.charAt(2)) + methodName.substring(3);
                    } else if (c2 == '_') {
                        propertyName = methodName.substring(3);
                    } else if (c2 == 'f') {
                        propertyName = methodName.substring(2);
                    }
                    Field field2 = Tv.T(clazz, propertyName);
                    if (!(field2 == null || (fieldAnnotation = (Tr) field2.getAnnotation(Tr.class)) == null)) {
                        if (fieldAnnotation.Ty()) {
                            if (fieldAnnotation.T().length() != 0) {
                                propertyName = fieldAnnotation.T();
                                if (aliasMap != null && (propertyName = aliasMap.get(propertyName)) == null) {
                                }
                            }
                        }
                    }
                    if (aliasMap == null || (propertyName = aliasMap.get(propertyName)) != null) {
                        fieldInfoMap.put(propertyName, new Ty(propertyName, method, field2));
                    }
                }
            }
        }
        for (Field field3 : clazz.getFields()) {
            if (!Modifier.isStatic(field3.getModifiers())) {
                Tr fieldAnnotation3 = (Tr) field3.getAnnotation(Tr.class);
                String propertyName4 = field3.getName();
                if (fieldAnnotation3 != null) {
                    if (fieldAnnotation3.Ty()) {
                        if (fieldAnnotation3.T().length() != 0) {
                            propertyName4 = fieldAnnotation3.T();
                        }
                    }
                }
                if ((aliasMap == null || (propertyName4 = aliasMap.get(propertyName4)) != null) && !fieldInfoMap.containsKey(propertyName4)) {
                    fieldInfoMap.put(propertyName4, new Ty(propertyName4, (Method) null, field3));
                }
            }
        }
        List<Ty> fieldInfoList = new ArrayList<>();
        boolean containsAll = false;
        String[] orders = null;
        Ty annotation2 = (Ty) clazz.getAnnotation(Ty.class);
        if (annotation2 != null) {
            orders = annotation2.T();
            if (orders == null || orders.length != fieldInfoMap.size()) {
                containsAll = false;
            } else {
                containsAll = true;
                String[] arr$ = orders;
                int len$ = arr$.length;
                int i$ = 0;
                while (true) {
                    if (i$ >= len$) {
                        break;
                    } else if (!fieldInfoMap.containsKey(arr$[i$])) {
                        containsAll = false;
                        break;
                    } else {
                        i$++;
                    }
                }
            }
        }
        if (containsAll) {
            for (String item : orders) {
                fieldInfoList.add(fieldInfoMap.get(item));
            }
        } else {
            for (Ty fieldInfo : fieldInfoMap.values()) {
                fieldInfoList.add(fieldInfo);
            }
            if (sorted) {
                Collections.sort(fieldInfoList);
            }
        }
        return fieldInfoList;
    }

    public static Tr T(Class<?> clazz, Method method) {
        Tr annotation;
        for (Class<?> interfaceClass : clazz.getInterfaces()) {
            for (Method interfaceMethod : interfaceClass.getMethods()) {
                if (interfaceMethod.getName().equals(method.getName()) && interfaceMethod.getParameterTypes().length == method.getParameterTypes().length) {
                    boolean match = true;
                    int i = 0;
                    while (true) {
                        if (i >= interfaceMethod.getParameterTypes().length) {
                            break;
                        } else if (!interfaceMethod.getParameterTypes()[i].equals(method.getParameterTypes()[i])) {
                            match = false;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (match && (annotation = (Tr) interfaceMethod.getAnnotation(Tr.class)) != null) {
                        return annotation;
                    }
                }
            }
        }
        return null;
    }

    private static boolean T(Class<?> clazz, String propertyName) {
        Ty jsonType = (Ty) clazz.getAnnotation(Ty.class);
        if (!(jsonType == null || jsonType.Tr() == null)) {
            for (String item : jsonType.Tr()) {
                if (propertyName.equalsIgnoreCase(item)) {
                    return true;
                }
            }
        }
        if (clazz.getSuperclass() == Object.class || clazz.getSuperclass() == null || !T((Class<?>) clazz.getSuperclass(), propertyName)) {
            return false;
        }
        return true;
    }

    public static Class<?> T(Type type) {
        if (type.getClass() == Class.class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return T(((ParameterizedType) type).getRawType());
        }
        return Object.class;
    }
}
