package com.txznet.comm.ui.T5.T;

import com.txznet.comm.Ty.Tr;
import java.util.ArrayList;

/* compiled from: Proguard */
public class Ts extends TB {

    /* renamed from: T  reason: collision with root package name */
    private ArrayList<T> f454T = new ArrayList<>();

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f455T;
        public String Tr;
    }

    public Ts() {
        super(25);
    }

    public ArrayList<T> T() {
        return this.f454T;
    }

    public void T(Tr data) {
        this.f454T.clear();
        String[] beans = (String[]) data.T("beans", String[].class);
        for (String val : beans) {
            String[] vals = val.split(":");
            T object = new T();
            object.Tr = vals[0];
            object.f455T = vals[1];
            this.f454T.add(object);
        }
    }
}
