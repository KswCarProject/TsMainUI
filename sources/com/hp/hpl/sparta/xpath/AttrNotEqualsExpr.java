package com.hp.hpl.sparta.xpath;

public class AttrNotEqualsExpr extends AttrCompareExpr {
    AttrNotEqualsExpr(String str, String str2) {
        super(str, str2);
    }

    public void accept(BooleanExprVisitor booleanExprVisitor) throws XPathException {
        booleanExprVisitor.visit(this);
    }

    public String toString() {
        return toString("!=");
    }
}
