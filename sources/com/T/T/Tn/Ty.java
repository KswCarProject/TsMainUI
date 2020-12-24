package com.T.T.Tn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* compiled from: Proguard */
public class Ty implements Comparable<Ty> {

    /* renamed from: T  reason: collision with root package name */
    private final String f136T;
    private final Type T9;
    private boolean TZ;
    private final Class<?> Tk;
    private final Class<?> Tn;
    private final Method Tr;
    private final Field Ty;

    public Ty(String name, Class<?> declaringClass, Class<?> fieldClass, Type fieldType, Field field) {
        this.TZ = false;
        this.f136T = name;
        this.Tk = declaringClass;
        this.Tn = fieldClass;
        this.T9 = fieldType;
        this.Tr = null;
        this.Ty = field;
        if (field != null) {
            field.setAccessible(true);
        }
    }

    public Ty(String name, Method method, Field field) {
        this(name, method, field, (Class<?>) null, (Type) null);
    }

    public Ty(String name, Method method, Field field, Class<?> clazz, Type type) {
        Class<?> fieldClass;
        Type fieldType;
        Type genericFieldType;
        this.TZ = false;
        this.f136T = name;
        this.Tr = method;
        this.Ty = field;
        if (method != null) {
            method.setAccessible(true);
        }
        if (field != null) {
            field.setAccessible(true);
        }
        if (method != null) {
            if (method.getParameterTypes().length == 1) {
                fieldClass = method.getParameterTypes()[0];
                fieldType = method.getGenericParameterTypes()[0];
            } else {
                fieldClass = method.getReturnType();
                fieldType = method.getGenericReturnType();
                this.TZ = true;
            }
            this.Tk = method.getDeclaringClass();
        } else {
            fieldClass = field.getType();
            fieldType = field.getGenericType();
            this.Tk = field.getDeclaringClass();
        }
        if (clazz == null || fieldClass != Object.class || !(fieldType instanceof TypeVariable) || (genericFieldType = T(clazz, (TypeVariable) fieldType)) == null) {
            Type genericFieldType2 = T(clazz, type, fieldType);
            if (genericFieldType2 != fieldType) {
                if (genericFieldType2 instanceof ParameterizedType) {
                    fieldClass = TZ.T(genericFieldType2);
                } else if (genericFieldType2 instanceof Class) {
                    fieldClass = TZ.T(genericFieldType2);
                }
            }
            this.T9 = genericFieldType2;
            this.Tn = fieldClass;
            return;
        }
        this.Tn = TZ.T(genericFieldType);
        this.T9 = genericFieldType;
    }

    public static Type T(Class<?> clazz, Type type, Type fieldType) {
        if (clazz == null || type == null) {
            return fieldType;
        }
        if (!(type instanceof ParameterizedType)) {
            return fieldType;
        }
        if (fieldType instanceof TypeVariable) {
            ParameterizedType paramType = (ParameterizedType) type;
            TypeVariable<?> typeVar = (TypeVariable) fieldType;
            for (int i = 0; i < clazz.getTypeParameters().length; i++) {
                if (clazz.getTypeParameters()[i].getName().equals(typeVar.getName())) {
                    return paramType.getActualTypeArguments()[i];
                }
            }
        }
        if (fieldType instanceof ParameterizedType) {
            ParameterizedType parameterizedFieldType = (ParameterizedType) fieldType;
            Type[] arguments = parameterizedFieldType.getActualTypeArguments();
            boolean changed = false;
            for (int i2 = 0; i2 < arguments.length; i2++) {
                Type feildTypeArguement = arguments[i2];
                if (feildTypeArguement instanceof TypeVariable) {
                    TypeVariable<?> typeVar2 = (TypeVariable) feildTypeArguement;
                    if (type instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) type;
                        for (int j = 0; j < clazz.getTypeParameters().length; j++) {
                            if (clazz.getTypeParameters()[j].getName().equals(typeVar2.getName())) {
                                arguments[i2] = parameterizedType.getActualTypeArguments()[j];
                                changed = true;
                            }
                        }
                    }
                }
            }
            if (changed) {
                return new Tk(arguments, parameterizedFieldType.getOwnerType(), parameterizedFieldType.getRawType());
            }
        }
        return fieldType;
    }

    public static Type T(Class<?> clazz, TypeVariable<?> typeVariable) {
        Type type;
        GenericDeclaration gd = typeVariable.getGenericDeclaration();
        do {
            type = clazz.getGenericSuperclass();
            if (type == null) {
                return null;
            }
            if (type instanceof ParameterizedType) {
                ParameterizedType ptype = (ParameterizedType) type;
                if (ptype.getRawType() == gd) {
                    TypeVariable<?>[] tvs = gd.getTypeParameters();
                    Type[] types = ptype.getActualTypeArguments();
                    for (int i = 0; i < tvs.length; i++) {
                        if (tvs[i] == typeVariable) {
                            return types[i];
                        }
                    }
                    return null;
                }
            }
            clazz = TZ.T(type);
        } while (type != null);
        return null;
    }

    public String toString() {
        return this.f136T;
    }

    public Class<?> T() {
        return this.Tn;
    }

    public Type Tr() {
        return this.T9;
    }

    public String Ty() {
        return this.f136T;
    }

    public Method Tn() {
        return this.Tr;
    }

    public Field T9() {
        return this.Ty;
    }

    /* renamed from: T */
    public int compareTo(Ty o) {
        return this.f136T.compareTo(o.f136T);
    }

    public <T extends Annotation> T T(Class<T> annotationClass) {
        T annotation = null;
        if (this.Tr != null) {
            annotation = this.Tr.getAnnotation(annotationClass);
        }
        if (annotation != null || this.Ty == null) {
            return annotation;
        }
        return this.Ty.getAnnotation(annotationClass);
    }

    public Object T(Object javaObject) throws IllegalAccessException, InvocationTargetException {
        if (this.Tr != null) {
            return this.Tr.invoke(javaObject, new Object[0]);
        }
        return this.Ty.get(javaObject);
    }

    public void T(Object javaObject, Object value) throws IllegalAccessException, InvocationTargetException {
        if (this.Tr != null) {
            this.Tr.invoke(javaObject, new Object[]{value});
            return;
        }
        this.Ty.set(javaObject, value);
    }

    public void T(boolean flag) throws SecurityException {
        if (this.Tr != null) {
            this.Tr.setAccessible(flag);
        } else {
            this.Ty.setAccessible(flag);
        }
    }

    public boolean Tk() {
        return this.TZ;
    }
}
