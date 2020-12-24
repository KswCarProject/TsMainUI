package net.sourceforge.pinyin4j.format;

public final class HanyuPinyinOutputFormat {
    private HanyuPinyinCaseType caseType;
    private HanyuPinyinToneType toneType;
    private HanyuPinyinVCharType vCharType;

    public HanyuPinyinOutputFormat() {
        restoreDefault();
    }

    public HanyuPinyinCaseType getCaseType() {
        return this.caseType;
    }

    public HanyuPinyinToneType getToneType() {
        return this.toneType;
    }

    public HanyuPinyinVCharType getVCharType() {
        return this.vCharType;
    }

    public void restoreDefault() {
        this.vCharType = HanyuPinyinVCharType.WITH_U_AND_COLON;
        this.caseType = HanyuPinyinCaseType.LOWERCASE;
        this.toneType = HanyuPinyinToneType.WITH_TONE_NUMBER;
    }

    public void setCaseType(HanyuPinyinCaseType hanyuPinyinCaseType) {
        this.caseType = hanyuPinyinCaseType;
    }

    public void setToneType(HanyuPinyinToneType hanyuPinyinToneType) {
        this.toneType = hanyuPinyinToneType;
    }

    public void setVCharType(HanyuPinyinVCharType hanyuPinyinVCharType) {
        this.vCharType = hanyuPinyinVCharType;
    }
}
