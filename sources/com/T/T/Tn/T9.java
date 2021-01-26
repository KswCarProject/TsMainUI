package com.T.T.Tn;

/* compiled from: Proguard */
public class T9<K, V> {

    /* renamed from: T  reason: collision with root package name */
    private final T<K, V>[] f133T;
    private final int Tr;

    public T9() {
        this(1024);
    }

    public T9(int tableSize) {
        this.Tr = tableSize - 1;
        this.f133T = new T[tableSize];
    }

    public final V T(K key) {
        for (T<K, V> entry = this.f133T[System.identityHashCode(key) & this.Tr]; entry != null; entry = entry.Tn) {
            if (key == entry.Tr) {
                return entry.Ty;
            }
        }
        return null;
    }

    public boolean T(K key, V value) {
        int hash = System.identityHashCode(key);
        int bucket = hash & this.Tr;
        for (T<K, V> entry = this.f133T[bucket]; entry != null; entry = entry.Tn) {
            if (key == entry.Tr) {
                entry.Ty = value;
                return true;
            }
        }
        this.f133T[bucket] = new T<>(key, value, hash, this.f133T[bucket]);
        return false;
    }

    /* compiled from: Proguard */
    protected static final class T<K, V> {

        /* renamed from: T  reason: collision with root package name */
        public final int f134T;
        public final T<K, V> Tn;
        public final K Tr;
        public V Ty;

        public T(K key, V value, int hash, T<K, V> next) {
            this.Tr = key;
            this.Ty = value;
            this.Tn = next;
            this.f134T = hash;
        }
    }
}
