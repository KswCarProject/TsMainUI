package com.hp.hpl.sparta;

import java.util.Enumeration;
import java.util.NoSuchElementException;

class EmptyEnumeration implements Enumeration {
    EmptyEnumeration() {
    }

    public boolean hasMoreElements() {
        return false;
    }

    public Object nextElement() {
        throw new NoSuchElementException();
    }
}
