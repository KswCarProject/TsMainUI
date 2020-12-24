package com.hp.hpl.sparta;

public class ParseException extends Exception {
    private Throwable cause_;
    private int lineNumber_;

    public ParseException(ParseCharStream parseCharStream, char c, char c2) {
        this(parseCharStream, new StringBuffer().append("got '").append(c).append("' instead of expected '").append(c2).append("'").toString());
    }

    public ParseException(ParseCharStream parseCharStream, char c, String str) {
        this(parseCharStream, new StringBuffer().append("got '").append(c).append("' instead of ").append(str).append(" as expected").toString());
    }

    public ParseException(ParseCharStream parseCharStream, char c, char[] cArr) {
        this(parseCharStream, new StringBuffer().append("got '").append(c).append("' instead of ").append(toString(cArr)).toString());
    }

    public ParseException(ParseCharStream parseCharStream, String str) {
        this(parseCharStream.getLog(), parseCharStream.getSystemId(), parseCharStream.getLineNumber(), parseCharStream.getLastCharRead(), parseCharStream.getHistory(), str);
    }

    public ParseException(ParseCharStream parseCharStream, String str, String str2) {
        this(parseCharStream, new StringBuffer().append("got \"").append(str).append("\" instead of \"").append(str2).append("\" as expected").toString());
    }

    public ParseException(ParseCharStream parseCharStream, String str, char[] cArr) {
        this(parseCharStream, str, new String(cArr));
    }

    public ParseException(ParseLog parseLog, String str, int i, int i2, String str2, String str3) {
        this(str, i, i2, str2, str3);
        parseLog.error(str3, str, i);
    }

    public ParseException(String str) {
        super(str);
        this.lineNumber_ = -1;
        this.cause_ = null;
    }

    public ParseException(String str, int i, int i2, String str2, String str3) {
        super(toMessage(str, i, i2, str2, str3));
        this.lineNumber_ = -1;
        this.cause_ = null;
        this.lineNumber_ = i;
    }

    public ParseException(String str, Throwable th) {
        super(new StringBuffer().append(str).append(" ").append(th).toString());
        this.lineNumber_ = -1;
        this.cause_ = null;
        this.cause_ = th;
    }

    static String charRepr(int i) {
        return i == -1 ? "EOF" : new StringBuffer().append("").append((char) i).toString();
    }

    private static String toMessage(String str, int i, int i2, String str2, String str3) {
        return new StringBuffer().append(str).append("(").append(i).append("): \n").append(str2).append("\nLast character read was '").append(charRepr(i2)).append("'\n").append(str3).toString();
    }

    private static String toString(char[] cArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(cArr[0]);
        for (int i = 1; i < cArr.length; i++) {
            stringBuffer.append(new StringBuffer().append("or ").append(cArr[i]).toString());
        }
        return stringBuffer.toString();
    }

    public Throwable getCause() {
        return this.cause_;
    }

    public int getLineNumber() {
        return this.lineNumber_;
    }
}
