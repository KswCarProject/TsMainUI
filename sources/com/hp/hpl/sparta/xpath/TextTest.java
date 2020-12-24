package com.hp.hpl.sparta.xpath;

public class TextTest extends NodeTest {
    static final TextTest INSTANCE = new TextTest();

    private TextTest() {
    }

    public void accept(Visitor visitor) throws XPathException {
        visitor.visit(this);
    }

    public boolean isStringValue() {
        return true;
    }

    public String toString() {
        return "text()";
    }
}
