package com.ts.factoryset;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.ts.MainUI.R;
import com.ts.factoryset.FsInputDlg;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.FtSet;
import net.easyconn.platform.wrc.core.WrcManager;

public class FsKeyActivity extends FsBaseActivity {
    public static final String TAG = "FsKeyActivity";
    private static final int mKeyAdHeader = 2034987860;
    private static final int mKeyAdSize = 872;
    private static final int mKeyEcHeader = 1665495892;
    private static final int mKeyEcSize = 48;
    private static final int mKeyIrHeader = 1917416276;
    private static final int mKeyIrSize = 520;
    private static final int mKeySwHeader = 2001957716;
    private static final int mKeySwSize = 76;
    private static final String mStrKeyAd = "TsKey.bin";
    private static final String mStrKeyEc = "TsEc.bin";
    private static final String mStrKeyIr = "TsIr.bin";
    private static final String mStrKeySw = "TsSw.bin";
    private ParamButton mBtnKey;
    private FsInputDlg mDlg;
    private int[] mKeyVal = new int[512];
    private FsInputDlg.onInputOK onOK = new FsInputDlg.onInputOK() {
        public void onOK(String strVal) {
            Log.e(FsKeyActivity.TAG, "Input val = " + strVal);
            if (strVal.equals("5525")) {
                FsKeyActivity.this.importKeyData();
            }
        }
    };

    public class FileInfo {
        public byte[] data;
        public int len;

        public FileInfo() {
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fs_key);
        topBtnInit(R.string.str_fsmain_key);
        this.mBtnKey = (ParamButton) findViewById(R.id.fskey_import);
        this.mBtnKey.setStateDrawable(R.drawable.factory_tab_up, R.drawable.factory_tab_dn, R.drawable.factory_tab_dn);
        this.mBtnKey.setOnClickListener(this);
        this.mDlg = new FsInputDlg();
        byte[] data = new byte[4];
        data[0] = -1;
        data[1] = -1;
        data[2] = -1;
        int byteArrayToInt = byteArrayToInt(data, 0);
    }

    /* access modifiers changed from: private */
    public void importKeyData() {
        if (getKeyFileData(mStrKeyEc, mKeyEcHeader, 48)) {
            FtSet.SetEcdAd(this.mKeyVal);
            Toast.makeText(this, R.string.fs_key_ec_success, 0).show();
        }
        if (getKeyFileData(mStrKeyIr, mKeyIrHeader, 520)) {
            FtSet.SetKeyIr(this.mKeyVal);
            Toast.makeText(this, R.string.fs_key_ir_success, 0).show();
        }
        if (getKeyFileData(mStrKeySw, mKeySwHeader, 76)) {
            FtSet.SetKeySw(this.mKeyVal);
            Toast.makeText(this, R.string.fs_key_sw_success, 0).show();
        }
        if (getKeyFileData(mStrKeyAd, mKeyAdHeader, mKeyAdSize)) {
            FtSet.SetKeyAd(this.mKeyVal);
            Toast.makeText(this, R.string.fs_key_ad_success, 0).show();
        }
    }

