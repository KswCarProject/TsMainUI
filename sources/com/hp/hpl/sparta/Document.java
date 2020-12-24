package com.hp.hpl.sparta;

import com.hp.hpl.sparta.Sparta;
import com.hp.hpl.sparta.xpath.Step;
import com.hp.hpl.sparta.xpath.XPath;
import com.hp.hpl.sparta.xpath.XPathException;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Document extends Node {
    private static final boolean DEBUG = false;
    static final Enumeration EMPTY = new EmptyEnumeration();
    private static final Integer ONE = new Integer(1);
    private final Hashtable indexible_;
    private Sparta.Cache indices_;
    private Vector observers_;
    private Element rootElement_;
    private String systemId_;

    public class Index implements Observer {
        private final String attrName_;
        private transient Sparta.Cache dict_ = null;
        private final Document this$0;
        private final XPath xpath_;

        Index(Document document, XPath xPath) throws XPathException {
            this.this$0 = document;
            this.attrName_ = xPath.getIndexingAttrName();
            this.xpath_ = xPath;
            document.addObserver(this);
        }

        private void regenerate() throws ParseException {
            try {
                this.dict_ = Sparta.newCache();
                Enumeration resultEnumeration = this.this$0.visitor(this.xpath_, false).getResultEnumeration();
                while (resultEnumeration.hasMoreElements()) {
                    Element element = (Element) resultEnumeration.nextElement();
                    String attribute = element.getAttribute(this.attrName_);
                    Vector vector = (Vector) this.dict_.get(attribute);
                    if (vector == null) {
                        vector = new Vector(1);
                        this.dict_.put(attribute, vector);
                    }
                    vector.addElement(element);
                }
            } catch (XPathException e) {
                throw new ParseException("XPath problem", (Throwable) e);
            }
        }

        public synchronized Enumeration get(String str) throws ParseException {
            Vector vector;
            if (this.dict_ == null) {
                regenerate();
            }
            vector = (Vector) this.dict_.get(str);
            return vector == null ? Document.EMPTY : vector.elements();
        }

        public synchronized int size() throws ParseException {
            if (this.dict_ == null) {
                regenerate();
            }
            return this.dict_.size();
        }

        public synchronized void update(Document document) {
            this.dict_ = null;
        }
    }

    public interface Observer {
        void update(Document document);
    }

    public Document() {
        this.rootElement_ = null;
        this.indices_ = Sparta.newCache();
        this.observers_ = new Vector();
        this.indexible_ = null;
        this.systemId_ = "MEMORY";
    }

    Document(String str) {
        this.rootElement_ = null;
        this.indices_ = Sparta.newCache();
        this.observers_ = new Vector();
        this.indexible_ = null;
        this.systemId_ = str;
    }

    private XPathVisitor visitor(String str, boolean z) throws XPathException {
        if (str.charAt(0) != '/') {
            str = new StringBuffer().append("/").append(str).toString();
        }
        return visitor(XPath.get(str), z);
    }

    public void addObserver(Observer observer) {
        this.observers_.addElement(observer);
    }

    public Object clone() {
        Document document = new Document(this.systemId_);
        document.rootElement_ = (Element) this.rootElement_.clone();
        return document;
    }

    /* access modifiers changed from: protected */
    public int computeHashCode() {
        return this.rootElement_.hashCode();
    }

    public void deleteObserver(Observer observer) {
        this.observers_.removeElement(observer);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Document)) {
            return false;
        }
        return this.rootElement_.equals(((Document) obj).rootElement_);
    }

    public Element getDocumentElement() {
        return this.rootElement_;
    }

    public String getSystemId() {
        return this.systemId_;
    }

    /* access modifiers changed from: package-private */
    public void monitor(XPath xPath) throws XPathException {
    }

    /* access modifiers changed from: package-private */
    public void notifyObservers() {
        Enumeration elements = this.observers_.elements();
        while (elements.hasMoreElements()) {
            ((Observer) elements.nextElement()).update(this);
        }
    }

    public void setDocumentElement(Element element) {
        this.rootElement_ = element;
        this.rootElement_.setOwnerDocument(this);
        notifyObservers();
    }

    public void setSystemId(String str) {
        this.systemId_ = str;
        notifyObservers();
    }

    public String toString() {
        return this.systemId_;
    }

    public void toString(Writer writer) throws IOException {
        this.rootElement_.toString(writer);
    }

    public void toXml(Writer writer) throws IOException {
        writer.write("<?xml version=\"1.0\" ?>\n");
        this.rootElement_.toXml(writer);
    }

    /* access modifiers changed from: package-private */
    public XPathVisitor visitor(XPath xPath, boolean z) throws XPathException {
        if (xPath.isStringValue() == z) {
            return new XPathVisitor(this, xPath);
        }
        throw new XPathException(xPath, new StringBuffer().append("\"").append(xPath).append("\" evaluates to ").append(z ? "evaluates to element not string" : "evaluates to string not element").toString());
    }

    public boolean xpathEnsure(String str) throws ParseException {
        try {
            if (xpathSelectElement(str) != null) {
                return false;
            }
            XPath xPath = XPath.get(str);
            Enumeration steps = xPath.getSteps();
            int i = 0;
            while (steps.hasMoreElements()) {
                steps.nextElement();
                i++;
            }
            Enumeration steps2 = xPath.getSteps();
            Step step = (Step) steps2.nextElement();
            Step[] stepArr = new Step[(i - 1)];
            for (int i2 = 0; i2 < stepArr.length; i2++) {
                stepArr[i2] = (Step) steps2.nextElement();
            }
            if (this.rootElement_ == null) {
                setDocumentElement(makeMatching((Element) null, step, str));
            } else if (xpathSelectElement(new StringBuffer().append("/").append(step).toString()) == null) {
                throw new ParseException(new StringBuffer().append("Existing root element <").append(this.rootElement_.getTagName()).append("...> does not match first step \"").append(step).append("\" of \"").append(str).toString());
            }
            if (stepArr.length == 0) {
                return true;
            }
            return this.rootElement_.xpathEnsure(XPath.get(false, stepArr).toString());
        } catch (XPathException e) {
            throw new ParseException(str, (Throwable) e);
        }
    }

    public Index xpathGetIndex(String str) throws ParseException {
        try {
            Index index = (Index) this.indices_.get(str);
            if (index != null) {
                return index;
            }
            Index index2 = new Index(this, XPath.get(str));
            this.indices_.put(str, index2);
            return index2;
        } catch (XPathException e) {
            throw new ParseException("XPath problem", (Throwable) e);
        }
    }

    public boolean xpathHasIndex(String str) {
        return this.indices_.get(str) != null;
    }

    public Element xpathSelectElement(String str) throws ParseException {
        try {
            if (str.charAt(0) != '/') {
                str = new StringBuffer().append("/").append(str).toString();
            }
            XPath xPath = XPath.get(str);
            monitor(xPath);
            return visitor(xPath, false).getFirstResultElement();
        } catch (XPathException e) {
            throw new ParseException("XPath problem", (Throwable) e);
        }
    }

    public Enumeration xpathSelectElements(String str) throws ParseException {
        try {
            if (str.charAt(0) != '/') {
                str = new StringBuffer().append("/").append(str).toString();
            }
            XPath xPath = XPath.get(str);
            monitor(xPath);
            return visitor(xPath, false).getResultEnumeration();
        } catch (XPathException e) {
            throw new ParseException("XPath problem", (Throwable) e);
        }
    }

    public String xpathSelectString(String str) throws ParseException {
        try {
            return visitor(str, true).getFirstResultString();
        } catch (XPathException e) {
            throw new ParseException("XPath problem", (Throwable) e);
        }
    }

    public Enumeration xpathSelectStrings(String str) throws ParseException {
        try {
            return visitor(str, true).getResultEnumeration();
        } catch (XPathException e) {
            throw new ParseException("XPath problem", (Throwable) e);
        }
    }
}
