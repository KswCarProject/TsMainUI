package com.hp.hpl.sparta;

public class EncodingMismatchException extends ParseException {
    private String declaredEncoding_;

    EncodingMismatchException(String str, String str2, String str3) {
        super(str, 0, str2.charAt(str2.length() - 1), str2, new StringBuffer().append("encoding '").append(str2).append("' declared instead of of ").append(str3).append(" as expected").toString());
        this.declaredEncoding_ = str2;
    }

    /* access modifiers changed from: package-private */
    public String getDeclaredEncoding() {
        return this.declaredEncoding_;
    }
}
