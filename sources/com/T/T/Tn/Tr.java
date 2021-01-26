package com.T.T.Tn;

import com.T.T.T.T;
import com.T.T.Tn;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: Proguard */
public class Tr {

    /* renamed from: T  reason: collision with root package name */
    private final Class<?> f138T;
    private final List<Ty> T9 = new ArrayList();
    private Method Tn;
    private Constructor<?> Tr;
    private Constructor<?> Ty;

    public Tr(Class<?> clazz) {
        this.f138T = clazz;
    }

    public Constructor<?> T() {
        return this.Tr;
    }

    public void T(Constructor<?> defaultConstructor) {
        this.Tr = defaultConstructor;
    }

    public Constructor<?> Tr() {
        return this.Ty;
    }

    public void Tr(Constructor<?> createConstructor) {
        this.Ty = createConstructor;
    }

    public Method Ty() {
        return this.Tn;
    }

    public void T(Method factoryMethod) {
        this.Tn = factoryMethod;
    }

    public List<Ty> Tn() {
        return this.T9;
    }

    public Ty T(String propertyName) {
        for (Ty item : this.T9) {
            if (item.Ty().equals(propertyName)) {
                return item;
            }
        }
        return null;
    }

    public boolean T(Ty field) {
        for (Ty item : this.T9) {
            if (item.Ty().equals(field.Ty())) {
                return false;
            }
        }
        this.T9.add(field);
        return true;
    }

