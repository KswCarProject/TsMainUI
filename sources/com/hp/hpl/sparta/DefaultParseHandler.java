package com.hp.hpl.sparta;

public class DefaultParseHandler implements ParseHandler {
    private ParseSource parseSource_ = null;

    public void characters(char[] cArr, int i, int i2) throws ParseException {
    }

    public void endDocument() throws ParseException {
    }

    public void endElement(Element element) throws ParseException {
    }

    public ParseSource getParseSource() {
        return this.parseSource_;
    }

    public void setParseSource(ParseSource parseSource) {
        this.parseSource_ = parseSource;
    }

    public void startDocument() throws ParseException {
    }

    public void startElement(Element element) throws ParseException {
    }
}
