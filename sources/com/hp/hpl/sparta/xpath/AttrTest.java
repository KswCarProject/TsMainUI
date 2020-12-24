package com.hp.hpl.sparta.xpath;

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
        return new StringBuffer().append("@").append(this.attrName_).toString();
    }
}
