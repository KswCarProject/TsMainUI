package com.T.T.Tr;

/* compiled from: Proguard */
public class Th {

    /* renamed from: T  reason: collision with root package name */
    private final T[] f181T;
    private int T9;
    private final int Tn;
    private final String[] Tr;
    private final char[][] Ty;

    public Th() {
        this(256);
        T("$ref", 0, 4, "$ref".hashCode());
        T("@type", 0, 4, "$type".hashCode());
    }

    public Th(int tableSize) {
        this.T9 = 0;
        this.Tn = tableSize - 1;
        this.f181T = new T[tableSize];
        this.Tr = new String[tableSize];
        this.Ty = new char[tableSize][];
    }

    public String T(char[] buffer, int offset, int len, int hash) {
        int bucket = hash & this.Tn;
        String sym = this.Tr[bucket];
        boolean match = true;
        if (sym != null) {
            if (sym.length() == len) {
                char[] characters = this.Ty[bucket];
                int i = 0;
                while (true) {
                    if (i >= len) {
                        break;
                    } else if (buffer[offset + i] != characters[i]) {
                        match = false;
                        break;
                    } else {
                        i++;
                    }
                }
                if (match) {
                    return sym;
                }
            } else {
                match = false;
            }
        }
        int entryIndex = 0;
        for (T entry = this.f181T[bucket]; entry != null; entry = entry.T9) {
            char[] characters2 = entry.Ty;
            if (len == characters2.length && hash == entry.Tr) {
                boolean eq = true;
                int i2 = 0;
                while (true) {
                    if (i2 >= len) {
                        break;
                    } else if (buffer[offset + i2] != characters2[i2]) {
                        eq = false;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (eq) {
                    return entry.f182T;
                }
                entryIndex++;
            }
        }
        if (entryIndex >= 8) {
            return new String(buffer, offset, len);
        }
        if (this.T9 >= 1024) {
            return new String(buffer, offset, len);
        }
        T entry2 = new T(buffer, offset, len, hash, this.f181T[bucket]);
        this.f181T[bucket] = entry2;
        if (match) {
            this.Tr[bucket] = entry2.f182T;
            this.Ty[bucket] = entry2.Ty;
        }
        this.T9++;
        return entry2.f182T;
    }

    public String T(String buffer, int offset, int len, int hash) {
        int bucket = hash & this.Tn;
        String sym = this.Tr[bucket];
        boolean match = true;
        if (sym != null) {
            if (sym.length() == len) {
                char[] characters = this.Ty[bucket];
                int i = 0;
                while (true) {
                    if (i >= len) {
                        break;
                    } else if (buffer.charAt(offset + i) != characters[i]) {
                        match = false;
                        break;
                    } else {
                        i++;
                    }
                }
                if (match) {
                    return sym;
                }
            } else {
                match = false;
            }
        }
        int entryIndex = 0;
        for (T entry = this.f181T[bucket]; entry != null; entry = entry.T9) {
            char[] characters2 = entry.Ty;
            if (len == characters2.length && hash == entry.Tr) {
                boolean eq = true;
                int i2 = 0;
                while (true) {
                    if (i2 >= len) {
                        break;
                    } else if (buffer.charAt(offset + i2) != characters2[i2]) {
                        eq = false;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (eq) {
                    return entry.f182T;
                }
                entryIndex++;
            }
        }
        if (entryIndex >= 8) {
            return buffer.substring(offset, offset + len);
        } else if (this.T9 >= 1024) {
            return buffer.substring(offset, offset + len);
        } else {
            T entry2 = new T(buffer, offset, len, hash, this.f181T[bucket]);
            this.f181T[bucket] = entry2;
            if (match) {
                this.Tr[bucket] = entry2.f182T;
                this.Ty[bucket] = entry2.Ty;
            }
            this.T9++;
            return entry2.f182T;
        }
    }

    /* compiled from: Proguard */
    protected static final class T {

        /* renamed from: T  reason: collision with root package name */
        public final String f182T;
        public T T9;
        public final byte[] Tn;
        public final int Tr;
        public final char[] Ty;

        public T(char[] ch, int offset, int length, int hash, T next) {
            this.Ty = new char[length];
            System.arraycopy(ch, offset, this.Ty, 0, length);
            this.f182T = new String(this.Ty).intern();
            this.T9 = next;
            this.Tr = hash;
            this.Tn = null;
        }

        public T(String text, int offset, int length, int hash, T next) {
            this.f182T = text.substring(offset, offset + length).intern();
            this.Ty = this.f182T.toCharArray();
            this.T9 = next;
            this.Tr = hash;
            this.Tn = null;
        }
    }
}
