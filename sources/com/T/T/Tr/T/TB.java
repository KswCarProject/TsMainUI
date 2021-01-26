package com.T.T.Tr.T;

import com.T.T.Tn.Ty;
import com.T.T.Tr.Tv;
import com.T.T.Tr.Ty;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/* compiled from: Proguard */
public class TB extends Ts {
    private T7 Ty;

    public TB(Tv mapping, Class<?> clazz, Ty fieldInfo) {
        super(clazz, fieldInfo);
    }

    public void T(com.T.T.Tr.Ty parser, Object object, Type objectType, Map<String, Object> fieldValues) {
        if (this.Ty == null) {
            this.Ty = parser.Ty().T(this.f173T);
        }
        if (objectType instanceof ParameterizedType) {
            parser.Tk().T(objectType);
        }
        Object value = this.Ty.T(parser, T9(), this.f173T.Ty());
        if (parser.Tn() == 1) {
            Ty.T task = parser.TE();
            task.T((Ts) this);
            task.T(parser.Tk());
            parser.T(0);
        } else if (object == null) {
            fieldValues.put(this.f173T.Ty(), value);
        } else {
            T(object, value);
        }
    }

    public int T() {
        if (this.Ty != null) {
            return this.Ty.T();
        }
        return 2;
    }
}
