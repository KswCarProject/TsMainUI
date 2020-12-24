package com.hp.hpl.sparta.xpath;

import com.hp.hpl.sparta.Sparta;

public abstract class AttrCompareExpr extends AttrExpr {
    private final String attrValue_;

    AttrCompareExpr(String str, String str2) {
        super(str);
        this.attrValue_ = Sparta.intern(str2);
    }

    public String getAttrValue() {
        return this.attrValue_;
    }

    /* access modifiers changed from: protected */
    public String toString(String str) {
        return new StringBuffer().append("[").append(super.toString()).append(str).append("'").append(this.attrValue_).append("']").toString();
    }
}
