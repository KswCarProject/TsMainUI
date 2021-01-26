package com.Ty.T.Tr.T.T;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: Proguard */
public class Tn<E> extends AbstractQueue<E> implements T<E>, Serializable {

    /* renamed from: T  reason: collision with root package name */
    transient Ty<E> f312T;
    private final int T9;
    private final Condition TZ;
    private final Condition Tk;
    private transient int Tn;
    transient Ty<E> Tr;
    final ReentrantLock Ty;

    /* compiled from: Proguard */
    static final class Ty<E> {

        /* renamed from: T  reason: collision with root package name */
        E f314T;
        Ty<E> Tr;
        Ty<E> Ty;

        Ty(E x) {
            this.f314T = x;
        }
    }

    public Tn() {
        this(Integer.MAX_VALUE);
    }

    public Tn(int capacity) {
        this.Ty = new ReentrantLock();
        this.Tk = this.Ty.newCondition();
        this.TZ = this.Ty.newCondition();
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.T9 = capacity;
    }

    private boolean Tr(Ty<E> node) {
        if (this.Tn >= this.T9) {
            return false;
        }
        Ty<E> f = this.f312T;
        node.Ty = f;
        this.f312T = node;
        if (this.Tr == null) {
            this.Tr = node;
        } else {
            f.Tr = node;
        }
        this.Tn++;
        this.Tk.signal();
        return true;
    }

    private boolean Ty(Ty<E> node) {
        if (this.Tn >= this.T9) {
            return false;
        }
        Ty<E> l = this.Tr;
        node.Tr = l;
        this.Tr = node;
        if (this.f312T == null) {
            this.f312T = node;
        } else {
            l.Ty = node;
        }
        this.Tn++;
        this.Tk.signal();
        return true;
    }

    private E Tk() {
        Ty<E> f = this.f312T;
        if (f == null) {
            return null;
        }
        Ty<E> n = f.Ty;
        E e = f.f314T;
        f.f314T = null;
        f.Ty = f;
        this.f312T = n;
        if (n == null) {
            this.Tr = null;
        } else {
            n.Tr = null;
        }
        this.Tn--;
        this.TZ.signal();
        return e;
    }

    private E TZ() {
        Ty<E> l = this.Tr;
        if (l == null) {
            return null;
        }
        Ty<E> p = l.Tr;
        E e = l.f314T;
        l.f314T = null;
        l.Tr = l;
        this.Tr = p;
        if (p == null) {
            this.f312T = null;
        } else {
            p.Ty = null;
        }
        this.Tn--;
        this.TZ.signal();
        return e;
    }

    /* access modifiers changed from: package-private */
    public void T(Ty<E> x) {
        Ty<E> p = x.Tr;
        Ty<E> n = x.Ty;
        if (p == null) {
            Tk();
        } else if (n == null) {
            TZ();
        } else {
            p.Ty = n;
            n.Tr = p;
            x.f314T = null;
            this.Tn--;
            this.TZ.signal();
        }
    }

