package com.Ty.T.Tr.Tn;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import com.Ty.T.Tr.Tn.Tr;
import com.ts.bt.BtExe;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: Proguard */
public class T implements Tr {

    /* renamed from: T  reason: collision with root package name */
    protected final Context f336T;
    protected final int Tr;
    protected final int Ty;

    public T(Context context) {
        this(context, BtExe.AUTO_ANSWER_CHK_TIME, 20000);
    }

    public T(Context context, int connectTimeout, int readTimeout) {
        this.f336T = context.getApplicationContext();
        this.Tr = connectTimeout;
        this.Ty = readTimeout;
    }

    public InputStream T(String imageUri, Object extra) throws IOException {
        switch (Tr.T.T(imageUri)) {
            case HTTP:
            case HTTPS:
                return Tr(imageUri, extra);
            case FILE:
                return Tn(imageUri, extra);
            case CONTENT:
                return T9(imageUri, extra);
            case ASSETS:
                return Tk(imageUri, extra);
            case DRAWABLE:
                return TZ(imageUri, extra);
            default:
                return TE(imageUri, extra);
        }
    }

    /* access modifiers changed from: protected */
    public InputStream Tr(String imageUri, Object extra) throws IOException {
        HttpURLConnection conn = Ty(imageUri, extra);
        int redirectCount = 0;
        while (conn.getResponseCode() / 100 == 3 && redirectCount < 5) {
            conn = Ty(conn.getHeaderField("Location"), extra);
            redirectCount++;
        }
        try {
            InputStream imageStream = conn.getInputStream();
            if (T(conn)) {
                return new com.Ty.T.Tr.T.T(new BufferedInputStream(imageStream, 32768), conn.getContentLength());
            }
            com.Ty.T.Ty.Tr.T((Closeable) imageStream);
            throw new IOException("Image request failed with response code " + conn.getResponseCode());
        } catch (IOException e) {
            com.Ty.T.Ty.Tr.T(conn.getErrorStream());
            throw e;
        }
    }

    /* access modifiers changed from: protected */
    public boolean T(HttpURLConnection conn) throws IOException {
        return conn.getResponseCode() == 200;
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection Ty(String url, Object extra) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(Uri.encode(url, "@#&=*+-_.,:!?()/~'%")).openConnection();
        conn.setConnectTimeout(this.Tr);
        conn.setReadTimeout(this.Ty);
        return conn;
    }

    /* access modifiers changed from: protected */
    public InputStream Tn(String imageUri, Object extra) throws IOException {
        String filePath = Tr.T.FILE.Ty(imageUri);
        if (Tr(imageUri)) {
            return T(filePath);
        }
        return new com.Ty.T.Tr.T.T(new BufferedInputStream(new FileInputStream(filePath), 32768), (int) new File(filePath).length());
    }

    @TargetApi(8)
    private InputStream T(String filePath) {
        Bitmap bitmap;
        if (Build.VERSION.SDK_INT < 8 || (bitmap = ThumbnailUtils.createVideoThumbnail(filePath, 2)) == null) {
            return null;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return new ByteArrayInputStream(bos.toByteArray());
    }

    /* access modifiers changed from: protected */
    public InputStream T9(String imageUri, Object extra) throws FileNotFoundException {
        ContentResolver res = this.f336T.getContentResolver();
        Uri uri = Uri.parse(imageUri);
        if (Tr(uri)) {
            Bitmap bitmap = MediaStore.Video.Thumbnails.getThumbnail(res, Long.valueOf(uri.getLastPathSegment()).longValue(), 1, (BitmapFactory.Options) null);
            if (bitmap != null) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
                return new ByteArrayInputStream(bos.toByteArray());
            }
        } else if (imageUri.startsWith("content://com.android.contacts/")) {
            return T(uri);
        }
        return res.openInputStream(uri);
    }

    /* access modifiers changed from: protected */
    @TargetApi(14)
    public InputStream T(Uri uri) {
        ContentResolver res = this.f336T.getContentResolver();
        if (Build.VERSION.SDK_INT >= 14) {
            return ContactsContract.Contacts.openContactPhotoInputStream(res, uri, true);
        }
        return ContactsContract.Contacts.openContactPhotoInputStream(res, uri);
    }

    /* access modifiers changed from: protected */
    public InputStream Tk(String imageUri, Object extra) throws IOException {
        return this.f336T.getAssets().open(Tr.T.ASSETS.Ty(imageUri));
    }

    /* access modifiers changed from: protected */
    public InputStream TZ(String imageUri, Object extra) {
        return this.f336T.getResources().openRawResource(Integer.parseInt(Tr.T.DRAWABLE.Ty(imageUri)));
    }

    /* access modifiers changed from: protected */
    public InputStream TE(String imageUri, Object extra) throws IOException {
        throw new UnsupportedOperationException(String.format("UIL doesn't support scheme(protocol) by default [%s]. You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))", new Object[]{imageUri}));
    }

    private boolean Tr(Uri uri) {
        String mimeType = this.f336T.getContentResolver().getType(uri);
        return mimeType != null && mimeType.startsWith("video/");
    }

    private boolean Tr(String uri) {
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri));
        return mimeType != null && mimeType.startsWith("video/");
    }
}
