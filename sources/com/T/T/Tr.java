package com.T.T;

import com.T.T.Tn.TZ;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: Proguard */
public class Tr extends T implements Ty, Serializable, Cloneable, List<Object>, RandomAccess {
    protected transient Object T9;
    private final List<Object> TZ;
    protected transient Type Tk;

    public Tr() {
        this.TZ = new ArrayList(10);
    }

    public Tr(List<Object> list) {
        this.TZ = list;
    }

    public Object Tr() {
        return this.T9;
    }

    public void Tr(Object relatedArray) {
        this.T9 = relatedArray;
    }

    public Type Ty() {
        return this.Tk;
    }

    public void T(Type componentType) {
        this.Tk = componentType;
    }

    public int size() {
        return this.TZ.size();
    }

    public boolean isEmpty() {
        return this.TZ.isEmpty();
    }

    public boolean contains(Object o) {
        return this.TZ.contains(o);
    }

    public Iterator<Object> iterator() {
        return this.TZ.iterator();
    }

    public Object[] toArray() {
        return this.TZ.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return this.TZ.toArray(a);
    }

    public boolean add(Object e) {
        return this.TZ.add(e);
    }

    public boolean remove(Object o) {
        return this.TZ.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return this.TZ.containsAll(c);
    }

    public boolean addAll(Collection<? extends Object> c) {
        return this.TZ.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends Object> c) {
        return this.TZ.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return this.TZ.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return this.TZ.retainAll(c);
    }

    public void clear() {
        this.TZ.clear();
    }

    public Object set(int index, Object element) {
        return this.TZ.set(index, element);
    }

    public void add(int index, Object element) {
        this.TZ.add(index, element);
    }

    public Object remove(int index) {
        return this.TZ.remove(index);
    }

    public int indexOf(Object o) {
        return this.TZ.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return this.TZ.lastIndexOf(o);
    }

    public ListIterator<Object> listIterator() {
        return this.TZ.listIterator();
    }

    public ListIterator<Object> listIterator(int index) {
        return this.TZ.listIterator(index);
    }

    public List<Object> subList(int fromIndex, int toIndex) {
        return this.TZ.subList(fromIndex, toIndex);
    }

    public Object get(int index) {
        return this.TZ.get(index);
    }

    public Integer T(int index) {
        return TZ.Te(get(index));
    }

    public Long Tr(int index) {
        return TZ.T6(get(index));
    }

    public Object clone() {
        return new Tr(new ArrayList(this.TZ));
    }

    public boolean equals(Object obj) {
        return this.TZ.equals(obj);
    }

    public int hashCode() {
        return this.TZ.hashCode();
    }
}
