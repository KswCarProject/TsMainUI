package com.google.zxing.client.j2se;

public final class MatrixToImageConfig {
    public static final int BLACK = -16777216;
    public static final int WHITE = -1;
    private final int offColor;
    private final int onColor;

    public MatrixToImageConfig() {
        this(-16777216, -1);
    }

    public MatrixToImageConfig(int onColor2, int offColor2) {
        this.onColor = onColor2;
        this.offColor = offColor2;
    }

    public int getPixelOnColor() {
        return this.onColor;
    }

    public int getPixelOffColor() {
        return this.offColor;
    }

    /* access modifiers changed from: package-private */
    public int getBufferedImageColorModel() {
        return (this.onColor == -16777216 && this.offColor == -1) ? 12 : 1;
    }
}