    public static Tr T(Class<?> clazz, Type type) {
        String propertyName;
        com.T.T.T.Tr fieldAnnotation;
        Tr tr = new Tr(clazz);
        Constructor<?> defaultConstructor = T(clazz);
        if (defaultConstructor != null) {
            defaultConstructor.setAccessible(true);
            tr.T(defaultConstructor);
        } else if (defaultConstructor == null && !clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers())) {
            Constructor<?> creatorConstructor = Tr(clazz);
            if (creatorConstructor != null) {
                creatorConstructor.setAccessible(true);
                tr.Tr(creatorConstructor);
                for (int i = 0; i < creatorConstructor.getParameterTypes().length; i++) {
                    com.T.T.T.Tr fieldAnnotation2 = null;
                    Annotation[] arr$ = creatorConstructor.getParameterAnnotations()[i];
                    int len$ = arr$.length;
                    int i$ = 0;
                    while (true) {
                        if (i$ >= len$) {
                            break;
                        }
                        Annotation paramAnnotation = arr$[i$];
                        if (paramAnnotation instanceof com.T.T.T.Tr) {
                            fieldAnnotation2 = (com.T.T.T.Tr) paramAnnotation;
                            break;
                        }
                        i$++;
                    }
                    if (fieldAnnotation2 == null) {
                        throw new Tn("illegal json creator");
                    }
                    tr.T(new Ty(fieldAnnotation2.T(), clazz, creatorConstructor.getParameterTypes()[i], creatorConstructor.getGenericParameterTypes()[i], T(clazz, fieldAnnotation2.T())));
                }
            } else {
                Method factoryMethod = Ty(clazz);
                if (factoryMethod != null) {
                    factoryMethod.setAccessible(true);
                    tr.T(factoryMethod);
                    for (int i2 = 0; i2 < factoryMethod.getParameterTypes().length; i2++) {
                        com.T.T.T.Tr fieldAnnotation3 = null;
                        Annotation[] arr$2 = factoryMethod.getParameterAnnotations()[i2];
                        int len$2 = arr$2.length;
                        int i$2 = 0;
                        while (true) {
                            if (i$2 >= len$2) {
                                break;
                            }
                            Annotation paramAnnotation2 = arr$2[i$2];
                            if (paramAnnotation2 instanceof com.T.T.T.Tr) {
                                fieldAnnotation3 = (com.T.T.T.Tr) paramAnnotation2;
                                break;
                            }
                            i$2++;
                        }
                        if (fieldAnnotation3 == null) {
                            throw new Tn("illegal json creator");
                        }
                        tr.T(new Ty(fieldAnnotation3.T(), clazz, factoryMethod.getParameterTypes()[i2], factoryMethod.getGenericParameterTypes()[i2], T(clazz, fieldAnnotation3.T())));
                    }
                } else {
                    throw new Tn("default constructor not found. " + clazz);
                }
            }
            return tr;
        }
        Method[] arr$3 = clazz.getMethods();
        int len$3 = arr$3.length;
        for (int i$3 = 0; i$3 < len$3; i$3++) {
            Method method = arr$3[i$3];
            String methodName = method.getName();
            if (methodName.length() >= 4 && !Modifier.isStatic(method.getModifiers()) && ((method.getReturnType().equals(Void.TYPE) || method.getReturnType().equals(clazz)) && method.getParameterTypes().length == 1)) {
                com.T.T.T.Tr annotation = (com.T.T.T.Tr) method.getAnnotation(com.T.T.T.Tr.class);
                if (annotation == null) {
                    annotation = TZ.T(clazz, method);
                }
                if (annotation != null) {
                    if (annotation.Tn()) {
                        if (annotation.T().length() != 0) {
                            tr.T(new Ty(annotation.T(), method, (Field) null, clazz, type));
                            method.setAccessible(true);
                        }
                    }
                }
                if (methodName.startsWith("set")) {
                    char c3 = methodName.charAt(3);
                    if (Character.isUpperCase(c3)) {
                        propertyName = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                    } else if (c3 == '_') {
                        propertyName = methodName.substring(4);
                    } else if (c3 == 'f') {
                        propertyName = methodName.substring(3);
                    }
                    Field field = T(clazz, propertyName);
                    if (field == null || (fieldAnnotation = (com.T.T.T.Tr) field.getAnnotation(com.T.T.T.Tr.class)) == null || fieldAnnotation.T().length() == 0) {
                        tr.T(new Ty(propertyName, method, (Field) null, clazz, type));
                        method.setAccessible(true);
                    } else {
                        tr.T(new Ty(fieldAnnotation.T(), method, field, clazz, type));
                    }
                }
            }
        }
        Field[] arr$4 = clazz.getFields();
        int len$4 = arr$4.length;
        int i$4 = 0;
        while (true) {
            int i$5 = i$4;
            if (i$5 >= len$4) {
                break;
            }
            Field field2 = arr$4[i$5];
            if (!Modifier.isStatic(field2.getModifiers())) {
                boolean contains = false;
                for (Ty item : tr.Tn()) {
                    if (item.Ty().equals(field2.getName())) {
                        contains = true;
                    }
                }
                if (!contains) {
                    String propertyName2 = field2.getName();
                    com.T.T.T.Tr fieldAnnotation4 = (com.T.T.T.Tr) field2.getAnnotation(com.T.T.T.Tr.class);
                    if (!(fieldAnnotation4 == null || fieldAnnotation4.T().length() == 0)) {
                        propertyName2 = fieldAnnotation4.T();
                    }
                    tr.T(new Ty(propertyName2, (Method) null, field2, clazz, type));
                }
            }
            i$4 = i$5 + 1;
        }
        Method[] arr$5 = clazz.getMethods();
        int len$5 = arr$5.length;
        for (int i$6 = 0; i$6 < len$5; i$6++) {
            Method method2 = arr$5[i$6];
            String methodName2 = method2.getName();
            if (methodName2.length() >= 4 && !Modifier.isStatic(method2.getModifiers()) && methodName2.startsWith("get") && Character.isUpperCase(methodName2.charAt(3)) && method2.getParameterTypes().length == 0 && (Collection.class.isAssignableFrom(method2.getReturnType()) || Map.class.isAssignableFrom(method2.getReturnType()) || AtomicBoolean.class == method2.getReturnType() || AtomicInteger.class == method2.getReturnType() || AtomicLong.class == method2.getReturnType())) {
                String propertyName3 = Character.toLowerCase(methodName2.charAt(3)) + methodName2.substring(4);
                if (tr.T(propertyName3) == null) {
                    tr.T(new Ty(propertyName3, method2, (Field) null, clazz, type));
                    method2.setAccessible(true);
                }
            }
        }
        return tr;
    }

    public static Field T(Class<?> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (Exception e) {
            return null;
        }
    }

    public static Constructor<?> T(Class<?> clazz) {
        if (Modifier.isAbstract(clazz.getModifiers())) {
            return null;
        }
        Constructor<?> defaultConstructor = null;
        Constructor<?>[] arr$ = clazz.getDeclaredConstructors();
        int len$ = arr$.length;
        int i$ = 0;
        while (true) {
            if (i$ >= len$) {
                break;
            }
            Constructor<?> constructor = arr$[i$];
            if (constructor.getParameterTypes().length == 0) {
                defaultConstructor = constructor;
                break;
            }
            i$++;
        }
        if (defaultConstructor != null || !clazz.isMemberClass() || Modifier.isStatic(clazz.getModifiers())) {
            return defaultConstructor;
        }
        for (Constructor<?> constructor2 : clazz.getDeclaredConstructors()) {
            if (constructor2.getParameterTypes().length == 1 && constructor2.getParameterTypes()[0].equals(clazz.getDeclaringClass())) {
                return constructor2;
            }
        }
        return defaultConstructor;
    }

    public static Constructor<?> Tr(Class<?> clazz) {
        Constructor<?>[] arr$ = clazz.getDeclaredConstructors();
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            Constructor<?> constructor = arr$[i$];
            if (((T) constructor.getAnnotation(T.class)) == null) {
                i$++;
            } else if (0 == 0) {
                return constructor;
            } else {
                throw new Tn("multi-json creator");
            }
        }
        return null;
    }

    public static Method Ty(Class<?> clazz) {
        Method[] arr$ = clazz.getDeclaredMethods();
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            Method method = arr$[i$];
            if (!Modifier.isStatic(method.getModifiers()) || !clazz.isAssignableFrom(method.getReturnType()) || ((T) method.getAnnotation(T.class)) == null) {
                i$++;
            } else if (0 == 0) {
                return method;
            } else {
                throw new Tn("multi-json creator");
            }
        }
        return null;
    }
}
