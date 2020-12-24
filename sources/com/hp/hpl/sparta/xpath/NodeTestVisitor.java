package com.hp.hpl.sparta.xpath;

public interface NodeTestVisitor {
    void visit(AllElementTest allElementTest);

    void visit(AttrTest attrTest);

    void visit(ElementTest elementTest);

    void visit(ParentNodeTest parentNodeTest) throws XPathException;

    void visit(TextTest textTest);

    void visit(ThisNodeTest thisNodeTest);
}
