package com.T.T.Ty;

import com.T.T.Tn.Ty;

/* compiled from: Proguard */
class TrE extends TM {
    public TrE(Ty fieldInfo) {
        super(fieldInfo);
    }

    public void T(T7 serializer, Object propertyValue) throws Exception {
        Trs out = serializer.Tv();
        T(serializer);
        Object value = propertyValue;
        if (value != null) {
            out.append((CharSequence) value.toString());
        } else if (out.T(TrG.WriteNullNumberAsZero)) {
            out.T('0');
        } else {
            out.T();
        }
    }
}
