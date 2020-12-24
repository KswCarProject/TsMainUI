package com.hp.hpl.sparta;

class DefaultLog implements ParseLog {
    DefaultLog() {
    }

    public void error(String str, String str2, int i) {
        System.err.println(new StringBuffer().append(str2).append("(").append(i).append("): ").append(str).append(" (ERROR)").toString());
    }

    public void note(String str, String str2, int i) {
        System.out.println(new StringBuffer().append(str2).append("(").append(i).append("): ").append(str).append(" (NOTE)").toString());
    }

    public void warning(String str, String str2, int i) {
        System.out.println(new StringBuffer().append(str2).append("(").append(i).append("): ").append(str).append(" (WARNING)").toString());
    }
}
