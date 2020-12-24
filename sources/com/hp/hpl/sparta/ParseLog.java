package com.hp.hpl.sparta;

public interface ParseLog {
    void error(String str, String str2, int i);

    void note(String str, String str2, int i);

    void warning(String str, String str2, int i);
}
