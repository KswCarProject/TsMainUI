package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FinderPatternFinder {
    private static final int CENTER_QUORUM = 2;
    protected static final int MAX_MODULES = 57;
    protected static final int MIN_SKIP = 3;
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final List<FinderPattern> possibleCenters;
    private final ResultPointCallback resultPointCallback;

    public FinderPatternFinder(BitMatrix image2) {
        this(image2, (ResultPointCallback) null);
    }

    public FinderPatternFinder(BitMatrix image2, ResultPointCallback resultPointCallback2) {
        this.image = image2;
        this.possibleCenters = new ArrayList();
        this.crossCheckStateCount = new int[5];
        this.resultPointCallback = resultPointCallback2;
    }

    /* access modifiers changed from: protected */
    public final BitMatrix getImage() {
        return this.image;
    }

    /* access modifiers changed from: protected */
    public final List<FinderPattern> getPossibleCenters() {
        return this.possibleCenters;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003c A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x000f  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0123 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.zxing.qrcode.detector.FinderPatternInfo find(java.util.Map<com.google.zxing.DecodeHintType, ?> r17) throws com.google.zxing.NotFoundException {
        /*
            r16 = this;
            if (r17 == 0) goto L_0x0070
            com.google.zxing.DecodeHintType r14 = com.google.zxing.DecodeHintType.TRY_HARDER
            r0 = r17
            boolean r14 = r0.containsKey(r14)
            if (r14 == 0) goto L_0x0070
            r13 = 1
        L_0x000d:
            if (r17 == 0) goto L_0x0072
            com.google.zxing.DecodeHintType r14 = com.google.zxing.DecodeHintType.PURE_BARCODE
            r0 = r17
            boolean r14 = r0.containsKey(r14)
            if (r14 == 0) goto L_0x0072
            r10 = 1
        L_0x001a:
            r0 = r16
            com.google.zxing.common.BitMatrix r14 = r0.image
            int r7 = r14.getHeight()
            r0 = r16
            com.google.zxing.common.BitMatrix r14 = r0.image
            int r8 = r14.getWidth()
            int r14 = r7 * 3
            int r5 = r14 / 228
            r14 = 3
            if (r5 < r14) goto L_0x0033
            if (r13 == 0) goto L_0x0034
        L_0x0033:
            r5 = 3
        L_0x0034:
            r3 = 0
            r14 = 5
            int[] r12 = new int[r14]
            int r4 = r5 + -1
        L_0x003a:
            if (r4 >= r7) goto L_0x0126
            if (r3 != 0) goto L_0x0126
            r14 = 0
            r15 = 0
            r12[r14] = r15
            r14 = 1
            r15 = 0
            r12[r14] = r15
            r14 = 2
            r15 = 0
            r12[r14] = r15
            r14 = 3
            r15 = 0
            r12[r14] = r15
            r14 = 4
            r15 = 0
            r12[r14] = r15
            r2 = 0
            r6 = 0
        L_0x0054:
            if (r6 >= r8) goto L_0x0108
            r0 = r16
            com.google.zxing.common.BitMatrix r14 = r0.image
            boolean r14 = r14.get(r6, r4)
            if (r14 == 0) goto L_0x0074
            r14 = r2 & 1
            r15 = 1
            if (r14 != r15) goto L_0x0067
            int r2 = r2 + 1
        L_0x0067:
            r14 = r12[r2]
            int r14 = r14 + 1
            r12[r2] = r14
        L_0x006d:
            int r6 = r6 + 1
            goto L_0x0054
        L_0x0070:
            r13 = 0
            goto L_0x000d
        L_0x0072:
            r10 = 0
            goto L_0x001a
        L_0x0074:
            r14 = r2 & 1
            if (r14 != 0) goto L_0x0100
            r14 = 4
            if (r2 != r14) goto L_0x00f6
            boolean r14 = foundPatternCross(r12)
            if (r14 == 0) goto L_0x00d9
            r0 = r16
            boolean r1 = r0.handlePossibleCenter(r12, r4, r6, r10)
            if (r1 == 0) goto L_0x00bd
            r5 = 2
            r0 = r16
            boolean r14 = r0.hasSkipped
            if (r14 == 0) goto L_0x00aa
            boolean r3 = r16.haveMultiplyConfirmedCenters()
        L_0x0094:
            r2 = 0
            r14 = 0
            r15 = 0
            r12[r14] = r15
            r14 = 1
            r15 = 0
            r12[r14] = r15
            r14 = 2
            r15 = 0
            r12[r14] = r15
            r14 = 3
            r15 = 0
            r12[r14] = r15
            r14 = 4
            r15 = 0
            r12[r14] = r15
            goto L_0x006d
        L_0x00aa:
            int r11 = r16.findRowSkip()
            r14 = 2
            r14 = r12[r14]
            if (r11 <= r14) goto L_0x0094
            r14 = 2
            r14 = r12[r14]
            int r14 = r11 - r14
            int r14 = r14 - r5
            int r4 = r4 + r14
            int r6 = r8 + -1
            goto L_0x0094
        L_0x00bd:
            r14 = 0
            r15 = 2
            r15 = r12[r15]
            r12[r14] = r15
            r14 = 1
            r15 = 3
            r15 = r12[r15]
            r12[r14] = r15
            r14 = 2
            r15 = 4
            r15 = r12[r15]
            r12[r14] = r15
            r14 = 3
            r15 = 1
            r12[r14] = r15
            r14 = 4
            r15 = 0
            r12[r14] = r15
            r2 = 3
            goto L_0x006d
        L_0x00d9:
            r14 = 0
            r15 = 2
            r15 = r12[r15]
            r12[r14] = r15
            r14 = 1
            r15 = 3
            r15 = r12[r15]
            r12[r14] = r15
            r14 = 2
            r15 = 4
            r15 = r12[r15]
            r12[r14] = r15
            r14 = 3
            r15 = 1
            r12[r14] = r15
            r14 = 4
            r15 = 0
            r12[r14] = r15
            r2 = 3
            goto L_0x006d
        L_0x00f6:
            int r2 = r2 + 1
            r14 = r12[r2]
            int r14 = r14 + 1
            r12[r2] = r14
            goto L_0x006d
        L_0x0100:
            r14 = r12[r2]
            int r14 = r14 + 1
            r12[r2] = r14
            goto L_0x006d
        L_0x0108:
            boolean r14 = foundPatternCross(r12)
            if (r14 == 0) goto L_0x0123
            r0 = r16
            boolean r1 = r0.handlePossibleCenter(r12, r4, r8, r10)
            if (r1 == 0) goto L_0x0123
            r14 = 0
            r5 = r12[r14]
            r0 = r16
            boolean r14 = r0.hasSkipped
            if (r14 == 0) goto L_0x0123
            boolean r3 = r16.haveMultiplyConfirmedCenters()
        L_0x0123:
            int r4 = r4 + r5
            goto L_0x003a
        L_0x0126:
            com.google.zxing.qrcode.detector.FinderPattern[] r9 = r16.selectBestPatterns()
            com.google.zxing.ResultPoint.orderBestPatterns(r9)
            com.google.zxing.qrcode.detector.FinderPatternInfo r14 = new com.google.zxing.qrcode.detector.FinderPatternInfo
            r14.<init>(r9)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.find(java.util.Map):com.google.zxing.qrcode.detector.FinderPatternInfo");
    }

    private static float centerFromEnd(int[] stateCount, int end) {
        return ((float) ((end - stateCount[4]) - stateCount[3])) - (((float) stateCount[2]) / 2.0f);
    }

    protected static boolean foundPatternCross(int[] stateCount) {
        boolean z = true;
        int totalModuleSize = 0;
        for (int i = 0; i < 5; i++) {
            int count = stateCount[i];
            if (count == 0) {
                return false;
            }
            totalModuleSize += count;
        }
        if (totalModuleSize < 7) {
            return false;
        }
        float moduleSize = ((float) totalModuleSize) / 7.0f;
        float maxVariance = moduleSize / 2.0f;
        if (Math.abs(moduleSize - ((float) stateCount[0])) >= maxVariance || Math.abs(moduleSize - ((float) stateCount[1])) >= maxVariance || Math.abs((3.0f * moduleSize) - ((float) stateCount[2])) >= 3.0f * maxVariance || Math.abs(moduleSize - ((float) stateCount[3])) >= maxVariance || Math.abs(moduleSize - ((float) stateCount[4])) >= maxVariance) {
            z = false;
        }
        return z;
    }

    private int[] getCrossCheckStateCount() {
        this.crossCheckStateCount[0] = 0;
        this.crossCheckStateCount[1] = 0;
        this.crossCheckStateCount[2] = 0;
        this.crossCheckStateCount[3] = 0;
        this.crossCheckStateCount[4] = 0;
        return this.crossCheckStateCount;
    }

    private boolean crossCheckDiagonal(int startI, int centerJ, int maxCount, int originalStateCountTotal) {
        int[] stateCount = getCrossCheckStateCount();
        int i = 0;
        while (startI >= i && centerJ >= i && this.image.get(centerJ - i, startI - i)) {
            stateCount[2] = stateCount[2] + 1;
            i++;
        }
        if (startI < i || centerJ < i) {
            return false;
        }
        while (startI >= i && centerJ >= i && !this.image.get(centerJ - i, startI - i) && stateCount[1] <= maxCount) {
            stateCount[1] = stateCount[1] + 1;
            i++;
        }
        if (startI < i || centerJ < i || stateCount[1] > maxCount) {
            return false;
        }
        while (startI >= i && centerJ >= i && this.image.get(centerJ - i, startI - i) && stateCount[0] <= maxCount) {
            stateCount[0] = stateCount[0] + 1;
            i++;
        }
        if (stateCount[0] > maxCount) {
            return false;
        }
        int maxI = this.image.getHeight();
        int maxJ = this.image.getWidth();
        int i2 = 1;
        while (startI + i2 < maxI && centerJ + i2 < maxJ && this.image.get(centerJ + i2, startI + i2)) {
            stateCount[2] = stateCount[2] + 1;
            i2++;
        }
        if (startI + i2 >= maxI || centerJ + i2 >= maxJ) {
            return false;
        }
        while (startI + i2 < maxI && centerJ + i2 < maxJ && !this.image.get(centerJ + i2, startI + i2) && stateCount[3] < maxCount) {
            stateCount[3] = stateCount[3] + 1;
            i2++;
        }
        if (startI + i2 >= maxI || centerJ + i2 >= maxJ || stateCount[3] >= maxCount) {
            return false;
        }
        while (startI + i2 < maxI && centerJ + i2 < maxJ && this.image.get(centerJ + i2, startI + i2) && stateCount[4] < maxCount) {
            stateCount[4] = stateCount[4] + 1;
            i2++;
        }
        return stateCount[4] < maxCount && Math.abs(((((stateCount[0] + stateCount[1]) + stateCount[2]) + stateCount[3]) + stateCount[4]) - originalStateCountTotal) < originalStateCountTotal * 2 && foundPatternCross(stateCount);
    }

    private float crossCheckVertical(int startI, int centerJ, int maxCount, int originalStateCountTotal) {
        BitMatrix image2 = this.image;
        int maxI = image2.getHeight();
        int[] stateCount = getCrossCheckStateCount();
        int i = startI;
        while (i >= 0 && image2.get(centerJ, i)) {
            stateCount[2] = stateCount[2] + 1;
            i--;
        }
        if (i < 0) {
            return Float.NaN;
        }
        while (i >= 0 && !image2.get(centerJ, i) && stateCount[1] <= maxCount) {
            stateCount[1] = stateCount[1] + 1;
            i--;
        }
        if (i < 0 || stateCount[1] > maxCount) {
            return Float.NaN;
        }
        while (i >= 0 && image2.get(centerJ, i) && stateCount[0] <= maxCount) {
            stateCount[0] = stateCount[0] + 1;
            i--;
        }
        if (stateCount[0] > maxCount) {
            return Float.NaN;
        }
        int i2 = startI + 1;
        while (i2 < maxI && image2.get(centerJ, i2)) {
            stateCount[2] = stateCount[2] + 1;
            i2++;
        }
        if (i2 == maxI) {
            return Float.NaN;
        }
        while (i2 < maxI && !image2.get(centerJ, i2) && stateCount[3] < maxCount) {
            stateCount[3] = stateCount[3] + 1;
            i2++;
        }
        if (i2 == maxI || stateCount[3] >= maxCount) {
            return Float.NaN;
        }
        while (i2 < maxI && image2.get(centerJ, i2) && stateCount[4] < maxCount) {
            stateCount[4] = stateCount[4] + 1;
            i2++;
        }
        if (stateCount[4] < maxCount && Math.abs(((((stateCount[0] + stateCount[1]) + stateCount[2]) + stateCount[3]) + stateCount[4]) - originalStateCountTotal) * 5 < originalStateCountTotal * 2 && foundPatternCross(stateCount)) {
            return centerFromEnd(stateCount, i2);
        }
        return Float.NaN;
    }

    private float crossCheckHorizontal(int startJ, int centerI, int maxCount, int originalStateCountTotal) {
        BitMatrix image2 = this.image;
        int maxJ = image2.getWidth();
        int[] stateCount = getCrossCheckStateCount();
        int j = startJ;
        while (j >= 0 && image2.get(j, centerI)) {
            stateCount[2] = stateCount[2] + 1;
            j--;
        }
        if (j < 0) {
            return Float.NaN;
        }
        while (j >= 0 && !image2.get(j, centerI) && stateCount[1] <= maxCount) {
            stateCount[1] = stateCount[1] + 1;
            j--;
        }
        if (j < 0 || stateCount[1] > maxCount) {
            return Float.NaN;
        }
        while (j >= 0 && image2.get(j, centerI) && stateCount[0] <= maxCount) {
            stateCount[0] = stateCount[0] + 1;
            j--;
        }
        if (stateCount[0] > maxCount) {
            return Float.NaN;
        }
        int j2 = startJ + 1;
        while (j2 < maxJ && image2.get(j2, centerI)) {
            stateCount[2] = stateCount[2] + 1;
            j2++;
        }
        if (j2 == maxJ) {
            return Float.NaN;
        }
        while (j2 < maxJ && !image2.get(j2, centerI) && stateCount[3] < maxCount) {
            stateCount[3] = stateCount[3] + 1;
            j2++;
        }
        if (j2 == maxJ || stateCount[3] >= maxCount) {
            return Float.NaN;
        }
        while (j2 < maxJ && image2.get(j2, centerI) && stateCount[4] < maxCount) {
            stateCount[4] = stateCount[4] + 1;
            j2++;
        }
        if (stateCount[4] < maxCount && Math.abs(((((stateCount[0] + stateCount[1]) + stateCount[2]) + stateCount[3]) + stateCount[4]) - originalStateCountTotal) * 5 < originalStateCountTotal && foundPatternCross(stateCount)) {
            return centerFromEnd(stateCount, j2);
        }
        return Float.NaN;
    }

    /* access modifiers changed from: protected */
    public final boolean handlePossibleCenter(int[] stateCount, int i, int j, boolean pureBarcode) {
        int stateCountTotal = stateCount[0] + stateCount[1] + stateCount[2] + stateCount[3] + stateCount[4];
        float centerJ = centerFromEnd(stateCount, j);
        float centerI = crossCheckVertical(i, (int) centerJ, stateCount[2], stateCountTotal);
        if (!Float.isNaN(centerI)) {
            float centerJ2 = crossCheckHorizontal((int) centerJ, (int) centerI, stateCount[2], stateCountTotal);
            if (!Float.isNaN(centerJ2) && (!pureBarcode || crossCheckDiagonal((int) centerI, (int) centerJ2, stateCount[2], stateCountTotal))) {
                float estimatedModuleSize = ((float) stateCountTotal) / 7.0f;
                boolean found = false;
                int index = 0;
                while (true) {
                    if (index >= this.possibleCenters.size()) {
                        break;
                    }
                    FinderPattern center = this.possibleCenters.get(index);
                    if (center.aboutEquals(estimatedModuleSize, centerI, centerJ2)) {
                        this.possibleCenters.set(index, center.combineEstimate(centerI, centerJ2, estimatedModuleSize));
                        found = true;
                        break;
                    }
                    index++;
                }
                if (!found) {
                    FinderPattern point = new FinderPattern(centerJ2, centerI, estimatedModuleSize);
                    this.possibleCenters.add(point);
                    if (this.resultPointCallback != null) {
                        this.resultPointCallback.foundPossibleResultPoint(point);
                    }
                }
                return true;
            }
        }
        return false;
    }

    private int findRowSkip() {
        if (this.possibleCenters.size() <= 1) {
            return 0;
        }
        ResultPoint firstConfirmedCenter = null;
        for (FinderPattern center : this.possibleCenters) {
            if (center.getCount() >= 2) {
                if (firstConfirmedCenter == null) {
                    firstConfirmedCenter = center;
                } else {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(firstConfirmedCenter.getX() - center.getX()) - Math.abs(firstConfirmedCenter.getY() - center.getY()))) / 2;
                }
            }
        }
        return 0;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int confirmedCount = 0;
        float totalModuleSize = 0.0f;
        int max = this.possibleCenters.size();
        for (FinderPattern pattern : this.possibleCenters) {
            if (pattern.getCount() >= 2) {
                confirmedCount++;
                totalModuleSize += pattern.getEstimatedModuleSize();
            }
        }
        if (confirmedCount < 3) {
            return false;
        }
        float average = totalModuleSize / ((float) max);
        float totalDeviation = 0.0f;
        for (FinderPattern pattern2 : this.possibleCenters) {
            totalDeviation += Math.abs(pattern2.getEstimatedModuleSize() - average);
        }
        if (totalDeviation <= 0.05f * totalModuleSize) {
            return true;
        }
        return false;
    }

    private FinderPattern[] selectBestPatterns() throws NotFoundException {
        int startSize = this.possibleCenters.size();
        if (startSize < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (startSize > 3) {
            float totalModuleSize = 0.0f;
            float square = 0.0f;
            for (FinderPattern center : this.possibleCenters) {
                float size = center.getEstimatedModuleSize();
                totalModuleSize += size;
                square += size * size;
            }
            float average = totalModuleSize / ((float) startSize);
            float stdDev = (float) Math.sqrt((double) ((square / ((float) startSize)) - (average * average)));
            Collections.sort(this.possibleCenters, new FurthestFromAverageComparator(average));
            float limit = Math.max(0.2f * average, stdDev);
            int i = 0;
            while (i < this.possibleCenters.size() && this.possibleCenters.size() > 3) {
                if (Math.abs(this.possibleCenters.get(i).getEstimatedModuleSize() - average) > limit) {
                    this.possibleCenters.remove(i);
                    i--;
                }
                i++;
            }
        }
        if (this.possibleCenters.size() > 3) {
            float totalModuleSize2 = 0.0f;
            for (FinderPattern possibleCenter : this.possibleCenters) {
                totalModuleSize2 += possibleCenter.getEstimatedModuleSize();
            }
            Collections.sort(this.possibleCenters, new CenterComparator(totalModuleSize2 / ((float) this.possibleCenters.size())));
            this.possibleCenters.subList(3, this.possibleCenters.size()).clear();
        }
        return new FinderPattern[]{this.possibleCenters.get(0), this.possibleCenters.get(1), this.possibleCenters.get(2)};
    }

    private static final class FurthestFromAverageComparator implements Comparator<FinderPattern>, Serializable {
        private final float average;

        private FurthestFromAverageComparator(float f) {
            this.average = f;
        }

        public int compare(FinderPattern center1, FinderPattern center2) {
            float dA = Math.abs(center2.getEstimatedModuleSize() - this.average);
            float dB = Math.abs(center1.getEstimatedModuleSize() - this.average);
            if (dA < dB) {
                return -1;
            }
            return dA == dB ? 0 : 1;
        }
    }

    private static final class CenterComparator implements Comparator<FinderPattern>, Serializable {
        private final float average;

        private CenterComparator(float f) {
            this.average = f;
        }

        public int compare(FinderPattern center1, FinderPattern center2) {
            if (center2.getCount() != center1.getCount()) {
                return center2.getCount() - center1.getCount();
            }
            float dA = Math.abs(center2.getEstimatedModuleSize() - this.average);
            float dB = Math.abs(center1.getEstimatedModuleSize() - this.average);
            if (dA < dB) {
                return 1;
            }
            return dA == dB ? 0 : -1;
        }
    }
}
