package com.hp.hpl.sparta.xpath;

public class TextNotEqualsExpr extends TextCompareExpr {
    TextNotEqualsExpr(String str) {
        super(str);
    }

    public void accept(BooleanExprVisitor booleanExprVisitor) throws XPathException {
        booleanExprVisitor.visit(this);
    }

    public String toString() {
        return toString("!=");
    }
}
