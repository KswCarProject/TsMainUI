package net.sourceforge.pinyin4j;

import com.txznet.sdk.TXZResourceManager;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

class PinyinFormatter {
    PinyinFormatter() {
    }

    private static String convertToneNumber2ToneMark(String str) {
        char c;
        String lowerCase = str.toLowerCase();
        if (!lowerCase.matches("[a-z]*[1-5]?")) {
            return lowerCase;
        }
        if (!lowerCase.matches("[a-z]*[1-5]")) {
            return lowerCase.replaceAll("v", "ü");
        }
        int numericValue = Character.getNumericValue(lowerCase.charAt(lowerCase.length() - 1));
        int indexOf = lowerCase.indexOf(97);
        int indexOf2 = lowerCase.indexOf(101);
        int indexOf3 = lowerCase.indexOf("ou");
        if (-1 == indexOf) {
            if (-1 == indexOf2) {
                if (-1 == indexOf3) {
                    indexOf = lowerCase.length() - 1;
                    while (true) {
                        if (indexOf < 0) {
                            indexOf = -1;
                            c = '$';
                            break;
                        } else if (String.valueOf(lowerCase.charAt(indexOf)).matches("[aeiouv]")) {
                            c = lowerCase.charAt(indexOf);
                            break;
                        } else {
                            indexOf--;
                        }
                    }
                } else {
                    c = "ou".charAt(0);
                    indexOf = indexOf3;
                }
            } else {
                indexOf = indexOf2;
                c = 'e';
            }
        } else {
            c = 'a';
        }
        if ('$' == c || -1 == indexOf) {
            return lowerCase;
        }
        char charAt = "āáăàaēéĕèeīíĭìiōóŏòoūúŭùuǖǘǚǜü".charAt(("aeiouv".indexOf(c) * 5) + (numericValue - 1));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(lowerCase.substring(0, indexOf).replaceAll("v", "ü"));
        stringBuffer.append(charAt);
        stringBuffer.append(lowerCase.substring(indexOf + 1, lowerCase.length() - 1).replaceAll("v", "ü"));
        return stringBuffer.toString();
    }

    static String formatHanyuPinyin(String str, HanyuPinyinOutputFormat hanyuPinyinOutputFormat) throws BadHanyuPinyinOutputFormatCombination {
        if (HanyuPinyinToneType.WITH_TONE_MARK == hanyuPinyinOutputFormat.getToneType() && (HanyuPinyinVCharType.WITH_V == hanyuPinyinOutputFormat.getVCharType() || HanyuPinyinVCharType.WITH_U_AND_COLON == hanyuPinyinOutputFormat.getVCharType())) {
            throw new BadHanyuPinyinOutputFormatCombination("tone marks cannot be added to v or u:");
        }
        if (HanyuPinyinToneType.WITHOUT_TONE == hanyuPinyinOutputFormat.getToneType()) {
            str = str.replaceAll("[1-5]", TXZResourceManager.STYLE_DEFAULT);
        } else if (HanyuPinyinToneType.WITH_TONE_MARK == hanyuPinyinOutputFormat.getToneType()) {
            str = convertToneNumber2ToneMark(str.replaceAll("u:", "v"));
        }
        if (HanyuPinyinVCharType.WITH_V == hanyuPinyinOutputFormat.getVCharType()) {
            str = str.replaceAll("u:", "v");
        } else if (HanyuPinyinVCharType.WITH_U_UNICODE == hanyuPinyinOutputFormat.getVCharType()) {
            str = str.replaceAll("u:", "ü");
        }
        return HanyuPinyinCaseType.UPPERCASE == hanyuPinyinOutputFormat.getCaseType() ? str.toUpperCase() : str;
    }
}
