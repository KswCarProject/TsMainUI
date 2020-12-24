package com.hp.hpl.sparta;

class CharCircBuffer {
    private final int[] buf_;
    private boolean enabled_ = true;
    private int next_ = 0;
    private int total_ = 0;

    CharCircBuffer(int i) {
        this.buf_ = new int[i];
    }

    private void addRaw(int i) {
        if (this.enabled_) {
            this.buf_[this.next_] = i;
            this.next_ = (this.next_ + 1) % this.buf_.length;
            this.total_++;
        }
    }

    /* access modifiers changed from: package-private */
    public void addChar(char c) {
        addRaw(c);
    }

    /* access modifiers changed from: package-private */
    public void addInt(int i) {
        addRaw(65536 + i);
    }

    /* access modifiers changed from: package-private */
    public void addString(String str) {
        for (char addChar : str.toCharArray()) {
            addChar(addChar);
        }
    }

    /* access modifiers changed from: package-private */
    public void disable() {
        this.enabled_ = false;
    }

    /* access modifiers changed from: package-private */
    public void enable() {
        this.enabled_ = true;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer((this.buf_.length * 11) / 10);
        for (int length = this.total_ < this.buf_.length ? this.buf_.length - this.total_ : 0; length < this.buf_.length; length++) {
            int i = this.buf_[(this.next_ + length) % this.buf_.length];
            if (i < 65536) {
                stringBuffer.append((char) i);
            } else {
                stringBuffer.append(Integer.toString(i - 65536));
            }
        }
        return stringBuffer.toString();
    }
}
