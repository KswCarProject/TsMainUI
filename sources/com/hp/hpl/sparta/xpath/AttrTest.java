package com.hp.hpl.sparta.xpath;

import com.android.SdkConstants;

public class AttrTest extends NodeTest {
    private final String attrName_;

    AttrTest(String str) {
        this.attrName_ = str;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getAttrName() {
        return this.attrName_;
    }

    public boolean isStringValue() {
        return true;
    }

    public String toString() {
        return new StringBuffer().append(SdkConstants.PREFIX_RESOURCE_REF).append(this.attrName_).toString();
    }
}
