package com.T.T.Tr.T;

import com.T.T.Tn.Ty;
import java.lang.reflect.Type;
import java.util.Map;

/* compiled from: Proguard */
public final class T4 extends Ts {
    private final Map Tn;
    private final String Ty;

    public T4(Map map, String index) {
        super((Class<?>) null, (Ty) null);
        this.Ty = index;
        this.Tn = map;
    }

    public void T(Object object, Object value) {
        this.Tn.put(this.Ty, value);
    }

    public void T(com.T.T.Tr.Ty parser, Object object, Type objectType, Map<String, Object> map) {
    }
}
