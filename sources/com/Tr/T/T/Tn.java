package com.Tr.T.T;

import java.io.IOException;

/* compiled from: Proguard */
public class Tn extends IOException {
    public Tn(String str) {
        super(str);
    }

    static Tn T() {
        return new Tn("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }

    static Tn Tr() {
        return new Tn("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static Tn Ty() {
        return new Tn("CodedInputStream encountered a malformed varint.");
    }

    static Tn Tn() {
        return new Tn("Protocol message contained an invalid tag (zero).");
    }

    static Tn T9() {
        return new Tn("Protocol message end-group tag did not match expected tag.");
    }

    static Tn Tk() {
        return new Tn("Protocol message tag had invalid wire type.");
    }

    static Tn TZ() {
        return new Tn("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }
}
