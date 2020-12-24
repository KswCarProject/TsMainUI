package net.sourceforge.pinyin4j;

import com.hp.hpl.sparta.Element;
import com.hp.hpl.sparta.ParseException;

class PinyinRomanizationTranslator {
    PinyinRomanizationTranslator() {
    }

    static String convertRomanizationSystem(String str, PinyinRomanizationType pinyinRomanizationType, PinyinRomanizationType pinyinRomanizationType2) {
        String extractPinyinString = TextHelper.extractPinyinString(str);
        String extractToneNumber = TextHelper.extractToneNumber(str);
        try {
            Element xpathSelectElement = PinyinRomanizationResource.getInstance().getPinyinMappingDoc().xpathSelectElement(new StringBuffer().append("//").append(pinyinRomanizationType.getTagName()).append("[text()='").append(extractPinyinString).append("']").toString());
            if (xpathSelectElement == null) {
                return null;
            }
            return new StringBuffer().append(xpathSelectElement.xpathSelectString(new StringBuffer().append("../").append(pinyinRomanizationType2.getTagName()).append("/text()").toString())).append(extractToneNumber).toString();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
