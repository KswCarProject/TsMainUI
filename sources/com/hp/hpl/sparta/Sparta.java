package com.hp.hpl.sparta;

import java.util.Hashtable;

public class Sparta {
    private static CacheFactory cacheFactory_ = new CacheFactory() {
        public Cache create() {
            return new HashtableCache((AnonymousClass1) null);
        }
    };
    private static Internment internment_ = new Internment() {
        private final Hashtable strings_ = new Hashtable();

        public String intern(String str) {
            String str2 = (String) this.strings_.get(str);
            if (str2 != null) {
                return str2;
            }
            this.strings_.put(str, str);
            return str;
        }
    };

    public interface Cache {
        Object get(Object obj);

        Object put(Object obj, Object obj2);

        int size();
    }

    public interface CacheFactory {
        Cache create();
    }

    private static class HashtableCache extends Hashtable implements Cache {
        private HashtableCache() {
        }

        HashtableCache(AnonymousClass1 r1) {
            this();
        }
    }

    public interface Internment {
        String intern(String str);
    }

    public static String intern(String str) {
        return internment_.intern(str);
    }

    static Cache newCache() {
        return cacheFactory_.create();
    }

    public static void setCacheFactory(CacheFactory cacheFactory) {
        cacheFactory_ = cacheFactory;
    }

    public static void setInternment(Internment internment) {
        internment_ = internment;
    }
}
