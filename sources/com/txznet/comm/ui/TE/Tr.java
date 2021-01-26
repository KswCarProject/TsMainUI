package com.txznet.comm.ui.TE;

import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.ui.Tk.T;

/* compiled from: Proguard */
public class Tr {

    /* renamed from: T  reason: collision with root package name */
    private static T f541T = T.Tr();

    public static void T() {
        f541T.T();
    }

    public static XmlResourceParser T(String layoutName) {
        return f541T.Tn(layoutName);
    }

    public static View Tr(String layoutName) {
        XmlResourceParser parser = T(layoutName);
        if (parser != null) {
            return LayoutInflater.from(com.txznet.comm.Tr.T.Tr()).inflate(parser, (ViewGroup) null);
        }
        Tn.Tn(layoutName + " not exist!");
        return null;
    }

    public static Object T(String name, View view) {
        try {
            View target = view.findViewById(f541T.T(name, "id"));
            if (target != null) {
                return target;
            }
            return view.findViewById(com.txznet.comm.Tr.T.Tr().getResources().getIdentifier(name, "id", com.txznet.comm.Tr.T.Tr().getPackageName()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Drawable Ty(String name) {
        try {
            return f541T.T9(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static float Tn(String name) {
        try {
            return f541T.Tk(name);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public static String T9(String name) {
        return f541T.TZ(name);
    }
}
