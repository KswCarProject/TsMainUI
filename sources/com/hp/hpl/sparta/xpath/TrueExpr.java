package com.hp.hpl.sparta.xpath;

import com.txznet.sdk.TXZResourceManager;

public class TrueExpr extends BooleanExpr {
    static final TrueExpr INSTANCE = new TrueExpr();

    private TrueExpr() {
    }

    public void accept(BooleanExprVisitor booleanExprVisitor) {
        booleanExprVisitor.visit(this);
    }

    public String toString() {
        return TXZResourceManager.STYLE_DEFAULT;
    }
}
