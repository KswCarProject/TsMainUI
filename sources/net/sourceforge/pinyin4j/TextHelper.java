package net.sourceforge.pinyin4j;

class TextHelper {
    TextHelper() {
    }

    static String extractPinyinString(String str) {
        return str.substring(0, str.length() - 1);
    }

    static String extractToneNumber(String str) {
        return str.substring(str.length() - 1);
    }
}
