package com.Ty.T.Tr.Tn;

import com.android.SdkConstants;
import com.txznet.sdk.TXZResourceManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/* compiled from: Proguard */
public interface Tr {
    InputStream T(String str, Object obj) throws IOException;

    /* compiled from: Proguard */
    public enum T {
        HTTP("http"),
        HTTPS("https"),
        FILE("file"),
        CONTENT(SdkConstants.ATTR_CONTENT),
        ASSETS(SdkConstants.FD_ASSETS),
        DRAWABLE("drawable"),
        UNKNOWN(TXZResourceManager.STYLE_DEFAULT);
        
        private String T5;
        private String TE;

        private T(String scheme) {
            this.TE = scheme;
            this.T5 = scheme + "://";
        }

        public static T T(String uri) {
            if (uri != null) {
                for (T s : values()) {
                    if (s.Tn(uri)) {
                        return s;
                    }
                }
            }
            return UNKNOWN;
        }

        private boolean Tn(String uri) {
            return uri.toLowerCase(Locale.US).startsWith(this.T5);
        }

        public String Tr(String path) {
            return this.T5 + path;
        }

        public String Ty(String uri) {
            if (Tn(uri)) {
                return uri.substring(this.T5.length());
            }
            throw new IllegalArgumentException(String.format("URI [%1$s] doesn't have expected scheme [%2$s]", new Object[]{uri, this.TE}));
        }
    }
}
