package com.T.T.Tr.T;

import com.T.T.Tn;
import com.T.T.Tr.T5;
import com.T.T.Tr.T9;
import com.T.T.Tr.TZ;
import com.T.T.Tr.Tv;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* compiled from: Proguard */
public class Ty extends Ts {
    private T7 T9;
    private int Tn;
    private final Type Ty;

    public Ty(Tv mapping, Class<?> clazz, com.T.T.Tn.Ty fieldInfo) {
        super(clazz, fieldInfo);
        if (T9() instanceof ParameterizedType) {
            this.Ty = ((ParameterizedType) T9()).getActualTypeArguments()[0];
        } else {
            this.Ty = Object.class;
        }
    }

    public int T() {
        return 14;
    }

    public void T(com.T.T.Tr.Ty parser, Object object, Type objectType, Map<String, Object> fieldValues) {
        if (parser.Th().Tn() == 8) {
            T(object, (String) null);
            return;
        }
        ArrayList list = new ArrayList();
        T5 context = parser.Tk();
        parser.T(context, object, (Object) this.f170T.Ty());
        T(parser, objectType, list);
        parser.T(context);
        if (object == null) {
            fieldValues.put(this.f170T.Ty(), list);
        } else {
            T(object, (Object) list);
        }
    }

    public final void T(com.T.T.Tr.Ty parser, Type objectType, Collection array) {
        Type itemType = this.Ty;
        T7 itemTypeDeser = this.T9;
        if ((itemType instanceof TypeVariable) && (objectType instanceof ParameterizedType)) {
            TypeVariable typeVar = (TypeVariable) itemType;
            ParameterizedType paramType = (ParameterizedType) objectType;
            Class<?> objectClass = null;
            if (paramType.getRawType() instanceof Class) {
                objectClass = (Class) paramType.getRawType();
            }
            int paramIndex = -1;
            if (objectClass != null) {
                int i = 0;
                int size = objectClass.getTypeParameters().length;
                while (true) {
                    if (i >= size) {
                        break;
                    } else if (objectClass.getTypeParameters()[i].getName().equals(typeVar.getName())) {
                        paramIndex = i;
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (paramIndex != -1) {
                itemType = paramType.getActualTypeArguments()[paramIndex];
                if (!itemType.equals(this.Ty)) {
                    itemTypeDeser = parser.Ty().T(itemType);
                }
            }
        }
        T9 lexer = parser.Th();
        if (lexer.Tn() != 14) {
            String errorMessage = "exepct '[', but " + TZ.T(lexer.Tn());
            if (objectType != null) {
                errorMessage = errorMessage + ", type : " + objectType;
            }
            throw new Tn(errorMessage);
        }
        if (itemTypeDeser == null) {
            itemTypeDeser = parser.Ty().T(itemType);
            this.T9 = itemTypeDeser;
            this.Tn = this.T9.T();
        }
        lexer.T(this.Tn);
        int i2 = 0;
        while (true) {
            if (lexer.T(com.T.T.Tr.Tn.AllowArbitraryCommas)) {
                while (lexer.Tn() == 16) {
                    lexer.T();
                }
            }
            if (lexer.Tn() == 15) {
                lexer.T(16);
                return;
            }
            array.add(itemTypeDeser.T(parser, itemType, Integer.valueOf(i2)));
            parser.T(array);
            if (lexer.Tn() == 16) {
                lexer.T(this.Tn);
            }
            i2++;
        }
    }
}
