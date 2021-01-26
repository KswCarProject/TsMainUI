package com.txznet.comm.ui.Tk;

import android.app.Application;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import com.android.SdkConstants;
import com.ts.bt.ContactInfo;
import com.txznet.comm.Tr.Tr.T9;
import com.txznet.comm.Ty.Tn;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.regex.Pattern;

/* compiled from: Proguard */
public class T {
    private static T Tr = new T();

    /* renamed from: T  reason: collision with root package name */
    public boolean f547T = false;
    private Tn.T T9 = new Tn.T() {
        public void T(int width, int height) {
            int width2 = Tn.T(width);
            int height2 = Tn.T(height);
            Configuration configuration = T.this.TZ.getConfiguration();
            configuration.screenWidthDp = width2;
            configuration.screenHeightDp = height2;
            T.this.TZ.updateConfiguration(configuration, T.this.TZ.getDisplayMetrics());
        }
    };
    /* access modifiers changed from: private */
    public Resources TZ = null;
    private DexClassLoader Tk = null;
    private C0020T Tn;
    private String Ty = null;

    /* renamed from: com.txznet.comm.ui.Tk.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public interface C0020T {
        void T();

        void T(String str);
    }

    public void T(C0020T listener, boolean forceDefault) {
        com.txznet.comm.Ty.T.Ty = com.txznet.comm.Tr.T.Tr().getApplicationInfo().dataDir + "/data/ResHolder.apk";
        this.Tn = listener;
        String strResourceFile = forceDefault ? com.txznet.comm.Ty.T.Ty : com.txznet.comm.Ty.T.T();
        this.f547T = com.txznet.comm.Ty.T.Tr.equals(strResourceFile);
        T((Application) com.txznet.comm.Tr.T.Tr().getApplicationContext(), com.txznet.comm.Tr.T.Tr().getResources(), strResourceFile);
        if (this.TZ != null) {
            Tn.T(this.T9);
        }
    }

    public void T() {
        if (this.TZ != null) {
            this.TZ.flushLayoutCache();
        }
    }

    public Object T(String className) {
        if (this.Tk == null) {
            com.txznet.comm.Tr.Tr.Tn.Tn("mResourceClassLoader null,failed getClassInstance");
            return null;
        }
        try {
            return this.Tk.loadClass(className).getDeclaredMethod("getInstance", new Class[0]).invoke("getInstance", new Object[0]);
        } catch (Exception e) {
            com.txznet.comm.Tr.Tr.Tn.Tn("failed getClassInstance:" + className);
            return null;
        }
    }

    public Object Tr(String configClassName) {
        if (this.Tk == null) {
            com.txznet.comm.Tr.Tr.Tn.Tn("mResourceClassLoader null,failed getClassInstance");
            return null;
        }
        try {
            return this.Tk.loadClass(configClassName).newInstance();
        } catch (Exception e) {
            com.txznet.comm.Tr.Tr.Tn.Tn("failed getConfigInstance:" + configClassName);
            return null;
        }
    }

    public Class<?> Ty(String className) {
        if (this.Tk == null) {
            com.txznet.comm.Tr.Tr.Tn.Tn("mResourceClassLoader null,failed getClassInstance");
            return null;
        }
        try {
            return this.Tk.loadClass(className);
        } catch (Exception e) {
            com.txznet.comm.Tr.Tr.Tn.Tn("failed getClass:" + className);
            return null;
        }
    }

    public static T Tr() {
        return Tr;
    }

    private void T(Application app, Resources superRes, String resourceFile) {
        com.txznet.comm.Tr.Tr.Tn.T("[UI2.0]start loadRes path:" + resourceFile);
        File sourceFile = new File(resourceFile);
        if (sourceFile.exists()) {
            String dexOutputDir = app.getApplicationInfo().dataDir + "/dex";
            File outFile = new File(dexOutputDir);
            if (!outFile.exists()) {
                outFile.mkdir();
            }
            this.Tk = new DexClassLoader(sourceFile.getAbsolutePath(), dexOutputDir, (String) null, getClass().getClassLoader());
            try {
                AssetManager assetManager = AssetManager.class.newInstance();
                assetManager.getClass().getMethod("addAssetPath", new Class[]{String.class}).invoke(assetManager, new Object[]{sourceFile.getAbsolutePath()});
                this.TZ = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
                this.Ty = resourceFile;
                if (this.Tn != null) {
                    this.Tn.T();
                }
            } catch (Exception e) {
                T9.T("ui.init.error.E.dex");
                com.txznet.comm.Tr.Tr.Tn.Tn("[UI2.0] load " + resourceFile + " dex error!");
                if (this.Tn != null) {
                    this.Tn.T("load resource dex error!");
                }
            }
        } else {
            T9.T("ui.init.error.E.file");
            com.txznet.comm.Tr.Tr.Tn.Tn("[UI2.0] " + resourceFile + "not exist!");
            if (this.Tn != null) {
                this.Tn.T("UI2.0 resources file not exist!");
            }
        }
    }

    public XmlResourceParser Tn(String name) {
        int id = Tr(name, "layout");
        if (id != 0) {
            try {
                return this.TZ.getLayout(id);
            } catch (Resources.NotFoundException e) {
            }
        }
        try {
            int id2 = com.txznet.comm.Tr.T.Tr().getResources().getIdentifier(name, "layout", com.txznet.comm.Tr.T.Tr().getPackageName());
            if (id2 != 0) {
                return com.txznet.comm.Tr.T.Tr().getResources().getLayout(id2);
            }
        } catch (Exception e2) {
            com.txznet.comm.Tr.Tr.Tn.T("UIResLoader", (Throwable) e2);
        }
        com.txznet.comm.Tr.Tr.Tn.Tn("failed getLayout " + name);
        return null;
    }

    public Drawable T9(String name) {
        int id = Tr(name, "drawable");
        if (id != 0) {
            return this.TZ.getDrawable(id);
        }
        try {
            int id2 = com.txznet.comm.Tr.T.Tr().getResources().getIdentifier(name, "drawable", com.txznet.comm.Tr.T.Tr().getPackageName());
            if (id2 != 0) {
                return com.txznet.comm.Tr.T.Tr().getResources().getDrawable(id2);
            }
        } catch (Exception e) {
            com.txznet.comm.Tr.Tr.Tn.T("UIResLoader", (Throwable) e);
        }
        com.txznet.comm.Tr.Tr.Tn.Tn("failed getDrawable " + name);
        return null;
    }

    public float Tk(String name) {
        int id;
        if (!Pattern.compile("^[x,y,m]\\d+$").matcher(name).matches() && (id = Tr(name, SdkConstants.TAG_DIMEN)) != 0) {
            try {
                return this.TZ.getDimension(id);
            } catch (Resources.NotFoundException e) {
            }
        }
        try {
            int id2 = com.txznet.comm.Tr.T.Tr().getResources().getIdentifier(name, SdkConstants.TAG_DIMEN, com.txznet.comm.Tr.T.Tr().getPackageName());
            if (id2 != 0) {
                return com.txznet.comm.Tr.T.Tr().getResources().getDimension(id2);
            }
        } catch (Exception e2) {
            com.txznet.comm.Tr.Tr.Tn.T("UIResLoader", (Throwable) e2);
        }
        com.txznet.comm.Tr.Tr.Tn.Tn("failed getDimension " + name);
        return 0.0f;
    }

    public String TZ(String name) {
        int id = Tr(name, SdkConstants.TAG_STRING);
        if (id != 0) {
            try {
                return this.TZ.getString(id);
            } catch (Resources.NotFoundException e) {
            }
        }
        try {
            int id2 = com.txznet.comm.Tr.T.Tr().getResources().getIdentifier(name, SdkConstants.TAG_STRING, com.txznet.comm.Tr.T.Tr().getPackageName());
            if (id2 != 0) {
                return com.txznet.comm.Tr.T.Tr().getResources().getString(id2);
            }
        } catch (Exception e2) {
            com.txznet.comm.Tr.Tr.Tn.T("UIResLoader", (Throwable) e2);
        }
        com.txznet.comm.Tr.Tr.Tn.Tn("failed getString " + name);
        return null;
    }

    public int T(String name, String type) {
        int id = Tr(name, type);
        return id != 0 ? id : com.txznet.comm.Tr.T.Tr().getResources().getIdentifier(name, type, com.txznet.comm.Tr.T.Tr().getPackageName());
    }

    public int Tr(String name, String type) {
        if (this.TZ != null) {
            try {
                return this.TZ.getIdentifier(name, type, "com.txznet.resholder");
            } catch (Exception e) {
                com.txznet.comm.Tr.Tr.Tn.Tn("[UI2.0] get id failed:" + type + ContactInfo.COMBINATION_SEPERATOR + name);
            }
        }
        com.txznet.comm.Tr.Tr.Tn.Tn("failed Id " + name);
        return 0;
    }
}
