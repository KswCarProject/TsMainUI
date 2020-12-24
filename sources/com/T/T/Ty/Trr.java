package com.T.T.Ty;

import com.T.T.T;
import com.T.T.Tn;
import com.T.T.Tn.TZ;
import com.T.T.Tn.Ty;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: Proguard */
public class Trr implements Trh {

    /* renamed from: T  reason: collision with root package name */
    private final TM[] f224T;
    private final TM[] Tr;

    public Trr(Class<?> clazz) {
        this(clazz, (Map<String, String>) null);
    }

    public Trr(Class<?> clazz, Map<String, String> aliasMap) {
        List<TM> getterList = new ArrayList<>();
        for (Ty fieldInfo : TZ.T(clazz, aliasMap, false)) {
            getterList.add(T(fieldInfo));
        }
        this.f224T = (TM[]) getterList.toArray(new TM[getterList.size()]);
        List<TM> getterList2 = new ArrayList<>();
        for (Ty fieldInfo2 : TZ.T(clazz, aliasMap, true)) {
            getterList2.add(T(fieldInfo2));
        }
        this.Tr = (TM[]) getterList2.toArray(new TM[getterList2.size()]);
    }

    /* access modifiers changed from: protected */
    public boolean T(T7 serializer, Object obj, Type fieldType, Object fieldName) {
        return serializer.T(fieldType, obj);
    }

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        TM[] getters;
        Field field;
        Trs out = serializer.Tv();
        if (object == null) {
            out.T();
        } else if (serializer.Tr(object)) {
            T(serializer, object);
        } else {
            if (out.T(TrG.SortField)) {
                getters = this.Tr;
            } else {
                getters = this.f224T;
            }
            TrB parent = serializer.Tr();
            serializer.T(parent, object, fieldName);
            try {
                out.append('{');
                if (getters.length > 0 && out.T(TrG.PrettyFormat)) {
                    serializer.Tn();
                    serializer.Tk();
                }
                boolean commaFlag = false;
                if (T(serializer, object, fieldType, fieldName) && object.getClass() != fieldType) {
                    out.Tr(T.f128T);
                    serializer.Tn(object.getClass());
                    commaFlag = true;
                }
                for (TM fieldSerializer : getters) {
                    if (!serializer.T(TrG.SkipTransientField) || (field = fieldSerializer.Tr()) == null || !Modifier.isTransient(field.getModifiers())) {
                        if (TI.T(serializer, object, fieldSerializer.Ty())) {
                            Object propertyValue = fieldSerializer.T(object);
                            if (TI.Ty(serializer, object, fieldSerializer.Ty(), propertyValue)) {
                                String key = TI.Tr(serializer, object, fieldSerializer.Ty(), propertyValue);
                                Object originalValue = propertyValue;
                                Object propertyValue2 = TI.T(serializer, object, fieldSerializer.Ty(), propertyValue);
                                if (propertyValue2 != null || fieldSerializer.T() || serializer.T(TrG.WriteMapNullValue)) {
                                    if (commaFlag) {
                                        out.append(',');
                                        if (out.T(TrG.PrettyFormat)) {
                                            serializer.Tk();
                                        }
                                    }
                                    if (key != fieldSerializer.Ty()) {
                                        out.Tr(key);
                                        serializer.Tn(propertyValue2);
                                    } else if (originalValue != propertyValue2) {
                                        fieldSerializer.T(serializer);
                                        serializer.Tn(propertyValue2);
                                    } else {
                                        fieldSerializer.T(serializer, propertyValue2);
                                    }
                                    commaFlag = true;
                                }
                            }
                        }
                    }
                }
                if (getters.length > 0 && out.T(TrG.PrettyFormat)) {
                    serializer.T9();
                    serializer.Tk();
                }
                out.append('}');
                serializer.T(parent);
            } catch (Exception e) {
                throw new Tn("write javaBean error", e);
            } catch (Throwable th) {
                serializer.T(parent);
                throw th;
            }
        }
    }

    public void T(T7 serializer, Object object) {
        serializer.Ty(object);
    }

    public TM T(Ty fieldInfo) {
        if (fieldInfo.T() == Number.class) {
            return new TrE(fieldInfo);
        }
        return new Trv(fieldInfo);
    }
}
