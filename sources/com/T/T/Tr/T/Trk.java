package com.T.T.Tr.T;

import com.T.T.Tn.Ty;
import com.T.T.Tr.T9;
import com.T.T.Tr.Tv;
import java.lang.reflect.Type;
import java.util.Map;

/* compiled from: Proguard */
public class Trk extends Ts {
    private final T7 Ty;

    public Trk(Tv config, Class<?> clazz, Ty fieldInfo) {
        super(clazz, fieldInfo);
        this.Ty = config.T(fieldInfo);
    }

    public void T(com.T.T.Tr.Ty parser, Object object, Type objectType, Map<String, Object> fieldValues) {
        String value;
        T9 lexer = parser.Th();
        if (lexer.Tn() == 4) {
            value = lexer.Tf();
            lexer.T(16);
        } else {
            Object obj = parser.Tv();
            if (obj == null) {
                value = null;
            } else {
                value = obj.toString();
            }
        }
        if (object == null) {
            fieldValues.put(this.f170T.Ty(), value);
        } else {
            T(object, value);
        }
    }

    public int T() {
        return this.Ty.T();
    }
}
