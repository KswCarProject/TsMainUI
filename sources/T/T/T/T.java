package T.T.T;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: Proguard */
public class T {
    private static T Ty;

    /* renamed from: T  reason: collision with root package name */
    private Map<Character, Character> f127T = new HashMap();
    private Map<Character, Character> Tr = new HashMap();

    public static T T() throws IOException {
        if (Ty == null) {
            Ty = new T();
        }
        return Ty;
    }

    public String T(String s) {
        char[] cs = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            cs[i] = T(s.charAt(i)).charValue();
        }
        return new String(cs);
    }

    public Character T(char c) {
        if (this.Tr.get(Character.valueOf(c)) == null) {
            return Character.valueOf(c);
        }
        return this.Tr.get(Character.valueOf(c));
    }

    private List<Character> Tr() throws IOException {
        List<Character> cs = T("/cfg/ts.tab", "UTF-8");
        if (cs.size() % 2 == 0) {
            return cs;
        }
        throw new RuntimeException("The conversion table may be damaged or not exists");
    }

    private T() throws IOException {
        List<Character> cs = Tr();
        for (int i = 0; i < cs.size(); i += 2) {
            this.f127T.put(cs.get(i), cs.get(i + 1));
            this.Tr.put(cs.get(i + 1), cs.get(i));
        }
    }

    private List<Character> T(String file, String charset) throws IOException {
        List<Character> content = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file), charset));
        while (true) {
            int c = in.read();
            if (c == -1) {
                in.close();
                return content;
            }
            content.add(Character.valueOf((char) c));
        }
    }
}
