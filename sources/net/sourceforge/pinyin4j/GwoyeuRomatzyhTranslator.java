package net.sourceforge.pinyin4j;

import com.hp.hpl.sparta.Element;
import com.hp.hpl.sparta.ParseException;

class GwoyeuRomatzyhTranslator {
    private static String[] tones = {"_I", "_II", "_III", "_IV", "_V"};

    GwoyeuRomatzyhTranslator() {
    }

    static String convertHanyuPinyinToGwoyeuRomatzyh(String str) {
        String extractPinyinString = TextHelper.extractPinyinString(str);
        String extractToneNumber = TextHelper.extractToneNumber(str);
        try {
            Element xpathSelectElement = GwoyeuRomatzyhResource.getInstance().getPinyinToGwoyeuMappingDoc().xpathSelectElement(new StringBuffer().append("//").append(PinyinRomanizationType.HANYU_PINYIN.getTagName()).append("[text()='").append(extractPinyinString).append("']").toString());
            if (xpathSelectElement != null) {
                return xpathSelectElement.xpathSelectString(new StringBuffer().append("../").append(PinyinRomanizationType.GWOYEU_ROMATZYH.getTagName()).append(tones[Integer.parseInt(extractToneNumber) - 1]).append("/text()").toString());
            }
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
