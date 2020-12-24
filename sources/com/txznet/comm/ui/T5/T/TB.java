package com.txznet.comm.ui.T5.T;

import com.txznet.comm.Ty.Tr;

/* compiled from: Proguard */
public abstract class TB extends TM {
    public int T9;
    public String TE;
    public int TZ;
    public String Tk;
    public T Tn;

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f431T;
        public String T9;
        public int TZ;
        public int Tk;
        public String Tn;
        public String Tr;
        public String Ty;
    }

    public abstract void T(Tr tr);

    public TB(int type) {
        super(type);
    }

    public void T(String data) {
        Tr jsonBuilder = new Tr(data);
        this.TZ = ((Integer) jsonBuilder.T("listType", Integer.class, 0)).intValue();
        this.T9 = ((Integer) jsonBuilder.T("count", Integer.class, 0)).intValue();
        this.Tk = (String) jsonBuilder.T("action", String.class);
        this.TE = (String) jsonBuilder.T("keywords", String.class);
        Tr(jsonBuilder);
        T(jsonBuilder);
    }

    public void Tr(Tr data) {
        this.Tn = new T();
        this.Tn.Tk = ((Integer) data.T("curPage", Integer.class, 0)).intValue();
        this.Tn.TZ = ((Integer) data.T("maxPage", Integer.class, 0)).intValue();
        this.Tn.f431T = (String) data.T("prefix", String.class);
        this.Tn.Tn = (String) data.T("titlefix", String.class);
        this.Tn.T9 = (String) data.T("aftfix", String.class);
        this.Tn.Tr = (String) data.T("city", String.class);
        this.Tn.Ty = (String) data.T("midfix", String.class);
    }
}
