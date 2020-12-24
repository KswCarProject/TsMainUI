package com.hp.hpl.sparta.xpath;

public abstract class NodeTest {
    public abstract void accept(Visitor visitor) throws XPathException;

    public abstract boolean isStringValue();
}
