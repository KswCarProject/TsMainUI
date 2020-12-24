package com.google.zxing.client.j2se;

import com.txznet.sdk.tongting.IConstantData;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

public final class ImageReader {
    private static final String BASE64TOKEN = "base64,";

    private ImageReader() {
    }

    public static BufferedImage readImage(URI uri) throws IOException {
        if (IConstantData.KEY_DATA.equals(uri.getScheme())) {
            return readDataURIImage(uri);
        }
        try {
            BufferedImage result = ImageIO.read(uri.toURL());
            if (result != null) {
                return result;
            }
            throw new IOException("Could not load " + uri);
        } catch (IllegalArgumentException iae) {
            throw new IOException("Resource not found: " + uri, iae);
        }
    }

    public static BufferedImage readDataURIImage(URI uri) throws IOException {
        String uriString = uri.toString();
        if (!uriString.startsWith("data:image/")) {
            throw new IOException("Unsupported data URI MIME type");
        }
        int base64Start = uriString.indexOf(BASE64TOKEN);
        if (base64Start >= 0) {
            return ImageIO.read(new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(URLDecoder.decode(uriString.substring(BASE64TOKEN.length() + base64Start), "UTF-8"))));
        }
        throw new IOException("Unsupported data URI encoding");
    }
}
