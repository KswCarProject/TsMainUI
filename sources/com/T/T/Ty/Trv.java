package com.T.T.Ty;

import com.T.T.T.Tr;
import com.T.T.Tn.Ty;
import com.android.SdkConstants;
import java.lang.reflect.Type;
import java.util.Collection;

/* compiled from: Proguard */
public class Trv extends TM {
    private boolean T5 = false;
    boolean T9 = false;
    private String TE;
    private Class<?> TZ;
    private Trh Tk;
    boolean Tn = false;
    boolean Tr = false;
    boolean Ty = false;

    public Trv(Ty fieldInfo) {
        super(fieldInfo);
        Tr annotation = (Tr) fieldInfo.T(Tr.class);
        if (annotation != null) {
            this.TE = annotation.Tr();
            if (this.TE.trim().length() == 0) {
                this.TE = null;
            }
            for (TrG feature : annotation.T9()) {
                if (feature == TrG.WriteNullNumberAsZero) {
                    this.T5 = true;
                } else if (feature == TrG.WriteNullStringAsEmpty) {
                    this.Tr = true;
                } else if (feature == TrG.WriteNullBooleanAsFalse) {
                    this.Ty = true;
                } else if (feature == TrG.WriteNullListAsEmpty) {
                    this.Tn = true;
                } else if (feature == TrG.WriteEnumUsingToString) {
                    this.T9 = true;
                }
            }
        }
    }

    public void T(T7 serializer, Object propertyValue) throws Exception {
        T(serializer);
        if (this.TE != null) {
            serializer.T(propertyValue, this.TE);
            return;
        }
        if (this.Tk == null) {
            if (propertyValue == null) {
                this.TZ = this.f206T.T();
            } else {
                this.TZ = propertyValue.getClass();
            }
            this.Tk = serializer.T(this.TZ);
        }
        if (propertyValue == null) {
            if (this.T5 && Number.class.isAssignableFrom(this.TZ)) {
                serializer.Tv().T('0');
            } else if (this.Tr && String.class == this.TZ) {
                serializer.Tv().write("\"\"");
            } else if (this.Ty && Boolean.class == this.TZ) {
                serializer.Tv().write(SdkConstants.VALUE_FALSE);
            } else if (!this.Tn || !Collection.class.isAssignableFrom(this.TZ)) {
                this.Tk.T(serializer, (Object) null, this.f206T.Ty(), (Type) null);
            } else {
                serializer.Tv().write("[]");
            }
        } else if (!this.T9 || !this.TZ.isEnum()) {
            Class<?> valueClass = propertyValue.getClass();
            if (valueClass == this.TZ) {
                this.Tk.T(serializer, propertyValue, this.f206T.Ty(), this.f206T.Tr());
            } else {
                serializer.T(valueClass).T(serializer, propertyValue, this.f206T.Ty(), this.f206T.Tr());
            }
        } else {
            serializer.Tv().T(((Enum) propertyValue).name());
        }
    }
}
