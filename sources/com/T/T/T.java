package com.T.T;

import com.T.T.Tr.T.Ts;
import com.T.T.Tr.T9;
import com.T.T.Tr.Tn;
import com.T.T.Tr.Tv;
import com.T.T.Tr.Ty;
import com.T.T.Ty.T7;
import com.T.T.Ty.TrG;
import com.T.T.Ty.Trs;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: Proguard */
public abstract class T implements Tk, Ty {

    /* renamed from: T  reason: collision with root package name */
    public static String f128T = "@type";
    public static int Tn = ((((0 | TrG.QuoteFieldNames.T()) | TrG.SkipTransientField.T()) | TrG.WriteEnumUsingToString.T()) | TrG.SortField.T());
    public static int Tr = ((((((((0 | Tn.AutoCloseSource.T()) | Tn.InternFieldNames.T()) | Tn.UseBigDecimal.T()) | Tn.AllowUnQuotedFieldNames.T()) | Tn.AllowSingleQuotes.T()) | Tn.AllowArbitraryCommas.T()) | Tn.SortFeidFastMatch.T()) | Tn.IgnoreNotMatch.T());
    public static String Ty = "yyyy-MM-dd HH:mm:ss";

    public static final <T> T T(String text, Class<T> clazz, Tn... features) {
        return T(text, clazz, Tv.T(), Tr, features);
    }

    public static final <T> T T(String input, Type clazz, Tv config, int featureValues, Tn... features) {
        if (input == null) {
            return null;
        }
        for (Tn featrue : features) {
            featureValues = Tn.T(featureValues, featrue, true);
        }
        Ty parser = new Ty(input, config, featureValues);
        T value = parser.T(clazz);
        T(parser, value);
        parser.close();
        return value;
    }

    public static <T> int T(Ty parser, T t) {
        Object refValue;
        int size = parser.TZ().size();
        for (int i = 0; i < size; i++) {
            Ty.T task = parser.TZ().get(i);
            Ts fieldDeser = task.Ty();
            Object object = null;
            if (task.Tn() != null) {
                object = task.Tn().Tr();
            }
            String ref = task.Tr();
            if (ref.startsWith("$")) {
                refValue = parser.T(ref);
            } else {
                refValue = task.T().Tr();
            }
            fieldDeser.T(object, refValue);
        }
        return size;
    }

    public static final <T> T T(String text, Class<T> clazz) {
        return T(text, clazz, new Tn[0]);
    }

    public static final <T> List<T> Tr(String text, Class<T> clazz) {
        List<T> list;
        if (text == null) {
            return null;
        }
        Ty parser = new Ty(text, Tv.T());
        T9 lexer = parser.Th();
        if (lexer.Tn() == 8) {
            lexer.T();
            list = null;
        } else {
            list = new ArrayList<>();
            parser.T((Class<?>) clazz, (Collection) list);
            T(parser, list);
        }
        parser.close();
        return list;
    }

    public static final String T(Object object) {
        return T(object, new TrG[0]);
    }

    public static final String T(Object object, TrG... features) {
        Trs out = new Trs();
        try {
            T7 serializer = new T7(out);
            for (TrG feature : features) {
                serializer.T(feature, true);
            }
            serializer.Tn(object);
            return out.toString();
        } finally {
            out.close();
        }
    }

    public String toString() {
        return T();
    }

    public String T() {
        Trs out = new Trs();
        try {
            new T7(out).Tn(this);
            return out.toString();
        } finally {
            out.close();
        }
    }

    public void T(Appendable appendable) {
        Trs out = new Trs();
        try {
            new T7(out).Tn(this);
            appendable.append(out.toString());
            out.close();
        } catch (IOException e) {
            throw new Tn(e.getMessage(), e);
        } catch (Throwable th) {
            out.close();
            throw th;
        }
    }
}
