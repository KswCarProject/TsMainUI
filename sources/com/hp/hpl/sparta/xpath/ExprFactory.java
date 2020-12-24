package com.hp.hpl.sparta.xpath;

import com.txznet.sdk.tongting.IConstantData;
import java.io.IOException;

public class ExprFactory {
    static BooleanExpr createExpr(XPath xPath, SimpleStreamTokenizer simpleStreamTokenizer) throws XPathException, IOException {
        int i;
        int i2;
        switch (simpleStreamTokenizer.ttype) {
            case -3:
                if (!simpleStreamTokenizer.sval.equals("text")) {
                    throw new XPathException(xPath, "at beginning of expression", simpleStreamTokenizer, "text()");
                } else if (simpleStreamTokenizer.nextToken() != 40) {
                    throw new XPathException(xPath, "after text", simpleStreamTokenizer, "(");
                } else if (simpleStreamTokenizer.nextToken() != 41) {
                    throw new XPathException(xPath, "after text(", simpleStreamTokenizer, ")");
                } else {
                    switch (simpleStreamTokenizer.nextToken()) {
                        case 33:
                            simpleStreamTokenizer.nextToken();
                            if (simpleStreamTokenizer.ttype != 61) {
                                throw new XPathException(xPath, "after !", simpleStreamTokenizer, "=");
                            }
                            simpleStreamTokenizer.nextToken();
                            if (simpleStreamTokenizer.ttype == 34 || simpleStreamTokenizer.ttype == 39) {
                                String str = simpleStreamTokenizer.sval;
                                simpleStreamTokenizer.nextToken();
                                return new TextNotEqualsExpr(str);
                            }
                            throw new XPathException(xPath, "right hand side of !=", simpleStreamTokenizer, "quoted string");
                        case 61:
                            simpleStreamTokenizer.nextToken();
                            if (simpleStreamTokenizer.ttype == 34 || simpleStreamTokenizer.ttype == 39) {
                                String str2 = simpleStreamTokenizer.sval;
                                simpleStreamTokenizer.nextToken();
                                return new TextEqualsExpr(str2);
                            }
                            throw new XPathException(xPath, "right hand side of equals", simpleStreamTokenizer, "quoted string");
                        default:
                            return TextExistsExpr.INSTANCE;
                    }
                }
            case -2:
                int i3 = simpleStreamTokenizer.nval;
                simpleStreamTokenizer.nextToken();
                return new PositionEqualsExpr(i3);
            case 64:
                if (simpleStreamTokenizer.nextToken() != -3) {
                    throw new XPathException(xPath, "after @", simpleStreamTokenizer, IConstantData.KEY_NAME);
                }
                String str3 = simpleStreamTokenizer.sval;
                switch (simpleStreamTokenizer.nextToken()) {
                    case 33:
                        simpleStreamTokenizer.nextToken();
                        if (simpleStreamTokenizer.ttype != 61) {
                            throw new XPathException(xPath, "after !", simpleStreamTokenizer, "=");
                        }
                        simpleStreamTokenizer.nextToken();
                        if (simpleStreamTokenizer.ttype == 34 || simpleStreamTokenizer.ttype == 39) {
                            String str4 = simpleStreamTokenizer.sval;
                            simpleStreamTokenizer.nextToken();
                            return new AttrNotEqualsExpr(str3, str4);
                        }
                        throw new XPathException(xPath, "right hand side of !=", simpleStreamTokenizer, "quoted string");
                    case 60:
                        simpleStreamTokenizer.nextToken();
                        if (simpleStreamTokenizer.ttype == 34 || simpleStreamTokenizer.ttype == 39) {
                            i2 = Integer.parseInt(simpleStreamTokenizer.sval);
                        } else if (simpleStreamTokenizer.ttype == -2) {
                            i2 = simpleStreamTokenizer.nval;
                        } else {
                            throw new XPathException(xPath, "right hand side of less-than", simpleStreamTokenizer, "quoted string or number");
                        }
                        simpleStreamTokenizer.nextToken();
                        return new AttrLessExpr(str3, i2);
                    case 61:
                        simpleStreamTokenizer.nextToken();
                        if (simpleStreamTokenizer.ttype == 34 || simpleStreamTokenizer.ttype == 39) {
                            String str5 = simpleStreamTokenizer.sval;
                            simpleStreamTokenizer.nextToken();
                            return new AttrEqualsExpr(str3, str5);
                        }
                        throw new XPathException(xPath, "right hand side of equals", simpleStreamTokenizer, "quoted string");
                    case 62:
                        simpleStreamTokenizer.nextToken();
                        if (simpleStreamTokenizer.ttype == 34 || simpleStreamTokenizer.ttype == 39) {
                            i = Integer.parseInt(simpleStreamTokenizer.sval);
                        } else if (simpleStreamTokenizer.ttype == -2) {
                            i = simpleStreamTokenizer.nval;
                        } else {
                            throw new XPathException(xPath, "right hand side of greater-than", simpleStreamTokenizer, "quoted string or number");
                        }
                        simpleStreamTokenizer.nextToken();
                        return new AttrGreaterExpr(str3, i);
                    default:
                        return new AttrExistsExpr(str3);
                }
            default:
                throw new XPathException(xPath, "at beginning of expression", simpleStreamTokenizer, "@, number, or text()");
        }
    }
}