    public void T(E e) {
        if (!Ty(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public boolean Tr(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        Ty<E> node = new Ty<>(e);
        ReentrantLock lock = this.Ty;
        lock.lock();
        try {
            return Tr(node);
        } finally {
            lock.unlock();
        }
    }

    public boolean Ty(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        Ty<E> node = new Ty<>(e);
        ReentrantLock lock = this.Ty;
        lock.lock();
        try {
            return Ty(node);
        } finally {
            lock.unlock();
        }
    }

    public void Tn(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        Ty<E> node = new Ty<>(e);
        ReentrantLock lock = this.Ty;
        lock.lock();
        while (!Ty(node)) {
            try {
                this.TZ.await();
            } finally {
                lock.unlock();
            }
        }
    }

    public boolean T(E e, long timeout, TimeUnit unit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        Ty<E> node = new Ty<>(e);
        long nanos = unit.toNanos(timeout);
        ReentrantLock lock = this.Ty;
        lock.lockInterruptibly();
        while (!Ty(node)) {
            try {
                if (nanos <= 0) {
                    return false;
                }
                nanos = this.TZ.awaitNanos(nanos);
            } finally {
                lock.unlock();
            }
        }
        lock.unlock();
        return true;
    }

    public E T() {
        E x = Tr();
        if (x != null) {
            return x;
        }
        throw new NoSuchElementException();
    }

    public E Tr() {
        ReentrantLock lock = this.Ty;
        lock.lock();
        try {
            return Tk();
        } finally {
            lock.unlock();
        }
    }

    public E Ty() throws InterruptedException {
        ReentrantLock lock = this.Ty;
        lock.lock();
        while (true) {
            try {
                E x = Tk();
                if (x != null) {
                    return x;
                }
                this.Tk.await();
            } finally {
                lock.unlock();
            }
        }
    }

    public E T(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        ReentrantLock lock = this.Ty;
        lock.lockInterruptibly();
        while (true) {
            try {
                E x = Tk();
                if (x != null) {
                    lock.unlock();
                    return x;
                } else if (nanos <= 0) {
                    return null;
                } else {
                    nanos = this.Tk.awaitNanos(nanos);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public E Tn() {
        E x = T9();
        if (x != null) {
            return x;
        }
        throw new NoSuchElementException();
    }

    public E T9() {
        ReentrantLock lock = this.Ty;
        lock.lock();
        try {
            return this.f312T == null ? null : this.f312T.f314T;
        } finally {
            lock.unlock();
        }
    }

    public boolean T9(Object o) {
        if (o == null) {
            return false;
        }
        ReentrantLock lock = this.Ty;
        lock.lock();
        try {
            for (Ty<E> p = this.f312T; p != null; p = p.Ty) {
                if (o.equals(p.f314T)) {
                    T(p);
                    return true;
                }
            }
            lock.unlock();
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean add(E e) {
        T(e);
        return true;
    }

    public boolean offer(E e) {
        return Ty(e);
    }

    public void put(E e) throws InterruptedException {
        Tn(e);
    }

    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        return T(e, timeout, unit);
    }

    public E remove() {
        return T();
    }

    public E poll() {
        return Tr();
    }

    public E take() throws InterruptedException {
        return Ty();
    }

    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return T(timeout, unit);
    }

    public E element() {
        return Tn();
    }

    public E peek() {
        return T9();
    }

    public int remainingCapacity() {
        ReentrantLock lock = this.Ty;
        lock.lock();
        try {
            return this.T9 - this.Tn;
        } finally {
            lock.unlock();
        }
    }

    public int drainTo(Collection<? super E> c) {
        return drainTo(c, Integer.MAX_VALUE);
    }

    public int drainTo(Collection<? super E> c, int maxElements) {
        if (c == null) {
            throw new NullPointerException();
        } else if (c == this) {
            throw new IllegalArgumentException();
        } else {
            ReentrantLock lock = this.Ty;
            lock.lock();
            try {
                int n = Math.min(maxElements, this.Tn);
                for (int i = 0; i < n; i++) {
                    c.add(this.f312T.f314T);
                    Tk();
                }
                return n;
            } finally {
                lock.unlock();
            }
        }
    }

    public boolean remove(Object o) {
        return T9(o);
    }

    public int size() {
        ReentrantLock lock = this.Ty;
        lock.lock();
        try {
            return this.Tn;
        } finally {
            lock.unlock();
        }
    }

    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        ReentrantLock lock = this.Ty;
        lock.lock();
        try {
            for (Ty<E> p = this.f312T; p != null; p = p.Ty) {
                if (o.equals(p.f314T)) {
                    return true;
                }
            }
            lock.unlock();
            return false;
        } finally {
            lock.unlock();
        }
    }

    public Object[] toArray() {
        ReentrantLock lock = this.Ty;
        lock.lock();
        try {
            Object[] a = new Object[this.Tn];
            Ty<E> p = this.f312T;
            int k = 0;
            while (p != null) {
                int k2 = k + 1;
                a[k] = p.f314T;
                p = p.Ty;
                k = k2;
            }
            return a;
        } finally {
            lock.unlock();
        }
    }

    public <T> T[] toArray(T[] a) {
        ReentrantLock lock = this.Ty;
        lock.lock();
        try {
            int length = a.length;
            T[] a2 = a;
            if (length < this.Tn) {
                a2 = (Object[]) ((Object[]) Array.newInstance(a.getClass().getComponentType(), this.Tn));
            }
            Ty<E> p = this.f312T;
            int k = 0;
            while (p != null) {
                a2[k] = p.f314T;
                p = p.Ty;
                k++;
            }
            if (a2.length > k) {
                a2[k] = null;
            }
            return a2;
        } finally {
            lock.unlock();
        }
    }

    public String toString() {
        String sb;
        ReentrantLock lock = this.Ty;
        lock.lock();
        try {
            Ty<E> p = this.f312T;
            if (p == null) {
                sb = "[]";
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append('[');
                while (true) {
                    E e = p.f314T;
                    if (e == this) {
                        e = "(this Collection)";
                    }
                    sb2.append(e);
                    p = p.Ty;
                    if (p == null) {
                        break;
                    }
                    sb2.append(',').append(' ');
                }
                sb = sb2.append(']').toString();
                lock.unlock();
            }
            return sb;
        } finally {
            lock.unlock();
        }
    }

    public void clear() {
        ReentrantLock lock = this.Ty;
        lock.lock();
        try {
            Ty<E> f = this.f312T;
            while (f != null) {
                f.f314T = null;
                Ty<E> n = f.Ty;
                f.Tr = null;
                f.Ty = null;
                f = n;
            }
            this.Tr = null;
            this.f312T = null;
            this.Tn = 0;
            this.TZ.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Iterator<E> iterator() {
        return new Tr();
    }

    /* compiled from: Proguard */
    private abstract class T implements Iterator<E> {

        /* renamed from: T  reason: collision with root package name */
        Ty<E> f313T;
        private Ty<E> Tn;
        E Tr;

        /* access modifiers changed from: package-private */
        public abstract Ty<E> T();

        /* access modifiers changed from: package-private */
        public abstract Ty<E> T(Ty<E> ty);

        T() {
            ReentrantLock lock = Tn.this.Ty;
            lock.lock();
            try {
                this.f313T = T();
                this.Tr = this.f313T == null ? null : this.f313T.f314T;
            } finally {
                lock.unlock();
            }
        }

        private Ty<E> Tr(Ty<E> n) {
            while (true) {
                Ty<E> s = T(n);
                if (s == null) {
                    return null;
                }
                if (s.f314T != null) {
                    return s;
                }
                if (s == n) {
                    return T();
                }
                n = s;
            }
        }

        /* access modifiers changed from: package-private */
        public void Tr() {
            ReentrantLock lock = Tn.this.Ty;
            lock.lock();
            try {
                this.f313T = Tr(this.f313T);
                this.Tr = this.f313T == null ? null : this.f313T.f314T;
            } finally {
                lock.unlock();
            }
        }

        public boolean hasNext() {
            return this.f313T != null;
        }

        public E next() {
            if (this.f313T == null) {
                throw new NoSuchElementException();
            }
            this.Tn = this.f313T;
            E x = this.Tr;
            Tr();
            return x;
        }

        public void remove() {
            Ty<E> n = this.Tn;
            if (n == null) {
                throw new IllegalStateException();
            }
            this.Tn = null;
            ReentrantLock lock = Tn.this.Ty;
            lock.lock();
            try {
                if (n.f314T != null) {
                    Tn.this.T(n);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    /* compiled from: Proguard */
    private class Tr extends Tn<E>.T {
        private Tr() {
            super();
        }

        /* access modifiers changed from: package-private */
        public Ty<E> T() {
            return Tn.this.f312T;
        }

        /* access modifiers changed from: package-private */
        public Ty<E> T(Ty<E> n) {
            return n.Ty;
        }
    }
}
