package com.txznet.txz.T;

import com.Tn.Tr.Ty.T;
import com.txznet.comm.Tr.Tr.Tk;
import com.txznet.comm.Tr.Tr.Tn;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Proguard */
public class Ty {

    /* renamed from: T  reason: collision with root package name */
    public static List<T.C0008T> f892T = new ArrayList();
    private static String Tr = (com.txznet.T.T.T().getApplicationInfo().dataDir + "/dex");
    private static String Ty = (com.txznet.T.T.T().getApplicationInfo().dataDir + "/solibs");

    public static String T(String path) {
        int end = path.lastIndexOf(47);
        return path.substring(path.lastIndexOf(47, end - 1) + 1, end);
    }

    public static Object T(String path, String className, byte[] data) {
        Tn.T("load plugin " + className + ": " + path);
        new File(Tr).mkdirs();
        new File(Ty).mkdirs();
        ClassLoader loader = new DexClassLoader(path, Tr, Ty, Ty.class.getClassLoader());
        try {
            Class<?> clsPlugin = loader.loadClass(className);
            switch (((Tr) clsPlugin.newInstance()).T()) {
                case 1:
                    T objPlugin = (T) clsPlugin.newInstance();
                    if (objPlugin.Tr() > Tn.Ty) {
                        Tn.T("plugin:" + className + ":" + path + " load err:comm version is too low[" + Tn.Ty + "]-[" + objPlugin.Tr() + "]");
                        return null;
                    }
                    T.C0008T info = new T.C0008T();
                    info.Tr = className;
                    info.Ty = T(path);
                    info.Tn = objPlugin.T();
                    f892T.add(info);
                    return objPlugin.T(loader, path, data);
                default:
                    return null;
            }
        } catch (Exception e) {
            Tn.T("plugin:load plugin error", (Throwable) e);
            e.printStackTrace();
            return null;
        }
        Tn.T("plugin:load plugin error", (Throwable) e);
        e.printStackTrace();
        return null;
    }

    public static byte[] Tr(String packageName, String command, byte[] data) {
        try {
            if (command.startsWith("load.")) {
                String param = command.substring("load.".length());
                int n = param.indexOf(124);
                com.txznet.T.T.Tr().T(param.substring(n + 1), param.substring(0, n), data);
            } else if (command.startsWith("download")) {
                Tk.T(data);
            }
        } catch (Exception e) {
        }
        return null;
    }
}
