package com.txznet.comm.Ty.T;

import android.content.Context;
import android.graphics.Bitmap;
import com.Ty.T.Tr.T.TZ;
import com.Ty.T.Tr.T9;
import com.Ty.T.Tr.Tn;
import com.Ty.T.Tr.Tn.Tr;
import com.Ty.T.Tr.Ty;
import com.txznet.txz.util.Tv;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Map;

/* compiled from: Proguard */
public class T {

    /* renamed from: T  reason: collision with root package name */
    public static Ty.T f413T;
    public static com.Ty.T.T.Tr.T Tn;
    public static Ty Tr;
    public static T9 Ty;

    public static void T(Context application) {
        if (!C0017T.T9().Tr() || !Tn.T().Tr()) {
            f413T = new Ty.T();
            Tr = f413T.T(false).T(Bitmap.Config.RGB_565).Tr(false).Ty(false).T();
            Ty = new T9.T(application).T(Tr).T((Tr) new com.Ty.T.Tr.Tn.T(application) {
                /* access modifiers changed from: protected */
                public HttpURLConnection Ty(String url, Object extra) throws IOException {
                    Map<String, String> headMap;
                    HttpURLConnection connect = super.Ty(url, extra);
                    if (!(extra == null || !(extra instanceof Tv.T) || (headMap = ((Tv.T) extra).f907T) == null)) {
                        for (Map.Entry<String, String> entry : headMap.entrySet()) {
                            connect.addRequestProperty(entry.getKey(), entry.getValue());
                        }
                        connect.addRequestProperty("test", "test");
                    }
                    return connect;
                }

                /* access modifiers changed from: protected */
                public InputStream Tr(String imageUri, Object extra) throws IOException {
                    return super.Tr(imageUri, extra);
                }
            }).T(Tn).T(TZ.LIFO).Tr(3).T(1).T();
            Tn.T().T(Ty);
            C0017T.T9().T(Ty);
        }
    }

    /* renamed from: com.txznet.comm.Ty.T.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public static class C0017T extends Tn {
        private static C0017T Tr = new C0017T();

        public static C0017T T9() {
            return Tr;
        }
    }
}
