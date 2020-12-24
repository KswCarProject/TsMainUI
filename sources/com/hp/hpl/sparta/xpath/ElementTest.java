package com.hp.hpl.sparta.xpath;

import com.hp.hpl.sparta.Sparta;

public class ElementTest extends NodeTest {
    private final String tagName_;

    ElementTest(String str) {
        this.tagName_ = Sparta.intern(str);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getTagName() {
        return this.tagName_;
    }

    public boolean isStringValue() {
        return false;
    }

    public String toString() {
        return this.tagName_;
    }
}
