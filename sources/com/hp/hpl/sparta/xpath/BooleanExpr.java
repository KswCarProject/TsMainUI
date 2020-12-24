package com.hp.hpl.sparta.xpath;

public abstract class BooleanExpr {
    public abstract void accept(BooleanExprVisitor booleanExprVisitor) throws XPathException;
}
