package com.hp.hpl.sparta.xpath;

public abstract class TextCompareExpr extends BooleanExpr {
    private final String value_;

    TextCompareExpr(String str) {
        this.value_ = str;
    }

    public String getValue() {
        return this.value_;
    }

    /* access modifiers changed from: protected */
    public String toString(String str) {
        return new StringBuffer().append("[text()").append(str).append("'").append(this.value_).append("']").toString();
    }
}
