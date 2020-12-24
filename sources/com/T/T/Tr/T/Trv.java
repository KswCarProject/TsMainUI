package com.T.T.Tr.T;

import com.T.T.Tn;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

/* compiled from: Proguard */
public class Trv extends T implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Trv f168T = new Trv();

    /* access modifiers changed from: protected */
    public <T> T T(Ty parser, Type clazz, Object fieldName, Object val) {
        if (val == null) {
            return null;
        }
        if (val instanceof Date) {
            return new Timestamp(((Date) val).getTime());
        }
        if (val instanceof Number) {
            return new Timestamp(((Number) val).longValue());
        }
        if (val instanceof String) {
            String strVal = (String) val;
            if (strVal.length() == 0) {
                return null;
            }
            try {
                return new Timestamp(parser.T().parse(strVal).getTime());
            } catch (ParseException e) {
                return new Timestamp(Long.parseLong(strVal));
            }
        } else {
            throw new Tn("parse error");
        }
    }

    public int T() {
        return 2;
    }
}
