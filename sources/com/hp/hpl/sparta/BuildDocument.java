package com.hp.hpl.sparta;

class BuildDocument implements DocumentSource, ParseHandler {
    private Element currentElement_;
    private final Document doc_;
    private final ParseLog log_;
    private ParseSource parseSource_;

    public BuildDocument() {
        this((ParseLog) null);
    }

    public BuildDocument(ParseLog parseLog) {
        this.currentElement_ = null;
        this.doc_ = new Document();
        this.parseSource_ = null;
        this.log_ = parseLog == null ? ParseSource.DEFAULT_LOG : parseLog;
    }

    public void characters(char[] cArr, int i, int i2) {
        Element element = this.currentElement_;
        if (element.getLastChild() instanceof Text) {
            ((Text) element.getLastChild()).appendData(cArr, i, i2);
        } else {
            element.appendChildNoChecking(new Text(new String(cArr, i, i2)));
        }
    }

    public void endDocument() {
    }

    public void endElement(Element element) {
        this.currentElement_ = this.currentElement_.getParentNode();
    }

    public Document getDocument() {
        return this.doc_;
    }

    public int getLineNumber() {
        if (this.parseSource_ != null) {
            return this.parseSource_.getLineNumber();
        }
        return -1;
    }

    public ParseSource getParseSource() {
        return this.parseSource_;
    }

    public String getSystemId() {
        if (this.parseSource_ != null) {
            return this.parseSource_.getSystemId();
        }
        return null;
    }

    public void setParseSource(ParseSource parseSource) {
        this.parseSource_ = parseSource;
        this.doc_.setSystemId(parseSource.toString());
    }

    public void startDocument() {
    }

    public void startElement(Element element) {
        if (this.currentElement_ == null) {
            this.doc_.setDocumentElement(element);
        } else {
            this.currentElement_.appendChild(element);
        }
        this.currentElement_ = element;
    }

    public String toString() {
        if (this.parseSource_ != null) {
            return new StringBuffer().append("BuildDoc: ").append(this.parseSource_.toString()).toString();
        }
        return null;
    }
}
