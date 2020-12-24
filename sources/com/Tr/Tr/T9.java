package com.Tr.Tr;

import com.Tr.Tr.Tk.T;
import com.Tr.Tr.Tn.TB;
import com.Tr.Tr.Tn.TE;
import com.Tr.Tr.Tn.Th;
import com.Tr.Tr.Tn.Tk;
import com.Tr.Tr.Tn.Tn;
import com.Tr.Tr.Tn.Tq;
import com.Tr.Tr.Tn.Tu;
import com.Tr.Tr.Tn.Tv;
import com.Tr.Tr.Tr.Tr;
import java.util.Map;

/* compiled from: Proguard */
public final class T9 implements TZ {
    public Tr T(String contents, T format, int width, int height, Map<Ty, ?> hints) throws TE {
        TZ writer;
        switch (format) {
            case EAN_8:
                writer = new Th();
                break;
            case UPC_E:
                writer = new Tu();
                break;
            case EAN_13:
                writer = new Tv();
                break;
            case UPC_A:
                writer = new TB();
                break;
            case QR_CODE:
                writer = new T();
                break;
            case CODE_39:
                writer = new Tk();
                break;
            case CODE_93:
                writer = new TE();
                break;
            case CODE_128:
                writer = new Tn();
                break;
            case ITF:
                writer = new Tq();
                break;
            case PDF_417:
                writer = new com.Tr.Tr.T9.T();
                break;
            case CODABAR:
                writer = new com.Tr.Tr.Tn.Tr();
                break;
            case DATA_MATRIX:
                writer = new com.Tr.Tr.Ty.T();
                break;
            case AZTEC:
                writer = new com.Tr.Tr.T.T();
                break;
            default:
                throw new IllegalArgumentException("No encoder available for format " + format);
        }
        return writer.T(contents, format, width, height, hints);
    }
}