    public void onClick(View v) {
        importKeyData();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0044 A[SYNTHETIC, Splitter:B:20:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0053 A[SYNTHETIC, Splitter:B:28:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005f A[SYNTHETIC, Splitter:B:34:0x005f] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:25:0x004e=Splitter:B:25:0x004e, B:17:0x003f=Splitter:B:17:0x003f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ts.factoryset.FsKeyActivity.FileInfo getFileData(java.lang.String r9) {
        /*
            r8 = this;
            com.ts.factoryset.FsKeyActivity$FileInfo r5 = new com.ts.factoryset.FsKeyActivity$FileInfo
            r5.<init>()
            r7 = 0
            r5.len = r7
            java.io.File r6 = new java.io.File
            r6.<init>(r9)
            boolean r7 = r6.exists()
            if (r7 == 0) goto L_0x0019
            boolean r7 = r6.isDirectory()
            if (r7 == 0) goto L_0x001a
        L_0x0019:
            return r5
        L_0x001a:
            r3 = 0
            r0 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x003e, IOException -> 0x004d }
            r4.<init>(r6)     // Catch:{ FileNotFoundException -> 0x003e, IOException -> 0x004d }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x0076, all -> 0x006f }
            r1.<init>(r4)     // Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x0076, all -> 0x006f }
            int r7 = r4.available()     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x0079, all -> 0x0072 }
            byte[] r7 = new byte[r7]     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x0079, all -> 0x0072 }
            r5.data = r7     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x0079, all -> 0x0072 }
            byte[] r7 = r5.data     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x0079, all -> 0x0072 }
            int r7 = r1.read(r7)     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x0079, all -> 0x0072 }
            r5.len = r7     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x0079, all -> 0x0072 }
            if (r1 == 0) goto L_0x006c
            r1.close()     // Catch:{ IOException -> 0x0068 }
            r0 = r1
            r3 = r4
            goto L_0x0019
        L_0x003e:
            r2 = move-exception
        L_0x003f:
            r2.printStackTrace()     // Catch:{ all -> 0x005c }
            if (r0 == 0) goto L_0x0019
            r0.close()     // Catch:{ IOException -> 0x0048 }
            goto L_0x0019
        L_0x0048:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0019
        L_0x004d:
            r2 = move-exception
        L_0x004e:
            r2.printStackTrace()     // Catch:{ all -> 0x005c }
            if (r0 == 0) goto L_0x0019
            r0.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x0019
        L_0x0057:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0019
        L_0x005c:
            r7 = move-exception
        L_0x005d:
            if (r0 == 0) goto L_0x0062
            r0.close()     // Catch:{ IOException -> 0x0063 }
        L_0x0062:
            throw r7
        L_0x0063:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0062
        L_0x0068:
            r2 = move-exception
            r2.printStackTrace()
        L_0x006c:
            r0 = r1
            r3 = r4
            goto L_0x0019
        L_0x006f:
            r7 = move-exception
            r3 = r4
            goto L_0x005d
        L_0x0072:
            r7 = move-exception
            r0 = r1
            r3 = r4
            goto L_0x005d
        L_0x0076:
            r2 = move-exception
            r3 = r4
            goto L_0x004e
        L_0x0079:
            r2 = move-exception
            r0 = r1
            r3 = r4
            goto L_0x004e
        L_0x007d:
            r2 = move-exception
            r3 = r4
            goto L_0x003f
        L_0x0080:
            r2 = move-exception
            r0 = r1
            r3 = r4
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.factoryset.FsKeyActivity.getFileData(java.lang.String):com.ts.factoryset.FsKeyActivity$FileInfo");
    }

    public boolean getKeyFileData(String strName, int header, int fileSize) {
        boolean ret = false;
        String[] mStrKeyFile = MainSet.GetInstance().GetMountedStorage();
        if (mStrKeyFile == null) {
            return false;
        }
        int i = 0;
        while (true) {
            if (i >= mStrKeyFile.length) {
                break;
            }
            Log.i("xxxxx", mStrKeyFile[i]);
            FileInfo info = getFileData(String.valueOf(mStrKeyFile[i]) + "/" + strName);
            if (info.len == fileSize) {
                int keyNum = (fileSize - 8) / 4;
                if (byteArrayToInt(info.data, 0) == header && byteArrayToInt(info.data, 4) == keyNum) {
                    resetKeyData();
                    int offset = 8;
                    for (int j = 0; j < keyNum; j++) {
                        this.mKeyVal[j] = byteArrayToInt(info.data, offset);
                        offset += 4;
                    }
                    ret = true;
                }
            }
            i++;
        }
        return ret;
    }

    public static int byteArrayToInt(byte[] b, int offset) {
        return (b[offset + 0] & 255) | ((b[offset + 1] & 255) << 8) | ((b[offset + 2] & 255) << WrcManager.WrcCallback.KEY_CENTER) | ((b[offset + 3] & 255) << 24);
    }

    /* access modifiers changed from: package-private */
    public void resetKeyData() {
        for (int i = 0; i < this.mKeyVal.length; i++) {
            this.mKeyVal[i] = 0;
        }
    }
}
