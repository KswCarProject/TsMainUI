package com.T.T.Tr.T;

import com.T.T.Tn.TZ;
import com.T.T.Tn.Ty;
import com.T.T.Tr.T9;
import com.T.T.Tr.Tv;
import java.lang.reflect.Type;
import java.util.Map;

/* compiled from: Proguard */
public class Tx extends Ts {
    public Tx(Tv mapping, Class<?> clazz, Ty fieldInfo) {
        super(clazz, fieldInfo);
    }

    public void T(com.T.T.Tr.Ty parser, Object object, Type objectType, Map<String, Object> fieldValues) {
        Integer value;
        T9 lexer = parser.Th();
        if (lexer.Tn() == 2) {
            int val = lexer.TK();
            lexer.T(16);
            if (object == null) {
                fieldValues.put(this.f170T.Ty(), Integer.valueOf(val));
            } else {
                T(object, val);
            }
        } else {
            if (lexer.Tn() == 8) {
                value = null;
                lexer.T(16);
            } else {
                value = TZ.Te(parser.Tv());
            }
            if (value != null || Tn() != Integer.TYPE) {
                if (object == null) {
                    fieldValues.put(this.f170T.Ty(), value);
                } else {
                    T(object, (Object) value);
                }
            }
        }
    }

    public int T() {
        return 2;
    }
}
