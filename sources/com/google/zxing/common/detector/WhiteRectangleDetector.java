package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class WhiteRectangleDetector {
    private static final int CORR = 1;
    private static final int INIT_SIZE = 10;
    private final int downInit;
    private final int height;
    private final BitMatrix image;
    private final int leftInit;
    private final int rightInit;
    private final int upInit;
    private final int width;

    public WhiteRectangleDetector(BitMatrix image2) throws NotFoundException {
        this(image2, 10, image2.getWidth() / 2, image2.getHeight() / 2);
    }

    public WhiteRectangleDetector(BitMatrix image2, int initSize, int x, int y) throws NotFoundException {
        this.image = image2;
        this.height = image2.getHeight();
        this.width = image2.getWidth();
        int halfsize = initSize / 2;
        this.leftInit = x - halfsize;
        this.rightInit = x + halfsize;
        this.upInit = y - halfsize;
        this.downInit = y + halfsize;
        if (this.upInit < 0 || this.leftInit < 0 || this.downInit >= this.height || this.rightInit >= this.width) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public ResultPoint[] detect() throws NotFoundException {
        int left = this.leftInit;
        int right = this.rightInit;
        int up = this.upInit;
        int down = this.downInit;
        boolean sizeExceeded = false;
        boolean aBlackPointFoundOnBorder = true;
        boolean atLeastOneBlackPointFoundOnBorder = false;
        boolean atLeastOneBlackPointFoundOnRight = false;
        boolean atLeastOneBlackPointFoundOnBottom = false;
        boolean atLeastOneBlackPointFoundOnLeft = false;
        boolean atLeastOneBlackPointFoundOnTop = false;
        while (true) {
            if (!aBlackPointFoundOnBorder) {
                break;
            }
            aBlackPointFoundOnBorder = false;
            boolean rightBorderNotWhite = true;
            while (true) {
                if ((rightBorderNotWhite || !atLeastOneBlackPointFoundOnRight) && right < this.width) {
                    rightBorderNotWhite = containsBlackPoint(up, down, right, false);
                    if (rightBorderNotWhite) {
                        right++;
                        aBlackPointFoundOnBorder = true;
                        atLeastOneBlackPointFoundOnRight = true;
                    } else if (!atLeastOneBlackPointFoundOnRight) {
                        right++;
                    }
                }
            }
            if (right >= this.width) {
                sizeExceeded = true;
                break;
            }
            boolean bottomBorderNotWhite = true;
            while (true) {
                if ((bottomBorderNotWhite || !atLeastOneBlackPointFoundOnBottom) && down < this.height) {
                    bottomBorderNotWhite = containsBlackPoint(left, right, down, true);
                    if (bottomBorderNotWhite) {
                        down++;
                        aBlackPointFoundOnBorder = true;
                        atLeastOneBlackPointFoundOnBottom = true;
                    } else if (!atLeastOneBlackPointFoundOnBottom) {
                        down++;
                    }
                }
            }
            if (down >= this.height) {
                sizeExceeded = true;
                break;
            }
            boolean leftBorderNotWhite = true;
            while (true) {
                if ((leftBorderNotWhite || !atLeastOneBlackPointFoundOnLeft) && left >= 0) {
                    leftBorderNotWhite = containsBlackPoint(up, down, left, false);
                    if (leftBorderNotWhite) {
                        left--;
                        aBlackPointFoundOnBorder = true;
                        atLeastOneBlackPointFoundOnLeft = true;
                    } else if (!atLeastOneBlackPointFoundOnLeft) {
                        left--;
                    }
                }
            }
            if (left < 0) {
                sizeExceeded = true;
                break;
            }
            boolean topBorderNotWhite = true;
            while (true) {
                if ((topBorderNotWhite || !atLeastOneBlackPointFoundOnTop) && up >= 0) {
                    topBorderNotWhite = containsBlackPoint(left, right, up, true);
                    if (topBorderNotWhite) {
                        up--;
                        aBlackPointFoundOnBorder = true;
                        atLeastOneBlackPointFoundOnTop = true;
                    } else if (!atLeastOneBlackPointFoundOnTop) {
                        up--;
                    }
                }
            }
            if (up < 0) {
                sizeExceeded = true;
                break;
            } else if (aBlackPointFoundOnBorder) {
                atLeastOneBlackPointFoundOnBorder = true;
            }
        }
        if (sizeExceeded || !atLeastOneBlackPointFoundOnBorder) {
            throw NotFoundException.getNotFoundInstance();
        }
        int maxSize = right - left;
        ResultPoint z = null;
        int i = 1;
        while (i < maxSize && (z = getBlackPointOnSegment((float) left, (float) (down - i), (float) (left + i), (float) down)) == null) {
            i++;
        }
        if (z == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint t = null;
        int i2 = 1;
        while (i2 < maxSize && (t = getBlackPointOnSegment((float) left, (float) (up + i2), (float) (left + i2), (float) up)) == null) {
            i2++;
        }
        if (t == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint x = null;
        int i3 = 1;
        while (i3 < maxSize && (x = getBlackPointOnSegment((float) right, (float) (up + i3), (float) (right - i3), (float) up)) == null) {
            i3++;
        }
        if (x == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint y = null;
        int i4 = 1;
        while (i4 < maxSize && (y = getBlackPointOnSegment((float) right, (float) (down - i4), (float) (right - i4), (float) down)) == null) {
            i4++;
        }
        if (y != null) {
            return centerEdges(y, z, x, t);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private ResultPoint getBlackPointOnSegment(float aX, float aY, float bX, float bY) {
        int dist = MathUtils.round(MathUtils.distance(aX, aY, bX, bY));
        float xStep = (bX - aX) / ((float) dist);
        float yStep = (bY - aY) / ((float) dist);
        for (int i = 0; i < dist; i++) {
            int x = MathUtils.round((((float) i) * xStep) + aX);
            int y = MathUtils.round((((float) i) * yStep) + aY);
            if (this.image.get(x, y)) {
                return new ResultPoint((float) x, (float) y);
            }
        }
        return null;
    }

    private ResultPoint[] centerEdges(ResultPoint y, ResultPoint z, ResultPoint x, ResultPoint t) {
        float yi = y.getX();
        float yj = y.getY();
        float zi = z.getX();
        float zj = z.getY();
        float xi = x.getX();
        float xj = x.getY();
        float ti = t.getX();
        float tj = t.getY();
        if (yi < ((float) this.width) / 2.0f) {
            return new ResultPoint[]{new ResultPoint(ti - 1.0f, 1.0f + tj), new ResultPoint(1.0f + zi, 1.0f + zj), new ResultPoint(xi - 1.0f, xj - 1.0f), new ResultPoint(1.0f + yi, yj - 1.0f)};
        }
        return new ResultPoint[]{new ResultPoint(1.0f + ti, 1.0f + tj), new ResultPoint(1.0f + zi, zj - 1.0f), new ResultPoint(xi - 1.0f, 1.0f + xj), new ResultPoint(yi - 1.0f, yj - 1.0f)};
    }

    private boolean containsBlackPoint(int a, int b, int fixed, boolean horizontal) {
        if (horizontal) {
            for (int x = a; x <= b; x++) {
                if (this.image.get(x, fixed)) {
                    return true;
                }
            }
        } else {
            for (int y = a; y <= b; y++) {
                if (this.image.get(fixed, y)) {
                    return true;
                }
            }
        }
        return false;
    }
}
