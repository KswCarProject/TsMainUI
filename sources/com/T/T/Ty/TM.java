package com.T.T.Ty;

import com.T.T.T.Tr;
import com.T.T.Tn.Ty;
import java.io.IOException;
import java.lang.reflect.Field;

/* compiled from: Proguard */
public abstract class TM {

    /* renamed from: T  reason: collision with root package name */
    protected final Ty f206T;
    private boolean T9 = false;
    private final String Tn;
    private final String Tr;
    private final String Ty;

    public abstract void T(T7 t7, Object obj) throws Exception;

    public TM(Ty fieldInfo) {
        this.f206T = fieldInfo;
        fieldInfo.T(true);
        this.Tr = '\"' + fieldInfo.Ty() + "\":";
        this.Ty = '\'' + fieldInfo.Ty() + "':";
        this.Tn = fieldInfo.Ty() + ":";
        Tr annotation = (Tr) fieldInfo.T(Tr.class);
        if (annotation != null) {
            for (TrG feature : annotation.T9()) {
                if (feature == TrG.WriteMapNullValue) {
                    this.T9 = true;
                }
            }
        }
    }

    public boolean T() {
        return this.T9;
    }

    public Field Tr() {
        return this.f206T.T9();
    }

    public String Ty() {
        return this.f206T.Ty();
    }

    public void T(T7 serializer) throws IOException {
        Trs out = serializer.Tv();
        if (!serializer.T(TrG.QuoteFieldNames)) {
            out.write(this.Tn);
        } else if (serializer.T(TrG.UseSingleQuotes)) {
            out.write(this.Ty);
        } else {
            out.write(this.Tr);
        }
    }

    public Object T(Object object) throws Exception {
        return this.f206T.T(object);
    }
}
