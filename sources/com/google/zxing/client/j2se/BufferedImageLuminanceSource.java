package com.google.zxing.client.j2se;

import com.google.zxing.LuminanceSource;
import com.ts.can.CanCameraUI;
import com.yyw.ts70xhw.KeyDef;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;

public final class BufferedImageLuminanceSource extends LuminanceSource {
    private static final double MINUS_45_IN_RADIANS = -0.7853981633974483d;
    private final BufferedImage image;
    private final int left;
    private final int top;

    public BufferedImageLuminanceSource(BufferedImage image2) {
        this(image2, 0, 0, image2.getWidth(), image2.getHeight());
    }

    public BufferedImageLuminanceSource(BufferedImage image2, int left2, int top2, int width, int height) {
        super(width, height);
        if (image2.getType() == 10) {
            this.image = image2;
        } else {
            int sourceWidth = image2.getWidth();
            int sourceHeight = image2.getHeight();
            if (left2 + width > sourceWidth || top2 + height > sourceHeight) {
                throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
            }
            this.image = new BufferedImage(sourceWidth, sourceHeight, 10);
            WritableRaster raster = this.image.getRaster();
            int[] buffer = new int[width];
            for (int y = top2; y < top2 + height; y++) {
                image2.getRGB(left2, y, width, 1, buffer, 0, sourceWidth);
                for (int x = 0; x < width; x++) {
                    int pixel = buffer[x];
                    if ((-16777216 & pixel) == 0) {
                        pixel = -1;
                    }
                    buffer[x] = ((((((pixel >> 16) & 255) * KeyDef.RKEY_MEDIA_TITLE) + (((pixel >> 8) & 255) * CanCameraUI.BTN_GOLF_WC_MODE2)) + ((pixel & 255) * 117)) + 512) >> 10;
                }
                raster.setPixels(left2, y, width, 1, buffer);
            }
        }
        this.left = left2;
        this.top = top2;
    }

    public byte[] getRow(int y, byte[] row) {
        if (y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + y);
        }
        int width = getWidth();
        if (row == null || row.length < width) {
            row = new byte[width];
        }
        this.image.getRaster().getDataElements(this.left, this.top + y, width, 1, row);
        return row;
    }

    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        byte[] matrix = new byte[(width * height)];
        this.image.getRaster().getDataElements(this.left, this.top, width, height, matrix);
        return matrix;
    }

    public boolean isCropSupported() {
        return true;
    }

    public LuminanceSource crop(int left2, int top2, int width, int height) {
        return new BufferedImageLuminanceSource(this.image, this.left + left2, this.top + top2, width, height);
    }

    public boolean isRotateSupported() {
        return true;
    }

    public LuminanceSource rotateCounterClockwise() {
        int sourceWidth = this.image.getWidth();
        int sourceHeight = this.image.getHeight();
        AffineTransform transform = new AffineTransform(0.0d, -1.0d, 1.0d, 0.0d, 0.0d, (double) sourceWidth);
        BufferedImage rotatedImage = new BufferedImage(sourceHeight, sourceWidth, 10);
        Graphics2D g = rotatedImage.createGraphics();
        g.drawImage(this.image, transform, (ImageObserver) null);
        g.dispose();
        int width = getWidth();
        return new BufferedImageLuminanceSource(rotatedImage, this.top, sourceWidth - (this.left + width), getHeight(), width);
    }

    public LuminanceSource rotateCounterClockwise45() {
        int width = getWidth();
        int height = getHeight();
        int oldCenterX = this.left + (width / 2);
        int oldCenterY = this.top + (height / 2);
        AffineTransform transform = AffineTransform.getRotateInstance(MINUS_45_IN_RADIANS, (double) oldCenterX, (double) oldCenterY);
        int sourceDimension = Math.max(this.image.getWidth(), this.image.getHeight());
        BufferedImage rotatedImage = new BufferedImage(sourceDimension, sourceDimension, 10);
        Graphics2D g = rotatedImage.createGraphics();
        g.drawImage(this.image, transform, (ImageObserver) null);
        g.dispose();
        int halfDimension = Math.max(width, height) / 2;
        int newLeft = Math.max(0, oldCenterX - halfDimension);
        int newTop = Math.max(0, oldCenterY - halfDimension);
        return new BufferedImageLuminanceSource(rotatedImage, newLeft, newTop, Math.min(sourceDimension - 1, oldCenterX + halfDimension) - newLeft, Math.min(sourceDimension - 1, oldCenterY + halfDimension) - newTop);
    }
}
