package com.hp.hpl.sparta.xpath;

import com.android.SdkConstants;

public abstract class AttrExpr extends BooleanExpr {
    private final String attrName_;

    AttrExpr(String str) {
        this.attrName_ = str;
    }

    public String getAttrName() {
        return this.attrName_;
    }

    public String toString() {
        return new StringBuffer().append(SdkConstants.PREFIX_RESOURCE_REF).append(this.attrName_).toString();
    }
}
