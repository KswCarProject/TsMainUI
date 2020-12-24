package com.google.zxing.client.j2se;

import com.google.zxing.DecodeHintType;
import java.util.Map;

final class Config {
    private boolean brief;
    private int[] crop;
    private boolean dumpBlackPoint;
    private boolean dumpResults;
    private Map<DecodeHintType, ?> hints;
    private boolean multi;
    private String[] possibleFormats;
    private boolean productsOnly;
    private boolean pureBarcode;
    private boolean recursive;
    private boolean tryHarder;

    Config() {
    }

    /* access modifiers changed from: package-private */
    public Map<DecodeHintType, ?> getHints() {
        return this.hints;
    }

    /* access modifiers changed from: package-private */
    public void setHints(Map<DecodeHintType, ?> hints2) {
        this.hints = hints2;
    }

    /* access modifiers changed from: package-private */
    public boolean isTryHarder() {
        return this.tryHarder;
    }

    /* access modifiers changed from: package-private */
    public void setTryHarder(boolean tryHarder2) {
        this.tryHarder = tryHarder2;
    }

    /* access modifiers changed from: package-private */
    public boolean isPureBarcode() {
        return this.pureBarcode;
    }

    /* access modifiers changed from: package-private */
    public void setPureBarcode(boolean pureBarcode2) {
        this.pureBarcode = pureBarcode2;
    }

    /* access modifiers changed from: package-private */
    public boolean isProductsOnly() {
        return this.productsOnly;
    }

    /* access modifiers changed from: package-private */
    public void setProductsOnly(boolean productsOnly2) {
        this.productsOnly = productsOnly2;
    }

    /* access modifiers changed from: package-private */
    public boolean isDumpResults() {
        return this.dumpResults;
    }

    /* access modifiers changed from: package-private */
    public void setDumpResults(boolean dumpResults2) {
        this.dumpResults = dumpResults2;
    }

    /* access modifiers changed from: package-private */
    public boolean isDumpBlackPoint() {
        return this.dumpBlackPoint;
    }

    /* access modifiers changed from: package-private */
    public void setDumpBlackPoint(boolean dumpBlackPoint2) {
        this.dumpBlackPoint = dumpBlackPoint2;
    }

    /* access modifiers changed from: package-private */
    public boolean isMulti() {
        return this.multi;
    }

    /* access modifiers changed from: package-private */
    public void setMulti(boolean multi2) {
        this.multi = multi2;
    }

    /* access modifiers changed from: package-private */
    public boolean isBrief() {
        return this.brief;
    }

    /* access modifiers changed from: package-private */
    public void setBrief(boolean brief2) {
        this.brief = brief2;
    }

    /* access modifiers changed from: package-private */
    public boolean isRecursive() {
        return this.recursive;
    }

    /* access modifiers changed from: package-private */
    public void setRecursive(boolean recursive2) {
        this.recursive = recursive2;
    }

    /* access modifiers changed from: package-private */
    public int[] getCrop() {
        return this.crop;
    }

    /* access modifiers changed from: package-private */
    public void setCrop(int[] crop2) {
        this.crop = crop2;
    }

    /* access modifiers changed from: package-private */
    public String[] getPossibleFormats() {
        return this.possibleFormats;
    }

    /* access modifiers changed from: package-private */
    public void setPossibleFormats(String[] possibleFormats2) {
        this.possibleFormats = possibleFormats2;
    }
}
