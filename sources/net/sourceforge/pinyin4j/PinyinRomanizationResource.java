package net.sourceforge.pinyin4j;

import com.hp.hpl.sparta.Document;
import com.hp.hpl.sparta.ParseException;
import com.hp.hpl.sparta.Parser;
import com.txznet.sdk.TXZResourceManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

class PinyinRomanizationResource {
    private Document pinyinMappingDoc;

    /* renamed from: net.sourceforge.pinyin4j.PinyinRomanizationResource$1  reason: invalid class name */
    static class AnonymousClass1 {
    }

    private static class PinyinRomanizationSystemResourceHolder {
        static final PinyinRomanizationResource theInstance = new PinyinRomanizationResource((AnonymousClass1) null);

        private PinyinRomanizationSystemResourceHolder() {
        }
    }

    private PinyinRomanizationResource() {
        initializeResource();
    }

    PinyinRomanizationResource(AnonymousClass1 r1) {
        this();
    }

    static PinyinRomanizationResource getInstance() {
        return PinyinRomanizationSystemResourceHolder.theInstance;
    }

    private void initializeResource() {
        try {
            setPinyinMappingDoc(Parser.parse(TXZResourceManager.STYLE_DEFAULT, (InputStream) ResourceHelper.getResourceInputStream("/pinyindb/pinyin_mapping.xml")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (ParseException e3) {
            e3.printStackTrace();
        }
    }

    private void setPinyinMappingDoc(Document document) {
        this.pinyinMappingDoc = document;
    }

    /* access modifiers changed from: package-private */
    public Document getPinyinMappingDoc() {
        return this.pinyinMappingDoc;
    }
}
