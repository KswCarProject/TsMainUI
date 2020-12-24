package com.hp.hpl.sparta.xpath;

import java.io.IOException;

public class XPathException extends Exception {
    private Throwable cause_;

    XPathException(XPath xPath, Exception exc) {
        super(new StringBuffer().append(xPath).append(" ").append(exc).toString());
        this.cause_ = null;
        this.cause_ = exc;
    }

    public XPathException(XPath xPath, String str) {
        super(new StringBuffer().append(xPath).append(" ").append(str).toString());
        this.cause_ = null;
    }

    XPathException(XPath xPath, String str, SimpleStreamTokenizer simpleStreamTokenizer, String str2) {
        this(xPath, new StringBuffer().append(str).append(" got \"").append(toString(simpleStreamTokenizer)).append("\" instead of expected ").append(str2).toString());
    }

    private static String toString(SimpleStreamTokenizer simpleStreamTokenizer) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(tokenToString(simpleStreamTokenizer));
            if (simpleStreamTokenizer.ttype != -1) {
                simpleStreamTokenizer.nextToken();
                stringBuffer.append(tokenToString(simpleStreamTokenizer));
                simpleStreamTokenizer.pushBack();
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            return new StringBuffer().append("(cannot get  info: ").append(e).append(")").toString();
        }
    }

    private static String tokenToString(SimpleStreamTokenizer simpleStreamTokenizer) {
        switch (simpleStreamTokenizer.ttype) {
            case -3:
                return simpleStreamTokenizer.sval;
            case -2:
                return new StringBuffer().append(simpleStreamTokenizer.nval).append("").toString();
            case -1:
                return "<end of expression>";
            default:
                return new StringBuffer().append((char) simpleStreamTokenizer.ttype).append("").toString();
        }
    }

    public Throwable getCause() {
        return this.cause_;
    }
}
