package com.T.T.Ty;

import com.T.T.Tk;
import com.T.T.Tn;
import com.T.T.Ty;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/* compiled from: Proguard */
public class T7 {

    /* renamed from: T  reason: collision with root package name */
    private final TrO f192T;
    private String T5;
    private TrB T6;
    private List<TrZ> T9;
    private String TE;
    private int TZ;
    private IdentityHashMap<Object, TrB> Th;
    private List<TrF> Tk;
    private List<Tr0> Tn;
    private final Trs Tr;
    private DateFormat Tv;
    private List<Trq> Ty;

    public T7() {
        this(new Trs(), TrO.T());
    }

    public T7(Trs out) {
        this(out, TrO.T());
    }

    public T7(Trs out, TrO config) {
        this.Ty = null;
        this.Tn = null;
        this.T9 = null;
        this.Tk = null;
        this.TZ = 0;
        this.TE = "\t";
        this.Th = null;
        this.Tr = out;
        this.f192T = config;
    }

    public DateFormat T() {
        if (this.Tv == null && this.T5 != null) {
            this.Tv = new SimpleDateFormat(this.T5);
        }
        return this.Tv;
    }

    public TrB Tr() {
        return this.T6;
    }

    public void T(TrB context) {
        this.T6 = context;
    }

    public void T(TrB parent, Object object, Object fieldName) {
        if (!T(TrG.DisableCircularReferenceDetect)) {
            this.T6 = new TrB(parent, object, fieldName);
            if (this.Th == null) {
                this.Th = new IdentityHashMap<>();
            }
            this.Th.put(object, this.T6);
        }
    }

    public final boolean T(Type fieldType, Object obj) {
        if (!this.Tr.T(TrG.WriteClassName)) {
            return false;
        }
        if (fieldType == null && T(TrG.NotWriteRootClassName)) {
            if (this.T6.T() == null) {
                return false;
            }
        }
        return true;
    }

    public TrB T(Object object) {
        if (this.Th == null) {
            return null;
        }
        return this.Th.get(object);
    }

    public boolean Tr(Object value) {
        if (this.Th == null) {
            return false;
        }
        return this.Th.containsKey(value);
    }

    public void Ty(Object object) {
        TrB context = Tr();
        if (object == context.Tr()) {
            this.Tr.write("{\"$ref\":\"@\"}");
            return;
        }
        TrB parentContext = context.T();
        if (parentContext == null || object != parentContext.Tr()) {
            TrB rootContext = context;
            while (rootContext.T() != null) {
                rootContext = rootContext.T();
            }
            if (object == rootContext.Tr()) {
                this.Tr.write("{\"$ref\":\"$\"}");
                return;
            }
            String path = T(object).Ty();
            this.Tr.write("{\"$ref\":\"");
            this.Tr.write(path);
            this.Tr.write("\"}");
            return;
        }
        this.Tr.write("{\"$ref\":\"..\"}");
    }

    public List<Tr0> Ty() {
        return this.Tn;
    }

    public void Tn() {
        this.TZ++;
    }

    public void T9() {
        this.TZ--;
    }

    public void Tk() {
        this.Tr.T(10);
        for (int i = 0; i < this.TZ; i++) {
            this.Tr.write(this.TE);
        }
    }

    public List<TrZ> TZ() {
        return this.T9;
    }

    public List<TrF> TE() {
        return this.Tk;
    }

    public List<Trq> T5() {
        return this.Ty;
    }

    public Trs Tv() {
        return this.Tr;
    }

    public String toString() {
        return this.Tr.toString();
    }

    public void T(TrG feature, boolean state) {
        this.Tr.T(feature, state);
    }

    public boolean T(TrG feature) {
        return this.Tr.T(feature);
    }

    public void Th() {
        this.Tr.T();
    }

    public final void Tn(Object object) {
        if (object == null) {
            this.Tr.T();
            return;
        }
        try {
            T(object.getClass()).T(this, object, (Object) null, (Type) null);
        } catch (IOException e) {
            throw new Tn(e.getMessage(), e);
        }
    }

    public final void T(Object object, String format) {
        if (object instanceof Date) {
            DateFormat dateFormat = T();
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat(format);
            }
            this.Tr.T(dateFormat.format((Date) object));
            return;
        }
        Tn(object);
    }

    public final void T(String text) {
        TrD.f218T.T(this, text);
    }

    public Trh T(Class<?> clazz) {
        Trh writer = (Trh) this.f192T.T(clazz);
        if (writer == null) {
            if (Map.class.isAssignableFrom(clazz)) {
                this.f192T.T(clazz, Trk.f222T);
            } else if (List.class.isAssignableFrom(clazz)) {
                this.f192T.T(clazz, Try.f229T);
            } else if (Collection.class.isAssignableFrom(clazz)) {
                this.f192T.T(clazz, Ts.f230T);
            } else if (Date.class.isAssignableFrom(clazz)) {
                this.f192T.T(clazz, Tu.f232T);
            } else if (Ty.class.isAssignableFrom(clazz)) {
                this.f192T.T(clazz, T3.f188T);
            } else if (Tk.class.isAssignableFrom(clazz)) {
                this.f192T.T(clazz, T1.f186T);
            } else if (clazz.isEnum() || (clazz.getSuperclass() != null && clazz.getSuperclass().isEnum())) {
                this.f192T.T(clazz, TA.f195T);
            } else if (clazz.isArray()) {
                Class<?> componentType = clazz.getComponentType();
                this.f192T.T(clazz, new Tr(componentType, T(componentType)));
            } else if (Throwable.class.isAssignableFrom(clazz)) {
                this.f192T.T(clazz, new T0(clazz));
            } else if (TimeZone.class.isAssignableFrom(clazz)) {
                this.f192T.T(clazz, TrA.f216T);
            } else if (Charset.class.isAssignableFrom(clazz)) {
                this.f192T.T(clazz, Trx.f228T);
            } else if (Enumeration.class.isAssignableFrom(clazz)) {
                this.f192T.T(clazz, Tx.f235T);
            } else if (Calendar.class.isAssignableFrom(clazz)) {
                this.f192T.T(clazz, TF.f200T);
            } else {
                boolean isCglibProxy = false;
                boolean isJavassistProxy = false;
                Class<?>[] arr$ = clazz.getInterfaces();
                int len$ = arr$.length;
                int i$ = 0;
                while (true) {
                    if (i$ >= len$) {
                        break;
                    }
                    Class<?> item = arr$[i$];
                    if (item.getName().equals("net.sf.cglib.proxy.Factory")) {
                        isCglibProxy = true;
                        break;
                    } else if (item.getName().equals("javassist.util.proxy.ProxyObject")) {
                        isJavassistProxy = true;
                        break;
                    } else {
                        i$++;
                    }
                }
                if (isCglibProxy || isJavassistProxy) {
                    Trh superWriter = T((Class<?>) clazz.getSuperclass());
                    this.f192T.T(clazz, superWriter);
                    return superWriter;
                } else if (Proxy.isProxyClass(clazz)) {
                    this.f192T.T(clazz, this.f192T.T(clazz));
                } else {
                    this.f192T.T(clazz, this.f192T.T(clazz));
                }
            }
            writer = (Trh) this.f192T.T(clazz);
        }
        return writer;
    }
}
