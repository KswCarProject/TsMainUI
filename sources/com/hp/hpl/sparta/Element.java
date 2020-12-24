package com.hp.hpl.sparta;

import com.hp.hpl.sparta.xpath.Step;
import com.hp.hpl.sparta.xpath.XPath;
import com.hp.hpl.sparta.xpath.XPathException;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Element extends Node {
    private static final boolean DEBUG = false;
    private Vector attributeNames_ = null;
    private Hashtable attributes_ = null;
    private Node firstChild_ = null;
    private Node lastChild_ = null;
    private String tagName_ = null;

    Element() {
    }

    public Element(String str) {
        this.tagName_ = Sparta.intern(str);
    }

    private void checkInvariant() {
    }

    private boolean removeChildNoChecking(Node node) {
        int i = 0;
        for (Node node2 = this.firstChild_; node2 != null; node2 = node2.getNextSibling()) {
            if (node2.equals(node)) {
                if (this.firstChild_ == node2) {
                    this.firstChild_ = node2.getNextSibling();
                }
                if (this.lastChild_ == node2) {
                    this.lastChild_ = node2.getPreviousSibling();
                }
                node2.removeFromLinkedList();
                node2.setParentNode((Element) null);
                node2.setOwnerDocument((Document) null);
                return true;
            }
            i++;
        }
        return false;
    }

    private void replaceChild_(Node node, Node node2) throws DOMException {
        int i = 0;
        for (Node node3 = this.firstChild_; node3 != null; node3 = node3.getNextSibling()) {
            if (node3 == node2) {
                if (this.firstChild_ == node2) {
                    this.firstChild_ = node;
                }
                if (this.lastChild_ == node2) {
                    this.lastChild_ = node;
                }
                node2.replaceInLinkedList(node);
                node.setParentNode(this);
                node2.setParentNode((Element) null);
                return;
            }
            i++;
        }
        throw new DOMException(8, new StringBuffer().append("Cannot find ").append(node2).append(" in ").append(this).toString());
    }

    private XPathVisitor visitor(String str, boolean z) throws XPathException {
        XPath xPath = XPath.get(str);
        if (xPath.isStringValue() == z) {
            return new XPathVisitor(this, xPath);
        }
        throw new XPathException(xPath, new StringBuffer().append("\"").append(xPath).append("\" evaluates to ").append(z ? "evaluates to element not string" : "evaluates to string not element").toString());
    }

    public void appendChild(Node node) {
        appendChildNoChecking(!canHaveAsDescendent(node) ? (Element) node.clone() : node);
        notifyObservers();
    }

    /* access modifiers changed from: package-private */
    public void appendChildNoChecking(Node node) {
        Element parentNode = node.getParentNode();
        if (parentNode != null) {
            parentNode.removeChildNoChecking(node);
        }
        node.insertAtEndOfLinkedList(this.lastChild_);
        if (this.firstChild_ == null) {
            this.firstChild_ = node;
        }
        node.setParentNode(this);
        this.lastChild_ = node;
        node.setOwnerDocument(getOwnerDocument());
    }

    /* access modifiers changed from: package-private */
    public boolean canHaveAsDescendent(Node node) {
        if (node == this) {
            return false;
        }
        Element parentNode = getParentNode();
        if (parentNode == null) {
            return true;
        }
        return parentNode.canHaveAsDescendent(node);
    }

    public Object clone() {
        return cloneElement(true);
    }

    public Element cloneElement(boolean z) {
        Element element = new Element(this.tagName_);
        if (this.attributeNames_ != null) {
            Enumeration elements = this.attributeNames_.elements();
            while (elements.hasMoreElements()) {
                String str = (String) elements.nextElement();
                element.setAttribute(str, (String) this.attributes_.get(str));
            }
        }
        if (z) {
            for (Node node = this.firstChild_; node != null; node = node.getNextSibling()) {
                element.appendChild((Node) node.clone());
            }
        }
        return element;
    }

    public Element cloneShallow() {
        return cloneElement(false);
    }

    /* access modifiers changed from: protected */
    public int computeHashCode() {
        int i;
        int hashCode = this.tagName_.hashCode();
        if (this.attributes_ != null) {
            Enumeration keys = this.attributes_.keys();
            while (true) {
                i = hashCode;
                if (!keys.hasMoreElements()) {
                    break;
                }
                String str = (String) keys.nextElement();
                int hashCode2 = (i * 31) + str.hashCode();
                hashCode = ((String) this.attributes_.get(str)).hashCode() + (hashCode2 * 31);
            }
        } else {
            i = hashCode;
        }
        for (Node node = this.firstChild_; node != null; node = node.getNextSibling()) {
            i = (i * 31) + node.hashCode();
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Element)) {
            return false;
        }
        Element element = (Element) obj;
        if (!this.tagName_.equals(element.tagName_)) {
            return false;
        }
        if ((this.attributes_ == null ? 0 : this.attributes_.size()) != (element.attributes_ == null ? 0 : element.attributes_.size())) {
            return false;
        }
        if (this.attributes_ != null) {
            Enumeration keys = this.attributes_.keys();
            while (keys.hasMoreElements()) {
                String str = (String) keys.nextElement();
                if (!((String) this.attributes_.get(str)).equals((String) element.attributes_.get(str))) {
                    return false;
                }
            }
        }
        Node node = this.firstChild_;
        Node node2 = element.firstChild_;
        while (node != null) {
            if (!node.equals(node2)) {
                return false;
            }
            node = node.getNextSibling();
            node2 = node2.getNextSibling();
        }
        return true;
    }

    public String getAttribute(String str) {
        if (this.attributes_ == null) {
            return null;
        }
        return (String) this.attributes_.get(str);
    }

    public Enumeration getAttributeNames() {
        return this.attributeNames_ == null ? Document.EMPTY : this.attributeNames_.elements();
    }

    public Node getFirstChild() {
        return this.firstChild_;
    }

    public Node getLastChild() {
        return this.lastChild_;
    }

    public String getTagName() {
        return this.tagName_;
    }

    public void removeAttribute(String str) {
        if (this.attributes_ != null) {
            this.attributes_.remove(str);
            this.attributeNames_.removeElement(str);
            notifyObservers();
        }
    }

    public void removeChild(Node node) throws DOMException {
        if (!removeChildNoChecking(node)) {
            throw new DOMException(8, new StringBuffer().append("Cannot find ").append(node).append(" in ").append(this).toString());
        }
        notifyObservers();
    }

    public void replaceChild(Element element, Node node) throws DOMException {
        replaceChild_(element, node);
        notifyObservers();
    }

    public void replaceChild(Text text, Node node) throws DOMException {
        replaceChild_(text, node);
        notifyObservers();
    }

    public void setAttribute(String str, String str2) {
        if (this.attributes_ == null) {
            this.attributes_ = new Hashtable();
            this.attributeNames_ = new Vector();
        }
        if (this.attributes_.get(str) == null) {
            this.attributeNames_.addElement(str);
        }
        this.attributes_.put(str, str2);
        notifyObservers();
    }

    public void setTagName(String str) {
        this.tagName_ = Sparta.intern(str);
        notifyObservers();
    }

    /* access modifiers changed from: package-private */
    public void toString(Writer writer) throws IOException {
        for (Node node = this.firstChild_; node != null; node = node.getNextSibling()) {
            node.toString(writer);
        }
    }

    public void toXml(Writer writer) throws IOException {
        writer.write(new StringBuffer().append("<").append(this.tagName_).toString());
        if (this.attributeNames_ != null) {
            Enumeration elements = this.attributeNames_.elements();
            while (elements.hasMoreElements()) {
                String str = (String) elements.nextElement();
                writer.write(new StringBuffer().append(" ").append(str).append("=\"").toString());
                Node.htmlEncode(writer, (String) this.attributes_.get(str));
                writer.write("\"");
            }
        }
        if (this.firstChild_ == null) {
            writer.write("/>");
            return;
        }
        writer.write(">");
        for (Node node = this.firstChild_; node != null; node = node.getNextSibling()) {
            node.toXml(writer);
        }
        writer.write(new StringBuffer().append("</").append(this.tagName_).append(">").toString());
    }

    public boolean xpathEnsure(String str) throws ParseException {
        Element xpathSelectElement;
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
            Step[] stepArr = new Step[(i - 1)];
            Enumeration steps2 = xPath.getSteps();
            for (int i2 = 0; i2 < stepArr.length; i2++) {
                stepArr[i2] = (Step) steps2.nextElement();
            }
            Step step = (Step) steps2.nextElement();
            if (stepArr.length == 0) {
                xpathSelectElement = this;
            } else {
                String xPath2 = XPath.get(xPath.isAbsolute(), stepArr).toString();
                xpathEnsure(xPath2.toString());
                xpathSelectElement = xpathSelectElement(xPath2);
            }
            xpathSelectElement.appendChildNoChecking(makeMatching(xpathSelectElement, step, str));
            return true;
        } catch (XPathException e) {
            throw new ParseException(str, (Throwable) e);
        }
    }

    public Element xpathSelectElement(String str) throws ParseException {
        try {
            return visitor(str, false).getFirstResultElement();
        } catch (XPathException e) {
            throw new ParseException("XPath problem", (Throwable) e);
        }
    }

    public Enumeration xpathSelectElements(String str) throws ParseException {
        try {
            return visitor(str, false).getResultEnumeration();
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
