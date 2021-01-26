package com.hp.hpl.sparta.xpath;

import android.support.v4.widget.ExploreByTouchHelper;
import com.txznet.sdk.TXZResourceManager;
import java.io.IOException;
import java.io.Reader;

public class SimpleStreamTokenizer {
    private static final int QUOTE = -6;
    public static final int TT_EOF = -1;
    public static final int TT_NUMBER = -2;
    public static final int TT_WORD = -3;
    private static final int WHITESPACE = -5;
    private final StringBuffer buf_ = new StringBuffer();
    private final int[] charType_ = new int[256];
    private char inQuote_ = 0;
    private int nextType_;
    public int nval = ExploreByTouchHelper.INVALID_ID;
    private boolean pushedBack_ = false;
    private final Reader reader_;
    public String sval = TXZResourceManager.STYLE_DEFAULT;
    public int ttype = ExploreByTouchHelper.INVALID_ID;

    public SimpleStreamTokenizer(Reader reader) throws IOException {
        this.reader_ = reader;
        for (int i = 0; i < this.charType_.length; i = (char) (i + 1)) {
            if ((65 <= i && i <= 90) || ((97 <= i && i <= 122) || i == 45)) {
                this.charType_[i] = -3;
            } else if (48 <= i && i <= 57) {
                this.charType_[i] = -2;
            } else if (i < 0 || i > 32) {
                this.charType_[i] = i;
            } else {
                this.charType_[i] = WHITESPACE;
            }
        }
        nextToken();
    }

    public int nextToken() throws IOException {
        int read;
        int i;
        boolean z;
        boolean z2;
        if (this.pushedBack_) {
            this.pushedBack_ = false;
            return this.ttype;
        }
        this.ttype = this.nextType_;
        do {
            boolean z3 = false;
            do {
                read = this.reader_.read();
                if (read != -1) {
                    i = this.charType_[read];
                } else if (this.inQuote_ != 0) {
                    throw new IOException("Unterminated quote");
                } else {
                    i = -1;
                }
                z = this.inQuote_ == 0 && i == WHITESPACE;
                if (z3 || z) {
                    z3 = true;
                    continue;
                } else {
                    z3 = false;
                    continue;
                }
            } while (z);
            if (i == 39 || i == 34) {
                if (this.inQuote_ == 0) {
                    this.inQuote_ = (char) i;
                } else if (this.inQuote_ == i) {
                    this.inQuote_ = 0;
                }
            }
            if (this.inQuote_ != 0) {
                i = this.inQuote_;
            }
            z2 = z3 || !((this.ttype < -1 || this.ttype == 39 || this.ttype == 34) && this.ttype == i);
            if (z2) {
                switch (this.ttype) {
                    case -3:
                        this.sval = this.buf_.toString();
                        this.buf_.setLength(0);
                        break;
                    case -2:
                        this.nval = Integer.parseInt(this.buf_.toString());
                        this.buf_.setLength(0);
                        break;
                    case 34:
                    case 39:
                        this.sval = this.buf_.toString().substring(1, this.buf_.length() - 1);
                        this.buf_.setLength(0);
                        break;
                }
                if (i != WHITESPACE) {
                    this.nextType_ = i == QUOTE ? read : i;
                }
            }
            switch (i) {
                case -3:
                case -2:
                case 34:
                case 39:
                    this.buf_.append((char) read);
                    continue;
            }
        } while (!z2);
        return this.ttype;
    }

    public void ordinaryChar(char c) {
        this.charType_[c] = c;
    }

    public void pushBack() {
        this.pushedBack_ = true;
    }

    public String toString() {
        switch (this.ttype) {
            case -3:
            case 34:
                return new StringBuffer().append("\"").append(this.sval).append("\"").toString();
            case -2:
                return Integer.toString(this.nval);
            case -1:
                return "(EOF)";
            case 39:
                return new StringBuffer().append("'").append(this.sval).append("'").toString();
            default:
                return new StringBuffer().append("'").append((char) this.ttype).append("'").toString();
        }
    }

    public void wordChars(char c, char c2) {
        while (c <= c2) {
            this.charType_[c] = -3;
            c = (char) (c + 1);
        }
    }
}
